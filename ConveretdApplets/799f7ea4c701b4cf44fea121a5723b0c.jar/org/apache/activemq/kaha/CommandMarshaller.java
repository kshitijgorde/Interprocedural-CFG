// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.io.DataInput;
import java.io.IOException;
import org.apache.activemq.util.ByteSequence;
import java.io.DataOutput;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.wireformat.WireFormat;

public class CommandMarshaller implements Marshaller<Object>
{
    private WireFormat wireFormat;
    
    public CommandMarshaller(final WireFormat wireFormat) {
        this.wireFormat = wireFormat;
    }
    
    public CommandMarshaller() {
        this(new OpenWireFormat());
    }
    
    @Override
    public void writePayload(final Object object, final DataOutput dataOut) throws IOException {
        final ByteSequence packet = this.wireFormat.marshal(object);
        dataOut.writeInt(packet.length);
        dataOut.write(packet.data, packet.offset, packet.length);
    }
    
    @Override
    public Object readPayload(final DataInput dataIn) throws IOException {
        final int size = dataIn.readInt();
        final byte[] data = new byte[size];
        dataIn.readFully(data);
        return this.wireFormat.unmarshal(new ByteSequence(data));
    }
}
