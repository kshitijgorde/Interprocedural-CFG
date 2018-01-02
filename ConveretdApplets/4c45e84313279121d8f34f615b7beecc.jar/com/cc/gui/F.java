// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import com.cc.D.A;

public class F extends E
{
    public static final float Q = 0.0f;
    public static final float P = 1.0f;
    public static final float Y = 0.5f;
    public static final int H = 4;
    protected static final int V = 2;
    protected A N;
    protected Font R;
    protected int U;
    protected Color J;
    protected int X;
    protected boolean I;
    protected boolean T;
    protected float S;
    protected A._A M;
    protected int K;
    protected boolean L;
    protected Point W;
    public int Z;
    protected boolean O;
    
    public F(final A n, final Font r, final int x, final boolean b, final E e) {
        super(e);
        this.I = false;
        this.T = false;
        this.S = 0.0f;
        this.M = new A._A("");
        this.K = 2;
        this.L = true;
        this.W = new Point();
        this.Z = 0;
        this.O = false;
        this.R = r;
        this.X = x;
        if (b) {
            this.N = n.A(" ", true);
        }
        else {
            this.N = n;
        }
    }
    
    public F(final A n, final Font r, final int x, final boolean b, final Component component) {
        super(component);
        this.I = false;
        this.T = false;
        this.S = 0.0f;
        this.M = new A._A("");
        this.K = 2;
        this.L = true;
        this.W = new Point();
        this.Z = 0;
        this.O = false;
        this.R = r;
        this.X = x;
        if (b) {
            this.N = n.A(" ", true);
        }
        else {
            this.N = n;
        }
    }
    
    public F(final A a, final Component component, final int n, final boolean b) {
        this(a, component.getFont(), n, b, component);
    }
    
    public F(final Component component) {
        super(component);
        this.I = false;
        this.T = false;
        this.S = 0.0f;
        this.M = new A._A("");
        this.K = 2;
        this.L = true;
        this.W = new Point();
        this.Z = 0;
        this.O = false;
        this.T = false;
    }
    
    public void C(final int n) {
        this.R = new Font(this.R.getName(), this.R.getStyle(), n);
    }
    
    public boolean F() {
        if (this.R.getSize() <= 4) {
            return false;
        }
        this.C(this.R.getSize() - 1);
        return true;
    }
    
    public Dimension H() {
        if (this.N.B().length == 0) {
            return new Dimension(0, 0);
        }
        this.A(this.X);
        return new Dimension(this.U, this.N.C()[this.N.C().length - 1].A.y + 4);
    }
    
    public void C(final Graphics graphics) {
        graphics.setColor(super.E);
        graphics.fillRect(0, 0, this.B().width, this.B().height);
        if (this.N.B().length == 0) {
            return;
        }
        this.A(this.B().width);
        final Rectangle clipBounds = graphics.getClipBounds();
        final int n = clipBounds.y + clipBounds.height;
        graphics.setColor(super.F);
        final A._B[] b = this.N.B();
        for (int i = 0; i < b.length; ++i) {
            final A._A a = b[i].A(0);
            if (a.A.y < n || !this.T) {
                this.A(graphics, b[i]);
            }
            else if (i > 0 && a.A().length() > 0 && !a.A().equals(" ")) {
                graphics.drawString(" ...", b[i].A(0).A.x, b[i - 1].A(0).A.y + 4);
                break;
            }
        }
        final int max = Math.max(this.B(this.M), this.K);
        if (this.M.A().length() > 0 && (b[0].A(0).A.y < n || !this.T)) {
            graphics.setFont(this.A(this.M));
            graphics.drawString(this.M.A(), this.L ? Math.max(2, max - this.B(this.M)) : 2, graphics.getFontMetrics().getHeight() + this.W.y);
        }
        if (this.J != null) {
            graphics.setColor(this.J);
            graphics.drawRect(0, 0, this.B().width - 1, this.B().height - 1);
        }
    }
    
    protected void A(final Graphics graphics, final A._B b) {
        this.A(graphics, b, false);
    }
    
