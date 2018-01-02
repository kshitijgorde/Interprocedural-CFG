// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import com.easypano.tourweaver.d.e;
import java.awt.event.AdjustmentEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.event.AdjustmentListener;

public class n extends f implements AdjustmentListener
{
    public static final int v = 1;
    public static final int w = 2;
    s x;
    m y;
    m z;
    Dimension A;
    ib B;
    int C;
    int D;
    boolean E;
    private static String[] F;
    
    public n() {
        this(new s());
    }
    
    public void b(final int c) {
        this.C = c;
    }
    
    public void setBounds(final int n, final int n2, final int d, final int n3) {
        super.setBounds(n, n2, d, n3);
        this.D = d;
    }
    
    public void h() {
        this.D = this.getBounds().width;
    }
    
    public n(final s s) {
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = new Dimension();
        this.C = 1;
        this.D = 0;
        this.E = true;
        this.y = new m(0);
        this.z = new m(1);
        this.y.a(0, 40, 0, 500);
        this.z.a(0, 40, 0, 500);
        this.z.a(2);
        this.y.a(3);
        this.y.addAdjustmentListener(this);
        this.z.addAdjustmentListener(this);
        this.setLayout(null);
        this.a(s);
    }
    
    public Dimension l() {
        return this.A;
    }
    
    public void a(final s x) {
        this.b(this.x);
        this.x = x;
        s s = x;
        if (!f.u) {
            if (x == null) {
                return;
            }
            s = x;
        }
        s.a(0);
        super.a(x, 0);
    }
    
    public s m() {
        return this.x;
    }
    
    public void a(final a a) {
        final s x = this.x;
        if (!f.u) {
            if (x == null) {
                return;
            }
            final s x2 = this.x;
        }
        x.a(a);
    }
    
    public void c(final a a) {
        a.setVisible(true);
        super.a(a, a.k());
        super.t.addElement(a);
    }
    
    public boolean c(final int n) {
        final boolean u = f.u;
        int n2;
        final boolean b = (n2 = (this.E ? 1 : 0)) != 0;
        Label_0055: {
            if (!u) {
                if (b) {
                    final boolean visible = this.y.isVisible();
                    if (u || !visible) {
                        return visible;
                    }
                    final boolean b2 = (n2 = (this.z.isVisible() ? 1 : 0)) != 0;
                    if (u) {
                        break Label_0055;
                    }
                    if (!b2) {
                        goto Label_0047;
                    }
                }
                n2 = this.D + n;
            }
        }
        final int d = n2;
        final double n3 = this.getBounds().width / d;
        final int width = (int)(n3 * this.x.h() + 0.5);
        final int height = (int)(n3 * this.x.l() + 0.5);
        final int n4 = width;
        if (!u) {
            if (n4 > this.getBounds().width) {
                final int n5 = height;
                if (!u) {
                    if (n5 > this.getBounds().height) {
                        this.D = d;
                        this.x.a(n3, n3);
                        final Rectangle bounds2;
                        final Rectangle bounds = bounds2 = this.x.getBounds();
                        bounds2.x += (bounds.width - width) / 2;
                        final Rectangle rectangle = bounds;
                        rectangle.y += (bounds.height - height) / 2;
                        bounds.height = height;
                        bounds.width = width;
                        this.x.setBounds(bounds);
                        this.d(0);
                        this.e(0);
                        return true;
                    }
                }
            }
        }
        return n4 != 0;
    }
    
    public boolean d(final int n) {
        final boolean u = f.u;
        final s x = this.x;
        if (!u) {
            if (x == null) {
                return false;
            }
            final s x2 = this.x;
        }
        final Rectangle bounds = x.getBounds();
        final int width = bounds.width;
        final int width2 = this.A.width;
        if (!u) {
            if (width < width2) {
                return false;
            }
            final int x3 = bounds.x;
        }
        int n2 = width + width2;
        int n4;
        final int n3 = n4 = n2;
        if (!u) {
            if (n3 > 0) {
                n2 = 0;
            }
            final int n5;
            n4 = (n5 = n2 + bounds.width);
        }
        if (!u) {
            if (n3 < this.A.width) {
                n2 = this.A.width - bounds.width;
            }
            this.x.setBounds(n2, bounds.y, bounds.width, bounds.height);
            this.x.b(bounds.width, bounds.height);
            n4 = -n2;
        }
        final double n6 = n4 / (bounds.width - this.A.width);
        final int n7 = (int)(n6 * (this.y.getMaximum() - this.y.getMinimum())) + this.y.getMinimum();
        n n8 = this;
        Label_0217: {
            if (!u) {
                if (this.y.isVisible()) {
                    this.y.e(n7);
                    if (!u) {
                        break Label_0217;
                    }
                }
                n8 = this;
            }
            n8.c();
        }
        final double n9 = dcmpg(n6, 0.0);
        if (u || n9 <= 0) {
            return n9 != 0.0;
        }
        final double n10 = dcmpl(n6, 1.0);
        if (!u && n10 >= 0) {
            goto Label_0241;
        }
        return n10 != 0.0;
    }
    
