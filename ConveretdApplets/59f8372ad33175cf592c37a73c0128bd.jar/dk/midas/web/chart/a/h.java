// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.a;

public class h
{
    private static byte[] try;
    private byte[] int;
    private int for;
    private boolean a;
    private byte[] do;
    private int if;
    private int new;
    
    public h() {
        this.new = 4096;
        this.do = new byte[this.new];
        this.int = new byte[4];
        final boolean b = false;
        this.for = (b ? 1 : 0);
        this.if = (b ? 1 : 0);
        this.a = false;
    }
    
    private void a(final byte b) {
        if (this.if == this.new) {
            this.new *= (int)1.5;
            final byte[] do1 = new byte[this.new];
            System.arraycopy(this.do, 0, do1, 0, this.if);
            this.do = do1;
        }
        this.do[this.if++] = b;
    }
    
    private void a(final char c) throws IllegalArgumentException {
        if (this.a) {
            throw new IllegalArgumentException("bad '='");
        }
        final byte b = h.try[c];
        if (b < 0) {
            return;
        }
        this.int[this.for++] = b;
        if (this.for == 4) {
            this.for = 0;
            if (this.int[0] == 64 || this.int[1] == 64 || (this.int[2] == 64 && this.int[3] != 64)) {
                throw new IllegalArgumentException("bad '='");
            }
            this.a((byte)(this.int[0] << 2 | this.int[1] >>> 4));
            if (this.int[2] == 64) {
                this.a = true;
                return;
            }
            this.a((byte)(this.int[1] << 4 | this.int[2] >>> 2));
            if (this.int[3] == 64) {
                this.a = true;
                return;
            }
            this.a((byte)(this.int[2] << 6 | this.int[3]));
        }
    }
    
    public void a(final String s) throws IllegalArgumentException {
        for (int i = 0; i < s.length(); ++i) {
            this.a(s.charAt(i));
        }
    }
    
    public byte[] a() throws IllegalArgumentException {
        if (this.for != 0) {
            throw new IllegalArgumentException("not multiple of 4 chars");
        }
        final byte[] array = new byte[this.if];
        System.arraycopy(this.do, 0, array, 0, this.if);
        final boolean b = false;
        this.if = (b ? 1 : 0);
        this.for = (b ? 1 : 0);
        this.a = false;
        return array;
    }
    
    static {
        h.try = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 64, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    }
}
