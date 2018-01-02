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

public class W implements aH
{
    private int b;
    private int c;
    final /* synthetic */ f a;
    
    public W(final f a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        final int a_ = this.a.a_(point);
        this.b = a_;
        this.c = a_;
        if (this.b != -1) {
            this.a.r[this.b] = !this.a.r[this.b];
            return true;
        }
        return false;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        if (this.b != -1) {
            final int a_ = this.a.a_(point3);
            if (a_ != this.c) {
                this.c = a_;
                this.a.r[this.b] = !this.a.r[this.b];
            }
            return true;
        }
        return false;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        if (this.b != -1) {
            if (this.a.a_(point) != this.b) {
                this.a.r[this.b] = !this.a.r[this.b];
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
