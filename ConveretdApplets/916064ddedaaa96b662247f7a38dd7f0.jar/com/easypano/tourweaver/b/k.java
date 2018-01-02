// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.util.Hashtable;
import java.awt.event.MouseListener;

public class k extends i implements MouseListener
{
    private i K;
    private k L;
    private Hashtable M;
    private int N;
    int O;
    int P;
    Panel Q;
    
    public k(final String s) {
        this.M = new Hashtable();
        this.N = 0;
        this.O = 2;
        this.P = 2;
        this.Q = null;
        this.setLayout(null);
        this.O = 2;
        this.P = 2;
        this.K = new i(s, true);
    }
    
    public i p() {
        return this.K;
    }
    
    private void q() {
        final boolean u = f.u;
        int n = this.K.getParent().getParent().getLocation().x + this.K.getLocation().x + this.K.getPreferredSize().width;
        int n2 = this.K.getParent().getParent().getLocation().y + this.K.getLocation().y;
        final Container parent = this.K.getParent().getParent().getParent();
        final Dimension size = parent.getSize();
        int n4;
        final int n3 = n4 = n + super.I.width;
        int n6;
        final int n5 = n6 = size.width;
        if (!u) {
            if (n3 > n5) {
                n -= super.I.width + this.K.getPreferredSize().width;
            }
            final int n7;
            n4 = (n7 = n2 + super.I.height);
            final int height;
            n6 = (height = size.height);
        }
        Label_0178: {
            if (!u) {
                if (n3 <= n5) {
                    break Label_0178;
                }
                n4 = n2 - super.I.height;
                n6 = this.K.getPreferredSize().height;
            }
            n2 = n4 + n6;
        }
        final Panel q = this.Q;
        if (!u) {
            if (q == null) {
                (this.Q = new db()).setLayout(null);
                this.Q.add(this, 0);
            }
            parent.add(this.Q, 0);
            this.setBounds(0, 0, super.I.width, super.I.height);
            final Panel q2 = this.Q;
        }
        q.setBounds(n, n2, super.I.width, super.I.height);
        k k = this;
        if (!u) {
            if (this.getComponentCount() == 0) {
                return;
            }
            k = this;
        }
        k.Q.setVisible(true);
    }
    
    public void e(final String s) {
        this.remove(this.f(s));
    }
    
    public i f(String lowerCase) {
        final boolean u = f.u;
        lowerCase = lowerCase.toLowerCase();
        int i = 0;
        while (i < this.getComponentCount()) {
            final i j = (i)this.getComponent(i);
            if (!u) {
                if (j.n() != null) {
                    final i k = j;
                    if (u || k.n().toLowerCase().equals(lowerCase)) {
                        return k;
                    }
                }
                ++i;
            }
            if (u) {
                break;
            }
        }
        return null;
    }
    
    public void a(final i i, final int n) {
        final boolean u = f.u;
        i p2 = i;
        int n3;
        final int n2 = n3 = ((i instanceof k) ? 1 : 0);
        if (!u) {
            if (n2 != 0) {
                p2 = ((k)i).p();
                this.M.put(p2, i);
            }
            final boolean b;
            n3 = ((b = (i instanceof j)) ? 1 : 0);
        }
        k k = null;
        Label_0096: {
            if (!u) {
                if (n2 == 0) {
                    p2.addMouseListener(this);
                }
                this.add(p2, n);
                k = this;
                if (u) {
                    break Label_0096;
                }
                n3 = this.N;
            }
            if (n3 < p2.getPreferredSize().width) {
                this.N = p2.getPreferredSize().width;
            }
            this.r();
            k = this;
        }
        k.doLayout();
    }
    
    public void b(final int n) {
        k k = this;
        if (!f.u) {
            if (this.N >= n) {
                return;
            }
            this.N = n;
            this.r();
            k = this;
        }
        k.doLayout();
    }
    
    public void g(final String s) {
        final boolean u = f.u;
        if (s == null) {
            return;
        }
        final int componentCount = this.getComponentCount();
        int i = 0;
        while (i < componentCount) {
            final i j = (i)this.getComponent(i);
            if (!u) {
                Label_0088: {
                    if (j.m()) {
                        final String h = j.h();
                        if ((u || h != null) && h.equals(s)) {
                            j.b(true);
                            if (!u) {
                                break Label_0088;
                            }
                        }
                        j.b(false);
                    }
                }
                ++i;
            }
            if (u) {
                break;
            }
        }
    }
    
