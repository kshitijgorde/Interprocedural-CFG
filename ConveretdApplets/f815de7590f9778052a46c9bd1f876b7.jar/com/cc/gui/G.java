// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import com.cc.B.B;
import java.awt.Polygon;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Insets;
import com.cc.D.D;
import com.cc.D.C;
import com.cc.C.A;
import java.util.Observer;

public class G extends E implements Observer
{
    protected static final int i = 2;
    private H n;
    protected com.cc.B.E s;
    private A h;
    private com.cc.B.A d;
    private C f;
    private boolean r;
    private F[] m;
    private D[] l;
    protected Insets p;
    private int e;
    private int g;
    private int o;
    private int j;
    private int q;
    private Graphics k;
    
    public Point A(final Point point) {
        return new Point(point.x - this.B().x, point.y - this.B().y);
    }
    
    public G(final H n, final com.cc.B.E s, final A h, final com.cc.B.A d, final C f, final boolean r, final int e) {
        super(n.M);
        this.p = new Insets(0, 0, 0, 0);
        this.n = n;
        this.r = r;
        this.s = s;
        this.h = h;
        this.d = d;
        this.e = e;
        this.f = f;
        d.addObserver(this);
        if (d.d) {
            return;
        }
        this.l = f.A(s);
        if (this.l.length > 0) {
            this.m = new F[this.l.length];
            for (int i = 0; i < this.l.length; ++i) {
                final F f2 = new F(this.l[i].F, h.M, e, false, this);
                f2.B(true);
                f2.A(0.5f);
                f2.B((s.U != null) ? s.U : d.o);
                this.m[i] = f2;
            }
        }
    }
    
    public void A(final Rectangle rectangle) {
        super.A(rectangle);
        if (!this.J()) {
            return;
        }
        final int[] array = new int[this.m.length];
        int n = rectangle.height + 1;
        for (int max = 0, max2 = Math.max(6, rectangle.height / 5); max2 >= 4 && (n > rectangle.height || max > rectangle.width); --max2) {
            n = 0;
            max = 0;
            for (int i = 0; i < this.m.length; ++i) {
                this.m[i].C(max2);
                this.m[i].E(rectangle.width);
                final Dimension h = this.m[i].H();
                max = Math.max(h.width, max);
                array[i] = h.height;
                n += array[i];
            }
        }
        int n2 = (rectangle.height - n) / 2;
        for (int j = 0; j < this.m.length; ++j) {
            this.m[j].A(this.h.\u00cb);
            this.m[j].B(0, n2, rectangle.width, (int)(array[j] + 0.99));
            n2 += this.m[j].B().height;
        }
    }
    
