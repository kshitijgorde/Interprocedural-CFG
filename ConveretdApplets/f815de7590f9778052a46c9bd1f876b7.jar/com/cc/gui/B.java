// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import java.util.Observable;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.Rectangle;
import com.cc.D.C;
import com.cc.D.D;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Observer;

public class B implements Observer, MouseListener, MouseMotionListener
{
    private final D[] N;
    private final com.cc.D.B O;
    private static final int M = 2;
    private F R;
    private F[] C;
    private F[] J;
    private int F;
    private A A;
    private C Q;
    private com.cc.B.A B;
    private com.cc.C.A P;
    private int G;
    private boolean D;
    private Rectangle L;
    private Component I;
    private com.cc.gui.D E;
    private boolean H;
    private int K;
    
    public B(final Component i, final com.cc.gui.D e, final com.cc.C.A p6, final com.cc.D.B o, final com.cc.B.A b, final C q) {
        this.F = -1;
        this.D = false;
        this.L = new Rectangle(0, 0, 0, 0);
        this.H = false;
        this.K = 0;
        this.B = b;
        this.Q = q;
        this.O = o;
        this.N = o.D;
        this.P = p6;
        this.I = i;
        this.E = e;
        i.addMouseListener(this);
        i.addMouseMotionListener(this);
    }
    
    public void A(final com.cc.gui.D e) {
        this.E = e;
    }
    
    private void A(final int n, final int n2) {
        if (this.O.E != null && this.O.E.A().length() > 0) {
            (this.R = new F(this.O.E, new Font(this.P.M.getName(), 0, this.P.M.getSize() + 2), n, true, this.I)).D(true);
            if (this.P.W) {
                this.R.A(0.5f);
            }
            this.R.B(2, 0, n - 2, this.R.H().height);
            this.G = this.R.H().height;
        }
        this.L = new Rectangle(0, 0, n, n2 - this.G);
        this.A(this.P);
        if (this.C() + this.G > n2) {
            this.L = new Rectangle(0, 0, n - 15, n2 - this.G);
            this.A(this.P);
            (this.A = new A(this, this.I)).B(n - 15, this.G, 15, n2 - this.G);
            this.A.F(this.C() - this.L.height);
            this.A.A(this.P.X);
        }
        this.B.addObserver(this);
        if (!this.B.d) {
            for (int i = 0; i < this.N.length; ++i) {
                final String a = this.N[i].A();
                if (a != null && this.B.D(a).A(this.B.I())) {
                    this.A(i);
                    break;
                }
            }
        }
    }
    
    private int C() {
        return this.C[this.C.length - 1].B().y + this.C[this.C.length - 1].B().height;
    }
    
    private void A(final com.cc.C.A a) {
        final int n = this.L.width - 2;
        this.C = new F[this.N.length];
        this.J = new F[this.N.length];
        int max = 0;
        String s = " ";
        if (a.y) {
            s = ". ";
        }
        for (int i = 0; i < this.N.length; ++i) {
            final com.cc.D.A._A a2 = new com.cc.D.A._A((this.N[i].E.length() > 0 && !this.O.B) ? (this.N[i].E + s) : "", a.O, false, com.cc.D.A._A.E);
            (this.C[i] = new F(this.N[i].F, a.M, n, true, this.I)).A(true);
            if ((!a.R || a2.A().length() > s.length() + 1) && !this.O.B) {
                a2.A(a2.A() + " ");
            }
            this.C[i].A(a2, a.R);
            this.C[i].A(a.\u00cb);
            (this.J[i] = new F(new com.cc.D.A(new com.cc.D.A._A[] { new com.cc.D.A._A(this.N[i].G() ? a.i : " ", true, false, com.cc.D.A._A.E) }), a.M, n, true, this.I)).A(true);
            this.J[i].A(a.\u00d1);
            this.J[i].B(true);
            max = Math.max(this.J[i].H().width, max);
        }
        final int n2 = n - max;
        int n3 = 0;
        int b = 0;
        if (a.ª) {
            b = this.C[0].B(new com.cc.D.A._A("99" + s, true, false, com.cc.D.A._A.E));
        }
        for (int j = 0; j < this.C.length; ++j) {
            this.C[j].E(n2);
            this.C[j].B(b);
            final int height = this.C[j].H().height;
            this.C[j].B(2, n3, n2, height);
            if (this.J[j] != null) {
                this.J[j].B(2 + n2, n3, max, height);
            }
            n3 += height;
        }
        if (this.R != null) {
            this.R.B(b);
        }
    }
    
