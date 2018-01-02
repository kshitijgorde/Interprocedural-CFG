// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import java.awt.Image;
import java.util.Vector;
import com.easypano.tourweaver.b.j;
import java.awt.event.ActionEvent;
import com.easypano.tourweaver.b.a;
import com.easypano.tourweaver.b.f;
import com.easypano.tourweaver.f.n;
import com.easypano.tourweaver.b.lb;
import com.easypano.tourweaver.f.x;
import com.easypano.tourweaver.b.t;
import com.easypano.tourweaver.d.b;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import com.easypano.tourweaver.b.jb;
import com.easypano.tourweaver.b.cb;
import com.easypano.tourweaver.a.e;
import java.awt.Frame;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.LayoutManager;
import com.easypano.tourweaver.b.k;
import com.easypano.tourweaver.b.ab;
import java.awt.Container;
import java.awt.Window;
import java.awt.Rectangle;
import java.awt.Panel;
import com.easypano.tourweaver.b.o;
import com.easypano.tourweaver.b.hb;
import com.easypano.tourweaver.b.mb;
import com.easypano.tourweaver.b.gb;
import com.easypano.tourweaver.b.q;
import com.easypano.tourweaver.b.l;
import com.easypano.tourweaver.b.ib;
import java.awt.event.ActionListener;

public class h implements i, ActionListener, PlayerListener
{
    ib a;
    com.easypano.tourweaver.b.h b;
    l c;
    q d;
    gb e;
    mb f;
    hb g;
    boolean h;
    m i;
    o j;
    PlayerListener k;
    long l;
    Panel m;
    Rectangle n;
    Window o;
    Container p;
    ab q;
    boolean r;
    boolean s;
    g t;
    k u;
    com.easypano.tourweaver.b.i[] v;
    com.easypano.tourweaver.b.i w;
    com.easypano.tourweaver.b.i x;
    com.easypano.tourweaver.b.i y;
    k z;
    k A;
    k B;
    String C;
    Container D;
    Container E;
    private static String[] F;
    
    public h() {
        this.f = new mb();
        this.g = new hb();
        this.h = false;
        this.l = 0L;
        this.o = null;
        this.p = null;
        this.r = true;
        this.s = false;
        this.C = null;
        this.D = null;
        this.E = null;
        (this.m = new Panel()).setLayout(null);
    }
    
    public void repaint() {
    }
    
    public void setPlayerListener(final PlayerListener k) {
        this.k = k;
    }
    
    public void addListbox(final o j) {
        final boolean v = com.easypano.tourweaver.g.v;
        final o i = this.j;
        Label_0105: {
            if (!v) {
                if (i != null) {
                    final o k = this.j;
                    if (v) {
                        break Label_0105;
                    }
                    if (k.isVisible()) {
                        this.m.remove(this.j);
                    }
                }
                this.j = j;
                this.m.setBounds(j.getBounds());
                this.m.add(j);
                this.j.setBounds(0, 0, j.getBounds().width, j.getBounds().height);
                this.a.add(this.m, 0);
                final o l = this.j;
            }
        }
        i.setVisible(true);
        this.m.setVisible(true);
    }
    
    public void destroy() {
        this.f.c();
        this.a.destroy();
        this.b.destroy();
        this.e.a();
        this.a = null;
        this.f = null;
    }
    
    public void removeListbox() {
        final o j = this.j;
        if (!com.easypano.tourweaver.g.v) {
            if (j == null) {
                return;
            }
            this.m.remove(this.j);
            this.m.setVisible(false);
            final o i = this.j;
        }
        j.setVisible(false);
        this.a.remove(this.m);
        this.j = null;
    }
    
    public void setInitFullScreen(final boolean h) {
        this.h = h;
    }
    
    public void setPlayController(final m i) {
        this.i = i;
    }
    
    public hb getLoadingWindow() {
        return this.g;
    }
    
    public ib getMainWindow() {
        return this.a;
    }
    
    public boolean hasMapViewer() {
        return this.d != null;
    }
    
    public boolean hasSceneViewer() {
        return this.c != null;
    }
    
    private void a(final Enumeration enumeration) {
        final boolean v = com.easypano.tourweaver.g.v;
        while (enumeration.hasMoreElements()) {
            final ib nextElement = enumeration.nextElement();
            final boolean b = nextElement instanceof ib;
            Label_0058: {
                if (!v) {
                    if (b) {
                        this.a = nextElement;
                        if (!v) {
                            break Label_0058;
                        }
                    }
                    final boolean b2 = nextElement instanceof com.easypano.tourweaver.b.h;
                }
                if (b) {
                    this.b = (com.easypano.tourweaver.b.h)nextElement;
                }
            }
            if (v) {
                break;
            }
        }
    }
    
    public void showDetailImage(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        h h = this;
        h h2 = this;
        if (!v) {
            if (this.e == null) {
                return;
            }
            h = this;
            h2 = this;
        }
        h h3 = null;
        Label_0107: {
            Label_0105: {
                Label_0093: {
                    h h4 = null;
                    Label_0066: {
                        Label_0065: {
                            if (!v) {
                                if (h2.d != null) {
                                    h3 = this;
                                    h4 = this;
                                    if (v) {
                                        break Label_0066;
                                    }
                                    if (this.d.isFullScreen()) {
                                        break Label_0065;
                                    }
                                }
                                h = this;
                            }
                            final l c = h.c;
                            if (v) {
                                break Label_0105;
                            }
                            if (c == null) {
                                break Label_0093;
                            }
                            final l c2 = this.c;
                            if (v) {
                                break Label_0105;
                            }
                            if (!c2.h()) {
                                break Label_0093;
                            }
                        }
                        h3 = this;
                        h4 = this;
                    }
                    if (v) {
                        break Label_0107;
                    }
                    if (h4.o == null) {
                        break Label_0105;
                    }
                    this.o.add(this.e, 0);
                    if (!v) {
                        break Label_0105;
                    }
                }
                this.a.add(this.e, 0);
            }
            h3 = this;
        }
        h3.e.b(s);
    }
    
