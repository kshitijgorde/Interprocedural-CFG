// 
// Decompiled by Procyon v0.5.30
// 

package com.NeoVision.WebHeatmaps.Client;

import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Container;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.URL;
import java.awt.Graphics;
import java.util.Properties;
import java.applet.Applet;

public final class WhClientApplet extends Applet implements b
{
    public boolean a;
    public Integer b;
    public Integer c;
    public String d;
    public d e;
    public q f;
    
    public WhClientApplet() {
        this.a = false;
    }
    
    public final void init() {
        this.a(this.getCodeBase(), new c(this));
    }
    
    public final void start() {
        this.a = false;
        Object applets;
        try {
            applets = this.getAppletContext().getApplets();
        }
        catch (Throwable t) {
            applets = null;
        }
        if (applets != null) {
            while (((Enumeration)applets).hasMoreElements()) {
                final WhClientApplet nextElement = ((Enumeration<WhClientApplet>)applets).nextElement();
                if (nextElement instanceof WhClientApplet && nextElement.a() && !nextElement.b()) {
                    try {
                        nextElement.destroy();
                    }
                    catch (Throwable t2) {}
                }
            }
        }
        if (this.b()) {
            this.a(this.getCodeBase(), new c(this));
        }
    }
    
    public final void stop() {
        this.a = true;
    }
    
    public final boolean a() {
        return this.a;
    }
    
    public final boolean b() {
        return this.e == null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void a(final URL url, final Properties properties) {
        this.e = new d(url, this, properties);
        if (this.e.q) {
            this.Update(this.e.k());
        }
        if (!this.e.q) {
            this.setLayout(new BorderLayout());
            final o o = new o(this.e);
            o.a(e.a(14, new Object[] { d.b() }));
            this.add("Center", o);
        }
        else {
            if (this.b != null) {
                if (this.c != null) {
                    if (this.d != null) {
                        this.setLayout(new BorderLayout());
                        final Button button = new Button(this.d);
                        button.setBackground(this.e.i().c);
                        final Button button2 = button;
                        this.e.d();
                        button2.setForeground(f.a(button.getBackground()));
                        button.setFont(this.e.i().o);
                        this.add("Center", button);
                        return;
                    }
                    this.c();
                    return;
                }
            }
            try {
                this.f = new q(this.e, this.e.k(), this);
            }
            catch (Throwable t) {
                t.printStackTrace();
                this.e.c().a(this, 12, new Object[] { t.getMessage() });
                this.f = null;
            }
        }
    }
    
    public final boolean action(final Event event, final Object o) {
        this.c();
        return true;
    }
    
    public void Launch(final String s, final String s2, final String s3) {
        final String s4 = "\u007f";
        if (this.e.k() instanceof c) {
            ((c)this.e.k()).a(this);
        }
        n a;
        if ((a = this.e.g().a(s4)) == null) {
            a = new n(this.e, this.e.d().a(this), s4, true);
        }
        this.e.g().a(a);
        a.Reset(s, s2, s3, null);
        a.toFront();
    }
    
    private void c() {
        final Properties k = this.e.k();
        final String property;
        if ((property = k.getProperty("Heatmap")) == null) {
            this.e.c().a((Component)this.e.s, 15, new Object[] { "Heatmap" });
        }
        else {
            final String property2 = k.getProperty("InitToken");
            String string = null;
            final String s = "KeyList1";
            final String property3;
            if ((property3 = k.getProperty(s)) != null) {
                string = property3;
                final String substring = s.substring(0, s.length() - 1);
                String property4;
                for (int n = 2; (property4 = k.getProperty(substring + n)) != null; ++n) {
                    string = string + ";" + property4;
                }
            }
            this.Launch(property, property2, string);
        }
    }
    
    public final void Update(final Properties properties) {
        final String property;
        if ((property = properties.getProperty("TearOffWidth")) != null) {
            try {
                final Integer c = this.e.d().c(property);
                if (c >= 0) {
                    this.b = c;
                }
                else {
                    this.e.c().a((Component)this.e.s, 4, new Object[] { property, "TearOffWidth", "negative" });
                }
            }
            catch (Exception ex) {
                this.e.c().a((Component)this.e.s, 4, new Object[] { property, "TearOffWidth", ex.getMessage() });
            }
        }
        final String property2;
        if ((property2 = properties.getProperty("TearOffHeight")) != null) {
            try {
                final Integer c2 = this.e.d().c(property2);
                if (c2 >= 0) {
                    this.c = c2;
                }
                else {
                    this.e.c().a((Component)this.e.s, 4, new Object[] { property2, "TearOffHeight", "negative" });
                }
            }
            catch (Exception ex2) {
                this.e.c().a((Component)this.e.s, 4, new Object[] { property2, "TearOffHeight", ex2.getMessage() });
            }
        }
        this.d = properties.getProperty("Caption");
        if (this.b == null || this.c == null) {
            final Integer n = null;
            this.c = n;
            this.b = n;
            this.d = null;
        }
    }
    
    public final Dimension GetSize() {
        final Dimension size = this.size();
        if (this.b != null && this.c != null) {
            size.width = this.b;
            size.height = this.c;
        }
        return size;
    }
    
    public final void Reset(final String s, final String s2, final String s3, final t t) {
        try {
            this.Destroy();
            (this.f = new q(this.e, this.e.k(), this, s, s2, s3, false)).a(t);
        }
        catch (Throwable t2) {
            t2.printStackTrace();
            this.e.c().a(this, 12, new Object[] { t2.getMessage() });
            this.f = null;
        }
    }
    
    public final void Reset(final q q, final String s, final t t) {
        try {
            final q f = new q(this.e, this.e.k(), this, q, s);
            f.a(t);
            this.Destroy();
            this.f = f;
        }
        catch (Throwable t2) {
            t2.printStackTrace();
            this.e.c().a(this, 12, new Object[] { t2.getMessage() });
            this.f = null;
        }
    }
    
    public final void Destroy() {
        if (this.e.q) {
            try {
                if (this.f != null) {
                    this.f.e();
                    this.f = null;
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
                this.e.c().a(this, 1, new Object[] { t.getMessage() });
                this.f = null;
            }
        }
    }
    
    public final void destroy() {
        try {
            if (!this.b()) {
                if (this.e.q) {
                    try {
                        this.e.l();
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                        this.e.c().a(this, 1, new Object[] { t.getMessage() });
                    }
                }
                this.d();
            }
        }
        catch (Throwable t2) {
            System.err.println("~" + this.getClass().getName() + "()");
            t2.printStackTrace(System.err);
        }
    }
    
    private void d() {
        if (this.e != null) {
            this.e.m();
            this.e = null;
        }
        System.gc();
        Thread.currentThread();
        Thread.yield();
    }
}
