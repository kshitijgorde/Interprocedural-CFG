// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class b6 extends b5
{
    char[] cv;
    
    public b6() {
        this.cv = new char[] { 'r', 'e', 'c', 't', 'a', 'n', 'g', 'l', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.cv = null;
    }
    
    public void a(final an b, final bh bh, final aq aq, final ae bs) {
        super.b = b;
        super.case = this.cv;
        super.bs = bs;
        super.a(bh, aq);
        this.if(bh);
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
            a.a.a.a.a3.if(super.b.goto, super.bh, super.bg, super.aI, super.aK, super.bi);
        }
    }
    
    void a(final am am) {
        if (super.for) {
            if (!am.i && am.goto >= super.bh && am.goto < super.bh + super.aI && am.else >= super.bg && am.else < super.bg + super.aK && (super.a1 || am.if != 4)) {
                super.bj = true;
                if (i.a(super.bq) != 0 || i.a(super.a6) != 0 || i.a(super.bn) != 0) {
                    am.for = 0;
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
