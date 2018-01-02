// 
// Decompiled by Procyon v0.5.30
// 

public class aa
{
    public String l;
    public int if;
    public int d;
    public byte[] k;
    public aa goto;
    public aa[] int;
    public int j;
    public boolean void;
    public boolean long;
    public boolean do;
    public boolean g;
    public int b;
    public int new;
    public an case;
    public aa else;
    public int a;
    public boolean try;
    public boolean for;
    public boolean byte;
    public int i;
    private int f;
    public l e;
    boolean char;
    boolean c;
    int h;
    
    public aa(final l e) {
        this.l = null;
        this.if = 0;
        this.d = 0;
        this.k = null;
        this.goto = null;
        this.int = null;
        this.j = 0;
        this.void = false;
        this.long = false;
        this.do = false;
        this.g = false;
        this.b = 0;
        this.new = 0;
        this.case = null;
        this.else = null;
        this.a = 0;
        this.try = false;
        this.for = false;
        this.byte = false;
        this.i = 0;
        this.f = 0;
        this.e = null;
        this.char = false;
        this.c = false;
        this.h = 0;
        this.e = e;
    }
    
    int a(final byte[] array, final int n, final int n2, final boolean[] array2) {
        try {
            if (this.f == 50) {
                Thread.yield();
                this.f = 0;
            }
            ++this.f;
            if (this.e.h) {
                array2[0] = true;
                return 0;
            }
            while (!this.void && this.if - n < 1000) {
                if (this.e.h) {
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
            final int if1 = this.if;
            if (this.k != null) {
                if (this.char) {
                    array2[0] = true;
                    return 0;
                }
                if (this.c) {
                    return -1;
                }
                int n3 = if1 - this.h;
                if (n3 > n2) {
                    n3 = n2;
                }
                synchronized (this) {
                    System.arraycopy(this.k, this.h, array, n, n3);
                }
                this.h += n3;
                if (this.void && this.h >= this.if) {
                    array2[0] = (this.char = true);
                }
                return n3;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public an a(final an an) {
        if (this.a == 1 || this.a == 2) {
            an.c = this.case.c;
            an.long = this.case.long;
            an.e = this.case.e;
            an.for = this.case.for;
            an.f = this.case.f;
            an.byte = this;
            ++this.i;
            return an;
        }
        return an;
    }
    
    public void a() {
        --this.i;
        if (this.i <= 0) {
            this.try = false;
            this.case = null;
            if (this.else != null && this != this.else) {
                this.else.a();
            }
            this.else = null;
            this.byte = false;
            this.for = false;
        }
    }
}
