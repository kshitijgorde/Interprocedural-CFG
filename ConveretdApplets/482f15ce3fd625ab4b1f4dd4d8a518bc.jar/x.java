import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class x extends at
{
    private int a;
    private boolean a;
    
    public x() {
        this.a = 0;
        this.a = false;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, 14, 15);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
            return;
        }
        this.c(0, 0);
        this.c(0, 1);
        this.c(0, 2);
        this.c(0, 3);
        this.c(0, 4);
        this.c(0, 5);
        this.c(0, 6);
        this.c(0, 7);
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        final int n3 = (n2 & 0x3C) >> 2;
        final int n4 = n2 & 0x3;
        this.c(n3 * 2 + 0);
        this.d(n3 * 2 + 1);
        this.c(0, n4 * 8 + 0);
        this.c(1, n4 * 8 + 1);
        this.c(2, n4 * 8 + 2);
        this.c(3, n4 * 8 + 3);
        this.c(4, n4 * 8 + 4);
        this.c(5, n4 * 8 + 5);
        this.c(6, n4 * 8 + 6);
        this.c(7, n4 * 8 + 7);
    }
    
    public final void b(final int n, final int n2) {
        switch (n) {
            case 17150: {
                if ((n2 & 0x10) != 0x0) {
                    this.d();
                    return;
                }
                this.e();
            }
            case 17151: {
                if ((n2 & 0x10) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
            }
            case 17665: {
                this.a = false;
            }
            case 17666: {
                this.a = ((this.a & 0xFF00) | (n2 & 0xFF));
            }
            case 17667: {
                this.a = ((this.a & 0xFF) | n2 << 8);
                this.a = true;
                break;
            }
        }
    }
    
    public final int a() {
        return 6;
    }
    
    public final int a(final int n) {
        if (this.a) {
            this.a += 133;
            if (this.a >= 65535) {
                this.a = 0;
                return 3;
            }
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
