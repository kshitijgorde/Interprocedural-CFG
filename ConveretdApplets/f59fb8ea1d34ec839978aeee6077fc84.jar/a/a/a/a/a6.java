// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a6 extends f
{
    boolean dd;
    boolean c7;
    boolean da;
    boolean dg;
    float db;
    aw df;
    a4 dc;
    a4 de;
    a4 c6;
    float[][] c9;
    static final char[] c8;
    
    static {
        c8 = new char[] { 'v', 'o', 'l', 'u', 'm', 'e', '\0' };
    }
    
    public a6() {
        this.dd = true;
        this.c7 = true;
        this.da = false;
        this.dg = false;
        this.db = 1.0f;
        this.df = null;
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t t) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / ca;
        super.long = long1;
        super.cC = cc;
        super.int = 4;
        super.case = "sound\u0000".toCharArray();
    }
    
    public void int(final bh bh) {
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("loop") == 0) {
                if (bh.new[i].toLowerCase().compareTo("false") == 0) {
                    this.dd = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("play") == 0) {
                if (bh.new[i].toLowerCase().compareTo("false") == 0) {
                    this.c7 = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("pan") == 0) {
                super.cE = new Float(bh.new[i]) * super.cA;
                this.dg = true;
            }
            else if (bh.try[i].toLowerCase().compareTo("tilt") == 0) {
                super.cx = new Float(bh.new[i]) * super.cA;
                this.dg = true;
            }
            else if (bh.try[i].toLowerCase().compareTo("volume") == 0) {
                this.db = new Float(bh.new[i]) / 100.0f;
                if (this.db < 0.0f) {
                    this.db = 0.0f;
                }
                else if (this.db > 1.0f) {
                    this.db = 1.0f;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("file") == 0) {
                this.df = super.b.s.a(bh.new[i], super.long, super.b, super.goto, super.try);
            }
            else if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
            }
        }
        if (this.df != null) {
            this.df.ay = this.c7;
            this.df.av = this.dd;
            this.df.a(this.db);
        }
        this.a();
        this.dc = new a4();
        this.de = new a4();
        this.c6 = new a4();
        this.c9 = super.b.if();
        this.dc.byte = (float)Math.sin(super.cx);
        this.dc.try = (float)(Math.cos(super.cE) * Math.cos(super.cx));
        this.dc.if = (float)(Math.sin(super.cE) * Math.cos(super.cx));
        this.c6.byte = this.dc.byte;
        this.c6.try = this.dc.try;
        this.c6.if = this.dc.if;
        this.da = true;
    }
    
    public void if() {
        if (this.df != null) {
            this.df.if();
        }
        this.df = null;
        if (this.c9 != null) {
            for (int i = 0; i < this.c9.length; ++i) {
                this.c9[i] = null;
            }
        }
        this.c9 = null;
    }
    
    public boolean a(final long n) {
        if (this.df != null) {
            this.df.goto = super.goto;
            this.df.a(n);
            this.l();
            super.byte = super.long.b;
        }
        return false;
    }
    
    void new(final long n) {
        super.goto = true;
        if (this.df != null) {
            this.df.do(n);
        }
    }
    
    void a(final float[][] array) {
        super.b.a(array, this.c6, this.dc);
    }
    
    void try(final boolean b) {
        this.df.int(b);
    }
    
    public void new(final boolean b) {
        if (this.dg && this.da) {
            super.b.a(-super.cC.cE, super.cC.cx, 0.0f, this.c9);
            super.b.a(this.c9, this.dc, this.de);
            this.df.a((float)Math.atan2(this.de.if, this.de.try), (float)Math.asin(this.de.byte));
        }
    }
    
    public void a(final char[] array, final bi bi) {
        if (i.do(array, an.M) == 0) {
            super.cE = az.do(bi);
            this.dc.byte = (float)Math.sin(super.cx);
            this.dc.try = (float)(Math.cos(super.cE) * Math.cos(super.cx));
            this.dc.if = (float)(Math.sin(super.cE) * Math.cos(super.cx));
            this.c6.byte = this.dc.byte;
            this.c6.try = this.dc.try;
            this.c6.if = this.dc.if;
        }
        else if (i.do(array, an.byte) == 0) {
            super.cx = az.do(bi);
            this.dc.byte = (float)Math.sin(super.cx);
            this.dc.try = (float)(Math.cos(super.cE) * Math.cos(super.cx));
            this.dc.if = (float)(Math.sin(super.cE) * Math.cos(super.cx));
            this.c6.byte = this.dc.byte;
            this.c6.try = this.dc.try;
            this.c6.if = this.dc.if;
        }
        else if (i.do(array, an.ae) == 0) {
            this.c7 = az.a(bi);
            if (this.c7) {
                this.df.else();
            }
            else {
                this.df.long();
            }
        }
        else if (i.do(array, an.m) == 0) {
            this.c7 = false;
            this.df.goto();
        }
        else if (i.do(array, an.try) == 0) {
            if (!this.c7) {
                return;
            }
            this.c7 = false;
            this.df.long();
        }
        else if (i.do(array, an.i) == 0) {
            this.dd = az.a(bi);
            this.df.for(this.dd);
        }
        else if (i.do(array, a6.c8) == 0) {
            this.db = az.do(bi) / 100.0f;
            if (this.db < 0.0f) {
                this.db = 0.0f;
            }
            else if (this.db > 1.0f) {
                this.db = 1.0f;
            }
            this.df.a(this.db);
        }
    }
    
    public bi a(final char[] array) {
        if (i.do(array, an.M) == 0) {
            super.new.char = 3;
            super.new.else = super.cE;
            return super.new;
        }
        if (i.do(array, an.byte) == 0) {
            super.new.char = 3;
            super.new.else = super.cx;
            return super.new;
        }
        if (i.do(array, a6.c8) == 0) {
            super.new.char = 3;
            super.new.else = this.db * 100.0f;
            return super.new;
        }
        if (i.do(array, an.ae) == 0) {
            super.new.char = 1;
            super.new.long = this.c7;
            return super.new;
        }
        if (i.do(array, an.i) == 0) {
            super.new.char = 1;
            super.new.long = this.dd;
            return super.new;
        }
        return null;
    }
}
