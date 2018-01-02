// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class bb extends ax implements bZ
{
    private bZ as;
    private N at;
    private boolean au;
    private float[] av;
    
    public bb() {
        this.at = new N();
        this.au = true;
    }
    
    public final void a_() {
        this.as = new cT();
        ((cT)this.as).g();
        this.as.a(this.au);
        super.a_();
        this.a(160, 40, 8, 640, 0.7f);
    }
    
    public final void b_() {
        this.as = new bb();
        ((bb)this.as).a_();
        this.as.a(this.au);
        super.b_();
        this.a(320, 80, 8, 1280, 0.5f);
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.a(n, n2, n3, n4, n5);
        this.ab = 0;
        this.av = new float[n2];
    }
    
    public final int a(final cH ch, final float[] array) {
        final int a;
        if ((a = this.as.a(ch, this.g)) != 0) {
            return a;
        }
        final boolean d = this.as.d();
        if (ch == null) {
            this.a(array, d);
            return 0;
        }
        if (ch.b() != 0) {
            ch.b(1);
            this.I = ch.b(3);
        }
        else {
            this.I = 0;
        }
        for (int i = 0; i < this.K; ++i) {
            this.aa[i] = 0.0f;
        }
        if (this.H[this.I] != null) {
            final float[] h = this.as.h();
            final float[] e = this.as.e();
            final float[] f = this.as.f();
            this.H[this.I].e.a(this.ae, this.O, ch);
            if (this.J != 0) {
                for (int j = 0; j < this.O; ++j) {
                    this.af[j] = this.ae[j];
                }
            }
            for (int k = 0; k < this.M; ++k) {
                float n = 0.0f;
                float n2 = 0.0f;
                final int n3 = this.L * k;
                final float n4 = (1.0f + k) / this.M;
                for (int l = 0; l < this.O; ++l) {
                    this.ag[l] = (1.0f - n4) * this.af[l] + n4 * this.ae[l];
                }
                bX.a(this.ag, this.O, 0.05f);
                for (int n5 = 0; n5 < this.O; ++n5) {
                    this.ag[n5] = (float)Math.cos(this.ag[n5]);
                }
                this.F.a(this.ag, this.ah, this.O);
                if (this.au) {
                    final float h2 = this.H[this.I].h;
                    final float m = this.H[this.I].i;
                    final float n6 = h2 - m;
                    ba.a(h2, this.ah, this.ak, this.O);
                    ba.a(m, this.ah, this.al, this.O);
                    ba.a(n6, this.ah, this.am, this.O);
                }
                float n7 = 1.0f;
                this.aj[k] = 0.0f;
                for (int n8 = 0; n8 <= this.O; ++n8) {
                    n2 += n7 * this.ah[n8];
                    n7 = -n7;
                    final float[] aj = this.aj;
                    final int n9 = k;
                    aj[n9] += this.ah[n8];
                }
                final float n10 = Math.abs(1.0f / (Math.abs(n2) + 0.01f) + 0.01f) / (0.01f + Math.abs(1.0f / (Math.abs(h[k]) + 0.01f)));
                for (int n11 = n3; n11 < n3 + this.L; ++n11) {
                    this.aa[n11] = 0.0f;
                }
                if (this.H[this.I].g == null) {
                    final float n12 = (float)Math.exp((ch.b(5) - 10.0) / 8.0) / n10;
                    for (int n13 = n3; n13 < n3 + this.L; ++n13) {
                        this.aa[n13] = this.c * n12 * f[n13];
                    }
                }
                else {
                    final int b = ch.b(4);
                    for (int n14 = n3; n14 < n3 + this.L; ++n14) {
                        n += e[n14] * e[n14];
                    }
                    final float n15 = (float)Math.exp(0.27027026f * b - 2.0f) * (float)Math.sqrt(n + 1.0f) / n10;
                    this.H[this.I].g.a(this.aa, n3, this.L, ch);
                    for (int n16 = n3; n16 < n3 + this.L; ++n16) {
                        final float[] aa = this.aa;
                        final int n17 = n16;
                        aa[n17] *= n15;
                    }
                    if (this.H[this.I].d != 0) {
                        for (int n18 = 0; n18 < this.L; ++n18) {
                            this.av[n18] = 0.0f;
                        }
                        this.H[this.I].g.a(this.av, 0, this.L, ch);
                        for (int n19 = 0; n19 < this.L; ++n19) {
                            final float[] av = this.av;
                            final int n20 = n19;
                            av[n20] *= n15 * 0.4f;
                        }
                        for (int n21 = 0; n21 < this.L; ++n21) {
                            final float[] aa2 = this.aa;
                            final int n22 = n3 + n21;
                            aa2[n22] += this.av[n21];
                        }
                    }
                }
                for (int n23 = n3; n23 < n3 + this.L; ++n23) {
                    this.d[n23] = this.aa[n23];
                }
                if (this.au) {
                    ba.a(this.d, n3, this.al, this.ak, this.L, this.O, this.ai, this.O);
                    ba.a(this.d, n3, this.am, this.ah, this.L, this.O, this.ai, 0);
                }
                else {
                    for (int n24 = 0; n24 < this.O; ++n24) {
                        this.ai[this.O + n24] = 0.0f;
                    }
                    ba.a(this.d, n3, this.ah, this.d, n3, this.L, this.O, this.ai);
                }
            }
            this.G.a(this.g, aO.A, this.e, this.b, 64, this.h);
            this.G.a(this.d, aO.B, this.f, this.b, 64, this.i);
            for (int n25 = 0; n25 < this.b; ++n25) {
                array[n25] = 2.0f * (this.e[n25] - this.f[n25]);
            }
            for (int n26 = 0; n26 < this.O; ++n26) {
                this.af[n26] = this.ae[n26];
            }
            return this.J = 0;
        }
        if (d) {
            this.a(array, true);
            return 0;
        }
        for (int n27 = 0; n27 < this.K; ++n27) {
            this.aa[n27] = 0.0f;
        }
        this.J = 1;
        ba.a(this.aa, this.ab, this.ah, this.d, 0, this.K, this.O, this.ai);
        this.G.a(this.g, aO.A, this.e, this.b, 64, this.h);
        this.G.a(this.d, aO.B, this.f, this.b, 64, this.i);
        for (int n28 = 0; n28 < this.b; ++n28) {
            array[n28] = 2.0f * (this.e[n28] - this.f[n28]);
        }
        return 0;
    }
    
    private int a(final float[] array, final boolean b) {
        int i = 0;
        if (b) {
            i = this.I;
            this.I = 1;
        }
        else {
            ba.a(0.99f, this.ah, this.ah, this.O);
        }
        this.J = 1;
        this.ak = new float[this.O + 1];
        this.al = new float[this.O + 1];
        this.am = new float[this.O + 1];
        if (this.au) {
            float h;
            float j;
            if (this.H[this.I] != null) {
                h = this.H[this.I].h;
                j = this.H[this.I].i;
            }
            else {
                j = (h = 0.7f);
            }
            final float n = h - j;
            ba.a(h, this.ah, this.ak, this.O);
            ba.a(j, this.ah, this.al, this.O);
            ba.a(n, this.ah, this.am, this.O);
        }
        if (!b) {
            for (int k = 0; k < this.K; ++k) {
                final float[] aa = this.aa;
                final int n2 = this.ab + k;
                aa[n2] *= 0.9;
            }
        }
        for (int l = 0; l < this.K; ++l) {
            this.d[l] = this.aa[this.ab + l];
        }
        if (this.au) {
            ba.a(this.d, 0, this.al, this.ak, this.d, 0, this.K, this.O, this.ai, this.O);
            ba.a(this.d, 0, this.am, this.ah, this.d, 0, this.K, this.O, this.ai, 0);
        }
        else {
            for (int n3 = 0; n3 < this.O; ++n3) {
                this.ai[this.O + n3] = 0.0f;
            }
            ba.a(this.d, 0, this.ah, this.d, 0, this.K, this.O, this.ai);
        }
        this.G.a(this.g, aO.A, this.e, this.b, 64, this.h);
        this.G.a(this.d, aO.B, this.f, this.b, 64, this.i);
        for (int n4 = 0; n4 < this.b; ++n4) {
            array[n4] = 2.0f * (this.e[n4] - this.f[n4]);
        }
        if (b) {
            this.I = i;
        }
        return 0;
    }
    
    public final void a(final float[] array, final int n) {
        this.at.a(array, n);
    }
    
    public final void a(final boolean au) {
        this.au = au;
    }
}
