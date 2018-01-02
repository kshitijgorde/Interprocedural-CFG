import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Hda extends public
{
    public Hda(final int[] array, final b b) {
        super("POS - Price Oscillator", 2, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = Math.max(super.Oa[0], super.Oa[1]) - 1;
        super.Uoa[1] = Math.max(super.Oa[0], super.Oa[1]) - 1 + super.Oa[2] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            super.Yoa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        e.calculateSimpleAvg(h, super.Woa[0], 0, super.Oa[0]);
        e.calculateSimpleAvg(h, super.Woa[1], 0, super.Oa[1]);
        for (int i = 0; i < h.length; ++i) {
            final double[] array = super.Woa[0];
            final int n = i;
            array[n] -= super.Woa[1][i];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        e.calculateExpAvg(super.Woa[0], super.Woa[1], super.Uoa[0], super.Oa[2]);
        super.Yoa = new byte[h.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)e.b(super.Woa[0], 0.0, super.Uoa[1]).elements();
        while (elements.hasMoreElements()) {
            super.Yoa[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)e._(super.Woa[0], 0.0, super.Uoa[1]).elements();
        while (elements2.hasMoreElements()) {
            super.Yoa[elements2.nextElement()] = -1;
        }
    }
}
