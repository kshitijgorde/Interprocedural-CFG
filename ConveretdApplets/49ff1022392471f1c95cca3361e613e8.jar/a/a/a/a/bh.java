// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bh extends p
{
    aq[] cF;
    aq[] cI;
    float cG;
    boolean cE;
    ae cH;
    protected char[] cJ;
    
    public bh() {
        this.cG = 0.0f;
        this.cE = false;
        this.cH = null;
        this.cJ = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'P', 'o', 'i', 'n', 't', '\0' };
    }
    
    public void a(final ac void1, final float cj, final ae goto1, final f cl, final m ck) {
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / super.cj;
        super.goto = goto1;
        super.cl = cl;
        super.for = 2;
        super.ck = ck;
        super.int = new a3();
        super.byte = this.cJ;
        super.try = false;
    }
    
    public void if() {
        this.cF[0] = null;
        this.cI[0] = null;
        this.cF = null;
        this.cI = null;
        if (super.cp != null) {
            super.cp.if();
        }
        super.cp = null;
    }
    
    public void int(final a2 a2) {
        float n = 0.0f;
        float n2 = 0.0f;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.do = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("size") == 0) {
                if (a2.new[i].trim().endsWith("%")) {
                    this.cE = true;
                    this.cG = new Float(a2.new[i].trim().replace('%', '0')) / 10.0f;
                }
                else {
                    this.cG = new Float(a2.new[i]);
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                super.cr = ac.a(a2.new[i]);
                if ((super.cr & 0xFF000000) == 0x0) {
                    super.cr |= 0xFF000000;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("display") == 0) {
                if (a2.new[i].compareTo("true") == 0) {
                    super.cw = true;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("image") == 0) {
                super.cp = new ad();
                super.cp.k = true;
                this.cH = super.void.w.a(a2.new[i], super.goto, false, true, false);
                super.ck.a(super.cp);
            }
        }
        this.a();
        this.cF = new aq[1];
        this.cI = new aq[1];
        this.cF[0] = new aq();
        this.cI[0] = new aq();
        this.cF[0].try = (float)(Math.cos(n) * Math.cos(n2));
        this.cF[0].if = (float)(Math.sin(n) * Math.cos(n2));
        this.cF[0].byte = (float)Math.sin(n2);
        this.cI[0].try = this.cF[0].try;
        this.cI[0].if = this.cF[0].if;
        this.cI[0].byte = this.cF[0].byte;
        this.try(a2);
    }
    
    void a(final float[][] array) {
        super.void.a(array, this.cI[0], this.cF[0]);
    }
    
    void if(final ab ab) {
        if (!super.do) {
            return;
        }
        ap.a(this.cF, 1, super.cl, super.cl.et >> 1, super.cl.ey >> 1);
        if (!this.cF[0].new) {
            this.a(ab, false);
            return;
        }
        int n;
        if (this.cE) {
            n = (int)(Math.sqrt(super.cl.et * super.cl.et + super.cl.ey * super.cl.ey) * this.cG * 0.005);
        }
        else {
            n = (int)(this.cG * 0.5);
        }
        if (this.cF[0].int + n > ab.goto && this.cF[0].int - n < ab.goto && this.cF[0].for + n > ab.else && this.cF[0].for - n < ab.else) {
            this.a(ab, true);
        }
        else {
            this.a(ab, false);
        }
    }
    
    public boolean a(final long n) {
        boolean a = false;
        final boolean if1 = super.if;
        super.if = false;
        if (!super.cp.try && this.cH.case) {
            super.cp = this.cH.a(super.cp);
            super.try = true;
            super.if = true;
            if (super.else) {
                super.cp.if(n);
            }
        }
        if (!super.else) {
            return false;
        }
        if (super.else && super.try) {
            a = super.cp.a(n);
        }
        return (a & super.do) | if1;
    }
    
    boolean if(final int n) {
        return super.cp == null || super.cp.try;
    }
    
    public void new(final boolean b) {
        if (super.cw && super.do) {
            ap.a(this.cF, 1, super.cl, super.cl.et >> 1, super.cl.ey >> 1);
            if (super.cp.try && this.cF[0].new) {
                super.cp.for();
                ap.a(super.cp, super.cl.em, (int)(this.cF[0].int - (super.cp.i >> 1) + super.cl.em.h), (int)(this.cF[0].for - (super.cp.t >> 1) + super.cl.em.g), super.cp.i, super.cp.t);
            }
        }
    }
    
    void new(final long n) {
        super.else = true;
        if (super.cp != null) {
            super.cp.if(n);
        }
    }
}
