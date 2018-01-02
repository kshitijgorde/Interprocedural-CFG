// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.applet.AppletContext;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.net.URL;
import java.applet.Applet;
import java.util.Hashtable;
import java.applet.AppletStub;
import java.awt.Panel;

public class vah extends Panel implements AppletStub, Runnable
{
    private static Object a;
    private static Hashtable b;
    private Applet c;
    private String d;
    private String e;
    private URL f;
    private int g;
    private int h;
    private Object i;
    private Properties j;
    private Applet k;
    private boolean l;
    private boolean m;
    private boolean n;
    
    private static Properties a(final URL url) {
        Properties properties = new Properties();
        if (url != null) {
            synchronized (vah.a) {
                final Properties properties2 = vah.b.get(url.toString());
                if (properties2 != null) {
                    properties = properties2;
                }
                else {
                    try {
                        final InputStream openStream = url.openStream();
                        properties.load(openStream);
                        openStream.close();
                        vah.b.put(url.toString(), properties);
                    }
                    catch (IOException ex) {
                        System.err.println(ex);
                    }
                }
            }
            // monitorexit(vah.a)
        }
        return properties;
    }
    
    public vah(final Applet c, final String d, final String e, final URL f, final int g, final int h) {
        this.i = new Object();
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.setLayout(new BorderLayout());
        this.j = a(f);
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.g, this.h);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void addNotify() {
        super.addNotify();
        if (!this.l) {
            this.l = true;
            new Thread(this, "PlayerStub").start();
        }
    }
    
    public void removeNotify() {
        super.removeNotify();
        synchronized (this.i) {
            if (!this.m) {
                this.m = true;
                this.i.notify();
            }
        }
        // monitorexit(this.i)
    }
    
    public void run() {
        try {
            if (this.e.endsWith(".class")) {
                this.e = this.e.substring(0, this.e.length() - ".class".length());
            }
            (this.k = (Applet)Class.forName(this.e).newInstance()).setStub(this);
            this.k.hide();
            this.add("Center", this.k);
            this.validate();
            for (int n = 0; !this.isShowing() && n < 5; ++n) {
                Thread.sleep(1000L);
            }
            this.k.resize(this.size());
            this.k.init();
            this.validate();
            this.k.resize(this.size());
            this.n = true;
            this.k.start();
            this.validate();
            this.k.show();
            synchronized (this.i) {
                while (!this.m) {
                    this.i.wait();
                }
            }
            // monitorexit(this.i)
            this.k.hide();
            this.n = false;
            this.k.stop();
            this.k.destroy();
            this.remove(this.k);
            this.k = null;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            synchronized (System.err) {
                System.err.println("Error in applet: " + this.e);
                t.printStackTrace(System.err);
            }
            // monitorexit(System.err)
        }
    }
    
    public void appletResize(final int g, final int h) {
        this.resize(this.g = g, this.h = h);
        this.validate();
    }
    
    public AppletContext getAppletContext() {
        return this.c.getAppletContext();
    }
    
    public URL getCodeBase() {
        if (this.f == null) {
            return this.c.getCodeBase();
        }
        return this.f;
    }
    
    public URL getDocumentBase() {
        return this.c.getDocumentBase();
    }
    
    public String getParameter(final String s) {
        String s2 = this.c.getParameter(String.valueOf(this.d) + s);
        if (s2 == null) {
            s2 = this.j.getProperty(s);
        }
        if (s2 == null) {
            return null;
        }
        return s2.trim();
    }
    
    public boolean isActive() {
        return this.n;
    }
    
    static {
        vah.a = new Object();
        vah.b = new Hashtable();
    }
}
