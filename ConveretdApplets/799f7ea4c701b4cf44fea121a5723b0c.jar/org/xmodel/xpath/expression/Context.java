// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.List;
import org.xmodel.IModel;
import java.util.HashMap;
import org.xmodel.xpath.variable.IVariableScope;
import java.util.Map;
import org.xmodel.IModelObject;

public class Context implements IContext
{
    private IModelObject F;
    private int E;
    private int G;
    private Map<IExpression, Integer> H;
    protected IVariableScope scope;
    
    public Context(final IModelObject modelObject) {
        this(modelObject, 1, 1);
    }
    
    public Context(final IModelObject f, final int e, final int g) {
        this.F = f;
        this.E = e;
        this.G = g;
        this.H = new HashMap<IExpression, Integer>(1);
    }
    
    protected Context(final IVariableScope variableScope, final IModelObject modelObject) {
        this(variableScope, modelObject, 1, 1);
    }
    
    protected Context(final IVariableScope scope, final IModelObject f, final int e, final int g) {
        this.F = f;
        this.E = e;
        this.G = g;
        this.H = new HashMap<IExpression, Integer>(1);
        this.scope = scope;
    }
    
    public Context(final IContext context, final IModelObject modelObject) {
        this((context != null) ? context.getScope() : null, modelObject, 1, 1);
    }
    
    public Context(final IContext context, final IModelObject modelObject, final int n, final int n2) {
        this((context != null) ? context.getScope() : null, modelObject, n, n2);
    }
    
    public String getName() {
        return "context";
    }
    
    public int getPrecedence() {
        return 2;
    }
    
    @Override
    public IModel getModel() {
        return (this.F != null) ? this.F.getModel() : null;
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
        return this.F;
    }
    
    @Override
    public int getPosition() {
        return this.E;
    }
    
    @Override
    public int getSize() {
        return this.G;
    }
    
    @Override
    public Boolean set(final String s, final Boolean b) {
        if (this.scope != null) {
            return this.scope.set(s, b);
        }
        return null;
    }
    
    @Override
    public List<IModelObject> set(final String s, final IModelObject modelObject) {
        if (this.scope != null) {
            return this.scope.set(s, modelObject);
        }
        return null;
    }
    
    @Override
    public List<IModelObject> set(final String s, final List<IModelObject> list) {
        if (this.scope != null) {
            return this.scope.set(s, list);
        }
        return null;
    }
    
    @Override
    public Number set(final String s, final Number n) {
        if (this.scope != null) {
            return this.scope.set(s, n);
        }
        return null;
    }
    
    @Override
    public String set(final String s, final String s2) {
        if (this.scope != null) {
            return this.scope.set(s, s2);
        }
        return null;
    }
    
    @Override
    public Object get(final String s) {
        if (this.scope != null) {
            return this.scope.get(s);
        }
        return null;
    }
    
    @Override
    public void notifyBind(final IExpression expression) {
        this.H.remove(expression);
    }
    
    @Override
    public void notifyUnbind(final IExpression expression) {
        this.H.remove(expression);
    }
    
    @Override
    public void notifyUpdate(final IExpression expression) {
        this.H.put(expression, this.getModel().getUpdateID());
    }
    
    @Override
    public void markUpdate(final IExpression expression) {
        this.H.remove(expression);
    }
    
    @Override
    public boolean shouldUpdate(final IExpression expression) {
        final Integer n = this.H.get(expression);
        return n == null || n == 0 || n != this.getModel().getUpdateID();
    }
    
    @Override
    public int getLastUpdate(final IExpression expression) {
        final Integer n = this.H.get(expression);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    @Override
    public IVariableScope getScope() {
        return this.scope;
    }
    
    public IVariableScope cloneOne() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int hashCode() {
        return this.F.hashCode() ^ this.E + (this.G << 16);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IContext)) {
            return false;
        }
        final IContext context = (IContext)o;
        return context.getObject() == this.getObject() && context.getPosition() == this.getPosition() && context.getSize() == this.getSize();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(this.E);
        sb.append(", ");
        sb.append(this.G);
        sb.append(", ");
        sb.append(this.F);
        sb.append("]");
        return sb.toString();
    }
}
