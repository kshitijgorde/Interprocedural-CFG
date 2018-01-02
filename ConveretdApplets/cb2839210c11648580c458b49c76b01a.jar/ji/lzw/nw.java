// 
// Decompiled by Procyon v0.5.30
// 

package ji.lzw;

public class nw
{
    byte[] a;
    short[] b;
    short[] c;
    short d;
    int[] e;
    
    public nw() {
        this.a = new byte[4096];
        this.b = new short[4096];
        this.e = new int[4096];
        this.c = new short[9973];
    }
    
    public int a(final short n, final byte b) {
        if (this.d >= 4096) {
            return 65535;
        }
        int c;
        for (c = c(n, b); this.c[c] != -1; c = (c + 2039) % 9973) {}
        this.c[c] = this.d;
        this.a[this.d] = b;
        if (n == -1) {
            this.b[this.d] = -1;
            this.e[this.d] = 1;
        }
        else {
            this.b[this.d] = n;
            this.e[this.d] = this.e[n] + 1;
        }
        final short d = this.d;
        this.d = (short)(d + 1);
        return d;
    }
    
    public short b(final short n, final byte b) {
        if (n == -1) {
            return (short)(b & 0xFF);
        }
        short n2;
        for (int c = c(n, b); (n2 = this.c[c]) != -1; c = (c + 2039) % 9973) {
            if (this.b[n2] == n && this.a[n2] == b) {
                return n2;
            }
        }
        return -1;
    }
    
    public void a(final int n) {
        this.d = 0;
        for (int i = 0; i < 9973; ++i) {
            this.c[i] = -1;
        }
        for (int n2 = (1 << n) + 2, j = 0; j < n2; ++j) {
            this.a((short)(-1), (byte)j);
        }
    }
    
    public static int c(final short n, final byte b) {
        return (((short)(b << 8) ^ n) & 0xFFFF) % 9973;
    }
}
