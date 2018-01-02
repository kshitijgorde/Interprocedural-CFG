// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class w extends f
{
    boolean cO;
    protected char[] cP;
    protected char[] cQ;
    protected char[] cH;
    protected char[] cS;
    protected char[] cM;
    protected char[] cR;
    private static final char[] cL;
    protected boolean cK;
    protected boolean cF;
    protected boolean cN;
    ap cG;
    int cI;
    protected int cJ;
    
    static {
        cL = new char[] { 'b', 'o', 'r', 'd', 'e', 'r', 's', 'i', 'z', 'e', '\0' };
    }
    
    public w() {
        this.cO = false;
        this.cP = new char[1];
        this.cQ = new char[1];
        this.cH = new char[1];
        this.cS = new char[1];
        this.cM = new char[1];
        this.cR = new char[1];
        this.cK = false;
        this.cF = false;
        this.cN = false;
        this.cG = null;
        this.cI = -1;
        this.cJ = 1;
        super.byte = true;
    }
    
    void try(final bh bh) {
        for (bh bh2 = bh.if; bh2 != null; bh2 = bh2.for) {
            final String lowerCase = bh2.a.toLowerCase();
            if (bh2.case != null && bh2.case.do != null && bh2.case.do.trim().length() != 0) {
                final String do1 = bh2.case.do;
                if (lowerCase.compareTo("mouseclick") == 0) {
                    this.cQ = do1.toCharArray();
                }
                else if (lowerCase.compareTo("mousepress") == 0) {
                    this.cP = do1.toCharArray();
                }
                else if (lowerCase.compareTo("mouseenter") == 0) {
                    this.cH = do1.toCharArray();
                }
                else if (lowerCase.compareTo("mouseleave") == 0 || lowerCase.compareTo("mouseexit") == 0) {
                    this.cS = do1.toCharArray();
                }
                else if (lowerCase.compareTo("mouserelease") == 0) {
                    this.cM = do1.toCharArray();
                }
                else if (lowerCase.compareTo("text") == 0) {
                    this.cR = do1.toCharArray();
                }
            }
        }
    }
    
    void a(final am am, final boolean cf) {
        this.cF = cf;
        if (this.cF && !am.i && am.if != 4) {
            am.for = 0;
        }
        if (this.cK && am.if == 1) {
            am.a(this.cM, true);
            super.cB.a(super.f, 3);
            am.i = true;
            this.cK = false;
        }
        else if (!am.i && this.cF) {
            if (am.if == 2) {
                am.a(this.cQ, true);
                super.cB.a(super.f, 4);
                am.i = true;
            }
            else if (am.if == 0) {
                am.a(this.cP, true);
                super.cB.a(super.f, 2);
                am.i = true;
                this.cK = true;
            }
            else if (!this.cN) {
                am.a(this.cH, true);
                super.cB.a(super.f, 0);
                am.i = true;
                this.cN = true;
            }
            else if (this.cN) {
                am.i = true;
            }
        }
        else if (this.cN) {
            am.a(this.cS, true);
            super.cB.a(super.f, 1);
            am.i = true;
            this.cN = false;
        }
    }
    
    void a(final float[][] array) {
    }
    
    public void int(final bh bh) {
    }
    
    protected void new(final bh bh) {
    }
    
    protected void a(final ap ap, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = -(n6 >> 1);
        for (int n8 = n7 + n6, i = n7; i < n8; ++i) {
            for (int j = n7; j < n8; ++j) {
                a3.a(ap, n + i, n2 + j, n3 + i, n4 + j, n5);
            }
        }
    }
    
    public void a(final char[] array, final bi bi) {
        if (i.do(array, an.j) == 0) {
            this.cO = az.a(bi);
            super.do = true;
        }
        else if (i.do(array, an.Q) == 0) {
            super.for = az.a(bi);
            super.do = true;
        }
        else if (i.do(array, an.y) == 0) {
            if (bi.char == 4) {
                this.cP = bi.int;
            }
        }
        else if (i.do(array, an.d) == 0) {
            if (bi.char == 4) {
                this.cQ = bi.int;
            }
        }
        else if (i.do(array, an.long) == 0) {
            if (bi.char == 4) {
                this.cH = bi.int;
            }
        }
        else if (i.do(array, an.J) == 0) {
            if (bi.char == 4) {
                this.cS = bi.int;
            }
        }
        else if (i.do(array, an.E) == 0) {
            if (bi.char == 4) {
                this.cM = bi.int;
            }
        }
        else if (i.do(array, an.o) == 0) {
            if (bi.char == 4) {
                this.cR = bi.int;
            }
            super.do = true;
        }
        else if (i.do(array, an.ak) == 0) {
            if (bi.char == 2) {
                this.cI = (int)bi.case;
            }
            super.do = true;
        }
        else if (i.do(array, w.cL) == 0) {
            if (bi.char == 2) {
                this.cJ = (int)bi.case;
            }
            super.do = true;
        }
    }
    
    public bi a(final char[] array) {
        if (i.do(array, an.j) == 0) {
            super.new.char = 1;
            super.new.long = this.cO;
            return super.new;
        }
        if (i.do(array, an.Q) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (i.do(array, an.y) == 0) {
            super.new.char = 4;
            super.new.int = this.cP;
            return super.new;
        }
        if (i.do(array, an.d) == 0) {
            super.new.char = 4;
            super.new.int = this.cQ;
            return super.new;
        }
        if (i.do(array, an.long) == 0) {
            super.new.char = 4;
            super.new.int = this.cH;
            return super.new;
        }
        if (i.do(array, an.J) == 0) {
            super.new.char = 4;
            super.new.int = this.cS;
            return super.new;
        }
        if (i.do(array, an.E) == 0) {
            super.new.char = 4;
            super.new.int = this.cM;
            return super.new;
        }
        if (i.do(array, an.o) == 0) {
            super.new.char = 4;
            super.new.int = this.cR;
            return super.new;
        }
        if (i.do(array, an.t) == 0) {
            super.new.char = 5;
            if (this.cG != null) {
                super.new.goto = 1;
                (super.new.a = new bi[1])[0] = new bi();
                super.new.a[0].char = 4;
                super.new.a[0].int = this.cG.f;
            }
            else {
                super.new.goto = 0;
            }
            return super.new;
        }
        if (i.do(array, an.ak) == 0) {
            super.new.char = 2;
            super.new.case = this.cI;
            return super.new;
        }
        return an.a(super.new);
    }
}
