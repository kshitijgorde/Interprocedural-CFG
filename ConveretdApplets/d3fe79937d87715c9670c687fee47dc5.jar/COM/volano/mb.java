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

public abstract class mb extends Applet implements Runnable, Observer
{
    public md a;
    public mc b;
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
    
    public String a() {
        return "2.6.3";
    }
    
    public String getAppletInfo() {
        return String.valueOf(this.getClass().getName()) + " " + "2.6.3" + " " + "(www.volano.com)" + "\n" + "Copyright (c) 1996-2004 Volano LLC.  All rights reserved.";
    }
    
    public abstract mk b();
    
    public abstract void a(final md p0);
    
    public void init() {
        mc.a(this.b());
        mc.b = true;
        this.a(this.a = new md(this));
        if (this.a.ac != null) {
            this.setBackground(this.a.ac);
        }
        if (this.a.ad != null) {
            this.setForeground(this.a.ad);
        }
        if (this.a.bj != null) {
            this.setFont(this.a.bj);
        }
        this.setLayout(new BorderLayout());
        this.c = new mf(this.a.b, this.a.bv, this.a.bw, this.a.bu, this.a.dr, this.a.bx, this.a.by);
        this.d = new Label(this.a.ds, 1);
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
            this.a(this.a.ct);
        }
    }
    
    public void a(final URL url) {
        System.err.println("See " + url);
        if (this.a.co) {
            this.a.b.showDocument(url, "_blank");
        }
        else {
            this.a.b.showDocument(url);
        }
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement(this.a.f);
        vector.addElement((String)new Integer(this.a.am));
        this.d.setText(mz.a(this.a.dw, vector));
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
            this.a(this.a.ct);
        }
    }
    
    private void d() throws IOException {
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement((this.a.f.length() == 0) ? "localhost" : this.a.f);
        vector.addElement((String)new Integer(this.a.am));
        this.d.setText(mz.a(this.a.dt, vector));
        (this.b = new mc(this.a.f, this.a.am, false)).addObserver(this);
        this.b.a(5);
        this.b.b(5);
    }
    
    private void e() throws IOException {
        this.d.setText(this.a.du);
        final String a = this.a();
        final String string = this.a.d.toString();
        final String string2 = this.a.c.toString();
        ml ml;
        if (this.a.y || this.a.x || this.a.w || this.a.z) {
            ml = new mas(this.a.ae, a, string, string2, this.a.p, this.a.q, this.a.r, this.a.s, this.a.t, this.a.u, this.a.v, this.a.y, this.a.w, this.a.x, this.i, this.j, this.k, this.a.z, this.a.af);
        }
        else {
            ml = new ml(this.a.ae, a, string, string2, this.a.p, this.a.q, this.a.r, this.a.s, this.a.t, this.a.u, this.a.v);
        }
        this.b.a(ml);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 && event.target == this.g) {
            this.d.setText(this.a.ds);
            this.f = 0;
            this.g = null;
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void update(final Observable observable, final Object o) {
        final mc mc = (mc)observable;
        if (o instanceof my) {
            final my my = (my)o;
            my.e();
            this.a(mc, my);
            return;
        }
        if (o instanceof maq) {
            this.d.setText("Server error (" + ((maq)o).a + ").");
            mc.deleteObserver(this);
            mc.d();
            this.f = 0;
            return;
        }
        if (o == null && this.f == 1) {
            this.a(this.a.ct);
        }
    }
    
    public void a(final mc mc, final mm mm) {
        try {
            mc.a(mm);
        }
        catch (IOException ex) {}
    }
    
    public byte[] a(final byte[] array) {
        final mi b = mi.b();
        b.a();
        return b.a(array);
    }
    
    public void a(final int n) {
        this.b.d();
        if (n == 2) {
            this.a(this.a.cr);
            return;
        }
        if (n == 3) {
            this.a(this.a.cq);
            return;
        }
        if (n == 4) {
            this.a(this.a.cu);
            return;
        }
        if (n == 5) {
            this.a(this.a.cs);
            return;
        }
        if (n == 6) {
            this.a(this.a.cw);
            return;
        }
        if (n == 7) {
            this.a(this.a.cv);
        }
    }
    
    public mb() {
        this.f = 0;
        this.i = "";
        this.j = "";
        this.k = "";
    }
}
