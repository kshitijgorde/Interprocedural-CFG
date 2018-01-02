// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

public class r implements Runnable
{
    private s a;
    private Runnable b;
    private int c;
    
    public r(final Runnable runnable) {
        this(runnable, 3000);
    }
    
    public r(final Runnable b, final int c) {
        this.b = b;
        this.c = c;
    }
    
    public void run() {
        try {
            Thread.sleep(this.c);
        }
        catch (InterruptedException ex) {
            return;
        }
        this.b.run();
        this.a = null;
    }
    
    public void a() {
        this.b();
        (this.a = new s(this)).start();
    }
    
    public void b() {
        if (this.a == null) {
            return;
        }
        this.a.a();
    }
}
