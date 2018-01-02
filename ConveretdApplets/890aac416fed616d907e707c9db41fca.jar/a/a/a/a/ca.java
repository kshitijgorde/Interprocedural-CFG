// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ca extends a2
{
    private char[] gn;
    private int gw;
    public double[] gr;
    public double[] gq;
    public double[] gp;
    public double[] gD;
    public double[] gC;
    public double[] gB;
    public double[] gz;
    private double gA;
    private int[] gu;
    private int[] gv;
    private int[] gx;
    private int[] gs;
    private int gy;
    private int gt;
    private double go;
    private double gm;
    
    public ca() {
        this.gn = new char[] { 's', 'f', 'x', '\0' };
        this.gt = 10000;
        this.go = 300.0;
        this.gm = 1.0;
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t cb) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.cB = cb;
        super.goto = cb.h;
        super.int = 1;
        super.case = this.gn;
    }
    
    public void int(final bh bh) {
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
            }
            else if (bh.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = new Integer(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("visible") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("play") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.ek = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("raindrops") == 0) {
                this.gt = an.a(bh.new[i]);
                if (this.gt < 1) {
                    this.gt = 10000;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("maxdist") == 0) {
                this.go = an.a(bh.new[i]);
                if (this.go < 1.0) {
                    this.go = 1.0;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("mindist") == 0) {
                this.gm = an.a(bh.new[i]);
                if (this.gm < 0.0) {
                    this.gm = 0.0;
                }
            }
            if (this.go < this.gm) {
                final double gm = this.gm;
                this.gm = this.go;
                this.go = gm;
            }
        }
        this.a();
        super.byte = true;
        super.goto = true;
        super.else = true;
        try {
            this.gw = 0;
            this.gr = new double[this.gt];
            this.gq = new double[this.gt];
            this.gp = new double[this.gt];
            this.gD = new double[this.gt];
            this.gC = new double[this.gt];
            this.gB = new double[this.gt];
            this.gz = new double[this.gt];
            this.gA = 6.2831853;
            this.gu = new int[this.gt];
            this.gv = new int[this.gt];
            this.gx = new int[this.gt];
            this.gs = new int[this.gt];
            this.gy = 0;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Not enough memory to play Rain effect.");
            super.goto = false;
        }
    }
    
    public boolean a(final long n) {
        this.l();
        return super.for && super.byte;
    }
    
    boolean byte(final boolean b) {
        return super.byte = true;
    }
    
    void new(final long n) {
        super.byte = true;
    }
    
    public void if() {
    }
    
    public void new(final boolean b) {
        if (!super.goto || !super.for || !super.byte) {
            return;
        }
        try {
            final ap ef = super.cC.eF;
            if (ef.s < -ef.n || ef.r < -ef.m || ef.n > ef.s || ef.m > ef.r) {
                return;
            }
            int l = ef.l;
            int p = ef.p;
            int n = ef.n;
            int m = ef.m;
            if (n < 0) {
                l += n;
                n = 0;
            }
            if (m < 0) {
                p += m;
                m = 0;
            }
            if (n + l > ef.s) {
                l = ef.s - n;
            }
            if (m + p > ef.r) {
                p = ef.r - m;
            }
            final int n2 = l;
            final int n3 = p;
            this.gy = 0;
            if (this.gw == 0) {
                for (int i = 0; i < this.gt; ++i) {
                    this.gr[i] = Math.random() * 120.0 - 20.0;
                    final double n4 = Math.random() * this.gA;
                    final double n5 = this.gm + Math.random() * (this.go - this.gm);
                    this.gq[i] = n5 * Math.cos(n4);
                    this.gp[i] = n5 * Math.sin(n4);
                    this.gD[i] = -1.0;
                    this.gC[i] = 0.0;
                    this.gB[i] = 0.0;
                    this.gz[i] = 6.0;
                }
                ++this.gw;
            }
            for (int j = 0; j < this.gt; ++j) {
                this.if(this.gr[j], this.gq[j], this.gp[j], j);
            }
            for (int k = 0; k < this.gy; ++k) {
                if ((this.gu[k] < n2 && this.gu[k] >= 0 && this.gv[k] < n3 && this.gv[k] >= 0) || (this.gx[k] < n2 && this.gx[k] >= 0 && this.gs[k] < n3 && this.gs[k] >= 0)) {
                    int n6 = this.gx[k] - this.gu[k];
                    final int n7 = this.gs[k] - this.gv[k];
                    if (n6 != 0 || n7 != 0) {
                        final double sqrt = Math.sqrt(n6 * n6 + n7 * n7);
                        float n8;
                        if (n7 != 0) {
                            n8 = n6 / Math.abs(n7);
                        }
                        else {
                            n8 = n6;
                        }
                        float n9;
                        if (n6 != 0) {
                            n9 = n7 / Math.abs(n6);
                        }
                        else {
                            n9 = n7;
                        }
                        int n10 = 1;
                        if (sqrt > 10.0) {
                            n10 = 2;
                        }
                        final int abs = Math.abs(n6);
                        final int abs2 = Math.abs(n7);
                        final float abs3 = Math.abs(n8);
                        final float abs4 = Math.abs(n9);
                        for (int n11 = 0; n11 < n10; ++n11) {
                            float n12 = 0.0f;
                            float n13 = 0.0f;
                            float n14 = 0.0f;
                            double n15;
                            if (sqrt > 1.0) {
                                n15 = 1.0 / sqrt;
                            }
                            else {
                                n15 = 0.0;
                            }
                            while (Math.abs(n12) <= abs && Math.abs(n13) <= abs2) {
                                if (abs3 >= 1.0f) {
                                    if (n8 > 0.0f) {
                                        ++n12;
                                    }
                                    else {
                                        --n12;
                                    }
                                }
                                else {
                                    n12 += n8;
                                }
                                if (abs4 >= 1.0f) {
                                    if (n9 > 0.0f) {
                                        ++n13;
                                    }
                                    else {
                                        --n13;
                                    }
                                }
                                else {
                                    n13 += n9;
                                }
                                final int n16 = (int)(n12 + 0.5) + this.gu[k];
                                final int n17 = (int)(n13 + 0.5) + this.gv[k];
                                if (n16 < n2 && n16 >= 0 && n17 < n3 && n17 >= 0) {
                                    final int n18 = n + n16 + (n17 + m) * super.cC.eF.s;
                                    n14 += (float)n15;
                                    final float n19 = n14 * 255.0f;
                                    final float n20 = 1.0f / (2.0f + n14);
                                    final int n21 = super.cC.eF.x[n18];
                                    final int n22 = n21 >> 16 & 0xFF;
                                    final int n23 = n21 >> 8 & 0xFF;
                                    final int n24 = n21 & 0xFF;
                                    super.cC.eF.x[n18] = (0xFF000000 | (int)((n19 + n22 + n22) * n20) << 16 | (int)((n19 + n23 + n23) * n20) << 8 | (int)((n19 + n24 + n24) * n20));
                                }
                            }
                            final int[] gu = this.gu;
                            final int n25 = k;
                            ++gu[n25];
                            ++n6;
                        }
                    }
                }
            }
            for (int n26 = 0; n26 < this.gt; ++n26) {
                if (this.gr[n26] < -20.0) {
                    this.gr[n26] = 100.0 + Math.random() * 10.0;
                    final double n27 = Math.random() * this.gA;
                    final double n28 = this.gm + Math.random() * (this.go - this.gm);
                    this.gq[n26] = n28 * Math.cos(n27);
                    this.gp[n26] = n28 * Math.sin(n27);
                    this.gz[n26] = 6.0;
                }
                else {
                    final double[] gr = this.gr;
                    final int n29 = n26;
                    gr[n29] += this.gD[n26] * this.gz[n26];
                    final double[] gq = this.gq;
                    final int n30 = n26;
                    gq[n30] += this.gC[n26] * this.gz[n26];
                    final double[] gp = this.gp;
                    final int n31 = n26;
                    gp[n31] += this.gB[n26] * this.gz[n26];
                }
            }
            this.gy = 0;
        }
        catch (Exception ex) {}
    }
    
    private boolean if(final double n, final double n2, final double n3, final int n4) {
        final double n5 = super.cC.eJ * n2;
        final double n6 = super.cC.eV * n3;
        final double n7 = -super.cC.eT * n5 + super.cC.eT * n6;
        final double n8 = -super.cC.eZ * n5 + super.cC.eZ * n6;
        final double n9 = -super.cC.eZ * n + n7;
        if (n9 > 0.0) {
            return false;
        }
        final double n10 = -super.cC.eZ * (n - 5.0) + n7;
        if (n10 > 0.0) {
            return false;
        }
        final double n11 = super.cC.eT * n + n8;
        final double n12 = super.cC.eT * (n - 5.0) + n8;
        final double n13 = super.cC.eJ * n3 + super.cC.eV * n2;
        final double n14 = super.cC.eI / n9;
        final double n15 = super.cC.eI / n10;
        this.gu[this.gy] = (int)(n13 * n14 + super.cC.eW);
        this.gv[this.gy] = (int)(n11 * n14 + super.cC.eG);
        this.gx[this.gy] = (int)(n13 * n15 + super.cC.eW);
        this.gs[this.gy] = (int)(n12 * n15 + super.cC.eG);
        ++this.gy;
        return true;
    }
}
