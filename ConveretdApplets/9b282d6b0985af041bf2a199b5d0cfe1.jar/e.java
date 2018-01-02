import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class e extends FilterInputStream
{
    protected int a;
    protected int b;
    protected bq c;
    
    void a(final int b) {
        this.b = b;
    }
    
    e(final bq c, final InputStream inputStream) {
        super(inputStream);
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.c = c;
    }
    
    public int read() throws IOException {
        final int read = super.read();
        if (read != -1) {
            ++this.a;
        }
        return read;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = super.read(array, n, n2);
        if (read != -1) {
            this.a += read;
            if (this.b != 0) {
                this.c.a(new f(3, this.a / this.b));
            }
        }
        return read;
    }
}
