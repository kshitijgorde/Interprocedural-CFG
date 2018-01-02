// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class c
{
    private int a;
    private int do;
    private int if;
    private String new;
    private a int;
    private String for;
    
    public c(final int n, final int n2, final int n3, final a a, final String s, final String s2) {
        this.a(n);
        this.do(n2);
        this.if(n3);
        this.a(a);
        this.a(s);
        this.if(s2);
    }
    
    public c(final int n, final int n2, final int n3, final a a, final String s) {
        this(n, n2, n3, a, s, "");
    }
    
    public c(final int n, final int n2, final int n3, final String s) {
        this(n, n2, n3, (a)new b.b(), s);
    }
    
    public String do() {
        String s = String.valueOf(this.new()) + "." + this.int() + "." + this.for() + this.try().toString();
        if (this.a() != null && !this.a().equalsIgnoreCase("")) {
            s = String.valueOf(s) + "-" + this.a();
        }
        return s;
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public int new() {
        return this.a;
    }
    
    public void do(final int do1) {
        this.do = do1;
    }
    
    public int int() {
        return this.do;
    }
    
    public void if(final int if1) {
        this.if = if1;
    }
    
    public int for() {
        return this.if;
    }
    
    public void a(final String new1) {
        this.new = new1;
    }
    
    public String a() {
        return this.new;
    }
    
    public void a(final a int1) {
        this.int = int1;
    }
    
    public a try() {
        return this.int;
    }
    
    public void if(final String for1) {
        this.for = for1;
    }
    
    public String if() {
        return this.for;
    }
}
