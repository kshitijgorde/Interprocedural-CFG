import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class g
{
    private int a;
    private int b;
    public i c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public h h;
    public int i;
    public int j;
    public byte k;
    public int l;
    public int m;
    public int n;
    public boolean o;
    
    public void a() {
        if (this.e != 12) {
            ++this.e;
            this.f = (1 << this.e) - 1;
            if (this.g) {
                --this.f;
            }
            this.c.a(this.e);
        }
    }
    
    public void a(final InputStream inputStream) {
        this.c = new i(inputStream, true);
        this.d();
        this.c();
    }
    
    public g(final InputStream inputStream, final int d, final boolean g) {
        this.d = d;
        this.g = g;
        this.a = 1 << d;
        this.b = (1 << d) + 1;
        this.h = new h(d);
        this.a(inputStream);
        this.d();
    }
    
    public int a(final byte[] array, final int n, final int n2) {
        return this.a(array, n, n2, 0);
    }
    
    public int a(final byte[] array, final int n, final int l, final int n2) {
        final int a = this.h.a(array, n, l, n2);
        if (a < 0) {
            this.l = l;
            this.n = this.j;
            this.o = true;
            this.m = -a;
        }
        else {
            this.o = false;
        }
        return a;
    }
    
    public int a(final byte[] array) throws IOException {
        final int n = 0;
        final int b = this.b(array);
        if (this.o) {
            return array.length;
        }
        int i = n + b;
        while (i < array.length) {
            final int b2 = this.b();
            if (b2 == this.b) {
                break;
            }
            if (b2 == this.a) {
                this.d();
                final int b3 = this.b();
                if (b3 == this.b) {
                    break;
                }
                array[i++] = (byte)b3;
                this.j = b3;
                this.k = (byte)b3;
            }
            else if (this.h.a(b2)) {
                final int a = this.a(array, i, b2);
                if (this.h.a(this.j, array[i]) == this.f) {
                    this.a();
                }
                this.j = b2;
                this.k = array[i];
                if (a < 0) {
                    return array.length;
                }
                i += a;
            }
            else {
                final int a2 = this.h.a(this.j, this.k);
                final int a3 = this.a(array, i, a2);
                this.j = b2;
                this.k = array[i];
                if (a2 == this.f) {
                    this.a();
                }
                if (a3 < 0) {
                    return array.length;
                }
                i += a3;
            }
        }
        return i;
    }
    
    public int b() throws IOException {
        return this.c.a();
    }
    
    public int b(final byte[] array) {
        if (!this.o) {
            return 0;
        }
        return this.a(array, 0, this.l, this.m);
    }
    
    public void c() {
        this.i = 0;
        this.e = this.d;
        this.a();
    }
    
    public void d() {
        this.h.b();
        this.c();
    }
}
