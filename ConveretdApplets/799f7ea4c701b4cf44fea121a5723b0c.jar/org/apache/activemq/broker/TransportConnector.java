// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker;

import java.util.regex.Pattern;
import java.util.StringTokenizer;
import org.apache.activemq.command.ConnectionControl;
import org.apache.activemq.transport.discovery.DiscoveryAgentFactory;
import org.apache.activemq.transport.TransportFactory;
import java.util.Iterator;
import org.apache.activemq.util.ServiceStopper;
import org.apache.activemq.Service;
import org.apache.activemq.util.ServiceSupport;
import org.apache.activemq.thread.DefaultThreadPools;
import org.apache.activemq.transport.Transport;
import java.util.Map;
import org.apache.activemq.transport.TransportAcceptListener;
import org.apache.activemq.util.MDCHelper;
import java.net.URISyntaxException;
import java.io.IOException;
import org.apache.activemq.broker.jmx.ManagedTransportConnector;
import javax.management.ObjectName;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.slf4j.LoggerFactory;
import org.apache.activemq.broker.region.ConnectorStatistics;
import org.apache.activemq.transport.discovery.DiscoveryAgent;
import org.apache.activemq.security.MessageAuthorizationPolicy;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.command.BrokerInfo;
import java.net.URI;
import org.apache.activemq.transport.TransportServer;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;

public class TransportConnector implements Connector, BrokerServiceAware
{
    final Logger LOG;
    protected CopyOnWriteArrayList<TransportConnection> connections;
    protected TransportStatusDetector statusDector;
    private BrokerService brokerService;
    private TransportServer server;
    private URI uri;
    private BrokerInfo brokerInfo;
    private TaskRunnerFactory taskRunnerFactory;
    private MessageAuthorizationPolicy messageAuthorizationPolicy;
    private DiscoveryAgent discoveryAgent;
    private final ConnectorStatistics statistics;
    private URI discoveryUri;
    private URI connectUri;
    private String name;
    private boolean disableAsyncDispatch;
    private boolean enableStatusMonitor;
    private Broker broker;
    private boolean updateClusterClients;
    private boolean rebalanceClusterClients;
    private boolean updateClusterClientsOnRemove;
    private String updateClusterFilter;
    
    public TransportConnector() {
        this.LOG = LoggerFactory.getLogger(TransportConnector.class);
        this.connections = new CopyOnWriteArrayList<TransportConnection>();
        this.brokerInfo = new BrokerInfo();
        this.statistics = new ConnectorStatistics();
        this.enableStatusMonitor = false;
        this.updateClusterClients = false;
        this.updateClusterClientsOnRemove = false;
    }
    
    public TransportConnector(final TransportServer server) {
        this();
        this.setServer(server);
        if (server != null && server.getConnectURI() != null) {
            final URI uri = server.getConnectURI();
            if (uri != null && uri.getScheme().equals("vm")) {
                this.setEnableStatusMonitor(false);
            }
        }
    }
    
    public CopyOnWriteArrayList<TransportConnection> getConnections() {
        return this.connections;
    }
    
    public ManagedTransportConnector asManagedConnector(final ManagementContext context, final ObjectName connectorName) throws IOException, URISyntaxException {
        final ManagedTransportConnector rc = new ManagedTransportConnector(context, connectorName, this.getServer());
        rc.setBrokerInfo(this.getBrokerInfo());
        rc.setConnectUri(this.getConnectUri());
        rc.setDisableAsyncDispatch(this.isDisableAsyncDispatch());
        rc.setDiscoveryAgent(this.getDiscoveryAgent());
        rc.setDiscoveryUri(this.getDiscoveryUri());
        rc.setEnableStatusMonitor(this.isEnableStatusMonitor());
        rc.setMessageAuthorizationPolicy(this.getMessageAuthorizationPolicy());
        rc.setName(this.getName());
        rc.setTaskRunnerFactory(this.getTaskRunnerFactory());
        rc.setUri(this.getUri());
        rc.setBrokerService(this.brokerService);
        rc.setUpdateClusterClients(this.isUpdateClusterClients());
        rc.setRebalanceClusterClients(this.isRebalanceClusterClients());
        rc.setUpdateClusterFilter(this.getUpdateClusterFilter());
        rc.setUpdateClusterClientsOnRemove(this.isUpdateClusterClientsOnRemove());
        return rc;
    }
    
