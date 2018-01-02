import java.awt.Event;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends Canvas
{
    public boolean a;
    public boolean b;
    public String c;
    public int d;
    public int e;
    public Image f;
    public Image g;
    public Image h;
    public Image i;
    public boolean j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public Frame p;
    public int q;
    public int r;
    public boolean s;
    public String t;
    public Container u;
    public Image v;
    public Graphics w;
    public int x;
    
    public j(final Image image, final Image image2, final Image h, final Image i, final Container u, final int n, final int n2, final Frame p8) {
        this.a = false;
        this.b = false;
        this.j = true;
        this.m = 4;
        this.n = 80;
        this.o = 2;
        this.s = false;
        this.t = null;
        this.v = null;
        this.w = null;
        this.x = 0;
        this.p = p8;
        this.u = u;
        this.h = h;
        this.i = i;
        this.o = this.h.getWidth(null) / 2;
        this.a(image, image2, n, n2);
    }
    
    public void finalize() {
        if (this.w != null) {
            this.w.dispose();
            this.v.flush();
            this.v = null;
        }
    }
    
    public void a(final Image f, final Image g, final int m, final int n) {
        if (this.f != null) {
            this.f.flush();
        }
        if (this.g != null) {
            this.g.flush();
        }
        this.f = f;
        this.g = g;
        if (this.f != null) {
            this.q = this.f.getWidth(null);
            this.r = this.f.getHeight(null);
            this.v = this.u.createImage(this.q, this.r);
            this.w = this.v.getGraphics();
        }
        if (m >= 0 && n > 0) {
            this.m = m;
            this.n = n;
        }
        else {
            this.m = 2 + this.o;
            this.n = this.q - this.m - this.m;
        }
        super.resize(this.size());
    }
    
    public void a(final float n) {
        this.d = (int)(n * this.n);
        this.repaint();
    }
    
    public void a(final int n, final int x, final String t) {
        if (!this.a) {
            this.x = x;
            this.e = n * this.n / x;
            this.t = t;
            this.repaint();
        }
    }
    
    public double a() {
        return (this.e + 0.5) / this.n;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.f == null) {
            return;
        }
        this.w.drawImage(this.f, 0, 0, null);
        if (this.j && this.g != null && this.d > 0) {
            if (this.d >= this.n) {
                this.w.drawImage(this.g, 0, 0, null);
            }
            else {
                final Image image = this.createImage(this.m + this.d, this.r);
                if (image != null) {
                    final Graphics graphics2 = image.getGraphics();
                    graphics2.drawImage(this.g, 0, 0, null);
                    this.w.drawImage(image, 0, 0, null);
                    graphics2.dispose();
                    image.flush();
                }
            }
        }
        if (this.j && this.h != null && this.i != null) {
            this.w.drawImage((this.d == 0 || this.e > this.d) ? this.h : this.i, this.m - this.o + this.e, 0, null);
        }
        if (this.s && this.t != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            final int stringWidth = fontMetrics.stringWidth(this.t);
            int r = fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent() + 6;
            if (r > this.r) {
                r = this.r;
            }
            int n;
            if (this.m + this.e > this.q / 2) {
                n = this.m + this.e - this.o - stringWidth - 8;
            }
            else {
                n = this.m + this.e + this.o + 8;
            }
            final Color color = this.w.getColor();
            this.w.setColor(Color.yellow);
            this.w.fillRect(n - 4, (this.r - r) / 2, stringWidth + 7, r - 1);
            this.w.setColor(Color.black);
            this.w.drawString(this.t, n, (this.r - fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent()) / 2);
            this.w.drawRect(n - 4, (this.r - r) / 2, stringWidth + 7, r - 1);
            this.w.setColor(color);
        }
        graphics.drawImage(this.v, 0, 0, null);
    }
    
    public void setName(final String c) {
        this.c = c;
    }
    
    public String getName() {
        return this.c;
    }
    
    public void enable() {
        this.j = true;
        this.repaint();
    }
    
    public void disable() {
        this.d = 0;
        this.e = 0;
        this.j = false;
        this.repaint();
    }
    
    public Dimension size() {
        return new Dimension(this.q, this.r);
    }
    
    private void b() {
        if (this.a) {
            this.e = this.k - 5;
            if (this.e < 0) {
                this.e = 0;
            }
            if (this.e >= this.n) {
                this.e = this.n - 1;
            }
            this.t = c.a((int)(this.a() * this.x)) + "/" + c.a(this.x);
            this.repaint();
        }
    }
    
    public synchronized boolean mouseDown(final Event event, final int k, final int l) {
        this.k = k;
        this.l = l;
        this.a = true;
        this.mouseDrag(event, k, l);
        if (this.j) {
            this.b();
            this.p.setCursor(11);
        }
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, final int k, final int l) {
        this.k = k;
        this.l = l;
        this.postEvent(new Event(this, 1001, this.c));
        if (this.j) {
            this.p.setCursor(12);
        }
        this.s = false;
        this.a = false;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseEnter(final Event event, final int k, final int l) {
        this.k = k;
        this.l = l;
        this.b = true;
        this.repaint();
        if (this.j) {
            this.p.setCursor(12);
            this.s = true;
        }
        return true;
    }
    
    public synchronized boolean mouseDrag(final Event event, final int k, final int l) {
        this.k = k;
        this.l = l;
        if (this.j) {
            this.s = true;
            this.b();
        }
        return true;
    }
    
    public synchronized boolean mouseExit(final Event event, final int k, final int l) {
        this.k = k;
        this.l = l;
        this.b = false;
        this.repaint();
        if (this.j) {
            this.p.setCursor(0);
            this.s = false;
        }
        return true;
    }
}
