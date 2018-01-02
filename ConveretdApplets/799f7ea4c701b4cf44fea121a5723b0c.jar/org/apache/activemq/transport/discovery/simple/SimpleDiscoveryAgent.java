// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.discovery.simple;

import org.slf4j.LoggerFactory;
import java.util.Map;
import org.apache.activemq.thread.DefaultThreadPools;
import org.apache.activemq.util.MDCHelper;
import java.net.URI;
import org.apache.activemq.command.DiscoveryEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.transport.discovery.DiscoveryListener;
import org.slf4j.Logger;
import org.apache.activemq.transport.discovery.DiscoveryAgent;

public class SimpleDiscoveryAgent implements DiscoveryAgent
{
    private static final Logger LOG;
    private long initialReconnectDelay;
    private long maxReconnectDelay;
    private long backOffMultiplier;
    private boolean useExponentialBackOff;
    private int maxReconnectAttempts;
    private final Object sleepMutex;
    private long minConnectTime;
    private DiscoveryListener listener;
    private String[] services;
    private final AtomicBoolean running;
    
    public SimpleDiscoveryAgent() {
        this.initialReconnectDelay = 1000L;
        this.maxReconnectDelay = 30000L;
        this.backOffMultiplier = 2L;
        this.useExponentialBackOff = true;
        this.sleepMutex = new Object();
        this.minConnectTime = 5000L;
        this.services = new String[0];
        this.running = new AtomicBoolean(false);
    }
    
    @Override
    public void setDiscoveryListener(final DiscoveryListener listener) {
        this.listener = listener;
    }
    
    @Override
    public void registerService(final String name) throws IOException {
    }
    
    @Override
    public void start() throws Exception {
        this.running.set(true);
        for (int i = 0; i < this.services.length; ++i) {
            this.listener.onServiceAdd(new SimpleDiscoveryEvent(this.services[i]));
        }
    }
    
    @Override
    public void stop() throws Exception {
        this.running.set(false);
        synchronized (this.sleepMutex) {
            this.sleepMutex.notifyAll();
        }
    }
    
    public String[] getServices() {
        return this.services;
    }
    
    public void setServices(final String services) {
        this.services = services.split(",");
    }
    
    public void setServices(final String[] services) {
        this.services = services;
    }
    
    public void setServices(final URI[] services) {
        this.services = new String[services.length];
        for (int i = 0; i < services.length; ++i) {
            this.services[i] = services[i].toString();
        }
    }
    
    @Override
    public void serviceFailed(final DiscoveryEvent devent) throws IOException {
        final SimpleDiscoveryEvent event = (SimpleDiscoveryEvent)devent;
        if (event.failed.compareAndSet(false, true)) {
            this.listener.onServiceRemove(event);
            final Map context = MDCHelper.getCopyOfContextMap();
            DefaultThreadPools.getDefaultTaskRunnerFactory().execute(new Runnable() {
                @Override
                public void run() {
                    MDCHelper.setContextMap(context);
                    if (event.connectTime + SimpleDiscoveryAgent.this.minConnectTime > System.currentTimeMillis()) {
                        SimpleDiscoveryAgent.LOG.debug("Failure occurred soon after the discovery event was generated.  It will be classified as a connection failure: " + event);
                        event.connectFailures++;
                        if (SimpleDiscoveryAgent.this.maxReconnectAttempts > 0 && event.connectFailures >= SimpleDiscoveryAgent.this.maxReconnectAttempts) {
                            SimpleDiscoveryAgent.LOG.debug("Reconnect attempts exceeded " + SimpleDiscoveryAgent.this.maxReconnectAttempts + " tries.  Reconnecting has been disabled.");
                            return;
                        }
                        synchronized (SimpleDiscoveryAgent.this.sleepMutex) {
                            try {
                                if (!SimpleDiscoveryAgent.this.running.get()) {
                                    return;
                                }
                                SimpleDiscoveryAgent.LOG.debug("Waiting " + event.reconnectDelay + " ms before attempting to reconnect.");
                                SimpleDiscoveryAgent.this.sleepMutex.wait(event.reconnectDelay);
                            }
                            catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                        if (!SimpleDiscoveryAgent.this.useExponentialBackOff) {
                            event.reconnectDelay = SimpleDiscoveryAgent.this.initialReconnectDelay;
                        }
                        else {
                            event.reconnectDelay *= SimpleDiscoveryAgent.this.backOffMultiplier;
                            if (event.reconnectDelay > SimpleDiscoveryAgent.this.maxReconnectDelay) {
                                event.reconnectDelay = SimpleDiscoveryAgent.this.maxReconnectDelay;
                            }
                        }
                    }
                    else {
                        event.connectFailures = 0;
                        event.reconnectDelay = SimpleDiscoveryAgent.this.initialReconnectDelay;
                    }
                    if (!SimpleDiscoveryAgent.this.running.get()) {
                        return;
                    }
                    event.connectTime = System.currentTimeMillis();
                    event.failed.set(false);
                    SimpleDiscoveryAgent.this.listener.onServiceAdd(event);
                }
            }, "Simple Discovery Agent");
        }
    }
    
    public long getBackOffMultiplier() {
        return this.backOffMultiplier;
    }
    
    public void setBackOffMultiplier(final long backOffMultiplier) {
        this.backOffMultiplier = backOffMultiplier;
    }
    
    public long getInitialReconnectDelay() {
        return this.initialReconnectDelay;
    }
    
    public void setInitialReconnectDelay(final long initialReconnectDelay) {
        this.initialReconnectDelay = initialReconnectDelay;
    }
    
    public int getMaxReconnectAttempts() {
        return this.maxReconnectAttempts;
    }
    
    public void setMaxReconnectAttempts(final int maxReconnectAttempts) {
        this.maxReconnectAttempts = maxReconnectAttempts;
    }
    
    public long getMaxReconnectDelay() {
        return this.maxReconnectDelay;
    }
    
    public void setMaxReconnectDelay(final long maxReconnectDelay) {
        this.maxReconnectDelay = maxReconnectDelay;
    }
    
    public long getMinConnectTime() {
        return this.minConnectTime;
    }
    
    public void setMinConnectTime(final long minConnectTime) {
        this.minConnectTime = minConnectTime;
    }
    
    public boolean isUseExponentialBackOff() {
        return this.useExponentialBackOff;
    }
    
    public void setUseExponentialBackOff(final boolean useExponentialBackOff) {
        this.useExponentialBackOff = useExponentialBackOff;
    }
    
    static {
        LOG = LoggerFactory.getLogger(SimpleDiscoveryAgent.class);
    }
    
    class SimpleDiscoveryEvent extends DiscoveryEvent
    {
        private int connectFailures;
        private long reconnectDelay;
        private long connectTime;
        private AtomicBoolean failed;
        
        public SimpleDiscoveryEvent(final String service) {
            super(service);
            this.reconnectDelay = SimpleDiscoveryAgent.this.initialReconnectDelay;
            this.connectTime = System.currentTimeMillis();
            this.failed = new AtomicBoolean(false);
        }
    }
}
