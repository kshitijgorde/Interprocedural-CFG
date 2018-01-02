// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class Variable extends Constant
{
    public Variable() {
        super(0.0);
    }
    
    public Variable(final String s) {
        super(s, 0.0);
    }
    
    public Variable(final String s, final double n) {
        super(s, n);
    }
    
    public void setVal(final double value) {
        super.value = value;
    }
    
    public Expression derivative(final Variable variable) {
        return new Constant((variable == this) ? 1.0 : 0.0);
    }
    
    public void compileDerivative(final ExpressionProgram expressionProgram, final int n, final ExpressionProgram expressionProgram2, final Variable variable) {
        expressionProgram2.addConstant((variable == this) ? 1.0 : 0.0);
    }
    
    public boolean dependsOn(final Variable variable) {
        return this == variable;
    }
    
    public String toString() {
        final String name = this.getName();
        return (name == null) ? "(unnamed variable)" : name;
    }
}
