import com.daysofwonder.applet.aC;
import java.awt.Color;
import com.daysofwonder.applet.y;
import com.daysofwonder.applet.aE;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.tt.f;
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

public class ap implements aH
{
    private int b;
    private D c;
    final /* synthetic */ v a;
    
    public ap(final v a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        this.a.l = this.a.a(this.a.a.g().k(), point);
        if (this.a.l != null) {
            this.a.j = true;
            this.c = (D)this.a.N.b("board");
            this.b = this.c.a(1);
            this.c.a(this.a.l);
            this.c.e();
            return true;
        }
        return false;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        return true;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        this.a.j = false;
        if (this.a.l != null) {
            this.c.b(this.a.l);
            this.c.a(this.b);
            this.c.e();
        }
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