    @Override
    public BrokerInfo getBrokerInfo() {
        return this.brokerInfo;
    }
    
    public void setBrokerInfo(final BrokerInfo brokerInfo) {
        this.brokerInfo = brokerInfo;
    }
    
    @Deprecated
    public void setBrokerName(final String name) {
        if (this.brokerInfo == null) {
            this.brokerInfo = new BrokerInfo();
        }
        this.brokerInfo.setBrokerName(name);
    }
    
    public TransportServer getServer() throws IOException, URISyntaxException {
        if (this.server == null) {
            this.setServer(this.createTransportServer());
        }
        return this.server;
    }
    
    public void setServer(final TransportServer server) {
        this.server = server;
    }
    
    public URI getUri() {
        if (this.uri == null) {
            try {
                this.uri = this.getConnectUri();
            }
            catch (Throwable t) {}
        }
        return this.uri;
    }
    
    public void setUri(final URI uri) {
        this.uri = uri;
    }
    
    public TaskRunnerFactory getTaskRunnerFactory() {
        return this.taskRunnerFactory;
    }
    
    public void setTaskRunnerFactory(final TaskRunnerFactory taskRunnerFactory) {
        this.taskRunnerFactory = taskRunnerFactory;
    }
    
    @Override
    public ConnectorStatistics getStatistics() {
        return this.statistics;
    }
    
    public MessageAuthorizationPolicy getMessageAuthorizationPolicy() {
        return this.messageAuthorizationPolicy;
    }
    
    public void setMessageAuthorizationPolicy(final MessageAuthorizationPolicy messageAuthorizationPolicy) {
        this.messageAuthorizationPolicy = messageAuthorizationPolicy;
    }
    
    @Override
    public void start() throws Exception {
        this.broker = this.brokerService.getBroker();
        this.brokerInfo.setBrokerName(this.broker.getBrokerName());
        this.brokerInfo.setBrokerId(this.broker.getBrokerId());
        this.brokerInfo.setPeerBrokerInfos(this.broker.getPeerBrokerInfos());
        this.brokerInfo.setFaultTolerantConfiguration(this.broker.isFaultTolerantConfiguration());
        this.brokerInfo.setBrokerURL(this.getServer().getConnectURI().toString());
        final Map context = MDCHelper.getCopyOfContextMap();
        this.getServer().setAcceptListener(new TransportAcceptListener() {
            @Override
            public void onAccept(final Transport transport) {
                try {
                    DefaultThreadPools.getDefaultTaskRunnerFactory().execute(new Runnable() {
                        @Override
                        public void run() {
                            MDCHelper.setContextMap(context);
                            try {
                                final Connection connection = TransportConnector.this.createConnection(transport);
                                connection.start();
                            }
                            catch (Exception e) {
                                ServiceSupport.dispose(transport);
                                TransportAcceptListener.this.onAcceptError(e);
                            }
                        }
                    });
                }
                catch (Exception e) {
                    final String remoteHost = transport.getRemoteAddress();
                    ServiceSupport.dispose(transport);
                    this.onAcceptError(e, remoteHost);
                }
            }
            
            @Override
            public void onAcceptError(final Exception error) {
                this.onAcceptError(error, null);
            }
            
            private void onAcceptError(final Exception error, final String remoteHost) {
                TransportConnector.this.LOG.error("Could not accept connection " + ((remoteHost == null) ? "" : ("from " + remoteHost)) + ": " + error);
                TransportConnector.this.LOG.debug("Reason: " + error, error);
            }
        });
        this.getServer().setBrokerInfo(this.brokerInfo);
        this.getServer().start();
        final DiscoveryAgent da = this.getDiscoveryAgent();
        if (da != null) {
            da.registerService(this.getPublishableConnectString());
            da.start();
        }
        if (this.enableStatusMonitor) {
            (this.statusDector = new TransportStatusDetector(this)).start();
        }
        this.LOG.info("Connector " + this.getName() + " Started");
    }
    
