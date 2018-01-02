import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Hi extends implements
{
    public Hi(final int[] array, final class class1) {
        super("QStick - QStick Indicator", 2, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1;
        super.r[1] = super.Ua[0] + super.Ua[1] - 2;
    }
    
    protected void X() {
        final double[] g = super.s.g();
        final double[] _ = super.s._();
        if (g == null || _ == null) {
            super.t = null;
            super.z = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        for (int i = 0; i < _.length; ++i) {
            array[i] = _[i] - g[i];
        }
        do.calculateSimpleAvg(array, super.t[0], 0, super.Ua[0]);
        do._(super.t[0], super.r[0], 0.0);
        do.calculateExpAvg(super.t[0], super.t[1], super.Ua[0] - 1, super.Ua[1]);
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
