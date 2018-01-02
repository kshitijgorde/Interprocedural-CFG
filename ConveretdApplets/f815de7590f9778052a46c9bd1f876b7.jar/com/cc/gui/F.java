// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Component;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import com.cc.D.A;

public class F extends E
{
    public static final float R = 0.0f;
    public static final float Q = 1.0f;
    public static final float a = 0.5f;
    public static final int H = 4;
    protected static final int W = 2;
    protected A O;
    protected Font S;
    protected int V;
    protected Color J;
    protected int Z;
    protected boolean X;
    protected boolean I;
    protected boolean U;
    protected float T;
    protected A._A N;
    protected int K;
    protected boolean L;
    protected Point Y;
    public int c;
    private Component b;
    public boolean M;
    private Image _;
    protected boolean P;
    
    public F(final A o, final Font s, final int z, final boolean b, final E e) {
        super(e);
        this.X = false;
        this.I = false;
        this.U = false;
        this.T = 0.0f;
        this.N = new A._A("");
        this.K = 2;
        this.L = true;
        this.Y = new Point();
        this.c = 0;
        this.b = null;
        this.M = false;
        this._ = null;
        this.P = false;
        this.S = s;
        this.Z = z;
        if (b) {
            this.O = o.A(" ", true);
        }
        else {
            this.O = o;
        }
    }
    
    public F(final A o, final Font s, final int z, final boolean b, final Component b2) {
        super(b2);
        this.X = false;
        this.I = false;
        this.U = false;
        this.T = 0.0f;
        this.N = new A._A("");
        this.K = 2;
        this.L = true;
        this.Y = new Point();
        this.c = 0;
        this.b = null;
        this.M = false;
        this._ = null;
        this.P = false;
        this.S = s;
        this.Z = z;
        if (b) {
            this.O = o.A(" ", true);
        }
        else {
            this.O = o;
        }
        this.b = b2;
    }
    
    public F(final A a, final Component component, final int n, final boolean b) {
        this(a, component.getFont(), n, b, component);
    }
    
    public F(final Component component) {
        super(component);
        this.X = false;
        this.I = false;
        this.U = false;
        this.T = 0.0f;
        this.N = new A._A("");
        this.K = 2;
        this.L = true;
        this.Y = new Point();
        this.c = 0;
        this.b = null;
        this.M = false;
        this._ = null;
        this.P = false;
        this.U = false;
    }
    
    public void C(final int n) {
        this.S = new Font(this.S.getName(), this.S.getStyle(), n);
    }
    
    public boolean F() {
        if (this.S.getSize() <= 4) {
            return false;
        }
        this.C(this.S.getSize() - 1);
        return true;
    }
    
    public Dimension H() {
        if (this.O.B().length == 0) {
            return new Dimension(0, 0);
        }
        this.A(this.Z);
        return new Dimension(this.V, this.O.C()[this.O.C().length - 1].A.y + 4);
    }
    
    public void C(final boolean m) {
        this.M = m;
    }
    
    public void C(final Graphics graphics) {
        if (this.M && this.b != null && this._ == null) {
            this._ = this.b.createImage(this.B().width, this.B().height);
        }
        if (this._ != null) {
            final Graphics graphics2 = this._.getGraphics();
            this.D(graphics2);
            graphics.drawImage(this._, 0, 0, this.b);
            graphics2.dispose();
        }
        else {
            this.D(graphics);
        }
    }
    
    public void D(final Graphics graphics) {
        graphics.setColor(super.E);
        graphics.fillRect(0, 0, super.D.width, super.D.height);
        if (this.O.B().length == 0) {
            return;
        }
        this.A(super.D.width);
        final Rectangle clipBounds = graphics.getClipBounds();
        int n;
        if (clipBounds != null) {
            n = clipBounds.y + clipBounds.height;
        }
        else {
            n = 1048576;
        }
        graphics.setColor(super.F);
        final A._B[] b = this.O.B();
        for (int i = 0; i < b.length; ++i) {
            final A._A a = b[i].A(0);
            if (a.A.y < n || !this.U) {
                this.A(graphics, b[i]);
            }
            else if (i > 0 && a.A().length() > 0 && !a.A().equals(" ")) {
                graphics.drawString(" ...", b[i].A(0).A.x, b[i - 1].A(0).A.y + 4);
                break;
            }
        }
        final int max = Math.max(this.B(this.N), this.K);
        if (this.N.A().length() > 0 && (b[0].A(0).A.y < n || !this.U)) {
            graphics.setFont(this.A(this.N));
            graphics.drawString(this.N.A(), this.L ? Math.max(2, max - this.B(this.N)) : 2, graphics.getFontMetrics().getHeight() + this.Y.y);
        }
        if (this.J != null) {
            graphics.setColor(this.J);
            graphics.drawRect(0, 0, super.D.width - 1, super.D.height - 1);
        }
    }
    
    protected void A(final Graphics graphics, final A._B b) {
        for (int i = 0; i < b.A.A(); ++i) {
            final A._A a = b.A(i);
            graphics.setFont(this.A(a));
            graphics.drawString(a.A(), a.A.x, a.A.y);
        }
    }
    
