// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

import javax.jms.JMSException;
import java.io.InputStream;
import org.apache.activemq.util.ByteArrayInputStream;
import javax.jms.BytesMessage;
import org.apache.xmlbeans.XmlObject;
import javax.jms.TextMessage;
import org.apache.activemq.command.Message;

public class XMLBeansXPathEvaluator implements XPathExpression.XPathEvaluator
{
    private final String xpath;
    
    public XMLBeansXPathEvaluator(final String xpath) {
        this.xpath = xpath;
    }
    
    @Override
    public boolean evaluate(final Message message) throws JMSException {
        if (message instanceof TextMessage) {
            final String text = ((TextMessage)message).getText();
            try {
                final XmlObject object = XmlObject.Factory.parse(text);
                final XmlObject[] objects = object.selectPath(this.xpath);
                return object != null && objects.length > 0;
            }
            catch (Throwable e) {
                return false;
            }
        }
        if (message instanceof BytesMessage) {
            final BytesMessage bm = (BytesMessage)message;
            final byte[] data = new byte[(int)bm.getBodyLength()];
            bm.readBytes(data);
            try {
                final XmlObject object2 = XmlObject.Factory.parse((InputStream)new ByteArrayInputStream(data));
                final XmlObject[] objects2 = object2.selectPath(this.xpath);
                return object2 != null && objects2.length > 0;
            }
            catch (Throwable e2) {
                return false;
            }
        }
        return false;
    }
}
