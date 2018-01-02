// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public abstract class Function extends MathSymbol
{
    protected Variable[] params;
    protected int arity;
    public double preferredXmin;
    public double preferredXmax;
    public double preferredYmin;
    public double preferredYmax;
    
    public int getArity() {
        return this.arity;
    }
    
    public abstract double eval(final double p0);
    
    public abstract double eval(final double p0, final double p1);
    
    public abstract double eval(final double[] p0);
    
    public abstract Function derivative(final int p0);
    
    public abstract boolean checkCases();
    
    public Function derivative() {
        if (this.arity != 1) {
            throw new IllegalArgumentException("Attempt to take the derivative of a multiparameter function, without specifying which partial to compute.");
        }
        return this.derivative(1);
    }
    
    void setParam(final int n, final double value) {
        this.params[n].setValue(value);
    }
    
    public boolean contains(final MathSymbol mathSymbol) {
        return false;
    }
    
    public Function() {
        this.preferredXmin = -5.0;
        this.preferredXmax = 5.0;
        this.preferredYmin = -5.0;
        this.preferredYmax = 5.0;
    }
}
