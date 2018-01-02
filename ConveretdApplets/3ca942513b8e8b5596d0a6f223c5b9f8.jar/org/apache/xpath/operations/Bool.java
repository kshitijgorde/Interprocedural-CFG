// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;

public class Bool extends UnaryOperation
{
    static final long serialVersionUID = 44705375321914635L;
    
    public XObject operate(final XObject right) throws TransformerException {
        if (1 == right.getType()) {
            return right;
        }
        return right.bool() ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
    
    public boolean bool(final XPathContext xctxt) throws TransformerException {
        return super.m_right.bool(xctxt);
    }
}
