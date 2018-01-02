import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Envelope extends Applet
{
    EnvelopeFrame mf;
    boolean security;
    
    public Envelope() {
        this.security = false;
    }
    
    void destroyFrame() {
        if (this.mf != null) {
            this.mf.dispose();
        }
        this.mf = null;
    }
    
    public void init() {
        try {
            (this.mf = new EnvelopeFrame(this)).init();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.mf = null;
            this.security = true;
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.security) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 800, 200);
            graphics.setColor(Color.white);
            graphics.drawString("Doesn't work with java 2", 0, 20);
        }
    }
    
    public void destroy() {
        if (this.mf != null) {
            this.mf.dispose();
        }
        this.mf = null;
    }
}
