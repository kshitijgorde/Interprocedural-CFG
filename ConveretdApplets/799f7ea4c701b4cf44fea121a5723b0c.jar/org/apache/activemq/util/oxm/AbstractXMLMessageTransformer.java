// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util.oxm;

import java.io.Serializable;
import javax.jms.MessageProducer;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.MessageTransformerSupport;

public abstract class AbstractXMLMessageTransformer extends MessageTransformerSupport
{
    protected MessageTransform transformType;
    
    public AbstractXMLMessageTransformer() {
        this(MessageTransform.XML);
    }
    
    public AbstractXMLMessageTransformer(final MessageTransform transformType) {
        this.transformType = transformType;
    }
    
    @Override
    public Message consumerTransform(final Session session, final MessageConsumer consumer, final Message message) throws JMSException {
        switch (this.transformType) {
            case XML: {
                return (message instanceof TextMessage) ? this.textToObject(session, (TextMessage)message) : message;
            }
            case OBJECT: {
                return (message instanceof ObjectMessage) ? this.objectToText(session, (ObjectMessage)message) : message;
            }
            case ADAPTIVE: {
                return (message instanceof TextMessage) ? this.textToObject(session, (TextMessage)message) : ((message instanceof ObjectMessage) ? this.objectToText(session, (ObjectMessage)message) : message);
            }
            default: {
                return message;
            }
        }
    }
    
    @Override
    public Message producerTransform(final Session session, final MessageProducer producer, final Message message) throws JMSException {
        switch (this.transformType) {
            case XML: {
                return (message instanceof ObjectMessage) ? this.objectToText(session, (ObjectMessage)message) : message;
            }
            case OBJECT: {
                return (message instanceof TextMessage) ? this.textToObject(session, (TextMessage)message) : message;
            }
            case ADAPTIVE: {
                return (message instanceof TextMessage) ? this.textToObject(session, (TextMessage)message) : ((message instanceof ObjectMessage) ? this.objectToText(session, (ObjectMessage)message) : message);
            }
            default: {
                return message;
            }
        }
    }
    
    public MessageTransform getTransformType() {
        return this.transformType;
    }
    
    public void setTransformType(final MessageTransform transformType) {
        this.transformType = transformType;
    }
    
    protected ObjectMessage textToObject(final Session session, final TextMessage textMessage) throws JMSException {
        final Object object = this.unmarshall(session, textMessage);
        if (object instanceof Serializable) {
            final ObjectMessage answer = session.createObjectMessage((Serializable)object);
            this.copyProperties(textMessage, answer);
            return answer;
        }
        throw new JMSException("Object is not serializable: " + object);
    }
    
    protected TextMessage objectToText(final Session session, final ObjectMessage objectMessage) throws JMSException {
        final TextMessage answer = session.createTextMessage(this.marshall(session, objectMessage));
        this.copyProperties(objectMessage, answer);
        return answer;
    }
    
    protected abstract String marshall(final Session p0, final ObjectMessage p1) throws JMSException;
    
    protected abstract Object unmarshall(final Session p0, final TextMessage p1) throws JMSException;
    
    public enum MessageTransform
    {
        XML, 
        OBJECT, 
        ADAPTIVE;
    }
}
