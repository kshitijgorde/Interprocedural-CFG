// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import java.io.DataInput;
import java.io.IOException;
import org.apache.activemq.kaha.impl.index.IndexItem;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;

public class StoreEntryMarshaller implements Marshaller
{
    @Override
    public void writePayload(final Object object, final DataOutput dataOut) throws IOException {
        final IndexItem item = (IndexItem)object;
        dataOut.writeLong(item.getOffset());
        item.write(dataOut);
    }
    
    @Override
    public Object readPayload(final DataInput dataIn) throws IOException {
        final IndexItem item = new IndexItem();
        item.setOffset(dataIn.readLong());
        item.read(dataIn);
        return item;
    }
}
