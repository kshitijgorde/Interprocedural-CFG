// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dg
{
    private static Boolean q;
    private static Boolean w;
    public static boolean q;
    
    public static dB q(final dH dh, final di di) {
        if (q()) {
            return new cQ(dh, di);
        }
        if (w()) {
            return new cP(dh, di);
        }
        return new cP(dh, di);
    }
    
    public static dB q(final dH dh, final p p2) {
        if (q()) {
            return new cQ(dh, p2);
        }
        if (w()) {
            return new cP(dh, p2);
        }
        return new cP(dh, p2);
    }
    
    private static synchronized boolean q() {
        if (dg.q == null) {
            dg.q = Boolean.FALSE;
            try {
                Class.forName("netscape.security.UserDialogHelper");
                dg.q = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return dg.q;
    }
    
    private static synchronized boolean w() {
        if (dg.w == null) {
            dg.w = Boolean.FALSE;
            try {
                Class.forName("com.ms.security.PolicyEngine");
                dg.w = Boolean.TRUE;
            }
            catch (Exception ex) {}
            catch (Throwable t) {}
        }
        return dg.w;
    }
    
    static {
        dg.q = false;
        if (w()) {
            dg.q = true;
        }
        if (q()) {
            dg.q = cX.q();
        }
        if (!dg.q && !w() && !q()) {
            try {
                dg.q = true;
            }
            catch (Exception ex) {}
        }
    }
}
