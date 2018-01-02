// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.camel.converter;

import org.springframework.util.ObjectUtils;
import org.apache.activemq.command.ActiveMQMessage;
import javax.jms.Session;
import org.springframework.jms.support.converter.MessageConversionException;
import javax.jms.JMSException;
import javax.jms.Message;
import org.springframework.jms.support.converter.MessageConverter;

public class IdentityMessageReuseConverter implements MessageConverter
{
    public Object fromMessage(final Message message) throws JMSException, MessageConversionException {
        return message;
    }
    
    public Message toMessage(final Object object, final Session session) throws JMSException, MessageConversionException {
        if (object instanceof ActiveMQMessage) {
            ((ActiveMQMessage)object).setReadOnlyProperties(false);
            return (Message)object;
        }
        throw new MessageConversionException("Cannot reuse object of type [" + ObjectUtils.nullSafeClassName(object) + "] as ActiveMQMessage message. Message must already be an ActiveMQMessage.");
    }
}
