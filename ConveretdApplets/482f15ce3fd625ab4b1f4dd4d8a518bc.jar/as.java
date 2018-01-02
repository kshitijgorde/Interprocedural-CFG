import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class as extends at
{
    private int a;
    private boolean a;
    private int b;
    private int[] a;
    private int c;
    private int d;
    
    public as() {
        this.a = 0;
        this.a = false;
        this.b = 0;
        this.a = new int[4];
        this.c = 0;
        this.d = 0;
    }
    
    public final int a() {
        return 105;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
        this.a[0] = 12;
        this.a[1] = 0;
        this.a[2] = 0;
        this.a[3] = 16;
        this.c = 0;
        this.d = 0;
        this.a = false;
        this.a = 0;
        this.b = 0;
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        final int n3 = (n & 0x7FFF) >> 13;
        if ((n2 & 0x80) != 0x0) {
            this.c = 0;
            this.d = 0;
            if (n3 == 0) {
                final int[] a = this.a;
                final int n4 = n3;
                a[n4] |= 0xC;
            }
        }
        else {
            this.c |= (n2 & 0x1) << this.d++;
            if (this.d == 5) {
                this.a[n3] = (this.c & 0x1F);
                this.c = 0;
                this.d = 0;
            }
        }
        if ((this.a[0] & 0x2) != 0x0) {
            if ((this.a[0] & 0x1) != 0x0) {
                this.c();
            }
            else {
                this.b();
            }
        }
        else if ((this.a[0] & 0x1) != 0x0) {
            this.b(1, 1, 1, 1);
        }
        else {
            this.b(0, 0, 0, 0);
        }
        switch (this.b) {
            case 0:
            case 1: {
                ++this.b;
            }
            case 2: {
                if ((this.a[1] & 0x8) != 0x0) {
                    if ((this.a[0] & 0x8) != 0x0) {
                        if ((this.a[0] & 0x4) != 0x0) {
                            this.c((this.a[3] & 0x7) * 2 + 16);
                            this.d((this.a[3] & 0x7) * 2 + 17);
                            this.e(30);
                            this.f(31);
                        }
                        else {
                            this.c(16);
                            this.d(17);
                            this.e((this.a[3] & 0x7) * 2 + 16);
                            this.f((this.a[3] & 0x7) * 2 + 17);
                        }
                    }
                    else {
                        this.c((this.a[3] & 0x6) * 2 + 16);
                        this.d((this.a[3] & 0x6) * 2 + 17);
                        this.e((this.a[3] & 0x6) * 2 + 18);
                        this.f((this.a[3] & 0x6) * 2 + 19);
                    }
                }
                else {
                    this.c((this.a[1] & 0x6) * 2 + 0);
                    this.d((this.a[1] & 0x6) * 2 + 1);
                    this.e((this.a[1] & 0x6) * 2 + 2);
                    this.f((this.a[1] & 0x6) * 2 + 3);
                }
                if ((this.a[1] & 0x10) != 0x0) {
                    this.a = 0;
                    this.a = false;
                    return;
                }
                this.a = true;
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (n == 0) {
            if (this.a) {
                this.a += 29781;
            }
            if (((this.a | 0x21FFFFFF) & 0x3E000000) == 0x3E000000) {
                return 3;
            }
        }
        return 0;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() << 8) + inputStream.read();
        this.a = (inputStream.read() == 255);
        this.b = (inputStream.read() << 8) + inputStream.read();
        this.c = (inputStream.read() & 0xFF);
        this.d = (inputStream.read() & 0xFF);
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = (inputStream.read() & 0xFF);
        }
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 12);
        outputStream.write(this.a() & 0xFF);
        outputStream.write((this.a & 0xFF00) >> 8);
        outputStream.write(this.a & 0xFF);
        outputStream.write(this.a ? 255 : 0);
        outputStream.write((this.b & 0xFF00) >> 8);
        outputStream.write(this.b & 0xFF);
        outputStream.write(this.c & 0xFF);
        outputStream.write(this.d & 0xFF);
        for (int i = 0; i < this.a.length; ++i) {
            outputStream.write(this.a[i] & 0xFF);
        }
    }
}
