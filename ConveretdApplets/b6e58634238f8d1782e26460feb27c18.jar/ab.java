// 
// Decompiled by Procyon v0.5.30
// 

public class ab
{
    public static final ab a;
    public String b;
    public String c;
    public String d;
    
    public ab(final String b, String c, String d) {
        this.b = b;
        if (b == null) {
            c = null;
        }
        if ((this.c = c) == null) {
            d = null;
        }
        this.d = d;
    }
    
    public final boolean a() {
        return this.b == null;
    }
    
    public final boolean b() {
        return !this.a() && this.c != null;
    }
    
    public final void c() {
        this.c = null;
        this.b = null;
        this.d = null;
    }
    
    static {
        a = new ab(null, null, null);
    }
}
