import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Fourier extends Applet implements ComponentListener
{
    FourierFrame ogf;
    boolean started;
    
    public Fourier() {
        this.started = false;
    }
    
    void destroyFrame() {
        if (this.ogf != null) {
            this.ogf.dispose();
        }
        this.ogf = null;
        this.repaint();
    }
    
    public void init() {
        this.addComponentListener(this);
    }
    
    void showFrame() {
        if (this.ogf == null) {
            this.started = true;
            (this.ogf = new FourierFrame(this)).init();
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        String s = "Applet is open in a separate window.";
        if (!this.started) {
            s = "Applet is starting.";
        }
        else if (this.ogf == null) {
            s = "Applet is finished.";
        }
        else if (this.ogf.useFrame) {
            this.ogf.triggerShow();
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
        if (this.ogf != null) {
            this.ogf.dispose();
        }
        this.ogf = null;
        this.repaint();
    }
}
