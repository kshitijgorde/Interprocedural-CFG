import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class lda extends public
{
    public lda(final int[] array, final b b) {
        super("CCI - Commodity Channel Index", 1, array, new double[] { -100.0, 100.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1;
    }
    
    protected void H() {
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        final double[] h = super.Voa.h();
        if (_ == null || g == null || h == null) {
            super.Woa = null;
            super.Yoa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (_[i] + g[i] + h[i]) / 3.0;
        }
        final double[] array2 = new double[h.length];
        e.calculateSimpleAvg(array, array2, 0, super.Oa[0]);
        final double[] array3 = new double[h.length];
        for (int j = 0; j < array3.length; ++j) {
            array3[j] = 0.0;
            int n;
            for (n = 0; j - n >= 0 && n < super.Oa[0]; ++n) {
                final double[] array4 = array3;
                final int n2 = j;
                array4[n2] += Math.abs(array2[j] - array[j - n]);
            }
            final double[] array5 = array3;
            final int n3 = j;
            array5[n3] /= n + 1;
        }
        for (int k = 0; k < super.Woa[0].length; ++k) {
            if (array3[k] != 0.0) {
                super.Woa[0][k] = (array[k] - array2[k]) / (0.015 * array3[k]);
            }
            else {
                super.Woa[0][k] = 0.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        super.Yoa = new byte[h.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)e.b(super.Woa[0], 250.0, super.Uoa[0]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = -1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)e._(super.Woa[0], -250.0, super.Uoa[0]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = 1;
        }
    }
}
