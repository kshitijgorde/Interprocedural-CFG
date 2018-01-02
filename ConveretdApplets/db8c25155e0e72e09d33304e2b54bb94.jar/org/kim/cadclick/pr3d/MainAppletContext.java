// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
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
import java.awt.event.WindowListener;
import java.awt.Component;
import java.net.URL;
import java.applet.Applet;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.TextField;
import java.net.URLStreamHandlerFactory;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Frame;

public class MainAppletContext extends Frame implements AppletStub, AppletContext, URLStreamHandlerFactory
{
    private TextField status;
    private Hashtable params;
    private Vector applets;
    private int initial_width;
    private int initial_height;
    
    public static void main(final String[] array) {
        new MainAppletContext(array);
    }
    
    public MainAppletContext(final Applet applet, final String[] array) {
        this(applet, 640, 480, array);
    }
    
    public MainAppletContext(final Applet applet, final int n, final int n2, final String[] array) {
        super(applet.getClass().getName());
        this.params = new Hashtable();
        this.applets = new Vector();
        this.a(applet, n, n2, array, 0);
    }
    
    public MainAppletContext(final String[] array) {
        super(array[0]);
        this.params = new Hashtable();
        this.applets = new Vector();
        try {
            this.a((Applet)Class.forName(array[0]).newInstance(), 640, 480, array, 1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    private void a(final Applet applet, final int initial_width, final int initial_height, final String[] array, final int n) {
        URL.setURLStreamHandlerFactory(this);
        this.applets.addElement(applet);
        applet.setStub(this);
        this.initial_width = initial_width;
        this.initial_height = initial_height;
        this.a(array, n);
        (this.status = new TextField()).setEditable(false);
        this.add("Center", applet);
        this.add("South", this.status);
        this.addWindowListener(new a());
        this.appletResize(this.initial_width, this.initial_height);
        this.setVisible(true);
        applet.init();
        this.setVisible(true);
        applet.start();
    }
    
    public void a(final String[] array, final int n) {
        for (int i = n; i < array.length - n; i += 2) {
            try {
                if (array[i].equals("-width")) {
                    this.initial_width = Integer.parseInt(array[i + 1]);
                }
                else if (array[i].equals("-height")) {
                    this.initial_height = Integer.parseInt(array[i + 1]);
                }
                else {
                    this.params.put(array[i], array[i + 1]);
                }
            }
            catch (NumberFormatException ex) {
                System.err.println("Warning: command line argument " + array[i] + " is not a valid number.");
            }
        }
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
        this.setSize(n + insets.left + insets.right, n2 + this.status.getPreferredSize().height + insets.top + insets.bottom);
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
        this.status.setText(text);
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
    
    class a extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            System.out.println("Bye!");
            MainAppletContext.this.dispose();
            System.exit(0);
        }
    }
}
