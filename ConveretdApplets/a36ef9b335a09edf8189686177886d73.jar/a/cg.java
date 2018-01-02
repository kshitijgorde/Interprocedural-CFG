// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cg extends Thread
{
    private static boolean q;
    private boolean w;
    private bH q;
    
    public cg(final bH q) {
        this.w = false;
        this.setName("OtpravkaTikov");
        this.q = q;
    }
    
    public final synchronized void q() {
        if (cg.q) {
            return;
        }
        cg.q = true;
        this.start();
    }
    
    public final void run() {
        while (true) {
            try {
                final bH q = this.q;
                final cJ cj;
                (cj = new cJ(4198432, 1)).q = -1;
                cj.w = -1;
                cj.e = -999;
                q.q(cj);
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(240000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    static {
        cg.q = false;
    }
}
