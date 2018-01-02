// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncRound extends FunctionOneArg
{
    static final long serialVersionUID = -7970583902573826611L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XObject obj = super.m_arg0.execute(xctxt);
        final double val = obj.num();
        if (val >= -0.5 && val < 0.0) {
            return new XNumber(-0.0);
        }
        if (val == 0.0) {
            return new XNumber(val);
        }
        return new XNumber(Math.floor(val + 0.5));
    }
}
