// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import prefuse.data.expression.Predicate;
import prefuse.data.Tuple;
import prefuse.data.expression.ObjectLiteral;
import prefuse.data.expression.IfExpression;
import prefuse.data.expression.Expression;

public class PredicateChain
{
    private Expression m_head;
    private IfExpression m_tail;
    
    public PredicateChain() {
        this.m_head = new ObjectLiteral(null);
        this.m_tail = null;
    }
    
    public Expression getExpression() {
        return this.m_head;
    }
    
    public Object get(final Tuple tuple) {
        return this.m_head.get(tuple);
    }
    
    public void add(final Predicate predicate, final Object o) {
        if (this.m_tail == null) {
            this.m_tail = new IfExpression(predicate, new ObjectLiteral(o), this.m_head);
            this.m_head = this.m_tail;
        }
        else {
            final IfExpression ifExpression = new IfExpression(predicate, new ObjectLiteral(o), this.m_tail.getElseExpression());
            this.m_tail.setElseExpression(ifExpression);
            this.m_tail = ifExpression;
        }
    }
    
    public boolean remove(final Predicate predicate) {
        if (predicate == null) {
            return false;
        }
        IfExpression tail = null;
        IfExpression ifExpression;
        for (Expression expression = this.m_head; expression instanceof IfExpression; expression = ifExpression.getElseExpression()) {
            ifExpression = (IfExpression)expression;
            if (predicate.equals(ifExpression.getTestPredicate())) {
                final Expression elseExpression = ifExpression.getElseExpression();
                ifExpression.setElseExpression(new ObjectLiteral(null));
                if (tail != null) {
                    tail.setElseExpression(elseExpression);
                    if (ifExpression == this.m_tail) {
                        this.m_tail = tail;
                    }
                }
                else {
                    this.m_head = elseExpression;
                    if (ifExpression == this.m_tail) {
                        this.m_tail = null;
                    }
                }
                return true;
            }
            tail = ifExpression;
        }
        return false;
    }
    
    public void clear() {
        this.m_head = new ObjectLiteral(null);
        this.m_tail = null;
    }
}
