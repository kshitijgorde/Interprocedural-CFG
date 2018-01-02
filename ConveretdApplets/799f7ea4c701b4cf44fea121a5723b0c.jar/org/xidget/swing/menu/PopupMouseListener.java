// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;
import org.xidget.IXidget;
import java.awt.event.MouseAdapter;

public class PopupMouseListener extends MouseAdapter
{
    private IXidget xidget;
    
    public PopupMouseListener(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        final JPopupMenu popupMenu = this.xidget.getFeature(JPopupMenu.class);
        if (mouseEvent.getButton() == 3) {
            popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
}
