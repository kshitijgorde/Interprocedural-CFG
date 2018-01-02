import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tda extends public
{
    public Tda(final int[] array, final b b) {
        super("TRIX - TRIX Index", 2, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = Math.max((super.Oa[0] - 1) * 3, 1);
        super.Uoa[1] = super.Uoa[0] + super.Oa[1] - 1;
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
        e.calculateExpAvg(h, array, 0, super.Oa[0]);
        e.calculateExpAvg(array, array, 0, super.Oa[0]);
        e.calculateExpAvg(array, array, 0, super.Oa[0]);
        for (int i = super.Uoa[0]; i < super.Woa[0].length; ++i) {
            if (array[i - 1] != 0.0) {
                super.Woa[0][i] = (array[i] - array[i - 1]) / array[i - 1];
            }
            else {
                super.Woa[0][i] = super.Woa[0][i];
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
