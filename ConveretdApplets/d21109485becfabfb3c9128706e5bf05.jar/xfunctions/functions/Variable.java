// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public class Variable extends MathSymbol
{
    private double value;
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    public Variable(final String s) {
        this(s, 0.0);
    }
    
    public Variable(final String name, final double value) {
        this.setName(name);
        this.value = value;
    }
}
