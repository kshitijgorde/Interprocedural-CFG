import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class EncryptedInputStream extends BufferedInputStream
{
    protected Decrypt des;
    protected int decPos;
    
    EncryptedInputStream(final InputStream in, final long key) {
        super(in);
        this.decPos = 0;
        this.buf = new byte[0];
        this.des = new Decrypt(key);
    }
    
    public synchronized int available() throws IOException {
        return this.in.available() + this.count - this.pos;
    }
    
    public synchronized int read(final byte[] b, final int off, final int len) throws IOException {
        final int toread = (this.pos + len + 7 & 0xFFFFFFF8) - this.count;
        if (toread + this.count > this.buf.length) {
            final byte[] tmp = new byte[toread + this.count];
            System.arraycopy(this.buf, 0, tmp, 0, this.buf.length);
            this.buf = tmp;
        }
        int read = this.in.read(this.buf, this.count, toread);
        if (read == -1) {
            return -1;
        }
        this.count += read;
        final int newpos = this.count & 0xFFFFFFF8;
        this.des.decrypt(this.buf, this.decPos, newpos - this.decPos);
        this.decPos = newpos;
        read = Math.min(this.decPos - this.pos, len);
        System.arraycopy(this.buf, this.pos, b, off, read);
        this.pos += read;
        return read;
    }
}
