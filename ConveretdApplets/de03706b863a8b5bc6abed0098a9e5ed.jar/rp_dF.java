import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.awt.AlphaComposite;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dF extends JPanel
{
    private AlphaComposite a;
    rp_dC a;
    public rp_fx a;
    public rp_N a;
    private Point c;
    public Point a;
    Point b;
    
    public rp_dF() {
        this.a = null;
        this.a = null;
        this.b = null;
        this.setOpaque(false);
        this.a = AlphaComposite.getInstance(3, 0.5f);
    }
    
    public final void a(final rp_dC a) {
        this.a = a;
        if (this.a != null) {
            final Point point;
            SwingUtilities.convertPointToScreen(point = new Point(0, 0), this.a);
            final Point point2;
            SwingUtilities.convertPointToScreen(point2 = new Point(0, 0), this);
            this.c = new Point(this.a.a().a(new Point(point2.x - point.x, point2.y - point.y)));
        }
    }
    
    public final void paintComponent(Graphics a) {
        if (this.a == null) {
            return;
        }
        ((Graphics2D)a).setComposite(this.a);
        final Image a2;
        if (this.a.a().c("drag").equals("ph") && this.b != null && this.a instanceof rp_cv && (a2 = ((rp_cv)this.a).a.a().a(120)) != null && a2.getWidth(null) > 0) {
            a.drawImage(a2, this.b.x - a2.getWidth(null) / 2, this.b.y - a2.getHeight(null) / 2, null);
            return;
        }
        final rp_eV rp_eV2;
        final rp_eV rp_eV = rp_eV2 = new rp_eV(this.c, this.a.a.a);
        a = a;
        rp_eV.a = a;
        new rp_q(rp_eV2);
        this.a.a(rp_eV2, rp_au.a);
    }
}
