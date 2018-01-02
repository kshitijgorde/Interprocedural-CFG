// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class Constant implements Expression, ExpressionCommand, MathObject
{
    private String name;
    protected double value;
    
    public Constant(final double value) {
        this.value = value;
    }
    
    public Constant(final String name, final double value) {
        this.setName(name);
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public double getVal() {
        return this.value;
    }
    
    public double getValueWithCases(final Cases cases) {
        return this.value;
    }
    
    public Expression derivative(final Variable variable) {
        return new Constant(0.0);
    }
    
    public String toString() {
        if (this.name == null) {
            return NumUtils.realToString(this.value);
        }
        return this.name;
    }
    
    public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
        stackOfDouble.push(this.getVal());
    }
    
    public void compileDerivative(final ExpressionProgram expressionProgram, final int n, final ExpressionProgram expressionProgram2, final Variable variable) {
        expressionProgram2.addConstant(0.0);
    }
    
    public int extent(final ExpressionProgram expressionProgram, final int n) {
        return 1;
    }
    
    public boolean dependsOn(final Variable variable) {
        return false;
    }
    
    public void appendOutputString(final ExpressionProgram expressionProgram, final int n, final StringBuffer sb) {
        sb.append(this.toString());
    }
}
