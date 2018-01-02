// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.store.MessageStore;
import org.slf4j.Logger;
import org.apache.activemq.store.MessageRecoveryListener;

final class RecoveryListenerAdapter implements MessageRecoveryListener
{
    private static final Logger LOG;
    private final MessageStore store;
    private final MessageRecoveryListener listener;
    private int count;
    private MessageId lastRecovered;
    
    RecoveryListenerAdapter(final MessageStore store, final MessageRecoveryListener listener) {
        this.store = store;
        this.listener = listener;
    }
    
    @Override
    public boolean hasSpace() {
        return this.listener.hasSpace();
    }
    
    @Override
    public boolean isDuplicate(final MessageId id) {
        return this.listener.isDuplicate(id);
    }
    
    @Override
    public boolean recoverMessage(final Message message) throws Exception {
        if (this.listener.hasSpace() && this.listener.recoverMessage(message)) {
            this.lastRecovered = message.getMessageId();
            ++this.count;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean recoverMessageReference(final MessageId ref) throws Exception {
        final Message message = this.store.getMessage(ref);
        if (message != null) {
            return this.recoverMessage(message);
        }
        throw new IllegalStateException("Message id " + ref + " could not be recovered from the data store for: " + this.store.getDestination().getQualifiedName() + " - already dispatched");
    }
    
    MessageId getLastRecoveredMessageId() {
        return this.lastRecovered;
    }
    
    int size() {
        return this.count;
    }
    
    void reset() {
        this.count = 0;
    }
    
    static {
        LOG = LoggerFactory.getLogger(RecoveryListenerAdapter.class);
    }
}
