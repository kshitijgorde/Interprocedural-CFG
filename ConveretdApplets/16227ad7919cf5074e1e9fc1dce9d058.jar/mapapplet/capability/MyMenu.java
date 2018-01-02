// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.capability;

import java.awt.MenuItem;
import java.util.Vector;
import java.awt.PopupMenu;

public class MyMenu extends PopupMenu
{
    public final Vector subMenus;
    
    public MyMenu() {
        this("");
    }
    
    MyMenu(final String name) {
        super(name);
        this.subMenus = new Vector();
    }
    
    public final MenuItem add(final MenuItem menuItem) {
        if (menuItem.getLabel() != null && menuItem.getLabel().lastIndexOf("_") == menuItem.getLabel().length() - 2 && menuItem.getClass().isInstance(new LayerMenuItem())) {
            String layerName = ((LayerMenuItem)menuItem).layerName;
            layerName = layerName.substring(0, layerName.lastIndexOf(95));
            menuItem.setLabel(layerName);
            ((LayerMenuItem)menuItem).layerName = layerName;
        }
        if (!this.subMenus.contains(menuItem)) {
            super.add(menuItem);
            this.subMenus.addElement(menuItem);
            return menuItem;
        }
        final LayerMenuItem item = this.subMenus.elementAt(this.subMenus.indexOf(menuItem));
        final StringBuffer sb = new StringBuffer();
        final LayerMenuItem layerMenuItem = item;
        layerMenuItem.layerQuery = sb.append(layerMenuItem.layerQuery).append((item.layerQuery.length() > 0) ? "," : "").append(((LayerMenuItem)menuItem).layerQuery).toString();
        return null;
    }
}