    public void D(final boolean p) {
        this.P = p;
        this.E();
    }
    
    private FontMetrics A(final Font font) {
        if (super.A instanceof Component) {
            return ((Component)super.A).getFontMetrics(font);
        }
        return Toolkit.getDefaultToolkit().getFontMetrics(font);
    }
    
    protected int A(final A._B b) {
        int x = 0;
        for (int i = 0; i < b.A.A(); ++i) {
            final A._A a = b.A(i);
            final FontMetrics a2 = this.A(this.A(a));
            a.A.x = x;
            a.A.y = 0;
            x += a2.stringWidth(a.A());
        }
        return x;
    }
    
    protected void A(int n) {
        n -= 2;
        final int height = this.A(this.S).getHeight();
        int n2 = height + this.Y.y;
        if (this.P) {
            n2 -= 5;
        }
        int n3 = Math.max(this.B(this.N), this.K) + this.Y.x;
        final A._B[] b = this.O.B();
        this.V = 0;
        for (int i = 0; i < b.length; ++i) {
            final A._B b2 = b[i];
            final int a = this.A(b2);
            if (a + n3 > n && !this.I) {
                n2 += height;
                n3 = this.K;
                if (b2.A(0).A().startsWith(" ")) {
                    n3 -= this.A(this.A(b2.A(0))).charWidth(' ');
                }
            }
            for (int j = 0; j < b[i].A.A(); ++j) {
                final Point a2 = b2.A(j).A;
                a2.x += n3;
                final Point a3 = b2.A(j).A;
                a3.y += n2;
            }
            n3 += a;
            this.V = Math.max(this.V, n3);
            if (this.I) {
                n3 = this.K;
                n2 += height;
            }
        }
        if (this.T != 0.0f) {
            this.D(n);
        }
        final A._A[] c = this.O.C();
        for (int k = 0; k < c.length; ++k) {
            final A._A a4 = c[k];
            if (a4.B() == com.cc.D.A._A.H) {
                this.O.C()[k].A.y += height / 4;
            }
            else if (a4.B() == com.cc.D.A._A.B) {
                this.O.C()[k].A.y -= height / 4;
            }
        }
    }
    
    protected void D(final int n) {
        if (this.O.B().length == 0) {
            return;
        }
        int n2 = this.O.C()[0].A.y;
        int n3 = 0;
        final A._A[] c = this.O.C();
        for (int i = 1; i < c.length; ++i) {
            if (n2 < this.O.C()[i].A.y) {
                this.A(n3, i - 1, n, this.T);
                n3 = i;
                n2 = this.O.C()[i].A.y;
            }
        }
        this.A(n3, c.length - 1, n, this.T);
    }
    
    protected void A(final int n, final int n2, final int n3, final float n4) {
        int n5 = 0;
        final A._A[] c = this.O.C();
        for (int i = n; i <= n2; ++i) {
            final A._A a = c[i];
            n5 += this.A(this.A(a)).stringWidth(a.A());
        }
        final int n6 = (int)((n3 - n5) * n4);
        for (int j = n; j <= n2; ++j) {
            final Point a2 = this.O.C()[j].A;
            a2.x += n6;
        }
    }
    
    public void A(final float t) {
        if (this.T != t) {
            this.T = t;
            this.E();
        }
    }
    
    public String toString() {
        return "Label " + this.B() + ": " + this.O;
    }
    
    public void E(final int z) {
        this.Z = z;
    }
    
    public void C(final Color j) {
        if (this.J == null && j == null) {
            return;
        }
        if (this.J != null && this.J.equals(j)) {
            return;
        }
        this.J = j;
        this.E();
    }
    
    public void B(final boolean i) {
        this.I = i;
    }
    
    public void A(final boolean u) {
        this.U = u;
    }
    
    public void A(final Rectangle rectangle) {
        super.A(rectangle);
        while ((this.H().height > rectangle.height || this.H().width > rectangle.width) && this.S.getSize() > 6) {
            this.S = new Font(this.S.getName(), this.S.getStyle(), this.S.getSize() - 1);
        }
    }
    
    public void B(final int n) {
        this.K = n + 2;
    }
    
    public void A(final A._A n, final boolean l) {
        this.N = n;
        this.L = l;
    }
    
    public int B(final A._A a) {
        return this.A(this.A(a)).stringWidth(a.A());
    }
    
    protected Font A(final A._A a) {
        Font s;
        if (a.C() && a.D()) {
            s = new Font(this.S.getName(), 3, this.S.getSize());
        }
        else if (a.C()) {
            s = new Font(this.S.getName(), 1, this.S.getSize());
        }
        else if (a.D()) {
            s = new Font(this.S.getName(), 2, this.S.getSize());
        }
        else {
            s = this.S;
        }
        if (a.B() == com.cc.D.A._A.H || a.B() == com.cc.D.A._A.B) {
            s = new Font(s.getName(), s.getStyle(), Math.max(10, s.getSize() * 2 / 3));
        }
        return s;
    }
    
    public void B(final Point y) {
        this.Y = y;
    }
    
    public A G() {
        return this.O;
    }
}
