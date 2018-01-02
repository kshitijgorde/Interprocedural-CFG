// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncQname extends FunctionDef1Arg
{
    static final long serialVersionUID = -1532307875532617380L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final int context = this.getArg0AsNode(xctxt);
        XObject val;
        if (-1 != context) {
            final DTM dtm = xctxt.getDTM(context);
            final String qname = dtm.getNodeNameX(context);
            val = ((null == qname) ? XString.EMPTYSTRING : new XString(qname));
        }
        else {
            val = XString.EMPTYSTRING;
        }
        return val;
    }
}
