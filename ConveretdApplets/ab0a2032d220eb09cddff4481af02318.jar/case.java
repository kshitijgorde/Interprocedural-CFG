import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class case extends o
{
    public case(final int[] array, final a a) {
        super("CMO - Chande Momentum Oscillator", 2, array, new double[] { -50.0, 50.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
        super.qIb[1] = super.DDb[0] + super.DDb[1] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            super.uIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        array[0] = 0.0;
        for (int i = 1; i < l.length; ++i) {
            if (l[i] > l[i - 1]) {
                array[i] = l[i] - l[i - 1];
            }
            else {
                array[i] = 0.0;
            }
        }
        j._(array, array, 1, super.DDb[0]);
        final double[] array2 = new double[l.length];
        array2[0] = 0.0;
        for (int j = 1; j < l.length; ++j) {
            if (l[j] < l[j - 1]) {
                array2[j] = l[j - 1] - l[j];
            }
            else {
                array2[j] = 0.0;
            }
        }
        j._(array2, array2, 1, super.DDb[0]);
        for (int k = 0; k < l.length; ++k) {
            if (array[k] + array2[k] == 0.0) {
                super.sIb[0][k] = 100.0;
            }
            else {
                super.sIb[0][k] = 100.0 * (array[k] - array2[k]) / (array[k] + array2[k]);
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        j.calculateExpAvg(super.sIb[0], super.sIb[1], super.qIb[0], super.DDb[1]);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = j.a(super.sIb[0], super.sIb[1], super.qIb[1]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = j.b(super.sIb[0], super.sIb[1], super.qIb[1]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
