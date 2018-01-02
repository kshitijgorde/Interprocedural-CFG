import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ar extends at implements Serializable
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    
    public final int a() {
        return 10;
    }
    
    public final void a(final ap a) {
        super.a = a;
        super.a.a.e = true;
        this.a();
    }
    
    public final void a() {
        this.c = 0;
        this.d = 4;
        this.e = 0;
        this.f = 0;
        final int b = this.b();
        this.a(0, 1, b - 2, b - 1);
        this.a = 254;
        this.b = 254;
        super.a.a.b(0, this.d * 4096 + 0);
        super.a.a.b(1, this.d * 4096 + 1024);
        super.a.a.b(2, this.d * 4096 + 2048);
        super.a.a.b(3, this.d * 4096 + 3072);
        super.a.a.b(4, this.f * 4096 + 0);
        super.a.a.b(5, this.f * 4096 + 1024);
        super.a.a.b(6, this.f * 4096 + 2048);
        super.a.a.b(7, this.f * 4096 + 3072);
    }
    
    public final void a(int n, int n2) {
        if (n < 32768) {
            return;
        }
        n2 &= 0xFF;
        switch (n &= 0xF000) {
            case 40960: {
                this.c(n2 * 2);
                this.d(n2 * 2 + 1);
            }
            case 45056: {
                this.c = n2;
                if (this.a == 253) {
                    super.a.a.b(0, n2 * 4096 + 0);
                    super.a.a.b(1, n2 * 4096 + 1024);
                    super.a.a.b(2, n2 * 4096 + 2048);
                    super.a.a.b(3, n2 * 4096 + 3072);
                }
            }
            case 49152: {
                this.d = n2;
                if (this.a == 254) {
                    super.a.a.b(0, n2 * 4096 + 0);
                    super.a.a.b(1, n2 * 4096 + 1024);
                    super.a.a.b(2, n2 * 4096 + 2048);
                    super.a.a.b(3, n2 * 4096 + 3072);
                }
            }
            case 53248: {
                this.e = n2;
                if (this.b == 253) {
                    super.a.a.b(4, n2 * 4096 + 0);
                    super.a.a.b(5, n2 * 4096 + 1024);
                    super.a.a.b(6, n2 * 4096 + 2048);
                    super.a.a.b(7, n2 * 4096 + 3072);
                }
            }
            case 57344: {
                this.f = n2;
                if (this.b == 254) {
                    super.a.a.b(4, n2 * 4096 + 0);
                    super.a.a.b(5, n2 * 4096 + 1024);
                    super.a.a.b(6, n2 * 4096 + 2048);
                    super.a.a.b(7, n2 * 4096 + 3072);
                }
            }
            case 61440: {
                if ((n2 & 0x1) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
            }
            default: {}
        }
    }
    
    public final void a(final int n) {
        if ((n & 0x1FF0) == 0xFD0 && this.a != 253) {
            super.a.a.b(0, this.c * 4096 + 0);
            super.a.a.b(1, this.c * 4096 + 1024);
            super.a.a.b(2, this.c * 4096 + 2048);
            super.a.a.b(3, this.c * 4096 + 3072);
            this.a = 253;
            return;
        }
        if ((n & 0x1FF0) == 0xFE0 && this.a != 254) {
            super.a.a.b(0, this.d * 4096 + 0);
            super.a.a.b(1, this.d * 4096 + 1024);
            super.a.a.b(2, this.d * 4096 + 2048);
            super.a.a.b(3, this.d * 4096 + 3072);
            this.a = 254;
            return;
        }
        if ((n & 0x1FF0) == 0x1FD0 && this.b != 253) {
            super.a.a.b(4, this.e * 4096 + 0);
            super.a.a.b(5, this.e * 4096 + 1024);
            super.a.a.b(6, this.e * 4096 + 2048);
            super.a.a.b(7, this.e * 4096 + 3072);
            this.b = 253;
            return;
        }
        if ((n & 0x1FF0) == 0x1FE0 && this.b != 254) {
            super.a.a.b(4, this.f * 4096 + 0);
            super.a.a.b(5, this.f * 4096 + 1024);
            super.a.a.b(6, this.f * 4096 + 2048);
            super.a.a.b(7, this.f * 4096 + 3072);
            this.b = 254;
        }
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() & 0xFF);
        this.b = (inputStream.read() & 0xFF);
        this.c = (inputStream.read() & 0xFF);
        this.d = (inputStream.read() & 0xFF);
        this.e = (inputStream.read() & 0xFF);
        this.f = (inputStream.read() & 0xFF);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 7);
        outputStream.write(this.a() & 0xFF);
        outputStream.write(this.a & 0xFF);
        outputStream.write(this.b & 0xFF);
        outputStream.write(this.c & 0xFF);
        outputStream.write(this.d & 0xFF);
        outputStream.write(this.e & 0xFF);
        outputStream.write(this.f & 0xFF);
    }
}
