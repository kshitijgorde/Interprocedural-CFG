// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bh extends p
{
    aq[] cY;
    aq[] c1;
    float cZ;
    boolean cX;
    ae c0;
    protected char[] c2;
    
    public bh() {
        this.cZ = 0.0f;
        this.cX = false;
        this.c0 = null;
        this.c2 = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'P', 'o', 'i', 'n', 't', '\0' };
    }
    
    public void a(final ac b, final float ca, final ae long1, final f cc, final m cb) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.int = 2;
        super.cB = cb;
        super.new = new a3();
        super.case = this.c2;
        super.byte = false;
    }
    
    public void if() {
        if (this.cY != null && this.cY.length >= 1) {
            this.cY[0] = null;
        }
        if (this.c1 != null && this.c1.length >= 1) {
            this.c1[0] = null;
        }
        this.cY = null;
        this.c1 = null;
        if (super.cG != null) {
            super.cG.if();
        }
        super.cG = null;
        this.c2 = null;
    }
    
    public void int(final a2 a2) {
        float n = 0.0f;
        float n2 = 0.0f;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("size") == 0) {
                if (a2.new[i].trim().endsWith("%")) {
                    this.cX = true;
                    this.cZ = new Float(a2.new[i].trim().replace('%', '0')) / 10.0f;
                }
                else {
                    this.cZ = new Float(a2.new[i]);
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                super.cI = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("display") == 0) {
                if (a2.new[i].compareTo("true") == 0) {
                    super.cO = true;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("image") == 0) {
                super.cG = new ad();
                super.cG.t = true;
                this.c0 = super.b.B.a(a2.new[i], super.long, false, true, false);
                super.cB.a(super.cG);
            }
        }
        this.a();
        this.cY = new aq[1];
        this.c1 = new aq[1];
        this.cY[0] = new aq();
        this.c1[0] = new aq();
        this.cY[0].try = (float)(Math.cos(n) * Math.cos(n2));
        this.cY[0].if = (float)(Math.sin(n) * Math.cos(n2));
        this.cY[0].byte = (float)Math.sin(n2);
        this.c1[0].try = this.cY[0].try;
        this.c1[0].if = this.cY[0].if;
        this.c1[0].byte = this.cY[0].byte;
        this.try(a2);
    }
    
    void a(final float[][] array) {
        super.b.a(array, this.c1[0], this.cY[0]);
    }
    
    void if(final ab ab) {
        if (!super.for) {
            return;
        }
        ap.a(this.cY, 1, super.cC, super.cC.eM >> 1, super.cC.eR >> 1);
        if (!this.cY[0].new) {
            this.a(ab, false);
            return;
        }
        int n;
        if (this.cX) {
            n = (int)(Math.sqrt(super.cC.eM * super.cC.eM + super.cC.eR * super.cC.eR) * this.cZ * 0.005);
        }
        else {
            n = (int)(this.cZ * 0.5);
        }
        if (this.cY[0].int + n > ab.goto && this.cY[0].int - n < ab.goto && this.cY[0].for + n > ab.else && this.cY[0].for - n < ab.else) {
            this.a(ab, true);
        }
        else {
            this.a(ab, false);
        }
    }
    
    public boolean a(final long n) {
        boolean a = false;
        final boolean do1 = super.do;
        if (this.c0.k > 0) {
            this.c0 = this.c0.try[0];
        }
        super.do = false;
        if (!super.cG.byte && this.c0.case) {
            super.cG = this.c0.a(super.cG);
            super.byte = true;
            super.do = true;
            if (super.goto) {
                super.cG.if(n);
            }
        }
        this.l();
        if (!super.goto) {
            return false;
        }
        if (super.goto && super.byte) {
            a = super.cG.a(n);
        }
        return (a & super.for) | do1;
    }
    
    boolean if(final int n) {
        return super.cG == null || super.cG.byte;
    }
    
    public void new(final boolean b) {
        if (super.cO && super.for) {
            ap.a(this.cY, 1, super.cC, super.cC.eM >> 1, super.cC.eR >> 1);
            if (super.cG.byte && this.cY[0].new) {
                super.cG.for();
                ap.a(super.cG, super.cC.eF, (int)(this.cY[0].int - (super.cG.s >> 1) + super.cC.eF.n), (int)(this.cY[0].for - (super.cG.r >> 1) + super.cC.eF.m), super.cG.s, super.cG.r);
            }
        }
    }
    
    void new(final long n) {
        super.goto = true;
        if (super.cG != null) {
            super.cG.if(n);
        }
    }
}
