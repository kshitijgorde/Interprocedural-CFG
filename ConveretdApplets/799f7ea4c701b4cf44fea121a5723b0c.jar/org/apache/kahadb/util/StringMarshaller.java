// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;

public class StringMarshaller implements Marshaller<String>
{
    public static final StringMarshaller INSTANCE;
    
    public void writePayload(final String object, final DataOutput dataOut) throws IOException {
        dataOut.writeUTF(object);
    }
    
    public String readPayload(final DataInput dataIn) throws IOException {
        return dataIn.readUTF();
    }
    
    public int getFixedSize() {
        return -1;
    }
    
    public String deepCopy(final String source) {
        return source;
    }
    
    public boolean isDeepCopySupported() {
        return true;
    }
    
    static {
        INSTANCE = new StringMarshaller();
    }
}
