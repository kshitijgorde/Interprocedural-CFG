// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class u extends bm
{
    boolean bN;
    boolean bO;
    boolean bR;
    float bQ;
    ai bS;
    static final char[] bP;
    private static final char[] bT;
    
    static {
        bP = new char[] { 'v', 'o', 'l', 'u', 'm', 'e', '\0' };
        bT = new char[] { 's', 'o', 'u', 'n', 'd', '\0' };
    }
    
    u(final ac b) {
        this.bN = true;
        this.bO = true;
        this.bR = false;
        this.bQ = 1.0f;
        this.bS = null;
        super.b = b;
        super.int = 4;
        super.case = u.bT;
        super.byte = true;
    }
    
    public void if(final a2 a2, final ae long1, final v br) {
        super.br = br;
        super.long = long1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.bN = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("play") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.bO = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("volume") == 0) {
                this.bQ = new Float(a2.new[i]) / 100.0f;
                if (this.bQ < 0.0f) {
                    this.bQ = 0.0f;
                }
                else if (this.bQ > 1.0f) {
                    this.bQ = 1.0f;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                this.bS = super.b.r.a(a2.new[i], super.long, super.b, super.goto, super.try);
            }
            else if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
        }
        if (this.bS != null) {
            this.bS.ax = this.bO;
            this.bS.au = this.bN;
            this.bS.aw = this.bQ;
            this.bS.a(this.bQ);
        }
        this.a();
        this.bR = true;
    }
    
    public void if() {
        if (this.bS != null) {
            this.bS.if();
        }
        this.bS = null;
    }
    
    public boolean a(final long n) {
        if (this.bS != null) {
            this.bS.goto = super.goto;
            this.bS.a(n);
            this.d();
            super.byte = super.long.b;
        }
        return false;
    }
    
    void int(final long n) {
        super.goto = true;
        if (this.bS != null) {
            this.bS.do(n);
        }
    }
    
    public void f() {
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.ac) == 0) {
            final boolean a4 = al.a(a3);
            if (this.bO == a4) {
                return;
            }
            this.bO = a4;
            if (this.bO) {
                this.bS.void();
            }
            else {
                this.bS.c();
            }
        }
        else if (g.do(array, ac.m) == 0) {
            this.bO = false;
            this.bS.b();
        }
        else if (g.do(array, ac.try) == 0) {
            if (!this.bO) {
                return;
            }
            this.bO = false;
            this.bS.c();
        }
        else if (g.do(array, ac.i) == 0) {
            this.bN = al.a(a3);
            this.bS.for(this.bN);
        }
        else if (g.do(array, u.bP) == 0) {
            this.bQ = al.do(a3) / 100.0f;
            if (this.bQ < 0.0f) {
                this.bQ = 0.0f;
            }
            else if (this.bQ > 1.0f) {
                this.bQ = 1.0f;
            }
            this.bS.a(this.bQ);
        }
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, u.bP) == 0) {
            super.new.char = 3;
            super.new.else = this.bQ * 100.0f;
            return super.new;
        }
        if (g.do(array, ac.ac) == 0) {
            super.new.char = 1;
            super.new.long = this.bO;
            return super.new;
        }
        if (g.do(array, ac.i) == 0) {
            super.new.char = 1;
            super.new.long = this.bN;
            return super.new;
        }
        return ac.a(super.new);
    }
}
