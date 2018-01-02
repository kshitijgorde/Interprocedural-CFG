import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class d extends BufferedInputStream
{
    protected h a;
    protected int b;
    
    d(final InputStream inputStream, final long n) {
        super(inputStream);
        this.b = 0;
        super.buf = new byte[0];
        this.a = new h(n);
    }
    
    public synchronized int read() throws IOException {
        throw new IOException();
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) throws IOException {
        final int n3 = (super.pos + n2 + 7 & 0xFFFFFFF8) - super.count;
        if (n3 + super.count > super.buf.length) {
            final byte[] buf = new byte[n3 + super.count];
            System.arraycopy(super.buf, 0, buf, 0, super.buf.length);
            super.buf = buf;
        }
        final int read = super.in.read(super.buf, super.count, n3);
        if (read == -1) {
            return -1;
        }
        super.count += read;
        final int b = super.count & 0xFFFFFFF8;
        this.a.a(super.buf, this.b, b - this.b);
        this.b = b;
        final int min = Math.min(this.b - super.pos, n2);
        System.arraycopy(super.buf, super.pos, array, n, min);
        super.pos += min;
        return min;
    }
    
    public synchronized int available() throws IOException {
        return super.in.available() + super.count - super.pos;
    }
}
