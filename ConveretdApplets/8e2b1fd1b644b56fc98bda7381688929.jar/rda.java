import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class rda extends public
{
    public rda(final int[] array, final b b) {
        super("DMI - Directional Movement Index", 3, array, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] * 2 + super.Oa[1] * 2;
        super.Uoa[1] = super.Oa[0] * 2;
        super.Uoa[2] = super.Oa[0] * 2;
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
        array[0] = 0.0;
        final double[] array2 = new double[h.length];
        array2[0] = 0.0;
        for (int i = 1; i < array.length; ++i) {
            if (_[i] > _[i - 1] || g[i] < g[i - 1]) {
                array[i] = _[i] - _[i - 1];
                array2[i] = g[i - 1] - g[i];
                if (array[i] > array2[i]) {
                    array2[i] = 0.0;
                }
                else if (array[i] < array2[i]) {
                    array[i] = 0.0;
                }
                else {
                    array[i] = (array2[i] = 0.0);
                }
            }
            else {
                array[i] = (array2[i] = 0.0);
            }
        }
        final double[] array3 = new double[h.length];
        array3[0] = 0.0;
        for (int j = 1; j < array3.length; ++j) {
            final double abs = Math.abs(_[j] - g[j]);
            final double abs2 = Math.abs(_[j] - h[j - 1]);
            final double abs3 = Math.abs(g[j] - h[j - 1]);
            array3[j] = Math.max(abs, abs2);
            array3[j] = Math.max(array3[j], abs3);
        }
        final int n = super.Oa[0] * 2 - 1;
        e.calculateExpAvg(array, array, 1, n);
        e.calculateExpAvg(array2, array2, 1, n);
        e.calculateExpAvg(array3, array3, 1, n);
        for (int k = 0; k < super.Woa[0].length; ++k) {
            if (array3[k] != 0.0) {
                super.Woa[1][k] = 100.0 * array2[k] / array3[k];
                super.Woa[2][k] = 100.0 * array[k] / array3[k];
                if (super.Woa[1][k] + super.Woa[2][k] != 0.0) {
                    super.Woa[0][k] = 100.0 * Math.abs(super.Woa[2][k] - super.Woa[1][k]) / (super.Woa[1][k] + super.Woa[2][k]);
                }
            }
            else {
                super.Woa[1][k] = 0.0;
                super.Woa[2][k] = 0.0;
                super.Woa[0][k] = 0.0;
            }
        }
        e.calculateExpAvg(super.Woa[0], super.Woa[0], 1, super.Oa[1] * 2 - 1);
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        e.a(super.Woa[1], super.Uoa[1], 0.0);
        e.a(super.Woa[2], super.Uoa[2], 0.0);
        super.Yoa = new byte[h.length];
        final Enumeration<Integer> elements = e.a(super.Woa[2], super.Woa[1], super.Uoa[2]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = e.b(super.Woa[2], super.Woa[1], super.Uoa[2]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = -1;
        }
    }
}
