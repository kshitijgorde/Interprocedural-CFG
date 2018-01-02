import com.daysofwonder.applet.aE;
import java.util.Vector;
import java.awt.Color;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import java.awt.Rectangle;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;
import com.daysofwonder.applet.z;
import java.awt.Point;
import java.awt.event.MouseEvent;
import com.daysofwonder.applet.aH;

// 
// Decompiled by Procyon v0.5.30
// 

public class Y implements aH
{
    private int b;
    private D c;
    final /* synthetic */ f a;
    
    public Y(final f a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        this.a.u = this.a.a_(point);
        if (this.a.u != -1) {
            this.a.t = true;
            this.c = (D)this.a.N.b("board");
            this.b = this.c.a(1);
            this.c.a((com.daysofwonder.tt.f)this.a.i.b(this.a.u));
            this.c.e();
            return true;
        }
        return false;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        return true;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        this.c.b((com.daysofwonder.tt.f)this.a.i.b(this.a.u));
        this.c.a(this.b);
        this.c.e();
        this.a.t = false;
        return true;
    }
    
    public void a(final MouseEvent mouseEvent) {
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n == 1) {
            this.c.a(z.d());
        }
    }
}
