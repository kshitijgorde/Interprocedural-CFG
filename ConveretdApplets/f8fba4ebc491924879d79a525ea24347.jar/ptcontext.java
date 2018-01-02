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
    private Frame _frame;
    
    public ptcontext(final Frame frame) {
        this._frame = frame;
    }
    
    public void showStatus(final String s) {
        if (s.length() > 0) {
            final Dimension size = this._frame.getSize();
            final Applet applet = (Applet)this._frame.getComponent(0);
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
        new ptvjapp(new String[] { url.getFile() });
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
