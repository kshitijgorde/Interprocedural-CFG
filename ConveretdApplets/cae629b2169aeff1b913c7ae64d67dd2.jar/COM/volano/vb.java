// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Observable;
import java.io.IOException;
import java.util.Vector;
import java.net.URL;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Component;
import java.util.Observer;
import java.applet.Applet;

public abstract class vb extends Applet implements Runnable, Observer
{
    public vd a;
    public vc b;
    public Component c;
    public Label d;
    public boolean e;
    public int f;
    public Frame g;
    private Thread h;
    public String i;
    public String j;
    public String k;
    
    private static void a(final String s, final Throwable t) {
        synchronized (System.err) {
            System.err.println(s);
            t.printStackTrace(System.err);
        }
        // monitorexit(System.err)
    }
    
    public String c() {
        return "2.10.0";
    }
    
    public String getAppletInfo() {
        return String.valueOf(this.getClass().getName()) + " " + "2.10.0" + " " + "(www.volano.com)" + "\n" + "Copyright (c) 1996-2010 Volano Software";
    }
    
    public abstract vn a();
    
    public abstract void a(final vd p0);
    
    public void init() {
        vc.a(this.a());
        vc.b = true;
        this.a(this.a = new vd(this));
        if (this.a.ac != null) {
            this.setBackground(this.a.ac);
        }
        if (this.a.ad != null) {
            this.setForeground(this.a.ad);
        }
        if (this.a.bn != null) {
            this.setFont(this.a.bn);
        }
        this.setLayout(new BorderLayout());
        this.c = new vf(this.a.b, this.a.bz, this.a.b0, this.a.by, this.a.dw, this.a.b1, this.a.b2);
        this.d = new Label(this.a.dx, 1);
    }
    
    public void start() {
        if (this.h == null) {
            (this.h = new Thread(this, this.getClass().getName())).start();
        }
    }
    
    public void stop() {
        if (this.g != null) {
            this.g.deliverEvent(new Event(this.g, 201, null));
        }
        if (this.h != null) {
            final Thread h = this.h;
            this.h = null;
            try {
                h.interrupt();
            }
            catch (Throwable t) {}
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            while (this.h == currentThread) {
                synchronized (this) {
                    while (!this.e) {
                        this.wait();
                    }
                    this.e = false;
                }
                if (this.f == 0) {
                    this.f = 1;
                    this.d();
                }
                else {
                    if (this.g == null) {
                        continue;
                    }
                    this.g.requestFocus();
                }
            }
        }
        catch (InterruptedException ex) {}
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            a("Applet thread error", t);
            this.a(this.a.cx);
        }
    }
    
    public void a(final URL url) {
        System.err.println("See " + url);
        if (this.a.cs) {
            this.a.b.showDocument(url, "_blank");
        }
        else {
            this.a.b.showDocument(url);
        }
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement(this.a.f);
        vector.addElement((String)new Integer(this.a.am));
        this.d.setText(vaq.a(this.a.d1, vector));
        this.f = 0;
    }
    
    private void d() {
        try {
            this.e();
            this.f();
        }
        catch (IOException ex) {
            if (this.b != null) {
                this.b.deleteObserver(this);
                this.b.d();
            }
            this.a(this.a.cx);
        }
    }
    
    private void e() throws IOException {
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement((this.a.f.length() == 0) ? "localhost" : this.a.f);
        vector.addElement((String)new Integer(this.a.am));
        this.d.setText(vaq.a(this.a.dy, vector));
        (this.b = new vc(this.a.f, this.a.am, false)).addObserver(this);
        this.b.a(5);
        this.b.b(5);
    }
    
    private void f() throws IOException {
        this.d.setText(this.a.dz);
        final String c = this.c();
        final String string = this.a.d.toString();
        final String string2 = this.a.c.toString();
        vo vo;
        if (this.a.y || this.a.x || this.a.w || this.a.z) {
            vo = new vae(this.a.ae, c, string, string2, this.a.p, this.a.q, this.a.r, this.a.s, this.a.t, this.a.u, this.a.v, this.a.y, this.a.w, this.a.x, this.i, this.j, this.k, this.a.z, this.a.af);
        }
        else {
            vo = new vo(this.a.ae, c, string, string2, this.a.p, this.a.q, this.a.r, this.a.s, this.a.t, this.a.u, this.a.v);
        }
        this.b.a(vo);
    }
    
    public abstract void b();
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 && event.target == this.g) {
            this.d.setText(this.a.dx);
            this.f = 0;
            this.g = null;
            this.b();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void update(final Observable observable, final Object o) {
        final vc vc = (vc)observable;
        if (o instanceof vab) {
            final vab vab = (vab)o;
            vab.e();
            this.a(vc, vab);
            return;
        }
        if (o instanceof vas) {
            this.d.setText("Server error (" + ((vas)o).a + ").");
            vc.deleteObserver(this);
            vc.d();
            this.f = 0;
            return;
        }
        if (o == null && this.f == 1) {
            this.a(this.a.cx);
        }
    }
    
    public void a(final vc vc, final vp vp) {
        try {
            vc.a(vp);
        }
        catch (IOException ex) {}
    }
    
    public byte[] a(final byte[] array) {
        final vi b = vi.b();
        b.a();
        return b.a(array);
    }
    
    public void a(final int n) {
        this.b.d();
        if (n == 2) {
            this.a(this.a.cv);
            return;
        }
        if (n == 3) {
            this.a(this.a.cu);
            return;
        }
        if (n == 4) {
            this.a(this.a.cy);
            return;
        }
        if (n == 5) {
            this.a(this.a.cw);
            return;
        }
        if (n == 6) {
            this.a(this.a.c0);
            return;
        }
        if (n == 7) {
            this.a(this.a.cz);
        }
    }
    
    public vb() {
        this.f = 0;
        this.i = "";
        this.j = "";
        this.k = "";
    }
}
