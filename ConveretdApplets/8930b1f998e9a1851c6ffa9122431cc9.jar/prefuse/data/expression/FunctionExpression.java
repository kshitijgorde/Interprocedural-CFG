// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.event.ExpressionListener;
import prefuse.util.collections.CopyOnWriteArrayList;

public abstract class FunctionExpression extends AbstractExpression implements Function
{
    protected CopyOnWriteArrayList m_params;
    protected final int m_pcount;
    
    protected FunctionExpression(final int pcount) {
        this.m_pcount = pcount;
    }
    
    public abstract String getName();
    
    public void addParameter(final Expression expression) {
        final int parameterCount = this.getParameterCount();
        if (parameterCount != -1 && this.paramCount() + 1 > parameterCount) {
            throw new IllegalStateException("This function takes only " + parameterCount + " parameters.");
        }
        if (this.m_params == null) {
            this.m_params = new CopyOnWriteArrayList();
        }
        this.m_params.add(expression);
    }
    
    protected int paramCount() {
        return (this.m_params == null) ? 0 : this.m_params.size();
    }
    
    protected final Expression param(final int n) {
        return (Expression)this.m_params.get(n);
    }
    
    public int getParameterCount() {
        return this.m_pcount;
    }
    
    protected void missingParams() {
        throw new IllegalStateException("Function is missing parameters: " + this.getName());
    }
    
    public void visit(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visitExpression(this);
        if (this.paramCount() > 0) {
            final Object[] array = this.m_params.getArray();
            for (int i = 0; i < array.length; ++i) {
                expressionVisitor.down();
                ((Expression)array[i]).visit(expressionVisitor);
                expressionVisitor.up();
            }
        }
    }
    
    protected void addChildListeners() {
        if (this.paramCount() > 0) {
            final Object[] array = this.m_params.getArray();
            for (int i = 0; i < array.length; ++i) {
                ((Expression)array[i]).addExpressionListener(this);
            }
        }
    }
    
    protected void removeChildListeners() {
        if (this.paramCount() > 0) {
            final Object[] array = this.m_params.getArray();
            for (int i = 0; i < array.length; ++i) {
                ((Expression)array[i]).removeExpressionListener(this);
            }
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.getName()).append('(');
        for (int i = 0; i < this.paramCount(); ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.param(i).toString());
        }
        sb.append(')');
        return sb.toString();
    }
}
