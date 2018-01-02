// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.toolbar;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Component;
import com.iseemedia.apps.tourclients40.players.h;
import java.applet.AppletContext;

public final class g
{
    private AppletContext a;
    private h b;
    private b c;
    private f d;
    private d e;
    private d f;
    private a g;
    
    public g(final h h, final int n, final AppletContext appletContext, final boolean b) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        if (h.l == 1) {
            this.g = new a();
            this.b = h;
            this.a = appletContext;
            this.c = new b(this.b, this.g, this.a);
            this.b.add(this.c);
            this.c.reshape(0, n - this.c.e, this.c.d, this.c.e);
            this.f = new d(this.g.b, this.g, this.b);
            this.b.add(this.f);
            this.f.reshape(this.c.d, n - this.g.c, this.g.b, this.g.c);
            (this.d = new f(this, b, this.g, this.b)).setLayout(null);
            this.b.add(this.d);
            this.d.reshape(this.c.d + this.g.b, n - this.g.c, this.g.b * 6, this.g.c);
            this.d.a();
            final int n2 = this.b.size().width - this.c.d - this.g.b * 6;
            this.e = new d(n2, this.g, this.b);
            this.b.add(this.e);
            this.e.reshape(this.c.d + this.g.b + this.g.b * 6, n - this.g.c, n2, this.g.c);
            return;
        }
        this.g = new a();
        this.b = h;
        this.a = appletContext;
        this.c = new b(this.b, this.g, this.a);
        this.b.add(this.c);
        this.f = new d(this.g.b, this.g, this.b);
        this.b.add(this.f);
        (this.d = new f(this, b, this.g, this.b)).setLayout(null);
        this.b.add(this.d);
        this.d.a();
        this.e = new d(this.b.size().width - this.c.d - this.g.b * 6, this.g, this.b);
        this.b.add(this.e);
    }
    
    public final void a() {
        this.a = null;
        if (this.d != null) {
            this.d.b();
            this.d = null;
        }
        if (this.c != null) {
            this.c.a();
            this.c = null;
        }
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
        this.b = null;
    }
    
    public final void a(final int n) {
        this.d.a(n);
    }
    
    public final boolean b() {
        return this.c != null && this.c.b();
    }
    
    public final void c() {
        if (this.d != null) {
            this.d.c();
        }
    }
    
    public final void d() {
        if (this.d != null) {
            this.d.show();
        }
    }
    
    public final void b(final int n) {
        this.b.c(n);
    }
    
    final void a(final int n, final int n2) {
        switch (n) {
            case 0: {
                if (n2 == 2) {
                    this.b.K = true;
                    return;
                }
                break;
            }
            case 2: {
                if (n2 == 2) {
                    this.e();
                    break;
                }
                break;
            }
        }
    }
    
    final void a(final String s) {
        this.a.showStatus(s);
    }
    
    public final void e() {
        try {
            this.a.showDocument(new URL(this.g.h), "iseemedia");
        }
        catch (MalformedURLException ex) {}
    }
}
