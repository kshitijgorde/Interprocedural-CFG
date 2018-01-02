import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class c extends BufferedInputStream
{
    protected f a;
    protected int b;
    
    c(final InputStream inputStream, final long n) {
        super(inputStream);
        this.b = 0;
        this.buf = new byte[0];
        this.a = new f(n);
    }
    
    public synchronized int available() throws IOException {
        return this.in.available() + this.count - this.pos;
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) throws IOException {
        final int n3 = (this.pos + n2 + 7 & 0xFFFFFFF8) - this.count;
        if (n3 + this.count > this.buf.length) {
            final byte[] buf = new byte[n3 + this.count];
            System.arraycopy(this.buf, 0, buf, 0, this.buf.length);
            this.buf = buf;
        }
        final int read = this.in.read(this.buf, this.count, n3);
        if (read == -1) {
            return -1;
        }
        this.count += read;
        final int b = this.count & 0xFFFFFFF8;
        this.a.a(this.buf, this.b, b - this.b);
        this.b = b;
        final int min = Math.min(this.b - this.pos, n2);
        System.arraycopy(this.buf, this.pos, array, n, min);
        this.pos += min;
        return min;
    }
}
