// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class k extends bm
{
    private final int bu = 3;
    private char[] bv;
    
    public k() {
        this.bv = new char[] { 'b', 'u', 't', 't', 'o', 'n', '\0' };
    }
    
    public void if() {
        super.if();
        this.bv = null;
    }
    
    public void a(final ac b, final a2 a2, final ae ae, final v bs) {
        super.b = b;
        super.case = this.bv;
        super.bs = bs;
        super.a(a2, ae);
        this.if(a2);
    }
    
    public boolean a(final long n) {
        super.a(n);
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        if (!super.aO.byte && super.aT.case) {
            super.aO = super.aT.a(super.aO);
            if (super.aI < 0) {
                super.aI = super.aO.s / 3;
            }
            if (super.aK < 0) {
                super.aK = super.aO.r;
            }
            super.byte = true;
            super.do = true;
            if (super.goto) {
                super.aO.if(n);
            }
        }
        this.void();
        if (!super.goto) {
            return false;
        }
        if (super.goto && super.byte) {
            b = (super.aO.a(n) | this.for(n));
        }
        return (b & super.for) | do1;
    }
    
    public void c() {
        if (super.aO != null && super.byte && super.for && super.aI != -1 && super.aK != -1) {
            super.aO.for();
            ap.a(super.aO, super.br, super.aO.s / 3, super.b.goto, super.bh, super.bg, super.aI, super.aK);
        }
    }
    
    void a(final ab ab) {
        if (super.for) {
            if (!ab.i && ab.goto >= super.bh && ab.goto < super.bh + super.aI && ab.else >= super.bg && ab.else < super.bg + super.aK && (super.a1 || ab.if != 4)) {
                if ((super.aO.x[(ab.goto - super.bh) * (super.aO.s / 3) / super.aI + super.br + (ab.else - super.bg) * super.aO.r / super.aK * super.aO.s] & 0xFF000000) != 0x0 || !super.a7) {
                    super.bj = true;
                    ab.for = 0;
                }
                else {
                    super.bj = false;
                }
            }
            else {
                super.bj = false;
            }
            if (super.a0 && ab.if == 1) {
                ab.a(super.bn, false);
                ab.i = super.bo;
                if (super.bj) {
                    super.br = super.aO.s / 3;
                }
                else {
                    super.br = 0;
                }
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
                    super.br = super.aO.s * 2 / 3;
                    super.a0 = true;
                    super.do = true;
                }
                else if (!super.a1 && ab.if != 4) {
                    ab.a(super.aP, false);
                    ab.i = super.bo;
                    super.a1 = true;
                    super.br = super.aO.s / 3;
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
                super.br = 0;
                super.do = true;
            }
        }
    }
}
