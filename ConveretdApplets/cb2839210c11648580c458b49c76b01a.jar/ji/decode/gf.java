// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

import ji.util.d;
import ji.io.ac;
import ji.image.ds;
import ji.image.dx;
import ji.util.e;
import ji.v1event.a6;
import ji.v1event.af;

public class gf
{
    private int a;
    private int b;
    private int c;
    private int d;
    private af e;
    private boolean f;
    private byte[] g;
    private int h;
    private short[] i;
    private int j;
    private byte k;
    private int l;
    private byte m;
    private int n;
    private int o;
    private int p;
    private a6 q;
    private int r;
    private int s;
    
    public gf() {
        this.a = 0;
        this.b = 2048;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = null;
        this.h = 0;
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = null;
        this.r = 0;
        this.s = 0;
    }
    
    public final void a() {
        if (ji.util.e.ai()) {
            this.f = true;
        }
    }
    
    public final void a(final af e, final dx dx, final ds ds) throws Exception {
        this.f = false;
        this.a = 0;
        this.d = 0;
        this.c = 0;
        this.j = 0;
        this.e = e;
        if (e != null) {
            if (dx.z < 2) {
                this.r = (dx.m >> 3) * dx.n;
            }
            else if (dx.z == 4) {
                this.r = (1 + dx.m / 2) * dx.n;
            }
            else if (dx.z == 8) {
                this.r = dx.m * dx.n;
            }
            else if (dx.z == 24) {
                this.r = 3 * dx.m * dx.n;
            }
            else if (dx.z == 32) {
                this.r = 4 * dx.m * dx.n;
            }
            this.s = this.r;
            this.o = Math.max(this.r / 15, 16);
            this.p = this.o;
            if (this.q == null) {
                this.q = new a6(this, 4, "");
            }
        }
        this.i = new short[dx.m];
    }
    
    public final void b() {
        this.g = null;
        this.i = null;
        this.e = null;
    }
    
