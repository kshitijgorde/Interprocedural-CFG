// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

class VariableNode extends Expression
{
    Variable variable;
    
    VariableNode(final Variable variable) {
        this.variable = variable;
    }
    
    public double value() {
        return this.variable.getValue();
    }
    
    public Expression derivative(final Variable variable) {
        if (variable == this.variable) {
            return new ConstantNode(1.0);
        }
        return new ConstantNode(0.0);
    }
    
    public boolean isLocallyConstantWRT(final Variable variable) {
        return this.variable != variable;
    }
    
    boolean isConstant() {
        return false;
    }
    
    public Expression copy() {
        return new VariableNode(this.variable);
    }
    
    public void put(final StringBuffer sb) {
        sb.append(this.variable.getName());
    }
    
    boolean contains(final MathSymbol mathSymbol) {
        return mathSymbol == this.variable;
    }
}
