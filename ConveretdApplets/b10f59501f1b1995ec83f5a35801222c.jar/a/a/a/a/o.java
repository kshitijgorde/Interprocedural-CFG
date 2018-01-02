// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class o extends bm
{
    private int bG;
    int bx;
    private int bz;
    int bv;
    private boolean bC;
    int by;
    int bw;
    int bE;
    int bD;
    int bB;
    int bA;
    private char[] bF;
    
    public void if() {
        super.if();
        this.bF = null;
    }
    
    o(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.bG = 0;
        this.bx = 0;
        this.bz = -1;
        this.bv = -16777063;
        this.bC = false;
        this.by = 0;
        this.bw = 0;
        this.bE = 0;
        this.bD = 0;
        this.bB = super.bh;
        this.bA = this.bv;
        this.bF = new char[] { 'p', 'r', 'o', 'g', 'r', 'e', 's', 's', 'b', 'a', 'r', '\0' };
        super.bh = -2236963;
        this.bB = n;
        super.bh = n;
        this.bA = n2;
        this.bv = n2;
        this.by = n3;
        super.bg = n3;
        this.bw = n4;
        super.bf = n4;
        this.bE = n5;
        super.aH = n5;
        this.bD = n6;
        super.aJ = n6;
        super.byte = true;
        super.case = this.bF;
        super.for = true;
    }
    
    public void g() {
        this.bG = 0;
        this.bx = 0;
    }
    
    public void h() {
        this.bC = true;
    }
    
    public void a(final a2 a2, final ae ae, final v br) {
        super.br = br;
        super.aH = -1;
        super.aJ = -1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                super.bg = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                super.bf = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.aH = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.aJ = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                super.bh = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("fgcolor") == 0) {
                this.bv = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = new Integer(a2.new[i]);
            }
        }
        this.if(a2);
        super.byte = true;
    }
    
    void a(final ab ab) {
        if (super.for && (ab.if == 2 || ab.if == 3 || ab.if == 0 || ab.if == 1) && ab.goto >= super.bg && ab.goto < super.bg + super.aH && ab.else >= super.bf && ab.else < super.bf + super.aJ) {
            ab.i = true;
        }
    }
    
    public boolean a(final long n) {
        this.for(n);
        if (this.bx == this.bG) {
            this.bG = 0;
            this.bx = 0;
        }
        if (this.bx != this.bz) {
            this.bz = this.bx;
            return true;
        }
        final boolean do1 = super.do;
        super.do = false;
        return do1 | super.for;
    }
    
    public void a(final int n) {
        this.bG += n;
    }
    
    public void f() {
        if (!super.for) {
            return;
        }
        int n = 0;
        if (this.bC) {
            if (this.bG != 0) {
                n = this.bx * super.aH / this.bG;
            }
            ap.if(super.b.goto, super.bg, super.bf, n, super.aJ, this.bv);
            ap.if(super.b.goto, super.bg + n, super.bf, super.aH - n, super.aJ, super.bh);
        }
        else {
            if (this.bG != 0) {
                n = this.bx * this.bE / this.bG;
            }
            ap.if(super.b.goto, this.by, this.bw, n, this.bD, this.bA);
            ap.if(super.b.goto, this.by + n, this.bw, this.bE - n, this.bD, this.bB);
        }
    }
}
