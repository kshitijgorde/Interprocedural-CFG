// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Calendar;

public final class bM implements Runnable
{
    private static t j;
    private static Thread d;
    private static int aF;
    private static boolean enabled;
    
    public static void a(final t t, final int af) {
        bM.j = t;
        r();
        if (af == 0) {
            if (bM.j.c == 1) {
                final cD cd = new cD(66305, 1);
                cd.a(0, 0, "mystatus=5");
                cd.o = -1;
                cd.j = bM.j.h();
                cd.a(0, 0, -1);
                bM.j.o(cd);
            }
            return;
        }
        bM.j = t;
        bM.aF = af;
        bM.enabled = true;
        (bM.d = new Thread(new bM(), "KeepAliveThread")).start();
    }
    
    public static void r() {
        try {
            bM.enabled = false;
            if (bM.d != null && bM.d.isAlive()) {
                bM.d.stop();
            }
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        try {
            while (bM.enabled) {
                if ((Calendar.getInstance().getTime().getTime() - bM.j.a.getTime()) / 60000L >= bM.aF) {
                    if (bM.j.c != 1) {
                        final cD cd = new cD(66305, 1);
                        cd.a(0, 0, "mystatus=1");
                        cd.o = -1;
                        cd.j = bM.j.h();
                        cd.a(0, 0, -1);
                        bM.j.o(cd);
                        bM.j.c = 1;
                    }
                }
                else if (bM.j.c == 1) {
                    final cD cd2 = new cD(66305, 1);
                    cd2.a(0, 0, "mystatus=5");
                    cd2.o = -1;
                    cd2.j = bM.j.h();
                    cd2.a(0, 0, -1);
                    bM.j.o(cd2);
                    bM.j.c = 0;
                }
                Thread.sleep(1000L);
            }
        }
        catch (Exception ex) {
            System.out.println("keep alive stopped by exception. " + ex.getMessage());
        }
    }
    
    static {
        bM.enabled = false;
    }
}
