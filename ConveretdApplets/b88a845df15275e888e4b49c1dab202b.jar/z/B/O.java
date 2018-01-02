// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;

public class O extends X
{
    private X o;
    private e n;
    private long m;
    private boolean l;
    private Q k;
    
    public O(final X o, final e n, final boolean l) {
        this.m = 0L;
        this.k = new Q();
        this.o = o;
        this.n = n;
        this.l = l;
        if (l && !o.B()) {
            throw new IllegalArgumentException(z.B.m.A("SegmentedSeekableStream0"));
        }
    }
    
    public O(final X x, final long[] array, final int[] array2, final boolean b) {
        this(x, new D(array, array2), b);
    }
    
    public O(final X x, final long[] array, final int n, final int n2, final boolean b) {
        this(x, new E(array, n, n2), b);
    }
    
    public long G() {
        return this.m;
    }
    
    public boolean B() {
        return this.l;
    }
    
    public void A(final long m) throws IOException {
        if (m < 0L) {
            throw new IOException();
        }
        this.m = m;
    }
    
    public int read() throws IOException {
        this.n.A(this.m, 1, this.k);
        this.o.A(this.k.B());
        final int read = this.o.read();
        ++this.m;
        return read;
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
        this.n.A(this.m, n2, this.k);
        this.o.A(this.k.B());
        final int read = this.o.read(array, n, this.k.A());
        this.m += read;
        return read;
    }
}
