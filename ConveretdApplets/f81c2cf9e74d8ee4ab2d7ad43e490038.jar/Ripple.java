import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ripple extends Applet implements ComponentListener
{
    static RippleFrame ogf;
    boolean started;
    
    public Ripple() {
        this.started = false;
    }
    
    void destroyFrame() {
        if (Ripple.ogf != null) {
            Ripple.ogf.dispose();
        }
        Ripple.ogf = null;
        this.repaint();
    }
    
    public void init() {
        this.addComponentListener(this);
    }
    
    public static void main(final String[] array) {
        (Ripple.ogf = new RippleFrame((Ripple)null)).init();
    }
    
    void showFrame() {
        if (Ripple.ogf == null) {
            this.started = true;
            (Ripple.ogf = new RippleFrame(this)).init();
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        String s = "Applet is open in a separate window.";
        if (!this.started) {
            s = "Applet is starting.";
        }
        else if (Ripple.ogf == null) {
            s = "Applet is finished.";
        }
        else if (Ripple.ogf.useFrame) {
            Ripple.ogf.triggerShow();
        }
        graphics.drawString(s, 10, 30);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.showFrame();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void destroy() {
        if (Ripple.ogf != null) {
            Ripple.ogf.dispose();
        }
        Ripple.ogf = null;
        this.repaint();
    }
}
