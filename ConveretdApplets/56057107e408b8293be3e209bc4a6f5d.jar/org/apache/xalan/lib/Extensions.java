// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib;

import java.util.StringTokenizer;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Text;
import org.w3c.dom.Document;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XBoolean;
import org.xml.sax.SAXNotSupportedException;
import org.apache.xpath.XPath;
import org.apache.xpath.XPathContext;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.extensions.ExpressionContext;
import java.util.Hashtable;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xpath.NodeSet;
import org.w3c.dom.traversal.NodeIterator;

public class Extensions
{
    public static NodeSet difference(final NodeIterator ni1, final NodeIterator ni2) throws TransformerException {
        final NodeSet ns1 = new NodeSet(ni1);
        final NodeSet ns2 = new NodeSet(ni2);
        final NodeSet diff = new NodeSet();
        diff.setShouldCacheNodes(true);
        for (int i = 0; i < ns1.getLength(); ++i) {
            final Node n = ns1.elementAt(i);
            if (!ns2.contains(n)) {
                diff.addElement(n);
            }
        }
        return diff;
    }
    
    public static NodeSet distinct(final NodeIterator ni) throws TransformerException {
        final NodeSet ns = new NodeSet(ni);
        final NodeSet dist = new NodeSet();
        dist.setShouldCacheNodes(true);
        final Hashtable stringTable = new Hashtable();
        for (int i = 0; i < ns.getLength(); ++i) {
            final Node n = ns.elementAt(i);
            final String key = n.getNodeValue();
            if (!stringTable.containsKey(key)) {
                stringTable.put(key, n);
                dist.addElement(n);
            }
        }
        return dist;
    }
    
    public static XObject evaluate(final ExpressionContext myContext, final String xpathExpr) throws SAXNotSupportedException, Exception {
        if (myContext instanceof XPathContext) {
            try {
                final XPathContext xctxt = (XPathContext)myContext;
                final XPath dynamicXPath = new XPath(xpathExpr, xctxt.getSAXLocator(), xctxt.getNamespaceContext(), 0);
                return dynamicXPath.execute(xctxt, myContext.getContextNode(), xctxt.getNamespaceContext());
            }
            catch (Exception e) {
                throw e;
            }
        }
        throw new SAXNotSupportedException("Invalid context passed to evaluate " + myContext);
    }
    
    public static boolean hasSameNodes(final NodeIterator ni1, final NodeIterator ni2) {
        final NodeSet ns1 = new NodeSet(ni1);
        final NodeSet ns2 = new NodeSet(ni2);
        if (ns1.getLength() != ns2.getLength()) {
            return false;
        }
        for (int i = 0; i < ns1.getLength(); ++i) {
            final Node n = ns1.elementAt(i);
            if (!ns2.contains(n)) {
                return false;
            }
        }
        return true;
    }
    
    public static NodeSet intersection(final NodeIterator ni1, final NodeIterator ni2) throws TransformerException {
        final NodeSet ns1 = new NodeSet(ni1);
        final NodeSet ns2 = new NodeSet(ni2);
        final NodeSet inter = new NodeSet();
        inter.setShouldCacheNodes(true);
        for (int i = 0; i < ns1.getLength(); ++i) {
            final Node n = ns1.elementAt(i);
            if (ns2.contains(n)) {
                inter.addElement(n);
            }
        }
        return inter;
    }
    
    public static NodeSet nodeset(final ExpressionContext myProcessor, final Object rtf) {
        if (rtf instanceof NodeIterator) {
            return new NodeSet((NodeIterator)rtf);
        }
        String textNodeValue;
        if (rtf instanceof String) {
            textNodeValue = (String)rtf;
        }
        else if (rtf instanceof Boolean) {
            textNodeValue = new XBoolean((boolean)rtf).str();
        }
        else if (rtf instanceof Double) {
            textNodeValue = new XNumber((double)rtf).str();
        }
        else {
            textNodeValue = rtf.toString();
        }
        final Document myDoc = myProcessor.getContextNode().getOwnerDocument();
        final Text textNode = myDoc.createTextNode(textNodeValue);
        final DocumentFragment docFrag = myDoc.createDocumentFragment();
        docFrag.appendChild(textNode);
        return new NodeSet(docFrag);
    }
    
    public static NodeSet tokenize(final ExpressionContext myContext, final String toTokenize) {
        return tokenize(myContext, toTokenize, " \t\n\r");
    }
    
    public static NodeSet tokenize(final ExpressionContext myContext, final String toTokenize, final String delims) {
        final Document lDoc = myContext.getContextNode().getOwnerDocument();
        final StringTokenizer lTokenizer = new StringTokenizer(toTokenize, delims);
        final NodeSet resultSet = new NodeSet();
        while (lTokenizer.hasMoreTokens()) {
            resultSet.addNode(lDoc.createTextNode(lTokenizer.nextToken()));
        }
        return resultSet;
    }
}
