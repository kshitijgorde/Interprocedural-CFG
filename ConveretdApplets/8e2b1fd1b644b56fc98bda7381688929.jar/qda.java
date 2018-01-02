import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class qda extends public
{
    public qda(final int[] array, final b b) {
        super("CMO - Chande Momentum Oscillator", 2, array, new double[] { -50.0, 50.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
        super.Uoa[1] = super.Oa[0] + super.Oa[1] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            super.Yoa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        array[0] = 0.0;
        for (int i = 1; i < h.length; ++i) {
            if (h[i] > h[i - 1]) {
                array[i] = h[i] - h[i - 1];
            }
            else {
                array[i] = 0.0;
            }
        }
        e._(array, array, 1, super.Oa[0]);
        final double[] array2 = new double[h.length];
        array2[0] = 0.0;
        for (int j = 1; j < h.length; ++j) {
            if (h[j] < h[j - 1]) {
                array2[j] = h[j - 1] - h[j];
            }
            else {
                array2[j] = 0.0;
            }
        }
        e._(array2, array2, 1, super.Oa[0]);
        for (int k = 0; k < h.length; ++k) {
            if (array[k] + array2[k] == 0.0) {
                super.Woa[0][k] = 100.0;
            }
            else {
                super.Woa[0][k] = 100.0 * (array[k] - array2[k]) / (array[k] + array2[k]);
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        e.calculateExpAvg(super.Woa[0], super.Woa[1], super.Uoa[0], super.Oa[1]);
        super.Yoa = new byte[h.length];
        final Enumeration<Integer> elements = e.a(super.Woa[0], super.Woa[1], super.Uoa[1]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = e.b(super.Woa[0], super.Woa[1], super.Uoa[1]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = -1;
        }
    }
}
