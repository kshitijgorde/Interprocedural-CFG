// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a0 extends ad
{
    ad W;
    ad T;
    ae Y;
    ae U;
    boolean V;
    ae goto;
    String X;
    boolean S;
    int R;
    
    a0(final ac void1, final ae goto1, final boolean new1, final boolean else1) {
        this.W = null;
        this.T = null;
        this.Y = null;
        this.U = null;
        this.V = false;
        this.X = null;
        this.S = false;
        this.R = 0;
        super.void = void1;
        this.goto = goto1;
        super.new = new1;
        super.else = else1;
    }
    
    public void a(final a2 a2) {
        String s = null;
        String s2 = null;
        super.t = -1;
        super.i = -1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                s2 = a2.new[i];
            }
            else if (a2.try[i].toLowerCase().compareTo("preview") == 0) {
                s = a2.new[i];
            }
            else if (a2.try[i].toLowerCase().compareTo("play") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.k = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.q = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.i = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.t = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0 && a2.new[i].compareTo("false") == 0) {
                super.do = false;
            }
        }
        this.a();
        if (super.i > 0 && super.t > 0) {
            super.try = true;
        }
        if (s != null) {
            this.W = new ad();
            this.Y = super.void.w.a(s, this.goto, false, true, true);
            this.W.k = super.k;
            this.W.q = super.q;
        }
        if (s2 != null) {
            this.T = new ad();
            this.U = super.void.w.a(s2, this.goto, false, true, true);
            if (this.Y != null) {
                this.U.goto = this.Y;
            }
            this.R = 2;
            this.T.k = super.k;
            this.T.q = super.q;
        }
    }
    
    void if(final long n) {
        if (this.R == 0 && this.W != null && this.W.try) {
            this.W.if(n);
        }
        else if (this.R > 0 && this.T != null && this.T.try) {
            this.T.if(n);
        }
        super.else = true;
    }
    
    public boolean a(final long n) {
        boolean a = false;
        final boolean if1 = super.if;
        super.if = false;
        if (!super.try && ((this.U.int && super.new) || this.U.case)) {
            this.T = this.U.a(this.T);
            this.T.k = super.k;
            this.T.else = super.else;
            super.i = this.T.i;
            super.t = this.T.t;
            super.p = this.T.p;
            super.m = true;
            super.try = true;
            this.V = true;
            super.if = true;
            super.u = this.T.u;
            super.o = this.T.o;
        }
        if (!super.else) {
            return false;
        }
        if (super.else && super.try) {
            a = this.T.a(n);
        }
        if (this.T != null) {
            if (!this.T.try) {
                this.T.try = this.U.case;
                return true;
            }
            super.char = true;
        }
        return (a & super.do) | if1;
    }
    
    public void a(final char[] array, final a3 a3) {
        if (a.a.a.a.g.if(array, ac.W) == 0) {
            super.k = al.a(a3);
            if (this.W != null) {
                this.W.if(super.k);
            }
            if (this.T != null) {
                this.T.if(super.k);
            }
            super.if = true;
        }
    }
    
    public void for() {
        if (super.do) {
            this.T.for();
        }
    }
    
    public void if() {
        if (this.W != null) {
            this.W.if();
            this.W = null;
        }
        if (this.T != null) {
            this.T.if();
            this.T = null;
        }
    }
}
