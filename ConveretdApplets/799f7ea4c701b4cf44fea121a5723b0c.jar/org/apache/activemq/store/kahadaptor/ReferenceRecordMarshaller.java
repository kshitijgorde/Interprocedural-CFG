// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import java.io.DataOutput;
import java.io.IOException;
import org.apache.activemq.store.ReferenceStore;
import java.io.DataInput;
import org.apache.activemq.kaha.Marshaller;

public class ReferenceRecordMarshaller implements Marshaller<ReferenceRecord>
{
    @Override
    public ReferenceRecord readPayload(final DataInput dataIn) throws IOException {
        final ReferenceRecord rr = new ReferenceRecord();
        rr.setMessageId(dataIn.readUTF());
        final ReferenceStore.ReferenceData referenceData = new ReferenceStore.ReferenceData();
        referenceData.setFileId(dataIn.readInt());
        referenceData.setOffset(dataIn.readInt());
        referenceData.setExpiration(dataIn.readLong());
        rr.setData(referenceData);
        return rr;
    }
    
    @Override
    public void writePayload(final ReferenceRecord rr, final DataOutput dataOut) throws IOException {
        dataOut.writeUTF(rr.getMessageId());
        dataOut.writeInt(rr.getData().getFileId());
        dataOut.writeInt(rr.getData().getOffset());
        dataOut.writeLong(rr.getData().getExpiration());
    }
}
