import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class X extends at
{
    private int b;
    private int c;
    private boolean a;
    private int[] a;
    public int a;
    
    public X() {
        this.b = 0;
        this.c = 0;
        this.a = false;
        this.a = new int[11];
        this.a = 0;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a[0] = 0;
        this.a[1] = 1;
        this.a[2] = this.b() - 2;
        this.a[3] = this.b() - 1;
        this.a[4] = 0;
        this.a[5] = 0;
        this.a[6] = 0;
        this.a[7] = 0;
        this.a[8] = 0;
        this.a[9] = 0;
        this.a[10] = 0;
        this.a = false;
        this.c = 0;
        this.b = 0;
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
    
    public final void a(final int n, int n2) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 32768: {
                this.c(this.a[0] = ((this.a[0] & 0xF0) | (n2 & 0xF)));
            }
            case 32769: {
                this.c(this.a[0] = ((this.a[0] & 0xF) | (n2 & 0xF) << 4));
            }
            case 32770: {
                this.d(this.a[1] = ((this.a[1] & 0xF0) | (n2 & 0xF)));
            }
            case 32771: {
                this.d(this.a[1] = ((this.a[1] & 0xF) | (n2 & 0xF) << 4));
            }
            case 36864: {
                this.e(this.a[2] = ((this.a[2] & 0xF0) | (n2 & 0xF)));
            }
            case 36865: {
                this.e(this.a[2] = ((this.a[2] & 0xF) | (n2 & 0xF) << 4));
            }
            case 40960: {
                this.g(this.a[3] = ((this.a[3] & 0xF0) | (n2 & 0xF)));
            }
            case 40961: {
                this.g(this.a[3] = ((this.a[3] & 0xF) | (n2 & 0xF) << 4));
            }
            case 40962: {
                this.h(this.a[4] = ((this.a[4] & 0xF0) | (n2 & 0xF)));
            }
            case 40963: {
                this.h(this.a[4] = ((this.a[4] & 0xF) | (n2 & 0xF) << 4));
            }
            case 45056: {
                this.i(this.a[5] = ((this.a[5] & 0xF0) | (n2 & 0xF)));
            }
            case 45057: {
                this.i(this.a[5] = ((this.a[5] & 0xF) | (n2 & 0xF) << 4));
            }
            case 45058: {
                this.j(this.a[6] = ((this.a[6] & 0xF0) | (n2 & 0xF)));
            }
            case 45059: {
                this.j(this.a[6] = ((this.a[6] & 0xF) | (n2 & 0xF) << 4));
            }
            case 49152: {
                this.k(this.a[7] = ((this.a[7] & 0xF0) | (n2 & 0xF)));
            }
            case 49153: {
                this.k(this.a[7] = ((this.a[7] & 0xF) | (n2 & 0xF) << 4));
            }
            case 49154: {
                this.l(this.a[8] = ((this.a[8] & 0xF0) | (n2 & 0xF)));
            }
            case 49155: {
                this.l(this.a[8] = ((this.a[8] & 0xF) | (n2 & 0xF) << 4));
            }
            case 53248: {
                this.m(this.a[9] = ((this.a[9] & 0xF0) | (n2 & 0xF)));
            }
            case 53249: {
                this.m(this.a[9] = ((this.a[9] & 0xF) | (n2 & 0xF) << 4));
            }
            case 53250: {
                this.n(this.a[10] = ((this.a[10] & 0xF0) | (n2 & 0xF)));
            }
            case 53251: {
                this.n(this.a[10] = ((this.a[10] & 0xF) | (n2 & 0xF) << 4));
            }
            case 57344: {
                this.c = ((this.c & 0xFFF0) | (n2 & 0xF));
            }
            case 57345: {
                this.c = ((this.c & 0xFF0F) | (n2 & 0xF) << 4);
            }
            case 57346: {
                this.c = ((this.c & 0xF0FF) | (n2 & 0xF) << 8);
            }
            case 57347: {
                this.c = ((this.c & 0xFFF) | (n2 & 0xF) << 12);
            }
            case 61440: {
                this.b = this.c;
            }
            case 61441: {
                this.a = ((n2 & 0x1) != 0x0);
            }
            case 61442: {
                if ((n2 &= 0x3) == 0x0) {
                    this.c();
                    return;
                }
                if (n2 == 1) {
                    this.b();
                    return;
                }
                this.e();
                break;
            }
        }
    }
    
    public final int a() {
        return 18;
    }
    
    public final int a(final int n) {
        if (this.a) {
            if (this.b <= 113) {
                this.b = ((this.a == 1) ? 114 : 0);
                this.a = false;
                return 3;
            }
            this.b -= 113;
        }
        return 0;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.b = (inputStream.read() << 8) + inputStream.read();
        this.c = (inputStream.read() << 8) + inputStream.read();
        this.a = (inputStream.read() == 255);
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = inputStream.read();
        }
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 6 + this.a.length);
        outputStream.write(this.a() & 0xFF);
        outputStream.write((this.b & 0xFF00) >> 8);
        outputStream.write(this.b & 0xFF);
        outputStream.write((this.c & 0xFF00) >> 8);
        outputStream.write(this.c & 0xFF);
        outputStream.write(this.a ? 255 : 0);
        for (int i = 0; i < this.a.length; ++i) {
            outputStream.write(this.a[i]);
        }
    }
    
    public final void a(final long n) {
        this.a = 0;
        if (n == 253471719L) {
            this.a = 1;
        }
    }
}
