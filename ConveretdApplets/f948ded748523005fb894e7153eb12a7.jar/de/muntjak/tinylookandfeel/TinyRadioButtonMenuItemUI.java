// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Point;
import javax.swing.MenuSelectionManager;
import javax.swing.MenuElement;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;

public class TinyRadioButtonMenuItemUI extends TinyMenuItemUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinyRadioButtonMenuItemUI();
    }
    
    protected String getPropertyPrefix() {
        return "RadioButtonMenuItem";
    }
    
    public void processMouseEvent(final JMenuItem menuItem, final MouseEvent mouseEvent, final MenuElement[] selectedPath, final MenuSelectionManager menuSelectionManager) {
        final Point point = mouseEvent.getPoint();
        if (point.x >= 0 && point.x < menuItem.getWidth() && point.y >= 0 && point.y < menuItem.getHeight()) {
            if (mouseEvent.getID() == 502) {
                menuSelectionManager.clearSelectedPath();
                menuItem.doClick(0);
                menuItem.setArmed(false);
            }
            else {
                menuSelectionManager.setSelectedPath(selectedPath);
            }
        }
        else if (menuItem.getModel().isArmed()) {
            final MenuElement[] selectedPath2 = new MenuElement[selectedPath.length - 1];
            for (int i = 0; i < selectedPath.length - 1; ++i) {
                selectedPath2[i] = selectedPath[i];
            }
            menuSelectionManager.setSelectedPath(selectedPath2);
        }
    }
}
