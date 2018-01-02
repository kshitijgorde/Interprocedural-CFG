import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class bo extends Thread
{
    protected Image a;
    private final IpixViewer b;
    
    public bo(final IpixViewer b, final Image a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        this.b.j = new Object();
        this.b.b(3);
        this.b.stop();
        this.b.U = false;
        this.b.V = false;
        this.b.W = false;
        this.b.a(this.b.e().getGraphics(), this.a);
        this.b.b(0);
        this.b.start();
        this.b.repaint();
        this.b.j = null;
        synchronized (this.b.i) {
            this.b.i.notify();
        }
    }
}
