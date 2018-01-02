import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class k
{
    String a;
    protected j b;
    float c;
    boolean d;
    boolean e;
    boolean f;
    protected int g;
    g h;
    protected l[] i;
    protected int[] k;
    protected int[] l;
    protected int m;
    protected int n;
    protected int p;
    boolean q;
    protected int r;
    protected int s;
    int[] t;
    int[] u;
    int v;
    int w;
    p x;
    protected float[] y;
    protected float[] z;
    protected int[] aa;
    protected int[] ab;
    int[] ac;
    float[] ad;
    r ae;
    protected int af;
    protected int ag;
    protected int ah;
    boolean ai;
    float[] aj;
    float[] ak;
    float[] al;
    protected float am;
    protected float an;
    protected float ao;
    protected float ap;
    protected float aq;
    protected float ar;
    protected float as;
    protected float at;
    protected float au;
    protected float av;
    protected float aw;
    protected float ax;
    protected int ay;
    protected int az;
    protected int a0;
    protected int a1;
    protected int a2;
    protected int a3;
    float[] a4;
    float[] a5;
    float[] a6;
    float[] a7;
    protected float a8;
    protected float a9;
    protected float ba;
    protected float bb;
    protected float bc;
    protected float bd;
    protected float be;
    protected int[] bf;
    protected int bg;
    protected int bh;
    protected int bi;
    protected int bj;
    protected int bk;
    protected int bl;
    int[] bm;
    char[] bn;
    char[] bo;
    char bp;
    char bq;
    static s br;
    static bf bs;
    static be bt;
    static int bu;
    static int bv;
    static int[] bw;
    static boolean bx;
    static float[] by;
    static float[] bz;
    static float[] b0;
    static float[] b1;
    static float[][] b2;
    static boolean b3;
    static Hashtable b4;
    private static float[] b5;
    private static float[] b6;
    private static float[] b7;
    private static float[][] b8;
    private static float[][] b9;
    private static float[][] ca;
    private static float[][] cb;
    private static float[][] cc;
    private static float[][] cd;
    private static float[][] ce;
    private static float[][] cf;
    private static float[] cg;
    private static float[] ch;
    private static float[][] ci;
    private static float[][] cj;
    private static float[][] ck;
    private static float[][] cl;
    private static float[] cm;
    private static float[] cn;
    private static float[] co;
    private static int cp;
    private static int cq;
    private static int cr;
    private static int cs;
    private static int ct;
    private static int cu;
    private static int cv;
    private static int cw;
    private static int cx;
    private static boolean cy;
    private static boolean[] cz;
    private static int c0;
    private static int c1;
    private static int c2;
    private static int c3;
    int c4;
    int c5;
    static int c6;
    
    int a() {
        return 0;
    }
    
    k() {
        this.c = 255.0f;
        this.d = false;
        this.e = false;
        this.f = true;
        this.m = 0;
        this.n = 0;
        this.p = 0;
        this.q = false;
        this.r = 0;
        this.s = 0;
        this.v = 0;
        this.w = 0;
        this.ai = false;
        this.a4 = new float[3];
        this.a5 = new float[3];
        this.a6 = new float[3];
        this.a7 = new float[3];
        this.bm = new int[3];
        this.c4 = 0;
        this.c5 = 0;
    }
    
    void a(final j b) {
        this.b = b;
        this.i = b.d;
    }
    
    abstract void a(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    abstract void a(final int p0, final float p1, final float p2, final float p3, final float p4);
    
    boolean a(final int n) {
        return false;
    }
    
    protected boolean a(final float[][] array) {
        return true;
    }
    
    void a(final float[] array, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
    }
    
    int b() {
        return 7;
    }
    
    protected void a(final l[] array) {
        if (array[0] != null && !array[0].h) {
            array[0].b(8);
        }
        if (array[1] != null && !array[1].h) {
            array[1].b(8);
        }
        if (array[3] != null && !array[3].h) {
            array[3].b(8);
        }
        final int b = this.b();
        if (array[0] != null && !array[0].h) {
            final l l = array[0];
            l.h = true;
            if ((b & 0x1) != 0x0) {
                a(l, 12, 4, 2, 4, 1);
            }
        }
        if (array[1] != null && !array[1].h) {
            array[1].h = true;
            if ((b & 0x2) != 0x0) {
                a(array[1], 200, 4, 2, 4, 1);
                array[1].b();
                this.a(array[1], 7, 24);
            }
        }
        if (this.a(2) && array[2] != null && !array[2].h) {
            array[2].h = true;
            if ((b & 0x4) != 0x0) {
                final l i = array[2];
                i.f[0] = array[2].b;
                b(i, 1);
                this.c(i, 8);
                a(i, -1879048448);
                a(i, 15, 0, 1, 1, 0);
                i.b();
                this.a(i, 7, 0);
            }
        }
    }
    
    synchronized boolean a(final String s, final float n, final String s2, final Object o) {
        if (s.equals("doubleSided")) {
            if ((int)n == 0) {
                this.f = true;
            }
            else {
                this.f = false;
            }
            return true;
        }
        return false;
    }
    
    void a(final p x) {
        if (x == null) {
            return;
        }
        this.x = x;
        this.y = x.i;
        this.z = x.j;
        this.aa = x.k;
        this.ab = x.h;
        this.af = x.v;
        this.ag = x.t;
        this.ah = x.u;
    }
    
    private void a(final int n, final int n2, final float[] array, final float[] array2, final float[][] array3) {
        k.b6[0] = array[k.cp];
        k.b6[1] = array[k.cq];
        k.b6[2] = array[k.cr];
        k.b5[0] = array[k.cp + 1];
        k.b5[1] = array[k.cq + 1];
        k.b5[2] = array[k.cr + 1];
        k.b7[0] = array[k.cp + 2];
        k.b7[1] = array[k.cq + 2];
        k.b7[2] = array[k.cr + 2];
        final float[] array4 = array3[0];
        if (array4 != null) {
            final float[] array5 = k.b8[0];
            final float[] array6 = k.b9[0];
            final float[] array7 = k.cc[0];
            final float[] array8 = k.cd[0];
            array7[0] = (array5[0] = array4[k.cs]);
            array8[0] = (array6[0] = array4[k.cs + 1]);
            array7[1] = (array5[1] = array4[k.ct]);
            array8[1] = (array6[1] = array4[k.ct + 1]);
            array7[2] = (array5[2] = array4[k.cu]);
            array8[2] = (array6[2] = array4[k.cu + 1]);
        }
        for (int i = 1; i < 3; ++i) {
            final float[] array9 = array3[i];
            if (array9 != null) {
                final float[] array10 = k.b8[i];
                final float[] array11 = k.b9[i];
                final float[] array12 = k.cc[i];
                final float[] array13 = k.cd[i];
                array12[0] = (array10[0] = array9[k.cs]);
                array13[0] = (array11[0] = array9[k.cs + 1]);
                array12[1] = (array10[1] = array9[k.ct]);
                array13[1] = (array11[1] = array9[k.ct + 1]);
                array12[2] = (array10[2] = array9[k.cu]);
                array13[2] = (array11[2] = array9[k.cu + 1]);
                final float[] array14 = k.ca[i];
                final float[] array15 = k.cb[i];
                final float[] array16 = k.ce[i];
                final float[] array17 = k.cf[i];
                array16[0] = (array14[0] = array9[k.cv]);
                array17[0] = (array15[0] = array9[k.cv + 1]);
                array16[1] = (array14[1] = array9[k.cw]);
                array17[1] = (array15[1] = array9[k.cw + 1]);
                array16[2] = (array14[2] = array9[k.cx]);
                array17[2] = (array15[2] = array9[k.cx + 1]);
            }
        }
        k.ch[0] = array2[k.cp];
        k.ch[1] = array2[k.cq];
        k.ch[2] = array2[k.cr];
        k.cg[0] = array2[k.cp + 1];
        k.cg[1] = array2[k.cq + 1];
        k.cg[2] = array2[k.cr + 1];
        for (int j = 0; j < 3; ++j) {
            final int n3 = (j == 2) ? 0 : (j + 1);
            if (k.cz[j] ^ k.cz[n3]) {
                final float n4 = (p.a - this.a6[j]) / (this.a6[n3] - this.a6[j]);
                k.cm[j] = k.cg[j] + n4 * (k.cg[n3] - k.cg[j]);
                k.cn[j] = k.ch[j] + n4 * (k.ch[n3] - k.ch[j]);
                k.co[j] = p.a;
                k.cm[j] = k.cm[j] * this.x.ar / p.a + this.x.w;
                k.cn[j] = k.cn[j] * this.x.as / p.a + this.x.x;
                if (array3[0] != null) {
                    final float[] array18 = k.b8[0];
                    final float[] array19 = k.b9[0];
                    final float[] array20 = k.ci[0];
                    final float[] array21 = k.cj[0];
                    array20[j] = array18[j] + n4 * (array18[n3] - array18[j]);
                    array21[j] = array19[j] + n4 * (array19[n3] - array19[j]);
                }
                for (int k = 1; k < 3; ++k) {
                    if (array3[k] != null) {
                        final float[] array22 = k.b8[k];
                        final float[] array23 = k.b9[k];
                        final float[] array24 = k.ci[k];
                        final float[] array25 = k.cj[k];
                        array24[j] = array22[j] + n4 * (array22[n3] - array22[j]);
                        array25[j] = array23[j] + n4 * (array23[n3] - array23[j]);
                        final float[] array26 = k.ca[k];
                        final float[] array27 = k.cb[k];
                        final float[] array28 = k.ck[k];
                        final float[] array29 = k.cl[k];
                        array28[j] = array26[j] + n4 * (array26[n3] - array26[j]);
                        array29[j] = array27[j] + n4 * (array27[n3] - array27[j]);
                    }
                }
            }
            else {
                k.cm[j] = this.a4[j];
                k.cn[j] = this.a5[j];
                k.co[j] = this.a6[j];
                if (array3[0] != null) {
                    k.ci[0][j] = k.cc[0][j];
                    k.cj[0][j] = k.cd[0][j];
                }
                for (int l = 1; l < 3; ++l) {
                    if (array3[l] != null) {
                        k.ci[l][j] = k.cc[l][j];
                        k.cj[l][j] = k.cd[l][j];
                        k.ck[l][j] = k.ce[l][j];
                        k.cl[l][j] = k.cf[l][j];
                    }
                }
            }
        }
        if (n == 1) {
            k.cy = false;
            k.c3 = 0;
            switch (n2) {
                case 1: {
                    k.c0 = 0;
                    break;
                }
                case 2: {
                    k.c0 = 1;
                    break;
                }
                case 4: {
                    k.c0 = 2;
                    break;
                }
            }
            k.c1 = ((k.c0 == 0) ? 2 : (k.c0 - 1));
            k.c2 = 3 - (k.c0 + k.c1);
            this.a4[k.c0] = k.cm[k.c0];
            this.a5[k.c0] = k.cn[k.c0];
            this.a6[k.c0] = p.a;
            this.a4[k.c1] = k.cm[k.c1];
            this.a5[k.c1] = k.cn[k.c1];
            this.a6[k.c1] = p.a;
            k.cc[0][k.c0] = k.ci[0][k.c0];
            k.cd[0][k.c0] = k.cj[0][k.c0];
            k.cc[0][k.c1] = k.ci[0][k.c1];
            k.cd[0][k.c1] = k.cj[0][k.c1];
            for (int n5 = 1; n5 < 3; ++n5) {
                k.cc[n5][k.c0] = k.ci[n5][k.c0];
                k.cd[n5][k.c0] = k.cj[n5][k.c0];
                k.cc[n5][k.c1] = k.ci[n5][k.c1];
                k.cd[n5][k.c1] = k.cj[n5][k.c1];
                k.ce[n5][k.c0] = k.ck[n5][k.c0];
                k.cf[n5][k.c0] = k.cl[n5][k.c0];
                k.ce[n5][k.c1] = k.ck[n5][k.c1];
                k.cf[n5][k.c1] = k.cl[n5][k.c1];
            }
        }
        else {
            int n6 = 0;
            switch (n2) {
                case 3: {
                    n6 = 0;
                    break;
                }
                case 6: {
                    n6 = 1;
                    break;
                }
                case 5: {
                    n6 = 2;
                    break;
                }
            }
            final int n7 = (n6 == 2) ? 0 : (n6 + 1);
            final int n8 = (n6 == 0) ? 2 : (n6 - 1);
            this.a4[n6] = k.cm[n8];
            this.a5[n6] = k.cn[n8];
            this.a6[n6] = p.a;
            this.a4[n7] = k.cm[n7];
            this.a5[n7] = k.cn[n7];
            this.a6[n7] = p.a;
            k.cc[0][n6] = k.ci[0][n8];
            k.cd[0][n6] = k.cj[0][n8];
            k.cc[0][n7] = k.ci[0][n7];
            k.cd[0][n7] = k.cj[0][n7];
            for (int n9 = 1; n9 < 3; ++n9) {
                k.cc[n9][n6] = k.ci[n9][n8];
                k.cd[n9][n6] = k.cj[n9][n8];
                k.cc[n9][n7] = k.ci[n9][n7];
                k.cd[n9][n7] = k.cj[n9][n7];
                k.ce[n9][n6] = k.ck[n9][n8];
                k.cf[n9][n6] = k.cl[n9][n8];
                k.ce[n9][n7] = k.ck[n9][n7];
                k.cf[n9][n7] = k.cl[n9][n7];
            }
        }
    }
    
    void a(final int[] array, final int n, final float[] array2, final float[] array3, final int[] bf, final float[][] array4, final r r, final int n2, final a9 a9, final int[] array5) {
        ++k.c6;
        final int g = p.g;
        if (k.b3) {
            k.b3 = false;
            k.b2[0] = k.bz;
            k.b2[1] = k.b0;
            k.b2[2] = k.b1;
            k.bt = new be();
            k.bs = new bf();
            k.bt.c();
            k.bs.c();
        }
        this.ae = r;
        int n3 = 0;
        this.bf = bf;
        this.ae = r;
        this.g = -1;
        this.a(this.i);
        this.bn = a9.a(this.i[1]);
        this.bo = a9.a(this.i[2]);
        this.bp = '\u00ff';
        this.bq = '\u00ff';
        final int a10 = this.a();
        k.cy = true;
        k.c3 = 1;
        int n4 = 0;
        int n5 = 0;
        for (int i = 0; i < n; i += k.c3) {
            if (n4 != 0 && k.cy) {
                n4 = 0;
                final int n6 = array[i - 1];
                k.cp = this.bf[n6];
                k.cq = this.bf[n6 + 1];
                k.cr = this.bf[n6 + 2];
                k.cs = this.bf[n6 + 3];
                k.ct = this.bf[n6 + 4];
                k.cu = this.bf[n6 + 5];
                k.cv = this.bf[n6 + 6];
                k.cw = this.bf[n6 + 7];
                k.cx = this.bf[n6 + 8];
                array2[k.cp] = k.b6[0];
                array2[k.cq] = k.b6[1];
                array2[k.cr] = k.b6[2];
                array2[k.cp + 1] = k.b5[0];
                array2[k.cq + 1] = k.b5[1];
                array2[k.cr + 1] = k.b5[2];
                array2[k.cp + 2] = k.b7[0];
                array2[k.cq + 2] = k.b7[1];
                array2[k.cr + 2] = k.b7[2];
                final float[] array6 = array4[0];
                if (array6 != null) {
                    final float[] array7 = k.b8[0];
                    final float[] array8 = k.b9[0];
                    array6[k.cs] = array7[0];
                    array6[k.cs + 1] = array8[0];
                    array6[k.ct] = array7[1];
                    array6[k.ct + 1] = array8[1];
                    array6[k.cu] = array7[2];
                    array6[k.cu + 1] = array8[2];
                }
                for (int j = 1; j < 3; ++j) {
                    final float[] array9 = array4[j];
                    if (array9 != null) {
                        final float[] array10 = k.b8[j];
                        final float[] array11 = k.b9[j];
                        array9[k.cs] = array10[0];
                        array9[k.cs + 1] = array11[0];
                        array9[k.ct] = array10[1];
                        array9[k.ct + 1] = array11[1];
                        array9[k.cu] = array10[2];
                        array9[k.cu + 1] = array11[2];
                        final float[] array12 = k.ca[j];
                        final float[] array13 = k.cb[j];
                        array9[k.cv] = array12[0];
                        array9[k.cv + 1] = array13[0];
                        array9[k.cw] = array12[1];
                        array9[k.cw + 1] = array13[1];
                        array9[k.cx] = array12[2];
                        array9[k.cx + 1] = array13[2];
                    }
                }
            }
            final int n7 = array5[i];
            final int n8 = array[i];
            k.cp = this.bf[n8];
            k.cq = this.bf[n8 + 1];
            k.cr = this.bf[n8 + 2];
            k.cs = this.bf[n8 + 3];
            k.ct = this.bf[n8 + 4];
            k.cu = this.bf[n8 + 5];
            k.cv = this.bf[n8 + 6];
            k.cw = this.bf[n8 + 7];
            k.cx = this.bf[n8 + 8];
            if (k.cy) {
                this.a5[0] = (this.ap = array2[k.cp]);
                this.a4[0] = (this.as = array2[k.cp + 1]);
                this.a6[0] = (this.am = array3[k.cp + 2]);
                this.a5[1] = (this.aq = array2[k.cq]);
                this.a4[1] = (this.at = array2[k.cq + 1]);
                this.a6[1] = (this.an = array3[k.cq + 2]);
                this.a5[2] = (this.ar = array2[k.cr]);
                this.a4[2] = (this.au = array2[k.cr + 1]);
                this.a6[2] = (this.ao = array3[k.cr + 2]);
                int n9 = 0;
                int n10 = 0;
                if (this.am < p.a) {
                    n9 |= 0x1;
                    k.cz[0] = true;
                    ++n10;
                }
                else {
                    k.cz[0] = false;
                }
                if (this.an < p.a) {
                    n9 |= 0x2;
                    k.cz[1] = true;
                    ++n10;
                }
                else {
                    k.cz[1] = false;
                }
                if (this.ao < p.a) {
                    n9 |= 0x4;
                    k.cz[2] = true;
                    ++n10;
                }
                else {
                    k.cz[2] = false;
                }
                if (n10 > 0) {
                    if (n10 == 3) {
                        continue;
                    }
                    n4 = 1;
                    n5 = 1;
                    this.a(n10, n9, array2, array3, array4);
                }
            }
            else {
                k.c3 = 1;
                k.cy = true;
                n5 = 1;
                final int n11 = (k.c0 == 0) ? 2 : (k.c0 - 1);
                this.a4[k.c0] = k.cm[n11];
                this.a5[k.c0] = k.cn[n11];
                this.a6[k.c0] = p.a;
                this.a4[k.c1] = k.b5[k.c1];
                this.a5[k.c1] = k.b6[k.c1];
                this.a6[k.c1] = k.b7[k.c1];
                this.a4[k.c2] = k.b5[k.c2];
                this.a5[k.c2] = k.b6[k.c2];
                this.a6[k.c2] = k.b7[k.c2];
                final float[] array14 = k.b8[0];
                final float[] array15 = k.b9[0];
                final float[] array16 = k.cc[0];
                final float[] array17 = k.cd[0];
                array16[k.c0] = k.ci[0][n11];
                array17[k.c0] = k.cj[0][n11];
                array16[k.c1] = array14[k.c1];
                array17[k.c1] = array15[k.c1];
                array16[k.c2] = array14[k.c2];
                array17[k.c2] = array15[k.c2];
                for (int k = 1; k < 3; ++k) {
                    final float[] array18 = k.b8[k];
                    final float[] array19 = k.b9[k];
                    final float[] array20 = k.cc[k];
                    final float[] array21 = k.cd[k];
                    array20[k.c0] = k.ci[k][n11];
                    array21[k.c0] = k.cj[k][n11];
                    array20[k.c1] = array18[k.c1];
                    array21[k.c1] = array19[k.c1];
                    array20[k.c2] = array18[k.c2];
                    array21[k.c2] = array19[k.c2];
                    final float[] array22 = k.ca[k];
                    final float[] array23 = k.cb[k];
                    final float[] array24 = k.ce[k];
                    final float[] array25 = k.cf[k];
                    array24[k.c0] = k.ck[k][n11];
                    array25[k.c0] = k.cl[k][n11];
                    array24[k.c1] = array22[k.c1];
                    array25[k.c1] = array23[k.c1];
                    array24[k.c2] = array22[k.c2];
                    array25[k.c2] = array23[k.c2];
                }
            }
            if (n5 != 0) {
                n5 = 0;
                array2[k.cp] = (this.ap = this.a5[0]);
                array2[k.cq] = (this.aq = this.a5[1]);
                array2[k.cr] = (this.ar = this.a5[2]);
                array2[k.cp + 1] = (this.as = this.a4[0]);
                array2[k.cq + 1] = (this.at = this.a4[1]);
                array2[k.cr + 1] = (this.au = this.a4[2]);
                array2[k.cp + 2] = (this.am = this.a6[0]);
                array2[k.cq + 2] = (this.an = this.a6[1]);
                array2[k.cr + 2] = (this.ao = this.a6[2]);
                final float[] array26 = array4[0];
                if (array26 != null) {
                    final float[] array27 = k.cc[0];
                    final float[] array28 = k.cd[0];
                    array26[k.cs] = array27[0];
                    array26[k.cs + 1] = array28[0];
                    array26[k.ct] = array27[1];
                    array26[k.ct + 1] = array28[1];
                    array26[k.cu] = array27[2];
                    array26[k.cu + 1] = array28[2];
                }
                for (int l = 1; l < 3; ++l) {
                    final float[] array29 = array4[l];
                    if (array29 != null) {
                        final float[] array30 = k.cc[l];
                        final float[] array31 = k.cd[l];
                        array29[k.cs] = array30[0];
                        array29[k.cs + 1] = array31[0];
                        array29[k.ct] = array30[1];
                        array29[k.ct + 1] = array31[1];
                        array29[k.cu] = array30[2];
                        array29[k.cu + 1] = array31[2];
                        final float[] array32 = k.ce[l];
                        final float[] array33 = k.cf[l];
                        array29[k.cv] = array32[0];
                        array29[k.cv + 1] = array33[0];
                        array29[k.cw] = array32[1];
                        array29[k.cw + 1] = array33[1];
                        array29[k.cx] = array32[2];
                        array29[k.cx + 1] = array33[2];
                    }
                }
            }
            if (!this.f || (this.au - this.as) * (this.aq - this.ap) > (this.at - this.as) * (this.ar - this.ap)) {
                char c = '\0';
                char c2 = '\0';
                if (this.bn != null) {
                    c = (char)(this.bn[n7] & this.bp);
                }
                if (this.bo != null) {
                    c2 = (char)(this.bo[n7] & this.bq);
                }
                int n12 = a10;
                k.br = null;
                if (n12 == 1 && (c2 & '\u0010') != '\0') {
                    n12 = 2;
                    k.br = k.bt;
                }
                if (n12 == 2 && (c & '\u0002') != '\0') {
                    k.br = k.bs;
                }
                if (k.br != null && k.br.b != g) {
                    k.br.a(this);
                    k.br.b = g;
                }
                this.av = 1.0f / this.am;
                this.aw = 1.0f / this.an;
                this.ax = 1.0f / this.ao;
                this.a7[0] = this.av;
                this.a7[1] = this.aw;
                this.a7[2] = this.ax;
                if (k.br != null && k.br.b()) {
                    final float[] array34 = array4[1];
                    if (array34 != null) {
                        float n13;
                        float n14;
                        if ((this.b.g & 0x2) == 0x0) {
                            this.bj = this.bf[n8 + 3];
                            this.bk = this.bf[n8 + 4];
                            this.bl = this.bf[n8 + 5];
                            n13 = array34[this.bj] + array34[this.bk] + array34[this.bl];
                            n14 = array34[this.bj + 1] + array34[this.bk + 1] + array34[this.bl + 1];
                        }
                        else {
                            this.bg = this.bf[n8 + 6];
                            this.bh = this.bf[n8 + 7];
                            this.bi = this.bf[n8 + 8];
                            n13 = array34[this.bg] + array34[this.bh] + array34[this.bi];
                            n14 = array34[this.bg + 1] + array34[this.bh + 1] + array34[this.bi + 1];
                        }
                        int n15 = (int)(n13 * 0.33333f);
                        int n16 = (int)(n14 * 0.33333f);
                        while (n15 < 0) {
                            n15 += this.i[1].c;
                        }
                        while (n16 < 0) {
                            n16 += this.i[1].d;
                        }
                        while (n15 >= this.i[1].c) {
                            n15 -= this.i[1].c;
                        }
                        while (n16 >= this.i[1].d) {
                            n16 -= this.i[1].d;
                        }
                        k.br.a(this.i[1].b[n15 + n16 * this.i[1].c]);
                    }
                }
                if (!this.e && (k.br == null || k.br.a())) {
                    final float n17 = (this.as + this.at + this.au) * 0.3333f;
                    final float n18 = (this.ap + this.aq + this.ar) * 0.3333f;
                    final float n19 = (this.a6[0] + this.a6[1] + this.a6[2]) * 0.3333f;
                    final float n20 = 0.3333f / (this.a7[0] * n19);
                    final float n21 = 0.3333f / (this.a7[1] * n19);
                    final float n22 = 0.3333f / (this.a7[2] * n19);
                    final float n23 = n20 * this.a4[0] + n21 * this.a4[1] + n22 * this.a4[2];
                    final float n24 = n20 * this.a5[0] + n21 * this.a5[1] + n22 * this.a5[2];
                    final float n25 = n17 - n23;
                    final float n26 = n18 - n24;
                    final boolean b = n25 * n25 + n26 * n26 > 0.25f;
                    if (!p.aj && b) {
                        final float n27 = 0.5f * this.a6[1] / ((this.a6[0] + this.a6[1]) * 0.5f);
                        final float n28 = this.a4[1] - this.a4[0];
                        final float n29 = this.a5[1] - this.a5[0];
                        float n30 = (n27 - 0.5f) * (float)Math.sqrt(n28 * n28 + n29 * n29);
                        if (n30 < 0.0f) {
                            n30 = -n30;
                        }
                        final float n31 = 0.5f * this.a6[2] / ((this.a6[1] + this.a6[2]) * 0.5f);
                        final float n32 = this.a4[2] - this.a4[1];
                        final float n33 = this.a5[2] - this.a5[1];
                        float n34 = (n31 - 0.5f) * (float)Math.sqrt(n32 * n32 + n33 * n33);
                        if (n34 < 0.0f) {
                            n34 = -n34;
                        }
                        final float n35 = 0.5f * this.a6[0] / ((this.a6[2] + this.a6[0]) * 0.5f);
                        final float n36 = this.a4[0] - this.a4[2];
                        final float n37 = this.a5[0] - this.a5[2];
                        float n38 = (n35 - 0.5f) * (float)Math.sqrt(n36 * n36 + n37 * n37);
                        if (n38 < 0.0f) {
                            n38 = -n38;
                        }
                        final float n39 = (n30 > n38) ? n30 : n38;
                        final float n40 = n34;
                        this.a(k.b2);
                        n3 = 0;
                        final int n41 = 0;
                        float n42 = 1.5f * (float)Math.sqrt(n40);
                        float n43 = 1.5f * (float)Math.sqrt(n39);
                        if (n42 < 2.0f) {
                            n42 = 2.0f;
                        }
                        if (n42 > 8.0f) {
                            n42 = 8.0f;
                        }
                        final int n44 = (int)Math.ceil(n42);
                        if (n43 < 1.0f) {
                            n43 = 1.0f;
                        }
                        if (n43 > 8.0f) {
                            n43 = 8.0f;
                        }
                        final int n45 = (int)Math.ceil(n43);
                        int n46 = 0;
                        int n47 = n41 + 1;
                        if (n47 == 3) {
                            n47 = 0;
                        }
                        int n48 = n41 - 1;
                        if (n48 == -1) {
                            n48 = 2;
                        }
                        k.by[n46 << 2] = this.a4[n41];
                        k.by[(n46 << 2) + 1] = this.a5[n41];
                        k.by[(n46 << 2) + 2] = this.a6[n41];
                        k.by[(n46 << 2) + 3] = this.a7[n41];
                        for (int n49 = 0; n49 < 3; ++n49) {
                            if (array4[n49] != null) {
                                final int n50 = 1 << n49;
                                final int n51 = ((this.b.g & n50) != 0x0) ? 2 : 0;
                                final int n52 = ((this.b.g & n50) != 0x0) ? this.bf[n8 + n41 + 6] : this.bf[n8 + n41 + 3];
                                final float[] array35 = k.b2[n49];
                                array35[n46 * 4 + n51] = array4[n49][n52];
                                array35[n46 * 4 + n51 + 1] = array4[n49][n52 + 1];
                            }
                        }
                        ++n46;
                        final float n53 = 1.0f / n43;
                        final float n54 = 1.0f / n42;
                        float n55 = n53;
                        for (int n56 = 1; n56 <= n45; ++n56, n55 += n53) {
                            float n57 = 0.0f;
                            float n58 = n55;
                            if (n56 == n45) {
                                n58 = 1.0f;
                            }
                            for (int n59 = 0; n59 <= n44; ++n59, n57 += n54) {
                                float n60 = n57;
                                if (n59 == n44) {
                                    n60 = 1.0f;
                                }
                                final float n61 = n58 * n60;
                                final float n62 = n58 - n61;
                                final float n63 = 1.0f - n62 - n61;
                                final float n64 = n62;
                                final float n65 = n61;
                                final float n66 = this.a4[n41] * n63 + this.a4[n47] * n64 + this.a4[n48] * n65;
                                final float n67 = this.a5[n41] * n63 + this.a5[n47] * n64 + this.a5[n48] * n65;
                                final float n68 = this.a7[n41] * n63 + this.a7[n47] * n64 + this.a7[n48] * n65;
                                k.by[n46 << 2] = n66;
                                k.by[(n46 << 2) + 1] = n67;
                                k.by[(n46 << 2) + 3] = n68;
                                final float n69 = 1.0f / n68;
                                final float n70 = n63 * this.a7[n41] * n69;
                                final float n71 = n64 * this.a7[n47] * n69;
                                final float n72 = n65 * this.a7[n48] * n69;
                                k.by[(n46 << 2) + 2] = this.a6[n41] * n70 + this.a6[n47] * n71 + this.a6[n48] * n72;
                                for (int n73 = 0; n73 < 3; ++n73) {
                                    if (array4[n73] != null) {
                                        final boolean b2 = (this.b.g & 1 << n73) != 0x0;
                                        final int n74 = b2 ? this.bf[n8 + n41 + 6] : this.bf[n8 + n41 + 3];
                                        final int n75 = b2 ? this.bf[n8 + n47 + 6] : this.bf[n8 + n47 + 3];
                                        final int n76 = b2 ? this.bf[n8 + n48 + 6] : this.bf[n8 + n48 + 3];
                                        final int n77 = b2 ? 2 : 0;
                                        final float[] array36 = array4[n73];
                                        final float[] array37 = k.b2[n73];
                                        array37[n46 * 4 + n77] = array36[n74] * n70 + array36[n75] * n71 + array36[n76] * n72;
                                        array37[n46 * 4 + n77 + 1] = array36[n74 + 1] * n70 + array36[n75 + 1] * n71 + array36[n76 + 1] * n72;
                                    }
                                }
                                ++n46;
                            }
                        }
                        final float[] array38 = (array4[1] != null) ? array4[1] : array4[2];
                        if (array38 != null) {
                            final int n78 = n8;
                            final int n79 = n8 + 1;
                            final int n80 = n8 + 2;
                            this.bg = this.bf[n78 + 6];
                            this.bh = this.bf[n79 + 6];
                            this.bi = this.bf[n80 + 6];
                            this.a(array38, this.as, this.ap, this.at, this.aq, this.au, this.ar);
                        }
                        for (int n81 = 0; n81 < n45; ++n81) {
                            final int n82 = 1 + n81 * (n44 + 1);
                            final int n83 = n82 - (n44 + 1);
                            for (int n84 = 0; n84 < n44 * 2; ++n84) {
                                final int n85 = n82 + (n84 + 1 >> 1);
                                int n86 = n83 + (n84 >> 1);
                                final int n87 = 1 + (((n84 & 0x1) != 0x0) ? n86 : n85);
                                if (n81 == 0) {
                                    n86 = 0;
                                    ++n84;
                                }
                                final int n88 = n85 << 2;
                                final int n89 = n86 << 2;
                                final int n90 = n87 << 2;
                                int n91 = 4095;
                                final int n92 = n41;
                                final int n93 = n48;
                                final int n94 = n47;
                                if (n81 == 0) {
                                    if (n84 == 1) {
                                        n91 = ((n91 & 0xFF0) | n92);
                                    }
                                    if (n84 == n44 * 2 - 1) {
                                        n91 = ((n91 & 0xF0F) | n93 << 4);
                                    }
                                    if (n81 == n45 - 1) {
                                        n91 = ((n91 & 0xFF) | n94 << 8);
                                    }
                                }
                                else {
                                    if (n84 == 0) {
                                        n91 = ((n91 & 0xFF0) | n92);
                                    }
                                    if (n84 == n44 * 2 - 1) {
                                        n91 = ((n91 & 0xFF) | n93 << 8);
                                    }
                                    if (n81 == n45 - 1 && (n84 & 0x1) == 0x0) {
                                        n91 = ((n91 & 0xFF) | n94 << 8);
                                    }
                                }
                                int bj;
                                int bk;
                                int bl;
                                if (k.by[n88 + 1] < k.by[n89 + 1]) {
                                    if (k.by[n90 + 1] < k.by[n89 + 1]) {
                                        if (k.by[n90 + 1] < k.by[n88 + 1]) {
                                            bj = n90;
                                            bk = n88;
                                            bl = n89;
                                            n91 = ((n91 & 0xFF) << 4 | (n91 & 0xF00) >> 8);
                                        }
                                        else {
                                            bj = n88;
                                            bk = n90;
                                            bl = n89;
                                            n91 = ((n91 & 0xF) << 8 | (n91 & 0xF0) | (n91 & 0xF00) >> 8);
                                        }
                                    }
                                    else {
                                        bj = n88;
                                        bk = n89;
                                        bl = n90;
                                    }
                                }
                                else if (k.by[n90 + 1] > k.by[n89 + 1]) {
                                    if (k.by[n90 + 1] < k.by[n88 + 1]) {
                                        bj = n89;
                                        bk = n90;
                                        bl = n88;
                                        n91 = ((n91 & 0xF) << 8 | (n91 & 0xFF0) >> 4);
                                    }
                                    else {
                                        bj = n89;
                                        bk = n88;
                                        bl = n90;
                                        n91 = ((n91 & 0xF) | (n91 & 0xF0) << 4 | (n91 & 0xF00) >> 4);
                                    }
                                }
                                else {
                                    bj = n90;
                                    bk = n89;
                                    bl = n88;
                                    n91 = ((n91 & 0xF) << 4 | (n91 & 0xF0) >> 4 | (n91 & 0xF00));
                                }
                                this.as = k.by[bj];
                                this.ap = k.by[bj + 1];
                                this.am = k.by[bj + 2];
                                this.av = k.by[bj + 3];
                                this.at = k.by[bk];
                                this.aq = k.by[bk + 1];
                                this.an = k.by[bk + 2];
                                this.aw = k.by[bk + 3];
                                this.au = k.by[bl];
                                this.ar = k.by[bl + 1];
                                this.ao = k.by[bl + 2];
                                this.ax = k.by[bl + 3];
                                this.bj = bj;
                                this.bg = bj + 2;
                                this.bk = bk;
                                this.bh = bk + 2;
                                this.bl = bl;
                                this.bi = bl + 2;
                                this.ay = (int)this.ap;
                                if (this.ap < 0.0f && this.ap != this.ay) {
                                    --this.ay;
                                }
                                if (++this.ay < 0) {
                                    this.ay = 0;
                                }
                                this.az = (int)this.aq;
                                if (this.aq < 0.0f && this.aq != this.az) {
                                    --this.az;
                                }
                                if (++this.az < 0) {
                                    this.az = 0;
                                }
                                if (this.az > this.af) {
                                    this.az = this.af;
                                }
                                this.a0 = (int)this.ar;
                                if (this.ar < 0.0f && this.ar != this.a0) {
                                    --this.a0;
                                }
                                if (++this.a0 > this.af) {
                                    this.a0 = this.af;
                                }
                                this.a1 = (int)(this.as + 2048.0f) - 2048;
                                this.a2 = (int)(this.at + 2048.0f) - 2048;
                                this.a3 = (int)(this.au + 2048.0f) - 2048;
                                final float n95 = this.at - this.as;
                                final float n96 = this.aq - this.ap;
                                final float n97 = this.au - this.as;
                                final float n98 = this.ar - this.ap;
                                final float n99 = n95 * n98 - n96 * n97;
                                if (n99 > 0.001f || n99 < -0.001f) {
                                    final float av = this.av;
                                    final float aw = this.aw;
                                    final float ax = this.ax;
                                    final float n100 = 1.0f / n99;
                                    this.a8 = n98 * n100;
                                    this.a9 = -n96 * n100;
                                    this.ba = -n97 * n100;
                                    this.bb = n95 * n100;
                                    this.bc = (aw - av) * this.a8 + (ax - av) * this.a9;
                                    this.bd = (aw - av) * this.ba + (ax - av) * this.bb;
                                    this.be = av - (this.as * this.bc + this.ap * this.bd);
                                }
                                else {
                                    final float n101 = 0.0f;
                                    this.bd = n101;
                                    this.bc = n101;
                                    this.be = this.av;
                                }
                                this.a(this.as, this.ap, this.at, this.aq, this.au, this.ar);
                                final int n102 = n91 & 0xF;
                                if (n102 != 15) {
                                    final int n103 = n102;
                                    int n104 = n102 + 1;
                                    if (n104 == 3) {
                                        n104 = 0;
                                    }
                                    this.a(0, array2[this.bf[n8 + n103] + 1], array2[this.bf[n8 + n103]], array2[this.bf[n8 + n104] + 1], array2[this.bf[n8 + n104]]);
                                }
                                final int n105 = (n91 & 0xF0) >> 4;
                                if (n105 != 15) {
                                    final int n106 = n105;
                                    int n107 = n105 + 1;
                                    if (n107 == 3) {
                                        n107 = 0;
                                    }
                                    this.a(1, array2[this.bf[n8 + n106] + 1], array2[this.bf[n8 + n106]], array2[this.bf[n8 + n107] + 1], array2[this.bf[n8 + n107]]);
                                }
                                final int n108 = (n91 & 0xF00) >> 8;
                                if (n108 != 15) {
                                    final int n109 = n108;
                                    int n110 = n108 + 1;
                                    if (n110 == 3) {
                                        n110 = 0;
                                    }
                                    this.a(2, array2[this.bf[n8 + n109] + 1], array2[this.bf[n8 + n109]], array2[this.bf[n8 + n110] + 1], array2[this.bf[n8 + n110]]);
                                }
                                this.a(n8, this.bf, array4, n2);
                            }
                        }
                        continue;
                    }
                }
                if (n3 == 0) {
                    if (!this.a(array4)) {
                        return;
                    }
                    n3 = 1;
                }
                int n111;
                int n112;
                int n113;
                if (this.ap < this.aq) {
                    if (this.ar < this.aq) {
                        if (this.ar < this.ap) {
                            n111 = n8 + 2;
                            n112 = n8;
                            n113 = n8 + 1;
                        }
                        else {
                            n111 = n8;
                            n112 = n8 + 2;
                            n113 = n8 + 1;
                        }
                    }
                    else {
                        n111 = n8;
                        n112 = n8 + 1;
                        n113 = n8 + 2;
                    }
                }
                else if (this.ar > this.aq) {
                    if (this.ar < this.ap) {
                        n111 = n8 + 1;
                        n112 = n8 + 2;
                        n113 = n8;
                    }
                    else {
                        n111 = n8 + 1;
                        n112 = n8;
                        n113 = n8 + 2;
                    }
                }
                else {
                    n111 = n8 + 2;
                    n112 = n8 + 1;
                    n113 = n8;
                }
                int n114 = this.bf[n111];
                k.cr = this.bf[n113];
                this.ap = array2[n114++];
                this.ar = array2[k.cr++];
                if (this.ap < this.af) {
                    if (this.ar >= 0.0f) {
                        this.as = array2[n114++];
                        this.au = array2[k.cr++];
                        int n115 = this.bf[n112];
                        this.aq = array2[n115++];
                        this.at = array2[n115++];
                        float n116;
                        float n117;
                        if (this.at < this.as) {
                            if (this.au < this.at) {
                                n116 = this.au;
                                n117 = this.as;
                            }
                            else {
                                n116 = this.at;
                                if (this.au > this.as) {
                                    n117 = this.au;
                                }
                                else {
                                    n117 = this.as;
                                }
                            }
                        }
                        else if (this.au < this.as) {
                            n116 = this.au;
                            n117 = this.at;
                        }
                        else {
                            n116 = this.as;
                            if (this.au > this.at) {
                                n117 = this.au;
                            }
                            else {
                                n117 = this.at;
                            }
                        }
                        if (n116 < this.ag && n117 >= 0.0f) {
                            if (this.ar - this.ap > 0.0f) {
                                this.ay = (int)this.ap;
                                if (this.ap < 0.0f && this.ap != this.ay) {
                                    --this.ay;
                                }
                                if (++this.ay < 0) {
                                    this.ay = 0;
                                }
                                this.az = (int)this.aq;
                                if (this.aq < 0.0f && this.aq != this.az) {
                                    --this.az;
                                }
                                if (++this.az < 0) {
                                    this.az = 0;
                                }
                                if (this.az > this.af) {
                                    this.az = this.af;
                                }
                                this.a0 = (int)this.ar;
                                if (this.ar < 0.0f && this.ar != this.a0) {
                                    --this.a0;
                                }
                                if (++this.a0 > this.af) {
                                    this.a0 = this.af;
                                }
                                this.am = array2[n114];
                                this.an = array2[n115];
                                this.ao = array2[k.cr];
                                this.a1 = (int)(this.as + 2048.0f) - 2048;
                                this.a2 = (int)(this.at + 2048.0f) - 2048;
                                this.a3 = (int)(this.au + 2048.0f) - 2048;
                                this.av = 1.0f / this.am;
                                this.aw = 1.0f / this.an;
                                this.ax = 1.0f / this.ao;
                                this.a7[0] = this.av;
                                this.a7[1] = this.aw;
                                this.a7[2] = this.ax;
                                this.bj = this.bf[n111 + 3];
                                this.bk = this.bf[n112 + 3];
                                this.bl = this.bf[n113 + 3];
                                this.bg = this.bf[n111 + 6];
                                this.bh = this.bf[n112 + 6];
                                this.bi = this.bf[n113 + 6];
                                final float n118 = this.at - this.as;
                                final float n119 = this.aq - this.ap;
                                final float n120 = this.au - this.as;
                                final float n121 = this.ar - this.ap;
                                final float n122 = n118 * n121 - n119 * n120;
                                if (n122 > 0.001f || n122 < -0.001f) {
                                    final float av2 = this.av;
                                    final float aw2 = this.aw;
                                    final float ax2 = this.ax;
                                    final float n123 = 1.0f / n122;
                                    this.a8 = n121 * n123;
                                    this.a9 = -n119 * n123;
                                    this.ba = -n120 * n123;
                                    this.bb = n118 * n123;
                                    this.bc = (aw2 - av2) * this.a8 + (ax2 - av2) * this.a9;
                                    this.bd = (aw2 - av2) * this.ba + (ax2 - av2) * this.bb;
                                    this.be = av2 - (this.as * this.bc + this.ap * this.bd);
                                }
                                else {
                                    final float n124 = 0.0f;
                                    this.bd = n124;
                                    this.bc = n124;
                                    this.be = this.av;
                                }
                                this.a(this.as, this.ap, this.at, this.aq, this.au, this.ar);
                                final float[] array39 = (array4[1] != null) ? array4[1] : array4[2];
                                if (array39 != null) {
                                    this.a(array39, this.as, this.ap, this.at, this.aq, this.au, this.ar);
                                }
                                this.a(n8, this.bf, array4, n2);
                            }
                        }
                    }
                }
            }
        }
        if (n4 != 0) {
            final int n125 = array[n - 1];
            k.cp = this.bf[n125];
            k.cq = this.bf[n125 + 1];
            k.cr = this.bf[n125 + 2];
            k.cs = this.bf[n125 + 3];
            k.ct = this.bf[n125 + 4];
            k.cu = this.bf[n125 + 5];
            k.cv = this.bf[n125 + 6];
            k.cw = this.bf[n125 + 7];
            k.cx = this.bf[n125 + 8];
            array2[k.cp] = k.b6[0];
            array2[k.cq] = k.b6[1];
            array2[k.cr] = k.b6[2];
            array2[k.cp + 1] = k.b5[0];
            array2[k.cq + 1] = k.b5[1];
            array2[k.cr + 1] = k.b5[2];
            array2[k.cp + 2] = k.b7[0];
            array2[k.cq + 2] = k.b7[1];
            array2[k.cr + 2] = k.b7[2];
            final float[] array40 = array4[0];
            if (array40 != null) {
                final float[] array41 = k.b8[0];
                final float[] array42 = k.b9[0];
                array40[k.cs] = array41[0];
                array40[k.cs + 1] = array42[0];
                array40[k.ct] = array41[1];
                array40[k.ct + 1] = array42[1];
                array40[k.cu] = array41[2];
                array40[k.cu + 1] = array42[2];
            }
            for (int n126 = 1; n126 < 3; ++n126) {
                final float[] array43 = array4[n126];
                if (array43 != null) {
                    final float[] array44 = k.b8[n126];
                    final float[] array45 = k.b9[n126];
                    array43[k.cs] = array44[0];
                    array43[k.cs + 1] = array45[0];
                    array43[k.ct] = array44[1];
                    array43[k.ct + 1] = array45[1];
                    array43[k.cu] = array44[2];
                    array43[k.cu + 1] = array45[2];
                    final float[] array46 = k.ca[n126];
                    final float[] array47 = k.cb[n126];
                    array43[k.cv] = array46[0];
                    array43[k.cv + 1] = array47[0];
                    array43[k.cw] = array46[1];
                    array43[k.cw + 1] = array47[1];
                    array43[k.cx] = array46[2];
                    array43[k.cx + 1] = array47[2];
                }
            }
        }
    }
    
    static final int a(final int n, final int n2) {
        final int n3 = n - n2;
        return n - (n3 & n3 >> 31);
    }
    
    static final int a(final int n, final int n2, final int n3, final int n4) {
        return a(a(a(n, n2), n3), n4);
    }
    
    static final int b(final int n, final int n2) {
        final int n3 = n - n2;
        return n2 + (n3 & n3 >> 31);
    }
    
    static final int b(final int n, final int n2, final int n3, final int n4) {
        return b(b(b(n, n2), n3), n4);
    }
    
    void a(final l l, final int n, final int n2) {
        if (k.bx) {
            int n3 = 1;
            int n4 = -1;
            for (int i = 0; i < 256; ++i) {
                if (i >= n3) {
                    ++n4;
                    n3 <<= 1;
                }
                k.bw[i] = n4;
            }
            k.bw[0] = 0;
            k.bx = false;
        }
        final int n5 = (1 << n) - 1;
        if (l.c * l.d < 1) {
            return;
        }
        final int[] array = new int[l.c];
        for (int j = 0; j < l.g; ++j) {
            a(l.f[j], array, l.c(j), l.d(j), n5, n2);
        }
    }
    
    static final void a(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n3 << n4;
        final int n6 = -1 - n5;
        final int n7 = -1 ^ n6;
        for (int i = 0; i < n; ++i) {
            final int n8 = 1 << n4;
            int n9 = n5;
            for (int n10 = i, j = 0; j < n2; ++j, n10 += n) {
                if (n9 < n5) {
                    n9 += n8;
                }
                if ((array[n10] & Integer.MIN_VALUE) != 0x0) {
                    n9 = 0;
                }
                array[n10] = ((array[n10] & n6) | n9);
            }
            int n11 = n5;
            for (int n12 = i + n * (n2 - 1), k = n2 - 1; k >= 0; --k, n12 -= n) {
                if (n11 < n5) {
                    n11 += n8;
                }
                if ((array[n12] & n7) == 0x0) {
                    n11 = 0;
                }
                if (n11 < (array[n12] & n7)) {
                    array[n12] = ((array[n12] & n6) | n11);
                }
            }
        }
        for (int l = 0; l < n2; ++l) {
            int n13 = n3;
            int n14 = n5;
            for (int n15 = l * n, n16 = 0; n16 < n; ++n16, ++n15) {
                if (n13 < n3) {
                    ++n13;
                }
                if ((array[n15] & n7) >> n4 > a(n13, n14 >> n4)) {
                    array2[n16] = n13;
                    array[n15] = ((array[n15] & n6) | n14);
                }
                else {
                    final int n17 = n16;
                    final int n18 = 0;
                    array2[n17] = n18;
                    n13 = n18;
                    n14 = (array[n15] & n7);
                }
            }
            int n19 = n3;
            int n20 = n5;
            for (int n21 = l * n + n - 1, n22 = n - 1; n22 >= 0; --n22, --n21) {
                if (n19 < n3) {
                    ++n19;
                }
                final int a = a(n19, n20 >> n4);
                final int a2 = a(array2[n22], (array[n21] & n7) >> n4);
                int n23;
                if (a2 > a) {
                    n23 = a;
                }
                else {
                    n19 = array2[n22];
                    n20 = (array[n21] & n7);
                    n23 = a2;
                }
                array[n21] = ((array[n21] & n6) | n23 << n4);
            }
        }
    }
    
    static void a(final l l, final int n, int n2, int n3, int n4, int n5) {
        final int n6 = n * n >> 3;
        n2 *= n2;
        n3 *= n3;
        n4 *= n4;
        n5 *= n5;
        for (int i = 0; i < l.g; ++i) {
            final int c = l.c(i);
            final int d = l.d(i);
            if (d <= 2) {
                break;
            }
            if (c <= 2) {
                break;
            }
            final int[] array = l.f[i];
            for (int j = 0; j < d; ++j) {
                int n7 = j * c;
                int n8 = 1;
                for (int k = 0; k < c; ++k, ++n7) {
                    final int[] array2 = array;
                    final int n9 = n7;
                    array2[n9] &= Integer.MAX_VALUE;
                    final int n10 = array[n7];
                    final int n11 = n10 & 0x7F000000;
                    final int n12 = n10 & 0xFF0000;
                    final int n13 = n10 & 0xFF00;
                    final int n14 = n10 & 0xFF;
                    for (int n15 = n7 + c - 1 + n8, n16 = n8; n16 < 4; ++n16, ++n15) {
                        if (j != d - 1 || n16 >= 3) {
                            if (k != c - 1 || n16 <= 1) {
                                if (n16 == 3) {
                                    n15 = n7 + 1;
                                }
                                final int n17 = array[n15];
                                final int n18 = (n17 & 0x7F000000) - n11 >> 24;
                                final int n19 = (n17 & 0xFF0000) - n12 >> 16;
                                final int n20 = (n17 & 0xFF00) - n13 >> 8;
                                final int n21 = (n17 & 0xFF) - n14;
                                if (n18 * n18 * n2 + n19 * n19 * n3 + n20 * n20 * n4 + n21 * n21 * n5 > n6) {
                                    final int[] array3 = array;
                                    final int n22 = n7;
                                    array3[n22] |= Integer.MIN_VALUE;
                                    final int[] array4 = array;
                                    final int n23 = n15;
                                    array4[n23] |= Integer.MIN_VALUE;
                                }
                            }
                        }
                    }
                    n8 = 0;
                }
            }
        }
    }
    
    static void a(final l l, final int n) {
        for (int i = 0; i < l.g; ++i) {
            final int[] array = l.f[i];
            for (int j = array.length - 1; j >= 0; --j) {
                final int[] array2 = array;
                final int n2 = j;
                array2[n2] &= n;
            }
        }
    }
    
    static void b(final l l, final int n) {
        final float n2 = 0.16666667f;
        for (int i = 0; i < n; ++i) {
            final int c = l.c(i);
            final int d = l.d(i);
            final int[] array = l.f[i];
            for (int j = 0; j < d; ++j) {
                for (int k = 0; k < c; ++k) {
                    if (k == 0 || j == 0 || k == c - 1 || j == d - 1) {
                        final int[] array2 = array;
                        final int n3 = j * c + k;
                        array2[n3] &= 0xFF0000FF;
                        final int[] array3 = array;
                        final int n4 = j * c + k;
                        array3[n4] |= 0x7F7F00;
                    }
                    else {
                        final int n5 = 0;
                        final int n6 = 0;
                        final int n7 = n5 + ((array[(j - 1) * c + k - 1] & 0xFF) + (array[j * c + k - 1] & 0xFF) + (array[(j + 1) * c + k - 1] & 0xFF));
                        final int n8 = n6 + ((array[(j - 1) * c + k - 1] & 0xFF) + (array[(j - 1) * c + k] & 0xFF) + (array[(j - 1) * c + k + 1] & 0xFF));
                        final int n9 = n7 - ((array[(j - 1) * c + k + 1] & 0xFF) + (array[j * c + k + 1] & 0xFF) + (array[(j + 1) * c + k + 1] & 0xFF));
                        final int n10 = n8 - ((array[(j + 1) * c + k - 1] & 0xFF) + (array[(j + 1) * c + k] & 0xFF) + (array[(j + 1) * c + k + 1] & 0xFF));
                        int n11 = (int)(n9 * n2);
                        int n12 = (int)(n10 * n2);
                        n11 += 127;
                        n12 += 127;
                        if (n11 > 255) {
                            n11 = 255;
                        }
                        else if (n11 < 0) {
                            n11 = 0;
                        }
                        if (n12 > 255) {
                            n12 = 255;
                        }
                        else if (n12 < 0) {
                            n12 = 0;
                        }
                        final int[] array4 = array;
                        final int n13 = j * c + k;
                        array4[n13] &= 0xFF0000FF;
                        final int[] array5 = array;
                        final int n14 = j * c + k;
                        array5[n14] |= (n11 << 16) + (n12 << 8);
                    }
                }
            }
        }
    }
    
    void c(final l l, final int n) {
        if (l.g < n) {
            l.a(n);
        }
        for (int i = 1; i < n; ++i) {
            final int[] array = l.f[i - 1];
            final int c = l.c(i - 1);
            final int d = l.d(i - 1);
            final int[] array2 = l.f[i];
            final int c2 = l.c(i);
            for (int d2 = l.d(i), j = 0; j < d2; ++j) {
                for (int n2 = j * 2 * c, n3 = j * c2, k = 0; k < c2; ++k, n2 += 2, ++n3) {
                    final int n4 = (k * 2 < c - 1) ? 1 : 0;
                    final int n5 = (j * 2 < d - 1) ? c : 0;
                    final int n6 = array[n2];
                    final int n7 = array[n2 + n4];
                    final int n8 = array[n2 + n5];
                    final int n9 = array[n2 + n5 + n4];
                    int n10;
                    int n11;
                    int n12;
                    int a;
                    if (i == 1) {
                        n10 = b(n6 & 0xFF0000, n7 & 0xFF0000, n8 & 0xFF0000, n9 & 0xFF0000) << 8;
                        n11 = a(n6 & 0xFF0000, n7 & 0xFF0000, n8 & 0xFF0000, n9 & 0xFF0000);
                        n12 = b(n6 & 0xFF00, n7 & 0xFF00, n8 & 0xFF00, n9 & 0xFF00);
                        a = a(n6 & 0xFF00, n7 & 0xFF00, n8 & 0xFF00, n9 & 0xFF00) >> 8;
                    }
                    else {
                        n10 = b(n6 >>> 8 & 0xFF0000, n7 >>> 8 & 0xFF0000, n8 >>> 8 & 0xFF0000, n9 >>> 8 & 0xFF0000) << 8;
                        n11 = a(n6 & 0xFF0000, n7 & 0xFF0000, n8 & 0xFF0000, n9 & 0xFF0000);
                        n12 = b(n6 & 0xFF00, n7 & 0xFF00, n8 & 0xFF00, n9 & 0xFF00);
                        a = a(n6 & 0xFF, n7 & 0xFF, n8 & 0xFF, n9 & 0xFF);
                    }
                    array2[n3] = (n10 | n11 | n12 | a);
                }
            }
        }
        final int[] b = l.b;
        for (int n13 = l.c * l.d - 1; n13 >= 0; --n13) {
            final int[] array3 = b;
            final int n14 = n13;
            array3[n14] &= 0x80FFFFFF;
        }
        for (int n15 = 0; n15 < n; ++n15) {
            final int[] array4 = l.f[n15];
            final int c3 = l.c(n15);
            final int d3 = l.d(n15);
            if (n15 > 0) {
                final int[] array5 = l.f[n15 - 1];
                final int c4 = l.c(n15 - 1);
                final int d4 = l.d(n15 - 1);
                for (int n16 = 0; n16 < d3; ++n16) {
                    for (int n17 = n16 * 2 * c4, n18 = n16 * c3, n19 = 0; n19 < c3; ++n19, n17 += 2, ++n18) {
                        int n20 = a(((array4[n18] & 0xFF0000) >> 16) - ((array4[n18] & 0xFF000000) >>> 24), (array4[n18] & 0xFF) - ((array4[n18] & 0xFF00) >> 8)) * (int)this.c >> 8;
                        if (n20 > 255) {
                            n20 = 255;
                        }
                        final int n21 = k.bw[n20];
                        final int n22 = array5[n17];
                        int n25;
                        int n24;
                        int n23;
                        if (n19 == c4 >> 1 && n16 == d4 >> 1) {
                            n23 = (n24 = (n25 = n22));
                        }
                        else if (n19 == c4 >> 1) {
                            n23 = array5[n17 + c4];
                            n24 = n22;
                            n25 = n23;
                        }
                        else if (n16 == d4 >> 1) {
                            n24 = array5[n17 + 1];
                            n23 = n22;
                            n25 = n24;
                        }
                        else {
                            n24 = array5[n17 + 1];
                            n23 = array5[n17 + c4];
                            n25 = array5[n17 + c4 + 1];
                        }
                        array4[n18] = (((n22 & 0xFF0000) + (n24 & 0xFF0000) + (n23 & 0xFF0000) + (n25 & 0xFF0000) >> 2 & 0xFF0000) | ((n22 & 0xFF00) + (n24 & 0xFF00) + (n23 & 0xFF00) + (n25 & 0xFF00) >> 2 & 0xFF00) | n21 << 24);
                    }
                }
            }
            for (int n26 = 0; n26 < d3; ++n26) {
                for (int n27 = n26 * c3, n28 = 0; n28 < c3; ++n28, ++n27) {
                    final int n29 = array4[n27];
                    int n30 = 0;
                    for (int n31 = -1; n31 <= 1; ++n31) {
                        final int n32 = n26 + n31;
                        if (n32 >= 0) {
                            if (n32 != d3) {
                                final int n33 = n32 * c3;
                                for (int n34 = -1; n34 <= 1; ++n34) {
                                    if (n34 != 0 || n31 != 0) {
                                        final int n35 = n28 + n34;
                                        if (n35 >= 0) {
                                            if (n35 != c3) {
                                                final int n36 = array4[n33 + n35];
                                                int n37 = (n36 & 0xFF0000) - (n29 & 0xFF0000) >> 16;
                                                if (n37 < 0) {
                                                    n37 = -n37;
                                                }
                                                int n38 = (n36 & 0xFF00) - (n29 & 0xFF00) >> 8;
                                                if (n38 < 0) {
                                                    n38 = -n38;
                                                }
                                                final int n39 = (n37 > n38) ? n37 : n38;
                                                n30 = ((n30 > n39) ? n30 : n39);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    int n40 = n30 * (int)this.c >> 8;
                    if (n40 > 255) {
                        n40 = 255;
                    }
                    final int n41 = k.bw[n40];
                    if (n41 > (n29 & 0xF000000) >> 24) {
                        array4[n27] = ((n29 & 0xF0FFFFFF) | n41 << 24);
                    }
                }
            }
        }
    }
    
    abstract void a(final int p0, final int[] p1, final float[][] p2, final int p3);
    
    void c() {
        this.k = null;
        this.l = null;
    }
    
    void d() {
        this.y = null;
        this.aa = null;
        this.ab = null;
        this.i = null;
        this.c();
    }
    
    static {
        k.bu = 8;
        k.bv = (k.bu + 1) * (k.bu + 1) + 1;
        k.bw = new int[256];
        k.bx = true;
        k.by = new float[k.bv * 4];
        k.bz = new float[k.bv * 6];
        k.b0 = new float[k.bv * 6];
        k.b1 = new float[k.bv * 6];
        k.b2 = new float[3][];
        k.b3 = true;
        k.b4 = new Hashtable(30000, 0.5f);
        k.b5 = new float[3];
        k.b6 = new float[3];
        k.b7 = new float[3];
        k.b8 = new float[3][3];
        k.b9 = new float[3][3];
        k.ca = new float[3][3];
        k.cb = new float[3][3];
        k.cc = new float[3][3];
        k.cd = new float[3][3];
        k.ce = new float[3][3];
        k.cf = new float[3][3];
        k.cg = new float[3];
        k.ch = new float[3];
        k.ci = new float[3][3];
        k.cj = new float[3][3];
        k.ck = new float[3][3];
        k.cl = new float[3][3];
        k.cm = new float[3];
        k.cn = new float[3];
        k.co = new float[3];
        k.cp = 0;
        k.cq = 0;
        k.cr = 0;
        k.cs = 0;
        k.ct = 0;
        k.cu = 0;
        k.cv = 0;
        k.cw = 0;
        k.cx = 0;
        k.cy = true;
        k.cz = new boolean[3];
        k.c0 = 0;
        k.c1 = 0;
        k.c2 = 0;
        k.c3 = 1;
        k.c6 = 0;
    }
}
