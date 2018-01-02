// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.activemq.store.amq.AMQTxOperation;
import org.apache.activemq.kaha.impl.async.Location;
import java.io.DataInput;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.store.amq.AMQTx;
import org.apache.activemq.kaha.Marshaller;

public class AMQTxMarshaller implements Marshaller<AMQTx>
{
    private WireFormat wireFormat;
    
    public AMQTxMarshaller(final WireFormat wireFormat) {
        this.wireFormat = wireFormat;
    }
    
    @Override
    public AMQTx readPayload(final DataInput dataIn) throws IOException {
        final Location location = new Location();
        location.readExternal(dataIn);
        final AMQTx result = new AMQTx(location);
        for (int size = dataIn.readInt(), i = 0; i < size; ++i) {
            final AMQTxOperation op = new AMQTxOperation();
            op.readExternal(this.wireFormat, dataIn);
            result.getOperations().add(op);
        }
        return result;
    }
    
    @Override
    public void writePayload(final AMQTx amqtx, final DataOutput dataOut) throws IOException {
        amqtx.getLocation().writeExternal(dataOut);
        final List<AMQTxOperation> list = amqtx.getOperations();
        final List<AMQTxOperation> ops = new ArrayList<AMQTxOperation>();
        for (final AMQTxOperation op : list) {
            if (op.getOperationType() == 0) {
                ops.add(op);
            }
        }
        dataOut.writeInt(ops.size());
        for (final AMQTxOperation op : ops) {
            op.writeExternal(this.wireFormat, dataOut);
        }
    }
}
