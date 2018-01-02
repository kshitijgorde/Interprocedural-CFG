// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

class ConstantNode extends Expression
{
    double value;
    
    ConstantNode(final double value) {
        this.value = value;
    }
    
    public double value() {
        return this.value;
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
        return new ConstantNode(this.value);
    }
    
    public void put(final StringBuffer sb) {
        final String realToString = Utilities.realToString(this.value);
        if (this.value < 0.0) {
            sb.append("(" + realToString + ")");
        }
        else {
            sb.append(realToString);
        }
    }
    
    boolean contains(final MathSymbol mathSymbol) {
        return false;
    }
}
