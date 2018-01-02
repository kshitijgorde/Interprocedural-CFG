// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bi extends ao
{
    protected static int fj;
    protected static int fl;
    protected static int fn;
    protected static int fk;
    protected static int fm;
    protected static int fp;
    private char[] fo;
    
    static {
        bi.fj = 0;
        bi.fl = 1;
        bi.fn = 2;
        bi.fk = 3;
        bi.fm = 4;
        bi.fp = 5;
    }
    
    public bi() {
        this.fo = new char[] { 'p', 'a', 'n', 'o', 'C', 'u', 'b', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.fo = null;
    }
    
    public void a(final ac b, final float ca, final ae long1, final f cc, final m cb) {
        super.cB = cb;
        super.goto = super.cB.h;
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.int = 1;
        super.case = this.fo;
        super.eu = 6;
    }
    
    protected void r() {
        super.d9 = new aq[6][];
        super.ef = new aq[6][];
        super.ej = (int)(17.0f * super.b.ar / 100.0f);
        if (super.ej / 2 * 2 == super.ej) {
            ++super.ej;
        }
        super.eh = super.ej;
        for (int i = 0; i < 6; ++i) {
            super.d9[i] = new aq[super.ej * super.eh];
            super.ef[i] = new aq[super.ej * super.eh];
            for (int j = 0; j < super.ej * super.eh; ++j) {
                super.d9[i][j] = new aq();
                super.ef[i][j] = new aq();
            }
        }
        this.a(super.d9);
        this.a(super.ef);
    }
    
    private void a(final aq[][] array) {
        this.a(5, array[0], 0, 0, -1, 0, -1, -1, 0, 0, 0);
        this.a(3, array[1], 0, 1, 0, 1, 0, 0, 0, 0, -1);
        this.a(1, array[2], 0, -1, 0, -1, 0, 0, 0, 0, -1);
        this.a(2, array[3], -1, 0, 0, 0, 0, 1, 0, 0, -1);
        this.a(4, array[4], 0, 0, 1, 0, 1, -1, 0, 0, 0);
        this.a(0, array[5], 1, 0, 0, 0, 0, -1, 0, 0, -1);
    }
    
    private void a(int n, final aq[] array, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        final int n11 = super.ej * super.ej;
        final int n12 = super.ej / 2;
        final float n13 = super.dX.s / (super.ej - 1);
        final float n14 = (super.dX.r / 6 - 1) / (super.ej - 1);
        n = n * super.dX.r / 6;
        for (int i = 0; i < super.eh; ++i) {
            for (int j = 0; j < super.ej; ++j) {
                final int n15 = j + i * super.ej;
                array[n15].try = n2 * n12 + n5 * (j - n12) + n6 * (i - n12);
                array[n15].if = n3 * n12 + n7 * (j - n12) + n8 * (i - n12);
                array[n15].byte = n4 * n12 + n9 * (j - n12) + n10 * (i - n12);
                array[n15].do = j * n13;
                array[n15].a = n + i * n14 + 0.5f;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return super.dX != null && (!super.dX.v || this.do(this.if(n, n2)) < 0L);
    }
    
    protected long do(final aq aq) {
        final int if1 = this.if(aq);
        float n = (float)Math.atan2(aq.if, aq.try);
        final float n2 = (float)Math.atan(aq.byte / Math.sqrt(Math.pow(aq.try, 2.0) + Math.pow(aq.if, 2.0)));
        final long n3 = super.dX.s / 2;
        final long n4 = super.dX.r / 12;
        int n5 = 0;
        int n6 = 0;
        if (if1 == bi.fm || if1 == bi.fp) {
            final float n7 = (float)Math.tan(1.5707963267948966 - Math.abs(n2)) * n4;
            n5 = (int)(n3 - Math.sin(n) * n7);
            if (if1 == bi.fm) {
                n6 = (int)(Math.cos(n) * n7 + n4);
            }
            else if (if1 == bi.fp) {
                n6 = (int)(n4 - Math.cos(n) * n7);
            }
        }
        else if (if1 == bi.fj || if1 == bi.fn || if1 == bi.fk || if1 == bi.fl) {
            if (if1 == bi.fk || if1 == bi.fl) {
                if (n < 0.0f) {
                    n += 1.5707963267948966;
                }
                else {
                    n -= 1.5707963267948966;
                }
            }
            if (n < 0.0f) {
                n5 = (int)(Math.tan(Math.abs(n)) * n3 + n3);
            }
            else {
                n5 = (int)(n3 - Math.tan(n) * n3);
            }
            n6 = (int)(-n4 * (Math.sqrt(1.0 + Math.tan(n) * Math.tan(n)) * Math.tan(n2)) + n4);
        }
        return super.dX.a(n5, n6 + if1 * super.dX.r / 6);
    }
    
    private int if(final aq aq) {
        final float try1 = aq.try;
        final float if1 = aq.if;
        final float byte1 = aq.byte;
        if (Math.abs(try1) > Math.abs(if1)) {
            if (Math.abs(try1) > Math.abs(byte1)) {
                if (try1 > 0.0f) {
                    return bi.fj;
                }
                return bi.fn;
            }
            else {
                if (byte1 > 0.0f) {
                    return bi.fm;
                }
                return bi.fp;
            }
        }
        else if (Math.abs(if1) > Math.abs(byte1)) {
            if (if1 > 0.0f) {
                return bi.fk;
            }
            return bi.fl;
        }
        else {
            if (byte1 > 0.0f) {
                return bi.fm;
            }
            return bi.fp;
        }
    }
}
