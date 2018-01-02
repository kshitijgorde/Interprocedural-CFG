// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Point;
import com.cc.B.D;
import com.cc.B.E;
import java.awt.Rectangle;
import com.cc.B.F;
import java.util.Observable;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Vector;
import com.cc.C.B;
import java.util.Hashtable;
import com.cc.B.C;
import com.cc.B.A;
import java.awt.Component;
import java.util.Observer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class H implements MouseListener, MouseMotionListener, Observer
{
    Component M;
    public G[][] F;
    public boolean N;
    public int P;
    public int Q;
    public final A A;
    public final C B;
    private boolean L;
    protected com.cc.D.C S;
    public com.cc.C.A R;
    private Hashtable C;
    private B H;
    public boolean I;
    private static final int G = 8;
    private int E;
    public Vector J;
    private Insets D;
    G K;
    G O;
    
    public int A(final Dimension dimension, final boolean b) {
        final int n = dimension.width - (this.A.r ? 2 : 0);
        final int n2 = dimension.height - (this.A.r ? 2 : 0);
        int n3 = this.A.A + (this.A.F ? 1 : 0);
        int n4 = this.A.D + (this.A.F ? 1 : 0) + ((b && this.A.K() != null) ? 2 : 0);
        if (Math.max(n3, n4) < 8) {
            n3 += 2;
            n4 += 2;
        }
        return Math.min(n / n3, n2 / n4);
    }
    
    public int N() {
        return this.E;
    }
    
    public Dimension A(final int n) {
        return new Dimension((this.A.r ? 2 : 0) + (this.A.A + (this.A.F ? 1 : 0)) * n, (this.A.r ? 2 : 0) + (this.A.D + (this.A.F ? 1 : 0)) * n);
    }
    
    public H(final Component m, final A a, final com.cc.D.C s, final com.cc.C.A r, final boolean i, final boolean b, final int e) {
        this.N = false;
        this.L = false;
        this.C = new Hashtable();
        this.H = new B();
        this.J = new Vector();
        this.D = new Insets(0, 0, 0, 0);
        this.K = null;
        this.O = null;
        this.A = a;
        this.E = e;
        this.M = m;
        this.B = new C(this.A);
        this.S = s;
        this.R = r;
        if (!(this.I = i)) {
            this.M();
        }
        else {
            this.K();
        }
        if (b) {
            return;
        }
        m.addMouseListener(this);
        if (this.A.d) {
            m.addMouseMotionListener(this);
            this.A.addObserver(this);
        }
    }
    
    public Dimension D() {
        return new Dimension(this.Q, this.P);
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof F && ((F)o).E == 6) {
            this.M.repaint();
        }
    }
    
    private void K() {
        final D k = this.A.K();
        this.F = new G[1][k.K().A()];
        final B i = k.K();
        final Rectangle rectangle = new Rectangle(1, 1, this.E, this.E);
        for (int j = 0; j < i.A(); ++j) {
            final G g = new G(this, (E)i.A(j), this.R, this.A, this.S, true, this.E);
            g.A(new Rectangle(rectangle));
            final Rectangle rectangle2 = rectangle;
            rectangle2.x += rectangle.width + 1;
            this.F[0][j] = g;
            this.C.put(i.A(j), g);
        }
        this.Q = rectangle.x;
        this.P = rectangle.height + 2;
    }
    
    private void M() {
        int n = 0;
        int e = 0;
        if (this.A.F) {
            this.A("", 0, 0);
            for (int i = 0; i < this.A.I.length; ++i) {
                this.A(this.A.I[i], i + 1, 0).A(0.5f);
            }
            for (int j = 0; j < this.A.H.length; ++j) {
                this.A(this.A.H[j], 0, j + 1).A(1.0f);
            }
            n = (e = this.E);
        }
        this.A(n, e);
        this.F();
        this.C();
        this.H();
        this.I();
        for (int k = 0; k < this.A.A; ++k) {
            for (int l = 0; l < this.A.D; ++l) {
                final E a = this.A.A(k, l, true);
                if (a != null) {
                    final G g = this.C.get(a);
                    final Rectangle b = g.B();
                    final Insets p = g.p;
                    b.setSize(b.width - p.left - p.right, b.height - p.top - p.bottom);
                    b.setLocation(b.x + p.left, b.y + p.top);
                    g.A(b);
                }
            }
        }
        this.Q = this.A.A * this.E + this.D.left + this.D.right + n;
        this.P = this.A.D * this.E + this.D.top + this.D.bottom + e;
    }
    
    private void F() {
        for (int i = 0; i < this.A.A; ++i) {
            for (int j = 0; j < this.A.D; ++j) {
                final E a = this.A.A(i, j, true);
                if (a != null) {
                    if (!a.R()) {
                        final E g = this.B.G(a);
                        final Point a2 = this.A(a);
                        final G g2 = this.C.get(a);
                        final Insets p = g2.p;
                        p.left += a2.x;
                        if (g == null) {
                            this.D.left = Math.max(this.D.left, a2.y);
                            this.H.A(new Rectangle(-a2.y, g2.B().y, a2.y, g2.B().height));
                        }
                        else {
                            final Insets p2 = this.C.get(g).p;
                            p2.right += a2.y;
                        }
                    }
                }
            }
        }
    }
    
    private Point A(final E e) {
        final E g = this.B.G(e);
        final int f = e.F ? 1 : 0;
        int f2 = e.F ? 1 : 0;
        int n;
        if (g == null || g.R()) {
            n = f + (this.A.r ? 2 : 1);
            f2 += (this.A.r ? 1 : 0);
        }
        else {
            n = f + (this.A.N ? 0 : 1);
        }
        return new Point(n, f2);
    }
    
    private Point D(final E e) {
        final E c = this.B.C(e);
        int n = 0;
        int n2 = 0;
        if (c == null || c.R()) {
            n += (this.A.r ? 1 : 0);
            n2 += (this.A.r ? 2 : 1);
        }
        return new Point(n, n2);
    }
    
    private void C() {
        for (int i = 0; i < this.A.A; ++i) {
            for (int j = 0; j < this.A.D; ++j) {
                final E a = this.A.A(i, j, true);
                if (a != null) {
                    if (!a.R()) {
                        final int n = a.N ? 1 : 0;
                        int n2 = a.N ? 1 : 0;
                        final E h = this.B.H(a);
                        int n3;
                        if (h == null || h.R()) {
                            n3 = n + (this.A.r ? 2 : 1);
                            n2 += (this.A.r ? 1 : 0);
                        }
                        else {
                            n3 = n + (this.A.N ? 0 : 1);
                        }
                        final G g = this.C.get(a);
                        final Insets p = g.p;
                        p.top += n3;
                        if (h == null) {
                            this.D.top = Math.max(this.D.top, n2);
                            this.H.A(new Rectangle(g.B().x, g.B().y - n2, g.B().width, n2));
                        }
                        else {
                            final G g2 = this.C.get(h);
                            if (h.a > 1) {
                                g2.p.bottom = Math.max(g2.p.bottom, n2);
                            }
                            else {
                                final Insets p2 = g2.p;
                                p2.bottom += n2;
                            }
                        }
                        final int y = this.A(a).y;
                        if (y > 0) {
                            this.H.A(new Rectangle(g.B().x - y, g.B().y - n2, y, n2));
                        }
                        final int y2 = this.D(a).y;
                        if (y2 > 0) {
                            this.H.A(new Rectangle(g.B().x + g.B().width, g.B().y - n2, y2, n2));
                        }
                    }
                }
            }
        }
    }
    
    private void H() {
        for (int i = 0; i < this.A.A; ++i) {
            for (int j = 0; j < this.A.D; ++j) {
                final E a = this.A.A(i, j, true);
                if (a != null) {
                    if (!a.R()) {
                        final E c = this.B.C(a);
                        final Point d = this.D(a);
                        final G g = this.C.get(a);
                        final Insets p = g.p;
                        p.right += d.x;
                        if (c == null) {
                            this.D.right = Math.max(this.D.right, d.y);
                            this.H.A(new Rectangle(g.B().x + g.B().width, g.B().y, d.y, g.B().height));
                        }
                        else {
                            final G g2 = this.C.get(c);
                            if (c.Z > 1) {
                                g2.p.left = Math.max(g2.p.left, d.y);
                            }
                            else {
                                final Insets p2 = g2.p;
                                p2.left += d.y;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void I() {
        for (int i = 0; i < this.A.A; ++i) {
            for (int j = 0; j < this.A.D; ++j) {
                final E a = this.A.A(i, j, true);
                if (a != null) {
                    if (!a.R()) {
                        int n = 0;
                        int n2 = 0;
                        final E b = this.B.B(a);
                        if (b == null || b.R()) {
                            n += (this.A.r ? 1 : 0);
                            n2 += (this.A.r ? 2 : 1);
                        }
                        else {
                            n += 0;
                        }
                        final G g = this.C.get(a);
                        final Insets p = g.p;
                        p.bottom += n;
                        if (b == null) {
                            this.D.bottom = Math.max(this.D.bottom, n2);
                            this.H.A(new Rectangle(g.B().x, g.B().y + g.B().height, g.B().width, n2));
                        }
                        else {
                            final Insets p2 = this.C.get(b).p;
                            p2.top += n2;
                        }
                        final int y = this.A(a).y;
                        if (y > 0) {
                            this.H.A(new Rectangle(g.B().x - y, g.B().y + g.B().height, y, n2));
                        }
                        final int y2 = this.D(a).y;
                        if (y2 > 0) {
                            this.H.A(new Rectangle(g.B().x + g.B().width, g.B().y + g.B().height, y2, n2));
                        }
                    }
                }
            }
        }
    }
    
    private void A(final int n, final int n2) {
        this.F = new G[this.A.A][this.A.D];
        for (int i = 0; i < this.A.A; ++i) {
            for (int j = 0; j < this.A.D; ++j) {
                final E a = this.A.A(i, j, true);
                if (a == null) {
                    this.F[i][j] = (G)this.C.get(this.A.A(i, j, false));
                }
                else {
                    (this.F[i][j] = new G(this, a, this.R, this.A, this.S, false, this.E)).A(new Rectangle(i * this.E + n, n2 + j * this.E, this.E * a.O(), this.E * a.N()));
                    this.C.put(a, this.F[i][j]);
                }
            }
        }
    }
    
    private com.cc.gui.F A(final String s, final int n, final int n2) {
        final com.cc.gui.F f = new com.cc.gui.F(new com.cc.D.A(s), new Font("SansSerif", 0, (this.E - 2) * 8 / 15), this.E, false, this.M);
        f.B(this.R.\u00c4);
        f.A(this.A.e);
        f.B(n * this.E, n2 * this.E, (this.A.H.length == n) ? (this.E + 1) : this.E, (this.A.H.length == n2) ? (this.E + 1) : this.E);
        if (n == 0) {
            f.A(1.0f);
            f.B(new Point(-3, 5));
        }
        else {
            f.B(new Point(0, 6));
            f.A(0.5f);
        }
        this.J.addElement(f);
        return f;
    }
    
    public void B(final Graphics graphics) {
        graphics.translate(this.D.left, this.D.top);
        for (int i = 0; i < this.F.length; ++i) {
            for (int j = 0; j < this.F[i].length; ++j) {
                if (this.A.A(i, j, true) != null) {
                    final Rectangle b = this.F[i][j].B();
                    this.F[i][j].B(graphics.create(b.x, b.y, b.width, b.height));
                }
            }
        }
        if (this.I) {
            return;
        }
        final Enumeration<com.cc.gui.F> elements = this.J.elements();
        while (elements.hasMoreElements()) {
            final com.cc.gui.F f = elements.nextElement();
            final Rectangle b2 = f.B();
            f.B(graphics.create(b2.x, b2.y, b2.width, b2.height));
        }
        if (this.A.d) {
            for (int k = 0; k < this.A.H().A(); ++k) {
                this.A(graphics, (D)this.A.H().A(k));
            }
            this.A(graphics, this.A.O);
        }
        this.A(graphics);
    }
    
    private void A(final Graphics graphics) {
        graphics.setColor(this.A._);
        for (int i = 0; i < this.A.A; ++i) {
            for (int j = 0; j < this.A.D; ++j) {
                final E a = this.A.A(i, j, true);
                if (a != null) {
                    final G g = this.C.get(a);
                    final Rectangle b = g.B();
                    final Insets p = g.p;
                    final int n = b.x - p.left - (this.A.r ? 0 : 0);
                    final int n2 = b.width + p.left + p.right + (this.A.r ? 0 : 0);
                    if (p.top > 0) {
                        graphics.fillRect(n, b.y - p.top, n2, p.top);
                    }
                    if (p.bottom > 0) {
                        graphics.fillRect(n, b.y + b.height, n2, p.bottom);
                    }
                    final int n3 = b.y - p.top - (this.A.r ? 0 : 0);
                    final int n4 = b.height + p.top + p.bottom + (this.A.r ? 0 : 0);
                    if (p.left > 0) {
                        graphics.fillRect(b.x - p.left, n3, p.left, n4);
                    }
                    if (p.right > 0) {
                        graphics.fillRect(b.x + b.width, n3, p.right, n4);
                    }
                }
            }
        }
        for (int k = 0; k < this.H.A(); ++k) {
            final Rectangle rectangle = (Rectangle)this.H.A(k);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
    
    private void A(final Graphics graphics, final D d) {
        if (d.E()) {
            return;
        }
        if (d.B() && this.A.µ) {
            graphics.setColor(this.A.E);
        }
        else {
            graphics.setColor(this.A.X);
        }
        final E h = d.H();
        final E d2 = d.D();
        if (h == null) {
            return;
        }
        this.A(graphics, this.C(this.C.get(h)), this.C((G)this.C.get(d2)));
    }
    
    private void A(final Graphics graphics, final Point point, final Point point2) {
        final int b = this.B(point2.x - point.x);
        final int b2 = this.B(point2.y - point.y);
        final int n = this.E - 4;
        int n2;
        if (b2 == 0) {
            n2 = 90 * b;
        }
        else if (b == 0) {
            n2 = 90 - 90 * b2;
        }
        else {
            n2 = b * ((b2 < 0) ? 135 : 45);
        }
        graphics.drawArc(point.x - n / 2, point.y - n / 2, n, n, n2, 180);
        graphics.drawArc(point2.x - n / 2, point2.y - n / 2, n, n, n2, -180);
        final double n3 = n2 * 3.141592653589793 / 180.0;
        final Point point3 = new Point((int)Math.round(-Math.cos(n3) * n / 2.0), (int)Math.round(Math.sin(n3) * n / 2.0));
        graphics.drawLine(point.x + point3.x, point.y + point3.y, point2.x + point3.x, point2.y + point3.y);
        graphics.drawLine(point.x - point3.x, point.y - point3.y, point2.x - point3.x, point2.y - point3.y);
    }
    
    private int B(final int n) {
        return (n == 0) ? 0 : ((n > 0) ? 1 : -1);
    }
    
    private Point C(final G g) {
        final Rectangle b = g.B();
        final Insets p = g.p;
        return new Point((b.width + p.left + p.right) / 2 + (b.x - p.left), (b.height + p.top + p.bottom) / 2 + (b.y - p.top));
    }
    
    private boolean C(char upperCase) {
        final E i = this.A.I();
        if (i.I) {
            return true;
        }
        if (!String.valueOf(upperCase).equals(i.M)) {
            upperCase = Character.toUpperCase(upperCase);
        }
        final String s = (upperCase == '\0') ? "" : String.valueOf(upperCase);
        return this.A.A(i, this.L() ? (i.L() + upperCase) : s, this.A.R());
    }
    
    public void B(char c) {
        if (c == 'i' && this.R.A == "Cp1254") {
            c = '\u0130';
        }
        this.A(c);
    }
    
    private void A(final com.cc.D.D d) {
        this.A.B(d.D());
        this.S.B(d);
    }
    
    public boolean L() {
        return this.L;
    }
    
    public void B(final boolean l) {
        if (l != this.L) {
            this.L = l;
            this.A.A(new F(8));
        }
    }
    
    public boolean A(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 9:
            case 10: {
                this.B(false);
                if (this.S.C() == null) {
                    break;
                }
                if (keyEvent.isShiftDown()) {
                    this.A(this.S.C(this.S.C()));
                    break;
                }
                this.A(this.S.A(this.S.C()));
                break;
            }
            case 127: {
                this.C('\0');
                break;
            }
            case 155: {
                this.B(!this.L());
                break;
            }
            case 37: {
                this.B(false);
                this.A(this.B.A(this.A.I()), keyEvent.isShiftDown(), false);
                break;
            }
            case 39: {
                this.B(false);
                this.A(this.B.E(this.A.I()), keyEvent.isShiftDown(), true);
                break;
            }
            case 38: {
                this.B(false);
                this.A(this.B.F(this.A.I()), keyEvent.isShiftDown(), false);
                break;
            }
            case 40: {
                this.B(false);
                this.A(this.B.D(this.A.I()), keyEvent.isShiftDown(), true);
                break;
            }
            case 8: {
                if (this.L()) {
                    String s = this.A.I().L();
                    if (s.length() > 0) {
                        s = s.substring(0, s.length() - 1);
                    }
                    this.A.A(this.A.I(), s, this.A.R());
                    break;
                }
                this.C('\0');
                final E b = this.A.M().B(this.A.N(), this.A.I());
                if (b != null) {
                    this.A.B(b);
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    private void A(final E e, final boolean b, final boolean b2) {
        int n = 0;
        if (b) {
            final com.cc.D.D c = this.S.C();
            if ((e == null || this.A.M().A(e)) && c != null) {
                if (!b2) {
                    this.A(this.S.C(c));
                }
                else {
                    this.A(this.S.A(c));
                }
                return;
            }
        }
        if (e != null) {
            n = ((this.S.C() == null || b || this.A.M().A(e)) ? 1 : 0);
            if (n == 0) {
                n = 1;
                final B a = this.A.A(this.A.I());
                for (int i = 0; i < a.A(); ++i) {
                    if (((D)a.A(i)).A(e)) {
                        n = ((this.A.I().L().length() > 0) ? 1 : 0);
                        this.A.A(a, b);
                        break;
                    }
                }
            }
        }
        if (n != 0) {
            this.A.B(e);
        }
    }
    
    public void B(final G g) {
        if (this.A.I() == g.s || g.J() || this.A.d) {
            return;
        }
        final Point c = this.A.C(g.s);
        final Point c2 = this.A.C(this.A.I());
        if (Math.abs(c.x - c2.x) > Math.abs(c.y - c2.y)) {
            if (this.C(this.B.G(this.A.I()))) {
                return;
            }
            this.C(this.B.C(this.A.I()));
        }
        else {
            if (this.C(this.B.B(this.A.I()))) {
                return;
            }
            this.C(this.B.H(this.A.I()));
        }
    }
    
    private boolean C(final E e) {
        if (e != null) {
            final B a = this.A.A(e);
            for (int i = 0; i < a.A(); ++i) {
                final D d = (D)a.A(i);
                if (d.A(this.A.I())) {
                    this.A.A(d);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void B() {
        if (!this.A.d) {
            this.A.M().A(this.A);
        }
        else {
            final com.cc.D.D c = this.S.C();
            if (c != null) {
                c.B();
            }
            this.M.repaint();
        }
    }
    
    public void P() {
        if (this.A.I() != null) {
            this.A.D(this.A.I());
        }
    }
    
    public void O() {
        this.A.Q();
    }
    
    public void A() {
        this.A.B();
    }
    
    public void G() {
        this.A.C(this.R.\u00ca);
    }
    
    public void A(final char c) {
        if (!this.D(Character.toUpperCase(c))) {
            return;
        }
        final E i = this.A.I();
        if (i == null || !i.B()) {
            return;
        }
        final boolean b = i.L().length() == 0;
        if (!i.I && !this.C(c)) {
            return;
        }
        if (!this.L()) {
            final E a = this.A.M().A(this.A.N(), this.A.I());
            if (a != null && (!b || a.L().length() == 0)) {
                this.A.B(a);
            }
        }
    }
    
    private boolean D(final char c) {
        if ((this.A.G && this.A.i) || this.A.z) {
            return this.A.Y.indexOf(c) > -1 || this.A.Y.indexOf(Character.toUpperCase(c)) > -1;
        }
        return c > '\r' && c != '\u007f';
    }
    
    public void A(final G g) {
        this.B(false);
        if (g.s.U()) {
            this.A.A(this.A.A(g.s), false);
        }
        else {
            this.A.B(g.s);
        }
    }
    
    public void D(final G g) {
        if (this.K == null) {
            this.K = g;
        }
        if (g == this.O) {
            return;
        }
        this.O = g;
        this.A.A(this.A.C(this.K.s), this.A.C(this.O.s));
    }
    
    private G A(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.F.length; ++i) {
            for (int j = 0; j < this.F[i].length; ++j) {
                if (this.F[i][j] != null && this.F[i][j].B().contains(mouseEvent.getPoint())) {
                    return this.F[i][j];
                }
            }
        }
        return null;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.A(mouseEvent) != null) {
            this.D(this.A(mouseEvent));
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final G a = this.A(mouseEvent);
        if (a != null && a.s.B()) {
            this.A(a);
        }
        if (a != null && a.J()) {
            a.C(a.A(mouseEvent.getPoint()));
        }
    }
    
    public E B(final MouseEvent mouseEvent) {
        final G a = this.A(mouseEvent);
        if (a != null) {
            return a.s;
        }
        return null;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.A.d && this.K != null) {
            final D j = this.A.O.J();
            this.K = null;
            this.O = null;
            this.A.D();
            this.A.C(j);
            this.A.O();
        }
        else if (this.A(mouseEvent) != null) {
            this.B(this.A(mouseEvent));
        }
    }
    
    public void A(final boolean b) {
        this.A.A(b);
    }
    
    public int E() {
        return Math.max(5, (this.E + 2) * 4 / 11);
    }
    
    public int J() {
        if (this.A.p == 2) {
            return this.E - this.E / 5 - 2;
        }
        return (this.E - 2) * 7 / 11;
    }
    
    public G B(final E e) {
        return this.C.get(e);
    }
}
