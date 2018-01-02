import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class E extends at
{
    private int a;
    private boolean a;
    private int[] a;
    
    public E() {
        this.a = 0;
        this.a = false;
        this.a = new int[1];
    }
    
    public final int a() {
        return 48;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int a) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 32768: {
                if (this.a[0] == 0) {
                    if ((a & 0x40) != 0x0) {
                        this.c();
                    }
                    else {
                        this.b();
                    }
                }
                this.c(a);
            }
            case 32769: {
                this.d(a);
            }
            case 32770: {
                this.g(a * 2 + 0);
                this.h(a * 2 + 1);
            }
            case 32771: {
                this.i(a * 2 + 0);
                this.j(a * 2 + 1);
            }
            case 40960: {
                this.k(a);
            }
            case 40961: {
                this.l(a);
            }
            case 40962: {
                this.m(a);
            }
            case 40963: {
                this.n(a);
            }
            case 49152: {
                this.a = a;
            }
            case 49153: {
                this.a = ((a & 0x1) != 0x0);
            }
            case 57344: {
                if ((a & 0x40) != 0x0) {
                    this.c();
                }
                else {
                    this.b();
                }
                this.a[0] = 1;
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
        this.a = false;
        (this.a = 0)[0] = false;
    }
    
    public final int a(final int n) {
        if (this.a && n >= 0 && n <= 239 && (super.a.a.a.b & 0x18) != 0x0) {
            if (this.a == 255) {
                this.a = false;
                return 3;
            }
            ++this.a;
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
