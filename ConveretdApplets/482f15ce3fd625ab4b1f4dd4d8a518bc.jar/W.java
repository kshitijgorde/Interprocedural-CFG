import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class W extends at
{
    private int a;
    private boolean a;
    
    public W() {
        this.a = 0;
        this.a = false;
    }
    
    public final int a() {
        return 91;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(this.b() - 2, this.b() - 1, this.b() - 2, this.b() - 1);
        this.a = false;
        this.a = 0;
        if (this.c() > 0) {
            this.a(0, 0, 0, 0, 0, 0, 0, 0);
        }
    }
    
    public final void a(final int n, final int a) {
        switch (n & 0xF007) {
            case 24576: {
                this.g(a * 2 + 0);
                this.h(a * 2 + 1);
            }
            case 24577: {
                this.i(a * 2 + 0);
                this.j(a * 2 + 1);
            }
            case 24578: {
                this.k(a * 2 + 0);
                this.l(a * 2 + 1);
            }
            case 24579: {
                this.m(a * 2 + 0);
                this.n(a * 2 + 1);
            }
            case 28672: {
                System.out.println("7000, date:" + a);
                this.c(a);
            }
            case 28673: {
                System.out.println("7001, date:" + a);
                this.d(a);
            }
            case 28674: {
                System.out.println("7002, date:" + a);
                this.a = a;
            }
            case 28675: {
                System.out.println("7003, date:" + a);
                this.a = (a != 0);
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.a && 0 <= n && n <= 240 && (super.a.a.a.b & 0x18) != 0x0 && this.a-- == 0) {
            System.out.println("Trigger");
            this.a = false;
            return 3;
        }
        return 0;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() << 8) + inputStream.read();
        this.a = (inputStream.read() == 255);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 4);
        outputStream.write(this.a() & 0xFF);
        outputStream.write((this.a & 0xFF00) >> 8);
        outputStream.write(this.a & 0xFF);
        outputStream.write(this.a ? 255 : 0);
    }
}
