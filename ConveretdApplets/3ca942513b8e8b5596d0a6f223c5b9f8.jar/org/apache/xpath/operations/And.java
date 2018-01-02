// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class And extends Operation
{
    static final long serialVersionUID = 392330077126534022L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XObject expr1 = super.m_left.execute(xctxt);
        if (expr1.bool()) {
            final XObject expr2 = super.m_right.execute(xctxt);
            return expr2.bool() ? XBoolean.S_TRUE : XBoolean.S_FALSE;
        }
        return XBoolean.S_FALSE;
    }
    
    public boolean bool(final XPathContext xctxt) throws TransformerException {
        return super.m_left.bool(xctxt) && super.m_right.bool(xctxt);
    }
}
