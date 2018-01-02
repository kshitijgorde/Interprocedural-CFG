import com.daysofwonder.applet.aL;
import com.daysofwonder.tt.f;
import com.daysofwonder.applet.aJ;
import com.daysofwonder.applet.aG;
import com.daysofwonder.applet.ap;
import java.util.Vector;
import java.awt.Rectangle;
import com.daysofwonder.applet.aC;

// 
// Decompiled by Procyon v0.5.30
// 

public class an extends aC
{
    private int a;
    private G b;
    private z c;
    private int d;
    private int e;
    private Rectangle f;
    private Rectangle g;
    private v h;
    private Vector i;
    private Rectangle j;
    private int k;
    private com.daysofwonder.util.G l;
    
    public an(final int a, final int k) {
        this.i = new Vector();
        this.j = new Rectangle();
        this.a = a;
        this.k = k;
    }
    
    public an(final int a, final com.daysofwonder.util.G l) {
        this.i = new Vector();
        this.j = new Rectangle();
        this.a = a;
        this.l = l;
    }
    
    public void a(final ap ap, final aG ag) {
        this.b = (G)ag;
        this.c = (z)ap;
        this.h = (v)ap.b("tickets");
        this.f = this.h.c();
        this.g = this.h.b();
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n) {
        this.d = this.b.d(1).a(null);
        this.e = this.b.d(1).b(null);
        if (this.l == null) {
            Rectangle rectangle;
            Rectangle rectangle2;
            if (this.k > 0) {
                final aw aw = (aw)this.c.b("other");
                if (aw != null) {
                    final Rectangle a = aw.a(this.a);
                    if (a != null) {
                        rectangle = new Rectangle(a);
                    }
                    else {
                        rectangle = new Rectangle(((p)this.c.b("player")).p());
                    }
                }
                else {
                    rectangle = new Rectangle();
                }
                rectangle2 = new Rectangle(this.g);
            }
            else {
                rectangle = new Rectangle(this.g);
                final aw aw2 = (aw)this.c.b("other");
                if (aw2 != null) {
                    final Rectangle a2 = aw2.a(this.a);
                    if (a2 != null) {
                        rectangle2 = new Rectangle(a2);
                    }
                    else {
                        rectangle2 = new Rectangle(((p)this.c.b("player")).p());
                    }
                }
                else {
                    rectangle2 = new Rectangle();
                }
                this.k = -this.k;
            }
            for (int i = 0; i < this.k; ++i) {
                final aJ aj = new aJ(this.c, this.h.d(), 0, 0);
                aj.a(rectangle.x, rectangle.y);
                aj.a(rectangle.x, rectangle.y);
                aj.a((rectangle2.x - rectangle.x) / 30.0f, (rectangle2.y - rectangle.y) / 30.0f);
                this.i.addElement(aj);
                final Rectangle rectangle3 = rectangle;
                rectangle3.x += 8;
                final Rectangle rectangle4 = rectangle;
                rectangle4.y += 8;
                final Rectangle rectangle5 = rectangle2;
                rectangle5.x += 8;
                final Rectangle rectangle6 = rectangle2;
                rectangle6.y += 8;
            }
        }
        else {
            final Rectangle rectangle7 = new Rectangle(this.g);
            final Rectangle rectangle8 = new Rectangle(this.f);
            rectangle8.y = rectangle8.y + rectangle8.height - this.e;
            for (int j = 0; j < this.l.a(); ++j) {
                final aJ aj2 = new aJ(this.c, this.b.d(((f)this.l.b(j)).j()), 0, 0);
                aj2.a(rectangle7.x, rectangle7.y);
                aj2.a(rectangle7.x, rectangle7.y);
                aj2.a((rectangle8.x - rectangle7.x) / 30.0f, (rectangle8.y - rectangle7.y) / 30.0f);
                this.i.addElement(aj2);
                final Rectangle rectangle9 = rectangle7;
                rectangle9.x += 4;
                final Rectangle rectangle10 = rectangle7;
                rectangle10.y += 4;
                final Rectangle rectangle11 = rectangle8;
                rectangle11.x += 4;
                final Rectangle rectangle12 = rectangle8;
                rectangle12.y += 4;
            }
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n, final int n2) {
        if (n2 == 0) {
            for (int i = 0; i < this.i.size(); ++i) {
                final aJ aj = this.i.elementAt(i);
                aj.a(z, 0);
                this.j = aL.b(this.j, aj.b());
            }
        }
        else if (n2 == 1) {
            for (int j = 0; j < this.i.size(); ++j) {
                final aJ aj2 = this.i.elementAt(j);
                aj2.a(z);
                this.j = aL.b(this.j, aj2.b());
            }
            for (int k = 0; k < this.i.size(); ++k) {
                final aJ aj3 = this.i.elementAt(k);
                aj3.a(z, 1);
                this.j = aL.b(this.j, aj3.b());
            }
        }
        if (n2 == 1) {
            z.a(this.j);
            this.j.setBounds(0, 0, 0, 0);
        }
    }
    
    public boolean b(final com.daysofwonder.applet.z z, final int n) {
        for (int i = 0; i < this.i.size(); ++i) {
            ((aJ)this.i.elementAt(i)).c();
        }
        return n != 30;
    }
    
    public void a(final com.daysofwonder.applet.z z) {
        for (int i = 0; i < this.i.size(); ++i) {
            ((aJ)this.i.elementAt(i)).a();
        }
        this.i.removeAllElements();
    }
}
