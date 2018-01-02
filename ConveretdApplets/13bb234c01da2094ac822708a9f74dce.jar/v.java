import com.daysofwonder.applet.aC;
import java.awt.Color;
import com.daysofwonder.applet.y;
import com.daysofwonder.applet.aE;
import java.awt.Point;
import com.daysofwonder.applet.aH;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import com.daysofwonder.tt.f;
import java.awt.Rectangle;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;

// 
// Decompiled by Procyon v0.5.30
// 

public class v extends am
{
    private G a;
    private z b;
    private b c;
    private b d;
    private Rectangle e;
    private Rectangle f;
    private Rectangle g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private f l;
    private int m;
    
    public v(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.k = true;
        this.b = (z)ap;
        this.e = aL.a(uiProperties, s + ".deck.r");
        this.f = aL.a(uiProperties, s + ".tickets.r");
        this.g = aL.a(uiProperties, s + ".ticketsnb.r");
        this.d = ap.c(uiProperties.a(s + ".deck"));
        this.h = Integer.parseInt(uiProperties.a(s + ".card.h"));
        this.a = this.b.n();
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.c == null) {
                this.c = this.N.b(this.G);
            }
            a.a(this.c, this.G.x, this.G.y, null);
            if (this.a.L() > 0) {
                a.a(this.d, this.e.x, this.e.y, null);
                if (this.a.L() < 3 && this.g != null) {
                    aL.b(a, this.a.L(), this.g, null, this.b.l());
                }
            }
            if (this.a.g() != null && !this.a.r() && this.a.ad()) {
                final com.daysofwonder.util.G k = this.a.g().k();
                if (k != null) {
                    int n = this.a.Y() ? this.f.y : (this.f.y + this.f.height - this.h);
                    int n2 = 0;
                    if (k.a() > 1) {
                        n2 = (this.f.height - k.a() * this.h) / (k.a() - 1);
                    }
                    for (int i = 0; i < k.a(); ++i) {
                        final f f = (f)k.b((i + this.i) % k.a());
                        if (f != null) {
                            a.a(this.a.d(f.j()), this.f.x, n, null);
                        }
                        if (this.a.Y()) {
                            n += n2 + this.h;
                        }
                        else {
                            n -= n2 + this.h;
                        }
                    }
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
        if (mouseEvent.isControlDown() || (mouseEvent.getModifiers() & 0x4) == 0x4) {
            if (this.f.contains(mouseEvent.getPoint()) && !this.a.s()) {
                return this.N.a(new ap(this), mouseEvent);
            }
        }
        else if (this.f.contains(mouseEvent.getPoint()) && !this.a.s()) {
            return this.N.a(new x(this), mouseEvent);
        }
        return false;
    }
    
    public void b(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !this.a.aA() && this.a.i() && this.a.o()) {
            if (this.a.L() > 0 && this.e.contains(mouseEvent.getPoint())) {
                this.N.a(this.O.b("yesno.title.0"), this.O.b("yesno.text.0"), true, 2);
            }
            this.N.A();
        }
    }
    
    public boolean b(final Point point) {
        return !this.a.aA() && this.a.o() && this.a.i() && this.a.L() > 0 && this.e.contains(point);
    }
    
    public Object c(final Point point) {
        if (this.a.L() > 0 && this.e.contains(point)) {
            return com.daysofwonder.tt.f.a;
        }
        return null;
    }
    
    public String d(final Point point) {
        return "tickets";
    }
    
    public aE a(final com.daysofwonder.applet.z z, final Point point, final Object o, final String s) {
        if (o instanceof f) {
            final f f = (f)o;
            final int n = point.x - this.e.x;
            final int n2 = point.y - this.e.y;
            this.a.h(8);
            return y.a((f.j() != -1) ? this.a.d(f.j()) : this.d, this.b, n, n2);
        }
        return null;
    }
    
