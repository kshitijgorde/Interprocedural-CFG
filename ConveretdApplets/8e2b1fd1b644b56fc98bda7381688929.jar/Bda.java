import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bda extends public
{
    public Bda(final int[] array, final b b) {
        super("MFI - Money Flow Index", 1, array, new double[] { array[1], array[2] }, b);
        this.G();
    }
    
    public void a(final int n, final int n2) {
        if (super.Zoa != null && n > 0 && n - 1 < super.Zoa.length) {
            super.Zoa[n - 1] = n2;
        }
        super.a(n, n2);
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
    }
    
    protected void H() {
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        final double[] h = super.Voa.h();
        final double[] i = super.Voa.i();
        if (_ == null || g == null || h == null || i == null) {
            super.Woa = null;
            super.Yoa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        for (int j = 0; j < array.length; ++j) {
            array[j] = (_[j] + g[j] + h[j]) / 3.0;
        }
        final double[] array2 = new double[h.length];
        for (int k = 0; k < array.length; ++k) {
            array2[k] = array[k] * i[k];
        }
        for (int l = super.Oa[0]; l < super.Woa[0].length; ++l) {
            double n2;
            double n = n2 = 0.0;
            for (int n3 = l - (super.Oa[0] - 1); n3 <= l; ++n3) {
                if (array[n3] > array[n3 - 1]) {
                    n2 += array2[n3];
                }
                else if (array[n3] < array[n3 - 1]) {
                    n += array2[n3];
                }
            }
            double n4;
            if (n != 0.0) {
                n4 = n2 / n;
            }
            else {
                n4 = 0.0;
            }
            if (1.0 + n4 != 0.0) {
                super.Woa[0][l] = 100.0 - 100.0 / (1.0 + n4);
            }
            else {
                super.Woa[0][l] = 0.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        super.Yoa = new byte[h.length];
        final Enumeration<Integer> elements = e.b(super.Woa[0], super.Oa[1], super.Uoa[0]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = e._(super.Woa[0], super.Oa[2], super.Uoa[0]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = -1;
        }
    }
}
