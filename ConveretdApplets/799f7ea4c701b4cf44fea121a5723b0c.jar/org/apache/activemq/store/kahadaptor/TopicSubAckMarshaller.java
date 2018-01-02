// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.kaha.StoreEntry;
import java.io.DataInput;
import java.io.IOException;
import org.apache.activemq.kaha.impl.index.IndexItem;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;

public class TopicSubAckMarshaller implements Marshaller
{
    @Override
    public void writePayload(final Object object, final DataOutput dataOut) throws IOException {
        final TopicSubAck tsa = (TopicSubAck)object;
        dataOut.writeInt(tsa.getCount());
        final IndexItem item = (IndexItem)tsa.getMessageEntry();
        dataOut.writeLong(item.getOffset());
        item.write(dataOut);
    }
    
    @Override
    public Object readPayload(final DataInput dataIn) throws IOException {
        final TopicSubAck tsa = new TopicSubAck();
        final int count = dataIn.readInt();
        tsa.setCount(count);
        final IndexItem item = new IndexItem();
        item.setOffset(dataIn.readLong());
        item.read(dataIn);
        tsa.setMessageEntry(item);
        return tsa;
    }
}
