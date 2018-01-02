import com.daysofwonder.tt.d;
import com.daysofwonder.applet.aN;
import com.daysofwonder.applet.H;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import com.daysofwonder.applet.L;
import com.daysofwonder.applet.Y;
import com.daysofwonder.applet.am;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class aw extends am implements Y
{
    protected G a;
    protected z b;
    protected L c;
    private int d;
    
    public aw(final ap ap, final String s, final UIProperties uiProperties, final UIProperties uiProperties2) {
        super(ap, s, uiProperties, uiProperties2);
        this.d = -1;
        this.b = (z)ap;
    }
    
    public boolean a(final MouseEvent mouseEvent) {
        return false;
    }
    
    public abstract Rectangle a(final int p0);
    
    public void c() {
        this.d = -1;
    }
    
    public int b() {
        return this.d;
    }
    
    public void b(final int d) {
        this.d = d;
    }
    
    public Vector a(final Object o) {
        final int intValue = (int)o;
        final Vector<H> vector = new Vector<H>();
        if (this.a != null && intValue >= 0 && intValue < this.a.aE()) {
            final d b = this.a.b(intValue);
            if (!b.E()) {
                if (this.a.aG() == 0 && (this.a.k() || this.a.j() || this.a.x())) {
                    vector.add(new H(this.O.a("kick", new Object[] { b.z() }), 1, b));
                    vector.add((H)new aN());
                }
                final boolean r = this.a.r(b.w());
                final boolean q = this.a.q(b.w());
                if (q) {
                    vector.add(new H(this.O.a("remove_ignore", new Object[] { b.z() }), 4, b));
                }
                else if (!r) {
                    vector.add(new H(this.O.a("add_ignore", new Object[] { b.z() }), 2, b));
                }
                if (r) {
                    vector.add(new H(this.O.a("remove_buddy", new Object[] { b.z() }), 5, b));
                }
                else if (!q) {
                    vector.add(new H(this.O.a("add_buddy", new Object[] { b.z() }), 3, b));
                }
                vector.add((H)new aN());
                H h;
                if (b.D()) {
                    h = new H(this.O.a("rank", new Object[] { b.x(), (int)b.y() }));
                }
                else {
                    h = new H(this.O.b("notranked"));
                }
                h.a(false);
                vector.add(h);
            }
            else {
                final H h2 = new H(this.O.a("robot", new Object[] { b.z() }));
                h2.a(false);
                vector.add(h2);
            }
            return vector;
        }
        return null;
    }
}
