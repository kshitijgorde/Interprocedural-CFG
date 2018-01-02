// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.command.BaseCommand;
import java.util.ArrayList;
import java.io.DataInput;
import java.io.IOException;
import org.apache.activemq.util.ByteSequence;
import java.util.List;
import java.io.DataOutput;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.kaha.Marshaller;

public class TransactionMarshaller implements Marshaller
{
    private WireFormat wireFormat;
    
    public TransactionMarshaller(final WireFormat wireFormat) {
        this.wireFormat = wireFormat;
    }
    
    @Override
    public void writePayload(final Object object, final DataOutput dataOut) throws IOException {
        final KahaTransaction kt = (KahaTransaction)object;
        final List list = kt.getList();
        dataOut.writeInt(list.size());
        for (int i = 0; i < list.size(); ++i) {
            final TxCommand tx = list.get(i);
            final Object key = tx.getMessageStoreKey();
            ByteSequence packet = this.wireFormat.marshal(key);
            dataOut.writeInt(packet.length);
            dataOut.write(packet.data, packet.offset, packet.length);
            final Object command = tx.getCommand();
            packet = this.wireFormat.marshal(command);
            dataOut.writeInt(packet.length);
            dataOut.write(packet.data, packet.offset, packet.length);
        }
    }
    
    @Override
    public Object readPayload(final DataInput dataIn) throws IOException {
        final KahaTransaction result = new KahaTransaction();
        final List list = new ArrayList();
        result.setList(list);
        for (int number = dataIn.readInt(), i = 0; i < number; ++i) {
            final TxCommand command = new TxCommand();
            int size = dataIn.readInt();
            byte[] data = new byte[size];
            dataIn.readFully(data);
            final Object key = this.wireFormat.unmarshal(new ByteSequence(data));
            command.setMessageStoreKey(key);
            size = dataIn.readInt();
            data = new byte[size];
            dataIn.readFully(data);
            final BaseCommand bc = (BaseCommand)this.wireFormat.unmarshal(new ByteSequence(data));
            command.setCommand(bc);
            list.add(command);
        }
        return result;
    }
}
