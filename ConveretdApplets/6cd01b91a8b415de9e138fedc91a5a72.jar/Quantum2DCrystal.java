import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Quantum2DCrystal extends Applet implements ComponentListener
{
    Quantum2DCrystalFrame qf;
    boolean started;
    
    public Quantum2DCrystal() {
        this.started = false;
    }
    
    void destroyFrame() {
        if (this.qf != null) {
            this.qf.dispose();
        }
        this.qf = null;
        this.repaint();
    }
    
    public void init() {
        this.addComponentListener(this);
    }
    
    void showFrame() {
        if (this.qf == null) {
            this.started = true;
            (this.qf = new Quantum2DCrystalFrame(this)).init();
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        String s = "Applet is open in a separate window.";
        if (!this.started) {
            s = "Applet is starting.";
        }
        else if (this.qf == null) {
            s = "Applet is finished.";
        }
        else {
            this.qf.triggerShow();
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
        if (this.qf != null) {
            this.qf.dispose();
        }
        this.qf = null;
        this.repaint();
    }
}
