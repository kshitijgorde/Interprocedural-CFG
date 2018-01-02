import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Nda extends public
{
    public Nda(final int[] array, final b b) {
        super("RSI - Relative Strength Index", 1, array, new double[] { array[1], array[2] }, b);
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
            array[i] = h[i] - h[i - 1];
        }
        double n2;
        double n = n2 = 0.0;
        for (int n3 = 0; n3 < super.Oa[0] && n3 < array.length; ++n3) {
            if (array[n3] >= 0.0) {
                n2 += array[n3];
            }
            else {
                n -= array[n3];
            }
        }
        double n4 = n2 / super.Oa[0];
        double n5 = n / super.Oa[0];
        for (int j = super.Oa[0]; j < array.length; ++j) {
            double n7;
            double n6 = n7 = 0.0;
            if (array[j] >= 0.0) {
                n7 = array[j];
            }
            else {
                n6 = -array[j];
            }
            n4 = (n4 * (super.Oa[0] - 1) + n7) / super.Oa[0];
            n5 = (n5 * (super.Oa[0] - 1) + n6) / super.Oa[0];
            if (n4 + n5 != 0.0) {
                super.Woa[0][j] = 100.0 * n4 / (n4 + n5);
            }
            else {
                super.Woa[0][j] = 100.0;
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
