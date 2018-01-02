// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import javax.jms.JMSException;
import org.apache.activemq.command.ActiveMQMessage;
import javax.jms.Destination;
import javax.jms.Message;

public class MessageDestinationComparator extends MessageComparatorSupport
{
    @Override
    protected int compareMessages(final Message message1, final Message message2) {
        return this.compareComparators(this.getComparable(this.getDestination(message1)), this.getComparable(this.getDestination(message2)));
    }
    
    protected Destination getDestination(final Message message) {
        if (message instanceof ActiveMQMessage) {
            final ActiveMQMessage amqMessage = (ActiveMQMessage)message;
            return amqMessage.getDestination();
        }
        try {
            return message.getJMSDestination();
        }
        catch (JMSException e) {
            return null;
        }
    }
    
    protected Comparable getComparable(final Destination destination) {
        if (destination != null) {
            return destination.toString();
        }
        return null;
    }
}
