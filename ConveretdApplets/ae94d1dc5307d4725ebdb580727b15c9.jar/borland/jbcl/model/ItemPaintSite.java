// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

public interface ItemPaintSite
{
    Color getBackground();
    
    Color getForeground();
    
    Font getFont();
    
    int getAlignment();
    
    Insets getItemMargins();
    
    Component getSiteComponent();
}
