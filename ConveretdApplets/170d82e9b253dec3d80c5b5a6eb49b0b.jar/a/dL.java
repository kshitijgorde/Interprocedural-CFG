// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dL extends Thread
{
    private static boolean q;
    private boolean w;
    private Object q;
    
    public dL(final Object q) {
        this.w = false;
        this.setName("UdalenieZaviswihPolzovatelei");
        this.q = q;
    }
    
    public final synchronized void q() {
        if (dL.q) {
            return;
        }
        dL.q = true;
        this.start();
    }
    
    public final void run() {
        while (true) {
            try {
                ((cT)this.q).r();
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    static {
        dL.q = false;
    }
}
