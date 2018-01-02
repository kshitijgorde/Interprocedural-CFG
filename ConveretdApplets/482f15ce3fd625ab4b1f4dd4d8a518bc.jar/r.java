import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class r
{
    public ae a;
    public boolean a;
    public boolean b;
    public boolean c;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    
    public final void a(final InputStream inputStream) throws IOException, aO {
        this.a = (inputStream.read() == 255);
        this.b = (inputStream.read() == 255);
        this.c = (inputStream.read() == 255);
        this.a = aK.b(inputStream);
        this.b = aK.b(inputStream);
        this.c = aK.b(inputStream);
        this.d = aK.b(inputStream);
        this.e = aK.b(inputStream);
        this.f = aK.b(inputStream);
        this.g = aK.b(inputStream);
        this.h = aK.b(inputStream);
        this.i = aK.b(inputStream);
        this.j = aK.b(inputStream);
        this.k = aK.b(inputStream);
        this.l = aK.b(inputStream);
        this.m = aK.b(inputStream);
        this.n = aK.b(inputStream);
        this.o = aK.b(inputStream);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write(this.a ? 255 : 0);
        outputStream.write(this.b ? 255 : 0);
        outputStream.write(this.c ? 255 : 0);
        aK.a(outputStream, this.a);
        aK.a(outputStream, this.b);
        aK.a(outputStream, this.c);
        aK.a(outputStream, this.d);
        aK.a(outputStream, this.e);
        aK.a(outputStream, this.f);
        aK.a(outputStream, this.g);
        aK.a(outputStream, this.h);
        aK.a(outputStream, this.i);
        aK.a(outputStream, this.j);
        aK.a(outputStream, this.k);
        aK.a(outputStream, this.l);
        aK.a(outputStream, this.m);
        aK.a(outputStream, this.n);
        aK.a(outputStream, this.o);
    }
    
    public r(final ae a) {
        this.c = false;
        this.a = a;
    }
    
    public final void a() {
        if (this.b) {
            if ((this.o & 0x1) == 0x0) {
                if (this.d > 0) {
                    --this.d;
                }
            }
            else if (this.d < 63) {
                ++this.d;
            }
            this.m = (this.a ? ((this.d << 1) + this.n) : 0);
            this.o >>= 1;
        }
        --this.c;
        if (this.c <= 0) {
            this.b = false;
            this.c();
            this.c = 8;
        }
        if (this.c) {
            this.a.a.a.f();
        }
    }
    
    private void c() {
        if (this.h == 0 && this.a == 1) {
            this.f = this.e;
            this.h = this.g;
        }
        if (this.h > 0) {
            this.d();
            if (this.h == 0 && this.a == 2) {
                this.c = true;
            }
        }
    }
    
    private void d() {
        this.o = this.a.a.a.a(this.f);
        this.a.a.a.a(4);
        --this.h;
        ++this.f;
        if (this.f > 65535) {
            this.f = 32768;
        }
        this.b = true;
    }
    
    public final void a(final int n, final int n2) {
        if (n == 16400) {
            if (n2 >> 6 == 0) {
                this.a = 0;
            }
            else if ((n2 >> 6 & 0x1) == 0x1) {
                this.a = 1;
            }
            else if (n2 >> 6 == 2) {
                this.a = 2;
            }
            if ((n2 & 0x80) == 0x0) {
                this.c = false;
            }
            this.b = this.a.b(n2 & 0xF);
            return;
        }
        if (n == 16401) {
            this.d = (n2 >> 1 & 0x3F);
            this.n = (n2 & 0x1);
            if (this.a.f) {
                this.m = (this.d << 1) + this.n;
            }
        }
        else {
            if (n == 16402) {
                this.e = (n2 << 6 | 0xC000);
                this.f = this.e;
                this.j = n2;
                return;
            }
            if (n == 16403) {
                this.g = (n2 << 4) + 1;
                this.h = this.g;
                this.k = n2;
                return;
            }
            if (n == 16405) {
                if ((n2 >> 4 & 0x1) == 0x0) {
                    this.h = 0;
                }
                else {
                    this.f = this.e;
                    this.h = this.g;
                }
                this.c = false;
            }
        }
    }
    
    public final void a(final boolean a) {
        if (!this.a && a) {
            this.h = this.g;
        }
        this.a = a;
    }
    
    public final int a() {
        if (this.h == 0 || !this.a) {
            return 0;
        }
        return 1;
    }
    
    public final int b() {
        if (this.c) {
            return 1;
        }
        return 0;
    }
    
    public final void b() {
        this.a = false;
        this.c = false;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.o = 0;
    }
}
