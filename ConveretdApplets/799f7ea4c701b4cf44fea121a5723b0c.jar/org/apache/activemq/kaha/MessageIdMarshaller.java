// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;
import org.apache.activemq.command.MessageId;

public class MessageIdMarshaller implements Marshaller<MessageId>
{
    @Override
    public void writePayload(final MessageId object, final DataOutput dataOut) throws IOException {
        dataOut.writeUTF(object.toString());
    }
    
    @Override
    public MessageId readPayload(final DataInput dataIn) throws IOException {
        return new MessageId(dataIn.readUTF());
    }
}
