import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lda extends public
{
    public Lda(final int[] array, final b b) {
        super("QStick - QStick Indicator", 2, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1;
        super.Uoa[1] = super.Oa[0] + super.Oa[1] - 2;
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
        for (int i = 0; i < h.length; ++i) {
            array[i] = h[i] - b[i];
        }
        e.calculateSimpleAvg(array, super.Woa[0], 0, super.Oa[0]);
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        e.calculateExpAvg(super.Woa[0], super.Woa[1], super.Oa[0] - 1, super.Oa[1]);
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
