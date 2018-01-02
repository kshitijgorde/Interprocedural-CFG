import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.event.MouseEvent;
import com.daysofwonder.applet.y;
import com.daysofwonder.tt.i;
import com.daysofwonder.b.a;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.aE;

// 
// Decompiled by Procyon v0.5.30
// 

public class av implements aE
{
    private z a;
    private b b;
    private b c;
    private int d;
    private int e;
    private int f;
    private int g;
    private b h;
    private a i;
    private boolean j;
    
    public av() {
    }
    
    public av(final Object o, final z a, final int d, final int e) {
        this.a = a;
        if (o instanceof i) {
            final i i = (i)o;
            this.b = this.a.d(i.a());
            this.f = this.b.a(null);
            this.g = this.b.b(null);
            this.c = this.a.c(i.a());
            this.j = false;
        }
        else if (o instanceof b) {
            final b b = (b)o;
            this.b = b;
            this.f = b.a(null);
            this.g = b.b(null);
            this.c = y.a(b);
            this.j = true;
        }
        this.d = d;
        this.e = e;
        this.h = this.a.a(this.f, this.g);
        if (this.h != null) {
            this.i = this.h.a();
        }
    }
    
    public void a() {
        if (this.i != null) {
            this.i.g();
        }
        if (this.h != null) {
            this.h.c();
        }
        if (this.j) {
            this.c.c();
        }
    }
    
    public void a(final com.daysofwonder.applet.z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n, final boolean b) {
        if (n == 0) {
            z.a(this.h, point2.x - this.d, point2.y - this.e, null);
        }
        else {
            this.a.a(this.i, point3.x - this.d, point3.y - this.e, this.f + 1, this.g + 1);
            if (b || this.c == null) {
                z.a(this.b, point3.x - this.d, point3.y - this.e, 0.75f, null);
            }
            else {
                z.a(this.c, point3.x - this.d, point3.y - this.e, 0.75f, null);
            }
            if (y.a) {
                Toolkit.getDefaultToolkit().sync();
            }
        }
    }
    
    public int b() {
        return this.d;
    }
    
    public int c() {
        return this.e;
    }
    
    public void a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
    }
}
