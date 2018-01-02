// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class o extends bm
{
    private int bH;
    int by;
    private int bA;
    int bw;
    private boolean bD;
    int bz;
    int bx;
    int bF;
    int bE;
    int bC;
    int bB;
    private char[] bG;
    
    public void if() {
        super.if();
        this.bG = null;
    }
    
    o() {
        this.bH = 0;
        this.by = 0;
        this.bA = -1;
        this.bw = -16777063;
        this.bD = false;
        this.bz = 0;
        this.bx = 0;
        this.bF = 0;
        this.bE = 0;
        this.bC = super.bi;
        this.bB = this.bw;
        this.bG = new char[] { 'p', 'r', 'o', 'g', 'r', 'e', 's', 's', 'b', 'a', 'r', '\0' };
        super.case = this.bG;
    }
    
    public void f() {
        this.bH = 0;
        this.by = 0;
    }
    
    public void d() {
        this.bD = true;
    }
    
    public void a(final a2 a2, final ae ae, final v bs) {
        super.bs = bs;
        super.aI = -1;
        super.aK = -1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                super.bh = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                super.bg = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                super.aI = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                super.aK = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                super.bi = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("fgcolor") == 0) {
                this.bw = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = new Integer(a2.new[i]);
            }
        }
        this.if(a2);
        super.byte = true;
    }
    
    void a(final ab ab) {
        if (super.for && (ab.if == 2 || ab.if == 3 || ab.if == 0 || ab.if == 1) && ab.goto >= super.bh && ab.goto < super.bh + super.aI && ab.else >= super.bg && ab.else < super.bg + super.aK) {
            ab.i = true;
        }
    }
    
    public boolean a(final long n) {
        this.for(n);
        if (this.by == this.bH) {
            this.bH = 0;
            this.by = 0;
        }
        if (this.by != this.bA) {
            this.bA = this.by;
            return true;
        }
        final boolean do1 = super.do;
        super.do = false;
        return do1 | super.for;
    }
    
    public void e() {
        if (this.by == this.bH) {
            this.bH = 0;
            this.by = 0;
        }
    }
    
    public void a(final int n) {
        this.bH += n;
    }
    
    public void c() {
        if (!super.for) {
            return;
        }
        int n = 0;
        if (this.bD) {
            if (this.bH != 0) {
                n = this.by * super.aI / this.bH;
            }
            ap.if(super.b.goto, super.bh, super.bg, n, super.aK, this.bw);
            ap.if(super.b.goto, super.bh + n, super.bg, super.aI - n, super.aK, super.bi);
        }
        else {
            if (this.bH != 0) {
                n = this.by * this.bF / this.bH;
            }
            ap.if(super.b.goto, this.bz, this.bx, n, this.bE, this.bB);
            ap.if(super.b.goto, this.bz + n, this.bx, this.bF - n, this.bE, this.bC);
        }
    }
}
