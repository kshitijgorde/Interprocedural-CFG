import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fH extends rp_cZ implements ActionListener
{
    public rp_fH(final rp_dv rp_dv, final rp_dl rp_dl) {
        super(rp_dv, rp_dl);
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (this.a != null && rp_au.a.a().a() >= 10 && mouseEvent.getClickCount() == 1 && mouseEvent.getButton() == 3) {
            final rp_fb a = rp_au.a.a();
            final JPopupMenu popupMenu = new JPopupMenu();
            final JMenuItem menuItem;
            (menuItem = new JMenuItem(a.a(0, "pl7"))).addActionListener(this);
            menuItem.setActionCommand("C");
            popupMenu.add(menuItem);
            popupMenu.show(this, mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        super.mouseClicked(mouseEvent);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        rp_fu.a(new rp_cv(0, 0, 0, 0.0, (rp_dv)this.a.clone(), null), 30);
    }
}
