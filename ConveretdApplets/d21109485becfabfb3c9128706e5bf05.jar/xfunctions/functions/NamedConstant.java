// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public class NamedConstant extends MathSymbol
{
    private double value;
    
    public double getValue() {
        return this.value;
    }
    
    public NamedConstant(final String name, final double value) {
        this.value = value;
        this.setName(name);
    }
}
