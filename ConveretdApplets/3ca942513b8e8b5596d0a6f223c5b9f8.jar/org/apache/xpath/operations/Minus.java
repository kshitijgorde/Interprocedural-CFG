// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;

public class Minus extends Operation
{
    static final long serialVersionUID = -5297672838170871043L;
    
    public XObject operate(final XObject left, final XObject right) throws TransformerException {
        return new XNumber(left.num() - right.num());
    }
    
    public double num(final XPathContext xctxt) throws TransformerException {
        return super.m_left.num(xctxt) - super.m_right.num(xctxt);
    }
}
