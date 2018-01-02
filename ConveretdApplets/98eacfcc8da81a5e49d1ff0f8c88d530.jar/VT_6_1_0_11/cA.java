// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class cA extends bK implements dg
{
    private static int[] a;
    private int b;
    private int[] c;
    private float d;
    private float[] e;
    private int f;
    private float[] g;
    private int h;
    private float[] i;
    private float[] as;
    private float[] at;
    private float[] au;
    private float[] av;
    private float[] aw;
    private float[] ax;
    private float[] ay;
    private float[] az;
    private float[] aA;
    private float[] aB;
    private float[] aC;
    private float[] aD;
    private float[] aE;
    private df aF;
    private int aG;
    private int aH;
    private int aI;
    private int aJ;
    private int aK;
    private float aL;
    private float aM;
    private float aN;
    private int aO;
    private int aP;
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        super.a(n, n2, n3, n4);
        this.aH = 3;
        this.aI = 0;
        this.aJ = 0;
        this.aK = 0;
        this.aL = 8.0f;
        this.aP = 5;
        this.d = 0.0f;
        this.b = 1;
        this.e = new float[n4];
        this.f = n4 - this.N;
        this.g = new float[n4];
        this.h = n4 - this.N;
        this.i = VT_6_1_0_11.z.a(this.N, n2);
        this.au = VT_6_1_0_11.z.a(n3, this.U);
        this.at = new float[n3 + 1];
        this.as = new float[this.N];
        this.ay = new float[n3 + 1];
        this.ah = new float[n3 + 1];
        this.az = new float[n3 + 1];
        this.aA = new float[n3 + 1];
        this.av = new float[n3];
        this.ae = new float[n3];
        this.aw = new float[n3];
        this.af = new float[n3];
        this.ax = new float[n3];
        this.ag = new float[n3];
        this.aB = new float[n3];
        this.ai = new float[n3];
        this.aC = new float[n3];
        this.aD = new float[n3];
        this.aE = new float[n3];
        this.aF = new df();
        this.aG = 0;
        this.aN = 0.0f;
        this.aO = 8000;
        this.ak = new float[n3 + 1];
        this.al = new float[n3 + 1];
        this.am = new float[n3 + 1];
        this.G.a();
        this.c = new int[this.M];
    }
    
    public final int a(final cH ch, final float[] array) {
        System.arraycopy(this.Y, this.K, this.Y, 0, this.P - this.K);
        this.Y[this.P - this.K] = array[0] - 0.0f * this.X;
        for (int i = 1; i < this.K; ++i) {
            this.Y[this.P - this.K + i] = array[i] - 0.0f * array[i - 1];
        }
        this.X = array[this.K - 1];
        System.arraycopy(this.e, this.K, this.e, 0, this.P - this.K);
        System.arraycopy(this.aa, this.K, this.aa, 0, this.P - this.K);
        System.arraycopy(this.g, this.K, this.g, 0, this.P - this.K);
        for (int j = 0; j < this.N; ++j) {
            this.as[j] = this.Y[j + this.Z] * this.i[j];
        }
        VT_6_1_0_11.M.a(this.as, this.at, this.O + 1, this.N);
        final float[] at = this.at;
        final int n = 0;
        at[n] += 10.0f;
        final float[] at2 = this.at;
        final int n2 = 0;
        at2[n2] *= this.V;
        for (int k = 0; k < this.O + 1; ++k) {
            final float[] at3 = this.at;
            final int n3 = k;
            at3[n3] *= this.au[k];
        }
        VT_6_1_0_11.M.a(this.ad, this.at, this.aB, this.O);
        System.arraycopy(this.ad, 0, this.ad, 1, this.O);
        this.ad[0] = 1.0f;
        int n4;
        if ((n4 = bX.a(this.ad, this.O, this.av, 15, 0.2f)) == this.O) {
            for (int l = 0; l < this.O; ++l) {
                this.av[l] = (float)Math.acos(this.av[l]);
            }
        }
        else {
            if (this.aH > 1) {
                n4 = bX.a(this.ad, this.O, this.av, 11, 0.05f);
            }
            if (n4 == this.O) {
                for (int n5 = 0; n5 < this.O; ++n5) {
                    this.av[n5] = (float)Math.acos(this.av[n5]);
                }
            }
            else {
                for (int n6 = 0; n6 < this.O; ++n6) {
                    this.av[n6] = this.aw[n6];
                }
            }
        }
        float n7 = 0.0f;
        for (int n8 = 0; n8 < this.O; ++n8) {
            n7 += (this.aw[n8] - this.av[n8]) * (this.aw[n8] - this.av[n8]);
        }
        if (this.J != 0) {
            for (int n9 = 0; n9 < this.O; ++n9) {
                this.ax[n9] = this.av[n9];
            }
        }
        else {
            for (int n10 = 0; n10 < this.O; ++n10) {
                this.ax[n10] = 0.375f * this.aw[n10] + 0.625f * this.av[n10];
            }
        }
        bX.a(this.ax, this.O, 0.002f);
        for (int n11 = 0; n11 < this.O; ++n11) {
            this.ax[n11] = (float)Math.cos(this.ax[n11]);
        }
        this.F.a(this.ax, this.ay, this.O);
        int n12;
        float n13;
        if (this.H[this.I] == null || this.aI != 0 || this.H[this.I].b != 0 || this.H[this.I].a != -1) {
            final int[] array2 = new int[6];
            final float[] array3 = new float[6];
            ba.a(this.S, this.ay, this.az, this.O);
            ba.a(this.T, this.ay, this.aA, this.O);
            ba.a(this.Y, this.Z, this.az, this.aA, this.g, this.h, this.K, this.O, this.aD, 0);
            bI.a(this.g, this.h, this.Q, this.R, this.K, array2, array3, 6);
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
        ba.b(this.Y, this.Z, this.ay, this.aa, this.ab, this.K, this.O, this.aE);
        float n15 = 0.0f;
        for (int n16 = 0; n16 < this.K; ++n16) {
            n15 += this.aa[this.ab + n16] * this.aa[this.ab + n16];
        }
        final float n17 = (float)Math.sqrt(1.0f + n15 / this.K);
        if (this.aF != null && this.aI != 0) {
            this.aM = this.aF.a(array, this.K, n13);
            if (this.aI != 0) {
                int n18 = 0;
                float n19 = 100.0f;
                for (int n20 = 8; n20 > 0; --n20) {
                    final int n21;
                    float n22;
                    if ((n21 = (int)Math.floor(this.aL)) == 10) {
                        n22 = df.a[n20][n21];
                    }
                    else {
                        n22 = (this.aL - n21) * df.a[n20][n21 + 1] + (n21 + 1 - this.aL) * df.a[n20][n21];
                    }
                    if (this.aM > n22 && this.aM - n22 < n19) {
                        n18 = n20;
                        n19 = this.aM - n22;
                    }
                }
                int n23;
                if ((n23 = n18) == 0) {
                    if (this.aG == 0 || n7 > 0.05 || this.ar == 0 || this.aG > 20) {
                        n23 = 1;
                        this.aG = 1;
                    }
                    else {
                        n23 = 0;
                        ++this.aG;
                    }
                }
                else {
                    this.aG = 0;
                }
                this.b(n23);
            }
            else {
                int ap;
                if (this.aM < 2.0f) {
                    if (this.aG == 0 || n7 > 0.05 || this.ar == 0 || this.aG > 20) {
                        this.aG = 1;
                        ap = 1;
                    }
                    else {
                        ap = 0;
                        ++this.aG;
                    }
                }
                else {
                    this.aG = 0;
                    ap = this.aP;
                }
                this.I = ap;
            }
        }
        else {
            this.aM = -1.0f;
        }
        ch.a(0, 1);
        ch.a(this.I, 4);
        if (this.H[this.I] == null) {
            for (int n24 = 0; n24 < this.K; ++n24) {
                final float[] aa = this.aa;
                final int n25 = this.ab + n24;
                final float[] e = this.e;
                final int n26 = this.f + n24;
                final float[] g = this.g;
                final int n27 = this.h + n24;
                final float n28 = 0.0f;
                g[n27] = n28;
                aa[n25] = (e[n26] = n28);
            }
            for (int n29 = 0; n29 < this.O; ++n29) {
                this.aC[n29] = 0.0f;
            }
            this.J = 1;
            this.b = 1;
            ba.a(this.aa, this.ab, this.ah, this.Y, this.Z, this.K, this.O, this.ai);
            array[0] = this.Y[this.Z] + 0.0f * this.d;
            for (int n30 = 1; n30 < this.K; ++n30) {
                final int n31 = n30;
                final float[] y = this.Y;
                final int z = n30;
                this.Z = z;
                array[n31] = y[z] + 0.0f * array[n30 - 1];
            }
            this.d = array[this.K - 1];
            return 0;
        }
        if (this.J != 0) {
            for (int n32 = 0; n32 < this.O; ++n32) {
                this.aw[n32] = this.av[n32];
            }
        }
        this.H[this.I].e.a(this.av, this.ae, this.O, ch);
        if (this.H[this.I].a != -1) {
            ch.a(n12 - this.Q, 7);
        }
        if (this.H[this.I].b != 0) {
            int n33;
            if ((n33 = (int)Math.floor(0.5 + 15.0f * n13)) > 15) {
                n33 = 15;
            }
            if (n33 < 0) {
                n33 = 0;
            }
            ch.a(n33, 4);
            n13 = 0.066667f * n33;
        }
        int n34;
        if ((n34 = (int)Math.floor(0.5 + 3.5 * Math.log(n17))) < 0) {
            n34 = 0;
        }
        if (n34 > 31) {
            n34 = 31;
        }
        final float n35 = (float)Math.exp(n34 / 3.5);
        ch.a(n34, 5);
        if (this.J != 0) {
            for (int n36 = 0; n36 < this.O; ++n36) {
                this.af[n36] = this.ae[n36];
            }
        }
        final float[] array4 = new float[this.L];
        final float[] array5 = new float[this.L];
        final float[] array6 = new float[this.L];
        final float[] array7 = new float[this.O];
        final float[] array8 = new float[this.K];
        for (int n37 = 0; n37 < this.K; ++n37) {
            array8[n37] = this.Y[this.Z + n37];
        }
        for (int n38 = 0; n38 < this.M; ++n38) {
            final int n39 = this.L * n38;
            final int n40 = this.Z + n39;
            final int n41 = this.ab + n39;
            final int n42 = this.h + n39;
            final int n43 = this.f + n39;
            final float n44 = (float)(1.0 + n38) / this.M;
            for (int n45 = 0; n45 < this.O; ++n45) {
                this.ax[n45] = (1.0f - n44) * this.aw[n45] + n44 * this.av[n45];
            }
            for (int n46 = 0; n46 < this.O; ++n46) {
                this.ag[n46] = (1.0f - n44) * this.af[n46] + n44 * this.ae[n46];
            }
            bX.a(this.ax, this.O, 0.002f);
            bX.a(this.ag, this.O, 0.002f);
            for (int n47 = 0; n47 < this.O; ++n47) {
                this.ax[n47] = (float)Math.cos(this.ax[n47]);
            }
            this.F.a(this.ax, this.ay, this.O);
            for (int n48 = 0; n48 < this.O; ++n48) {
                this.ag[n48] = (float)Math.cos(this.ag[n48]);
            }
            this.F.a(this.ag, this.ah, this.O);
            float n49 = 1.0f;
            this.aj[n38] = 0.0f;
            for (int n50 = 0; n50 <= this.O; ++n50) {
                final float[] aj = this.aj;
                final int n51 = n38;
                aj[n51] += n49 * this.ah[n50];
                n49 = -n49;
            }
            ba.a(this.S, this.ay, this.az, this.O);
            if (this.T >= 0.0f) {
                ba.a(this.T, this.ay, this.aA, this.O);
            }
            else {
                this.aA[0] = 1.0f;
                this.aA[1] = 0.0f;
                for (int n52 = 2; n52 <= this.O; ++n52) {
                    this.aA[n52] = 0.0f;
                }
            }
            for (int n53 = 0; n53 < this.L; ++n53) {
                this.aa[n41 + n53] = 0.0f;
            }
            this.aa[n41] = 1.0f;
            ba.a(this.aa, n41, this.ah, this.az, this.aA, array6, this.L, this.O);
            for (int n54 = 0; n54 < this.L; ++n54) {
                this.aa[n41 + n54] = 0.0f;
            }
            for (int n55 = 0; n55 < this.L; ++n55) {
                this.e[n43 + n55] = 0.0f;
            }
            for (int n56 = 0; n56 < this.O; ++n56) {
                array7[n56] = this.ai[n56];
            }
            ba.a(this.aa, n41, this.ah, this.aa, n41, this.L, this.O, array7);
            for (int n57 = 0; n57 < this.O; ++n57) {
                array7[n57] = this.aC[n57];
            }
            ba.a(this.aa, n41, this.az, this.aA, array4, 0, this.L, this.O, array7, 0);
            for (int n58 = 0; n58 < this.O; ++n58) {
                array7[n58] = this.aC[n58];
            }
            ba.a(this.Y, n40, this.az, this.aA, this.g, n42, this.L, this.O, array7, 0);
            for (int n59 = 0; n59 < this.L; ++n59) {
                array5[n59] = this.g[n42 + n59] - array4[n59];
            }
            for (int n60 = 0; n60 < this.L; ++n60) {
                this.aa[n41 + n60] = (this.e[n43 + n60] = 0.0f);
            }
            int q;
            int r;
            if (this.H[this.I].a != -1) {
                final int a;
                if ((a = this.H[this.I].a) != 0) {
                    if (n12 < this.Q + a - 1) {
                        n12 = this.Q + a - 1;
                    }
                    if (n12 > this.R - a) {
                        n12 = this.R - a;
                    }
                    q = n12 - a + 1;
                    r = n12 + a;
                }
                else {
                    r = (q = n12);
                }
            }
            else {
                q = this.Q;
                r = this.R;
            }
            if (this.b != 0 && r > n39) {
                r = n39;
            }
            this.c[n38] = this.H[this.I].f.a(array5, this.g, n42, this.ah, this.az, this.aA, this.aa, n41, q, r, n13, this.O, this.L, ch, this.e, n43, array6, this.aH);
            ba.a(this.aa, n41, this.ah, this.az, this.aA, array4, this.L, this.O);
            for (int n61 = 0; n61 < this.L; ++n61) {
                final float[] array9 = array5;
                final int n62 = n61;
                array9[n62] -= array4[n61];
            }
            float n63 = 0.0f;
            final int n64 = n38 * this.L;
            for (int n65 = 0; n65 < this.L; ++n65) {
                this.ac[n64 + n65] = 0.0f;
            }
            ba.b(array5, 0, this.ah, this.az, this.aA, this.as, this.L, this.O);
            for (int n66 = 0; n66 < this.L; ++n66) {
                n63 += this.as[n66] * this.as[n66];
            }
            final float n67 = (float)Math.sqrt(0.1f + n63 / this.L) / n35;
            float n70;
            if (this.H[this.I].c != 0) {
                final float n68 = (float)Math.log(n67);
                float n69;
                if (this.H[this.I].c == 3) {
                    final int a2 = VT_6_1_0_11.aK.a(n68, cA.E, 8);
                    ch.a(a2, 3);
                    n69 = cA.E[a2];
                }
                else {
                    final int a3 = VT_6_1_0_11.aK.a(n68, cA.D, 2);
                    ch.a(a3, 1);
                    n69 = cA.D[a3];
                }
                n70 = (float)Math.exp(n69);
            }
            else {
                n70 = 1.0f;
            }
            final float n71 = n70 * n35;
            final float n72 = 1.0f / n71;
            for (int n73 = 0; n73 < this.L; ++n73) {
                final float[] array10 = array5;
                final int n74 = n73;
                array10[n74] *= n72;
            }
            this.H[this.I].g.a(array5, this.ah, this.az, this.aA, this.O, this.L, this.ac, n64, array6, ch, this.aH);
            for (int n75 = 0; n75 < this.L; ++n75) {
                final float[] ac = this.ac;
                final int n76 = n64 + n75;
                ac[n76] *= n71;
            }
            for (int n77 = 0; n77 < this.L; ++n77) {
                final float[] aa2 = this.aa;
                final int n78 = n41 + n77;
                aa2[n78] += this.ac[n64 + n77];
            }
            if (this.H[this.I].d != 0) {
                final float[] array11 = new float[this.L];
                for (int n79 = 0; n79 < this.L; ++n79) {
                    final float[] array12 = array5;
                    final int n80 = n79;
                    array12[n80] *= 2.2;
                }
                this.H[this.I].g.a(array5, this.ah, this.az, this.aA, this.O, this.L, array11, 0, array6, ch, this.aH);
                for (int n81 = 0; n81 < this.L; ++n81) {
                    final float[] array13 = array11;
                    final int n82 = n81;
                    array13[n82] *= (float)(n71 * 0.45454545454545453);
                }
                for (int n83 = 0; n83 < this.L; ++n83) {
                    final float[] aa3 = this.aa;
                    final int n84 = n41 + n83;
                    aa3[n84] += array11[n83];
                }
            }
            for (int n85 = 0; n85 < this.L; ++n85) {
                final float[] array14 = array5;
                final int n86 = n85;
                array14[n86] *= n71;
            }
            for (int n87 = 0; n87 < this.O; ++n87) {
                array7[n87] = this.ai[n87];
            }
            ba.a(this.aa, n41, this.ah, this.Y, n40, this.L, this.O, this.ai);
            ba.a(this.Y, n40, this.az, this.aA, this.g, n42, this.L, this.O, this.aC, 0);
            for (int n88 = 0; n88 < this.L; ++n88) {
                this.e[n43 + n88] = this.aa[n41 + n88];
            }
        }
        if (this.I > 0) {
            for (int n89 = 0; n89 < this.O; ++n89) {
                this.aw[n89] = this.av[n89];
            }
            for (int n90 = 0; n90 < this.O; ++n90) {
                this.af[n90] = this.ae[n90];
            }
        }
        if (this.I == 1) {
            if (this.aG != 0) {
                ch.a(15, 4);
            }
            else {
                ch.a(0, 4);
            }
        }
        this.J = 0;
        float n91 = 0.0f;
        float n92 = 0.0f;
        for (int n93 = 0; n93 < this.K; ++n93) {
            n91 += this.Y[this.Z + n93] * this.Y[this.Z + n93];
            n92 += (this.Y[this.Z + n93] - array8[n93]) * (this.Y[this.Z + n93] - array8[n93]);
        }
        Math.log((n91 + 1.0f) / (n92 + 1.0f));
        array[0] = this.Y[this.Z] + 0.0f * this.d;
        for (int n94 = 1; n94 < this.K; ++n94) {
            array[n94] = this.Y[this.Z + n94] + 0.0f * array[n94 - 1];
        }
        this.d = array[this.K - 1];
        if (this.H[this.I].g instanceof dD || this.I == 0) {
            this.b = 1;
        }
        else {
            this.b = 0;
        }
        return 1;
    }
    
    public final int a() {
        return cA.C[this.I];
    }
    
    public final void a(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n > 10) {
            n = 10;
        }
        final int n2 = cA.a[n];
        this.aP = n2;
        this.I = n2;
    }
    
    public final void b(int n) {
        if (n < 0) {
            n = 0;
        }
        final int n2 = n;
        this.aP = n2;
        this.I = n2;
    }
    
    public final int b() {
        return this.I;
    }
    
    public final void a(final boolean ai) {
        this.aI = (ai ? 1 : 0);
    }
    
    public final boolean i() {
        return this.aI != 0;
    }
    
    public final float j() {
        return this.aM;
    }
    
    static {
        cA.a = new int[] { 1, 8, 2, 3, 3, 4, 4, 5, 5, 6, 7 };
    }
}
