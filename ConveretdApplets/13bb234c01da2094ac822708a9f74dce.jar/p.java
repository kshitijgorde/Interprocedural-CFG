import com.daysofwonder.applet.aC;
import com.daysofwonder.applet.y;
import com.daysofwonder.applet.aE;
import com.daysofwonder.a.o;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import com.daysofwonder.tt.n;
import com.daysofwonder.tt.i;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import java.awt.Color;
import com.daysofwonder.b.b;
import com.daysofwonder.tt.d;
import com.daysofwonder.applet.am;

// 
// Decompiled by Procyon v0.5.30
// 

public class p extends am
{
    private static final int[] a;
    private G b;
    private d c;
    private z d;
    private b e;
    private com.daysofwonder.util.G[] f;
    private Color g;
    
    public p(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.f = new com.daysofwonder.util.G[10];
        this.g = Color.black;
        this.d = (z)ap;
        this.b = this.d.n();
        for (int i = 0; i < this.f.length; ++i) {
            this.f[i] = new com.daysofwonder.util.G();
        }
        if (uiProperties.a(s + ".color") != null) {
            this.g = aL.b(uiProperties, s + ".color");
        }
    }
    
    public synchronized void a(final a a) {
        if (this.K) {
            if (this.e == null) {
                this.e = this.N.b(this.G);
            }
            a.a(this.e, this.G.x, this.G.y, null);
            if (this.c == null) {
                this.c = this.b.g();
            }
            if (this.c == null) {
                return;
            }
            if (this.b.u()) {
                final Color c = a.c();
                a.a(this.g);
                aL.a(a, this.G, this.O.b("click.to.continue.longest"), this.N.N(), 0);
                a.a(c);
            }
            else if (this.b.t()) {
                final Color c2 = a.c();
                a.a(this.g);
                aL.a(a, this.G, this.O.b("click.to.continue"), this.N.N(), 0);
                a.a(c2);
            }
            else if (this.b.v()) {
                final Color c3 = a.c();
                a.a(this.g);
                aL.a(a, this.G, this.O.b("click.to.continue.stations"), this.N.N(), 0);
                a.a(c3);
            }
            else {
                this.d();
                if (this.b.ad()) {
                    this.b(a);
                }
                if (this.b.r()) {
                    final Color c4 = a.c();
                    a.a(this.g);
                    aL.a(a, this.G, String.format(this.O.b("first.player"), this.b.h().z()), this.N.N(), 2);
                    a.a(c4);
                }
            }
        }
    }
    
    public void a() {
        if (this.e != null) {
            this.e.c();
            this.e = null;
        }
    }
    
    private synchronized void d() {
        final n l = this.c.l();
        for (int i = 0; i < this.f.length; ++i) {
            this.f[i].b();
        }
        if (l != null && l.d() > 0) {
            for (int j = 0; j < l.d(); ++j) {
                final i k = (i)l.a(j);
                this.f[this.f.length - k.b()].c(k);
            }
        }
    }
    
