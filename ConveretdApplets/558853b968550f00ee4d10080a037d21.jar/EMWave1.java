import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EMWave1 extends Applet implements ComponentListener
{
    EMWave1Frame ogf;
    boolean started;
    
    public EMWave1() {
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
            (this.ogf = new EMWave1Frame(this)).init();
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
        else {
            this.ogf.show();
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
