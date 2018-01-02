// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.io.DataInput;
import java.io.IOException;
import org.apache.activemq.util.ByteSequence;
import java.io.DataOutput;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.command.Message;

public class MessageMarshaller implements Marshaller<Message>
{
    private WireFormat wireFormat;
    
    public MessageMarshaller(final WireFormat wireFormat) {
        this.wireFormat = wireFormat;
    }
    
    @Override
    public void writePayload(final Message message, final DataOutput dataOut) throws IOException {
        final ByteSequence packet = this.wireFormat.marshal(message);
        dataOut.writeInt(packet.length);
        dataOut.write(packet.data, packet.offset, packet.length);
    }
    
    @Override
    public Message readPayload(final DataInput dataIn) throws IOException {
        final int size = dataIn.readInt();
        final byte[] data = new byte[size];
        dataIn.readFully(data);
        return (Message)this.wireFormat.unmarshal(new ByteSequence(data));
    }
}
