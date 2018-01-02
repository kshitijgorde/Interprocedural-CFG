// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dM extends Thread
{
    private static boolean q;
    private boolean w;
    private cU q;
    
    public dM(final cU q) {
        this.w = false;
        this.setName("OtpravkaTikov");
        this.q = q;
    }
    
    public final synchronized void q() {
        if (dM.q) {
            return;
        }
        dM.q = true;
        this.start();
    }
    
    public final void run() {
        while (true) {
            try {
                final cU q = this.q;
                final es es;
                (es = new es(4198432, 1)).q = -1;
                es.w = -1;
                es.e = -999;
                q.q(es);
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(240000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    static {
        dM.q = false;
    }
}
