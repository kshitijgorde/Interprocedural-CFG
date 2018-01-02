// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.axes.ContextNodeList;
import org.apache.xpath.axes.SubContextList;
import org.w3c.dom.traversal.NodeIterator;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncLast extends Function
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return new XNumber(this.getCountOfContextNodeList(xctxt));
    }
    
    public int getCountOfContextNodeList(final XPathContext xctxt) throws TransformerException {
        final SubContextList iter = xctxt.getSubContextList();
        if (iter != null) {
            return iter.getLastPos(xctxt);
        }
        final ContextNodeList cnl = xctxt.getContextNodeList();
        if (cnl.size() == 0) {
            try {
                final NodeIterator ni = (NodeIterator)cnl.clone();
                int count = cnl.getCurrentPos();
                while (ni.nextNode() != null) {
                    ++count;
                }
                cnl.setLast(count);
                return count;
            }
            catch (CloneNotSupportedException ex) {}
        }
        return cnl.size();
    }
}
