// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class k extends bm
{
    private int bg;
    private final int bj = 3;
    private char[] bk;
    
    public k() {
        this.bg = 0;
        this.bk = new char[] { 'b', 'u', 't', 't', 'o', 'n', '\0' };
    }
    
    public void a(final ac void1, final a2 a2, final ae ae, final v bh) {
        super.void = void1;
        super.byte = this.bk;
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
            super.ay = super.aE.i / 3;
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
        if (super.aE != null && super.try && super.do && super.ay != -1 && super.aA != -1) {
            super.aE.for();
            ap.a(super.aE, this.bg, super.aE.i / 3, super.void.else, super.a7, super.a6, super.ay, super.aA);
        }
    }
    
    void a(final ab ab) {
        if (super.do) {
            if (!ab.i && ab.goto >= super.a7 && ab.goto < super.a7 + super.ay && ab.else >= super.a6 && ab.else < super.a6 + super.aA && (super.aR || ab.if != 4)) {
                if ((super.aE.p[(ab.goto - super.a7) * (super.aE.i / 3) / super.ay + this.bg + (ab.else - super.a6) * super.aE.t / super.aA * super.aE.i] & 0xFF000000) != 0x0 || !super.aX) {
                    super.a9 = true;
                    ab.for = 0;
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
                if (super.a9) {
                    this.bg = super.aE.i / 3;
                }
                else {
                    this.bg = 0;
                }
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
                    this.bg = super.aE.i * 2 / 3;
                    super.aQ = true;
                    super.if = true;
                }
                else if (!super.aR && ab.if != 4) {
                    ab.a(super.aF, false);
                    ab.i = true;
                    super.aR = true;
                    this.bg = super.aE.i / 3;
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
                this.bg = 0;
                super.if = true;
            }
        }
    }
}
