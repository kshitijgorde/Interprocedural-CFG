// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Image;
import com.easypano.tourweaver.d.e;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

public class f extends a
{
    Vector p;
    a q;
    boolean r;
    int s;
    Vector t;
    public static boolean u;
    
    public f() {
        this.p = new Vector();
        this.q = null;
        this.s = 0;
        this.t = new Vector();
    }
    
    public void a(final a a) {
        this.a(a, this.p.size());
    }
    
    public Enumeration e() {
        return this.p.elements();
    }
    
    public void a(final a a, final int n) {
        final boolean u = f.u;
        int contains;
        final int n2 = contains = ((this instanceof n) ? 1 : 0);
        Label_0049: {
            if (!u) {
                if (n2 != 0) {
                    final int n4;
                    final int n3 = n4 = (contains = (this.p.contains(a) ? 1 : 0));
                    if (u) {
                        break Label_0049;
                    }
                    if (n3 != 0) {
                        this.p.removeElement(a);
                    }
                }
                int n4;
                contains = (n4 = ((a.getParent() instanceof f) ? 1 : 0));
            }
        }
        if (!u) {
            if (n2 != 0) {
                ((f)a.getParent()).b(a);
            }
            contains = this.p.size() - 1;
        }
        int i = contains;
        while (true) {
            while (i >= 0) {
                final a a2 = this.p.elementAt(i);
                if (!u) {
                    final int k = a2.k();
                    final int n5 = n;
                    if (u) {
                        if (k == n5) {
                            this.p.insertElementAt(a, 0);
                        }
                        a.a(super.f);
                        a.a(this);
                        return;
                    }
                    if (k >= n) {
                        this.p.insertElementAt(a, i + 1);
                        if (!u) {
                            break;
                        }
                    }
                    --i;
                }
                if (u) {
                    break;
                }
            }
            final int n5 = -1;
            continue;
        }
    }
    
    public void b(final a a) {
        this.p.removeElement(a);
    }
    
    public void a(final Enumeration enumeration) {
        final boolean u = f.u;
        while (enumeration.hasMoreElements()) {
            this.b(enumeration.nextElement());
            if (u) {
                break;
            }
        }
    }
    
    public void f() {
        this.p.removeAllElements();
    }
    
    public int g() {
        return this.p.size();
    }
    
    protected boolean a(final MouseEvent mouseEvent, final Vector vector) {
        final boolean u = f.u;
        final int id = mouseEvent.getID();
        final int n = 506;
        int n2 = 0;
        Label_0082: {
            if (!u) {
                if (id == n) {
                    final a q = this.q;
                    if (!u) {
                        if (q == null) {
                            return false;
                        }
                        final a q2 = this.q;
                    }
                    q.a(mouseEvent.getID(), mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getModifiers(), mouseEvent.getClickCount());
                    return true;
                }
                final int id2;
                n2 = (id2 = mouseEvent.getID());
                if (u) {
                    break Label_0082;
                }
            }
            if (id == n) {
                this.q = null;
            }
            n2 = 0;
        }
        int i = n2;
        while (i <= vector.size() - 1) {
            final a q3 = vector.elementAt(i);
            final boolean id3;
            int n3;
            int contains;
            final boolean b = (contains = (n3 = ((id3 = (mouseEvent.getID() != 0)) ? 1 : 0))) != 0;
            if (u) {
                return id3;
            }
            Label_0277: {
                if (!u) {
                    switch (b) {
                        case 501: {
                            n3 = (contains = (q3.getBounds().contains(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0));
                            break;
                        }
                        case 500:
                        case 502: {
                            final boolean contains2 = q3.getBounds().contains(mouseEvent.getX(), mouseEvent.getY());
                            if (!u) {
                                if (!contains2) {
                                    break Label_0277;
                                }
                                q3.a(mouseEvent.getID(), mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getModifiers(), mouseEvent.getClickCount());
                            }
                            return contains2;
                        }
                        default: {
                            q3.a(mouseEvent.getID(), mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getModifiers(), mouseEvent.getClickCount());
                            break Label_0277;
                        }
                    }
                }
                if (!u) {
                    if (contains == 0) {
                        break Label_0277;
                    }
                    (this.q = q3).a(mouseEvent.getID(), mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getModifiers(), mouseEvent.getClickCount());
                    n3 = (true ? 1 : 0);
                }
                return n3 != 0;
            }
            ++i;
            if (u) {
                break;
            }
        }
        return false;
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        this.r = this.a(mouseEvent, this.p);
        f f = this;
        if (!com.easypano.tourweaver.b.f.u) {
            if (this.r) {
                return;
            }
            f = this;
        }
        f.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (!this.a(mouseEvent, this.p)) {
            super.processMouseMotionEvent(mouseEvent);
        }
    }
    
    public synchronized void a(final Graphics graphics) {
        final boolean u = f.u;
        final int size = this.p.size();
        if (!u) {
            if (size == 0) {
                return;
            }
            final int n = this.p.size() - 1;
        }
        int i = size;
        while (i >= 0) {
            final a a = this.p.elementAt(i);
            if (!u) {
                if (a.isVisible()) {
                    final Rectangle bounds = a.getBounds();
                    Graphics graphics2 = null;
                    try {
                        final Object create;
                        graphics2 = (Graphics)(create = graphics.create(bounds.x, bounds.y, bounds.width, bounds.height));
                        if (!u && create != null) {
                            a.paint(graphics2);
                            goto Label_0110;
                        }
                    }
                    finally {
                        final Graphics graphics3 = graphics2;
                        if (u || graphics3 != null) {
                            graphics3.dispose();
                        }
                    }
                }
                --i;
            }
            if (u) {
                break;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.a(graphics);
        super.paint(graphics);
    }
    
    public void a(final e e) {
        e e2 = e;
        Label_0027: {
            if (!f.u) {
                if (!(e instanceof a)) {
                    break Label_0027;
                }
                e2 = e;
            }
            final a a = (a)e2;
            this.a(a, a.k());
        }
        this.t.addElement(e);
    }
    
    public void b(final e e) {
        final boolean b = e instanceof a;
        if (!f.u) {
            if (b) {
                this.b((a)e);
            }
            this.t.removeElement(e);
        }
    }
    
    public e a(final String s) {
        return null;
    }
    
    public Enumeration d() {
        return this.t.elements();
    }
    
    public void a(final Image image, final String s) {
        final boolean u = f.u;
        final Enumeration d = this.d();
        while (d.hasMoreElements()) {
            d.nextElement().a(image, s);
            if (u) {
                break;
            }
        }
    }
    
    public void destroy() {
        final boolean u = f.u;
        final Enumeration<a> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().destroy();
            if (u) {
                break;
            }
        }
    }
}