    private void b(final a a) {
        final int c = this.c();
        int x = this.G.x;
        final int y = this.G.y;
        for (int i = 0; i < this.f.length; ++i) {
            final com.daysofwonder.util.G g = this.f[i];
            final int n = (g.a() < p.a.length) ? p.a[g.a()] : p.a[p.a.length - 1];
            final boolean b = g.a() > 4;
            final int n2 = x;
            for (int j = 0; j < g.a(); ++j) {
                a.a(this.d.a(((i)g.b(j)).a()), x, y, null);
                x += n;
            }
            if (b) {
                aL.a(a, g.a(), new Rectangle(n2, y + 8, x - n2 - 2 * n, this.G.height), null, this.d.l());
            }
            if (g.a() > 0) {
                x += c;
            }
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
    
    public void c(final MouseEvent mouseEvent) {
        if (!this.N.i()) {
            this.N.e(true);
        }
    }
    
    public void d(final MouseEvent mouseEvent) {
        if (!this.N.i()) {
            this.N.e(false);
        }
    }
    
    public void e(final MouseEvent mouseEvent) {
    }
    
    public void b(final MouseEvent mouseEvent) {
    }
    
    public String a(final Point point) {
        final i f = this.f(point);
        if (f != null) {
            return this.O.b("card." + f.b());
        }
        return this.N.i() ? super.a(point) : null;
    }
    
    public Point e(final Point point) {
        final Rectangle a = this.a(this.f(point));
        if (a != null) {
            final Point location = a.getLocation();
            location.translate(a.width / 2, a.height + 10);
            return location;
        }
        return null;
    }
    
    public boolean b(final Point point) {
        return !this.b.aA() && this.b.i() && this.b.o() && this.f(point) != null;
    }
    
    public Object c(final Point point) {
        return this.f(point);
    }
    
    public String d(final Point point) {
        if (this.f(point) != null) {
            return "player";
        }
        return "";
    }
    
    public aE a(final com.daysofwonder.applet.z z, final Point point, final Object o, final String s) {
        if (o instanceof i && this.c.c((i)o)) {
            final i i = (i)o;
            final Rectangle a = this.a(i);
            if (a != null) {
                System.out.println("getTracker: removing card " + i.f());
                this.c.a(i);
                this.b.h(8);
                return y.a(i, this.d, point.x - a.x, point.y - a.y);
            }
        }
        return null;
    }
    
    public boolean a(final Point point, final Object o, final am am, final String s) {
        if (!this.b.aA() && o instanceof i && this.G.contains(point)) {
            final i i = (i)o;
            if (am != this && this.b.i() && (this.b.o() || this.b.p()) && i != null && (!i.c() || !this.b.E())) {
                return true;
            }
            if (am == this) {
                return true;
            }
        }
        return false;
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final am am, final Point point, final int n, final boolean b, final boolean b2) {
        if (z != null && !b2 && (b || n == 0)) {
            z.a(Color.black);
            z.b(Color.white);
            z.a(this.G.x - 1, this.G.y - 1, this.G.width + 2, this.G.height + 2);
            z.a(this.G.x - 2, this.G.y - 2, this.G.width + 4, this.G.height + 4);
            z.a(this.G.x - 3, this.G.y - 3, this.G.width + 6, this.G.height + 6);
            z.b();
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final am am, final Point point) {
        if (o instanceof i) {
            final i i = (i)o;
            if (this.G.contains(point) && am != this) {
                this.b.h(9);
                if (i.e() != -1 || i == com.daysofwonder.tt.i.a) {
                    this.b.a(this.c, i);
                }
            }
            else if (am == this) {
                this.b.h(9);
                this.c.b(i);
            }
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final Point point, final Point point2, final aE ae) {
        int b = 0;
        int c = 0;
        if (ae instanceof av) {
            final av av = (av)ae;
            b = av.b();
            c = av.c();
        }
        final int a = this.d.a(1).a(null);
        final int b2 = this.d.a(1).b(null);
        if (z != null) {
            z.f();
            aL.a(z, 10, this.d.r(), new Rectangle(point2.x - b, point2.y - c, a, b2), new Rectangle(point.x - b, point.y - c, a, b2));
            this.c.b((i)o);
        }
        else {
            try {
                this.b.a(new k(this.d.a(((i)o).a()), point2.x - b, point2.y - c, point.x - b, point.y - c, new am(this, o)));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private i f(final Point point) {
        i i = null;
        if (this.c != null && point != null) {
            this.d();
            final int c = this.c();
            final int a = this.d.a(1).a(null);
            final int b = this.d.a(1).b(null);
            final Rectangle rectangle = new Rectangle();
            rectangle.x = this.G.x;
            rectangle.y = this.G.y;
            rectangle.width = a;
            rectangle.height = b;
            for (int j = 0; j < this.f.length; ++j) {
                final com.daysofwonder.util.G g = this.f[j];
                final int n = (g.a() < p.a.length) ? p.a[g.a()] : p.a[p.a.length - 1];
                final boolean b2 = g.a() > 4;
                for (int k = 0; k < g.a(); ++k) {
                    final i l = (i)g.b(k);
                    if (rectangle.contains(point)) {
                        i = l;
                    }
                    final Rectangle rectangle2 = rectangle;
                    rectangle2.x += n;
                }
                if (g.a() > 0) {
                    final Rectangle rectangle3 = rectangle;
                    rectangle3.x += c;
                }
            }
        }
        return i;
    }
    
    private Rectangle a(final o o) {
        if (this.c != null) {
            this.d();
            final int c = this.c();
            final int a = this.d.a(1).a(null);
            final int b = this.d.a(1).b(null);
            final Rectangle rectangle = new Rectangle();
            rectangle.x = this.G.x;
            rectangle.y = this.G.y;
            rectangle.width = a;
            rectangle.height = b;
            for (int i = 0; i < this.f.length; ++i) {
                final com.daysofwonder.util.G g = this.f[i];
                final int n = (g.a() < p.a.length) ? p.a[g.a()] : p.a[p.a.length - 1];
                final boolean b2 = g.a() > 4;
                for (int j = 0; j < g.a(); ++j) {
                    if (g.b(j) == o) {
                        return rectangle;
                    }
                    final Rectangle rectangle2 = rectangle;
                    rectangle2.x += n;
                }
                if (g.a() > 0) {
                    final Rectangle rectangle3 = rectangle;
                    rectangle3.x += c;
                }
            }
        }
        return null;
    }
    
    public void b() {
        this.c = null;
    }
    
    public int c() {
        int n = 0;
        int n2 = 0;
        final int a = this.d.a(1).a(null);
        for (int i = 0; i < this.f.length; ++i) {
            final com.daysofwonder.util.G g = this.f[i];
            final int n3 = (g.a() < p.a.length) ? p.a[g.a()] : p.a[p.a.length - 1];
            final boolean b = g.a() > 4;
            n += g.a() * n3;
            if (g.a() > 0) {
                n += 20;
                ++n2;
            }
        }
        final int n4 = n + a;
        int n5 = 20;
        if (n4 >= this.G.width && n2 > 1) {
            final int n6 = n4 - this.G.width;
            --n2;
            n5 = (n5 * n2 - n6) / n2;
        }
        return n5;
    }
    
    static {
        a = new int[] { 20, 20, 20, 18, 15, 10, 5, 5, 5, 5, 3 };
    }
}
