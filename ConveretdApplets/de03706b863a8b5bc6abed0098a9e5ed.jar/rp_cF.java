import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cF extends MouseMotionAdapter
{
    private rp_dF a;
    
    public rp_cF(final rp_dF a) {
        this.a = a;
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        final Component component = mouseEvent.getComponent();
        final rp_dC a;
        if ((a = this.a.a) == null) {
            return;
        }
        final Point point;
        SwingUtilities.convertPointToScreen(point = (Point)mouseEvent.getPoint().clone(), component);
        final Point b;
        SwingUtilities.convertPointFromScreen(b = new Point(point), this.a);
        this.a.b = b;
        SwingUtilities.convertPointFromScreen(point, this.a.a);
        final Point a2 = this.a.a.a().a(point);
        Point point2;
        if ((point2 = this.a.a) == null) {
            point2 = a.a();
        }
        final int n = a2.x - point2.x;
        final int n2 = a2.y - point2.y;
        if (a.a() != null && a.a().length > 0) {
            this.a.a.g(a);
            this.a.a.a(n, n2);
            this.a.a.g(null);
        }
        else {
            a.a(n, n2);
        }
        this.a.a = (Point)a2.clone();
        this.a.repaint();
    }
}
