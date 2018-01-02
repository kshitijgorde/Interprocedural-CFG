import com.daysofwonder.applet.z;
import com.daysofwonder.tt.d;
import java.awt.Point;
import java.awt.event.MouseEvent;
import com.daysofwonder.applet.aH;

// 
// Decompiled by Procyon v0.5.30
// 

public class ak implements aH
{
    private int b;
    final /* synthetic */ N a;
    
    public ak(final N a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        this.b = this.a.f(point);
        if (this.b != -1) {
            final d b = this.a.a.b(this.b);
            if (b != null) {
                this.a.B = b.k();
                this.a.C = b.q();
            }
            return true;
        }
        return false;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        return true;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        this.a.B = null;
        this.a.C = null;
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
