import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class D
{
    public ae a;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int[] a;
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() == 255);
        this.b = (inputStream.read() == 255);
        this.c = (inputStream.read() == 255);
        this.d = (inputStream.read() == 255);
        this.e = (inputStream.read() == 255);
        this.a = aK.b(inputStream);
        this.b = aK.b(inputStream);
        this.c = aK.b(inputStream);
        this.d = aK.b(inputStream);
        this.e = aK.b(inputStream);
        this.f = aK.b(inputStream);
        this.g = aK.b(inputStream);
        this.h = aK.b(inputStream);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write(this.a ? 255 : 0);
        outputStream.write(this.b ? 255 : 0);
        outputStream.write(this.c ? 255 : 0);
        outputStream.write(this.d ? 255 : 0);
        outputStream.write(this.e ? 255 : 0);
        aK.a(outputStream, this.a);
        aK.a(outputStream, this.b);
        aK.a(outputStream, this.c);
        aK.a(outputStream, this.d);
        aK.a(outputStream, this.e);
        aK.a(outputStream, this.f);
        aK.a(outputStream, this.g);
        aK.a(outputStream, this.h);
    }
    
    public D(final ae a) {
        this.a = null;
        this.a = new int[4];
        this.a = a;
    }
    
    public final void a() {
        if (this.c && this.d > 0) {
            --this.d;
            if (this.d == 0) {
                this.c();
            }
        }
    }
    
    public final void b() {
        if (this.d) {
            this.e = this.f;
            this.c();
        }
        else if (this.e > 0) {
            --this.e;
            this.c();
        }
        if (!this.e) {
            this.d = false;
        }
    }
    
    public final int a() {
        if (this.d == 0 || !this.a) {
            return 0;
        }
        return 1;
    }
    
    public final void a(final int n, final int n2) {
        if (n == 16392) {
            this.a[0] = n2;
            this.e = ((n2 & 0x80) != 0x0);
            this.f = (n2 & 0x7F);
            this.c = !this.e;
        }
        else if (n == 16394) {
            this.a[2] = n2;
            this.b &= 0x700;
            this.b |= n2;
        }
        else if (n == 16395) {
            this.a[3] = n2;
            this.b &= 0xFF;
            this.b |= (n2 & 0x7) << 8;
            this.d = this.a.a(n2 & 0xF8);
            this.d = true;
        }
        this.c();
    }
    
    public final void a(final boolean a) {
        if (!(this.a = a)) {
            this.d = 0;
        }
        this.c();
    }
    
    public final void c() {
        this.b = (this.a && this.b > 7 && this.e > 0 && this.d > 0);
    }
    
    public final void d() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.a = false;
        this.b = false;
        this.d = 0;
        this.c = false;
        this.e = 0;
        this.f = 0;
        this.d = true;
        this.e = false;
        this.h = 0;
        this.g = 15;
    }
}
