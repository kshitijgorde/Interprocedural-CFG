// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ao extends e
{
    protected a0 dE;
    boolean d1;
    protected float ed;
    protected float d3;
    protected float dR;
    protected float dM;
    protected float dF;
    protected float dU;
    boolean eg;
    boolean ef;
    boolean dS;
    int d0;
    int dY;
    int d7;
    char[] dD;
    int ee;
    int dZ;
    int d6;
    e[] ea;
    e[] dK;
    e[] dV;
    float[][] d4;
    a5[] dI;
    int dP;
    static final char[] dG;
    boolean dN;
    aq dH;
    aq dB;
    float dT;
    float dJ;
    float d5;
    static final char[] eh;
    static final char[] d9;
    static final char[] d8;
    static final char[] dX;
    static final char[] dO;
    static final char[] ei;
    static final char[] d2;
    static final char[] ec;
    static final char[] dL;
    int eb;
    boolean dC;
    aq[][] dQ;
    aq[][] dW;
    
    static {
        dG = new char[1];
        eh = new char[] { 'm', 'a', 'x', 't', 'i', 'l', 't', '\0' };
        d9 = new char[] { 'm', 'i', 'n', 't', 'i', 'l', 't', '\0' };
        d8 = new char[] { 'm', 'a', 'x', 'p', 'a', 'n', '\0' };
        dX = new char[] { 'm', 'i', 'n', 'p', 'a', 'n', '\0' };
        dO = new char[] { 'm', 'a', 'x', 'f', 'o', 'v', '\0' };
        ei = new char[] { 'm', 'i', 'n', 'f', 'o', 'v', '\0' };
        d2 = new char[] { 'r', 'm', 't', 'i', 'l', 't', '\0' };
        ec = new char[] { 'r', 'm', 'p', 'a', 'n', '\0' };
        dL = new char[] { 'l', 'o', 'c', 'k', 'z', 'e', 'n', 'i', 't', 'h', 'n', 'a', 'd', 'i', 'r' };
    }
    
    public void a(final ac ac, final float n, final ae ae, final f f, final m m) {
    }
    
    ao() {
        this.dE = null;
        this.d1 = true;
        this.ed = 0.0f;
        this.d3 = 0.0f;
        this.dR = 0.0f;
        this.dM = 0.0f;
        this.dF = 0.01745f;
        this.dU = 2.5f;
        this.eg = true;
        this.ef = true;
        this.dS = false;
        this.d7 = -1;
        this.dD = new char[1];
        this.ee = 0;
        this.dZ = 0;
        this.d6 = 0;
        this.ea = null;
        this.dK = null;
        this.dV = null;
        this.dI = null;
        this.dP = 0;
        this.dN = false;
        this.dH = null;
        this.dB = null;
        this.dT = 0.0f;
        this.dJ = 0.0f;
        this.d5 = 0.0f;
        this.eb = 1;
        this.dC = false;
        this.dH = new aq();
        this.dB = new aq();
        super.int = new a3();
    }
    
    public void int(final a2 a2) {
        this.d4 = super.void.a();
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.c = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                super.cn = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                super.cg = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("roll") == 0) {
                super.ci = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.do = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("maxpan") == 0) {
                this.ed = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("minpan") == 0) {
                this.d3 = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("maxtilt") == 0) {
                this.dR = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("mintilt") == 0) {
                this.dM = new Float(a2.new[i]) * super.cj;
            }
        }
        this.a();
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("image") == 0) {
                (this.dE = new a0(super.void, super.goto, super.ck.G, super.else)).a(a3);
                super.ck.a(this.dE);
            }
            else if (a3.a.toLowerCase().compareTo("autopath") == 0) {
                for (int j = 0; j < a3.do; ++j) {
                    if (a3.try[j].toLowerCase().compareTo("play") == 0) {
                        if (a3.new[j].compareTo("false") == 0) {
                            this.d1 = false;
                        }
                    }
                    else if (a3.try[j].toLowerCase().compareTo("first") == 0) {
                        this.dD = (String.valueOf(a3.new[j]) + "\u0000").toCharArray();
                    }
                }
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for) {
                    this.byte(a4);
                }
            }
        }
        for (a2 a5 = a2.if; a5 != null; a5 = a5.for) {
            if (a5.a.toLowerCase().compareTo("image") != 0 && a5.a.toLowerCase().compareTo("autopath") != 0) {
                this.a(a5, super.else);
            }
        }
    }
    
    private void a(final a2 a2, final boolean else1) {
        try {
            if (a2.a.toLowerCase().compareTo("hotspotpoint") == 0) {
                this.s();
                (this.ea[this.ee] = (e)Class.forName("a.a.a.a.bh").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.void, super.cj, super.goto, super.cl, super.ck);
                super.ck.a(this.ea[this.ee]);
                this.ea[this.ee].else = else1;
                this.ea[this.ee].int(a2);
                ++this.ee;
            }
            else if (a2.a.toLowerCase().compareTo("hotspotrectangle") == 0) {
                this.s();
                (this.ea[this.ee] = (e)Class.forName("a.a.a.a.bs").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.void, super.cj, super.goto, super.cl, super.ck);
                super.ck.a(this.ea[this.ee]);
                this.ea[this.ee].else = else1;
                this.ea[this.ee].int(a2);
                ++this.ee;
            }
            else if (a2.a.toLowerCase().compareTo("hotspotpolygonal") == 0) {
                this.s();
                (this.ea[this.ee] = (e)Class.forName("a.a.a.a.n").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.void, super.cj, super.goto, super.cl, super.ck);
                super.ck.a(this.ea[this.ee]);
                this.ea[this.ee].else = else1;
                this.ea[this.ee].int(a2);
                ++this.ee;
            }
            else if (a2.a.toLowerCase().compareTo("light") == 0) {
                this.q();
                (this.dK[this.dZ] = (e)Class.forName("a.a.a.a.at").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.void, super.cj, super.goto, super.cl, super.ck);
                super.ck.a(this.dK[this.dZ]);
                this.dK[this.dZ].else = else1;
                this.dK[this.dZ].int(a2);
                ++this.dZ;
            }
            else if (a2.a.toLowerCase().compareTo("sound") == 0) {
                this.p();
                (this.dV[this.d6] = (e)Class.forName("a.a.a.a.as").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.void, super.cj, super.goto, super.cl, super.ck);
                super.ck.a(this.dV[this.d6]);
                this.dV[this.d6].else = else1;
                this.dV[this.d6].int(a2);
                ++this.d6;
            }
        }
        catch (Exception ex) {}
    }
    
    protected void byte(final a2 a2) {
        if (a2.a.toLowerCase().compareTo("moveto") == 0 || a2.a.toLowerCase().compareTo("move") == 0 || a2.a.toLowerCase().compareTo("sleep") == 0 || a2.a.toLowerCase().compareTo("action") == 0) {
            this.t();
            this.dI[this.dP] = new a5();
            if (a2.a.toLowerCase().compareTo("moveto") == 0) {
                this.dI[this.dP].do = 0;
            }
            else if (a2.a.toLowerCase().compareTo("move") == 0) {
                this.dI[this.dP].do = 1;
            }
            else if (a2.a.toLowerCase().compareTo("sleep") == 0) {
                this.dI[this.dP].do = 2;
            }
            else if (a2.a.toLowerCase().compareTo("action") == 0) {
                this.dI[this.dP].do = 3;
                this.dI[this.dP].h = (String.valueOf(a2.case.if) + "\u0000").toCharArray();
            }
            for (int i = 0; i < a2.do; ++i) {
                if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                    this.dI[this.dP].b = new Float(a2.new[i]) * super.cj;
                    this.dI[this.dP].k = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                    this.dI[this.dP].c = new Float(a2.new[i]) * super.cj;
                    this.dI[this.dP].f = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("roll") == 0) {
                    this.dI[this.dP].l = new Float(a2.new[i]) * super.cj;
                    this.dI[this.dP].o = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("fov") == 0) {
                    this.dI[this.dP].g = new Float(a2.new[i]) * super.cj;
                    this.dI[this.dP].p = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("time") == 0) {
                    this.dI[this.dP].new = (long)(new Float(a2.new[i]) * 1000.0f);
                    this.dI[this.dP].m = 0;
                }
                else if (a2.try[i].toLowerCase().compareTo("speed") == 0) {
                    this.dI[this.dP].i = new Float(a2.new[i]) * super.cj;
                    this.dI[this.dP].m = 1;
                }
                else if (a2.try[i].toLowerCase().compareTo("amid") == 0 || a2.try[i].toLowerCase().compareTo("apid") == 0) {
                    this.dI[this.dP].long = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
                else if (a2.try[i].toLowerCase().compareTo("next") == 0) {
                    this.dI[this.dP].void = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
            }
            ++this.dP;
        }
    }
    
    void a(final aq[][] array, final aq[][] array2, final int n) {
        if (array != null && array2 != null) {
            super.void.a(super.cn, super.cg, super.ci, this.d4);
            for (int i = 0; i < this.eb; ++i) {
                for (int j = 0; j < this.d0 * this.dY; ++j) {
                    super.void.a(this.d4, array[i][j], array2[i][j]);
                }
            }
        }
        this.n();
    }
    
    void n() {
        for (int i = 0; i < this.dZ; ++i) {
            this.dK[i].a(this.d4);
        }
        for (int j = 0; j < this.ee; ++j) {
            this.ea[j].a(this.d4);
        }
        for (int k = 0; k < this.d6; ++k) {
            this.dV[k].a(this.d4);
        }
    }
    
    boolean byte(final boolean b) {
        super.try = true;
        for (int i = 0; i < this.ee; ++i) {
            if (!this.ea[i].try) {
                super.try = false;
            }
        }
        for (int j = 0; j < this.dZ; ++j) {
            if (!this.dK[j].try) {
                super.try = false;
            }
        }
        return (b && this.dE != null && this.dE.m) || ((this.dE == null || this.dE.try) && super.try);
    }
    
    void new(final long n) {
        for (int i = 0; i < this.ee; ++i) {
            this.ea[i].new(n);
        }
        for (int j = 0; j < this.dZ; ++j) {
            this.dK[j].new(n);
        }
        for (int k = 0; k < this.d6; ++k) {
            this.dV[k].new(n);
        }
        if (this.dE != null) {
            this.dE.if(n);
        }
        super.else = true;
    }
    
    public boolean a(final long n) {
        super.char = false;
        boolean b = false;
        if (this.dE != null) {
            b |= this.dE.a(n);
            super.try = this.dE.try;
        }
        else {
            super.try = true;
        }
        boolean b2;
        if (super.try && super.else) {
            if ((this.dE != null && this.dE.V) || super.b) {
                if (this.dE != null) {
                    this.dE.V = false;
                }
                this.r();
                if (super.cn != 0.0f || super.cg != 0.0f || super.ci != 0.0f) {
                    this.a(this.dW, this.dQ, this.d0 * this.dY);
                }
                super.b = false;
                b = true;
            }
            if (this.dT != super.cn || this.dJ != super.cg || this.d5 != super.ci) {
                this.a(this.dW, this.dQ, this.d0 * this.dY);
                b = true;
            }
            if (this.try(n)) {
                this.a(this.dW, this.dQ, this.d0 * this.dY);
                b = true;
            }
            this.dT = super.cn;
            this.dJ = super.cg;
            this.d5 = super.ci;
            b2 = (b | this.byte(n));
        }
        else {
            b2 = (b | this.byte(n));
        }
        if (this.dE != null) {
            super.char &= this.dE.char;
        }
        final boolean if1 = super.if;
        super.if = false;
        return (b2 & super.do) | if1;
    }
    
    boolean try(final long n) {
        boolean b = false;
        if (this.d1) {
            if ((this.d7 == -1 || (this.dI[this.d7].int != 0L && this.dI[this.d7].int < n)) && (this.d7 == -1 || this.dI[this.d7].do != 0 || this.dI[this.d7].int == 1L)) {
                this.if(n, false);
                if (this.d7 == -1) {
                    return false;
                }
            }
            if (this.dI[this.d7].do == 1) {
                final float n2 = 1000.0f / (n - this.dI[this.d7].q);
                this.dI[this.d7].q = n;
                if (this.dI[this.d7].k) {
                    super.cn += this.dI[this.d7].b / n2;
                }
                if (this.dI[this.d7].f) {
                    super.cg += this.dI[this.d7].c / n2;
                }
                if (this.dI[this.d7].o) {
                    super.ci += this.dI[this.d7].l / n2;
                }
                if (this.dI[this.d7].p) {
                    super.ch += this.dI[this.d7].g / n2;
                }
                b = true;
            }
            else if (this.dI[this.d7].do == 0) {
                float n3;
                if (this.dI[this.d7].new == 0L || this.dI[this.d7].int < n || n - this.dI[this.d7].q >= this.dI[this.d7].int - n) {
                    n3 = 0.0f;
                    this.dI[this.d7].int = 1L;
                }
                else {
                    n3 = (this.dI[this.d7].int - n) / (n - this.dI[this.d7].q);
                }
                this.dI[this.d7].q = n;
                if (this.dI[this.d7].k) {
                    super.cn = super.void.a(super.cn, this.dI[this.d7].b);
                    if (n3 == 0.0f) {
                        super.cn = this.dI[this.d7].b;
                    }
                    else {
                        super.cn += (this.dI[this.d7].b - super.cn) / n3;
                    }
                }
                if (this.dI[this.d7].f) {
                    super.cg = super.void.a(super.cg, this.dI[this.d7].c);
                    if (n3 == 0.0f) {
                        super.cg = this.dI[this.d7].c;
                    }
                    else {
                        super.cg += (this.dI[this.d7].c - super.cg) / n3;
                    }
                }
                if (this.dI[this.d7].o) {
                    super.ci = super.void.a(super.ci, this.dI[this.d7].l);
                    if (n3 == 0.0f) {
                        super.ci = this.dI[this.d7].l;
                    }
                    else {
                        super.ci += (this.dI[this.d7].l - super.ci) / n3;
                    }
                }
                if (this.dI[this.d7].p) {
                    super.ch = super.void.a(super.ch, this.dI[this.d7].g);
                    if (n3 == 0.0f) {
                        super.ch = this.dI[this.d7].g;
                    }
                    else {
                        super.ch += (this.dI[this.d7].g - super.ch) / n3;
                    }
                }
                b = true;
            }
        }
        return b;
    }
    
    private void if(final long q, final boolean b) {
        if (!b || this.d7 == -1) {
            if (this.d7 == -1) {
                if (g.a(this.dD) == 0) {
                    this.d7 = 0;
                }
                else {
                    for (int i = 0; i < this.dP; ++i) {
                        if (g.a(this.dD, this.dI[i].long) == 0) {
                            this.d7 = i;
                        }
                    }
                }
                if (this.d7 >= this.dP || this.d7 == -1) {
                    this.d1 = false;
                    this.d7 = -1;
                    return;
                }
            }
            else {
                if (g.a(this.dI[this.d7].void, ao.dG) == 0) {
                    this.d7 = -1;
                    this.d1 = false;
                    return;
                }
                boolean b2 = false;
                for (int j = 0; j < this.dP; ++j) {
                    if (g.a(this.dI[this.d7].void, this.dI[j].long) == 0) {
                        this.d7 = j;
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.d7 = -1;
                    this.d1 = false;
                    return;
                }
            }
        }
        this.dI[this.d7].q = q;
        if (this.dI[this.d7].do == 1) {
            if (this.dI[this.d7].new == 0L) {
                this.dI[this.d7].int = 0L;
                this.dI[this.d7].q = q - 40L;
            }
            else {
                this.dI[this.d7].int = q + this.dI[this.d7].new + 40L;
            }
        }
        else if (this.dI[this.d7].do == 0) {
            if (this.dI[this.d7].m == 1) {
                float n = 0.0f;
                if (this.dI[this.d7].k) {
                    n = Math.abs(this.dI[this.d7].b - super.void.a(super.cn, this.dI[this.d7].b));
                }
                if (this.dI[this.d7].f) {
                    n = Math.max(n, Math.abs(this.dI[this.d7].c - super.void.a(super.cg, this.dI[this.d7].c)));
                }
                if (this.dI[this.d7].o) {
                    n = Math.max(n, Math.abs(this.dI[this.d7].l - super.void.a(super.ci, this.dI[this.d7].l)));
                }
                if (this.dI[this.d7].p) {
                    n = Math.max(n, Math.abs(this.dI[this.d7].g - super.void.a(super.ch, this.dI[this.d7].g)));
                }
                this.dI[this.d7].new = (long)(n * 1000.0f / this.dI[this.d7].i);
            }
            if (this.dI[this.d7].new == 0L) {
                this.dI[this.d7].int = 0L;
            }
            else {
                this.dI[this.d7].int = q + this.dI[this.d7].new + 40L;
            }
        }
        else {
            if (this.dI[this.d7].do == 3) {
                super.ck.if(this.dI[this.d7].h);
                this.dI[this.d7].int = 1L;
                return;
            }
            this.dI[this.d7].int = q + this.dI[this.d7].new;
        }
    }
    
    boolean byte(final long n) {
        boolean b = false;
        boolean char1 = true;
        for (int i = 0; i < this.dZ; ++i) {
            b |= this.dK[i].a(n);
            char1 &= this.dK[i].try;
        }
        for (int j = 0; j < this.ee; ++j) {
            b |= this.ea[j].a(n);
            char1 &= this.ea[j].try;
        }
        for (int k = 0; k < this.d6; ++k) {
            b |= this.dV[k].a(n);
        }
        super.char = char1;
        return b;
    }
    
    void o() {
        for (int i = 0; i < this.dZ; ++i) {
            this.dK[i].new(false);
        }
        for (int j = 0; j < this.ee; ++j) {
            this.ea[j].new(false);
        }
        for (int k = 0; k < this.d6; ++k) {
            this.dV[k].new(false);
        }
    }
    
    public void new(final boolean b) {
        if (super.else && super.try && super.do) {
            if (this.dE != null && this.dE.do && !super.b) {
                this.dE.for();
                for (int i = 0; i < this.eb; ++i) {
                    super.void.P.a(this.dQ[i], this.d0, this.dY, this.dE, super.cl, b);
                }
            }
            this.o();
        }
    }
    
    private void p() {
        if (this.dV == null || this.dV.length == this.d6) {
            final e[] dv = new e[this.d6 + 10];
            for (int i = 0; i < this.d6; ++i) {
                dv[i] = this.dV[i];
            }
            this.dV = dv;
        }
    }
    
    private void q() {
        if (this.dK == null || this.dK.length == this.dZ) {
            final e[] dk = new e[this.dZ + 10];
            for (int i = 0; i < this.dZ; ++i) {
                dk[i] = this.dK[i];
            }
            this.dK = dk;
        }
    }
    
    private void s() {
        if (this.ea == null || this.ea.length == this.ee) {
            final e[] ea = new e[this.ee + 10];
            for (int i = 0; i < this.ee; ++i) {
                ea[i] = this.ea[i];
            }
            this.ea = ea;
        }
    }
    
    private void t() {
        if (this.dI == null || this.dI.length == this.dP) {
            final a5[] di = new a5[this.dP + 10];
            for (int i = 0; i < this.dP; ++i) {
                di[i] = this.dI[i];
            }
            this.dI = di;
        }
    }
    
    protected final aq if(final int n, final int n2) {
        final double n3 = super.cl.ep * super.cl.eG;
        final double n4 = super.cl.ep * super.cl.eC * super.cl.eA;
        final double n5 = super.cl.ep * super.cl.eq * super.cl.eA;
        final int n6 = n - (super.cl.et >> 1);
        final int n7 = n2 - (super.cl.ey >> 1);
        this.dB.try = (float)(n4 + n7 * super.cl.eG * super.cl.eC + n6 * super.cl.eq);
        this.dB.if = (float)(n5 + n7 * super.cl.eG * super.cl.eq - n6 * super.cl.eC);
        this.dB.byte = (float)(n3 - n7 * super.cl.eA);
        final float[][] a = super.void.a();
        super.void.a(super.cn, super.cg, super.ci, a);
        super.void.a(a, this.dB, this.dH);
        return this.dH;
    }
    
    protected boolean do(final int n, final int n2) {
        return false;
    }
    
    void if(final ab ab) {
        final boolean i = ab.i;
        if (!super.do) {
            ab.i = true;
        }
        for (int j = 0; j < this.ee; ++j) {
            this.ea[j].if(ab);
        }
        if (!super.do) {
            ab.i = i;
        }
        if (!ab.i && ab.if == 0 && this.do(ab.goto, ab.else)) {
            ab.i = true;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, ac.G) == 0) {
            super.cn = al.do(a3) * super.cj;
        }
        else if (g.if(array, ac.try) == 0) {
            super.cg = al.do(a3) * super.cj;
        }
        else if (g.if(array, ac.ao) == 0) {
            super.ci = al.do(a3) * super.cj;
        }
        else if (g.if(array, ac.H) == 0) {
            super.ch = al.do(a3) * super.cj;
        }
        else if (g.if(array, ac.ap) == 0) {
            super.c = al.if(a3);
            super.void.if(super.ck.O, 0, super.ck.J - 1);
        }
        else if (g.if(array, ac.K) == 0) {
            if (!(super.do = al.a(a3))) {
                for (int i = 0; i < this.d6; ++i) {
                    this.dV[i].try(super.do);
                }
            }
            super.if = true;
        }
        else if (g.if(array, ac.W) == 0) {
            this.d1 = al.a(a3);
            if (this.d1) {
                this.if(System.currentTimeMillis(), true);
                this.dN = false;
            }
        }
        else if (g.if(array, ac.j) == 0) {
            if (a3.char != 4) {
                return;
            }
            for (int j = 0; j < this.dP; ++j) {
                if (g.a(a3.int, this.dI[j].long) == 0) {
                    this.d7 = j;
                    this.if(System.currentTimeMillis(), true);
                    this.d1 = true;
                    this.dN = false;
                    return;
                }
            }
        }
        else if (g.if(array, ac.x) == 0) {
            int n = 0;
            for (int k = 0; k < this.dP; ++k) {
                if (n != 0) {
                    this.dI[k - 1] = this.dI[k];
                }
                else if (g.a(a3.int, this.dI[k].long) == 0) {
                    n = 1;
                }
            }
            if (n != 0) {
                --this.dP;
            }
        }
        else if (g.if(array, ac.u) == 0) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int);
                this.byte(bf.do);
            }
            catch (Exception ex) {}
        }
        else if (g.if(array, ao.eh) == 0) {
            this.dR = al.do(a3) * super.cj;
            this.ef = true;
        }
        else if (g.if(array, ao.d9) == 0) {
            this.dM = al.do(a3) * super.cj;
            this.ef = true;
        }
        else if (g.if(array, ao.d8) == 0) {
            this.ed = al.do(a3) * super.cj;
            this.eg = true;
        }
        else if (g.if(array, ao.dX) == 0) {
            this.d3 = al.do(a3) * super.cj;
            this.eg = true;
        }
        else if (g.if(array, ao.dO) == 0) {
            this.dU = al.do(a3) * super.cj;
        }
        else if (g.if(array, ao.ei) == 0) {
            this.dF = al.do(a3) * super.cj;
        }
        else if (g.if(array, ao.ec) == 0) {
            this.eg = false;
        }
        else if (g.if(array, ao.d2) == 0) {
            this.ef = false;
        }
        else if (g.if(array, ao.dL) == 0) {
            this.dC = al.a(a3);
        }
        else if (g.if(array, ac.y) == 0) {
            final bf bf2 = new bf();
            try {
                y.a(bf2, a3.int);
                this.a(bf2.do, true);
            }
            catch (Exception ex2) {}
        }
        else if (g.if(array, ac.ak) == 0) {
            this.ee = super.void.a(this.ea, a3.int, this.ee);
            this.dZ = super.void.a(this.dK, a3.int, this.dZ);
            super.ck.r = super.void.a(super.ck.i, a3.int, super.ck.r);
        }
    }
    
    public a3 a(final char[] array) {
        if (g.if(array, ac.G) == 0) {
            super.int.char = 3;
            super.int.else = super.cn * super.cm;
            return super.int;
        }
        if (g.if(array, ac.try) == 0) {
            super.int.char = 3;
            super.int.else = super.cg * super.cm;
            return super.int;
        }
        if (g.if(array, ac.ao) == 0) {
            super.int.char = 3;
            super.int.else = super.ci * super.cm;
            return super.int;
        }
        if (g.if(array, ac.H) == 0) {
            super.int.char = 3;
            super.int.else = super.ch * super.cm;
            return super.int;
        }
        if (g.if(array, ac.K) == 0) {
            super.int.char = 1;
            super.int.long = super.do;
            return super.int;
        }
        if (g.if(array, ac.ap) == 0) {
            super.int.char = 2;
            super.int.case = super.c;
            return super.int;
        }
        if (g.if(array, ac.W) == 0) {
            super.int.char = 1;
            super.int.long = this.d1;
            return super.int;
        }
        if (g.if(array, ao.dL) == 0) {
            super.int.char = 1;
            super.int.long = this.dC;
            return super.int;
        }
        if (g.if(array, ac.j) == 0) {
            if (this.dP == 0) {
                super.int.char = 1;
                super.int.long = false;
                return super.int;
            }
            super.int.char = 4;
            if (this.d7 != -1) {
                super.int.int = this.dI[this.d7].long;
            }
            else if (g.a(this.dD, ao.dG) != 0) {
                super.int.int = this.dD;
            }
            else {
                super.int.int = this.dI[0].long;
            }
            return super.int;
        }
        else {
            if (g.if(array, ac.W) == 0) {
                super.int.char = 1;
                super.int.long = this.d1;
                return super.int;
            }
            if (g.if(array, ao.eh) == 0) {
                if (!this.ef) {
                    return ac.a(super.int);
                }
                super.int.char = 3;
                super.int.else = this.dR * super.cm;
                return super.int;
            }
            else if (g.if(array, ao.d9) == 0) {
                if (!this.ef) {
                    return ac.a(super.int);
                }
                super.int.char = 3;
                super.int.else = this.dM * super.cm;
                return super.int;
            }
            else if (g.if(array, ao.d8) == 0) {
                if (!this.ef) {
                    return ac.a(super.int);
                }
                super.int.char = 3;
                super.int.else = this.ed * super.cm;
                return super.int;
            }
            else if (g.if(array, ao.dX) == 0) {
                if (!this.ef) {
                    return ac.a(super.int);
                }
                super.int.char = 3;
                super.int.else = this.d3 * super.cm;
                return super.int;
            }
            else if (g.if(array, ao.dO) == 0) {
                if (!this.dS) {
                    return ac.a(super.int);
                }
                super.int.char = 3;
                super.int.else = this.dU * super.cm;
                return super.int;
            }
            else if (g.if(array, ao.ei) == 0) {
                if (!this.dS) {
                    return ac.a(super.int);
                }
                super.int.char = 3;
                super.int.else = this.dF * super.cm;
                return super.int;
            }
            else {
                if (g.if(array, ac.o) == 0) {
                    super.int = new a3();
                    super.int.char = 5;
                    final int goto1 = this.ee + this.dZ + this.d6 + ((this.dE != null) ? 1 : 0);
                    super.int.goto = goto1;
                    super.int.a = new a3[goto1];
                    for (int i = 0; i < this.ee; ++i) {
                        super.int.a[i] = new a3();
                        super.int.a[i].char = 4;
                        super.int.a[i].int = this.ea[i].e;
                    }
                    for (int j = this.ee; j < this.ee + this.dZ; ++j) {
                        super.int.a[j] = new a3();
                        super.int.a[j].char = 4;
                        super.int.a[j].int = this.dK[j - this.ee].e;
                    }
                    for (int k = this.ee + this.dZ; k < this.ee + this.dZ + this.d6; ++k) {
                        super.int.a[k] = new a3();
                        super.int.a[k].char = 4;
                        super.int.a[k].int = this.dV[k - this.ee - this.dZ].e;
                    }
                    if (this.dE != null) {
                        super.int.a[goto1 - 1] = new a3();
                        super.int.a[goto1 - 1].char = 4;
                        super.int.a[goto1 - 1].int = this.dE.e;
                    }
                    return super.int;
                }
                return ac.a(super.int);
            }
        }
    }
    
    public void for(final char[] array, final a3 a3) {
        for (int i = 0; i < this.ee; ++i) {
            this.ea[i].a(array, a3);
        }
    }
    
    public void if(final char[] array, final a3 a3) {
        for (int i = 0; i < this.dZ; ++i) {
            this.dK[i].a(array, a3);
        }
    }
    
    public void do(final char[] array, final a3 a3) {
        for (int i = 0; i < this.d6; ++i) {
            this.dV[i].a(array, a3);
        }
    }
    
    protected void r() {
    }
    
    public void if() {
        if (this.dE != null) {
            this.dE.if();
            this.dE = null;
        }
        for (int i = 0; i < this.dZ; ++i) {
            this.dK[i].if();
            this.dK[i] = null;
        }
        this.dK = null;
        for (int j = 0; j < this.ee; ++j) {
            this.ea[j].if();
            this.ea[j] = null;
        }
        this.ea = null;
        for (int k = 0; k < this.d6; ++k) {
            this.dV[k].if();
            this.dV[k] = null;
        }
        this.dV = null;
    }
}
