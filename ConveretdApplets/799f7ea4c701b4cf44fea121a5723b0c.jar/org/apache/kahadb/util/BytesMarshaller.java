// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;

public class BytesMarshaller implements Marshaller<byte[]>
{
    public void writePayload(final byte[] data, final DataOutput dataOut) throws IOException {
        dataOut.writeInt(data.length);
        dataOut.write(data);
    }
    
    public byte[] readPayload(final DataInput dataIn) throws IOException {
        final int size = dataIn.readInt();
        final byte[] data = new byte[size];
        dataIn.readFully(data);
        return data;
    }
    
    public int getFixedSize() {
        return -1;
    }
    
    public byte[] deepCopy(final byte[] source) {
        final byte[] rc = new byte[source.length];
        System.arraycopy(source, 0, rc, 0, source.length);
        return rc;
    }
    
    public boolean isDeepCopySupported() {
        return true;
    }
}
