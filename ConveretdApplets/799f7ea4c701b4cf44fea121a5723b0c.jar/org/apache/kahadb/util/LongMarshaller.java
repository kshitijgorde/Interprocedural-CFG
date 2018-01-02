// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;

public class LongMarshaller implements Marshaller<Long>
{
    public static final LongMarshaller INSTANCE;
    
    public void writePayload(final Long object, final DataOutput dataOut) throws IOException {
        dataOut.writeLong(object);
    }
    
    public Long readPayload(final DataInput dataIn) throws IOException {
        return dataIn.readLong();
    }
    
    public int getFixedSize() {
        return 8;
    }
    
    public Long deepCopy(final Long source) {
        return source;
    }
    
    public boolean isDeepCopySupported() {
        return true;
    }
    
    static {
        INSTANCE = new LongMarshaller();
    }
}
