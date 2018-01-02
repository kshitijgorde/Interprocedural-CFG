// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cJ implements Runnable
{
    private ba q;
    private W q;
    private Thread q;
    
    public final void run() {
        try {
            this.q.q.waitForID(10);
        }
        catch (InterruptedException ex) {}
        if (!this.q.q.isErrorID(10) && !this.q.q.isVisible()) {
            this.q.q.setVisible(true);
        }
    }
    
    public cJ(final ba q, final W q2) {
        this.q = q;
        this.q = q2;
        (this.q = new Thread(this, "Worker")).start();
    }
}
