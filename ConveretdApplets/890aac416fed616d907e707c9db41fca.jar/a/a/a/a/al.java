// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class al extends b5
{
    char[] bW;
    
    public al() {
        this.bW = new char[] { 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.bW = null;
    }
    
    public void a(final an b, final bh bh, final aq aq, final ae bs) {
        super.b = b;
        super.case = this.bW;
        super.bs = bs;
        super.a(bh, aq);
        this.if(bh);
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        if (super.aT.k != 0) {
            super.aT = super.aT.try[0];
        }
        if (!super.aO.byte && super.aT.case) {
            super.aO = super.aT.a(super.aO);
            if (super.aI < 0) {
                super.aI = super.aO.s;
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
        if (super.aO != null && super.for && super.aI != -1 && super.aK != -1) {
            super.aO.for();
            a.a.a.a.a3.a(super.aO, 0, super.aO.s, super.b.goto, super.bh, super.bg, super.aI, super.aK);
        }
    }
    
    void a(final am am) {
        if (super.for) {
            if (!am.i && am.goto >= super.bh && am.goto < super.bh + super.aI && am.else >= super.bg && am.else < super.bg + super.aK && (super.a1 || am.if != 4)) {
                final int n = (am.goto - super.bh) * super.aO.s / super.aI + (am.else - super.bg) * super.aO.r / super.aK * super.aO.s;
                if ((super.aO.x != null && (super.aO.x[n] & 0xFF000000) != 0x0) || !super.a7) {
                    super.bj = true;
                    if (i.a(super.bq) != 0 || i.a(super.a6) != 0 || i.a(super.bn) != 0) {
                        am.for = 0;
                    }
                }
                else {
                    super.bj = false;
                }
            }
            else {
                super.bj = false;
            }
            if (super.a0 && am.if == 1) {
                am.a(super.bn, false);
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
                    am.a(super.a6, false);
                    am.i = super.bo;
                    super.a0 = true;
                    super.do = true;
                }
                else if (!super.a1 && am.if != 4) {
                    am.a(super.aP, false);
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
                am.i = super.bo;
                super.a1 = false;
                super.do = true;
            }
        }
    }
}
