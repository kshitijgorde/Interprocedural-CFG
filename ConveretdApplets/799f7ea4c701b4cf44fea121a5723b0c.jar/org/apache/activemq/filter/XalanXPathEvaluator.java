// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

import java.io.Reader;
import java.io.StringReader;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.objects.XObject;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Node;
import org.apache.xpath.CachedXPathAPI;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import org.xml.sax.InputSource;
import org.apache.activemq.util.ByteArrayInputStream;
import javax.jms.JMSException;
import javax.jms.BytesMessage;
import javax.jms.TextMessage;
import org.apache.activemq.command.Message;

public class XalanXPathEvaluator implements XPathExpression.XPathEvaluator
{
    private final String xpath;
    
    public XalanXPathEvaluator(final String xpath) {
        this.xpath = xpath;
    }
    
    @Override
    public boolean evaluate(final Message m) throws JMSException {
        if (m instanceof TextMessage) {
            final String text = ((TextMessage)m).getText();
            return this.evaluate(text);
        }
        if (m instanceof BytesMessage) {
            final BytesMessage bm = (BytesMessage)m;
            final byte[] data = new byte[(int)bm.getBodyLength()];
            bm.readBytes(data);
            return this.evaluate(data);
        }
        return false;
    }
    
    private boolean evaluate(final byte[] data) {
        try {
            final InputSource inputSource = new InputSource(new ByteArrayInputStream(data));
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            final DocumentBuilder dbuilder = factory.newDocumentBuilder();
            final Document doc = dbuilder.parse(inputSource);
            final CachedXPathAPI cachedXPathAPI = new CachedXPathAPI();
            final XObject result = cachedXPathAPI.eval((Node)doc, this.xpath);
            if (result.bool()) {
                return true;
            }
            final NodeIterator iterator = cachedXPathAPI.selectNodeIterator((Node)doc, this.xpath);
            return iterator.nextNode() != null;
        }
        catch (Throwable e) {
            return false;
        }
    }
    
    private boolean evaluate(final String text) {
        try {
            final InputSource inputSource = new InputSource(new StringReader(text));
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            final DocumentBuilder dbuilder = factory.newDocumentBuilder();
            final Document doc = dbuilder.parse(inputSource);
            final CachedXPathAPI cachedXPathAPI = new CachedXPathAPI();
            final XObject result = cachedXPathAPI.eval((Node)doc, this.xpath);
            if (result.bool()) {
                return true;
            }
            final NodeIterator iterator = cachedXPathAPI.selectNodeIterator((Node)doc, this.xpath);
            return iterator.nextNode() != null;
        }
        catch (Throwable e) {
            return false;
        }
    }
}
