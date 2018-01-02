import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class xda extends public
{
    public xda(final int[] array, final b b) {
        super("IMI - Intraday Momentum Index", 1, array, new double[] { array[1], array[2] }, b);
        this.G();
    }
    
    public void a(final int n, final int n2) {
        if (super.Zoa != null && n > 0 && n - 1 < super.Zoa.length) {
            super.Zoa[n - 1] = n2;
        }
        super.a(n, n2);
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1;
    }
    
    protected void H() {
        final double[] b = super.Voa.b();
        final double[] h = super.Voa.h();
        if (b == null || h == null) {
            super.Woa = null;
            super.Yoa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        final double[] array2 = new double[h.length];
        for (int i = 0; i < h.length; ++i) {
            if (h[i] > b[i]) {
                array[i] = h[i] - b[i];
            }
            else {
                array[i] = 0.0;
            }
            if (h[i] < b[i]) {
                array2[i] = b[i] - h[i];
            }
            else {
                array2[i] = 0.0;
            }
        }
        e.calculateSimpleAvg(array, array, 0, super.Oa[0]);
        e.calculateSimpleAvg(array2, array2, 0, super.Oa[0]);
        for (int j = 0; j < h.length; ++j) {
            if (array[j] + array2[j] != 0.0) {
                super.Woa[0][j] = 100.0 * array[j] / (array[j] + array2[j]);
            }
            else {
                super.Woa[0][j] = 0.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        super.Yoa = new byte[h.length];
        final Enumeration<Integer> elements = e.b(super.Woa[0], super.Oa[2], super.Uoa[0]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = -1;
        }
        final Enumeration<Integer> elements2 = e._(super.Woa[0], super.Oa[1], super.Uoa[0]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = 1;
        }
    }
}
