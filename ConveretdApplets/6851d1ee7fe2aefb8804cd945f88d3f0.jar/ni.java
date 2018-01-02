import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class ni extends implements
{
    public ni(final int[] array, final class class1) {
        super("CMO - Chande Momentum Oscillator", 2, array, new double[] { -50.0, 50.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
        super.r[1] = super.Ua[0] + super.Ua[1] - 1;
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
        array[0] = 0.0;
        for (int i = 1; i < _.length; ++i) {
            if (_[i] > _[i - 1]) {
                array[i] = _[i] - _[i - 1];
            }
            else {
                array[i] = 0.0;
            }
        }
        do.a(array, array, 1, super.Ua[0]);
        final double[] array2 = new double[_.length];
        array2[0] = 0.0;
        for (int j = 1; j < _.length; ++j) {
            if (_[j] < _[j - 1]) {
                array2[j] = _[j - 1] - _[j];
            }
            else {
                array2[j] = 0.0;
            }
        }
        do.a(array2, array2, 1, super.Ua[0]);
        for (int k = 0; k < _.length; ++k) {
            if (array[k] + array2[k] == 0.0) {
                super.t[0][k] = 100.0;
            }
            else {
                super.t[0][k] = 100.0 * (array[k] - array2[k]) / (array[k] + array2[k]);
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