    public void C(final Point point) {
        int i = 0;
        while (i < this.m.length) {
            if (this.m[i].B().contains(point)) {
                if (this.l[i].C() != null) {
                    this.f.B(this.l[i]);
                    this.d.B(this.l[i].C());
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        final com.cc.B.F f = (com.cc.B.F)o;
        if ((f.E == 1 || f.E == 5) && this.d.d) {
            return;
        }
        if (this.J() && f.E == 5) {
            for (int i = 0; i < this.l.length; ++i) {
                if (this.f.C() == this.l[i]) {
                    this.m[i].A(this.h.w);
                }
                else {
                    this.m[i].A(this.h.\u00cb);
                }
            }
        }
        else if (f.G.B(this.s) > -1) {
            super.C = false;
            this.E();
        }
    }
    
    protected void C(final Graphics graphics) {
        final int width = this.B().width;
        final int height = this.B().height;
        graphics.setColor(this.I());
        graphics.fillRect(0, 0, width, height);
        if (this.s.I()) {
            this.C(graphics, width, height);
        }
        else if (this.J()) {
            graphics.setColor(this.d._);
            for (int i = 0; i < this.m.length; ++i) {
                final Rectangle b = this.m[i].B();
                this.m[i].B(graphics.create(b.x, b.y, b.width, b.height));
                if (i > 0 && this.d.q != 0.0) {
                    final int n = (int)(width * (1.0 - this.d.q)) / 2;
                    graphics.drawLine(n, b.y, width - n, b.y);
                }
            }
        }
        if (this.s.M() && !this.r && !this.s.U()) {
            boolean drawImage = false;
            while (!drawImage) {
                drawImage = graphics.drawImage(this.s.K(), -1, -1, width + 2, height + 2, graphics.getColor(), (ImageObserver)super.A);
                if (!this.s.B()) {
                    break;
                }
            }
            if (this.s.c) {
                return;
            }
        }
        if (this.s.B()) {
            this.A(graphics, width, height);
        }
        super.C = true;
    }
    
    private void A(final Graphics graphics, final int n, final int n2) {
        if (this.s.K && !this.r) {
            this.B(graphics, n, n2);
        }
        if (!this.h.µ && !this.r) {
            this.A(graphics, n);
        }
        if (!this.s.S() || this.s.I) {
            this.E(graphics);
        }
        if (!this.r) {
            this.F(graphics);
        }
    }
    
    protected void B(final Graphics graphics, final int n, final int n2) {
        graphics.setColor((this.s.U == null) ? this.d._ : this.s.U);
        if (this.s.W()) {
            graphics.drawArc(0, 0, n, n2, 160, 320);
            if (com.cc.gui.E.G == null) {
                graphics.drawArc(1, 1, n - 2, n2 - 2, 160, 320);
                graphics.drawArc(1, 0, n - 2, n2, 160, 320);
                graphics.drawArc(0, 1, n, n2 - 2, 160, 320);
            }
        }
        else {
            graphics.drawOval(0, 0, n, n2);
            if (com.cc.gui.E.G == null) {
                graphics.drawOval(1, 1, n - 2, n2 - 2);
                graphics.drawOval(1, 0, n - 2, n2);
                graphics.drawOval(0, 1, n, n2 - 2);
            }
        }
    }
    
    private void E(final Graphics graphics) {
        final Rectangle b = this.B();
        if (this.s.W) {
            if (this.s.U != null && Color.red.equals(this.s.U)) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.red);
            }
            graphics.drawLine(0, 0, b.width, b.width);
            graphics.drawLine(b.width, 0, 0, b.width);
        }
        if (this.s.C()) {
            graphics.setFont(new Font("SansSerif", 2, this.n.J()));
            graphics.setColor((this.s.O == null) ? this.d.v : this.s.O.brighter());
        }
        else {
            graphics.setFont(new Font("SansSerif", 1, this.n.J()));
            if (this.s.E() && this.d.s != this.d.e) {
                graphics.setColor((this.s.O == null) ? this.d.s : this.s.O.brighter());
            }
            else {
                graphics.setColor((this.s.O == null) ? this.d.e : this.s.O);
            }
        }
        if (this.d.µ && !this.s.H()) {
            graphics.setColor(this.d.E);
        }
        final String l = this.s.L();
        if (l.length() == 1) {
            graphics.drawString(l, (this.e - graphics.getFontMetrics().charWidth(l.charAt(0))) / 2, this.G(graphics) - 1 - this.p.top);
        }
        else {
            while (this.h.A(graphics, l) >= b.width) {
                graphics.setFont(new Font(graphics.getFont().getName(), graphics.getFont().getStyle(), graphics.getFont().getSize() - 1));
            }
            graphics.drawString(l, (b.width - this.h.A(graphics, l) + 1) / 2, this.G(graphics) - 1 - this.p.top);
        }
    }
    
    private int G(final Graphics graphics) {
        int n;
        if (this.d.p == 2 || this.r) {
            n = this.e - (this.e - graphics.getFontMetrics().getAscent()) / 2;
        }
        else {
            n = this.e - (graphics.getFontMetrics().getDescent() + 2) / 2;
        }
        return n;
    }
    
    protected void C(final Graphics graphics, final int n, final int n2) {
        graphics.setColor((this.s.U == null) ? this.d.w : this.s.U);
        final int n3 = this.d.J ? 2 : 0;
        graphics.fillRect(n3, n3, n - n3 * 2, n2 - n3 * 2);
    }
    
    protected Color I() {
        if (this.s.B() && !this.r && !this.d.d && !this.n.N) {
            if (this.s.U() && !this.d.d) {
                return this.d.a;
            }
            if (this.d.M().A(this.s)) {
                return this.d.d ? this.d.a : this.d.X;
            }
        }
        if (this.s.U != null && !this.s.K) {
            return this.s.U;
        }
        return this.d.o;
    }
    
    public boolean J() {
        return this.m != null;
    }
    
    protected void A(final Graphics graphics, final int n) {
        graphics.setColor(this.d.y);
        graphics.setFont(new Font("SansSerif", 0, this.n.E()));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n2 = fontMetrics.getHeight() - fontMetrics.getDescent() - this.p.top;
        if (this.s.W()) {
            graphics.drawString(this.s.B, 2, n2);
        }
        if (this.s.P != null) {
            graphics.drawString(this.s.P, n - 2 - fontMetrics.stringWidth(this.s.P), n2);
        }
    }
    
    private Point A(final Point point, final int n) {
        final double n2 = point.x * n;
        final double n3 = point.y * n;
        final double n4 = Math.sqrt(n2 * n2 + n3 * n3) / n;
        return new Point((int)Math.round(n2 / n4), (int)Math.round(n3 / n4));
    }
    
    private void B(final int n, final int n2, final boolean b) {
        final int n3 = n2 - (b ? this.j : this.q);
        this.k.drawLine(n, n2, n, n3);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n - this.o, n3);
        polygon.addPoint(n + this.o, n3);
        polygon.addPoint(n, n3 - this.g);
        this.k.fillPolygon(polygon);
        this.k.drawPolygon(polygon);
    }
    
