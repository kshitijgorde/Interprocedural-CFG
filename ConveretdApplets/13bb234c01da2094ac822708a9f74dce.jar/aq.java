import com.daysofwonder.applet.aL;
import com.daysofwonder.applet.aJ;
import com.daysofwonder.tt.i;
import com.daysofwonder.util.t;
import java.awt.Point;
import com.daysofwonder.applet.aG;
import com.daysofwonder.applet.ap;
import com.daysofwonder.tt.n;
import java.awt.Rectangle;
import java.util.Vector;
import com.daysofwonder.util.G;
import com.daysofwonder.applet.aC;

// 
// Decompiled by Procyon v0.5.30
// 

public class aq extends aC
{
    private G a;
    private G b;
    private z c;
    private int d;
    private int e;
    private h f;
    private Vector g;
    private Rectangle h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    
    public aq(final n n, final int i, final int j, final int k) {
        this.g = new Vector();
        this.h = new Rectangle();
        this.l = 0;
        this.m = this.l + 50;
        this.n = this.m + 50;
        this.a = n.a();
        this.i = i;
        this.j = j;
        this.k = k;
    }
    
    public aq(final G a, final int i, final int j, final int k) {
        this.g = new Vector();
        this.h = new Rectangle();
        this.l = 0;
        this.m = this.l + 50;
        this.n = this.m + 50;
        this.a = a;
        this.i = i;
        this.j = j;
        this.k = k;
    }
    
    public void a(final ap ap, final aG ag) {
        this.b = (G)ag;
        this.c = (z)ap;
        this.f = (h)ap.b("tunnel");
    }
    
    public Point a(final int n, final boolean b) {
        final Point point = new Point();
        switch (n) {
            case 1: {
                final Rectangle b2 = ((O)this.c.b("deck")).b();
                point.x = b2.x;
                point.y = b2.y;
                if (!b) {
                    this.n -= 50;
                    break;
                }
                break;
            }
            case 2: {
                final Rectangle p2 = ((p)this.c.b("player")).p();
                point.x = p2.x;
                point.y = p2.y;
                break;
            }
            case 3: {
                final Rectangle b3 = ((h)this.c.b("tunnel")).b();
                point.x = b3.x;
                point.y = b3.y;
                break;
            }
            case 4: {
                final Rectangle a = ((aw)this.c.b("other")).a(this.k);
                point.x = a.x;
                point.y = a.y;
                break;
            }
        }
        return point;
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n) {
        t.a("prepare:::");
        this.d = this.c.a(1).a(null);
        this.e = this.c.a(1).b(null);
        final Point a = this.a(this.i, true);
        final Point a2 = this.a(this.j, false);
        for (int i = 0; i < this.a.a(); ++i) {
            final aJ aj = new aJ(this.c, this.c.a(((i)this.a.b(i)).a()), 0, 0);
            aj.a(a.x, a.y);
            aj.a(a.x, a.y);
            aj.a((a2.x - a.x) / 50.0f, (a2.y - a.y) / 50.0f);
            final Point point = a;
            point.x += 8;
            final Point point2 = a;
            point2.y += 8;
            this.g.addElement(aj);
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n, final int n2) {
        if (n2 == 0) {
            for (int i = 0; i < this.g.size(); ++i) {
                final aJ aj = this.g.elementAt(i);
                aj.a(z, 0);
                this.h = aL.b(this.h, aj.b());
            }
        }
        else if (n2 == 1) {
            for (int j = 0; j < this.g.size(); ++j) {
                final aJ aj2 = this.g.elementAt(j);
                aj2.a(z);
                this.h = aL.b(this.h, aj2.b());
            }
            for (int k = 0; k < this.g.size(); ++k) {
                final aJ aj3 = this.g.elementAt(k);
                aj3.a(z, 1);
                this.h = aL.b(this.h, aj3.b());
            }
        }
        if (n2 == 0 && n == this.m && this.j == 3) {
            for (int l = 0; l < this.g.size(); ++l) {
                ((aJ)this.g.elementAt(l)).a();
            }
            this.g.removeAllElements();
            this.b.a(this.a);
            this.f.a(z.d());
            this.h = aL.b(this.h, this.f.p());
        }
        if (n2 == 1) {
            z.a(this.h);
            this.h.setBounds(0, 0, 0, 0);
        }
    }
    
    public boolean b(final com.daysofwonder.applet.z z, final int n) {
        for (int i = 0; i < this.g.size(); ++i) {
            ((aJ)this.g.elementAt(i)).c();
        }
        return n != this.n;
    }
    
    public void a(final com.daysofwonder.applet.z z) {
        for (int i = 0; i < this.g.size(); ++i) {
            ((aJ)this.g.elementAt(i)).a();
        }
        this.g.removeAllElements();
    }
}
