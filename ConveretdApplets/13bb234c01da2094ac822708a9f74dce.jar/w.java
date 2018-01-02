import java.util.Vector;
import com.daysofwonder.util.t;
import java.awt.Color;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import com.daysofwonder.tt.n;
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

public class w implements aH
{
    private int b;
    private int c;
    final /* synthetic */ E a;
    
    public w(final E a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        final int f = this.a.f(point);
        this.b = f;
        this.c = f;
        if (this.b != -1) {
            if (this.a.r == 1) {
                for (int i = 0; i < this.a.p.length; ++i) {
                    if (i != this.b) {
                        this.a.p[i] = false;
                    }
                }
            }
            this.a.p[this.b] = !this.a.p[this.b];
            return true;
        }
        return false;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        if (this.b != -1) {
            final int f = this.a.f(point3);
            if (f != this.c) {
                this.c = f;
                this.a.p[this.b] = !this.a.p[this.b];
            }
            return true;
        }
        return false;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        if (this.b != -1) {
            if (this.a.f(point) != this.b) {
                this.a.p[this.b] = !this.a.p[this.b];
            }
            return true;
        }
        return false;
    }
    
    public void a(final MouseEvent mouseEvent) {
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n == 1) {
            this.a.a(z.d());
        }
    }
}
