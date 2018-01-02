// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class r extends ad
{
    boolean W;
    int X;
    int U;
    ad[] R;
    ae[] S;
    boolean[] Q;
    int[] M;
    int[] Y;
    int[] V;
    int Z;
    long P;
    boolean O;
    boolean T;
    protected static char[] N;
    
    static {
        r.N = new char[] { 'x', 'm', 'l', 'a', 'n', 'i', 'm', '\0' };
    }
    
    public r() {
        this.W = false;
        this.X = 0;
        this.U = 0;
        this.Z = 0;
        this.P = 0L;
        this.O = false;
        this.T = true;
    }
    
    public void a(final a2 a2, final ac b, final ae long1) {
        super.case = r.N;
        if (a2 == null && b == null && long1 == null) {
            return;
        }
        super.b = b;
        super.long = long1;
        super.v = true;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.s = Integer.valueOf(a2.new[i]);
                super.l = super.s;
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.r = Integer.valueOf(a2.new[i]);
                super.p = super.r;
            }
            else if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].toLowerCase().compareTo("true") == 0) {
                    this.W = true;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                this.X = ac.a(a2.new[i]);
                if ((this.X & 0xFF000000) == 0xFF000000) {
                    super.v = false;
                }
                else if ((this.X & 0xFF000000) != 0x0) {
                    super.i = true;
                }
            }
        }
        if (super.s > 0 && super.r > 0) {
            super.x = new int[super.s * super.r];
            try {
                int n = 0;
                while (true) {
                    super.x[n++] = this.X;
                }
            }
            catch (Exception ex) {}
        }
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("image") == 0) {
                ++this.U;
            }
        }
        this.R = new ad[this.U];
        this.Q = new boolean[this.U];
        this.M = new int[this.U];
        this.Y = new int[this.U];
        this.V = new int[this.U];
        this.S = new ae[this.U];
        this.U = 0;
        for (a2 a4 = a2.if; a4 != null; a4 = a4.for) {
            if (a4.a.toLowerCase().compareTo("image") == 0) {
                for (int j = 0; j < a4.do; ++j) {
                    if (a4.try[j].toLowerCase().compareTo("file") == 0) {
                        this.R[this.U] = new ad();
                        this.S[this.U] = super.b.B.a(a4.new[j], super.long, false, true, false);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("posx") == 0) {
                        this.M[this.U] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("posy") == 0) {
                        this.Y[this.U] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("time") == 0) {
                        this.V[this.U] = Integer.valueOf(a4.new[j]);
                    }
                    else if (a4.try[j].toLowerCase().compareTo("replace") == 0) {
                        if (a4.new[j].toLowerCase().compareTo("true") == 0) {
                            this.Q[this.U] = true;
                        }
                        else {
                            this.Q[this.U] = false;
                        }
                    }
                }
                ++this.U;
            }
        }
    }
    
    public ad a(final ad ad, final ae long1) {
        final r r = new r();
        r.a(null, null, null);
        r.byte = super.byte;
        r.W = this.W;
        r.Z = this.Z;
        r.r = super.r;
        r.s = super.s;
        r.p = super.p;
        r.l = super.l;
        final int n = r.s * r.r;
        r.x = new int[n];
        for (int i = 0; i < n; ++i) {
            r.x[i] = this.X;
        }
        r.R = this.R;
        r.U = this.U;
        r.S = this.S;
        r.long = super.long;
        r.Q = this.Q;
        r.M = this.M;
        r.Y = this.Y;
        r.V = this.V;
        r.t = super.t;
        r.O = this.O;
        r.P = this.P;
        r.v = super.v;
        r.long = long1;
        return r;
    }
    
    public boolean a(final long p) {
        boolean b = false;
        if (!super.byte) {
            b = true;
            for (int i = 0; i < this.U; ++i) {
                if (this.S[i] != null) {
                    b &= this.S[i].case;
                }
            }
            if (b) {
                super.byte = true;
                for (int j = 0; j < this.U; ++j) {
                    this.S[j].a(this.R[j]);
                }
                super.long.case = true;
            }
        }
        if (super.goto && super.byte) {
            if (this.V[this.Z] <= p - this.P && super.t) {
                ++this.Z;
                if (this.Z == this.U && this.W) {
                    this.Z = 0;
                }
                else if (this.Z == this.U) {
                    super.t = false;
                    this.Z = 0;
                }
                if (this.R[this.Z].byte) {
                    if (this.Q[this.Z]) {
                        this.R[this.Z].v = false;
                    }
                    this.P = p;
                }
            }
            if (super.t) {
                this.O = true;
            }
        }
        return b | this.O;
    }
    
    public void if(final boolean t) {
        if (t == super.t) {
            return;
        }
        if (!t) {
            super.t = t;
        }
        else {
            if (this.Z >= this.U - 1) {
                this.Z = 0;
            }
            super.t = true;
            this.P = System.currentTimeMillis();
            this.for();
        }
    }
    
    void if(final long p) {
        this.P = p;
        super.goto = true;
        super.c = false;
    }
    
    public void for() {
        if ((super.x == null || !this.O) & !this.T) {
            return;
        }
        this.O = false;
        if (this.T && this.R[this.Z].byte) {
            this.T = false;
        }
        ap.a(this.R[this.Z], this, this.M[this.Z], this.Y[this.Z], this.R[this.Z].s, this.R[this.Z].r);
    }
    
    public void if() {
        if (super.long.j <= 0) {
            for (int i = 0; i < this.U; ++i) {
                if (this.R[i] != null) {
                    this.R[i].if();
                }
                this.R[i] = null;
            }
        }
        else {
            super.long.a();
        }
    }
    
    public void int() {
        for (int i = 0; i < this.U; ++i) {
            this.S[i].a();
        }
    }
}
