// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.AWTEvent;
import java.awt.Panel;

public class MenuPanel extends Panel implements MenuItemGroup
{
    protected void processEvent(final AWTEvent awtEvent) {
        if (awtEvent instanceof LaunchEvent) {
            this.clearItemsSelection();
            return;
        }
        super.processEvent(awtEvent);
    }
    
    public void itemSelectedNotify(final MenuItem menuItem) {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof MenuItem && ((MenuItem)components[i]).getState()) {
                ((MenuItem)components[i]).setState(false);
                break;
            }
        }
        menuItem.setState(true);
    }
    
    public void clearItemsSelection() {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof MenuItem && ((MenuItem)components[i]).getState()) {
                ((MenuItem)components[i]).setState(false);
                if (components[i] instanceof Menu) {
                    ((Menu)components[i]).getPopup().clearItemsSelection();
                }
                break;
            }
        }
    }
    
    public MenuPanel(final LayoutManager layoutManager) {
        this(layoutManager, Color.lightGray);
    }
    
    public MenuPanel(final LayoutManager layoutManager, final Color background) {
        super(layoutManager);
        this.setBackground(background);
        this.enableEvents(0L);
    }
}
