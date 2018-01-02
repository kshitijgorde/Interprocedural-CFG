// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class aw extends bm
{
    private m bZ;
    private boolean bY;
    public boolean b2;
    private boolean b0;
    int[] bX;
    private char[] b1;
    
    aw(final ac b) {
        this.bZ = null;
        this.b2 = false;
        this.b0 = true;
        this.b1 = new char[] { 'v', 'i', 'e', 'w', 'e', 'r', '\0' };
        super.b = b;
        super.case = this.b1;
    }
    
    public void if() {
        super.if();
        this.bX = null;
    }
    
    public void if(final a2 a2, final ae ae, final v bs) {
        super.aO = new ad();
        super.bs = bs;
        super.a(a2, ae);
        this.if(a2);
        super.byte = true;
        this.j();
    }
    
    void j() {
        if (super.b.T) {
            super.aO.l = super.aI;
            super.aO.p = super.aK;
            super.aO.n = super.bh;
            super.aO.m = super.bg;
            super.aO.x = super.b.goto.x;
            super.aO.s = super.b.goto.s;
            super.aO.r = super.b.goto.r;
        }
        else if (super.aO.l != super.aI || super.aO.p != super.aK) {
            super.aO.x = new int[super.aI * super.aK];
            for (int n = super.aI * super.aK, i = 0; i < n; ++i) {
                super.aO.x[i] = -1;
            }
            this.bX = super.aO.x;
            super.aO.l = super.aI;
            super.aO.p = super.aK;
            super.aO.n = 0;
            super.aO.m = 0;
            super.aO.s = super.aI;
            super.aO.r = super.aK;
        }
        if (this.bZ != null) {
            this.bZ.W.a(super.aO);
        }
    }
    
    void a(final m bz) {
        this.bZ = bz;
        this.bZ.P = true;
        this.bZ.W.eM = super.aI;
        this.bZ.W.eR = super.aK;
        this.j();
        this.b2 = false;
    }
    
    public void h() {
        this.bZ = null;
    }
    
    void i() {
        this.bZ.P = false;
        this.bZ = null;
        this.j();
        this.b2 = false;
    }
    
    void a(final ab ab) {
        if (this.bZ != null) {
            ab.goto -= super.bh;
            ab.else -= super.bg;
            this.bZ.do(ab);
            if (ab.goto >= 0 && ab.goto < super.aI && ab.else >= 0 && ab.else < super.aK) {
                ab.i = true;
            }
            ab.goto += super.bh;
            ab.else += super.bg;
        }
    }
    
    public void c() {
        if (!super.for) {
            return;
        }
        if (this.bZ != null && this.bZ.z) {
            if (this.bY) {
                if (this.b0 && !super.b.T) {
                    super.aO.l = super.aI;
                    super.aO.p = super.aK;
                    super.aO.n = super.bh;
                    super.aO.m = super.bg;
                    super.aO.x = super.b.goto.x;
                    super.aO.s = super.b.goto.s;
                    super.aO.r = super.b.goto.r;
                    if (this.bZ != null) {
                        this.bZ.W.a(super.aO);
                    }
                    this.b0 = false;
                }
                this.b2 = false;
                if (super.bs.i.m == 2) {
                    this.bZ.a(true);
                }
                else {
                    this.bZ.a(false);
                }
            }
            else if (!this.b2) {
                if (!super.b.T) {
                    super.aO.x = this.bX;
                    super.aO.l = super.aI;
                    super.aO.p = super.aK;
                    super.aO.n = 0;
                    super.aO.m = 0;
                    super.aO.s = super.aI;
                    super.aO.r = super.aK;
                    if (this.bZ != null) {
                        this.bZ.W.a(super.aO);
                    }
                }
                this.b2 = true;
                if (super.bs.i.m == 0) {
                    this.bZ.a(false);
                }
                else {
                    this.bZ.a(true);
                }
                if (!super.b.T && !this.bZ.W.e5) {
                    ap.a(super.aO, super.b.goto, super.bh, super.bg, super.aI, super.aK);
                }
                this.b0 = true;
            }
            else if (super.b.T) {
                if (super.bs.i.m == 0) {
                    this.bZ.a(false);
                }
                else {
                    this.bZ.a(true);
                }
            }
            else if (!this.bZ.W.e5) {
                ap.a(super.aO, super.b.goto, super.bh, super.bg, super.aI, super.aK);
            }
        }
        else if (super.b.P.E != null) {
            ap.a(super.b.P.E, super.b.goto, super.bh, super.bg, super.aI, super.aK);
        }
        else {
            ap.if(super.b.goto, super.bh, super.bg, super.aI, super.aK, super.bi);
        }
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        if (this.bZ != null) {
            final boolean b2 = b | this.for(n);
            if (this.bZ.W.eM != super.aI || this.bZ.W.eR != super.aK) {
                this.bZ.W.eM = super.aI;
                this.bZ.W.eR = super.aK;
                this.j();
                this.b0 = true;
            }
            b = (this.bZ.do(n) | b2);
        }
        this.void();
        if (!b && this.bY) {
            this.bY = b;
            return super.for | do1;
        }
        this.bY = b;
        return (this.bY & super.for) | do1;
    }
    
    public ad g() {
        try {
            final ad ad = new ad();
            ad.s = super.aI;
            ad.r = super.aK;
            ad.n = 0;
            ad.m = 0;
            ad.x = new int[super.aI * super.aK];
            for (int i = 0; i < super.aK; ++i) {
                for (int j = 0; j < super.aI; ++j) {
                    if (j + super.bh >= 0 && j + super.bh < super.b.goto.s && i + super.bg >= 0 && i + super.bg < super.b.goto.r) {
                        ad.x[j + i * super.aI] = super.b.goto.x[j + super.bh + (i + super.bg) * super.b.goto.s];
                    }
                    else {
                        ad.x[j + i * super.aI] = -1;
                    }
                }
            }
            return ad;
        }
        catch (Exception ex) {
            return null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Transition disabled. Not enough memory. 1");
            return null;
        }
    }
}
