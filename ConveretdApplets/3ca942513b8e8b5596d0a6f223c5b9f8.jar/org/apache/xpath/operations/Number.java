// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;

public class Number extends UnaryOperation
{
    static final long serialVersionUID = 7196954482871619765L;
    
    public XObject operate(final XObject right) throws TransformerException {
        if (2 == right.getType()) {
            return right;
        }
        return new XNumber(right.num());
    }
    
    public double num(final XPathContext xctxt) throws TransformerException {
        return super.m_right.num(xctxt);
    }
}
