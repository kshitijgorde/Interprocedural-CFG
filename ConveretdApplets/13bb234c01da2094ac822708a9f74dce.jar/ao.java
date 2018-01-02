import com.daysofwonder.applet.aL;
import com.daysofwonder.applet.aJ;
import com.daysofwonder.applet.aG;
import com.daysofwonder.applet.ap;
import java.util.Vector;
import com.daysofwonder.b.b;
import java.awt.Rectangle;
import com.daysofwonder.tt.i;
import com.daysofwonder.applet.aC;

// 
// Decompiled by Procyon v0.5.30
// 

public class ao extends aC
{
    private i[] a;
    private i[] b;
    private G c;
    private z d;
    private Rectangle e;
    private Rectangle f;
    private int g;
    private int h;
    private b i;
    private Rectangle j;
    private O k;
    private Vector l;
    private Rectangle m;
    
    public ao(final i[] a, final i[] b) {
        this.l = new Vector();
        this.m = new Rectangle();
        this.a = a;
        this.b = b;
    }
    
    public void a(final ap ap, final aG ag) {
        this.c = (G)ag;
        this.d = (z)ap;
        this.k = (O)ap.b("deck");
        this.e = this.k.d();
        this.f = this.k.b();
        this.i = this.k.e();
        this.j = this.k.p();
        this.g = this.d.a(1).a(null);
        this.h = this.d.a(1).b(null);
        final int x = this.e.x;
        int y = this.e.y;
        final int n = (this.e.height - this.a.length * this.h) / (this.a.length - 1);
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                final aJ aj = new aJ(this.d, this.d.a(this.a[i].a()), 0, 0);
                aj.a(x, y);
                aj.a(x, y);
                y += n + this.h;
                aj.a((this.f.x - x) / 20.0f, (this.f.y - y) / 20.0f);
                this.l.addElement(aj);
            }
            this.a[i] = null;
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n) {
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n, final int n2) {
        if (n2 == 0) {
            for (int i = 0; i < this.l.size(); ++i) {
                final aJ aj = this.l.elementAt(i);
                aj.a(z, 0);
                this.m = aL.b(this.m, aj.b());
            }
        }
        else if (n2 == 1) {
            for (int j = 0; j < this.l.size(); ++j) {
                final aJ aj2 = this.l.elementAt(j);
                aj2.a(z);
                this.m = aL.b(this.m, aj2.b());
            }
            for (int k = 0; k < this.l.size(); ++k) {
                final aJ aj3 = this.l.elementAt(k);
                aj3.a(z, 1);
                this.m = aL.b(this.m, aj3.b());
            }
        }
        if (n2 == 1) {
            z.a(this.m);
            this.m.setBounds(0, 0, 0, 0);
        }
    }
    
    public boolean b(final com.daysofwonder.applet.z z, final int n) {
        for (int i = 0; i < this.l.size(); ++i) {
            ((aJ)this.l.elementAt(i)).c();
        }
        if (n == 20) {
            for (int j = 0; j < this.l.size(); ++j) {
                ((aJ)this.l.elementAt(j)).a();
            }
            this.l.removeAllElements();
            final int x = this.e.x;
            int y = this.e.y;
            final int n2 = (this.e.height - this.a.length * this.h) / (this.a.length - 1);
            this.c.h(10);
            for (int k = 0; k < this.b.length; ++k) {
                if (this.b[k] != null) {
                    final aJ aj = new aJ(this.d, this.d.a(this.b[k].a()), 0, 0);
                    aj.a(this.f.x, this.f.y);
                    aj.a(this.f.x, this.f.y);
                    y += n2 + this.h;
                    aj.a((x - this.f.x) / 20.0f, (y - this.f.y) / 20.0f);
                    this.l.addElement(aj);
                }
            }
        }
        return n != 40;
    }
    
    public void a(final com.daysofwonder.applet.z z) {
        synchronized (this.c.ab()) {
            System.arraycopy(this.b, 0, this.a, 0, this.a.length);
        }
        for (int i = 0; i < this.l.size(); ++i) {
            ((aJ)this.l.elementAt(i)).a();
        }
        this.l.removeAllElements();
    }
}
