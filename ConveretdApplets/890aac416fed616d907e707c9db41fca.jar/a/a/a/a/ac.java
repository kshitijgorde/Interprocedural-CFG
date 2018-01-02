// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ac extends b5
{
    boolean bO;
    boolean bP;
    boolean bS;
    float bR;
    aw bT;
    static final char[] bQ;
    private static final char[] bU;
    
    static {
        bQ = new char[] { 'v', 'o', 'l', 'u', 'm', 'e', '\0' };
        bU = new char[] { 's', 'o', 'u', 'n', 'd', '\0' };
    }
    
    public ac() {
        this.bO = true;
        this.bP = true;
        this.bS = false;
        this.bR = 1.0f;
        this.bT = null;
    }
    
    public void a(final an b, final bh bh, final aq long1, final ae bs) {
        super.b = b;
        super.int = 4;
        super.case = ac.bU;
        super.byte = true;
        super.bs = bs;
        super.long = long1;
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("loop") == 0) {
                if (bh.new[i].toLowerCase().compareTo("false") == 0) {
                    this.bO = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("play") == 0) {
                if (bh.new[i].toLowerCase().compareTo("false") == 0) {
                    this.bP = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("volume") == 0) {
                this.bR = new Float(bh.new[i]) / 100.0f;
                if (this.bR < 0.0f) {
                    this.bR = 0.0f;
                }
                else if (this.bR > 1.0f) {
                    this.bR = 1.0f;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("file") == 0) {
                this.bT = super.b.s.a(bh.new[i], super.long, super.b, super.goto, super.try);
            }
            else if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
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
    
    public void a(final char[] array, final bi bi) {
        if (i.do(array, an.ae) == 0) {
            final boolean a = az.a(bi);
            if (this.bP == a) {
                return;
            }
            this.bP = a;
            if (this.bP) {
                this.bT.else();
            }
            else {
                this.bT.long();
            }
        }
        else if (i.do(array, an.m) == 0) {
            this.bP = false;
            this.bT.goto();
        }
        else if (i.do(array, an.try) == 0) {
            if (!this.bP) {
                return;
            }
            this.bP = false;
            this.bT.long();
        }
        else if (i.do(array, an.i) == 0) {
            this.bO = az.a(bi);
            this.bT.for(this.bO);
        }
        else if (i.do(array, ac.bQ) == 0) {
            this.bR = az.do(bi) / 100.0f;
            if (this.bR < 0.0f) {
                this.bR = 0.0f;
            }
            else if (this.bR > 1.0f) {
                this.bR = 1.0f;
            }
            this.bT.a(this.bR);
        }
    }
    
    public bi a(final char[] array) {
        if (i.do(array, ac.bQ) == 0) {
            super.new.char = 3;
            super.new.else = this.bR * 100.0f;
            return super.new;
        }
        if (i.do(array, an.ae) == 0) {
            super.new.char = 1;
            super.new.long = this.bP;
            return super.new;
        }
        if (i.do(array, an.i) == 0) {
            super.new.char = 1;
            super.new.long = this.bO;
            return super.new;
        }
        return an.a(super.new);
    }
}
