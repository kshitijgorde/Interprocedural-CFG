// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import org.apache.activemq.util.ByteSequence;
import javax.jms.JMSException;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQBlobMessage;
import org.apache.activemq.command.ActiveMQMessage;

public class MessageBodyFormatter
{
    final ActiveMQMessage message;
    
    public MessageBodyFormatter(final ActiveMQMessage message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        try {
            switch (this.message.getDataStructureType()) {
                case 23: {
                    return "";
                }
                case 29: {
                    final ActiveMQBlobMessage blob = (ActiveMQBlobMessage)this.message;
                    return blob.getRemoteBlobUrl();
                }
                case 25: {
                    final ActiveMQMapMessage map = (ActiveMQMapMessage)this.message;
                    return map.getContentMap().toString();
                }
                case 28: {
                    final ActiveMQTextMessage text = (ActiveMQTextMessage)this.message;
                    return text.getText();
                }
                case 24:
                case 26:
                case 27: {
                    final ByteSequence data = this.message.getContent();
                    return "binary payload {length=" + data.getLength() + ", compressed=" + this.message.isCompressed() + "}";
                }
            }
        }
        catch (JMSException ex) {}
        return "";
    }
}
