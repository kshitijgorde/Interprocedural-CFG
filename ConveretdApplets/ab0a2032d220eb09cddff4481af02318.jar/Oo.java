// 
// Decompiled by Procyon v0.5.30
// 

public class Oo extends o
{
    public Oo(final String s, final int[] array, final a a) {
        super(s, 2, array, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1;
        super.qIb[1] = super.DDb[0] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        final double[] array2 = new double[l.length];
        j.calculateSimpleAvg(l, array, 0, super.DDb[0]);
        j.calculateStdDev(l, array2, 0, super.DDb[0]);
        for (int i = super.qIb[0]; i < super.sIb[0].length; ++i) {
            super.sIb[0][i] = array[i] - array2[i] * super.DDb[1];
            super.sIb[1][i] = array[i] + array2[i] * super.DDb[1];
            if (super.sIb[0][i] < 0.0) {
                super.sIb[0][i] = 0.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], l[0]);
        j.a(super.sIb[1], super.qIb[0], l[0]);
    }
}
