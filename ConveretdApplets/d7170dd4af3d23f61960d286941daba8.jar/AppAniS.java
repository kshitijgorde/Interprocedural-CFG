import javax.swing.JOptionPane;
import java.util.Iterator;
import java.io.InputStream;
import java.applet.AudioClip;
import java.util.Enumeration;
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.applet.Applet;
import java.util.Hashtable;
import java.io.File;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppAniS extends Frame implements AppletStub, AppletContext
{
    private File baseDir;
    private Hashtable params;
    private Applet app;
    private String base;
    private Hashtable streams;
    
    public AppAniS(final Applet app, final int n, final int n2, final Hashtable params, final String base) {
        this.baseDir = null;
        this.params = params;
        this.app = app;
        this.base = base;
        this.streams = new Hashtable();
        String title = this.params.get("title");
        if (title == null) {
            title = "AniS Application ver.1.0";
        }
        this.setTitle(title);
        this.resize(n, n2);
        this.add("Center", app);
        this.setLocation(30, 30);
        this.show();
        app.setStub(this);
        app.init();
        app.start();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.app.stop();
            this.app.destroy();
            System.exit(0);
        }
        return super.handleEvent(event);
    }
    
    public void appletResize(final int n, final int n2) {
        this.resize(n, n2);
        this.validate();
    }
    
    public AppletContext getAppletContext() {
        return this;
    }
    
    public URL getCodeBase() {
        try {
            return new URL(this.base);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public URL getDocumentBase() {
        try {
            return new URL(this.base);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getParameter(final String s) {
        if (this.params == null) {
            return null;
        }
        return this.params.get(s);
    }
    
    public boolean isActive() {
        return true;
    }
    
    public Image getImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public void showStatus(final String s) {
        System.err.println(s);
    }
    
    public Applet getApplet(final String s) {
        return null;
    }
    
    public Enumeration getApplets() {
        return null;
    }
    
    public AudioClip getAudioClip(final URL url) {
        return null;
    }
    
    public void showDocument(final URL url) {
    }
    
    public void showDocument(final URL url, final String s) {
    }
    
    public void setStream(final String s, final InputStream inputStream) {
        this.streams.put(s, inputStream);
    }
    
    public InputStream getStream(final String s) {
        return this.streams.get(s);
    }
    
    public Iterator getStreamKeys() {
        return (Iterator)this.streams.keys();
    }
    
    public static void main(final String[] array) {
        final String[] array2 = { "width", "height" };
        final Hashtable<String, String> hashtable = new Hashtable<String, String>(60);
        String showInputDialog = null;
        String s = "Enter the complete URL of the AniS applet you want to show ... then click OK button";
        String s2 = null;
        int int1 = 500;
        int int2 = 500;
        hashtable.put("AppAniS", "AppAniS");
        while (showInputDialog == null) {
            if (array.length > 0) {
                showInputDialog = array[0];
            }
            else {
                showInputDialog = JOptionPane.showInputDialog(null, s, "AppAniS - Enter Remote URL", -1);
                if (showInputDialog == null) {
                    System.exit(0);
                }
            }
            try {
                URL url;
                String host;
                if (showInputDialog.toLowerCase().startsWith("http://") || showInputDialog.toLowerCase().startsWith("https://") || showInputDialog.toLowerCase().startsWith("ftp://")) {
                    url = new URL(showInputDialog);
                    s2 = showInputDialog.substring(0, showInputDialog.indexOf(new File(url.getFile()).getName()));
                    host = url.getHost();
                }
                else {
                    final File file = new File(showInputDialog);
                    url = file.toURL();
                    host = "localhost";
                    s2 = showInputDialog.substring(0, showInputDialog.indexOf(file.getName()));
                }
                if (!new AppletParams(url.openStream()).tokenIt(hashtable, true)) {
                    System.out.println("Problem with HTML file format");
                    return;
                }
                hashtable.put("host", host);
                int1 = Integer.parseInt(hashtable.get("width"));
                int2 = Integer.parseInt(hashtable.get("height"));
            }
            catch (Exception ex) {
                System.out.println(ex);
                ex.printStackTrace();
                s = "Invalid URL!  Please try again...(or check Java Console for more details)";
                showInputDialog = null;
            }
        }
        try {
            Thread.sleep(2000L);
        }
        catch (Exception ex2) {}
        final AppAniS appAniS = new AppAniS(new AniS(), int1 + 8, int2 + 27, hashtable, s2);
    }
}
