import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class tda extends public
{
    public tda(final int[] array, final b b) {
        super("EMV - Ease of Movement", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
    }
    
    protected void H() {
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        final double[] i = super.Voa.i();
        if (_ == null || g == null || i == null) {
            super.Woa = null;
            super.Yoa = null;
            return;
        }
        super.Woa = new double[super.Xoa][_.length];
        final double[] array = new double[_.length];
        array[0] = 0.0;
        for (int j = 1; j < _.length; ++j) {
            array[j] = (_[j] + g[j]) / 2.0 - (_[j - 1] + g[j - 1]) / 2.0;
        }
        final double[] array2 = new double[_.length];
        for (int k = 0; k < _.length; ++k) {
            if (_[k] - g[k] != 0.0) {
                array2[k] = i[k] / 10000.0 / (_[k] - g[k]);
            }
            else {
                array2[k] = 0.0;
            }
        }
        for (int l = 0; l < super.Woa[0].length; ++l) {
            if (array2[l] != 0.0) {
                super.Woa[0][l] = array[l] / array2[l];
            }
            else {
                super.Woa[0][l] = 0.0;
            }
        }
        e.calculateSimpleAvg(super.Woa[0], super.Woa[0], 1, super.Oa[0]);
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        super.Yoa = new byte[_.length];
        final Enumeration<Integer> elements = e.b(super.Woa[0], 0.0, super.Uoa[0]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = e._(super.Woa[0], 0.0, super.Uoa[0]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = -1;
        }
    }
}
