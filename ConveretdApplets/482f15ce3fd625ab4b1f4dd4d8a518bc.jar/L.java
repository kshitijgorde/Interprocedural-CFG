import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class L extends at
{
    private boolean a;
    private int a;
    
    public L() {
        this.a = false;
        this.a = 0;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        switch (n & 0xE000) {
            case 32768: {
                this.a = false;
                this.a = 0;
            }
            case 40960: {
                this.a = true;
                this.a = 37;
            }
            case 49152: {}
            case 57344: {
                this.e(n2 & 0x7);
                break;
            }
        }
    }
    
    public final int a() {
        return 40;
    }
    
    public final void a() {
        this.a = false;
        this.a = 0;
        for (int i = 0; i < 8192; ++i) {
            super.a.c[i] = super.a.b[49152 + i];
        }
        this.a(4, 5, 0, 7);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
    }
    
    public final int a(final int n) {
        if (this.a && this.a-- <= 0) {
            return 3;
        }
        return 0;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a = (inputStream.read() == 255);
        this.a = (inputStream.read() & 0xFF);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 3);
        outputStream.write(this.a() & 0xFF);
        outputStream.write(this.a ? 255 : 0);
        outputStream.write(this.a & 0xFF);
    }
}
