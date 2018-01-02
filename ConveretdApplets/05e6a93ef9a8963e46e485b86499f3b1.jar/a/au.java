// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class au extends Thread
{
    private static boolean q;
    private boolean w;
    private Object q;
    
    public au(final Object q) {
        this.w = false;
        this.setName("UdalenieZaviswihPolzovatelei");
        this.q = q;
    }
    
    public final synchronized void q() {
        if (au.q) {
            return;
        }
        au.q = true;
        this.start();
    }
    
    public final void run() {
        while (true) {
            try {
                ((ba)this.q).q.q();
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    static {
        au.q = false;
    }
}
