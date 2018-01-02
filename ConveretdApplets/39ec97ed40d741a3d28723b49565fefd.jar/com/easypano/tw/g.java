// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.easypano.tw.a.j;
import java.awt.Color;
import java.awt.event.MouseListener;
import com.easypano.tw.a.p;
import com.easypano.tw.a.a;
import java.util.Vector;

public class g extends e
{
    public static final String l = "";
    ct m;
    protected e n;
    protected e o;
    protected Vector p;
    public static boolean q;
    
    public g() {
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = new Vector();
        this.a(new ct(this));
        this.a(new a(this));
        this.addMouseListener(new u(this));
    }
    
    public void a(final ct m) {
        this.m = m;
    }
    
    public ct e() {
        return this.m;
    }
    
    public void a(final e n) {
        super.a(n);
        this.n = n;
    }
    
    public void b(final String s, final Color background, final Color color) {
        final boolean q = g.q;
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
            this.o = null;
            if (!q) {
                return;
            }
        }
        final g o = new g();
        o.setBackground(background);
        final j j = new j(o);
        j.d(color);
        o.a(j);
        o.e().a(s);
        this.o = o;
    }
    
    public void f() {
        final boolean q = g.q;
        g g = this;
        if (!q) {
            Label_0057: {
                if (((a)super.c).d() == 32) {
                    g g2 = this;
                    if (!q) {
                        if (this.m.d()) {
                            super.b = this.o;
                            if (!q) {
                                break Label_0057;
                            }
                        }
                        g2 = this;
                    }
                    g2.b = this.n;
                }
            }
            g = this;
        }
        g.repaint();
    }
    
    public synchronized void a(final ActionListener actionListener) {
        this.p.addElement(actionListener);
    }
    
    public synchronized void b(final ActionListener actionListener) {
        this.p.removeElement(actionListener);
    }
    
    protected void a(final ActionEvent actionEvent) {
        final Vector vector = (Vector)this.p.clone();
        int n = 0;
        while (true) {
            Label_0042: {
                if (!g.q) {
                    break Label_0042;
                }
                vector.elementAt(n).actionPerformed(actionEvent);
                ++n;
            }
            if (n >= this.p.size()) {
                return;
            }
            continue;
        }
    }
    
    public void destroyResource() {
        super.destroyResource();
        if (this.p != null) {
            this.p.removeAllElements();
            this.p = null;
            this.m = null;
        }
    }
}
