import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Rda extends public
{
    public Rda(final int[] array, final b b) {
        super("STS - Stochastic", 2, array, new double[] { 20.0, 80.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = Math.max(super.Oa[0] - 1, super.Oa[1] - 1);
        super.Uoa[1] = super.Uoa[0] + super.Oa[2] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        if (h == null || _ == null || g == null) {
            super.Woa = null;
            super.Yoa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[_.length];
        final double[] array2 = new double[g.length];
        e.calculateMin(g, array2, 0, super.Oa[0]);
        e.calculateMax(_, array, 0, super.Oa[0]);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] - array2[i] != 0.0) {
                super.Woa[0][i] = 100.0 * (h[i] - array2[i]) / (array[i] - array2[i]);
            }
            else {
                super.Woa[0][i] = 100.0;
            }
        }
        e.calculateSimpleAvg(super.Woa[0], super.Woa[0], super.Uoa[0], super.Oa[1]);
        e.calculateExpAvg(super.Woa[0], super.Woa[1], super.Uoa[0], super.Oa[2]);
        super.Yoa = new byte[h.length];
        final Enumeration<Integer> elements = e.b(super.Woa[0], 20.0, super.Uoa[0]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = e._(super.Woa[0], 80.0, super.Uoa[0]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = -1;
        }
    }
}
