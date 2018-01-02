// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;

public final class bR extends InputStream implements Cloneable
{
    private byte[] a;
    private int b;
    private boolean c;
    private int d;
    private int e;
    private boolean f;
    
    private bR() {
        this.f = false;
        this.c = false;
        this.b = 0;
        this.d = 0;
        this.e = -1;
    }
    
    public bR(final int n) {
        this();
        this.a = new byte[n];
    }
    
    private bR(final byte[] a) {
        this();
        this.a = a;
    }
    
    public final synchronized void a(final byte[] array) {
        this.a(array, 0, array.length);
    }
    
    private synchronized void a(final byte[] array, final int n, final int n2) {
        this.b(array, 0, n2);
        this.notifyAll();
    }
    
    private synchronized void b(final byte[] array, final int n, final int i) {
        if (i <= 0) {
            return;
        }
        if (this.e != 0 && i > this.a.length - this.b) {
            int n2;
            if (this.e != -1) {
                n2 = this.e;
            }
            else {
                n2 = this.d;
            }
            if (n2 != 0) {
                System.err.println("moveBytes in ByteArrayStream");
                System.arraycopy(this.a, n2, this.a, 0, this.b - n2);
                this.b -= n2;
                this.d -= n2;
                if (this.e != -1) {
                    this.e = 0;
                }
            }
        }
        while (i > this.a.length - this.b) {
            System.err.println("resize array in ByteArrayStream");
            final byte[] a = new byte[Math.max(2 * this.a.length, this.a.length + i * 2)];
            System.arraycopy(this.a, 0, a, 0, this.a.length);
            this.a = a;
        }
        System.arraycopy(array, n, this.a, this.b, i);
        this.b += i;
    }
    
    public final synchronized int read(final byte[] array, final int n, int i) {
        if (this.f) {
            throw new EOFException("Stream has been closed");
        }
        if (i < 0) {
            throw new IOException("Can not read " + i + " bytes!!!");
        }
        if (i == 0) {
            if (this.c && this.d == this.b) {
                return -1;
            }
            return 0;
        }
        else {
            if (this.f) {
                throw new EOFException("Stream has been closed");
            }
            while (i > this.b - this.d) {
                if (this.c) {
                    if ((i = this.b - this.d) > 0) {
                        System.arraycopy(this.a, this.d, array, n, i);
                        this.d += i;
                        return i;
                    }
                    return -1;
                }
                else {
                    if (this.b > this.d) {
                        i = this.b - this.d;
                        System.arraycopy(this.a, this.d, array, n, i);
                        this.d += i;
                        return i;
                    }
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        throw new IOException("read interrupted while buffering");
                    }
                }
            }
            System.arraycopy(this.a, this.d, array, n, i);
            this.d += i;
            return i;
        }
    }
    
    public final synchronized int read() {
        while (!this.f) {
            if (this.c && this.d == this.b) {
                return -1;
            }
            if (this.d < this.b) {
                return this.a[this.d++];
            }
            try {
                this.wait();
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new IOException("read interrupted while buffering");
            }
        }
        throw new EOFException("Stream has been closed");
    }
    
    public final synchronized int available() {
        return this.b - this.d;
    }
    
    public final boolean markSupported() {
        return true;
    }
    
    public final synchronized void mark(final int n) {
        this.e = this.d;
        this.notifyAll();
    }
    
    public final synchronized void reset() {
        if (this.e == -1) {
            throw new IOException("Reset called and no mark has been set");
        }
        this.d = this.e;
        this.notifyAll();
    }
    
    public final synchronized long skip(long min) {
        final long n = this.d;
        min = Math.min(min, this.b - this.d);
        this.d += (int)min;
        this.notifyAll();
        return this.d - n;
    }
    
    public final synchronized void a() {
        this.c = true;
        this.notifyAll();
    }
    
    public final synchronized void close() {
        this.f = true;
        this.notifyAll();
    }
    
    public final synchronized Object clone() {
        final byte[] array = new byte[this.a.length];
        System.arraycopy(this.a, 0, array, 0, this.a.length);
        final bR br;
        (br = new bR(array)).d = this.d;
        br.b = this.b;
        br.c = this.c;
        br.f = this.f;
        br.e = this.e;
        return br;
    }
}
