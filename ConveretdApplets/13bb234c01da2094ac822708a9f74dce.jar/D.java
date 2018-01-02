import java.awt.Rectangle;
import com.daysofwonder.tt.f;
import com.daysofwonder.tt.d;
import com.daysofwonder.b.a;
import com.daysofwonder.util.t;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.c;
import com.daysofwonder.tt.i;
import com.daysofwonder.applet.aE;
import java.awt.Point;
import java.awt.Image;
import com.daysofwonder.applet.aH;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.daysofwonder.applet.aL;
import com.daysofwonder.applet.ap;
import com.daysofwonder.tt.e;
import com.daysofwonder.util.UIProperties;
import java.util.Vector;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class D extends am
{
    protected G a;
    protected z b;
    protected R c;
    protected R d;
    protected R e;
    protected b f;
    protected boolean g;
    protected Vector h;
    protected UIProperties i;
    protected String j;
    protected e k;
    protected int l;
    protected float m;
    protected int n;
    protected int o;
    private int p;
    private boolean q;
    
    public D(final ap ap, final String j, final UIProperties i, final UIProperties uiProperties) {
        super(ap, j, i, uiProperties);
        this.h = new Vector();
        this.l = -1;
        this.p = 0;
        this.q = true;
        this.b = (z)ap;
        this.g = true;
        this.a = this.b.n();
        this.n = 32;
        this.o = 80;
        if (i.a(j + ".trainsize") != null) {
            this.o = Integer.parseInt(i.a(j + ".trainsize"));
        }
        if (i.a(j + ".smalltrainsize") != null) {
            this.n = Integer.parseInt(i.a(j + ".smalltrainsize"));
        }
        this.m = 0.3928f;
        if (i.a(j + ".ratio") != null) {
            this.m = Float.valueOf(i.a(j + ".ratio"));
        }
        int n = 0;
        for (Color color = aL.b(i, j + ".playercolor.0"); color != null; color = aL.b(i, j + ".playercolor." + n)) {
            this.h.addElement(color);
            ++n;
        }
        this.i = i;
        this.j = j;
    }
    
    public void a() {
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        if (!this.a.j() && !this.a.s() && !this.a.u() && !this.a.v() && !this.a.w() && !this.a.t()) {
            this.N.a(new U(this), mouseEvent);
            return true;
        }
        return false;
    }
    
    public void b(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !this.a.j() && !this.a.s() && !this.a.u() && !this.a.v() && !this.a.w() && !this.a.t()) {
            (this.e = ((this.e == this.c) ? this.d : this.c)).e();
            this.N.A();
        }
    }
    
    public void b() {
        (this.e = ((this.e == this.c) ? this.d : this.c)).e();
        this.N.A();
    }
    
    public abstract void a(final e p0, final boolean p1, final boolean p2, final Image p3);
    
    public abstract void a(final e p0, final Image p1, final int p2, final int p3, final int p4, final int p5);
    
    public boolean b(final Point point) {
        return false;
    }
    
    public Object c(final Point point) {
        return null;
    }
    
    public String d(final Point point) {
        return "";
    }
    
    public aE a(final com.daysofwonder.applet.z z, final Point point, final Object o, final String s) {
        return null;
    }
    
    public boolean a(final Point point, final Object o, final am am, final String s) {
        if (!(am instanceof p)) {
            return false;
        }
        if (this.a.aA() || !this.a.i() || !(o instanceof i)) {
            return false;
        }
        final int a = this.e.a(point.x - this.G.x, point.y - this.G.y);
        if (a == -1) {
            if (this.k.c()) {
                final c c = this.e.c(point.x - this.G.x, point.y - this.G.y);
                if (c != null && this.a.a(c) && this.a.g().a(c, ((i)o).b())) {
                    return true;
                }
            }
            return false;
        }
        final o c2 = this.a.c(a);
        if (c2 != null && this.a.a(c2)) {
            final i i = (i)o;
            return (c2.c() == 9 || c2.c() == i.b() || i.c() || (i.d() && c2.j())) && this.a.g().b(c2, i.b());
        }
        return false;
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final am am, final Point point, final int n, final boolean b, final boolean b2) {
        if (am instanceof p && this.G.contains(point)) {
            final int a = this.e.a(point.x - this.G.x, point.y - this.G.y);
            if (this.l == -1) {
                if (a >= 0 && b) {
                    this.e.a(a);
                    this.l = a;
                    this.e.a(z, this.G.width, this.G.height, this.G.x, this.G.y);
                }
            }
            else if (this.l != -1 && a != this.l) {
                this.e.b(this.l);
                this.l = -1;
                this.e.a(z, this.G.width, this.G.height, this.G.x, this.G.y);
                if (a >= 0 && b) {
                    this.e.a(a);
                    this.l = a;
                }
                this.e.a(z, this.G.width, this.G.height, this.G.x, this.G.y);
            }
            if (point.x < this.G.x + 15 || point.x > this.G.x + this.G.width - 15 || point.y < this.G.y + 15 || point.y > this.G.y + this.G.height - 15) {
                this.e.d((point.x < this.G.x + 15) ? -5 : ((point.x > this.G.x + this.G.width - 15) ? 5 : 0), (point.y < this.G.y + 15) ? -5 : ((point.y > this.G.y + this.G.height - 15) ? 5 : 0));
                this.e.a(z, this.G.width, this.G.height, this.G.x, this.G.y);
            }
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final am am, final Point point) {
        if (am instanceof p && !this.a.aA() && this.a.i() && o instanceof i) {
            this.l = -1;
            final int a = this.e.a(point.x - this.G.x, point.y - this.G.y);
            if (a == -1) {
                this.a.h(9);
                if (this.k.c()) {
                    final c c = this.e.c(point.x - this.G.x, point.y - this.G.y);
                    if (c != null && this.a.a(c)) {
                        final i i = (i)o;
                        if (this.a.g().a(c, i.b())) {
                            this.a.h(9);
                            this.a.b(c, i);
                            this.N.a(this.O.b("yesno.title.1"), this.O.b("yesno.text.1"), true, 3);
                            return;
                        }
                    }
                }
            }
            else {
                final o c2 = this.a.c(a);
                if (c2 != null && this.a.a(c2)) {
                    final i j = (i)o;
                    if ((c2.c() == 9 || c2.c() == j.b() || j.c() || (c2.j() && j.d())) && this.a.g().b(c2, j.b())) {
                        this.a.h(9);
                        this.a.a(c2, j);
                        t.a("Claimed!");
                        if (!c2.j()) {
                            t.a("add Tracked");
                            this.c.a(c2, this.a.f(this.a.C()));
                            this.d.a(c2, this.a.f(this.a.C()));
                            this.e.e();
                        }
                        return;
                    }
                }
            }
            this.a.h(29);
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final Point point, final Point point2, final aE ae) {
    }
    
    public boolean c_() {
        return true;
    }
    
    public boolean a(final a a, final int n) {
        boolean b = false;
        if (this.a.u()) {
            if (this.p++ >= 2) {
                this.p = 0;
                if (this.g()) {
                    this.e.e();
                    if (this.q) {
                        final Vector s = this.a.S();
                        final Vector r = this.a.R();
                        if (r != null && s != null) {
                            for (int i = 0; i < Math.min(r.size(), s.size()); ++i) {
                                final com.daysofwonder.util.G g = r.elementAt(i);
                                final Color color = this.h.elementAt(this.a.g(s.elementAt(i)));
                                final int n2 = (color.getRed() << 16) + (color.getGreen() << 8) + color.getBlue();
                                if (g != null) {
                                    for (int j = 0; j < g.a(); ++j) {
                                        this.e.b(this.k.a((o)g.b(j)).g(), n2);
                                    }
                                }
                            }
                        }
                        this.q = false;
                    }
                    else {
                        this.q = true;
                    }
                    this.e.a(a, this.G.width, this.G.height, this.G.x, this.G.y);
                    b = true;
                }
            }
        }
        else if (this.a.v() && this.p++ >= 2) {
            this.p = 0;
            if (this.g()) {
                this.e.e();
                if (this.q) {
                    final Vector az = this.a.az();
                    for (int k = 0; k < az.size(); ++k) {
                        final d d = az.elementAt(k);
                        final com.daysofwonder.util.G s2 = d.s();
                        if (s2 != null && s2.a() > 0) {
                            for (int l = 0; l < s2.a(); ++l) {
                                final o o = (o)s2.b(l);
                                final Color color2 = this.h.elementAt((d.C() == -1) ? this.a.b(d) : d.C());
                                this.e.b(o.g(), (color2.getRed() << 16) + (color2.getGreen() << 8) + color2.getBlue());
                            }
                        }
                    }
                    this.q = false;
                }
                else {
                    this.q = true;
                }
                this.e.a(a, this.G.width, this.G.height, this.G.x, this.G.y);
                b = true;
            }
        }
        return b;
    }
    
    public void a(final c c, final int n) {
        this.c.a(c, n);
        this.d.a(c, n);
    }
    
    public void a(final o o, final int n) {
        this.c.a(o, n);
        this.d.a(o, n);
    }
    
    public void a(final o o) {
        this.c.a(o);
        this.d.a(o);
    }
    
    public void a(final com.daysofwonder.util.G g) {
        if (this.a.ad()) {
            this.c.a(g);
            this.d.a(g);
        }
    }
    
    public void d() {
        this.c.d();
        this.d.d();
    }
    
    public void a(final f f) {
        if (this.a.ad()) {
            this.c.a(f);
            this.d.a(f);
        }
    }
    
    public void b(final f f) {
        if (this.a.ad()) {
            this.c.b(f);
            this.d.b(f);
        }
    }
    
    public void a(final c c, final i i) {
        this.a.a(c, i);
        this.c.a(c, this.a.g(this.a.g().v()));
        this.d.a(c, this.a.g(this.a.g().v()));
        this.e.e();
    }
    
    public void e() {
        if (this.e.f()) {
            this.e.e();
        }
    }
    
    public Point b(final o o) {
        final Point b = this.d.b(o);
        t.a("getLocation p: " + b);
        b.translate(this.G.x, this.G.y);
        return b;
    }
    
    public int a(final int n) {
        int n2 = n;
        if (n == 1 && this.e == this.c) {
            n2 = 0;
            (this.e = this.d).e();
        }
        else if (n == 0 && this.e == this.d) {
            n2 = 1;
            (this.e = this.c).e();
        }
        return n2;
    }
    
    public int f() {
        return this.e.c();
    }
    
    public boolean g() {
        return this.c != null && this.d != null && this.c.f() && this.d.f();
    }
}
