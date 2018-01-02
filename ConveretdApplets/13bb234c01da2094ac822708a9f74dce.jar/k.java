import com.daysofwonder.applet.aL;
import com.daysofwonder.applet.aJ;
import com.daysofwonder.applet.aG;
import com.daysofwonder.applet.ap;
import com.daysofwonder.b.b;
import java.awt.Rectangle;
import java.util.Vector;
import com.daysofwonder.applet.aC;

// 
// Decompiled by Procyon v0.5.30
// 

public class k extends aC
{
    private G a;
    private z b;
    private int c;
    private int d;
    private Vector e;
    private Rectangle f;
    private b g;
    private int h;
    private int i;
    private int j;
    private int k;
    private Runnable l;
    private int m;
    
    public k(final b g, final int h, final int i, final int j, final int k, final Runnable l) {
        this.e = new Vector();
        this.f = new Rectangle();
        this.m = 10;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
    }
    
    public void a(final ap ap, final aG ag) {
        this.a = (G)ag;
        this.b = (z)ap;
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n) {
        this.c = this.g.a(null);
        this.d = this.g.b(null);
        final aJ aj = new aJ(this.b, this.g, 0, 0);
        aj.a(this.h, this.i);
        aj.a(this.h, this.i);
        aj.a((this.j - this.h) / 10.0f, (this.k - this.i) / 10.0f);
        this.e.addElement(aj);
        this.a.h(29);
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n, final int n2) {
        if (n2 == 0) {
            for (int i = 0; i < this.e.size(); ++i) {
                final aJ aj = this.e.elementAt(i);
                aj.a(z, 0);
                this.f = aL.b(this.f, aj.b());
            }
        }
        else if (n2 == 1) {
            for (int j = 0; j < this.e.size(); ++j) {
                final aJ aj2 = this.e.elementAt(j);
                aj2.a(z);
                this.f = aL.b(this.f, aj2.b());
            }
            for (int k = 0; k < this.e.size(); ++k) {
                final aJ aj3 = this.e.elementAt(k);
                aj3.a(z, 1);
                this.f = aL.b(this.f, aj3.b());
            }
        }
        if (n2 == 1) {
            z.a(this.f);
            this.f.setBounds(0, 0, 0, 0);
        }
    }
    
    public boolean b(final com.daysofwonder.applet.z z, final int n) {
        for (int i = 0; i < this.e.size(); ++i) {
            ((aJ)this.e.elementAt(i)).c();
        }
        return n != this.m;
    }
    
    public void a(final com.daysofwonder.applet.z z) {
        for (int i = 0; i < this.e.size(); ++i) {
            ((aJ)this.e.elementAt(i)).a();
        }
        this.e.removeAllElements();
        if (this.l != null) {
            this.l.run();
        }
    }
}
