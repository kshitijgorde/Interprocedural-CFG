// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Calendar;

public final class bu implements Runnable
{
    private static as l;
    private static Thread a;
    private static int az;
    private static boolean enabled;
    
    public static void a(final as as, final int az) {
        bu.l = as;
        b();
        if (az == 0) {
            if (bu.l.b == 1) {
                final aJ aj = new aJ(66305, 1);
                aj.a(0, 0, "mystatus=5");
                aj.C = -1;
                aj.j = bu.l.e();
                aj.a(0, 0, -1);
                bu.l.q(aj);
            }
            return;
        }
        bu.l = as;
        bu.az = az;
        bu.enabled = true;
        (bu.a = new Thread(new bu(), "KeepAliveThread")).start();
    }
    
    public static void b() {
        try {
            bu.enabled = false;
            if (bu.a != null && bu.a.isAlive()) {
                bu.a.stop();
            }
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        try {
            while (bu.enabled) {
                if ((Calendar.getInstance().getTime().getTime() - bu.l.a.getTime()) / 60000L >= bu.az) {
                    if (bu.l.b != 1) {
                        final aJ aj = new aJ(66305, 1);
                        aj.a(0, 0, "mystatus=1");
                        aj.C = -1;
                        aj.j = bu.l.e();
                        aj.a(0, 0, -1);
                        bu.l.q(aj);
                        bu.l.b = 1;
                    }
                }
                else if (bu.l.b == 1) {
                    final aJ aj2 = new aJ(66305, 1);
                    aj2.a(0, 0, "mystatus=5");
                    aj2.C = -1;
                    aj2.j = bu.l.e();
                    aj2.a(0, 0, -1);
                    bu.l.q(aj2);
                    bu.l.b = 0;
                }
                Thread.sleep(1000L);
            }
        }
        catch (Exception ex) {
            System.out.println("keep alive stopped by exception. " + ex.getMessage());
        }
    }
    
    static {
        bu.enabled = false;
    }
}