    public q getMapViewer() {
        return this.d;
    }
    
    private Window a() {
        final boolean v = com.easypano.tourweaver.g.v;
        Container container = this.c;
        while (true) {
        Label_0075_Outer:
            while (true) {
                Frame frame2;
                while (true) {
                    while (container.getParent() != null) {
                        container = container.getParent();
                        if (v) {
                            container = new Frame();
                            container.setBounds(0, 0, 0, 0);
                            container.setVisible(true);
                            final Container container2 = container;
                            final Frame frame = (Frame)container2;
                            Window window;
                            if (com.easypano.tourweaver.a.e.i >= 4) {
                                window = new cb(frame);
                                ((cb)window).a(this.u);
                            }
                            else {
                                window = new jb(frame);
                                ((jb)window).a(this.u);
                            }
                            window.setLayout(null);
                            return window;
                        }
                        if (v) {
                            break;
                        }
                    }
                    Container container2;
                    frame2 = (Frame)(container2 = container);
                    if (!v) {
                        if (frame2 == null) {
                            continue Label_0075_Outer;
                        }
                        final Frame frame3;
                        container2 = (frame3 = (Frame)container);
                    }
                    if (v) {
                        continue;
                    }
                    break;
                }
                if (!(frame2 instanceof Frame)) {
                    continue Label_0075_Outer;
                }
                break;
            }
            continue;
        }
    }
    
    private void a(final Component component) {
        h h = this;
        if (!com.easypano.tourweaver.g.v) {
            if (this.o == null) {
                this.o = this.a();
            }
            h = this;
        }
        h.p = component.getParent();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.n = component.getBounds();
        this.o.setSize(screenSize.width, screenSize.height);
        this.o.add(component);
        this.o.setLocation(0, 0);
        this.o.setBounds(0, 0, screenSize.width, screenSize.height + 30);
        component.setBounds(0, 0, screenSize.width, screenSize.height);
        this.o.setVisible(true);
        this.o.toFront();
        this.o.requestFocus();
    }
    
    public void showToolBar() {
        final boolean r = this.r;
        if (!com.easypano.tourweaver.g.v && r) {}
        this.r = r;
        this.b();
    }
    
    public void setInitShowToolbar(final boolean r) {
        this.r = r;
    }
    
    public ab getMovieController() {
        return this.q;
    }
    
    private void b() {
        final boolean v = com.easypano.tourweaver.g.v;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        boolean r;
        final boolean b = r = (this.c.getParent() instanceof ib);
        if (!v) {
            if (b) {
                return;
            }
            final boolean r2;
            r = (r2 = this.r);
        }
        h h = null;
        Label_0162: {
            Label_0161: {
                if (!v) {
                    h h2 = null;
                    Label_0115: {
                        if (b) {
                            h = this;
                            h2 = this;
                            if (v) {
                                break Label_0115;
                            }
                            if (this.b != null) {
                                this.c.setBounds(0, 0, screenSize.width, screenSize.height);
                                this.b.setBounds(0, screenSize.height - 65, screenSize.width, 65);
                                this.c.add(this.b, 0);
                                this.b.setVisible(true);
                                if (!v) {
                                    break Label_0161;
                                }
                            }
                        }
                        h = this;
                        h2 = this;
                    }
                    if (v) {
                        break Label_0162;
                    }
                    r = h2.r;
                }
                if (!r) {
                    h = this;
                    if (v) {
                        break Label_0162;
                    }
                    if (this.b != null) {
                        this.c.setBounds(0, 0, screenSize.width, screenSize.height);
                        this.b.setVisible(false);
                    }
                }
            }
            h = this;
        }
        h.u.f(com.easypano.tourweaver.h.F[20]).b(this.r);
        this.o.requestFocus();
        this.o.toFront();
        this.c.requestFocus();
    }
    
