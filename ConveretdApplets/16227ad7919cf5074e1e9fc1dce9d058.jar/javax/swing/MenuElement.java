// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;

public interface MenuElement
{
    Component getComponent();
    
    MenuElement[] getSubElements();
    
    void menuSelectionChanged(final boolean p0);
    
    void processKeyEvent(final KeyEvent p0, final MenuElement[] p1, final MenuSelectionManager p2);
    
    void processMouseEvent(final MouseEvent p0, final MenuElement[] p1, final MenuSelectionManager p2);
}
