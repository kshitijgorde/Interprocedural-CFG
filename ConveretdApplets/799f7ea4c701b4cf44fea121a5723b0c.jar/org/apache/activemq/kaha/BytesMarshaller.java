// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;

public class BytesMarshaller implements Marshaller
{
    @Override
    public void writePayload(final Object object, final DataOutput dataOut) throws IOException {
        final byte[] data = (byte[])object;
        dataOut.writeInt(data.length);
        dataOut.write(data);
    }
    
    @Override
    public Object readPayload(final DataInput dataIn) throws IOException {
        final int size = dataIn.readInt();
        final byte[] data = new byte[size];
        dataIn.readFully(data);
        return data;
    }
}
