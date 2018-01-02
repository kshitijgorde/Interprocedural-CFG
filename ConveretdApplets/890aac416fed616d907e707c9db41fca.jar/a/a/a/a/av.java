// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class av extends a2
{
    private char[] fQ;
    private int fX;
    public double[] fW;
    public double[] fV;
    public double[] fU;
    public double[] gb;
    public double[] ga;
    public double[] f9;
    public double[] fZ;
    public double[] f0;
    public int[] f2;
    private double f8;
    private int[] f5;
    private int[] f6;
    private double[] f4;
    private int[] f3;
    private float fT;
    private int fY;
    private int f1;
    private int[] f7;
    int fR;
    private double fS;
    private double fP;
    
    public av() {
        this.fQ = new char[] { 's', 'f', 'x', '\0' };
        this.fR = 100000;
        this.fS = 200.0;
        this.fP = 0.0;
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
        super.case = this.fQ;
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
            else if (bh.try[i].toLowerCase().compareTo("flakes") == 0) {
                this.fR = an.a(bh.new[i]);
                if (this.fR < 1) {
                    this.fR = 10000;
                }
                if (this.fR > 100000) {
                    this.fR = 100000;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("maxdist") == 0) {
                this.fS = an.a(bh.new[i]);
                if (this.fS < 1.0) {
                    this.fS = 1.0;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("mindist") == 0) {
                this.fP = an.a(bh.new[i]);
                if (this.fP < 0.0) {
                    this.fP = 0.0;
                }
            }
            if (this.fS < this.fP) {
                final double fp = this.fP;
                this.fP = this.fS;
                this.fS = fp;
            }
        }
        this.a();
        super.byte = true;
        super.goto = true;
        super.else = true;
        try {
            this.fX = 0;
            this.fW = new double[this.fR];
            this.fV = new double[this.fR];
            this.fU = new double[this.fR];
            this.gb = new double[this.fR];
            this.ga = new double[this.fR];
            this.f9 = new double[this.fR];
            this.fZ = new double[this.fR];
            this.f0 = new double[this.fR];
            this.f2 = new int[this.fR];
            this.f8 = 6.2831853;
            this.f5 = new int[this.fR];
            this.f6 = new int[this.fR];
            this.f4 = new double[this.fR];
            this.f3 = new int[this.fR];
            this.fY = 0;
            this.f1 = 15;
            this.f7 = new int[this.f1 * this.f1];
            for (int j = 0; j < this.f1 * this.f1; ++j) {
                this.f7[j] = 2;
            }
            this.f7[7] = 1;
            final int[] f7 = this.f7;
            final int n = 21;
            final int[] f8 = this.f7;
            final int n2 = 22;
            final int[] f9 = this.f7;
            final int n3 = 23;
            final boolean b = true;
            f9[n3] = (b ? 1 : 0);
            f7[n] = (f8[n2] = (b ? 1 : 0));
            final int[] f10 = this.f7;
            final int n4 = 32;
            final int[] f11 = this.f7;
            final int n5 = 34;
            final int[] f12 = this.f7;
            final int n6 = 37;
            final int[] f13 = this.f7;
            final int n7 = 40;
            final int[] f14 = this.f7;
            final int n8 = 42;
            final boolean b2 = true;
            f14[n8] = (b2 ? 1 : 0);
            f12[n6] = (f13[n7] = (b2 ? 1 : 0));
            f10[n4] = (f11[n5] = (b2 ? 1 : 0));
            final int[] f15 = this.f7;
            final int n9 = 48;
            final int[] f16 = this.f7;
            final int n10 = 52;
            final int[] f17 = this.f7;
            final int n11 = 56;
            final boolean b3 = true;
            f17[n11] = (b3 ? 1 : 0);
            f15[n9] = (f16[n10] = (b3 ? 1 : 0));
            final int[] f18 = this.f7;
            final int n12 = 62;
            final int[] f19 = this.f7;
            final int n13 = 64;
            final int[] f20 = this.f7;
            final int n14 = 67;
            final int[] f21 = this.f7;
            final int n15 = 70;
            final int[] f22 = this.f7;
            final int n16 = 72;
            final boolean b4 = true;
            f22[n16] = (b4 ? 1 : 0);
            f20[n14] = (f21[n15] = (b4 ? 1 : 0));
            f18[n12] = (f19[n13] = (b4 ? 1 : 0));
            final int[] f23 = this.f7;
            final int n17 = 80;
            final int[] f24 = this.f7;
            final int n18 = 82;
            final int[] f25 = this.f7;
            final int n19 = 84;
            final boolean b5 = true;
            f25[n19] = (b5 ? 1 : 0);
            f23[n17] = (f24[n18] = (b5 ? 1 : 0));
            final int[] f26 = this.f7;
            final int n20 = 91;
            final int[] f27 = this.f7;
            final int n21 = 93;
            final int[] f28 = this.f7;
            final int n22 = 96;
            final int[] f29 = this.f7;
            final int n23 = 97;
            final int[] f30 = this.f7;
            final int n24 = 98;
            final int[] f31 = this.f7;
            final int n25 = 101;
            final int[] f32 = this.f7;
            final int n26 = 103;
            final boolean b6 = true;
            f32[n26] = (b6 ? 1 : 0);
            f30[n24] = (f31[n25] = (b6 ? 1 : 0));
            f28[n22] = (f29[n23] = (b6 ? 1 : 0));
            f26[n20] = (f27[n21] = (b6 ? 1 : 0));
            for (int k = 0; k < this.f1; ++k) {
                this.f7[105 + k] = 1;
            }
            final int[] f33 = this.f7;
            final int n27 = 121;
            final int[] f34 = this.f7;
            final int n28 = 123;
            final int[] f35 = this.f7;
            final int n29 = 126;
            final int[] f36 = this.f7;
            final int n30 = 127;
            final int[] f37 = this.f7;
            final int n31 = 128;
            final int[] f38 = this.f7;
            final int n32 = 131;
            final int[] f39 = this.f7;
            final int n33 = 133;
            final boolean b7 = true;
            f39[n33] = (b7 ? 1 : 0);
            f37[n31] = (f38[n32] = (b7 ? 1 : 0));
            f35[n29] = (f36[n30] = (b7 ? 1 : 0));
            f33[n27] = (f34[n28] = (b7 ? 1 : 0));
            final int[] f40 = this.f7;
            final int n34 = 140;
            final int[] f41 = this.f7;
            final int n35 = 142;
            final int[] f42 = this.f7;
            final int n36 = 144;
            final boolean b8 = true;
            f42[n36] = (b8 ? 1 : 0);
            f40[n34] = (f41[n35] = (b8 ? 1 : 0));
            final int[] f43 = this.f7;
            final int n37 = 152;
            final int[] f44 = this.f7;
            final int n38 = 154;
            final int[] f45 = this.f7;
            final int n39 = 157;
            final int[] f46 = this.f7;
            final int n40 = 160;
            final int[] f47 = this.f7;
            final int n41 = 162;
            final boolean b9 = true;
            f47[n41] = (b9 ? 1 : 0);
            f45[n39] = (f46[n40] = (b9 ? 1 : 0));
            f43[n37] = (f44[n38] = (b9 ? 1 : 0));
            final int[] f48 = this.f7;
            final int n42 = 168;
            final int[] f49 = this.f7;
            final int n43 = 172;
            final int[] f50 = this.f7;
            final int n44 = 176;
            final boolean b10 = true;
            f50[n44] = (b10 ? 1 : 0);
            f48[n42] = (f49[n43] = (b10 ? 1 : 0));
            final int[] f51 = this.f7;
            final int n45 = 182;
            final int[] f52 = this.f7;
            final int n46 = 184;
            final int[] f53 = this.f7;
            final int n47 = 187;
            final int[] f54 = this.f7;
            final int n48 = 190;
            final int[] f55 = this.f7;
            final int n49 = 192;
            final boolean b11 = true;
            f55[n49] = (b11 ? 1 : 0);
            f53[n47] = (f54[n48] = (b11 ? 1 : 0));
            f51[n45] = (f52[n46] = (b11 ? 1 : 0));
            final int[] f56 = this.f7;
            final int n50 = 201;
            final int[] f57 = this.f7;
            final int n51 = 202;
            final int[] f58 = this.f7;
            final int n52 = 203;
            final boolean b12 = true;
            f58[n52] = (b12 ? 1 : 0);
            f56[n50] = (f57[n51] = (b12 ? 1 : 0));
            this.f7[217] = 1;
            final int[] f59 = this.f7;
            final int n53 = 0;
            final int[] f60 = this.f7;
            final int n54 = 1;
            final int[] f61 = this.f7;
            final int n55 = 2;
            final int[] f62 = this.f7;
            final int n56 = 3;
            final int[] f63 = this.f7;
            final int n57 = 4;
            final boolean b13 = false;
            f63[n57] = (b13 ? 1 : 0);
            f61[n55] = (f62[n56] = (b13 ? 1 : 0));
            f59[n53] = (f60[n54] = (b13 ? 1 : 0));
            final int[] f64 = this.f7;
            final int n58 = 15;
            final int[] f65 = this.f7;
            final int n59 = 30;
            final int[] f66 = this.f7;
            final int n60 = 45;
            final int[] f67 = this.f7;
            final int n61 = 60;
            final boolean b14 = false;
            f66[n60] = (f67[n61] = (b14 ? 1 : 0));
            f64[n58] = (f65[n59] = (b14 ? 1 : 0));
            final int[] f68 = this.f7;
            final int n62 = 10;
            final int[] f69 = this.f7;
            final int n63 = 11;
            final int[] f70 = this.f7;
            final int n64 = 12;
            final int[] f71 = this.f7;
            final int n65 = 13;
            final int[] f72 = this.f7;
            final int n66 = 14;
            final boolean b15 = false;
            f72[n66] = (b15 ? 1 : 0);
            f70[n64] = (f71[n65] = (b15 ? 1 : 0));
            f68[n62] = (f69[n63] = (b15 ? 1 : 0));
            final int[] f73 = this.f7;
            final int n67 = 29;
            final int[] f74 = this.f7;
            final int n68 = 44;
            final int[] f75 = this.f7;
            final int n69 = 59;
            final int[] f76 = this.f7;
            final int n70 = 74;
            final boolean b16 = false;
            f75[n69] = (f76[n70] = (b16 ? 1 : 0));
            f73[n67] = (f74[n68] = (b16 ? 1 : 0));
            final int[] f77 = this.f7;
            final int n71 = 150;
            final int[] f78 = this.f7;
            final int n72 = 165;
            final int[] f79 = this.f7;
            final int n73 = 180;
            final int[] f80 = this.f7;
            final int n74 = 195;
            final boolean b17 = false;
            f79[n73] = (f80[n74] = (b17 ? 1 : 0));
            f77[n71] = (f78[n72] = (b17 ? 1 : 0));
            final int[] f81 = this.f7;
            final int n75 = 210;
            final int[] f82 = this.f7;
            final int n76 = 211;
            final int[] f83 = this.f7;
            final int n77 = 212;
            final int[] f84 = this.f7;
            final int n78 = 213;
            final int[] f85 = this.f7;
            final int n79 = 214;
            final boolean b18 = false;
            f85[n79] = (b18 ? 1 : 0);
            f83[n77] = (f84[n78] = (b18 ? 1 : 0));
            f81[n75] = (f82[n76] = (b18 ? 1 : 0));
            final int[] f86 = this.f7;
            final int n80 = 164;
            final int[] f87 = this.f7;
            final int n81 = 179;
            final int[] f88 = this.f7;
            final int n82 = 194;
            final int[] f89 = this.f7;
            final int n83 = 209;
            final boolean b19 = false;
            f88[n82] = (f89[n83] = (b19 ? 1 : 0));
            f86[n80] = (f87[n81] = (b19 ? 1 : 0));
            final int[] f90 = this.f7;
            final int n84 = 220;
            final int[] f91 = this.f7;
            final int n85 = 221;
            final int[] f92 = this.f7;
            final int n86 = 222;
            final int[] f93 = this.f7;
            final int n87 = 223;
            final int[] f94 = this.f7;
            final int n88 = 224;
            final boolean b20 = false;
            f94[n88] = (b20 ? 1 : 0);
            f92[n86] = (f93[n87] = (b20 ? 1 : 0));
            f90[n84] = (f91[n85] = (b20 ? 1 : 0));
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Not enough memory to play Snow effect.");
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
            this.fT = super.cC.eI;
            this.fY = 0;
            if (this.fX == 0) {
                for (int i = 0; i < this.fR; ++i) {
                    this.f0[i] = 0.3;
                    this.fW[i] = Math.random() * 100.0;
                    final double n4 = Math.random() * this.f8;
                    final double n5 = this.fP + Math.random() * (this.fS - this.fP);
                    this.fV[i] = n5 * Math.cos(n4);
                    this.fU[i] = n5 * Math.sin(n4);
                    final double n6 = Math.random() * this.f8;
                    final double n7 = Math.random() * 1.0;
                    this.gb[i] = -0.8 - Math.random() * 0.4;
                    this.ga[i] = n7 * Math.cos(n6);
                    this.f9[i] = n7 * Math.sin(n6);
                    this.fZ[i] = 0.3;
                }
                ++this.fX;
            }
            for (int j = 0; j < this.fR; ++j) {
                this.a(this.fW[j], this.fV[j], this.fU[j], j);
            }
            for (int k = 0; k < this.fY; ++k) {
                if (this.f5[k] < n2 && this.f5[k] >= 0 && this.f6[k] < n3 && this.f6[k] >= 0) {
                    if (this.f3[k] == 0 && this.f5[k] < n2 && this.f5[k] >= 0 && this.f6[k] < n3 && this.f6[k] >= 0) {
                        if (this.f4[k] > 0.0) {
                            final double n8 = this.f4[k];
                            final double n9 = (1.0 - n8) * 255.0;
                            final int n10 = n + this.f5[k] + (this.f6[k] + m) * super.cC.eF.s;
                            super.cC.eF.x[n10] = (0xFF000000 | ((int)(n9 + (int)((super.cC.eF.x[n10] >> 16 & 0xFF) * n8)) & 0xFF) << 16 | ((int)(n9 + (int)((super.cC.eF.x[n10] >> 8 & 0xFF) * n8)) & 0xFF) << 8 | ((int)(n9 + (int)((super.cC.eF.x[n10] & 0xFF) * n8)) & 0xFF));
                        }
                        else {
                            super.cC.eF.x[n + this.f5[k] + (this.f6[k] + m) * super.cC.eF.s] = -1;
                        }
                    }
                    if (this.f3[k] == 1) {
                        for (int n11 = 0; n11 <= this.f3[k]; ++n11) {
                            for (int n12 = 0; n12 <= this.f3[k]; ++n12) {
                                if (this.f5[k] + n11 < n2 && this.f5[k] + n11 >= 0 && this.f6[k] + n12 < n3 && this.f6[k] + n12 >= 0) {
                                    if (this.f4[k] > 0.0) {
                                        final double n13 = this.f4[k];
                                        final double n14 = (1.0 - n13) * 255.0;
                                        final int n15 = n + this.f5[k] + n11 + (this.f6[k] + n12 + m) * super.cC.eF.s;
                                        super.cC.eF.x[n15] = (0xFF000000 | ((int)(n14 + (int)((super.cC.eF.x[n15] >> 16 & 0xFF) * n13)) & 0xFF) << 16 | ((int)(n14 + (int)((super.cC.eF.x[n15] >> 8 & 0xFF) * n13)) & 0xFF) << 8 | ((int)(n14 + (int)((super.cC.eF.x[n15] & 0xFF) * n13)) & 0xFF));
                                    }
                                    else {
                                        super.cC.eF.x[n + this.f5[k] + n11 + (this.f6[k] + n12 + m) * super.cC.eF.s] = -1;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        final double n16 = (this.f1 - 1) / this.f3[k];
                        double n17 = 0.0;
                        double n18 = 0.0;
                        for (int n19 = 0; n19 <= this.f3[k]; ++n19) {
                            for (int n20 = 0; n20 <= this.f3[k]; ++n20) {
                                if (this.f5[k] + n19 < n2 && this.f5[k] + n19 >= 0 && this.f6[k] + n20 < n3 && this.f6[k] + n20 >= 0 && this.f7[(int)(n17 + 0.5) + this.f1 * (int)(n18 + 0.5)] == 1) {
                                    if (this.f4[k] > 0.0) {
                                        final double n21 = this.f4[k];
                                        final double n22 = (1.0 - n21) * 255.0;
                                        final int n23 = n + this.f5[k] + n19 + (this.f6[k] + n20 + m) * super.cC.eF.s;
                                        super.cC.eF.x[n23] = (0xFF000000 | ((int)(n22 + (int)((super.cC.eF.x[n23] >> 16 & 0xFF) * n21)) & 0xFF) << 16 | ((int)(n22 + (int)((super.cC.eF.x[n23] >> 8 & 0xFF) * n21)) & 0xFF) << 8 | ((int)(n22 + (int)((super.cC.eF.x[n23] & 0xFF) * n21)) & 0xFF));
                                    }
                                    else {
                                        super.cC.eF.x[n + this.f5[k] + n19 + (this.f6[k] + n20 + m) * super.cC.eF.s] = -1;
                                    }
                                }
                                n18 += n16;
                            }
                            n17 += n16;
                            n18 = 0.0;
                        }
                    }
                }
            }
            for (int n24 = 0; n24 < this.fR; ++n24) {
                if (this.f0[n24] >= 1.0) {
                    this.fW[n24] = 100.0;
                    final double n25 = Math.random() * this.f8;
                    final double n26 = this.fP + Math.random() * (this.fS - this.fP);
                    this.fV[n24] = n26 * Math.cos(n25);
                    this.fU[n24] = n26 * Math.sin(n25);
                    this.fZ[n24] = 0.3 + Math.random() * 0.2;
                    this.f0[n24] = 0.3;
                }
                else if (this.fW[n24] < -20.0) {
                    final double[] f0 = this.f0;
                    final int n27 = n24;
                    f0[n27] += 0.10000000149011612;
                }
                else {
                    final double[] fw = this.fW;
                    final int n28 = n24;
                    fw[n28] += this.gb[n24] * this.fZ[n24];
                    final double[] fv = this.fV;
                    final int n29 = n24;
                    fv[n29] += this.ga[n24] * this.fZ[n24];
                    final double[] fu = this.fU;
                    final int n30 = n24;
                    fu[n30] += this.f9[n24] * this.fZ[n24];
                }
            }
            this.fY = 0;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean a(final double n, final double n2, final double n3, final int n4) {
        final double n5 = super.cC.eJ * n2;
        final double n6 = super.cC.eV * n3;
        final double n7 = -super.cC.eZ * n - super.cC.eT * n5 + super.cC.eT * n6;
        if (n7 > 0.0) {
            return false;
        }
        final double n8 = super.cC.eT * n - super.cC.eZ * n5 + super.cC.eZ * n6;
        final double n9 = super.cC.eJ * n3 + super.cC.eV * n2;
        final double n10 = this.fT / n7;
        this.f5[this.fY] = (int)(n9 * n10 + super.cC.eW);
        this.f6[this.fY] = (int)(n8 * n10 + super.cC.eG);
        this.f4[this.fY] = this.f0[n4];
        this.f3[this.fY] = Math.min(6, -(int)(this.fT / (3.0 * n7)));
        ++this.fY;
        return true;
    }
}
