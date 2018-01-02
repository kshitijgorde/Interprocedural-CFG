import com.daysofwonder.applet.aC;
import java.awt.Color;
import com.daysofwonder.applet.aE;
import java.awt.Point;
import com.daysofwonder.util.t;
import java.awt.event.MouseEvent;
import com.daysofwonder.tt.i;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import java.awt.Rectangle;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;

// 
// Decompiled by Procyon v0.5.30
// 

public class O extends am
{
    private G a;
    private z b;
    private b c;
    private b d;
    private Rectangle e;
    private Rectangle f;
    private int g;
    
    public O(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.b = (z)ap;
        this.e = aL.a(uiProperties, s + ".deck.r");
        this.f = aL.a(uiProperties, s + ".table.r");
        this.d = ap.c(uiProperties.a(s + ".deck"));
        this.g = Integer.parseInt(uiProperties.a(s + ".card.h"));
        this.a = this.b.n();
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.c == null) {
                this.c = this.N.b(this.G);
            }
            a.a(this.c, this.G.x, this.G.y, null);
            if (!this.a.Q()) {
                a.a(this.d, this.e.x, this.e.y, null);
            }
            final i[] d = this.a.D();
            if (d != null) {
                int y = this.f.y;
                final int n = (this.f.height - d.length * this.g) / (d.length - 1);
                for (int i = 0; i < d.length; ++i) {
                    if (d[i] != null) {
                        a.a(this.b.a(d[i].a()), this.f.x, y, null);
                    }
                    y += n + this.g;
                }
            }
        }
    }
    
    public void a() {
        if (this.c != null) {
            this.c.c();
            this.c = null;
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
    
    public void b(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !this.a.aA() && this.a.i() && (this.a.o() || this.a.p())) {
            t.a("double-click at " + System.currentTimeMillis());
            final int f = this.f(mouseEvent.getPoint());
            if (f != -1) {
                t.a("found card at " + System.currentTimeMillis());
                synchronized (this.a.ab()) {
                    t.a("got lock at " + System.currentTimeMillis());
                    final i i = this.a.D()[f];
                    if (i != null && (!i.c() || !this.a.E())) {
                        i.b(f);
                        t.a("remove card at " + System.currentTimeMillis());
                        this.a.a(i);
                        t.a("draw card at " + System.currentTimeMillis());
                        this.a.a(this.a.g(), i, true);
                        t.a("end draw card at " + System.currentTimeMillis());
                    }
                }
            }
            else if (!this.a.Q() && this.e.contains(mouseEvent.getPoint())) {
                this.a.a(this.a.g(), i.a, true);
            }
            this.N.A();
        }
    }
    
    public boolean b(final Point point) {
        if (!this.a.aA() && this.a.i() && (this.a.o() || this.a.p())) {
            final int f = this.f(point);
            if (f != -1) {
                synchronized (this.a.ab()) {
                    final i i = this.a.D()[f];
                    if (i != null && (!i.c() || !this.a.E())) {
                        return true;
                    }
                }
            }
            else if (!this.a.Q() && this.e.contains(point)) {
                return true;
            }
        }
        return false;
    }
    
    public Object c(final Point point) {
        if (!this.e.contains(point)) {
            synchronized (this.a.ab()) {
                final i[] d = this.a.D();
                final int f = this.f(point);
                if (f != -1 && d[f] != null && (!d[f].c() || !this.a.E())) {
                    return d[f];
                }
            }
            return null;
        }
        return i.a;
    }
    
    public String d(final Point point) {
        return "deck";
    }
    
    public aE a(final com.daysofwonder.applet.z z, final Point point, final Object o, final String s) {
        if (o instanceof i) {
            final i i = (i)o;
            final Rectangle a = this.a(i);
            if (a != null) {
                if (i != com.daysofwonder.tt.i.a) {
                    i.b(this.f(point));
                    this.a.a(i);
                }
                final int n = point.x - a.x;
                final int n2 = point.y - a.y;
                this.a.h(8);
                return new av(i, this.b, n, n2);
            }
        }
        return null;
    }
    
    public boolean a(final Point point, final Object o, final String s) {
        if (!this.a.aA() && o instanceof i) {
            final i i = (i)o;
            if (i.e() >= 0 || i == com.daysofwonder.tt.i.a) {
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
        if (o instanceof i && o != i.a) {
            this.a.h(9);
            this.a.b((i)o);
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final Point point, final Point point2, final aE ae) {
        if (o instanceof i) {
            final i i = (i)o;
            final int a = this.b.a(1).a(null);
            final int b = this.b.a(1).b(null);
            int b2 = 0;
            int c = 0;
            if (ae instanceof av) {
                final av av = (av)ae;
                b2 = av.b();
                c = av.c();
            }
            if (z != null) {
                aL.a(z, 10, this.b.r(), new Rectangle(point2.x - b2, point2.y - c, a, b), new Rectangle(point.x - b2, point.y - c, a, b));
                this.a.h(9);
                if (i != com.daysofwonder.tt.i.a) {
                    this.a.b(i);
                }
            }
            else {
                this.a.h(9);
                try {
                    this.a.a(new k(this.b.a(i.a()), point2.x - b2, point2.y - c, point.x - b2, point.y - c, new j(this, i)));
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public Rectangle b() {
        return this.e;
    }
    
    public Rectangle d() {
        return this.f;
    }
    
    public b e() {
        return this.c;
    }
    
    private int f(final Point point) {
        final i[] d = this.a.D();
        if (d != null) {
            final int n = (this.f.height - d.length * this.g) / (d.length - 1);
            final Rectangle rectangle = new Rectangle();
            rectangle.x = this.f.x;
            rectangle.y = this.f.y;
            rectangle.width = this.b.a(1).a(null);
            rectangle.height = this.b.a(1).b(null);
            for (int i = 0; i < d.length; ++i) {
                if (d[i] != null && rectangle.contains(point)) {
                    return i;
                }
                final Rectangle rectangle2 = rectangle;
                rectangle2.y += n + this.g;
            }
        }
        return -1;
    }
    
    private Rectangle a(final i i) {
        final i[] d = this.a.D();
        if (d != null) {
            final int n = (this.f.height - d.length * this.g) / (d.length - 1);
            final Rectangle rectangle = new Rectangle();
            rectangle.x = this.f.x;
            rectangle.y = this.f.y;
            rectangle.width = this.b.a(1).a(null);
            rectangle.height = this.b.a(1).b(null);
            for (int j = 0; j < d.length; ++j) {
                if (d[j] == i) {
                    return rectangle;
                }
                final Rectangle rectangle2 = rectangle;
                rectangle2.y += n + this.g;
            }
            if (i == i.a) {
                return this.e;
            }
        }
        return null;
    }
    
    public String a(final Point point) {
        final i[] d = this.a.D();
        if (d == null) {
            return this.N.i() ? super.a(point) : null;
        }
        final int f = this.f(point);
        if (f >= 0 && f < d.length && d[f] != null) {
            return this.O.b("card." + d[f].b());
        }
        return this.N.i() ? super.a(point) : null;
    }
    
    public Point e(final Point point) {
        final i[] d = this.a.D();
        if (d == null) {
            return super.e(point);
        }
        final int f = this.f(point);
        if (f >= 0 && f < d.length) {
            final Rectangle a = this.a(d[f]);
            final Point location = a.getLocation();
            location.translate(a.width + 10, a.height / 2);
            return location;
        }
        return this.N.i() ? super.e(point) : null;
    }
}
