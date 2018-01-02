// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class at extends e
{
    aq ds;
    aq dv;
    float dO;
    int di;
    ae dz;
    boolean dC;
    int dM;
    int[] dg;
    float[] dJ;
    int[] dl;
    int[] dp;
    ad dk;
    ae dm;
    int[] dB;
    boolean dG;
    int dr;
    int dj;
    int dS;
    int dN;
    int dL;
    int dK;
    int dE;
    int dq;
    int[] dx;
    int[] dD;
    int[] dF;
    int du;
    int dI;
    int dt;
    int dH;
    private static char[] dh;
    private static char[] dw;
    private static char[] dy;
    private char[] dR;
    int dA;
    int dQ;
    int dn;
    int dP;
    
    static {
        at.dh = new char[] { 'd', 'a', 'z', 'z', 'l', 'e', 'c', 'o', 'l', 'o', 'r', '\0' };
        at.dw = new char[] { 'f', 'l', 'a', 'r', 'e', 'c', 'o', 'l', 'o', 'r', '\0' };
        at.dy = new char[] { 's', 'i', 'z', 'e', '\0' };
    }
    
    public at() {
        this.dO = 0.0f;
        this.di = 0;
        this.dz = null;
        this.dC = false;
        this.dM = 0;
        this.dk = null;
        this.dm = null;
        this.dB = null;
        this.dG = false;
        this.dr = 0;
        this.dR = new char[] { 'l', 'i', 'g', 'h', 't', '\0' };
    }
    
    public void a(final ac b, final float cz, final ae long1, final f cb, final m m) {
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / super.cz;
        super.long = long1;
        super.cB = cb;
        super.int = 3;
        super.case = this.dR;
        super.new = new a3();
        this.dx = new int[512];
        this.dD = new int[512];
        this.dF = new int[512];
        for (int i = 256; i < 512; ++i) {
            this.dx[i] = -65536;
            this.dD[i] = 65280;
            this.dF[i] = 255;
        }
    }
    
    public void int(final a2 a2) {
        this.ds = new aq();
        this.dv = new aq();
        float n = 0.0f;
        float n2 = 0.0f;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("dazzlecolor") == 0) {
                this.dG = true;
                final int a3 = ac.a(a2.new[i]);
                this.dL = (a3 >> 16 & 0xFF);
                this.dK = (a3 >> 8 & 0xFF);
                this.dE = (a3 & 0xFF);
            }
            else if (a2.try[i].toLowerCase().compareTo("flarecolor") == 0) {
                this.dr |= 0x1;
                final int a4 = ac.a(a2.new[i]);
                this.dj = (a4 >> 16 & 0xFF);
                this.dS = (a4 >> 8 & 0xFF);
                this.dN = (a4 & 0xFF);
            }
            else if (a2.try[i].toLowerCase().compareTo("size") == 0) {
                this.dO = new Float(a2.new[i]);
                this.dr |= 0x2;
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                this.dz = super.b.A.a(a2.new[i], super.long, false, false, false);
                this.dr |= 0x4;
            }
        }
        this.a();
        this.ds.byte = (float)Math.sin(n2);
        this.ds.try = (float)(Math.cos(n) * Math.cos(n2));
        this.ds.if = (float)(Math.sin(n) * Math.cos(n2));
        this.dv.byte = this.ds.byte;
        this.dv.try = this.ds.try;
        this.dv.if = this.ds.if;
    }
    
    void a(final float[][] array) {
        super.b.a(array, this.dv, this.ds);
    }
    
    public boolean a(final long n) {
        this.n();
        if (this.dC) {
            boolean a = false;
            if (this.dm != null && this.dk != null && this.dm.case && !this.dk.byte) {
                this.dm.a(this.dk);
                super.byte = this.dk.byte;
                if (super.byte) {
                    this.dB = new int[256];
                    super.do = true;
                }
            }
            if (super.byte && this.dk != null && this.dk.byte) {
                a = this.dk.a(n);
            }
            final boolean do1 = super.do;
            super.do = false;
            return (a & super.for) || do1;
        }
        if (this.dr == 7 && this.dz != null && this.dz.b) {
            a2 do2 = null;
            if (this.dz.k != 0) {
                this.dz = this.dz.try[0];
            }
            final bf bf = new bf();
            try {
                y.a(bf, this.dz.l, this.dz.do);
                do2 = bf.do;
            }
            catch (Exception ex) {}
            if (do2 == null) {
                this.dC = true;
                return false;
            }
            for (int i = 0; i < do2.do; ++i) {
                if (do2.try[i].toLowerCase().compareTo("xmlns") == 0) {
                    if (do2.new[i].toLowerCase().compareTo("http://www.immervision.com/lensflare") != 0) {
                        return false;
                    }
                }
                else if (do2.try[i].toLowerCase().compareTo("image") == 0) {
                    this.dk = new ad();
                    this.dm = super.b.A.a(do2.new[i], this.dz, false, true, false);
                }
            }
            for (a2 a2 = do2.if; a2 != null; a2 = a2.for) {
                if (a2.a.toLowerCase().compareTo("flare") == 0) {
                    this.o();
                    for (int j = 0; j < a2.do; ++j) {
                        if (a2.try[j].toLowerCase().compareTo("map") == 0) {
                            this.dp[this.dM] = new Integer(a2.new[j]);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("color") == 0) {
                            this.dg[this.dM] = ac.a(a2.new[j]);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("position") == 0) {
                            this.dl[this.dM] = (int)(new Float(a2.new[j]) * 655.35);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("size") == 0) {
                            this.dJ[this.dM] = new Float(a2.new[j]);
                        }
                    }
                    ++this.dM;
                }
            }
            this.dC = true;
        }
        else if (this.dr != 7) {
            this.dC = true;
            super.byte = true;
        }
        return false;
    }
    
    public void new(final boolean b) {
        if (!super.byte || !super.for) {
            return;
        }
        final int[] q = super.cB.eE.q;
        this.dq = super.cB.eE.j;
        if (super.cB.eL < super.cB.eQ) {
            this.dA = super.cB.eL >> 1;
            this.dQ = super.cB.eQ >> 1;
            this.dn = (int)(super.cB.eQ * 0.1);
            this.dP = this.dQ + this.dn;
        }
        else {
            this.dA = super.cB.eQ >> 1;
            this.dQ = super.cB.eL >> 1;
            this.dn = (int)(super.cB.eQ * 0.1);
            this.dP = this.dQ + this.dn;
        }
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        if (this.dG || this.dr == 7) {
            final float n4 = super.cB.eU * this.ds.try + super.cB.eI * this.ds.if;
            final float n5 = super.cB.eS * n4 + super.cB.eY * this.ds.byte;
            if (n5 < 0.01f) {
                return;
            }
            final float n6 = super.cB.eH / n5;
            final float n7 = (-super.cB.eU * this.ds.if + super.cB.eI * this.ds.try) * n6;
            final float n8 = -(super.cB.eS * this.ds.byte - super.cB.eY * n4) * n6;
            this.du = ((super.cB.eE.i + super.cB.eE.t > super.cB.eE.j) ? super.cB.eE.j : (super.cB.eE.i + super.cB.eE.t));
            this.dI = ((super.cB.eE.i > 0) ? super.cB.eE.i : 0);
            this.dt = ((super.cB.eE.h + super.cB.eE.k > super.cB.eE.u) ? super.cB.eE.u : (super.cB.eE.h + super.cB.eE.k));
            this.dH = ((super.cB.eE.h > 0) ? super.cB.eE.h : 0);
            final int n9 = (int)Math.sqrt(n7 * n7 + n8 * n8);
            if (this.dr == 7 && n9 < this.dQ) {
                for (int i = 0; i < this.dM; ++i) {
                    final int n10 = (int)((super.cB.eL >> 1) + n7 - ((int)(n7 * this.dl[i]) >> 16));
                    final int n11 = (int)((super.cB.eQ >> 1) + n8 - ((int)(n8 * this.dl[i]) >> 16));
                    final int n12 = (int)(65535.0f * (this.dk.j * 10000 / (this.dO * this.dJ[i] * (super.cB.eL + super.cB.eQ) / 2.0f)));
                    final int n13 = this.dp[i] * this.dk.j * this.dk.j;
                    for (int j = 0; j < 256; ++j) {
                        this.dB[j] = ((this.dN * j * (this.dg[i] >> 16) & 0xFF0000) | (this.dS * j * ((this.dg[i] & 0xFF00) >> 8) >> 8 & 0xFF00) | (this.dj * j * (this.dg[i] & 0xFF) >> 16 & 0xFF));
                    }
                    this.a(n10, n11, n12, n13, q);
                }
            }
            else if (this.dr == 7 && n9 < this.dP) {
                final float n14 = (this.dP - n9) / this.dn;
                for (int k = 0; k < this.dM; ++k) {
                    final int n15 = (int)((super.cB.eL >> 1) + n7 - ((int)(n7 * this.dl[k]) >> 16));
                    final int n16 = (int)((super.cB.eQ >> 1) + n8 - ((int)(n8 * this.dl[k]) >> 16));
                    final int n17 = (int)(65535.0f * (this.dk.j * 10000 / (this.dO * this.dJ[k] * (super.cB.eL + super.cB.eQ) / 2.0f)));
                    final int n18 = this.dp[k] * this.dk.j * this.dk.j;
                    for (int l = 0; l < 256; ++l) {
                        this.dB[l] = (((int)(this.dN * l * (this.dg[k] >> 16) * n14) & 0xFF0000) | ((int)(this.dS * l * ((this.dg[k] & 0xFF00) >> 8) * n14) >> 8 & 0xFF00) | ((int)(this.dj * l * (this.dg[k] & 0xFF) * n14) >> 16 & 0xFF));
                    }
                    this.a(n15, n16, n17, n18, q);
                }
            }
            if (this.dG && n9 < this.dA && Math.abs(n7) < this.dA && Math.abs(n8) < this.dA) {
                int n19 = n + (this.dL * (this.dA - n9) / this.dA & 0xFF);
                if (n19 > 255) {
                    n19 = 255;
                }
                int n20 = n2 + (this.dK * (this.dA - n9) / this.dA & 0xFF);
                if (n20 > 255) {
                    n20 = 255;
                }
                int n21 = n3 + (this.dE * (this.dA - n9) / this.dA & 0xFF);
                if (n21 > 255) {
                    n21 = 255;
                }
                this.a(n21 << 16, n20 << 8, n19, q);
            }
        }
    }
    
    private void a(int n, int n2, final int n3, final int n4, final int[] array) {
        n += super.cB.eE.i;
        n2 += super.cB.eE.h;
        final int n5 = this.dk.j >> 1;
        final int n6 = (n5 << 16) / n3;
        final int n7;
        if ((n7 = ((n - n6 < this.dI) ? this.dI : (n - n6))) > super.cB.eL) {
            return;
        }
        final int n8;
        if ((n8 = ((n2 - n6 < this.dH) ? this.dH : (n2 - n6))) > super.cB.eQ) {
            return;
        }
        final int n9;
        if ((n9 = ((n + n6 > this.du) ? this.du : (n + n6))) < this.dI) {
            return;
        }
        final int n10;
        if ((n10 = ((n2 + n6 > this.dt) ? this.dt : (n2 + n6))) < this.dH) {
            return;
        }
        int n11 = n8 * this.dq;
        int n12 = (n8 - n2) * n3 + (n5 << 16);
        final int n13 = (n7 - n) * n3 + (n5 << 16);
        for (int i = n8; i < n10; ++i) {
            int n14 = n13;
            for (int j = n7; j < n9; ++j) {
                final int n15 = array[j + n11];
                final int n16 = this.dB[this.dk.q[n4 + (n14 >> 16) + (n12 >> 16) * this.dk.j] & 0xFF];
                final int n17;
                final int n18;
                final int n19;
                array[j + n11] = ((((n17 = (n15 & 0xFF) + (n16 & 0xFF)) > 255) ? 255 : n17) | (((n18 = (n15 & 0xFF00) + (n16 & 0xFF00)) > 65280) ? 65280 : n18) | (((n19 = (n15 & 0xFF0000) + (n16 & 0xFF0000)) > 16711680) ? 16711680 : n19) | 0xFF000000);
                n14 += n3;
            }
            n11 += this.dq;
            n12 += n3;
        }
    }
    
    void a(final int n, final int n2, final int n3, final int[] array) {
        int n4 = this.dH * this.dq + this.dI;
        for (int i = this.dH; i < this.dt; ++i) {
            int n5 = n4;
            for (int j = this.dI; j < this.du; ++j) {
                final int n6 = array[n5];
                final int n7;
                final int n8;
                final int n9;
                array[n5] = ((((n7 = (n6 & 0xFF) + n3) > 255) ? 255 : n7) | (((n8 = (n6 & 0xFF00) + n2) > 65280) ? 65280 : n8) | (((n9 = (n6 & 0xFF0000) + n) > 16711680) ? 16711680 : n9) | 0xFF000000);
                ++n5;
            }
            n4 += this.dq;
        }
    }
    
    private void o() {
        if (this.dp == null || this.dp.length == this.dM) {
            final int[] dg = new int[this.dM + 10];
            final float[] dj = new float[this.dM + 10];
            final int[] dl = new int[this.dM + 10];
            final int[] dp = new int[this.dM + 10];
            for (int i = 0; i < this.dM; ++i) {
                dg[i] = this.dg[i];
                dj[i] = this.dJ[i];
                dl[i] = this.dl[i];
                dp[i] = this.dp[i];
            }
            this.dg = dg;
            this.dJ = dj;
            this.dl = dl;
            this.dp = dp;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.P) == 0) {
            super.for = al.a(a3);
            super.do = true;
        }
        else if (g.do(array, at.dh) == 0) {
            if (a3.char == 2) {
                if ((a3.case & 0xFFFFFFL) == 0x0L) {
                    this.dG = false;
                }
                else {
                    this.dG = true;
                    this.dL = ((int)(a3.case >> 16) & 0xFF);
                    this.dK = ((int)(a3.case >> 8) & 0xFF);
                    this.dE = ((int)a3.case & 0xFF);
                }
            }
        }
        else if (g.do(array, at.dw) == 0) {
            if (a3.char == 2) {
                if ((a3.case & 0xFFFFFFL) == 0x0L) {
                    this.dr = 0;
                }
                else {
                    this.dr = 7;
                    this.dj = ((int)(a3.case >> 16) & 0xFF);
                    this.dS = ((int)(a3.case >> 8) & 0xFF);
                    this.dN = ((int)a3.case & 0xFF);
                }
            }
        }
        else if (g.do(array, ac.L) == 0) {
            if (a3.char == 2 || a3.char == 3) {
                super.cD = (float)((a3.char == 2) ? a3.case : a3.else);
            }
        }
        else if (g.do(array, ac.byte) == 0 && (a3.char == 2 || a3.char == 3)) {
            super.cw = (float)((a3.char == 2) ? a3.case : a3.else);
        }
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.P) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (g.do(array, at.dh) == 0) {
            super.new.char = 2;
            super.new.case = (this.dG ? (0xFF000000 | this.dL << 16 | this.dK << 8 | this.dE) : 0);
            return super.new;
        }
        if (g.do(array, at.dw) == 0) {
            super.new.char = 2;
            super.new.case = ((this.dr == 7) ? (0xFF000000 | this.dj << 16 | this.dS << 8 | this.dN) : 0);
            return super.new;
        }
        if (g.do(array, at.dy) == 0) {
            super.new.char = 3;
            super.new.else = this.dO;
            return super.new;
        }
        return ac.a(super.new);
    }
    
    public void if() {
        if (this.dk != null) {
            this.dk.if();
        }
    }
}
