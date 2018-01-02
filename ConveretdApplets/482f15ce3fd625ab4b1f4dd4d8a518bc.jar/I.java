import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class I
{
    public ae a;
    public static int[] a;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
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
    public int p;
    public int[] b;
    
    public final void a(final InputStream inputStream) throws IOException, aO {
        this.a = (inputStream.read() == 255);
        this.b = (inputStream.read() == 255);
        this.c = (inputStream.read() == 255);
        this.d = (inputStream.read() == 255);
        this.e = (inputStream.read() == 255);
        this.f = (inputStream.read() == 255);
        this.g = (inputStream.read() == 255);
        this.h = (inputStream.read() == 255);
        this.i = (inputStream.read() == 255);
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
        this.p = aK.b(inputStream);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write(this.a ? 255 : 0);
        outputStream.write(this.b ? 255 : 0);
        outputStream.write(this.c ? 255 : 0);
        outputStream.write(this.d ? 255 : 0);
        outputStream.write(this.e ? 255 : 0);
        outputStream.write(this.f ? 255 : 0);
        outputStream.write(this.g ? 255 : 0);
        outputStream.write(this.h ? 255 : 0);
        outputStream.write(this.i ? 255 : 0);
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
        aK.a(outputStream, this.p);
    }
    
    public I(final ae a, final boolean b) {
        this.b = new int[4];
        this.a = a;
        this.b = b;
    }
    
    public final void a() {
        if (this.c && this.c > 0) {
            --this.c;
            if (this.c == 0) {
                this.d();
            }
        }
    }
    
    public final void b() {
        if (this.g) {
            this.g = false;
            this.j = this.i + 1;
            this.k = 15;
        }
        else if (--this.j <= 0) {
            this.j = this.i + 1;
            if (this.k > 0) {
                --this.k;
            }
            else {
                this.k = (this.f ? 15 : 0);
            }
        }
        this.l = (this.e ? this.i : this.k);
        this.d();
    }
    
    public final void c() {
        final int e = this.e - 1;
        this.e = e;
        if (e <= 0) {
            this.e = this.f + 1;
            if (this.d && this.h > 0 && this.b > 7) {
                this.h = false;
                if (this.g == 0) {
                    this.b += this.b >> this.h;
                    if (this.b > 4095) {
                        this.b = 4095;
                        this.h = true;
                    }
                }
                else {
                    this.b -= (this.b >> this.h) - (this.b ? 1 : 0);
                }
            }
        }
        if (this.i) {
            this.i = false;
            this.e = this.f + 1;
        }
    }
    
    public final void d() {
        if (!this.a || this.c <= 0 || this.b <= 7) {
            this.o = 0;
            return;
        }
        if (this.g == 0 && this.b + (this.b >> this.h) > 4095) {
            this.o = 0;
            return;
        }
        this.o = this.l * I.a[(this.m << 3) + this.d];
    }
    
    public final void a(final int n, final int n2) {
        final int n3 = this.b ? 0 : 4;
        if (n == 16384 + n3) {
            this.b[0] = n2;
            this.e = ((n2 & 0x10) != 0x0);
            this.i = (n2 & 0xF);
            this.f = ((n2 & 0x20) != 0x0);
            this.m = (n2 >> 6 & 0x3);
            this.c = ((n2 & 0x20) == 0x0);
            this.l = (this.e ? this.i : this.k);
            this.d();
            return;
        }
        if (n == 16385 + n3) {
            this.b[1] = n2;
            this.d = ((n2 & 0x80) != 0x0);
            this.f = (n2 >> 4 & 0x7);
            this.g = (n2 >> 3 & 0x1);
            this.h = (n2 & 0x7);
            this.i = true;
            return;
        }
        if (n == 16386 + n3) {
            this.b[2] = n2;
            this.b &= 0x700;
            this.b |= n2;
            return;
        }
        if (n == 16387 + n3) {
            this.b[3] = n2;
            this.b &= 0xFF;
            this.b |= (n2 & 0x7) << 8;
            if (this.a) {
                this.c = this.a.a(n2 & 0xF8);
            }
            this.g = true;
        }
    }
    
    public final void a(final boolean a) {
        if (!(this.a = a)) {
            this.c = 0;
        }
        this.d();
    }
    
    public final int a() {
        if (this.c == 0 || !this.a) {
            return 0;
        }
        return 1;
    }
    
    public final void e() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.p = 0;
        this.a = false;
        this.c = false;
        this.d = false;
        this.h = false;
        this.e = false;
        this.f = false;
    }
    
    static {
        I.a = new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1 };
        final int[] array = { 1, -1, 0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 1, 0, 0, 0, 0, 0 };
    }
}
