// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class b8 extends b5
{
    private char[] cw;
    
    public b8() {
        this.cw = new char[] { 'c', 'h', 'e', 'c', 'k', 'B', 'o', 'x', '\0' };
    }
    
    public void if() {
        super.if();
        this.cw = null;
    }
    
    public void a(final an b, final bh bh, final aq aq, final ae bs) {
        super.b = b;
        super.case = this.cw;
        super.bs = bs;
        super.a(bh, aq);
        this.if(bh);
    }
    
    public boolean a(final long n) {
        super.a(n);
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        if (!super.aO.byte && super.aT.case) {
            super.aO = super.aT.a(super.aO);
            if (super.aI < 0) {
                super.aI = super.aO.s / 5;
            }
            if (super.aK < 0) {
                super.aK = super.aO.r;
            }
            super.byte = true;
            super.do = true;
            if (super.goto) {
                super.aO.if(n);
            }
            if (super.a9) {
                super.br = super.aO.s / 5;
            }
            else {
                super.br = 0;
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
        if (super.aO != null && super.for && super.aI != -1 && super.aK != -1) {
            super.aO.for();
            a.a.a.a.a3.a(super.aO, super.br, super.aO.s / 5, super.b.goto, super.bh, super.bg, super.aI, super.aK);
        }
    }
    
    void a(final am am) {
        if (super.for) {
            if (!am.i && am.goto >= super.bh && am.goto < super.bh + super.aI && am.else >= super.bg && am.else < super.bg + super.aK && (super.a1 || am.if != 4)) {
                if ((super.aO.x[(am.goto - super.bh) * (super.aO.s / 5) / super.aI + super.br + (am.else - super.bg) * super.aO.r / super.aK * super.aO.s] & 0xFF000000) != 0x0 || !super.a7) {
                    super.bj = true;
                }
            }
            else {
                super.bj = false;
            }
            if (super.a0 && am.if == 1) {
                am.a(super.bn, false);
                if (super.a9) {
                    if (super.bj) {
                        super.br = super.aO.s * 3 / 5;
                    }
                    else {
                        super.br = 0;
                    }
                    super.a9 = false;
                    am.a(super.bp, false);
                }
                else {
                    if (super.bj) {
                        super.br = super.aO.s * 4 / 5;
                    }
                    else {
                        super.br = super.aO.s / 5;
                    }
                    super.a9 = true;
                    am.a(super.a3, false);
                }
                am.i = super.bo;
                super.a0 = false;
                super.do = true;
            }
            else if (!am.i && super.bj) {
                if (am.if == 2) {
                    am.a(super.bq, false);
                    am.i = super.bo;
                }
                else if (am.if == 0) {
                    super.br = super.aO.s * 2 / 5;
                    am.a(super.a6, false);
                    am.i = super.bo;
                    super.a0 = true;
                    super.do = true;
                }
                else if (!super.a1) {
                    am.a(super.aP, false);
                    if (super.a9) {
                        super.br = super.aO.s * 4 / 5;
                        am.a(super.aU, false);
                    }
                    else {
                        super.br = super.aO.s * 3 / 5;
                        am.a(super.bm, false);
                    }
                    am.i = super.bo;
                    super.a1 = true;
                    super.do = true;
                }
                else if (super.a1) {
                    am.i = super.bo;
                }
            }
            else if (!super.a0 && super.a1) {
                am.a(super.a8, false);
                if (super.a9) {
                    super.br = super.aO.s / 5;
                    am.a(super.aW, false);
                }
                else {
                    super.br = 0;
                    am.a(super.bt, false);
                }
                am.i = super.bo;
                super.a1 = false;
                super.do = true;
            }
        }
        if (super.bj) {
            am.for = 0;
        }
    }
}
