// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class cH
{
    private byte[] a;
    private int b;
    private int c;
    
    public final void a() {
        this.a = new byte[1024];
        this.b = 0;
        this.c = 0;
    }
    
    public final void a(final int n) {
        this.b += n >> 3;
        this.c += (n & 0x7);
        if (this.c > 7) {
            this.c -= 8;
            ++this.b;
        }
    }
    
    public final int b() {
        return (this.a[this.b] & 0xFF) >> 7 - this.c & 0x1;
    }
    
    public final void a(final byte[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            this.a[i] = array[n + i];
        }
        this.b = 0;
        this.c = 0;
    }
    
    public final int b(int i) {
        int n = 0;
        while (i != 0) {
            n = (n << 1 | ((this.a[this.b] & 0xFF) >> 7 - this.c & 0x1));
            ++this.c;
            if (this.c == 8) {
                this.c = 0;
                ++this.b;
            }
            --i;
        }
        return n;
    }
    
    public final void a(final int n, int i) {
        while (this.b + (i + this.c >> 3) >= this.a.length) {
            final byte[] a = new byte[this.a.length << 1];
            System.arraycopy(this.a, 0, a, 0, this.a.length);
            this.a = a;
        }
        while (i > 0) {
            final int n2 = n >> i - 1 & 0x1;
            final byte[] a2 = this.a;
            final int b = this.b;
            a2[b] |= (byte)(n2 << 7 - this.c);
            ++this.c;
            if (this.c == 8) {
                this.c = 0;
                ++this.b;
            }
            --i;
        }
    }
    
    public final byte[] c() {
        return this.a;
    }
    
    public final int d() {
        return this.b + ((this.c > 0) ? 1 : 0);
    }
}
