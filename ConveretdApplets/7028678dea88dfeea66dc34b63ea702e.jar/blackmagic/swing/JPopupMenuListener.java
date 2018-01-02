// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;

public class JPopupMenuListener extends MouseAdapter
{
    private JPopupMenu vMenu;
    
    public JPopupMenuListener(final JPopupMenu vMenu) {
        this.vMenu = vMenu;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.processMouseEvent(mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.processMouseEvent(mouseEvent);
    }
    
    private void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.vMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
}
