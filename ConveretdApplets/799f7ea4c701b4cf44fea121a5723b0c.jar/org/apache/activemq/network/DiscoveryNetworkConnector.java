// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.slf4j.LoggerFactory;
import javax.management.ObjectName;
import org.apache.activemq.broker.BrokerService;
import java.util.Iterator;
import org.apache.activemq.util.ServiceStopper;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.transport.TransportDisposedIOException;
import org.apache.activemq.Service;
import org.apache.activemq.util.ServiceSupport;
import org.apache.activemq.transport.TransportFactory;
import org.apache.activemq.broker.SslContext;
import org.apache.activemq.command.DiscoveryEvent;
import java.net.URISyntaxException;
import org.apache.activemq.util.IntrospectionSupport;
import org.apache.activemq.util.URISupport;
import org.apache.activemq.transport.discovery.DiscoveryAgentFactory;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.activemq.transport.discovery.DiscoveryAgent;
import org.slf4j.Logger;
import org.apache.activemq.transport.discovery.DiscoveryListener;

public class DiscoveryNetworkConnector extends NetworkConnector implements DiscoveryListener
{
    private static final Logger LOG;
    private DiscoveryAgent discoveryAgent;
    private Map<String, String> parameters;
    
    public DiscoveryNetworkConnector() {
    }
    
    public DiscoveryNetworkConnector(final URI discoveryURI) throws IOException {
        this.setUri(discoveryURI);
    }
    
    public void setUri(final URI discoveryURI) throws IOException {
        this.setDiscoveryAgent(DiscoveryAgentFactory.createDiscoveryAgent(discoveryURI));
        try {
            this.parameters = URISupport.parseParameters(discoveryURI);
            IntrospectionSupport.setProperties(this.getDiscoveryAgent(), this.parameters);
        }
        catch (URISyntaxException e) {
            DiscoveryNetworkConnector.LOG.warn("failed to parse query parameters from discoveryURI: " + discoveryURI, e);
        }
    }
    
    @Override
    public void onServiceAdd(final DiscoveryEvent event) {
        if (this.serviceSupport.isStopped() || this.serviceSupport.isStopping()) {
            return;
        }
        final String url = event.getServiceName();
        if (url != null) {
            URI uri;
            try {
                uri = new URI(url);
            }
            catch (URISyntaxException e) {
                DiscoveryNetworkConnector.LOG.warn("Could not connect to remote URI: " + url + " due to bad URI syntax: " + e, e);
                return;
            }
            if (this.bridges.containsKey(uri)) {
                DiscoveryNetworkConnector.LOG.debug("Discovery agent generated a duplicate onServiceAdd event for: " + uri);
                return;
            }
            if (this.localURI.equals(uri) || (this.connectionFilter != null && !this.connectionFilter.connectTo(uri))) {
                DiscoveryNetworkConnector.LOG.debug("not connecting loopback: " + uri);
                return;
            }
            URI connectUri = uri;
            try {
                connectUri = URISupport.applyParameters(connectUri, this.parameters, "discovered.");
            }
            catch (URISyntaxException e2) {
                DiscoveryNetworkConnector.LOG.warn("could not apply query parameters: " + this.parameters + " to: " + connectUri, e2);
            }
            DiscoveryNetworkConnector.LOG.info("Establishing network connection from " + this.localURI + " to " + connectUri);
            Transport remoteTransport;
            Transport localTransport;
            try {
                SslContext.setCurrentSslContext(this.getBrokerService().getSslContext());
                try {
                    remoteTransport = TransportFactory.connect(connectUri);
                }
                catch (Exception e3) {
                    DiscoveryNetworkConnector.LOG.warn("Could not connect to remote URI: " + connectUri + ": " + e3.getMessage());
                    DiscoveryNetworkConnector.LOG.debug("Connection failure exception: " + e3, e3);
                    return;
                }
                try {
                    localTransport = this.createLocalTransport();
                }
                catch (Exception e3) {
                    ServiceSupport.dispose(remoteTransport);
                    DiscoveryNetworkConnector.LOG.warn("Could not connect to local URI: " + this.localURI + ": " + e3.getMessage());
                    DiscoveryNetworkConnector.LOG.debug("Connection failure exception: " + e3, e3);
                    return;
                }
            }
            finally {
                SslContext.setCurrentSslContext(null);
            }
            final NetworkBridge bridge = this.createBridge(localTransport, remoteTransport, event);
            try {
                bridge.start();
                this.bridges.put(uri, bridge);
            }
            catch (TransportDisposedIOException e6) {
                DiscoveryNetworkConnector.LOG.warn("Network bridge between: " + this.localURI + " and: " + uri + " was correctly stopped before it was correctly started.");
            }
            catch (Exception e4) {
                ServiceSupport.dispose(localTransport);
                ServiceSupport.dispose(remoteTransport);
                DiscoveryNetworkConnector.LOG.warn("Could not start network bridge between: " + this.localURI + " and: " + uri + " due to: " + e4);
                DiscoveryNetworkConnector.LOG.debug("Start failure exception: " + e4, e4);
                try {
                    this.discoveryAgent.serviceFailed(event);
                }
                catch (IOException e5) {
                    DiscoveryNetworkConnector.LOG.debug("Discovery agent failure while handling failure event: " + e5.getMessage(), e5);
                }
            }
        }
    }
    
