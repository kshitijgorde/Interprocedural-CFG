// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class aa extends bm
{
    char[] bV;
    
    public aa() {
        this.bV = new char[] { 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.bV = null;
    }
    
    public void a(final ac b, final a2 a2, final ae ae, final v br) {
        super.b = b;
        super.case = this.bV;
        super.br = br;
        super.a(a2, ae);
        this.if(a2);
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        if (super.aS.k != 0) {
            super.aS = super.aS.try[0];
        }
        if (!super.aN.byte && super.aS.case) {
            super.aN = super.aS.a(super.aN);
            if (super.aH < 0) {
                super.aH = super.aN.j;
            }
            if (super.aJ < 0) {
                super.aJ = super.aN.u;
            }
            super.byte = true;
            super.do = true;
            if (super.goto) {
                super.aN.if(n);
            }
        }
        this.d();
        if (!super.goto) {
            return false;
        }
        if (super.goto && super.byte) {
            b = (super.aN.a(n) | this.for(n));
        }
        return (b & super.for) | do1;
    }
    
    public void f() {
        if (super.aN != null && super.for && super.aH != -1 && super.aJ != -1) {
            super.aN.for();
            ap.a(super.aN, 0, super.aN.j, super.b.goto, super.bg, super.bf, super.aH, super.aJ);
        }
    }
    
    void a(final ab ab) {
        if (super.for) {
            if (!ab.i && ab.goto >= super.bg && ab.goto < super.bg + super.aH && ab.else >= super.bf && ab.else < super.bf + super.aJ && (super.a0 || ab.if != 4)) {
                final int n = (ab.goto - super.bg) * super.aN.j / super.aH + (ab.else - super.bf) * super.aN.u / super.aJ * super.aN.j;
                if ((super.aN.q != null && (super.aN.q[n] & 0xFF000000) != 0x0) || !super.a6) {
                    super.bi = true;
                    if (g.a(super.bp) != 0 || g.a(super.a5) != 0 || g.a(super.bm) != 0) {
                        ab.for = 0;
                    }
                }
                else {
                    super.bi = false;
                }
            }
            else {
                super.bi = false;
            }
            if (super.aZ && ab.if == 1) {
                ab.a(super.bm, false);
                ab.i = super.bn;
                super.aZ = false;
                super.do = true;
            }
            else if (!ab.i && super.bi) {
                if (ab.if == 2) {
                    ab.a(super.bp, false);
                    ab.i = super.bn;
                }
                else if (ab.if == 0) {
                    ab.a(super.a5, false);
                    ab.i = super.bn;
                    super.aZ = true;
                    super.do = true;
                }
                else if (!super.a0 && ab.if != 4) {
                    ab.a(super.aO, false);
                    ab.i = super.bn;
                    super.a0 = true;
                    super.do = true;
                }
                else if (super.a0) {
                    ab.i = super.bn;
                }
            }
            else if (!super.aZ && super.a0) {
                ab.a(super.a7, false);
                ab.i = super.bn;
                super.a0 = false;
                super.do = true;
            }
        }
    }
}
