// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a8
{
    public char[] e;
    protected boolean if;
    protected ac void;
    protected ae goto;
    protected boolean do;
    public int c;
    public boolean else;
    public boolean try;
    public boolean b;
    public boolean char;
    static final int case = 1;
    static final int d = 2;
    static final int long = 3;
    static final int a = 4;
    public boolean new;
    protected char[] byte;
    int for;
    a3 int;
    
    public a8() {
        this.e = null;
        this.if = false;
        this.goto = null;
        this.do = true;
        this.c = 0;
        this.else = false;
        this.try = false;
        this.b = true;
        this.char = false;
        this.new = false;
        this.byte = new char[1];
        this.for = 0;
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, ac.K) == 0) {
            this.do = al.a(a3);
            this.if = true;
        }
    }
    
    protected void a() {
        if (this.e == null || g.a(this.e) == 0) {
            this.e = (String.valueOf(ac.I) + this.void.Z + "\u0000").toCharArray();
            final ac void1 = this.void;
            ++void1.Z;
        }
    }
    
    public a3 a(final char[] array) {
        return ac.a(new a3());
    }
    
    public boolean a(final long n) {
        return false;
    }
    
    public void if() {
    }
}