    public void fullScreen(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        boolean b3;
        boolean equals;
        boolean b2;
        final boolean b = b2 = (equals = (b3 = this.s));
        if (!v) {
            if (!b) {
                return;
            }
            final boolean b4;
            b2 = (b4 = (equals = (b3 = s.toLowerCase().equals(com.easypano.tourweaver.h.F[3]))));
        }
        final q d;
        Label_0283: {
            Label_0268: {
                Label_0257: {
                    Label_0074: {
                        if (!v) {
                            if (b) {
                                break Label_0074;
                            }
                            equals = (b2 = (b3 = s.toLowerCase().equals(com.easypano.tourweaver.h.F[25])));
                        }
                        if (!v) {
                            if (b2) {
                                break Label_0074;
                            }
                            b3 = (equals = s.toLowerCase().equals(com.easypano.tourweaver.h.F[4]));
                        }
                        if (v) {
                            break Label_0268;
                        }
                        if (!equals) {
                            break Label_0257;
                        }
                    }
                    final l c = this.c;
                    h h = null;
                    h h2 = null;
                    Label_0115: {
                        if (!v) {
                            if (c != null) {
                                this.c.p();
                            }
                            h = this;
                            h2 = this;
                            if (v) {
                                break Label_0115;
                            }
                            final l c2 = this.c;
                        }
                        if (c.h()) {
                            this.c.requestFocus();
                            return;
                        }
                        h = this;
                        h2 = this;
                    }
                    Label_0199: {
                        if (!v) {
                            if (h2.d != null) {
                                h = this;
                                if (v) {
                                    break Label_0199;
                                }
                                if (this.d.isFullScreen()) {
                                    this.b(this.d);
                                    this.d.setFullScreen(false);
                                }
                            }
                            this.a(this.c);
                            this.u.a(this.x, 4);
                            this.u.a(this.y, 5);
                            this.b();
                            this.o.repaint();
                            h = this;
                        }
                    }
                    final com.easypano.tourweaver.b.i f = h.u.f(com.easypano.tourweaver.h.F[2]);
                    if (!v) {
                        if (f != null) {
                            f.d(com.easypano.tourweaver.h.F[24]);
                            f.c(com.easypano.tourweaver.h.F[0]);
                        }
                        this.c.a(true);
                        this.c.requestFocus();
                    }
                    if (!v) {
                        return;
                    }
                }
                d = this.d;
                if (v) {
                    break Label_0283;
                }
                b3 = d.isFullScreen();
            }
            if (b3) {
                return;
            }
            this.a(this.d);
            final q d2 = this.d;
        }
        d.setFullScreen(true);
    }
    
    public void exitFullScreen(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        boolean b2;
        final boolean b = b2 = this.s;
        if (!v) {
            if (!b) {
                return;
            }
            final boolean equals;
            b2 = (equals = s.toLowerCase().equals(com.easypano.tourweaver.h.F[3]));
        }
        Label_0166: {
            Label_0050: {
                if (!v) {
                    if (b) {
                        break Label_0050;
                    }
                    b2 = s.toLowerCase().equals(com.easypano.tourweaver.h.F[4]);
                }
                if (!b2) {
                    break Label_0166;
                }
            }
            h h = this;
            if (!v) {
                if (this.c != null) {
                    this.c.o();
                }
                this.b(this.c);
                h = this;
            }
            final com.easypano.tourweaver.b.i f = h.u.f(com.easypano.tourweaver.h.F[0]);
            if (!v) {
                if (f != null) {
                    f.d(com.easypano.tourweaver.h.F[1]);
                    f.c(com.easypano.tourweaver.h.F[2]);
                }
                this.u.b(this.x);
                this.u.b(this.y);
                this.c.setCursor(Cursor.getDefaultCursor());
                this.c.a(false);
                this.c.requestFocus();
            }
            if (!v) {
                return;
            }
        }
        this.b(this.d);
        this.d.setFullScreen(false);
    }
    
    private void b(final Component component) {
        final boolean v = com.easypano.tourweaver.g.v;
        final boolean b = component instanceof l;
        Component component2 = null;
        Label_0051: {
            Label_0050: {
                if (!v) {
                    if (b) {
                        this.D.add(component, 0);
                        if (!v) {
                            break Label_0050;
                        }
                    }
                    component2 = component;
                    if (v) {
                        break Label_0051;
                    }
                    final boolean b2 = component instanceof q;
                }
                if (b) {
                    this.E.add(component, 0);
                }
            }
            component2 = component;
        }
        component2.setBounds(this.n);
        final Window o = this.o;
        if (!v) {
            if (o == null) {
                return;
            }
            this.o.removeAll();
            final Window o2 = this.o;
        }
        o.setVisible(false);
    }
    
    public void setActionReceiver(final g t) {
        this.t = t;
    }
    
