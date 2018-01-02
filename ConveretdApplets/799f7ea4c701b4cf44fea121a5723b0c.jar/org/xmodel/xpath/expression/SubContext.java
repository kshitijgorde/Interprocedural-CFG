// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.List;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModel;
import org.xmodel.IModelObject;

public class SubContext implements IContext
{
    private IContext D;
    private IModelObject B;
    private int A;
    private int C;
    
    public SubContext(final IContext context, final IContext context2) {
        this(context, context2.getObject(), context2.getPosition(), context2.getSize());
    }
    
    public SubContext(final IContext d, final IModelObject b, final int a, final int c) {
        if (d == null) {
            throw new IllegalArgumentException("SubContext must have parent.");
        }
        this.D = d;
        this.B = b;
        this.A = a;
        this.C = c;
    }
    
    @Override
    public IModel getModel() {
        return (this.B != null) ? this.B.getModel() : null;
    }
    
    @Override
    public IContext getParent() {
        return this.D;
    }
    
    @Override
    public IContext getRoot() {
        IContext parent;
        for (parent = this; parent.getParent() != null; parent = parent.getParent()) {}
        return parent;
    }
    
    @Override
    public IModelObject getObject() {
        return this.B;
    }
    
    @Override
    public int getPosition() {
        return this.A;
    }
    
    @Override
    public int getSize() {
        return this.C;
    }
    
    @Override
    public Boolean set(final String s, final Boolean b) {
        final IVariableScope scope = this.getScope();
        if (scope != null) {
            return scope.set(s, b);
        }
        return null;
    }
    
    @Override
    public List<IModelObject> set(final String s, final IModelObject modelObject) {
        final IVariableScope scope = this.getScope();
        if (scope != null) {
            return scope.set(s, modelObject);
        }
        return null;
    }
    
    @Override
    public List<IModelObject> set(final String s, final List<IModelObject> list) {
        final IVariableScope scope = this.getScope();
        if (scope != null) {
            return scope.set(s, list);
        }
        return null;
    }
    
    @Override
    public Number set(final String s, final Number n) {
        final IVariableScope scope = this.getScope();
        if (scope != null) {
            return scope.set(s, n);
        }
        return null;
    }
    
    @Override
    public String set(final String s, final String s2) {
        final IVariableScope scope = this.getScope();
        if (scope != null) {
            return scope.set(s, s2);
        }
        return null;
    }
    
    @Override
    public Object get(final String s) {
        return this.getParent().get(s);
    }
    
    @Override
    public void notifyBind(final IExpression expression) {
        this.getParent().notifyBind(expression);
    }
    
    @Override
    public void notifyUnbind(final IExpression expression) {
        this.getParent().notifyUnbind(expression);
    }
    
    @Override
    public void notifyUpdate(final IExpression expression) {
        this.getParent().notifyUpdate(expression);
    }
    
    @Override
    public void markUpdate(final IExpression expression) {
        this.getParent().markUpdate(expression);
    }
    
    @Override
    public boolean shouldUpdate(final IExpression expression) {
        return this.getParent().shouldUpdate(expression);
    }
    
    @Override
    public int getLastUpdate(final IExpression expression) {
        return this.getParent().getLastUpdate(expression);
    }
    
    @Override
    public IVariableScope getScope() {
        return this.getParent().getScope();
    }
    
    @Override
    public int hashCode() {
        return this.B.hashCode() ^ this.A + (this.C << 16) ^ this.D.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IContext)) {
            return false;
        }
        final IContext context = (IContext)o;
        if (context.getObject() != this.getObject()) {
            return false;
        }
        if (context.getPosition() != this.getPosition()) {
            return false;
        }
        if (context.getSize() != this.getSize()) {
            return false;
        }
        final IContext parent = context.getParent();
        return this.D == null || parent == null || this.D.equals(parent);
    }
    
    public IContext cloneOne() {
        return new Context(this.B, this.A, this.C);
    }
    
    public IContext cloneTree() {
        return new SubContext(this.D, this.B, this.A, this.C);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("sub:( ");
        sb.append(this.A);
        sb.append(", ");
        sb.append(this.C);
        sb.append(", ");
        sb.append(this.B);
        sb.append(")");
        return sb.toString();
    }
}
