// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import com.easypano.tourweaver.g;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import com.easypano.tourweaver.d.e;
import java.awt.Panel;

public class ib extends Panel implements lb, e
{
    Image a;
    String b;
    int c;
    int d;
    ab e;
    int f;
    k g;
    Image h;
    Graphics i;
    l j;
    Rectangle k;
    q l;
    Rectangle m;
    Vector n;
    g o;
    private static String[] z;
    
    public ib() {
        this.a = null;
        this.b = "";
        this.c = 0;
        this.d = 0;
        this.f = -1;
        this.h = null;
        this.i = null;
        this.n = new Vector();
        this.setLayout(null);
        this.enableEvents(48L);
    }
    
    public void repaint() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        super.repaint();
        ib ib = this;
        if (!u) {
            if (this.j != null) {
                this.j.repaint();
            }
            ib = this;
        }
        final q l = ib.l;
        if (!u) {
            if (l == null) {
                return;
            }
            final q i = this.l;
        }
        l.repaint();
    }
    
    public void e() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Component[] components = this.getComponents();
        final l j = this.j;
        if (!u) {
            if (j == null) {
                return;
            }
            final l i = this.j;
        }
        final Rectangle bounds = j.getBounds();
        int k = 0;
        while (k < components.length) {
            final boolean b = components[k] instanceof b;
            Label_0443: {
                final boolean b4;
                Label_0432: {
                    Label_0421: {
                        final Component component;
                        Label_0277: {
                            Label_0257: {
                                if (!u) {
                                    if (b) {
                                        final boolean b2 = components[k] instanceof com.easypano.tourweaver.b.e;
                                        if (u) {
                                            break Label_0257;
                                        }
                                        if (!b2) {
                                            final Rectangle bounds2 = components[k].getBounds();
                                            Rectangle m;
                                            final Rectangle rectangle = m = bounds;
                                            Label_0241: {
                                                if (!u) {
                                                    if (rectangle.intersects(bounds2)) {
                                                        components[k].setBounds(bounds2.x - bounds.x, bounds2.y - bounds.y, bounds2.width, bounds2.height);
                                                        this.j.a((e)components[k]);
                                                        this.remove(components[k]);
                                                        if (!u) {
                                                            break Label_0241;
                                                        }
                                                    }
                                                    final Rectangle l;
                                                    m = (l = this.m);
                                                }
                                                ib ib = null;
                                                Label_0235: {
                                                    if (!u) {
                                                        if (rectangle == null) {
                                                            break Label_0241;
                                                        }
                                                        ib = this;
                                                        if (u) {
                                                            break Label_0235;
                                                        }
                                                        m = this.m;
                                                    }
                                                    if (!m.intersects(bounds2)) {
                                                        break Label_0241;
                                                    }
                                                    components[k].setBounds(bounds2.x - this.m.x, bounds2.y - this.m.y, bounds2.width, bounds2.height);
                                                    this.l.c((a)components[k]);
                                                    ib = this;
                                                }
                                                ib.remove(components[k]);
                                            }
                                            if (!u) {
                                                break Label_0443;
                                            }
                                        }
                                    }
                                    component = components[k];
                                    if (u) {
                                        break Label_0277;
                                    }
                                    final boolean b3 = component instanceof l;
                                }
                            }
                            if (!b) {
                                b4 = (components[k] instanceof q);
                                if (u) {
                                    break Label_0432;
                                }
                                if (!b4) {
                                    break Label_0421;
                                }
                            }
                            final Component component2 = components[k];
                        }
                        final Rectangle bounds3 = component.getBounds();
                        final fb fb = new fb();
                        fb.setLayout(null);
                        fb.setBounds(bounds3);
                        components[k].setBounds(0, 0, bounds3.width, bounds3.height);
                        fb.add(components[k]);
                        final a a = (a)components[k];
                        System.out.println(ib.z[0] + components[k]);
                        final a a2 = a;
                        Label_0416: {
                            if (!u) {
                                if (a2.k() > this.f) {
                                    super.add(fb, 1);
                                    this.f = a.k();
                                    if (!u) {
                                        break Label_0416;
                                    }
                                }
                                super.add(fb, 2);
                            }
                        }
                        if (!u) {
                            break Label_0443;
                        }
                    }
                    final Component component3 = components[k];
                    if (u) {
                        break Label_0443;
                    }
                    final boolean b5 = component3 instanceof gb;
                }
                if (b4) {
                    super.add(components[k], 0);
                }
            }
            ++k;
            if (u) {
                break;
            }
        }
    }
    
    public void a(final bb bb) {
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        final l j = this.j;
        if (!com.easypano.tourweaver.b.f.u) {
            if (j == null) {
                return;
            }
            final l i = this.j;
        }
        j.processKeyEvent(keyEvent);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        int n;
        final boolean b = (n = (this.isEnabled() ? 1 : 0)) != 0;
        ib ib = null;
        Label_0036: {
            if (!u) {
                if (!b) {
                    return;
                }
                ib = this;
                if (u) {
                    break Label_0036;
                }
                super.processMouseEvent(mouseEvent);
                n = mouseEvent.getID();
            }
            if (n != 501) {
                return;
            }
            ib = this;
        }
        ib.o.removeListbox();
    }
    
    public bb a() {
        return null;
    }
    
    public void a(final k g) {
        this.g = g;
    }
    
    public k b() {
        return this.g;
    }
    
    public void c() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        Graphics graphics = null;
        try {
            this.paint(graphics = this.getGraphics());
        }
        catch (Exception ex) {}
        finally {
            final Graphics graphics2 = graphics;
            if (u || graphics2 != null) {
                graphics2.dispose();
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Rectangle bounds = this.getBounds();
        ib ib = this;
        ib ib2 = this;
        ib ib3 = this;
        ib ib4 = this;
        if (!u) {
            if (this.h == null) {
                this.h = this.createImage(bounds.width, bounds.height);
                this.i = this.h.getGraphics();
            }
            ib = this;
            ib2 = this;
            ib3 = this;
            ib4 = this;
        }
        if (!u) {
            if (ib4.i != null) {
                this.i.clearRect(0, 0, bounds.width, bounds.height);
            }
            ib = this;
            ib2 = this;
            ib3 = this;
        }
        Label_0152: {
            final Graphics i;
            Label_0140: {
                if (!u) {
                    if (ib3.a != null) {
                        i = this.i;
                        if (u) {
                            break Label_0140;
                        }
                        if (i != null) {
                            this.i.drawImage(this.a, (bounds.width - this.c) / 2, (bounds.height - this.d) / 2, this);
                        }
                    }
                    ib = this;
                    ib2 = this;
                }
                if (u) {
                    break Label_0152;
                }
                final Graphics j = ib2.i;
            }
            if (i != null) {
                super.paint(this.i);
            }
            ib = this;
        }
        if (ib.h != null) {
            graphics.drawImage(this.h, 0, 0, this);
        }
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        String s3 = s;
        String s4 = s;
        if (!u) {
            if (s == null) {
                return;
            }
            s3 = s2;
            s4 = s2;
        }
        if (!u) {
            if (s4 == null) {
                return;
            }
            s3 = s;
        }
        boolean b2;
        final boolean b = b2 = s3.equals(ib.z[3]);
        if (!u) {
            if (b) {
                this.b = s2;
                if (!u) {
                    return;
                }
            }
            final boolean equals;
            b2 = (equals = s.equals(ib.z[1]));
        }
        if (!u) {
            if (b) {
                this.setBackground(com.easypano.tourweaver.a.e.b(s2));
                if (!u) {
                    return;
                }
            }
            b2 = s.equals(ib.z[2]);
        }
        if (b2) {
            this.setName(s2);
        }
    }
    
    public void a(final Image a, final String s) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        String s2 = s;
        if (!u) {
            if (s == null) {
                return;
            }
            s2 = s;
        }
        if (s2.equals(this.b)) {
            com.easypano.tourweaver.a.e.a(this.a = a);
            this.c = a.getWidth(this);
            this.d = a.getHeight(this);
            ib ib = this;
            if (!u) {
                if (this.e != null) {
                    final Rectangle bounds = this.getBounds();
                    final int n = (bounds.width - this.c) / 2;
                    final int n2 = (bounds.height - this.d) / 2;
                    final Rectangle bounds2 = this.e.getBounds();
                    this.e.a(a, n - bounds2.x, n2 - bounds2.y, this.c, this.d);
                }
                ib = this;
            }
            ib.c();
        }
        final Enumeration d = this.d();
        while (d.hasMoreElements()) {
            final e nextElement = d.nextElement();
            if (u || nextElement instanceof e) {
                nextElement.a(a, s);
            }
            if (u) {
                break;
            }
        }
    }
    
    public l f() {
        return this.j;
    }
    
    public Component add(final Component component) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Component[] components = this.getComponents();
        Component component2 = component;
        if (!u) {
            if (!(component instanceof a)) {
                this.add(component, 0);
                return component;
            }
            component2 = component;
        }
        final a a = (a)component2;
        int i = components.length - 1;
        while (true) {
            while (i >= 0) {
                final Component component3 = components[i];
                Label_0111: {
                    Label_0108: {
                        if (!u) {
                            final int n;
                            final boolean b = (n = ((component3 instanceof a) ? 1 : 0)) != 0;
                            if (u) {
                                if (n == -1) {
                                    this.add(component, 0);
                                }
                                return component;
                            }
                            if (!b) {
                                break Label_0108;
                            }
                            final Component component4 = components[i];
                        }
                        final a a2 = (a)component3;
                        if (u) {
                            break Label_0111;
                        }
                        if (a2.k() > a.k()) {
                            this.add(component, i + 1);
                            if (!u) {
                                break;
                            }
                        }
                    }
                    --i;
                }
                if (u) {
                    break;
                }
            }
            int n = i;
            continue;
        }
    }
    
    public void a(final e e) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (e instanceof Component));
        if (!u) {
            if (!b) {
                return;
            }
            this.add((Component)e);
            this.n.addElement(e);
            final boolean b4;
            b2 = (b4 = (b3 = (e instanceof ab)));
        }
        if (!u) {
            if (b) {
                this.e = (ab)e;
                if (!u) {
                    return;
                }
            }
            b3 = (b2 = (e instanceof l));
        }
        if (!u) {
            if (b2) {
                this.j = (l)e;
                this.k = this.j.getBounds();
                if (!u) {
                    return;
                }
            }
            b3 = (e instanceof q);
        }
        if (b3) {
            this.l = (q)e;
            this.m = this.l.getBounds();
        }
    }
    
    public void b(final e e) {
        if (e instanceof a) {
            this.remove((Component)e);
            final Vector n = this.n;
            if (!com.easypano.tourweaver.b.f.u) {
                if (n == null) {
                    return;
                }
                final Vector n2 = this.n;
            }
            n.removeElement(e);
        }
    }
    
    public e a(final String s) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        Object component = null;
        int i = 0;
        while (i < this.getComponentCount()) {
            component = this.getComponent(i);
            final String name = ((Component)component).getName();
            if (u) {
                return null;
            }
            if (!u) {
                if (name != null && name.equals(s) && !u) {
                    break;
                }
                ++i;
            }
            if (u) {
                break;
            }
        }
        Component component3;
        final Component component2 = component3 = (Component)component;
        if (!u) {
            if (!(component2 instanceof e)) {
                return null;
            }
            component3 = (Component)component;
        }
        return (e)component3;
    }
    
    public Enumeration d() {
        return this.n.elements();
    }
    
    public void a(final g o) {
        this.o = o;
        g g = o;
        if (!com.easypano.tourweaver.b.f.u) {
            if (o == null) {
                return;
            }
            g = o;
        }
        g.registerComponent(this);
    }
    
    public void destroy() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Component[] components = this.getComponents();
        int i = 0;
        while (true) {
            while (i < components.length) {
                if (u) {
                    final Graphics j = this.i;
                    if (!u) {
                        if (j == null) {
                            return;
                        }
                        final Graphics k = this.i;
                    }
                    j.dispose();
                    this.i = null;
                    return;
                }
                final Component component = components[i];
                Label_0045: {
                    if (!u) {
                        if (!(component instanceof lb)) {
                            break Label_0045;
                        }
                        final Component component2 = components[i];
                    }
                    ((lb)component).destroy();
                }
                ++i;
                if (u) {
                    break;
                }
            }
            this.setVisible(false);
            this.h = null;
            continue;
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "-\u000fF)\u0016<\nLl\u000el".toCharArray();
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
                            c2 = 'L';
                            break;
                        }
                        case 1: {
                            c2 = 'k';
                            break;
                        }
                        case 2: {
                            c2 = '\"';
                            break;
                        }
                        case 3: {
                            c2 = '\t';
                            break;
                        }
                        default: {
                            c2 = 'b';
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
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = ".\faf\u000e#\u0019".toCharArray();
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
                            c4 = 'L';
                            break;
                        }
                        case 1: {
                            c4 = 'k';
                            break;
                        }
                        case 2: {
                            c4 = '\"';
                            break;
                        }
                        case 3: {
                            c4 = '\t';
                            break;
                        }
                        default: {
                            c4 = 'b';
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
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\"\nOl".toCharArray();
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
                            c6 = 'L';
                            break;
                        }
                        case 1: {
                            c6 = 'k';
                            break;
                        }
                        case 2: {
                            c6 = '\"';
                            break;
                        }
                        case 3: {
                            c6 = '\t';
                            break;
                        }
                        default: {
                            c6 = 'b';
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
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = ".\fkd\u0005".toCharArray();
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
                            c8 = 'L';
                            break;
                        }
                        case 1: {
                            c8 = 'k';
                            break;
                        }
                        case 2: {
                            c8 = '\"';
                            break;
                        }
                        case 3: {
                            c8 = '\t';
                            break;
                        }
                        default: {
                            c8 = 'b';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                ib.z = z;
                return;
            }
            continue;
        }
    }
}
