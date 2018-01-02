import com.daysofwonder.applet.aC;
import java.awt.Color;
import com.daysofwonder.applet.y;
import com.daysofwonder.applet.aE;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import com.daysofwonder.tt.f;
import java.awt.Rectangle;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;
import com.daysofwonder.applet.z;
import com.daysofwonder.util.t;
import java.awt.Point;
import java.awt.event.MouseEvent;
import com.daysofwonder.applet.aH;

// 
// Decompiled by Procyon v0.5.30
// 

public class x implements aH
{
    private int b;
    final /* synthetic */ v a;
    
    public x(final v a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        this.b = this.a.a.g().k().a();
        if (this.b > 0) {
            final int a = this.a.b(this.a.a.g().k(), point);
            t.a("fNbTickets: " + this.b);
            t.a("Chosen: " + a);
            if (a == this.b - 1) {
                t.a("inc : " + this.a.i);
                this.a.i++;
                v.a(this.a, this.b);
                t.a("inc : " + this.a.i);
            }
            else {
                t.a("move : " + this.a.i);
                this.a.i = a + this.a.i % this.b - (this.b - 1);
                t.a("move : " + this.a.i);
                while (this.a.i < 0) {
                    v.c(this.a, this.b);
                }
                t.a("move : " + this.a.i);
                v.a(this.a, this.b);
                t.a("move : " + this.a.i);
            }
        }
        return true;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        return true;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        return true;
    }
    
    public void a(final MouseEvent mouseEvent) {
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n == 1) {
            this.a.a(z.d());
        }
    }
}
