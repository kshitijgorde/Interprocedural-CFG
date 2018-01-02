// 
// Decompiled by Procyon v0.5.30
// 

package z;

enum aq
{
    a("ICONS", 0), 
    b("LIST", 1);
    
    private static final /* synthetic */ aq[] c;
    
    public static aq[] a() {
        return aq.c.clone();
    }
    
    private aq(final String s, final int n) {
    }
    
    static {
        c = new aq[] { aq.a, aq.b };
    }
}
