// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.A.F;
import org.xmodel.A.B;
import org.xmodel.xpath.variable.IVariableListener;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import org.xmodel.IModelObject;
import org.xmodel.xpath.variable.IVariableSource;
import org.xmodel.xpath.variable.IVariableScope;

class N implements IVariableScope
{
    IVariableSource D;
    IVariableScope B;
    String A;
    Object C;
    
    N(final IVariableScope b, final String a, final IModelObject modelObject) {
        this.B = b;
        this.A = a;
        this.C = Collections.singletonList(modelObject);
    }
    
    @Override
    public String getName() {
        return "context";
    }
    
    @Override
    public int getPrecedence() {
        return 2;
    }
    
    @Override
    public Collection<String> getVariables() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Boolean set(final String s, final Boolean b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public List<IModelObject> set(final String s, final IModelObject modelObject) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public List<IModelObject> set(final String s, final List<IModelObject> list) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Object set(final String s, final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void insert(final String s, final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void insert(final String s, final Object o, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void remove(final String s, final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void remove(final String s, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Number set(final String s, final Number n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String set(final String s, final String s2) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void define(final String s, final IExpression expression) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Object get(final String s, final IContext context) throws ExpressionException {
        if (s.equals(this.A)) {
            return this.C;
        }
        if (this.B == null) {
            return null;
        }
        return this.B.get(s, context);
    }
    
    @Override
    public Object get(final String s) {
        if (s.equals(this.A)) {
            return this.C;
        }
        if (this.B == null) {
            return null;
        }
        return this.B.get(s);
    }
    
    @Override
    public Collection<String> getAll() {
        final HashSet<String> set = new HashSet<String>();
        if (this.B != null) {
            set.addAll((Collection<?>)this.B.getAll());
        }
        set.add(this.A);
        return set;
    }
    
    @Override
    public IVariableSource getSource() {
        return this.D;
    }
    
    @Override
    public IExpression.ResultType getType(final String s) {
        if (s.equals(this.A)) {
            return IExpression.ResultType.NODES;
        }
        return this.B.getType(s);
    }
    
    @Override
    public IExpression.ResultType getType(final String s, final IContext context) {
        if (s.equals(this.A)) {
            return IExpression.ResultType.NODES;
        }
        return this.B.getType(s, context);
    }
    
    @Override
    public void internal_setSource(final IVariableSource d) {
        this.D = d;
    }
    
    @Override
    public boolean isDefined(final String s) {
        return s.equals(this.A) || (this.B != null && this.B.isDefined(s));
    }
    
    @Override
    public boolean isBound(final String s) {
        return !s.equals(this.A) && this.B != null && this.B.isBound(s);
    }
    
    @Override
    public void addListener(final String s, final IContext context, final IVariableListener variableListener) {
        if (s.equals(this.A)) {
            return;
        }
        this.B.addListener(s, context, variableListener);
    }
    
    @Override
    public void removeListener(final String s, final IContext context, final IVariableListener variableListener) {
        if (s.equals(this.A)) {
            return;
        }
        this.B.removeListener(s, context, variableListener);
    }
    
    @Override
    public void restore(final B b) {
        if (((F)b).N.equals(this.A)) {
            return;
        }
        this.B.restore(b);
    }
    
    @Override
    public void revert(final B b) {
        if (((F)b).N.equals(this.A)) {
            return;
        }
        this.B.revert(b);
    }
    
    @Override
    public void copyFrom(final IVariableScope variableScope) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof N) {
            final N n = (N)o;
            return n.A.equals(this.A) && n.C.equals(this.C);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.A.hashCode() ^ this.C.hashCode();
    }
    
    @Override
    public IVariableScope cloneOne() {
        throw new UnsupportedOperationException();
    }
}
