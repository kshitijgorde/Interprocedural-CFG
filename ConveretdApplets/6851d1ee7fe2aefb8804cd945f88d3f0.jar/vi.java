import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class vi extends implements
{
    public vi(final int[] array, final class class1) {
        super("MACDO - MACD Oscillator", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = Math.max(super.Ua[0], super.Ua[1]) - 1 + super.Ua[2] - 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            super.z = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        do.calculateExpAvg(_, array, 0, super.Ua[0]);
        final double[] array2 = new double[_.length];
        do.calculateExpAvg(_, array2, 0, super.Ua[1]);
        final double[] array3 = new double[_.length];
        for (int i = 0; i < _.length; ++i) {
            array3[i] = array[i] - array2[i];
        }
        do.calculateExpAvg(array3, super.t[0], Math.max(super.Ua[0], super.Ua[1]) - 1, super.Ua[2]);
        for (int j = 0; j < super.t[0].length; ++j) {
            super.t[0][j] = array3[j] - super.t[0][j];
        }
        do._(super.t[0], super.r[0], 0.0);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = do._(super.t[0], 0.0, super.r[0]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = do.a(super.t[0], 0.0, super.r[0]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = -1;
        }
    }
}
