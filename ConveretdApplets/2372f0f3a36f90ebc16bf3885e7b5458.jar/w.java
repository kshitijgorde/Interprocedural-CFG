// 
// Decompiled by Procyon v0.5.30
// 

public class w
{
    public static final int[] a;
    public int b;
    public byte[] c;
    public int d;
    public int e;
    public int f;
    
    public w() {
        this.b = 0;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }
    
    public void a() {
        this.c = new byte[256];
        this.b = 0;
        this.c[0] = 0;
        this.f = 256;
    }
    
    public void b() {
        this.c = null;
    }
    
    public void a(final byte[] c, final int b, final int f) {
        this.b = b;
        this.c = c;
        final boolean b2 = false;
        this.e = (b2 ? 1 : 0);
        this.d = (b2 ? 1 : 0);
        this.f = f;
    }
    
    public int a(int n) {
        final int n2 = w.a[n];
        n += this.d;
        if (this.e + 4 >= this.f && this.e + (n - 1) / 8 >= this.f) {
            return -1;
        }
        int n3 = (this.c[this.b] & 0xFF) >>> this.d;
        if (n > 8) {
            n3 |= (this.c[this.b + 1] & 0xFF) << 8 - this.d;
            if (n > 16) {
                n3 |= (this.c[this.b + 2] & 0xFF) << 16 - this.d;
                if (n > 24) {
                    n3 |= (this.c[this.b + 3] & 0xFF) << 24 - this.d;
                    if (n > 32 && this.d != 0) {
                        n3 |= (this.c[this.b + 4] & 0xFF) << 32 - this.d;
                    }
                }
            }
        }
        return n2 & n3;
    }
    
    public void b(int n) {
        n += this.d;
        this.b += n / 8;
        this.e += n / 8;
        this.d = (n & 0x7);
    }
    
    public void c(final int n) {
        this.b += n / 8;
        this.e += n / 8;
        this.d = (n & 0x7);
    }
    
    public int d(int n) {
        final int n2 = w.a[n];
        n += this.d;
        if (this.e + 4 >= this.f) {
            final int n3 = -1;
            if (this.e + (n - 1) / 8 >= this.f) {
                this.c(n);
                return n3;
            }
        }
        int n4 = (this.c[this.b] & 0xFF) >>> this.d;
        if (n > 8) {
            n4 |= (this.c[this.b + 1] & 0xFF) << 8 - this.d;
            if (n > 16) {
                n4 |= (this.c[this.b + 2] & 0xFF) << 16 - this.d;
                if (n > 24) {
                    n4 |= (this.c[this.b + 3] & 0xFF) << 24 - this.d;
                    if (n > 32 && this.d != 0) {
                        n4 |= (this.c[this.b + 4] & 0xFF) << 32 - this.d;
                    }
                }
            }
        }
        final int n5 = n4 & n2;
        this.c(n);
        return n5;
    }
    
    public int c() {
        if (this.e >= this.f) {
            final int n = -1;
            ++this.d;
            if (this.d > 7) {
                this.d = 0;
                ++this.b;
                ++this.e;
            }
            return n;
        }
        final int n2 = this.c[this.b] >> this.d & 0x1;
        ++this.d;
        if (this.d > 7) {
            this.d = 0;
            ++this.b;
            ++this.e;
        }
        return n2;
    }
    
    static {
        a = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };
    }
}
