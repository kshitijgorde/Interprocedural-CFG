// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a8
{
    public char[] f;
    protected boolean do;
    protected ac b;
    protected ae long;
    protected boolean for;
    public int d;
    public boolean goto;
    public boolean byte;
    public boolean c;
    public boolean else;
    static final int char = 1;
    static final int e = 2;
    static final int void = 3;
    static final int if = 4;
    public boolean a;
    public boolean try;
    protected char[] case;
    int int;
    a3 new;
    
    public a8() {
        this.f = null;
        this.do = false;
        this.long = null;
        this.for = true;
        this.d = 0;
        this.goto = false;
        this.byte = false;
        this.c = true;
        this.else = false;
        this.a = false;
        this.try = false;
        this.case = new char[1];
        this.int = 0;
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.P) == 0) {
            this.for = al.a(a3);
            this.do = true;
        }
    }
    
    protected void a() {
        if (this.f == null || g.a(this.f) == 0) {
            this.f = (String.valueOf(ac.N) + this.b.ag + "\u0000").toCharArray();
            final ac b = this.b;
            ++b.ag;
        }
    }
    
    public a3 a(final char[] array) {
        return ac.a(new a3());
    }
    
    public boolean a(final long n) {
        return false;
    }
    
    public void if() {
        if (this.new != null) {
            this.new.a();
        }
        this.new = null;
        this.case = null;
        this.f = null;
        this.b = null;
        if (this.long != null) {
            this.long.a();
        }
        this.long = null;
    }
}
