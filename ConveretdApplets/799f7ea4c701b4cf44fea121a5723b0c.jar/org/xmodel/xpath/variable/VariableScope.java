// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.variable;

public class VariableScope extends AbstractVariableScope
{
    private int I;
    private String H;
    
    public VariableScope(final String h, final int i) {
        this.H = h;
        this.I = i;
    }
    
    @Override
    public int getPrecedence() {
        return this.I;
    }
    
    @Override
    public String getName() {
        return this.H;
    }
    
    @Override
    public IVariableScope cloneOne() {
        final VariableScope variableScope = new VariableScope(this.H, this.I);
        if (this.variables != null) {
            variableScope.copyFrom(this);
        }
        return variableScope;
    }
}
