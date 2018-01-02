// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bU
{
    private static Boolean q;
    private static Boolean w;
    public static boolean q;
    
    public static cj q(final co co, final bW bw) {
        if (q()) {
            return new bI(co, bw);
        }
        if (w()) {
            return new bH(co, bw);
        }
        return new bH(co, bw);
    }
    
    public static cj q(final co co, final l l) {
        if (q()) {
            return new bI(co, l);
        }
        if (w()) {
            return new bH(co, l);
        }
        return new bH(co, l);
    }
    
    private static synchronized boolean q() {
        if (bU.q == null) {
            bU.q = Boolean.FALSE;
            try {
                Class.forName("netscape.security.UserDialogHelper");
                bU.q = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return bU.q;
    }
    
    private static synchronized boolean w() {
        if (bU.w == null) {
            bU.w = Boolean.FALSE;
            try {
                Class.forName("com.ms.security.PolicyEngine");
                bU.w = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return bU.w;
    }
    
    static {
        bU.q = false;
        if (w()) {
            bU.q = true;
        }
        if (q()) {
            bU.q = bO.q();
        }
        if (!bU.q && !w() && !q()) {
            try {
                bU.q = true;
            }
            catch (Exception ex) {}
        }
    }
}
