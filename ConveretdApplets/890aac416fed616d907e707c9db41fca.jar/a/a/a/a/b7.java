// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class b7 extends ap
{
    bp[] aq;
    int ai;
    aq an;
    aq at;
    aq[] aj;
    aq[] as;
    ap[] ao;
    ap[] am;
    int ap;
    boolean al;
    boolean ar;
    protected static char[] ak;
    
    static {
        b7.ak = new char[] { 'x', 'm', 'l', 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    public b7() {
        this.aq = null;
        this.ai = 0;
        this.an = null;
        this.at = null;
        this.ap = 0;
        this.al = false;
        this.ar = false;
    }
    
    public void a(final bh bh, final an b, final aq long1) {
        super.case = b7.ak;
        super.b = b;
        super.long = long1;
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("width") == 0) {
                super.s = Integer.valueOf(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("height") == 0) {
                super.r = Integer.valueOf(bh.new[i]);
            }
        }
        if (super.s > 0 && super.r > 0) {
            super.x = new int[super.s * super.r];
            for (int n = super.s * super.r, j = 0; j < n; ++j) {
                super.x[j] = 0;
            }
        }
        for (bh bh2 = bh.if; bh2 != null; bh2 = bh2.for) {
            if (bh2.a.toLowerCase().compareTo("source") == 0) {
                this.an = null;
                this.at = null;
                this.case();
                for (int k = 0; k < bh2.do; ++k) {
                    if (bh2.try[k].toLowerCase().compareTo("file") == 0) {
                        this.an = super.b.B.a(bh2.new[k], super.long, false, true, false);
                    }
                    else if (bh2.try[k].toLowerCase().compareTo("mask") == 0) {
                        this.at = super.b.B.a(bh2.new[k], super.long, false, true, false);
                    }
                }
                this.aj[this.ap] = this.an;
                this.as[this.ap] = this.at;
                this.ao[this.ap] = new ap();
                this.am[this.ap] = new ap();
                for (bh bh3 = bh2.if; bh3 != null; bh3 = bh3.for) {
                    if (bh3.a.toLowerCase().compareTo("rectangle") == 0) {
                        this.char();
                        bh bh4 = bh3.if;
                        this.aq[this.ai] = new bp();
                        this.aq[this.ai].for = this.ao[this.ap];
                        this.aq[this.ai].a = this.am[this.ap];
                        for (int n2 = 0; n2 < 4 && bh4 != null; bh4 = bh4.for, ++n2) {
                            if (bh4.a.toLowerCase().compareTo("vertex") == 0) {
                                for (int l = 0; l < bh4.do; ++l) {
                                    if (bh4.try[l].toLowerCase().compareTo("srcx") == 0) {
                                        this.aq[this.ai].if[n2].do = Integer.valueOf(bh4.new[l]);
                                    }
                                    else if (bh4.try[l].toLowerCase().compareTo("srcy") == 0) {
                                        this.aq[this.ai].if[n2].a = Integer.valueOf(bh4.new[l]);
                                    }
                                    else if (bh4.try[l].toLowerCase().compareTo("destx") == 0) {
                                        this.aq[this.ai].if[n2].int = Integer.valueOf(bh4.new[l]);
                                    }
                                    else if (bh4.try[l].toLowerCase().compareTo("desty") == 0) {
                                        this.aq[this.ai].if[n2].for = Integer.valueOf(bh4.new[l]);
                                    }
                                }
                            }
                        }
                        ++this.ai;
                    }
                }
                ++this.ap;
            }
        }
        this.a(0L);
    }
    
    public ap a(final ap ap, final aq aq) {
        return null;
    }
    
    private void char() {
        if (this.aq == null || this.aq.length == this.ai) {
            final bp[] aq = new bp[this.ai + 10];
            for (int i = 0; i < this.ai; ++i) {
                aq[i] = this.aq[i];
            }
            this.aq = aq;
        }
    }
    
    private void case() {
        if (this.aq == null || this.aq.length == this.ai) {
            final ap[] ao = new ap[this.ap + 10];
            final ap[] am = new ap[this.ap + 10];
            final aq[] aj = new aq[this.ap + 10];
            final aq[] as = new aq[this.ap + 10];
            for (int i = 0; i < this.ai; ++i) {
                ao[i] = this.ao[i];
                am[i] = this.am[i];
                aj[i] = this.aj[i];
                as[i] = this.as[i];
            }
            this.ao = ao;
            this.am = am;
            this.aj = aj;
            this.as = as;
        }
    }
    
    public boolean a(final long n) {
        if (!super.byte) {
            boolean byte1 = true;
            for (int i = 0; i < this.ap; ++i) {
                if (this.aj[i] != null) {
                    byte1 &= this.aj[i].case;
                }
                if (this.as[i] != null) {
                    byte1 &= this.as[i].case;
                }
            }
            super.byte = byte1;
            if (super.byte) {
                for (int j = 0; j < this.ap; ++j) {
                    if (this.aj[j] != null) {
                        this.aj[j].a(this.ao[j]);
                        super.v |= this.ao[j].v;
                    }
                    if (this.as[j] != null) {
                        super.v = true;
                        this.ao[j].v = true;
                        this.as[j].a(this.am[j]);
                        try {
                            for (int n2 = this.ao[j].s * this.ao[j].r, k = 0; k < n2; ++k) {
                                this.ao[j].x[k] = ((this.ao[j].x[k] & 0xFFFFFF) | (this.am[j].x[k] & 0xFF) << 24);
                                if ((this.am[j].x[k] & 0xFF) != 0x0 || (this.am[j].x[k] & 0xFF) != 0xFF) {
                                    super.i = true;
                                    this.am[j].i = true;
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                for (int l = 0; l < this.ai; ++l) {
                    if (this.aq[l].for != null) {
                        if (super.i) {
                            this.aq[l].for.v = false;
                        }
                        super.b.W.a(this.aq[l].for, this, this.aq[l].if, false);
                    }
                }
                return super.long.case = true;
            }
        }
        return false;
    }
    
    public void if() {
        super.long.a();
        for (int i = 0; i < this.ap; ++i) {
            if (this.ao[i] != null) {
                this.ao[i].if();
            }
            if (this.am[i] != null) {
                this.am[i].if();
            }
            this.ao[i] = null;
            this.am[i] = null;
        }
    }
    
    public void int() {
        for (int i = 0; i < this.ap; ++i) {
            this.aj[i].a();
        }
    }
}
