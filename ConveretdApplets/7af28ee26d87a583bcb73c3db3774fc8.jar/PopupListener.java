import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class PopupListener extends MouseAdapter
{
    JPopupMenu _popupMenu;
    
    PopupListener(final JPopupMenu popupMenu) {
        this._popupMenu = popupMenu;
    }
    
    public void mousePressed(final MouseEvent e) {
        this.pShowPopup(e);
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.pShowPopup(e);
    }
    
    private void pShowPopup(final MouseEvent e) {
        if (e.isPopupTrigger()) {
            this._popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
