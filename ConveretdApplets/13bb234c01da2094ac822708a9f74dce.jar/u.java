import java.awt.Point;
import com.daysofwonder.applet.Y;
import java.awt.event.MouseEvent;
import java.util.Vector;
import com.daysofwonder.tt.d;
import java.awt.Color;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import java.awt.Rectangle;
import com.daysofwonder.b.b;

// 
// Decompiled by Procyon v0.5.30
// 

public class u extends aw
{
    private b d;
    private b[] e;
    private b f;
    private b g;
    private b h;
    private Rectangle i;
    private Rectangle j;
    private Rectangle k;
    private Rectangle l;
    private Rectangle m;
    private Rectangle n;
    private Rectangle o;
    private Rectangle p;
    private Rectangle q;
    private int[] r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private String y;
    private String z;
    private String A;
    private String B;
    private int C;
    
    public u(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.e = new b[5];
        this.r = new int[5];
        this.C = -1;
        this.e[0] = ap.c(uiProperties.a(s + ".greendot"));
        this.e[1] = ap.c(uiProperties.a(s + ".reddot"));
        this.e[2] = ap.c(uiProperties.a(s + ".yellowdot"));
        this.e[3] = ap.c(uiProperties.a(s + ".bluedot"));
        this.e[4] = ap.c(uiProperties.a(s + ".blackdot"));
        this.f = ap.c(uiProperties.a(s + ".purplearrow"));
        this.g = ap.c(uiProperties.a(s + ".minitrain"));
        this.h = ap.c(uiProperties.a(s + ".ministation"));
        this.i = aL.a(uiProperties, s + ".header.r");
        this.j = aL.a(uiProperties, s + ".window.r");
        this.k = aL.c(this.i, aL.a(uiProperties, s + ".header.name.r"));
        this.l = aL.c(this.i, aL.a(uiProperties, s + ".header.cards.r"));
        this.m = aL.c(this.i, aL.a(uiProperties, s + ".header.tickets.r"));
        this.n = aL.c(this.i, aL.a(uiProperties, s + ".value.tickets.r"));
        this.o = aL.c(this.i, aL.a(uiProperties, s + ".header.trainleft.r"));
        this.p = aL.c(this.i, aL.a(uiProperties, s + ".value.trainleft.r"));
        this.q = aL.c(this.i, aL.a(uiProperties, s + ".header.score.r"));
        this.r[0] = this.j.y + Integer.parseInt(uiProperties.a(s + ".line.0"));
        this.r[1] = this.j.y + Integer.parseInt(uiProperties.a(s + ".line.1"));
        this.r[2] = this.j.y + Integer.parseInt(uiProperties.a(s + ".line.2"));
        this.r[3] = this.j.y + Integer.parseInt(uiProperties.a(s + ".line.3"));
        this.r[4] = this.j.y + Integer.parseInt(uiProperties.a(s + ".line.4"));
        this.s = Integer.parseInt(uiProperties.a(s + ".coloreddot.x"));
        this.t = Integer.parseInt(uiProperties.a(s + ".name.x"));
        this.u = Integer.parseInt(uiProperties.a(s + ".cards.x"));
        this.v = Integer.parseInt(uiProperties.a(s + ".tickets.x"));
        this.w = Integer.parseInt(uiProperties.a(s + ".trainleft.x"));
        this.x = Integer.parseInt(uiProperties.a(s + ".score.x"));
        this.a = this.b.n();
        this.y = uiProperties2.a("name");
        this.z = uiProperties2.a("cards");
        this.A = uiProperties2.a("tickets");
        this.B = uiProperties2.a("score");
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.d == null) {
                this.d = this.N.b(this.G);
            }
            a.a(this.d, this.G.x, this.G.y, null);
            a.a(Color.black);
            a.e(this.i.x, this.i.y + this.i.height, this.i.x + this.i.width, this.i.y + this.i.height);
            aL.a(a, this.k, this.y, this.b.M(), 0);
            a.a(this.g, this.o.x + this.o.width / 2 - this.g.a(null) / 2, this.o.y, null);
            if (this.a.T()) {
                a.a(this.h, this.o.x + this.o.width / 2 + 3 + this.g.a(null) / 2, this.o.y, null);
            }
            aL.a(a, this.m, this.A, this.b.M(), 1);
            aL.a(a, this.q, this.B, this.b.M(), 1);
            if (this.a != null) {
                final Vector az = this.a.az();
                final Rectangle rectangle = new Rectangle(this.k);
                final Rectangle rectangle2 = new Rectangle(this.l);
                final Rectangle rectangle3 = new Rectangle(this.n);
                final Rectangle rectangle4 = new Rectangle(this.p);
                final Rectangle rectangle5 = new Rectangle(this.q);
                for (int i = 0; i < az.size(); ++i) {
                    final d d = az.elementAt(i);
                    rectangle.y = this.r[i];
                    rectangle2.y = this.r[i];
                    rectangle3.y = this.r[i];
                    rectangle4.y = this.r[i];
                    rectangle5.y = this.r[i];
                    if (this.C == i) {
                        a.a(Color.gray);
                        a.d(this.i.x, this.r[i], this.i.width, this.i.height);
                        a.a(Color.white);
                    }
                    String s;
                    if (this.a.g() == d) {
                        a.a(Color.red);
                        s = "/" + Integer.toString(d.c());
                    }
                    else {
                        s = this.d(d.c());
                    }
                    aL.a(a, rectangle, a(d.z()), this.b.I(), 0);
                    aL.a(a, rectangle3, Integer.toString(d.f()) + s, this.b.I(), 1);
                    aL.a(a, rectangle4, Integer.toString(d.d()) + (this.a.T() ? ("/" + Integer.toString(d.e())) : ""), this.b.I(), 1);
                    aL.a(a, rectangle5, Integer.toString(d.A()), this.b.I(), 1);
                    if (this.C == i) {
                        a.a(Color.black);
                    }
                    if (this.a.g() == d) {
                        a.a(Color.black);
                    }
                    if (this.a.a(d)) {
                        a.a(this.f, this.j.x, this.r[i] + 3, null);
                    }
                }
                for (int j = 0; j < this.a.aE(); ++j) {
                    a.a(this.e[j], this.j.x + this.s, this.r[j] + 3, null);
                }
            }
        }
    }
    
    private static String a(final String s) {
        if (s.length() > 15) {
            return s.substring(0, 15);
        }
        return s;
    }
    
    private String d(final int n) {
        if (n <= 8) {
            return "/" + Integer.toString(n) + " ";
        }
        return "/8+";
    }
    
    public Rectangle a(final int n) {
        final Rectangle rectangle = new Rectangle(this.k);
        rectangle.y = this.r[n];
        rectangle.x += rectangle.width >> 1;
        return rectangle;
    }
    
    public void a() {
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        if (this.a != null) {
            final Rectangle rectangle = new Rectangle(this.k);
            rectangle.width = this.i.width;
            for (int i = 0; i < this.a.aE(); ++i) {
                final d b = this.a.b(i);
                rectangle.y = this.r[i];
                if (rectangle.contains(mouseEvent.getPoint()) && this.a.aG() != i && b.w() != 0) {
                    (this.c = this.N.Y()).b(this.P);
                    this.c.a(this, i);
                    this.N.a(this.c, mouseEvent.getPoint().x, mouseEvent.getPoint().y, 150, 1);
                    return this.c.a(mouseEvent);
                }
            }
        }
        return false;
    }
    
    private int f(final Point point) {
        if (this.a != null) {
            final Rectangle rectangle = new Rectangle(this.k);
            rectangle.width = this.i.width;
            for (int i = 0; i < this.a.aE(); ++i) {
                final d b = this.a.b(i);
                rectangle.y = this.r[i];
                if (rectangle.contains(point) && this.a.aG() != i && b.w() != 0) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void c(final MouseEvent mouseEvent) {
        this.C = this.f(mouseEvent.getPoint());
        this.v();
    }
    
    public void d(final MouseEvent mouseEvent) {
        this.C = -1;
        this.v();
    }
    
    public void e(final MouseEvent mouseEvent) {
        this.C = this.f(mouseEvent.getPoint());
        this.v();
    }
}
