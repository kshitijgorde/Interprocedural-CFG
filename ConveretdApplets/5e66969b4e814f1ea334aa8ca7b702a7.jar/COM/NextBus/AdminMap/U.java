// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

public final class U
{
    private static final String[] a;
    private static final String[] b;
    
    public static boolean a(final String s) {
        for (int i = 0; i < U.a.length; ++i) {
            if (s.indexOf(U.a[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean b(final String s) {
        for (int i = 0; i < U.b.length; ++i) {
            if (s.indexOf(U.b[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    static {
        a = new String[] { "actransit", "lametro", "wmata", "bawt", "las-vegas", "sf-muni", "guelph" };
        b = new String[] { "sf-muni", "lacmta" };
    }
}
