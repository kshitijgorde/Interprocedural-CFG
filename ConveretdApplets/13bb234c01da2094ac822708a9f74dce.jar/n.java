import com.daysofwonder.applet.aL;
import java.awt.Point;
import com.daysofwonder.applet.aJ;
import com.daysofwonder.tt.i;
import com.daysofwonder.util.t;
import com.daysofwonder.applet.aG;
import com.daysofwonder.applet.ap;
import java.awt.Rectangle;
import java.util.Vector;
import com.daysofwonder.tt.c;
import com.daysofwonder.applet.aC;

// 
// Decompiled by Procyon v0.5.30
// 

public class n extends aC
{
    private com.daysofwonder.tt.n a;
    private G b;
    private z c;
    private c d;
    private int e;
    private int f;
    private D g;
    private Vector h;
    private Rectangle i;
    private int j;
    private int k;
    
    public n(final com.daysofwonder.tt.n a, final c d, final int k) {
        this.h = new Vector();
        this.i = new Rectangle();
        this.a = a;
        this.d = d;
        this.k = k;
    }
    
    public void a(final ap ap, final aG ag) {
        this.b = (G)ag;
        this.c = (z)ap;
        this.g = (D)ap.b("board");
        this.j = this.g.a(1);
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n) {
        t.a("prepare:::");
        this.e = this.c.a(1).a(null);
        this.f = this.c.a(1).b(null);
        int n2;
        int n3;
        if (this.k == -1) {
            final Rectangle p2 = ((p)this.c.b("player")).p();
            n2 = p2.x;
            n3 = p2.y;
        }
        else {
            Rectangle rectangle = null;
            final aw aw = (aw)this.c.b("other");
            if (aw != null) {
                rectangle = aw.a(this.k);
                if (rectangle == null) {
                    rectangle = ((p)this.c.b("player")).p();
                }
            }
            n2 = rectangle.x;
            n3 = rectangle.y;
        }
        final Point a = this.d.a(((D)this.c.b("board")).f());
        for (int i = 0; i < this.a.d(); ++i) {
            final aJ aj = new aJ(this.c, this.c.a(((i)this.a.a(i)).a()), 0, 0);
            aj.a(n2, n3);
            aj.a(n2, n3);
            aj.a((a.x - n2) / 50.0f, (a.y - n3) / 50.0f);
            n2 += 8;
            n3 += 8;
            this.h.addElement(aj);
        }
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
        if (n2 == 0 && n == 50) {
            for (int l = 0; l < this.h.size(); ++l) {
                ((aJ)this.h.elementAt(l)).a();
            }
            this.h.removeAllElements();
            this.g.t();
            this.g.a(this.d, this.b.f(this.k));
            this.g.e();
            this.g.a(z.d());
            this.d = null;
            this.i = aL.b(this.i, this.g.p());
            this.g.s();
        }
        if (n2 == 1) {
            z.a(this.i);
            this.i.setBounds(0, 0, 0, 0);
        }
    }
    
    public boolean b(final com.daysofwonder.applet.z z, final int n) {
        for (int i = 0; i < this.h.size(); ++i) {
            ((aJ)this.h.elementAt(i)).c();
        }
        return n != 75;
    }
    
    public void a(final com.daysofwonder.applet.z z) {
        for (int i = 0; i < this.h.size(); ++i) {
            ((aJ)this.h.elementAt(i)).a();
        }
        this.h.removeAllElements();
        if (this.d != null) {
            this.g.a(this.d, this.b.f(this.k));
            this.g.e();
        }
        this.g.a(this.j);
    }
}
