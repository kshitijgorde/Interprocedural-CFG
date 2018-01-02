import java.awt.Event;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends Canvas
{
    public boolean a;
    public boolean b;
    public String c;
    public int d;
    public int e;
    public Image f;
    public Image g;
    public Image h;
    public boolean i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public Component o;
    public Frame p;
    public d q;
    public int r;
    
    public l(final Image f, final Image g, final Image h, final Container o, final d q, final int l, final int m) {
        this.a = false;
        this.b = false;
        this.i = true;
        this.l = 2;
        this.m = 80;
        this.n = 2;
        this.q = null;
        this.r = 0;
        this.q = q;
        this.o = o;
        while (this.o != null && !(this.o instanceof Frame)) {
            this.o = this.o.getParent();
        }
        this.p = (Frame)this.o;
        this.f = f;
        this.g = g;
        this.h = h;
        this.n = this.g.getWidth(null) / 2;
        if (l >= 0 && m > 0) {
            this.l = l;
            this.m = m;
        }
        else {
            this.l = 2 + this.n;
            this.m = this.f.getWidth(null) - this.l - this.l;
        }
    }
    
    public void a(final float n) {
        this.d = (int)(n * this.m);
        this.repaint();
    }
    
    public void a(final int n, final int r, final String j) {
        if (!this.a) {
            this.r = r;
            this.e = n * this.m / r;
            if (this.q != null) {
                this.q.j = j;
            }
            this.repaint();
        }
    }
    
    public double a() {
        return this.e / this.m;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.f == null) {
                return;
            }
            final Image image = this.createImage(this.f.getWidth(this), this.f.getHeight(this));
            final Graphics graphics2 = image.getGraphics();
            graphics2.drawImage(this.f, 0, 0, null);
            if (this.i && this.h != null) {
                final Image image2 = this.createImage(this.l + this.d, this.f.getHeight(this));
                final Graphics graphics3 = image2.getGraphics();
                graphics3.drawImage(this.h, 0, 0, null);
                graphics2.drawImage(image2, 0, 0, null);
                graphics3.dispose();
                image2.flush();
            }
            if (this.i && this.g != null) {
                graphics2.drawImage(this.g, this.l - this.n + this.e, 0, null);
            }
            graphics.drawImage(image, 0, 0, null);
            graphics2.dispose();
            image.flush();
        }
        catch (Exception ex) {}
    }
    
    public void setName(final String c) {
        this.c = c;
    }
    
    public String getName() {
        return this.c;
    }
    
    public void enable() {
        this.i = true;
        this.repaint();
    }
    
    public void disable() {
        this.i = false;
        this.repaint();
    }
    
    public Dimension size() {
        return new Dimension(this.f.getWidth(this), this.f.getHeight(this));
    }
    
    private void c() {
        if (this.a) {
            this.e = this.j - 5;
            if (this.e < 0) {
                this.e = 0;
            }
            if (this.e >= this.m) {
                this.e = this.m - 1;
            }
            final Graphics graphics = this.getGraphics();
            this.paint(graphics);
            graphics.dispose();
            final String string = c.d((int)(this.a() * this.r)) + "/" + c.d(this.r);
            if (this.q != null) {
                this.q.j = string;
            }
        }
    }
    
    public synchronized boolean mouseDown(final Event event, final int j, final int k) {
        this.j = j;
        this.k = k;
        if (!event.metaDown()) {
            this.a = true;
            this.mouseDrag(event, j, k);
            if (this.i) {
                this.c();
                this.p.setCursor(11);
            }
        }
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, final int j, final int k) {
        this.j = j;
        this.k = k;
        if (event.metaDown()) {
            this.postEvent(new Event(this, 0L, 1001, j, k, 0, 4));
        }
        else {
            this.postEvent(new Event(this, 1001, this.c));
            if (this.i) {
                this.p.setCursor(12);
            }
            if (this.q != null) {
                this.q.f = false;
                this.q.j = null;
                this.q.repaint();
            }
            this.a = false;
            this.repaint();
        }
        return true;
    }
    
    public synchronized boolean mouseEnter(final Event event, final int j, final int k) {
        this.j = j;
        this.k = k;
        this.b = true;
        this.repaint();
        if (this.i) {
            this.p.setCursor(12);
            if (this.q != null) {
                this.q.f = true;
                this.q.repaint();
            }
        }
        return true;
    }
    
    public synchronized boolean mouseDrag(final Event event, final int j, final int k) {
        this.j = j;
        this.k = k;
        if (!event.metaDown() && this.i) {
            this.c();
            if (this.q != null) {
                this.q.f = true;
            }
        }
        return true;
    }
    
    public synchronized boolean mouseExit(final Event event, final int j, final int k) {
        this.j = j;
        this.k = k;
        this.b = false;
        this.repaint();
        if (this.i) {
            this.p.setCursor(0);
            if (this.q != null) {
                this.q.f = false;
                this.q.j = null;
                this.q.repaint();
            }
        }
        return true;
    }
    
    public synchronized void b() {
        this.p.hide();
        this.p.show();
    }
}
