// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;

public class StringMarshaller implements Marshaller<String>
{
    @Override
    public void writePayload(final String object, final DataOutput dataOut) throws IOException {
        dataOut.writeUTF(object);
    }
    
    @Override
    public String readPayload(final DataInput dataIn) throws IOException {
        return dataIn.readUTF();
    }
}
