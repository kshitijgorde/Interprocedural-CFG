// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class ei
{
    private static Boolean q;
    private static Boolean w;
    public static boolean q;
    
    public static eg q(final cU cu, final eh eh) {
        if (q()) {
            return new el(cu, eh);
        }
        if (w()) {
            return new em(cu, eh);
        }
        return new em(cu, eh);
    }
    
    public static eg q(final cU cu, final cz cz) {
        if (q()) {
            return new el(cu, cz);
        }
        if (w()) {
            return new em(cu, cz);
        }
        return new em(cu, cz);
    }
    
    private static synchronized boolean q() {
        if (ei.q == null) {
            ei.q = Boolean.FALSE;
            try {
                Class.forName("netscape.security.UserDialogHelper");
                ei.q = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return ei.q;
    }
    
    private static synchronized boolean w() {
        if (ei.w == null) {
            ei.w = Boolean.FALSE;
            try {
                Class.forName("com.ms.security.PolicyEngine");
                ei.w = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return ei.w;
    }
    
    static {
        ei.q = false;
        if (w()) {
            ei.q = true;
        }
        if (q()) {
            ei.q = ek.q();
        }
        if (!ei.q && !w() && !q()) {
            try {
                ei.q = true;
            }
            catch (Exception ex) {}
        }
    }
}
