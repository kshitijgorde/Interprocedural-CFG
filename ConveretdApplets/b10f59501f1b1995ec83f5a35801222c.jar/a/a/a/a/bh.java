// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bh extends p
{
    aq[] cX;
    aq[] c0;
    float cY;
    boolean cW;
    ae cZ;
    protected char[] c1;
    
    public bh() {
        this.cY = 0.0f;
        this.cW = false;
        this.cZ = null;
        this.c1 = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'P', 'o', 'i', 'n', 't', '\0' };
    }
    
    public void a(final ac b, final float cz, final ae long1, final f cb, final m ca) {
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / super.cz;
        super.long = long1;
        super.cB = cb;
        super.int = 2;
        super.cA = ca;
        super.new = new a3();
        super.case = this.c1;
        super.byte = false;
    }
    
    public void if() {
        if (this.cX != null && this.cX.length >= 1) {
            this.cX[0] = null;
        }
        if (this.c0 != null && this.c0.length >= 1) {
            this.c0[0] = null;
        }
        this.cX = null;
        this.c0 = null;
        if (super.cF != null) {
            super.cF.if();
        }
        super.cF = null;
        this.c1 = null;
    }
    
    public void int(final a2 a2) {
        float n = 0.0f;
        float n2 = 0.0f;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("size") == 0) {
                if (a2.new[i].trim().endsWith("%")) {
                    this.cW = true;
                    this.cY = new Float(a2.new[i].trim().replace('%', '0')) / 10.0f;
                }
                else {
                    this.cY = new Float(a2.new[i]);
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                super.cH = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("display") == 0) {
                if (a2.new[i].compareTo("true") == 0) {
                    super.cN = true;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("image") == 0) {
                super.cF = new ad();
                super.cF.l = true;
                this.cZ = super.b.A.a(a2.new[i], super.long, false, true, false);
                super.cA.a(super.cF);
            }
        }
        this.a();
        this.cX = new aq[1];
        this.c0 = new aq[1];
        this.cX[0] = new aq();
        this.c0[0] = new aq();
        this.cX[0].try = (float)(Math.cos(n) * Math.cos(n2));
        this.cX[0].if = (float)(Math.sin(n) * Math.cos(n2));
        this.cX[0].byte = (float)Math.sin(n2);
        this.c0[0].try = this.cX[0].try;
        this.c0[0].if = this.cX[0].if;
        this.c0[0].byte = this.cX[0].byte;
        this.try(a2);
    }
    
    void a(final float[][] array) {
        super.b.a(array, this.c0[0], this.cX[0]);
    }
    
    void if(final ab ab) {
        if (!super.for) {
            return;
        }
        ap.a(this.cX, 1, super.cB, super.cB.eL >> 1, super.cB.eQ >> 1);
        if (!this.cX[0].new) {
            this.a(ab, false);
            return;
        }
        int n;
        if (this.cW) {
            n = (int)(Math.sqrt(super.cB.eL * super.cB.eL + super.cB.eQ * super.cB.eQ) * this.cY * 0.005);
        }
        else {
            n = (int)(this.cY * 0.5);
        }
        if (this.cX[0].int + n > ab.goto && this.cX[0].int - n < ab.goto && this.cX[0].for + n > ab.else && this.cX[0].for - n < ab.else) {
            this.a(ab, true);
        }
        else {
            this.a(ab, false);
        }
    }
    
    public boolean a(final long n) {
        boolean a = false;
        final boolean do1 = super.do;
        super.do = false;
        if (!super.cF.byte && this.cZ.case) {
            super.cF = this.cZ.a(super.cF);
            super.byte = true;
            super.do = true;
            if (super.goto) {
                super.cF.if(n);
            }
        }
        this.n();
        if (!super.goto) {
            return false;
        }
        if (super.goto && super.byte) {
            a = super.cF.a(n);
        }
        return (a & super.for) | do1;
    }
    
    boolean if(final int n) {
        return super.cF == null || super.cF.byte;
    }
    
    public void new(final boolean b) {
        if (super.cN && super.for) {
            ap.a(this.cX, 1, super.cB, super.cB.eL >> 1, super.cB.eQ >> 1);
            if (super.cF.byte && this.cX[0].new) {
                super.cF.for();
                ap.a(super.cF, super.cB.eE, (int)(this.cX[0].int - (super.cF.j >> 1) + super.cB.eE.i), (int)(this.cX[0].for - (super.cF.u >> 1) + super.cB.eE.h), super.cF.j, super.cF.u);
            }
        }
    }
    
    void new(final long n) {
        super.goto = true;
        if (super.cF != null) {
            super.cF.if(n);
        }
    }
}
