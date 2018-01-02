// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;

public class Equals extends Operation
{
    public XObject operate(final XObject left, final XObject right) throws TransformerException {
        return left.equals(right) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
    
    public boolean bool(final XPathContext xctxt) throws TransformerException {
        final XObject left = super.m_left.execute(xctxt, true);
        final XObject right = super.m_right.execute(xctxt, true);
        final boolean result = left.equals(right);
        left.detach();
        right.detach();
        return result;
    }
}
