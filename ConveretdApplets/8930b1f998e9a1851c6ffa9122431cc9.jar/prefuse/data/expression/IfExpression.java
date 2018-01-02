// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.util.TypeLib;
import prefuse.data.Schema;
import prefuse.data.event.ExpressionListener;

public class IfExpression extends AbstractExpression
{
    private Predicate m_test;
    private Expression m_then;
    private Expression m_else;
    
    public IfExpression(final Predicate test, final Expression then, final Expression else1) {
        this.m_test = test;
        this.m_then = then;
        this.m_else = else1;
    }
    
    public Predicate getTestPredicate() {
        return this.m_test;
    }
    
    public Expression getThenExpression() {
        return this.m_then;
    }
    
    public Expression getElseExpression() {
        return this.m_else;
    }
    
    public void setTestPredicate(final Predicate test) {
        this.m_test.removeExpressionListener(this);
        this.m_test = test;
        if (this.hasListeners()) {
            test.addExpressionListener(this);
        }
        this.fireExpressionChange();
    }
    
    public void setThenExpression(final Expression then) {
        this.m_then.removeExpressionListener(this);
        this.m_then = then;
        if (this.hasListeners()) {
            then.addExpressionListener(this);
        }
        this.fireExpressionChange();
    }
    
    public void setElseExpression(final Expression else1) {
        this.m_else.removeExpressionListener(this);
        this.m_else = else1;
        if (this.hasListeners()) {
            else1.addExpressionListener(this);
        }
        this.fireExpressionChange();
    }
    
    public Class getType(final Schema schema) {
        return TypeLib.getSharedType(this.m_then.getType(schema), this.m_else.getType(schema));
    }
    
    public Object get(final Tuple tuple) {
        return (this.m_test.getBoolean(tuple) ? this.m_then : this.m_else).get(tuple);
    }
    
    public boolean getBoolean(final Tuple tuple) {
        return (this.m_test.getBoolean(tuple) ? this.m_then : this.m_else).getBoolean(tuple);
    }
    
    public double getDouble(final Tuple tuple) {
        return (this.m_test.getBoolean(tuple) ? this.m_then : this.m_else).getDouble(tuple);
    }
    
    public float getFloat(final Tuple tuple) {
        return (this.m_test.getBoolean(tuple) ? this.m_then : this.m_else).getFloat(tuple);
    }
    
    public int getInt(final Tuple tuple) {
        return (this.m_test.getBoolean(tuple) ? this.m_then : this.m_else).getInt(tuple);
    }
    
    public long getLong(final Tuple tuple) {
        return (this.m_test.getBoolean(tuple) ? this.m_then : this.m_else).getLong(tuple);
    }
    
    public void visit(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visitExpression(this);
        expressionVisitor.down();
        this.m_test.visit(expressionVisitor);
        expressionVisitor.up();
        expressionVisitor.down();
        this.m_then.visit(expressionVisitor);
        expressionVisitor.up();
        expressionVisitor.down();
        this.m_else.visit(expressionVisitor);
        expressionVisitor.up();
    }
    
    protected void addChildListeners() {
        this.m_test.addExpressionListener(this);
        this.m_then.addExpressionListener(this);
        this.m_else.addExpressionListener(this);
    }
    
    protected void removeChildListeners() {
        this.m_test.removeExpressionListener(this);
        this.m_then.removeExpressionListener(this);
        this.m_else.removeExpressionListener(this);
    }
    
    public String toString() {
        return "IF " + this.m_test.toString() + " THEN " + this.m_then.toString() + " ELSE " + this.m_else.toString();
    }
}
