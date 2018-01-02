// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class at extends e
{
    aq dt;
    aq dw;
    float dP;
    int dj;
    ae dA;
    boolean dD;
    int dN;
    int[] dh;
    float[] dK;
    int[] dm;
    int[] dq;
    ad dl;
    ae dn;
    int[] dC;
    boolean dH;
    int ds;
    int dk;
    int dT;
    int dO;
    int dM;
    int dL;
    int dF;
    int dr;
    int[] dy;
    int[] dE;
    int[] dG;
    int dv;
    int dJ;
    int du;
    int dI;
    private static char[] di;
    private static char[] dx;
    private static char[] dz;
    private char[] dS;
    int dB;
    int dR;
    int dp;
    int dQ;
    
    static {
        at.di = new char[] { 'd', 'a', 'z', 'z', 'l', 'e', 'c', 'o', 'l', 'o', 'r', '\0' };
        at.dx = new char[] { 'f', 'l', 'a', 'r', 'e', 'c', 'o', 'l', 'o', 'r', '\0' };
        at.dz = new char[] { 's', 'i', 'z', 'e', '\0' };
    }
    
    public at() {
        this.dP = 0.0f;
        this.dj = 0;
        this.dA = null;
        this.dD = false;
        this.dN = 0;
        this.dl = null;
        this.dn = null;
        this.dC = null;
        this.dH = false;
        this.ds = 0;
        this.dS = new char[] { 'l', 'i', 'g', 'h', 't', '\0' };
    }
    
    public void a(final ac b, final float ca, final ae long1, final f cc, final m m) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.int = 3;
        super.case = this.dS;
        super.new = new a3();
        this.dy = new int[512];
        this.dE = new int[512];
        this.dG = new int[512];
        for (int i = 256; i < 512; ++i) {
            this.dy[i] = -65536;
            this.dE[i] = 65280;
            this.dG[i] = 255;
        }
    }
    
    public void int(final a2 a2) {
        this.dt = new aq();
        this.dw = new aq();
        float n = 0.0f;
        float n2 = 0.0f;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("dazzlecolor") == 0) {
                this.dH = true;
                final int a3 = ac.a(a2.new[i]);
                this.dM = (a3 >> 16 & 0xFF);
                this.dL = (a3 >> 8 & 0xFF);
                this.dF = (a3 & 0xFF);
            }
            else if (a2.try[i].toLowerCase().compareTo("flarecolor") == 0) {
                this.ds |= 0x1;
                final int a4 = ac.a(a2.new[i]);
                this.dk = (a4 >> 16 & 0xFF);
                this.dT = (a4 >> 8 & 0xFF);
                this.dO = (a4 & 0xFF);
            }
            else if (a2.try[i].toLowerCase().compareTo("size") == 0) {
                this.dP = new Float(a2.new[i]);
                this.ds |= 0x2;
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                this.dA = super.b.B.a(a2.new[i], super.long, false, false, false);
                this.ds |= 0x4;
            }
        }
        this.a();
        this.dt.byte = (float)Math.sin(n2);
        this.dt.try = (float)(Math.cos(n) * Math.cos(n2));
        this.dt.if = (float)(Math.sin(n) * Math.cos(n2));
        this.dw.byte = this.dt.byte;
        this.dw.try = this.dt.try;
        this.dw.if = this.dt.if;
    }
    
    void a(final float[][] array) {
        super.b.a(array, this.dw, this.dt);
    }
    
    public boolean a(final long n) {
        this.l();
        if (this.dD) {
            boolean a = false;
            if (this.dn != null && this.dl != null && this.dn.case && !this.dl.byte) {
                this.dn.a(this.dl);
                super.byte = this.dl.byte;
                if (super.byte) {
                    this.dC = new int[256];
                    super.do = true;
                }
            }
            if (super.byte && this.dl != null && this.dl.byte) {
                a = this.dl.a(n);
            }
            final boolean do1 = super.do;
            super.do = false;
            return (a & super.for) || do1;
        }
        if (this.ds == 7 && this.dA != null && this.dA.b) {
            a2 do2 = null;
            if (this.dA.k != 0) {
                this.dA = this.dA.try[0];
            }
            final bf bf = new bf();
            try {
                y.a(bf, this.dA.l, this.dA.do);
                do2 = bf.do;
            }
            catch (Exception ex) {}
            if (do2 == null) {
                this.dD = true;
                return false;
            }
            for (int i = 0; i < do2.do; ++i) {
                if (do2.try[i].toLowerCase().compareTo("xmlns") == 0) {
                    if (do2.new[i].toLowerCase().compareTo("http://www.immervision.com/lensflare") != 0) {
                        return false;
                    }
                }
                else if (do2.try[i].toLowerCase().compareTo("image") == 0) {
                    this.dl = new ad();
                    this.dn = super.b.B.a(do2.new[i], this.dA, false, true, false);
                }
            }
            for (a2 a2 = do2.if; a2 != null; a2 = a2.for) {
                if (a2.a.toLowerCase().compareTo("flare") == 0) {
                    this.m();
                    for (int j = 0; j < a2.do; ++j) {
                        if (a2.try[j].toLowerCase().compareTo("map") == 0) {
                            this.dq[this.dN] = new Integer(a2.new[j]);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("color") == 0) {
                            this.dh[this.dN] = ac.a(a2.new[j]);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("position") == 0) {
                            this.dm[this.dN] = (int)(new Float(a2.new[j]) * 655.35);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("size") == 0) {
                            this.dK[this.dN] = new Float(a2.new[j]);
                        }
                    }
                    ++this.dN;
                }
            }
            this.dD = true;
        }
        else if (this.ds != 7) {
            this.dD = true;
            super.byte = true;
        }
        return false;
    }
    
    public void new(final boolean b) {
        if (!super.byte || !super.for) {
            return;
        }
        final int[] x = super.cC.eF.x;
        this.dr = super.cC.eF.s;
        if (super.cC.eM < super.cC.eR) {
            this.dB = super.cC.eM >> 1;
            this.dR = super.cC.eR >> 1;
            this.dp = (int)(super.cC.eR * 0.1);
            this.dQ = this.dR + this.dp;
        }
        else {
            this.dB = super.cC.eR >> 1;
            this.dR = super.cC.eM >> 1;
            this.dp = (int)(super.cC.eR * 0.1);
            this.dQ = this.dR + this.dp;
        }
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        if (this.dH || this.ds == 7) {
            final float n4 = super.cC.eV * this.dt.try + super.cC.eJ * this.dt.if;
            final float n5 = super.cC.eT * n4 + super.cC.eZ * this.dt.byte;
            if (n5 < 0.01f) {
                return;
            }
            final float n6 = super.cC.eI / n5;
            final float n7 = (-super.cC.eV * this.dt.if + super.cC.eJ * this.dt.try) * n6;
            final float n8 = -(super.cC.eT * this.dt.byte - super.cC.eZ * n4) * n6;
            this.dv = ((super.cC.eF.n + super.cC.eF.l > super.cC.eF.s) ? super.cC.eF.s : (super.cC.eF.n + super.cC.eF.l));
            this.dJ = ((super.cC.eF.n > 0) ? super.cC.eF.n : 0);
            this.du = ((super.cC.eF.m + super.cC.eF.p > super.cC.eF.r) ? super.cC.eF.r : (super.cC.eF.m + super.cC.eF.p));
            this.dI = ((super.cC.eF.m > 0) ? super.cC.eF.m : 0);
            final int n9 = (int)Math.sqrt(n7 * n7 + n8 * n8);
            if (this.ds == 7 && n9 < this.dR) {
                for (int i = 0; i < this.dN; ++i) {
                    final int n10 = (int)((super.cC.eM >> 1) + n7 - ((int)(n7 * this.dm[i]) >> 16));
                    final int n11 = (int)((super.cC.eR >> 1) + n8 - ((int)(n8 * this.dm[i]) >> 16));
                    final int n12 = (int)(65535.0f * (this.dl.s * 10000 / (this.dP * this.dK[i] * (super.cC.eM + super.cC.eR) / 2.0f)));
                    final int n13 = this.dq[i] * this.dl.s * this.dl.s;
                    for (int j = 0; j < 256; ++j) {
                        this.dC[j] = ((this.dO * j * (this.dh[i] >> 16) & 0xFF0000) | (this.dT * j * ((this.dh[i] & 0xFF00) >> 8) >> 8 & 0xFF00) | (this.dk * j * (this.dh[i] & 0xFF) >> 16 & 0xFF));
                    }
                    this.a(n10, n11, n12, n13, x);
                }
            }
            else if (this.ds == 7 && n9 < this.dQ) {
                final float n14 = (this.dQ - n9) / this.dp;
                for (int k = 0; k < this.dN; ++k) {
                    final int n15 = (int)((super.cC.eM >> 1) + n7 - ((int)(n7 * this.dm[k]) >> 16));
                    final int n16 = (int)((super.cC.eR >> 1) + n8 - ((int)(n8 * this.dm[k]) >> 16));
                    final int n17 = (int)(65535.0f * (this.dl.s * 10000 / (this.dP * this.dK[k] * (super.cC.eM + super.cC.eR) / 2.0f)));
                    final int n18 = this.dq[k] * this.dl.s * this.dl.s;
                    for (int l = 0; l < 256; ++l) {
                        this.dC[l] = (((int)(this.dO * l * (this.dh[k] >> 16) * n14) & 0xFF0000) | ((int)(this.dT * l * ((this.dh[k] & 0xFF00) >> 8) * n14) >> 8 & 0xFF00) | ((int)(this.dk * l * (this.dh[k] & 0xFF) * n14) >> 16 & 0xFF));
                    }
                    this.a(n15, n16, n17, n18, x);
                }
            }
            if (this.dH && n9 < this.dB && Math.abs(n7) < this.dB && Math.abs(n8) < this.dB) {
                int n19 = n + (this.dM * (this.dB - n9) / this.dB & 0xFF);
                if (n19 > 255) {
                    n19 = 255;
                }
                int n20 = n2 + (this.dL * (this.dB - n9) / this.dB & 0xFF);
                if (n20 > 255) {
                    n20 = 255;
                }
                int n21 = n3 + (this.dF * (this.dB - n9) / this.dB & 0xFF);
                if (n21 > 255) {
                    n21 = 255;
                }
                this.a(n21 << 16, n20 << 8, n19, x);
            }
        }
    }
    
    private void a(int n, int n2, final int n3, final int n4, final int[] array) {
        n += super.cC.eF.n;
        n2 += super.cC.eF.m;
        final int n5 = this.dl.s >> 1;
        final int n6 = (n5 << 16) / n3;
        final int n7;
        if ((n7 = ((n - n6 < this.dJ) ? this.dJ : (n - n6))) > super.cC.eM) {
            return;
        }
        final int n8;
        if ((n8 = ((n2 - n6 < this.dI) ? this.dI : (n2 - n6))) > super.cC.eR) {
            return;
        }
        final int n9;
        if ((n9 = ((n + n6 > this.dv) ? this.dv : (n + n6))) < this.dJ) {
            return;
        }
        final int n10;
        if ((n10 = ((n2 + n6 > this.du) ? this.du : (n2 + n6))) < this.dI) {
            return;
        }
        int n11 = n8 * this.dr;
        int n12 = (n8 - n2) * n3 + (n5 << 16);
        final int n13 = (n7 - n) * n3 + (n5 << 16);
        for (int i = n8; i < n10; ++i) {
            int n14 = n13;
            for (int j = n7; j < n9; ++j) {
                final int n15 = array[j + n11];
                final int n16 = this.dC[this.dl.x[n4 + (n14 >> 16) + (n12 >> 16) * this.dl.s] & 0xFF];
                final int n17;
                final int n18;
                final int n19;
                array[j + n11] = ((((n17 = (n15 & 0xFF) + (n16 & 0xFF)) > 255) ? 255 : n17) | (((n18 = (n15 & 0xFF00) + (n16 & 0xFF00)) > 65280) ? 65280 : n18) | (((n19 = (n15 & 0xFF0000) + (n16 & 0xFF0000)) > 16711680) ? 16711680 : n19) | 0xFF000000);
                n14 += n3;
            }
            n11 += this.dr;
            n12 += n3;
        }
    }
    
    void a(final int n, final int n2, final int n3, final int[] array) {
        int n4 = this.dI * this.dr + this.dJ;
        for (int i = this.dI; i < this.du; ++i) {
            int n5 = n4;
            for (int j = this.dJ; j < this.dv; ++j) {
                final int n6 = array[n5];
                final int n7;
                final int n8;
                final int n9;
                array[n5] = ((((n7 = (n6 & 0xFF) + n3) > 255) ? 255 : n7) | (((n8 = (n6 & 0xFF00) + n2) > 65280) ? 65280 : n8) | (((n9 = (n6 & 0xFF0000) + n) > 16711680) ? 16711680 : n9) | 0xFF000000);
                ++n5;
            }
            n4 += this.dr;
        }
    }
    
    private void m() {
        if (this.dq == null || this.dq.length == this.dN) {
            final int[] dh = new int[this.dN + 10];
            final float[] dk = new float[this.dN + 10];
            final int[] dm = new int[this.dN + 10];
            final int[] dq = new int[this.dN + 10];
            for (int i = 0; i < this.dN; ++i) {
                dh[i] = this.dh[i];
                dk[i] = this.dK[i];
                dm[i] = this.dm[i];
                dq[i] = this.dq[i];
            }
            this.dh = dh;
            this.dK = dk;
            this.dm = dm;
            this.dq = dq;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.Q) == 0) {
            super.for = al.a(a3);
            super.do = true;
        }
        else if (g.do(array, at.di) == 0) {
            if (a3.char == 2) {
                if ((a3.case & 0xFFFFFFL) == 0x0L) {
                    this.dH = false;
                }
                else {
                    this.dH = true;
                    this.dM = ((int)(a3.case >> 16) & 0xFF);
                    this.dL = ((int)(a3.case >> 8) & 0xFF);
                    this.dF = ((int)a3.case & 0xFF);
                }
                super.do = true;
            }
        }
        else if (g.do(array, at.dx) == 0) {
            if (a3.char == 2) {
                if ((a3.case & 0xFFFFFFL) == 0x0L) {
                    this.ds = 0;
                }
                else {
                    this.ds = 7;
                    this.dk = ((int)(a3.case >> 16) & 0xFF);
                    this.dT = ((int)(a3.case >> 8) & 0xFF);
                    this.dO = ((int)a3.case & 0xFF);
                }
                super.do = true;
            }
        }
        else if (g.do(array, ac.M) == 0) {
            if (a3.char == 2 || a3.char == 3) {
                super.cE = (float)((a3.char == 2) ? a3.case : a3.else);
            }
        }
        else if (g.do(array, ac.byte) == 0) {
            if (a3.char == 2 || a3.char == 3) {
                super.cx = (float)((a3.char == 2) ? a3.case : a3.else);
            }
        }
        else if (g.do(array, at.dz) == 0 && (a3.char == 2 || a3.char == 3)) {
            this.dP = (float)((a3.char == 2) ? a3.case : a3.else);
            super.do = true;
        }
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.Q) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (g.do(array, at.di) == 0) {
            super.new.char = 2;
            super.new.case = (this.dH ? (0xFF000000 | this.dM << 16 | this.dL << 8 | this.dF) : 0);
            return super.new;
        }
        if (g.do(array, at.dx) == 0) {
            super.new.char = 2;
            super.new.case = ((this.ds == 7) ? (0xFF000000 | this.dk << 16 | this.dT << 8 | this.dO) : 0);
            return super.new;
        }
        if (g.do(array, at.dz) == 0) {
            super.new.char = 3;
            super.new.else = this.dP;
            return super.new;
        }
        if (g.do(array, ac.M) == 0) {
            super.new.char = 3;
            super.new.else = super.cE;
            return super.new;
        }
        if (g.do(array, ac.byte) == 0) {
            super.new.char = 3;
            super.new.else = super.cx;
            return super.new;
        }
        return ac.a(super.new);
    }
    
    public void if() {
        if (this.dl != null) {
            this.dl.if();
        }
    }
}
