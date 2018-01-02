// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class aa extends bm
{
    char[] bH;
    
    public aa() {
        this.bH = new char[] { 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    public void a(final ac void1, final a2 a2, final ae ae, final v bh) {
        super.void = void1;
        super.byte = this.bH;
        super.bh = bh;
        super.a(a2, ae);
        this.if(a2);
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean if1 = super.if;
        super.if = false;
        if (!super.aE.try && super.aJ.case) {
            super.aE = super.aJ.a(super.aE);
            super.ay = super.aE.i;
            super.aA = super.aE.t;
            super.try = true;
            super.if = true;
            if (super.else) {
                super.aE.if(n);
            }
        }
        if (!super.else) {
            return false;
        }
        if (super.else && super.try) {
            b = (super.aE.a(n) | this.for(n));
        }
        return (b & super.do) | if1;
    }
    
    public void e() {
        if (super.aE != null && super.do && super.ay != -1 && super.aA != -1) {
            super.aE.for();
            ap.a(super.aE, 0, super.aE.i, super.void.else, super.a7, super.a6, super.ay, super.aA);
        }
    }
    
    void a(final ab ab) {
        if (super.do) {
            if (!ab.i && ab.goto >= super.a7 && ab.goto < super.a7 + super.ay && ab.else >= super.a6 && ab.else < super.a6 + super.aA && (super.aR || ab.if != 4)) {
                if ((super.aE.p[(ab.goto - super.a7) * super.aE.i / super.ay + (ab.else - super.a6) * super.aE.t / super.aA * super.aE.i] & 0xFF000000) != 0x0 || !super.aX) {
                    super.a9 = true;
                    if (g.a(super.bf) != 0 || g.a(super.aW) != 0 || g.a(super.bd) != 0) {
                        ab.for = 0;
                    }
                }
                else {
                    super.a9 = false;
                }
            }
            else {
                super.a9 = false;
            }
            if (super.aQ && ab.if == 1) {
                ab.a(super.bd, false);
                ab.i = true;
                super.aQ = false;
                super.if = true;
            }
            else if (!ab.i && super.a9) {
                if (ab.if == 2) {
                    ab.a(super.bf, false);
                    ab.i = true;
                }
                else if (ab.if == 0) {
                    ab.a(super.aW, false);
                    ab.i = true;
                    super.aQ = true;
                    super.if = true;
                }
                else if (!super.aR && ab.if != 4) {
                    ab.a(super.aF, false);
                    ab.i = true;
                    super.aR = true;
                    super.if = true;
                }
                else if (super.aR) {
                    ab.i = true;
                }
            }
            else if (!super.aQ && super.aR) {
                ab.a(super.aY, false);
                ab.i = true;
                super.aR = false;
                super.if = true;
            }
        }
    }
}
