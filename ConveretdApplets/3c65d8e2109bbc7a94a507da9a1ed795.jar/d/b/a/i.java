// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class i extends c implements n
{
    public static final int[] a2;
    public static final int[] a7;
    public static final int[] bl;
    protected n bn;
    private float[] bw;
    private float[] bs;
    private float[] bu;
    private float[] bt;
    private float[] bz;
    private float[] bx;
    private float[] bf;
    private float[] bk;
    private float[] bp;
    private float[] a9;
    private float[] bi;
    private float[] bg;
    private float[] a1;
    private float[] bc;
    private float[] a5;
    private float[] a4;
    private float[] bv;
    private float[] a3;
    protected int bb;
    private boolean a8;
    protected int bj;
    protected int be;
    protected int ba;
    protected int by;
    protected float bh;
    protected float bm;
    protected float bq;
    protected float bo;
    protected float a6;
    protected int br;
    protected int bd;
    
    public void goto() {
        this.bn = new l();
        ((l)this.bn).for();
        super.goto();
        this.a(160, 40, 8, 640, 0.9f);
        this.a8 = false;
        this.bb = 5;
        this.br = 16000;
    }
    
    public void char() {
        this.bn = new i();
        ((i)this.bn).goto();
        super.char();
        this.a(320, 80, 8, 1280, 0.7f);
        this.a8 = true;
        this.bb = 2;
        this.br = 32000;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.a(n, n2, n3, n4, n5);
        this.bj = 3;
        this.be = 0;
        this.ba = 0;
        this.by = 0;
        this.bh = 8.0f;
        this.bd = this.t;
        this.bw = new float[n];
        this.bs = new float[64];
        this.bu = new float[this.o];
        this.bt = new float[n];
        this.bz = new float[n];
        this.bx = new float[n2];
        this.bf = d.b.a.s.a(this.o, n2);
        this.bk = d.b.a.s.a(n3, this.ac);
        this.bp = new float[n3];
        this.a9 = new float[n3 + 1];
        this.bi = new float[n3];
        this.bg = new float[n3];
        this.a1 = new float[n3];
        this.bc = new float[n3 + 1];
        this.a5 = new float[n3 + 1];
        this.a4 = new float[n3 + 1];
        this.bv = new float[n3];
        this.a3 = new float[n3];
        this.a6 = 0.0f;
    }
    
    public int if(final ad ad, final float[] array) {
        d.b.a.ae.a(array, d.b.a.ab.char, this.af, this.bw, this.ak, 64, this.bs);
        this.bn.if(ad, this.af);
        for (int i = 0; i < this.o - this.z; ++i) {
            this.ai[i] = this.ai[this.z + i];
        }
        for (int j = 0; j < this.z; ++j) {
            this.ai[this.o - this.z + j] = this.bw[j];
        }
        System.arraycopy(this.m, this.z, this.m, 0, this.X - this.z);
        final float[] new1 = this.bn.new();
        final float[] int1 = this.bn.int();
        final float[] if1 = this.bn.if();
        final boolean b = this.bn.void() == 0;
        for (int k = 0; k < this.o; ++k) {
            this.bu[k] = this.ai[k] * this.bf[k];
        }
        d.b.a.a.a(this.bu, this.a9, this.n + 1, this.o);
        final float[] a9 = this.a9;
        final int n = 0;
        ++a9[n];
        final float[] a10 = this.a9;
        final int n2 = 0;
        a10[n2] *= this.w;
        for (int l = 0; l < this.n + 1; ++l) {
            final float[] a11 = this.a9;
            final int n3 = l;
            a11[n3] *= this.bk[l];
        }
        d.b.a.a.a(this.ab, this.a9, this.bp, this.n);
        System.arraycopy(this.ab, 0, this.ab, 1, this.n);
        this.ab[0] = 1.0f;
        if (d.b.a.y.a(this.ab, this.n, this.bi, 15, 0.2f) != this.n && d.b.a.y.a(this.ab, this.n, this.bi, 11, 0.02f) != this.n) {
            for (int n4 = 0; n4 < this.n; ++n4) {
                this.bi[n4] = (float)Math.cos(3.141592653589793 * (n4 + 1) / (this.n + 1));
            }
        }
        for (int n5 = 0; n5 < this.n; ++n5) {
            this.bi[n5] = (float)Math.acos(this.bi[n5]);
        }
        float n6 = 0.0f;
        for (int n7 = 0; n7 < this.n; ++n7) {
            n6 += (this.bg[n7] - this.bi[n7]) * (this.bg[n7] - this.bi[n7]);
        }
        if ((this.be != 0 || this.ba != 0) && !b) {
            float n8 = 0.0f;
            float n9 = 0.0f;
            if (this.by != 0) {
                float n10 = 0.0f;
                if (this.bo * this.bq > 0.0f) {
                    n10 = -1.0E-5f * this.bq / (1.0f + this.a6);
                    if (n10 > 0.1f) {
                        n10 = 0.1f;
                    }
                    if (n10 < -0.1f) {
                        n10 = -0.1f;
                    }
                }
                this.bh += n10;
                if (this.bh > 10.0f) {
                    this.bh = 10.0f;
                }
                if (this.bh < 0.0f) {
                    this.bh = 0.0f;
                }
            }
            for (int n11 = 0; n11 < this.z; ++n11) {
                n8 += this.af[n11] * this.af[n11];
                n9 += this.ai[n11] * this.ai[n11];
            }
            float n12 = (float)Math.log((1.0f + n9) / (1.0f + n8));
            this.bm = this.bn.b();
            if (n12 < -4.0f) {
                n12 = -4.0f;
            }
            if (n12 > 2.0f) {
                n12 = 2.0f;
            }
            if (this.be != 0) {
                int n13 = this.bb - 1;
                this.bm += (float)(1.0 * (n12 + 2.0f));
                if (this.bm < -1.0f) {
                    this.bm = -1.0f;
                }
                while (n13 != 0) {
                    final int n14 = (int)Math.floor(this.bh);
                    float n15;
                    if (n14 == 10) {
                        n15 = d.b.a.p.else[n13][n14];
                    }
                    else {
                        n15 = (this.bh - n14) * d.b.a.p.else[n13][n14 + 1] + (1 + n14 - this.bh) * d.b.a.p.else[n13][n14];
                    }
                    if (this.bm >= n15) {
                        break;
                    }
                    --n13;
                }
                this.new(n13);
                if (this.by != 0) {
                    final int m = this.j();
                    this.bq += m - this.by;
                    this.bo = 0.95f * this.bo + 0.05f * (m - this.by);
                    ++this.a6;
                }
            }
            else {
                int bd;
                if (this.bm < 2.0) {
                    bd = 1;
                }
                else {
                    bd = this.bd;
                }
                this.t = bd;
            }
        }
        ad.a(1, 1);
        if (b) {
            ad.a(0, 3);
        }
        else {
            ad.a(this.t, 3);
        }
        if (!b && this.aa[this.t] != null) {
            this.aa[this.t].else.a(this.bi, this.v, this.n, ad);
            if (this.y != 0) {
                for (int n16 = 0; n16 < this.n; ++n16) {
                    this.bg[n16] = this.bi[n16];
                }
                for (int n17 = 0; n17 < this.n; ++n17) {
                    this.W[n17] = this.v[n17];
                }
            }
            final float[] array2 = new float[this.n];
            final float[] array3 = new float[this.T];
            final float[] array4 = new float[this.T];
            for (int n18 = 0; n18 < this.Q; ++n18) {
                float n19 = 0.0f;
                float n20 = 0.0f;
                final int n22;
                final int n21 = n22 = this.T * n18;
                final int n23 = this.K + n21;
                final int n24 = n21;
                final int n25 = n21;
                final float n26 = (1.0f + n18) / this.Q;
                for (int n27 = 0; n27 < this.n; ++n27) {
                    this.a1[n27] = (1.0f - n26) * this.bg[n27] + n26 * this.bi[n27];
                }
                for (int n28 = 0; n28 < this.n; ++n28) {
                    this.B[n28] = (1.0f - n26) * this.W[n28] + n26 * this.v[n28];
                }
                d.b.a.y.a(this.a1, this.n, 0.05f);
                d.b.a.y.a(this.B, this.n, 0.05f);
                for (int n29 = 0; n29 < this.n; ++n29) {
                    this.a1[n29] = (float)Math.cos(this.a1[n29]);
                }
                for (int n30 = 0; n30 < this.n; ++n30) {
                    this.B[n30] = (float)Math.cos(this.B[n30]);
                }
                this.ad.a(this.a1, this.bc, this.n);
                this.ad.a(this.B, this.D, this.n);
                d.b.a.ae.a(this.J, this.bc, this.a5, this.n);
                d.b.a.ae.a(this.I, this.bc, this.a4, this.n);
                float n31 = 0.0f;
                float n32 = 1.0f;
                this.r[n18] = 0.0f;
                for (int n33 = 0; n33 <= this.n; ++n33) {
                    n31 += n32 * this.D[n33];
                    n32 = -n32;
                    final float[] r = this.r;
                    final int n34 = n18;
                    r[n34] += this.D[n33];
                }
                final float n35 = Math.abs(0.01f + 1.0f / (Math.abs(n31) + 0.01f)) / (0.01f + Math.abs(1.0f / (Math.abs(new1[n18]) + 0.01f)));
                final boolean b2 = n35 < 5.0f;
                d.b.a.ae.a(this.ai, n22, this.D, this.m, n23, this.T, this.n, this.bv);
                for (int n36 = 0; n36 < this.T; ++n36) {
                    n19 += this.m[n23 + n36] * this.m[n23 + n36];
                }
                if (this.aa[this.t].for == null) {
                    for (int n37 = 0; n37 < this.T; ++n37) {
                        n20 += if1[n21 + n37] * if1[n21 + n37];
                    }
                    int n38 = (int)Math.floor(10.5 + 8.0 * Math.log((float)Math.sqrt(n19 / (0.01f + n20)) * n35 + 1.0E-4));
                    if (n38 < 0) {
                        n38 = 0;
                    }
                    if (n38 > 31) {
                        n38 = 31;
                    }
                    ad.a(n38, 5);
                    final float n39 = (float)(0.1 * Math.exp(n38 / 9.4)) / n35;
                }
                else {
                    for (int n40 = 0; n40 < this.T; ++n40) {
                        n20 += int1[n21 + n40] * int1[n21 + n40];
                    }
                    int n41 = (int)Math.floor(0.5 + 3.7 * (Math.log((float)(Math.sqrt(1.0f + n19) * n35 / Math.sqrt((1.0f + n20) * this.T))) + 2.0));
                    if (n41 < 0) {
                        n41 = 0;
                    }
                    if (n41 > 15) {
                        n41 = 15;
                    }
                    ad.a(n41, 4);
                    final float n42 = (float)Math.exp(0.27027027027027023 * n41 - 2.0) * (float)Math.sqrt(1.0f + n20) / n35;
                    final float n43 = 1.0f / n42;
                    for (int n44 = 0; n44 < this.T; ++n44) {
                        this.m[n23 + n44] = 0.0f;
                    }
                    this.m[n23] = 1.0f;
                    d.b.a.ae.a(this.m, n23, this.D, this.a5, this.a4, array3, this.T, this.n);
                    for (int n45 = 0; n45 < this.T; ++n45) {
                        this.m[n23 + n45] = 0.0f;
                    }
                    for (int n46 = 0; n46 < this.n; ++n46) {
                        array2[n46] = this.R[n46];
                    }
                    d.b.a.ae.if(this.m, n23, this.D, this.m, n23, this.T, this.n, array2);
                    for (int n47 = 0; n47 < this.n; ++n47) {
                        array2[n47] = this.a3[n47];
                    }
                    d.b.a.ae.a(this.m, n23, this.a5, this.a4, this.bz, n24, this.T, this.n, array2, 0);
                    for (int n48 = 0; n48 < this.n; ++n48) {
                        array2[n48] = this.a3[n48];
                    }
                    d.b.a.ae.a(this.ai, n22, this.a5, this.a4, this.bt, n25, this.T, this.n, array2, 0);
                    for (int n49 = 0; n49 < this.T; ++n49) {
                        this.bx[n49] = this.bt[n25 + n49] - this.bz[n24 + n49];
                    }
                    for (int n50 = 0; n50 < this.T; ++n50) {
                        this.m[n23 + n50] = 0.0f;
                    }
                    for (int n51 = 0; n51 < this.T; ++n51) {
                        final float[] bx = this.bx;
                        final int n52 = n51;
                        bx[n52] *= n43;
                    }
                    for (int n53 = 0; n53 < this.T; ++n53) {
                        array4[n53] = 0.0f;
                    }
                    this.aa[this.t].for.a(this.bx, this.D, this.a5, this.a4, this.n, this.T, array4, 0, array3, ad, this.bj + 1 >> 1);
                    for (int n54 = 0; n54 < this.T; ++n54) {
                        final float[] m2 = this.m;
                        final int n55 = n23 + n54;
                        m2[n55] += array4[n54] * n42;
                    }
                    if (this.aa[this.t].do != 0) {
                        final float[] array5 = new float[this.T];
                        for (int n56 = 0; n56 < this.T; ++n56) {
                            array5[n56] = 0.0f;
                        }
                        for (int n57 = 0; n57 < this.T; ++n57) {
                            final float[] bx2 = this.bx;
                            final int n58 = n57;
                            bx2[n58] *= 2.5;
                        }
                        this.aa[this.t].for.a(this.bx, this.D, this.a5, this.a4, this.n, this.T, array5, 0, array3, ad, this.bj + 1 >> 1);
                        for (int n59 = 0; n59 < this.T; ++n59) {
                            final float[] array6 = array5;
                            final int n60 = n59;
                            array6[n60] *= (float)(n42 * 0.4);
                        }
                        for (int n61 = 0; n61 < this.T; ++n61) {
                            final float[] m3 = this.m;
                            final int n62 = n23 + n61;
                            m3[n62] += array5[n61];
                        }
                    }
                }
                for (int n63 = 0; n63 < this.n; ++n63) {
                    array2[n63] = this.R[n63];
                }
                d.b.a.ae.if(this.m, n23, this.D, this.ai, n22, this.T, this.n, this.R);
                d.b.a.ae.a(this.ai, n22, this.a5, this.a4, this.bt, n25, this.T, this.n, this.a3, 0);
            }
            this.C.a(this.af, d.b.a.ab.char, this.aq, this.ak, 64, this.al);
            this.C.a(this.ai, d.b.a.ab.case, this.ap, this.ak, 64, this.aj);
            for (int n64 = 0; n64 < this.ak; ++n64) {
                array[n64] = 2.0f * (this.aq[n64] - this.ap[n64]);
            }
            for (int n65 = 0; n65 < this.n; ++n65) {
                this.bg[n65] = this.bi[n65];
            }
            for (int n66 = 0; n66 < this.n; ++n66) {
                this.W[n66] = this.v[n66];
            }
            this.y = 0;
            return 1;
        }
        for (int n67 = 0; n67 < this.z; ++n67) {
            this.m[this.K + n67] = (this.bt[n67] = 0.0f);
        }
        for (int n68 = 0; n68 < this.n; ++n68) {
            this.a3[n68] = 0.0f;
        }
        this.y = 1;
        d.b.a.ae.if(this.m, this.K, this.D, this.ai, 0, this.T, this.n, this.R);
        this.C.a(this.af, d.b.a.ab.char, this.aq, this.ak, 64, this.al);
        this.C.a(this.ai, d.b.a.ab.case, this.ap, this.ak, 64, this.aj);
        for (int n69 = 0; n69 < this.ak; ++n69) {
            array[n69] = 2.0f * (this.aq[n69] - this.ap[n69]);
        }
        if (b) {
            return 0;
        }
        return 1;
    }
    
    public int g() {
        return i.am[this.t] + this.bn.g();
    }
    
    public void if(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n > 10) {
            n = 10;
        }
        if (this.a8) {
            this.bn.if(n);
            this.new(i.bl[n]);
        }
        else {
            this.bn.new(i.a2[n]);
            this.new(i.a7[n]);
        }
    }
    
    public void a(final float bh) {
        this.bh = bh;
        float n = bh + 0.6f;
        if (n > 10.0f) {
            n = 10.0f;
        }
        this.bn.a(n);
        int n2 = (int)Math.floor(0.5 + bh);
        if (n2 > 10) {
            n2 = 10;
        }
        this.if(n2);
    }
    
    public void do(final boolean be) {
        this.be = (be ? 1 : 0);
        this.bn.do(be);
    }
    
    public void a(final int n) {
        this.bn.do(true);
        this.by = ((n != 0) ? 1 : 0);
        this.be = 1;
        int i;
        for (i = 10; i >= 0; --i) {
            this.if(i);
            if (this.j() <= n) {
                break;
            }
        }
        float n2 = i;
        if (n2 < 0.0f) {
            n2 = 0.0f;
        }
        this.a(n2);
        this.a6 = 0.0f;
        this.bq = 0.0f;
        this.bo = 0.0f;
    }
    
    public int j() {
        if (this.aa[this.t] != null) {
            return this.bn.j() + this.br * this.aa[this.t].int / this.z;
        }
        return this.bn.j() + this.br * 4 / this.z;
    }
    
    public void for(final int br) {
        this.br = br;
        this.bn.for(br);
    }
    
    public int e() {
        return 2 * this.bn.e() + 64 - 1;
    }
    
    public void new(int n) {
        if (n < 0) {
            n = 0;
        }
        final int n2 = n;
        this.bd = n2;
        this.t = n2;
    }
    
    public int void() {
        return this.t;
    }
    
    public void do(final int n) {
        for (int i = 10; i >= 0; --i) {
            this.if(i);
            if (this.j() <= n) {
                return;
            }
        }
    }
    
    public boolean long() {
        return this.be != 0;
    }
    
    public void for(final boolean ba) {
        this.ba = (ba ? 1 : 0);
    }
    
    public boolean c() {
        return this.ba != 0;
    }
    
    public void if(final boolean h) {
        this.H = (h ? 1 : 0);
    }
    
    public int i() {
        return this.by;
    }
    
    public float f() {
        return this.bh;
    }
    
    public void int(int bj) {
        if (bj < 0) {
            bj = 0;
        }
        if (bj > 10) {
            bj = 10;
        }
        this.bj = bj;
    }
    
    public int d() {
        return this.bj;
    }
    
    public int h() {
        return this.br;
    }
    
    public float b() {
        return this.bm;
    }
    
    static {
        a2 = new int[] { 1, 8, 2, 3, 4, 5, 5, 6, 6, 7, 7 };
        a7 = new int[] { 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4 };
        bl = new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    }
}
