// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util.oxm;

import javax.xml.transform.Source;
import org.springframework.xml.transform.StringSource;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.xml.transform.Result;
import org.springframework.xml.transform.StringResult;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.oxm.AbstractMarshaller;

public class OXMMessageTransformer extends AbstractXMLMessageTransformer
{
    private AbstractMarshaller marshaller;
    
    public AbstractMarshaller getMarshaller() {
        return this.marshaller;
    }
    
    public void setMarshaller(final AbstractMarshaller marshaller) {
        this.marshaller = marshaller;
    }
    
    @Override
    protected String marshall(final Session session, final ObjectMessage objectMessage) throws JMSException {
        final StringResult result = new StringResult();
        try {
            this.marshaller.marshal((Object)objectMessage.getObject(), (Result)result);
            return result.toString();
        }
        catch (Exception e) {
            throw new JMSException(e.getMessage());
        }
    }
    
    @Override
    protected Object unmarshall(final Session session, final TextMessage textMessage) throws JMSException {
        try {
            return this.marshaller.unmarshal((Source)new StringSource(textMessage.getText()));
        }
        catch (Exception e) {
            throw new JMSException(e.getMessage());
        }
    }
}
