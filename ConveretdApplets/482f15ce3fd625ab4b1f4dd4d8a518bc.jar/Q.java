import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Q extends at
{
    private int a;
    private boolean a;
    private int[] a;
    
    public Q() {
        this.a = 0;
        this.a = false;
        this.a = new int[1];
    }
    
    public final int a() {
        return 182;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int a) {
        if (n < 32768) {
            return;
        }
        switch (n & 0xF003) {
            case 32769: {
                if ((a & 0x1) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
            }
            case 40960: {
                this.a[0] = (a & 0x7);
            }
            case 49152: {
                switch (this.a[0]) {
                    case 0: {
                        this.g((a & 0xFE) + 0);
                        this.h((a & 0xFE) + 1);
                        break;
                    }
                    case 1: {
                        this.l(a);
                        break;
                    }
                    case 2: {
                        this.i((a & 0xFE) + 0);
                        this.j((a & 0xFE) + 1);
                        break;
                    }
                    case 3: {
                        this.n(a);
                        break;
                    }
                    case 4: {
                        this.c(a);
                        break;
                    }
                    case 5: {
                        this.d(a);
                        break;
                    }
                    case 6: {
                        this.k(a);
                        break;
                    }
                    case 7: {
                        this.m(a);
                        break;
                    }
                }
            }
            case 57347: {
                this.a = a;
                this.a = (a != 0);
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
        this.a = false;
        this.a = 0;
    }
    
    public final int a(final int n) {
        if (this.a && n >= 0 && n <= 240 && (super.a.a.a.b & 0x18) != 0x0 && 0 == --this.a) {
            this.a = 0;
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
