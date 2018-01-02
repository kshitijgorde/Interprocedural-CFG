// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import java.util.Iterator;
import org.apache.activemq.command.JournalTopicAck;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.command.Message;
import java.util.ArrayList;
import java.util.List;
import org.apache.activemq.kaha.impl.async.Location;

public class AMQTx
{
    private final Location location;
    private List<AMQTxOperation> operations;
    
    public AMQTx(final Location location) {
        this.operations = new ArrayList<AMQTxOperation>();
        this.location = location;
    }
    
    public void add(final AMQMessageStore store, final Message msg, final Location location) {
        this.operations.add(new AMQTxOperation((byte)0, store.getDestination(), msg, location));
    }
    
    public void add(final AMQMessageStore store, final MessageAck ack) {
        this.operations.add(new AMQTxOperation((byte)1, store.getDestination(), ack, null));
    }
    
    public void add(final AMQTopicMessageStore store, final JournalTopicAck ack) {
        this.operations.add(new AMQTxOperation((byte)3, store.getDestination(), ack, null));
    }
    
    public Message[] getMessages() {
        final List<Object> list = new ArrayList<Object>();
        for (final AMQTxOperation op : this.operations) {
            if (op.getOperationType() == 0) {
                list.add(op.getData());
            }
        }
        final Message[] rc = new Message[list.size()];
        list.toArray(rc);
        return rc;
    }
    
    public MessageAck[] getAcks() {
        final List<Object> list = new ArrayList<Object>();
        for (final AMQTxOperation op : this.operations) {
            if (op.getOperationType() == 1) {
                list.add(op.getData());
            }
        }
        final MessageAck[] rc = new MessageAck[list.size()];
        list.toArray(rc);
        return rc;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public List<AMQTxOperation> getOperations() {
        return this.operations;
    }
    
    public void setOperations(final List<AMQTxOperation> operations) {
        this.operations = operations;
    }
}
