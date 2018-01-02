// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.util.collections.CopyOnWriteArrayList;
import prefuse.data.event.ExpressionListener;

public abstract class AbstractExpression implements Expression, ExpressionListener
{
    private CopyOnWriteArrayList m_listeners;
    
    public AbstractExpression() {
        this.m_listeners = new CopyOnWriteArrayList();
    }
    
    public void visit(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visitExpression(this);
    }
    
    public final void addExpressionListener(final ExpressionListener expressionListener) {
        if (!this.m_listeners.contains(expressionListener)) {
            this.m_listeners.add(expressionListener);
            this.addChildListeners();
        }
    }
    
    public final void removeExpressionListener(final ExpressionListener expressionListener) {
        this.m_listeners.remove(expressionListener);
        if (this.m_listeners.size() == 0) {
            this.removeChildListeners();
        }
    }
    
    protected final boolean hasListeners() {
        return this.m_listeners != null && this.m_listeners.size() > 0;
    }
    
    protected final void fireExpressionChange() {
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ExpressionListener)array[i]).expressionChanged(this);
        }
    }
    
    protected void addChildListeners() {
    }
    
    protected void removeChildListeners() {
    }
    
    public void expressionChanged(final Expression expression) {
        this.fireExpressionChange();
    }
    
    public Object get(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
    
    public int getInt(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
    
    public long getLong(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
    
    public float getFloat(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
    
    public double getDouble(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
    
    public boolean getBoolean(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
}
