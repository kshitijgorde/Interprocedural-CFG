// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bW
{
    private static Boolean q;
    private static Boolean w;
    public static boolean q;
    
    public static cl q(final cq cq, final bY by) {
        if (q()) {
            return new bK(cq, by);
        }
        if (w()) {
            return new bJ(cq, by);
        }
        return new bJ(cq, by);
    }
    
    public static cl q(final cq cq, final l l) {
        if (q()) {
            return new bK(cq, l);
        }
        if (w()) {
            return new bJ(cq, l);
        }
        return new bJ(cq, l);
    }
    
    private static synchronized boolean q() {
        if (bW.q == null) {
            bW.q = Boolean.FALSE;
            try {
                Class.forName("netscape.security.UserDialogHelper");
                bW.q = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return bW.q;
    }
    
    private static synchronized boolean w() {
        if (bW.w == null) {
            bW.w = Boolean.FALSE;
            try {
                Class.forName("com.ms.security.PolicyEngine");
                bW.w = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return bW.w;
    }
    
    static {
        bW.q = false;
        if (w()) {
            bW.q = true;
        }
        if (q()) {
            bW.q = bQ.q();
        }
        if (!bW.q && !w() && !q()) {
            try {
                bW.q = true;
            }
            catch (Exception ex) {}
        }
    }
}
