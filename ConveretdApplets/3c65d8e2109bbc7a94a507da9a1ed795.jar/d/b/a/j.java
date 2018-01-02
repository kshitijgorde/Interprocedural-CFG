// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

import java.io.StreamCorruptedException;
import java.util.Random;

public class j extends m implements aa
{
    private float[] bN;
    private int bL;
    private int bO;
    private float bE;
    private float[] bF;
    private int bI;
    private float bK;
    protected Random bG;
    protected w bM;
    protected g bJ;
    protected boolean bH;
    
    public j() {
        this.bG = new Random();
        this.bM = new w();
        this.bJ = new g(this.bM);
        this.bH = true;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        super.a(n, n2, n3, n4);
        this.C.a();
        this.bN = new float[40];
        this.bL = 0;
        this.bO = 40;
        this.bE = 0.0f;
        this.bF = new float[3];
        this.bI = 0;
        this.bK = 0.0f;
    }
    
    public int a(final ad ad, final float[] array) throws StreamCorruptedException {
        int n = 0;
        final float[] array2 = new float[3];
        float n2 = 0.0f;
        int bo = 40;
        float n3 = 0.0f;
        float n4 = 0.0f;
        if (ad == null && this.H != 0) {
            this.t = 0;
        }
        else {
            if (ad == null) {
                this.a(array);
                return 0;
            }
            int i;
            do {
                if (ad.a(1) != 0) {
                    final int a = ad.a(3);
                    int n5 = d.b.a.c.am[a];
                    if (n5 < 0) {
                        throw new StreamCorruptedException("Invalid sideband mode encountered (1st sideband): " + a);
                    }
                    n5 -= 4;
                    ad.if(n5);
                    if (ad.a(1) != 0) {
                        final int a2 = ad.a(3);
                        int n6 = d.b.a.c.am[a2];
                        if (n6 < 0) {
                            throw new StreamCorruptedException("Invalid sideband mode encountered. (2nd sideband): " + a2);
                        }
                        n6 -= 4;
                        ad.if(n6);
                        if (ad.a(1) != 0) {
                            throw new StreamCorruptedException("More than two sideband layers found");
                        }
                    }
                }
                i = ad.a(4);
                if (i == 15) {
                    return 1;
                }
                if (i == 14) {
                    this.bJ.a(ad);
                }
                else if (i == 13) {
                    this.bJ.if(ad);
                }
                else {
                    if (i > 8) {
                        throw new StreamCorruptedException("Invalid mode encountered: " + i);
                    }
                    continue;
                }
            } while (i > 8);
            this.t = i;
        }
        System.arraycopy(this.A, this.z, this.A, 0, this.X - this.z);
        System.arraycopy(this.m, this.z, this.m, 0, this.X - this.z);
        if (this.aa[this.t] == null) {
            d.b.a.ae.a(0.93f, this.D, this.ab, 10);
            float n7 = 0.0f;
            for (int j = 0; j < this.z; ++j) {
                n7 += this.x[j] * this.x[j];
            }
            final float n8 = (float)Math.sqrt(n7 / this.z);
            for (int k = this.K; k < this.K + this.z; ++k) {
                this.m[k] = 3.0f * n8 * (this.bG.nextFloat() - 0.5f);
            }
            this.y = 1;
            d.b.a.ae.if(this.m, this.K, this.ab, this.A, this.Z, this.z, this.n, this.R);
            array[0] = this.A[this.Z] + this.S * this.V;
            for (int l = 1; l < this.z; ++l) {
                array[l] = this.A[this.Z + l] + this.S * array[l - 1];
            }
            this.V = array[this.z - 1];
            return this.bL = 0;
        }
        this.aa[this.t].else.a(this.v, this.n, ad);
        if (this.bL != 0) {
            float n9 = 0.0f;
            for (int n10 = 0; n10 < this.n; ++n10) {
                n9 += Math.abs(this.W[n10] - this.v[n10]);
            }
            final float n11 = (float)(0.6 * Math.exp(-0.2 * n9));
            for (int n12 = 0; n12 < 2 * this.n; ++n12) {
                final float[] r = this.R;
                final int n13 = n12;
                r[n13] *= n11;
            }
        }
        if (this.y != 0 || this.bL != 0) {
            for (int n14 = 0; n14 < this.n; ++n14) {
                this.W[n14] = this.v[n14];
            }
        }
        if (this.aa[this.t].byte != -1) {
            n = this.N + ad.a(7);
        }
        if (this.aa[this.t].try != 0) {
            n2 = 0.066667f * ad.a(4);
        }
        final float bk = (float)Math.exp(ad.a(5) / 3.5);
        if (this.t == 1) {
            if (ad.a(4) == 15) {
                this.H = 1;
            }
            else {
                this.H = 0;
            }
        }
        if (this.t > 1) {
            this.H = 0;
        }
        for (int n15 = 0; n15 < this.Q; ++n15) {
            final int n16 = this.T * n15;
            final int n17 = this.Z + n16;
            final int n18 = this.K + n16;
            final float n19 = (1.0f + n15) / this.Q;
            for (int n20 = 0; n20 < this.n; ++n20) {
                this.B[n20] = (1.0f - n19) * this.W[n20] + n19 * this.v[n20];
            }
            d.b.a.y.a(this.B, this.n, 0.002f);
            for (int n21 = 0; n21 < this.n; ++n21) {
                this.B[n21] = (float)Math.cos(this.B[n21]);
            }
            this.ad.a(this.B, this.D, this.n);
            if (this.bH) {
                final float n22 = 0.9f;
                final float if1 = this.aa[this.t].if;
                final float a3 = this.aa[this.t].a;
                final float n23 = (1.0f - (1.0f - n22 * if1) / (1.0f - n22 * a3)) / n22;
                d.b.a.ae.a(if1, this.D, this.P, this.n);
                d.b.a.ae.a(a3, this.D, this.O, this.n);
                d.b.a.ae.a(n23, this.D, this.L, this.n);
            }
            float n24 = 1.0f;
            this.r[n15] = 0.0f;
            for (int n25 = 0; n25 <= this.n; ++n25) {
                final float[] r2 = this.r;
                final int n26 = n15;
                r2[n26] += n24 * this.D[n25];
                n24 = -n24;
            }
            for (int n27 = 0; n27 < this.T; ++n27) {
                this.m[n18 + n27] = 0.0f;
            }
            int n28;
            if (this.aa[this.t].byte != -1) {
                final int byte1 = this.aa[this.t].byte;
                if (byte1 != 0) {
                    n28 = n - byte1 + 1;
                    if (n28 < this.N) {
                        n28 = this.N;
                    }
                    if (n + byte1 > this.ae) {
                        final int ae = this.ae;
                    }
                }
                else {
                    n28 = n;
                }
            }
            else {
                n28 = this.N;
                final int ae2 = this.ae;
            }
            final int a4 = this.aa[this.t].new.a(this.m, n18, n28, n2, this.T, array2, ad, this.bL, n16, this.bE);
            if (this.bL != 0 && bk < this.bK) {
                final float n29 = bk / (this.bK + 1.0f);
                for (int n30 = 0; n30 < this.T; ++n30) {
                    final float[] m = this.m;
                    final int n31 = this.K + n30;
                    m[n31] *= n29;
                }
            }
            Math.abs(array2[0] + array2[1] + array2[2]);
            final float abs = Math.abs(array2[1]);
            float n32;
            if (array2[0] > 0.0f) {
                n32 = abs + array2[0];
            }
            else {
                n32 = (float)(abs - 0.5 * array2[0]);
            }
            float n33;
            if (array2[2] > 0.0f) {
                n33 = n32 + array2[2];
            }
            else {
                n33 = (float)(n32 - 0.5 * array2[0]);
            }
            n4 += n33;
            if (n33 > n3) {
                bo = a4;
                n3 = n33;
            }
            int n35;
            int n34;
            for (n34 = (n35 = n15 * this.T); n35 < n34 + this.T; ++n35) {
                this.x[n35] = 0.0f;
            }
            float n36;
            if (this.aa[this.t].case == 3) {
                n36 = (float)(bk * Math.exp(j.E[ad.a(3)]));
            }
            else if (this.aa[this.t].case == 1) {
                n36 = (float)(bk * Math.exp(j.F[ad.a(1)]));
            }
            else {
                n36 = bk;
            }
            if (this.aa[this.t].for != null) {
                this.aa[this.t].for.a(this.x, n34, this.T, ad);
            }
            for (int n37 = n34; n37 < n34 + this.T; ++n37) {
                final float[] x = this.x;
                final int n38 = n37;
                x[n38] *= n36;
            }
            if (this.t == 1) {
                final float n39 = n2;
                for (int n40 = 0; n40 < this.T; ++n40) {
                    this.m[n18 + n40] = 0.0f;
                }
                while (this.G < this.T) {
                    if (this.G >= 0) {
                        this.m[n18 + this.G] = (float)Math.sqrt(1.0f * n);
                    }
                    this.G += n;
                }
                this.G -= this.T;
                float n41 = 0.5f + 2.0f * (n39 - 0.6f);
                if (n41 < 0.0f) {
                    n41 = 0.0f;
                }
                if (n41 > 1.0f) {
                    n41 = 1.0f;
                }
                for (int n42 = 0; n42 < this.T; ++n42) {
                    final float q = this.m[n18 + n42];
                    this.m[n18 + n42] = 0.8f * n41 * this.m[n18 + n42] * bk + 0.6f * n41 * this.q * bk + 0.5f * n41 * this.x[n34 + n42] - 0.5f * n41 * this.p + (1.0f - n41) * this.x[n34 + n42];
                    this.q = q;
                    this.p = this.x[n34 + n42];
                    this.Y = 0.95f * this.Y + 0.05f * this.m[n18 + n42];
                    final float[] m2 = this.m;
                    final int n43 = n18 + n42;
                    m2[n43] -= this.Y;
                }
            }
            else {
                for (int n44 = 0; n44 < this.T; ++n44) {
                    final float[] m3 = this.m;
                    final int n45 = n18 + n44;
                    m3[n45] += this.x[n34 + n44];
                }
            }
            if (this.aa[this.t].do != 0) {
                for (int n46 = 0; n46 < this.T; ++n46) {
                    this.bN[n46] = 0.0f;
                }
                this.aa[this.t].for.a(this.bN, 0, this.T, ad);
                for (int n47 = 0; n47 < this.T; ++n47) {
                    final float[] bn = this.bN;
                    final int n48 = n47;
                    bn[n48] *= (float)(n36 * 0.45454545454545453);
                }
                for (int n49 = 0; n49 < this.T; ++n49) {
                    final float[] m4 = this.m;
                    final int n50 = n18 + n49;
                    m4[n50] += this.bN[n49];
                }
            }
            for (int n51 = 0; n51 < this.T; ++n51) {
                this.A[n17 + n51] = this.m[n18 + n51];
            }
            if (this.bH && this.aa[this.t].char > 0.0f) {
                this.C.a(this.m, n18, this.A, n17, this.T, a4, array2, this.aa[this.t].char);
            }
            if (this.bH) {
                d.b.a.ae.a(this.A, n17, this.O, this.P, this.T, this.n, this.R, this.n);
                d.b.a.ae.a(this.A, n17, this.L, this.D, this.T, this.n, this.R, 0);
            }
            else {
                for (int n52 = 0; n52 < this.n; ++n52) {
                    this.R[this.n + n52] = 0.0f;
                }
                d.b.a.ae.if(this.A, n17, this.D, this.A, n17, this.T, this.n, this.R);
            }
        }
        array[0] = this.A[this.Z] + this.S * this.V;
        for (int n53 = 1; n53 < this.z; ++n53) {
            array[n53] = this.A[this.Z + n53] + this.S * array[n53 - 1];
        }
        this.V = array[this.z - 1];
        for (int n54 = 0; n54 < this.n; ++n54) {
            this.W[n54] = this.v[n54];
        }
        this.y = 0;
        this.bL = 0;
        this.bO = bo;
        this.bE = 0.25f * n4;
        this.bF[this.bI++] = this.bE;
        if (this.bI > 2) {
            this.bI = 0;
        }
        this.bK = bk;
        return 0;
    }
    
