// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.awt.Component;
import javax.swing.MenuSelectionManager;
import javax.swing.MenuElement;
import java.awt.event.MouseEvent;

public class MenuDragMouseEvent extends MouseEvent
{
    private MenuElement[] path;
    private MenuSelectionManager manager;
    
    public MenuDragMouseEvent(final Component component, final int n, final long n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final MenuElement[] path, final MenuSelectionManager manager) {
        super(component, n, n2, n3, n4, n5, n6, b);
        this.path = path;
        this.manager = manager;
    }
    
    public MenuSelectionManager getMenuSelectionManager() {
        return this.manager;
    }
    
    public MenuElement[] getPath() {
        return this.path;
    }
}
