// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.AWTEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

public class p extends f
{
    private Container l;
    private f m;
    private Component n;
    private o o;
    private o p;
    private f q;
    private f r;
    
    public p() {
        this.l = null;
        this.m = new f();
        this.n = null;
        this.o = new o();
        this.p = new o();
        this.q = new f();
        this.r = new f();
        this.setLayout(new BorderLayout());
        this.a(new com.easypano.tw.d.o(this));
        this.r.a(false);
        this.m.setLayout(null);
        this.m.a(false);
        this.m.addComponentListener(new r(this));
        this.l = this.m;
        this.q.setLayout(new BorderLayout());
        this.add(this.l, b("}E)\u0004\u001fL"));
        this.add(this.q, b("mO2\u0004\u0012"));
        this.o.b(42);
        this.p.b(41);
        this.a(this.o, this.p);
        this.e(false);
        this.d(false);
    }
    
    public void a(final o o, final o p2) {
        this.q.remove(this.o);
        this.q.remove(this.r);
        this.remove(this.p);
        o.a(new dq(this));
        o.c(100);
        o.d(0);
        p2.a(new dr(this));
        p2.c(100);
        p2.d(0);
        this.r.setPreferredSize(p2.getPreferredSize());
        this.o = o;
        this.p = p2;
        this.q.add(this.o, b("}E)\u0004\u001fL"));
        this.q.add(this.r, b("{A4\u0004"));
        this.add(this.p, b("{A4\u0004"));
        this.dispatchEvent(new ComponentEvent(this.l, 101));
    }
    
    private void d(final boolean visible) {
        final f q = this.q;
        if (com.easypano.tw.h.q == 0) {
            if (q.isVisible() == visible) {
                return;
            }
            final f q2 = this.q;
        }
        q.setVisible(visible);
        super.validate();
        this.doLayout();
    }
    
    public boolean e() {
        return this.q.isVisible();
    }
    
    private void e(final boolean b) {
        f f;
        final o o = (o)(f = this.p);
        if (com.easypano.tw.h.q == 0) {
            if (o.isVisible() == b) {
                return;
            }
            this.p.setVisible(b);
            f = this.r;
        }
        f.setVisible(b);
        super.validate();
        this.doLayout();
    }
    
    public boolean f() {
        return this.p.isVisible();
    }
    
    protected void g() {
        final int q = com.easypano.tw.h.q;
        final int width = this.getBounds().width;
        final int n = width - com.easypano.tw.o.l.width;
        final int height = this.getBounds().height;
        final int n2 = height - com.easypano.tw.o.l.height;
        final Dimension preferredSize = this.n.getPreferredSize();
        final int width2 = preferredSize.width;
        final int n3 = width;
        Label_0114: {
            Label_0109: {
                Label_0104: {
                    if (q == 0) {
                        if (width2 <= n3) {
                            break Label_0109;
                        }
                        this.d(true);
                        if (q != 0) {
                            break Label_0104;
                        }
                        final int height2 = preferredSize.height;
                    }
                    if (width2 > n3) {
                        this.e(true);
                        if (q == 0) {
                            break Label_0114;
                        }
                    }
                    this.e(false);
                }
                if (q == 0) {
                    break Label_0114;
                }
            }
            this.d(false);
        }
        final int height3 = preferredSize.height;
        if (q == 0 && height3 > height) {
            this.e(true);
            if (q == 0) {
                if (preferredSize.width > n) {
                    this.d(true);
                    if (q == 0) {
                        return;
                    }
                }
                this.d(false);
            }
            if (q != 0) {
                goto Label_0167;
            }
        }
        else if (height3 == 0) {
            this.e(false);
        }
    }
    
    public void h() {
        this.f(true);
    }
    
    protected void f(final boolean b) {
        final int q = com.easypano.tw.h.q;
        p p = null;
        Label_0293: {
            try {
                if (b) {
                    this.g();
                }
                p = this;
                if (q != 0) {
                    break Label_0293;
                }
                if (this.n != null) {
                    final Dimension preferredSize = this.n.getPreferredSize();
                    Component component;
                    final Container container = (Container)(component = this.l);
                    Label_0156: {
                        if (q == 0) {
                            if (container.getBounds().width >= preferredSize.width) {
                                this.n.setBounds(0, this.n.getBounds().y, this.l.getBounds().width, this.n.getBounds().height);
                                if (q == 0) {
                                    break Label_0156;
                                }
                            }
                            component = this.n;
                        }
                        component.setSize(preferredSize.width, this.n.getBounds().height);
                        this.o.a((double)(-this.n.getBounds().x / (this.n.getBounds().width - this.l.getBounds().width)));
                    }
                    Component component2;
                    final Container container2 = (Container)(component2 = this.l);
                    Label_0281: {
                        if (q == 0) {
                            if (container2.getBounds().height >= preferredSize.height) {
                                this.n.setBounds(this.n.getBounds().x, 0, this.n.getBounds().width, this.l.getBounds().height);
                                if (q == 0) {
                                    break Label_0281;
                                }
                            }
                            component2 = this.n;
                        }
                        component2.setSize(this.n.getBounds().width, preferredSize.height);
                        this.o.a((double)(-this.n.getBounds().y / (this.n.getBounds().height - this.l.getBounds().height)));
                    }
                    this.n.doLayout();
                }
            }
            catch (Exception ex) {}
            p = this;
        }
        p.validate();
    }
    
    public void validate() {
        this.h();
    }
    
