// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class X implements Runnable
{
    private af a;
    private bl a;
    private Thread a;
    
    public final void run() {
        try {
            this.a.a.waitForID(10);
        }
        catch (InterruptedException ex) {}
        if (!this.a.a.isErrorID(10) && !this.a.a.isVisible()) {
            this.a.a.setVisible(true);
        }
    }
    
    public X(final af a, final bl a2) {
        this.a = a;
        this.a = a2;
        (this.a = new Thread(this, "Worker")).start();
    }
}
