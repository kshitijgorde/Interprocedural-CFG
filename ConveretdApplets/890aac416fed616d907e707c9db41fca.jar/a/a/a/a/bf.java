// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bf extends ap
{
    ap ag;
    ap af;
    aq aa;
    aq ad;
    boolean ah;
    String ac;
    boolean ae;
    int ab;
    
    bf(final an b, final aq long1, final boolean try1, final boolean goto1) {
        this.ag = null;
        this.af = null;
        this.aa = null;
        this.ad = null;
        this.ah = false;
        this.ac = null;
        this.ae = false;
        this.ab = 0;
        super.b = b;
        super.long = long1;
        super.try = try1;
        super.goto = goto1;
    }
    
    public void a(final bh bh) {
        String s = null;
        String s2 = null;
        super.r = -1;
        super.s = -1;
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
            }
            else if (bh.try[i].toLowerCase().compareTo("file") == 0) {
                s2 = bh.new[i];
            }
            else if (bh.try[i].toLowerCase().compareTo("preview") == 0) {
                s = bh.new[i];
            }
            else if (bh.try[i].toLowerCase().compareTo("play") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.t = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("loop") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.u = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("width") == 0) {
                super.s = new Integer(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("height") == 0) {
                super.r = new Integer(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("visible") == 0 && bh.new[i].compareTo("false") == 0) {
                super.for = false;
            }
        }
        this.a();
        if (super.s > 0 && super.r > 0) {
            super.byte = true;
        }
        if (s != null) {
            this.ag = new ap();
            this.aa = super.b.B.a(s, super.long, false, true, true, null);
            this.ag.t = super.t;
            this.ag.u = super.u;
        }
        if (s2 != null) {
            this.af = new ap();
            this.ad = super.b.B.a(s2, super.long, false, true, true, this.aa);
            if (this.aa != null) {
                this.ad.goto = this.aa;
            }
            this.ab = 2;
            this.af.t = super.t;
            this.af.u = super.u;
        }
    }
    
    void if(final long n) {
        if (this.ab == 0 && this.ag != null && this.ag.byte) {
            this.ag.if(n);
        }
        else if (this.ab > 0 && this.af != null && this.af.byte) {
            this.af.if(n);
        }
        super.goto = true;
    }
    
    public boolean a(final long n) {
        boolean a = false;
        final boolean do1 = super.do;
        super.do = false;
        if (this.ad.k != 0) {
            this.ad = this.ad.try[0];
        }
        if (!super.byte && ((this.ad.new && super.try) || this.ad.case)) {
            this.af = this.ad.a(this.af);
            this.af.t = super.t;
            this.af.goto = super.goto;
            super.s = this.af.s;
            super.r = this.af.r;
            super.x = this.af.x;
            super.j = true;
            super.byte = true;
            this.ah = true;
            super.do = true;
            super.v = this.af.v;
            super.i = this.af.i;
        }
        if (!super.goto) {
            return false;
        }
        if (super.goto && super.byte) {
            a = this.af.a(n);
        }
        if (this.af != null) {
            if (!this.af.byte) {
                this.af.byte = this.ad.case;
                return true;
            }
            super.else = true;
        }
        return (a & super.for) | do1;
    }
    
    public void a(final char[] array, final bi bi) {
        if (a.a.a.a.i.do(array, an.ae) == 0) {
            super.t = az.a(bi);
            if (this.ag != null) {
                this.ag.if(super.t);
            }
            if (this.af != null) {
                this.af.if(super.t);
            }
            super.do = true;
        }
    }
    
    public void for() {
        if (super.for) {
            this.af.for();
        }
    }
    
    public void if() {
        if (this.ag != null) {
            this.ag.if();
            this.ag = null;
        }
        if (this.af != null) {
            this.af.if();
            this.af = null;
        }
        super.x = null;
    }
}
