import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class C extends at
{
    public int a;
    private int b;
    private boolean a;
    private int c;
    private int[] a;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    
    public C() {
        this.a = 0;
        this.b = 0;
        this.a = false;
        this.c = 0;
        this.a = new int[8];
        this.d = 0;
        this.e = 1;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }
    
    public final int a() {
        return 47;
    }
    
    public final void a(final long n) {
        this.a = 0;
        if (n == 2055219138L) {
            this.a = 1;
        }
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        for (int i = 0; i < 8; ++i) {
            this.a[i] = 0;
        }
        this.l = 0;
        this.d = 0;
        this.e = 1;
        this.f();
        if (this.c() > 0) {
            this.f = 0;
            this.g = 2;
            this.h = 4;
            this.i = 5;
            this.j = 6;
            this.k = 7;
            this.g();
        }
        else {
            final boolean b = false;
            this.k = (b ? 1 : 0);
            this.j = (b ? 1 : 0);
            this.i = (b ? 1 : 0);
            this.h = (b ? 1 : 0);
            this.g = (b ? 1 : 0);
            this.f = (b ? 1 : 0);
        }
        this.a = false;
        this.b = 0;
        this.c = 0;
    }
    
    public final void a(final int n, final int n2) {
        if (n >= 24576 && n < 32768) {
            if (n == 24576) {
                if (this.a == 1) {
                    this.l = (n2 & 0x6) >> 1;
                }
                else {
                    this.l = (n2 & 0x1) << 1;
                }
                this.f();
                this.g();
            }
            return;
        }
        switch (n & 0xE001) {
            case 32768: {
                this.a[0] = n2;
                this.g();
                this.f();
            }
            case 32769: {
                this.a[1] = n2;
                final int n3 = this.a[1];
                switch (this.a[0] & 0x7) {
                    case 0: {
                        if (this.c() > 0) {
                            this.f = (n3 & 0xFE);
                            this.g();
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (this.c() > 0) {
                            this.g = (n3 & 0xFE);
                            this.g();
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.c() > 0) {
                            this.h = n3;
                            this.g();
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.c() > 0) {
                            this.i = n3;
                            this.g();
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if (this.c() > 0) {
                            this.j = n3;
                            this.g();
                            break;
                        }
                        break;
                    }
                    case 5: {
                        if (this.c() > 0) {
                            this.k = n3;
                            this.g();
                            break;
                        }
                        break;
                    }
                    case 6: {
                        this.d = n3;
                        this.f();
                        break;
                    }
                    case 7: {
                        this.e = n3;
                        this.f();
                        break;
                    }
                }
            }
            case 40960: {
                this.a[2] = n2;
                if (super.a.a.b) {
                    break;
                }
                if ((n2 & 0x1) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
            }
            case 40961: {
                this.a[3] = n2;
            }
            case 49152: {
                this.a[4] = n2;
                this.b = this.a[4];
            }
            case 49153: {
                this.a[5] = n2;
                this.c = this.a[5];
            }
            case 57344: {
                this.a[6] = n2;
                this.a = false;
            }
            case 57345: {
                this.a[7] = n2;
                this.a = true;
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.a && n >= 0 && n < 240 && (super.a.a.a.b & 0x18) != 0x0) {
            --this.b;
            if (this.b < 0) {
                this.b = this.c;
                return 3;
            }
        }
        return 0;
    }
    
    private void f() {
        if (this.b()) {
            this.c(this.l * 8 + ((this.a == 1 && this.l != 2) ? 6 : 14));
            this.d(this.l * 8 + this.e);
            this.e(this.l * 8 + this.d);
            this.f(this.l * 8 + ((this.a == 1 && this.l != 2) ? 7 : 15));
            return;
        }
        this.c(this.l * 8 + this.d);
        this.d(this.l * 8 + this.e);
        this.e(this.l * 8 + ((this.a == 1 && this.l != 2) ? 6 : 14));
        this.f(this.l * 8 + ((this.a == 1 && this.l != 2) ? 7 : 15));
    }
    
    private void g() {
        if (this.c() != 0) {
            if (this.a()) {
                this.g((this.l & 0x2) * 64 + this.h);
                this.h((this.l & 0x2) * 64 + this.i);
                this.i((this.l & 0x2) * 64 + this.j);
                this.j((this.l & 0x2) * 64 + this.k);
                this.k((this.l & 0x2) * 64 + this.f + 0);
                this.l((this.l & 0x2) * 64 + this.f + 1);
                this.m((this.l & 0x2) * 64 + this.g + 0);
                this.n((this.l & 0x2) * 64 + this.g + 1);
                return;
            }
            this.g((this.l & 0x2) * 64 + this.f + 0);
            this.h((this.l & 0x2) * 64 + this.f + 1);
            this.i((this.l & 0x2) * 64 + this.g + 0);
            this.j((this.l & 0x2) * 64 + this.g + 1);
            this.k((this.l & 0x2) * 64 + this.h);
            this.l((this.l & 0x2) * 64 + this.i);
            this.m((this.l & 0x2) * 64 + this.j);
            this.n((this.l & 0x2) * 64 + this.k);
        }
    }
    
    private boolean a() {
        return (this.a[0] & 0x80) != 0x0;
    }
    
    private boolean b() {
        return (this.a[0] & 0x40) != 0x0;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.b = (inputStream.read() & 0xFF);
        this.a = (inputStream.read() == 255);
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
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 4 + this.a.length + 8);
        outputStream.write(this.a() & 0xFF);
        outputStream.write(this.b & 0xFF);
        outputStream.write(this.a ? 255 : 0);
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
    }
}
