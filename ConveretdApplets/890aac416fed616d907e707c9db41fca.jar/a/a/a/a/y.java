// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class y extends ap
{
    boolean W;
    int X;
    int U;
    ap[] R;
    aq[] S;
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
        y.N = new char[] { 'x', 'm', 'l', 'a', 'n', 'i', 'm', '\0' };
    }
    
    public y() {
        this.W = false;
        this.X = 0;
        this.U = 0;
        this.Z = 0;
        this.P = 0L;
        this.O = false;
        this.T = true;
    }
    
    public void a(final bh bh, final an b, final aq long1) {
        super.case = y.N;
        if (bh == null && b == null && long1 == null) {
            return;
        }
        super.b = b;
        super.long = long1;
        super.v = true;
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("width") == 0) {
                super.s = Integer.valueOf(bh.new[i]);
                super.l = super.s;
            }
            else if (bh.try[i].toLowerCase().compareTo("height") == 0) {
                super.r = Integer.valueOf(bh.new[i]);
                super.p = super.r;
            }
            else if (bh.try[i].toLowerCase().compareTo("loop") == 0) {
                if (bh.new[i].toLowerCase().compareTo("true") == 0) {
                    this.W = true;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                this.X = an.a(bh.new[i]);
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
        for (bh bh2 = bh.if; bh2 != null; bh2 = bh2.for) {
            if (bh2.a.toLowerCase().compareTo("image") == 0) {
                ++this.U;
            }
        }
        this.R = new ap[this.U];
        this.Q = new boolean[this.U];
        this.M = new int[this.U];
        this.Y = new int[this.U];
        this.V = new int[this.U];
        this.S = new aq[this.U];
        this.U = 0;
        for (bh bh3 = bh.if; bh3 != null; bh3 = bh3.for) {
            if (bh3.a.toLowerCase().compareTo("image") == 0) {
                for (int j = 0; j < bh3.do; ++j) {
                    if (bh3.try[j].toLowerCase().compareTo("file") == 0) {
                        this.R[this.U] = new ap();
                        this.S[this.U] = super.b.B.a(bh3.new[j], super.long, false, true, false);
                    }
                    else if (bh3.try[j].toLowerCase().compareTo("posx") == 0) {
                        this.M[this.U] = Integer.valueOf(bh3.new[j]);
                    }
                    else if (bh3.try[j].toLowerCase().compareTo("posy") == 0) {
                        this.Y[this.U] = Integer.valueOf(bh3.new[j]);
                    }
                    else if (bh3.try[j].toLowerCase().compareTo("time") == 0) {
                        this.V[this.U] = Integer.valueOf(bh3.new[j]);
                    }
                    else if (bh3.try[j].toLowerCase().compareTo("replace") == 0) {
                        if (bh3.new[j].toLowerCase().compareTo("true") == 0) {
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
    
    public ap a(final ap ap, final aq long1) {
        final y y = new y();
        y.a(null, null, null);
        y.byte = super.byte;
        y.W = this.W;
        y.Z = this.Z;
        y.r = super.r;
        y.s = super.s;
        y.p = super.p;
        y.l = super.l;
        final int n = y.s * y.r;
        y.x = new int[n];
        for (int i = 0; i < n; ++i) {
            y.x[i] = this.X;
        }
        y.R = this.R;
        y.U = this.U;
        y.S = this.S;
        y.long = super.long;
        y.Q = this.Q;
        y.M = this.M;
        y.Y = this.Y;
        y.V = this.V;
        y.t = super.t;
        y.O = this.O;
        y.P = this.P;
        y.v = super.v;
        y.long = long1;
        return y;
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
        a3.a(this.R[this.Z], this, this.M[this.Z], this.Y[this.Z], this.R[this.Z].s, this.R[this.Z].r);
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
