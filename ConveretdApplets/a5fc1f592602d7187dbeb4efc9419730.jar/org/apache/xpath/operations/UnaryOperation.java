// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import org.apache.xpath.XPathVisitor;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.ExpressionNode;
import java.util.Vector;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.Expression;

public abstract class UnaryOperation extends Expression implements ExpressionOwner
{
    protected Expression m_right;
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        this.m_right.fixupVariables(vars, globalsSize);
    }
    
    public boolean canTraverseOutsideSubtree() {
        return null != this.m_right && this.m_right.canTraverseOutsideSubtree();
    }
    
    public void setRight(final Expression r) {
        (this.m_right = r).exprSetParent(this);
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return this.operate(this.m_right.execute(xctxt));
    }
    
    public abstract XObject operate(final XObject p0) throws TransformerException;
    
    public Expression getOperand() {
        return this.m_right;
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        if (visitor.visitUnaryOperation(owner, this)) {
            this.m_right.callVisitors(this, visitor);
        }
    }
    
    public Expression getExpression() {
        return this.m_right;
    }
    
    public void setExpression(final Expression exp) {
        exp.exprSetParent(this);
        this.m_right = exp;
    }
    
    public boolean deepEquals(final Expression expr) {
        return this.isSameClass(expr) && this.m_right.deepEquals(((UnaryOperation)expr).m_right);
    }
}
