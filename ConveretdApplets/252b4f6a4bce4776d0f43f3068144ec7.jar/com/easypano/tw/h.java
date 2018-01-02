// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.easypano.tw.d.j;
import java.awt.Color;
import java.awt.event.MouseListener;
import com.easypano.tw.d.p;
import com.easypano.tw.d.a;
import java.util.Vector;

public class h extends f
{
    public static final String l = "";
    ct m;
    protected f n;
    protected f o;
    protected Vector p;
    public static int q;
    
    public h() {
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = new Vector();
        this.a(new ct(this));
        this.a(new a(this));
        this.addMouseListener(new v(this));
    }
    
    public void a(final ct m) {
        this.m = m;
    }
    
    public ct e() {
        return this.m;
    }
    
    public void a(final f n) {
        super.a(n);
        this.n = n;
    }
    
    public void b(final String s, final Color background, final Color color) {
        final int q = h.q;
        String s2 = s;
        Label_0033: {
            Label_0023: {
                if (q == 0) {
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
            if (q == 0) {
                return;
            }
        }
        final h o = new h();
        o.setBackground(background);
        final j j = new j(o);
        j.d(color);
        o.a(j);
        o.e().a(s);
        this.o = o;
    }
    
    public void f() {
        final int q = h.q;
        h h = this;
        if (q == 0) {
            Label_0057: {
                if (((a)super.c).d() == 32) {
                    h h2 = this;
                    if (q == 0) {
                        if (this.m.d()) {
                            super.b = this.o;
                            if (q == 0) {
                                break Label_0057;
                            }
                        }
                        h2 = this;
                    }
                    h2.b = this.n;
                }
            }
            h = this;
        }
        h.repaint();
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
                if (h.q == 0) {
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
