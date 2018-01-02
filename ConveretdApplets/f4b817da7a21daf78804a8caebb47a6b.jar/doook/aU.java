// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Calendar;

public final class aU implements Runnable
{
    private static be j;
    private static Thread d;
    private static int a;
    private static boolean a;
    
    public static void a(final be be, final int a) {
        aU.j = be;
        c();
        if (a == 0) {
            if (aU.j.T == 1) {
                final V v = new V(66305, 1);
                v.a(0, 0, "mystatus=5");
                v.u = -1;
                v.j = aU.j.b();
                v.a(0, 0, -1);
                aU.j.F(v);
            }
            return;
        }
        aU.j = be;
        aU.a = a;
        aU.a = true;
        (aU.d = new Thread(new aU(), "KeepAliveThread")).start();
    }
    
    public static void c() {
        try {
            aU.a = false;
            if (aU.d != null && aU.d.isAlive()) {
                aU.d.stop();
            }
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        try {
            while (aU.a) {
                if ((Calendar.getInstance().getTime().getTime() - aU.j.a.getTime()) / 60000L >= aU.a) {
                    if (aU.j.T != 1) {
                        final V v = new V(66305, 1);
                        v.a(0, 0, "mystatus=1");
                        v.u = -1;
                        v.j = aU.j.b();
                        v.a(0, 0, -1);
                        aU.j.F(v);
                        aU.j.T = 1;
                    }
                }
                else if (aU.j.T == 1) {
                    final V v2 = new V(66305, 1);
                    v2.a(0, 0, "mystatus=5");
                    v2.u = -1;
                    v2.j = aU.j.b();
                    v2.a(0, 0, -1);
                    aU.j.F(v2);
                    aU.j.T = 0;
                }
                Thread.sleep(1000L);
            }
        }
        catch (Exception ex) {
            System.out.println("keep alive stopped by exception. " + ex.getMessage());
        }
    }
    
    static {
        aU.a = false;
    }
}
