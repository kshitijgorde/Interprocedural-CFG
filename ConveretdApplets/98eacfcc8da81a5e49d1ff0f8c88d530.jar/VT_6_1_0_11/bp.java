// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.StreamCorruptedException;
import java.io.InputStream;
import java.io.IOException;
import java.io.FilterInputStream;

public final class bp extends FilterInputStream implements ch
{
    private static int a;
    private byte[] b;
    private int c;
    private int d;
    private int e;
    private int f;
    private byte[] g;
    private int h;
    private int i;
    
    private void a() {
        if (this.in == null) {
            throw new IOException("Stream closed");
        }
    }
    
    public bp(final InputStream inputStream, final int n, final boolean b) {
        super(inputStream);
        this.e = -1;
        if (n <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        if (n <= 65) {
            throw new IllegalArgumentException("Buffer size not big enough for GSM wav blocks");
        }
        this.b = new byte[n];
        Label_0155: {
            if (b) {
                try {
                    final dF df = new dF(inputStream);
                    new bO(1, 42, df.b().c(), df.b().d(), df.c().b() / 65 * 66, -3, 33, (df.b().c() * df.b().d() << 3) * 33 / 160);
                    break Label_0155;
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException("InputStream must contain GSM wav data");
                }
            }
            new bO(1, 42, -1, -1, -1, -3, 33, -1);
        }
        this.c = 0;
        this.g = new byte[this.b.length];
    }
    
    private void b() {
        this.c();
        int read;
        while ((read = this.in.read(this.g, this.h, this.g.length - this.h)) >= 0) {
            if (read > 0) {
                this.h += read;
                int n;
                if ((n = (this.h - this.i) / 65) > 0) {
                    if (this.b.length - this.c < n * 66) {
                        final byte[] b = new byte[this.b.length << 1];
                        System.arraycopy(this.b, 0, b, 0, this.c);
                        this.b = b;
                    }
                    while (n-- > 0 && this.b.length - this.c >= 65) {
                        VT_6_1_0_11.c.a(this.g, this.i, this.b, this.c, this.b, this.c + 33);
                        this.i += 65;
                        this.c += 66;
                    }
                    System.arraycopy(this.g, this.i, this.g, 0, this.h - this.i);
                    this.h -= this.i;
                    this.i = 0;
                    return;
                }
                continue;
            }
            else {
                if (this.h < this.g.length) {
                    return;
                }
                if (this.i <= 0) {
                    return;
                }
                System.arraycopy(this.g, this.i, this.g, 0, this.h - this.i);
                this.h -= this.i;
                this.i = 0;
            }
        }
        if ((this.h - this.i) % 65 != 0 && (this.h - this.i) % 65 != 1) {
            throw new StreamCorruptedException("Incompleted GSM wav block when stream ended");
        }
        int n2 = (this.h - this.i) / 65;
        if (this.b.length - this.c < n2 * 66) {
            final byte[] b2 = new byte[this.b.length << 1];
            System.arraycopy(this.b, 0, b2, 0, this.c);
            this.b = b2;
        }
        while (n2 > 0 && this.b.length - this.c >= 66) {
            VT_6_1_0_11.c.a(this.g, this.i, this.b, this.c, this.b, this.c + 33);
            this.i += 65;
            this.c += 66;
            --n2;
        }
    }
    
    private void c() {
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
            else if (this.b.length >= this.f) {
                this.e = -1;
                this.d = 0;
            }
            else {
                int f;
                if ((f = this.d << 1) > this.f) {
                    f = this.f;
                }
                final byte[] b = new byte[f];
                System.arraycopy(this.b, 0, b, 0, this.d);
                this.b = b;
            }
        }
        this.c = this.d;
    }
    
    public final synchronized int read() {
        this.a();
        if (this.d >= this.c) {
            this.b();
            if (this.d >= this.c) {
                return -1;
            }
        }
        return this.b[this.d++] & 0xFF;
    }
    
    private int a(final byte[] array, final int n, int n2) {
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
    
    public final synchronized int read(final byte[] array, final int n, final int n2) {
        this.a();
        if (n < 0 || n > array.length || n2 < 0 || n + n2 > array.length || n + n2 < 0) {
            throw new IndexOutOfBoundsException("Can't read into a buffer of size " + array.length + " at offset " + n + " for a length of " + n2);
        }
        if (n2 == 0) {
            return 0;
        }
        int a;
        if ((a = this.a(array, n, n2)) <= 0) {
            return a;
        }
        int a2;
        while (a < n2 && this.available() > 0 && (a2 = this.a(array, n + a, n2 - a)) > 0) {
            a += a2;
        }
        return a;
    }
    
    public final synchronized long skip(final long n) {
        this.a();
        if (n <= 0L) {
            return 0L;
        }
        int n2;
        if ((n2 = this.c - this.d) <= 0 && n > 66L && this.e < 0) {
            int n3;
            if ((n3 = this.h - this.i) <= 0) {
                if (this.i > 0) {
                    System.arraycopy(this.g, this.i, this.g, 0, this.h - this.i);
                    this.h -= this.i;
                    this.i = 0;
                }
                final int read;
                if ((read = this.in.read(this.g, this.h, Math.min(this.g.length - this.h, this.in.available()))) > 0) {
                    this.h += read;
                    n3 += read;
                }
            }
            if (n3 > 0) {
                final int min = Math.min(n3 / 65, (int)n / 66);
                this.i += min * 65;
                return min * 66;
            }
        }
        if (n2 <= 0) {
            this.b();
            if ((n2 = this.c - this.d) <= 0) {
                return 0L;
            }
        }
        final long n4 = (n2 < n) ? n2 : n;
        this.d += (int)n4;
        return n4;
    }
    
    public final synchronized int available() {
        this.a();
        return this.c - this.d + (this.h - this.i + this.in.available()) / 65 * 66;
    }
    
    public final synchronized void mark(final int f) {
        this.f = f;
        this.e = this.d;
    }
    
    public final synchronized void reset() {
        this.a();
        if (this.e < 0) {
            throw new IOException("Resetting to invalid mark");
        }
        this.d = this.e;
    }
    
    public final boolean markSupported() {
        return true;
    }
    
    public final synchronized void close() {
        if (this.in == null) {
            return;
        }
        this.in.close();
        this.in = null;
        this.b = null;
        this.g = null;
    }
    
    public final synchronized void a(final byte[] array, final boolean b) {
        if (this.e < 0) {
            if (this.b.length - this.c < array.length) {
                final byte[] b2 = new byte[this.b.length + array.length];
                System.arraycopy(this.b, 0, b2, 0, this.d);
                this.b = b2;
            }
            if (this.c > 0) {
                System.arraycopy(this.b, 0, this.b, array.length, this.c);
            }
            System.arraycopy(array, 0, this.b, 0, array.length);
            this.c += array.length;
            return;
        }
        this.c();
        if (this.b.length - this.c < array.length) {
            final byte[] b3 = new byte[this.b.length + array.length];
            System.arraycopy(this.b, 0, b3, 0, this.d);
            this.b = b3;
        }
        System.arraycopy(array, 0, this.b, this.c, array.length);
        this.c += array.length;
    }
    
    static {
        bp.a = 2048;
    }
}
