// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bQ extends Thread
{
    private static boolean q;
    private boolean w;
    private dH q;
    
    public bQ(final dH q) {
        this.w = false;
        this.setName("OtpravkaTikov");
        this.q = q;
    }
    
    public final synchronized void q() {
        if (bQ.q) {
            return;
        }
        bQ.q = true;
        this.start();
    }
    
    public final void run() {
        while (true) {
            try {
                final dH q = this.q;
                final dI di;
                (di = new dI(4198432, 1)).q = -1;
                di.w = -1;
                di.e = -999;
                q.o(di);
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(240000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    static {
        bQ.q = false;
    }
}
