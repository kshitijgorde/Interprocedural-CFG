// 
// Decompiled by Procyon v0.5.30
// 

public class Mo extends o
{
    public Mo(final int[] array, final a a) {
        super("WAD2 - Williams Acc. Dist. (type 2)", 1, null, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = 1;
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
        final double[] array = new double[l.length];
        array[0] = j[0];
        for (int i = 1; i < array.length; ++i) {
            array[i] = Math.max(j[i], l[i - 1]);
        }
        final double[] array2 = new double[l.length];
        array2[0] = k[0];
        for (int n = 1; n < array2.length; ++n) {
            array2[n] = Math.min(k[n], l[n - 1]);
        }
        final double[] array3 = new double[l.length];
        array3[0] = 0.0;
        for (int n2 = 1; n2 < array.length; ++n2) {
            if (l[n2] > l[n2 - 1]) {
                array3[n2] = l[n2] - array2[n2];
            }
            else if (l[n2] < l[n2 - 1]) {
                array3[n2] = l[n2] - array[n2];
            }
            else {
                array3[n2] = 0.0;
            }
        }
        super.sIb[0][0] = 0.0;
        for (int n3 = 1; n3 < super.sIb[0].length; ++n3) {
            super.sIb[0][n3] = array3[n3] + super.sIb[0][n3 - 1];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
