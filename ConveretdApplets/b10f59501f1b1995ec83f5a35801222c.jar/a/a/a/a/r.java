// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class r extends ad
{
    boolean V;
    int W;
    int T;
    ad[] Q;
    ae[] R;
    boolean[] P;
    int[] L;
    int[] X;
    int[] U;
    int Y;
    long O;
    boolean N;
    boolean S;
    protected static char[] M;
    
    static {
        r.M = new char[] { 'x', 'm', 'l', 'a', 'n', 'i', 'm', '\0' };
    }
    
    public r() {
        this.V = false;
        this.W = 0;
        this.T = 0;
        this.Y = 0;
        this.O = 0L;
        this.N = false;
        this.S = true;
    }
    
    public void a(final a2 a2, final ac b, final ae long1) {
        super.case = r.M;
        if (a2 == null && b == null && long1 == null) {
            return;
        }
        super.b = b;
        super.long = long1;
        super.v = true;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.j = Integer.valueOf(a2.new[i]);
                super.t = super.j;
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.u = Integer.valueOf(a2.new[i]);
                super.k = super.u;
            }
            else if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].toLowerCase().compareTo("true") == 0) {
                    this.V = true;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                this.W = ac.a(a2.new[i]);
                if ((this.W & 0xFF000000) == 0xFF000000) {
                    super.v = false;
                }
                else if ((this.W & 0xFF000000) != 0x0) {
                    super.p = true;
                }
            }
        }
        if (super.j > 0 && super.u > 0) {
            super.q = new int[super.j * super.u];
            try {
                int n = 0;
                while (true) {
                    super.q[n++] = this.W;
                }
            }
            catch (Exception ex) {}
        }
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("image") == 0) {
                ++this.T;
            }
        }
        this.Q = new ad[this.T];
        this.P = new boolean[this.T];
        this.L = new int[this.T];
        this.X = new int[this.T];
        this.U = new int[this.T];
        this.R = new ae[this.T];
        this.T = 0;
        for (a2 a4 = a2.if; a4 != null; a4 = a4.for) {
            if (a4.a.toLowerCase().compareTo("image") == 0) {
                for (int j = 0; j < a4.do; ++j) {
                    if (a4.try[j].toLowerCase().compareTo("file") == 0) {
                        this.Q[this.T] = new ad();
                        this.R[this.T] = super.b.A.a(a4.new[j], super.long, false, true, false);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("posx") == 0) {
                        this.L[this.T] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("posy") == 0) {
                        this.X[this.T] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("time") == 0) {
                        this.U[this.T] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("replace") == 0) {
                        if (a4.new[j].toLowerCase().compareTo("true") == 0) {
                            this.P[this.T] = true;
                        }
                        else {
                            this.P[this.T] = false;
                        }
                    }
                }
                ++this.T;
            }
        }
    }
    
    public ad a(final ad ad, final ae long1) {
        final r r = new r();
        r.a(null, null, null);
        r.byte = super.byte;
        r.V = this.V;
        r.Y = this.Y;
        r.u = super.u;
        r.j = super.j;
        r.k = super.k;
        r.t = super.t;
        final int n = r.j * r.u;
        r.q = new int[n];
        for (int i = 0; i < n; ++i) {
            r.q[i] = this.W;
        }
        r.Q = this.Q;
        r.T = this.T;
        r.R = this.R;
        r.long = super.long;
        r.P = this.P;
        r.L = this.L;
        r.X = this.X;
        r.U = this.U;
        r.l = super.l;
        r.N = this.N;
        r.O = this.O;
        r.v = super.v;
        r.long = long1;
        return r;
    }
    
    public boolean a(final long o) {
        boolean b = false;
        if (!super.byte) {
            b = true;
            for (int i = 0; i < this.T; ++i) {
                if (this.R[i] != null) {
                    b &= this.R[i].case;
                }
            }
            if (b) {
                super.byte = true;
                for (int j = 0; j < this.T; ++j) {
                    this.R[j].a(this.Q[j]);
                }
                super.long.case = true;
            }
        }
        if (super.goto && super.byte) {
            if (this.U[this.Y] <= o - this.O && super.l) {
                ++this.Y;
                if (this.Y == this.T && this.V) {
                    this.Y = 0;
                }
                else if (this.Y == this.T) {
                    super.l = false;
                    this.Y = 0;
                }
                if (this.Q[this.Y].byte) {
                    if (this.P[this.Y]) {
                        this.Q[this.Y].v = false;
                    }
                    this.O = o;
                }
            }
            if (super.l) {
                this.N = true;
            }
        }
        return b | this.N;
    }
    
    public void if(final boolean l) {
        if (l == super.l) {
            return;
        }
        if (!l) {
            super.l = l;
        }
        else {
            if (this.Y >= this.T - 1) {
                this.Y = 0;
            }
            super.l = true;
            this.O = System.currentTimeMillis();
            this.for();
        }
    }
    
    void if(final long o) {
        this.O = o;
        super.goto = true;
        super.c = false;
    }
    
    public void for() {
        if ((super.q == null || !this.N) & !this.S) {
            return;
        }
        this.N = false;
        if (this.S && this.Q[this.Y].byte) {
            this.S = false;
        }
        ap.a(this.Q[this.Y], this, this.L[this.Y], this.X[this.Y], this.Q[this.Y].j, this.Q[this.Y].u);
    }
    
    public void if() {
        if (super.long.j <= 0) {
            for (int i = 0; i < this.T; ++i) {
                if (this.Q[i] != null) {
                    this.Q[i].if();
                }
                this.Q[i] = null;
            }
        }
        else {
            super.long.a();
        }
    }
    
    public void int() {
        for (int i = 0; i < this.T; ++i) {
            this.R[i].a();
        }
    }
}
