import java.awt.Rectangle;
import com.daysofwonder.tt.f;
import com.daysofwonder.tt.d;
import com.daysofwonder.b.a;
import com.daysofwonder.util.t;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.c;
import com.daysofwonder.tt.i;
import com.daysofwonder.applet.aE;
import java.awt.Image;
import java.awt.Color;
import com.daysofwonder.applet.aL;
import com.daysofwonder.applet.ap;
import com.daysofwonder.tt.e;
import com.daysofwonder.util.UIProperties;
import java.util.Vector;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;
import com.daysofwonder.applet.z;
import java.awt.Point;
import java.awt.event.MouseEvent;
import com.daysofwonder.applet.aH;

// 
// Decompiled by Procyon v0.5.30
// 

public class U implements aH
{
    private boolean b;
    final /* synthetic */ D a;
    
    public U(final D a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        return true;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        this.a.e.d(point2.x - point3.x, point2.y - point3.y);
        return true;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        return true;
    }
    
    public void a(final MouseEvent mouseEvent) {
        this.b = true;
        this.a.b.A();
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n != 0) {
            if (!this.b) {
                this.a.e.a(z, this.a.G.width, this.a.G.height, this.a.G.x, this.a.G.y);
            }
        }
    }
}
