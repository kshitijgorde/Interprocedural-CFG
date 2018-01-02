import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_fJ implements ActionListener
{
    private /* synthetic */ rp_aZ a;
    
    rp_fJ(final rp_aZ a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final rp_fX rp_fX = new rp_fX(rp_au.a.a().a(0, "tx3"));
        final Point location;
        SwingUtilities.convertPointToScreen(location = new Point(0, 0), this.a.a);
        SwingUtilities.convertPointFromScreen(location, rp_au.a());
        final Point point = location;
        point.y += this.a.a.getSize().height;
        rp_fX.setLocation(location);
        rp_fX.setVisible(true);
        if (rp_fX.a() != null) {
            this.a.a.setBackground(rp_fX.a());
        }
    }
}
