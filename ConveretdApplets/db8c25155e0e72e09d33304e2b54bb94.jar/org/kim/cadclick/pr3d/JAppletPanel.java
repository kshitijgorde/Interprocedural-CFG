// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLStreamHandler;
import java.util.Enumeration;
import java.awt.Toolkit;
import java.awt.Image;
import java.applet.AudioClip;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.io.File;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import java.util.Vector;
import java.util.Hashtable;
import javax.swing.JTextField;
import java.net.URLStreamHandlerFactory;
import java.applet.AppletContext;
import java.applet.AppletStub;
import javax.swing.JPanel;

public class JAppletPanel extends JPanel implements AppletStub, AppletContext, URLStreamHandlerFactory
{
    private JTextField status;
    private Hashtable params;
    private Vector applets;
    private int initial_width;
    private int initial_height;
    private boolean bIsStarted;
    private boolean bIsInitialized;
    
    public JAppletPanel(final Applet applet, final boolean b) throws NullPointerException {
        super(new BorderLayout(), true);
        this.params = new Hashtable();
        this.applets = new Vector();
        if (applet == null) {
            throw new NullPointerException("Applet is NULL!");
        }
        this.applets.add(applet);
        if (b) {
            (this.status = new JTextField()).setEditable(false);
            this.add(this.status, "South");
        }
        else {
            this.status = null;
        }
        this.add(applet, "Center");
        this.bIsStarted = false;
        this.bIsInitialized = false;
    }
    
    public boolean isActive() {
        return true;
    }
    
    public URL getDocumentBase() {
        URL url = null;
        try {
            String s = new File("dummy.html").getAbsolutePath();
            if (!File.separator.equals("/")) {
                final StringBuffer sb = new StringBuffer();
                if (s.charAt(0) != File.separator.charAt(0)) {
                    sb.append("/");
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(s, File.separator);
                while (stringTokenizer.hasMoreTokens()) {
                    sb.append(String.valueOf(stringTokenizer.nextToken()) + "/");
                }
                if (File.separator.equals("\\") && sb.charAt(2) == ':') {
                    sb.setCharAt(2, '|');
                }
                final String string = sb.toString();
                s = string.substring(0, string.length() - 1);
            }
            url = new URL("file", "", -1, s);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return url;
    }
    
    public final URL getCodeBase() {
        return this.getDocumentBase();
    }
    
    public final String getParameter(final String s) {
        return this.params.get(s);
    }
    
    public final AppletContext getAppletContext() {
        return this;
    }
    
    public void appletResize(final int n, final int n2) {
        final Insets insets = this.getInsets();
        this.setSize(n + insets.left + insets.right, n2 + insets.top + insets.bottom);
    }
    
    public final AudioClip getAudioClip(final URL url) {
        return null;
    }
    
    public final Image getImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(this.a(url));
    }
    
    private String a(final URL url) {
        String s = url.getFile();
        if (s.charAt(1) == '|') {
            final StringBuffer sb = new StringBuffer(s);
            sb.setCharAt(1, ':');
            s = sb.toString();
        }
        else if (s.charAt(2) == '|') {
            final StringBuffer sb2 = new StringBuffer(s);
            sb2.setCharAt(2, ':');
            s = sb2.toString();
        }
        return s;
    }
    
    public final Applet getApplet(final String s) {
        if (this.applets.size() > 0) {
            return this.applets.get(0);
        }
        return null;
    }
    
    public final Enumeration getApplets() {
        return this.applets.elements();
    }
    
    public void showDocument(final URL url) {
        this.status.setText("AppletContext request to show URL " + url.toString());
    }
    
    public void showDocument(final URL url, final String s) {
        this.status.setText("AppletContext request to show URL " + url.toString() + " in target: " + s);
    }
    
    public void showStatus(final String text) {
        if (this.status != null) {
            this.status.setText(text);
        }
    }
    
    public URLStreamHandler createURLStreamHandler(final String s) {
        return new l();
    }
    
    public void setStream(final String s, final InputStream inputStream) throws IOException {
    }
    
    public InputStream getStream(final String s) {
        return null;
    }
    
    public Iterator getStreamKeys() {
        return null;
    }
}
