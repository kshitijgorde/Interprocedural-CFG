import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ai extends implements
{
    public Ai(final int[] array, final class class1) {
        super("NVI - Negative Volume Index", 3, array, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = 0;
        super.r[1] = super.Ua[0] - 1;
        super.r[2] = Math.max(super.Ua[0], super.Ua[1]) - 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        final double[] f = super.s.f();
        if (_ == null) {
            super.t = null;
            super.z = null;
            return;
        }
        super.t = new double[super.u][_.length];
        super.t[0][0] = 100.0;
        for (int i = 1; i < super.t[0].length; ++i) {
            if (f[i] <= f[i - 1] && _[i - 1] > 0.0) {
                super.t[0][i] = _[i] / _[i - 1] - 1.0;
            }
            else {
                super.t[0][i] = 0.0;
            }
        }
        for (int j = 1; j < super.t[0].length; ++j) {
            final double[] array = super.t[0];
            final int n = j;
            array[n] += super.t[0][j - 1];
        }
        do.calculateExpAvg(super.t[0], super.t[1], 0, super.Ua[0]);
        do.calculateExpAvg(super.t[0], super.t[2], 0, super.Ua[1]);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)do._(super.t[1], super.t[2], super.r[2]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)do.a(super.t[1], super.t[2], super.r[2]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = -1;
        }
    }
}
