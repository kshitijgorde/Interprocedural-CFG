// 
// Decompiled by Procyon v0.5.30
// 

public class aj extends a
{
    public boolean bP;
    protected String bQ;
    protected String bR;
    protected String bI;
    protected String bK;
    protected String bN;
    protected String bS;
    protected boolean bM;
    protected boolean bG;
    protected boolean bO;
    an bH;
    int bJ;
    protected int bL;
    
    public aj() {
        this.bP = false;
        this.bQ = "";
        this.bR = "";
        this.bI = "";
        this.bK = "";
        this.bN = "";
        this.bS = "";
        this.bM = false;
        this.bG = false;
        this.bO = false;
        this.bH = null;
        this.bJ = -1;
        this.bL = 1;
        super.for = true;
    }
    
    void new(final n n) {
        for (n n2 = n.if; n2 != null; n2 = n2.for) {
            final String lowerCase = n2.a.toLowerCase();
            if (n2.case != null && n2.case.do.trim().length() != 0) {
                final String do1 = n2.case.do;
                if (lowerCase.compareTo("mouseclick") == 0) {
                    this.bR = do1;
                }
                else if (lowerCase.compareTo("mousepress") == 0) {
                    this.bQ = do1;
                }
                else if (lowerCase.compareTo("mouseenter") == 0) {
                    this.bI = do1;
                }
                else if (lowerCase.compareTo("mouseleave") == 0) {
                    this.bK = do1;
                }
                else if (lowerCase.compareTo("mouserelease") == 0) {
                    this.bN = do1;
                }
                else if (lowerCase.compareTo("text") == 0) {
                    this.bS = do1;
                }
            }
        }
    }
    
    void a(final ah ah, final boolean bg) {
        this.bG = bg;
        if (this.bG && !ah.h && ah.if != 4) {
            ah.for = 0;
        }
        if (this.bM && ah.if == 1) {
            ah.a(this.bN);
            ah.h = true;
            this.bM = false;
        }
        else if (this.bG) {
            if (ah.if == 2) {
                ah.a(this.bR);
                ah.h = true;
            }
            else if (ah.if == 0) {
                ah.a(this.bQ);
                ah.h = true;
                this.bM = true;
            }
            else if (!this.bO) {
                ah.a(this.bI);
                super.F.b.r.a(this.bS);
                ah.h = true;
                this.bO = true;
            }
            else if (this.bO) {
                ah.h = true;
            }
        }
        else if (this.bO) {
            ah.a(this.bK);
            if (super.F.b.r.if().compareTo(this.bS.trim()) == 0) {
                super.F.b.r.a("");
            }
            ah.h = true;
            this.bO = false;
        }
    }
    
    void a(final float[][] array) {
    }
    
    public void if(final n n) {
    }
    
    protected void int(final n n) {
    }
    
    protected void a(final an an, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = -(n6 >> 1);
        for (int n8 = n7 + n6, i = n7; i < n8; ++i) {
            for (int j = n7; j < n8; ++j) {
                d.a(an, n + i, n2 + j, n3 + i, n4 + j, n5);
            }
        }
    }
}
