import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class bn extends Thread
{
    protected Image a;
    private boolean b;
    private final IpixViewer c;
    
    public bn(final IpixViewer c, final Image a, final boolean b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        this.c.j = new Object();
        final IpixViewer c = this.c;
        c.bj |= (this.b ? IpixViewer.bo : IpixViewer.bn);
        final MediaTracker mediaTracker = new MediaTracker(this.c);
        mediaTracker.addImage(this.a, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.c.j = null;
        synchronized (this.c.i) {
            this.c.i.notify();
        }
        final IpixViewer c2 = this.c;
        c2.bj &= (this.b ? (~IpixViewer.bo) : (~IpixViewer.bn));
    }
}
