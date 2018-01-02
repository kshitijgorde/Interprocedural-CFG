import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Pi extends implements
{
    public Pi(final int[] array, final class class1) {
        super("TRIX - TRIX Index", 2, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = Math.max((super.Ua[0] - 1) * 3, 1);
        super.r[1] = super.r[0] + super.Ua[1] - 1;
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
        do.calculateExpAvg(array, array, 0, super.Ua[0]);
        do.calculateExpAvg(array, array, 0, super.Ua[0]);
        for (int i = super.r[0]; i < super.t[0].length; ++i) {
            if (array[i - 1] != 0.0) {
                super.t[0][i] = (array[i] - array[i - 1]) / array[i - 1];
            }
            else {
                super.t[0][i] = super.t[0][i];
            }
        }
        do._(super.t[0], super.r[0], 0.0);
        do.calculateExpAvg(super.t[0], super.t[1], super.r[0], super.Ua[1]);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = do._(super.t[0], super.t[1], super.r[1]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = do.a(super.t[0], super.t[1], super.r[1]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = -1;
        }
    }
}
