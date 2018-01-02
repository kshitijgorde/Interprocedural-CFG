// 
// Decompiled by Procyon v0.5.30
// 

package z;

public enum Z
{
    a("ForSpeed", 0), 
    b("ForQuality", 1), 
    c("ForBestQuality", 2);
    
    private static final /* synthetic */ Z[] d;
    
    public static Z[] a() {
        return Z.d.clone();
    }
    
    private Z(final String s, final int n) {
    }
    
    static {
        d = new Z[] { Z.a, Z.b, Z.c };
    }
}
