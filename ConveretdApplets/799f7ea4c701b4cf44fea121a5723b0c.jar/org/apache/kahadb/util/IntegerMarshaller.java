// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;

public class IntegerMarshaller implements Marshaller<Integer>
{
    public static final IntegerMarshaller INSTANCE;
    
    public void writePayload(final Integer object, final DataOutput dataOut) throws IOException {
        dataOut.writeInt(object);
    }
    
    public Integer readPayload(final DataInput dataIn) throws IOException {
        return dataIn.readInt();
    }
    
    public int getFixedSize() {
        return 4;
    }
    
    public Integer deepCopy(final Integer source) {
        return source;
    }
    
    public boolean isDeepCopySupported() {
        return true;
    }
    
    static {
        INSTANCE = new IntegerMarshaller();
    }
}
