import com.daysofwonder.applet.aH;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Point;
import com.daysofwonder.util.t;
import java.awt.Color;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import com.daysofwonder.tt.n;
import java.awt.Rectangle;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;

// 
// Decompiled by Procyon v0.5.30
// 

public class E extends am
{
    private G a;
    private z b;
    private b c;
    private b d;
    private Rectangle e;
    private Rectangle f;
    private n g;
    private String h;
    private boolean i;
    private int j;
    private int k;
    private am l;
    private boolean m;
    private int n;
    private int o;
    private boolean[] p;
    private int q;
    private int r;
    
    public E(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.i = true;
        this.q = 0;
        this.r = 1;
        this.b = (z)ap;
        this.d = ap.c(uiProperties.a(s + ".back"));
        this.e = aL.a(uiProperties, s + ".cards.r");
        this.f = aL.a(uiProperties, s + ".msg.r");
        this.h = uiProperties.a(s + ".done");
        this.a = this.b.n();
        this.k = Integer.parseInt(uiProperties.a(s + ".maxspace"));
        this.j = Integer.parseInt(uiProperties.a(s + ".minspace"));
        if (uiProperties.a(s + ".active") != null) {
            this.i = !uiProperties.a(s + ".active").equalsIgnoreCase("false");
        }
    }
    
    public void a(final a a) {
        if (this.l == null) {
            this.l = this.N.b(this.h);
        }
        if (this.K && this.a.z()) {
            if (this.c == null) {
                this.c = this.N.b(this.G);
            }
            else {
                a.a(this.c, this.G.x, this.G.y, null);
            }
            if (this.d != null) {
                a.a(this.d, this.G.x, this.G.y, null);
            }
            if (this.g != this.a.ac() && this.a.ac() != null) {
                this.a(this.a.ac());
            }
            if (this.l != null && !this.a.aA()) {
                this.l.a((this.p != null && this.d() >= this.q) || !this.i);
                this.l.a(a);
            }
            if (this.g != null && this.a.g() != null) {
                a.a(Color.white);
                aL.a(a, this.f, this.O.b("colorcardchooser.text"), this.N.M(), 0);
                a.a(Color.black);
                this.b(a);
            }
        }
        else if (this.l != null) {
            this.l.a(false);
            this.l.a(a);
        }
    }
    
    public void a() {
        if (this.c != null) {
            this.c.c();
            this.c = null;
        }
    }
    
    public void a(final n g) {
        this.g = g;
        this.p = new boolean[this.g.d()];
        this.m = true;
        this.q = 1;
    }
    
    private int d() {
        int n = 0;
        if (this.p != null) {
            for (int i = 0; i < this.p.length; ++i) {
                if (this.p[i]) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public void b(final a a) {
        if (this.m && this.g.d() > 0) {
            this.m = false;
            final b b = this.b.b(this.g.a(0).a());
            this.n = b.a(null);
            this.o = b.b(null);
        }
        int x = this.e.x;
        final int n = this.e.y + 5;
        int n2 = 0;
        t.a("fCards: " + this.g);
        if (this.g.d() > 1) {
            n2 = (this.e.width - this.g.d() * this.n) / (this.g.d() - 1);
            t.a("dx: " + n2);
            if (n2 < this.j) {
                n2 = this.j;
            }
            if (n2 > this.k) {
                n2 = this.k;
            }
        }
        else {
            x += (this.e.width - this.n) / 2;
        }
        t.a("dx: " + n2);
        for (int i = 0; i < this.g.d(); ++i) {
            a.a(this.b.b(this.g.a(i).a()), x, n, null);
            if (this.p[i]) {
                a.a(Color.black);
                a.b(Color.white);
                a.c(x - 1, n - 1, this.n + 2, this.o + 2);
                a.c(x - 2, n - 2, this.n + 4, this.o + 4);
                a.c(x - 3, n - 3, this.n + 6, this.o + 6);
                a.e();
            }
            x += n2 + this.n;
        }
    }
    
    public int f(final Point point) {
        int n = -1;
        if (this.g != null && this.g.d() > 0) {
            final int n2 = this.n;
            final int o = this.o;
            int n3 = 0;
            final Rectangle rectangle = new Rectangle();
            rectangle.x = this.e.x;
            rectangle.y = this.e.y + 5;
            rectangle.width = n2;
            rectangle.height = o;
            if (this.g.d() > 1) {
                n3 = (this.e.width - this.g.d() * this.n) / (this.g.d() - 1);
                if (n3 < this.j) {
                    n3 = this.j;
                }
                if (n3 > this.k) {
                    n3 = this.k;
                }
            }
            else {
                final Rectangle rectangle2 = rectangle;
                rectangle2.x += (this.e.width - this.n) / 2;
            }
            for (int i = 0; i < this.g.d(); ++i) {
                if (rectangle.contains(point)) {
                    n = i;
                }
                final Rectangle rectangle3 = rectangle;
                rectangle3.x += n3 + this.n;
            }
        }
        return n;
    }
    
    public Vector b() {
        final Vector<Integer> vector = new Vector<Integer>();
        if (this.p != null) {
            for (int i = 0; i < this.p.length; ++i) {
                if (this.p[i]) {
                    vector.addElement(i);
                }
            }
        }
        return vector;
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return this.i && this.e.contains(mouseEvent.getPoint()) && this.f(mouseEvent.getPoint()) != -1 && this.N.a(new w(this), mouseEvent);
    }
    
    public void b(final MouseEvent mouseEvent) {
    }
    
    public void a(final boolean b) {
        super.a(b);
        if (this.l != null && !b) {
            this.l.a(b);
        }
    }
}
