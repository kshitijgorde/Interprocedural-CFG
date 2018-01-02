// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.or.jms;

import javax.jms.JMSException;
import org.apache.log4j.helpers.LogLog;
import javax.jms.Message;
import org.apache.log4j.or.ObjectRenderer;

public class MessageRenderer implements ObjectRenderer
{
    public String doRender(final Object o) {
        if (o instanceof Message) {
            final StringBuffer sb = new StringBuffer();
            final Message message = (Message)o;
            try {
                sb.append("DeliveryMode=");
                switch (message.getJMSDeliveryMode()) {
                    case 1: {
                        sb.append("NON_PERSISTENT");
                        break;
                    }
                    case 2: {
                        sb.append("PERSISTENT");
                        break;
                    }
                    default: {
                        sb.append("UNKNOWN");
                        break;
                    }
                }
                sb.append(", CorrelationID=");
                sb.append(message.getJMSCorrelationID());
                sb.append(", Destination=");
                sb.append(message.getJMSDestination());
                sb.append(", Expiration=");
                sb.append(message.getJMSExpiration());
                sb.append(", MessageID=");
                sb.append(message.getJMSMessageID());
                sb.append(", Priority=");
                sb.append(message.getJMSPriority());
                sb.append(", Redelivered=");
                sb.append(message.getJMSRedelivered());
                sb.append(", ReplyTo=");
                sb.append(message.getJMSReplyTo());
                sb.append(", Timestamp=");
                sb.append(message.getJMSTimestamp());
                sb.append(", Type=");
                sb.append(message.getJMSType());
            }
            catch (JMSException t) {
                LogLog.error("Could not parse Message.", t);
            }
            return sb.toString();
        }
        return o.toString();
    }
}
