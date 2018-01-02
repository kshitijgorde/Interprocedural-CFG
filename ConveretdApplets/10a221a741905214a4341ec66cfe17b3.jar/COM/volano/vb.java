// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Observable;
import java.awt.Event;
import java.io.IOException;
import java.util.Vector;
import java.net.URL;
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
    
    public String b() {
        return "2.6.2.1";
    }
    
    public String getAppletInfo() {
        return this.getClass().getName() + " " + "2.6.2.1" + " " + "(www.volano.com)" + "\n" + "Copyright (c) 1996-2004 Volano LLC.  All rights reserved.";
    }
    
    public abstract vk a();
    
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
        if (this.a.bv != null) {
            this.setFont(this.a.bv);
        }
        this.setLayout(new BorderLayout());
        this.c = new vf(this.a.b, this.a.b8, this.a.b9, this.a.b7, this.a.ds, this.a.ca, this.a.cb);
        this.d = new Label(this.a.dt, 1);
    }
    
    public void start() {
        if (this.h == null) {
            (this.h = new Thread(this, this.getClass().getName())).start();
        }
    }
    
    public void destroy() {
        if (this.h != null) {
            final Thread h = this.h;
            this.h = null;
            try {
                h.interrupt();
            }
            catch (Throwable t) {}
        }
        if (this.b != null) {
            this.b.deleteObserver(this);
            this.b.d();
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
                    this.c();
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
            this.a(this.a.c6);
        }
    }
    
    public void a(final URL url) {
        System.err.println("See " + url);
        if (this.a.c1) {
            this.a.b.showDocument(url, "_blank");
        }
        else {
            this.a.b.showDocument(url);
        }
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement(this.a.f);
        vector.addElement((String)new Integer(this.a.am));
        this.d.setText(van.a(this.a.dx, vector));
        this.f = 0;
    }
    
    private void c() {
        try {
            this.d();
            this.e();
        }
        catch (IOException ex) {
            if (this.b != null) {
                this.b.deleteObserver(this);
                this.b.d();
            }
            this.a(this.a.c6);
        }
    }
    
    private void d() throws IOException {
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement((this.a.f.length() == 0) ? "localhost" : this.a.f);
        vector.addElement((String)new Integer(this.a.am));
        this.d.setText(van.a(this.a.du, vector));
        (this.b = new vc(this.a.f, this.a.am, false)).addObserver(this);
        this.b.a(5);
        this.b.b(5);
    }
    
    private void e() throws IOException {
        this.d.setText(this.a.dv);
        final String b = this.b();
        final String string = this.a.d.toString();
        final String string2 = this.a.c.toString();
        vl vl;
        if (this.a.y || this.a.x || this.a.w || this.a.z) {
            vl = new vab(this.a.ae, b, string, string2, this.a.p, this.a.q, this.a.r, this.a.s, this.a.t, this.a.u, this.a.v, this.a.y, this.a.w, this.a.x, this.i, this.j, this.k, this.a.z, this.a.af);
        }
        else {
            vl = new vl(this.a.ae, b, string, string2, this.a.p, this.a.q, this.a.r, this.a.s, this.a.t, this.a.u, this.a.v);
        }
        this.b.a(vl);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 && event.target == this.g) {
            this.d.setText(this.a.dt);
            this.f = 0;
            this.g = null;
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void update(final Observable observable, final Object o) {
        final vc vc = (vc)observable;
        if (o instanceof vy) {
            final vy vy = (vy)o;
            vy.e();
            this.a(vc, vy);
            return;
        }
        if (o instanceof vap) {
            this.d.setText("Server error (" + ((vap)o).a + ").");
            vc.deleteObserver(this);
            vc.d();
            this.f = 0;
            return;
        }
        if (o == null && this.f == 1) {
            this.a(this.a.c6);
        }
    }
    
    public void a(final vc vc, final vm vm) {
        try {
            vc.a(vm);
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
            this.a(this.a.c4);
            return;
        }
        if (n == 3) {
            this.a(this.a.c3);
            return;
        }
        if (n == 4) {
            this.a(this.a.c7);
            return;
        }
        if (n == 5) {
            this.a(this.a.c5);
            return;
        }
        if (n == 6) {
            this.a(this.a.c9);
            return;
        }
        if (n == 7) {
            this.a(this.a.c8);
        }
    }
    
    public vb() {
        this.i = "";
        this.j = "";
        this.k = "";
    }
}
