// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Graphics;
import java.awt.Component;

public interface Icon
{
    int getIconHeight();
    
    int getIconWidth();
    
    void paintIcon(final Component p0, final Graphics p1, final int p2, final int p3);
}
