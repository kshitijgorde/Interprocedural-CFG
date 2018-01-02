// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bq extends b5
{
    a4[] ct;
    private char[] cu;
    
    public bq() {
        this.cu = new char[] { 'c', 'o', 'm', 'p', 'a', 's', 's', '\0' };
    }
    
    public void if() {
        super.if();
        this.cu = null;
        this.ct = null;
    }
    
    public void a(final an b, final bh bh, final aq aq, final ae bs) {
        super.b = b;
        this.ct = new a4[3];
        for (int i = 0; i < 3; ++i) {
            this.ct[i] = new a4();
        }
        super.case = this.cu;
        super.bs = bs;
        super.a(bh, aq);
        this.if(bh);
        super.byte = true;
    }
    
    public boolean a(final long n) {
        this.void();
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        if (super.goto) {
            b |= this.for(n);
        }
        return (b & super.for) | do1;
    }
    
    public void c() {
        if (super.for && super.aI != -1 && super.aK != -1) {
            if (super.bs.i.C == null) {
                return;
            }
            float az;
            if (super.bs.i.C != null) {
                az = super.aZ + super.bs.i.C.W.cE - super.bs.i.C.T;
            }
            else {
                az = super.aZ;
            }
            this.ct[0].int = super.a5 + super.bh;
            this.ct[0].for = super.a4 + super.bg;
            final int n = 2000;
            if (super.bs.i.C.W.eI == 0.0f) {
                super.do = true;
                return;
            }
            final float n2 = (float)Math.atan2(super.bs.i.C.W.eM >> 1, super.bs.i.C.W.eI);
            if (Math.cos(super.bs.i.C.W.cx) >= 0.0) {
                this.ct[1].int = (int)(-n * (float)Math.sin(az + n2)) + this.ct[0].int;
                this.ct[1].for = (int)(-n * (float)Math.cos(az + n2)) + this.ct[0].for;
                this.ct[2].int = (int)(-n * (float)Math.sin(az - n2)) + this.ct[0].int;
                this.ct[2].for = (int)(-n * (float)Math.cos(az - n2)) + this.ct[0].for;
            }
            else {
                final float n3 = 3.1415927f;
                this.ct[1].int = (int)(-n * (float)Math.sin(n3 + az + n2)) + this.ct[0].int;
                this.ct[1].for = (int)(-n * (float)Math.cos(n3 + az + n2)) + this.ct[0].for;
                this.ct[2].int = (int)(-n * (float)Math.sin(n3 + az - n2)) + this.ct[0].int;
                this.ct[2].for = (int)(-n * (float)Math.cos(n3 + az - n2)) + this.ct[0].for;
            }
            this.a(this.ct, super.b.goto.x, super.b.goto.s, super.b.goto.r);
            final int n4 = (super.bh > 0) ? super.bh : 0;
            final int n5 = (super.b.goto.s < super.bh + super.aI) ? super.b.goto.s : (super.bh + super.aI);
            final int n6 = (super.bg > 0) ? super.bg : 0;
            final int n7 = (super.b.goto.r < super.bg + super.aK) ? super.b.goto.r : (super.bg + super.aK);
            a.a.a.a.a3.a(super.b.goto, (int)this.ct[0].int, (int)this.ct[0].for, (int)this.ct[1].int, (int)this.ct[1].for, n4, n5, n6, n7, super.aR);
            a.a.a.a.a3.a(super.b.goto, (int)this.ct[0].int - 1, (int)this.ct[0].for, (int)this.ct[1].int - 1, (int)this.ct[1].for, n4, n5, n6, n7, super.aR);
            a.a.a.a.a3.a(super.b.goto, (int)this.ct[0].int - 1, (int)(this.ct[0].for - 1.0f), (int)this.ct[1].int - 1, (int)(this.ct[1].for - 1.0f), n4, n5, n6, n7, super.aR);
            a.a.a.a.a3.a(super.b.goto, (int)this.ct[0].int, (int)(this.ct[0].for - 1.0f), (int)this.ct[1].int, (int)(this.ct[1].for - 1.0f), n4, n5, n6, n7, super.aR);
            a.a.a.a.a3.a(super.b.goto, (int)this.ct[0].int, (int)this.ct[0].for, (int)this.ct[2].int, (int)this.ct[2].for, n4, n5, n6, n7, super.aR);
            a.a.a.a.a3.a(super.b.goto, (int)this.ct[0].int - 1, (int)this.ct[0].for, (int)this.ct[2].int - 1, (int)this.ct[2].for, n4, n5, n6, n7, super.aR);
            a.a.a.a.a3.a(super.b.goto, (int)this.ct[0].int - 1, (int)(this.ct[0].for - 1.0f), (int)this.ct[2].int - 1, (int)(this.ct[2].for - 1.0f), n4, n5, n6, n7, super.aR);
            a.a.a.a.a3.a(super.b.goto, (int)this.ct[0].int, (int)(this.ct[0].for - 1.0f), (int)this.ct[2].int, (int)(this.ct[2].for - 1.0f), n4, n5, n6, n7, super.aR);
        }
    }
    
    private void a(final a4[] array, final int[] array2, final int n, final int n2) {
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
        int bg = (n5 > 0.0f) ? ((int)n5) : 0;
        int n8 = (int)array[n7].for;
        int n9 = (int)array[n6].for;
        for (int i = 2; i > 0; --i) {
            final a4 a4;
            final a4 a5;
            final float n11;
            float n10 = a4.int + (n11 = (a5.int - a4.int) * (1.0f / ((a5 = array[n6]).for - (a4 = array[(n6 + 1 > 2) ? 0 : (n6 + 1)]).for))) * (bg - a4.for);
            final a4 a6;
            final a4 a7;
            final float n13;
            float n12 = a6.int + (n13 = (a7.int - a6.int) * (1.0f / ((a7 = array[n7]).for - (a6 = array[(n7 - 1 < 0) ? 2 : (n7 - 1)]).for))) * (bg - a6.for);
            final float n14 = Math.min(Math.min(Math.min(n9, n8), super.bg + super.aK), n2);
            if (bg < super.bg) {
                n10 += (super.bg - bg) * n11;
                n12 += (super.bg - bg) * n13;
                bg = super.bg;
            }
            float n15 = bg * n;
            while (bg < n14) {
                n10 += n11;
                n12 += n13;
                final int n16;
                final int n17;
                if ((n16 = ((n10 > super.bh) ? ((int)n10) : super.bh)) < (n17 = ((n12 > super.bh + super.aI) ? (super.bh + super.aI) : ((int)n12)))) {
                    final int n18 = (int)((n16 < 0) ? n15 : (n16 + n15));
                    for (int n19 = (int)((n17 >= n) ? (n15 + n) : (n17 + n15)), j = n18; j < n19; ++j) {
                        array2[j] = (0xFF000000 | (super.aV >> 1 & 0x7F7F7F) + (array2[j] >> 1 & 0x7F7F7F));
                    }
                }
                n15 += n;
                ++bg;
            }
            if (bg == n2) {
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
    
    void a(final am am) {
        if (super.for) {
            if (!am.i && am.goto >= super.bh && am.goto < super.bh + super.aI && am.else >= super.bg && am.else < super.bg + super.aK && (super.a1 || am.if != 4)) {
                super.bj = true;
            }
            else {
                super.bj = false;
            }
            if (super.a0 && am.if == 1) {
                super.a0 = false;
            }
            else if ((!am.i && super.bj && am.if == 0) || (super.a0 && am.if == 4)) {
                super.a0 = true;
                final float n = (float)Math.atan2(super.a5 - (am.goto - super.bh), super.a4 - (am.else - super.bg));
                if (super.bs.i.C != null) {
                    super.bs.i.C.W.ek = false;
                    super.bs.i.C.W.cE = n + super.bs.i.C.T - super.aZ;
                    super.bs.i.C.long = true;
                }
            }
        }
    }
}
