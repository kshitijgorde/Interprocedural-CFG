import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class U extends at
{
    private int a;
    private int b;
    private boolean a;
    
    public U() {
        this.a = 0;
        this.b = 0;
        this.a = false;
    }
    
    public final int a() {
        return 17;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        this.a = false;
        this.b = 0;
        this.a = 0;
    }
    
    public final void b(final int n, final int n2) {
        switch (n) {
            case 17150: {
                if ((n2 & 0x10) == 0x0) {
                    this.b(0, 0, 0, 0);
                    return;
                }
                this.b(1, 1, 1, 1);
            }
            case 17151: {
                if ((n2 & 0x10) == 0x0) {
                    this.b();
                    return;
                }
                this.c();
            }
            case 17665: {
                this.a = false;
            }
            case 17666: {
                this.b = ((this.b & 0xFF00) | n2);
            }
            case 17667: {
                this.b = ((this.b & 0xFF) | n2 << 8);
                this.a = this.b;
                this.a = true;
            }
            case 17668: {
                this.c(n2);
            }
            case 17669: {
                this.d(n2);
            }
            case 17670: {
                this.e(n2);
            }
            case 17671: {
                this.f(n2);
            }
            case 17680: {
                this.g(n2);
            }
            case 17681: {
                this.h(n2);
            }
            case 17682: {
                this.i(n2);
            }
            case 17683: {
                this.j(n2);
            }
            case 17684: {
                this.k(n2);
            }
            case 17685: {
                this.l(n2);
            }
            case 17686: {
                this.m(n2);
            }
            case 17687: {
                this.n(n2);
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.a) {
            if (this.a >= 65422) {
                this.a = 0;
                this.a = false;
                return 3;
            }
            this.a += 113;
        }
        return 0;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() << 8) + inputStream.read();
        this.b = (inputStream.read() << 8) + inputStream.read();
        this.a = (inputStream.read() == 255);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 6);
        outputStream.write(this.a() & 0xFF);
        outputStream.write((this.a & 0xFF00) >> 8);
        outputStream.write(this.a & 0xFF);
        outputStream.write((this.b & 0xFF00) >> 8);
        outputStream.write(this.b & 0xFF);
        outputStream.write(this.a ? 255 : 0);
    }
}
