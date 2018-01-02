// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class ba extends Thread
{
    private static boolean q;
    private boolean w;
    private cq q;
    
    public ba(final cq q) {
        this.w = false;
        this.setName("OtpravkaTikov");
        this.q = q;
    }
    
    public final synchronized void q() {
        if (ba.q) {
            return;
        }
        ba.q = true;
        this.start();
    }
    
    public final void run() {
        while (true) {
            try {
                final cq q = this.q;
                final cr cr;
                (cr = new cr(4198432, 1)).q = -1;
                cr.w = -1;
                cr.e = -999;
                q.r(cr);
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(240000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    static {
        ba.q = false;
    }
}