    public boolean a(final Point point, final Object o, final String s) {
        return !this.a.aA() && this.a.o() && this.a.i() && o instanceof f && o == com.daysofwonder.tt.f.a && this.f.contains(point);
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final am am, final Point point, final int n, final boolean b, final boolean b2) {
        if (z != null && !b2 && (b || n == 0)) {
            z.a(Color.black);
            z.b(Color.white);
            z.a(this.f.x - 1, this.f.y - 1, this.f.width + 2, this.f.height + 2);
            z.a(this.f.x - 2, this.f.y - 2, this.f.width + 4, this.f.height + 4);
            z.a(this.f.x - 3, this.f.y - 3, this.f.width + 6, this.f.height + 6);
            z.b();
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final am am, final Point point) {
        if (o instanceof f && this.f.contains(point)) {
            final String b = this.O.b("yesno.title.0");
            final String b2 = this.O.b("yesno.text.0");
            this.a.h(9);
            this.N.a(b, b2, true, 2);
        }
        else if (!(o instanceof f) || this.e.contains(point)) {}
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final Point point, final Point point2, final aE ae) {
        if (o instanceof f) {
            final f f = (f)o;
            this.a.h(9);
            final int a = this.b.a(1).a(null);
            final int b = this.b.a(1).b(null);
            int b2 = 0;
            int c = 0;
            if (ae instanceof av) {
                final av av = (av)ae;
                b2 = av.b();
                c = av.c();
            }
            final Rectangle rectangle = new Rectangle(point2.x - b2, point2.y - c, a, b);
            final Rectangle rectangle2 = new Rectangle(point.x - b2, point.y - c, a, b);
            if (z != null) {
                aL.a(z, 10, this.b.r(), rectangle, rectangle2);
            }
            else {
                try {
                    this.a.a(new k(this.d, point2.x - b2, point2.y - c, point.x - b2, point.y - c, null));
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public Rectangle b() {
        return this.e;
    }
    
    public Rectangle c() {
        return this.f;
    }
    
    public b d() {
        return this.d;
    }
    
    public boolean a(final a a, final int n) {
        boolean b = false;
        if (this.j && this.m++ >= 1) {
            this.m = 0;
            final D n2 = (D)this.N.b("board");
            if (this.k) {
                n2.b(this.l);
                this.k = false;
            }
            else {
                n2.a(this.l);
                this.k = true;
            }
            n2.e();
            n2.a(a);
            b = true;
        }
        return b;
    }
    
    private f a(final com.daysofwonder.util.G g, final Point point) {
        f f = null;
        int n = 0;
        if (g.a() > 1) {
            n = (this.f.height - g.a() * this.h) / (g.a() - 1);
        }
        final Rectangle rectangle = new Rectangle();
        rectangle.x = this.f.x;
        rectangle.y = (this.a.Y() ? this.f.y : (this.f.y + this.f.height - this.h));
        rectangle.width = this.a.d(1).a(null);
        rectangle.height = this.h;
        for (int i = 0; i < g.a(); ++i) {
            final f f2 = (f)g.b((i + this.i) % g.a());
            if (rectangle.contains(point)) {
                f = f2;
            }
            if (this.a.Y()) {
                final Rectangle rectangle2 = rectangle;
                rectangle2.y += n + this.h;
            }
            else {
                final Rectangle rectangle3 = rectangle;
                rectangle3.y -= n + this.h;
            }
        }
        return f;
    }
    
    private int b(final com.daysofwonder.util.G g, final Point point) {
        int n = -1;
        int n2 = 0;
        if (g.a() > 1) {
            n2 = (this.f.height - g.a() * this.h) / (g.a() - 1);
        }
        final Rectangle rectangle = new Rectangle();
        rectangle.x = this.f.x;
        rectangle.y = (this.a.Y() ? this.f.y : (this.f.y + this.f.height - this.h));
        rectangle.width = this.a.d(1).a(null);
        rectangle.height = this.h;
        for (int i = 0; i < g.a(); ++i) {
            if (rectangle.contains(point)) {
                n = i;
            }
            if (this.a.Y()) {
                final Rectangle rectangle2 = rectangle;
                rectangle2.y += n2 + this.h;
            }
            else {
                final Rectangle rectangle3 = rectangle;
                rectangle3.y -= n2 + this.h;
            }
        }
        return n;
    }
}
