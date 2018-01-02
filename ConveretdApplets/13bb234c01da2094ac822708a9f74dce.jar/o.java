import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;

// 
// Decompiled by Procyon v0.5.30
// 

public class o extends am
{
    private G a;
    private z b;
    private b c;
    private b d;
    private b e;
    private b f;
    private b g;
    
    public o(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.b = (z)ap;
        this.d = ap.c(uiProperties.a(s + ".deck1"));
        this.e = ap.c(uiProperties.a(s + ".deck2"));
        this.f = ap.c(uiProperties.a(s + ".deck3"));
        this.g = ap.c(uiProperties.a(s + ".deck4"));
        this.a = this.b.n();
    }
    
    public void a(final a a) {
        if (this.K) {
            if (this.c == null) {
                this.c = this.N.b(this.G);
            }
            a.a(this.c, this.G.x, this.G.y, null);
            if (!this.a.Q() && this.a.M() > 0) {
                a.a(this.d(), this.G.x, this.G.y, null);
            }
        }
    }
    
    private b d() {
        final int n = this.a.M() * 100 / this.a.N();
        if (n >= 75 && n <= 100) {
            return this.d;
        }
        if (n >= 50 && n <= 75) {
            return this.e;
        }
        if (n >= 25 && n <= 50) {
            return this.f;
        }
        return this.g;
    }
    
    public void a() {
        if (this.c != null) {
            this.c.c();
            this.c = null;
        }
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
}
