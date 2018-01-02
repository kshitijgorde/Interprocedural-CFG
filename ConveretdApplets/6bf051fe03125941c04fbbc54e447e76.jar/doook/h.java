// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public final class h implements Runnable
{
    private static Thread a;
    private static boolean enabled;
    
    public static void a() {
        h.enabled = true;
        (h.a = new Thread(new h(), "RssKeepAliveThread")).start();
    }
    
    public static void b() {
        if (bj.a != null) {
            bj.a.stop();
        }
        bj.a = null;
        bj.b = null;
        try {
            h.enabled = false;
            if (h.a != null && h.a.isAlive()) {
                h.a.stop();
            }
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        try {
            while (h.enabled) {
                if (bj.a != null && bj.b != null) {
                    bj.a.g.p(bj.b);
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
        h.enabled = false;
    }
}
