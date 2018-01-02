// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

class NamedConstantNode extends Expression
{
    NamedConstant constant;
    
    NamedConstantNode(final NamedConstant constant) {
        this.constant = constant;
    }
    
    public double value() {
        return this.constant.getValue();
    }
    
    public Expression derivative(final Variable variable) {
        return new ConstantNode(0.0);
    }
    
    public boolean isLocallyConstantWRT(final Variable variable) {
        return true;
    }
    
    boolean isConstant() {
        return true;
    }
    
    public Expression copy() {
        return new NamedConstantNode(this.constant);
    }
    
    public void put(final StringBuffer sb) {
        sb.append(this.constant.getName());
    }
    
    boolean contains(final MathSymbol mathSymbol) {
        return mathSymbol == this.constant;
    }
}
