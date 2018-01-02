// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class M implements Runnable
{
    private S a;
    private u g;
    private Thread c;
    
    public void run() {
        try {
            this.g.a.waitForID(10);
        }
        catch (InterruptedException ex) {}
        if (!this.g.a.isErrorID(10) && !this.a.b.isVisible()) {
            this.a.b.setVisible(true);
        }
    }
    
    public M(final S a, final u g) {
        this.a = a;
        this.g = g;
        (this.c = new Thread(this, "Worker")).start();
    }
}
