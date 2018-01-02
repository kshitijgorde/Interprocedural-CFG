import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aA extends at
{
    private int a;
    private boolean a;
    private int b;
    
    public aA() {
        this.a = 0;
        this.a = false;
        this.b = 0;
    }
    
    public final int a() {
        return 67;
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.g(0);
        this.h(1);
        this.i(2);
        this.j(3);
        this.k(this.b() - 4);
        this.l(this.b() - 3);
        this.m(this.b() - 2);
        this.n(this.b() - 1);
        this.a = false;
        this.a = 0;
        this.b = 0;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, int b) {
        if (n < 32768) {
            return;
        }
        switch (n & 0xF800) {
            case 34816: {
                this.g(b * 2 + 0);
                this.h(b * 2 + 1);
            }
            case 38912: {
                this.i(b * 2 + 0);
                this.j(b * 2 + 1);
            }
            case 43008: {
                this.k(b * 2 + 0);
                this.l(b * 2 + 1);
            }
            case 47104: {
                this.m(b * 2 + 0);
                this.n(b * 2 + 1);
            }
            case 51200: {
                this.a = this.b;
                this.b = b;
            }
            case 55296: {
                this.a = ((b & 0x10) != 0x0);
            }
            case 59392: {
                if ((b &= 0x3) == 0x0) {
                    this.b();
                    return;
                }
                if (b == 1) {
                    this.c();
                    return;
                }
                if (b == 2) {
                    this.b(0, 0, 0, 0);
                    return;
                }
                this.b(1, 1, 1, 1);
            }
            case 63488: {
                this.c(b * 2 + 0);
                this.d(b * 2 + 1);
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.a && n >= 0 && n <= 239 && (super.a.a.a.b & 0x18) != 0x0) {
            if (--this.a == 246) {
                this.a = this.b;
                return 3;
            }
            this.a &= 0xFF;
        }
        return 0;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() << 8) + inputStream.read();
        this.a = (inputStream.read() == 255);
        this.b = (inputStream.read() << 8) + inputStream.read();
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 6);
        outputStream.write(this.a() & 0xFF);
        outputStream.write((this.a & 0xFF00) >> 8);
        outputStream.write(this.a & 0xFF);
        outputStream.write(this.a ? 255 : 0);
        outputStream.write((this.b & 0xFF00) >> 8);
        outputStream.write(this.b & 0xFF);
    }
}
