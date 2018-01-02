import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class final extends o
{
    public final(final int[] array, final a a) {
        super("MACDO - MACD Oscillator", 1, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = Math.max(super.DDb[0], super.DDb[1]) - 1 + super.DDb[2] - 1;
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
        final double[] array2 = new double[l.length];
        j.calculateExpAvg(l, array2, 0, super.DDb[1]);
        final double[] array3 = new double[l.length];
        for (int i = 0; i < l.length; ++i) {
            array3[i] = array[i] - array2[i];
        }
        j.calculateExpAvg(array3, super.sIb[0], Math.max(super.DDb[0], super.DDb[1]) - 1, super.DDb[2]);
        for (int j = 0; j < super.sIb[0].length; ++j) {
            super.sIb[0][j] = array3[j] - super.sIb[0][j];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = j._(super.sIb[0], 0.0, super.qIb[0]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = j.a(super.sIb[0], 0.0, super.qIb[0]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
