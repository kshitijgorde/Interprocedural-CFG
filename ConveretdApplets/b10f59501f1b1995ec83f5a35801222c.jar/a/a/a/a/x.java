// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class x extends bm
{
    private char[] bU;
    
    public x() {
        this.bU = new char[] { 'a', 'c', 't', 'i', 'v', 'e', 'A', 'r', 'e', 'a', '\0' };
    }
    
    public void if() {
        super.if();
        this.bU = null;
    }
    
    public void a(final ac b, final a2 a2, final ae ae, final v br) {
        super.b = b;
        super.case = this.bU;
        super.br = br;
        super.a(a2, ae);
        this.if(a2);
    }
    
    public boolean a(final long n) {
        super.byte = true;
        this.d();
        this.for(n);
        return false;
    }
    
    void a(final ab ab) {
        if (super.for) {
            if (!ab.i && ab.goto >= super.bg && ab.goto < super.bg + super.aH && ab.else >= super.bf && ab.else < super.bf + super.aJ && (super.a0 || ab.if != 4)) {
                if (!super.a6) {
                    ab.for = 0;
                }
                super.bi = true;
            }
            else {
                super.bi = false;
            }
            if (super.aZ && ab.if == 1) {
                ab.a(super.bm, false);
                super.aZ = false;
            }
            else if (!ab.i && super.bi) {
                if (ab.if == 2) {
                    ab.a(super.bp, false);
                }
                else if (ab.if == 0) {
                    ab.a(super.a5, false);
                    super.aZ = true;
                }
                else if (!super.a0) {
                    ab.a(super.aO, false);
                    super.a0 = true;
                }
            }
            else if (super.a0) {
                ab.a(super.a7, false);
                super.a0 = false;
            }
        }
    }
}
