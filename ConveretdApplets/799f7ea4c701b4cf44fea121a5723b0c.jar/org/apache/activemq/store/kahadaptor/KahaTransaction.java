// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import java.util.Collection;
import java.io.IOException;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.command.BaseCommand;
import java.util.ArrayList;
import java.util.List;

class KahaTransaction
{
    protected List<TxCommand> list;
    
    KahaTransaction() {
        this.list = new ArrayList<TxCommand>();
    }
    
    void add(final KahaMessageStore store, final BaseCommand command) {
        final TxCommand tx = new TxCommand();
        tx.setCommand(command);
        tx.setMessageStoreKey(store.getId());
        this.list.add(tx);
    }
    
    public void add(final KahaMessageStore destination, final String clientId, final String subscriptionName, final MessageId messageId, final MessageAck ack) {
        final TxCommand tx = new TxCommand();
        tx.setCommand(ack);
        tx.setMessageStoreKey(destination.getId());
        tx.setClientId(clientId);
        tx.setSubName(subscriptionName);
        tx.setMessageId(messageId);
        this.list.add(tx);
    }
    
    Message[] getMessages() {
        final List<BaseCommand> result = new ArrayList<BaseCommand>();
        for (int i = 0; i < this.list.size(); ++i) {
            final TxCommand command = this.list.get(i);
            if (command.isAdd()) {
                result.add(command.getCommand());
            }
        }
        final Message[] messages = new Message[result.size()];
        return result.toArray(messages);
    }
    
    MessageAck[] getAcks() {
        final List<BaseCommand> result = new ArrayList<BaseCommand>();
        for (int i = 0; i < this.list.size(); ++i) {
            final TxCommand command = this.list.get(i);
            if (command.isRemove()) {
                result.add(command.getCommand());
            }
        }
        final MessageAck[] acks = new MessageAck[result.size()];
        return result.toArray(acks);
    }
    
    void prepare() {
    }
    
    void rollback() {
        this.list.clear();
    }
    
    void commit(final KahaTransactionStore transactionStore) throws IOException {
        for (int i = 0; i < this.list.size(); ++i) {
            final TxCommand command = this.list.get(i);
            final MessageStore ms = transactionStore.getStoreById(command.getMessageStoreKey());
            if (command.isAdd()) {
                ms.addMessage(null, (Message)command.getCommand());
            }
        }
        for (int i = 0; i < this.list.size(); ++i) {
            final TxCommand command = this.list.get(i);
            final MessageStore ms = transactionStore.getStoreById(command.getMessageStoreKey());
            if (command.isRemove()) {
                ms.removeMessage(null, (MessageAck)command.getCommand());
            }
            else if (command.isAck()) {
                ((TopicMessageStore)ms).acknowledge(null, command.getClientId(), command.getSubscriptionName(), command.getMessageId(), (MessageAck)command.getCommand());
            }
        }
    }
    
    List<TxCommand> getList() {
        return new ArrayList<TxCommand>(this.list);
    }
    
    void setList(final List<TxCommand> list) {
        this.list = list;
    }
}
