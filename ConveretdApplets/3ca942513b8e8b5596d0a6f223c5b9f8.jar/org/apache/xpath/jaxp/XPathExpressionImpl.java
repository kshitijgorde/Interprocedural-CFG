// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.jaxp;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.apache.xpath.res.XPATHMessages;
import org.xml.sax.InputSource;
import javax.xml.xpath.XPathConstants;
import org.apache.xpath.objects.XObject;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.xpath.XPathContext;
import javax.xml.namespace.QName;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.XPath;
import javax.xml.xpath.XPathExpression;

public class XPathExpressionImpl implements XPathExpression
{
    private XPath m_xpath;
    private PrefixResolver m_prefixResolver;
    private boolean m_isSecureProcessing;
    
    protected XPathExpressionImpl(final XPath xpath, final PrefixResolver prefixResolver, final boolean isSecureProcessing) {
        this.m_xpath = null;
        this.m_prefixResolver = null;
        this.m_isSecureProcessing = false;
        this.m_xpath = xpath;
        this.m_prefixResolver = prefixResolver;
        this.m_isSecureProcessing = isSecureProcessing;
    }
    
    public Object evaluate(final Object item, final QName returnType) throws XPathExpressionException {
        final XPathContext xctxt = new XPathContext();
        if (this.m_isSecureProcessing) {
            xctxt.setSecureProcessing(true);
        }
        final int contextNode = getContextNodeHandle(item, xctxt);
        try {
            final XObject xobj = this.m_xpath.execute(xctxt, contextNode, this.m_prefixResolver);
            return xObjectToObject(xobj, returnType);
        }
        catch (TransformerException te) {
            final Throwable containedException = te.getException();
            if (containedException instanceof XPathExpressionException) {
                throw (XPathExpressionException)containedException;
            }
            throw new XPathExpressionException(te);
        }
    }
    
    public String evaluate(final Object item) throws XPathExpressionException {
        return (String)this.evaluate(item, XPathConstants.STRING);
    }
    
    public Object evaluate(final InputSource source, final QName returnType) throws XPathExpressionException {
        if (source == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_NULL_INPUT_SOURCE", null));
        }
        Document doc = null;
        try {
            final DocumentBuilder builder = getDocumentBuilder(this.m_isSecureProcessing);
            doc = builder.parse(source);
        }
        catch (Exception e) {
            throw new XPathExpressionException(e);
        }
        return this.evaluate(doc, returnType);
    }
    
    public String evaluate(final InputSource source) throws XPathExpressionException {
        return (String)this.evaluate(source, XPathConstants.STRING);
    }
    
    private static int getContextNodeHandle(final Object item, final XPathContext xctxt) throws XPathExpressionException {
        Node node = null;
        if (item == null) {
            try {
                node = getDocumentBuilder(xctxt.isSecureProcessing()).newDocument();
            }
            catch (ParserConfigurationException pce) {
                throw new XPathExpressionException(pce);
            }
        }
        else if (item instanceof Node) {
            node = (Node)item;
        }
        else if (item instanceof NodeList) {
            node = ((NodeList)item).item(0);
        }
        else {
            if (!(item instanceof NodeIterator)) {
                throw new XPathExpressionException(XPATHMessages.createXPATHMessage("ER_UNSUPPORTED_CONTEXT_ITEM", new Object[] { item }));
            }
            node = ((NodeIterator)item).nextNode();
        }
        return xctxt.getDTMHandleFromNode(node);
    }
    
    private static DocumentBuilder getDocumentBuilder(final boolean isSecureProcessing) throws ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        try {
            if (isSecureProcessing) {
                factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            }
        }
        catch (ParserConfigurationException ex) {}
        return factory.newDocumentBuilder();
    }
    
    private static Object xObjectToObject(final XObject xobj, final QName returnType) throws TransformerException {
        if (returnType == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_NULL_XPATH_RETURN_TYPE", null));
        }
        if (returnType.equals(XPathConstants.STRING)) {
            return xobj.str();
        }
        if (returnType.equals(XPathConstants.NUMBER)) {
            return new Double(xobj.num());
        }
        if (returnType.equals(XPathConstants.BOOLEAN)) {
            return new Boolean(xobj.bool());
        }
        if (returnType.equals(XPathConstants.NODESET)) {
            return xobj.nodelist();
        }
        if (returnType.equals(XPathConstants.NODE)) {
            return xobj.nodeset().nextNode();
        }
        throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_INVALID_XPATH_RETURN_TYPE", new Object[] { returnType }));
    }
}
