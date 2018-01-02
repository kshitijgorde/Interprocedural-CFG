// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

import java.io.StreamCorruptedException;

public class f extends c implements aa
{
    protected aa bC;
    protected w bB;
    protected boolean bD;
    private float[] bA;
    
    public f() {
        this.bB = new w();
        this.bD = true;
    }
    
    public void goto() {
        this.bC = new j();
        ((j)this.bC).for();
        this.bC.a(this.bD);
        super.goto();
        this.a(160, 40, 8, 640, 0.7f);
    }
    
    public void char() {
        this.bC = new f();
        ((f)this.bC).goto();
        this.bC.a(this.bD);
        super.char();
        this.a(320, 80, 8, 1280, 0.5f);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.a(n, n2, n3, n4, n5);
        this.K = 0;
        this.bA = new float[n2];
    }
    
    public int a(final ad ad, final float[] array) throws StreamCorruptedException {
        final int a = this.bC.a(ad, this.af);
        if (a != 0) {
            return a;
        }
        final boolean a2 = this.bC.a();
        if (ad == null) {
            this.a(array, a2);
            return 0;
        }
        if (ad.a() != 0) {
            ad.a(1);
            this.t = ad.a(3);
        }
        else {
            this.t = 0;
        }
        for (int i = 0; i < this.z; ++i) {
            this.m[i] = 0.0f;
        }
        if (this.aa[this.t] != null) {
            final float[] new1 = this.bC.new();
            final float[] int1 = this.bC.int();
            final float[] if1 = this.bC.if();
            this.aa[this.t].else.a(this.v, this.n, ad);
            if (this.y != 0) {
                for (int j = 0; j < this.n; ++j) {
                    this.W[j] = this.v[j];
                }
            }
            for (int k = 0; k < this.Q; ++k) {
                float n = 0.0f;
                float n2 = 0.0f;
                final int n3 = this.T * k;
                final float n4 = (1.0f + k) / this.Q;
                for (int l = 0; l < this.n; ++l) {
                    this.B[l] = (1.0f - n4) * this.W[l] + n4 * this.v[l];
                }
                d.b.a.y.a(this.B, this.n, 0.05f);
                for (int n5 = 0; n5 < this.n; ++n5) {
                    this.B[n5] = (float)Math.cos(this.B[n5]);
                }
                this.ad.a(this.B, this.D, this.n);
                if (this.bD) {
                    final float if2 = this.aa[this.t].if;
                    final float a3 = this.aa[this.t].a;
                    final float n6 = if2 - a3;
                    d.b.a.ae.a(if2, this.D, this.P, this.n);
                    d.b.a.ae.a(a3, this.D, this.O, this.n);
                    d.b.a.ae.a(n6, this.D, this.L, this.n);
                }
                float n7 = 1.0f;
                this.r[k] = 0.0f;
                for (int n8 = 0; n8 <= this.n; ++n8) {
                    n2 += n7 * this.D[n8];
                    n7 = -n7;
                    final float[] r = this.r;
                    final int n9 = k;
                    r[n9] += this.D[n8];
                }
                final float n10 = Math.abs(0.01f + 1.0f / (Math.abs(n2) + 0.01f)) / (0.01f + Math.abs(1.0f / (Math.abs(new1[k]) + 0.01f)));
                for (int n11 = n3; n11 < n3 + this.T; ++n11) {
                    this.m[n11] = 0.0f;
                }
                if (this.aa[this.t].for == null) {
                    final float n12 = (float)Math.exp((ad.a(5) - 10.0) / 8.0) / n10;
                    for (int n13 = n3; n13 < n3 + this.T; ++n13) {
                        this.m[n13] = this.ao * n12 * if1[n13];
                    }
                }
                else {
                    final int a4 = ad.a(4);
                    for (int n14 = n3; n14 < n3 + this.T; ++n14) {
                        n += int1[n14] * int1[n14];
                    }
                    final float n15 = (float)Math.exp(0.27027026f * a4 - 2.0f) * (float)Math.sqrt(1.0f + n) / n10;
                    this.aa[this.t].for.a(this.m, n3, this.T, ad);
                    for (int n16 = n3; n16 < n3 + this.T; ++n16) {
                        final float[] m = this.m;
                        final int n17 = n16;
                        m[n17] *= n15;
                    }
                    if (this.aa[this.t].do != 0) {
                        for (int n18 = 0; n18 < this.T; ++n18) {
                            this.bA[n18] = 0.0f;
                        }
                        this.aa[this.t].for.a(this.bA, 0, this.T, ad);
                        for (int n19 = 0; n19 < this.T; ++n19) {
                            final float[] ba = this.bA;
                            final int n20 = n19;
                            ba[n20] *= n15 * 0.4f;
                        }
                        for (int n21 = 0; n21 < this.T; ++n21) {
                            final float[] m2 = this.m;
                            final int n22 = n3 + n21;
                            m2[n22] += this.bA[n21];
                        }
                    }
                }
                for (int n23 = n3; n23 < n3 + this.T; ++n23) {
                    this.ai[n23] = this.m[n23];
                }
                if (this.bD) {
                    d.b.a.ae.a(this.ai, n3, this.O, this.P, this.T, this.n, this.R, this.n);
                    d.b.a.ae.a(this.ai, n3, this.L, this.D, this.T, this.n, this.R, 0);
                }
                else {
                    for (int n24 = 0; n24 < this.n; ++n24) {
                        this.R[this.n + n24] = 0.0f;
                    }
                    d.b.a.ae.if(this.ai, n3, this.D, this.ai, n3, this.T, this.n, this.R);
                }
            }
            this.C.a(this.af, d.b.a.ab.char, this.aq, this.ak, 64, this.al);
            this.C.a(this.ai, d.b.a.ab.case, this.ap, this.ak, 64, this.aj);
            for (int n25 = 0; n25 < this.ak; ++n25) {
                array[n25] = 2.0f * (this.aq[n25] - this.ap[n25]);
            }
            for (int n26 = 0; n26 < this.n; ++n26) {
                this.W[n26] = this.v[n26];
            }
            return this.y = 0;
        }
        if (a2) {
            this.a(array, true);
            return 0;
        }
        for (int n27 = 0; n27 < this.z; ++n27) {
            this.m[n27] = 0.0f;
        }
        this.y = 1;
        d.b.a.ae.if(this.m, this.K, this.D, this.ai, 0, this.z, this.n, this.R);
        this.C.a(this.af, d.b.a.ab.char, this.aq, this.ak, 64, this.al);
        this.C.a(this.ai, d.b.a.ab.case, this.ap, this.ak, 64, this.aj);
        for (int n28 = 0; n28 < this.ak; ++n28) {
            array[n28] = 2.0f * (this.aq[n28] - this.ap[n28]);
        }
        return 0;
    }
    
