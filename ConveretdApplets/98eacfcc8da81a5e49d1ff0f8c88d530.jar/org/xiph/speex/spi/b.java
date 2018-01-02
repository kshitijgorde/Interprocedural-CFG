// 
// Decompiled by Procyon v0.5.30
// 

package org.xiph.speex.spi;

import java.io.IOException;
import VT_6_1_0_11.bb;
import VT_6_1_0_11.cT;
import java.io.StreamCorruptedException;
import javax.sound.sampled.AudioFormat;
import java.io.InputStream;
import VT_6_1_0_11.bZ;
import VT_6_1_0_11.cH;

public final class b extends a
{
    private boolean i;
    private int j;
    private float[] k;
    private byte[] l;
    private cH m;
    private bZ n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private byte[] t;
    
    public b(final InputStream inputStream, final AudioFormat audioFormat, final long n) {
        this(inputStream, audioFormat, -1L, 2048);
    }
    
    private b(final InputStream inputStream, final AudioFormat audioFormat, final long n, final int n2) {
        super(inputStream, audioFormat, n, 2048);
        this.m = new cH();
        this.t = new byte[256];
        this.i = false;
    }
    
    private void a(final boolean b) {
        while (!this.i) {
            final int n = this.f.length - this.g - 1;
            final int available = this.a.available();
            if (!b && available <= 0) {
                return;
            }
            final int read;
            if ((read = this.a.read(this.f, this.g, (available > 0) ? Math.min(available, n) : n)) < 0) {
                throw new StreamCorruptedException("Incomplete Ogg Headers");
            }
            this.g += read;
            if (this.n == null && this.g >= 108) {
                if (!new String(this.f, 0, 4).equals("OggS")) {
                    throw new StreamCorruptedException("The given stream does not appear to be Ogg.");
                }
                this.q = a(this.f, 14);
                if (!new String(this.f, 28, 8).equals("Speex   ")) {
                    throw new StreamCorruptedException("The given stream does not appear to be Ogg Speex.");
                }
                this.j = a(this.f, 76);
                this.p = a(this.f, 92);
                switch (a(this.f, 68)) {
                    case 0: {
                        this.n = new cT();
                        ((cT)this.n).g();
                        break;
                    }
                    case 1: {
                        this.n = new bb();
                        ((bb)this.n).a_();
                        break;
                    }
                    case 2: {
                        this.n = new bb();
                        ((bb)this.n).b_();
                        break;
                    }
                }
                this.n.a(true);
                this.o = this.n.c();
                this.k = new float[this.o * this.j];
                this.l = new byte[2 * this.o * this.j * this.p];
                this.m.a();
            }
            if (this.n == null || this.g < 135) {
                continue;
            }
            this.r = (0xFF & this.f[134]);
            if (this.g < 135 + this.r) {
                continue;
            }
            int n2 = 0;
            for (int i = 0; i < this.r; ++i) {
                n2 += (0xFF & this.f[i + 135]);
            }
            if (this.g < 135 + this.r + n2) {
                continue;
            }
            this.h = 135 + this.r + n2;
            this.r = 0;
            this.s = 255;
            this.i = true;
        }
    }
    
    protected final void b() {
        this.c();
        while (!this.i) {
            this.a(true);
        }
        int read;
        while ((read = this.a.read(this.f, this.g, this.f.length - this.g)) >= 0) {
            if (read >= 0) {
                this.g += read;
                if (this.s >= this.r) {
                    this.d();
                }
                if (this.s < this.r && this.g - this.h >= this.t[this.s]) {
                    while (this.g - this.h >= this.t[this.s] && this.s < this.r) {
                        final byte b = this.t[this.s++];
                        this.a(this.f, this.h, b);
                        this.h += b;
                        while (this.b.length - this.c < this.l.length) {
                            final byte[] b2 = new byte[this.b.length << 1];
                            System.arraycopy(this.b, 0, b2, 0, this.c);
                            this.b = b2;
                        }
                        System.arraycopy(this.l, 0, this.b, this.c, this.l.length);
                        this.c += this.l.length;
                        if (this.s >= this.r) {
                            this.d();
                        }
                    }
                    System.arraycopy(this.f, this.h, this.f, 0, this.g - this.h);
                    this.g -= this.h;
                    this.h = 0;
                    return;
                }
                continue;
            }
        }
        while (this.h < this.g) {
            if (this.s >= this.r) {
                this.d();
            }
            if (this.s < this.r) {
                final byte b3 = this.t[this.s++];
                if (this.g - this.h < b3) {
                    throw new StreamCorruptedException("Incompleted last Speex packet");
                }
                this.a(this.f, this.h, b3);
                this.h += b3;
                while (this.b.length - this.c < this.l.length) {
                    final byte[] b4 = new byte[this.b.length << 1];
                    System.arraycopy(this.b, 0, b4, 0, this.c);
                    this.b = b4;
                }
                System.arraycopy(this.l, 0, this.b, this.c, this.l.length);
                this.c += this.l.length;
            }
        }
    }
    
