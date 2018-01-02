import java.awt.event.MouseEvent;
import com.daysofwonder.util.y;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import com.daysofwonder.util.K;
import java.awt.Rectangle;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.aF;
import com.daysofwonder.applet.am;

// 
// Decompiled by Procyon v0.5.30
// 

public class as extends am implements aF
{
    private G l;
    private z m;
    private b n;
    private Rectangle o;
    private Rectangle p;
    private am q;
    protected K a;
    protected String b;
    protected String c;
    protected K d;
    protected String e;
    protected K f;
    protected String g;
    protected K h;
    protected String i;
    protected int j;
    protected int k;
    
    public as(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.m = (z)ap;
        this.n = ap.c(uiProperties.a(s + ".back"));
        this.o = aL.a(uiProperties, s + ".text.r");
        this.p = aL.a(uiProperties, s + ".progressbar.r");
        this.c = uiProperties2.b(uiProperties.a(s + ".text"));
        this.q = this.N.a("startb.subcontrol", ".subcontrol");
        this.e = uiProperties2.b(uiProperties.a(s + ".loadingtext"));
        this.g = uiProperties2.b(uiProperties.a(s + ".decompressingtext"));
        this.i = uiProperties2.b(uiProperties.a(s + ".waitingtext"));
        this.l = this.m.n();
    }
    
    public void a(final a a) {
        if (this.K) {
            a.a(this.n, this.G.x, this.G.y, null);
            if (this.l.x()) {
                if (this.d == null && this.o != null) {
                    this.j = 0;
                    this.k = 0;
                    a.a(this.N.L());
                    this.d = aL.a(this.e, this.o.width - 15, a.d(), false);
                }
                if (this.d != null) {
                    final FontMetrics d = a.d();
                    final int n = d.getMaxDescent() + d.getMaxAscent() + 1;
                    int n2 = this.o.y + n;
                    final y e = this.d.e();
                    while (e.a()) {
                        aL.a(a, (String)e.b(), this.o.x + 1, n2, this.N.N(), this.N.L());
                        n2 += n;
                    }
                    a.a(this.N.I());
                }
                a.c(this.p.x, this.p.y, this.p.width, this.p.height);
                a.d(this.p.x, this.p.y, this.j / this.k * this.p.width, this.p.height);
            }
            else if (this.l.y()) {
                if (this.f == null && this.o != null) {
                    this.j = 0;
                    this.k = 0;
                    a.a(this.N.L());
                    this.f = aL.a(this.g, this.o.width - 15, a.d(), false);
                }
                if (this.f != null) {
                    final FontMetrics d2 = a.d();
                    final int n3 = d2.getMaxDescent() + d2.getMaxAscent() + 1;
                    int n4 = this.o.y + n3;
                    final y e2 = this.f.e();
                    while (e2.a()) {
                        aL.a(a, (String)e2.b(), this.o.x + 1, n4, this.N.N(), this.N.L());
                        n4 += n3;
                    }
                    a.a(this.N.I());
                }
                a.c(this.p.x, this.p.y, this.p.width, this.p.height);
                a.d(this.p.x, this.p.y, this.j / this.k * this.p.width, this.p.height);
            }
            else if (this.l.k()) {
                if (this.h == null && this.o != null) {
                    a.a(this.N.L());
                    this.h = aL.a(this.i, this.o.width - 15, a.d(), false);
                }
                if (this.h != null) {
                    final FontMetrics d3 = a.d();
                    final int n5 = d3.getMaxDescent() + d3.getMaxAscent() + 1;
                    int n6 = this.o.y + n5;
                    final y e3 = this.h.e();
                    while (e3.a()) {
                        aL.a(a, (String)e3.b(), this.o.x + 1, n6, this.N.N(), this.N.L());
                        n6 += n5;
                    }
                    a.a(this.N.I());
                }
            }
            else if (this.l.j() && this.l.aF()) {
                if (this.b == null && this.c != null) {
                    this.b = this.N.a(this.c, new Object[] { this.O.a((Object)("map." + this.l.aa())) });
                }
                if (this.a == null && this.o != null) {
                    a.a(this.N.L());
                    this.a = aL.a(this.b, this.o.width - 15, a.d(), false);
                }
                if (this.a != null) {
                    final FontMetrics d4 = a.d();
                    final int n7 = d4.getMaxDescent() + d4.getMaxAscent() + 1;
                    int n8 = this.o.y + n7;
                    final y e4 = this.a.e();
                    while (e4.a()) {
                        aL.a(a, (String)e4.b(), this.o.x + 1, n8, this.N.N(), this.N.L());
                        n8 += n7;
                    }
                    a.a(this.N.I());
                }
                this.q.c(this.l.aE() >= this.l.af().e());
                this.q.a(a);
            }
        }
    }
    
    public void a() {
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return this.K && this.l.j() && this.l.aF() && this.q.n().contains(mouseEvent.getPoint()) && this.l.aE() > 1 && this.q.a(mouseEvent);
    }
    
    public void a(final boolean b) {
        super.a(b);
        if (this.q != null) {
            this.q.a(b);
        }
    }
    
    public void b(final int j, final int k) {
        this.j = j;
        this.k = k;
        this.v();
    }
}
