// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.command.MessageId;
import java.io.DataInput;
import java.io.IOException;
import org.apache.activemq.kaha.impl.index.IndexItem;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;

public class ConsumerMessageRefMarshaller implements Marshaller
{
    @Override
    public void writePayload(final Object object, final DataOutput dataOut) throws IOException {
        final ConsumerMessageRef ref = (ConsumerMessageRef)object;
        dataOut.writeUTF(ref.getMessageId().toString());
        IndexItem item = (IndexItem)ref.getMessageEntry();
        dataOut.writeLong(item.getOffset());
        item.write(dataOut);
        item = (IndexItem)ref.getAckEntry();
        dataOut.writeLong(item.getOffset());
        item.write(dataOut);
    }
    
    @Override
    public Object readPayload(final DataInput dataIn) throws IOException {
        final ConsumerMessageRef ref = new ConsumerMessageRef();
        ref.setMessageId(new MessageId(dataIn.readUTF()));
        IndexItem item = new IndexItem();
        item.setOffset(dataIn.readLong());
        item.read(dataIn);
        ref.setMessageEntry(item);
        item = new IndexItem();
        item.setOffset(dataIn.readLong());
        item.read(dataIn);
        ref.setAckEntry(item);
        return ref;
    }
}
