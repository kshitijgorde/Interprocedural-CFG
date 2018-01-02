// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.EOFException;
import java.io.InputStream;

public final class aY
{
    private byte[] a;
    private int b;
    private int c;
    private int d;
    private InputStream e;
    
    public aY(final byte[] array) {
        this(array, 0, array.length, 0);
    }
    
    public aY(final byte[] a, final int n, final int c, final int d) {
        this.a = a;
        this.b = 0;
        this.c = c;
        this.d = d;
        this.e = null;
    }
    
    public aY(final InputStream e, final int c, final int n) {
        this.a = null;
        this.b = 0;
        this.c = c;
        this.d = -1;
        this.e = e;
    }
    
    public final byte[] a() {
        if (this.a == null && this.e != null) {
            this.a = a(this.e, this.c);
        }
        return this.a;
    }
    
    public final byte[] b() {
        if (this.a == null && this.e != null) {
            this.a = a(this.e, this.c);
        }
        if (this.b == 0 && this.a.length == this.c) {
            return this.a;
        }
        final byte[] array = new byte[this.c];
        System.arraycopy(this.a, this.b, array, 0, this.c);
        return array;
    }
    
    public final int c() {
        return this.c;
    }
    
    public final int d() {
        return this.b;
    }
    
    public final int e() {
        return this.d;
    }
    
    public final boolean f() {
        return this.a != null || (this.e != null && this.e.available() >= this.c);
    }
    
    private static final byte[] a(final InputStream inputStream, final int n) {
        final byte[] array = new byte[n];
        int read2;
        for (int read = read2 = inputStream.read(array, 0, n); read2 < n && read > 0; read = inputStream.read(array, read2, n - read2), read2 += read) {}
        if (read2 < n) {
            throw new EOFException("InputStream ended before the packet was fully read");
        }
        return array;
    }
}
