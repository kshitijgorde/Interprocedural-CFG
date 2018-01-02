import java.awt.Toolkit;
import java.awt.Image;
import java.util.Enumeration;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.applet.Applet;
import java.awt.Frame;
import java.applet.AppletContext;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptcontext implements AppletContext
{
    private Frame f1;
    
    public ptcontext(final Frame f1) {
        this.f1 = f1;
    }
    
    public void showStatus(final String s) {
        if (s != null && s.length() > 0) {
            final Applet applet;
            final Dimension size = (applet = (Applet)this.f1.getComponent(0)).getSize();
            final Graphics graphics = applet.getGraphics();
            applet.setBackground(Color.lightGray);
            graphics.clearRect(0, size.height - 14, size.width, 14);
            graphics.drawString(s, 10, size.height - 2);
        }
    }
    
    public AudioClip getAudioClip(final URL url) {
        return null;
    }
    
    public void showDocument(final URL url) {
        new ptvjapp(url.getFile());
    }
    
    public void showDocument(final URL url, final String s) {
        this.showDocument(url);
    }
    
    public Applet getApplet(final String s) {
        return null;
    }
    
    public Enumeration getApplets() {
        return null;
    }
    
    public Image getImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }
}
