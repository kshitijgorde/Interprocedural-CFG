// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ae
{
    public String m;
    public int if;
    public int e;
    public byte[] k;
    public ae long;
    public ae[] new;
    public int j;
    public boolean b;
    public boolean void;
    public boolean do;
    public boolean g;
    public int c;
    public int try;
    public af char;
    public ad l;
    public ad for;
    public ae goto;
    public int a;
    public boolean byte;
    public boolean int;
    public boolean case;
    public int i;
    private int f;
    boolean else;
    boolean d;
    int h;
    
    public ae() {
        this.m = null;
        this.if = 0;
        this.e = 0;
        this.k = null;
        this.long = null;
        this.new = null;
        this.j = 0;
        this.b = false;
        this.void = false;
        this.do = false;
        this.g = false;
        this.c = 0;
        this.try = 0;
        this.char = null;
        this.l = null;
        this.for = null;
        this.goto = null;
        this.a = 0;
        this.byte = false;
        this.int = false;
        this.case = false;
        this.i = 0;
        this.f = 0;
        this.else = false;
        this.d = false;
        this.h = 0;
    }
    
    int a(final byte[] array, final int n, final int n2, final boolean[] array2) {
        if (this.f == 50) {
            Thread.yield();
            this.f = 0;
        }
        ++this.f;
        while (this.e != this.if && this.if - n < 1000) {
            try {
                Thread.yield();
                Thread.sleep(100L);
                Thread.yield();
            }
            catch (Exception ex) {}
        }
        final int if1 = this.if;
        if (this.k == null) {
            return -1;
        }
        if (this.else) {
            array2[0] = true;
            return 0;
        }
        if (this.d) {
            return -1;
        }
        int n3 = if1 - this.h;
        if (n3 > n2) {
            n3 = n2;
        }
        System.arraycopy(this.k, this.h, array, n, n3);
        this.h += n3;
        if (this.h >= this.e) {
            array2[0] = (this.else = true);
        }
        return n3;
    }
    
    public ad a(final ad ad) {
        if (this.a == 1 || this.a == 2) {
            ad.p = this.char.new;
            ad.i = this.char.do;
            ad.t = this.char.case;
            ad.try = this.char.int;
            ad.u = this.char.char;
            ad.goto = this;
            ++this.i;
            return ad;
        }
        if (this.a == 3) {
            this.l.goto = this;
            ++this.i;
            return this.l;
        }
        if (this.a == 4) {
            ++this.i;
            return this.for.a(null, this);
        }
        return ad;
    }
    
    public void a() {
        --this.i;
        if (this.for != null) {
            this.for.int();
        }
        if (this.l != null) {
            this.l.int();
        }
        if (this.i == 0) {
            this.byte = false;
            this.char = null;
            this.l = null;
            this.for = null;
            this.goto = null;
            this.case = false;
            this.int = false;
        }
    }
}
