// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.axes.ContextNodeList;
import org.apache.xpath.axes.SubContextList;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncPosition extends Function
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return new XNumber(this.getPositionInContextNodeList(xctxt));
    }
    
    public int getPositionInContextNodeList(final XPathContext xctxt) {
        final SubContextList iter = xctxt.getSubContextList();
        if (iter != null) {
            final int prox = iter.getProximityPosition(xctxt);
            return prox;
        }
        final ContextNodeList cnl = xctxt.getContextNodeList();
        if (cnl != null) {
            return cnl.getCurrentPos();
        }
        return -1;
    }
}
