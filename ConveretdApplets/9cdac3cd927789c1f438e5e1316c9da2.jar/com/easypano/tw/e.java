// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Graphics;
import com.easypano.tw.c.j;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import com.easypano.tw.c.k;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Cursor;
import com.easypano.tw.c.p;
import java.beans.PropertyChangeSupport;
import java.awt.Container;

public class e extends Container implements db
{
    protected PropertyChangeSupport a;
    protected e b;
    protected p c;
    protected Cursor d;
    protected boolean e;
    protected boolean f;
    protected Dimension g;
    protected e[] h;
    protected boolean i;
    private du j;
    public static boolean k;
    
    public e() {
        this.a = new PropertyChangeSupport(this);
        this.b = null;
        this.c = null;
        this.d = Cursor.getDefaultCursor();
        this.e = true;
        this.f = false;
        this.g = null;
        this.h = new e[0];
        this.i = true;
        this.j = null;
        this.addMouseListener(new x(this));
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
        final boolean q = com.easypano.tw.g.q;
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
        final g g = new g();
        g.setBackground(background);
        final j j = new j(g);
        j.d(color);
        g.a(j);
        g.e().a(s);
        this.a(g);
    }
    
    public void a(final e e) {
        final boolean q = com.easypano.tw.g.q;
        final e b;
        e b3 = null;
        Label_0040: {
            if (!q) {
                if (e != null) {
                    b = this.b;
                    if (!q) {
                        if (b != null) {
                            final e b2 = this.b;
                            b3 = e;
                            if (q) {
                                break Label_0040;
                            }
                            if (b2.equals(e)) {
                                return;
                            }
                        }
                    }
                }
            }
            b3 = e;
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
        final e b = this.b;
        if (!com.easypano.tw.g.q) {
            if (b == null) {
                return;
            }
            final e b2 = this.b;
        }
        b.setBackground(color);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean q = com.easypano.tw.g.q;
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
        final boolean q = com.easypano.tw.g.q;
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
            if (!(container instanceof e)) {
                return;
            }
            container2 = this.getParent();
        }
        ((e)container2).a(rectangle);
    }
    
    protected Object clone() throws CloneNotSupportedException {
        final e e = (e)super.clone();
        e.a = new PropertyChangeSupport(this);
        final e b = this.b;
        if (!com.easypano.tw.g.q) {
            if (b != null) {
                e.b = (e)this.b.clone();
            }
            e.d = Cursor.getPredefinedCursor(this.d.getType());
        }
        return b;
    }
    
    public Dimension getPreferredSize() {
        final boolean q = com.easypano.tw.g.q;
        final Dimension a = this.c.a();
        e e = this;
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
            e = this;
        }
        final Dimension g = e.g;
        return dimension;
    }
    
    public void a(final Dimension g) {
        this.g = g;
    }
    
    public void b(final boolean f) {
        this.f = f;
    }
    
    public boolean b() {
        return this.f;
    }
    
    public void requestFocus() {
        e e = this;
        if (!com.easypano.tw.g.q) {
            if (!this.f) {
                return;
            }
            e = this;
        }
        e.requestFocus();
    }
    
    public void a(final du j) {
        this.j = j;
    }
    
    public void repaint(final long n, final int n2, final int n3, final int n4, final int n5) {
        e e = this;
        if (!com.easypano.tw.g.q) {
            if (!this.i) {
                return;
            }
            e = this;
        }
        e.repaint(n, n2, n3, n4, n5);
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
            if (com.easypano.tw.g.q) {
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
                if (component instanceof e) {
                    ((e)component).destroyResource();
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
    
    static du b(final e e) {
        return e.j;
    }
}
