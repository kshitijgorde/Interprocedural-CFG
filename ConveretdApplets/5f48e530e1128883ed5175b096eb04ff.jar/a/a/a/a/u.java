// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class u extends bm
{
    boolean bO;
    boolean bP;
    boolean bS;
    float bR;
    ai bT;
    static final char[] bQ;
    private static final char[] bU;
    
    static {
        bQ = new char[] { 'v', 'o', 'l', 'u', 'm', 'e', '\0' };
        bU = new char[] { 's', 'o', 'u', 'n', 'd', '\0' };
    }
    
    public u() {
        this.bO = true;
        this.bP = true;
        this.bS = false;
        this.bR = 1.0f;
        this.bT = null;
    }
    
    public void a(final ac b, final a2 a2, final ae long1, final v bs) {
        super.b = b;
        super.int = 4;
        super.case = u.bU;
        super.byte = true;
        super.bs = bs;
        super.long = long1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.bO = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("play") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.bP = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("volume") == 0) {
                this.bR = new Float(a2.new[i]) / 100.0f;
                if (this.bR < 0.0f) {
                    this.bR = 0.0f;
                }
                else if (this.bR > 1.0f) {
                    this.bR = 1.0f;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                this.bT = super.b.s.a(a2.new[i], super.long, super.b, super.goto, super.try);
            }
            else if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
        }
        if (this.bT != null) {
            this.bT.ay = this.bP;
            this.bT.av = this.bO;
            this.bT.ax = this.bR;
            this.bT.a(this.bR);
        }
        this.a();
        this.bS = true;
    }
    
    public void if() {
        if (this.bT != null) {
            this.bT.if();
        }
        this.bT = null;
    }
    
    public boolean a(final long n) {
        if (this.bT != null) {
            this.bT.goto = super.goto;
            this.bT.a(n);
            this.void();
            super.byte = super.long.b;
        }
        return false;
    }
    
    void int(final long n) {
        super.goto = true;
        if (this.bT != null) {
            this.bT.do(n);
        }
    }
    
    public void c() {
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.ae) == 0) {
            final boolean a4 = al.a(a3);
            if (this.bP == a4) {
                return;
            }
            this.bP = a4;
            if (this.bP) {
                this.bT.else();
            }
            else {
                this.bT.long();
            }
        }
        else if (g.do(array, ac.m) == 0) {
            this.bP = false;
            this.bT.goto();
        }
        else if (g.do(array, ac.try) == 0) {
            if (!this.bP) {
                return;
            }
            this.bP = false;
            this.bT.long();
        }
        else if (g.do(array, ac.i) == 0) {
            this.bO = al.a(a3);
            this.bT.for(this.bO);
        }
        else if (g.do(array, u.bQ) == 0) {
            this.bR = al.do(a3) / 100.0f;
            if (this.bR < 0.0f) {
                this.bR = 0.0f;
            }
            else if (this.bR > 1.0f) {
                this.bR = 1.0f;
            }
            this.bT.a(this.bR);
        }
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, u.bQ) == 0) {
            super.new.char = 3;
            super.new.else = this.bR * 100.0f;
            return super.new;
        }
        if (g.do(array, ac.ae) == 0) {
            super.new.char = 1;
            super.new.long = this.bP;
            return super.new;
        }
        if (g.do(array, ac.i) == 0) {
            super.new.char = 1;
            super.new.long = this.bO;
            return super.new;
        }
        return ac.a(super.new);
    }
}
