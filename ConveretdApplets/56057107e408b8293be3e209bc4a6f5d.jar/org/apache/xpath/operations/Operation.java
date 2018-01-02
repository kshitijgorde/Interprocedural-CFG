// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.Expression;

public class Operation extends Expression
{
    protected Expression m_left;
    protected Expression m_right;
    
    public boolean canTraverseOutsideSubtree() {
        return (this.m_left != null && this.m_left.canTraverseOutsideSubtree()) || (this.m_right != null && this.m_right.canTraverseOutsideSubtree());
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XObject left = this.m_left.execute(xctxt);
        final XObject right = this.m_right.execute(xctxt);
        return this.operate(left, right);
    }
    
    public XObject operate(final XObject left, final XObject right) throws TransformerException {
        return null;
    }
    
    public void setLeftRight(final Expression l, final Expression r) {
        this.m_left = l;
        this.m_right = r;
    }
}
