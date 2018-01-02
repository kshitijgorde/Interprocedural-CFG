import ABLwidgets.utils;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemct extends Canvas
{
    public int a;
    public int b;
    private abljema c;
    private boolean d;
    private String[] e;
    private MenuItem[] f;
    private abljemtf g;
    private abljempu h;
    private boolean i;
    private boolean j;
    private long k;
    private Image l;
    private Graphics m;
    private boolean n;
    private boolean o;
    private abljemfs p;
    private Color q;
    private Color r;
    private Color s;
    private Color t;
    private Color u;
    private Color v;
    private Color w;
    private Color x;
    
    abljemct(final abljema c, final abljemtf g, final String[] e) {
        this.d = false;
        this.i = false;
        this.j = false;
        this.n = true;
        this.o = false;
        this.q = new Color(192, 192, 192);
        this.s = new Color(255, 255, 255);
        this.t = new Color(128, 128, 128);
        this.v = new Color(64, 64, 64);
        this.w = new Color(192, 192, 192);
        this.x = new Color(0, 0, 0);
        this.c = c;
        this.g = g;
        this.e = e;
        this.f = new MenuItem[e.length];
        this.setBackground(this.w);
        this.r = abljema.a(this.q, this.s);
        this.u = abljema.a(this.v, this.t);
        this.p = new abljemfs(c, this, true);
        this.hide();
        this.a();
    }
    
    private void a() {
        (this.h = new abljempu(this.c, this.c.fb, this)).hide();
        this.h.a(Color.white);
        for (int i = 0; i < this.f.length; ++i) {
            final String s = this.e[i];
            if (s == null) {
                break;
            }
            this.f[i] = new MenuItem(s);
            this.h.a(this.f[i]);
        }
    }
    
    private void b() {
        this.c.fb.b = this.h;
        final Rectangle bounds = this.g.bounds();
        this.bounds();
        this.h.i = bounds.width - 3;
        this.h.setFont(this.g.getFont());
        this.h.a((Graphics)null);
        this.h.a(this.g, 0 + 1 + this.c.fb.dx, bounds.height + this.c.fb.dy);
        this.repaint();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target != this.h || this.h == null) {
            return super.action(event, o);
        }
        if (event.id == 1005) {
            this.k = System.currentTimeMillis();
            return true;
        }
        final MenuItem a = this.h.a(event);
        for (int i = 0; i < this.f.length; ++i) {
            if (a == this.f[i] && a != null) {
                String substring = this.e[i];
                if (substring.length() > this.g.ae) {
                    substring = substring.substring(0, this.g.ae);
                }
                this.g.c(substring);
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 504) {
            this.i = true;
            this.repaint();
        }
        if (event.id == 505) {
            this.i = false;
            this.j = false;
            this.repaint();
        }
        return super.handleEvent(event);
    }
    
    public void enable() {
        super.enable();
        this.p.b();
    }
    
    public void disable() {
        if (!this.p.c(this.o)) {
            return;
        }
        super.disable();
    }
    
    public void hide() {
        if (!this.p.a(this.o)) {
            return;
        }
        super.hide();
    }
    
    public void show() {
        super.show();
        this.p.a();
    }
    
    public void requestFocus() {
        super.requestFocus();
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        this.o = true;
        return super.gotFocus(event, o);
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        this.o = false;
        return super.lostFocus(event, o);
    }
    
    public boolean getFocusTraversalKeysEnabled() {
        return false;
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.a = n3;
        super.reshape(n, n2, this.b = n3, n3);
    }
    
    public void update(final Graphics graphics) {
        if (this.c.cn || this.getBackground() != this.w) {
            this.setBackground(this.w);
        }
        if (this.m != null) {
            this.paint(graphics);
        }
        else {
            super.update(graphics);
        }
    }
    
    public void paint(Graphics m) {
        final Graphics graphics = m;
        Color color = this.q;
        final Color r = this.r;
        Color color2 = this.s;
        Color color3 = this.t;
        final Color u = this.u;
        Color color4 = this.v;
        int n = (this.b - 4) / 3 + 3;
        int n2 = n + (this.b - 4 + 1) / 3 - 1;
        int n3 = this.a / 2;
        if (this.j) {
            color = this.v;
            color2 = this.t;
            color3 = this.s;
            color4 = this.q;
            if (n2 - n >= 6) {
                ++n;
                if (++n2 - n >= 8) {
                    ++n3;
                }
            }
        }
        if (this.n && (this.l == null || this.a > this.l.getWidth(null) || this.b > this.l.getHeight(null))) {
            final int a = this.a;
            final int b = this.b;
            this.l = null;
            this.m = null;
            try {
                this.l = this.createImage(a, b);
            }
            catch (Throwable t2) {
                this.l = null;
            }
            if (this.l == null) {
                try {
                    this.l = this.createImage(a, b);
                }
                catch (Throwable t3) {
                    this.l = null;
                }
            }
            if (this.l == null) {
                abljem.d("abljemct image buffer create failed " + a + "x" + b);
            }
            else {
                try {
                    this.m = this.l.getGraphics();
                }
                catch (Throwable t) {
                    abljem.d("Image buffer getGraphics failed - " + t);
                    this.l = null;
                    this.m = null;
                }
            }
            if (this.l == null) {
                this.n = false;
            }
        }
        if (this.m != null) {
            m = this.m;
        }
        if (m == this.m) {
            utils.a(m, this.getBackground(), 0, 0, this.a, this.b);
        }
        m.setColor(color);
        m.drawLine(0, 0, this.a - 2, 0);
        m.drawLine(0, 0, 0, this.b - 1);
        m.setColor(color2);
        m.drawLine(1, 1, this.a - 3, 1);
        m.drawLine(1, 1, 1, this.b - 2);
        m.setColor(color4);
        m.drawLine(0, this.b - 1, this.a - 1, this.b - 1);
        m.drawLine(this.a - 1, this.b - 1, this.a - 1, 0);
        m.setColor(color3);
        m.drawLine(1, this.b - 2, this.a - 2, this.b - 2);
        m.drawLine(this.a - 2, this.b - 2, this.a - 2, 1);
        m.setColor(this.x);
        for (int i = n2, n4 = n3, n5 = n3; i >= n; --i, --n4, ++n5) {
            m.drawLine(n4, i, n5, i);
        }
        if (m == this.m) {
            m = graphics;
            m.drawImage(this.l, 0, 0, this);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.clickCount > 1) {
            return true;
        }
        this.j = true;
        this.repaint();
        if (this.h != null && System.currentTimeMillis() - this.k > 200L) {
            this.b();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (event.clickCount > 1) {
            return true;
        }
        if (this.h.isVisible()) {
            this.h.requestFocus();
        }
        this.j = false;
        this.repaint();
        return true;
    }
}
