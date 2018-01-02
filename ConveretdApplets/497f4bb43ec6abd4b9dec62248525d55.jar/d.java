import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

final class d
{
    final int s = 6;
    final int t = 13;
    private ChessBoard u;
    private int v;
    private boolean w;
    private char[] x;
    private int y;
    private int z;
    private char A;
    private Point B;
    private int C;
    private int D;
    private int E;
    private int F;
    private Point[] G;
    private Point H;
    private Point I;
    private Point J;
    private Thread K;
    private int L;
    private Object M;
    
    public d(final ChessBoard u, final int c, final int d, final int e, final int f) {
        this.u = u;
        this.C = c;
        this.D = d;
        this.E = e;
        this.F = f;
        this.B();
        this.G = new Point[13];
        this.J = new Point();
        this.K = null;
    }
    
    public boolean v() {
        return this.w;
    }
    
    public boolean b(final int n) {
        return (this.v & n) != 0x0;
    }
    
    public void w() {
        this.L = 1;
        (this.K = new Thread(this.u, "AnimationThreadName")).start();
    }
    
    public Thread x() {
        return this.K;
    }
    
    public void y() {
        synchronized (this.G) {
            --this.L;
            while (this.L == 0) {
                this.G.wait();
            }
            this.L = 1;
        }
    }
    
    public void z() {
        synchronized (this.G) {
            if (this.L++ == 0) {
                this.G.notify();
            }
        }
    }
    
    public void A() {
        final Point o = this.u.o(this.y);
        final Point o2 = this.u.o(this.z);
        final Point point = new Point();
        if (Character.toUpperCase(this.A) != 'N') {
            point.x = o.x + (o2.x - o.x) / 2;
            point.y = o.y + (o2.y - o.y) / 2;
        }
        else if ((this.y - this.z & 0x1) == 0x0) {
            point.x = o.x + (o2.x - o.x) / 2;
            point.y = o.y;
        }
        else {
            point.x = o.x;
            point.y = o.y + (o2.y - o.y) / 2;
        }
        for (int i = 0; i < 6; ++i) {
            this.G[i] = new Point(o.x + (point.x - o.x >> 6 - i - 1), o.y + (point.y - o.y >> 6 - i - 1));
            this.G[6 + i] = new Point(o2.x - (o2.x - point.x >> i + 1), o2.y - (o2.y - point.y >> i + 1));
        }
        this.G[12] = o2;
        synchronized (this) {
            this.w = true;
            this.H = null;
            long currentTimeMillis = System.currentTimeMillis();
            int n = 0;
            while (true) {
                this.I = this.H;
                final int n2 = (int)(System.currentTimeMillis() - currentTimeMillis) / 25;
                if (n2 > 0) {
                    currentTimeMillis += 25 * n2;
                    n += n2;
                }
                if (n >= 13) {
                    break;
                }
                this.H = this.G[n];
                currentTimeMillis += 25L;
                final int n3 = (int)(currentTimeMillis - System.currentTimeMillis());
                if (n3 > 0) {
                    Thread.sleep(n3);
                }
                this.u.repaint();
                this.wait();
                ++n;
            }
            this.w = false;
        }
    }
    
    private boolean a(final Graphics graphics, final Rectangle rectangle, final int n) {
        final Point o = this.u.o(n);
        this.u.a(graphics, n, this.x, o);
        rectangle.add(new Rectangle(o.x, o.y, this.C, this.D));
        return n == this.y;
    }
    
    public synchronized Rectangle a(final Graphics graphics) {
        final Rectangle rectangle = new Rectangle(this.H.x, this.H.y, this.E, this.F);
        boolean b5;
        if (this.I != null) {
            final int b = this.u.b(this.I.x, this.I.y);
            final int b2 = this.u.b(this.I.x + this.E, this.I.y);
            final int b3 = this.u.b(this.I.x, this.I.y + this.F);
            final int b4 = this.u.b(this.I.x + this.E, this.I.y + this.F);
            b5 = this.a(graphics, rectangle, b);
            if (b2 != b) {
                b5 |= this.a(graphics, rectangle, b2);
            }
            if (b3 != b) {
                b5 |= this.a(graphics, rectangle, b3);
            }
            if (b4 != b3 && b4 != b2) {
                b5 |= this.a(graphics, rectangle, b4);
            }
        }
        else {
            b5 = this.a(graphics, rectangle, this.y);
        }
        if (b5) {
            this.u.a(graphics, this.B.x + 1, this.B.y + 1, this.C - 2, this.D - 2, false);
        }
        this.u.a(graphics, this.A, this.H.x, this.H.y);
        this.notify();
        return rectangle;
    }
    
    public synchronized boolean a(final b b) {
        if (b.g() || b.h()) {
            return false;
        }
        this.v = 1;
        this.x = b.e().clone();
        this.y = b.a();
        this.z = b.b();
        this.A = this.x[this.y];
        this.x[this.y] = '-';
        this.B = this.u.o(this.y);
        this.M = null;
        return true;
    }
    
    public synchronized boolean a(final Point point, final char[] array) {
        if (this.b(3)) {
            return false;
        }
        this.y = this.u.b(point.x, point.y);
        if (this.y == -1) {
            return false;
        }
        if (array[this.y] == '-') {
            return false;
        }
        this.x = array.clone();
        this.A = this.x[this.y];
        this.x[this.y] = '-';
        this.H = this.u.o(this.y);
        this.I = null;
        this.B = new Point(this.H.x, this.H.y);
        this.J.x = point.x - this.H.x;
        this.J.y = point.y - this.H.y;
        this.v = 2;
        return this.w = true;
    }
    
    public void a(final Point point, final int n, final int n2, int n3, int n4) {
        int n5 = point.x - this.J.x;
        int n6 = point.y - this.J.y;
        if (n5 < n) {
            n5 = n;
        }
        n3 -= 2 * n + this.E / 2;
        if (n5 > n3) {
            n5 = n3;
        }
        if (n6 < n2) {
            n6 = n2;
        }
        n4 -= 2 * n2 + this.F / 2;
        if (n6 > n4) {
            n6 = n4;
        }
        this.I = this.H;
        this.H = new Point(n5, n6);
    }
    
    public synchronized void B() {
        this.v = 0;
        this.w = false;
    }
    
    public int a() {
        return this.y;
    }
    
    public int b() {
        return this.u.b(this.H.x + this.E / 2 + 2, this.H.y + this.F / 2 + 2);
    }
    
    public boolean a(final Object m) {
        if (!this.b(3)) {
            return true;
        }
        if (this.M == null) {
            this.M = m;
        }
        return false;
    }
    
    public void a(final e e) {
        if (this.M == null) {
            return;
        }
        if (this.M instanceof Label) {
            final Label label = (Label)this.M;
            final Point location = label.getLocation();
            this.u.mousePressed(new MouseEvent(label, 501, System.currentTimeMillis(), 0, location.x + 2, location.y + 2, 1, false));
        }
        else if (this.M instanceof Integer) {
            e.k((int)this.M);
            this.u.repaint();
        }
        else {
            this.u.actionPerformed((ActionEvent)this.M);
        }
    }
}
