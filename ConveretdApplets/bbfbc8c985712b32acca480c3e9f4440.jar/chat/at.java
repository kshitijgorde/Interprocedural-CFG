// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class at implements Runnable
{
    private aF a;
    private cx a;
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
    
    public at(final aF a, final cx a2) {
        this.a = a;
        this.a = a2;
        (this.a = new Thread(this, "Worker")).start();
    }
}
