// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class aw extends bm
{
    private m bY;
    private boolean bX;
    public boolean b1;
    private boolean bZ;
    int[] bW;
    private char[] b0;
    
    aw(final ac b) {
        this.bY = null;
        this.b1 = false;
        this.bZ = true;
        this.b0 = new char[] { 'v', 'i', 'e', 'w', 'e', 'r', '\0' };
        super.b = b;
        super.case = this.b0;
    }
    
    public void if() {
        super.if();
        this.bW = null;
    }
    
    public void do(final a2 a2, final ae ae, final v br) {
        super.aN = new ad();
        super.br = br;
        super.a(a2, ae);
        this.if(a2);
        super.byte = true;
        this.l();
    }
    
    void l() {
        if (super.b.S) {
            super.aN.t = super.aH;
            super.aN.k = super.aJ;
            super.aN.i = super.bg;
            super.aN.h = super.bf;
            super.aN.q = super.b.goto.q;
            super.aN.j = super.b.goto.j;
            super.aN.u = super.b.goto.u;
        }
        else if (super.aN.t != super.aH || super.aN.k != super.aJ) {
            super.aN.q = new int[super.aH * super.aJ];
            for (int n = super.aH * super.aJ, i = 0; i < n; ++i) {
                super.aN.q[i] = -1;
            }
            this.bW = super.aN.q;
            super.aN.t = super.aH;
            super.aN.k = super.aJ;
            super.aN.i = 0;
            super.aN.h = 0;
            super.aN.j = super.aH;
            super.aN.u = super.aJ;
        }
        if (this.bY != null) {
            this.bY.U.a(super.aN);
        }
    }
    
    void a(final m by) {
        this.bY = by;
        this.bY.N = true;
        this.bY.U.eL = super.aH;
        this.bY.U.eQ = super.aJ;
        this.l();
        this.b1 = false;
    }
    
    public void j() {
        this.bY = null;
    }
    
    void k() {
        this.bY.N = false;
        this.bY = null;
        this.l();
        this.b1 = false;
    }
    
    void a(final ab ab) {
        if (this.bY != null) {
            ab.goto -= super.bg;
            ab.else -= super.bf;
            this.bY.do(ab);
            if (ab.goto >= 0 && ab.goto < super.aH && ab.else >= 0 && ab.else < super.aJ) {
                ab.i = true;
            }
            ab.goto += super.bg;
            ab.else += super.bf;
        }
    }
    
    public void f() {
        if (!super.for) {
            return;
        }
        if (this.bY != null && this.bY.z) {
            if (this.bX) {
                if (this.bZ && !super.b.S) {
                    super.aN.t = super.aH;
                    super.aN.k = super.aJ;
                    super.aN.i = super.bg;
                    super.aN.h = super.bf;
                    super.aN.q = super.b.goto.q;
                    super.aN.j = super.b.goto.j;
                    super.aN.u = super.b.goto.u;
                    if (this.bY != null) {
                        this.bY.U.a(super.aN);
                    }
                    this.bZ = false;
                }
                this.b1 = false;
                if (super.br.i.o == 2) {
                    this.bY.a(true);
                }
                else {
                    this.bY.a(false);
                }
            }
            else if (!this.b1) {
                if (!super.b.S) {
                    super.aN.q = this.bW;
                    super.aN.t = super.aH;
                    super.aN.k = super.aJ;
                    super.aN.i = 0;
                    super.aN.h = 0;
                    super.aN.j = super.aH;
                    super.aN.u = super.aJ;
                    if (this.bY != null) {
                        this.bY.U.a(super.aN);
                    }
                }
                this.b1 = true;
                if (super.br.i.o == 0) {
                    this.bY.a(false);
                }
                else {
                    this.bY.a(true);
                }
                if (!super.b.S && !this.bY.U.e4) {
                    ap.a(super.aN, super.b.goto, super.bg, super.bf, super.aH, super.aJ);
                }
                this.bZ = true;
            }
            else if (super.b.S) {
                if (super.br.i.o == 0) {
                    this.bY.a(false);
                }
                else {
                    this.bY.a(true);
                }
            }
            else if (!this.bY.U.e4) {
                ap.a(super.aN, super.b.goto, super.bg, super.bf, super.aH, super.aJ);
            }
        }
        else {
            ap.if(super.b.goto, super.bg, super.bf, super.aH, super.aJ, super.bh);
        }
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        if (this.bY != null) {
            final boolean b2 = b | this.for(n);
            if (this.bY.U.eL != super.aH || this.bY.U.eQ != super.aJ) {
                this.bY.U.eL = super.aH;
                this.bY.U.eQ = super.aJ;
                this.l();
                this.bZ = true;
            }
            b = (this.bY.do(n) | b2);
        }
        this.d();
        if (!b && this.bX) {
            this.bX = b;
            return super.for | do1;
        }
        this.bX = b;
        return (this.bX & super.for) | do1;
    }
    
    public ad i() {
        try {
            final ad ad = new ad();
            ad.j = super.aH;
            ad.u = super.aJ;
            ad.i = 0;
            ad.h = 0;
            ad.q = new int[super.aH * super.aJ];
            for (int i = 0; i < super.aJ; ++i) {
                for (int j = 0; j < super.aH; ++j) {
                    if (j + super.bg >= 0 && j + super.bg < super.b.goto.j && i + super.bf >= 0 && i + super.bf < super.b.goto.u) {
                        ad.q[j + i * super.aH] = super.b.goto.q[j + super.bg + (i + super.bf) * super.b.goto.j];
                    }
                    else {
                        ad.q[j + i * super.aH] = -1;
                    }
                }
            }
            return ad;
        }
        catch (Exception ex) {
            return null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            return null;
        }
    }
}
