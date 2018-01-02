// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class as extends e
{
    boolean dc;
    boolean c6;
    boolean c9;
    boolean df;
    float da;
    ai de;
    aq db;
    aq dd;
    aq c5;
    float[][] c8;
    static final char[] c7;
    
    static {
        c7 = new char[] { 'v', 'o', 'l', 'u', 'm', 'e', '\0' };
    }
    
    public as() {
        this.dc = true;
        this.c6 = true;
        this.c9 = false;
        this.df = false;
        this.da = 1.0f;
        this.de = null;
    }
    
    public void a(final ac b, final float cz, final ae long1, final f cb, final m m) {
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / cz;
        super.long = long1;
        super.cB = cb;
        super.int = 4;
        super.case = "sound\u0000".toCharArray();
    }
    
    public void int(final a2 a2) {
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.dc = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("play") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.c6 = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                super.cD = new Float(a2.new[i]) * super.cz;
                this.df = true;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                super.cw = new Float(a2.new[i]) * super.cz;
                this.df = true;
            }
            else if (a2.try[i].toLowerCase().compareTo("volume") == 0) {
                this.da = new Float(a2.new[i]) / 100.0f;
                if (this.da < 0.0f) {
                    this.da = 0.0f;
                }
                else if (this.da > 1.0f) {
                    this.da = 1.0f;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                this.de = super.b.r.a(a2.new[i], super.long, super.b, super.goto, super.try);
            }
            else if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
        }
        if (this.de != null) {
            this.de.ax = this.c6;
            this.de.au = this.dc;
            this.de.a(this.da);
        }
        this.a();
        this.db = new aq();
        this.dd = new aq();
        this.c5 = new aq();
        this.c8 = super.b.if();
        this.db.byte = (float)Math.sin(super.cw);
        this.db.try = (float)(Math.cos(super.cD) * Math.cos(super.cw));
        this.db.if = (float)(Math.sin(super.cD) * Math.cos(super.cw));
        this.c5.byte = this.db.byte;
        this.c5.try = this.db.try;
        this.c5.if = this.db.if;
        this.c9 = true;
    }
    
    public void if() {
        if (this.de != null) {
            this.de.if();
        }
        this.de = null;
        if (this.c8 != null) {
            for (int i = 0; i < this.c8.length; ++i) {
                this.c8[i] = null;
            }
        }
        this.c8 = null;
    }
    
    public boolean a(final long n) {
        if (this.de != null) {
            this.de.goto = super.goto;
            this.de.a(n);
            this.n();
            super.byte = super.long.b;
        }
        return false;
    }
    
    void new(final long n) {
        super.goto = true;
        if (this.de != null) {
            this.de.do(n);
        }
    }
    
    void a(final float[][] array) {
        super.b.a(array, this.c5, this.db);
    }
    
    void try(final boolean b) {
        this.de.int(b);
    }
    
    public void new(final boolean b) {
        if (this.df && this.c9) {
            super.b.a(-super.cB.cD, super.cB.cw, 0.0f, this.c8);
            super.b.a(this.c8, this.db, this.dd);
            this.de.a((float)Math.atan2(this.dd.if, this.dd.try), (float)Math.asin(this.dd.byte));
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.L) == 0) {
            super.cD = al.do(a3);
            this.db.byte = (float)Math.sin(super.cw);
            this.db.try = (float)(Math.cos(super.cD) * Math.cos(super.cw));
            this.db.if = (float)(Math.sin(super.cD) * Math.cos(super.cw));
            this.c5.byte = this.db.byte;
            this.c5.try = this.db.try;
            this.c5.if = this.db.if;
        }
        else if (g.do(array, ac.byte) == 0) {
            super.cw = al.do(a3);
            this.db.byte = (float)Math.sin(super.cw);
            this.db.try = (float)(Math.cos(super.cD) * Math.cos(super.cw));
            this.db.if = (float)(Math.sin(super.cD) * Math.cos(super.cw));
            this.c5.byte = this.db.byte;
            this.c5.try = this.db.try;
            this.c5.if = this.db.if;
        }
        else if (g.do(array, ac.ac) == 0) {
            this.c6 = al.a(a3);
            if (this.c6) {
                this.de.void();
            }
            else {
                this.de.c();
            }
        }
        else if (g.do(array, ac.m) == 0) {
            this.c6 = false;
            this.de.b();
        }
        else if (g.do(array, ac.try) == 0) {
            if (!this.c6) {
                return;
            }
            this.c6 = false;
            this.de.c();
        }
        else if (g.do(array, ac.i) == 0) {
            this.dc = al.a(a3);
            this.de.for(this.dc);
        }
        else if (g.do(array, as.c7) == 0) {
            this.da = al.do(a3) / 100.0f;
            if (this.da < 0.0f) {
                this.da = 0.0f;
            }
            else if (this.da > 1.0f) {
                this.da = 1.0f;
            }
            this.de.a(this.da);
        }
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.L) == 0) {
            super.new.char = 3;
            super.new.else = super.cD;
            return super.new;
        }
        if (g.do(array, ac.byte) == 0) {
            super.new.char = 3;
            super.new.else = super.cw;
            return super.new;
        }
        if (g.do(array, as.c7) == 0) {
            super.new.char = 3;
            super.new.else = this.da * 100.0f;
            return super.new;
        }
        if (g.do(array, ac.ac) == 0) {
            super.new.char = 1;
            super.new.long = this.c6;
            return super.new;
        }
        if (g.do(array, ac.i) == 0) {
            super.new.char = 1;
            super.new.long = this.dc;
            return super.new;
        }
        return null;
    }
}
