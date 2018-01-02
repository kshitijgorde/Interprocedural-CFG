// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.ItemSelectable;

public class dg implements ItemSelectable
{
    private Vector a;
    private j b;
    private int c;
    private Vector d;
    
    public dg() {
        this.a = new Vector();
        this.b = null;
        this.c = -1;
        this.d = new Vector();
    }
    
    public void a(final j j) {
        j.a(this);
        j.a(new dh(this));
        j.addPropertyChangeListener(new di(this));
        this.a.addElement(j);
    }
    
    public j a(final int n) {
        return this.a.elementAt(n);
    }
    
    public int a() {
        return this.a.size();
    }
    
    public int b() {
        final boolean q = h.q;
        dg dg = this;
        dg dg2 = this;
        if (!q) {
            if (this.b == null) {
                this.c = -1;
                if (!q) {
                    return this.c;
                }
            }
            dg = this;
            dg2 = this;
        }
        int n2 = 0;
        final int n;
        Label_0090: {
            Label_0072: {
                if (!q) {
                    if (dg2.c < this.a.size()) {
                        n = (n2 = this.c);
                        if (q) {
                            break Label_0090;
                        }
                        if (n >= 0) {
                            break Label_0072;
                        }
                    }
                    dg = this;
                }
                dg.c = this.a.indexOf(this.b);
                if (!q) {
                    return this.c;
                }
            }
            final boolean equals;
            n2 = ((equals = this.a.elementAt(this.c).equals(this.b)) ? 1 : 0);
        }
        if (q) {
            return n2;
        }
        if (n == 0) {
            this.c = this.a.indexOf(this.b);
        }
        n2 = this.c;
        return n2;
    }
    
    public void b(final int c) {
        final boolean q = h.q;
        int n = c;
        if (!q) {
            if (c < 0) {
                return;
            }
            n = c;
        }
        if (n < this.a.size()) {
            final j b = this.b;
            if (!q) {
                if (b != null) {
                    this.b.e().a(false);
                }
                this.b = this.a(c);
                this.c = c;
                final j b2 = this.b;
            }
            b.e().a(true);
        }
    }
    
    public void c(final int c) {
        final boolean q = h.q;
        int n = c;
        if (!q) {
            if (c < 0) {
                return;
            }
            n = c;
        }
        if (n < this.a.size()) {
            final j b = this.b;
            if (!q) {
                if (b != null) {
                    this.b.e().b(false);
                }
                this.b = this.a(c);
                this.c = c;
                final j b2 = this.b;
            }
            b.e().b(true);
        }
    }
    
    public synchronized void addItemListener(final ItemListener itemListener) {
        this.d.addElement(itemListener);
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        this.d.removeElement(itemListener);
    }
    
    public Object[] getSelectedObjects() {
        return null;
    }
    
    protected void a(final ItemEvent itemEvent) {
        final Vector vector = (Vector)this.d.clone();
        int n = 0;
        while (true) {
            Label_0042: {
                if (!h.q) {
                    break Label_0042;
                }
                vector.elementAt(n).itemStateChanged(itemEvent);
                ++n;
            }
            if (n >= this.d.size()) {
                return;
            }
            continue;
        }
    }
    
    public void destroyResource() {
        this.a.removeAllElements();
        this.a = null;
        this.d.removeAllElements();
        this.d = null;
    }
    
    static j a(final dg dg) {
        return dg.b;
    }
    
    static void a(final dg dg, final j b) {
        dg.b = b;
    }
}
