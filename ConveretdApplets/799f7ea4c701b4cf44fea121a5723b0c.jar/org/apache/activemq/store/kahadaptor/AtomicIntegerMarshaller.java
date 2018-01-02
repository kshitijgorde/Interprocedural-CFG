// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.activemq.kaha.Marshaller;

public class AtomicIntegerMarshaller implements Marshaller<AtomicInteger>
{
    @Override
    public void writePayload(final AtomicInteger ai, final DataOutput dataOut) throws IOException {
        dataOut.writeInt(ai.get());
    }
    
    @Override
    public AtomicInteger readPayload(final DataInput dataIn) throws IOException {
        final int value = dataIn.readInt();
        return new AtomicInteger(value);
    }
}
