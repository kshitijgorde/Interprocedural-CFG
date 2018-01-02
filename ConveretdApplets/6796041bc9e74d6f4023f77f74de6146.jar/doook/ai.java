// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public final class ai implements Runnable
{
    private static Thread d;
    private static boolean enabled;
    
    public static void q() {
        ai.enabled = true;
        (ai.d = new Thread(new ai(), "RssKeepAliveThread")).start();
    }
    
    public static void r() {
        if (S.a != null) {
            S.a.stop();
        }
        S.a = null;
        S.a = null;
        try {
            ai.enabled = false;
            if (ai.d != null && ai.d.isAlive()) {
                ai.d.stop();
            }
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        try {
            while (ai.enabled) {
                if (S.a != null && S.a != null) {
                    S.a.l.p(S.a);
                }
                Thread.sleep(20000L);
            }
        }
        catch (Exception ex) {
            System.out.println("Rss keep alive stopped by exception.");
            ex.printStackTrace();
        }
    }
    
    static {
        ai.enabled = false;
    }
}
