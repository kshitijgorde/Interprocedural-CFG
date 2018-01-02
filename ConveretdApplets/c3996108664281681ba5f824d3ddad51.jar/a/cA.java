// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cA
{
    private static Boolean q;
    private static Boolean w;
    public static boolean q;
    
    public static cy q(final bH bh, final cz cz) {
        if (q()) {
            return new cD(bh, cz);
        }
        if (w()) {
            return new cE(bh, cz);
        }
        return new cE(bh, cz);
    }
    
    public static cy q(final bH bh, final bp bp) {
        if (q()) {
            return new cD(bh, bp);
        }
        if (w()) {
            return new cE(bh, bp);
        }
        return new cE(bh, bp);
    }
    
    private static synchronized boolean q() {
        if (cA.q == null) {
            cA.q = Boolean.FALSE;
            try {
                Class.forName("netscape.security.UserDialogHelper");
                cA.q = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return cA.q;
    }
    
    private static synchronized boolean w() {
        if (cA.w == null) {
            cA.w = Boolean.FALSE;
            try {
                Class.forName("com.ms.security.PolicyEngine");
                cA.w = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return cA.w;
    }
    
    static {
        cA.q = false;
        if (w()) {
            cA.q = true;
        }
        if (q()) {
            cA.q = cC.q();
        }
        if (!cA.q && !w() && !q()) {
            try {
                cA.q = true;
            }
            catch (Exception ex) {}
        }
    }
}