    private void a(final g g, final com.easypano.tourweaver.d.e e, final b b) {
        final boolean v = g.v;
        boolean b9;
        boolean b8;
        boolean b7;
        boolean b6;
        boolean b5;
        boolean b4;
        final boolean b3;
        final boolean b2 = b3 = (b4 = (b5 = (b6 = (b7 = (b8 = (b9 = (e instanceof com.easypano.tourweaver.b.g)))))));
        final boolean b11;
        final boolean b12;
        Label_0225: {
            Label_0221: {
                final boolean b10;
                Label_0205: {
                    Label_0201: {
                        Label_0107: {
                            if (!v) {
                                if (b2) {
                                    final com.easypano.tourweaver.b.g g2 = (com.easypano.tourweaver.b.g)e;
                                    g2.a((t)null);
                                    b10 = (b4 = (b5 = (b6 = (b7 = (b8 = (b9 = g2.l()))))));
                                    if (v) {
                                        break Label_0107;
                                    }
                                    if (b10) {
                                        final Enumeration elements = b.h().elements();
                                        while (elements.hasMoreElements()) {
                                            final x x = elements.nextElement();
                                            g2.a(b.a(x), x.getName());
                                            if (v) {
                                                break Label_0201;
                                            }
                                            if (v) {
                                                break;
                                            }
                                        }
                                    }
                                }
                                b11 = (b7 = (b8 = (b9 = (e instanceof o))));
                            }
                        }
                        if (v) {
                            break Label_0205;
                        }
                        if (b2) {
                            final o o = (o)e;
                            o.a((t)null);
                            b12 = (b5 = (b6 = (b7 = (b8 = (b9 = o.n())))));
                            if (v) {
                                break Label_0205;
                            }
                            if (b12) {
                                final Enumeration elements2 = b.h().elements();
                                while (elements2.hasMoreElements()) {
                                    final x x2 = elements2.nextElement();
                                    o.a(b.a(x2), x2.getName());
                                    if (v) {
                                        break Label_0221;
                                    }
                                    if (v) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    b7 = (b8 = (b9 = (e instanceof gb)));
                }
                if (v) {
                    break Label_0225;
                }
                if (b10) {
                    this.e = (gb)e;
                }
            }
            b7 = (b8 = (b9 = (e instanceof lb)));
        }
        if (!v) {
            if (b12) {
                ((lb)e).a(g);
            }
            b7 = (b8 = (b9 = (e instanceof l)));
        }
        if (!v) {
            if (b11) {
                (this.c = (l)e).a(this.k);
            }
            b7 = (b6 = (b8 = (b9 = (e instanceof q))));
        }
        if (!v) {
            com.easypano.tourweaver.d.e d = null;
            Label_0329: {
                if (b11) {
                    this.d = (q)e;
                    final q q = (q)(d = this.d);
                    if (v) {
                        break Label_0329;
                    }
                    q.setPlayerListener(this.k);
                    if (this.i != null) {
                        this.i.a(this.d);
                    }
                }
                d = e;
            }
            b8 = (b7 = (b9 = (d instanceof PlayerListener)));
        }
        if (!v) {
            if (b7) {
                g.addPlayerListener((PlayerListener)e);
            }
            b9 = (b8 = (e instanceof ab));
        }
        Enumeration enumeration = null;
        Label_0483: {
            com.easypano.tourweaver.d.e e2 = null;
            Label_0476: {
                if (!v) {
                    if (b8) {
                        this.q = (ab)e;
                        enumeration = b.j().elements();
                        while (enumeration.hasMoreElements()) {
                            this.q.a((n)enumeration.nextElement());
                            if (v) {
                                break Label_0483;
                            }
                            if (v) {
                                break;
                            }
                        }
                    }
                    e2 = e;
                    if (v) {
                        break Label_0476;
                    }
                    b9 = (e instanceof f);
                }
                if (b9) {
                    enumeration = ((f)e).e();
                    while (enumeration.hasMoreElements()) {
                        this.a(g, enumeration.nextElement(), b);
                        if (v) {
                            break Label_0483;
                        }
                        if (v) {
                            break;
                        }
                    }
                }
                e2 = e;
            }
            enumeration = e2.d();
        }
        final Enumeration<a> enumeration2 = (Enumeration<a>)enumeration;
        if ((v || enumeration2 != null) && enumeration2.hasMoreElements()) {
            this.a(g, enumeration.nextElement(), b);
            if (!v) {
                goto Label_0493;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean v = com.easypano.tourweaver.g.v;
        final Object source = actionEvent.getSource();
        int n2;
        final int n = n2 = ((source instanceof com.easypano.tourweaver.b.i) ? 1 : 0);
        if (!v) {
            if (n == 0) {
                return;
            }
            n2 = 0;
        }
        int i = n2;
        while (i < 3) {
            final com.easypano.tourweaver.b.i j = this.v[i];
            Label_0069: {
                if (!v) {
                    if (j != source) {
                        this.v[i].b(false);
                        if (!v) {
                            break Label_0069;
                        }
                    }
                    final com.easypano.tourweaver.b.i k = this.v[i];
                }
                j.b(true);
            }
            ++i;
            if (v) {
                break;
            }
        }
    }
    
    private void a(final lb lb, final b b, final g g) {
        final boolean v = g.v;
        lb u;
        final k k = (k)(u = this.u);
        if (!v) {
            Label_0957: {
                if (k == null) {
                    final Vector f = b.f();
                    final Vector i = b.i();
                    final Vector l = b.l();
                    this.u = new k("");
                    (this.w = new com.easypano.tourweaver.b.i(com.easypano.tourweaver.h.F[23])).c(com.easypano.tourweaver.h.F[20]);
                    final k j = new k(com.easypano.tourweaver.h.F[8]);
                    (this.v = new com.easypano.tourweaver.b.i[3])[0] = new com.easypano.tourweaver.b.i(com.easypano.tourweaver.h.F[12]);
                    this.v[1] = new com.easypano.tourweaver.b.i(com.easypano.tourweaver.h.F[13]);
                    this.v[2] = new com.easypano.tourweaver.b.i(com.easypano.tourweaver.h.F[17]);
                    this.v[0].a(this);
                    this.v[1].a(this);
                    this.v[2].a(this);
                    this.v[0].c(com.easypano.tourweaver.h.F[15]);
                    this.v[1].c(com.easypano.tourweaver.h.F[16]);
                    this.v[2].c(com.easypano.tourweaver.h.F[10]);
                    int n = 0;
                    while (true) {
                    Label_0657_Outer:
                        while (n < 3) {
                            this.v[n].a(g);
                            ++n;
                            if (v) {
                                int s;
                                final int n2 = s = (this.s ? 1 : 0);
                                if (!v) {
                                    if (n2 != 0) {
                                        final com.easypano.tourweaver.b.i m = new com.easypano.tourweaver.b.i(com.easypano.tourweaver.h.F[1]);
                                        m.c(com.easypano.tourweaver.h.F[2]);
                                        m.a(g);
                                        this.u.a(m);
                                        this.u.a(new j());
                                    }
                                    this.z = new k(com.easypano.tourweaver.h.F[11]);
                                    this.A = new k(com.easypano.tourweaver.h.F[14]);
                                    this.B = new k(com.easypano.tourweaver.h.F[9]);
                                    s = 0;
                                }
                                int n3 = s;
                                while (true) {
                                    while (n3 < f.size()) {
                                        final String s2 = f.elementAt(n3);
                                        final com.easypano.tourweaver.b.i i2 = new com.easypano.tourweaver.b.i(s2);
                                        i2.a(true);
                                        i2.a(g);
                                        i2.c(com.easypano.tourweaver.h.F[22] + s2 + ")");
                                        this.z.a(i2);
                                        if (!v) {
                                            final int n4 = n3;
                                            final int n5 = f.size() - 1;
                                            if (v) {
                                                Label_0946: {
                                                    while (true) {
                                                        int n6 = 0;
                                                        int n7 = 0;
                                                        Label_0799: {
                                                            while (true) {
                                                                if (n4 < n5) {
                                                                    final String s3 = i.elementAt(n3);
                                                                    final com.easypano.tourweaver.b.i i3 = new com.easypano.tourweaver.b.i(s3);
                                                                    i3.a(true);
                                                                    i3.a(g);
                                                                    i3.c(com.easypano.tourweaver.h.F[19] + s3 + ")");
                                                                    this.A.a(i3);
                                                                    if (!v) {
                                                                        n6 = n3;
                                                                        n7 = i.size() - 1;
                                                                        if (v) {
                                                                            break Label_0799;
                                                                        }
                                                                        if (n6 != n7) {
                                                                            this.A.a(new j());
                                                                        }
                                                                        ++n3;
                                                                    }
                                                                    if (!v) {
                                                                        i.size();
                                                                        continue Label_0657_Outer;
                                                                    }
                                                                }
                                                                break;
                                                            }
                                                            n3 = 0;
                                                            l.size();
                                                        }
                                                        if (n6 < n7) {
                                                            final String s4 = l.elementAt(n3);
                                                            final com.easypano.tourweaver.b.i i4 = new com.easypano.tourweaver.b.i(s4);
                                                            i4.a(true);
                                                            i4.a(g);
                                                            i4.c(com.easypano.tourweaver.h.F[18] + s4 + ")");
                                                            this.B.a(i4);
                                                            if (v) {
                                                                break Label_0946;
                                                            }
                                                            if (!v) {
                                                                if (n3 != l.size() - 1) {
                                                                    this.B.a(new j());
                                                                }
                                                                ++n3;
                                                            }
                                                            if (!v) {
                                                                continue;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    this.u.a((com.easypano.tourweaver.b.i)this.z);
                                                    this.u.a((com.easypano.tourweaver.b.i)this.A);
                                                }
                                                this.u.a((com.easypano.tourweaver.b.i)this.B);
                                                break Label_0957;
                                            }
                                            if (n4 != n5) {
                                                this.z.a(new j());
                                            }
                                            ++n3;
                                        }
                                        if (v) {
                                            break;
                                        }
                                    }
                                    n3 = 0;
                                    continue;
                                }
                            }
                            if (v) {
                                break;
                            }
                        }
                        j.a(this.v[0]);
                        j.a(new j());
                        j.a(this.v[1]);
                        j.a(new j());
                        j.a(this.v[2]);
                        this.u.a((com.easypano.tourweaver.b.i)j);
                        this.u.a(new j());
                        (this.x = new com.easypano.tourweaver.b.i(com.easypano.tourweaver.h.F[21])).c(com.easypano.tourweaver.h.F[20]);
                        this.x.a(true);
                        this.x.a(g);
                        this.y = new j();
                        continue;
                    }
                }
            }
            u = lb;
        }
        u.a(this.u);
    }
    
    public void updateConfig(final b b) {
        final boolean v = com.easypano.tourweaver.g.v;
        this.C = b.o();
        final com.easypano.tourweaver.d.e k = b.k();
        this.i.a(this);
        this.t.addPlayerListener(this);
        final String lowerCase = com.easypano.tourweaver.a.e.a(com.easypano.tourweaver.a.e.a(com.easypano.tourweaver.a.e.h.toString()).replace('\\', '/'), com.easypano.tourweaver.h.F[6], " ").toLowerCase();
        int n;
        final boolean b2 = (n = (b.d() ? 1 : 0)) != 0;
        Label_0120: {
            Label_0109: {
                if (!v) {
                    if (!b2) {
                        break Label_0109;
                    }
                    n = lowerCase.indexOf(b.e().toLowerCase());
                }
                if (n != -1) {
                    this.i.a(true);
                    if (!v) {
                        break Label_0120;
                    }
                }
            }
            this.i.a(b.c());
        }
        this.a(k.d());
        final ib a = this.a;
        if (!v) {
            if (a == null) {
                throw new NullPointerException(com.easypano.tourweaver.h.F[7]);
            }
            this.s = b.n();
            final ib a2 = this.a;
        }
        a.e();
        this.a(this.t, k, b);
        this.a(this.a, b, this.t);
        h h = this;
        if (!v) {
            if (this.c != null) {
                this.D = this.c.getParent();
            }
            h = this;
        }
        Container container;
        final q q = (q)(container = h.d);
        if (!v) {
            if (q != null) {
                this.E = this.d.getParent();
            }
            container = this.g.getParent();
        }
        final Container container2 = container;
        if (!v) {
            if (container2 == null) {
                return;
            }
            this.g.a(10);
        }
        Label_0373: {
            try {
                int i = 0;
                while (i < 45) {
                    Thread.sleep(30L);
                    this.g.a(10 + i * 2);
                    ++i;
                    if (v) {
                        break Label_0373;
                    }
                    if (v) {
                        break;
                    }
                }
            }
            catch (Exception ex) {}
            container2.remove(this.g);
            this.g.a();
            container2.add(this.a);
            this.a.setBounds(this.g.getBounds());
            this.c.getParent().requestFocus();
            this.c.requestFocus();
        }
        h h2 = this;
        h h3 = this;
        if (!v) {
            if (!this.h) {
                return;
            }
            h2 = this;
            h3 = this;
        }
        if (!v) {
            if (h3.c == null) {
                return;
            }
            h2 = this;
        }
        h2.fullScreen(com.easypano.tourweaver.h.F[5]);
    }
    
    public void updateScene(final int n, final int n2, final int n3, final int n4, final String s) {
    }
    
    public void updateScene(final byte[] array, final int n, final int n2, final int n3, final int n4, final String s) {
    }
    
    public void updateScene(final byte[] array, final String s, final int n, final int n2) {
    }
    
    public void updateScene(final int n, final int n2, final int n3, final String s) {
    }
    
    public void updateObject(final Object o, final String s) {
        if (o instanceof Image) {
            this.g.a((Image)o, s);
        }
    }
    
    public void sceneSwitching(final String s) {
    }
    
    public void movieSwitching(final String s) {
        this.A.g(s);
    }
    
    public void mapSwitching(final String s) {
    }
    
    public void sceneSwitched(final String s) {
        this.z.g(s);
    }
    
    public void mapSwitched(final String s) {
        this.B.g(s);
    }
    
    public void moviePaused(final String s) {
    }
    
    public void movieStoped(final String s) {
        h h = this;
        if (!com.easypano.tourweaver.g.v) {
            if (this.C == null) {
                return;
            }
            h = this;
        }
        h.t.inputScript(this.C);
    }
    
    static {
        final String[] f = new String[26];
        final int n = 0;
        final char[] charArray = "\u0002>|\u001fK\u0012*y\u0018N\u0015#p\u0005\u0005\u00142g\u0002C\u0000ff\bH\t#c\u0002H\u0010#gB".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'g';
                            break;
                        }
                        case 1: {
                            c2 = 'F';
                            break;
                        }
                        case 2: {
                            c2 = '\u0015';
                            break;
                        }
                        case 3: {
                            c2 = 'k';
                            break;
                        }
                        default: {
                            c2 = '-';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        f[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "!3y\u0007\r4%g\u000eH\t".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'g';
                            break;
                        }
                        case 1: {
                            c4 = 'F';
                            break;
                        }
                        case 2: {
                            c4 = '\u0015';
                            break;
                        }
                        case 3: {
                            c4 = 'k';
                            break;
                        }
                        default: {
                            c4 = '-';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        f[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u00013y\u0007^\u00044p\u000eCO5a\u0019D\t!5\u0018N\u0002(p\u001dD\u00021p\u0019\u0004".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'g';
                            break;
                        }
                        case 1: {
                            c6 = 'F';
                            break;
                        }
                        case 2: {
                            c6 = '\u0015';
                            break;
                        }
                        case 3: {
                            c6 = 'k';
                            break;
                        }
                        default: {
                            c6 = '-';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        f[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0014%p\u0005H\u0011/p\u001cH\u0015".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'g';
                            break;
                        }
                        case 1: {
                            c8 = 'F';
                            break;
                        }
                        case 2: {
                            c8 = '\u0015';
                            break;
                        }
                        case 3: {
                            c8 = 'k';
                            break;
                        }
                        default: {
                            c8 = '-';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        f[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u0014%p\u0005HG0|\u000eZ\u00024".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'g';
                            break;
                        }
                        case 1: {
                            c10 = 'F';
                            break;
                        }
                        case 2: {
                            c10 = '\u0015';
                            break;
                        }
                        case 3: {
                            c10 = 'k';
                            break;
                        }
                        default: {
                            c10 = '-';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        f[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "4%p\u0005H1/p\u001cH\u0015".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'g';
                            break;
                        }
                        case 1: {
                            c12 = 'F';
                            break;
                        }
                        case 2: {
                            c12 = '\u0015';
                            break;
                        }
                        case 3: {
                            c12 = 'k';
                            break;
                        }
                        default: {
                            c12 = '-';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        f[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "Bt%".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'g';
                            break;
                        }
                        case 1: {
                            c14 = 'F';
                            break;
                        }
                        case 2: {
                            c14 = '\u0015';
                            break;
                        }
                        case 3: {
                            c14 = 'k';
                            break;
                        }
                        default: {
                            c14 = '-';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        f[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\n'|\u0005z\u000e(q\u0004ZG{(KC\u0012*yG\r3.pKN\b(s\u0002JI2m\u001f\r\n3f\u001f\r\u0004){\u001fL\u000e(fKLG\u000bt\u0002C0/{\u000fB\u0010".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'g';
                            break;
                        }
                        case 1: {
                            c16 = 'F';
                            break;
                        }
                        case 2: {
                            c16 = '\u0015';
                            break;
                        }
                        case 3: {
                            c16 = 'k';
                            break;
                        }
                        default: {
                            c16 = '-';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        f[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "63t\u0007D\u0013?".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'g';
                            break;
                        }
                        case 1: {
                            c18 = 'F';
                            break;
                        }
                        case 2: {
                            c18 = '\u0015';
                            break;
                        }
                        case 3: {
                            c18 = 'k';
                            break;
                        }
                        default: {
                            c18 = '-';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        f[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "*'eKa\u000e5a".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'g';
                            break;
                        }
                        case 1: {
                            c20 = 'F';
                            break;
                        }
                        case 2: {
                            c20 = '\u0015';
                            break;
                        }
                        case 3: {
                            c20 = 'k';
                            break;
                        }
                        default: {
                            c20 = '-';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        f[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "\u0014#a\u001aX\u0006*|\u001fTO/{\u001f\rUo".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = 'g';
                            break;
                        }
                        case 1: {
                            c22 = 'F';
                            break;
                        }
                        case 2: {
                            c22 = '\u0015';
                            break;
                        }
                        case 3: {
                            c22 = 'k';
                            break;
                        }
                        default: {
                            c22 = '-';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        f[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "4%p\u0005HG\n|\u0018Y".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = 'g';
                            break;
                        }
                        case 1: {
                            c24 = 'F';
                            break;
                        }
                        case 2: {
                            c24 = '\u0015';
                            break;
                        }
                        case 3: {
                            c24 = 'k';
                            break;
                        }
                        default: {
                            c24 = '-';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        f[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "//r\u0003".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1518: {
                if (n50 > 1) {
                    break Label_1518;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = 'g';
                            break;
                        }
                        case 1: {
                            c26 = 'F';
                            break;
                        }
                        case 2: {
                            c26 = '\u0015';
                            break;
                        }
                        case 3: {
                            c26 = 'k';
                            break;
                        }
                        default: {
                            c26 = '-';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        f[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "+)b".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1638: {
                if (n54 > 1) {
                    break Label_1638;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = 'g';
                            break;
                        }
                        case 1: {
                            c28 = 'F';
                            break;
                        }
                        case 2: {
                            c28 = '\u0015';
                            break;
                        }
                        case 3: {
                            c28 = 'k';
                            break;
                        }
                        default: {
                            c28 = '-';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        f[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "*)c\u0002HG\n|\u0018Y".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1758: {
                if (n58 > 1) {
                    break Label_1758;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = 'g';
                            break;
                        }
                        case 1: {
                            c30 = 'F';
                            break;
                        }
                        case 2: {
                            c30 = '\u0015';
                            break;
                        }
                        case 3: {
                            c30 = 'k';
                            break;
                        }
                        default: {
                            c30 = '-';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        f[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "\u0014#a\u001aX\u0006*|\u001fTO/{\u001f\rVo".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1878: {
                if (n62 > 1) {
                    break Label_1878;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = 'g';
                            break;
                        }
                        case 1: {
                            c32 = 'F';
                            break;
                        }
                        case 2: {
                            c32 = '\u0015';
                            break;
                        }
                        case 3: {
                            c32 = 'k';
                            break;
                        }
                        default: {
                            c32 = '-';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        f[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "\u0014#a\u001aX\u0006*|\u001fTO/{\u001f\rWo".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1998: {
                if (n66 > 1) {
                    break Label_1998;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = 'g';
                            break;
                        }
                        case 1: {
                            c34 = 'F';
                            break;
                        }
                        case 2: {
                            c34 = '\u0015';
                            break;
                        }
                        case 3: {
                            c34 = 'k';
                            break;
                        }
                        default: {
                            c34 = '-';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        f[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "5'{\u000fB\n".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2118: {
                if (n70 > 1) {
                    break Label_2118;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = 'g';
                            break;
                        }
                        case 1: {
                            c36 = 'F';
                            break;
                        }
                        case 2: {
                            c36 = '\u0015';
                            break;
                        }
                        case 3: {
                            c36 = 'k';
                            break;
                        }
                        default: {
                            c36 = '-';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        f[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = "\u00141|\u001fN\u000f2z\u0006L\u0017nF\u001f_\u000e(rK".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2238: {
                if (n74 > 1) {
                    break Label_2238;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = 'g';
                            break;
                        }
                        case 1: {
                            c38 = 'F';
                            break;
                        }
                        case 2: {
                            c38 = '\u0015';
                            break;
                        }
                        case 3: {
                            c38 = 'k';
                            break;
                        }
                        default: {
                            c38 = '-';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        f[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = "\u00141|\u001fN\u000f2z\u0006B\u0011/pC~\u00134|\u0005JG".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2358: {
                if (n78 > 1) {
                    break Label_2358;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = 'g';
                            break;
                        }
                        case 1: {
                            c40 = 'F';
                            break;
                        }
                        case 2: {
                            c40 = '\u0015';
                            break;
                        }
                        case 3: {
                            c40 = 'k';
                            break;
                        }
                        default: {
                            c40 = '-';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 > n80) {
                continue;
            }
            break;
        }
        f[n77] = new String(charArray20).intern();
        final int n81 = 20;
        final char[] charArray21 = "\u0014.z\u001cY\b)y\tL\u0015n<".toCharArray();
        int length21;
        int n83;
        final int n82 = n83 = (length21 = charArray21.length);
        int n84 = 0;
        while (true) {
            Label_2478: {
                if (n82 > 1) {
                    break Label_2478;
                }
                length21 = (n83 = n84);
                do {
                    final char c41 = charArray21[n83];
                    char c42 = '\0';
                    switch (n84 % 5) {
                        case 0: {
                            c42 = 'g';
                            break;
                        }
                        case 1: {
                            c42 = 'F';
                            break;
                        }
                        case 2: {
                            c42 = '\u0015';
                            break;
                        }
                        case 3: {
                            c42 = 'k';
                            break;
                        }
                        default: {
                            c42 = '-';
                            break;
                        }
                    }
                    charArray21[length21] = (char)(c41 ^ c42);
                    ++n84;
                } while (n82 == 0);
            }
            if (n82 > n84) {
                continue;
            }
            break;
        }
        f[n81] = new String(charArray21).intern();
        final int n85 = 21;
        final char[] charArray22 = "4.z\u001cy\b)y)L\u0015".toCharArray();
        int length22;
        int n87;
        final int n86 = n87 = (length22 = charArray22.length);
        int n88 = 0;
        while (true) {
            Label_2598: {
                if (n86 > 1) {
                    break Label_2598;
                }
                length22 = (n87 = n88);
                do {
                    final char c43 = charArray22[n87];
                    char c44 = '\0';
                    switch (n88 % 5) {
                        case 0: {
                            c44 = 'g';
                            break;
                        }
                        case 1: {
                            c44 = 'F';
                            break;
                        }
                        case 2: {
                            c44 = '\u0015';
                            break;
                        }
                        case 3: {
                            c44 = 'k';
                            break;
                        }
                        default: {
                            c44 = '-';
                            break;
                        }
                    }
                    charArray22[length22] = (char)(c43 ^ c44);
                    ++n88;
                } while (n86 == 0);
            }
            if (n86 > n88) {
                continue;
            }
            break;
        }
        f[n85] = new String(charArray22).intern();
        final int n89 = 22;
        final char[] charArray23 = "\u00141|\u001fN\u000f2z\u0018N\u0002(pC~\u00134|\u0005JG".toCharArray();
        int length23;
        int n91;
        final int n90 = n91 = (length23 = charArray23.length);
        int n92 = 0;
        while (true) {
            Label_2718: {
                if (n90 > 1) {
                    break Label_2718;
                }
                length23 = (n91 = n92);
                do {
                    final char c45 = charArray23[n91];
                    char c46 = '\0';
                    switch (n92 % 5) {
                        case 0: {
                            c46 = 'g';
                            break;
                        }
                        case 1: {
                            c46 = 'F';
                            break;
                        }
                        case 2: {
                            c46 = '\u0015';
                            break;
                        }
                        case 3: {
                            c46 = 'k';
                            break;
                        }
                        default: {
                            c46 = '-';
                            break;
                        }
                    }
                    charArray23[length23] = (char)(c45 ^ c46);
                    ++n92;
                } while (n90 == 0);
            }
            if (n90 > n92) {
                continue;
            }
            break;
        }
        f[n89] = new String(charArray23).intern();
        final int n93 = 23;
        final char[] charArray24 = "4.z\u001c\r3)z\u0007O\u00064".toCharArray();
        int length24;
        int n95;
        final int n94 = n95 = (length24 = charArray24.length);
        int n96 = 0;
        while (true) {
            Label_2838: {
                if (n94 > 1) {
                    break Label_2838;
                }
                length24 = (n95 = n96);
                do {
                    final char c47 = charArray24[n95];
                    char c48 = '\0';
                    switch (n96 % 5) {
                        case 0: {
                            c48 = 'g';
                            break;
                        }
                        case 1: {
                            c48 = 'F';
                            break;
                        }
                        case 2: {
                            c48 = '\u0015';
                            break;
                        }
                        case 3: {
                            c48 = 'k';
                            break;
                        }
                        default: {
                            c48 = '-';
                            break;
                        }
                    }
                    charArray24[length24] = (char)(c47 ^ c48);
                    ++n96;
                } while (n94 == 0);
            }
            if (n94 > n96) {
                continue;
            }
            break;
        }
        f[n93] = new String(charArray24).intern();
        final int n97 = 24;
        final char[] charArray25 = "\">|\u001f\r!3y\u0007\r4%g\u000eH\t".toCharArray();
        int length25;
        int n99;
        final int n98 = n99 = (length25 = charArray25.length);
        int n100 = 0;
        while (true) {
            Label_2958: {
                if (n98 > 1) {
                    break Label_2958;
                }
                length25 = (n99 = n100);
                do {
                    final char c49 = charArray25[n99];
                    char c50 = '\0';
                    switch (n100 % 5) {
                        case 0: {
                            c50 = 'g';
                            break;
                        }
                        case 1: {
                            c50 = 'F';
                            break;
                        }
                        case 2: {
                            c50 = '\u0015';
                            break;
                        }
                        case 3: {
                            c50 = 'k';
                            break;
                        }
                        default: {
                            c50 = '-';
                            break;
                        }
                    }
                    charArray25[length25] = (char)(c49 ^ c50);
                    ++n100;
                } while (n98 == 0);
            }
            if (n98 > n100) {
                continue;
            }
            break;
        }
        f[n97] = new String(charArray25).intern();
        final int n101 = 25;
        final char[] charArray26 = "\u00140|\u000eZ\u00024".toCharArray();
        int length26;
        int n103;
        final int n102 = n103 = (length26 = charArray26.length);
        int n104 = 0;
        while (true) {
            Label_3078: {
                if (n102 > 1) {
                    break Label_3078;
                }
                length26 = (n103 = n104);
                do {
                    final char c51 = charArray26[n103];
                    char c52 = '\0';
                    switch (n104 % 5) {
                        case 0: {
                            c52 = 'g';
                            break;
                        }
                        case 1: {
                            c52 = 'F';
                            break;
                        }
                        case 2: {
                            c52 = '\u0015';
                            break;
                        }
                        case 3: {
                            c52 = 'k';
                            break;
                        }
                        default: {
                            c52 = '-';
                            break;
                        }
                    }
                    charArray26[length26] = (char)(c51 ^ c52);
                    ++n104;
                } while (n102 == 0);
            }
            if (n102 <= n104) {
                f[n101] = new String(charArray26).intern();
                h.F = f;
                return;
            }
            continue;
        }
    }
}
