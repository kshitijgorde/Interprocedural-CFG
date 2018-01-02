// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.command.MessageAck;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.broker.ConnectionContext;
import java.io.IOException;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.kaha.MapContainer;
import org.apache.activemq.store.AbstractMessageStore;

public class KahaMessageStore extends AbstractMessageStore
{
    protected final MapContainer<MessageId, Message> messageContainer;
    protected StoreEntry batchEntry;
    
    public KahaMessageStore(final MapContainer<MessageId, Message> container, final ActiveMQDestination destination) throws IOException {
        super(destination);
        this.messageContainer = container;
    }
    
    protected MessageId getMessageId(final Object object) {
        return ((Message)object).getMessageId();
    }
    
    public Object getId() {
        return this.messageContainer.getId();
    }
    
    @Override
    public synchronized void addMessage(final ConnectionContext context, final Message message) throws IOException {
        this.messageContainer.put(message.getMessageId(), message);
    }
    
    @Override
    public synchronized Message getMessage(final MessageId identity) throws IOException {
        final Message result = this.messageContainer.get(identity);
        return result;
    }
    
    protected boolean recoverMessage(final MessageRecoveryListener listener, final Message msg) throws Exception {
        listener.recoverMessage(msg);
        return listener.hasSpace();
    }
    
    @Override
    public void removeMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
        this.removeMessage(ack.getLastMessageId());
    }
    
    public synchronized void removeMessage(final MessageId msgId) throws IOException {
        final StoreEntry entry = this.messageContainer.getEntry(msgId);
        if (entry != null) {
            this.messageContainer.remove(entry);
            if (this.messageContainer.isEmpty() || (this.batchEntry != null && this.batchEntry.equals(entry))) {
                this.resetBatching();
            }
        }
    }
    
    @Override
    public synchronized void recover(final MessageRecoveryListener listener) throws Exception {
        for (StoreEntry entry = this.messageContainer.getFirst(); entry != null; entry = this.messageContainer.getNext(entry)) {
            final Message msg = this.messageContainer.getValue(entry);
            if (!this.recoverMessage(listener, msg)) {
                break;
            }
        }
    }
    
    @Override
    public synchronized void removeAllMessages(final ConnectionContext context) throws IOException {
        this.messageContainer.clear();
    }
    
    public synchronized void delete() {
        this.messageContainer.clear();
    }
    
    @Override
    public int getMessageCount() {
        return this.messageContainer.size();
    }
    
    public MessageId getPreviousMessageIdToDeliver(final MessageId id) throws Exception {
        return null;
    }
    
    @Override
    public synchronized void recoverNextMessages(final int maxReturned, final MessageRecoveryListener listener) throws Exception {
        StoreEntry entry = this.batchEntry;
        if (entry == null) {
            entry = this.messageContainer.getFirst();
        }
        else {
            entry = this.messageContainer.refresh(entry);
            entry = this.messageContainer.getNext(entry);
            if (entry == null) {
                this.batchEntry = null;
            }
        }
        if (entry != null) {
            int count = 0;
            do {
                final Message msg = this.messageContainer.getValue(entry);
                if (msg != null) {
                    this.recoverMessage(listener, msg);
                    ++count;
                }
                this.batchEntry = entry;
                entry = this.messageContainer.getNext(entry);
            } while (entry != null && count < maxReturned && listener.hasSpace());
        }
    }
    
    @Override
    public synchronized void resetBatching() {
        this.batchEntry = null;
    }
    
    public boolean isSupportForCursors() {
        return true;
    }
    
    @Override
    public void setBatch(final MessageId messageId) {
        this.batchEntry = this.messageContainer.getEntry(messageId);
    }
}
