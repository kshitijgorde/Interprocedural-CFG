// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class aq
{
    public String n;
    public int do;
    public int e;
    public byte[] l;
    public aq long;
    public aq[] try;
    public int k;
    public boolean b;
    public boolean void;
    public boolean for;
    public boolean h;
    public int c;
    public int if;
    public ar char;
    public ap m;
    public ap int;
    public aq goto;
    public int a;
    public boolean byte;
    public boolean new;
    public boolean case;
    public int j;
    private int g;
    public an f;
    boolean else;
    boolean d;
    int i;
    
    public aq(final an f) {
        this.n = null;
        this.do = 0;
        this.e = 0;
        this.l = null;
        this.long = null;
        this.try = null;
        this.k = 0;
        this.b = false;
        this.void = false;
        this.for = false;
        this.h = false;
        this.c = 0;
        this.if = 0;
        this.char = null;
        this.m = null;
        this.int = null;
        this.goto = null;
        this.a = 0;
        this.byte = false;
        this.new = false;
        this.case = false;
        this.j = 0;
        this.g = 0;
        this.f = null;
        this.else = false;
        this.d = false;
        this.i = 0;
        this.f = f;
    }
    
    public int a(final byte[] array, final int n, final int n2, final boolean[] array2) {
        try {
            if (this.g == 50) {
                Thread.yield();
                this.g = 0;
            }
            ++this.g;
            if (this.f.G) {
                array2[0] = true;
                return 0;
            }
            while (!this.b && this.do - n < 1000) {
                if (this.f.G) {
                    array2[0] = true;
                    return 0;
                }
                try {
                    Thread.yield();
                    Thread.sleep(100L);
                    Thread.yield();
                }
                catch (Exception ex2) {}
            }
            final int do1 = this.do;
            if (this.l != null) {
                if (this.else) {
                    array2[0] = true;
                    return 0;
                }
                if (this.d) {
                    return -1;
                }
                int n3 = do1 - this.i;
                if (n3 > n2) {
                    n3 = n2;
                }
                synchronized (this) {
                    System.arraycopy(this.l, this.i, array, n, n3);
                }
                this.i += n3;
                if (this.b && this.i >= this.do) {
                    array2[0] = (this.else = true);
                }
                return n3;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public ap a(final ap ap) {
        if (this.a == 1 || this.a == 2) {
            ap.x = this.char.new;
            ap.s = this.char.do;
            ap.r = this.char.else;
            ap.byte = this.char.int;
            ap.v = this.char.goto;
            ap.w = this.char.char;
            ap.g = this.char.try;
            ap.long = this;
            ++this.j;
            return ap;
        }
        if (this.a == 3) {
            this.m.long = this;
            ++this.j;
            return this.m;
        }
        if (this.a == 4) {
            ++this.j;
            final ap a = this.int.a(null, this);
            a.f = ap.f;
            a.t = ap.t;
            return a;
        }
        return ap;
    }
    
    public void a() {
        if (this.j < -10) {
            return;
        }
        --this.j;
        if (this.int != null) {
            this.int.int();
            if (this.j <= 0) {
                this.int.if();
            }
        }
        if (this.m != null) {
            this.m.int();
        }
        if (this.j <= 0) {
            this.byte = false;
            if (this.char != null) {
                this.char.new = null;
            }
            this.char = null;
            this.m = null;
            this.int = null;
            if (this.goto != null && this.goto != this) {
                this.goto.a();
            }
            this.goto = null;
            this.case = false;
            this.new = false;
        }
    }
}