// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;
import java.io.InputStream;

public class n extends X
{
    private InputStream X;
    long W;
    long B;
    
    public n(final InputStream x) {
        this.W = 0L;
        this.B = -1L;
        this.X = x;
    }
    
    public final int read() throws IOException {
        final int read = this.X.read();
        if (read != -1) {
            ++this.W;
        }
        return read;
    }
    
    public final int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = this.X.read(array, n, n2);
        if (read != -1) {
            this.W += read;
        }
        return read;
    }
    
    public final long skip(final long n) throws IOException {
        final long skip = this.X.skip(n);
        this.W += skip;
        return skip;
    }
    
    public final int available() throws IOException {
        return this.X.available();
    }
    
    public final void close() throws IOException {
        this.X.close();
    }
    
    public final synchronized void mark(final int n) {
        this.B = this.W;
        this.X.mark(n);
    }
    
    public final synchronized void reset() throws IOException {
        if (this.B != -1L) {
            this.W = this.B;
        }
        this.X.reset();
    }
    
    public boolean markSupported() {
        return this.X.markSupported();
    }
    
    public final boolean B() {
        return false;
    }
    
    public final long G() {
        return this.W;
    }
    
    public final void A(final long n) throws IOException {
        while (n - this.W > 0L) {
            this.W += this.X.skip(n - this.W);
        }
    }
}
