import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class implements extends o
{
    public implements(final int[] array, final a a) {
        super("NVI - Negative Volume Index", 3, array, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = 0;
        super.qIb[1] = super.DDb[0] - 1;
        super.qIb[2] = Math.max(super.DDb[0], super.DDb[1]) - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        final double[] m = super.rIb.m();
        if (l == null) {
            super.sIb = null;
            super.uIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        super.sIb[0][0] = 100.0;
        for (int i = 1; i < super.sIb[0].length; ++i) {
            if (m[i] <= m[i - 1] && l[i - 1] > 0.0) {
                super.sIb[0][i] = l[i] / l[i - 1] - 1.0;
            }
            else {
                super.sIb[0][i] = 0.0;
            }
        }
        for (int j = 1; j < super.sIb[0].length; ++j) {
            final double[] array = super.sIb[0];
            final int n = j;
            array[n] += super.sIb[0][j - 1];
        }
        j.calculateExpAvg(super.sIb[0], super.sIb[1], 0, super.DDb[0]);
        j.calculateExpAvg(super.sIb[0], super.sIb[2], 0, super.DDb[1]);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)j.a(super.sIb[1], super.sIb[2], super.qIb[2]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)j.b(super.sIb[1], super.sIb[2], super.qIb[2]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
