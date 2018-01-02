// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.command.MessageId;
import org.apache.activemq.command.BaseCommand;

class TxCommand
{
    protected Object messageStoreKey;
    protected BaseCommand command;
    private String clientId;
    private String subscriptionName;
    private MessageId messageId;
    
    public Object getMessageStoreKey() {
        return this.messageStoreKey;
    }
    
    public void setMessageStoreKey(final Object messageStoreKey) {
        this.messageStoreKey = messageStoreKey;
    }
    
    public BaseCommand getCommand() {
        return this.command;
    }
    
    public void setCommand(final BaseCommand command) {
        this.command = command;
    }
    
    public boolean isAdd() {
        return this.command != null && this.command.getDataStructureType() != 22;
    }
    
    public boolean isRemove() {
        return this.command != null && this.command.getDataStructureType() == 22 && this.subscriptionName == null;
    }
    
    public boolean isAck() {
        return this.command != null && this.command.getDataStructureType() == 22 && this.subscriptionName != null;
    }
    
    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }
    
    public void setSubName(final String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }
    
    public void setMessageId(final MessageId messageId) {
        this.messageId = messageId;
    }
    
    public String getClientId() {
        return this.clientId;
    }
    
    public String getSubscriptionName() {
        return this.subscriptionName;
    }
    
    public MessageId getMessageId() {
        return this.messageId;
    }
}
