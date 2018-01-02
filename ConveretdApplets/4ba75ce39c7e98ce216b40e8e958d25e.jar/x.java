import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class x extends w
{
    protected Image f;
    protected IpixViewer g;
    
    x(final IpixViewer g) {
        this.g = g;
    }
    
    synchronized void a(final Image image, final Dimension b, final float[] c) {
        try {
            final MediaTracker mediaTracker = new MediaTracker(this.g);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
            this.f = this.g.createImage(b.width + (int)(this.g.e().width * Math.max(1.0f, b.height / this.g.e().height)), b.height);
            final Graphics graphics = this.f.getGraphics();
            graphics.drawImage(image, 0, 0, null);
            graphics.drawImage(image, b.width, 0, null);
            graphics.dispose();
        }
        catch (InterruptedException ex) {}
        super.b = b;
        super.c = c;
        super.e = image.getSource();
    }
    
    Image e() {
        return this.f;
    }
    
    public Object clone() {
        final x x = new x(this.g);
        x.d = (Hashtable)super.d.clone();
        return x;
    }
}
