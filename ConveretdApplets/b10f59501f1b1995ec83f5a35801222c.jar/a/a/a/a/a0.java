// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a0 extends ad
{
    ad af;
    ad ae;
    ae Z;
    ae ac;
    boolean ag;
    String ab;
    boolean ad;
    int aa;
    
    a0(final ac b, final ae long1, final boolean try1, final boolean goto1) {
        this.af = null;
        this.ae = null;
        this.Z = null;
        this.ac = null;
        this.ag = false;
        this.ab = null;
        this.ad = false;
        this.aa = 0;
        super.b = b;
        super.long = long1;
        super.try = try1;
        super.goto = goto1;
    }
    
    public void a(final a2 a2) {
        String s = null;
        String s2 = null;
        super.u = -1;
        super.j = -1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                s2 = a2.new[i];
            }
            else if (a2.try[i].toLowerCase().compareTo("preview") == 0) {
                s = a2.new[i];
            }
            else if (a2.try[i].toLowerCase().compareTo("play") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.l = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.r = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.j = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.u = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0 && a2.new[i].compareTo("false") == 0) {
                super.for = false;
            }
        }
        this.a();
        if (super.j > 0 && super.u > 0) {
            super.byte = true;
        }
        if (s != null) {
            this.af = new ad();
            this.Z = super.b.A.a(s, super.long, false, true, true, null);
            this.af.l = super.l;
            this.af.r = super.r;
        }
        if (s2 != null) {
            this.ae = new ad();
            this.ac = super.b.A.a(s2, super.long, false, true, true, this.Z);
            if (this.Z != null) {
                this.ac.goto = this.Z;
            }
            this.aa = 2;
            this.ae.l = super.l;
            this.ae.r = super.r;
        }
    }
    
    void if(final long n) {
        if (this.aa == 0 && this.af != null && this.af.byte) {
            this.af.if(n);
        }
        else if (this.aa > 0 && this.ae != null && this.ae.byte) {
            this.ae.if(n);
        }
        super.goto = true;
    }
    
    public boolean a(final long n) {
        boolean a = false;
        final boolean do1 = super.do;
        super.do = false;
        if (this.ac.k != 0) {
            this.ac = this.ac.try[0];
        }
        if (!super.byte && ((this.ac.new && super.try) || this.ac.case)) {
            this.ae = this.ac.a(this.ae);
            this.ae.l = super.l;
            this.ae.goto = super.goto;
            super.j = this.ae.j;
            super.u = this.ae.u;
            super.q = this.ae.q;
            super.n = true;
            super.byte = true;
            this.ag = true;
            super.do = true;
            super.v = this.ae.v;
            super.p = this.ae.p;
        }
        if (!super.goto) {
            return false;
        }
        if (super.goto && super.byte) {
            a = this.ae.a(n);
        }
        if (this.ae != null) {
            if (!this.ae.byte) {
                this.ae.byte = this.ac.case;
                return true;
            }
            super.else = true;
        }
        return (a & super.for) | do1;
    }
    
    public void a(final char[] array, final a3 a3) {
        if (a.a.a.a.g.do(array, a.a.a.a.ac.ac) == 0) {
            super.l = al.a(a3);
            if (this.af != null) {
                this.af.if(super.l);
            }
            if (this.ae != null) {
                this.ae.if(super.l);
            }
            super.do = true;
        }
    }
    
    public void for() {
        if (super.for) {
            this.ae.for();
        }
    }
    
    public void if() {
        if (this.af != null) {
            this.af.if();
            this.af = null;
        }
        if (this.ae != null) {
            this.ae.if();
            this.ae = null;
        }
        super.q = null;
    }
}
