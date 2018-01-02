// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.domapi;

import org.apache.xpath.objects.XObject;
import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathContext;
import org.w3c.dom.xpath.XPathException;
import org.w3c.dom.DOMException;
import org.apache.xpath.res.XPATHMessages;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.apache.xpath.XPath;
import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.xpath.XPathExpression;

public class XPathExpressionImpl implements XPathExpression
{
    private PrefixResolver m_resolver;
    private XPath m_xpath;
    private Document m_doc;
    
    XPathExpressionImpl(final XPath xpath, final Document doc) {
        this.m_doc = null;
        this.m_xpath = xpath;
        this.m_doc = doc;
    }
    
    public Object evaluate(final Node contextNode, final short type, final Object result) throws XPathException, DOMException {
        if (this.m_doc != null) {
            if (contextNode != this.m_doc && !contextNode.getOwnerDocument().equals(this.m_doc)) {
                final String fmsg = XPATHMessages.createXPATHMessage("ER_WRONG_DOCUMENT", null);
                throw new DOMException((short)4, fmsg);
            }
            final short nodeType = contextNode.getNodeType();
            if (nodeType != 9 && nodeType != 1 && nodeType != 2 && nodeType != 3 && nodeType != 4 && nodeType != 8 && nodeType != 7 && nodeType != 13) {
                final String fmsg2 = XPATHMessages.createXPATHMessage("ER_WRONG_NODETYPE", null);
                throw new DOMException((short)9, fmsg2);
            }
        }
        if (!XPathResultImpl.isValidType(type)) {
            final String fmsg = XPATHMessages.createXPATHMessage("ER_INVALID_XPATH_TYPE", new Object[] { new Integer(type) });
            throw new XPathException((short)2, fmsg);
        }
        final XPathContext xpathSupport = new XPathContext();
        if (null != this.m_doc) {
            xpathSupport.getDTMHandleFromNode(this.m_doc);
        }
        XObject xobj = null;
        try {
            xobj = this.m_xpath.execute(xpathSupport, contextNode, this.m_resolver);
        }
        catch (TransformerException te) {
            throw new XPathException((short)1, te.getMessageAndLocation());
        }
        return new XPathResultImpl(type, xobj, contextNode);
    }
}
