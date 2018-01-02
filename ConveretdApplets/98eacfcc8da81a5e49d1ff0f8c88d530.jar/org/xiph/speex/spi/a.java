// 
// Decompiled by Procyon v0.5.30
// 

package org.xiph.speex.spi;

import javax.sound.sampled.AudioFormat;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;

public abstract class a extends AudioInputStream
{
    protected InputStream a;
    protected byte[] b;
    protected int c;
    protected int d;
    protected int e;
    private int i;
    private final byte[] j;
    protected byte[] f;
    protected int g;
    protected int h;
    
    protected final void a() {
        if (this.a == null) {
            throw new IOException("Stream closed");
        }
    }
    
    public a(final InputStream inputStream, final AudioFormat audioFormat, final long n, final int n2) {
        this(inputStream, audioFormat, n, n2, n2);
    }
    
    private a(final InputStream a, final AudioFormat audioFormat, final long n, final int i, final int n2) {
        super(a, audioFormat, n);
        this.j = new byte[1];
        this.a = a;
        if (i <= 0 || n2 <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        this.b = new byte[i];
        this.c = 0;
        this.f = new byte[n2];
        this.g = 0;
        this.i = i;
        this.e = -1;
    }
    
    protected void b() {
        this.c();
        int read;
        while ((read = this.a.read(this.f, this.g, this.f.length - this.g)) >= 0) {
            if (read > 0) {
                this.g += read;
                break;
            }
        }
    }
    
    protected final void c() {
        if (this.e < 0) {
            this.d = 0;
        }
        else if (this.d >= this.b.length) {
            if (this.e > 0) {
                final int d = this.d - this.e;
                System.arraycopy(this.b, this.e, this.b, 0, d);
                this.d = d;
                this.e = 0;
            }
            else if (this.b.length >= this.i) {
                this.e = -1;
                this.d = 0;
            }
            else {
                int i;
                if ((i = this.d << 1) > this.i) {
                    i = this.i;
                }
                final byte[] b = new byte[i];
                System.arraycopy(this.b, 0, b, 0, this.d);
                this.b = b;
            }
        }
        this.c = this.d;
    }
    
    public synchronized int read() {
        if (this.read(this.j, 0, 1) == -1) {
            return -1;
        }
        return this.j[0] & 0xFF;
    }
    
    public synchronized int read(final byte[] array, final int n, int n2) {
        this.a();
        if (n < 0 || n > array.length || n2 < 0 || n + n2 > array.length || n + n2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (n2 == 0) {
            return 0;
        }
        int n3;
        if ((n3 = this.c - this.d) <= 0) {
            this.b();
            if ((n3 = this.c - this.d) <= 0) {
                return -1;
            }
        }
        n2 = ((n3 < n2) ? n3 : n2);
        System.arraycopy(this.b, this.d, array, n, n2);
        this.d += n2;
        return n2;
    }
    
    public synchronized long skip(final long n) {
        this.a();
        if (n <= 0L) {
            return 0L;
        }
        if (this.d < this.c) {
            final int n2;
            if ((n2 = this.c - this.d) > n) {
                this.d += (int)n;
                return n;
            }
            this.d = this.c;
            return n2;
        }
        else {
            this.b();
            final int n3;
            if ((n3 = this.c - this.d) <= 0) {
                return 0L;
            }
            final long n4 = (n3 < n) ? n3 : n;
            this.d += (int)n4;
            return n4;
        }
    }
    
    public synchronized int available() {
        this.a();
        return this.c - this.d;
    }
    
    public synchronized void mark(final int i) {
        if (i > this.b.length - this.d) {
            byte[] b;
            if (i <= this.b.length) {
                b = this.b;
            }
            else {
                b = new byte[i];
            }
            System.arraycopy(this.b, this.d, b, 0, this.c - this.d);
            this.b = b;
            this.c -= this.d;
            final boolean b2 = false;
            this.e = (b2 ? 1 : 0);
            this.d = (b2 ? 1 : 0);
        }
        else {
            this.e = this.d;
        }
        this.i = i;
    }
    
    public synchronized void reset() {
        this.a();
        if (this.e < 0) {
            throw new IOException("Attempt to reset when no mark is valid");
        }
        this.d = this.e;
    }
    
    public boolean markSupported() {
        return true;
    }
    
    public synchronized void close() {
        if (this.a == null) {
            return;
        }
        this.a.close();
        this.a = null;
        this.b = null;
        this.f = null;
    }
}
