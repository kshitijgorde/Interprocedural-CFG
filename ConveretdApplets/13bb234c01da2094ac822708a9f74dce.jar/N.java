import java.awt.Point;
import com.daysofwonder.applet.aH;
import java.awt.event.MouseEvent;
import java.util.Vector;
import com.daysofwonder.tt.f;
import com.daysofwonder.tt.d;
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

public class N extends am
{
    protected G a;
    protected z b;
    protected b c;
    protected b d;
    protected b e;
    protected Rectangle f;
    protected am g;
    protected String h;
    protected String i;
    protected String j;
    protected int[] k;
    protected Rectangle l;
    protected Rectangle m;
    protected Rectangle n;
    protected Rectangle o;
    protected Rectangle p;
    protected Rectangle q;
    protected Rectangle r;
    protected Rectangle s;
    protected String t;
    protected String u;
    protected String v;
    protected String w;
    protected String x;
    protected String y;
    protected String z;
    protected Rectangle A;
    protected com.daysofwonder.util.G B;
    protected int[] C;
    protected int D;
    protected int E;
    
    public N(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.k = new int[5];
        this.b = (z)ap;
        this.a = this.b.n();
        this.c = ap.c(uiProperties.a(s + ".back"));
        this.d = ap.c(uiProperties.a(s + ".redcross"));
        this.e = ap.c(uiProperties.a(s + ".greenmark"));
        this.E = this.d.a(null);
        this.f = aL.a(uiProperties, s + ".congrattext.r");
        this.i = uiProperties2.b(uiProperties.a(s + ".congrattext"));
        this.h = uiProperties2.b(uiProperties.a(s + ".mecongrattext"));
        this.j = uiProperties2.b(uiProperties.a(s + ".onlymecongrattext"));
        this.g = this.N.a("scoredone.subcontrol", ".subcontrol");
        this.k[0] = this.G.y + Integer.parseInt(uiProperties.a(s + ".line.0"));
        this.k[1] = this.G.y + Integer.parseInt(uiProperties.a(s + ".line.1"));
        this.k[2] = this.G.y + Integer.parseInt(uiProperties.a(s + ".line.2"));
        this.k[3] = this.G.y + Integer.parseInt(uiProperties.a(s + ".line.3"));
        this.k[4] = this.G.y + Integer.parseInt(uiProperties.a(s + ".line.4"));
        this.l = aL.c(this.G, aL.a(uiProperties, s + ".header.name.r"));
        this.p = aL.c(this.G, aL.a(uiProperties, s + ".header.succeed.r"));
        this.q = aL.c(this.G, aL.a(uiProperties, s + ".header.failed.r"));
        this.r = aL.c(this.G, aL.a(uiProperties, s + ".header.longuest.r"));
        this.s = aL.c(this.G, aL.a(uiProperties, s + ".header.score.r"));
        this.m = aL.c(this.G, aL.a(uiProperties, s + ".header.train.r"));
        this.n = aL.c(this.G, aL.a(uiProperties, s + ".header.train2.r"));
        this.o = aL.c(this.G, aL.a(uiProperties, s + ".header.station.r"));
        this.A = aL.c(this.G, aL.a(uiProperties, s + ".tickets.r"));
        this.t = uiProperties2.a("name");
        this.u = uiProperties2.a("score");
        this.v = uiProperties2.a("station");
        this.w = uiProperties2.a("succeed");
        this.x = uiProperties2.a("failed");
        this.y = uiProperties2.a("longuest");
        this.z = ((uiProperties2.a("total") != null) ? uiProperties2.a("total") : uiProperties2.a("score"));
        this.D = Integer.parseInt(uiProperties.a(s + ".cardwidth"));
    }
    
