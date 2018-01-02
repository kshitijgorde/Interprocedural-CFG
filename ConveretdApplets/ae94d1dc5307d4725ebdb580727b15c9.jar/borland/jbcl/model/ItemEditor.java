// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.awt.event.KeyListener;
import java.awt.Rectangle;
import java.awt.Component;

public interface ItemEditor
{
    Object getValue();
    
    Component getComponent();
    
    void startEdit(final Object p0, final Rectangle p1, final ItemEditSite p2);
    
    void changeBounds(final Rectangle p0);
    
    boolean canPost();
    
    void endEdit(final boolean p0);
    
    void addKeyListener(final KeyListener p0);
    
    void removeKeyListener(final KeyListener p0);
}
