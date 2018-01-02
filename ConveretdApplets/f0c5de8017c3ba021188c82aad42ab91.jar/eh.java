import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class eh
{
    public static final boolean a() {
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            try {
                securityManager.checkPropertyAccess("browser");
                if (System.getProperty("browser") != null) {
                    if (n.b()) {
                        n.b("found applet/browser-property, assuming an applet");
                    }
                    return true;
                }
                if (n.b()) {
                    n.b("did not find applet/browser property");
                }
                return false;
            }
            catch (SecurityException ex) {
                if (n.b()) {
                    n.b("couldn't access browser-property because of security restriction, assuming an applet", ex);
                }
                return true;
            }
        }
        return true;
    }
    
    public static final String b() {
        try {
            return new InputStreamReader(System.in).getEncoding();
        }
        catch (Exception ex) {
            if (n.a()) {
                n.a("cannot access system's default charset, providing empty string");
            }
            return "";
        }
    }
}
