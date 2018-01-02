// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class x extends bm
{
    private char[] bG;
    
    public x() {
        this.bG = new char[] { 'a', 'c', 't', 'i', 'v', 'e', 'A', 'r', 'e', 'a', '\0' };
    }
    
    public void a(final ac void1, final a2 a2, final ae ae, final v bh) {
        super.void = void1;
        super.byte = this.bG;
        super.bh = bh;
        super.a(a2, ae);
        this.if(a2);
    }
    
    public boolean a(final long n) {
        super.try = true;
        this.for(n);
        return false;
    }
    
    void a(final ab ab) {
        if (super.do) {
            if (!ab.i && ab.goto >= super.a7 && ab.goto < super.a7 + super.ay && ab.else >= super.a6 && ab.else < super.a6 + super.aA && (super.aR || ab.if != 4)) {
                if (!super.aX) {
                    ab.for = 0;
                }
                super.a9 = true;
            }
            else {
                super.a9 = false;
            }
            if (super.aQ && ab.if == 1) {
                ab.a(super.bd, false);
                super.aQ = false;
            }
            else if (!ab.i && super.a9) {
                if (ab.if == 2) {
                    ab.a(super.bf, false);
                }
                else if (ab.if == 0) {
                    ab.a(super.aW, false);
                    super.aQ = true;
                }
                else if (!super.aR) {
                    ab.a(super.aF, false);
                    super.aR = true;
                }
            }
            else if (super.aR) {
                ab.a(super.aY, false);
                super.aR = false;
            }
        }
    }
}
