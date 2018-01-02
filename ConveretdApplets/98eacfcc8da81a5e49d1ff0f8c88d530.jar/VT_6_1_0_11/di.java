// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class di extends ax implements dg
{
    private static int[] as;
    private static int[] at;
    private static int[] au;
    private dg av;
    private float[] aw;
    private float[] ax;
    private float[] ay;
    private float[] az;
    private float[] aA;
    private float[] aB;
    private float[] aC;
    private float[] aD;
    private float[] aE;
    private float[] aF;
    private float[] aG;
    private float[] aH;
    private float[] aI;
    private float[] aJ;
    private float[] aK;
    private float[] aL;
    private float[] aM;
    private float[] aN;
    private int aO;
    private boolean aP;
    private int aQ;
    private int aR;
    private int aS;
    private int aT;
    private float aU;
    private float aV;
    private float aW;
    private int aX;
    private int aY;
    
    public final void a_() {
        this.av = new cA();
        ((cA)this.av).g();
        super.a_();
        this.a(160, 40, 8, 640, 0.9f);
        this.aP = false;
        this.aO = 5;
        this.aX = 16000;
    }
    
    public final void b_() {
        this.av = new di();
        ((di)this.av).a_();
        super.b_();
        this.a(320, 80, 8, 1280, 0.7f);
        this.aP = true;
        this.aO = 2;
        this.aX = 32000;
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.a(n, n2, n3, n4, n5);
        this.aQ = 3;
        this.aR = 0;
        this.aS = 0;
        this.aT = 0;
        this.aU = 8.0f;
        this.aY = this.I;
        this.aw = new float[n];
        this.ax = new float[64];
        this.ay = new float[this.N];
        this.az = new float[n];
        this.aA = new float[n];
        this.aB = new float[n2];
        this.aC = VT_6_1_0_11.z.a(this.N, n2);
        this.aD = VT_6_1_0_11.z.a(n3, this.U);
        this.aE = new float[n3];
        this.aF = new float[n3 + 1];
        this.aG = new float[n3];
        this.aH = new float[n3];
        this.aI = new float[n3];
        this.aJ = new float[n3 + 1];
        this.aK = new float[n3 + 1];
        this.aL = new float[n3 + 1];
        this.aM = new float[n3];
        this.aN = new float[n3];
        this.aW = 0.0f;
    }
    
    public final int a(final cH ch, final float[] array) {
        final float[] a = VT_6_1_0_11.aO.A;
        final float[] g = this.g;
        final float[] aw = this.aw;
        final int b = this.b;
        final float[] ax = this.ax;
        final int n = b;
        final float[] array2 = aw;
        final float[] array3 = g;
        final float[] array4 = a;
        final float[] array5 = new float[64];
        final float[] array6 = new float[n + 64 - 1];
        for (int i = 0; i < 64; ++i) {
            array5[64 - i - 1] = array4[i];
        }
        for (int j = 0; j < 63; ++j) {
            array6[j] = ax[64 - j - 2];
        }
        for (int k = 0; k < n; ++k) {
            array6[k + 64 - 1] = array[k];
        }
        for (int l = 0, n2 = 0; l < n; l += 2, ++n2) {
            array2[n2] = (array3[n2] = 0.0f);
            float[] array9;
            int n6;
            float[] array10;
            int n7;
            for (int n3 = 0; n3 < 32; ++n3, array9 = array3, n6 = n2, array9[n6] += array5[n3] * (array6[l + n3] + array6[l + 63 - n3]), array10 = array2, n7 = n2, array10[n7] += array5[n3] * (array6[l + n3] - array6[l + 63 - n3]), ++n3) {
                final float[] array7 = array3;
                final int n4 = n2;
                array7[n4] += array5[n3] * (array6[l + n3] + array6[l + 63 - n3]);
                final float[] array8 = array2;
                final int n5 = n2;
                array8[n5] -= array5[n3] * (array6[l + n3] - array6[l + 63 - n3]);
            }
        }
        for (int n8 = 0; n8 < 63; ++n8) {
            ax[n8] = array[n - n8 - 1];
        }
        this.av.a(ch, this.g);
        for (int n9 = 0; n9 < this.N - this.K; ++n9) {
            this.d[n9] = this.d[this.K + n9];
        }
        for (int n10 = 0; n10 < this.K; ++n10) {
            this.d[this.N - this.K + n10] = this.aw[n10];
        }
        System.arraycopy(this.aa, this.K, this.aa, 0, this.P - this.K);
        final float[] h = this.av.h();
        final float[] e = this.av.e();
        final float[] f = this.av.f();
        final boolean b2 = this.av.b() == 0;
        for (int n11 = 0; n11 < this.N; ++n11) {
            this.ay[n11] = this.d[n11] * this.aC[n11];
        }
        VT_6_1_0_11.M.a(this.ay, this.aF, this.O + 1, this.N);
        final float[] af = this.aF;
        final int n12 = 0;
        ++af[n12];
        final float[] af2 = this.aF;
        final int n13 = 0;
        af2[n13] *= this.V;
        for (int n14 = 0; n14 < this.O + 1; ++n14) {
            final float[] af3 = this.aF;
            final int n15 = n14;
            af3[n15] *= this.aD[n14];
        }
        VT_6_1_0_11.M.a(this.ad, this.aF, this.aE, this.O);
        System.arraycopy(this.ad, 0, this.ad, 1, this.O);
        this.ad[0] = 1.0f;
        final int a2;
        if (bX.a(this.ad, this.O, this.aG, 15, 0.2f) != this.O && (a2 = bX.a(this.ad, this.O, this.aG, 11, 0.02f)) != this.O) {
            for (int n16 = 0; n16 < this.O; ++n16) {
                this.aG[n16] = (float)Math.cos(3.141592653589793 * (n16 + 1) / (this.O + 1));
            }
        }
        for (int n17 = 0; n17 < this.O; ++n17) {
            this.aG[n17] = (float)Math.acos(this.aG[n17]);
        }
        if (this.aR != 0 && !b2) {
            float n18 = 0.0f;
            float n19 = 0.0f;
            for (int n20 = 0; n20 < this.K; ++n20) {
                n18 += this.g[n20] * this.g[n20];
                n19 += this.d[n20] * this.d[n20];
            }
            float n21 = (float)Math.log((n19 + 1.0f) / (n18 + 1.0f));
            this.aV = this.av.j();
            if (n21 < -4.0f) {
                n21 = -4.0f;
            }
            if (n21 > 2.0f) {
                n21 = 2.0f;
            }
            if (this.aR != 0) {
                int n22 = this.aO - 1;
                this.aV += (float)(1.0 * (n21 + 2.0f));
                if (this.aV < -1.0f) {
                    this.aV = -1.0f;
                }
                while (n22 != 0) {
                    final int n23;
                    float n24;
                    if ((n23 = (int)Math.floor(this.aU)) == 10) {
                        n24 = df.b[n22][n23];
                    }
                    else {
                        n24 = (this.aU - n23) * df.b[n22][n23 + 1] + (n23 + 1 - this.aU) * df.b[n22][n23];
                    }
                    if (this.aV >= n24) {
                        break;
                    }
                    --n22;
                }
                this.b(n22);
            }
            else {
                int ay;
                if (this.aV < 2.0) {
                    ay = 1;
                }
                else {
                    ay = this.aY;
                }
                this.I = ay;
            }
        }
        ch.a(1, 1);
        if (b2) {
            ch.a(0, 3);
        }
        else {
            ch.a(this.I, 3);
        }
        if (!b2 && this.H[this.I] != null) {
            this.H[this.I].e.a(this.aG, this.ae, this.O, ch);
            if (this.J != 0) {
                for (int n25 = 0; n25 < this.O; ++n25) {
                    this.aH[n25] = this.aG[n25];
                }
                for (int n26 = 0; n26 < this.O; ++n26) {
                    this.af[n26] = this.ae[n26];
                }
            }
            final float[] array11 = new float[this.O];
            final float[] array12 = new float[this.L];
            final float[] array13 = new float[this.L];
            for (int n27 = 0; n27 < this.M; ++n27) {
                float n28 = 0.0f;
                float n29 = 0.0f;
                final int n31;
                final int n30 = n31 = this.L * n27;
                final int n32 = this.ab + n30;
                final int n33 = n30;
                final int n34 = n30;
                final float n35 = (1.0f + n27) / this.M;
                for (int n36 = 0; n36 < this.O; ++n36) {
                    this.aI[n36] = (1.0f - n35) * this.aH[n36] + n35 * this.aG[n36];
                }
                for (int n37 = 0; n37 < this.O; ++n37) {
                    this.ag[n37] = (1.0f - n35) * this.af[n37] + n35 * this.ae[n37];
                }
                bX.a(this.aI, this.O, 0.05f);
                bX.a(this.ag, this.O, 0.05f);
                for (int n38 = 0; n38 < this.O; ++n38) {
                    this.aI[n38] = (float)Math.cos(this.aI[n38]);
                }
                for (int n39 = 0; n39 < this.O; ++n39) {
                    this.ag[n39] = (float)Math.cos(this.ag[n39]);
                }
                this.F.a(this.aI, this.aJ, this.O);
                this.F.a(this.ag, this.ah, this.O);
                ba.a(this.S, this.aJ, this.aK, this.O);
                ba.a(this.T, this.aJ, this.aL, this.O);
                float n40 = 0.0f;
                float n41 = 1.0f;
                this.aj[n27] = 0.0f;
                for (int n42 = 0; n42 <= this.O; ++n42) {
                    n40 += n41 * this.ah[n42];
                    n41 = -n41;
                    final float[] aj = this.aj;
                    final int n43 = n27;
                    aj[n43] += this.ah[n42];
                }
                final float n44 = Math.abs(1.0f / (Math.abs(n40) + 0.01f) + 0.01f) / (0.01f + Math.abs(1.0f / (Math.abs(h[n27]) + 0.01f)));
                ba.b(this.d, n31, this.ah, this.aa, n32, this.L, this.O, this.aM);
                for (int n45 = 0; n45 < this.L; ++n45) {
                    n28 += this.aa[n32 + n45] * this.aa[n32 + n45];
                }
                if (this.H[this.I].g == null) {
                    for (int n46 = 0; n46 < this.L; ++n46) {
                        n29 += f[n30 + n46] * f[n30 + n46];
                    }
                    int n47;
                    if ((n47 = (int)Math.floor(10.5 + 8.0 * Math.log((float)Math.sqrt(n28 / (n29 + 0.01f)) * n44 + 1.0E-4))) < 0) {
                        n47 = 0;
                    }
                    if (n47 > 31) {
                        n47 = 31;
                    }
                    ch.a(n47, 5);
                    Math.exp(n47 / 9.4);
                }
                else {
                    for (int n48 = 0; n48 < this.L; ++n48) {
                        n29 += e[n30 + n48] * e[n30 + n48];
                    }
                    int n49;
                    if ((n49 = (int)Math.floor(0.5 + 3.7 * (Math.log((float)(Math.sqrt(n28 + 1.0f) * n44 / Math.sqrt((n29 + 1.0f) * this.L))) + 2.0))) < 0) {
                        n49 = 0;
                    }
                    if (n49 > 15) {
                        n49 = 15;
                    }
                    ch.a(n49, 4);
                    final float n50 = (float)Math.exp(0.27027027027027023 * n49 - 2.0) * (float)Math.sqrt(n29 + 1.0f) / n44;
                    final float n51 = 1.0f / n50;
                    for (int n52 = 0; n52 < this.L; ++n52) {
                        this.aa[n32 + n52] = 0.0f;
                    }
                    this.aa[n32] = 1.0f;
                    ba.a(this.aa, n32, this.ah, this.aK, this.aL, array12, this.L, this.O);
                    for (int n53 = 0; n53 < this.L; ++n53) {
                        this.aa[n32 + n53] = 0.0f;
                    }
                    for (int n54 = 0; n54 < this.O; ++n54) {
                        array11[n54] = this.ai[n54];
                    }
                    ba.a(this.aa, n32, this.ah, this.aa, n32, this.L, this.O, array11);
                    for (int n55 = 0; n55 < this.O; ++n55) {
                        array11[n55] = this.aN[n55];
                    }
                    ba.a(this.aa, n32, this.aK, this.aL, this.aA, n33, this.L, this.O, array11, 0);
                    for (int n56 = 0; n56 < this.O; ++n56) {
                        array11[n56] = this.aN[n56];
                    }
                    ba.a(this.d, n31, this.aK, this.aL, this.az, n34, this.L, this.O, array11, 0);
                    for (int n57 = 0; n57 < this.L; ++n57) {
                        this.aB[n57] = this.az[n34 + n57] - this.aA[n33 + n57];
                    }
                    for (int n58 = 0; n58 < this.L; ++n58) {
                        this.aa[n32 + n58] = 0.0f;
                    }
                    for (int n59 = 0; n59 < this.L; ++n59) {
                        final float[] ab = this.aB;
                        final int n60 = n59;
                        ab[n60] *= n51;
                    }
                    for (int n61 = 0; n61 < this.L; ++n61) {
                        array13[n61] = 0.0f;
                    }
                    this.H[this.I].g.a(this.aB, this.ah, this.aK, this.aL, this.O, this.L, array13, 0, array12, ch, this.aQ + 1 >> 1);
                    for (int n62 = 0; n62 < this.L; ++n62) {
                        final float[] aa = this.aa;
                        final int n63 = n32 + n62;
                        aa[n63] += array13[n62] * n50;
                    }
                    if (this.H[this.I].d != 0) {
                        final float[] array14 = new float[this.L];
                        for (int n64 = 0; n64 < this.L; ++n64) {
                            array14[n64] = 0.0f;
                        }
                        for (int n65 = 0; n65 < this.L; ++n65) {
                            final float[] ab2 = this.aB;
                            final int n66 = n65;
                            ab2[n66] *= 2.5;
                        }
                        this.H[this.I].g.a(this.aB, this.ah, this.aK, this.aL, this.O, this.L, array14, 0, array12, ch, this.aQ + 1 >> 1);
                        for (int n67 = 0; n67 < this.L; ++n67) {
                            final float[] array15 = array14;
                            final int n68 = n67;
                            array15[n68] *= (float)(n50 * 0.4);
                        }
                        for (int n69 = 0; n69 < this.L; ++n69) {
                            final float[] aa2 = this.aa;
                            final int n70 = n32 + n69;
                            aa2[n70] += array14[n69];
                        }
                    }
                }
                for (int n71 = 0; n71 < this.O; ++n71) {
                    array11[n71] = this.ai[n71];
                }
                ba.a(this.aa, n32, this.ah, this.d, n31, this.L, this.O, this.ai);
                ba.a(this.d, n31, this.aK, this.aL, this.az, n34, this.L, this.O, this.aN, 0);
            }
            this.G.a(this.g, VT_6_1_0_11.aO.A, this.e, this.b, 64, this.h);
            this.G.a(this.d, VT_6_1_0_11.aO.B, this.f, this.b, 64, this.i);
            for (int n72 = 0; n72 < this.b; ++n72) {
                array[n72] = 2.0f * (this.e[n72] - this.f[n72]);
            }
            for (int n73 = 0; n73 < this.O; ++n73) {
                this.aH[n73] = this.aG[n73];
            }
            for (int n74 = 0; n74 < this.O; ++n74) {
                this.af[n74] = this.ae[n74];
            }
            this.J = 0;
            return 1;
        }
        for (int n75 = 0; n75 < this.K; ++n75) {
            this.aa[this.ab + n75] = (this.az[n75] = 0.0f);
        }
        for (int n76 = 0; n76 < this.O; ++n76) {
            this.aN[n76] = 0.0f;
        }
        this.J = 1;
        ba.a(this.aa, this.ab, this.ah, this.d, 0, this.L, this.O, this.ai);
        this.G.a(this.g, VT_6_1_0_11.aO.A, this.e, this.b, 64, this.h);
        this.G.a(this.d, VT_6_1_0_11.aO.B, this.f, this.b, 64, this.i);
        for (int n77 = 0; n77 < this.b; ++n77) {
            array[n77] = 2.0f * (this.e[n77] - this.f[n77]);
        }
        if (b2) {
            return 0;
        }
        return 1;
    }
    
    public final int a() {
        return di.a[this.I] + this.av.a();
    }
    
    public final void a(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n > 10) {
            n = 10;
        }
        if (this.aP) {
            this.av.a(n);
            this.b(di.au[n]);
            return;
        }
        this.av.b(di.as[n]);
        this.b(di.at[n]);
    }
    
    public final void a(final boolean ar) {
        this.aR = (ar ? 1 : 0);
        this.av.a(ar);
    }
    
    public final void b(int n) {
        if (n < 0) {
            n = 0;
        }
        final int n2 = n;
        this.aY = n2;
        this.I = n2;
    }
    
    public final int b() {
        return this.I;
    }
    
    public final boolean i() {
        return this.aR != 0;
    }
    
    public final float j() {
        return this.aV;
    }
    
    static {
        di.as = new int[] { 1, 8, 2, 3, 4, 5, 5, 6, 6, 7, 7 };
        di.at = new int[] { 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4 };
        di.au = new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    }
}
