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
    protected static final int e = 2;
    private H j;
    protected com.cc.B.E o;
    private A d;
    private com.cc.B.A _;
    private C b;
    private boolean n;
    private F[] i;
    private D[] h;
    protected Insets l;
    private int a;
    private int c;
    private int k;
    private int f;
    private int m;
    private Graphics g;
    
    public Point A(final Point point) {
        return new Point(point.x - this.B().x, point.y - this.B().y);
    }
    
    public G(final H j, final com.cc.B.E o, final A d, final com.cc.B.A _, final C b, final boolean n, final int a) {
        super(j.M);
        this.l = new Insets(0, 0, 0, 0);
        this.j = j;
        this.n = n;
        this.o = o;
        this.d = d;
        this._ = _;
        this.a = a;
        this.b = b;
        _.addObserver(this);
        if (_.d) {
            return;
        }
        this.h = b.A(o);
        if (this.h.length > 0) {
            this.i = new F[this.h.length];
            for (int i = 0; i < this.h.length; ++i) {
                final F f = new F(this.h[i].F, d.L, a, false, this);
                f.B(true);
                f.A(0.5f);
                f.B((o.U != null) ? o.U : _.o);
                this.i[i] = f;
            }
        }
    }
    
    public void A(final Rectangle rectangle) {
        super.A(rectangle);
        if (!this.J()) {
            return;
        }
        final int[] array = new int[this.i.length];
        int n = rectangle.height + 1;
        for (int max = 0, max2 = Math.max(6, rectangle.height / 5); max2 >= 4 && (n > rectangle.height || max > rectangle.width); --max2) {
            n = 0;
            max = 0;
            for (int i = 0; i < this.i.length; ++i) {
                this.i[i].C(max2);
                this.i[i].E(rectangle.width);
                final Dimension h = this.i[i].H();
                max = Math.max(h.width, max);
                array[i] = h.height;
                n += array[i];
            }
        }
        int n2 = (rectangle.height - n) / 2;
        for (int j = 0; j < this.i.length; ++j) {
            this.i[j].A(this.d.\u00c9);
            this.i[j].B(0, n2, rectangle.width, (int)(array[j] + 0.99));
            n2 += this.i[j].B().height;
        }
    }
    
    public void C(final Point point) {
        int i = 0;
        while (i < this.i.length) {
            if (this.i[i].B().contains(point)) {
                if (this.h[i].C() != null) {
                    this.b.B(this.h[i]);
                    this._.B(this.h[i].C());
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
        if ((f.E == 1 || f.E == 5) && this._.d) {
            return;
        }
        if (this.J() && f.E == 5) {
            for (int i = 0; i < this.h.length; ++i) {
                if (this.b.C() == this.h[i]) {
                    this.i[i].A(this.d.u);
                }
                else {
                    this.i[i].A(this.d.\u00c9);
                }
            }
        }
        else if (f.G.B(this.o) > -1) {
            super.C = false;
            this.E();
        }
    }
    
    protected void C(final Graphics graphics) {
        final int width = this.B().width;
        final int height = this.B().height;
        graphics.setColor(this.I());
        graphics.fillRect(0, 0, width, height);
        if (this.o.I()) {
            this.C(graphics, width, height);
        }
        else if (this.J()) {
            graphics.setColor(this._._);
            for (int i = 0; i < this.i.length; ++i) {
                final Rectangle b = this.i[i].B();
                this.i[i].B(graphics.create(b.x, b.y, b.width, b.height));
                if (i > 0 && this._.q != 0.0) {
                    final int n = (int)(width * (1.0 - this._.q)) / 2;
                    graphics.drawLine(n, b.y, width - n, b.y);
                }
            }
        }
        if (this.o.M() && !this.n && !this.o.U()) {
            boolean drawImage = false;
            while (!drawImage) {
                drawImage = graphics.drawImage(this.o.K(), -1, -1, width + 2, height + 2, graphics.getColor(), (ImageObserver)super.A);
                if (!this.o.B()) {
                    break;
                }
            }
            if (this.o.c) {
                return;
            }
        }
        if (this.o.B()) {
            this.A(graphics, width, height);
        }
        super.C = true;
    }
    
    private void A(final Graphics graphics, final int n, final int n2) {
        if (this.o.K && !this.n) {
            this.B(graphics, n, n2);
        }
        if (!this.d.¥ && !this.n) {
            this.A(graphics, n);
        }
        if (!this.o.S() || this.o.I) {
            this.D(graphics);
        }
        if (!this.n) {
            this.E(graphics);
        }
    }
    
    protected void B(final Graphics graphics, final int n, final int n2) {
        graphics.setColor((this.o.U == null) ? this._._ : this.o.U);
        if (this.o.W()) {
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
    
    private void D(final Graphics graphics) {
        final Rectangle b = this.B();
        if (this.o.W) {
            if (this.o.U != null && Color.red.equals(this.o.U)) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.red);
            }
            graphics.drawLine(0, 0, b.width, b.width);
            graphics.drawLine(b.width, 0, 0, b.width);
        }
        if (this.o.C()) {
            graphics.setFont(new Font("SansSerif", 2, this.j.J()));
            graphics.setColor((this.o.O == null) ? this._.u : this.o.O.brighter());
        }
        else {
            graphics.setFont(new Font("SansSerif", 1, this.j.J()));
            graphics.setColor((this.o.O == null) ? this._.e : this.o.O);
        }
        if (this._.ª && !this.o.H()) {
            graphics.setColor(this._.E);
        }
        final String l = this.o.L();
        if (l.length() == 1) {
            graphics.drawString(l, (this.a - graphics.getFontMetrics().charWidth(l.charAt(0))) / 2, this.F(graphics) - 1 - this.l.top);
        }
        else {
            while (this.d.A(graphics, l) >= b.width) {
                graphics.setFont(new Font(graphics.getFont().getName(), graphics.getFont().getStyle(), graphics.getFont().getSize() - 1));
            }
            graphics.drawString(l, (b.width - this.d.A(graphics, l) + 1) / 2, this.F(graphics) - 1 - this.l.top);
        }
    }
    
    private int F(final Graphics graphics) {
        int n;
        if (this._.p == 2 || this.n) {
            n = this.a - (this.a - graphics.getFontMetrics().getAscent()) / 2;
        }
        else {
            n = this.a - (graphics.getFontMetrics().getDescent() + 2) / 2;
        }
        return n;
    }
    
    protected void C(final Graphics graphics, final int n, final int n2) {
        graphics.setColor((this.o.U == null) ? this._.v : this.o.U);
        final int n3 = this._.J ? 2 : 0;
        graphics.fillRect(n3, n3, n - n3 * 2, n2 - n3 * 2);
    }
    
    protected Color I() {
        if (this.o.B() && !this.n && !this._.d && !this.j.N) {
            if (this.o.U() && !this._.d) {
                return this._.a;
            }
            if (this._.M().A(this.o)) {
                return this._.d ? this._.a : this._.X;
            }
        }
        if (this.o.U != null && !this.o.K) {
            return this.o.U;
        }
        return this._.o;
    }
    
    public boolean J() {
        return this.i != null;
    }
    
    protected void A(final Graphics graphics, final int n) {
        graphics.setColor(this._.x);
        graphics.setFont(new Font("SansSerif", 0, this.j.E()));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n2 = fontMetrics.getHeight() - fontMetrics.getDescent() - this.l.top;
        if (this.o.W()) {
            graphics.drawString(this.o.B, 2, n2);
        }
        if (this.o.P != null) {
            graphics.drawString(this.o.P, n - 2 - fontMetrics.stringWidth(this.o.P), n2);
        }
    }
    
    private Point A(final Point point, final int n) {
        final double n2 = point.x * n;
        final double n3 = point.y * n;
        final double n4 = Math.sqrt(n2 * n2 + n3 * n3) / n;
        return new Point((int)Math.round(n2 / n4), (int)Math.round(n3 / n4));
    }
    
    private void B(final int n, final int n2, final boolean b) {
        final int n3 = n2 - (b ? this.f : this.m);
        this.g.drawLine(n, n2, n, n3);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n - this.k, n3);
        polygon.addPoint(n + this.k, n3);
        polygon.addPoint(n, n3 - this.c);
        this.g.fillPolygon(polygon);
        this.g.drawPolygon(polygon);
    }
    
    private void A(final int n, final int n2, final boolean b) {
        final int n3 = n - (b ? this.f : this.m);
        this.g.drawLine(n, n2, n3, n2);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n3, n2 - this.k);
        polygon.addPoint(n3, n2 + this.k);
        polygon.addPoint(n3 - this.c, n2);
        this.g.fillPolygon(polygon);
        this.g.drawPolygon(polygon);
    }
    
    private void D(final int n, final int n2, final boolean b) {
        final int n3 = n2 + (b ? this.f : this.m);
        this.g.drawLine(n, n2, n, n3);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n - this.k, n3);
        polygon.addPoint(n + this.k, n3);
        polygon.addPoint(n, n3 + this.c);
        this.g.fillPolygon(polygon);
        this.g.drawPolygon(polygon);
    }
    
    private void C(final int n, final int n2, final boolean b) {
        final int n3 = n + (b ? this.f : this.m);
        this.g.drawLine(n, n2, n3, n2);
        final Polygon polygon = new Polygon();
        polygon.addPoint(n3, n2 - this.k);
        polygon.addPoint(n3, n2 + this.k);
        polygon.addPoint(n3 + this.c, n2);
        this.g.fillPolygon(polygon);
        this.g.drawPolygon(polygon);
    }
    
    private void E(final Graphics g) {
        g.setColor(this._._);
        this.g = g;
        final int height = this.B().height;
        final int width;
        final int n = width = this.B().width;
        final int n2 = height;
        final int n3 = 0;
        final int n4 = 0;
        this.m = (int)(this._.s * n);
        this.k = Math.max(1, (int)(this._.W / 2.0 * n));
        this.c = Math.max(2, (int)(this._.K * n));
        final int n5 = (int)(this._.S * n);
        this.f = (int)(this._.g * n);
        final int n6 = (int)(this._.Q * n);
        for (int i = 0; i < this.o.J().length; ++i) {
            final B b = this.o.J()[i];
            final int a = b.A();
            final int c = b.C();
            final double b2 = b.B();
            if (b.D) {
                if (a == 2 && c == 1) {
                    final int n7 = n3 + n6;
                    g.drawLine(n7, n2 - n6 - this.m, n7, n2 - n6);
                    this.C(n7, n2 - n6, false);
                }
                else if (a == 0 && c == 3) {
                    final int n8 = width - n6;
                    g.drawLine(n8 - this.m, n4 + n6, n8, n4 + n6);
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
                        g.drawLine(n9, n2, n9, n2 - n5);
                        this.A(n9, n2 - n5, true);
                    }
                    else if (c == 1) {
                        final int n10 = n3 + n6;
                        g.drawLine(n10, n2, n10, n2 - n5);
                        this.C(n10, n2 - n5, true);
                    }
                }
                if (a == 2) {
                    if (c == 3) {
                        this.D((n3 + width) / 2, n4, false);
                    }
                    else if (c == 0) {
                        final int n11 = width - n6;
                        g.drawLine(n11, n4, n11, n4 + n5);
                        this.A(n11, n4 + n5, true);
                    }
                    else if (c == 1) {
                        final int n12 = n3 + n6;
                        g.drawLine(n12, n4, n12, n4 + n5);
                        this.C(n12, n4 + n5, true);
                    }
                }
                if (a == 0) {
                    if (c == 1) {
                        this.C(n3, (int)(n4 + height * b2), false);
                    }
                    else if (c == 2) {
                        final int n13 = n2 - n6;
                        g.drawLine(n3, n13, n3 + n5, n13);
                        this.B(n3 + n5, n13, true);
                    }
                    else if (c == 3) {
                        final int n14 = n4 + n6;
                        g.drawLine(n3, n14, n3 + n5, n14);
                        this.D(n3 + n5, n14, true);
                    }
                }
                if (a == 1) {
                    if (c == 0) {
                        this.A(width, (int)(n4 + height * b2), false);
                    }
                    else if (c == 2) {
                        final int n15 = n2 - n6;
                        g.drawLine(width, n15, width - n5, n15);
                        this.B(width - n5, n15, true);
                    }
                    else if (c == 3) {
                        final int n16 = n4 + n6;
                        g.drawLine(width, n16, width - n5, n16);
                        this.D(width - n5, n16, true);
                    }
                }
            }
        }
    }
}
