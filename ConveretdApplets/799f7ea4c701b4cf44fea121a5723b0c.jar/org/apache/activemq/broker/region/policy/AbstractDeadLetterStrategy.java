// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.policy;

import org.slf4j.LoggerFactory;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.command.Message;
import org.apache.activemq.ActiveMQMessageAudit;
import org.slf4j.Logger;

public abstract class AbstractDeadLetterStrategy implements DeadLetterStrategy
{
    private static final Logger LOG;
    private boolean processNonPersistent;
    private boolean processExpired;
    private ActiveMQMessageAudit audit;
    
    public AbstractDeadLetterStrategy() {
        this.processNonPersistent = false;
        this.processExpired = true;
        this.audit = new ActiveMQMessageAudit();
    }
    
    @Override
    public boolean isSendToDeadLetterQueue(final Message message) {
        boolean result = false;
        if (message != null) {
            result = true;
            if (this.audit.isDuplicate(message)) {
                result = false;
                if (AbstractDeadLetterStrategy.LOG.isDebugEnabled()) {
                    AbstractDeadLetterStrategy.LOG.debug("Not adding duplicate to DLQ: " + message.getMessageId() + ", dest: " + message.getDestination());
                }
            }
            if (!message.isPersistent() && !this.processNonPersistent) {
                result = false;
            }
            if (message.isExpired() && !this.processExpired) {
                result = false;
            }
        }
        return result;
    }
    
    @Override
    public boolean isProcessExpired() {
        return this.processExpired;
    }
    
    @Override
    public void setProcessExpired(final boolean processExpired) {
        this.processExpired = processExpired;
    }
    
    @Override
    public boolean isProcessNonPersistent() {
        return this.processNonPersistent;
    }
    
    @Override
    public void setProcessNonPersistent(final boolean processNonPersistent) {
        this.processNonPersistent = processNonPersistent;
    }
    
    static {
        LOG = LoggerFactory.getLogger(AbstractDeadLetterStrategy.class);
    }
}
