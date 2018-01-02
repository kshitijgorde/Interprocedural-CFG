// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class aZ extends Thread
{
    private static boolean q;
    private boolean w;
    private co q;
    
    public aZ(final co q) {
        this.w = false;
        this.setName("OtpravkaTikov");
        this.q = q;
    }
    
    public final synchronized void q() {
        if (aZ.q) {
            return;
        }
        aZ.q = true;
        this.start();
    }
    
    public final void run() {
        while (true) {
            try {
                final co q = this.q;
                final cp cp;
                (cp = new cp(4198432, 1)).q = -1;
                cp.w = -1;
                cp.e = -999;
                q.r(cp);
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(240000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    static {
        aZ.q = false;
    }
}
