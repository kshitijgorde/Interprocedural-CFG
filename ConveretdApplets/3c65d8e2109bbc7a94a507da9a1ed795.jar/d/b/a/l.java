// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class l extends m implements n
{
    public static final int[] aE;
    private int aW;
    private int[] aM;
    private float az;
    private float[] aO;
    private int aF;
    private float[] aJ;
    private int as;
    private float[] aT;
    private float[] aX;
    private float[] aS;
    private float[] ax;
    private float[] aU;
    private float[] aI;
    private float[] a0;
    private float[] aw;
    private float[] aD;
    private float[] aB;
    private float[] aR;
    private float[] aL;
    private float[] aC;
    private float[] aN;
    private p aA;
    private int aV;
    private float[] aG;
    protected int aP;
    protected int au;
    protected int aY;
    protected int aZ;
    protected float at;
    protected float aK;
    protected float av;
    protected float aH;
    protected float ay;
    protected int ar;
    protected int aQ;
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        super.a(n, n2, n3, n4);
        this.aP = 3;
        this.au = 0;
        this.aY = 0;
        this.aZ = 0;
        this.at = 8.0f;
        this.aQ = 5;
        this.az = 0.0f;
        this.aW = 1;
        this.aO = new float[n4];
        this.aF = n4 - this.o;
        this.aJ = new float[n4];
        this.as = n4 - this.o;
        this.aT = d.b.a.s.a(this.o, n2);
        this.ax = d.b.a.s.a(n3, this.ac);
        this.aS = new float[n3 + 1];
        this.aX = new float[this.o];
        this.aw = new float[n3 + 1];
        this.D = new float[n3 + 1];
        this.aD = new float[n3 + 1];
        this.aB = new float[n3 + 1];
        this.aU = new float[n3];
        this.v = new float[n3];
        this.aI = new float[n3];
        this.W = new float[n3];
        this.a0 = new float[n3];
        this.B = new float[n3];
        this.aR = new float[n3];
        this.R = new float[n3];
        this.aL = new float[n3];
        this.aC = new float[n3];
        this.aN = new float[n3];
        this.aA = new p();
        this.aV = 0;
        this.ay = 0.0f;
        this.ar = 8000;
        this.P = new float[n3 + 1];
        this.O = new float[n3 + 1];
        this.L = new float[n3 + 1];
        this.aG = new float[40];
        this.C.a();
        this.aM = new int[this.Q];
    }
    
    public int if(final ad ad, final float[] array) {
        System.arraycopy(this.A, this.z, this.A, 0, this.X - this.z);
        this.A[this.X - this.z] = array[0] - this.S * this.V;
        for (int i = 1; i < this.z; ++i) {
            this.A[this.X - this.z + i] = array[i] - this.S * array[i - 1];
        }
        this.V = array[this.z - 1];
        System.arraycopy(this.aO, this.z, this.aO, 0, this.X - this.z);
        System.arraycopy(this.m, this.z, this.m, 0, this.X - this.z);
        System.arraycopy(this.aJ, this.z, this.aJ, 0, this.X - this.z);
        for (int j = 0; j < this.o; ++j) {
            this.aX[j] = this.A[j + this.Z] * this.aT[j];
        }
        d.b.a.a.a(this.aX, this.aS, this.n + 1, this.o);
        final float[] as = this.aS;
        final int n = 0;
        as[n] += 10.0f;
        final float[] as2 = this.aS;
        final int n2 = 0;
        as2[n2] *= this.w;
        for (int k = 0; k < this.n + 1; ++k) {
            final float[] as3 = this.aS;
            final int n3 = k;
            as3[n3] *= this.ax[k];
        }
        d.b.a.a.a(this.ab, this.aS, this.aR, this.n);
        System.arraycopy(this.ab, 0, this.ab, 1, this.n);
        this.ab[0] = 1.0f;
        int n4 = d.b.a.y.a(this.ab, this.n, this.aU, 15, 0.2f);
        if (n4 == this.n) {
            for (int l = 0; l < this.n; ++l) {
                this.aU[l] = (float)Math.acos(this.aU[l]);
            }
        }
        else {
            if (this.aP > 1) {
                n4 = d.b.a.y.a(this.ab, this.n, this.aU, 11, 0.05f);
            }
            if (n4 == this.n) {
                for (int n5 = 0; n5 < this.n; ++n5) {
                    this.aU[n5] = (float)Math.acos(this.aU[n5]);
                }
            }
            else {
                for (int n6 = 0; n6 < this.n; ++n6) {
                    this.aU[n6] = this.aI[n6];
                }
            }
        }
        float n7 = 0.0f;
        for (int n8 = 0; n8 < this.n; ++n8) {
            n7 += (this.aI[n8] - this.aU[n8]) * (this.aI[n8] - this.aU[n8]);
        }
        if (this.y != 0) {
            for (int n9 = 0; n9 < this.n; ++n9) {
                this.a0[n9] = this.aU[n9];
            }
        }
        else {
            for (int n10 = 0; n10 < this.n; ++n10) {
                this.a0[n10] = 0.375f * this.aI[n10] + 0.625f * this.aU[n10];
            }
        }
        d.b.a.y.a(this.a0, this.n, 0.002f);
        for (int n11 = 0; n11 < this.n; ++n11) {
            this.a0[n11] = (float)Math.cos(this.a0[n11]);
        }
        this.ad.a(this.a0, this.aw, this.n);
        int n12;
        float n13;
        if (this.aa[this.t] == null || this.au != 0 || this.aY != 0 || this.aa[this.t].try != 0 || this.aa[this.t].byte != -1) {
            final int[] array2 = new int[6];
            final float[] array3 = new float[6];
            d.b.a.ae.a(this.J, this.aw, this.aD, this.n);
            d.b.a.ae.a(this.I, this.aw, this.aB, this.n);
            d.b.a.ae.a(this.A, this.Z, this.aD, this.aB, this.aJ, this.as, this.z, this.n, this.aC, 0);
            d.b.a.e.a(this.aJ, this.as, this.N, this.ae, this.z, array2, array3, 6);
            n12 = array2[0];
            n13 = array3[0];
            for (int n14 = 1; n14 < 6; ++n14) {
                if (array3[n14] > 0.85 * n13 && (Math.abs(array2[n14] - n12 / 2.0) <= 1.0 || Math.abs(array2[n14] - n12 / 3.0) <= 1.0 || Math.abs(array2[n14] - n12 / 4.0) <= 1.0 || Math.abs(array2[n14] - n12 / 5.0) <= 1.0)) {
                    n12 = array2[n14];
                }
            }
        }
        else {
            n12 = 0;
            n13 = 0.0f;
        }
        d.b.a.ae.a(this.A, this.Z, this.aw, this.m, this.K, this.z, this.n, this.aN);
        float n15 = 0.0f;
        for (int n16 = 0; n16 < this.z; ++n16) {
            n15 += this.m[this.K + n16] * this.m[this.K + n16];
        }
        final float n17 = (float)Math.sqrt(1.0f + n15 / this.z);
        if (this.aA != null && (this.au != 0 || this.aY != 0)) {
            if (this.aZ != 0) {
                float n18 = 0.0f;
                if (this.aH * this.av > 0.0f) {
                    n18 = -1.0E-5f * this.av / (1.0f + this.ay);
                    if (n18 > 0.05f) {
                        n18 = 0.05f;
                    }
                    if (n18 < -0.05f) {
                        n18 = -0.05f;
                    }
                }
                this.at += n18;
                if (this.at > 10.0f) {
                    this.at = 10.0f;
                }
                if (this.at < 0.0f) {
                    this.at = 0.0f;
                }
            }
            this.aK = this.aA.a(array, this.z, n12, n13);
            if (this.au != 0) {
                int n19 = 0;
                float n20 = 100.0f;
                for (int n21 = 8; n21 > 0; --n21) {
                    final int n22 = (int)Math.floor(this.at);
                    float n23;
                    if (n22 == 10) {
                        n23 = d.b.a.p.e[n21][n22];
                    }
                    else {
                        n23 = (this.at - n22) * d.b.a.p.e[n21][n22 + 1] + (1 + n22 - this.at) * d.b.a.p.e[n21][n22];
                    }
                    if (this.aK > n23 && this.aK - n23 < n20) {
                        n19 = n21;
                        n20 = this.aK - n23;
                    }
                }
                int n24 = n19;
                if (n24 == 0) {
                    if (this.aV == 0 || n7 > 0.05 || this.H == 0 || this.aV > 20) {
                        n24 = 1;
                        this.aV = 1;
                    }
                    else {
                        n24 = 0;
                        ++this.aV;
                    }
                }
                else {
                    this.aV = 0;
                }
                this.new(n24);
                if (this.aZ != 0) {
                    final int m = this.j();
                    this.av += m - this.aZ;
                    this.aH = 0.95f * this.aH + 0.05f * (m - this.aZ);
                    ++this.ay;
                }
            }
            else {
                int aq;
                if (this.aK < 2.0f) {
                    if (this.aV == 0 || n7 > 0.05 || this.H == 0 || this.aV > 20) {
                        this.aV = 1;
                        aq = 1;
                    }
                    else {
                        aq = 0;
                        ++this.aV;
                    }
                }
                else {
                    this.aV = 0;
                    aq = this.aQ;
                }
                this.t = aq;
            }
        }
        else {
            this.aK = -1.0f;
        }
        ad.a(0, 1);
        ad.a(this.t, 4);
        if (this.aa[this.t] == null) {
            for (int n25 = 0; n25 < this.z; ++n25) {
                final float[] m2 = this.m;
                final int n26 = this.K + n25;
                final float[] ao = this.aO;
                final int n27 = this.aF + n25;
                final float[] aj = this.aJ;
                final int n28 = this.as + n25;
                final float n29 = 0.0f;
                aj[n28] = n29;
                m2[n26] = (ao[n27] = n29);
            }
            for (int n30 = 0; n30 < this.n; ++n30) {
                this.aL[n30] = 0.0f;
            }
            this.y = 1;
            this.aW = 1;
            d.b.a.ae.if(this.m, this.K, this.D, this.A, this.Z, this.z, this.n, this.R);
            array[0] = this.A[this.Z] + this.S * this.az;
            for (int n31 = 1; n31 < this.z; ++n31) {
                final int n32 = n31;
                final float[] a = this.A;
                final int z = n31;
                this.Z = z;
                array[n32] = a[z] + this.S * array[n31 - 1];
            }
            this.az = array[this.z - 1];
            return 0;
        }
        if (this.y != 0) {
            for (int n33 = 0; n33 < this.n; ++n33) {
                this.aI[n33] = this.aU[n33];
            }
        }
        this.aa[this.t].else.a(this.aU, this.v, this.n, ad);
        if (this.aa[this.t].byte != -1) {
            ad.a(n12 - this.N, 7);
        }
        if (this.aa[this.t].try != 0) {
            int n34 = (int)Math.floor(0.5 + 15.0f * n13);
            if (n34 > 15) {
                n34 = 15;
            }
            if (n34 < 0) {
                n34 = 0;
            }
            ad.a(n34, 4);
            n13 = 0.066667f * n34;
        }
        int n35 = (int)Math.floor(0.5 + 3.5 * Math.log(n17));
        if (n35 < 0) {
            n35 = 0;
        }
        if (n35 > 31) {
            n35 = 31;
        }
        final float n36 = (float)Math.exp(n35 / 3.5);
        ad.a(n35, 5);
        if (this.y != 0) {
            for (int n37 = 0; n37 < this.n; ++n37) {
                this.W[n37] = this.v[n37];
            }
        }
        final float[] array4 = new float[this.T];
        final float[] array5 = new float[this.T];
        final float[] array6 = new float[this.T];
        final float[] array7 = new float[this.n];
        final float[] array8 = new float[this.z];
        for (int n38 = 0; n38 < this.z; ++n38) {
            array8[n38] = this.A[this.Z + n38];
        }
        for (int n39 = 0; n39 < this.Q; ++n39) {
            final int n40 = this.T * n39;
            final int n41 = this.Z + n40;
            final int n42 = this.K + n40;
            final int n43 = this.as + n40;
            final int n44 = this.aF + n40;
            final float n45 = (float)(1.0 + n39) / this.Q;
            for (int n46 = 0; n46 < this.n; ++n46) {
                this.a0[n46] = (1.0f - n45) * this.aI[n46] + n45 * this.aU[n46];
            }
            for (int n47 = 0; n47 < this.n; ++n47) {
                this.B[n47] = (1.0f - n45) * this.W[n47] + n45 * this.v[n47];
            }
            d.b.a.y.a(this.a0, this.n, 0.002f);
            d.b.a.y.a(this.B, this.n, 0.002f);
            for (int n48 = 0; n48 < this.n; ++n48) {
                this.a0[n48] = (float)Math.cos(this.a0[n48]);
            }
            this.ad.a(this.a0, this.aw, this.n);
            for (int n49 = 0; n49 < this.n; ++n49) {
                this.B[n49] = (float)Math.cos(this.B[n49]);
            }
            this.ad.a(this.B, this.D, this.n);
            float n50 = 1.0f;
            this.r[n39] = 0.0f;
            for (int n51 = 0; n51 <= this.n; ++n51) {
                final float[] r = this.r;
                final int n52 = n39;
                r[n52] += n50 * this.D[n51];
                n50 = -n50;
            }
            d.b.a.ae.a(this.J, this.aw, this.aD, this.n);
            if (this.I >= 0.0f) {
                d.b.a.ae.a(this.I, this.aw, this.aB, this.n);
            }
            else {
                this.aB[0] = 1.0f;
                this.aB[1] = -this.S;
                for (int n53 = 2; n53 <= this.n; ++n53) {
                    this.aB[n53] = 0.0f;
                }
            }
            for (int n54 = 0; n54 < this.T; ++n54) {
                this.m[n42 + n54] = 0.0f;
            }
            this.m[n42] = 1.0f;
            d.b.a.ae.a(this.m, n42, this.D, this.aD, this.aB, array6, this.T, this.n);
            for (int n55 = 0; n55 < this.T; ++n55) {
                this.m[n42 + n55] = 0.0f;
            }
            for (int n56 = 0; n56 < this.T; ++n56) {
                this.aO[n44 + n56] = 0.0f;
            }
            for (int n57 = 0; n57 < this.n; ++n57) {
                array7[n57] = this.R[n57];
            }
            d.b.a.ae.if(this.m, n42, this.D, this.m, n42, this.T, this.n, array7);
            for (int n58 = 0; n58 < this.n; ++n58) {
                array7[n58] = this.aL[n58];
            }
            d.b.a.ae.a(this.m, n42, this.aD, this.aB, array4, 0, this.T, this.n, array7, 0);
            for (int n59 = 0; n59 < this.n; ++n59) {
                array7[n59] = this.aL[n59];
            }
            d.b.a.ae.a(this.A, n41, this.aD, this.aB, this.aJ, n43, this.T, this.n, array7, 0);
            for (int n60 = 0; n60 < this.T; ++n60) {
                array5[n60] = this.aJ[n43 + n60] - array4[n60];
            }
            for (int n61 = 0; n61 < this.T; ++n61) {
                this.m[n42 + n61] = (this.aO[n44 + n61] = 0.0f);
            }
            int n62;
            int ae;
            if (this.aa[this.t].byte != -1) {
                final int byte1 = this.aa[this.t].byte;
                if (byte1 != 0) {
                    if (n12 < this.N + byte1 - 1) {
                        n12 = this.N + byte1 - 1;
                    }
                    if (n12 > this.ae - byte1) {
                        n12 = this.ae - byte1;
                    }
                    n62 = n12 - byte1 + 1;
                    ae = n12 + byte1;
                }
                else {
                    ae = (n62 = n12);
                }
            }
            else {
                n62 = this.N;
                ae = this.ae;
            }
            if (this.aW != 0 && ae > n40) {
                ae = n40;
            }
            this.aM[n39] = this.aa[this.t].new.a(array5, this.aJ, n43, this.D, this.aD, this.aB, this.m, n42, n62, ae, n13, this.n, this.T, ad, this.aO, n44, array6, this.aP);
            d.b.a.ae.a(this.m, n42, this.D, this.aD, this.aB, array4, this.T, this.n);
            for (int n63 = 0; n63 < this.T; ++n63) {
                final float[] array9 = array5;
                final int n64 = n63;
                array9[n64] -= array4[n63];
            }
            float n65 = 0.0f;
            final int n66 = n39 * this.T;
            for (int n67 = 0; n67 < this.T; ++n67) {
                this.x[n66 + n67] = 0.0f;
            }
            d.b.a.ae.if(array5, 0, this.D, this.aD, this.aB, this.aX, this.T, this.n);
            for (int n68 = 0; n68 < this.T; ++n68) {
                n65 += this.aX[n68] * this.aX[n68];
            }
            final float n69 = (float)Math.sqrt(0.1f + n65 / this.T) / n36;
            float n72;
            if (this.aa[this.t].case != 0) {
                final float n70 = (float)Math.log(n69);
                float n71;
                if (this.aa[this.t].case == 3) {
                    final int a2 = d.b.a.r.a(n70, l.E, 8);
                    ad.a(a2, 3);
                    n71 = l.E[a2];
                }
                else {
                    final int a3 = d.b.a.r.a(n70, l.F, 2);
                    ad.a(a3, 1);
                    n71 = l.F[a3];
                }
                n72 = (float)Math.exp(n71);
            }
            else {
                n72 = 1.0f;
            }
            final float n73 = n72 * n36;
            final float n74 = 1.0f / n73;
            for (int n75 = 0; n75 < this.T; ++n75) {
                final float[] array10 = array5;
                final int n76 = n75;
                array10[n76] *= n74;
            }
            this.aa[this.t].for.a(array5, this.D, this.aD, this.aB, this.n, this.T, this.x, n66, array6, ad, this.aP);
            for (int n77 = 0; n77 < this.T; ++n77) {
                final float[] x = this.x;
                final int n78 = n66 + n77;
                x[n78] *= n73;
            }
            for (int n79 = 0; n79 < this.T; ++n79) {
                final float[] m3 = this.m;
                final int n80 = n42 + n79;
                m3[n80] += this.x[n66 + n79];
            }
            if (this.aa[this.t].do != 0) {
                final float[] array11 = new float[this.T];
                for (int n81 = 0; n81 < this.T; ++n81) {
                    final float[] array12 = array5;
                    final int n82 = n81;
                    array12[n82] *= 2.2;
                }
                this.aa[this.t].for.a(array5, this.D, this.aD, this.aB, this.n, this.T, array11, 0, array6, ad, this.aP);
                for (int n83 = 0; n83 < this.T; ++n83) {
                    final float[] array13 = array11;
                    final int n84 = n83;
                    array13[n84] *= (float)(n73 * 0.45454545454545453);
                }
                for (int n85 = 0; n85 < this.T; ++n85) {
                    final float[] m4 = this.m;
                    final int n86 = n42 + n85;
                    m4[n86] += array11[n85];
                }
            }
            for (int n87 = 0; n87 < this.T; ++n87) {
                final float[] array14 = array5;
                final int n88 = n87;
                array14[n88] *= n73;
            }
            for (int n89 = 0; n89 < this.n; ++n89) {
                array7[n89] = this.R[n89];
            }
            d.b.a.ae.if(this.m, n42, this.D, this.A, n41, this.T, this.n, this.R);
            d.b.a.ae.a(this.A, n41, this.aD, this.aB, this.aJ, n43, this.T, this.n, this.aL, 0);
            for (int n90 = 0; n90 < this.T; ++n90) {
                this.aO[n44 + n90] = this.m[n42 + n90];
            }
        }
        if (this.t >= 1) {
            for (int n91 = 0; n91 < this.n; ++n91) {
                this.aI[n91] = this.aU[n91];
            }
            for (int n92 = 0; n92 < this.n; ++n92) {
                this.W[n92] = this.v[n92];
            }
        }
        if (this.t == 1) {
            if (this.aV != 0) {
                ad.a(15, 4);
            }
            else {
                ad.a(0, 4);
            }
        }
        this.y = 0;
        float n93 = 0.0f;
        float n94 = 0.0f;
        for (int n95 = 0; n95 < this.z; ++n95) {
            n93 += this.A[this.Z + n95] * this.A[this.Z + n95];
            n94 += (this.A[this.Z + n95] - array8[n95]) * (this.A[this.Z + n95] - array8[n95]);
        }
        final float n96 = (float)(10.0 * Math.log((n93 + 1.0f) / (n94 + 1.0f)));
        array[0] = this.A[this.Z] + this.S * this.az;
        for (int n97 = 1; n97 < this.z; ++n97) {
            array[n97] = this.A[this.Z + n97] + this.S * array[n97 - 1];
        }
        this.az = array[this.z - 1];
        if (this.aa[this.t].for instanceof ac || this.t == 0) {
            this.aW = 1;
        }
        else {
            this.aW = 0;
        }
        return 1;
    }
    
    public int g() {
        return l.M[this.t];
    }
    
    public void if(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n > 10) {
            n = 10;
        }
        final int n2 = l.aE[n];
        this.aQ = n2;
        this.t = n2;
    }
    
    public int j() {
        if (this.aa[this.t] != null) {
            return this.ar * this.aa[this.t].int / this.z;
        }
        return this.ar * 5 / this.z;
    }
    
    public void new(int n) {
        if (n < 0) {
            n = 0;
        }
        final int n2 = n;
        this.aQ = n2;
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
    
    public void do(final boolean au) {
        this.au = (au ? 1 : 0);
    }
    
    public boolean long() {
        return this.au != 0;
    }
    
    public void for(final boolean ay) {
        this.aY = (ay ? 1 : 0);
    }
    
    public boolean c() {
        return this.aY != 0;
    }
    
    public void if(final boolean h) {
        this.H = (h ? 1 : 0);
    }
    
    public int i() {
        return this.aZ;
    }
    
    public void a(final int n) {
        this.aZ = ((n != 0) ? 1 : 0);
        this.au = 1;
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
        this.ay = 0.0f;
        this.av = 0.0f;
        this.aH = 0.0f;
    }
    
    public void a(float at) {
        if (at < 0.0f) {
            at = 0.0f;
        }
        if (at > 10.0f) {
            at = 10.0f;
        }
        this.at = at;
    }
    
    public float f() {
        return this.at;
    }
    
    public void int(int ap) {
        if (ap < 0) {
            ap = 0;
        }
        if (ap > 10) {
            ap = 10;
        }
        this.aP = ap;
    }
    
    public int d() {
        return this.aP;
    }
    
    public void for(final int ar) {
        this.ar = ar;
    }
    
    public int h() {
        return this.ar;
    }
    
    public int e() {
        return this.o - this.z;
    }
    
    public float b() {
        return this.aK;
    }
    
    static {
        aE = new int[] { 1, 8, 2, 3, 3, 4, 4, 5, 5, 6, 7 };
    }
}
