// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.discovery.rendezvous;

import org.slf4j.LoggerFactory;
import org.apache.activemq.util.MapHelper;
import java.net.UnknownHostException;
import org.apache.activemq.jmdns.ServiceEvent;
import org.apache.activemq.command.DiscoveryEvent;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.activemq.util.JMSExceptionSupport;
import java.io.IOException;
import org.apache.activemq.jmdns.ServiceInfo;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.activemq.transport.discovery.DiscoveryListener;
import java.net.InetAddress;
import org.apache.activemq.jmdns.JmDNS;
import org.slf4j.Logger;
import org.apache.activemq.jmdns.ServiceListener;
import org.apache.activemq.transport.discovery.DiscoveryAgent;

public class RendezvousDiscoveryAgent implements DiscoveryAgent, ServiceListener
{
    private static final Logger LOG;
    private static final String TYPE_SUFFIX = "ActiveMQ-4.";
    private JmDNS jmdns;
    private InetAddress localAddress;
    private String localhost;
    private int weight;
    private int priority;
    private DiscoveryListener listener;
    private String group;
    private final CopyOnWriteArrayList<ServiceInfo> serviceInfos;
    
    public RendezvousDiscoveryAgent() {
        this.group = "default";
        this.serviceInfos = new CopyOnWriteArrayList<ServiceInfo>();
    }
    
    @Override
    public void start() throws Exception {
        if (this.group == null) {
            throw new IOException("You must specify a group to discover");
        }
        String type = this.getType();
        if (!type.endsWith(".")) {
            RendezvousDiscoveryAgent.LOG.warn("The type '" + type + "' should end with '.' to be a valid Rendezvous type");
            type += ".";
        }
        try {
            this.getJmdns();
            if (this.listener != null) {
                RendezvousDiscoveryAgent.LOG.info("Discovering service of type: " + type);
                this.jmdns.addServiceListener(type, this);
            }
        }
        catch (IOException e) {
            JMSExceptionSupport.create("Failed to start JmDNS service: " + e, e);
        }
    }
    
    @Override
    public void stop() {
        if (this.jmdns != null) {
            for (final ServiceInfo si : this.serviceInfos) {
                this.jmdns.unregisterService(si);
            }
            final JmDNS closeTarget = this.jmdns;
            final Thread thread = new Thread() {
                @Override
                public void run() {
                    closeTarget.close();
                }
            };
            thread.setDaemon(true);
            thread.start();
            this.jmdns = null;
        }
    }
    
    @Override
    public void registerService(final String name) throws IOException {
        final ServiceInfo si = this.createServiceInfo(name, new HashMap());
        this.serviceInfos.add(si);
        this.getJmdns().registerService(si);
    }
    
    public void addService(final JmDNS jmDNS, final String type, final String name) {
        if (RendezvousDiscoveryAgent.LOG.isDebugEnabled()) {
            RendezvousDiscoveryAgent.LOG.debug("addService with type: " + type + " name: " + name);
        }
        if (this.listener != null) {
            this.listener.onServiceAdd(new DiscoveryEvent(name));
        }
        jmDNS.requestServiceInfo(type, name);
    }
    
    public void removeService(final JmDNS jmDNS, final String type, final String name) {
        if (RendezvousDiscoveryAgent.LOG.isDebugEnabled()) {
            RendezvousDiscoveryAgent.LOG.debug("removeService with type: " + type + " name: " + name);
        }
        if (this.listener != null) {
            this.listener.onServiceRemove(new DiscoveryEvent(name));
        }
    }
    
    @Override
    public void serviceAdded(final ServiceEvent event) {
        this.addService(event.getDNS(), event.getType(), event.getName());
    }
    
    @Override
    public void serviceRemoved(final ServiceEvent event) {
        this.removeService(event.getDNS(), event.getType(), event.getName());
    }
    
    @Override
    public void serviceResolved(final ServiceEvent event) {
    }
    
    public void resolveService(final JmDNS jmDNS, final String type, final String name, final ServiceInfo serviceInfo) {
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(final int priority) {
        this.priority = priority;
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public void setWeight(final int weight) {
        this.weight = weight;
    }
    
    public JmDNS getJmdns() throws IOException {
        if (this.jmdns == null) {
            this.jmdns = this.createJmDNS();
        }
        return this.jmdns;
    }
    
    public void setJmdns(final JmDNS jmdns) {
        this.jmdns = jmdns;
    }
    
    public InetAddress getLocalAddress() throws UnknownHostException {
        if (this.localAddress == null) {
            this.localAddress = this.createLocalAddress();
        }
        return this.localAddress;
    }
    
    public void setLocalAddress(final InetAddress localAddress) {
        this.localAddress = localAddress;
    }
    
    public String getLocalhost() {
        return this.localhost;
    }
    
    public void setLocalhost(final String localhost) {
        this.localhost = localhost;
    }
    
    protected ServiceInfo createServiceInfo(final String name, final Map map) {
        final int port = MapHelper.getInt(map, "port", 0);
        final String type = this.getType();
        if (RendezvousDiscoveryAgent.LOG.isDebugEnabled()) {
            RendezvousDiscoveryAgent.LOG.debug("Registering service type: " + type + " name: " + name + " details: " + map);
        }
        return new ServiceInfo(type, name + "." + type, port, this.weight, this.priority, "");
    }
    
    protected JmDNS createJmDNS() throws IOException {
        return JmDNSFactory.create(this.getLocalAddress());
    }
    
    protected InetAddress createLocalAddress() throws UnknownHostException {
        if (this.localhost != null) {
            return InetAddress.getByName(this.localhost);
        }
        return InetAddress.getLocalHost();
    }
    
    @Override
    public void setDiscoveryListener(final DiscoveryListener listener) {
        this.listener = listener;
    }
    
    public String getGroup() {
        return this.group;
    }
    
    public void setGroup(final String group) {
        this.group = group;
    }
    
    public String getType() {
        return "_" + this.group + "." + "ActiveMQ-4.";
    }
    
    @Override
    public void serviceFailed(final DiscoveryEvent event) throws IOException {
    }
    
    static {
        LOG = LoggerFactory.getLogger(RendezvousDiscoveryAgent.class);
    }
}