    public String getPublishableConnectString() throws Exception {
        String publishableConnectString = null;
        final URI theConnectURI = this.getConnectUri();
        if (theConnectURI != null) {
            publishableConnectString = theConnectURI.toString();
            if (theConnectURI.getRawQuery() != null) {
                publishableConnectString = publishableConnectString.substring(0, publishableConnectString.indexOf(theConnectURI.getRawQuery()) - 1);
            }
        }
        if (this.LOG.isDebugEnabled()) {
            this.LOG.debug("Publishing: " + publishableConnectString + " for broker transport URI: " + theConnectURI);
        }
        return publishableConnectString;
    }
    
    @Override
    public void stop() throws Exception {
        final ServiceStopper ss = new ServiceStopper();
        if (this.discoveryAgent != null) {
            ss.stop(this.discoveryAgent);
        }
        if (this.server != null) {
            ss.stop(this.server);
            this.server = null;
        }
        if (this.statusDector != null) {
            this.statusDector.stop();
        }
        for (final TransportConnection c : this.connections) {
            ss.stop(c);
        }
        ss.throwFirstException();
        this.LOG.info("Connector " + this.getName() + " Stopped");
    }
    
    protected Connection createConnection(final Transport transport) throws IOException {
        final TransportConnection answer = new TransportConnection(this, transport, this.broker, this.disableAsyncDispatch ? null : this.taskRunnerFactory);
        final boolean statEnabled = this.getStatistics().isEnabled();
        answer.getStatistics().setEnabled(statEnabled);
        answer.setMessageAuthorizationPolicy(this.messageAuthorizationPolicy);
        return answer;
    }
    
    protected TransportServer createTransportServer() throws IOException, URISyntaxException {
        if (this.uri == null) {
            throw new IllegalArgumentException("You must specify either a server or uri property");
        }
        if (this.brokerService == null) {
            throw new IllegalArgumentException("You must specify the brokerService property. Maybe this connector should be added to a broker?");
        }
        return TransportFactory.bind(this.brokerService, this.uri);
    }
    
    public DiscoveryAgent getDiscoveryAgent() throws IOException {
        if (this.discoveryAgent == null) {
            this.discoveryAgent = this.createDiscoveryAgent();
        }
        return this.discoveryAgent;
    }
    
    protected DiscoveryAgent createDiscoveryAgent() throws IOException {
        if (this.discoveryUri != null) {
            return DiscoveryAgentFactory.createDiscoveryAgent(this.discoveryUri);
        }
        return null;
    }
    
    public void setDiscoveryAgent(final DiscoveryAgent discoveryAgent) {
        this.discoveryAgent = discoveryAgent;
    }
    
    public URI getDiscoveryUri() {
        return this.discoveryUri;
    }
    
    public void setDiscoveryUri(final URI discoveryUri) {
        this.discoveryUri = discoveryUri;
    }
    
    public URI getConnectUri() throws IOException, URISyntaxException {
        if (this.connectUri == null && this.server != null) {
            this.connectUri = this.server.getConnectURI();
        }
        return this.connectUri;
    }
    
    public void setConnectUri(final URI transportUri) {
        this.connectUri = transportUri;
    }
    
    public void onStarted(final TransportConnection connection) {
        this.connections.add(connection);
    }
    
    public void onStopped(final TransportConnection connection) {
        this.connections.remove(connection);
    }
    
    public String getName() {
        if (this.name == null) {
            this.uri = this.getUri();
            if (this.uri != null) {
                this.name = this.uri.toString();
            }
        }
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        String rc = this.getName();
        if (rc == null) {
            rc = super.toString();
        }
        return rc;
    }
    
