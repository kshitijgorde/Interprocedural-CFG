// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xalan.stree.Child;
import javax.xml.transform.TransformerException;
import org.apache.xpath.axes.ContextNodeList;
import org.w3c.dom.Node;
import org.apache.xpath.axes.LocPathIterator;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.axes.PredicatedNodeTest;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncCurrent extends Function
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final PredicatedNodeTest iter = (PredicatedNodeTest)xctxt.getSubContextList();
        Node currentNode;
        if (iter != null) {
            final LocPathIterator lpi = iter.getLocPathIterator();
            currentNode = lpi.getCurrentContextNode();
        }
        else {
            final ContextNodeList cnl = xctxt.getContextNodeList();
            if (cnl != null) {
                currentNode = cnl.getCurrentNode();
            }
            else {
                currentNode = null;
            }
        }
        return new XNodeSet(currentNode);
    }
    
    protected String nodeToString(final Node n) {
        return (n != null) ? (String.valueOf(n.getNodeName()) + "{" + ((Child)n).getUid() + "}") : "null";
    }
}
