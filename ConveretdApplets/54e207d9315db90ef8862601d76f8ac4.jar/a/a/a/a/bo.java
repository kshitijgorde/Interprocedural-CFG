// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bo
{
    public char[] f;
    protected boolean do;
    protected an b;
    protected aq long;
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
    bi new;
    
    public bo() {
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
    
    public void a(final char[] array, final bi bi) {
        if (i.do(array, an.Q) == 0) {
            this.for = az.a(bi);
            this.do = true;
        }
    }
    
    protected void a() {
        if (this.f == null || i.a(this.f) == 0) {
            this.f = (String.valueOf(an.O) + this.b.ai + "\u0000").toCharArray();
            final an b = this.b;
            ++b.ai;
        }
    }
    
    public bi a(final char[] array) {
        return an.a(new bi());
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
