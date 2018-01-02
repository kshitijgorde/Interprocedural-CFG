import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class RenderThread extends Thread
{
    CgmViewApplet applet;
    double atX;
    double atY;
    double factorX;
    double factorY;
    double factor;
    Image img;
    Layer[] Layers;
    Rectangle clip;
    
    public RenderThread() {
    }
    
    RenderThread(final CgmViewApplet applet, final double atX, final double atY, final double factorX, final double factorY, final Image img, final Layer[] layers, final Rectangle clip, final double factor) {
        super("Render-Thread");
        this.applet = applet;
        this.atX = atX;
        this.atY = atY;
        this.factorX = factorX;
        this.factorY = factorY;
        this.factor = factor;
        this.img = img;
        this.Layers = layers;
        this.clip = clip;
    }
    
    public RenderThread(final Runnable runnable) {
        super(runnable);
    }
    
    public RenderThread(final Runnable runnable, final String s) {
        super(runnable, s);
    }
    
    public RenderThread(final String s) {
        super(s);
    }
    
    public RenderThread(final ThreadGroup threadGroup, final Runnable runnable) {
        super(threadGroup, runnable);
    }
    
    public RenderThread(final ThreadGroup threadGroup, final Runnable runnable, final String s) {
        super(threadGroup, runnable, s);
    }
    
    public RenderThread(final ThreadGroup threadGroup, final String s) {
        super(threadGroup, s);
    }
    
    public void run() {
        final Graphics graphics = this.img.getGraphics();
        for (int i = 0; i < 17; ++i) {
            final Layer layer;
            if ((layer = this.Layers[i]) != null) {
                try {
                    graphics.setClip(this.clip);
                }
                catch (Throwable t) {
                    System.out.println("java.awt JDK 1.1.0 or later required");
                }
                layer.render(graphics, this.atX, this.atY, this.factorX, this.factorY, this.factor);
            }
        }
        this.applet.showStatus(" ");
        this.applet.repaint();
    }
}
