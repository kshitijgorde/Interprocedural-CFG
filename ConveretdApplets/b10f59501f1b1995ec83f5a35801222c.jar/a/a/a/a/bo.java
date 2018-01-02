// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bo extends ad
{
    a9[] ap;
    int ah;
    ae am;
    ae as;
    ae[] ai;
    ae[] ar;
    ad[] an;
    ad[] al;
    int ao;
    boolean ak;
    boolean aq;
    protected static char[] aj;
    
    static {
        bo.aj = new char[] { 'x', 'm', 'l', 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    public bo() {
        this.ap = null;
        this.ah = 0;
        this.am = null;
        this.as = null;
        this.ao = 0;
        this.ak = false;
        this.aq = false;
    }
    
    public void a(final a2 a2, final ac b, final ae long1) {
        super.case = bo.aj;
        super.b = b;
        super.long = long1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.j = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.u = Integer.valueOf(a2.new[i]);
            }
        }
        if (super.j > 0 && super.u > 0) {
            super.q = new int[super.j * super.u];
            for (int n = super.j * super.u, j = 0; j < n; ++j) {
                super.q[j] = 0;
            }
        }
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("source") == 0) {
                this.am = null;
                this.as = null;
                this.goto();
                for (int k = 0; k < a3.do; ++k) {
                    if (a3.try[k].toLowerCase().compareTo("file") == 0) {
                        this.am = super.b.A.a(a3.new[k], super.long, false, true, false);
                    }
                    else if (a3.try[k].toLowerCase().compareTo("mask") == 0) {
                        this.as = super.b.A.a(a3.new[k], super.long, false, true, false);
                    }
                }
                this.ai[this.ao] = this.am;
                this.ar[this.ao] = this.as;
                this.an[this.ao] = new ad();
                this.al[this.ao] = new ad();
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for) {
                    if (a4.a.toLowerCase().compareTo("rectangle") == 0) {
                        this.long();
                        a2 a5 = a4.if;
                        this.ap[this.ah] = new a9();
                        this.ap[this.ah].for = this.an[this.ao];
                        this.ap[this.ah].a = this.al[this.ao];
                        for (int n2 = 0; n2 < 4 && a5 != null; a5 = a5.for, ++n2) {
                            if (a5.a.toLowerCase().compareTo("vertex") == 0) {
                                for (int l = 0; l < a5.do; ++l) {
                                    if (a5.try[l].toLowerCase().compareTo("srcx") == 0) {
                                        this.ap[this.ah].if[n2].do = Integer.valueOf(a5.new[l]);
                                    }
                                    else if (a5.try[l].toLowerCase().compareTo("srcy") == 0) {
                                        this.ap[this.ah].if[n2].a = Integer.valueOf(a5.new[l]);
                                    }
                                    else if (a5.try[l].toLowerCase().compareTo("destx") == 0) {
                                        this.ap[this.ah].if[n2].int = Integer.valueOf(a5.new[l]);
                                    }
                                    else if (a5.try[l].toLowerCase().compareTo("desty") == 0) {
                                        this.ap[this.ah].if[n2].for = Integer.valueOf(a5.new[l]);
                                    }
                                }
                            }
                        }
                        ++this.ah;
                    }
                }
                ++this.ao;
            }
        }
        this.a(0L);
    }
    
    public ad a(final ad ad, final ae ae) {
        return null;
    }
    
    private void long() {
        if (this.ap == null || this.ap.length == this.ah) {
            final a9[] ap = new a9[this.ah + 10];
            for (int i = 0; i < this.ah; ++i) {
                ap[i] = this.ap[i];
            }
            this.ap = ap;
        }
    }
    
    private void goto() {
        if (this.ap == null || this.ap.length == this.ah) {
            final ad[] an = new ad[this.ao + 10];
            final ad[] al = new ad[this.ao + 10];
            final ae[] ai = new ae[this.ao + 10];
            final ae[] ar = new ae[this.ao + 10];
            for (int i = 0; i < this.ah; ++i) {
                an[i] = this.an[i];
                al[i] = this.al[i];
                ai[i] = this.ai[i];
                ar[i] = this.ar[i];
            }
            this.an = an;
            this.al = al;
            this.ai = ai;
            this.ar = ar;
        }
    }
    
    public boolean a(final long n) {
        if (!super.byte) {
            boolean byte1 = true;
            for (int i = 0; i < this.ao; ++i) {
                if (this.ai[i] != null) {
                    byte1 &= this.ai[i].case;
                }
                if (this.ar[i] != null) {
                    byte1 &= this.ar[i].case;
                }
            }
            super.byte = byte1;
            if (super.byte) {
                for (int j = 0; j < this.ao; ++j) {
                    if (this.ai[j] != null) {
                        this.ai[j].a(this.an[j]);
                        super.v |= this.an[j].v;
                    }
                    if (this.ar[j] != null) {
                        super.v = true;
                        this.an[j].v = true;
                        this.ar[j].a(this.al[j]);
                        try {
                            for (int n2 = this.an[j].j * this.an[j].u, k = 0; k < n2; ++k) {
                                this.an[j].q[k] = ((this.an[j].q[k] & 0xFFFFFF) | (this.al[j].q[k] & 0xFF) << 24);
                                if ((this.al[j].q[k] & 0xFF) != 0x0 || (this.al[j].q[k] & 0xFF) != 0xFF) {
                                    super.p = true;
                                    this.al[j].p = true;
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                for (int l = 0; l < this.ah; ++l) {
                    if (this.ap[l].for != null) {
                        if (super.p) {
                            this.ap[l].for.v = false;
                        }
                        super.b.V.a(this.ap[l].for, this, this.ap[l].if, false);
                    }
                }
                return super.long.case = true;
            }
        }
        return false;
    }
    
    public void if() {
        super.long.a();
        for (int i = 0; i < this.ao; ++i) {
            if (this.an[i] != null) {
                this.an[i].if();
            }
            if (this.al[i] != null) {
                this.al[i].if();
            }
            this.an[i] = null;
            this.al[i] = null;
        }
    }
    
    public void int() {
        for (int i = 0; i < this.ao; ++i) {
            this.ai[i].a();
        }
    }
}
