// 
// Decompiled by Procyon v0.5.30
// 

package org.xiph.speex.spi;

import java.io.StreamCorruptedException;
import VT_6_1_0_11.f;
import VT_6_1_0_11.bm;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import java.io.InputStream;
import VT_6_1_0_11.dp;

public final class c extends a
{
    private dp i;
    private int j;
    private int k;
    private int l;
    private int m;
    private String n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private boolean u;
    
    public c(final InputStream inputStream, final AudioFormat audioFormat, final long n) {
        this(-1, -1, inputStream, audioFormat, -1L, 2560);
    }
    
    private c(int j, int a, final InputStream inputStream, final AudioFormat audioFormat, final long n, final int n2) {
        super(inputStream, audioFormat, n, n2);
        this.n = null;
        this.o = 0;
        if (this.p == 0) {
            this.p = new Random().nextInt();
        }
        this.q = 20;
        this.r = 0;
        this.s = 0;
        this.l = 1;
        int n3;
        if ((n3 = (int)audioFormat.getSampleRate()) < 0) {
            n3 = 8000;
        }
        this.m = audioFormat.getChannels();
        if (this.m < 0) {
            this.m = 1;
        }
        j = ((n3 < 12000) ? 0 : ((n3 <= 24000) ? 1 : 2));
        this.j = j;
        final AudioFormat.Encoding encoding;
        if ((encoding = audioFormat.getEncoding()) instanceof e) {
            a = ((e)encoding).a();
        }
        else {
            a = 3;
        }
        (this.i = new dp()).a(j, a, n3, this.m);
        if (encoding instanceof e && ((e)encoding).b()) {
            this.a(true);
        }
        else {
            this.a(false);
        }
        this.k = 2 * this.m * this.i.d();
        this.n = "Encoded with Java Speex Encoder v0.9.7 ($Revision: 1.6 $)";
        this.u = true;
    }
    
    private void a(final boolean b) {
        this.i.a().a(b);
    }
    
