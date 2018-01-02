import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class p
{
    static float a;
    int b;
    int c;
    int d;
    int[] e;
    r f;
    static int g;
    int[] h;
    float[] i;
    float[] j;
    int[] k;
    int[] l;
    float[] m;
    int n;
    float p;
    int[] q;
    float[] r;
    public boolean s;
    int t;
    int u;
    int v;
    float w;
    float x;
    int[] y;
    int z;
    int aa;
    static boolean ab;
    static boolean ac;
    static boolean ad;
    static boolean ae;
    static boolean af;
    static boolean ag;
    static boolean ah;
    static boolean ai;
    static boolean aj;
    static boolean ak;
    static boolean al;
    static boolean am;
    static boolean an;
    static boolean ao;
    protected float ap;
    protected float aq;
    protected float ar;
    protected float as;
    protected float at;
    protected float au;
    protected float av;
    protected float aw;
    private int ax;
    g[] ay;
    private int[] az;
    private int[] a0;
    float[] a1;
    int a2;
    private int[] a3;
    private j a4;
    private int a5;
    private int a6;
    private int a7;
    private int a8;
    int a9;
    int ba;
    q bb;
    int bc;
    int bd;
    int be;
    int bf;
    
    p(final blaze3d blaze3d) {
        this.b = 0;
        this.l = null;
        this.n = 0;
        this.s = false;
        this.y = null;
        this.z = 0;
        this.aa = 0;
        this.bb = new q();
        this.f = new r(blaze3d);
    }
    
    void a() {
        this.e = null;
        this.i = null;
        this.k = null;
        this.h = null;
        this.ay = null;
        this.az = null;
        this.a0 = null;
        this.a1 = null;
        this.a3 = null;
        if (this.bb != null) {
            this.bb.c();
        }
        this.bb = null;
        this.a4 = null;
        if (this.f != null) {
            this.f.b();
        }
        this.f = null;
    }
    
    void a(final l l) {
        this.c = l.c;
        this.d = l.d;
        this.e = l.b;
        final int c = this.c;
        this.t = c;
        this.u = c;
        this.v = this.d;
        this.k = this.e;
        this.i = new float[this.u * this.v];
        this.j = new float[(this.u * this.v >> 5) + 1];
        this.h = new int[(this.u * this.v >> 5) + 1];
    }
    
    private void a(final int ax) {
        this.ax = ax;
        this.a0 = null;
        this.a0 = new int[this.ax];
    }
    
    float b(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.t || n2 > this.v) {
            return 0.0f;
        }
        final int n3 = n + n2 * this.u;
        if (n3 > this.i.length) {
            return -1.0f;
        }
        final float a = this.f.a(n3);
        if (a == 0.0f) {
            return 0.0f;
        }
        return 1.0f / a;
    }
    
    final boolean a(final float[] array, final float[] array2, final int n, float n2, float n3, float n4, float n5, float n6, float n7, float n8, float n9, float n10) {
        final float n11 = (float)Math.sqrt((n2 * n2 + n3 * n3 + n4 * n4 + n5 * n5 + n6 * n6 + n7 * n7 + n8 * n8 + n9 * n9 + n10 * n10) / 3.0f);
        if (n11 < 1.0E-5) {
            return false;
        }
        final float n12 = 1.0f / n11;
        n2 *= n12;
        n3 *= n12;
        n4 *= n12;
        n5 *= n12;
        n6 *= n12;
        n7 *= n12;
        n8 *= n12;
        n9 *= n12;
        n10 *= n12;
        final float n13 = 1.01f;
        final float n14 = 0.99f;
        final float n15 = n2 * n2 + n3 * n3 + n4 * n4;
        if (n15 > n13 || n15 < n14) {
            return false;
        }
        final float n16 = n5 * n5 + n6 * n6 + n7 * n7;
        if (n16 > n13 || n16 < n14) {
            return false;
        }
        final float n17 = n8 * n8 + n9 * n9 + n10 * n10;
        if (n17 > n13 || n17 < n14) {
            return false;
        }
        final float n18 = n2 * n2 + n5 * n5 + n8 * n8;
        if (n18 > n13 || n18 < n14) {
            return false;
        }
        final float n19 = n3 * n3 + n6 * n6 + n9 * n9;
        if (n19 > n13 || n19 < n14) {
            return false;
        }
        final float n20 = n4 * n4 + n7 * n7 + n10 * n10;
        if (n20 > n13 || n20 < n14) {
            return false;
        }
        for (int i = 0; i < n; i += 3) {
            final float n21 = array[i];
            final float n22 = array[i + 1];
            final float n23 = array[i + 2];
            array2[i] = -(n21 * n5 + n22 * n6 + n23 * n7);
            array2[i + 1] = n21 * n2 + n22 * n3 + n23 * n4;
            array2[i + 2] = n21 * n8 + n22 * n9 + n23 * n10;
        }
        return true;
    }
    
    final void b(final float[] array, final float[] array2, final int n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10) {
        final float n11 = n2 * n10 - n7 * n9;
        final float n12 = n7 * n8 - n5 * n10;
        final float n13 = n5 * n9 - n8 * n6;
        final float n14 = n4 * n9 - n10 * n3;
        final float n15 = n10 * n2 - n4 * n8;
        final float n16 = n8 * n3 - n2 * n9;
        final float n17 = n7 * n3 - n4 * n6;
        final float n18 = n5 * n4 - n7 * n2;
        final float n19 = n2 * n6 - n5 * n3;
        for (int i = 0; i < n; i += 3) {
            final float n20 = array[i];
            final float n21 = array[i + 1];
            final float n22 = array[i + 2];
            final float n23 = n20 * n11 + n21 * n12 + n22 * n13;
            final float n24 = n20 * n14 + n21 * n15 + n22 * n16;
            final float n25 = n20 * n17 + n21 * n18 + n22 * n19;
            final float n26 = 1.0f / (float)Math.sqrt(n23 * n23 + n24 * n24 + n25 * n25);
            array2[i] = -n24 * n26;
            array2[i + 1] = n23 * n26;
            array2[i + 2] = n25 * n26;
        }
    }
    
    final int a(final float[] array, final float[] array2, final int n, final float[] array3, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13) {
        int n14 = 0;
        float n20;
        for (int i = 0; i < n; array3[i++] = n20) {
            final float n15 = array[i];
            final float n16 = array[i + 1];
            final float n17 = array[i + 2];
            final float n18 = n15 * n2 + n16 * n3 + n17 * n4 + n5;
            final float n19 = n15 * n6 + n16 * n7 + n17 * n8 + n9;
            array2[i] = n18;
            array2[i + 1] = n19;
            if ((n20 = n15 * n10 + n16 * n11 + n17 * n12 + n13) >= this.aw) {
                array3[i] = n18 * this.as / n20 + this.x;
                final int ba = (int)array3[i++];
                this.ba = ba;
                if (ba - 2 < this.bd) {
                    this.bd = this.ba - 2;
                }
                if (this.ba + 2 > this.bf) {
                    this.bf = this.ba + 2;
                }
                array3[i] = n19 * this.ar / n20 + this.w;
                final int a9 = (int)array3[i++];
                this.a9 = a9;
                if (a9 - 2 < this.bc) {
                    this.bc = this.a9 - 2;
                }
                if (this.a9 + 2 > this.be) {
                    this.be = this.a9 + 2;
                }
            }
            else {
                i += 2;
                ++n14;
            }
            array2[i] = n20;
        }
        return n14;
    }
    
    boolean a(final g g, final g g2, final boolean b) {
        ++p.g;
        boolean b2 = false;
        if (this.e == null) {
            return false;
        }
        System.currentTimeMillis();
        try {
            final int n = this.u * this.v;
            if (this.n < n) {
                this.l = new int[1 + (n >> 5)];
                this.m = new float[n << 1];
                this.n = n;
            }
            boolean i = g2.i();
            final a5 e = g2.q.e();
            this.av = 1.0E9f;
            this.aw = 1.0E-4f;
            p.a = this.aw;
            this.ap = g2.r;
            this.aq = this.ap;
            final Point point = new Point((int)(g2.s * 20.0f), (int)(g2.s * 20.0f));
            final ac d = g.d;
            d.a(point);
            final float n2 = (point.x - d.e) / 8.0f;
            final float n3 = (point.y - d.f) / 8.0f;
            this.ar = n2 / this.ap;
            this.as = -n3 / this.aq;
            this.at = this.ar / this.aw;
            this.au = this.as / this.aw;
            this.w = g.d.e / 4.0f;
            this.x = g.d.f / 4.0f;
            final float n4 = this.ap * (this.c - this.w) / n2;
            final float n5 = this.ap * this.w / n2;
            final float n6 = this.aq * this.x / n3;
            final float n7 = this.aq * (this.d - this.x) / n3;
            final float n8 = (float)Math.sqrt(1.0f + n4 * n4);
            final float n9 = (float)Math.sqrt(1.0f + n5 * n5);
            final float n10 = (float)Math.sqrt(1.0f + n6 * n6);
            final float n11 = (float)Math.sqrt(1.0f + n7 * n7);
            this.ax = g.e();
            g.a(this.ay = new g[this.ax], 0);
            this.a(this.ax);
            int n12 = 0;
            float n13 = 1.0E12f;
            float n14 = 0.0f;
            for (int j = 0; j < this.ax; ++j) {
                this.ay[j].as.a("onLocalSpace", null);
            }
            boolean b3 = false;
            for (int k = 0; k < this.ax; ++k) {
                final g g3 = this.ay[k];
                i |= g3.i();
                if (g3.u) {
                    g3.t = g3.q.b(e);
                    if (g3.j() > this.aa) {
                        b3 = true;
                    }
                    float l = 0.0f;
                    if (g3.g != null && g3.g.d != null) {
                        l = g3.g.d.l.i;
                    }
                    final float n15 = l * (float)Math.sqrt((float)new a5(g3.t.b, g3.t.e, g3.t.h, g3.t.c, g3.t.f, g3.t.i, g3.t.d, g3.t.g, g3.t.j, 0.0f, 0.0f, 0.0f).b(g3.t).f());
                    final float m = g3.t.m;
                    final float n16 = m - n15;
                    final float n17 = m + n15;
                    if (n16 < n13) {
                        n13 = n16;
                    }
                    if (n17 > n14) {
                        n14 = n17;
                    }
                    if (g3.t.k - n4 * m < n15 * n8 && -g3.t.k - n5 * m < n15 * n9 && g3.t.l - n6 * m < n15 * n10 && -g3.t.l - n7 * m < n15 * n11 && m + n15 > this.aw && m - n15 < this.av) {
                        this.a0[n12++] = k;
                    }
                }
            }
            this.p = 1.0f / n13 - 1.0f / (n13 + (n14 - n13) * 0.01f);
            if (this.p < 0.0f) {
                this.p = -this.p;
            }
            if (p.ab || i || b3 || b) {
                blaze3d.a();
                this.aa = blaze3d.c;
                b2 = true;
                this.bc = this.t - 1;
                this.bd = this.v - 1;
                this.be = 0;
                this.bf = 0;
                this.bb.a(n13 * 3.0f, n14 * 3.0f);
                this.b = 0;
                this.f.a(this.u, this.v, this.k, this.i, this.h);
                for (int n18 = 0; n18 <= n >> 5; ++n18) {
                    this.l[n18] = 0;
                }
                a(this.e, 0);
                a(this.i, 0.0f);
                a(this.j, 1.0f / this.aw);
                a(this.h, 0);
                if (this.s) {
                    if (this.q == null || this.u * this.v != this.q.length) {
                        this.q = new int[this.u * this.v];
                        this.r = new float[this.u * this.v];
                    }
                    a(this.r, 0.0f);
                }
                else {
                    this.q = null;
                    this.r = null;
                }
                for (int n19 = 0; n19 < n12; ++n19) {
                    final g g4 = this.ay[this.a0[n19]];
                    final i g5 = g4.g;
                    if (g5 != null) {
                        if (g5.f != null) {
                            final float[] ac = g4.ac;
                            if (this.z < g4.g.f.b) {
                                this.z = g4.g.f.b * 2;
                                this.y = new int[this.z];
                            }
                            final float[] ac2 = g4.ac;
                            final a5 t;
                            final float b4 = (t = g4.t).b;
                            final float c = t.c;
                            final float d2 = t.d;
                            final float e2 = t.e;
                            final float f = t.f;
                            final float g6 = t.g;
                            final float h = t.h;
                            final float i2 = t.i;
                            final float j2 = t.j;
                            final float k2 = t.k;
                            final float l2 = t.l;
                            final float m2 = t.m;
                            float n20 = 3.402823E38f;
                            float n21 = 3.402823E38f;
                            float n22 = Float.MIN_VALUE;
                            float n23 = Float.MIN_VALUE;
                            final float[] i3 = g4.i;
                            int n24 = 0;
                            while (n24 < 24) {
                                final float n25 = i3[n24++];
                                final float n26 = i3[n24++];
                                final float n27 = i3[n24++];
                                float aw;
                                if ((aw = n25 * d2 + n26 * g6 + n27 * j2 + m2) < this.aw) {
                                    aw = this.aw;
                                }
                                final float n28;
                                if ((n28 = (n25 * b4 + n26 * e2 + n27 * h + k2) * this.ar / aw + this.w) < n20) {
                                    n20 = n28;
                                }
                                if (n28 > n22) {
                                    n22 = n28;
                                }
                                final float n29;
                                if ((n29 = (n25 * c + n26 * f + n27 * i2 + l2) * this.as / aw + this.x) < n21) {
                                    n21 = n29;
                                }
                                if (n29 > n23) {
                                    n23 = n29;
                                }
                            }
                            this.a(g5.e.a, g4.ab, g5.e.b * 3, ac, c, f, i2, l2, b4, e2, h, k2, d2, g6, j2, m2);
                            if (this.bc < 0) {
                                this.bc = 0;
                            }
                            else if (this.be >= this.t) {
                                this.be = this.t - 1;
                            }
                            if (this.bd < 0) {
                                this.bd = 0;
                            }
                            else if (this.bf >= this.v) {
                                this.bf = this.v - 1;
                            }
                            final float[] i4 = g5.i;
                            final float[] ad = g4.ad;
                            final int n30 = g5.g * 3;
                            if (!this.a(i4, ad, n30, b4, e2, h, c, f, i2, d2, g6, j2)) {
                                this.b(i4, ad, n30, b4, e2, h, c, f, i2, d2, g6, j2);
                            }
                            for (int n31 = 0; n31 < g5.l; ++n31) {
                                this.a2 = 0;
                                this.a6 = 0;
                                this.a5 = 0;
                                final a9 a9 = g5.m[n31];
                                this.a4 = g5.n[n31];
                                if (this.a4 != null) {
                                    if (this.a4.b != null) {
                                        this.a4.b.a(this);
                                        final float[][] array = g5.p[n31];
                                        this.a4.a(g4, a9, array);
                                        final int n32 = a9.b * 9;
                                        this.a3 = g5.f.a;
                                        this.a4.b.ac = this.l;
                                        this.a4.b.ad = this.m;
                                        this.a7 = 0;
                                        this.a8 = 0;
                                        final c9[] e3 = a9.e;
                                        final int[] c2 = a9.c;
                                        final int a10 = this.bb.a(g4, ac, this.a3, array, this.a4.b, a9);
                                        while (this.a8 < a9.b) {
                                            final int a11 = e3[this.a8].a;
                                            final int n33 = a9.c[this.a8] * 9;
                                            final float n34 = ac[this.a3[n33] + 1];
                                            final float n35 = ac[this.a3[n33]];
                                            if (this.a4.b.d) {
                                                if ((ac[this.a3[n33 + 2] + 1] - n34) * (ac[this.a3[n33 + 1]] - n35) > (ac[this.a3[n33 + 1] + 1] - n34) * (ac[this.a3[n33 + 2]] - n35)) {
                                                    this.bb.a(n33, ac[this.a3[n33] + 2] + ac[this.a3[n33 + 1] + 2] + ac[this.a3[n33 + 2] + 2], a10, 2, true, this.a8);
                                                    this.y[a11] = 1;
                                                }
                                                else if (this.a4.b.f) {
                                                    this.y[a11] = 0;
                                                }
                                                else {
                                                    this.bb.a(n33, ac[this.a3[n33] + 2] + ac[this.a3[n33 + 1] + 2] + ac[this.a3[n33 + 2] + 2], a10, 1, true, this.a8);
                                                    this.y[a11] = 1;
                                                }
                                            }
                                            else if (!this.a4.b.f || (ac[this.a3[n33 + 2] + 1] - n34) * (ac[this.a3[n33 + 1]] - n35) > (ac[this.a3[n33 + 1] + 1] - n34) * (ac[this.a3[n33 + 2]] - n35)) {
                                                this.bb.a(n33, ac[this.a3[n33] + 2] + ac[this.a3[n33 + 1] + 2] + ac[this.a3[n33 + 2] + 2], a10, 0, false, this.a8);
                                                this.y[a11] = 1;
                                            }
                                            else {
                                                this.y[a11] = 0;
                                            }
                                            ++this.a8;
                                        }
                                    }
                                }
                            }
                            for (int n36 = 0; n36 < g4.g.r; ++n36) {
                                int d3 = -1;
                                int e4 = -1;
                                final int a12 = g4.g.q[n36].a;
                                float a13 = 1.0E10f;
                                if (a12 != 3) {
                                    d3 = g4.g.q[n36].d;
                                    if (a12 == 0) {
                                        if (this.y[d3] == 0) {
                                            continue;
                                        }
                                    }
                                    else {
                                        e4 = g4.g.q[n36].e;
                                        if (a12 == 1) {
                                            if (this.y[d3] == this.y[e4]) {
                                                continue;
                                            }
                                        }
                                        else if (this.y[d3] == 0 && this.y[e4] == 0) {
                                            continue;
                                        }
                                    }
                                }
                                final int b5 = g4.g.q[n36].b;
                                final int c3 = g4.g.q[n36].c;
                                final float n37 = ac2[b5 * 3] + 0.5f;
                                final float n38 = ac2[b5 * 3 + 1] + 0.5f;
                                final float n39 = ac2[b5 * 3 + 2];
                                final float n40 = ac2[c3 * 3] + 0.5f;
                                final float n41 = ac2[c3 * 3 + 1] + 0.5f;
                                final float n42 = ac2[c3 * 3 + 2];
                                if (d3 >= 0 && this.y[d3] == 1) {
                                    final int n43 = d3 * 9;
                                    final int n44 = g4.g.f.a[n43];
                                    final int n45 = g4.g.f.a[n43 + 1];
                                    final int n46 = g4.g.f.a[n43 + 2];
                                    a13 = this.a(ac2[n44 + 1], ac2[n44], 1.0f / ac2[n44 + 2], ac2[n45 + 1], ac2[n45], 1.0f / ac2[n45 + 2], ac2[n46 + 1], ac2[n46], 1.0f / ac2[n46 + 2]);
                                }
                                if (e4 >= 0 && this.y[e4] == 1) {
                                    final int n47 = e4 * 9;
                                    final int n48 = g4.g.f.a[n47];
                                    final int n49 = g4.g.f.a[n47 + 1];
                                    final int n50 = g4.g.f.a[n47 + 2];
                                    final float a14 = this.a(ac2[n48 + 1], ac2[n48], 1.0f / ac2[n48 + 2], ac2[n49 + 1], ac2[n49], 1.0f / ac2[n49 + 2], ac2[n50 + 1], ac2[n50], 1.0f / ac2[n50 + 2]);
                                    if (a14 > a13) {
                                        a13 = a14;
                                    }
                                }
                                this.a(n38, n37, 1.0f / n39, n41, n40, 1.0f / n42, a13);
                            }
                        }
                    }
                }
                this.bb.a(this.f);
                this.f.a(this.k, this.i);
            }
        }
        catch (Exception ex) {}
        return b2;
    }
    
    static final void a(final int[] array, final int n) {
        if (array.length > 0) {
            int n2 = array.length - 1;
            int i = 1;
            int n3 = 1;
            array[0] = n;
            while (i < n2) {
                System.arraycopy(array, 0, array, n3, i);
                n2 -= i;
                n3 += i;
                i <<= 1;
            }
            System.arraycopy(array, 0, array, n3, n2);
        }
    }
    
    static final void a(final float[] array, final float n) {
        if (array.length > 0) {
            int n2 = array.length - 1;
            int i = 1;
            int n3 = 1;
            array[0] = n;
            while (i < n2) {
                System.arraycopy(array, 0, array, n3, i);
                n2 -= i;
                n3 += i;
                i <<= 1;
            }
            System.arraycopy(array, 0, array, n3, n2);
        }
    }
    
    float a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        final float n10 = n4 - n;
        final float n11 = n5 - n2;
        final float n12 = n7 - n;
        final float n13 = n8 - n2;
        final float n14 = n10 * n13 - n11 * n12;
        if (n14 > 0.001f || n14 < -0.001f) {
            final float n15 = 1.0f / n14;
            final float n16 = n13 * n15;
            final float n17 = -n11 * n15;
            final float n18 = -n12 * n15;
            final float n19 = n10 * n15;
            float n20 = (n6 - n3) * n16 + (n9 - n3) * n17;
            float n21 = (n6 - n3) * n18 + (n9 - n3) * n19;
            if (n20 < 0.0f) {
                n20 = -n20;
            }
            if (n21 < 0.0f) {
                n21 = -n21;
            }
            return (n20 > n21) ? n20 : n21;
        }
        return 1.0E10f;
    }
    
    void a(float n, float n2, float n3, float n4, float n5, float n6, final float n7) {
        ++this.b;
        if (n < 0.0f && n4 < 0.0f) {
            return;
        }
        if (n2 < 0.0f && n5 < 0.0f) {
            return;
        }
        final float n8 = this.t - 1.0f;
        if (n > n8 && n4 > n8) {
            return;
        }
        final float n9 = this.v - 1.0f;
        if (n2 > n9 && n5 > n9) {
            return;
        }
        if (n < 0.0f != n4 < 0.0f) {
            final float n10 = n4 / (n4 - n);
            final float n11 = n5 + (n2 - n5) * n10;
            final float n12 = n6 + (n3 - n6) * n10;
            if (n < 0.0f) {
                n = 0.0f;
                n2 = n11;
                n3 = n12;
            }
            else {
                n4 = 0.0f;
                n5 = n11;
                n6 = n12;
            }
            if (n2 < 0.0f && n5 < 0.0f) {
                return;
            }
            if (n2 > n9 && n5 > n9) {
                return;
            }
        }
        if (n2 < 0.0f != n5 < 0.0f) {
            final float n13 = n5 / (n5 - n2);
            final float n14 = n4 + (n - n4) * n13;
            final float n15 = n6 + (n3 - n6) * n13;
            if (n2 < 0.0f) {
                n2 = 0.0f;
                n = n14;
                n3 = n15;
            }
            else {
                n5 = 0.0f;
                n4 = n14;
                n6 = n15;
            }
            if (n > n8 && n4 > n8) {
                return;
            }
            if (n < 0.0f && n4 < 0.0f) {
                return;
            }
        }
        if (n > n8 != n4 > n8) {
            final float n16 = (n4 - n8) / (n4 - n);
            final float n17 = n5 + (n2 - n5) * n16;
            final float n18 = n6 + (n3 - n6) * n16;
            if (n > n8) {
                n = n8;
                n2 = n17;
                n3 = n18;
            }
            else {
                n4 = n8;
                n5 = n17;
                n6 = n18;
            }
            if (n2 < 0.0f && n5 < 0.0f) {
                return;
            }
            if (n2 > n9 && n5 > n9) {
                return;
            }
        }
        if (n2 > n9 != n5 > n9) {
            final float n19 = (n5 - n9) / (n5 - n2);
            final float n20 = n4 + (n - n4) * n19;
            final float n21 = n6 + (n3 - n6) * n19;
            if (n2 > n9) {
                n2 = n9;
                n = n20;
                n3 = n21;
            }
            else {
                n5 = n9;
                n4 = n20;
                n6 = n21;
            }
            if (n > n8 && n4 > n8) {
                return;
            }
            if (n < 0.0f && n4 < 0.0f) {
                return;
            }
        }
        final int n22 = (int)n;
        final int n23 = (int)n2;
        final int n24 = (int)n4;
        final int n25 = (int)n5;
        float n26 = n22 - n;
        float n27 = n23 - n2;
        final float n28 = n4 - n;
        final float n30;
        final float n29 = n30 = n5 - n2;
        final float n31 = -n28;
        int n32;
        int n33;
        if (n28 > 0.0f) {
            ++n26;
            n32 = 1;
            n33 = n24 - n22;
        }
        else {
            n32 = -1;
            n33 = n22 - n24;
        }
        int n34;
        int n35;
        if (n29 > 0.0f) {
            ++n27;
            n34 = 1;
            n35 = n25 - n23;
        }
        else {
            n34 = -1;
            n35 = n23 - n25;
        }
        final int n36 = n33 + n35;
        float n37 = n26 * n30 + n27 * n31;
        final float n38 = n32 * n30;
        final float n39 = n34 * n31;
        float n40;
        int n41;
        int n42;
        float n43;
        int n44;
        int n45;
        if (n38 > 0.0f || (n38 == 0.0f && n32 == -1)) {
            n40 = n38;
            n41 = n32;
            n42 = 0;
            n43 = n39;
            n44 = 0;
            n45 = n34;
        }
        else {
            n43 = n38;
            n44 = n32;
            n45 = 0;
            n40 = n39;
            n41 = 0;
            n42 = n34;
        }
        int n46 = n22 + n23 * this.u;
        final int n47 = n44 + n45 * this.u;
        final int n48 = n41 + n42 * this.u;
        float n49 = n3 - n7;
        float n50 = n3 + n7;
        final float n51 = (n6 - n3) / n36;
        for (int i = n36; i >= 0; --i) {
            final int n52 = n46 >> 5;
            final int n53 = 1 << (n46 & 0x1F);
            final int n54 = this.l[n52];
            if ((n54 & n53) != 0x0) {
                int n55 = n46 << 1;
                if (n49 < this.m[n55]) {
                    this.m[n55] = n49;
                }
                ++n55;
                if (n50 > this.m[n55]) {
                    this.m[n55] = n50;
                }
            }
            else {
                this.l[n52] = (n54 | n53);
                final int n56 = n46 << 1;
                this.m[n56] = n49;
                this.m[n56 + 1] = n50;
            }
            if (n37 > 0.0f) {
                n46 += n47;
                n37 += n43;
            }
            else {
                n46 += n48;
                n37 += n40;
            }
            n49 += n51;
            n50 += n51;
        }
    }
    
    static {
        p.a = 1.0E-4f;
        p.g = 0;
        p.ab = false;
        p.ac = false;
        p.ad = false;
        p.ae = false;
        p.af = false;
        p.ag = false;
        p.ah = false;
        p.ai = false;
        p.aj = false;
        p.ak = false;
        p.al = false;
        p.am = false;
        p.an = false;
        p.ao = false;
    }
}
