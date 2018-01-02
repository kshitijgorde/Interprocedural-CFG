// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Graphics;
import com.easypano.tw.d.j;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import com.easypano.tw.d.k;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Cursor;
import com.easypano.tw.d.p;
import java.beans.PropertyChangeSupport;
import java.awt.Container;

public class f extends Container implements db
{
    protected PropertyChangeSupport a;
    protected f b;
    protected p c;
    protected Cursor d;
    protected boolean e;
    protected boolean f;
    protected Dimension g;
    protected f[] h;
    protected boolean i;
    private du j;
    public static int k;
    
    public f() {
        this.a = new PropertyChangeSupport(this);
        this.b = null;
        this.c = null;
        this.d = Cursor.getDefaultCursor();
        this.e = true;
        this.f = false;
        this.g = null;
        this.h = new f[0];
        this.i = true;
        this.j = null;
        this.addMouseListener(new y(this));
        this.addMouseMotionListener(new bh(this));
        this.a(new k(this));
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.a.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void a(final PropertyChangeListener propertyChangeListener) {
        this.a.removePropertyChangeListener(propertyChangeListener);
    }
    
    public void a(final String s) {
        this.a(s, Color.yellow);
    }
    
    public void a(final String s, final Color color) {
        this.a(s, color, null);
    }
    
    public void a(final String s, final Color background, final Color color) {
        final boolean q = com.easypano.tw.h.q;
        String s2 = s;
        Label_0033: {
            Label_0023: {
                if (!q) {
                    if (s == null) {
                        break Label_0023;
                    }
                    s2 = s;
                }
                if (!s2.equals("")) {
                    break Label_0033;
                }
            }
            this.b = null;
            if (!q) {
                return;
            }
        }
        final h h = new h();
        h.setBackground(background);
        final j j = new j(h);
        j.d(color);
        h.a(j);
        h.e().a(s);
        this.a(h);
    }
    
    public void a(final f f) {
        final boolean q = com.easypano.tw.h.q;
        final f b;
        f b3 = null;
        Label_0040: {
            if (!q) {
                if (f != null) {
                    b = this.b;
                    if (!q) {
                        if (b != null) {
                            final f b2 = this.b;
                            b3 = f;
                            if (q) {
                                break Label_0040;
                            }
                            if (b2.equals(f)) {
                                return;
                            }
                        }
                    }
                }
            }
            b3 = f;
        }
        b.b = b3;
    }
    
    public void a(final p c) {
        this.c = c;
    }
    
    public p a() {
        return this.c;
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    public boolean isOpaque() {
        return this.e;
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        final f b = this.b;
        if (!com.easypano.tw.h.q) {
            if (b == null) {
                return;
            }
            final f b2 = this.b;
        }
        b.setBackground(color);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean q = com.easypano.tw.h.q;
        p p2;
        final p p = p2 = this.c;
        if (!q) {
            if (p != null) {
                this.c.a(graphics);
            }
            super.paint(graphics);
            final p c;
            p2 = (c = this.c);
        }
        if (!q) {
            if (p == null) {
                return;
            }
            p2 = this.c;
        }
        p2.d(graphics);
    }
    
    public void a(final int n) {
        super.setCursor(Cursor.getPredefinedCursor(n));
    }
    
    public void a(final Rectangle rectangle) {
        final boolean q = com.easypano.tw.h.q;
        Container container2;
        final Container container = container2 = this.getParent();
        if (!q) {
            if (container == null) {
                return;
            }
            final Container parent;
            container2 = (parent = this.getParent());
        }
        if (!q) {
            if (!(container instanceof f)) {
                return;
            }
            container2 = this.getParent();
        }
        ((f)container2).a(rectangle);
    }
    
    protected Object clone() throws CloneNotSupportedException {
        final f f = (f)super.clone();
        f.a = new PropertyChangeSupport(this);
        final f b = this.b;
        if (!com.easypano.tw.h.q) {
            if (b != null) {
                f.b = (f)this.b.clone();
            }
            f.d = Cursor.getPredefinedCursor(this.d.getType());
        }
        return b;
    }
    
    public Dimension getPreferredSize() {
        final boolean q = com.easypano.tw.h.q;
        final Dimension a = this.c.a();
        f f = this;
        final Dimension dimension;
        if (!q) {
            if (this.g == null) {
                dimension = a;
                if (!q) {
                    if (dimension == null) {
                        super.getPreferredSize();
                    }
                }
                return dimension;
            }
            f = this;
        }
        final Dimension g = f.g;
        return dimension;
    }
    
    public void setPreferredSize(final Dimension g) {
        this.g = g;
    }
    
    public void b(final boolean f) {
        this.f = f;
    }
    
    public boolean b() {
        return this.f;
    }
    
    public void requestFocus() {
        f f = this;
        if (!com.easypano.tw.h.q) {
            if (!this.f) {
                return;
            }
            f = this;
        }
        f.requestFocus();
    }
    
    public void a(final du j) {
        this.j = j;
    }
    
    public void repaint(final long n, final int n2, final int n3, final int n4, final int n5) {
        f f = this;
        if (!com.easypano.tw.h.q) {
            if (!this.i) {
                return;
            }
            f = this;
        }
        f.repaint(n, n2, n3, n4, n5);
    }
    
    public void c(final boolean i) {
        this.i = i;
    }
    
    public boolean c() {
        return this.i;
    }
    
    public void d() {
        final Graphics graphics = this.getGraphics();
        Label_0020: {
            if (com.easypano.tw.h.q) {
                break Label_0020;
            }
            if (graphics == null) {
                return;
            }
            try {
                this.paint(graphics);
            }
            catch (Exception ex) {}
            finally {
                graphics.dispose();
            }
        }
        graphics.dispose();
    }
    
    public void destroyResource() {
        try {
            for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
                final Component component = this.getComponent(i);
                if (component instanceof f) {
                    ((f)component).destroyResource();
                }
            }
            this.removeAll();
            if (this.c != null) {
                this.c.destroyResource();
                this.c = null;
            }
            if (this.b != null) {
                this.b.destroyResource();
                this.b = null;
            }
            this.d = null;
            this.h = null;
            this.j = null;
        }
        catch (Exception ex) {}
    }
    
    static du b(final f f) {
        return f.j;
    }
}
