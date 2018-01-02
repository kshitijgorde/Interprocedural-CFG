import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class protected extends o
{
    public protected(final int[] array, final a a) {
        super("RSI - Relative Strength Index", 1, array, new double[] { array[1], array[2] }, a);
        this.C();
    }
    
    public void j(final int n, final int n2) {
        if (super.vIb != null && n > 0 && n - 1 < super.vIb.length) {
            super.vIb[n - 1] = n2;
        }
        super.j(n, n2);
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
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
            array[i] = l[i] - l[i - 1];
        }
        double n2;
        double n = n2 = 0.0;
        for (int n3 = 0; n3 < super.DDb[0] && n3 < array.length; ++n3) {
            if (array[n3] >= 0.0) {
                n2 += array[n3];
            }
            else {
                n -= array[n3];
            }
        }
        double n4 = n2 / super.DDb[0];
        double n5 = n / super.DDb[0];
        for (int j = super.DDb[0]; j < array.length; ++j) {
            double n7;
            double n6 = n7 = 0.0;
            if (array[j] >= 0.0) {
                n7 = array[j];
            }
            else {
                n6 = -array[j];
            }
            n4 = (n4 * (super.DDb[0] - 1) + n7) / super.DDb[0];
            n5 = (n5 * (super.DDb[0] - 1) + n6) / super.DDb[0];
            if (n4 + n5 != 0.0) {
                super.sIb[0][j] = 100.0 * n4 / (n4 + n5);
            }
            else {
                super.sIb[0][j] = 100.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = j._(super.sIb[0], super.DDb[1], super.qIb[0]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = j.a(super.sIb[0], (double)super.DDb[2], super.qIb[0]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
