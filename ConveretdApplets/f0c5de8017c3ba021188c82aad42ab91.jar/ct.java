// 
// Decompiled by Procyon v0.5.30
// 

public class ct extends am
{
    private int a;
    private String b;
    
    public ct(final String s, final int n) {
        this(s, n, null);
    }
    
    public ct(final String b, final int a, final String s) {
        super("HttpError:" + a + ":" + b);
        this.a = a;
        this.b = b;
        if (s != null) {
            this.b = this.b + " for " + s;
        }
    }
    
    public final int c() {
        return this.a;
    }
    
    public final String d() {
        return this.b;
    }
    
    public String toString() {
        return "HttpError: " + this.c() + " " + this.d();
    }
}
