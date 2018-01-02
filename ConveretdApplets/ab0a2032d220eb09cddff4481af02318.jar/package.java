import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class package extends o
{
    public package(final int[] array, final a a) {
        super("QStick - QStick Indicator", 2, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1;
        super.qIb[1] = super.DDb[0] + super.DDb[1] - 2;
    }
    
    protected void D() {
        final double[] i = super.rIb.i();
        final double[] l = super.rIb.l();
        if (i == null || l == null) {
            super.sIb = null;
            super.uIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        for (int j = 0; j < l.length; ++j) {
            array[j] = l[j] - i[j];
        }
        j.calculateSimpleAvg(array, super.sIb[0], 0, super.DDb[0]);
        j.a(super.sIb[0], super.qIb[0], 0.0);
        j.calculateExpAvg(super.sIb[0], super.sIb[1], super.DDb[0] - 1, super.DDb[1]);
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