    public void a(final i i) {
        this.a(i, this.getComponentCount());
    }
    
    public void b(final i i) {
        this.remove(i);
        this.r();
        this.doLayout();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        i l;
        final k k = (k)(l = this.L);
        if (!u) {
            if (k != null) {
                this.L.hide();
            }
            l = (i)mouseEvent.getSource();
        }
        final i i = l;
        if (!u) {
            if (!i.o()) {
                return;
            }
            this.L = (k)this.M.get(i);
        }
        this.L.q();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        try {
            ((lb)this.getParent().getParent()).b().hide();
        }
        catch (ClassCastException ex) {
            try {
                ((jb)this.getParent().getParent()).a().hide();
            }
            catch (ClassCastException ex2) {
                ((cb)this.getParent().getParent()).a().hide();
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    private void r() {
        final boolean u = f.u;
        int n = 0;
        final int componentCount = this.getComponentCount();
        int i = 0;
        while (i < componentCount) {
            final Dimension preferredSize = ((i)this.getComponent(i)).getPreferredSize();
            preferredSize.width = this.N;
            n += preferredSize.height;
            ++i;
            if (u) {
                return;
            }
            if (u) {
                break;
            }
        }
        super.I.setSize(this.N + 2 * this.O, n + 2 * this.P);
    }
    
    public void doLayout() {
        final boolean u = f.u;
        int p = this.P;
        final int componentCount = this.getComponentCount();
        int i = 0;
        while (i < componentCount) {
            final i j = (i)this.getComponent(i);
            j.setBounds(this.O, p, super.I.width - this.O, j.getPreferredSize().height);
            p += j.getPreferredSize().height;
            ++i;
            if (u) {
                return;
            }
            if (u) {
                break;
            }
        }
        super.doLayout();
    }
    
    public void a(final MouseEvent mouseEvent) {
    }
    
    public void b(final Graphics graphics) {
        graphics.setColor(i.C);
        graphics.drawLine(0, 0, super.I.width, 0);
        graphics.drawLine(0, 0, 0, super.I.height);
        graphics.setColor(i.E);
        graphics.drawLine(0, 1, super.I.width, 1);
        graphics.drawLine(1, 0, 1, super.I.height);
        graphics.setColor(i.F);
        graphics.drawLine(0, super.I.height - 2, super.I.width, super.I.height - 2);
        graphics.drawLine(super.I.width - 2, super.I.height, super.I.width - 2, 0);
        graphics.setColor(i.D);
        graphics.drawLine(0, super.I.height - 1, super.I.width, super.I.height - 1);
        graphics.drawLine(super.I.width - 1, super.I.height, super.I.width - 1, 0);
    }
    
    public void c(final Graphics graphics) {
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        final k l = this.L;
        if (!f.u && l != null) {
            this.L.hide();
            goto Label_0020;
        }
        l.setBounds(n, n2, n3, n4);
    }
    
    public void hide() {
        final boolean u = f.u;
        k l = this;
        k i = this;
        if (!u) {
            if (this.Q != null) {
                this.Q.setVisible(false);
            }
            l = (i = this.L);
        }
        if (!u) {
            if (i == null) {
                return;
            }
            l = this.L;
        }
        l.hide();
    }
    
    public void a(final Container container, int n, int n2) {
        final boolean u = f.u;
        final Dimension size = container.getSize();
        int n4;
        final int n3 = n4 = n + super.I.width;
        int n6;
        final int n5 = n6 = size.width;
        if (!u) {
            if (n3 >= n5) {
                n -= super.I.width;
            }
            final int n7;
            n4 = (n7 = n2 + super.I.height);
            final int height;
            n6 = (height = size.height);
        }
        Label_0075: {
            if (!u) {
                if (n3 < n5) {
                    break Label_0075;
                }
                n4 = n2;
                n6 = super.I.height;
            }
            n2 = n4 - n6;
        }
        final Panel q = this.Q;
        if (!u) {
            if (q == null) {
                (this.Q = new db()).setLayout(null);
                this.Q.add(this);
            }
            this.setBounds(0, 0, super.I.width, super.I.height);
            container.add(this.Q, 0);
            this.Q.setBounds(n, n2, super.I.width, super.I.height);
            final Panel q2 = this.Q;
        }
        q.setVisible(true);
    }
}
