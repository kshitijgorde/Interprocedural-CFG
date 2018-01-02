// 
// Decompiled by Procyon v0.5.30
// 

package ji.sec;

import netscape.security.PrivilegeManager;
import java.net.URLConnection;

public class bf
{
    static boolean a;
    
    private static final void a() {
        bf.a = (g.a() && !g.b());
    }
    
    public static void a(final String s) {
    }
    
    public static final void a(final URLConnection urlConnection, final String s) throws Exception {
        a(s);
        a();
        try {
            if (bf.a && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        urlConnection.connect();
    }
    
    static {
        bf.a = false;
    }
}
