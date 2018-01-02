// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class r extends ad
{
    ae goto;
    boolean q;
    int P;
    int M;
    ad[] J;
    ae[] K;
    boolean[] I;
    int[] F;
    int[] O;
    int[] N;
    int Q;
    long H;
    boolean G;
    boolean L;
    protected static char[] r;
    
    static {
        a.a.a.a.r.r = new char[] { 'x', 'm', 'l', 'a', 'n', 'i', 'm', '\0' };
    }
    
    public r() {
        this.q = false;
        this.P = 0;
        this.M = 0;
        this.Q = 0;
        this.H = 0L;
        this.G = false;
        this.L = true;
    }
    
    public void a(final a2 a2, final ac void1, final ae goto1) {
        super.byte = a.a.a.a.r.r;
        if (a2 == null && void1 == null && goto1 == null) {
            return;
        }
        super.void = void1;
        this.goto = goto1;
        super.u = true;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.i = Integer.valueOf(a2.new[i]);
                super.s = super.i;
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.t = Integer.valueOf(a2.new[i]);
                super.j = super.t;
            }
            else if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].toLowerCase().compareTo("true") == 0) {
                    this.q = true;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                this.P = ac.a(a2.new[i]);
                if ((this.P & 0xFF000000) == 0xFF000000) {
                    super.u = false;
                }
                else if ((this.P & 0xFF000000) != 0x0) {
                    super.o = true;
                }
            }
        }
        if (super.i > 0 && super.t > 0) {
            super.p = new int[super.i * super.t];
            try {
                int n = 0;
                while (true) {
                    super.p[n++] = this.P;
                }
            }
            catch (Exception ex) {}
        }
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("image") == 0) {
                ++this.M;
            }
        }
        this.J = new ad[this.M];
        this.I = new boolean[this.M];
        this.F = new int[this.M];
        this.O = new int[this.M];
        this.N = new int[this.M];
        this.K = new ae[this.M];
        this.M = 0;
        for (a2 a4 = a2.if; a4 != null; a4 = a4.for) {
            if (a4.a.toLowerCase().compareTo("image") == 0) {
                for (int j = 0; j < a4.do; ++j) {
                    if (a4.try[j].toLowerCase().compareTo("file") == 0) {
                        this.J[this.M] = new ad();
                        this.K[this.M] = super.void.w.a(a4.new[j], this.goto, false, true, false);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("posx") == 0) {
                        this.F[this.M] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("posy") == 0) {
                        this.O[this.M] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("time") == 0) {
                        this.N[this.M] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("replace") == 0) {
                        if (a4.new[j].toLowerCase().compareTo("true") == 0) {
                            this.I[this.M] = true;
                        }
                        else {
                            this.I[this.M] = false;
                        }
                    }
                }
                ++this.M;
            }
        }
    }
    
    public ad a(final ad ad, final ae goto1) {
        final r r = new r();
        r.a(null, null, null);
        r.try = super.try;
        r.q = this.q;
        r.Q = this.Q;
        r.t = super.t;
        r.i = super.i;
        r.j = super.j;
        r.s = super.s;
        final int n = r.i * r.t;
        r.p = new int[n];
        for (int i = 0; i < n; ++i) {
            r.p[i] = this.P;
        }
        r.J = this.J;
        r.M = this.M;
        r.K = this.K;
        r.goto = this.goto;
        r.I = this.I;
        r.F = this.F;
        r.O = this.O;
        r.N = this.N;
        r.k = super.k;
        r.G = this.G;
        r.H = this.H;
        r.u = super.u;
        r.goto = goto1;
        return r;
    }
    
    public boolean a(final long h) {
        boolean b = false;
        if (!super.try) {
            b = true;
            for (int i = 0; i < this.M; ++i) {
                if (this.K[i] != null) {
                    b &= this.K[i].case;
                }
            }
            if (b) {
                super.try = true;
                for (int j = 0; j < this.M; ++j) {
                    this.K[j].a(this.J[j]);
                }
                this.goto.case = true;
            }
        }
        if (super.else && super.try) {
            if (this.N[this.Q] <= h - this.H && super.k) {
                ++this.Q;
                if (this.Q == this.M && this.q) {
                    this.Q = 0;
                }
                else if (this.Q == this.M) {
                    super.k = false;
                    this.Q = 0;
                }
                if (this.J[this.Q].try) {
                    if (this.I[this.Q]) {
                        this.J[this.Q].u = false;
                    }
                    this.H = h;
                }
            }
            if (super.k) {
                this.G = true;
            }
        }
        return b | this.G;
    }
    
    public void if(final boolean k) {
        if (k == super.k) {
            return;
        }
        if (!k) {
            super.k = k;
        }
        else {
            if (this.Q >= this.M - 1) {
                this.Q = 0;
            }
            super.k = true;
            this.H = System.currentTimeMillis();
            this.for();
        }
    }
    
    void if(final long h) {
        this.H = h;
        super.else = true;
        super.b = false;
    }
    
    public void for() {
        if ((super.p == null || !this.G) & !this.L) {
            return;
        }
        this.G = false;
        if (this.L && this.J[this.Q].try) {
            this.L = false;
        }
        ap.a(this.J[this.Q], this, this.F[this.Q], this.O[this.Q], this.J[this.Q].i, this.J[this.Q].t);
    }
    
    public void if() {
        this.goto.a();
        for (int i = 0; i < this.M; ++i) {
            if (this.J[i] != null) {
                this.J[i].if();
            }
            this.J[i] = null;
        }
    }
    
    public void int() {
        for (int i = 0; i < this.M; ++i) {
            this.K[i].a();
        }
    }
}
