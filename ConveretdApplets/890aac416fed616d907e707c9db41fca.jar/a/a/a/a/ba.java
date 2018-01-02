// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ba extends b5
{
    private t bZ;
    private boolean bY;
    public boolean b2;
    private boolean b0;
    int[] bX;
    private char[] b1;
    
    ba(final an b) {
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
    
    public void if(final bh bh, final aq aq, final ae bs) {
        super.aO = new ap();
        super.bs = bs;
        super.a(bh, aq);
        this.if(bh);
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
    
    void a(final t bz) {
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
    
    void a(final am am) {
        if (this.bZ != null) {
            am.goto -= super.bh;
            am.else -= super.bg;
            this.bZ.do(am);
            if (am.goto >= 0 && am.goto < super.aI && am.else >= 0 && am.else < super.aK) {
                am.i = true;
            }
            am.goto += super.bh;
            am.else += super.bg;
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
                    a.a.a.a.a3.a(super.aO, super.b.goto, super.bh, super.bg, super.aI, super.aK);
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
                a.a.a.a.a3.a(super.aO, super.b.goto, super.bh, super.bg, super.aI, super.aK);
            }
        }
        else if (super.b.P.E != null) {
            a.a.a.a.a3.a(super.b.P.E, super.b.goto, super.bh, super.bg, super.aI, super.aK);
        }
        else {
            a.a.a.a.a3.if(super.b.goto, super.bh, super.bg, super.aI, super.aK, super.bi);
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
    
    public ap g() {
        try {
            final ap ap = new ap();
            ap.s = super.aI;
            ap.r = super.aK;
            ap.n = 0;
            ap.m = 0;
            ap.x = new int[super.aI * super.aK];
            for (int i = 0; i < super.aK; ++i) {
                for (int j = 0; j < super.aI; ++j) {
                    if (j + super.bh >= 0 && j + super.bh < super.b.goto.s && i + super.bg >= 0 && i + super.bg < super.b.goto.r) {
                        ap.x[j + i * super.aI] = super.b.goto.x[j + super.bh + (i + super.bg) * super.b.goto.s];
                    }
                    else {
                        ap.x[j + i * super.aI] = -1;
                    }
                }
            }
            return ap;
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
