// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bo extends ad
{
    a9[] ag;
    int Z;
    ac void;
    ae goto;
    ae ae;
    ae aj;
    ae[] aa;
    ae[] ai;
    ad[] ad;
    ad[] ac;
    int af;
    boolean ab;
    boolean ah;
    protected static char[] r;
    
    static {
        bo.r = new char[] { 'x', 'm', 'l', 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    public bo() {
        this.ag = null;
        this.Z = 0;
        this.ae = null;
        this.aj = null;
        this.af = 0;
        this.ab = false;
        this.ah = false;
    }
    
    public void a(final a2 a2, final ac void1, final ae goto1) {
        super.byte = bo.r;
        this.void = void1;
        this.goto = goto1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.i = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.t = Integer.valueOf(a2.new[i]);
            }
        }
        if (super.i > 0 && super.t > 0) {
            super.p = new int[super.i * super.t];
            for (int n = super.i * super.t, j = 0; j < n; ++j) {
                super.p[j] = 0;
            }
        }
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("source") == 0) {
                this.ae = null;
                this.aj = null;
                this.goto();
                for (int k = 0; k < a3.do; ++k) {
                    if (a3.try[k].toLowerCase().compareTo("file") == 0) {
                        this.ae = this.void.w.a(a3.new[k], this.goto, false, true, false);
                    }
                    else if (a3.try[k].toLowerCase().compareTo("mask") == 0) {
                        this.aj = this.void.w.a(a3.new[k], this.goto, false, true, false);
                    }
                }
                this.aa[this.af] = this.ae;
                this.ai[this.af] = this.aj;
                this.ad[this.af] = new ad();
                this.ac[this.af] = new ad();
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for) {
                    if (a4.a.toLowerCase().compareTo("rectangle") == 0) {
                        this.long();
                        a2 a5 = a4.if;
                        this.ag[this.Z] = new a9();
                        this.ag[this.Z].for = this.ad[this.af];
                        this.ag[this.Z].a = this.ac[this.af];
                        for (int n2 = 0; n2 < 4 && a5 != null; a5 = a5.for, ++n2) {
                            if (a5.a.toLowerCase().compareTo("vertex") == 0) {
                                for (int l = 0; l < a5.do; ++l) {
                                    if (a5.try[l].toLowerCase().compareTo("srcx") == 0) {
                                        this.ag[this.Z].if[n2].do = Integer.valueOf(a5.new[l]);
                                    }
                                    else if (a5.try[l].toLowerCase().compareTo("srcy") == 0) {
                                        this.ag[this.Z].if[n2].a = Integer.valueOf(a5.new[l]);
                                    }
                                    else if (a5.try[l].toLowerCase().compareTo("destx") == 0) {
                                        this.ag[this.Z].if[n2].int = Integer.valueOf(a5.new[l]);
                                    }
                                    else if (a5.try[l].toLowerCase().compareTo("desty") == 0) {
                                        this.ag[this.Z].if[n2].for = Integer.valueOf(a5.new[l]);
                                    }
                                }
                            }
                        }
                        ++this.Z;
                    }
                }
                ++this.af;
            }
        }
        this.a(0L);
    }
    
    public ad a(final ad ad, final ae ae) {
        return null;
    }
    
    private void long() {
        if (this.ag == null || this.ag.length == this.Z) {
            final a9[] ag = new a9[this.Z + 10];
            for (int i = 0; i < this.Z; ++i) {
                ag[i] = this.ag[i];
            }
            this.ag = ag;
        }
    }
    
    private void goto() {
        if (this.ag == null || this.ag.length == this.Z) {
            final ad[] ad = new ad[this.af + 10];
            final ad[] ac = new ad[this.af + 10];
            final ae[] aa = new ae[this.af + 10];
            final ae[] ai = new ae[this.af + 10];
            for (int i = 0; i < this.Z; ++i) {
                ad[i] = this.ad[i];
                ac[i] = this.ac[i];
                aa[i] = this.aa[i];
                ai[i] = this.ai[i];
            }
            this.ad = ad;
            this.ac = ac;
            this.aa = aa;
            this.ai = ai;
        }
    }
    
    public boolean a(final long n) {
        if (!super.try) {
            boolean try1 = true;
            for (int i = 0; i < this.af; ++i) {
                if (this.aa[i] != null) {
                    try1 &= this.aa[i].case;
                }
                if (this.ai[i] != null) {
                    try1 &= this.ai[i].case;
                }
            }
            super.try = try1;
            if (super.try) {
                for (int j = 0; j < this.af; ++j) {
                    if (this.aa[j] != null) {
                        this.aa[j].a(this.ad[j]);
                        super.u |= this.ad[j].u;
                    }
                    if (this.ai[j] != null) {
                        super.u = true;
                        this.ad[j].u = true;
                        this.ai[j].a(this.ac[j]);
                        try {
                            for (int n2 = this.ad[j].i * this.ad[j].t, k = 0; k < n2; ++k) {
                                this.ad[j].p[k] = ((this.ad[j].p[k] & 0xFFFFFF) | (this.ac[j].p[k] & 0xFF) << 24);
                                if ((this.ac[j].p[k] & 0xFF) != 0x0 || (this.ac[j].p[k] & 0xFF) != 0xFF) {
                                    super.o = true;
                                    this.ac[j].o = true;
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                for (int l = 0; l < this.Z; ++l) {
                    if (this.ag[l].for != null) {
                        this.void.P.a(this.ag[l].for, this, this.ag[l].if, false);
                    }
                }
                return this.goto.case = true;
            }
        }
        return false;
    }
    
    public void if() {
        this.goto.a();
        for (int i = 0; i < this.af; ++i) {
            if (this.ad[i] != null) {
                this.ad[i].if();
            }
            if (this.ac[i] != null) {
                this.ac[i].if();
            }
            this.ad[i] = null;
            this.ac[i] = null;
        }
    }
    
    public void int() {
        for (int i = 0; i < this.af; ++i) {
            this.aa[i].a();
        }
    }
}
