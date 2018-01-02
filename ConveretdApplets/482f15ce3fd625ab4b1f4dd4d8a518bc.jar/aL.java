import java.io.IOException;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aL
{
    private int m;
    private int n;
    private byte[] b;
    private int o;
    private int p;
    private int q;
    public int a;
    public int b;
    public int c;
    public int d;
    public int[] a;
    public int[] b;
    public int e;
    public int f;
    public boolean a;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int[] c;
    public int l;
    public byte[] a;
    
    public aL(final int m, final int n, final byte[] b, final int n2) {
        this.b = 12;
        this.d = 4096;
        this.a = new int[5003];
        this.b = new int[5003];
        this.e = 5003;
        this.f = 0;
        this.a = false;
        this.j = 0;
        this.k = 0;
        this.c = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
        this.a = new byte[256];
        this.m = m;
        this.n = n;
        this.b = b;
        this.o = Math.max(2, n2);
    }
    
    public final void a(final byte b, final OutputStream outputStream) throws IOException {
        this.a[this.l++] = b;
        if (this.l >= 254) {
            this.c(outputStream);
        }
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        this.a(this.e);
        this.f = this.h + 2;
        this.a = true;
        this.b(this.h, outputStream);
    }
    
    public final void a(final int n) {
        for (int i = 0; i < n; ++i) {
            this.a[i] = -1;
        }
    }
    
    public final void a(final int g, final OutputStream outputStream) throws IOException {
        this.g = g;
        this.a = false;
        this.a = this.g;
        this.c = this.a(this.a);
        this.h = 1 << g - 1;
        this.i = this.h + 1;
        this.f = this.h + 2;
        this.l = 0;
        int a = this.a();
        int n = 0;
        for (int i = this.e; i < 65536; i *= 2) {
            ++n;
        }
        final int n2 = 8 - n;
        final int e = this.e;
        this.a(e);
        this.b(this.h, outputStream);
        int a2;
    Label_0122:
        while ((a2 = this.a()) != -1) {
            final int n3 = (a2 << this.b) + a;
            int n4 = a2 << n2 ^ a;
            if (this.a[n4] == n3) {
                a = this.b[n4];
            }
            else {
                if (this.a[n4] >= 0) {
                    int n5 = e - n4;
                    if (n4 == 0) {
                        n5 = 1;
                    }
                    do {
                        if ((n4 -= n5) < 0) {
                            n4 += e;
                        }
                        if (this.a[n4] == n3) {
                            a = this.b[n4];
                            continue Label_0122;
                        }
                    } while (this.a[n4] >= 0);
                }
                this.b(a, outputStream);
                a = a2;
                if (this.f < this.d) {
                    this.b[n4] = this.f++;
                    this.a[n4] = n3;
                }
                else {
                    this.a(outputStream);
                }
            }
        }
        this.b(a, outputStream);
        this.b(this.i, outputStream);
    }
    
    public final void b(final OutputStream outputStream) throws IOException {
        outputStream.write(this.o);
        this.p = this.m * this.n;
        this.q = 0;
        this.a(this.o + 1, outputStream);
        outputStream.write(0);
    }
    
    public final void c(final OutputStream outputStream) throws IOException {
        if (this.l > 0) {
            outputStream.write(this.l);
            outputStream.write(this.a, 0, this.l);
            this.l = 0;
        }
    }
    
    public final int a(final int n) {
        return (1 << n) - 1;
    }
    
    private int a() {
        if (this.p == 0) {
            return -1;
        }
        --this.p;
        return this.b[this.q++] & 0xFF;
    }
    
    public final void b(final int j, final OutputStream outputStream) throws IOException {
        this.j &= this.c[this.k];
        if (this.k > 0) {
            this.j |= j << this.k;
        }
        else {
            this.j = j;
        }
        this.k += this.a;
        while (this.k >= 8) {
            this.a((byte)(this.j & 0xFF), outputStream);
            this.j >>= 8;
            this.k -= 8;
        }
        if (this.f > this.c || this.a) {
            if (this.a) {
                final int g = this.g;
                this.a = g;
                this.c = this.a(g);
                this.a = false;
            }
            else {
                ++this.a;
                if (this.a == this.b) {
                    this.c = this.d;
                }
                else {
                    this.c = this.a(this.a);
                }
            }
        }
        if (j == this.i) {
            while (this.k > 0) {
                this.a((byte)(this.j & 0xFF), outputStream);
                this.j >>= 8;
                this.k -= 8;
            }
            this.c(outputStream);
        }
    }
}
