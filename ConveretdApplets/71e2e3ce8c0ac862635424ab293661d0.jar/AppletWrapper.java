import java.awt.Event;
import java.awt.Component;
import java.util.Enumeration;
import java.applet.Applet;
import java.awt.Toolkit;
import java.awt.Image;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.AppletStub;
import java.applet.AppletContext;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppletWrapper extends Frame implements AppletContext, AppletStub
{
    public boolean isActive() {
        return true;
    }
    
    public URL getDocumentBase() {
        return this.getCodeBase();
    }
    
    public URL getCodeBase() {
        try {
            final URL url = new URL("file://" + System.getProperty("user.dir") + System.getProperty("file.separator"));
            System.out.println("getCodeBase(): " + url.toString());
            return url;
        }
        catch (MalformedURLException ex) {
            System.out.println("getCodeBase() " + ex.toString());
            return null;
        }
    }
    
    public String getParameter(final String s) {
        return "";
    }
    
    public AppletContext getAppletContext() {
        return this;
    }
    
    public void appletResize(final int n, final int n2) {
    }
    
    public AudioClip getAudioClip(final URL url) {
        return null;
    }
    
    public Image getImage(final URL url, final String s) {
        try {
            return this.getImage(new URL(url, s));
        }
        catch (MalformedURLException ex) {
            System.out.println("getImage(U,s): " + ex.toString());
            return null;
        }
    }
    
    public Image getImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public Applet getApplet(final String s) {
        return null;
    }
    
    public Enumeration getApplets() {
        return null;
    }
    
    public void showDocument(final URL url) {
    }
    
    public void showDocument(final URL url, final String s) {
    }
    
    public void showStatus(final String s) {
    }
    
    AppletWrapper(final Applet applet, final int n, final int n2) {
        this.resize(n, n2);
        this.add("Center", applet);
        applet.setStub(this);
        this.setTitle(applet.getClass().getName());
        this.show();
        applet.init();
        applet.start();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            System.exit(0);
        }
        return super.handleEvent(event);
    }
}
