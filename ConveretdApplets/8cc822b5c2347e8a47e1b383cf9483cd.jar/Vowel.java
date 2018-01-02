import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Vowel extends Applet implements ComponentListener
{
    static VowelFrame ogf;
    boolean started;
    boolean security;
    
    public Vowel() {
        this.started = false;
        this.security = false;
    }
    
    void destroyFrame() {
        if (Vowel.ogf != null) {
            Vowel.ogf.dispose();
        }
        Vowel.ogf = null;
        this.repaint();
    }
    
    public void init() {
        this.addComponentListener(this);
    }
    
    public static void main(final String[] array) {
        (Vowel.ogf = new VowelFrame((Vowel)null)).init();
    }
    
    void showFrame() {
        if (Vowel.ogf == null) {
            this.started = true;
            try {
                (Vowel.ogf = new VowelFrame(this)).init();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Vowel.ogf = null;
                this.security = true;
                this.repaint();
            }
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        String s = "Applet is open in a separate window.";
        if (this.security) {
            s = "Security exception, use nosound version";
        }
        else if (!this.started) {
            s = "Applet is starting.";
        }
        else if (Vowel.ogf == null) {
            s = "Applet is finished.";
        }
        else {
            Vowel.ogf.show();
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
        if (Vowel.ogf != null) {
            Vowel.ogf.dispose();
        }
        Vowel.ogf = null;
        this.repaint();
    }
}
