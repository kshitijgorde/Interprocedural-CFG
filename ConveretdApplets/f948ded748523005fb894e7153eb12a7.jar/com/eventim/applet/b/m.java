// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import com.eventim.applet.a.b;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.Color;
import com.eventim.applet.a.f;
import java.awt.Graphics2D;
import java.util.ArrayList;
import com.eventim.applet.a.q;
import com.eventim.applet.EventimApplet;
import java.awt.Dimension;
import com.eventim.applet.a.g;
import com.eventim.applet.a.c;
import java.util.List;
import java.awt.Rectangle;
import com.eventim.applet.a.k;

public final class m extends t
{
    private k b;
    private Rectangle c;
    private List d;
    private c e;
    private Rectangle f;
    
    public m(final g g, final Dimension dimension, final EventimApplet eventimApplet) {
        super(g, dimension, eventimApplet);
        this.b = q.g;
        this.d = new ArrayList();
        this.f = new Rectangle();
        this.c = new Rectangle();
        this.e = null;
    }
    
    public final void a() {
        if (this.b != null || !this.d.isEmpty()) {
            this.a(this.c());
            this.repaint();
        }
    }
    
    private void a(final Graphics2D graphics2D) {
        for (int i = 0; i < this.d.size(); ++i) {
            final f f;
            final Rectangle bounds = (f = this.d.get(i)).g_().getBounds();
            this.c.setBounds(bounds.x, bounds.y, bounds.width + 1, bounds.height + 1);
            graphics2D.setPaint(Color.white);
            graphics2D.fill(this.c);
            f.b(f.b());
            f.a(f.a());
            f.a(graphics2D);
        }
        if (this.b != null) {
            final Rectangle bounds2 = this.b.g_().getBounds();
            this.c.setBounds(bounds2.x, bounds2.y, bounds2.width + 1, bounds2.height + 1);
            graphics2D.setPaint(Color.white);
            graphics2D.fill(this.c);
            this.b.b(this.b.b());
            this.b.a(this.b.a());
            this.b.a(graphics2D);
        }
        this.b = null;
        this.d.clear();
    }
    
    private void a(final Graphics2D graphics2D, final k k, final Color color, final Color color2, final int n) {
        final Rectangle bounds = k.g_().getBounds();
        this.f.setBounds(bounds.x - n, bounds.y - n, bounds.width + ((n << 1) + 1), bounds.height + (n << 1));
        graphics2D.setPaint(this.a.d().B());
        graphics2D.fill(this.f);
        final Color b = k.b();
        final Color a = k.a();
        k.b(color);
        k.a(color2);
        k.a(graphics2D);
        k.b(b);
        k.a(a);
    }
    
    public final void a(final f f) {
        if (f != null) {
            this.a(f, f.b(), f.a(), 0);
            this.b = null;
        }
    }
    
    public final void a(final b b) {
        if (!b.f_()) {
            if (this.b != null) {
                this.a(this.b, this.b.b(), this.b.a());
                this.b = null;
            }
            final c e = (c)b;
            final Graphics2D c = this.c();
            if (e != this.e) {
                final k[] l;
                if ((l = e.l()) != null && l.length > 0) {
                    final Shape g_;
                    final Rectangle bounds = (g_ = l[0].g_()).getBounds();
                    this.a.a(bounds.x, bounds.y);
                    if (this.e != null) {
                        this.b(c);
                    }
                    c.setPaint(Color.red);
                    c.draw(g_);
                }
                this.e = e;
            }
            else {
                this.b(c);
                this.e = null;
            }
            this.repaint();
            return;
        }
        if (this.e != null) {
            this.b(this.c());
            this.e = null;
        }
        final f f;
        if (!(f = (f)b).equals(this.b)) {
            if (this.g() != 0) {
                this.a(0);
                this.a.a(this);
                this.a.b(this);
            }
            final Rectangle bounds2 = f.g_().getBounds();
            this.a.a(bounds2.x, bounds2.y);
            this.a(f, Color.red, f.a());
            return;
        }
        this.a(f, f.b(), f.a());
        this.b = null;
    }
    
    public final void b(final f f) {
        this.a(f, q.i, f.a());
    }
    
    public final void a(List d) {
        final m m = this;
        final List<f> list = d;
        final Color i = q.i;
        d = list;
        this = m;
        final Graphics2D c = m.c();
        this.a(c);
        for (int j = 0; j < d.size(); ++j) {
            final f f = d.get(j);
            this.a(c, f, i, f.a(), 0);
        }
        this.repaint();
        this.b = null;
        this.d = d;
    }
    
    private void a(final k b, final Color color, final Color color2) {
        this.a();
        this.a(b, color, color2, 0);
        this.b = b;
        this.d.clear();
    }
    
    public final void a(final k k, final Color color, final Color color2, final int n) {
        this.a(this.c(), k, color, color2, n);
        this.repaint();
    }
    
    public final void a(final short n) {
        final f[] a;
        if ((a = this.e().a()) != null) {
            for (int i = 0; i < a.length; ++i) {
                a[i].a(n);
            }
        }
        final c[] c;
        if ((c = this.e().c()) != null) {
            for (int j = 0; j < c.length; ++j) {
                c[j].a(n);
            }
        }
    }
    
    private void b(final Graphics2D graphics2D) {
        final k[] l;
        if ((l = this.e.l()) != null && l.length > 0) {
            final k k;
            final Shape g_ = (k = l[0]).g_();
            graphics2D.setStroke(k.f());
            graphics2D.setPaint(k.b());
            graphics2D.draw(g_);
        }
    }
}
