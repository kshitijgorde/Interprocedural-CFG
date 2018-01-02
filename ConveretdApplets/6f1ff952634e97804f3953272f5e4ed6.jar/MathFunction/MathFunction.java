// 
// Decompiled by Procyon v0.5.30
// 

package MathFunction;

import com.primalworld.math.MathEvaluator;

public class MathFunction
{
    private MathEvaluator func;
    private final int N = 10000;
    
    public MathFunction(final String s) {
        this.func = new MathEvaluator(s);
    }
    
    public void setFunction(final String s) {
        this.func = new MathEvaluator(s);
    }
    
    public double evaluate(final double n) {
        this.func.addVariable("x", n);
        return this.func.getValue();
    }
    
    public double integrate(double n, double n2) {
        if (n == n2) {
            return 0.0;
        }
        if (n > n2) {
            final double n3 = n;
            n = n2;
            n2 = n3;
        }
        float n4 = 0.0f;
        for (double n5 = (n2 - n) / 10000.0, n6 = n; n6 <= n2; n6 += n5 / 2.0) {
            n4 += (float)(this.evaluate(n6) * n5 / 2.0);
        }
        if (Float.toString(n4).indexOf("E") > 0) {
            return Math.round(n4);
        }
        return n4;
    }
    
    public double length(double n, double n2) {
        if (n > n2) {
            final double n3 = n;
            n = n2;
            n2 = n3;
        }
        float n4 = 0.0f;
        for (double n5 = (n2 - n) / 10000.0, n6 = n; n6 <= n2; n6 += n5 / 2.0) {
            n4 += (float)Math.sqrt((this.evaluate(n6 + n5 / 2.0) - this.evaluate(n6)) * (this.evaluate(n6 + n5 / 2.0) - this.evaluate(n6)) + (n6 + n5 / 2.0 - n6) * (n6 + n5 / 2.0 - n6));
        }
        if (Float.toString(n4).indexOf("E") > 0) {
            return Math.round(n4);
        }
        return n4;
    }
    
    public double Bolzano(final double n, final double n2) {
        return 0.0;
    }
}
