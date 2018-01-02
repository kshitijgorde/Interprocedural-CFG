import java.awt.event.MouseEvent;
import com.daysofwonder.tt.i;
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

public class h extends am
{
    private G a;
    private z b;
    private b c;
    private Rectangle d;
    private Rectangle e;
    private Rectangle f;
    private com.daysofwonder.util.G g;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    
    public h(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.h = true;
        this.n = 0;
        this.b = (z)ap;
        this.d = aL.a(uiProperties, s + ".cards.r");
        this.e = aL.a(uiProperties, s + ".msg1.r");
        this.f = aL.a(uiProperties, s + ".msg2.r");
        this.c = ap.c(uiProperties.a(s + ".back"));
        this.j = Integer.parseInt(uiProperties.a(s + ".maxspace"));
        this.i = Integer.parseInt(uiProperties.a(s + ".minspace"));
        this.a = this.b.n();
        if (uiProperties.a(s + ".active") != null) {
            this.h = !uiProperties.a(s + ".active").equalsIgnoreCase("false");
        }
    }
    
    public void a(final a a) {
        if (this.K) {
            a.a(this.c, this.G.x, this.G.y, null);
            this.a(this.a.X());
            if (this.g != null) {
                a.a(Color.white);
                aL.a(a, this.e, this.O.b("tunnel.text1"), this.N.M(), 0);
                if (this.a.K() != null) {
                    aL.a(a, this.f, this.N.a(this.O.b("tunnel.text2"), new Object[] { this.a.K() }), this.N.M(), 0);
                }
                a.a(Color.black);
                this.b(a);
            }
        }
    }
    
    public void a() {
    }
    
    public void a(final com.daysofwonder.util.G g) {
        this.g = g;
        this.k = true;
    }
    
    public void b(final a a) {
        if (this.k && this.g.a() > 0) {
            this.k = false;
            final b a2 = this.b.a(((i)this.g.b(0)).a());
            this.l = a2.a(null);
            this.m = a2.b(null);
        }
        int x = this.d.x;
        final int n = this.d.y + 5;
        int n2 = 0;
        if (this.g.a() > 1) {
            n2 = (this.d.width - this.g.a() * this.l) / (this.g.a() - 1);
            if (n2 < this.i) {
                n2 = this.i;
            }
            if (n2 > this.j) {
                n2 = this.j;
            }
        }
        else {
            x += (this.d.width - this.l) / 2;
        }
        for (int i = 0; i < this.g.a(); ++i) {
            a.a(this.b.a(((i)this.g.b(i)).a()), x, n, null);
            x += n2 + this.l;
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
    
    public void b(final MouseEvent mouseEvent) {
    }
    
    public Rectangle b() {
        return this.d;
    }
}
