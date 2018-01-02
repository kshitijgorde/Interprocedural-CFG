import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_W extends rp_cf
{
    private rp_fa a;
    private rp_dC a;
    private Point a;
    
    public rp_W(final rp_dC a, final rp_aJ rp_aJ, final Point a2, final Object o) {
        super(o);
        this.a = a;
        this.a = a2;
        this.a = new rp_fa(a2, "tt_w_del", 1, rp_aJ.a("del.gif"));
    }
    
    public final void a(final Graphics graphics) {
        this.a.a(graphics);
    }
    
    public final Rectangle a() {
        final Rectangle rectangle;
        (rectangle = new Rectangle(this.a)).grow(this.a.a(), this.a.b());
        return rectangle;
    }
    
    public final boolean a(final Point point, final boolean b) {
        return this.a.a(point) || (!b && this.a != null && this.a.contains(point));
    }
    
    public final boolean a(final MouseEvent mouseEvent) {
        return false;
    }
    
    public final boolean b(final MouseEvent mouseEvent) {
        return false;
    }
    
    public final boolean c(final MouseEvent mouseEvent) {
        return this.a(mouseEvent.getPoint(), true);
    }
    
    public final boolean d(final MouseEvent mouseEvent) {
        if (this.a.a(mouseEvent.getPoint())) {
            rp_dM.a.a(new rp_dM((rp_c)this.a, (int)this.a));
            return true;
        }
        return false;
    }
    
    public final void a(final Rectangle rectangle) {
    }
}
