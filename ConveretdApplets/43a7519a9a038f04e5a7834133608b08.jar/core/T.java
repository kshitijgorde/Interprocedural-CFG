// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.MemoryImageSource;

public final class T
{
    private final int[] a;
    private final MemoryImageSource b;
    private final int c;
    private final int d;
    private int e;
    private int f;
    
    private T(final RE re, final int c, final int d, final byte b) {
        this.c = c;
        this.d = d;
        this.e = this.c;
        this.f = this.d;
        final DirectColorModel directColorModel = new DirectColorModel(24, 16711680, 65280, 255);
        this.a = new int[c * d];
        (this.b = new MemoryImageSource(c, d, directColorModel, this.a, 0, c)).setAnimated(true);
        this.b.setFullBufferUpdates(true);
        RE.a(re, re.createImage(this.b));
    }
    
    public final void a(int n, int i, final M m) {
        final int n3;
        final int n2 = ((n3 = this.e - n) < m.a) ? n3 : m.a;
        final int n5;
        final int n4 = ((n5 = this.f - i) < m.b) ? n5 : m.b;
        final int n6 = (n < 0) ? (0 - n) : 0;
        final int n7 = (i < 0) ? (0 - i) : 0;
        n += this.c * i;
        int n8;
        int n9;
        int j;
        for (i = n7; i < n4; ++i) {
            n8 = m.a * i;
            n9 = n + this.c * i;
            for (j = n6; j < n2; ++j) {
                this.b(j + n9, 255, M.a(m)[j + n8] & 0xFF);
            }
        }
    }
    
    public final void a(int n, int i, final II ii) {
        final int n3;
        final int n2 = ((n3 = this.e - n) < ii.a) ? n3 : ii.a;
        final int n5;
        final int n4 = ((n5 = this.f - i) < ii.b) ? n5 : ii.b;
        final int n6 = (n < 0) ? (0 - n) : 0;
        final int n7 = (i < 0) ? (0 - i) : 0;
        n += this.c * i;
        int n8;
        int n9;
        int j;
        for (i = n7; i < n4; ++i) {
            n8 = ii.a * i;
            n9 = n + this.c * i;
            for (j = n6; j < n2; ++j) {
                this.a[j + n9] = II.a(ii)[j + n8];
            }
        }
    }
    
    public final void a(int n, int i, int n2) {
        if (i < this.f && i >= 0) {
            final int n3;
            n2 = (((n3 = this.e - n) < n2) ? n3 : n2);
            final int n4 = (n < 0) ? (0 - n) : 0;
            n += this.c * i;
            for (i = n4; i < n2; ++i) {
                this.a[i + n] = 4473924;
            }
        }
    }
    
    public final void a(int n, int i) {
        if (n >= 0 && n < this.e) {
            final int n3;
            final int n2 = ((n3 = this.f - i) < 11) ? n3 : 11;
            final int n4 = (i < 0) ? (0 - i) : 0;
            n += this.c * i;
            for (i = n4; i < n2; ++i) {
                this.a[n + this.c * i] = 4473924;
            }
        }
    }
    
    public final void a(int n, int i, int n2, int n3, final int n4) {
        final int n5;
        n2 = (((n5 = this.e - n) < n2) ? n5 : n2);
        final int n6;
        n3 = (((n6 = this.f - i) < n3) ? n6 : n3);
        final int n7 = (n < 0) ? (0 - n) : 0;
        final int n8 = (i < 0) ? (0 - i) : 0;
        n += this.c * i;
        int n9;
        int j;
        for (i = n8; i < n3; ++i) {
            n9 = n + this.c * i;
            for (j = n7; j < n2; ++j) {
                this.b(j + n9, 0, n4);
            }
        }
    }
    
    private void b(final int n, int n2, int n3) {
        if (n3 != 0) {
            final int n4 = this.a[n];
            n2 = n3 * n2 >> 8;
            n3 = 256 - n3;
            final int n5 = n2 + ((n4 >> 16) * n3 >> 8);
            final int n6 = n2 + ((n4 >> 8 & 0xFF) * n3 >> 8);
            n2 += (n4 & 0xFF) * n3 >> 8;
            n3 = (n5 << 16 | n6 << 8 | n2);
            this.a[n] = n3;
        }
    }
    
    public final void b(final int n, final int n2, int i, final int n3, final int n4) {
        final int n5 = i * i;
        int j = 0;
        while (i > 0) {
            final int n6;
            final boolean b = (n6 = n + i) >= 0 && n6 < this.e;
            final int n7;
            final boolean b2 = (n7 = n - i) >= 0 && n7 < this.e;
            int n8;
            for (n8 = (int)Math.sqrt(n5 - i * i); j < n8; ++j) {
                final int n9;
                if ((n9 = n2 + j) >= 0) {
                    if (n9 < this.f) {
                        final int n10 = this.c * n9;
                        if (b) {
                            this.b(n6 + n10, n3, n4);
                        }
                        if (b2) {
                            this.b(n7 + n10, n3, n4);
                        }
                    }
                    final int n11;
                    if ((n11 = n2 - j) >= 0 && n11 < this.f) {
                        final int n12 = this.c * n11;
                        if (b2) {
                            this.b(n7 + n12, n3, n4);
                        }
                        if (b) {
                            this.b(n6 + n12, n3, n4);
                        }
                    }
                }
            }
            j = n8;
            --i;
        }
    }
    
    T(final RE re, final int n, final int n2) {
        this(re, n, n2, (byte)0);
    }
    
    static final int[] a(final T t) {
        return t.a;
    }
    
    static final MemoryImageSource b(final T t) {
        return t.b;
    }
}
