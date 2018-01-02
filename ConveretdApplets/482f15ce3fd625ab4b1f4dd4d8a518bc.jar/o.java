import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class o extends at
{
    private int a;
    private boolean a;
    private int b;
    private int[] a;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    
    public o() {
        this.a = 0;
        this.a = false;
        this.b = 0;
        this.a = new int[8];
        this.c = 0;
        this.d = 1;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
    }
    
    public final int a() {
        return 248;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        for (int i = 0; i < 8; ++i) {
            this.a[i] = 0;
        }
        this.c = 0;
        this.d = 1;
        this.f();
        this.e = 0;
        this.f = 2;
        this.g = 4;
        this.h = 5;
        this.i = 6;
        this.j = 7;
        this.g();
        this.a = false;
        this.a = 0;
        this.b = 0;
    }
    
    public final void a(final int n, final int n2) {
        if (n >= 24576 && n < 32768) {
            this.a(2 * n2, 2 * n2 + 1, this.b() - 2, this.b() - 1);
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
                        this.e = (n3 & 0xFE);
                        this.g();
                        break;
                    }
                    case 1: {
                        this.f = (n3 & 0xFE);
                        this.g();
                        break;
                    }
                    case 2: {
                        this.g = n3;
                        this.g();
                        break;
                    }
                    case 3: {
                        this.h = n3;
                        this.g();
                        break;
                    }
                    case 4: {
                        this.i = n3;
                        this.g();
                        break;
                    }
                    case 5: {
                        this.j = n3;
                        this.g();
                        break;
                    }
                    case 6: {
                        this.c = n3;
                        this.f();
                        break;
                    }
                    case 7: {
                        this.d = n3;
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
            case 49152: {
                this.a = 190;
                this.b = 190;
                this.a = false;
            }
            case 49153: {
                this.a = 190;
                this.b = 190;
                this.a = true;
            }
            case 57344: {
                this.a = false;
            }
            case 57345: {
                this.a = true;
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.a && n >= 0 && n < 240 && (super.a.a.a.b & 0x18) != 0x0) {
            --this.a;
            if (this.a < 0) {
                this.a = this.b;
                return 3;
            }
        }
        return 0;
    }
    
    private void f() {
        if (this.b()) {
            this.a(this.b() - 2, this.d, this.c, this.b() - 1);
            return;
        }
        this.a(this.c, this.d, this.b() - 2, this.b() - 1);
    }
    
    private void g() {
        if (this.c() != 0) {
            if (this.a()) {
                this.a(this.g, this.h, this.i, this.j, this.e, this.e + 1, this.f, this.f + 1);
                return;
            }
            this.a(this.e, this.e + 1, this.f, this.f + 1, this.g, this.h, this.i, this.j);
        }
    }
    
    private boolean a() {
        return (this.a[0] & 0x80) != 0x0;
    }
    
    private boolean b() {
        return (this.a[0] & 0x40) != 0x0;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() & 0xFF);
        this.a = (inputStream.read() == 255);
        this.b = (inputStream.read() & 0xFF);
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = (inputStream.read() & 0xFF);
        }
        this.c = (inputStream.read() & 0xFF);
        this.d = (inputStream.read() & 0xFF);
        this.e = (inputStream.read() & 0xFF);
        this.f = (inputStream.read() & 0xFF);
        this.g = (inputStream.read() & 0xFF);
        this.h = (inputStream.read() & 0xFF);
        this.i = (inputStream.read() & 0xFF);
        this.j = (inputStream.read() & 0xFF);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 4 + this.a.length + 8);
        outputStream.write(this.a() & 0xFF);
        outputStream.write(this.a & 0xFF);
        outputStream.write(this.a ? 255 : 0);
        outputStream.write(this.b & 0xFF);
        for (int i = 0; i < this.a.length; ++i) {
            outputStream.write(this.a[i] & 0xFF);
        }
        outputStream.write(this.c & 0xFF);
        outputStream.write(this.d & 0xFF);
        outputStream.write(this.e & 0xFF);
        outputStream.write(this.f & 0xFF);
        outputStream.write(this.g & 0xFF);
        outputStream.write(this.h & 0xFF);
        outputStream.write(this.i & 0xFF);
        outputStream.write(this.j & 0xFF);
    }
}
