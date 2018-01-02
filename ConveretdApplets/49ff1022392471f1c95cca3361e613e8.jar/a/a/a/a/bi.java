// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bi extends ao
{
    protected static int e2;
    protected static int e4;
    protected static int e6;
    protected static int e3;
    protected static int e5;
    protected static int e8;
    private char[] e7;
    
    static {
        bi.e2 = 0;
        bi.e4 = 1;
        bi.e6 = 2;
        bi.e3 = 3;
        bi.e5 = 4;
        bi.e8 = 5;
    }
    
    public bi() {
        this.e7 = new char[] { 'p', 'a', 'n', 'o', 'C', 'u', 'b', 'e', '\0' };
    }
    
    public void a(final ac void1, final float cj, final ae goto1, final f cl, final m ck) {
        super.ck = ck;
        super.else = super.ck.l;
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / super.cj;
        super.goto = goto1;
        super.cl = cl;
        super.for = 1;
        super.byte = this.e7;
        super.eb = 6;
    }
    
    protected void r() {
        super.dQ = new aq[6][];
        super.dW = new aq[6][];
        super.d0 = (int)(17.0f * super.void.ai / 100.0f);
        if (super.d0 / 2 * 2 == super.d0) {
            ++super.d0;
        }
        super.dY = super.d0;
        for (int i = 0; i < 6; ++i) {
            super.dQ[i] = new aq[super.d0 * super.dY];
            super.dW[i] = new aq[super.d0 * super.dY];
            for (int j = 0; j < super.d0 * super.dY; ++j) {
                super.dQ[i][j] = new aq();
                super.dW[i][j] = new aq();
            }
        }
        this.a(super.dQ);
        this.a(super.dW);
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
        final int n11 = super.d0 * super.d0;
        final int n12 = super.d0 / 2;
        final float n13 = (super.dE.i - 1) / (super.d0 - 1);
        final float n14 = (super.dE.t / 6 - 1) / (super.d0 - 1);
        n = n * super.dE.t / 6;
        for (int i = 0; i < super.dY; ++i) {
            for (int j = 0; j < super.d0; ++j) {
                final int n15 = j + i * super.d0;
                array[n15].try = n2 * n12 + n5 * (j - n12) + n6 * (i - n12);
                array[n15].if = n3 * n12 + n7 * (j - n12) + n8 * (i - n12);
                array[n15].byte = n4 * n12 + n9 * (j - n12) + n10 * (i - n12);
                array[n15].do = j * n13;
                array[n15].a = n + 0.5f + i * n14;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return super.dE != null && (!super.dE.u || this.do(this.if(n, n2)) < 0L);
    }
    
    protected long do(final aq aq) {
        final int if1 = this.if(aq);
        float n = (float)Math.atan2(aq.if, aq.try);
        final float n2 = (float)Math.atan(aq.byte / Math.sqrt(Math.pow(aq.try, 2.0) + Math.pow(aq.if, 2.0)));
        final long n3 = super.dE.i / 2;
        final long n4 = super.dE.t / 12;
        int n5 = 0;
        int n6 = 0;
        if (if1 == bi.e5 || if1 == bi.e8) {
            final float n7 = (float)Math.tan(1.5707963267948966 - Math.abs(n2)) * n4;
            n5 = (int)(n3 - Math.sin(n) * n7);
            if (if1 == bi.e5) {
                n6 = (int)(Math.cos(n) * n7 + n4);
            }
            else if (if1 == bi.e8) {
                n6 = (int)(n4 - Math.cos(n) * n7);
            }
        }
        else if (if1 == bi.e2 || if1 == bi.e6 || if1 == bi.e3 || if1 == bi.e4) {
            if (if1 == bi.e3 || if1 == bi.e4) {
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
        return super.dE.a(n5, n6 + if1 * super.dE.t / 6);
    }
    
    private int if(final aq aq) {
        final float try1 = aq.try;
        final float if1 = aq.if;
        final float byte1 = aq.byte;
        if (Math.abs(try1) > Math.abs(if1)) {
            if (Math.abs(try1) > Math.abs(byte1)) {
                if (try1 > 0.0f) {
                    return bi.e2;
                }
                return bi.e6;
            }
            else {
                if (byte1 > 0.0f) {
                    return bi.e5;
                }
                return bi.e8;
            }
        }
        else if (Math.abs(if1) > Math.abs(byte1)) {
            if (if1 > 0.0f) {
                return bi.e3;
            }
            return bi.e4;
        }
        else {
            if (byte1 > 0.0f) {
                return bi.e5;
            }
            return bi.e8;
        }
    }
}