    private int A(final com.cc.B.F f) {
        if (f.E == 4) {
            for (int i = 0; i < this.N.length; ++i) {
                if (f.A.I().equals(this.N[i].A())) {
                    return i;
                }
            }
        }
        else if (f.E == 1) {
            for (int j = 0; j < this.N.length; ++j) {
                if (this.B.D(this.N[j].A()).A(this.B.I())) {
                    return j;
                }
            }
        }
        else if (f.E == 5) {
            for (int k = 0; k < this.N.length; ++k) {
                if (this.N[k].A().equals(f.A.I())) {
                    return k;
                }
            }
        }
        return -1;
    }
    
    private void A(final int n) {
        if (this.A == null) {
            return;
        }
        if (!this.A(this.C[n], true)) {
            final Rectangle b = this.C[n].B();
            int n2 = b.y - this.L.y;
            if (n2 > 0) {
                n2 = b.y + b.height - (this.L.y + this.L.height);
            }
            final Rectangle l = this.L;
            l.y += n2;
            int i = 0;
            while (i < this.C.length) {
                if (this.C[i].B().y >= this.L.y) {
                    this.L.y = this.C[i].B().y;
                    if (this.L.y > this.A.P()) {
                        this.L.y = this.A.P();
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            this.A.H(this.L.y);
        }
    }
    
    private boolean A(final F f, final boolean b) {
        final Rectangle d = f.D;
        if (b) {
            return this.L.contains(d.x, d.y) && this.L.contains(d.x + d.width, d.y + d.height);
        }
        return this.L.intersects(f.D);
    }
    
    public void A(final int n, final int n2, final int n3, final int n4) {
        if (!this.D) {
            this.A(n3, n4);
        }
    }
    
    public D[] B() {
        return this.N;
    }
    
    public int A() {
        return this.F;
    }
    
    public com.cc.D.B E() {
        return this.O;
    }
    
    public void F() {
        if (this.R != null) {
            this.R.C = false;
        }
        if (this.A != null) {
            this.A.C = false;
        }
        for (int i = 0; i < this.C.length; ++i) {
            if (this.L.intersects(this.C[i].B())) {
                this.C[i].C = false;
            }
            if (this.J[i] != null) {
                this.J[i].C = false;
            }
        }
    }
    
    public void C(final int y) {
        final Rectangle l = new Rectangle(this.L);
        l.y = y;
        for (int i = 0; i < this.C.length; ++i) {
            if (l.intersects(this.C[i].B())) {
                this.C[i].C = false;
            }
            if (this.J[i] != null) {
                this.J[i].C = false;
            }
        }
        this.I.repaint();
        this.L = l;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.A != null && this.A.B().contains(mouseEvent.getPoint())) {
            final Point point = mouseEvent.getPoint();
            point.translate(-this.A.B().x, -this.A.B().y);
            this.A.D(point);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.E.A("");
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.A != null && this.A.B().contains(mouseEvent.getPoint())) {
            final Point point = mouseEvent.getPoint();
            point.translate(-this.A.B().x, -this.A.B().y);
            this.A.E(point);
            this.H = false;
            return;
        }
        this.K = ((this.A != null) ? this.A.Q() : 0) + mouseEvent.getY();
        this.H = (this.A != null);
        final Point a = this.A(mouseEvent.getPoint());
        for (int i = 0; i < this.J.length; ++i) {
            final Rectangle b = this.C[i].B();
            final Rectangle rectangle = (this.J[i] == null) ? new Rectangle() : this.J[i].B();
            if (b.contains(a) || rectangle.contains(a)) {
                if (!this.O.A()) {
                    this.Q.B(this.N[i]);
                    if (!this.B.d) {
                        this.B.B(this.N[i].D());
                    }
                }
                if (rectangle.contains(a) && this.N[i].G()) {
                    this.E.A(this.N[i].F(), this.P.\u00cc, false, false);
                }
                return;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.A != null) {
            this.A.K();
            return;
        }
        if (this.H) {
            this.I.setCursor(Cursor.getPredefinedCursor(0));
        }
        this.H = false;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.A != null && !this.H) {
            point.translate(-this.A.B().x, -this.A.B().y);
            this.A.F(point);
            return;
        }
        if (this.H) {
            this.I.setCursor(Cursor.getPredefinedCursor(13));
            this.A.H(this.K - point.y);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.J.length; ++i) {
            if (this.J[i] != null) {
                if (this.J[i].B().contains(this.A(mouseEvent.getPoint()))) {
                    if (this.N[i].G()) {
                        this.E.A(this.N[i].F());
                        this.I.setCursor(Cursor.getPredefinedCursor(12));
                    }
                    return;
                }
            }
        }
        this.E.A("");
        this.I.setCursor(Cursor.getDefaultCursor());
    }
    
    private Point A(final Point point) {
        return new Point(point.x, point.y + this.L.y - this.G);
    }
    
    public boolean I() {
        for (int i = 0; i < this.N.length; ++i) {
            if (this.N[i] == this.Q.C()) {
                return true;
            }
        }
        return false;
    }
    
    public void B(final int n) {
        int n2 = this.A.Q();
        if (n > 0) {
            for (int i = 0; i < this.C.length; ++i) {
                final Rectangle b = this.C[i].B();
                if (b.y + b.height > this.L.y + this.L.height) {
                    n2 = b.y;
                    break;
                }
            }
        }
        else {
            for (int j = this.C.length - 1; j >= 0; --j) {
                final Rectangle b2 = this.C[j].B();
                if (b2.y < this.L.y) {
                    n2 = b2.y + b2.height - this.L.height;
                    break;
                }
            }
        }
        this.A.H(n2);
    }
    
    public void D() {
        if (this.R != null) {
            this.R.B = true;
        }
        if (this.A != null) {
            this.A.B = true;
        }
        for (int i = 0; i < this.C.length; ++i) {
            this.C[i].B = true;
            if (this.J[i] != null) {
                this.J[i].B = true;
            }
        }
    }
    
    public void A(final Graphics graphics) {
        graphics.setColor(this.I.getBackground());
        graphics.clearRect(0, 0, 2, this.I.getBounds().height);
        if (this.A != null) {
            final Rectangle b = this.A.B();
            this.A.B(graphics.create(b.x, b.y, b.width, b.height));
        }
        if (this.R != null) {
            final Rectangle b2 = this.R.B();
            this.R.B(graphics.create(b2.x, b2.y, b2.width, b2.height));
        }
        final D h = this.H();
        for (int i = 0; i < this.N.length; ++i) {
            final F f = this.C[i];
            if (this.N[i] == h) {
                f.C(this.P.w);
            }
            else {
                f.C((Color)null);
            }
            if (!this.B.d) {
                if (this.N[i].B(this.B.I()) && h != null) {
                    f.A(this.P.w);
                }
                else if (this.N[i].A() != null && this.B.D(this.N[i].A()).F() && h != null) {
                    f.A(this.P.£);
                }
                else {
                    f.A(this.I.getForeground());
                }
            }
            else if (this.N[i].A() != null && !this.B.D(this.N[i].A()).E()) {
                f.A(this.P.w);
            }
            else {
                f.A(this.I.getForeground());
            }
        }
        graphics.setClip(0, this.G, this.L.width, this.L.height);
        for (int j = 0; j < this.N.length; ++j) {
            this.A(graphics, this.C[j]);
            this.A(graphics, this.J[j]);
        }
        graphics.setClip(null);
        final Rectangle b3 = this.C[this.C.length - 1].B();
        final int n = this.L.height + this.L.y - b3.y - b3.height;
        if (n > 0) {
            graphics.setColor(this.I.getBackground());
            graphics.clearRect(this.L.x, b3.y + b3.height - this.L.y + this.G, this.L.width, n);
        }
    }
    
    private void A(final Graphics graphics, final F f) {
        if (f != null && this.A(f, false)) {
            final Rectangle b = f.B();
            final Graphics create = graphics.create(b.x, b.y - this.L.y + this.G, b.width, b.height);
            f.B(create);
            create.dispose();
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (((com.cc.B.F)o).E == 7) {
            this.I.repaint();
        }
        else {
            if (!this.O.A()) {
                final int a = this.A((com.cc.B.F)o);
                if (a > -1) {
                    this.A(a);
                }
            }
            this.I.repaint();
        }
    }
    
    public Rectangle G() {
        return this.L;
    }
    
    public D H() {
        return this.O.A() ? null : this.Q.C();
    }
}
