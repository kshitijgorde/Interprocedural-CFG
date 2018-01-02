// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;

public class v extends X
{
    private byte[] j;
    private int i;
    private int g;
    private int h;
    
    public v(final byte[] j, final int i, final int g) throws IOException {
        this.j = j;
        this.i = i;
        this.g = g;
    }
    
    public v(final byte[] array) throws IOException {
        this(array, 0, array.length);
    }
    
    public boolean B() {
        return true;
    }
    
    public long G() {
        return this.h;
    }
    
    public void A(final long n) {
        this.h = (int)n;
    }
    
    public int read() {
        if (this.h < this.g + this.i) {
            return this.j[this.h++ + this.i] & 0xFF;
        }
        return -1;
    }
    
    public int read(final byte[] array, final int n, final int n2) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (n < 0 || n2 < 0 || n + n2 > array.length) {
            throw new IndexOutOfBoundsException();
        }
        if (n2 == 0) {
            return 0;
        }
        final int h = this.h;
        this.h = Math.min(this.h + n2, this.g + this.i);
        if (this.h == h) {
            return -1;
        }
        System.arraycopy(this.j, h, array, n, this.h - h);
        return this.h - h;
    }
    
    public int skipBytes(final int n) {
        final int h = this.h;
        this.h = Math.min(this.h + n, this.g + this.i);
        return this.h - h;
    }
    
    public void close() {
    }
    
    public long L() {
        return this.g;
    }
}
