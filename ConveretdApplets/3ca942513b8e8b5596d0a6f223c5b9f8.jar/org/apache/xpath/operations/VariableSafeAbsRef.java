// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTMManager;
import org.apache.xpath.Expression;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class VariableSafeAbsRef extends Variable
{
    static final long serialVersionUID = -9174661990819967452L;
    
    public XObject execute(final XPathContext xctxt, final boolean destructiveOK) throws TransformerException {
        XNodeSet xns = (XNodeSet)super.execute(xctxt, destructiveOK);
        final DTMManager dtmMgr = xctxt.getDTMManager();
        final int context = xctxt.getContextNode();
        if (dtmMgr.getDTM(xns.getRoot()).getDocument() != dtmMgr.getDTM(context).getDocument()) {
            final Expression expr = (Expression)xns.getContainedIter();
            xns = (XNodeSet)expr.asIterator(xctxt, context);
        }
        return xns;
    }
}
