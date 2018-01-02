import java.awt.Graphics;
import java.awt.Event;
import ABLwidgets.font_metrics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.Color;
import ABLwidgets.new_font;
import java.util.Vector;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class abljemp extends Panel
{
    public int a;
    public int b;
    public int c;
    public int d;
    public boolean e;
    int f;
    int g;
    Image h;
    String[] i;
    abljema j;
    abljemf k;
    Object l;
    byte[] m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int t;
    int u;
    int v;
    int w;
    private boolean x;
    private boolean y;
    
    public abljemp(final abljema abljema, final byte[] array) {
        this.e = false;
        this.l = new Object();
        this.x = true;
        this.y = false;
        this.a(null, 10, 10, abljema, array);
    }
    
    public abljemp(final Vector vector) {
        this.e = false;
        this.l = new Object();
        this.x = true;
        this.y = false;
        this.setFont(new_font.a("Dialog", 0, 9));
        this.i = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            this.i[i] = " " + vector.elementAt(i) + " ";
        }
        this.a(null, (Color)null);
    }
    
    private void a(final Image h, final int n, final int n2, final abljema j, final byte[] m) {
        if (h != null) {
            this.h = h;
            this.f = this.h.getWidth(this);
            this.g = this.h.getHeight(this);
            if (this.f < 1 || this.g < 1) {
                this.f = n;
                this.g = n2;
            }
        }
        else {
            this.f = n;
            this.g = n2;
            this.j = j;
            if (this.j != null) {
                this.k = this.j.fb;
            }
            this.m = m;
        }
        this.setLayout(null);
        this.resize(this.f, this.g);
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.f, this.g);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.f, this.g);
    }
    
    public void a(final Rectangle rectangle, final Rectangle rectangle2) {
        final int n = 2;
        final int n2 = 2;
        final int n3 = n + n2;
        if (this.i == null) {
            return;
        }
        final font_metrics a = font_metrics.a(this.getFontMetrics(this.getFont()), (this.j == null) ? '1' : this.j.fd);
        final int g = a.g;
        this.f = 0;
        this.g = 0;
        for (int i = 0; i < this.i.length; ++i) {
            final int a2 = a.a(this.i[i]);
            if (a2 > this.f) {
                this.f = a2;
            }
            this.g += g;
        }
        this.g += 2 * a.f;
        final boolean b = rectangle2.y + rectangle2.height - (rectangle.y + rectangle.height) > this.g + n3;
        final boolean b2 = rectangle.y - rectangle2.y > this.g + n3;
        final boolean b3 = rectangle.x - rectangle2.x > this.f + n3;
        final boolean b4 = rectangle2.x + rectangle2.width - (rectangle.x + rectangle.width) > this.f + n3;
        int y;
        int n4;
        if (b || b2) {
            if (b) {
                y = rectangle.y + rectangle.height + n;
            }
            else {
                y = rectangle.y - this.g - n;
            }
            n4 = rectangle.x;
            if (n4 + this.f > rectangle2.x + rectangle2.width) {
                n4 = rectangle2.x + rectangle2.width - this.f - n2;
            }
            if (n4 < rectangle2.x) {
                n4 = rectangle2.x + n2;
            }
        }
        else if (b3 || b4) {
            if (b4) {
                n4 = rectangle.x + rectangle.width + n;
            }
            else {
                n4 = rectangle.x - this.f - n;
            }
            y = rectangle.y;
            if (y + this.g > rectangle2.y + rectangle2.height) {
                y = rectangle2.y + rectangle2.height - this.g - n2;
            }
            if (y < rectangle2.y) {
                y = rectangle2.y + n2;
            }
        }
        else {
            n4 = rectangle.x;
            y = rectangle.y + rectangle.height + n;
        }
        this.reshape(n4, y, this.f, this.g);
    }
    
    public void show() {
        super.show();
    }
    
    public void hide() {
        this.b();
        super.hide();
    }
    
    public void a(final Color color, final Color color2) {
        this.setForeground((color == null) ? Color.black : color);
        this.setBackground((color2 == null) ? Color.white : color2);
    }
    
    public boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
    
    public void update(final Graphics graphics) {
        super.update(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.m != null && this.j != null) {
            this.a(graphics);
        }
        else if (this.i != null) {
            this.b(graphics);
        }
        else if (this.h != null) {
            graphics.drawImage(this.h, 0, 0, this.f, this.g, this);
        }
        else {
            super.paint(graphics);
        }
    }
    
    private void a(final Graphics graphics) {
        synchronized (this.l) {
            graphics.setFont(this.k.y);
            for (int i = this.a; i <= this.c; ++i) {
                final int n = (i - 1) * this.j.es + (this.b - 1);
                final int n2 = this.d - this.b + 1;
                if (n >= 0 && n <= this.m.length && n2 > 0 && n + n2 <= this.m.length) {
                    final int n3 = (i - this.a + 1) * this.k.a6 - this.k.a5 - ((this.k.a4 < 10) ? 2 : 3);
                    if (this.e) {
                        this.k.a(graphics, this.m, n, n2, 0, n3, this.k.a7, false);
                    }
                    else {
                        abljemf.a(graphics, this.m, n, n2, 0, n3);
                    }
                }
            }
        }
        // monitorexit(this.l)
    }
    
    private void b(final Graphics graphics) {
        for (int g = font_metrics.a(this.getFontMetrics(this.getFont()), (this.j == null) ? '1' : this.j.fd).g, i = 0, n = g; i < this.i.length; ++i, n += g) {
            graphics.drawString(this.i[i], 0, n);
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.m != null) {
            this.getParent().mouseUp(event, n, n2);
        }
        return super.mouseUp(event, n, n2);
    }
    
    public void a(final int n, final int o, final int r, final int s) {
        synchronized (this.l) {
            this.n = n;
            this.o = o;
            this.p = 0;
            this.q = 0;
            this.r = r;
            this.s = s;
            this.t = 0;
            this.u = 0;
            this.hide();
            this.x = true;
        }
        // monitorexit(this.l)
    }
    
    public boolean a() {
        return this.y;
    }
    
    public void b() {
        this.x = false;
        this.y = false;
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        synchronized (this.l) {
            this.c(n, n2, n3, n4);
        }
        // monitorexit(this.l)
    }
    
    private void c(final int p4, final int q, final int t, final int u) {
        if (!this.x) {
            this.y = false;
            return;
        }
        this.p = p4;
        this.q = q;
        this.t = t;
        this.u = u;
        this.a = Math.min(this.r, this.t);
        this.b = Math.min(this.s, this.u);
        this.c = Math.max(this.r, this.t);
        this.d = Math.max(this.s, this.u);
        if (this.a < 1) {
            this.a = 1;
        }
        if (this.a > this.j.et) {
            this.a = this.j.et;
        }
        if (this.c < 1) {
            this.c = 1;
        }
        if (this.c > this.j.et) {
            this.c = this.j.et;
        }
        if (this.b < 1) {
            this.b = 1;
        }
        if (this.b > this.j.es) {
            this.b = this.j.es;
        }
        if (this.d < 1) {
            this.d = 1;
        }
        if (this.d > this.j.es) {
            this.d = this.j.es;
        }
        if (!this.y && Math.abs(this.p - this.n) < this.k.a7 && Math.abs(this.q - this.q) < this.k.a7) {
            return;
        }
        this.y = true;
        final int n = this.k.bb + (this.b - 1) * this.k.a7 + this.k.dv;
        final int n2 = this.k.bc + (this.a - 1) * this.k.a6 + this.k.dw + this.k.a5;
        final int n3 = (this.d - this.b + 1) * this.k.a7;
        final int n4 = (this.c - this.a + 1) * this.k.a6;
        if (this.v != n || this.w != n2) {
            this.v = n;
            this.w = n2;
            this.f = n3;
            this.g = n4;
            this.reshape(this.v, this.w, this.f, this.g);
        }
        else if (n3 != this.f || n4 != this.g) {
            this.v = n;
            this.w = n2;
            this.f = n3;
            this.g = n4;
            this.resize(this.f, this.g);
        }
        if (!this.isVisible()) {
            this.show();
        }
        else {
            this.repaint();
        }
    }
    
    public void c() {
        synchronized (this.l) {
            this.n = 0;
            this.o = 0;
            this.p = 0;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = 0;
            this.hide();
        }
        // monitorexit(this.l)
    }
}
