// 
// Decompiled by Procyon v0.5.30
// 

public class continue extends o
{
    public continue(final int[] array, final a a) {
        super("FastK - %K Fast", 1, array, new double[] { array[1], array[2] }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1;
    }
    
    public void j(final int n, final int n2) {
        if (super.vIb != null && n > 0 && n - 1 < super.vIb.length) {
            super.vIb[n - 1] = n2;
        }
        super.j(n, n2);
    }
    
    protected void D() {
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        final double[] l = super.rIb.l();
        if (j == null || k == null || l == null) {
            super.sIb = null;
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
    }
}
