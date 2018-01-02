import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aF extends at
{
    private int a;
    private boolean a;
    private int b;
    private int c;
    private int d;
    
    public aF() {
        this.a = 0;
        this.a = false;
        this.b = 0;
        this.c = 0;
        this.d = 0;
    }
    
    public final int a() {
        return 65;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 36864: {
                if (this.c != 0) {
                    break;
                }
                if ((n2 & 0x40) != 0x0) {
                    this.b();
                    return;
                }
                this.c();
            }
            case 36865: {
                if (this.c != 1) {
                    break;
                }
                if ((n2 & 0x80) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
            }
            case 36867: {
                if (this.c == 0) {
                    this.a = ((n2 & 0x80) != 0x0);
                    return;
                }
                break;
            }
            case 36868: {
                if (this.c == 0) {
                    this.a = this.b;
                    return;
                }
                break;
            }
            case 36869: {
                if (this.c == 1) {
                    this.a = n2 << 1;
                    this.a = (n2 != 0);
                    return;
                }
                this.b = ((this.b & 0xFF) | n2 << 8);
            }
            case 36870: {
                if (this.c == 1) {
                    this.a = true;
                    return;
                }
                this.b = ((this.b & 0xFF00) | n2);
            }
            case 45056: {
                this.g(n2);
            }
            case 45057: {
                this.h(n2);
            }
            case 45058: {
                this.i(n2);
            }
            case 45059: {
                this.j(n2);
            }
            case 45060: {
                this.k(n2);
            }
            case 45061: {
                this.l(n2);
            }
            case 45062: {
                this.m(n2);
            }
            case 45063: {
                this.n(n2);
            }
            case 32768: {
                this.c(n2);
            }
            case 40960: {
                this.d(n2);
            }
            case 49152: {
                this.e(n2);
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        this.a = false;
        this.a = 0;
    }
    
    public final void a(final long n) {
        this.c = 0;
        this.d = 0;
        if (n == 2659812229L) {
            this.c = 1;
        }
        if (n == 0L) {
            this.d = 1;
        }
    }
    
    public final int a(final int n) {
        if (this.a) {
            if (this.c == 1) {
                if (this.a == 0) {
                    this.a = false;
                    return 3;
                }
                --this.a;
            }
            else {
                if (this.a <= ((this.d == 1) ? 111 : 113)) {
                    this.a = false;
                    this.a = 65535;
                    return 3;
                }
                this.a -= ((this.d == 1) ? 111 : 113);
            }
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
