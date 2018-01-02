// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.InputStream;

public final class bs extends InputStream
{
    private byte[] a;
    private int a;
    private int b;
    
    public bs(final byte[] a) {
        this.a = a;
        this.b = a.length;
        this.a = 0;
    }
    
    public final int read(final byte[] array, final int n, int n2) {
        final int n3 = (this.b >= 0) ? this.b : this.a.length;
        if ((n2 = ((n2 + this.a > n3) ? (n3 - this.a) : n2)) < 1) {
            return -1;
        }
        System.arraycopy(this.a, this.a, array, n, n2);
        this.a += n2;
        return n2;
    }
    
    public final int read() {
        if (this.a >= this.a.length || (this.b >= 0 && this.a >= this.b)) {
            return -1;
        }
        final int n = this.a[this.a] & 0xFF;
        ++this.a;
        return n;
    }
}
