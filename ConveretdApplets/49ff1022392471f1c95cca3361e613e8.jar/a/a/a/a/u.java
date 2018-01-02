// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class u extends bm
{
    boolean bB;
    boolean a1;
    boolean a3;
    float bD;
    ai bE;
    static final char[] bC;
    private static final char[] bF;
    
    static {
        bC = new char[] { 'v', 'o', 'l', 'u', 'm', 'e', '\0' };
        bF = new char[] { 's', 'o', 'u', 'n', 'd', '\0' };
    }
    
    u(final ac void1) {
        this.bB = true;
        this.a1 = true;
        this.a3 = false;
        this.bD = 1.0f;
        this.bE = null;
        super.void = void1;
        super.for = 4;
        super.byte = u.bF;
        super.try = true;
    }
    
    public void if(final a2 a2, final ae goto1, final v bh) {
        super.bh = bh;
        super.goto = goto1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.bB = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("play") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.a1 = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("volume") == 0) {
                this.bD = new Float(a2.new[i]) / 100.0f;
                if (this.bD < 0.0f) {
                    this.bD = 0.0f;
                }
                else if (this.bD > 1.0f) {
                    this.bD = 1.0f;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                this.bE = super.void.n.a(a2.new[i], super.goto, super.void, super.else, super.new);
            }
            else if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
        }
        if (this.bE != null) {
            this.bE.ao = this.a1;
            this.bE.al = this.bB;
            this.bE.an = this.bD;
            this.bE.a(this.bD);
        }
        this.a();
        this.a3 = true;
    }
    
    public void if() {
        if (this.bE != null) {
            this.bE.if();
        }
    }
    
    public boolean a(final long n) {
        if (this.bE != null) {
            this.bE.else = super.else;
            this.bE.a(n);
        }
        return false;
    }
    
    void int(final long n) {
        super.else = true;
        if (this.bE != null) {
            this.bE.do(n);
        }
    }
    
    public void e() {
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, ac.W) == 0) {
            final boolean a4 = al.a(a3);
            if (this.a1 == a4) {
                return;
            }
            this.a1 = a4;
            if (this.a1) {
                this.bE.void();
            }
            else {
                this.bE.c();
            }
        }
        else if (g.if(array, ac.i) == 0) {
            this.a1 = false;
            this.bE.b();
        }
        else if (g.if(array, ac.new) == 0) {
            if (!this.a1) {
                return;
            }
            this.a1 = false;
            this.bE.c();
        }
        else if (g.if(array, ac.f) == 0) {
            this.bB = al.a(a3);
            this.bE.for(this.bB);
        }
        else if (g.if(array, u.bC) == 0) {
            this.bD = al.do(a3) / 100.0f;
            if (this.bD < 0.0f) {
                this.bD = 0.0f;
            }
            else if (this.bD > 1.0f) {
                this.bD = 1.0f;
            }
            this.bE.a(this.bD);
        }
    }
    
    public a3 a(final char[] array) {
        if (g.if(array, u.bC) == 0) {
            super.int.char = 3;
            super.int.else = this.bD * 100.0f;
            return super.int;
        }
        if (g.if(array, ac.W) == 0) {
            super.int.char = 1;
            super.int.long = this.a1;
            return super.int;
        }
        if (g.if(array, ac.f) == 0) {
            super.int.char = 1;
            super.int.long = this.bB;
            return super.int;
        }
        return ac.a(super.int);
    }
}
