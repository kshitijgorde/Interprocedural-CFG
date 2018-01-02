// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Event;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Panel;

public class tooltip extends Panel
{
    public int a;
    public int b;
    public int c;
    Color d;
    Color e;
    Image f;
    Font g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    boolean n;
    boolean o;
    FontMetrics p;
    timer q;
    Container r;
    menu_item s;
    Event t;
    static Hashtable u;
    
    private tooltip(final pen pen, final menu_item menu_item, final String s) {
        this.a = 20;
        this.b = 12;
        this.c = 6;
        this.a(pen, menu_item);
        this.q = new timer(this, s);
        try {
            this.q.start();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            abljem.b("tip_timer unable to start: out of memory.");
        }
    }
    
    private void a(final pen pen, final menu_item s) {
        this.s = s;
        if (pen != null) {
            this.e = pen.b;
            this.d = pen.c;
            this.g = pen.d;
        }
        this.p = this.getFontMetrics(this.g);
        this.j = this.p.stringWidth(" " + s.d + " ");
        this.k = this.p.getLeading() + this.p.getMaxAscent() + this.p.getMaxDescent();
        super.setSize(this.j + 2, this.k + 2);
        super.setVisible(false);
    }
    
    void a(final Event event) {
        for (Container parent = this.getParent(), parent2 = (Container)event.target; parent2 != null && parent2 != parent; parent2 = parent2.getParent()) {
            final Point location = parent2.getLocation();
            event.x += location.x;
            event.y += location.y;
        }
    }
    
    public void setLocation(int left, int bottom) {
        final Dimension size = this.r.getSize();
        final Insets insets = this.r.getInsets();
        final Dimension size2 = this.getSize();
        this.h = left;
        this.i = bottom;
        if (left + size2.width > size.width - insets.left) {
            left -= size2.width + this.a * 2 - this.c;
        }
        if (left < insets.left) {
            left = insets.left;
        }
        if (bottom + size2.height > size.height - insets.bottom) {
            bottom -= size2.height + this.b * 2 - this.c;
        }
        if (bottom < insets.bottom) {
            bottom = insets.bottom;
        }
        if (this.n) {
            super.setLocation(left, bottom);
        }
    }
    
    public boolean isVisible() {
        return this.n;
    }
    
    public boolean a() {
        return this.o;
    }
    
    public void a(final Container container, final boolean b) {
        if (b) {
            this.a(container, null, b, this.s.e);
        }
        else {
            this.a(container, null, b, 0L);
        }
    }
    
    public void a(final Container r, Event t, final boolean visible, final long n) {
        if (t != null) {
            this.t = t;
        }
        if (r != null) {
            this.r = r;
        }
        this.o = visible;
        if (n == 0L) {
            if (visible) {
                this.r.add(this, 0);
            }
            else {
                this.r.remove(this);
            }
            this.q.a(false);
            this.n = visible;
            t = this.t;
            if (t != null) {
                t = new Event(t.target, t.when, t.id, t.x, t.y, t.key, t.modifiers);
                this.a(t);
                this.h = t.x + this.a;
                this.i = t.y + this.b;
            }
            super.setSize(this.j + 2, this.k + 2);
            super.setVisible(visible);
            this.setLocation(this.h, this.i);
        }
        else {
            this.q.a(n, this.q, true);
        }
    }
    
    void b() {
        final int width = this.size().width;
        final int height = this.size().height;
        if (width != this.l || height != this.m || this.f == null) {
            this.f = utils.a(this, width, height);
            this.m = height;
            this.l = width;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.n) {
            this.setLocation(this.h, this.i);
            super.setSize(this.j + 2, this.k + 2);
        }
        this.b();
        if (this.f != null) {
            this.a(this.f.getGraphics());
            graphics.drawImage(this.f, 0, 0, this);
        }
    }
    
    void a(final Graphics graphics) {
        utils.a(graphics, this.d, 0, 0, this.size().width, this.size().height);
        graphics.setColor(this.e);
        graphics.setFont(this.g);
        graphics.drawString(" " + this.s.d + " ", 1, this.k - 1);
        graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.arg == this.q) {
            this.a(this.r, null, this.o, 0L);
            if (this.o) {
                this.a(this.r, null, false, this.s.f);
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    protected void finalize() {
        this.q.stop();
        this.q = null;
    }
    
    public static void a(final Object o, final Container container, final pen pen, final menu_item menu_item, final Event event) {
        if (o == null) {
            abljem.b("Null ref in tooltip activate");
            return;
        }
        if (menu_item == null || menu_item.d == null || menu_item.d.length() == 0) {
            return;
        }
        final tooltip b = b(o);
        if (b != null) {
            b.a(null, false);
        }
        a(o, pen, menu_item, container).a(container, event, true, menu_item.e);
    }
    
    public static void a(final Object o) {
        if (o == null) {
            return;
        }
        final tooltip b = b(o);
        if (b != null && (b.isVisible() || b.a())) {
            b.a(null, false);
        }
    }
    
    private static tooltip b(final Object o) {
        return tooltip.u.get(o);
    }
    
    private static tooltip a(final Object o, final pen pen, final menu_item menu_item, final Container container) {
        tooltip b = b(o);
        if (b == null) {
            String s = "tooltip";
            if (o != null) {
                s = String.valueOf(s) + ":" + o.toString();
            }
            if (container != null && container instanceof run_prefix) {
                s = String.valueOf(((run_prefix)container).a()) + s;
            }
            b = new tooltip(pen, menu_item, s);
            tooltip.u.put(o, b);
        }
        else {
            b.a(pen, menu_item);
        }
        return b;
    }
    
    static {
        tooltip.u = new Hashtable();
    }
}