    public void a(final Component n, final boolean b) {
        final Component n2 = this.n;
        if (com.easypano.tw.h.q == 0) {
            if (n2 != null) {
                this.l.remove(this.n);
            }
            this.n = n;
            this.l.add(n);
            this.l.setBackground(n.getBackground());
            this.q.setBackground(n.getBackground());
            n.setLocation(0, 0);
        }
        n2.setSize(n.getPreferredSize());
        this.h();
    }
    
    public void a(final Component component) {
        this.a(component, false);
    }
    
    public void a(final Rectangle rectangle) {
        final int q = com.easypano.tw.h.q;
        Container l = this;
        if (q == 0) {
            if (this.n == null) {
                return;
            }
            rectangle.x -= this.l.getBounds().x;
            rectangle.y -= this.l.getBounds().y;
            l = this.l;
        }
        int n3;
        int n2;
        final int n = n2 = (n3 = l.getBounds().width);
        int n7 = 0;
        int n6 = 0;
        int n5 = 0;
        Label_0416: {
            final int width;
            Label_0367: {
                Label_0343: {
                    if (q == 0) {
                        if (n >= this.n.getBounds().width) {
                            this.n.setBounds(0, this.n.getBounds().y, this.l.getBounds().width, this.n.getBounds().height);
                            if (q == 0) {
                                break Label_0343;
                            }
                        }
                        final int n4;
                        n2 = (n4 = (n3 = rectangle.x));
                    }
                    if (q == 0) {
                        if (n < 0) {
                            p p = this;
                            if (q == 0) {
                                if (this.n.getBounds().x < rectangle.x) {
                                    this.o.a((double)(-rectangle.x / (this.n.getBounds().width - this.l.getBounds().width)));
                                    if (q == 0) {
                                        break Label_0343;
                                    }
                                }
                                p = this;
                            }
                            p.o.a(0.0);
                            if (q == 0) {
                                break Label_0343;
                            }
                        }
                        n3 = (n2 = rectangle.x + rectangle.width);
                    }
                    width = this.l.getBounds().width;
                    if (q != 0) {
                        break Label_0367;
                    }
                    if (n2 > width) {
                        p p2 = this;
                        if (q == 0) {
                            if (this.n.getBounds().x + this.n.getBounds().width < rectangle.x + rectangle.width) {
                                this.o.a(100.0);
                                if (q == 0) {
                                    break Label_0343;
                                }
                            }
                            p2 = this;
                        }
                        p2.o.a((double)((rectangle.x + rectangle.width - this.l.getBounds().width - this.n.getBounds().x) / (this.n.getBounds().width - this.l.getBounds().width)));
                    }
                }
                n5 = (n3 = (n6 = (n7 = this.l.getBounds().height)));
                if (q != 0) {
                    break Label_0416;
                }
                final int height = this.n.getBounds().height;
            }
            if (n3 >= width) {
                this.n.setBounds(this.n.getBounds().x, 0, this.n.getBounds().width, this.l.getBounds().height);
                if (q == 0) {
                    return;
                }
            }
            n6 = (n5 = (n7 = rectangle.y));
        }
        if (q == 0) {
            if (n5 < 0) {
                p p3 = this;
                if (q == 0) {
                    if (this.n.getBounds().y < rectangle.y) {
                        this.p.a((double)(-rectangle.y / (this.n.getBounds().height - this.l.getBounds().height)));
                        if (q == 0) {
                            return;
                        }
                    }
                    p3 = this;
                }
                p3.p.a(0.0);
                if (q == 0) {
                    return;
                }
            }
            n7 = (n6 = rectangle.y + rectangle.height);
        }
        final int height2 = this.l.getBounds().height;
        p p4 = null;
        Label_0573: {
            if (q == 0) {
                if (n6 <= height2) {
                    return;
                }
                p4 = this;
                if (q != 0) {
                    break Label_0573;
                }
                n7 = this.n.getBounds().y + this.n.getBounds().height;
                final int n8 = rectangle.y + rectangle.height;
            }
            if (n7 < height2) {
                this.p.a(100.0);
                if (q == 0) {
                    return;
                }
            }
            p4 = this;
        }
        p4.p.a((double)((rectangle.y + rectangle.height - this.l.getBounds().height - this.n.getBounds().y) / (this.n.getBounds().height - this.l.getBounds().height)));
    }
    
    public o i() {
        return this.o;
    }
    
    public o j() {
        return this.p;
    }
    
    public void b(final boolean b) {
        super.b(b);
        this.p.b(b);
        this.o.b(b);
    }
    
    public void a(final dt dt) {
        final int q = com.easypano.tw.h.q;
        super.a(dt);
        this.m.a(dt);
        f r = this;
        Label_0085: {
            if (q == 0) {
                if (this.n != null) {
                    r = this;
                    if (q != 0) {
                        break Label_0085;
                    }
                    if (this.n instanceof da) {
                        ((da)this.n).a(dt);
                    }
                }
                this.o.a(dt);
                this.p.a(dt);
                this.q.a(dt);
                r = this.r;
            }
        }
        r.a(dt);
    }
    
    public void destroyResource() {
        super.destroyResource();
        this.o.destroyResource();
        this.o = null;
        this.p.destroyResource();
        this.p = null;
        this.q.destroyResource();
        this.q = null;
        this.r.destroyResource();
        this.r = null;
    }
    
    static Component a(final p p) {
        return p.n;
    }
    
    static Container b(final p p) {
        return p.l;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
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
                            c2 = '>';
                            break;
                        }
                        case 1: {
                            c2 = ' ';
                            break;
                        }
                        case 2: {
                            c2 = 'G';
                            break;
                        }
                        case 3: {
                            c2 = 'p';
                            break;
                        }
                        default: {
                            c2 = 'z';
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
