// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.xpath.variable.AbstractVariableScope;
import org.xmodel.xpath.variable.IVariableScope;

public class LocalContext extends StatefulContext
{
    private IContext P;
    
    public LocalContext(final IContext p) {
        super(A(p), p.getObject(), p.getPosition(), p.getSize());
        this.P = p;
    }
    
    private static ContextScope A(final IContext context) {
        final IVariableScope scope = context.getScope();
        if (scope != null) {
            return new _A(scope);
        }
        return new _A();
    }
    
    @Override
    public IContext getParent() {
        return this.P;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof LocalContext && this.P.equals(((LocalContext)o).getParent());
    }
    
    @Override
    public int hashCode() {
        return this.P.hashCode();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("local:");
        sb.append(super.toString());
        return sb.toString();
    }
    
    private static class _A extends ContextScope
    {
        public _A() {
        }
        
        public _A(final IVariableScope variableScope) {
            super(variableScope);
        }
        
        @Override
        protected Object internal_set(final String s, final Object o) {
            final Variable createVariable = this.getCreateVariable(s);
            if (createVariable.value == null) {
                createVariable.value = o;
                return null;
            }
            final Object value = createVariable.value;
            createVariable.value = o;
            return value;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof LocalContext && this.K.equals(((LocalContext)o).getParent());
        }
        
        @Override
        public int hashCode() {
            return this.K.hashCode();
        }
    }
}
