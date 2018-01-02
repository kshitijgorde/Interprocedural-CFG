import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class u extends at
{
    public int a;
    public int b;
    public int c;
    public int[] a;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public static int m;
    public static int n;
    public static int o;
    
    public u() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.a = new int[4];
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int a, final int n) {
        if (a < 32768) {
            return;
        }
        if ((a & 0x6000) != (this.a & 0x6000)) {
            this.b = 0;
            this.c = 0;
        }
        this.a = a;
        if ((n & 0x80) != 0x0) {
            this.b = 0;
            this.c = 0;
            return;
        }
        if ((n & 0x1) != 0x0) {
            this.c |= 1 << this.b;
        }
        ++this.b;
        if (this.b < 5) {
            return;
        }
        final int n2 = (a & 0x7FFF) >> 13;
        this.a[n2] = this.c;
        this.b = 0;
        this.c = 0;
        switch (n2) {
            case 0: {
                if ((this.a[0] & 0x2) != 0x0) {
                    if ((this.a[0] & 0x1) != 0x0) {
                        this.c();
                        return;
                    }
                    this.b();
                    return;
                }
                else {
                    if ((this.a[0] & 0x1) != 0x0) {
                        this.b(1, 1, 1, 1);
                        return;
                    }
                    this.b(0, 0, 0, 0);
                    return;
                }
                break;
            }
            case 1: {
                final int n3 = this.a[1];
                if (this.h == u.m) {
                    if ((this.a[0] & 0x10) != 0x0) {
                        if (this.j != 0) {
                            this.i = (this.a[1] & 0x10) >> 4;
                            if ((this.a[0] & 0x8) != 0x0) {
                                this.i |= (this.a[2] & 0x10) >> 3;
                            }
                            this.f();
                            this.j = 0;
                        }
                        else {
                            this.j = 1;
                        }
                    }
                    else {
                        this.i = (((this.a[1] & 0x10) != 0x0) ? 3 : 0);
                        this.f();
                    }
                }
                else if (this.h == u.n && this.c() == 0) {
                    this.i = (this.a[1] & 0x10) >> 4;
                    this.f();
                }
                else if (this.c() != 0) {
                    if ((this.a[0] & 0x10) != 0x0) {
                        final int n4 = n3 << 2;
                        this.g(n4 + 0);
                        this.h(n4 + 1);
                        this.i(n4 + 2);
                        this.j(n4 + 3);
                    }
                    else {
                        final int n5 = n3 << 2;
                        this.a(n5 + 0, n5 + 1, n5 + 2, n5 + 3, n5 + 4, n5 + 5, n5 + 6, n5 + 7);
                    }
                }
                else if ((this.a[0] & 0x10) != 0x0) {
                    final int n6 = n3 << 2;
                    this.c(0, n6 + 0);
                    this.c(1, n6 + 1);
                    this.c(2, n6 + 2);
                    this.c(3, n6 + 3);
                }
            }
            case 2: {
                final int n7 = this.a[2];
                if (this.h == u.m && (this.a[0] & 0x8) != 0x0) {
                    if (this.j != 0) {
                        this.i = (this.a[1] & 0x10) >> 4;
                        this.i |= (this.a[2] & 0x10) >> 3;
                        this.f();
                        this.j = 0;
                    }
                    else {
                        this.j = 1;
                    }
                }
                if (this.c() == 0 && (this.a[0] & 0x10) != 0x0) {
                    final int n8 = n7 << 2;
                    this.c(4, n8 + 0);
                    this.c(5, n8 + 1);
                    this.c(6, n8 + 2);
                    this.c(7, n8 + 3);
                    return;
                }
                if ((this.a[0] & 0x10) != 0x0) {
                    final int n9 = n7 << 2;
                    this.k(n9 + 0);
                    this.l(n9 + 1);
                    this.m(n9 + 2);
                    this.n(n9 + 3);
                }
            }
            case 3: {
                final int n10 = this.a[3];
                if ((this.a[0] & 0x8) != 0x0) {
                    final int n11 = n10 << 1;
                    if ((this.a[0] & 0x4) != 0x0) {
                        this.d = n11;
                        this.e = n11 + 1;
                        this.f = this.k;
                        this.g = this.l;
                    }
                    else if (this.h == u.o) {
                        this.d = 0;
                        this.e = 1;
                        this.f = n11;
                        this.g = n11 + 1;
                    }
                }
                else {
                    final int d = n10 << 1;
                    this.d = d;
                    this.e = d + 1;
                    if (this.h == u.o) {
                        this.f = d + 2;
                        this.g = d + 3;
                    }
                }
                this.f();
                break;
            }
        }
    }
    
    private void f() {
        this.a((this.i << 5) + (this.d & 0x1F), (this.i << 5) + (this.e & 0x1F), (this.i << 5) + (this.f & 0x1F), (this.i << 5) + (this.g & 0x1F));
    }
    
    public final int a() {
        return 1;
    }
    
    public final void a() {
        this.b = 0;
        this.c = 0;
        this.a[0] = 12;
        this.a[1] = 0;
        this.a[2] = 0;
        this.a[3] = 0;
        final int n;
        if ((n = this.b() * 8) == 1024) {
            this.h = u.m;
        }
        else if (n == 512) {
            this.h = u.n;
        }
        else {
            this.h = u.o;
        }
        this.i = 0;
        this.j = 0;
        if (this.h == u.o) {
            this.k = this.b() - 2;
            this.l = this.b() - 1;
        }
        else {
            this.k = 30;
            this.l = 31;
        }
        this.d = 0;
        this.e = 1;
        this.f = this.k;
        this.g = this.l;
        this.f();
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() & 0xFF) << 8;
        this.a |= (inputStream.read() & 0xFF);
        this.b = (inputStream.read() & 0xFF);
        this.c = (inputStream.read() & 0xFF);
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = (inputStream.read() & 0xFF);
        }
        this.d = (inputStream.read() & 0xFF);
        this.e = (inputStream.read() & 0xFF);
        this.f = (inputStream.read() & 0xFF);
        this.g = (inputStream.read() & 0xFF);
        this.h = (inputStream.read() & 0xFF);
        this.i = (inputStream.read() & 0xFF);
        this.j = (inputStream.read() & 0xFF);
        this.k = (inputStream.read() & 0xFF);
        this.l = (inputStream.read() & 0xFF);
        this.f();
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 5 + this.a.length + 9);
        outputStream.write(this.a() & 0xFF);
        outputStream.write(this.a >> 8 & 0xFF);
        outputStream.write(this.a & 0xFF);
        outputStream.write(this.b & 0xFF);
        outputStream.write(this.c & 0xFF);
        for (int i = 0; i < this.a.length; ++i) {
            outputStream.write(this.a[i] & 0xFF);
        }
        outputStream.write(this.d & 0xFF);
        outputStream.write(this.e & 0xFF);
        outputStream.write(this.f & 0xFF);
        outputStream.write(this.g & 0xFF);
        outputStream.write(this.h & 0xFF);
        outputStream.write(this.i & 0xFF);
        outputStream.write(this.j & 0xFF);
        outputStream.write(this.k & 0xFF);
        outputStream.write(this.l & 0xFF);
    }
    
    static {
        u.m = 1024;
        u.n = 512;
        u.o = 1;
    }
}