    public int a(final float[] array, final boolean b) {
        int t = 0;
        if (b) {
            t = this.t;
            this.t = 1;
        }
        else {
            d.b.a.ae.a(0.99f, this.D, this.D, this.n);
        }
        this.y = 1;
        this.P = new float[this.n + 1];
        this.O = new float[this.n + 1];
        this.L = new float[this.n + 1];
        if (this.bD) {
            float if1;
            float a;
            if (this.aa[this.t] != null) {
                if1 = this.aa[this.t].if;
                a = this.aa[this.t].a;
            }
            else {
                a = (if1 = 0.7f);
            }
            final float n = if1 - a;
            d.b.a.ae.a(if1, this.D, this.P, this.n);
            d.b.a.ae.a(a, this.D, this.O, this.n);
            d.b.a.ae.a(n, this.D, this.L, this.n);
        }
        if (!b) {
            for (int i = 0; i < this.z; ++i) {
                final float[] m = this.m;
                final int n2 = this.K + i;
                m[n2] *= 0.9;
            }
        }
        for (int j = 0; j < this.z; ++j) {
            this.ai[j] = this.m[this.K + j];
        }
        if (this.bD) {
            d.b.a.ae.a(this.ai, 0, this.O, this.P, this.ai, 0, this.z, this.n, this.R, this.n);
            d.b.a.ae.a(this.ai, 0, this.L, this.D, this.ai, 0, this.z, this.n, this.R, 0);
        }
        else {
            for (int k = 0; k < this.n; ++k) {
                this.R[this.n + k] = 0.0f;
            }
            d.b.a.ae.if(this.ai, 0, this.D, this.ai, 0, this.z, this.n, this.R);
        }
        this.C.a(this.af, d.b.a.ab.char, this.aq, this.ak, 64, this.al);
        this.C.a(this.ai, d.b.a.ab.case, this.ap, this.ak, 64, this.aj);
        for (int l = 0; l < this.ak; ++l) {
            array[l] = 2.0f * (this.aq[l] - this.ap[l]);
        }
        if (b) {
            this.t = t;
        }
        return 0;
    }
    
    public void a(final float[] array, final int n) {
        this.bB.a(array, n);
    }
    
    public void a(final boolean bd) {
        this.bD = bd;
    }
    
    public boolean byte() {
        return this.bD;
    }
}
