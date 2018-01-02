// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.util;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerPluginSupport;

public class TimeStampingBrokerPlugin extends BrokerPluginSupport
{
    private static final Logger LOG;
    long zeroExpirationOverride;
    long ttlCeiling;
    boolean futureOnly;
    boolean processNetworkMessages;
    
    public TimeStampingBrokerPlugin() {
        this.zeroExpirationOverride = 0L;
        this.ttlCeiling = 0L;
        this.futureOnly = false;
        this.processNetworkMessages = false;
    }
    
    public void setZeroExpirationOverride(final long ttl) {
        this.zeroExpirationOverride = ttl;
    }
    
    public void setTtlCeiling(final long ttlCeiling) {
        this.ttlCeiling = ttlCeiling;
    }
    
    public void setFutureOnly(final boolean futureOnly) {
        this.futureOnly = futureOnly;
    }
    
    public void setProcessNetworkMessages(final Boolean processNetworkMessages) {
        this.processNetworkMessages = processNetworkMessages;
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message message) throws Exception {
        if (message.getTimestamp() > 0L && (this.processNetworkMessages || message.getBrokerPath() == null || message.getBrokerPath().length == 0)) {
            final long oldExpiration = message.getExpiration();
            final long newTimeStamp = System.currentTimeMillis();
            long timeToLive = this.zeroExpirationOverride;
            final long oldTimestamp = message.getTimestamp();
            if (oldExpiration > 0L) {
                timeToLive = oldExpiration - oldTimestamp;
            }
            if (timeToLive > 0L && this.ttlCeiling > 0L && timeToLive > this.ttlCeiling) {
                timeToLive = this.ttlCeiling;
            }
            final long expiration = timeToLive + newTimeStamp;
            if (!this.futureOnly || expiration > oldExpiration) {
                if (timeToLive > 0L && expiration > 0L) {
                    message.setExpiration(expiration);
                }
                message.setTimestamp(newTimeStamp);
                if (TimeStampingBrokerPlugin.LOG.isDebugEnabled()) {
                    TimeStampingBrokerPlugin.LOG.debug("Set message " + message.getMessageId() + " timestamp from " + oldTimestamp + " to " + newTimeStamp);
                }
            }
        }
        super.send(producerExchange, message);
    }
    
    static {
        LOG = LoggerFactory.getLogger(TimeStampingBrokerPlugin.class);
    }
}
