// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Graphics;

public interface MenuItemModel
{
    MenuItem getParentItem();
    
    boolean hasParentItem();
    
    void setState(final boolean p0);
    
    boolean getState();
    
    boolean isSelectable();
    
    boolean isClickable();
    
    void paintItem(final Graphics p0);
}
