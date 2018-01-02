import java.awt.Component;
import java.awt.Point;
import java.awt.Color;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.io.PrintStream;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends Canvas implements Runnable
{
    PrintStream h;
    Thread c;
    int p;
    int s;
    boolean r;
    Graphics l;
    Image g;
    Graphics q;
    h k;
    k a;
    e f;
    j d;
    Vector j;
    Vector o;
    boolean m;
    Image b;
    d e;
    int i;
    int n;
    
    public void a(final boolean m) {
        this.m = m;
    }
    
    public void a(final h h, final b b, final boolean b2, final double n, final double n2) {
        if (h == null) {
            this.h.println("NULL _test_elem ???");
            b.b = true;
            return;
        }
        final double n3 = 0.0;
        h.k = n3;
        h.p = n3;
        final Enumeration<d> elements = (Enumeration<d>)this.j.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(h, b);
            if (b.b) {
                break;
            }
        }
        if (b.b) {
            return;
        }
        if (b2) {
            h.m = n + 0.05 * h.p;
            h.e = n2 + 0.05 * h.k;
        }
        else {
            h.m += h.p * 0.1;
            h.e += h.k * 0.1;
        }
        h.n += h.m * 0.1;
        h.o += h.e * 0.1;
    }
    
    public void a(final d d) {
        this.j.addElement(d);
    }
    
    public void update(final Graphics q) {
        if (this.g == null) {
            this.g = this.createImage(this.p, this.s);
            this.l = this.g.getGraphics();
        }
        this.q = q;
        this.paint(this.l);
        q.drawImage(this.g, 0, 0, this);
    }
    
    public d c(final int n, final int n2) {
        for (int i = this.j.size() - 1; i >= 0; --i) {
            final d d = this.j.elementAt(i);
            if ((this.r || d instanceof c) && d.a(n, n2)) {
                return d;
            }
        }
        return null;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.c != null && this.c.isAlive()) {
            return true;
        }
        if (this.e != null) {
            this.e.b(false);
            this.e = null;
            return super.mouseDown(event, n, n2);
        }
        final d c = this.c(n, n2);
        if (c != null && c.l) {
            (this.e = c).b();
            this.repaint();
        }
        return super.mouseDown(event, n, n2);
    }
    
    public boolean a(final int n, final int n2) {
        final int n3 = 40;
        boolean b = false;
        if (n < n3 || n > this.p - n3 || n2 < n3 || n2 > this.s - n3) {
            b = true;
        }
        return b;
    }
    
    public boolean b(final int n, final int n2) {
        final int abs = Math.abs(this.d.d - n);
        final int abs2 = Math.abs(this.d.j - n2);
        boolean b = false;
        if (abs < 50 && abs2 < 50) {
            b = true;
        }
        return b;
    }
    
    public boolean mouseMove(final Event event, final int d, final int j) {
        if (this.c != null && this.c.isAlive()) {
            return true;
        }
        if (this.e != null && !this.b(d, j) && !this.a(d, j)) {
            this.e.d = d;
            this.e.j = j;
            this.repaint();
        }
        return super.mouseMove(event, d, j);
    }
    
    public void paint(final Graphics graphics) {
        if (this.b != null) {
            graphics.drawImage(this.b, 0, 0, this);
        }
        Point point = null;
        graphics.setColor(Color.gray);
        final Enumeration<Point> elements = this.o.elements();
        while (elements.hasMoreElements()) {
            final Point point2 = elements.nextElement();
            if (point != null) {
                graphics.drawLine(point.x, point.y, point2.x, point2.y);
            }
            point = point2;
        }
        final Enumeration<d> elements2 = this.j.elements();
        while (elements2.hasMoreElements()) {
            elements2.nextElement().a(this, graphics);
        }
        if (this.k != null) {
            this.k.a(this, graphics);
        }
    }
    
    public void a() {
        (this.c = new Thread(this, "launch")).start();
    }
    
    public void run() {
        if (Thread.currentThread().getName().equals("launch")) {
            this.k = new h(this.d.d, this.d.j);
            this.o.removeAllElements();
            final b b = new b();
            this.o.addElement(new Point(this.k.d, this.k.j));
            this.k.n = this.k.d;
            this.k.o = this.k.j;
            int n = 2000;
            while (--n >= 0) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
                final h k = this.k;
                final b b2 = b;
                boolean b3 = false;
                if (this.o.size() == 1) {
                    b3 = true;
                }
                this.a(k, b2, b3, this.d.k, this.d.e);
                if (b.a) {}
                if (b.b) {
                    break;
                }
                this.k.c((int)(this.k.n + 0.5), (int)(this.k.o + 0.5));
                this.o.addElement(new Point(this.k.d, this.k.j));
                this.repaint();
                if (this.k.d < 10 || this.k.j < 10 || this.k.d > this.p - 10 || this.k.j > this.s - 10) {
                    b.c = "Oops, your charge left the screen!";
                    break;
                }
            }
            if (b.d == this.a) {
                this.a.a(Color.green);
                b.c = "Yay! You Win a Point!";
                this.d.d = this.p / 2;
                this.d.j = this.s / 2;
                this.d.a();
                ++this.i;
            }
            else if (b.d == this.f) {
                this.f.a(Color.green);
                b.c = "Rats! The Computer Gets a Point!";
                this.d.d = this.p / 2;
                this.d.j = this.s / 2;
                this.d.a();
                ++this.n;
            }
            else if (n <= 0) {
                b.c = "Out of Time! Start again in the middle.";
                this.d.d = this.p / 2;
                this.d.j = this.s / 2;
                this.d.a();
            }
            else {
                this.d.k = this.k.m;
                this.d.e = this.k.e;
                this.d.d = this.k.d;
                this.d.j = this.k.j;
                if (this.k.d < 10) {
                    b.c = "Bounce off West wall";
                    this.d.k = -this.k.m;
                    this.d.c();
                    this.d.d = 11;
                }
                else if (this.k.j < 10) {
                    b.c = "Bounce off North wall";
                    this.d.e = -this.k.e;
                    this.d.c();
                    this.d.j = 11;
                }
                if (this.k.d > this.p - 10) {
                    b.c = "Bounce off East wall";
                    this.d.k = -this.k.m;
                    this.d.c();
                    this.d.d = this.p - 10 - 1;
                }
                else if (this.k.j > this.s - 10) {
                    b.c = "Bounce off South wall";
                    this.d.e = -this.k.e;
                    this.d.c();
                    this.d.j = this.s - 10 - 1;
                }
            }
            this.postEvent(new Event(this, 1001, b));
            this.k = null;
            this.repaint();
        }
    }
    
    private final void b() {
        this.h = System.err;
        this.j = new Vector(100);
        this.o = new Vector(1000);
        this.i = 0;
        this.n = 0;
    }
    
    public i(final int p4, final int s, final boolean r, final Image b) {
        this.b();
        this.p = p4;
        this.s = s;
        this.b = b;
        this.r = r;
        this.a(true);
    }
}
