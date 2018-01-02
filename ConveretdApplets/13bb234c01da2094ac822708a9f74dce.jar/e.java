import java.awt.image.ImageObserver;
import com.daysofwonder.applet.aL;
import java.awt.Point;
import com.daysofwonder.applet.aG;
import com.daysofwonder.applet.ap;
import java.awt.Rectangle;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.n;
import com.daysofwonder.applet.aC;

// 
// Decompiled by Procyon v0.5.30
// 

public class e extends aC
{
    private n a;
    private o b;
    private G c;
    private z d;
    private int e;
    private D f;
    private int g;
    private int h;
    private Rectangle i;
    private float j;
    private Rectangle k;
    
    public e(final n a, final o b) {
        this.e = 55;
        this.h = -1;
        this.k = new Rectangle();
        this.a = a;
        this.b = b;
    }
    
    public void a(final ap ap, final aG ag) {
        this.c = (G)ag;
        this.d = (z)ap;
        this.f = (D)ap.b("board");
        this.g = this.f.a(1);
        this.h = ((this.b != null) ? this.c.j(this.b.d()) : -1);
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n) {
        final Point b = this.f.b(this.b);
        this.i = new Rectangle(b.x, b.y, 0, 0);
    }
    
    public void a(final com.daysofwonder.applet.z z, final int n, final int n2) {
        if (this.h > 0 && this.i != null && this.d.o() != null) {
            z.a(3, this.j);
            z.a(this.d.o(), aL.b(z.e(), this.h, this.i, null, this.d.m()) - this.d.o().a(null), this.i.y, null);
            z.a();
        }
        if (n2 == 1) {
            z.a(this.k);
            this.k.setBounds(0, 0, 0, 0);
        }
    }
    
    public boolean b(final com.daysofwonder.applet.z z, final int n) {
        if (n <= this.e) {
            this.j = 1.0f - n / 55.0f;
        }
        return n != this.e;
    }
    
    public void a(final com.daysofwonder.applet.z z) {
    }
}