    protected final void b() {
        this.c();
        if (this.u) {
            int length;
            if ((length = this.n.length()) > 247) {
                this.n = this.n.substring(0, 247);
                length = 247;
            }
            while (this.b.length - this.c < length + 144) {
                final byte[] b = new byte[this.b.length << 1];
                System.arraycopy(this.b, 0, b, 0, this.c);
                this.b = b;
            }
            bm.a(this.b, this.c, 2, this.o, this.p, this.s++, 1, new byte[] { 80 });
            this.t = this.c + 28;
            final byte[] b2 = this.b;
            final int t = this.t;
            final int b3 = this.i.b();
            final int j = this.j;
            final int c = this.i.c();
            final int i = this.i.a().i() ? 1 : 0;
            final int l = this.l;
            final int n = i;
            final int n2 = c;
            final int n3 = j;
            final int n4 = b3;
            final int n5 = t;
            final byte[] array = b2;
            bm.a(b2, n5, "Speex   ");
            bm.a(array, n5 + 8, "speex-1.0");
            System.arraycopy(new byte[11], 0, array, n5 + 17, 11);
            bm.a(array, n5 + 28, 1);
            bm.a(array, n5 + 32, 80);
            bm.a(array, n5 + 36, n4);
            bm.a(array, n5 + 40, n3);
            bm.a(array, n5 + 44, 4);
            bm.a(array, n5 + 48, n2);
            bm.a(array, n5 + 52, -1);
            bm.a(array, n5 + 56, 160 << n3);
            bm.a(array, n5 + 60, n);
            bm.a(array, n5 + 64, l);
            bm.a(array, n5 + 68, 0);
            bm.a(array, n5 + 72, 0);
            bm.a(array, n5 + 76, 0);
            this.t += 80;
            bm.a(this.b, this.c + 22, VT_6_1_0_11.f.a(0, this.b, this.c, this.t - this.c));
            this.c = this.t;
            bm.a(this.b, this.c, 0, this.o, this.p, this.s++, 1, new byte[] { (byte)(length + 8) });
            this.t = this.c + 28;
            final byte[] b4 = this.b;
            final int t2 = this.t;
            final String n6 = this.n;
            final int n7 = t2;
            final byte[] array2 = b4;
            final int length2 = n6.length();
            bm.a(array2, n7, length2);
            bm.a(array2, n7 + 4, n6);
            bm.a(array2, n7 + length2 + 4, 0);
            this.t += length + 8;
            bm.a(this.b, this.c + 22, VT_6_1_0_11.f.a(0, this.b, this.c, this.t - this.c));
            this.c = this.t;
            this.r = 0;
            this.u = false;
        }
        while (true) {
            if (this.f.length - this.h < this.l * this.k * this.q) {
                final byte[] f = new byte[this.h + this.l * this.k * this.q];
                System.arraycopy(this.f, 0, f, 0, this.g);
                this.f = f;
            }
            final int read;
            if ((read = this.a.read(this.f, this.g, this.f.length - this.g)) < 0) {
                if ((this.g - this.h) % 2 != 0) {
                    throw new StreamCorruptedException("Incompleted last PCM sample when stream ended");
                }
                while (this.h < this.g) {
                    if (this.g - this.h < this.l * this.k) {
                        while (this.g < this.h + this.l * this.k) {
                            this.f[this.g] = 0;
                            ++this.g;
                        }
                    }
                    if (this.r == 0) {
                        this.a(this.q, 0);
                    }
                    for (int k = 0; k < this.l; ++k) {
                        this.i.a(this.f, this.h, this.k);
                        this.h += this.k;
                    }
                    final int e = this.i.e();
                    while (this.b.length - this.t < e) {
                        final byte[] b5 = new byte[this.b.length << 1];
                        System.arraycopy(this.b, 0, b5, 0, this.t);
                        this.b = b5;
                    }
                    this.b[this.c + 27 + this.r] = (byte)(0xFF & e);
                    this.i.a(this.b, this.t);
                    this.t += e;
                    ++this.r;
                    if (this.r >= this.q) {
                        this.d();
                        return;
                    }
                }
                if (this.r > 0) {
                    this.b[this.c + 5] = 4;
                    this.b[this.c + 26] = (byte)(0xFF & this.r);
                    System.arraycopy(this.b, this.c + 27 + this.q, this.b, this.c + 27 + this.r, this.t - (this.c + 27 + this.q));
                    this.t -= this.q - this.r;
                    this.d();
                }
                return;
            }
            else if (read > 0) {
                this.g += read;
                if (this.g - this.h >= this.l * this.k * this.q) {
                    break;
                }
                continue;
            }
            else {
                if (this.g < this.f.length) {
                    return;
                }
                if (this.h <= 0) {
                    return;
                }
                System.arraycopy(this.f, this.h, this.f, 0, this.g - this.h);
                this.g -= this.h;
                this.h = 0;
            }
        }
        while (this.g - this.h >= this.l * this.k * this.q) {
            if (this.r == 0) {
                this.a(this.q, 0);
            }
            while (this.r < this.q) {
                for (int n8 = 0; n8 < this.l; ++n8) {
                    this.i.a(this.f, this.h, this.k);
                    this.h += this.k;
                }
                final int e2 = this.i.e();
                while (this.b.length - this.t < e2) {
                    final byte[] b6 = new byte[this.b.length << 1];
                    System.arraycopy(this.b, 0, b6, 0, this.t);
                    this.b = b6;
                }
                this.b[this.c + 27 + this.r] = (byte)(0xFF & e2);
                this.i.a(this.b, this.t);
                this.t += e2;
                ++this.r;
            }
            if (this.r >= this.q) {
                this.d();
            }
        }
        System.arraycopy(this.f, this.h, this.f, 0, this.g - this.h);
        this.g -= this.h;
        this.h = 0;
    }
    
    public final synchronized int available() {
        final int available = super.available();
        final int n = this.g - this.h + this.a.available();
        if (this.i.a().i()) {
            switch (this.j) {
                case 0: {
                    return available + (27 + 2 * this.q) * (n / (this.q * this.l * 320));
                }
                case 1: {
                    return available + (27 + 2 * this.q) * (n / (this.q * this.l * 640));
                }
                case 2: {
                    return available + (27 + 3 * this.q) * (n / (this.q * this.l * 1280));
                }
                default: {
                    return available;
                }
            }
        }
        else {
            int a = this.i.a().a();
            if (this.m > 1) {
                a += 17;
            }
            final int n2 = 27 + this.q * ((a * this.l + 7 >> 3) + 1);
            switch (this.j) {
                case 0: {
                    return available + n2 * (n / (this.q * (this.l * 320 * this.i.c())));
                }
                case 1: {
                    return available + n2 * (n / (this.q * (this.l * 640 * this.i.c())));
                }
                case 2: {
                    return available + n2 * (n / (this.q * (this.l * 1280 * this.i.c())));
                }
                default: {
                    return available;
                }
            }
        }
    }
    
    private void a(final int n, int n2) {
        while (this.b.length - this.c < n + 27) {
            final byte[] b = new byte[n2 = this.b.length << 1];
            System.arraycopy(this.b, 0, b, 0, this.c);
            this.b = b;
        }
        bm.a(this.b, this.c, 0, this.o, this.p, this.s++, n, new byte[n]);
        this.t = this.c + 27 + n;
    }
    
    private void d() {
        this.o += this.l * this.k * this.r / 2;
        bm.a(this.b, this.c + 6, (long)this.o);
        bm.a(this.b, this.c + 22, VT_6_1_0_11.f.a(0, this.b, this.c, this.t - this.c));
        this.c = this.t;
        this.r = 0;
    }
}
