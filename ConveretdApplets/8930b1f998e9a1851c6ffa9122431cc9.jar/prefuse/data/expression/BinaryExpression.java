// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.event.ExpressionListener;

public abstract class BinaryExpression extends AbstractExpression
{
    protected int m_op;
    protected Expression m_left;
    protected Expression m_right;
    
    protected BinaryExpression(final int op, final int n, final int n2, final Expression left, final Expression right) {
        if (op < n || op > n2) {
            throw new IllegalArgumentException("Unknown operation type: " + op);
        }
        if (left == null || right == null) {
            throw new IllegalArgumentException("Expressions must be non-null.");
        }
        this.m_op = op;
        this.m_left = left;
        this.m_right = right;
    }
    
    public Expression getLeftExpression() {
        return this.m_left;
    }
    
    public Expression getRightExpression() {
        return this.m_right;
    }
    
    public void setLeftExpression(final Expression left) {
        this.m_left.removeExpressionListener(this);
        this.m_left = left;
        if (this.hasListeners()) {
            left.addExpressionListener(this);
        }
        this.fireExpressionChange();
    }
    
    public void setRightExpression(final Expression right) {
        this.m_right.removeExpressionListener(this);
        this.m_right = right;
        if (this.hasListeners()) {
            right.addExpressionListener(this);
        }
        this.fireExpressionChange();
    }
    
    public int getOperation() {
        return this.m_op;
    }
    
    public void visit(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visitExpression(this);
        expressionVisitor.down();
        this.m_left.visit(expressionVisitor);
        expressionVisitor.up();
        expressionVisitor.down();
        this.m_right.visit(expressionVisitor);
        expressionVisitor.up();
    }
    
    protected void addChildListeners() {
        this.m_left.addExpressionListener(this);
        this.m_right.addExpressionListener(this);
    }
    
    protected void removeChildListeners() {
        this.m_left.removeExpressionListener(this);
        this.m_right.removeExpressionListener(this);
    }
}