    protected void A(final Graphics graphics, final A._B b, final boolean b2) {
        for (int i = 0; i < b.A.A(); ++i) {
            final A._A a = b.A(i);
            graphics.setFont(this.A(a));
            if (b2) {
                final Font font = graphics.getFont();
                graphics.setFont(new Font(font.getName(), font.getStyle(), font.getSize() / 2));
                graphics.drawString(a.A(), a.A.x, a.A.y);
            }
            else {
                graphics.drawString(a.A(), a.A.x, a.A.y);
            }
        }
    }
    
    public void C(final boolean o) {
        this.O = o;
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
        int height = this.A(this.R).getHeight();
        int n2 = height + this.W.y;
        if (this.O) {
            height += 0;
            n2 -= 5;
        }
        int n3 = Math.max(this.B(this.M), this.K) + this.W.x;
        final A._B[] b = this.N.B();
        this.U = 0;
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
            this.U = Math.max(this.U, n3);
            if (this.I) {
                n3 = this.K;
                n2 += height;
            }
        }
        if (this.S != 0.0f) {
            this.D(n);
        }
        final A._A[] c = this.N.C();
        for (int k = 0; k < c.length; ++k) {
            final A._A a4 = c[k];
            if (a4.B() == com.cc.D.A._A.H) {
                this.N.C()[k].A.y += height / 4;
            }
            else if (a4.B() == com.cc.D.A._A.B) {
                this.N.C()[k].A.y -= height / 4;
            }
        }
    }
    
    protected void D(final int n) {
        if (this.N.B().length == 0) {
            return;
        }
        int n2 = this.N.C()[0].A.y;
        int n3 = 0;
        final A._A[] c = this.N.C();
        final int x = c[0].A.x;
        for (int i = 1; i < c.length; ++i) {
            if (n2 < this.N.C()[i].A.y) {
                this.A(n3, i - 1, n, this.S);
                n3 = i;
                n2 = this.N.C()[i].A.y;
            }
        }
        this.A(n3, c.length - 1, n, this.S);
    }
    
    protected void A(final int n, final int n2, final int n3, final float n4) {
        int n5 = 0;
        final A._A[] c = this.N.C();
        for (int i = n; i <= n2; ++i) {
            final A._A a = c[i];
            n5 += this.A(this.A(a)).stringWidth(a.A());
        }
        final int n6 = (int)((n3 - n5) * n4);
        for (int j = n; j <= n2; ++j) {
            final Point a2 = this.N.C()[j].A;
            a2.x += n6;
        }
    }
    
    public void A(final float s) {
        if (this.S != s) {
            this.S = s;
            this.E();
        }
    }
    
    public String toString() {
        return "Label " + this.B() + ": " + this.N;
    }
    
    public void E(final int x) {
        this.X = x;
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
    
    public void A(final boolean t) {
        this.T = t;
    }
    
    public void A(final Rectangle rectangle) {
        super.A(rectangle);
        while ((this.H().height > rectangle.height || this.H().width > rectangle.width) && this.R.getSize() > 6) {
            this.R = new Font(this.R.getName(), this.R.getStyle(), this.R.getSize() - 1);
        }
    }
    
    public void B(final int n) {
        this.K = n + 2;
    }
    
    public void A(final A._A m, final boolean l) {
        this.M = m;
        this.L = l;
    }
    
    public int B(final A._A a) {
        return this.A(this.A(a)).stringWidth(a.A());
    }
    
    protected Font A(final A._A a) {
        Font r;
        if (a.C() && a.D()) {
            r = new Font(this.R.getName(), 3, this.R.getSize());
        }
        else if (a.C()) {
            r = new Font(this.R.getName(), 1, this.R.getSize());
        }
        else if (a.D()) {
            r = new Font(this.R.getName(), 2, this.R.getSize());
        }
        else {
            r = this.R;
        }
        if (a.B() == com.cc.D.A._A.H || a.B() == com.cc.D.A._A.B) {
            r = new Font(r.getName(), r.getStyle(), Math.max(10, r.getSize() * 2 / 3));
        }
        return r;
    }
    
    public void B(final Point w) {
        this.W = w;
    }
    
    public A G() {
        return this.N;
    }
}
