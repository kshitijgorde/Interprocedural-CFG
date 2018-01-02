import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class return extends implements
{
    public return(final int[] array, final class class1) {
        super("MACD - Moving Average Conv. Div.", 3, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = Math.max(super.Ua[0], super.Ua[1]) - 1;
        super.r[1] = super.r[0] + super.Ua[2] - 1;
        super.r[2] = super.r[1];
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            super.z = null;
            return;
        }
        super.t = new double[super.u][_.length];
        do.calculateExpAvg(_, super.t[0], 0, super.Ua[0]);
        do.calculateExpAvg(_, super.t[1], 0, super.Ua[1]);
        for (int i = 0; i < _.length; ++i) {
            final double[] array = super.t[0];
            final int n = i;
            array[n] -= super.t[1][i];
        }
        do._(super.t[0], super.r[0], 0.0);
        do.calculateExpAvg(super.t[0], super.t[1], super.r[0], super.Ua[2]);
        for (int j = 0; j < _.length; ++j) {
            super.t[2][j] = super.t[0][j] - super.t[1][j];
        }
        do._(super.t[2], super.r[1], 0.0);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)do._(super.t[0], super.t[1], super.r[1]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)do.a(super.t[0], super.t[1], super.r[1]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = -1;
        }
    }
}
