// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bi extends ao
{
    protected static int fi;
    protected static int fk;
    protected static int fm;
    protected static int fj;
    protected static int fl;
    protected static int fo;
    private char[] fn;
    
    static {
        bi.fi = 0;
        bi.fk = 1;
        bi.fm = 2;
        bi.fj = 3;
        bi.fl = 4;
        bi.fo = 5;
    }
    
    public bi() {
        this.fn = new char[] { 'p', 'a', 'n', 'o', 'C', 'u', 'b', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.fn = null;
    }
    
    public void a(final ac b, final float cz, final ae long1, final f cb, final m ca) {
        super.cA = ca;
        super.goto = super.cA.h;
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / super.cz;
        super.long = long1;
        super.cB = cb;
        super.int = 1;
        super.case = this.fn;
        super.et = 6;
    }
    
    protected void t() {
        super.d8 = new aq[6][];
        super.ee = new aq[6][];
        super.ei = (int)(17.0f * super.b.ap / 100.0f);
        if (super.ei / 2 * 2 == super.ei) {
            ++super.ei;
        }
        super.eg = super.ei;
        for (int i = 0; i < 6; ++i) {
            super.d8[i] = new aq[super.ei * super.eg];
            super.ee[i] = new aq[super.ei * super.eg];
            for (int j = 0; j < super.ei * super.eg; ++j) {
                super.d8[i][j] = new aq();
                super.ee[i][j] = new aq();
            }
        }
        this.a(super.d8);
        this.a(super.ee);
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
        final int n11 = super.ei * super.ei;
        final int n12 = super.ei / 2;
        final float n13 = (super.dW.j - 1) / (super.ei - 1);
        final float n14 = (super.dW.u / 6 - 1) / (super.ei - 1);
        n = n * super.dW.u / 6;
        for (int i = 0; i < super.eg; ++i) {
            for (int j = 0; j < super.ei; ++j) {
                final int n15 = j + i * super.ei;
                array[n15].try = n2 * n12 + n5 * (j - n12) + n6 * (i - n12);
                array[n15].if = n3 * n12 + n7 * (j - n12) + n8 * (i - n12);
                array[n15].byte = n4 * n12 + n9 * (j - n12) + n10 * (i - n12);
                array[n15].do = j * n13;
                array[n15].a = n + 0.5f + i * n14;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return super.dW != null && (!super.dW.v || this.do(this.if(n, n2)) < 0L);
    }
    
    protected long do(final aq aq) {
        final int if1 = this.if(aq);
        float n = (float)Math.atan2(aq.if, aq.try);
        final float n2 = (float)Math.atan(aq.byte / Math.sqrt(Math.pow(aq.try, 2.0) + Math.pow(aq.if, 2.0)));
        final long n3 = super.dW.j / 2;
        final long n4 = super.dW.u / 12;
        int n5 = 0;
        int n6 = 0;
        if (if1 == bi.fl || if1 == bi.fo) {
            final float n7 = (float)Math.tan(1.5707963267948966 - Math.abs(n2)) * n4;
            n5 = (int)(n3 - Math.sin(n) * n7);
            if (if1 == bi.fl) {
                n6 = (int)(Math.cos(n) * n7 + n4);
            }
            else if (if1 == bi.fo) {
                n6 = (int)(n4 - Math.cos(n) * n7);
            }
        }
        else if (if1 == bi.fi || if1 == bi.fm || if1 == bi.fj || if1 == bi.fk) {
            if (if1 == bi.fj || if1 == bi.fk) {
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
        return super.dW.a(n5, n6 + if1 * super.dW.u / 6);
    }
    
    private int if(final aq aq) {
        final float try1 = aq.try;
        final float if1 = aq.if;
        final float byte1 = aq.byte;
        if (Math.abs(try1) > Math.abs(if1)) {
            if (Math.abs(try1) > Math.abs(byte1)) {
                if (try1 > 0.0f) {
                    return bi.fi;
                }
                return bi.fm;
            }
            else {
                if (byte1 > 0.0f) {
                    return bi.fl;
                }
                return bi.fo;
            }
        }
        else if (Math.abs(if1) > Math.abs(byte1)) {
            if (if1 > 0.0f) {
                return bi.fj;
            }
            return bi.fk;
        }
        else {
            if (byte1 > 0.0f) {
                return bi.fl;
            }
            return bi.fo;
        }
    }
}
