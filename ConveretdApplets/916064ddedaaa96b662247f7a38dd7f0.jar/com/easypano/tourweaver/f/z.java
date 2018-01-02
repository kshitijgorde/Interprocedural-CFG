// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.util.Enumeration;
import java.util.Vector;

public class z
{
    Vector a;
    Vector b;
    b c;
    p d;
    
    public z() {
        this.a = new Vector();
        this.b = new Vector();
        this.d = new p();
    }
    
    public void a(final b c) {
        this.c = c;
    }
    
    public y a() {
        final boolean i = r.i;
        final Enumeration<y> elements = (Enumeration<y>)this.a.elements();
        while (elements.hasMoreElements()) {
            final y nextElement = elements.nextElement();
            if (i || nextElement instanceof y) {
                return nextElement;
            }
            if (i) {
                break;
            }
        }
        return null;
    }
    
    public boolean a(final a a) {
        return this.b.contains(a);
    }
    
    public a a(final String s) {
        final boolean i = r.i;
        if (s == null) {
            return null;
        }
        int j = 0;
        while (j < this.b.size()) {
            final a a = this.b.elementAt(j);
            final String name = a.getName();
            if (!i) {
                if (name != null && name.equals(s)) {
                    return a;
                }
                ++j;
            }
            if (i) {
                break;
            }
        }
        return null;
    }
    
    public void b() {
        final boolean i = r.i;
        final Enumeration<a> elements = (Enumeration<a>)this.b.elements();
        while (elements.hasMoreElements()) {
            final a a = elements.nextElement();
            if (i || a instanceof k) {
                a.c();
            }
            if (i) {
                break;
            }
        }
    }
    
    public void b(final a a) {
        final boolean i = r.i;
        a a2 = a;
        if (!i) {
            if (a == null) {
                return;
            }
            a.a();
            a2 = a;
        }
        a2.b();
        Vector vector2;
        final Vector vector = vector2 = this.b;
        if (!i) {
            if (vector.contains(a)) {
                return;
            }
            vector2 = this.b;
        }
        final Enumeration<a> elements = vector2.elements();
        while (elements.hasMoreElements()) {
            final a a3 = elements.nextElement();
            if (i) {
                return;
            }
            final boolean d = a.d(a3);
            if (!i) {
                if (d) {
                    a3.c();
                    this.b.removeElement(a3);
                }
            }
            if (i) {
                break;
            }
        }
        this.b.addElement(a);
    }
    
    public void c() {
        final boolean i = r.i;
        final Enumeration<a> elements = (Enumeration<a>)this.b.elements();
        while (elements.hasMoreElements()) {
            final a a2;
            final a a = a2 = elements.nextElement();
            Label_0196: {
                if (!i && !a2.i()) {
                    this.b.removeElement(a);
                    final a j = a.j();
                    if (!i && j != null) {
                        a.j().a(a.l());
                        this.b(a.j());
                        a.j().b();
                        if (i) {
                            goto Label_0112;
                        }
                    }
                    else {
                        final h k = j.k();
                        if (!i) {
                            if (k == null) {
                                break Label_0196;
                            }
                            a.k();
                        }
                        final h h = k;
                        h.a(a.l());
                        final b c = this.c;
                        Label_0185: {
                            if (!i) {
                                if (c == null) {
                                    break Label_0185;
                                }
                                final b c2 = this.c;
                            }
                            final a a3 = null;
                            final h h2 = h;
                            final int n = (h instanceof y) ? 1 : 0;
                            if (!i && n == 0) {}
                            c.updateRenderable(a3, h2, n);
                        }
                        if (i) {
                            goto Label_0190;
                        }
                    }
                }
                else {
                    a2.f();
                }
            }
            if (i) {
                break;
            }
        }
    }
    
    public boolean a(final h h) {
        return this.a.contains(h);
    }
    
    public void b(final h h) {
        z z = this;
        if (!r.i) {
            if (this.a(h)) {
                return;
            }
            z = this;
        }
        z.a.addElement(h);
    }
    
    public void c(final h h) {
        this.a.removeElement(h);
    }
    
    public h b(final String s) {
        final boolean i = r.i;
        final Enumeration<h> elements = (Enumeration<h>)this.a.elements();
        while (elements.hasMoreElements()) {
            final h h = elements.nextElement();
            final String name = h.getName();
            if ((i || name != null) && name.equals(s)) {
                return h;
            }
            if (i) {
                break;
            }
        }
        return null;
    }
    
    public void d() {
        this.a(this.a);
    }
    
    private void a(final Vector vector) {
        final boolean i = r.i;
        System.currentTimeMillis();
        int j = 0;
        while (j < vector.size()) {
            final h h = vector.elementAt(j);
            final boolean f = h.f();
            Label_0090: {
                Label_0084: {
                    Label_0067: {
                        final h h2;
                        Label_0062: {
                            if (!i) {
                                if (!f) {
                                    break Label_0084;
                                }
                                h2 = h;
                                if (i) {
                                    break Label_0062;
                                }
                                final boolean b = h2 instanceof s;
                            }
                            if (!f) {
                                break Label_0067;
                            }
                        }
                        h2.c();
                    }
                    this.a.removeElementAt(j);
                    --j;
                    if (!i) {
                        break Label_0090;
                    }
                }
                this.d(h);
            }
            ++j;
            if (i) {
                break;
            }
        }
    }
    
    public void d(final h h) {
        this.d.a(h);
    }
}
