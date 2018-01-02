import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class t extends at
{
    public int a;
    
    public t() {
        this.a = 0;
    }
    
    public final int a() {
        return 3;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int a) {
        if (n < 32768) {
            return;
        }
        this.a = a;
        final int n2 = (a & (this.c() >> 1) - 1) << 3;
        this.a(n2 + 0, n2 + 1, n2 + 2, n2 + 3, n2 + 4, n2 + 5, n2 + 6, n2 + 7);
    }
    
    public final void a() {
        if (this.b() > 2) {
            this.a(0, 1, 2, 3);
        }
        else {
            this.a(0, 1, 0, 1);
        }
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        this.a(32768, this.a = (inputStream.read() & 0xFF));
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MAP".getBytes());
        aK.a(outputStream, 2);
        outputStream.write(this.a() & 0xFF);
        outputStream.write(this.a & 0xFF);
    }
}
