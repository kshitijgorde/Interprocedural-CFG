// 
// Decompiled by Procyon v0.5.30
// 

package z;

public class B
{
    private String a;
    private String b;
    private static /* synthetic */ boolean c;
    
    public B(final bg bg) {
        if (!B.c && !bg.b().equals("entry")) {
            throw new AssertionError();
        }
        this.a = G.c(bg.c("caption"));
        this.b = bg.c("action");
    }
    
    public final String toString() {
        return this.a;
    }
    
    public final String a() {
        return this.b;
    }
    
    static {
        B.c = !B.class.desiredAssertionStatus();
    }
}
