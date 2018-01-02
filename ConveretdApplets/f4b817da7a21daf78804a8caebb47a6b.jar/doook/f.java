// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class f implements Runnable
{
    private O b;
    private aW b;
    private Thread a;
    
    public void run() {
        try {
            this.b.b.waitForID(10);
        }
        catch (InterruptedException ex) {}
        if (!this.b.b.isErrorID(10) && !this.b.b.isVisible()) {
            this.b.b.setVisible(true);
        }
    }
    
    public f(final O b, final aW b2) {
        this.b = b;
        this.b = b2;
        (this.a = new Thread(this, "Worker")).start();
    }
}
