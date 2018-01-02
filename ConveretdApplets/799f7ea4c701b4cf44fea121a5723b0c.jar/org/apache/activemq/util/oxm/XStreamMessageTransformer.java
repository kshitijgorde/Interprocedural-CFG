// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util.oxm;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.xml.XppReader;
import java.io.Reader;
import java.io.StringReader;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.io.Serializable;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import java.io.Writer;
import java.io.StringWriter;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.XStream;

public class XStreamMessageTransformer extends AbstractXMLMessageTransformer
{
    private XStream xStream;
    private HierarchicalStreamDriver streamDriver;
    
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
    
    @Override
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
    
    @Override
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
}