    public final int a(final ac ac, af af, final int n, final int n2, final int n3, final dx dx, final ds ds, final ac ac2, final Object o) throws Exception {
        boolean b = false;
        final boolean b2 = ji.util.e.aj() && dx.x == 1;
        if (dx.z < 2) {
            this.n = (n2 >> 3) * n3;
            this.h = 102400;
            this.h = Math.max(this.h / n2, 1);
            final int h = this.h;
            this.h *= n2;
            this.g = new byte[this.h];
            af = null;
        }
        else if (dx.z == 4) {
            this.g = ds.ag();
            this.n = this.g.length;
            this.h = 2 * this.g.length;
            this.h = Math.max((this.h + 1) / (2 * n2), 1);
            final int h2 = this.h;
            this.h = this.h * 2 * n2;
        }
        else if (dx.z == 8) {
            if (ds.g()) {
                this.g = ds.ag();
                this.n = this.g.length;
                this.h = 2 * this.g.length;
                this.h = Math.max(this.h / n2, 1);
                final int h3 = this.h;
                this.h *= n2;
            }
            else {
                b = true;
                this.n = n2 * n3;
                this.h = Math.min(n2, 204800 / n2) * n2;
                this.g = new byte[this.h];
                final byte[] array = new byte[this.h];
            }
        }
        else if (dx.z == 24) {
            this.n = 3 * n2 * n3;
            this.g = new byte[this.n];
            this.h = 2 * this.n;
        }
        else if (dx.z == 32) {
            this.n = 4 * n2 * n3;
            this.g = new byte[this.n];
            this.h = 2 * this.n;
        }
        System.currentTimeMillis();
        try {
            this.c = 0;
            this.j = 0;
            final byte[] array2 = new byte[Math.min(n2, 204800 / n2) * n2];
            int n4 = ac.a(array2);
            int n5 = 0;
            while (this.c < n && this.j < this.n && !this.f) {
                if (this.j >= this.h || this.j >= this.n) {
                    if (b) {
                        ds.b(this.g, this.j, o, -1, -1, true);
                    }
                    else {
                        ac2.b(this.g, 0, this.j);
                    }
                    this.j = 0;
                }
                this.k = array2[n5++];
                if (n5 >= n4) {
                    n4 = ac.a(array2);
                    n5 = 0;
                }
                ++this.c;
                if (this.k >= 0 && this.k <= 127 && this.c < n) {
                    this.l = 0;
                    while (this.l < this.k + 1) {
                        if (this.c >= n) {
                            break;
                        }
                        if (this.j >= this.n) {
                            break;
                        }
                        this.g[this.j] = array2[n5++];
                        if (b2) {
                            this.g[this.j] = (byte)(255 - this.g[this.j]);
                        }
                        ++this.j;
                        if (n5 >= n4) {
                            n4 = ac.a(array2);
                            n5 = 0;
                        }
                        ++this.c;
                        --this.p;
                        if (this.j >= this.h) {
                            if (b) {
                                ds.b(this.g, this.j, o, -1, -1, true);
                            }
                            else {
                                ac2.b(this.g, 0, this.j);
                            }
                            this.j = 0;
                        }
                        ++this.l;
                    }
                }
                else if (this.k < 0 && this.k >= -127 && this.c < n) {
                    this.m = array2[n5++];
                    if (b2) {
                        this.m = (byte)(255 - this.m);
                    }
                    if (n5 >= n4) {
                        n4 = ac.a(array2);
                        n5 = 0;
                    }
                    ++this.c;
                    this.l = 0;
                    while (this.l < -this.k + 1) {
                        if (this.j >= this.n) {
                            break;
                        }
                        this.g[this.j++] = this.m;
                        --this.p;
                        if (this.j >= this.h) {
                            if (b) {
                                ds.b(this.g, this.j, o, -1, -1, true);
                            }
                            else {
                                ac2.b(this.g, 0, this.j);
                            }
                            this.j = 0;
                        }
                        ++this.l;
                    }
                }
                if (af != null && this.p <= 0) {
                    this.s -= this.o;
                    this.q.a("".concat(String.valueOf(String.valueOf(100 * (this.r - this.s) / this.r))));
                    af.a(this.q);
                    this.p = this.o;
                }
            }
            if (af != null) {
                this.q.a("100");
                af.a(this.q);
            }
        }
        catch (Exception ex) {
            if (!ji.util.d.cy()) {
                throw ex;
            }
            ex.printStackTrace();
        }
        finally {
            final int length = this.g.length;
            if (dx.z == 4) {
                if (dx.x == 1) {
                    for (int i = 0; i < length; ++i) {
                        this.g[i] = (byte)(255 - this.g[i]);
                    }
                }
            }
            else if (dx.z == 8) {
                if (b) {
                    if (this.g != null && this.j >= 0) {
                        ds.b(this.g, this.j, o, -1, -1, true);
                        this.j = 0;
                    }
                }
                else if (dx.x == 1) {
                    for (int j = 0; j < length; ++j) {
                        this.g[j] = (byte)(255 - this.g[j]);
                    }
                }
            }
            else if (dx.z == 24) {
                int n6 = 409600;
                if (n6 > n2 * n3) {
                    n6 = n2 * n3;
                }
                final int max;
                final int n7 = (max = Math.max(n6 / n2, 1)) * n2;
                final int[] array3 = new int[n7];
                int n8 = 0;
                int n9 = n7;
                int n10 = 1;
                try {
                    for (int k = 0; k < length; k += 3) {
                        array3[n8++] = (0xFF000000 | (this.g[k] & 0xFF) << 16 | (this.g[k + 1] & 0xFF) << 8 | (this.g[k + 2] & 0xFF));
                        if (--n9 == 0) {
                            ds.a(array3, n8, o, n10, n10 + max, true);
                            n10 += max;
                            n9 = n7;
                            n8 = 0;
                        }
                    }
                }
                catch (Exception ex2) {}
                if (n8 > 0) {
                    ds.a(array3, n8, o, n10, n3, true);
                }
            }
            else if (dx.z == 32) {
                int n11 = 409600;
                if (n11 > n2 * n3) {
                    n11 = n2 * n3;
                }
                final int max2;
                final int n12 = (max2 = Math.max(n11 / n2, 1)) * n2;
                final int[] array4 = new int[n12];
                int n13 = 0;
                int n14 = n12;
                int n15 = 1;
                try {
                    for (int l = 0; l < length; l += 4) {
                        array4[n13++] = (0xFF000000 | (this.g[l] & 0xFF) << 16 | (this.g[l + 1] & 0xFF) << 8 | (this.g[l + 2] & 0xFF));
                        if (--n14 == 0) {
                            ds.a(array4, n13, o, n15, n15 + max2, true);
                            n15 += max2;
                            n14 = n12;
                            n13 = 0;
                        }
                    }
                }
                catch (Exception ex3) {}
                if (n13 > 0) {
                    ds.a(array4, n13, o, n15, n3, true);
                }
                dx.z = 24;
            }
            else if (this.g != null && this.j >= 0) {
                ac2.b(this.g, 0, this.j);
                this.j = 0;
            }
            this.g = null;
        }
        return n3;
    }
}
