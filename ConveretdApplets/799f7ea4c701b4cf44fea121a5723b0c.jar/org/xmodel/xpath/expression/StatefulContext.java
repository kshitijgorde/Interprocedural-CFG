// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModel;
import java.util.List;
import java.util.HashMap;
import org.xmodel.NullObject;
import org.xmodel.xpath.variable.IVariableScope;
import java.util.Map;
import org.xmodel.IModelObject;

public class StatefulContext implements IContext
{
    private IModelObject L;
    private int K;
    private int M;
    private Map<IExpression, Integer> O;
    private IVariableScope N;
    
    public StatefulContext() {
        this(new NullObject());
    }
    
    public StatefulContext(final IContext context) {
        this(new ContextScope(context.getScope()), context.getObject(), context.getPosition(), context.getSize());
    }
    
    public StatefulContext(final IModelObject modelObject) {
        this(new ContextScope(), modelObject, 1, 1);
    }
    
    public StatefulContext(final IModelObject modelObject, final int n, final int n2) {
        this(new ContextScope(), modelObject, n, n2);
    }
    
    public StatefulContext(final IVariableScope variableScope, final IModelObject modelObject) {
        this(variableScope, modelObject, 1, 1);
    }
    
    public StatefulContext(final IVariableScope variableScope, final IModelObject l, final int k, final int m) {
        this.L = l;
        this.K = k;
        this.M = m;
        this.O = new HashMap<IExpression, Integer>(1);
        this.N = ((variableScope != null) ? variableScope : new ContextScope());
    }
    
    public StatefulContext(final IContext context, final IModelObject modelObject) {
        this(new ContextScope(context.getScope()), modelObject, 1, 1);
    }
    
    public StatefulContext(final IContext context, final IModelObject modelObject, final int n, final int n2) {
        this(new ContextScope(context.getScope()), modelObject, n, n2);
    }
    
    @Override
    public String set(final String s, final String s2) {
        return this.N.set(s, s2);
    }
    
    @Override
    public Number set(final String s, final Number n) {
        return this.N.set(s, n);
    }
    
    @Override
    public Boolean set(final String s, final Boolean b) {
        return this.N.set(s, b);
    }
    
    @Override
    public List<IModelObject> set(final String s, final List<IModelObject> list) {
        return this.N.set(s, list);
    }
    
    @Override
    public List<IModelObject> set(final String s, final IModelObject modelObject) {
        return this.N.set(s, modelObject);
    }
    
    @Override
    public Object get(final String s) {
        for (IVariableScope variableScope = this.N; variableScope != null; variableScope = ((ContextScope)variableScope).getParent()) {
            if (!(variableScope instanceof ContextScope)) {
                return variableScope.get(s);
            }
            final Object value = variableScope.get(s);
            if (value != null) {
                return value;
            }
        }
        return null;
    }
    
    public void define(final String s, final IExpression expression) {
        this.N.define(s, expression);
    }
    
    public String getName() {
        return "context";
    }
    
    public int getPrecedence() {
        return 2;
    }
    
    @Override
    public IModel getModel() {
        return (this.L != null) ? this.L.getModel() : null;
    }
    
    @Override
    public IContext getParent() {
        return null;
    }
    
    @Override
    public IContext getRoot() {
        return this;
    }
    
    @Override
    public IModelObject getObject() {
        return this.L;
    }
    
    @Override
    public int getPosition() {
        return this.K;
    }
    
    @Override
    public int getSize() {
        return this.M;
    }
    
    @Override
    public void notifyBind(final IExpression expression) {
        this.O.remove(expression);
    }
    
    @Override
    public void notifyUnbind(final IExpression expression) {
        this.O.remove(expression);
    }
    
    @Override
    public void notifyUpdate(final IExpression expression) {
        this.O.put(expression, this.getModel().getUpdateID());
    }
    
    @Override
    public void markUpdate(final IExpression expression) {
        this.O.remove(expression);
    }
    
    @Override
    public boolean shouldUpdate(final IExpression expression) {
        final Integer n = this.O.get(expression);
        return n == null || n == 0 || n != this.getModel().getUpdateID();
    }
    
    @Override
    public int getLastUpdate(final IExpression expression) {
        final Integer n = this.O.get(expression);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    @Override
    public IVariableScope getScope() {
        return this.N;
    }
    
    public IVariableScope cloneOne() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("$( ");
        sb.append(this.K);
        sb.append(", ");
        sb.append(this.M);
        sb.append(", ");
        sb.append(this.L);
        sb.append(")");
        return sb.toString();
    }
}
