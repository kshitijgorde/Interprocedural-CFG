import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class p
{
    public ae a;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
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
    public long a;
    public long b;
    public int l;
    public int[] a;
    
    public final void a(final InputStream inputStream) throws IOException, aO {
        this.a = (inputStream.read() == 255);
        this.b = (inputStream.read() == 255);
        this.c = (inputStream.read() == 255);
        this.d = (inputStream.read() == 255);
        this.e = (inputStream.read() == 255);
        this.f = (inputStream.read() == 255);
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
        this.a = aK.b(inputStream);
        this.b = aK.b(inputStream);
        this.l = aK.b(inputStream);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write(this.a ? 255 : 0);
        outputStream.write(this.b ? 255 : 0);
        outputStream.write(this.c ? 255 : 0);
        outputStream.write(this.d ? 255 : 0);
        outputStream.write(this.e ? 255 : 0);
        outputStream.write(this.f ? 255 : 0);
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
        aK.a(outputStream, (int)this.a);
        aK.a(outputStream, (int)this.b);
        aK.a(outputStream, this.l);
    }
    
    public p(final ae a) {
        this.a = 0L;
        this.b = 1L;
        this.a = new int[4];
        this.a = a;
        this.h = 16384;
    }
    
    public final void a() {
        if (this.d && this.a > 0) {
            --this.a;
            if (this.a == 0) {
                this.c();
            }
        }
    }
    
    public final void b() {
        if (this.e) {
            this.e = false;
            this.e = this.d + 1;
            this.f = 15;
        }
        else if (--this.e <= 0) {
            this.e = this.d + 1;
            if (this.f > 0) {
                --this.f;
            }
            else {
                this.f = (this.c ? 15 : 0);
            }
        }
        this.g = (this.b ? this.d : this.f);
        this.c();
    }
    
    public final void c() {
        if (this.a && this.a > 0) {
            this.k = this.i * this.g;
        }
    }
    
    public final void a(final int n, final int n2) {
        if (n == 16396) {
            this.a[0] = n2;
            this.b = ((n2 & 0x10) != 0x0);
            this.d = (n2 & 0xF);
            this.c = ((n2 & 0x20) != 0x0);
            this.d = ((n2 & 0x20) == 0x0);
            this.g = (this.b ? this.d : this.f);
            return;
        }
        if (n == 16398) {
            this.a[2] = n2;
            this.c = this.a.c(n2 & 0xF);
            this.j = n2 >> 7;
            return;
        }
        if (n == 16399) {
            this.a[3] = n2;
            this.a = this.a.a(n2 & 0xF8);
            this.e = true;
        }
    }
    
    public final void a(final boolean a) {
        if (!(this.a = a)) {
            this.a = 0;
        }
        this.c();
    }
    
    public final int a() {
        if (this.a == 0 || !this.a) {
            return 0;
        }
        return 1;
    }
    
    public final void d() {
        this.b = 0;
        this.c = 0;
        this.a = false;
        this.a = 0;
        this.d = false;
        this.b = false;
        this.c = false;
        this.f = false;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 1;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }
}
