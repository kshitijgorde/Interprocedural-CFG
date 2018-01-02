import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class synchronized extends o
{
    public synchronized(final int[] array, final a a) {
        super("TRIX - TRIX Index", 2, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = Math.max((super.DDb[0] - 1) * 3, 1);
        super.qIb[1] = super.qIb[0] + super.DDb[1] - 1;
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
        j.calculateExpAvg(l, array, 0, super.DDb[0]);
        j.calculateExpAvg(array, array, 0, super.DDb[0]);
        j.calculateExpAvg(array, array, 0, super.DDb[0]);
        for (int i = super.qIb[0]; i < super.sIb[0].length; ++i) {
            if (array[i - 1] != 0.0) {
                super.sIb[0][i] = (array[i] - array[i - 1]) / array[i - 1];
            }
            else {
                super.sIb[0][i] = super.sIb[0][i];
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
