// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class ValueMath implements Value
{
    private Function f;
    private double[] param;
    private Value x;
    private Value y;
    private char op;
    
    public ValueMath(final Value x, final Value y, final char op) {
        this.x = x;
        this.y = y;
        this.op = op;
    }
    
    public ValueMath(final Function f, final Value x) {
        if (f.getArity() != 1) {
            throw new IllegalArgumentException("Internal Error:  The function in a ValueMath object must have arity 1.");
        }
        this.f = f;
        this.x = x;
        this.param = new double[1];
    }
    
    public double getVal() {
        if (this.f != null) {
            this.param[0] = this.x.getVal();
            return this.f.getVal(this.param);
        }
        final double val = this.x.getVal();
        final double val2 = this.y.getVal();
        switch (this.op) {
            case '+': {
                return val + val2;
            }
            case '-': {
                return val - val2;
            }
            case '*': {
                return val * val2;
            }
            case '/': {
                return val / val2;
            }
            case '^': {
                return Math.pow(val, val2);
            }
            default: {
                throw new IllegalArgumentException("Internal Error:  Unknown math operator.");
            }
        }
    }
}
