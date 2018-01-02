// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public final class bi implements Runnable
{
    private static Thread b;
    private static boolean j;
    
    public static void c() {
        bi.j = true;
        (bi.b = new Thread(new bi(), "RssKeepAliveThread")).start();
    }
    
    public static void a() {
        if (O.a != null) {
            O.a.c();
        }
        O.a = null;
        O.a = null;
        try {
            bi.j = false;
            if (bi.b != null && bi.b.isAlive()) {
                bi.b.stop();
            }
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        try {
            while (bi.j) {
                if (O.a != null && O.a != null) {
                    O.a.i.E(O.a);
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
        bi.j = false;
    }
}
