// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.InputStream;

final class K extends InputStream
{
    private static boolean c;
    private bC d;
    private cq e;
    boolean a;
    private boolean f;
    private byte[] g;
    private boolean h;
    private int i;
    private int j;
    int b;
    private byte[] k;
    
    K(final bC d, final cq e) {
        this.d = null;
        this.a = false;
        this.f = false;
        this.g = null;
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.b = 0;
        this.k = new byte[1];
        this.d = d;
        this.e = e;
    }
    
    public final synchronized int read() {
        if (this.read(this.k, 0, 1) == 1) {
            return this.k[0] & 0xFF;
        }
        return -1;
    }
    
    public final synchronized int read(final byte[] array, final int n, int n2) {
        if (this.a) {
            return -1;
        }
        final int n3 = this.j - this.i;
        if (this.g == null || (n3 == 0 && this.h)) {
            if (this.e.b.j != 1) {
                new StringBuffer().append("RspIS: Reading stream ").append(this.hashCode()).toString();
            }
            int n4;
            if (K.c && this.e.b.j != 1) {
                n4 = this.d.a(array, n, n2, this.e, 0);
            }
            else {
                n4 = this.d.a(array, n, n2, this.e, this.e.b.b);
            }
            if (n4 != -1 && this.e.b.l) {
                this.b += n4;
            }
            return n4;
        }
        if (n3 == 0) {
            return -1;
        }
        n2 = ((n2 > n3) ? n3 : n2);
        System.arraycopy(this.g, this.i, array, n, n2);
        this.i += n2;
        return n2;
    }
    
    public final synchronized long skip(long n) {
        if (this.a) {
            return 0L;
        }
        final int n2 = this.j - this.i;
        if (this.g != null && (n2 != 0 || !this.h)) {
            n = ((n > n2) ? n2 : n);
            this.i += (int)n;
            return n;
        }
        final long a = this.d.a(n, this.e);
        if (this.e.b.l) {
            this.b += (int)a;
        }
        return a;
    }
    
    public final synchronized int available() {
        if (this.a) {
            return 0;
        }
        if (this.g != null && (this.j - this.i != 0 || !this.h)) {
            return this.j - this.i;
        }
        return this.d.a(this.e);
    }
    
    public final synchronized void close() {
        if (!this.a) {
            this.a = true;
            if (this.f && (this.g == null || this.h)) {
                this.a(this.e.b.b);
            }
            new StringBuffer().append("RspIS: User closed stream ").append(this.hashCode()).toString();
            this.d.b();
            if (this.f) {
                try {
                    this.e.b.a.a(false);
                }
                catch (bw bw) {
                    throw new IOException(bw.toString());
                }
            }
        }
    }
    
    protected final void finalize() {
        try {
            this.close();
        }
        finally {
            super.finalize();
        }
    }
    
    final void a(final int b) {
        new StringBuffer().append("RspIS: Read-all on stream ").append(this.hashCode()).toString();
        synchronized (this.e.b) {
            if (!this.e.b.l) {
                final int b2 = this.e.b.b;
                this.e.b.b = b;
                this.e.b.a();
                this.e.b.b = b2;
            }
        }
        synchronized (this) {
            if (this.g != null && !this.h) {
                return;
            }
            int a = 0;
            try {
                if (this.a) {
                    this.g = new byte[10000];
                    do {
                        this.b += a;
                    } while ((a = this.d.a(this.g, 0, this.g.length, this.e, b)) != -1);
                    this.g = null;
                }
                else {
                    if (this.g == null) {
                        this.g = new byte[10000];
                        this.i = 0;
                        this.j = 0;
                    }
                    int a2;
                    while ((a2 = this.d.a(this.g, this.j, this.g.length - this.j, this.e, b)) >= 0) {
                        this.b += a2;
                        this.j += a2;
                        this.g = bz.a(this.g, this.j + 10000);
                    }
                }
            }
            catch (InterruptedIOException ex) {
                this.h = true;
                throw ex;
            }
            catch (IOException ex2) {
                this.g = null;
            }
            this.h = false;
        }
    }
    
    final synchronized void a() {
        this.f = true;
    }
    
    static {
        K.c = false;
        try {
            K.c = Boolean.getBoolean("HTTPClient.dontTimeoutRespBody");
        }
        catch (Exception ex) {}
    }
}
