// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

public class q implements Runnable
{
    private r a;
    private Runnable b;
    
    public q(final Runnable b) {
        this.b = b;
    }
    
    public void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {
            return;
        }
        this.b.run();
        this.a = null;
    }
    
    public void a() {
        this.b();
        (this.a = new r(this)).start();
    }
    
    public void b() {
        if (this.a == null) {
            return;
        }
        this.a.a();
    }
}
