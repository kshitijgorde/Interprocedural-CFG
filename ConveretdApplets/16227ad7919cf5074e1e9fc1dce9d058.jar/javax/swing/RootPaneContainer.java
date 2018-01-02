// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Component;
import java.awt.Container;

public interface RootPaneContainer
{
    Container getContentPane();
    
    Component getGlassPane();
    
    JLayeredPane getLayeredPane();
    
    JRootPane getRootPane();
    
    void setContentPane(final Container p0);
    
    void setGlassPane(final Component p0);
    
    void setLayeredPane(final JLayeredPane p0);
}
