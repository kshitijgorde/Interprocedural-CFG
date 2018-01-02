// 
// Decompiled by Procyon v0.5.30
// 

package z;

enum X
{
    a("placeholder", 0), 
    b("lowRes", 1), 
    c("highRes", 2);
    
    private static final /* synthetic */ X[] d;
    
    public static X[] a() {
        return X.d.clone();
    }
    
    private X(final String s, final int n) {
    }
    
    static {
        d = new X[] { X.a, X.b, X.c };
    }
}
