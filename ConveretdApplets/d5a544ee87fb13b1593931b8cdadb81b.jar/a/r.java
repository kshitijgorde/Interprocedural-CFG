// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class r extends Thread
{
    private HanengCharts a;
    private Thread b;
    
    public r(final HanengCharts a) {
        this.a = a;
    }
    
    public void start() {
        (this.b = new Thread(this)).start();
    }
    
    public void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        while (true) {
            this.a.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void a() {
        this.b.suspend();
    }
    
    public void b() {
        this.b.resume();
    }
}
