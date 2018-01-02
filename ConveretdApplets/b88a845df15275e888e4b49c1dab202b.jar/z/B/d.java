// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;
import java.util.Vector;
import java.io.InputStream;

public final class d extends X
{
    private InputStream N;
    private long O;
    private static final int U = 9;
    private static final int P = 512;
    private static final int Q = 511;
    private Vector T;
    int V;
    int S;
    boolean R;
    
    public d(final InputStream n) {
        this.O = 0L;
        this.T = new Vector();
        this.V = 0;
        this.S = 0;
        this.R = false;
        this.N = n;
    }
    
    private long C(final long n) throws IOException {
        if (n < this.S) {
            return n;
        }
        if (this.R) {
            return this.S;
        }
        for (int n2 = (int)(n >> 9), i = this.S >> 9; i <= n2; ++i) {
            final byte[] array = new byte[512];
            this.T.addElement(array);
            int j = 512;
            int n3 = 0;
            while (j > 0) {
                final int read = this.N.read(array, n3, j);
                if (read == -1) {
                    this.R = true;
                    return this.S;
                }
                n3 += read;
                j -= read;
                this.S += read;
            }
        }
        return this.S;
    }
    
    public boolean B() {
        return true;
    }
    
    public long G() {
        return this.O;
    }
    
    public void A(final long o) throws IOException {
        if (o < 0L) {
            throw new IOException(m.A("MemoryCacheSeekableStream0"));
        }
        this.O = o;
    }
    
    public int read() throws IOException {
        final long n = this.O + 1L;
        if (this.C(n) >= n) {
            return ((byte[])this.T.elementAt((int)(this.O >> 9)))[(int)(this.O++ & 0x1FFL)] & 0xFF;
        }
        return -1;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        if (array == null) {
            throw new NullPointerException();
        }
        if (n < 0 || n2 < 0 || n + n2 > array.length) {
            throw new IndexOutOfBoundsException();
        }
        if (n2 == 0) {
            return 0;
        }
        if (this.C(this.O + n2) <= this.O) {
            return -1;
        }
        final byte[] array2 = this.T.elementAt((int)(this.O >> 9));
        final int min = Math.min(n2, 512 - (int)(this.O & 0x1FFL));
        System.arraycopy(array2, (int)(this.O & 0x1FFL), array, n, min);
        this.O += min;
        return min;
    }
}
