// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class o extends bm
{
    private int bv;
    int bn;
    private int bp;
    int a8;
    int bl;
    int bo;
    int bm;
    int bt;
    int bs;
    int br;
    int bq;
    private char[] bu;
    
    o(final int a8, final int bl, final int a9, final int a10, final int ay, final int aa) {
        this.bv = 0;
        this.bn = 0;
        this.bp = -1;
        this.a8 = -2236963;
        this.bl = -6216402;
        this.bo = 0;
        this.bm = 0;
        this.bt = 0;
        this.bs = 0;
        this.br = this.a8;
        this.bq = this.bl;
        this.bu = new char[] { 'p', 'r', 'o', 'g', 'r', 'e', 's', 's', 'b', 'a', 'r', '\0' };
        this.a8 = a8;
        this.bl = bl;
        super.a7 = a9;
        super.a6 = a10;
        super.ay = ay;
        super.aA = aa;
        super.try = true;
        super.byte = this.bu;
        super.do = true;
    }
    
    public void f() {
        this.bv = 0;
        this.bn = 0;
    }
    
    public void g() {
        super.a7 = this.bo;
        super.a6 = this.bm;
        super.ay = this.bt;
        super.aA = this.bs;
        this.a8 = this.br;
        this.bl = this.bq;
    }
    
    public void a(final a2 a2, final ae ae, final v bh) {
        super.bh = bh;
        super.ay = -1;
        super.aA = -1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                this.bo = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                this.bm = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                this.bt = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                this.bs = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                this.br = ac.a(a2.new[i]);
                if ((this.br & 0xFF000000) == 0x0) {
                    this.br |= 0xFF000000;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("fgcolor") == 0) {
                this.bq = ac.a(a2.new[i]);
                if ((this.bq & 0xFF000000) == 0x0) {
                    this.bq |= 0xFF000000;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.c = new Integer(a2.new[i]);
            }
        }
        this.if(a2);
        super.try = true;
    }
    
    void a(final ab ab) {
        if (super.do && (ab.if == 2 || ab.if == 3 || ab.if == 0 || ab.if == 1) && ab.goto >= super.a7 && ab.goto < super.a7 + super.ay && ab.else >= super.a6 && ab.else < super.a6 + super.aA) {
            ab.i = true;
        }
    }
    
    public boolean a(final long n) {
        this.for(n);
        if (this.bn == this.bv) {
            this.bv = 0;
            this.bn = 0;
        }
        if (this.bn != this.bp) {
            this.bp = this.bn;
            return true;
        }
        final boolean if1 = super.if;
        super.if = false;
        return if1 | super.do;
    }
    
    public void a(final int n) {
        this.bv += n;
    }
    
    public void e() {
        if (!super.do) {
            return;
        }
        int n = 0;
        if (this.bv != 0) {
            n = this.bn * super.ay / this.bv;
        }
        ap.if(super.void.else, super.a7, super.a6, n, super.aA, this.bl);
        ap.if(super.void.else, super.a7 + n, super.a6, super.ay - n, super.aA, this.a8);
    }
}
