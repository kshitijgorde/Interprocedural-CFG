// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class aw extends bm
{
    private m bK;
    private boolean bJ;
    public boolean bN;
    private boolean bL;
    int[] bI;
    private char[] bM;
    
    aw(final ac void1) {
        this.bK = null;
        this.bN = false;
        this.bL = true;
        this.bM = new char[] { 'v', 'i', 'e', 'w', 'e', 'r', '\0' };
        super.void = void1;
        super.byte = this.bM;
    }
    
    public void do(final a2 a2, final ae ae, final v bh) {
        super.aE = new ad();
        super.bh = bh;
        super.a(a2, ae);
        this.if(a2);
        super.try = true;
        this.k();
    }
    
    void k() {
        if (super.void.M) {
            super.aE.s = super.ay;
            super.aE.j = super.aA;
            super.aE.h = super.a7;
            super.aE.g = super.a6;
            super.aE.p = super.void.else.p;
            super.aE.i = super.void.else.i;
            super.aE.t = super.void.else.t;
        }
        else if (super.aE.s != super.ay || super.aE.j != super.aA) {
            super.aE.p = new int[super.ay * super.aA];
            for (int n = super.ay * super.aA, i = 0; i < n; ++i) {
                super.aE.p[i] = -1;
            }
            this.bI = super.aE.p;
            super.aE.s = super.ay;
            super.aE.j = super.aA;
            super.aE.h = 0;
            super.aE.g = 0;
            super.aE.i = super.ay;
            super.aE.t = super.aA;
        }
        if (this.bK != null) {
            this.bK.L.a(super.aE);
        }
    }
    
    void a(final m bk) {
        this.bK = bk;
        this.bK.F = true;
        this.bK.L.et = super.ay;
        this.bK.L.ey = super.aA;
        this.k();
        this.bN = false;
    }
    
    public void i() {
        this.bK = null;
    }
    
    void j() {
        this.bK.F = false;
        this.bK = null;
        this.k();
        this.bN = false;
    }
    
    void a(final ab ab) {
        if (this.bK != null) {
            ab.goto -= super.a7;
            ab.else -= super.a6;
            this.bK.do(ab);
            if (ab.goto >= 0 && ab.goto < super.ay && ab.else >= 0 && ab.else < super.aA) {
                ab.i = true;
            }
            ab.goto += super.a7;
            ab.else += super.a6;
        }
    }
    
    public void e() {
        if (!super.do) {
            return;
        }
        if (this.bK != null && this.bK.goto) {
            if (this.bJ) {
                if (this.bL && !super.void.M) {
                    super.aE.s = super.ay;
                    super.aE.j = super.aA;
                    super.aE.h = super.a7;
                    super.aE.g = super.a6;
                    super.aE.p = super.void.else.p;
                    super.aE.i = super.void.else.i;
                    super.aE.t = super.void.else.t;
                    if (this.bK != null) {
                        this.bK.L.a(super.aE);
                    }
                    this.bL = false;
                }
                this.bN = false;
                if (super.bh.h.g == 2) {
                    this.bK.a(true);
                }
                else {
                    this.bK.a(false);
                }
            }
            else if (!this.bN) {
                if (!super.void.M) {
                    super.aE.p = this.bI;
                    super.aE.s = super.ay;
                    super.aE.j = super.aA;
                    super.aE.h = 0;
                    super.aE.g = 0;
                    super.aE.i = super.ay;
                    super.aE.t = super.aA;
                    if (this.bK != null) {
                        this.bK.L.a(super.aE);
                    }
                }
                this.bN = true;
                if (super.bh.h.g == 0) {
                    this.bK.a(false);
                }
                else {
                    this.bK.a(true);
                }
                if (!super.void.M && !this.bK.L.eM) {
                    ap.a(super.aE, super.void.else, super.a7, super.a6, super.ay, super.aA);
                }
                this.bL = true;
            }
            else if (super.void.M) {
                if (super.bh.h.g == 0) {
                    this.bK.a(false);
                }
                else {
                    this.bK.a(true);
                }
            }
            else if (!this.bK.L.eM) {
                ap.a(super.aE, super.void.else, super.a7, super.a6, super.ay, super.aA);
            }
        }
        else if (super.a8 < 0) {
            final int n = (super.a7 > 0) ? super.a7 : 0;
            if (n >= super.void.else.i) {
                return;
            }
            final int n2 = (super.a7 + super.ay > super.void.else.i) ? super.void.else.i : (super.a7 + super.ay);
            if (n2 < 0) {
                return;
            }
            final int n3 = (super.a6 > 0) ? super.a6 : 0;
            if (n3 >= super.void.else.t) {
                return;
            }
            final int n4 = (super.a6 + super.aA > super.void.else.t) ? super.void.else.t : (super.a6 + super.aA);
            if (n4 < 0) {
                return;
            }
            int n5 = n3 * super.void.else.i;
            for (int i = n3; i < n4; ++i) {
                for (int j = n; j < n2; ++j) {
                    super.void.else.p[n5 + j] = (super.a8 | 0xFF000000);
                }
                n5 += super.void.else.i;
            }
        }
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean if1 = super.if;
        super.if = false;
        if (this.bK != null) {
            final boolean b2 = b | this.for(n);
            if (this.bK.L.et != super.ay || this.bK.L.ey != super.aA) {
                this.bK.L.et = super.ay;
                this.bK.L.ey = super.aA;
                this.k();
                this.bL = true;
            }
            b = (this.bK.do(n) | b2);
        }
        if (!b && this.bJ) {
            this.bJ = b;
            return super.do | if1;
        }
        this.bJ = b;
        return (this.bJ & super.do) | if1;
    }
    
    public ad h() {
        try {
            final ad ad = new ad();
            ad.i = 0;
            ad.t = 0;
            ad.h = super.ay;
            ad.g = super.aA;
            ad.p = new int[super.ay * super.aA];
            for (int i = 0; i < super.aA; ++i) {
                for (int j = 0; j < super.ay; ++j) {
                    ad.p[j + i * super.ay] = super.void.else.p[j + super.a7 + (i + super.a6) * super.void.else.i];
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
