// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class p extends e
{
    boolean cN;
    protected char[] cO;
    protected char[] cP;
    protected char[] cG;
    protected char[] cR;
    protected char[] cL;
    protected char[] cQ;
    private static final char[] cK;
    protected boolean cJ;
    protected boolean cE;
    protected boolean cM;
    ad cF;
    int cH;
    protected int cI;
    
    static {
        cK = new char[] { 'b', 'o', 'r', 'd', 'e', 'r', 's', 'i', 'z', 'e', '\0' };
    }
    
    public p() {
        this.cN = false;
        this.cO = new char[1];
        this.cP = new char[1];
        this.cG = new char[1];
        this.cR = new char[1];
        this.cL = new char[1];
        this.cQ = new char[1];
        this.cJ = false;
        this.cE = false;
        this.cM = false;
        this.cF = null;
        this.cH = -1;
        this.cI = 1;
        super.byte = true;
    }
    
    void try(final a2 a2) {
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            final String lowerCase = a3.a.toLowerCase();
            if (a3.case != null && a3.case.do != null && a3.case.do.trim().length() != 0) {
                final String do1 = a3.case.do;
                if (lowerCase.compareTo("mouseclick") == 0) {
                    this.cP = do1.toCharArray();
                }
                else if (lowerCase.compareTo("mousepress") == 0) {
                    this.cO = do1.toCharArray();
                }
                else if (lowerCase.compareTo("mouseenter") == 0) {
                    this.cG = do1.toCharArray();
                }
                else if (lowerCase.compareTo("mouseleave") == 0 || lowerCase.compareTo("mouseexit") == 0) {
                    this.cR = do1.toCharArray();
                }
                else if (lowerCase.compareTo("mouserelease") == 0) {
                    this.cL = do1.toCharArray();
                }
                else if (lowerCase.compareTo("text") == 0) {
                    this.cQ = do1.toCharArray();
                }
            }
        }
    }
    
    void a(final ab ab, final boolean ce) {
        this.cE = ce;
        if (this.cE && !ab.i && ab.if != 4) {
            ab.for = 0;
        }
        if (this.cJ && ab.if == 1) {
            ab.a(this.cL, true);
            super.cA.a(super.f, 3);
            ab.i = true;
            this.cJ = false;
        }
        else if (!ab.i && this.cE) {
            if (ab.if == 2) {
                ab.a(this.cP, true);
                super.cA.a(super.f, 4);
                ab.i = true;
            }
            else if (ab.if == 0) {
                ab.a(this.cO, true);
                super.cA.a(super.f, 2);
                ab.i = true;
                this.cJ = true;
            }
            else if (!this.cM) {
                ab.a(this.cG, true);
                super.cA.a(super.f, 0);
                ab.i = true;
                this.cM = true;
            }
            else if (this.cM) {
                ab.i = true;
            }
        }
        else if (this.cM) {
            ab.a(this.cR, true);
            super.cA.a(super.f, 1);
            ab.i = true;
            this.cM = false;
        }
    }
    
    void a(final float[][] array) {
    }
    
    public void int(final a2 a2) {
    }
    
    protected void new(final a2 a2) {
    }
    
    protected void a(final ad ad, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = -(n6 >> 1);
        for (int n8 = n7 + n6, i = n7; i < n8; ++i) {
            for (int j = n7; j < n8; ++j) {
                ap.a(ad, n + i, n2 + j, n3 + i, n4 + j, n5);
            }
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.j) == 0) {
            this.cN = al.a(a3);
            super.do = true;
        }
        else if (g.do(array, ac.P) == 0) {
            super.for = al.a(a3);
            super.do = true;
        }
        else if (g.do(array, ac.x) == 0) {
            if (a3.char == 4) {
                this.cO = a3.int;
            }
        }
        else if (g.do(array, ac.c) == 0) {
            if (a3.char == 4) {
                this.cP = a3.int;
            }
        }
        else if (g.do(array, ac.long) == 0) {
            if (a3.char == 4) {
                this.cG = a3.int;
            }
        }
        else if (g.do(array, ac.I) == 0) {
            if (a3.char == 4) {
                this.cR = a3.int;
            }
        }
        else if (g.do(array, ac.D) == 0) {
            if (a3.char == 4) {
                this.cL = a3.int;
            }
        }
        else if (g.do(array, ac.o) == 0) {
            if (a3.char == 4) {
                this.cQ = a3.int;
            }
            super.do = true;
        }
        else if (g.do(array, ac.ai) == 0) {
            if (a3.char == 2) {
                this.cH = (int)a3.case;
            }
            super.do = true;
        }
        else if (g.do(array, p.cK) == 0) {
            if (a3.char == 2) {
                this.cI = (int)a3.case;
            }
            super.do = true;
        }
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.j) == 0) {
            super.new.char = 1;
            super.new.long = this.cN;
            return super.new;
        }
        if (g.do(array, ac.P) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (g.do(array, ac.x) == 0) {
            super.new.char = 4;
            super.new.int = this.cO;
            return super.new;
        }
        if (g.do(array, ac.c) == 0) {
            super.new.char = 4;
            super.new.int = this.cP;
            return super.new;
        }
        if (g.do(array, ac.long) == 0) {
            super.new.char = 4;
            super.new.int = this.cG;
            return super.new;
        }
        if (g.do(array, ac.I) == 0) {
            super.new.char = 4;
            super.new.int = this.cR;
            return super.new;
        }
        if (g.do(array, ac.D) == 0) {
            super.new.char = 4;
            super.new.int = this.cL;
            return super.new;
        }
        if (g.do(array, ac.o) == 0) {
            super.new.char = 4;
            super.new.int = this.cQ;
            return super.new;
        }
        if (g.do(array, ac.s) == 0) {
            super.new.char = 5;
            if (this.cF != null) {
                super.new.goto = 1;
                (super.new.a = new a3[1])[0] = new a3();
                super.new.a[0].char = 4;
                super.new.a[0].int = this.cF.f;
            }
            else {
                super.new.goto = 0;
            }
            return super.new;
        }
        if (g.do(array, ac.ai) == 0) {
            super.new.char = 2;
            super.new.case = this.cH;
            return super.new;
        }
        return ac.a(super.new);
    }
}
