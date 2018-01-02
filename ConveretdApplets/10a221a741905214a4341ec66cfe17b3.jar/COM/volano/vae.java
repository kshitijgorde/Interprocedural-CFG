// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.applet.AppletContext;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Properties;
import java.net.URL;
import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.Panel;

public class vae extends Panel implements AppletStub, Runnable
{
    private Applet a;
    private String b;
    private String c;
    private URL d;
    private int e;
    private int f;
    private Object g;
    private Properties h;
    private Applet i;
    private boolean j;
    private boolean k;
    private boolean l;
    
    public vae(final Applet a, final String b, final String c, final URL d, final int e, final int f) {
        this.g = new Object();
        this.h = new Properties();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.setLayout(new BorderLayout());
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.e, this.f);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void addNotify() {
        super.addNotify();
        if (!this.j) {
            this.j = true;
            new Thread(this, "PlayerStub").start();
        }
    }
    
    public void removeNotify() {
        super.removeNotify();
        synchronized (this.g) {
            if (!this.k) {
                this.k = true;
                this.g.notify();
            }
        }
        // monitorexit(this.g)
    }
    
    public void run() {
        try {
            if (this.d != null) {
                try {
                    final InputStream openStream = this.d.openStream();
                    this.h.load(openStream);
                    openStream.close();
                }
                catch (IOException ex) {
                    System.err.println(ex);
                }
            }
            if (this.c.endsWith(".class")) {
                this.c = this.c.substring(0, this.c.length() - ".class".length());
            }
            (this.i = (Applet)Class.forName(this.c).newInstance()).setStub(this);
            this.i.hide();
            this.add("Center", this.i);
            this.validate();
            for (int n = 0; !this.isShowing() && n < 5; ++n) {
                Thread.sleep(1000L);
            }
            this.i.resize(this.size());
            this.i.init();
            this.validate();
            this.i.resize(this.size());
            this.l = true;
            this.i.start();
            this.validate();
            this.i.show();
            synchronized (this.g) {
                while (!this.k) {
                    this.g.wait();
                }
            }
            // monitorexit(this.g)
            this.i.hide();
            this.l = false;
            this.i.stop();
            this.i.destroy();
            this.remove(this.i);
            this.i = null;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            synchronized (System.err) {
                System.err.println("Error in applet: " + this.c);
                t.printStackTrace(System.err);
            }
            // monitorexit(System.err)
        }
    }
    
    public void appletResize(final int e, final int f) {
        this.resize(this.e = e, this.f = f);
        this.validate();
    }
    
    public AppletContext getAppletContext() {
        return this.a.getAppletContext();
    }
    
    public URL getCodeBase() {
        if (this.d == null) {
            return this.a.getCodeBase();
        }
        return this.d;
    }
    
    public URL getDocumentBase() {
        return this.a.getDocumentBase();
    }
    
    public String getParameter(final String s) {
        String s2 = this.a.getParameter(this.b + s);
        if (s2 == null) {
            s2 = this.h.getProperty(s);
        }
        if (s2 == null) {
            return null;
        }
        return s2.trim();
    }
    
    public boolean isActive() {
        return this.l;
    }
}
