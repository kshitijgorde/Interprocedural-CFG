// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.event.ExpressionListener;
import prefuse.data.Tuple;

public class NotPredicate extends AbstractPredicate
{
    private Predicate m_predicate;
    
    public NotPredicate(final Predicate predicate) {
        this.m_predicate = predicate;
    }
    
    public Predicate getPredicate() {
        return this.m_predicate;
    }
    
    public boolean getBoolean(final Tuple tuple) {
        return !this.m_predicate.getBoolean(tuple);
    }
    
    public void visit(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visitExpression(this);
        expressionVisitor.down();
        this.m_predicate.visit(expressionVisitor);
        expressionVisitor.up();
    }
    
    public String toString() {
        return "NOT " + this.m_predicate.toString();
    }
    
    protected void addChildListeners() {
        this.m_predicate.addExpressionListener(this);
    }
    
    protected void removeChildListeners() {
        this.m_predicate.removeExpressionListener(this);
    }
}
