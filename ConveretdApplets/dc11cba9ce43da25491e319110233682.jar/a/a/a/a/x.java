// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class x extends bm
{
    private char[] bV;
    
    public x() {
        this.bV = new char[] { 'a', 'c', 't', 'i', 'v', 'e', 'A', 'r', 'e', 'a', '\0' };
    }
    
    public void if() {
        super.if();
        this.bV = null;
    }
    
    public void a(final ac b, final a2 a2, final ae ae, final v bs) {
        super.b = b;
        super.case = this.bV;
        super.bs = bs;
        super.a(a2, ae);
        this.if(a2);
    }
    
    public boolean a(final long n) {
        super.byte = true;
        this.void();
        this.for(n);
        return false;
    }
    
    void a(final ab ab) {
        if (super.for) {
            if (!ab.i && ab.goto >= super.bh && ab.goto < super.bh + super.aI && ab.else >= super.bg && ab.else < super.bg + super.aK && (super.a1 || ab.if != 4)) {
                if (!super.a7) {
                    ab.for = 0;
                }
                super.bj = true;
            }
            else {
                super.bj = false;
            }
            if (super.a0 && ab.if == 1) {
                ab.a(super.bn, false);
                super.a0 = false;
            }
            else if (!ab.i && super.bj) {
                if (ab.if == 2) {
                    ab.a(super.bq, false);
                }
                else if (ab.if == 0) {
                    ab.a(super.a6, false);
                    super.a0 = true;
                }
                else if (!super.a1) {
                    ab.a(super.aP, false);
                    super.a1 = true;
                }
            }
            else if (super.a1) {
                ab.a(super.a8, false);
                super.a1 = false;
            }
        }
    }
}
