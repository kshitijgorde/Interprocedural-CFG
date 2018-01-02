import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class db
{
    int[] a;
    float[] b;
    int c;
    int d;
    int e;
    int f;
    float g;
    float h;
    float i;
    dc j;
    dd[] k;
    int l;
    float m;
    int[] n;
    int[] p;
    bb q;
    bb r;
    bb s;
    bb t;
    Hashtable u;
    Hashtable v;
    public int w;
    public int x;
    public c8[] y;
    public int[][] z;
    
    public db() {
        this.g = 1.0f;
        this.h = 1.0f;
        this.i = 1.0f;
        this.p = null;
        this.q = new bb();
        this.r = new bb();
        this.s = new bb();
        this.t = new bb();
        this.u = null;
        this.v = null;
    }
    
    void a() {
        this.y = null;
        this.z = null;
    }
    
    void a(final w w, final int n) {
        final boolean l = c.l;
        w.c();
        final int d = w.d();
        final int d2 = w.d();
        final int d3 = w.d();
        final int d4 = w.d();
        this.c = w.a(31);
        this.d = w.a(31);
        this.e = w.a(31);
        this.f = w.a(31) / 2;
        this.a = new int[this.c * 2];
        this.b = new float[this.d * 3];
        this.z = new int[this.e][3];
        int n2 = 0;
        int n3;
        int n4;
        while (true) {
            while (true) {
                Label_0181: {
                    if (!l) {
                        break Label_0181;
                    }
                    n3 = n2 * 2;
                    this.a[n3] = w.a(d);
                    Label_0178: {
                        if (this.a[n3] == 2) {
                            this.a[n3 + 1] = w.a(d2);
                            if (!l) {
                                break Label_0178;
                            }
                        }
                        this.a[n3 + 1] = 0;
                    }
                    ++n2;
                }
                if (n2 < this.c) {
                    continue;
                }
                break;
            }
            this.m = (float)Math.pow(2.0, d3 - 3) - 1.0f;
            n3 = (int)(Math.pow(2.0, d3 - 1) - 1.0);
            n4 = 0;
            if (l) {
                continue;
            }
            break;
        }
        int n6;
        while (true) {
            while (true) {
                Label_0354: {
                    if (!l) {
                        break Label_0354;
                    }
                    final int n5 = n4 * 3;
                    this.b[n5] = w.a(d3);
                    this.b[n5] -= n3;
                    this.b[n5 + 1] = w.a(d3);
                    this.b[n5 + 1] -= n3;
                    this.b[n5 + 2] = w.a(d3);
                    this.b[n5 + 2] -= n3;
                    ++n4;
                }
                if (n4 < this.d) {
                    continue;
                }
                break;
            }
            this.n = new int[this.f * 2];
            n6 = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0423: {
                if (!l) {
                    break Label_0423;
                }
                this.n[n6 * 2] = w.a(d4);
                this.n[n6 * 2 + 1] = w.a(d4);
                ++n6;
            }
            if (n6 >= this.f) {
                if (w.b < n) {
                    final int a = w.a(31);
                    final int a2 = w.a(31);
                    final int a3 = w.a(31);
                    this.g = Float.intBitsToFloat(a) / this.m;
                    this.h = Float.intBitsToFloat(a2) / this.m;
                    this.i = Float.intBitsToFloat(a3) / this.m;
                }
                return;
            }
            continue;
        }
    }
    
    void a(final int n, final int n2) {
        final dd dd = new dd(this, n, n2);
        final df df = this.v.get(dd);
        if (df == null) {
            this.k[this.l] = dd;
            this.v.put(dd, new df(this, this.l));
            ++this.l;
            this.j.a(dd);
            if (!c.l) {
                return;
            }
        }
        this.j.b(this.k[df.a]);
    }
    
    int b(final int n, final int n2) {
        final df df = this.u.get(new dd(this, n, n2));
        if (df == null) {
            final int a = -1;
            if (!c.l) {
                return a;
            }
        }
        return df.a;
    }
    
    int[][] a(final w w, final int[][] array, final int n, final int n2) {
        final boolean l = c.l;
        this.j = new dc(this, null);
        this.a(w, n2);
        this.k = new dd[this.e * 3];
        this.l = 0;
        this.u = new Hashtable(this.e * 3, 0.9f);
        this.v = new Hashtable(this.e * 3, 0.9f);
        int w2 = 0;
        int x = 0;
        int n3 = 0;
        int n10;
        while (true) {
            while (true) {
                Label_1231: {
                    if (!l) {
                        break Label_1231;
                    }
                    Label_1228: {
                        while (true) {
                            Label_0832: {
                                switch (this.a[n3 * 2]) {
                                    case 3: {
                                        this.j.b(this.j.a.a);
                                        if (l) {
                                            break Label_0832;
                                        }
                                        break Label_1228;
                                    }
                                    case 0: {
                                        (this.p = this.z[w2])[0] = x;
                                        ++x;
                                        this.p[1] = x;
                                        ++x;
                                        this.p[2] = x;
                                        ++x;
                                        break;
                                    }
                                    case 2: {
                                        final dd a = this.j.a.a;
                                        (this.p = this.z[w2])[2] = this.a[n3 * 2 + 1];
                                        this.p[0] = a.b;
                                        this.p[1] = a.a;
                                        this.a(this.p[0], this.p[1]);
                                        this.a(this.p[1], this.p[2]);
                                        this.a(this.p[2], this.p[0]);
                                        this.u.put(new dd(this, this.p[0], this.p[1]), new df(this, this.p[2]));
                                        this.u.put(new dd(this, this.p[2], this.p[0]), new df(this, this.p[1]));
                                        this.u.put(new dd(this, this.p[1], this.p[2]), new df(this, this.p[0]));
                                        ++w2;
                                        if (l) {
                                            break Label_0832;
                                        }
                                        break Label_1228;
                                    }
                                    case 1: {
                                        final dd a2 = this.j.a.a;
                                        (this.p = this.z[w2])[2] = x;
                                        this.p[0] = a2.b;
                                        this.p[1] = a2.a;
                                        final int a3 = a2.a;
                                        final int b = a2.b;
                                        final int b2 = this.b(a3, b);
                                        final int n4 = a3 * 3;
                                        final int n5 = b * 3;
                                        final int n6 = b2 * 3;
                                        final float[] b3 = this.b;
                                        final int n7 = x * 3;
                                        b3[n7] += this.b[n4] + this.b[n5] - this.b[n6];
                                        final float[] b4 = this.b;
                                        final int n8 = x * 3 + 1;
                                        b4[n8] += this.b[n4 + 1] + this.b[n5 + 1] - this.b[n6 + 1];
                                        final float[] b5 = this.b;
                                        final int n9 = x * 3 + 2;
                                        b5[n9] += this.b[n4 + 2] + this.b[n5 + 2] - this.b[n6 + 2];
                                        this.a(this.p[0], this.p[1]);
                                        this.a(this.p[1], this.p[2]);
                                        this.a(this.p[2], this.p[0]);
                                        this.u.put(new dd(this, this.p[0], this.p[1]), new df(this, this.p[2]));
                                        this.u.put(new dd(this, this.p[2], this.p[0]), new df(this, this.p[1]));
                                        this.u.put(new dd(this, this.p[1], this.p[2]), new df(this, this.p[0]));
                                        ++x;
                                        ++w2;
                                        break Label_1228;
                                    }
                                }
                            }
                            final dd dd = new dd(this);
                            dd.a = this.p[0];
                            dd.b = this.p[1];
                            this.j.a(dd);
                            this.k[this.l] = dd;
                            this.v.put(dd, new df(this, this.l));
                            ++this.l;
                            final dd dd2 = new dd(this);
                            dd2.a = this.p[1];
                            dd2.b = this.p[2];
                            this.j.a(dd2);
                            this.k[this.l] = dd2;
                            this.v.put(dd2, new df(this, this.l));
                            ++this.l;
                            final dd dd3 = new dd(this);
                            dd3.a = this.p[2];
                            dd3.b = this.p[0];
                            this.j.a(dd3);
                            this.k[this.l] = dd3;
                            this.v.put(dd3, new df(this, this.l));
                            ++this.l;
                            this.u.put(new dd(this, this.p[0], this.p[1]), new df(this, this.p[2]));
                            this.u.put(new dd(this, this.p[2], this.p[0]), new df(this, this.p[1]));
                            this.u.put(new dd(this, this.p[1], this.p[2]), new df(this, this.p[0]));
                            ++w2;
                            if (l) {
                                continue;
                            }
                            break;
                        }
                    }
                    ++n3;
                }
                if (n3 < this.c) {
                    continue;
                }
                break;
            }
            n10 = 0;
            if (l) {
                continue;
            }
            break;
        }
        int n11;
        int n12;
        while (true) {
            int n13;
            int n14;
            Label_1296_Outer:Label_1376_Outer:
            while (true) {
                Label_1434: {
                    if (!l) {
                        break Label_1434;
                    }
                    n11 = this.n[n10 * 2];
                    n12 = this.n[n10 * 2 + 1];
                    n13 = 0;
                    while (true) {
                        while (true) {
                            Label_1356: {
                                if (!l) {
                                    break Label_1356;
                                }
                                this.p = this.z[n13];
                                if (this.p[0] == n11) {
                                    this.p[0] = n12;
                                }
                                if (this.p[1] == n11) {
                                    this.p[1] = n12;
                                }
                                if (this.p[2] == n11) {
                                    this.p[2] = n12;
                                }
                                ++n13;
                            }
                            if (n13 < w2) {
                                continue Label_1296_Outer;
                            }
                            break;
                        }
                        n14 = 0;
                        if (l) {
                            continue Label_1376_Outer;
                        }
                        break;
                    }
                    while (true) {
                        Label_1425: {
                            if (!l) {
                                break Label_1425;
                            }
                            this.p = array[n14];
                            if (this.p[0] == n11) {
                                this.p[0] = n12;
                            }
                            if (this.p[1] == n11) {
                                this.p[1] = n12;
                            }
                            ++n14;
                        }
                        if (n14 < n) {
                            continue;
                        }
                        break;
                    }
                    ++n10;
                }
                if (n10 < this.f) {
                    continue;
                }
                break;
            }
            n11 = this.f * 2;
            n12 = 0;
            if (l) {
                continue;
            }
            break;
        }
        int d;
        while (true) {
            int n15;
            int n16;
            int n17;
            int[] p4;
            int n18;
            int[] p5;
            int n19;
            int[] p6;
            int n20;
            int n21;
            int[] p7;
            int n22;
            int[] p8;
            int n23;
            Label_1544_Outer:Label_1567_Outer:Label_1578_Outer:Label_1664_Outer:Label_1672_Outer:
            while (true) {
                Label_1734: {
                    if (!l) {
                        break Label_1734;
                    }
                    d = this.n[n12];
                    n15 = x * 3 - 3;
                    n16 = d * 3;
                    while (true) {
                        while (true) {
                            Label_1547: {
                                if (!l) {
                                    break Label_1547;
                                }
                                this.b[n16] = this.b[n16 + 3];
                                this.b[n16 + 1] = this.b[n16 + 4];
                                this.b[n16 + 2] = this.b[n16 + 5];
                                n16 += 3;
                            }
                            if (n16 < n15) {
                                continue Label_1544_Outer;
                            }
                            break;
                        }
                        n17 = 0;
                        if (l) {
                            continue Label_1567_Outer;
                        }
                        break;
                    }
                    while (true) {
                        while (true) {
                            Label_1644: {
                                if (!l) {
                                    break Label_1644;
                                }
                                this.p = this.z[n17];
                                if (this.p[0] >= d) {
                                    p4 = this.p;
                                    n18 = 0;
                                    --p4[n18];
                                }
                                if (this.p[1] >= d) {
                                    p5 = this.p;
                                    n19 = 1;
                                    --p5[n19];
                                }
                                if (this.p[2] >= d) {
                                    p6 = this.p;
                                    n20 = 2;
                                    --p6[n20];
                                }
                                ++n17;
                            }
                            if (n17 < w2) {
                                continue Label_1578_Outer;
                            }
                            break;
                        }
                        n21 = 0;
                        if (l) {
                            continue Label_1664_Outer;
                        }
                        break;
                    }
                    while (true) {
                        while (true) {
                            Label_1717: {
                                if (!l) {
                                    break Label_1717;
                                }
                                this.p = array[n21];
                                if (this.p[0] >= d) {
                                    p7 = this.p;
                                    n22 = 0;
                                    --p7[n22];
                                }
                                if (this.p[1] >= d) {
                                    p8 = this.p;
                                    n23 = 1;
                                    --p8[n23];
                                }
                                ++n21;
                            }
                            if (n21 < n) {
                                continue Label_1672_Outer;
                            }
                            break;
                        }
                        --x;
                        if (l) {
                            continue;
                        }
                        break;
                    }
                    n12 += 2;
                }
                if (n12 < n11) {
                    continue;
                }
                break;
            }
            this.w = w2;
            this.x = x;
            this.y = new c8[this.x];
            d = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_1882: {
                    if (!l) {
                        break Label_1882;
                    }
                    this.y[d] = new c8();
                    this.y[d].a = this.b[d * 3] * this.g;
                    this.y[d].b = this.b[d * 3 + 1] * this.h;
                    this.y[d].c = this.b[d * 3 + 2] * this.i;
                    this.y[d].d = d;
                    ++d;
                }
                if (d < x) {
                    continue;
                }
                break;
            }
            this.a = null;
            this.b = null;
            this.k = null;
            this.n = null;
            this.p = null;
            this.u = null;
            this.v = null;
            this.q = null;
            this.r = null;
            this.s = null;
            this.t = null;
            if (!l) {
                return array;
            }
            continue;
        }
    }
}
