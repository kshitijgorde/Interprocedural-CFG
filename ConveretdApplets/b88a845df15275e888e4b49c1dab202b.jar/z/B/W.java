// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class W extends X
{
    private RandomAccessFile H;
    private long M;
    private static final int L = 9;
    private static final int F = 512;
    private static final int G = 511;
    private static final int I = 32;
    private static final int D = 512;
    private byte[][] J;
    private int[] K;
    private long E;
    private long C;
    
    public W(final RandomAccessFile h) throws IOException {
        this.M = -1L;
        this.J = new byte[512][32];
        this.K = new int[32];
        this.E = 0L;
        this.C = 0L;
        (this.H = h).seek(0L);
        this.E = h.length();
        for (int i = 0; i < 32; ++i) {
            this.J[i] = new byte[512];
            this.K[i] = -1;
        }
    }
    
    public W(final File file) throws IOException {
        this(new RandomAccessFile(file, "r"));
    }
    
    public W(final String s) throws IOException {
        this(new RandomAccessFile(s, "r"));
    }
    
    public final boolean B() {
        return true;
    }
    
    public final long G() throws IOException {
        return this.C;
    }
    
    public final void A(final long c) throws IOException {
        if (c < 0L) {
            throw new IOException(m.A("FileSeekableStream0"));
        }
        this.C = c;
    }
    
    public final int A(final int n) throws IOException {
        this.C += n;
        return n;
    }
    
    private byte[] B(final long n) throws IOException {
        final int n2 = (int)(n >> 9);
        for (int i = 0; i < 32; ++i) {
            if (this.K[i] == n2) {
                return this.J[i];
            }
        }
        final int n3 = (int)(Math.random() * 32.0);
        this.K[n3] = n2;
        final long n4 = n2 << 9;
        final long n5 = this.E - n4;
        final int n6 = (512L < n5) ? 512 : ((int)n5);
        this.H.seek(n4);
        this.H.readFully(this.J[n3], 0, n6);
        return this.J[n3];
    }
    
    public final int read() throws IOException {
        if (this.C >= this.E) {
            return -1;
        }
        return this.B(this.C)[(int)(this.C++ & 0x1FFL)] & 0xFF;
    }
    
    public final int read(final byte[] array, final int n, int n2) throws IOException {
        if (array == null) {
            throw new NullPointerException();
        }
        if (n < 0 || n2 < 0 || n + n2 > array.length) {
            throw new IndexOutOfBoundsException();
        }
        if (n2 == 0) {
            return 0;
        }
        n2 = (int)Math.min(n2, this.E - this.C);
        if (n2 <= 0) {
            return -1;
        }
        if (n2 > 512) {
            this.H.seek(this.C);
            final int read = this.H.read(array, n, n2);
            this.C += read;
            return read;
        }
        final byte[] b = this.B(this.C);
        final int n3 = 512 - (int)(this.C & 0x1FFL);
        final int n4 = (n2 < n3) ? n2 : n3;
        System.arraycopy(b, (int)(this.C & 0x1FFL), array, n, n4);
        this.C += n4;
        return n4;
    }
    
    public final void close() throws IOException {
        this.H.close();
    }
    
    public final synchronized void mark(final int n) {
        this.M = this.C;
    }
    
    public final synchronized void reset() throws IOException {
        if (this.M != -1L) {
            this.C = this.M;
        }
    }
    
    public boolean markSupported() {
        return true;
    }
}