    @Override
    public void onServiceRemove(final DiscoveryEvent event) {
        final String url = event.getServiceName();
        if (url != null) {
            URI uri;
            try {
                uri = new URI(url);
            }
            catch (URISyntaxException e) {
                DiscoveryNetworkConnector.LOG.warn("Could not connect to remote URI: " + url + " due to bad URI syntax: " + e, e);
                return;
            }
            final NetworkBridge bridge = this.bridges.remove(uri);
            if (bridge == null) {
                return;
            }
            ServiceSupport.dispose(bridge);
        }
    }
    
    public DiscoveryAgent getDiscoveryAgent() {
        return this.discoveryAgent;
    }
    
    public void setDiscoveryAgent(final DiscoveryAgent discoveryAgent) {
        this.discoveryAgent = discoveryAgent;
        if (discoveryAgent != null) {
            this.discoveryAgent.setDiscoveryListener(this);
        }
    }
    
    @Override
    protected void handleStart() throws Exception {
        if (this.discoveryAgent == null) {
            throw new IllegalStateException("You must configure the 'discoveryAgent' property");
        }
        this.discoveryAgent.start();
        super.handleStart();
    }
    
    @Override
    protected void handleStop(final ServiceStopper stopper) throws Exception {
        for (final NetworkBridge bridge : this.bridges.values()) {
            try {
                bridge.stop();
            }
            catch (Exception e) {
                stopper.onException(this, e);
            }
        }
        try {
            this.discoveryAgent.stop();
        }
        catch (Exception e2) {
            stopper.onException(this, e2);
        }
        super.handleStop(stopper);
    }
    
    protected NetworkBridge createBridge(final Transport localTransport, final Transport remoteTransport, final DiscoveryEvent event) {
        class DiscoverNetworkBridgeListener extends MBeanNetworkListener
        {
            final /* synthetic */ DiscoveryEvent val$event;
            
            public DiscoverNetworkBridgeListener(final BrokerService connectorName, final ObjectName val$event) {
                this.val$event = (DiscoveryEvent)val$event;
                super(brokerService, (ObjectName)connectorName);
            }
            
            @Override
            public void bridgeFailed() {
                if (!DiscoveryNetworkConnector.this.serviceSupport.isStopped()) {
                    try {
                        DiscoveryNetworkConnector.this.discoveryAgent.serviceFailed(this.val$event);
                    }
                    catch (IOException ex) {}
                }
            }
        }
        final NetworkBridgeListener listener = new DiscoverNetworkBridgeListener(this.getBrokerService(), this.getObjectName(), event);
        final DemandForwardingBridge result = NetworkBridgeFactory.createBridge(this, localTransport, remoteTransport, listener);
        result.setBrokerService(this.getBrokerService());
        return this.configureBridge(result);
    }
    
    @Override
    public String toString() {
        return "DiscoveryNetworkConnector:" + this.getName() + ":" + this.getBrokerService();
    }
    
    static {
        LOG = LoggerFactory.getLogger(DiscoveryNetworkConnector.class);
    }
}
