import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.PrintStream;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Canvas implements Runnable
{
    public static final PrintStream c;
    public static int g;
    public int n;
    public int p;
    public int w;
    private boolean t;
    private Rectangle b;
    private Rectangle f;
    private Rectangle a;
    private Rectangle h;
    private int d;
    private int q;
    private int m;
    private int k;
    private Polygon i;
    private Polygon r;
    private boolean o;
    private Point j;
    private int l;
    private Thread s;
    private int e;
    private boolean u;
    private Font v;
    
    public int b() {
        return this.k;
    }
    
    public void a(final int n) {
        this.a(n, true);
    }
    
    public void a(final int d, final int q) {
        if (d <= q) {
            this.d = d;
            this.q = q;
            this.m = this.q - this.d + 1;
            this.a(this.k, false);
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.s != null && this.s.isAlive()) {
            this.s.stop();
        }
        this.e = 0;
        this.o = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseDrag(final Event event, int x, int y) {
        if (!this.o) {
            return true;
        }
        if (x == this.j.x && y == this.j.y) {
            return true;
        }
        if (this.t) {
            y -= this.l;
            this.a(this.d + (y - this.a.y) / (this.a.height - a.g) * this.m, true);
        }
        else {
            x -= this.l;
            this.a(this.d + (x - this.a.x) / (this.a.width - a.g) * this.m, true);
        }
        this.j.x = x;
        this.j.y = y;
        return true;
    }
    
    public void run() {
        int n = 500;
        while (true) {
            this.a(this.k + this.e, true);
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
            n = 100;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.e = 0;
        if (this.h.inside(n, n2)) {
            this.l = n2 - this.h.y;
            this.o = true;
        }
        else if (this.b.inside(n, n2) || this.f.inside(n, n2)) {
            this.e = (this.b.inside(n, n2) ? -1 : 1);
            if (this.s != null && this.s.isAlive()) {
                this.s.stop();
            }
            (this.s = new Thread(this)).start();
        }
        else {
            this.e = ((this.n > 0) ? this.n : Math.max(2, this.m / 10));
            if (this.t) {
                if (n2 < this.h.y) {
                    this.e = -this.e;
                }
            }
            else if (n < this.h.x) {
                this.e = -this.e;
            }
            if (this.s != null && this.s.isAlive()) {
                this.s.stop();
            }
            (this.s = new Thread(this)).start();
        }
        return true;
    }
    
    private final void a(int k, boolean b) {
        if (k < this.d) {
            k = this.d;
        }
        if (k > this.q) {
            k = this.q;
        }
        if (this.k == k) {
            b = false;
        }
        this.k = k;
        if (b) {
            this.postEvent(new Event(this, 1001, new Point(1, k)));
        }
        final double n = (this.k - this.d) / this.m;
        if (this.t) {
            this.h.move(this.a.x, this.a.y + (int)(n * (this.a.height - this.h.height)));
        }
        else {
            this.h.move(this.a.x + (int)(n * (this.a.width - this.h.width)), this.a.y);
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.gray);
        graphics.fillRect(this.a.x, this.a.y, this.a.width - 1, this.a.height - 1);
        graphics.setColor(Color.lightGray);
        graphics.fillRect(this.b.x, this.b.y, this.b.width - 1, this.b.height - 1);
        graphics.fillRect(this.f.x, this.f.y, this.f.width - 1, this.f.height - 1);
        graphics.setColor(Color.darkGray);
        graphics.fillRect(this.h.x, this.h.y, this.h.width - 1, this.h.height - 1);
        graphics.setColor((this.e < 0) ? Color.red : Color.darkGray);
        graphics.fillPolygon(this.i);
        graphics.setColor((this.e > 0) ? Color.red : Color.darkGray);
        graphics.fillPolygon(this.r);
        graphics.setColor(Color.black);
        graphics.drawRect(this.a.x, this.a.y, this.a.width - 1, this.a.height - 1);
        graphics.drawRect(this.b.x, this.b.y, this.b.width - 1, this.b.height - 1);
        graphics.drawRect(this.f.x, this.f.y, this.f.width - 1, this.f.height - 1);
        graphics.setColor(this.o ? Color.red : Color.black);
        graphics.drawRect(this.h.x, this.h.y, this.h.width - 1, this.h.height - 1);
        if (this.u) {
            graphics.setColor(Color.white);
            graphics.setFont(this.v);
            a(graphics, Integer.toString(this.k), this.h.x, this.h.y + this.h.height - 4, this.h.width);
        }
    }
    
    public static void a(final Graphics graphics, final String s, int n, final int n2, final int n3) {
        n += n3 - graphics.getFontMetrics().stringWidth(s) >> 1;
        graphics.drawString(s, n, n2);
    }
    
    private final void a() {
        this.n = -1;
        this.j = new Point(0, 0);
        this.v = new Font("Dialog", 1, 9);
    }
    
    public a(final boolean t, final int p4, final int w, final boolean u) {
        this.a();
        this.p = p4;
        this.w = w;
        this.resize(this.p, this.w);
        this.t = t;
        this.u = u;
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        if (this.t) {
            this.b = new Rectangle(0, 0, p4, a.g);
            this.f = new Rectangle(0, w - a.g, p4, a.g);
            this.a = new Rectangle(0, a.g, p4, w - 2 * a.g);
            this.h = new Rectangle(0, 0, a.g, a.g);
            array[0] = this.b.x + this.b.width / 2;
            array2[0] = this.b.y + 3;
            array[1] = this.b.x + 3;
            array2[1] = this.b.y + this.b.height - 3;
            array[2] = this.b.x + this.b.width - 3;
            array2[2] = array2[1];
            this.i = new Polygon(array, array2, 3);
            array[0] = this.f.x + this.f.width / 2;
            array2[0] = this.f.y + this.f.height - 3;
            array[1] = this.f.x + 3;
            array2[1] = this.f.y + 3;
            array[2] = this.f.x + this.f.width - 3;
            array2[2] = array2[1];
            this.r = new Polygon(array, array2, 3);
        }
        else {
            this.b = new Rectangle(0, 0, a.g, w);
            this.f = new Rectangle(p4 - a.g, 0, a.g, w);
            this.a = new Rectangle(a.g, 0, p4 - 2 * a.g, w);
            this.h = new Rectangle(0, 0, a.g + a.g, a.g);
            array[0] = this.b.x + 3;
            array2[0] = this.b.y + this.b.height / 2;
            array[1] = this.b.x + this.b.width - 3;
            array2[1] = this.b.y + 3;
            array[2] = array[1];
            array2[2] = this.b.y + this.b.height - 3;
            this.i = new Polygon(array, array2, 3);
            array[0] = this.f.x + this.f.width - 3;
            array2[0] = this.f.y + this.f.height / 2;
            array[1] = this.f.x + 3;
            array2[1] = this.f.y + 3;
            array[2] = array[1];
            array2[2] = this.f.y + this.f.height - 3;
            this.r = new Polygon(array, array2, 3);
        }
        final int n = this.t ? w : p4;
        if (n < 50) {
            this.h.move(1000, 1000);
            if (n <= 32) {
                this.a.move(1000, 1000);
            }
        }
        this.a(this.k, false);
    }
    
    static {
        c = System.out;
        a.g = 16;
    }
}
