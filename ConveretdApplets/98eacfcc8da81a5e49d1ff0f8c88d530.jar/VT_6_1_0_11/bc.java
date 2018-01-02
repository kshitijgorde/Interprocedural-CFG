// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

final class bc extends FilterInputStream
{
    private byte[] a;
    private int b;
    private int c;
    private int d;
    private int e;
    
    bc(final InputStream inputStream) {
        super(inputStream);
        this.a = new byte[2000];
        this.b = 0;
        this.c = 0;
        this.d = -1;
        this.e = 1500;
    }
    
    public final int read() {
        if (this.b >= this.c) {
            this.b();
        }
        if (this.c > this.b) {
            return this.a[this.b++] & 0xFF;
        }
        return -1;
    }
    
    public final int read(final byte[] array, final int n, int n2) {
        if (n2 <= 0) {
            return 0;
        }
        if (this.b >= this.c && n2 >= this.e && this.d < 0) {
            return this.in.read(array, n, n2);
        }
        if (this.b >= this.c) {
            this.b();
        }
        if (this.b >= this.c) {
            return -1;
        }
        final int n3 = this.c - this.b;
        if (n2 > n3) {
            n2 = n3;
        }
        System.arraycopy(this.a, this.b, array, n, n2);
        this.b += n2;
        return n2;
    }
    
    public final long skip(final long n) {
        if (n <= 0L) {
            return 0L;
        }
        final int n2 = this.c - this.b;
        if (n <= n2) {
            this.b += (int)n;
            return n;
        }
        this.b = this.c;
        return n2 + this.in.skip(n - n2);
    }
    
    private final void b() {
        if (this.d > 0) {
            if (this.c >= this.a.length) {
                System.arraycopy(this.a, this.d, this.a, 0, this.c - this.d);
                this.b = this.c - this.d;
            }
        }
        else if (this.d != 0 || this.c >= this.a.length) {
            this.b = 0;
        }
        this.c = this.b;
        final int read;
        if ((read = this.in.read(this.a, this.b, this.a.length - this.b)) > 0) {
            this.c = this.b + read;
        }
    }
    
    public final int available() {
        int n;
        if ((n = this.c - this.b) == 0) {
            return this.in.available();
        }
        try {
            n += this.in.available();
        }
        catch (IOException ex) {}
        return n;
    }
    
    final void a() {
        this.d = this.b;
    }
    
    final int a(final byte[] array, final int[] array2) {
        int a;
        if ((a = bz.a(array, array2, this.a, this.d, this.b)) == -1) {
            this.d = ((this.b > array.length) ? (this.b - array.length) : 0);
        }
        else {
            final int b = a + array.length;
            a = this.b - b;
            this.b = b;
            this.d = -1;
        }
        return a;
    }
}
