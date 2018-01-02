import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class zda extends public
{
    public zda(final int[] array, final b b) {
        super("MACDO - MACD Oscillator", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = Math.max(super.Oa[0], super.Oa[1]) - 1 + super.Oa[2] - 1;
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
        final double[] array2 = new double[h.length];
        e.calculateExpAvg(h, array2, 0, super.Oa[1]);
        final double[] array3 = new double[h.length];
        for (int i = 0; i < h.length; ++i) {
            array3[i] = array[i] - array2[i];
        }
        e.calculateExpAvg(array3, super.Woa[0], Math.max(super.Oa[0], super.Oa[1]) - 1, super.Oa[2]);
        for (int j = 0; j < super.Woa[0].length; ++j) {
            super.Woa[0][j] = array3[j] - super.Woa[0][j];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        super.Yoa = new byte[h.length];
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
