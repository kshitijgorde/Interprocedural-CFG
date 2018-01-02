import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ida extends public
{
    public Ida(final int[] array, final b b) {
        super("PVI - Positive Volume Index", 3, array, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = 0;
        super.Uoa[1] = super.Oa[0] - 1;
        super.Uoa[2] = super.Oa[0] + super.Oa[1] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        final double[] i = super.Voa.i();
        if (h == null) {
            super.Woa = null;
            super.Yoa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        super.Woa[0][0] = 100.0;
        for (int j = 1; j < super.Woa[0].length; ++j) {
            if (i[j] > i[j - 1] && h[j - 1] > 0.0) {
                super.Woa[0][j] = h[j] / h[j - 1] - 1.0;
            }
            else {
                super.Woa[0][j] = 0.0;
            }
        }
        for (int k = 1; k < super.Woa[0].length; ++k) {
            final double[] array = super.Woa[0];
            final int n = k;
            array[n] += super.Woa[0][k - 1];
        }
        e.calculateExpAvg(super.Woa[0], super.Woa[1], 0, super.Oa[0]);
        e.calculateExpAvg(super.Woa[0], super.Woa[2], 0, super.Oa[1]);
        super.Yoa = new byte[h.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)e.a(super.Woa[1], super.Woa[2], super.Uoa[2]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)e.b(super.Woa[1], super.Woa[2], super.Uoa[2]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = -1;
        }
    }
}
