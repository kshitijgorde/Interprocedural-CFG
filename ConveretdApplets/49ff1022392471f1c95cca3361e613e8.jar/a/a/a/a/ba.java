// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ba extends bm
{
    aq[] cc;
    private char[] cd;
    
    public ba() {
        this.cd = new char[] { 'c', 'o', 'm', 'p', 'a', 's', 's', '\0' };
    }
    
    public void a(final ac void1, final a2 a2, final ae ae, final v bh) {
        super.void = void1;
        this.cc = new aq[3];
        for (int i = 0; i < 3; ++i) {
            this.cc[i] = new aq();
        }
        super.byte = this.cd;
        super.bh = bh;
        super.a(a2, ae);
        this.if(a2);
        super.try = true;
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean if1 = super.if;
        super.if = false;
        if (super.else) {
            b |= this.for(n);
        }
        return (b & super.do) | if1;
    }
    
    public void e() {
        if (super.do && super.ay != -1 && super.aA != -1) {
            if (super.bh.h.r == null) {
                return;
            }
            float ap;
            if (super.bh.h.r != null) {
                ap = super.aP + super.bh.h.r.L.cn - super.bh.h.r.I;
            }
            else {
                ap = super.aP;
            }
            this.cc[0].int = super.aV + super.a7;
            this.cc[0].for = super.aU + super.a6;
            final int n = 2000;
            if (super.bh.h.r.L.ep == 0.0f) {
                super.if = true;
                return;
            }
            final float n2 = (float)Math.atan2(super.bh.h.r.L.et >> 1, super.bh.h.r.L.ep);
            if (Math.cos(super.bh.h.r.L.cg) >= 0.0) {
                this.cc[1].int = (int)(-n * (float)Math.sin(ap + n2)) + this.cc[0].int;
                this.cc[1].for = (int)(-n * (float)Math.cos(ap + n2)) + this.cc[0].for;
                this.cc[2].int = (int)(-n * (float)Math.sin(ap - n2)) + this.cc[0].int;
                this.cc[2].for = (int)(-n * (float)Math.cos(ap - n2)) + this.cc[0].for;
            }
            else {
                final float n3 = 3.1415927f;
                this.cc[1].int = (int)(-n * (float)Math.sin(n3 + ap + n2)) + this.cc[0].int;
                this.cc[1].for = (int)(-n * (float)Math.cos(n3 + ap + n2)) + this.cc[0].for;
                this.cc[2].int = (int)(-n * (float)Math.sin(n3 + ap - n2)) + this.cc[0].int;
                this.cc[2].for = (int)(-n * (float)Math.cos(n3 + ap - n2)) + this.cc[0].for;
            }
            this.a(this.cc, super.void.else.p, super.void.else.i, super.void.else.t);
            this.a(super.void.else, (int)this.cc[0].int, (int)this.cc[0].for, (int)this.cc[1].int, (int)this.cc[1].for, super.aH);
            this.a(super.void.else, (int)this.cc[0].int - 1, (int)this.cc[0].for, (int)this.cc[1].int - 1, (int)this.cc[1].for, super.aH);
            this.a(super.void.else, (int)this.cc[0].int - 1, (int)(this.cc[0].for - 1.0f), (int)this.cc[1].int - 1, (int)(this.cc[1].for - 1.0f), super.aH);
            this.a(super.void.else, (int)this.cc[0].int, (int)(this.cc[0].for - 1.0f), (int)this.cc[1].int, (int)(this.cc[1].for - 1.0f), super.aH);
            this.a(super.void.else, (int)this.cc[0].int, (int)this.cc[0].for, (int)this.cc[2].int, (int)this.cc[2].for, super.aH);
            this.a(super.void.else, (int)this.cc[0].int - 1, (int)this.cc[0].for, (int)this.cc[2].int - 1, (int)this.cc[2].for, super.aH);
            this.a(super.void.else, (int)this.cc[0].int - 1, (int)(this.cc[0].for - 1.0f), (int)this.cc[2].int - 1, (int)(this.cc[2].for - 1.0f), super.aH);
            this.a(super.void.else, (int)this.cc[0].int, (int)(this.cc[0].for - 1.0f), (int)this.cc[2].int, (int)(this.cc[2].for - 1.0f), super.aH);
        }
    }
    
    private void a(final aq[] array, final int[] array2, final int n, final int n2) {
        if (array2 == null) {
            return;
        }
        if ((n < array[0].int && n < array[1].int && n < array[2].int) || (array[0].int < 0.0f && array[1].int < 0.0f && array[2].int < 0.0f) || (n2 < array[0].for && n2 < array[1].for && n2 < array[2].for) || (array[0].for < 0.0f && array[1].for < 0.0f && array[2].for < 0.0f)) {
            return;
        }
        final float for1 = array[0].for;
        int n3 = 0;
        final float n4 = (for1 > array[1].for) ? array[n3 = 1].for : for1;
        final float n5 = (n4 > array[2].for) ? array[n3 = 2].for : n4;
        int n6 = (n3 - 1 < 0) ? 2 : (n3 - 1);
        int n7 = (n3 + 1 > 2) ? 0 : (n3 + 1);
        int a6 = (n5 > 0.0f) ? ((int)n5) : 0;
        int n8 = (int)array[n7].for;
        int n9 = (int)array[n6].for;
        for (int i = 2; i > 0; --i) {
            final aq aq;
            final aq aq2;
            final float n11;
            float n10 = aq.int + (n11 = (aq2.int - aq.int) * (1.0f / ((aq2 = array[n6]).for - (aq = array[(n6 + 1 > 2) ? 0 : (n6 + 1)]).for))) * (a6 - aq.for);
            final aq aq3;
            final aq aq4;
            final float n13;
            float n12 = aq3.int + (n13 = (aq4.int - aq3.int) * (1.0f / ((aq4 = array[n7]).for - (aq3 = array[(n7 - 1 < 0) ? 2 : (n7 - 1)]).for))) * (a6 - aq3.for);
            final float n14 = Math.min(Math.min(Math.min(n9, n8), super.a6 + super.aA), n2);
            if (a6 < super.a6) {
                n10 += (super.a6 - a6) * n11;
                n12 += (super.a6 - a6) * n13;
                a6 = super.a6;
            }
            float n15 = a6 * n;
            while (a6 < n14) {
                n10 += n11;
                n12 += n13;
                final int n16;
                final int n17;
                if ((n16 = ((n10 > super.a7) ? ((int)n10) : super.a7)) < (n17 = ((n12 > super.a7 + super.ay) ? (super.a7 + super.ay) : ((int)n12)))) {
                    final int n18 = (int)((n16 < 0) ? n15 : (n16 + n15));
                    for (int n19 = (int)((n17 >= n) ? (n15 + n) : (n17 + n15)), j = n18; j < n19; ++j) {
                        array2[j] = (0xFF000000 | (super.aL >> 1 & 0x7F7F7F) + (array2[j] >> 1 & 0x7F7F7F));
                    }
                }
                n15 += n;
                ++a6;
            }
            if (a6 == n2) {
                return;
            }
            if (n9 > n8) {
                n8 = (int)array[n7 = ((n7 + 1 > 2) ? 0 : (n7 + 1))].for;
            }
            else if (n9 < n8) {
                n9 = (int)array[n6 = ((n6 - 1 < 0) ? 2 : (n6 - 1))].for;
            }
            else {
                n8 = (int)array[n7 = ((n7 + 1 > 2) ? 0 : (n7 + 1))].for;
                n9 = (int)array[n6 = ((n6 - 1 < 0) ? 2 : (n6 - 1))].for;
                --i;
            }
        }
    }
    
    private void a(final ad ad, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        int n6;
        if (n < n3) {
            n6 = 1;
        }
        else {
            n6 = -1;
        }
        int n7;
        if (n2 < n4) {
            n7 = 1;
        }
        else {
            n7 = -1;
        }
        int n8 = n;
        int n9 = n2;
        final int n10 = (super.a7 > 0) ? super.a7 : 0;
        final int n11 = (ad.i < super.a7 + super.ay) ? ad.i : (super.a7 + super.ay);
        final int n12 = (super.a6 > 0) ? super.a6 : 0;
        final int n13 = (ad.t < super.a6 + super.aA) ? ad.t : (super.a6 + super.aA);
        if (abs > abs2) {
            int n14 = abs >> 1;
            for (int i = 0; i < abs; ++i) {
                n8 += n6;
                n14 += abs2;
                if (n14 > abs) {
                    n14 -= abs;
                    n9 += n7;
                }
                if (n8 >= n10 && n8 < n11 && n9 >= n12 && n9 < n13) {
                    ad.p[n8 + n9 * ad.i] = n5;
                }
            }
        }
        else {
            int n15 = abs2 >> 1;
            for (int j = 0; j < abs2; ++j) {
                n9 += n7;
                n15 += abs;
                if (n15 > abs2) {
                    n15 -= abs2;
                    n8 += n6;
                }
                if (n8 >= n10 && n8 < n11 && n9 >= n12 && n9 < n13) {
                    ad.p[n8 + n9 * ad.i] = n5;
                }
            }
        }
    }
    
    void a(final ab ab) {
        if (super.do) {
            if (!ab.i && ab.goto >= super.a7 && ab.goto < super.a7 + super.ay && ab.else >= super.a6 && ab.else < super.a6 + super.aA && (super.aR || ab.if != 4)) {
                super.a9 = true;
            }
            else {
                super.a9 = false;
            }
            if (super.aQ && ab.if == 1) {
                super.aQ = false;
            }
            else if ((!ab.i && super.a9 && ab.if == 0) || (super.aQ && ab.if == 4)) {
                super.aQ = true;
                final float n = (float)Math.atan2(super.aV - (ab.goto - super.a7), super.aU - (ab.else - super.a6));
                if (super.bh.h.r != null) {
                    super.bh.h.r.L.d1 = false;
                    super.bh.h.r.L.cn = n + super.bh.h.r.I - super.aP;
                    super.bh.h.r.do = true;
                }
            }
        }
    }
}
