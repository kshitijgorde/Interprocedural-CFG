import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class interface extends o
{
    public interface(final int[] array, final a a) {
        super("POS - Price Oscillator", 2, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = Math.max(super.DDb[0], super.DDb[1]) - 1;
        super.qIb[1] = Math.max(super.DDb[0], super.DDb[1]) - 1 + super.DDb[2] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            super.uIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        j.calculateSimpleAvg(l, super.sIb[0], 0, super.DDb[0]);
        j.calculateSimpleAvg(l, super.sIb[1], 0, super.DDb[1]);
        for (int i = 0; i < l.length; ++i) {
            final double[] array = super.sIb[0];
            final int n = i;
            array[n] -= super.sIb[1][i];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        j.calculateExpAvg(super.sIb[0], super.sIb[1], super.qIb[0], super.DDb[2]);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)j._(super.sIb[0], 0.0, super.qIb[1]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)j.a(super.sIb[0], 0.0, super.qIb[1]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
