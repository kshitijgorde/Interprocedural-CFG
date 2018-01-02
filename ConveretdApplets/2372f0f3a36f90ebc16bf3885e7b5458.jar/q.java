// 
// Decompiled by Procyon v0.5.30
// 

public class q
{
    public byte[] a;
    public int b;
    public int c;
    public byte[] d;
    public int e;
    public int f;
    public static int[] g;
    
    public int a() {
        return this.a[this.b + 4] & 0xFF;
    }
    
    public int b() {
        return this.a[this.b + 5] & 0x1;
    }
    
    public int c() {
        return this.a[this.b + 5] & 0x2;
    }
    
    public int d() {
        return this.a[this.b + 5] & 0x4;
    }
    
    public long e() {
        return (((((((this.a[this.b + 13] & 0xFF) << 8 | (this.a[this.b + 12] & 0xFF)) << 8 | (this.a[this.b + 11] & 0xFF)) << 8 | (this.a[this.b + 10] & 0xFF)) << 8 | (this.a[this.b + 9] & 0xFF)) << 8 | (this.a[this.b + 8] & 0xFF)) << 8 | (this.a[this.b + 7] & 0xFF)) << 8 | (this.a[this.b + 6] & 0xFF);
    }
    
    public int f() {
        return (this.a[this.b + 14] & 0xFF) | (this.a[this.b + 15] & 0xFF) << 8 | (this.a[this.b + 16] & 0xFF) << 16 | (this.a[this.b + 17] & 0xFF) << 24;
    }
    
    public int g() {
        return (this.a[this.b + 18] & 0xFF) | (this.a[this.b + 19] & 0xFF) << 8 | (this.a[this.b + 20] & 0xFF) << 16 | (this.a[this.b + 21] & 0xFF) << 24;
    }
    
    public void h() {
        int n = 0;
        for (int i = 0; i < q.g.length; ++i) {
            int n2 = i << 24;
            for (int j = 0; j < 8; ++j) {
                if ((n2 & Integer.MIN_VALUE) != 0x0) {
                    n2 = (n2 << 1 ^ 0x4C11DB7);
                }
                else {
                    n2 <<= 1;
                }
            }
            q.g[i] = (n2 & -1);
        }
        for (int k = 0; k < this.c; ++k) {
            n = (n << 8 ^ q.g[(n >>> 24 & 0xFF) ^ (this.a[this.b + k] & 0xFF)]);
        }
        for (int l = 0; l < this.f; ++l) {
            n = (n << 8 ^ q.g[(n >>> 24 & 0xFF) ^ (this.d[this.e + l] & 0xFF)]);
        }
        this.a[this.b + 22] = (byte)n;
        this.a[this.b + 23] = (byte)(n >>> 8);
        this.a[this.b + 24] = (byte)(n >>> 16);
        this.a[this.b + 25] = (byte)(n >>> 24);
    }
    
    static {
        q.g = new int[256];
    }
}
