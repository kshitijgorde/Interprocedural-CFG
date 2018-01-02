// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bn extends bm
{
    char[] cv;
    
    public bn() {
        this.cv = new char[] { 'r', 'e', 'c', 't', 'a', 'n', 'g', 'l', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.cv = null;
    }
    
    public void a(final ac b, final a2 a2, final ae ae, final v bs) {
        super.b = b;
        super.case = this.cv;
        super.bs = bs;
        super.a(a2, ae);
        this.if(a2);
        super.byte = true;
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        this.void();
        if (!super.goto) {
            return false;
        }
        if (super.goto && super.byte) {
            b |= this.for(n);
        }
        return (b & super.for) | do1;
    }
    
    public void c() {
        if (super.for && super.aI != -1 && super.aK != -1) {
            ap.if(super.b.goto, super.bh, super.bg, super.aI, super.aK, super.bi);
        }
    }
    
    void a(final ab ab) {
        if (super.for) {
            if (!ab.i && ab.goto >= super.bh && ab.goto < super.bh + super.aI && ab.else >= super.bg && ab.else < super.bg + super.aK && (super.a1 || ab.if != 4)) {
                super.bj = true;
                if (g.a(super.bq) != 0 || g.a(super.a6) != 0 || g.a(super.bn) != 0) {
                    ab.for = 0;
                }
            }
            else {
                super.bj = false;
            }
            if (super.a0 && ab.if == 1) {
                ab.a(super.bn, false);
                ab.i = super.bo;
                super.a0 = false;
                super.do = true;
            }
            else if (!ab.i && super.bj) {
                if (ab.if == 2) {
                    ab.a(super.bq, false);
                    ab.i = super.bo;
                }
                else if (ab.if == 0) {
                    ab.a(super.a6, false);
                    ab.i = super.bo;
                    super.a0 = true;
                    super.do = true;
                }
                else if (!super.a1 && ab.if != 4) {
                    ab.a(super.aP, false);
                    ab.i = super.bo;
                    super.a1 = true;
                    super.do = true;
                }
                else if (super.a1) {
                    ab.i = super.bo;
                }
            }
            else if (!super.a0 && super.a1) {
                ab.a(super.a8, false);
                ab.i = super.bo;
                super.a1 = false;
                super.do = true;
            }
        }
    }
}
