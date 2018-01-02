// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ba extends bm
{
    aq[] cs;
    private char[] ct;
    
    public ba() {
        this.ct = new char[] { 'c', 'o', 'm', 'p', 'a', 's', 's', '\0' };
    }
    
    public void if() {
        super.if();
        this.ct = null;
        this.cs = null;
    }
    
    public void a(final ac b, final a2 a2, final ae ae, final v br) {
        super.b = b;
        this.cs = new aq[3];
        for (int i = 0; i < 3; ++i) {
            this.cs[i] = new aq();
        }
        super.case = this.ct;
        super.br = br;
        super.a(a2, ae);
        this.if(a2);
        super.byte = true;
    }
    
    public boolean a(final long n) {
        this.d();
        boolean b = false;
        final boolean do1 = super.do;
        super.do = false;
        if (super.goto) {
            b |= this.for(n);
        }
        return (b & super.for) | do1;
    }
    
    public void f() {
        if (super.for && super.aH != -1 && super.aJ != -1) {
            if (super.br.i.B == null) {
                return;
            }
            float ay;
            if (super.br.i.B != null) {
                ay = super.aY + super.br.i.B.U.cD - super.br.i.B.R;
            }
            else {
                ay = super.aY;
            }
            this.cs[0].int = super.a4 + super.bg;
            this.cs[0].for = super.a3 + super.bf;
            final int n = 2000;
            if (super.br.i.B.U.eH == 0.0f) {
                super.do = true;
                return;
            }
            final float n2 = (float)Math.atan2(super.br.i.B.U.eL >> 1, super.br.i.B.U.eH);
            if (Math.cos(super.br.i.B.U.cw) >= 0.0) {
                this.cs[1].int = (int)(-n * (float)Math.sin(ay + n2)) + this.cs[0].int;
                this.cs[1].for = (int)(-n * (float)Math.cos(ay + n2)) + this.cs[0].for;
                this.cs[2].int = (int)(-n * (float)Math.sin(ay - n2)) + this.cs[0].int;
                this.cs[2].for = (int)(-n * (float)Math.cos(ay - n2)) + this.cs[0].for;
            }
            else {
                final float n3 = 3.1415927f;
                this.cs[1].int = (int)(-n * (float)Math.sin(n3 + ay + n2)) + this.cs[0].int;
                this.cs[1].for = (int)(-n * (float)Math.cos(n3 + ay + n2)) + this.cs[0].for;
                this.cs[2].int = (int)(-n * (float)Math.sin(n3 + ay - n2)) + this.cs[0].int;
                this.cs[2].for = (int)(-n * (float)Math.cos(n3 + ay - n2)) + this.cs[0].for;
            }
            this.a(this.cs, super.b.goto.q, super.b.goto.j, super.b.goto.u);
            final int n4 = (super.bg > 0) ? super.bg : 0;
            final int n5 = (super.b.goto.j < super.bg + super.aH) ? super.b.goto.j : (super.bg + super.aH);
            final int n6 = (super.bf > 0) ? super.bf : 0;
            final int n7 = (super.b.goto.u < super.bf + super.aJ) ? super.b.goto.u : (super.bf + super.aJ);
            ap.a(super.b.goto, (int)this.cs[0].int, (int)this.cs[0].for, (int)this.cs[1].int, (int)this.cs[1].for, n4, n5, n6, n7, super.aQ);
            ap.a(super.b.goto, (int)this.cs[0].int - 1, (int)this.cs[0].for, (int)this.cs[1].int - 1, (int)this.cs[1].for, n4, n5, n6, n7, super.aQ);
            ap.a(super.b.goto, (int)this.cs[0].int - 1, (int)(this.cs[0].for - 1.0f), (int)this.cs[1].int - 1, (int)(this.cs[1].for - 1.0f), n4, n5, n6, n7, super.aQ);
            ap.a(super.b.goto, (int)this.cs[0].int, (int)(this.cs[0].for - 1.0f), (int)this.cs[1].int, (int)(this.cs[1].for - 1.0f), n4, n5, n6, n7, super.aQ);
            ap.a(super.b.goto, (int)this.cs[0].int, (int)this.cs[0].for, (int)this.cs[2].int, (int)this.cs[2].for, n4, n5, n6, n7, super.aQ);
            ap.a(super.b.goto, (int)this.cs[0].int - 1, (int)this.cs[0].for, (int)this.cs[2].int - 1, (int)this.cs[2].for, n4, n5, n6, n7, super.aQ);
            ap.a(super.b.goto, (int)this.cs[0].int - 1, (int)(this.cs[0].for - 1.0f), (int)this.cs[2].int - 1, (int)(this.cs[2].for - 1.0f), n4, n5, n6, n7, super.aQ);
            ap.a(super.b.goto, (int)this.cs[0].int, (int)(this.cs[0].for - 1.0f), (int)this.cs[2].int, (int)(this.cs[2].for - 1.0f), n4, n5, n6, n7, super.aQ);
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
        int bf = (n5 > 0.0f) ? ((int)n5) : 0;
        int n8 = (int)array[n7].for;
        int n9 = (int)array[n6].for;
        for (int i = 2; i > 0; --i) {
            final aq aq;
            final aq aq2;
            final float n11;
            float n10 = aq.int + (n11 = (aq2.int - aq.int) * (1.0f / ((aq2 = array[n6]).for - (aq = array[(n6 + 1 > 2) ? 0 : (n6 + 1)]).for))) * (bf - aq.for);
            final aq aq3;
            final aq aq4;
            final float n13;
            float n12 = aq3.int + (n13 = (aq4.int - aq3.int) * (1.0f / ((aq4 = array[n7]).for - (aq3 = array[(n7 - 1 < 0) ? 2 : (n7 - 1)]).for))) * (bf - aq3.for);
            final float n14 = Math.min(Math.min(Math.min(n9, n8), super.bf + super.aJ), n2);
            if (bf < super.bf) {
                n10 += (super.bf - bf) * n11;
                n12 += (super.bf - bf) * n13;
                bf = super.bf;
            }
            float n15 = bf * n;
            while (bf < n14) {
                n10 += n11;
                n12 += n13;
                final int n16;
                final int n17;
                if ((n16 = ((n10 > super.bg) ? ((int)n10) : super.bg)) < (n17 = ((n12 > super.bg + super.aH) ? (super.bg + super.aH) : ((int)n12)))) {
                    final int n18 = (int)((n16 < 0) ? n15 : (n16 + n15));
                    for (int n19 = (int)((n17 >= n) ? (n15 + n) : (n17 + n15)), j = n18; j < n19; ++j) {
                        array2[j] = (0xFF000000 | (super.aU >> 1 & 0x7F7F7F) + (array2[j] >> 1 & 0x7F7F7F));
                    }
                }
                n15 += n;
                ++bf;
            }
            if (bf == n2) {
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
    
    void a(final ab ab) {
        if (super.for) {
            if (!ab.i && ab.goto >= super.bg && ab.goto < super.bg + super.aH && ab.else >= super.bf && ab.else < super.bf + super.aJ && (super.a0 || ab.if != 4)) {
                super.bi = true;
            }
            else {
                super.bi = false;
            }
            if (super.aZ && ab.if == 1) {
                super.aZ = false;
            }
            else if ((!ab.i && super.bi && ab.if == 0) || (super.aZ && ab.if == 4)) {
                super.aZ = true;
                final float n = (float)Math.atan2(super.a4 - (ab.goto - super.bg), super.a3 - (ab.else - super.bf));
                if (super.br.i.B != null) {
                    super.br.i.B.U.ej = false;
                    super.br.i.B.U.cD = n + super.br.i.B.R - super.aY;
                    super.br.i.B.long = true;
                }
            }
        }
    }
}
