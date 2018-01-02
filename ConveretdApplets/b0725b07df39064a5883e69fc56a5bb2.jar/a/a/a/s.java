// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

public final class s implements h
{
    private int a;
    private int b;
    private final int[] c;
    private int d;
    private final int[] e;
    private final int[] f;
    private final int[] g;
    private int h;
    private int i;
    private int j;
    private int k;
    private final int[] l;
    private static final int[] m;
    
    public s() {
        this.l = new int[4];
        this.c = new int[8];
        this.e = new int[4];
        this.f = new int[4];
        this.g = new int[3];
    }
    
    public final boolean b() {
        this.a(this.j, this.k);
        return true;
    }
    
    public final boolean a() {
        this.b();
        return true;
    }
    
    public final void a(final int n) {
    }
    
    public final void a(int i, final int k) {
        this.j = i;
        this.k = k;
        this.a = (i << 8) / 16 / k;
        this.d = 0;
        this.b = 0;
        this.i = 32768;
        this.h = 16;
        for (i = 0; i < 4; ++i) {
            this.c[i << 1] = 1;
            this.c[(i << 1) + 1] = 15;
            this.e[i] = 0;
            this.f[i] = 1;
            if (i != 3) {
                this.g[i] = Integer.MIN_VALUE;
            }
        }
    }
    
    public final void a(final char c, final char c2) {
        Label_0241: {
            switch (c & '\u000f') {
                case '\u000e':
                case '\u000f': {
                    this = this;
                    if ((c2 & '\u0080') != '\0') {
                        this.d = (c2 >> 4 & '\u0007');
                        this.c[this.d] = ((this.c[this.d] & 0x3F0) | (c2 & '\u000f'));
                    }
                    else if (this.d == 0 || this.d == 2 || this.d == 4) {
                        this.c[this.d] = ((this.c[this.d] & 0xF) | (c2 & '?') << 4);
                    }
                    else {
                        this.c[this.d] = (c2 & '\u000f');
                    }
                    switch (this.d) {
                        case 0:
                        case 2:
                        case 4: {
                            if (this.c[this.d] == 0) {
                                this.c[this.d] = 1;
                                return;
                            }
                            break Label_0241;
                        }
                        case 6: {
                            this.h = 16 << (this.c[6] & 0x3);
                            this.i = 32768;
                            break Label_0241;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public final char a(final char c) {
        return '\u00ff';
    }
    
    public final void a(final byte[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (this.g[j] != Integer.MIN_VALUE) {
                    this.l[j] = s.m[this.c[(j << 1) + 1]] * this.g[j] >> 8;
                }
                else {
                    this.l[j] = s.m[this.c[(j << 1) + 1]] * this.f[j];
                }
            }
            this.l[3] = s.m[this.c[7]] * (this.i & 0x1) << 1;
            int n3;
            if ((n3 = this.l[0] + this.l[1] + this.l[2] + this.l[3]) > 127) {
                n3 = 127;
            }
            else if (n3 < -128) {
                n3 = -128;
            }
            array[n + i] = (byte)n3;
            this.b += this.a;
            final int n5;
            final int n4 = (n5 = this.b >> 8) << 8;
            this.b -= n4;
            final int[] e = this.e;
            final int n6 = 0;
            e[n6] -= n5;
            final int[] e2 = this.e;
            final int n7 = 1;
            e2[n7] -= n5;
            final int[] e3 = this.e;
            final int n8 = 2;
            e3[n8] -= n5;
            if (this.h == 128) {
                this.e[3] = this.e[2];
            }
            else {
                final int[] e4 = this.e;
                final int n9 = 3;
                e4[n9] -= n5;
            }
            for (int k = 0; k < 3; ++k) {
                final int n10;
                if ((n10 = this.e[k]) <= 0) {
                    final int n11;
                    if ((n11 = this.c[k << 1]) > 6) {
                        this.g[k] = (n4 - this.b + n10 * 512 << 8) * this.f[k] / (n4 + this.b);
                        this.f[k] = -this.f[k];
                    }
                    else {
                        this.f[k] = 1;
                        this.g[k] = Integer.MIN_VALUE;
                    }
                    final int[] e5 = this.e;
                    final int n12 = k;
                    e5[n12] += n11 * (n5 / n11 + 1);
                }
                else {
                    this.g[k] = Integer.MIN_VALUE;
                }
            }
            if (this.e[3] <= 0) {
                this.f[3] = -this.f[3];
                if (this.h != 128) {
                    final int[] e6 = this.e;
                    final int n13 = 3;
                    e6[n13] += this.h * (n5 / this.h + 1);
                }
                if (this.f[3] == 1) {
                    int n14;
                    if ((this.c[6] & 0x4) != 0x0) {
                        n14 = (((this.i & 0x9) != 0x0 && ((this.i & 0x9) ^ 0x9) != 0x0) ? 1 : 0);
                    }
                    else {
                        n14 = (this.i & 0x1);
                    }
                    this.i = (this.i >> 1 | n14 << 15);
                }
            }
        }
    }
    
    static {
        m = new int[] { 25, 20, 16, 13, 10, 8, 6, 5, 4, 3, 3, 2, 2, 1, 1, 0 };
    }
}
