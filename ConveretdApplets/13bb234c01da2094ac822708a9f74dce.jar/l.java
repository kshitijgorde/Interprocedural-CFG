import java.awt.image.ImageObserver;
import com.daysofwonder.applet.aL;
import java.awt.Point;
import com.daysofwonder.applet.aJ;
import com.daysofwonder.tt.i;
import com.daysofwonder.util.t;
import com.daysofwonder.applet.aG;
import com.daysofwonder.applet.ap;
import java.awt.Rectangle;
import java.util.Vector;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.n;
import com.daysofwonder.applet.aC;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends aC
{
    private n a;
    private G b;
    private z c;
    private o d;
    private int e;
    private int f;
    private D g;
    private Vector h;
    private Rectangle i;
    private boolean j;
    private boolean k;
    private boolean l;
    private Rectangle m;
    private float n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    
    public l(final n a, final o d, final int s) {
        this.h = new Vector();
        this.i = new Rectangle();
        this.j = true;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.t = 140;
        this.a = a;
        this.d = d;
        this.s = s;
    }
    
    public l(final n n, final o o, final int n2, final boolean j) {
        this(n, o, n2);
        this.j = j;
    }
    
    public l(final n n, final o o, final int n2, final boolean b, final boolean l) {
        this(n, o, n2, b);
        this.l = l;
    }
    
    public void a(final ap ap, final aG ag) {
        this.b = (G)ag;
        this.c = (z)ap;
        this.g = (D)ap.b("board");
        this.r = this.g.a(1);
        this.o = ((this.d != null) ? this.b.j(this.d.d()) : -1);
        this.k = (this.d != null && this.d.j());
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n) {
        this.e = this.c.a(1).a(null);
        this.f = this.c.a(1).b(null);
        Rectangle rectangle;
        int n2;
        int n3;
        if (this.s == -1) {
            rectangle = ((p)this.c.b("player")).p();
            n2 = rectangle.x;
            n3 = rectangle.y;
        }
        else {
            final aw aw = (aw)this.c.b("other");
            if (aw != null) {
                rectangle = aw.a(this.s);
                if (rectangle == null) {
                    rectangle = ((p)this.c.b("player")).p();
                }
            }
            else {
                rectangle = new Rectangle();
            }
            n2 = rectangle.x;
            n3 = rectangle.y;
        }
        final Point b = this.g.b(this.d);
        com.daysofwonder.util.t.a("Animation: " + b);
        if (this.l) {
            n2 = b.x;
            n3 = b.y;
            b.x = rectangle.x;
            b.y = rectangle.y;
            this.t = 50;
        }
        else {
            this.m = new Rectangle(b.x, b.y, 0, 0);
        }
        for (int i = 0; i < this.a.d(); ++i) {
            final aJ aj = new aJ(this.c, this.c.a(((i)this.a.a(i)).a()), 0, 0);
            aj.a(n2, n3);
            aj.a(n2, n3);
            com.daysofwonder.util.t.a("Animation to: " + n2 + "," + n3);
            aj.a((b.x - n2) / 50.0f, (b.y - n3) / 50.0f);
            n2 += 8;
            n3 += 8;
            this.h.addElement(aj);
        }
        int n4 = 6;
        if (this.d.h()) {
            n4 = 3;
        }
        else if (this.d.j()) {
            n4 = 4;
        }
        this.b.h(n4);
        this.b.a(n4, 85, 0.0f, true);
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n, final int n2) {
        if (n2 == 0) {
            for (int i = 0; i < this.h.size(); ++i) {
                final aJ aj = this.h.elementAt(i);
                aj.a(z, 0);
                this.i = aL.b(this.i, aj.b());
            }
        }
        else if (n2 == 1) {
            if (n <= 85) {
                for (int j = 0; j < this.h.size(); ++j) {
                    final aJ aj2 = this.h.elementAt(j);
                    aj2.a(z);
                    this.i = aL.b(this.i, aj2.b());
                }
                for (int k = 0; k < this.h.size(); ++k) {
                    final aJ aj3 = this.h.elementAt(k);
                    aj3.a(z, 1);
                    this.i = aL.b(this.i, aj3.b());
                }
            }
            else if (this.o > 0 && this.m != null && !this.k && this.c.o() != null) {
                z.a(3, this.n);
                z.a(this.c.o(), aL.b(z.e(), this.o, this.m, null, this.c.m()) - this.c.o().a(null), this.m.y, null);
                z.a();
            }
        }
        if (n2 == 0 && n == 50) {
            this.g.t();
            if (this.j) {
                this.g.a(this.d, this.b.f(this.s));
                this.d = null;
            }
            this.g.e();
            this.g.a(z.d());
            this.i = aL.b(this.i, this.g.p());
            this.g.s();
        }
        if (n2 == 1) {
            z.a(this.i);
            this.i.setBounds(0, 0, 0, 0);
        }
    }
    
    public boolean b(final com.daysofwonder.applet.z z, final int n) {
        if (n <= 50) {
            for (int i = 0; i < this.h.size(); ++i) {
                ((aJ)this.h.elementAt(i)).c();
            }
            if (n == 50 && this.j && this.d != null && !this.d.j()) {
                this.b.h(16 + this.o);
            }
            else if (n == 50 && this.d != null && this.d.j()) {
                return false;
            }
        }
        else if (n <= 85) {
            final float n2 = 1.0f - (n - 50) / 35.0f;
            for (int j = 0; j < this.h.size(); ++j) {
                ((aJ)this.h.elementAt(j)).a(n2);
            }
            if (n == 85 && this.j) {
                this.b.h(28);
            }
        }
        else if (n <= this.t) {
            this.n = 1.0f - (n - 85) / 55.0f;
        }
        return n != this.t;
    }
    
    public void a(final com.daysofwonder.applet.z z) {
        for (int i = 0; i < this.h.size(); ++i) {
            ((aJ)this.h.elementAt(i)).a();
        }
        this.h.removeAllElements();
        if (this.j && this.d != null) {
            this.g.a(this.d, this.b.f(this.s));
            int n = 6;
            if (this.d.h()) {
                n = 3;
            }
            else if (this.d.j()) {
                n = 4;
            }
            this.b.h(n);
        }
        this.g.e();
        this.g.a(this.r);
    }
}
