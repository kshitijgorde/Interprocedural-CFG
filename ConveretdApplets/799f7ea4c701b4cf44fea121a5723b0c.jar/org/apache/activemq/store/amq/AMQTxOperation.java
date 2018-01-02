// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import java.io.DataInput;
import org.apache.activemq.util.ByteSequence;
import java.io.DataOutput;
import org.apache.activemq.wireformat.WireFormat;
import java.io.IOException;
import org.apache.activemq.command.JournalTopicAck;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.kaha.impl.async.Location;
import org.apache.activemq.command.ActiveMQDestination;

public class AMQTxOperation
{
    public static final byte ADD_OPERATION_TYPE = 0;
    public static final byte REMOVE_OPERATION_TYPE = 1;
    public static final byte ACK_OPERATION_TYPE = 3;
    private byte operationType;
    private ActiveMQDestination destination;
    private Object data;
    private Location location;
    
    public AMQTxOperation() {
    }
    
    public AMQTxOperation(final byte operationType, final ActiveMQDestination destination, final Object data, final Location location) {
        this.operationType = operationType;
        this.destination = destination;
        this.data = data;
        this.location = location;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public byte getOperationType() {
        return this.operationType;
    }
    
    public void setOperationType(final byte operationType) {
        this.operationType = operationType;
    }
    
    public boolean replay(final AMQPersistenceAdapter adapter, final ConnectionContext context) throws IOException {
        boolean result = false;
        final AMQMessageStore store = (AMQMessageStore)adapter.createMessageStore(this.destination);
        if (this.operationType == 0) {
            result = store.replayAddMessage(context, (Message)this.data, this.location);
        }
        else if (this.operationType == 1) {
            result = store.replayRemoveMessage(context, (MessageAck)this.data);
        }
        else {
            final JournalTopicAck ack = (JournalTopicAck)this.data;
            result = ((AMQTopicMessageStore)store).replayAcknowledge(context, ack.getClientId(), ack.getSubscritionName(), ack.getMessageId());
        }
        return result;
    }
    
    public void writeExternal(final WireFormat wireFormat, final DataOutput dos) throws IOException {
        this.location.writeExternal(dos);
        ByteSequence packet = wireFormat.marshal(this.getData());
        dos.writeInt(packet.length);
        dos.write(packet.data, packet.offset, packet.length);
        packet = wireFormat.marshal(this.destination);
        dos.writeInt(packet.length);
        dos.write(packet.data, packet.offset, packet.length);
    }
    
    public void readExternal(final WireFormat wireFormat, final DataInput dis) throws IOException {
        (this.location = new Location()).readExternal(dis);
        int size = dis.readInt();
        byte[] data = new byte[size];
        dis.readFully(data);
        this.setData(wireFormat.unmarshal(new ByteSequence(data)));
        size = dis.readInt();
        data = new byte[size];
        dis.readFully(data);
        this.destination = (ActiveMQDestination)wireFormat.unmarshal(new ByteSequence(data));
    }
}
