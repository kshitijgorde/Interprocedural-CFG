import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class instanceof extends o
{
    public instanceof(final int[] array, final a a) {
        super("%R - Williams' Percent Range", 1, array, new double[] { array[1], array[2] }, a);
        this.C();
    }
    
    public void j(final int n, final int n2) {
        if (super.vIb != null && n > 0 && n - 1 < super.vIb.length) {
            super.vIb[n - 1] = n2;
        }
        super.j(n, n2);
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        if (l == null || j == null || k == null) {
            super.sIb = null;
            super.uIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[j.length];
        final double[] array2 = new double[k.length];
        j.calculateMin(k, array2, 0, super.DDb[0]);
        j.calculateMax(j, array, 0, super.DDb[0]);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] - array2[i] != 0.0) {
                super.sIb[0][i] = 100.0 * (l[i] - array2[i]) / (array[i] - array2[i]);
            }
            else {
                super.sIb[0][i] = 100.0;
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