    public int a(final float[] array) {
        final float n = (float)Math.exp(-0.04 * this.bL * this.bL);
        final float be = (this.bF[0] < this.bF[1]) ? ((this.bF[1] < this.bF[2]) ? this.bF[1] : ((this.bF[0] < this.bF[2]) ? this.bF[2] : this.bF[0])) : ((this.bF[2] < this.bF[1]) ? this.bF[1] : ((this.bF[2] < this.bF[0]) ? this.bF[2] : this.bF[0]));
        if (be < this.bE) {
            this.bE = be;
        }
        float be2 = this.bE;
        if (be2 > 0.95f) {
            be2 = 0.95f;
        }
        final float n2 = be2 * n;
        System.arraycopy(this.A, this.z, this.A, 0, this.X - this.z);
        System.arraycopy(this.m, this.z, this.m, 0, this.X - this.z);
        for (int i = 0; i < this.Q; ++i) {
            final int n3 = this.T * i;
            final int n4 = this.Z + n3;
            final int n5 = this.K + n3;
            if (this.bH) {
                final float n6 = 0.9f;
                float if1;
                float a;
                if (this.aa[this.t] != null) {
                    if1 = this.aa[this.t].if;
                    a = this.aa[this.t].a;
                }
                else {
                    a = (if1 = 0.7f);
                }
                final float n7 = (1.0f - (1.0f - n6 * if1) / (1.0f - n6 * a)) / n6;
                d.b.a.ae.a(if1, this.D, this.P, this.n);
                d.b.a.ae.a(a, this.D, this.O, this.n);
                d.b.a.ae.a(n7, this.D, this.L, this.n);
            }
            float n8 = 0.0f;
            for (int j = 0; j < this.z; ++j) {
                n8 += this.x[j] * this.x[j];
            }
            final float n9 = (float)Math.sqrt(n8 / this.z);
            for (int k = 0; k < this.T; ++k) {
                this.m[n5 + k] = n2 * this.m[n5 + k - this.bO] + n * (float)Math.sqrt(1.0f - n2) * 3.0f * n9 * (this.bG.nextFloat() - 0.5f);
            }
            for (int l = 0; l < this.T; ++l) {
                this.A[n4 + l] = this.m[n5 + l];
            }
            if (this.bH) {
                d.b.a.ae.a(this.A, n4, this.O, this.P, this.T, this.n, this.R, this.n);
                d.b.a.ae.a(this.A, n4, this.L, this.D, this.T, this.n, this.R, 0);
            }
            else {
                for (int n10 = 0; n10 < this.n; ++n10) {
                    this.R[this.n + n10] = 0.0f;
                }
                d.b.a.ae.if(this.A, n4, this.D, this.A, n4, this.T, this.n, this.R);
            }
        }
        array[0] = this.A[0] + this.S * this.V;
        for (int n11 = 1; n11 < this.z; ++n11) {
            array[n11] = this.A[n11] + this.S * array[n11 - 1];
        }
        this.V = array[this.z - 1];
        this.y = 0;
        ++this.bL;
        this.bF[this.bI++] = n2;
        if (this.bI > 2) {
            this.bI = 0;
        }
        return 0;
    }
    
    public void a(final float[] array, final int n) {
        this.bM.a(array, n);
    }
    
    public void a(final boolean bh) {
        this.bH = bh;
    }
    
    public boolean byte() {
        return this.bH;
    }
}
