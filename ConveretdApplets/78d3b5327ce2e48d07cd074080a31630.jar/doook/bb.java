// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class bb implements Runnable
{
    private bj b;
    private at j;
    private Thread e;
    
    public void run() {
        try {
            this.j.b.waitForID(10);
        }
        catch (InterruptedException ex) {}
        if (!this.j.b.isErrorID(10) && !this.b.b.isVisible()) {
            this.b.b.setVisible(true);
        }
    }
    
    public bb(final bj b, final at j) {
        this.b = b;
        this.j = j;
        (this.e = new Thread(this, "Worker")).start();
    }
}