    public boolean e(final int n) {
        final boolean u = f.u;
        final s x = this.x;
        if (!u) {
            if (x == null) {
                return false;
            }
            final s x2 = this.x;
        }
        final Rectangle bounds = x.getBounds();
        final int height = bounds.height;
        final int height2 = this.A.height;
        if (!u) {
            if (height < height2) {
                return false;
            }
            final int y = bounds.y;
        }
        int n2 = height + height2;
        int n4;
        final int n3 = n4 = n2;
        if (!u) {
            if (n3 > 0) {
                n2 = 0;
            }
            final int n5;
            n4 = (n5 = n2 + bounds.height);
        }
        if (!u) {
            if (n3 < this.A.height) {
                n2 = this.A.height - bounds.height;
            }
            this.x.setBounds(bounds.x, n2, bounds.width, bounds.height);
            n4 = -n2;
        }
        final double n6 = n4 / (bounds.height - this.A.height);
        n n7 = this;
        Label_0202: {
            if (!u) {
                if (this.z.isVisible()) {
                    this.z.e((int)(n6 * (this.z.getMaximum() - this.z.getMinimum())) + this.z.getMinimum());
                    if (!u) {
                        break Label_0202;
                    }
                }
                n7 = this;
            }
            n7.c();
        }
        final double n8 = dcmpg(n6, 0.0);
        if (u || n8 <= 0) {
            return n8 != 0.0;
        }
        final double n9 = dcmpl(n6, 1.0);
        if (!u && n9 >= 0) {
            goto Label_0226;
        }
        return n9 != 0.0;
    }
    
    public void a(final a a, final int n) {
        final s x = this.x;
        if (!f.u) {
            if (x == null) {
                return;
            }
            final s x2 = this.x;
        }
        x.a(a, n);
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = f.u;
        super.paint(graphics);
        final boolean visible = this.y.isVisible();
        final m z;
        Label_0041: {
            if (!u) {
                if (!visible) {
                    return;
                }
                z = this.z;
                if (u) {
                    break Label_0041;
                }
                z.isVisible();
            }
            if (!visible) {
                return;
            }
            final m y = this.y;
        }
        if (z.h() != null) {
            graphics.drawImage(this.y.h(), this.A.width, this.A.height, this.y.l(), this.y.l(), this);
        }
    }
    
