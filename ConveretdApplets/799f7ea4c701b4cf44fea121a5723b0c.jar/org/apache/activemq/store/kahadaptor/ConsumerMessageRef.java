// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.command.MessageId;

public class ConsumerMessageRef
{
    private MessageId messageId;
    private StoreEntry messageEntry;
    private StoreEntry ackEntry;
    
    public StoreEntry getAckEntry() {
        return this.ackEntry;
    }
    
    public void setAckEntry(final StoreEntry ackEntry) {
        this.ackEntry = ackEntry;
    }
    
    public StoreEntry getMessageEntry() {
        return this.messageEntry;
    }
    
    public void setMessageEntry(final StoreEntry messageEntry) {
        this.messageEntry = messageEntry;
    }
    
    public MessageId getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(final MessageId messageId) {
        this.messageId = messageId;
    }
    
    @Override
    public String toString() {
        return "ConsumerMessageRef[" + this.messageId + "]";
    }
}
