// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ai extends b5
{
    private char[] bV;
    
    public ai() {
        this.bV = new char[] { 'a', 'c', 't', 'i', 'v', 'e', 'A', 'r', 'e', 'a', '\0' };
    }
    
    public void if() {
        super.if();
        this.bV = null;
    }
    
    public void a(final an b, final bh bh, final aq aq, final ae bs) {
        super.b = b;
        super.case = this.bV;
        super.bs = bs;
        super.a(bh, aq);
        this.if(bh);
    }
    
    public boolean a(final long n) {
        super.byte = true;
        this.void();
        this.for(n);
        return false;
    }
    
    void a(final am am) {
        if (super.for) {
            if (!am.i && am.goto >= super.bh && am.goto < super.bh + super.aI && am.else >= super.bg && am.else < super.bg + super.aK && (super.a1 || am.if != 4)) {
                if (!super.a7) {
                    am.for = 0;
                }
                super.bj = true;
            }
            else {
                super.bj = false;
            }
            if (super.a0 && am.if == 1) {
                am.a(super.bn, false);
                super.a0 = false;
            }
            else if (!am.i && super.bj) {
                if (am.if == 2) {
                    am.a(super.bq, false);
                }
                else if (am.if == 0) {
                    am.a(super.a6, false);
                    super.a0 = true;
                }
                else if (!super.a1) {
                    am.a(super.aP, false);
                    super.a1 = true;
                }
            }
            else if (super.a1) {
                am.a(super.a8, false);
                super.a1 = false;
            }
        }
    }
}
