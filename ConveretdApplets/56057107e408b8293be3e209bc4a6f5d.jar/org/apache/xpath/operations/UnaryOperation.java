// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.Expression;

public abstract class UnaryOperation extends Expression
{
    protected Expression m_right;
    
    public boolean canTraverseOutsideSubtree() {
        return this.m_right != null && this.m_right.canTraverseOutsideSubtree();
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XObject right = this.m_right.execute(xctxt);
        return this.operate(right);
    }
    
    public abstract XObject operate(final XObject p0) throws TransformerException;
    
    public void setRight(final Expression r) {
        this.m_right = r;
    }
}