    protected ConnectionControl getConnectionControl() {
        final boolean rebalance = this.isRebalanceClusterClients();
        String connectedBrokers = "";
        String self = "";
        if (this.isUpdateClusterClients()) {
            if (this.brokerService.getDefaultSocketURIString() != null) {
                self += this.brokerService.getDefaultSocketURIString();
                self += ",";
            }
            if (!rebalance) {
                connectedBrokers += self;
            }
            if (this.broker.getPeerBrokerInfos() != null) {
                for (final BrokerInfo info : this.broker.getPeerBrokerInfos()) {
                    if (this.isMatchesClusterFilter(info.getBrokerName())) {
                        connectedBrokers += info.getBrokerURL();
                        connectedBrokers += ",";
                    }
                }
            }
            if (rebalance) {
                connectedBrokers += self;
            }
        }
        final ConnectionControl control = new ConnectionControl();
        control.setConnectedBrokers(connectedBrokers);
        control.setRebalanceConnection(rebalance);
        return control;
    }
    
    @Override
    public void updateClientClusterInfo() {
        if (this.isRebalanceClusterClients() || this.isUpdateClusterClients()) {
            final ConnectionControl control = this.getConnectionControl();
            for (final Connection c : this.connections) {
                c.updateClient(control);
            }
        }
    }
    
    private boolean isMatchesClusterFilter(final String brokerName) {
        boolean result = true;
        String filter = this.getUpdateClusterFilter();
        if (filter != null) {
            filter = filter.trim();
            if (filter.length() > 0) {
                String token;
                for (StringTokenizer tokenizer = new StringTokenizer(filter, ","); result && tokenizer.hasMoreTokens(); result = this.isMatchesClusterFilter(brokerName, token)) {
                    token = tokenizer.nextToken();
                }
            }
        }
        return result;
    }
    
    private boolean isMatchesClusterFilter(final String brokerName, final String match) {
        boolean result = true;
        if (brokerName != null && match != null && brokerName.length() > 0 && match.length() > 0) {
            result = Pattern.matches(match, brokerName);
        }
        return result;
    }
    
    public boolean isDisableAsyncDispatch() {
        return this.disableAsyncDispatch;
    }
    
    public void setDisableAsyncDispatch(final boolean disableAsyncDispatch) {
        this.disableAsyncDispatch = disableAsyncDispatch;
    }
    
    public boolean isEnableStatusMonitor() {
        return this.enableStatusMonitor;
    }
    
    public void setEnableStatusMonitor(final boolean enableStatusMonitor) {
        this.enableStatusMonitor = enableStatusMonitor;
    }
    
    @Override
    public void setBrokerService(final BrokerService brokerService) {
        this.brokerService = brokerService;
    }
    
    public Broker getBroker() {
        return this.broker;
    }
    
    public BrokerService getBrokerService() {
        return this.brokerService;
    }
    
    @Override
    public boolean isUpdateClusterClients() {
        return this.updateClusterClients;
    }
    
    public void setUpdateClusterClients(final boolean updateClusterClients) {
        this.updateClusterClients = updateClusterClients;
    }
    
    @Override
    public boolean isRebalanceClusterClients() {
        return this.rebalanceClusterClients;
    }
    
    public void setRebalanceClusterClients(final boolean rebalanceClusterClients) {
        this.rebalanceClusterClients = rebalanceClusterClients;
    }
    
    @Override
    public boolean isUpdateClusterClientsOnRemove() {
        return this.updateClusterClientsOnRemove;
    }
    
    public void setUpdateClusterClientsOnRemove(final boolean updateClusterClientsOnRemove) {
        this.updateClusterClientsOnRemove = updateClusterClientsOnRemove;
    }
    
    public String getUpdateClusterFilter() {
        return this.updateClusterFilter;
    }
    
    public void setUpdateClusterFilter(final String updateClusterFilter) {
        this.updateClusterFilter = updateClusterFilter;
    }
}