    private void A(final int n, final int n2, final boolean b) {
        final int n3 = n - (b ? this.j : this.q);
        this.k.drawLine(n, n2, n3, n2);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n3, n2 - this.o);
        polygon.addPoint(n3, n2 + this.o);
        polygon.addPoint(n3 - this.g, n2);
        this.k.fillPolygon(polygon);
        this.k.drawPolygon(polygon);
    }
    
    private void D(final int n, final int n2, final boolean b) {
        final int n3 = n2 + (b ? this.j : this.q);
        this.k.drawLine(n, n2, n, n3);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n - this.o, n3);
        polygon.addPoint(n + this.o, n3);
        polygon.addPoint(n, n3 + this.g);
        this.k.fillPolygon(polygon);
        this.k.drawPolygon(polygon);
    }
    
    private void C(final int n, final int n2, final boolean b) {
        final int n3 = n + (b ? this.j : this.q);
        this.k.drawLine(n, n2, n3, n2);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n3, n2 - this.o);
        polygon.addPoint(n3, n2 + this.o);
        polygon.addPoint(n3 + this.g, n2);
        this.k.fillPolygon(polygon);
        this.k.drawPolygon(polygon);
    }
    
    private void F(final Graphics k) {
        k.setColor(this.d._);
        this.k = k;
        final int height = this.B().height;
        final int width;
        final int n = width = this.B().width;
        final int n2 = height;
        final int n3 = 0;
        final int n4 = 0;
        this.q = (int)(this.d.t * n);
        this.o = Math.max(1, (int)(this.d.W / 2.0 * n));
        this.g = Math.max(2, (int)(this.d.K * n));
        final int n5 = (int)(this.d.S * n);
        this.j = (int)(this.d.g * n);
        final int n6 = (int)(this.d.Q * n);
        for (int i = 0; i < this.s.J().length; ++i) {
            final B b = this.s.J()[i];
            final int a = b.A();
            final int c = b.C();
            final double b2 = b.B();
            if (b.D) {
                if (a == 2 && c == 1) {
                    final int n7 = n3 + n6;
                    k.drawLine(n7, n2 - n6 - this.q, n7, n2 - n6);
                    this.C(n7, n2 - n6, false);
                }
                else if (a == 0 && c == 3) {
                    final int n8 = width - n6;
                    k.drawLine(n8 - this.q, n4 + n6, n8, n4 + n6);
                    this.D(n8, n4 + n6, false);
                }
            }
            else {
                if (a == 3) {
                    if (c == 2) {
                        this.B((n3 + width) / 2, n2, false);
                    }
                    else if (c == 0) {
                        final int n9 = width - n6;
                        k.drawLine(n9, n2, n9, n2 - n5);
                        this.A(n9, n2 - n5, true);
                    }
                    else if (c == 1) {
                        final int n10 = n3 + n6;
                        k.drawLine(n10, n2, n10, n2 - n5);
                        this.C(n10, n2 - n5, true);
                    }
                }
                if (a == 2) {
                    if (c == 3) {
                        this.D((n3 + width) / 2, n4, false);
                    }
                    else if (c == 0) {
                        final int n11 = width - n6;
                        k.drawLine(n11, n4, n11, n4 + n5);
                        this.A(n11, n4 + n5, true);
                    }
                    else if (c == 1) {
                        final int n12 = n3 + n6;
                        k.drawLine(n12, n4, n12, n4 + n5);
                        this.C(n12, n4 + n5, true);
                    }
                }
                if (a == 0) {
                    if (c == 1) {
                        this.C(n3, (int)(n4 + height * b2), false);
                    }
                    else if (c == 2) {
                        final int n13 = n2 - n6;
                        k.drawLine(n3, n13, n3 + n5, n13);
                        this.B(n3 + n5, n13, true);
                    }
                    else if (c == 3) {
                        final int n14 = n4 + n6;
                        k.drawLine(n3, n14, n3 + n5, n14);
                        this.D(n3 + n5, n14, true);
                    }
                }
                if (a == 1) {
                    if (c == 0) {
                        this.A(width, (int)(n4 + height * b2), false);
                    }
                    else if (c == 2) {
                        final int n15 = n2 - n6;
                        k.drawLine(width, n15, width - n5, n15);
                        this.B(width - n5, n15, true);
                    }
                    else if (c == 3) {
                        final int n16 = n4 + n6;
                        k.drawLine(width, n16, width - n5, n16);
                        this.D(width - n5, n16, true);
                    }
                }
            }
        }
    }
}
