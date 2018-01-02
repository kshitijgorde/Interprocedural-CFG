import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.awt.event.MouseEvent;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_ct extends rp_aC
{
    private Object a;
    private rp_dC a;
    private rp_fx a;
    private boolean a;
    
    public rp_ct(final rp_dF rp_dF, final String s, final rp_fx a) {
        super(rp_dF, s);
        this.a = null;
        this.a = null;
        this.a = false;
        this.a = a;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.a = null;
            final Component component;
            if ((component = mouseEvent.getComponent()) instanceof rp_cZ) {
                this.a = ((rp_cZ)component).a;
                final Point point;
                SwingUtilities.convertPointToScreen(point = (Point)mouseEvent.getPoint().clone(), component);
                if (this.a != null && ((rp_dv)this.a).a()) {
                    this.a = new rp_cv(this.a.a.a(), point.x, point.y, 0.0, (rp_dv)((rp_dv)this.a).clone(), null);
                    ((rp_cv)this.a).a(this.a.a());
                    if (!this.a) {
                        rp_au.a().b();
                        this.a = true;
                    }
                    final rp_N a;
                    (a = this.a.a).a.a();
                    a.c();
                    this.a.a(this.a);
                    this.a.a = null;
                    this.a.setVisible(true);
                    this.a.repaint();
                }
            }
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getModifiers() == 16) {
            if (this.a == null) {
                return;
            }
            if (this.a) {
                rp_au.a().d();
                this.a = false;
            }
            final Point point;
            SwingUtilities.convertPointToScreen(point = (Point)mouseEvent.getPoint().clone(), mouseEvent.getComponent());
            final Point point2 = (Point)point.clone();
            SwingUtilities.convertPointFromScreen(point, this.a);
            this.a.setVisible(false);
            this.a.a(null);
            final Rectangle rectangle;
            (rectangle = new Rectangle(this.a.a.a(), this.a.a.b())).grow((int)(rectangle.getWidth() / 10.0), (int)(rectangle.getHeight() / 10.0));
            if (rectangle.contains(this.a.a())) {
                this.a(new rp_C(this.a, point2, this.a));
            }
            this.a = null;
        }
    }
}
