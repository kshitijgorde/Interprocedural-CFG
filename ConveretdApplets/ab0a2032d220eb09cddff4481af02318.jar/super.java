import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class super extends o
{
    public super(final int[] array, final a a) {
        super("STS - Stochastic", 2, array, new double[] { 20.0, 80.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = Math.max(super.DDb[0] - 1, super.DDb[1] - 1);
        super.qIb[1] = super.qIb[0] + super.DDb[2] - 1;
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
        j.calculateSimpleAvg(super.sIb[0], super.sIb[0], super.qIb[0], super.DDb[1]);
        j.calculateExpAvg(super.sIb[0], super.sIb[1], super.qIb[0], super.DDb[2]);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = j._(super.sIb[0], 20.0, super.qIb[0]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = j.a(super.sIb[0], 80.0, super.qIb[0]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
