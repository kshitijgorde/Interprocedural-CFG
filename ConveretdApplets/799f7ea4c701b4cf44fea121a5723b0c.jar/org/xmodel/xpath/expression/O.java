// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModelObject;

class O extends Context
{
    IContext J;
    
    public O(final IContext j, final String s, final IModelObject modelObject) {
        super(new N(j.getScope(), s, modelObject), j.getObject(), j.getPosition(), j.getSize());
        this.J = j;
    }
    
    @Override
    public IContext getParent() {
        return this.J;
    }
    
    @Override
    public Object get(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof O) {
            final O o2 = (O)o;
            return this.J.equals(o2.getParent()) && o2.scope.equals(this.scope);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.scope.hashCode();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("return:");
        sb.append(super.toString());
        return sb.toString();
    }
}
