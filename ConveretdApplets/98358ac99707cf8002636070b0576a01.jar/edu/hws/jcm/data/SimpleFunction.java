// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class SimpleFunction implements Function
{
    private Expression e;
    private Variable[] v;
    private String name;
    private double[] save;
    
    public SimpleFunction(final Expression expression, final Variable variable) {
        this(expression, new Variable[] { variable });
    }
    
    public SimpleFunction(final Expression e, final Variable[] v) {
        this.e = e;
        this.v = v;
        this.save = new double[v.length];
    }
    
    public int getArity() {
        return this.v.length;
    }
    
    public double getVal(final double[] array) {
        return this.getValueWithCases(array, null);
    }
    
    public double getValueWithCases(final double[] array, final Cases cases) {
        for (int i = 0; i < this.v.length; ++i) {
            this.save[i] = this.v[i].getVal();
            this.v[i].setVal(array[i]);
        }
        final double valueWithCases = this.e.getValueWithCases(cases);
        for (int j = 0; j < this.v.length; ++j) {
            this.v[j].setVal(this.save[j]);
        }
        return valueWithCases;
    }
    
    public Function derivative(final int n) {
        if (n < 1 || n > this.v.length) {
            throw new IllegalArgumentException("Internal Error.  Function does not have an argument number  " + n);
        }
        return new SimpleFunction(this.e.derivative(this.v[n - 1]), this.v);
    }
    
    public Function derivative(final Variable variable) {
        for (int i = 0; i < this.v.length; ++i) {
            if (variable == this.v[i]) {
                return new SimpleFunction(new Constant(0.0), this.v);
            }
        }
        return new SimpleFunction(this.e.derivative(variable), this.v);
    }
    
    public boolean dependsOn(final Variable variable) {
        for (int i = 0; i < this.v.length; ++i) {
            if (variable == this.v[i]) {
                return false;
            }
        }
        return this.e.dependsOn(variable);
    }
}
