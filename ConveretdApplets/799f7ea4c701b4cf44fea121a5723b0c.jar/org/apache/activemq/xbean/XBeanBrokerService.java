// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.xbean;

import org.slf4j.LoggerFactory;
import javax.annotation.PreDestroy;
import java.io.IOException;
import org.apache.activemq.usage.SystemUsage;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerService;

public class XBeanBrokerService extends BrokerService
{
    private static final transient Logger LOG;
    private boolean start;
    
    public XBeanBrokerService() {
        this.start = true;
    }
    
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        this.ensureSystemUsageHasStore();
        if (this.shouldAutostart()) {
            this.start();
        }
    }
    
    @Override
    protected boolean shouldAutostart() {
        return this.start;
    }
    
    private void ensureSystemUsageHasStore() throws IOException {
        final SystemUsage usage = this.getSystemUsage();
        if (usage.getStoreUsage().getStore() == null) {
            usage.getStoreUsage().setStore(this.getPersistenceAdapter());
        }
        if (usage.getTempUsage().getStore() == null) {
            usage.getTempUsage().setStore(this.getTempDataStore());
        }
    }
    
    @PreDestroy
    public void destroy() throws Exception {
        this.stop();
    }
    
    public void setStart(final boolean start) {
        this.start = start;
    }
    
    @Deprecated
    public void setDestroyApplicationContextOnShutdown(final boolean destroy) {
        XBeanBrokerService.LOG.warn("destroyApplicationContextOnShutdown parameter is deprecated, please use shutdown hooks instead");
    }
    
    @Deprecated
    public void setDestroyApplicationContextOnStop(final boolean destroy) {
        XBeanBrokerService.LOG.warn("destroyApplicationContextOnStop parameter is deprecated, please use shutdown hooks instead");
    }
    
    static {
        LOG = LoggerFactory.getLogger(XBeanBrokerService.class);
    }
}
