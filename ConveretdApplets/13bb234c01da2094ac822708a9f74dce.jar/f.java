import com.daysofwonder.applet.aE;
import com.daysofwonder.applet.aH;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;
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

public class f extends am
{
    private G a;
    private z b;
    private b c;
    private Rectangle d;
    private Rectangle e;
    private Rectangle f;
    private Rectangle g;
    private Rectangle h;
    private com.daysofwonder.util.G i;
    private String j;
    private boolean k;
    private int l;
    private int m;
    private am n;
    private boolean o;
    private int p;
    private int q;
    private boolean[] r;
    private int s;
    private boolean t;
    private int u;
    private int v;
    private boolean w;
    
    public f(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.k = true;
        this.s = 0;
        this.w = true;
        this.b = (z)ap;
        this.d = aL.a(uiProperties, s + ".cards.r");
        this.c = ap.c(uiProperties.a(s + ".back"));
        this.e = aL.a(uiProperties, s + ".msg.r");
        this.f = aL.a(uiProperties, s + ".trains.r");
        this.g = aL.a(uiProperties, s + ".cardstext.r");
        this.h = aL.a(uiProperties, s + ".players.r");
        this.m = Integer.parseInt(uiProperties.a(s + ".maxspace"));
        this.l = Integer.parseInt(uiProperties.a(s + ".minspace"));
        this.j = uiProperties.a(s + ".done");
        this.a = this.b.n();
        if (uiProperties.a(s + ".active") != null) {
            this.k = !uiProperties.a(s + ".active").equalsIgnoreCase("false");
        }
    }
    
    public void a(final a a) {
        if (this.n == null) {
            this.e();
        }
        if (this.K) {
            if (this.c != null) {
                a.a(this.c, this.G.x, this.G.y, null);
            }
            if (this.i != this.a.I() && this.a.I() != null) {
                this.a(this.a.I(), this.a.J());
            }
            if (this.n != null && !this.a.aA()) {
                this.n.c(true);
                this.n.a((this.r != null && this.d() >= this.s) || !this.k);
                this.n.a(a);
            }
            if (this.i != null && this.a.g() != null && this.a.ad()) {
                a.a(Color.white);
                aL.a(a, this.e, this.O.b("ticketchooser.text"), this.N.M(), 0);
                if (this.f != null) {
                    aL.a(a, this.f, this.O.b("trainleft") + ":" + this.a.g().d(), this.N.M(), 1);
                }
                if (this.g != null) {
                    aL.a(a, this.g, this.O.b("cards") + ":" + this.a.g().c(), this.N.M(), 1);
                }
                if (this.h != null) {
                    aL.a(a, this.h, this.O.b("players") + ":" + this.a.aE(), this.N.M(), 1);
                }
                a.a(Color.black);
                this.b(a);
            }
        }
        else if (this.n != null) {
            this.n.a(false);
            this.n.c(false);
            this.n.a(a);
        }
    }
    
    public void a() {
    }
    
    public void b() {
        this.n.a(false);
        this.n.c(false);
        this.k = false;
    }
    
    public void a(final com.daysofwonder.util.G i, final int s) {
        this.i = i;
        this.r = new boolean[this.i.a()];
        this.o = true;
        this.s = s;
        this.k = true;
    }
    
