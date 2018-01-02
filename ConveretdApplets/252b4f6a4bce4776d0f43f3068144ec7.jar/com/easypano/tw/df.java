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

public class df implements ItemSelectable
{
    private Vector a;
    private j b;
    private int c;
    private Vector d;
    
    public df() {
        this.a = new Vector();
        this.b = null;
        this.c = -1;
        this.d = new Vector();
    }
    
    public void a(final j j) {
        j.a(this);
        j.a(new dg(this));
        j.addPropertyChangeListener(new dh(this));
        this.a.addElement(j);
    }
    
    public j a(final int n) {
        return this.a.elementAt(n);
    }
    
    public int a() {
        return this.a.size();
    }
    
    public int b() {
        final int q = h.q;
        df df = this;
        df df2 = this;
        if (q == 0) {
            if (this.b == null) {
                this.c = -1;
                if (q == 0) {
                    return this.c;
                }
            }
            df = this;
            df2 = this;
        }
        int n2 = 0;
        final int n;
        Label_0090: {
            Label_0072: {
                if (q == 0) {
                    if (df2.c < this.a.size()) {
                        n = (n2 = this.c);
                        if (q != 0) {
                            break Label_0090;
                        }
                        if (n >= 0) {
                            break Label_0072;
                        }
                    }
                    df = this;
                }
                df.c = this.a.indexOf(this.b);
                if (q == 0) {
                    return this.c;
                }
            }
            final boolean equals;
            n2 = ((equals = this.a.elementAt(this.c).equals(this.b)) ? 1 : 0);
        }
        if (q != 0) {
            return n2;
        }
        if (n == 0) {
            this.c = this.a.indexOf(this.b);
        }
        n2 = this.c;
        return n2;
    }
    
    public void b(final int c) {
        final int q = h.q;
        int n = c;
        if (q == 0) {
            if (c < 0) {
                return;
            }
            n = c;
        }
        if (n < this.a.size()) {
            final j b = this.b;
            if (q == 0) {
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
        final int q = h.q;
        int n = c;
        if (q == 0) {
            if (c < 0) {
                return;
            }
            n = c;
        }
        if (n < this.a.size()) {
            final j b = this.b;
            if (q == 0) {
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
        Vector<ItemListener> vector = null;
        synchronized (this) {
            vector = (Vector<ItemListener>)this.d.clone();
        }
        int n = 0;
        while (true) {
            Label_0054: {
                if (h.q == 0) {
                    break Label_0054;
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
    
    static j a(final df df) {
        return df.b;
    }
    
    static void a(final df df, final j b) {
        df.b = b;
    }
}
