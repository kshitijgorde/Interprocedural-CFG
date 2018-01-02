import java.io.ByteArrayInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class CircularInputStream extends ByteArrayInputStream
{
    protected boolean full;
    
    public CircularInputStream(final int len) {
        super(new byte[len]);
        this.count = 0;
        this.full = false;
    }
    
    public void add(final byte[] b, final int off, final int blen) {
        for (int i = 0; i < blen; ++i) {
            this.buf[this.count++] = b[i + off];
            if (this.count == this.buf.length) {
                this.count = 0;
            }
            if (this.count == this.pos) {
                this.full = true;
                return;
            }
        }
    }
    
    public int read(final byte[] b, final int off, final int len) {
        if (this.pos == this.count && !this.full) {
            return -1;
        }
        int k = this.count - this.pos;
        if (k < 1) {
            k += this.buf.length;
        }
        if (k > len) {
            k = len;
        }
        if (k > 0) {
            for (int i = 0; i < k; ++i) {
                b[off + i] = this.buf[this.pos++];
                if (this.pos == this.buf.length) {
                    this.pos = 0;
                }
            }
            this.full = false;
            return k;
        }
        return -1;
    }
    
    public int available() {
        final int k = this.count - this.pos;
        if (k < 0) {
            return k + this.buf.length;
        }
        if (k == 0 && this.full) {
            return this.buf.length;
        }
        return k;
    }
    
    public long skip(final long n) {
        long k = this.count - this.pos;
        if (k < 0L) {
            k += this.buf.length;
        }
        if (k > n) {
            k = n;
        }
        this.pos += (int)k;
        if (this.pos >= this.buf.length) {
            this.pos -= this.buf.length;
        }
        return k;
    }
}
