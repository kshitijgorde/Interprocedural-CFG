// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;
import com.easypano.tourweaver.d.e;

public class r implements h, e
{
    String d;
    Vector e;
    String f;
    int g;
    Vector h;
    public static boolean i;
    
    public r() {
        this.h = new Vector();
        this.e = new Vector();
    }
    
    public void c() {
        final boolean i = r.i;
        this.h.removeAllElements();
        final Enumeration<com.easypano.tourweaver.f.e> elements = this.e.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().destroy();
            if (i) {
                return;
            }
            if (i) {
                break;
            }
        }
        this.e.removeAllElements();
    }
    
    public void setName(final String d) {
        this.d = d;
    }
    
    public String getName() {
        return this.d;
    }
    
    public void a(final e e) {
        if (e instanceof com.easypano.tourweaver.f.e) {
            this.a((com.easypano.tourweaver.f.e)e);
        }
    }
    
    public void a(final com.easypano.tourweaver.f.e e) {
        this.e.addElement(e);
    }
    
    public Enumeration i() {
        return this.e.elements();
    }
    
    public Enumeration d() {
        return this.e.elements();
    }
    
    public void a(final Image image, final String s) {
        final boolean i = r.i;
        int j = 0;
        while (j < this.e.size()) {
            final com.easypano.tourweaver.f.e e = this.e.elementAt(j);
            if (!i) {
                if (e instanceof e) {
                    ((e)e).a(image, s);
                }
                ++j;
            }
            if (i) {
                break;
            }
        }
    }
    
    public void addAttributes(final String s, final String s2) {
    }
    
    public void b(final e e) {
        this.e.removeElement(e);
    }
    
    public e a(final String s) {
        final boolean i = r.i;
        if (s == null) {
            return null;
        }
        int j = 0;
        while (j < this.e.size()) {
            final com.easypano.tourweaver.f.e e = this.e.elementAt(j);
            final String name = e.getName();
            if (!i) {
                Label_0085: {
                    if (name != null) {
                        final boolean equals = name.equals(s);
                        if (!i) {
                            if (!equals) {
                                break Label_0085;
                            }
                            final com.easypano.tourweaver.f.e e3;
                            final com.easypano.tourweaver.f.e e2 = e3 = e;
                            if (i) {
                                return (e)e3;
                            }
                            final boolean b = e2 instanceof e;
                        }
                        if (!equals) {
                            break Label_0085;
                        }
                        com.easypano.tourweaver.f.e e3 = e;
                        return (e)e3;
                    }
                }
                ++j;
            }
            if (i) {
                break;
            }
        }
        return null;
    }
    
    public void a(final String f, final int g) {
        this.f = f;
        this.g = g;
    }
    
    public String a() {
        return this.f;
    }
    
    public int b() {
        return this.g;
    }
    
    public void a(final d d) {
        Vector vector2;
        final Vector vector = vector2 = this.h;
        d d2 = d;
        if (!r.i) {
            if (vector.contains(d)) {
                return;
            }
            vector2 = this.h;
            d2 = d;
        }
        vector2.addElement(d2);
        d.a(this);
    }
    
    public Enumeration e() {
        return this.h.elements();
    }
    
    public void b(final d d) {
        this.h.removeElement(d);
    }
    
    public boolean f() {
        return this.h.isEmpty();
    }
}
