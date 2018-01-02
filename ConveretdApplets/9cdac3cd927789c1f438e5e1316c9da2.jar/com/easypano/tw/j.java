// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import com.easypano.tw.c.c;
import com.easypano.tw.c.b;
import com.easypano.tw.c.p;
import com.easypano.tw.c.l;
import java.util.Vector;

public class j extends e
{
    private Vector l;
    protected bo m;
    protected k n;
    protected o o;
    protected g p;
    protected g q;
    
    public j() {
        this.l = new Vector();
        this.m = new bo(this);
        this.n = new k();
        this.o = null;
        this.p = new g();
        this.q = new g();
        this.a(new l(this));
        this.n.b(false);
        this.p.a(new b(this.p));
        this.p.b(false);
        this.q.a(new c(this.q, false));
        this.q.b(true);
        this.p.a(new cx(this));
        this.q.a(new cy(this));
        this.q.addKeyListener(new r(this));
        this.n.addItemListener(new cz(this));
        this.n.addMouseListener(new w(this));
        this.m.setVisible(false);
        this.setLayout(new BorderLayout());
        this.add(this.q, b("\ty\u0015NF8"));
        this.add(this.p, b("\u000f}\bN"));
        this.a(new o());
    }
    
    public void d(final boolean b) {
        final boolean q = com.easypano.tw.g.q;
        if (b) {
            final Container parent = this.getParent();
            if (q || parent != null) {
                final Rectangle bounds = parent.getBounds();
                final int size = this.n.e().f().size();
                if (!q) {
                    if (size < 5) {
                        this.n.e().f().size();
                    }
                }
                final int n = size;
                final Point location = this.getLocation();
                final int n3;
                int n2 = n3 = this.n.f() * n;
                if (q || n3 < 3) {
                    n2 = n3;
                }
                final Dimension dimension = new Dimension(this.getBounds().width, n2);
                final Point point = location;
                point.y += this.getBounds().height + 1;
                j j = null;
                Label_0234: {
                    if (!q) {
                        if (location.y + n2 > bounds.height) {
                            j = this;
                            if (q) {
                                break Label_0234;
                            }
                            if (this.getLocation().y > bounds.height - location.y) {
                                location.y = location.y - this.getBounds().height - n2 - 2;
                            }
                        }
                        this.m.setBounds(location.x, location.y, dimension.width, dimension.height);
                    }
                    j = this;
                }
                j.m.setVisible(true);
            }
            j i = this;
            Label_0314: {
                if (!q) {
                    if (this.n.getPreferredSize().width <= 0) {
                        final Graphics graphics = this.m.getGraphics();
                        if (q) {
                            break Label_0314;
                        }
                        if (graphics != null) {
                            try {
                                this.n.paint(graphics);
                            }
                            finally {
                                graphics.dispose();
                            }
                            graphics.dispose();
                        }
                    }
                    this.m.validate();
                    i = this;
                }
                i.o.h();
            }
            if (!q) {
                return;
            }
        }
        this.m.setVisible(false);
    }
    
    public void b(final int n) {
        this.n.e().e(n);
        this.q.e().a(this.n.e().f().elementAt(this.n.e().c()));
    }
    
    public synchronized void a(final ActionListener actionListener) {
        this.l.addElement(actionListener);
    }
    
    public synchronized void b(final ActionListener actionListener) {
        this.l.removeElement(actionListener);
    }
    
    protected void a(final ActionEvent actionEvent) {
        Vector<ActionListener> vector = null;
        synchronized (this) {
            vector = (Vector<ActionListener>)this.l.clone();
        }
        int n = 0;
        while (true) {
            Label_0054: {
                if (!com.easypano.tw.g.q) {
                    break Label_0054;
                }
                vector.elementAt(n).actionPerformed(actionEvent);
                ++n;
            }
            if (n >= this.l.size()) {
                return;
            }
            continue;
        }
    }
    
    public void setBounds(final Rectangle bounds) {
        Rectangle rectangle = bounds;
        Label_0021: {
            if (!com.easypano.tw.g.q) {
                if (bounds.height <= 20) {
                    break Label_0021;
                }
                rectangle = bounds;
            }
            rectangle.height = 20;
        }
        super.setBounds(bounds);
    }
    
    public g e() {
        return this.p;
    }
    
    public g f() {
        return this.q;
    }
    
    public k g() {
        return this.n;
    }
    
    public bo h() {
        return this.m;
    }
    
    public o i() {
        return this.o;
    }
    
    public void a(final o o) {
        final o o2 = this.o;
        if (!com.easypano.tw.g.q) {
            if (o2 != null) {
                this.m.remove(this.o);
            }
            (this.o = o).a((Component)this.n);
            final o o3 = this.o;
        }
        o2.b(false);
        this.m.setLayout(new BorderLayout());
        this.m.add(this.o, b("\ty\u0015NF8"));
        this.m.validate();
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0091: {
                if (length > 1) {
                    break Label_0091;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'J';
                            break;
                        }
                        case 1: {
                            c2 = '\u001c';
                            break;
                        }
                        case 2: {
                            c2 = '{';
                            break;
                        }
                        case 3: {
                            c2 = ':';
                            break;
                        }
                        default: {
                            c2 = '#';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
