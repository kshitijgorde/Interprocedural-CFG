// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.Canvas;

public final class ac extends av implements fe
{
    private int t;
    Canvas a;
    ei a;
    private Container a;
    u a;
    u b;
    boolean a;
    boolean b;
    private fh a;
    private boolean j;
    private boolean k;
    bb a;
    cb a;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean l;
    public KeyListener a;
    public MouseListener a;
    u c;
    int a;
    int b;
    
    final void b(final int u, final int w, final int n, final int n2) {
        if (u < this.u || this.u == -1) {
            this.u = u;
        }
        if (w < this.w || this.w == -1) {
            this.w = w;
        }
        if (u + n > this.v) {
            this.v = u + n;
        }
        if (w + n2 > this.x) {
            this.x = w + n2;
        }
        if (this.u < 0) {
            this.u = 0;
        }
        if (this.w < 0) {
            this.w = 0;
        }
    }
    
    public ac(final Container container, final dh dh) {
        this(container, dh.a);
    }
    
    public ac(final Container a, final cb a2) {
        super((byte)0);
        this.t = 50;
        this.a = new ei();
        this.a = false;
        this.b = false;
        this.a = new bb();
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        super.c = Color.lightGray;
        super.b = Color.black;
        super.d = Color.lightGray;
        super.e = Color.darkGray;
        super.f = Color.white;
        super.g = Color.black;
        this.a = new ao(this);
        this.a = a;
        this.a = a2;
        if (null != a) {
            this.a(a);
        }
        this.a = a2.a.a(this, -1);
    }
    
    public final void a(final Container a) {
        (this.a = a).setLayout(new BorderLayout());
        a.add("Center", this.a);
    }
    
    public final void a() {
        this.j = true;
        this.g();
        if (this.a != null) {
            final fh a = this.a;
            a.a.a(a);
        }
        this.a.b = null;
    }
    
    public final void i() {
        this.n();
    }
    
    public final void l() {
        if (this.u == -1) {
            this.l = true;
        }
        this.n();
    }
    
    final void a(final Graphics a, final int n, final int n2, final int n3, final int n4) {
        this.a.a = a;
        this.a.a = -n;
        this.a.b = -n2;
        super.b(this.a, n, n2, n3, n4);
    }
    
    protected final void b(final int e, final int f) {
        super.e = e;
        super.f = f;
        this.e();
    }
    
    public final void d() {
        super.d();
        this.a.invalidate();
    }
    
    public final void m() {
        if (this.k) {
            this.k = false;
            this.o();
        }
    }
    
    protected final Dimension a() {
        return new Dimension(this.a(), this.b());
    }
    
    final void b(final Event event) {
        if (null != this.a) {
            final int modifiers = event.modifiers;
            switch (event.id) {
                case 501: {
                    this.a.mousePressed(new MouseEvent(this.a, 501, System.currentTimeMillis(), modifiers, event.x, event.y, event.clickCount, false));
                }
                case 502: {
                    this.a.mouseReleased(new MouseEvent(this.a, 502, System.currentTimeMillis(), modifiers, event.x, event.y, event.clickCount, false));
                }
                case 504: {
                    this.a.mouseEntered(new MouseEvent(this.a, 504, System.currentTimeMillis(), modifiers, event.x, event.y, event.clickCount, false));
                }
                case 505: {
                    this.a.mouseExited(new MouseEvent(this.a, 505, System.currentTimeMillis(), modifiers, event.x, event.y, event.clickCount, false));
                    break;
                }
            }
        }
    }
    
    public final void a(final u b, final boolean b2) {
        final u b3 = this.b;
        this.b = b;
        this.b = b2;
        if (!this.a) {
            this.a.requestFocus();
        }
        if (this.a && b3 != this.b) {
            if (b3 != null) {
                b3.a(new Event(b3, 1005, null));
            }
            this.b.a(new Event(this.b, 1004, null));
        }
    }
    
    public final FontMetrics a(final Font font) {
        return this.a.getFontMetrics(font);
    }
    
    public final boolean a(final Event event) {
        if (!super.a(event) && null != this.a) {
            this.a.handleEvent(event);
        }
        return true;
    }
    
    public final av b() {
        return this;
    }
    
    public final void k() {
        super.k();
        this.n();
    }
    
    private void n() {
        final int a = this.a.a();
        this.k = true;
        if (this.a != null) {
            this.a.b = Math.max(15, a * 100 / this.t);
            return;
        }
        this.o();
    }
    
    private void o() {
        if (!this.j) {
            if (this.u != -1) {
                final int u = this.u;
                final int v = this.v;
                final int w = this.w;
                final int x = this.x;
                final int n = -1;
                this.x = n;
                this.v = n;
                this.w = n;
                this.u = n;
                this.a.repaint(u, w, v - u + 1, x - w + 1);
                return;
            }
            if (this.l) {
                this.a.repaint(0, 0, 1, 1);
            }
        }
    }
}
