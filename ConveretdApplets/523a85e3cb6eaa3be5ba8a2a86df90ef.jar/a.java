// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Thread
{
    private final EggApplet p;
    
    public a(final EggApplet p) {
        this.p = p;
        this.start();
    }
    
    public final void run() {
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex) {
            System.out.println("Caught exception while waiting after mouse click: ".concat(String.valueOf(String.valueOf(ex))));
        }
        this.p.a();
    }
}