    public void doLayout() {
        final boolean u = f.u;
        super.doLayout();
        final m y = this.y;
        n n = null;
        Label_0031: {
            if (!u) {
                if (y == null) {
                    return;
                }
                n = this;
                if (u) {
                    break Label_0031;
                }
                final m z = this.z;
            }
            if (y == null) {
                return;
            }
            n = this;
        }
        n.a(this.y.getValue(), this.z.getValue());
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource() == this.y) {
            this.a(adjustmentEvent.getValue(), this.z.getValue());
            if (!f.u) {
                return;
            }
        }
        this.a(this.y.getValue(), adjustmentEvent.getValue());
    }
    
    public void a(final int n, final int n2) {
        final boolean u = f.u;
        final s x = this.x;
        if (!u) {
            if (x == null) {
                System.out.println(n.F[1]);
                super.b(this.y);
                this.y.setVisible(false);
                super.b(this.z);
                this.z.setVisible(false);
                this.c();
                return;
            }
            final s x2 = this.x;
        }
        final Dimension m = x.m();
        final Rectangle bounds = this.x.getBounds();
        bounds.width = m.width;
        bounds.height = m.height;
        final Rectangle bounds2 = this.getBounds();
        this.A.setSize(bounds2.width, bounds2.height);
        n n3 = this;
        Label_0192: {
            if (!u) {
                if (this.E && bounds.width > bounds2.width) {
                    super.a(this.y, this.y.k());
                    this.y.setVisible(true);
                    if (!u) {
                        break Label_0192;
                    }
                }
                super.b(this.y);
                n3 = this;
            }
            n3.y.setVisible(false);
        }
        final boolean visible = this.y.isVisible();
        Label_0307: {
            n n4 = null;
            Label_0300: {
                if (!u) {
                    if (visible) {
                        this.A.setSize(this.A.width, this.A.height - this.y.l());
                    }
                    n4 = this;
                    if (u) {
                        break Label_0300;
                    }
                    final boolean e = this.E;
                }
                if (visible && bounds.height > this.A.height) {
                    super.a(this.z, this.z.k());
                    this.z.setVisible(true);
                    if (!u) {
                        break Label_0307;
                    }
                }
                super.b(this.z);
                n4 = this;
            }
            n4.z.setVisible(false);
        }
        int n6;
        int c;
        final int n5 = c = (n6 = (this.z.isVisible() ? 1 : 0));
        if (!u) {
            if (n5 != 0) {
                this.A.setSize(this.A.width - this.z.l(), this.A.height);
            }
            final boolean b;
            c = ((b = ((n6 = (this.y.isVisible() ? 1 : 0)) != 0)) ? 1 : 0);
        }
        int n9 = 0;
        int c2 = 0;
        int n8 = 0;
        Label_0533: {
            Label_0526: {
                if (!u) {
                    if (n5 != 0) {
                        this.y.setBounds(0, this.A.height, this.A.width, this.y.l());
                        bounds.x = -((bounds.width - this.A.width) * ((n - this.y.getMinimum()) / (this.y.getMaximum() - this.y.getMinimum())));
                        if (!u) {
                            break Label_0526;
                        }
                    }
                    n6 = (c = this.C);
                }
                final int n7 = 2;
                if (!u) {
                    if (c == n7) {
                        bounds.x = (this.A.width - bounds.width) / 2;
                        if (!u) {
                            break Label_0526;
                        }
                    }
                    n8 = (n6 = (c2 = (n9 = this.C)));
                    if (u) {
                        break Label_0533;
                    }
                }
                if (n6 == n7) {
                    final int n10 = n8 = (c2 = (n9 = (this.E ? 1 : 0)));
                    if (u) {
                        break Label_0533;
                    }
                    if (n10 != 0) {
                        bounds.x = 0;
                    }
                }
            }
            c2 = (n8 = (n9 = (this.z.isVisible() ? 1 : 0)));
        }
        n n12 = null;
        Label_0711: {
            Label_0701: {
                if (!u) {
                    if (n8 != 0) {
                        this.z.setBounds(this.A.width, 0, this.z.l(), this.A.height);
                        bounds.y = -((bounds.height - this.A.height) * ((n2 - this.z.getMinimum()) / (this.z.getMaximum() - this.z.getMinimum())));
                        if (!u) {
                            break Label_0701;
                        }
                    }
                    n9 = (c2 = this.C);
                }
                final int n11 = 2;
                if (!u) {
                    if (c2 == n11) {
                        bounds.y = (this.A.height - bounds.height) / 2;
                        if (!u) {
                            break Label_0701;
                        }
                    }
                    n12 = this;
                    if (u) {
                        break Label_0711;
                    }
                    n9 = this.C;
                }
                if (n9 == n11) {
                    n12 = this;
                    if (u) {
                        break Label_0711;
                    }
                    if (this.E) {
                        bounds.y = 0;
                    }
                }
            }
            this.x.setBounds(bounds);
            n12 = this;
        }
        n12.c();
    }
    
    public void a(final e e) {
        final boolean u = f.u;
        e e2 = e;
        Label_0156: {
            if (!u) {
                if (!(e instanceof m)) {
                    break Label_0156;
                }
                e2 = e;
            }
            final String name = e2.getName();
            Label_0140: {
                if (!u) {
                    if (name != null && name.equals(n.F[0])) {
                        (this.y = (m)e).a(0, 40, 0, 500);
                        this.y.a(3);
                        super.a(this.y, 3);
                        this.y.addAdjustmentListener(this);
                        if (!u) {
                            break Label_0140;
                        }
                    }
                    (this.z = (m)e).a(0, 40, 0, 500);
                    this.z.a(2);
                    super.a(this.z, 2);
                }
                this.z.addAdjustmentListener(this);
            }
            super.t.addElement(e);
            this.doLayout();
            if (!u) {
                return;
            }
        }
        super.a(e);
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = f.u;
        n n = this;
        if (!u) {
            super.addAttributes(s, s2);
            if (s.equals(com.easypano.tourweaver.b.n.F[2])) {
                this.E = com.easypano.tourweaver.a.e.e(s2);
            }
            n = this;
        }
        final s x = n.x;
        if (!u) {
            if (x == null) {
                return;
            }
            final s x2 = this.x;
        }
        x.b(super.d);
    }
    
    static {
        final String[] f = new String[3];
        final int n = 0;
        final char[] charArray = "\"H\u0000k".toCharArray();
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
                            c2 = 'J';
                            break;
                        }
                        case 1: {
                            c2 = '*';
                            break;
                        }
                        case 2: {
                            c2 = 'a';
                            break;
                        }
                        case 3: {
                            c2 = '\u0019';
                            break;
                        }
                        default: {
                            c2 = '\u0019';
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
        final char[] charArray2 = "#YAWL\u0006f".toCharArray();
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
                            c4 = 'J';
                            break;
                        }
                        case 1: {
                            c4 = '*';
                            break;
                        }
                        case 2: {
                            c4 = 'a';
                            break;
                        }
                        case 3: {
                            c4 = '\u0019';
                            break;
                        }
                        default: {
                            c4 = '\u0019';
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
        final char[] charArray3 = "#Y2qv=y\u0002kv&F\u0003xk".toCharArray();
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
                            c6 = 'J';
                            break;
                        }
                        case 1: {
                            c6 = '*';
                            break;
                        }
                        case 2: {
                            c6 = 'a';
                            break;
                        }
                        case 3: {
                            c6 = '\u0019';
                            break;
                        }
                        default: {
                            c6 = '\u0019';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                f[n9] = new String(charArray3).intern();
                com.easypano.tourweaver.b.n.F = f;
                return;
            }
            continue;
        }
    }
}