    private void a(final byte[] array, int n, int i) {
        int n2 = 0;
        this.m.a(array, n, i);
        int j;
        int k;
        for (i = 0; i < this.p; ++i) {
            this.n.a(this.m, this.k);
            if (this.j == 2) {
                this.n.a(this.k, this.o);
            }
            for (j = 0; j < this.o * this.j; ++j) {
                if (this.k[j] > 32767.0f) {
                    this.k[j] = 32767.0f;
                }
                else if (this.k[j] < -32768.0f) {
                    this.k[j] = -32768.0f;
                }
            }
            for (k = 0; k < this.o * this.j; ++k) {
                n = ((this.k[k] > 0.0f) ? ((short)(this.k[k] + 0.5)) : ((short)(this.k[k] - 0.5)));
                this.l[n2++] = (byte)n;
                this.l[n2++] = (byte)(n >> 8);
            }
        }
    }
    
    public final synchronized long skip(long n) {
        while (!this.i) {
            this.a(true);
        }
        this.a();
        if (n <= 0L) {
            return 0L;
        }
        if (this.d < this.c) {
            return super.skip(n);
        }
        final int n2 = 2 * this.p * this.o * this.j;
        if (this.e < 0 && n >= n2) {
            if (this.s >= this.r) {
                this.d();
            }
            if (this.s < this.r) {
                int n3 = 0;
                final int available;
                if (this.g - this.h < this.t[this.s] && (available = this.a.available()) > 0) {
                    final int read;
                    if ((read = this.a.read(this.f, this.g, Math.min(this.f.length - this.g, available))) < 0) {
                        throw new IOException("End of stream but there are still supposed to be packets to decode");
                    }
                    this.g += read;
                }
                while (this.g - this.h >= this.t[this.s] && this.s < this.r && n >= n2) {
                    this.h += this.t[this.s++];
                    n3 += n2;
                    n -= n2;
                    if (this.s >= this.r) {
                        this.d();
                    }
                }
                System.arraycopy(this.f, this.h, this.f, 0, this.g - this.h);
                this.g -= this.h;
                this.h = 0;
                return n3;
            }
        }
        return super.skip(n);
    }
    
    public final synchronized int available() {
        if (!this.i) {
            this.a(false);
            if (!this.i) {
                return 0;
            }
        }
        int available = super.available();
        if (this.s >= this.r) {
            this.d();
        }
        if (this.s < this.r) {
            int n = this.g - this.h + this.a.available();
            byte b = this.t[this.s];
            for (int n2 = 0; b < n && this.s + n2 < this.r; n -= b, available += 2 * this.o * this.p, ++n2, b = this.t[this.s + n2]) {}
        }
        return available;
    }
    
    private void d() {
        int r = 0;
        final int available;
        if (this.g - this.h < 27 && (available = this.a.available()) > 0) {
            final int read;
            if ((read = this.a.read(this.f, this.g, Math.min(this.f.length - this.g, available))) < 0) {
                throw new IOException("End of stream but available was positive");
            }
            this.g += read;
        }
        if (this.g - this.h >= 27) {
            if (!new String(this.f, this.h, 4).equals("OggS")) {
                throw new StreamCorruptedException("Lost Ogg Sync");
            }
            if (this.q != a(this.f, this.h + 14)) {
                throw new StreamCorruptedException("Ogg Stream Serial Number mismatch");
            }
            r = (0xFF & this.f[this.h + 26]);
        }
        final int available2;
        if (this.g - this.h < r + 27 && (available2 = this.a.available()) > 0) {
            final int read2;
            if ((read2 = this.a.read(this.f, this.g, Math.min(this.f.length - this.g, available2))) < 0) {
                throw new IOException("End of stream but available was positive");
            }
            this.g += read2;
        }
        if (this.g - this.h >= r + 27) {
            System.arraycopy(this.f, this.h + 27, this.t, 0, r);
            this.s = 0;
            this.h += r + 27;
            this.r = r;
        }
    }
    
    private static int a(final byte[] array, final int n) {
        return (array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | array[n + 3] << 24;
    }
}
