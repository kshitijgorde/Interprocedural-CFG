import com.daysofwonder.applet.aL;
import com.daysofwonder.applet.aJ;
import com.daysofwonder.applet.ap;
import java.util.Vector;
import com.daysofwonder.b.b;
import java.awt.Rectangle;
import com.daysofwonder.applet.aG;
import com.daysofwonder.tt.i;
import com.daysofwonder.applet.aC;

// 
// Decompiled by Procyon v0.5.30
// 

public class ar extends aC
{
    private i[] a;
    private i b;
    private int c;
    private int d;
    private aG e;
    private z f;
    private Rectangle g;
    private Rectangle h;
    private int i;
    private int j;
    private b k;
    private Rectangle l;
    private O m;
    private Vector n;
    private Rectangle o;
    private static int p;
    private static int q;
    private static int r;
    private static int s;
    
    public ar(final i[] a, final i b, final int c) {
        this.n = new Vector();
        this.o = new Rectangle();
        this.a = a;
        this.c = c;
        this.b = b;
        this.d = -1;
    }
    
    public ar(final i[] a, final i b, final int c, final int d) {
        this.n = new Vector();
        this.o = new Rectangle();
        this.a = a;
        this.c = c;
        this.b = b;
        this.d = d;
    }
    
    public void a(final ap ap, final aG e) {
        this.e = e;
        this.f = (z)ap;
        this.m = (O)ap.b("deck");
        this.g = this.m.d();
        this.h = this.m.b();
        this.k = this.m.e();
        this.l = this.m.p();
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n) {
        this.i = this.f.a(1).a(null);
        this.j = this.f.a(1).b(null);
        if (this.d == -1) {
            final int x = this.g.x;
            final int y = this.g.y;
            final int n2 = (this.g.height - this.a.length * this.j) / (this.a.length - 1);
            final aJ aj = new aJ(this.f, this.f.a(this.b.a()), 0, 0);
            aj.a(this.h.x, this.h.y);
            aj.a(this.h.x, this.h.y);
            aj.a((x - this.h.x) / ar.q, (y + (n2 + this.j) * this.c - this.h.y) / ar.q);
            this.n.addElement(aj);
        }
        else if (this.d >= 0) {
            int n3;
            int y2;
            if (this.c == -1) {
                n3 = this.h.x;
                y2 = this.h.y;
            }
            else {
                n3 = this.g.x;
                y2 = this.g.y + ((this.g.height - this.a.length * this.j) / (this.a.length - 1) + this.j) * this.c;
            }
            final aJ aj2 = new aJ(this.f, this.f.a(this.b.a()), 0, 0);
            aj2.a(n3, y2);
            aj2.a(n3, y2);
            final aw aw = (aw)this.f.b("other");
            Rectangle rectangle;
            if (aw != null) {
                rectangle = aw.a(this.d);
                if (rectangle == null) {
                    rectangle = ((p)this.f.b("player")).p();
                }
            }
            else {
                rectangle = new Rectangle();
            }
            aj2.a((rectangle.x - n3) / ar.q, (rectangle.y - y2) / ar.q);
            this.n.addElement(aj2);
        }
        else {
            int n4;
            int y3;
            if (this.c == -1) {
                n4 = this.h.x;
                y3 = this.h.y;
            }
            else {
                n4 = this.g.x;
                y3 = this.g.y + ((this.g.height - this.a.length * this.j) / (this.a.length - 1) + this.j) * this.c;
            }
            final aJ aj3 = new aJ(this.f, this.f.a(this.b.a()), 0, 0);
            aj3.a(n4, y3);
            aj3.a(n4, y3);
            final Rectangle p2 = ((p)this.f.b("player")).p();
            aj3.a((p2.x - n4) / ar.q, (p2.y - y3) / ar.q);
            this.n.addElement(aj3);
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n, final int n2) {
        if (n2 == 0) {
            for (int i = 0; i < this.n.size(); ++i) {
                final aJ aj = this.n.elementAt(i);
                aj.a(z, 0);
                this.o = aL.b(this.o, aj.b());
            }
        }
        else if (n2 == 1) {
            for (int j = 0; j < this.n.size(); ++j) {
                final aJ aj2 = this.n.elementAt(j);
                aj2.a(z);
                this.o = aL.b(this.o, aj2.b());
            }
            for (int k = 0; k < this.n.size(); ++k) {
                final aJ aj3 = this.n.elementAt(k);
                aj3.a(z, 1);
                this.o = aL.b(this.o, aj3.b());
            }
        }
        if (n2 == 1) {
            z.a(this.o);
            this.o.setBounds(0, 0, 0, 0);
        }
    }
    
    public boolean b(final com.daysofwonder.applet.z z, final int n) {
        for (int i = 0; i < this.n.size(); ++i) {
            ((aJ)this.n.elementAt(i)).c();
        }
        return n != ar.s;
    }
    
    public void a(final com.daysofwonder.applet.z z) {
        if (this.c >= 0 && this.d == -1) {
            this.a[this.c] = this.b;
        }
        for (int i = 0; i < this.n.size(); ++i) {
            ((aJ)this.n.elementAt(i)).a();
        }
        this.n.removeAllElements();
    }
    
    static {
        ar.p = 20;
        ar.q = ar.p;
        ar.r = 0;
        ar.s = ar.r + ar.q;
    }
}
