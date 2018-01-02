import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Circuit extends Applet implements ComponentListener
{
    static CirSim ogf;
    boolean started;
    
    public Circuit() {
        this.started = false;
    }
    
    void destroyFrame() {
        if (Circuit.ogf != null) {
            Circuit.ogf.dispose();
        }
        Circuit.ogf = null;
        this.repaint();
    }
    
    public void init() {
        this.addComponentListener(this);
    }
    
    public static void main(final String[] array) {
        (Circuit.ogf = new CirSim((Circuit)null)).init();
    }
    
    void showFrame() {
        if (Circuit.ogf == null) {
            this.started = true;
            (Circuit.ogf = new CirSim(this)).init();
            this.repaint();
        }
    }
    
    public void toggleSwitch(final int n) {
        Circuit.ogf.toggleSwitch(n);
    }
    
    public void paint(final Graphics graphics) {
        String s = "Applet is open in a separate window.";
        if (!this.started) {
            s = "Applet is starting.";
        }
        else if (Circuit.ogf == null) {
            s = "Applet is finished.";
        }
        else if (Circuit.ogf.useFrame) {
            Circuit.ogf.triggerShow();
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
        if (Circuit.ogf != null) {
            Circuit.ogf.componentResized(componentEvent);
        }
    }
    
    public void destroy() {
        if (Circuit.ogf != null) {
            Circuit.ogf.dispose();
        }
        Circuit.ogf = null;
        this.repaint();
    }
}
