// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;

public class IntegerMarshaller implements Marshaller<Integer>
{
    @Override
    public void writePayload(final Integer object, final DataOutput dataOut) throws IOException {
        dataOut.writeInt(object);
    }
    
    @Override
    public Integer readPayload(final DataInput dataIn) throws IOException {
        return dataIn.readInt();
    }
}