    public void a(final a a) {
        if (this.K && this.a.s()) {
            if (this.a.W() && this.a.T()) {
                this.m = this.n;
            }
            a.a(this.c, this.G.x, this.G.y, null);
            final com.daysofwonder.util.G o = this.a.O();
            if (this.a.P() && o.a() == 1) {
                aL.a(a, this.f, this.N.a(this.j, new Object[] { o }), this.N.O(), 1);
            }
            else if (this.a.P() && o.a() > 1) {
                aL.a(a, this.f, this.N.a(this.h, new Object[] { this.a.O() }), this.N.O(), 1);
            }
            else {
                aL.a(a, this.f, this.N.a(this.i, new Object[] { this.a.O() }), this.N.O(), 1);
            }
            aL.a(a, this.l, this.t, this.b.I(), 1);
            aL.a(a, this.m, this.u, this.b.I(), 1);
            if (this.a.T()) {
                aL.a(a, this.o, this.v, this.b.I(), 1);
            }
            aL.a(a, this.p, this.w, this.b.I(), 1);
            aL.a(a, this.q, this.x, this.b.I(), 1);
            aL.a(a, this.r, this.y, this.b.I(), 1);
            aL.a(a, this.s, this.z, this.b.I(), 1);
            final Vector az = this.a.az();
            if (az != null) {
                final Rectangle rectangle = new Rectangle(this.l);
                final Rectangle rectangle2 = new Rectangle(this.m);
                final Rectangle rectangle3 = new Rectangle(this.p);
                Rectangle rectangle4 = null;
                if (this.o != null) {
                    rectangle4 = new Rectangle(this.o);
                }
                final Rectangle rectangle5 = new Rectangle(this.q);
                final Rectangle rectangle6 = new Rectangle(this.r);
                final Rectangle rectangle7 = new Rectangle(this.s);
                a.e(this.l.x, this.l.y + this.l.height, this.s.width + this.s.x, this.l.y + this.l.height);
                a.e(this.l.x + this.l.width, this.l.y, this.l.width + this.l.x, this.k[4] + this.l.height);
                a.e(this.m.x + this.m.width, this.m.y, this.m.width + this.m.x, this.k[4] + this.m.height);
                if (rectangle4 != null) {
                    a.e(this.o.x + this.o.width, this.o.y, this.o.width + this.o.x, this.k[4] + this.o.height);
                }
                a.e(this.p.x + this.p.width, this.p.y, this.p.width + this.p.x, this.k[4] + this.p.height);
                a.e(this.q.x + this.q.width, this.q.y, this.q.width + this.q.x, this.k[4] + this.q.height);
                a.e(this.r.x + this.r.width, this.r.y, this.r.width + this.r.x, this.k[4] + this.r.height);
                for (int i = 0; i < az.size(); ++i) {
                    final d d = az.elementAt(i);
                    rectangle.y = this.k[i];
                    rectangle2.y = this.k[i];
                    if (rectangle4 != null) {
                        rectangle4.y = this.k[i];
                    }
                    rectangle3.y = this.k[i];
                    rectangle5.y = this.k[i];
                    rectangle6.y = this.k[i];
                    rectangle7.y = this.k[i];
                    final int[] q = d.q();
                    int n = 0;
                    int n2 = 0;
                    for (int j = 0; j < q.length; ++j) {
                        if (q[j] > 0) {
                            n += q[j];
                        }
                        else {
                            n2 += q[j];
                        }
                    }
                    aL.a(a, rectangle, d.z(), this.b.I(), 0);
                    aL.a(a, rectangle2, Integer.toString(d.r()), this.b.I(), 1);
                    if (this.a.T() && rectangle4 != null) {
                        aL.a(a, rectangle4, Integer.toString(d.e()) + " x 4", this.b.I(), 1);
                    }
                    aL.a(a, rectangle3, " + " + n, this.b.I(), 0);
                    aL.a(a, rectangle5, " - " + -n2, this.b.I(), 0);
                    if (d.m() >= 0 || d.o() >= 0) {
                        final StringBuffer sb = new StringBuffer();
                        sb.append(" +");
                        if (d.m() >= 0) {
                            sb.append(' ').append(d.m()).append('(').append(d.n()).append(')');
                        }
                        if (d.o() >= 0) {
                            sb.append(' ').append(d.o()).append('(').append(d.p()).append(')');
                        }
                        aL.a(a, rectangle6, sb.toString(), this.b.I(), 0);
                    }
                    aL.a(a, rectangle7, "    = " + d.A(), this.b.I(), 0);
                }
            }
            final com.daysofwonder.util.G g = (this.B != null) ? this.B : this.a.g().k();
            final int[] array = (this.C != null) ? this.C : this.a.g().q();
            int n3 = this.A.x + this.A.width - this.D;
            final int n4 = (this.A.width - g.a() * this.D) / (g.a() - 1);
            for (int k = 0; k < g.a(); ++k) {
                final f f = (f)g.b(k);
                a.a(this.a.d(f.j()), n3, this.A.y, null);
                final int b = this.a.d(f.j()).b(null);
                this.a.d(f.j()).a(null);
                int y = this.A.y;
                if (!this.a.Y()) {
                    y += b >> 2;
                }
                if (array[k] > 0) {
                    a.a(this.e, n3 + this.D - this.E - 3, y, null);
                }
                else {
                    a.a(this.d, n3 + this.D - this.E - 3, y, null);
                }
                n3 -= n4 + this.D;
            }
            this.g.a(a);
        }
    }
    
    public void a() {
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        if (this.K) {
            if (this.a.s() && this.g.n().contains(mouseEvent.getPoint())) {
                return this.g.a(mouseEvent);
            }
            if (this.a.s() && this.f(mouseEvent.getPoint()) != -1) {
                this.N.a(new ak(this), mouseEvent);
                return true;
            }
        }
        return false;
    }
    
    public void a(final boolean b) {
        super.a(b);
        if (this.g != null) {
            this.g.a(b);
        }
    }
    
    public int f(final Point point) {
        final Vector az = this.a.az();
        if (az != null) {
            final Rectangle rectangle = new Rectangle(this.l);
            final Rectangle rectangle2 = new Rectangle(this.m);
            final Rectangle rectangle3 = new Rectangle(this.o);
            final Rectangle rectangle4 = new Rectangle(this.p);
            final Rectangle rectangle5 = new Rectangle(this.q);
            final Rectangle rectangle6 = new Rectangle(this.r);
            final Rectangle rectangle7 = new Rectangle(this.s);
            for (int i = 0; i < az.size(); ++i) {
                rectangle.y = this.k[i];
                rectangle2.y = this.k[i];
                rectangle3.y = this.k[i];
                rectangle4.y = this.k[i];
                rectangle5.y = this.k[i];
                rectangle6.y = this.k[i];
                rectangle7.y = this.k[i];
                if (rectangle.contains(point) || rectangle2.contains(point) || rectangle3.contains(point) || rectangle4.contains(point) || rectangle5.contains(point) || rectangle6.contains(point) || rectangle7.contains(point)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