    private int d() {
        int n = 0;
        if (this.r != null) {
            for (int i = 0; i < this.r.length; ++i) {
                if (this.r[i]) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public void b(final a a) {
        if (this.o && this.i.a() > 0) {
            this.o = false;
            final b d = this.a.d(((com.daysofwonder.tt.f)this.i.b(0)).j());
            this.p = d.a(null);
            this.q = d.b(null);
        }
        int x = this.d.x;
        final int n = this.d.y + 5;
        int n2 = 0;
        if (this.i.a() > 1) {
            n2 = (this.d.width - this.i.a() * this.p) / (this.i.a() - 1);
            if (n2 < this.l) {
                n2 = this.l;
            }
            if (n2 > this.m) {
                n2 = this.m;
            }
        }
        else {
            x += (this.d.width - this.p) / 2;
        }
        for (int i = 0; i < this.i.a(); ++i) {
            a.a(this.a.d(((com.daysofwonder.tt.f)this.i.b(i)).j()), x, n, null);
            if (this.r[i]) {
                a.a(Color.black);
                a.b(Color.white);
                a.c(x - 1, n - 1, this.p + 2, this.q + 2);
                a.c(x - 2, n - 2, this.p + 4, this.q + 4);
                a.c(x - 3, n - 3, this.p + 6, this.q + 6);
                a.e();
            }
            x += n2 + this.p;
        }
    }
    
    public int a_(final Point point) {
        int n = -1;
        if (this.i != null && this.i.a() > 0) {
            final int p = this.p;
            final int q = this.q;
            int n2 = 0;
            final Rectangle rectangle = new Rectangle();
            rectangle.x = this.d.x;
            rectangle.y = this.d.y + 5;
            rectangle.width = p;
            rectangle.height = q;
            if (this.i.a() > 1) {
                n2 = (this.d.width - this.i.a() * this.p) / (this.i.a() - 1);
                if (n2 < this.l) {
                    n2 = this.l;
                }
                if (n2 > this.m) {
                    n2 = this.m;
                }
            }
            else {
                final Rectangle rectangle2 = rectangle;
                rectangle2.x += (this.d.width - this.p) / 2;
            }
            for (int i = 0; i < this.i.a(); ++i) {
                if (rectangle.contains(point)) {
                    n = i;
                }
                final Rectangle rectangle3 = rectangle;
                rectangle3.x += n2 + this.p;
            }
        }
        return n;
    }
    
    public Vector c() {
        final Vector<Integer> vector = new Vector<Integer>();
        if (this.r != null) {
            for (int i = 0; i < this.r.length; ++i) {
                if (this.r[i]) {
                    vector.addElement(i);
                }
            }
        }
        return vector;
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        if (mouseEvent.isControlDown() || (mouseEvent.getModifiers() & 0x4) == 0x4) {
            if (this.d.contains(mouseEvent.getPoint())) {
                if (this.a_(mouseEvent.getPoint()) != -1) {
                    return this.N.a(new Y(this), mouseEvent);
                }
            }
        }
        else if (this.k && this.d.contains(mouseEvent.getPoint()) && this.a_(mouseEvent.getPoint()) != -1) {
            return this.N.a(new W(this), mouseEvent);
        }
        return false;
    }
    
    public void b(final MouseEvent mouseEvent) {
    }
    
    public boolean a(final a a, final int n) {
        boolean b = false;
        if (this.t && this.v++ >= 1) {
            this.v = 0;
            final D n2 = (D)this.N.b("board");
            if (this.w) {
                n2.b((com.daysofwonder.tt.f)this.i.b(this.u));
                this.w = false;
            }
            else {
                n2.a((com.daysofwonder.tt.f)this.i.b(this.u));
                this.w = true;
            }
            n2.e();
            n2.a(a);
            b = true;
        }
        return b;
    }
    
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
    
    public boolean a(final Point point, final Object o, final String s) {
        return false;
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final am am, final Point point, final int n, final boolean b, final boolean b2) {
        if (!b2 && (b || n == 0)) {
            z.a(Color.black);
            z.b(Color.white);
            z.a(this.G.x - 1, this.G.y - 1, this.G.width + 2, this.G.height + 2);
            z.a(this.G.x - 2, this.G.y - 2, this.G.width + 4, this.G.height + 4);
            z.a(this.G.x - 3, this.G.y - 3, this.G.width + 6, this.G.height + 6);
            z.b();
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final am am, final Point point) {
    }
    
    public void a(final com.daysofwonder.applet.z z, final Object o, final String s, final Point point, final Point point2, final aE ae) {
    }
    
    public void a(final boolean b) {
        super.a(b);
        this.e();
        if (this.n != null && !b) {
            this.n.c(b);
            this.n.a(b);
        }
    }
    
    private void e() {
        if (this.n == null) {
            this.n = this.N.b(this.j);
        }
    }
}
