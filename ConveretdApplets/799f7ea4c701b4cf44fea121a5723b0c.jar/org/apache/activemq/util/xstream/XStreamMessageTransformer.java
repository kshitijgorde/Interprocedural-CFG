// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util.xstream;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.xml.XppReader;
import java.io.Reader;
import java.io.StringReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import java.io.Writer;
import java.io.StringWriter;
import java.io.Serializable;
import javax.jms.MessageProducer;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.XStream;
import org.apache.activemq.MessageTransformerSupport;

@Deprecated
public class XStreamMessageTransformer extends MessageTransformerSupport
{
    protected MessageTransform transformType;
    private XStream xStream;
    private HierarchicalStreamDriver streamDriver;
    
    public XStreamMessageTransformer() {
        this(MessageTransform.XML);
    }
    
    public XStreamMessageTransformer(final MessageTransform transformType) {
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
    
    public XStream getXStream() {
        if (this.xStream == null) {
            this.xStream = this.createXStream();
        }
        return this.xStream;
    }
    
    public void setXStream(final XStream xStream) {
        this.xStream = xStream;
    }
    
    public HierarchicalStreamDriver getStreamDriver() {
        return this.streamDriver;
    }
    
    public void setStreamDriver(final HierarchicalStreamDriver streamDriver) {
        this.streamDriver = streamDriver;
    }
    
    protected XStream createXStream() {
        return new XStream();
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
    
    protected String marshall(final Session session, final ObjectMessage objectMessage) throws JMSException {
        final Serializable object = objectMessage.getObject();
        final StringWriter buffer = new StringWriter();
        HierarchicalStreamWriter out;
        if (this.streamDriver != null) {
            out = this.streamDriver.createWriter((Writer)buffer);
        }
        else {
            out = (HierarchicalStreamWriter)new PrettyPrintWriter((Writer)buffer);
        }
        this.getXStream().marshal((Object)object, out);
        return buffer.toString();
    }
    
    protected Object unmarshall(final Session session, final TextMessage textMessage) throws JMSException {
        HierarchicalStreamReader in;
        if (this.streamDriver != null) {
            in = this.streamDriver.createReader((Reader)new StringReader(textMessage.getText()));
        }
        else {
            in = (HierarchicalStreamReader)new XppReader((Reader)new StringReader(textMessage.getText()));
        }
        return this.getXStream().unmarshal(in);
    }
    
    public enum MessageTransform
    {
        XML, 
        OBJECT, 
        ADAPTIVE;
    }
}
