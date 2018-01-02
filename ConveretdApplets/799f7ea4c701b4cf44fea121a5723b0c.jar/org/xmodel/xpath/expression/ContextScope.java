// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.A.F;
import org.xmodel.A.B;
import org.xmodel.xpath.variable.IVariableListener;
import java.util.HashSet;
import java.util.Collection;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.variable.AbstractVariableScope;

public class ContextScope extends AbstractVariableScope
{
    IVariableScope K;
    boolean J;
    
    public ContextScope() {
    }
    
    public ContextScope(final IVariableScope k) {
        this.K = k;
    }
    
    @Override
    public int getPrecedence() {
        return 2;
    }
    
    @Override
    public String getName() {
        return "context";
    }
    
    public IVariableScope getParent() {
        return this.K;
    }
    
    @Override
    public Object get(final String s, final IContext context) throws ExpressionException {
        final Object value = super.get(s, context);
        if (value != null) {
            return value;
        }
        if (this.K != null) {
            return this.K.get(s, context);
        }
        return null;
    }
    
    @Override
    public Object get(final String s) {
        final Object value = super.get(s);
        if (value != null) {
            return value;
        }
        if (this.K != null) {
            return this.K.get(s);
        }
        return null;
    }
    
    @Override
    public Collection<String> getAll() {
        if (this.K != null) {
            final HashSet<Object> set = (HashSet<Object>)new HashSet<String>();
            set.addAll(this.K.getAll());
            set.addAll(super.getAll());
            return (Collection<String>)set;
        }
        return super.getAll();
    }
    
    @Override
    public IExpression.ResultType getType(final String s) {
        final IExpression.ResultType type = super.getType(s);
        if (type != IExpression.ResultType.UNDEFINED) {
            return type;
        }
        if (this.K != null) {
            return this.K.getType(s);
        }
        return IExpression.ResultType.UNDEFINED;
    }
    
    @Override
    public IExpression.ResultType getType(final String s, final IContext context) {
        final IExpression.ResultType type = super.getType(s, context);
        if (type != IExpression.ResultType.UNDEFINED) {
            return type;
        }
        if (this.K != null) {
            return this.K.getType(s, context);
        }
        return IExpression.ResultType.UNDEFINED;
    }
    
    @Override
    public boolean isDefined(final String s) {
        return (this.K != null && this.K.isDefined(s)) || super.isDefined(s);
    }
    
    @Override
    public boolean isBound(final String s) {
        return super.isBound(s) || (this.K != null && this.K.isBound(s));
    }
    
    @Override
    public void addListener(final String s, final IContext context, final IVariableListener variableListener) {
        if (super.isDefined(s)) {
            super.addListener(s, context, variableListener);
        }
        else if (this.K != null) {
            this.K.addListener(s, context, variableListener);
        }
    }
    
    @Override
    public void removeListener(final String s, final IContext context, final IVariableListener variableListener) {
        if (super.isDefined(s)) {
            super.removeListener(s, context, variableListener);
        }
        else if (this.K != null) {
            this.K.removeListener(s, context, variableListener);
        }
    }
    
    @Override
    public void restore(final B b) {
        this.J = false;
        if (this.isDefined(((F)b).N)) {
            super.restore(b);
        }
        else {
            this.K.restore(b);
        }
    }
    
    @Override
    public void revert(final B b) {
        if (this.isDefined(((F)b).N)) {
            super.revert(b);
        }
        else {
            this.K.revert(b);
        }
        this.J = true;
    }
    
    @Override
    public void define(final String s, final IExpression expression) {
        if (this.J) {
            throw new IllegalStateException("Illegal set on reverted context: " + this);
        }
        super.define(s, expression);
    }
    
    @Override
    public Boolean set(final String s, final Boolean b) {
        if (this.J) {
            throw new IllegalStateException("Illegal set on reverted context: " + this);
        }
        return super.set(s, b);
    }
    
    @Override
    public List<IModelObject> set(final String s, final IModelObject modelObject) {
        if (this.J) {
            throw new IllegalStateException("Illegal set on reverted context: " + this);
        }
        return super.set(s, modelObject);
    }
    
    @Override
    public List<IModelObject> set(final String s, final List<IModelObject> list) {
        if (this.J) {
            throw new IllegalStateException("Illegal set on reverted context: " + this);
        }
        return super.set(s, list);
    }
    
    @Override
    public Number set(final String s, final Number n) {
        if (this.J) {
            throw new IllegalStateException("Illegal set on reverted context: " + this);
        }
        return super.set(s, n);
    }
    
    @Override
    public String set(final String s, final String s2) {
        if (this.J) {
            throw new IllegalStateException("Illegal set on reverted context: " + this);
        }
        return super.set(s, s2);
    }
    
    @Override
    public IVariableScope cloneOne() {
        final ContextScope contextScope = new ContextScope();
        if (this.variables != null) {
            contextScope.copyFrom(this);
        }
        return contextScope;
    }
}
