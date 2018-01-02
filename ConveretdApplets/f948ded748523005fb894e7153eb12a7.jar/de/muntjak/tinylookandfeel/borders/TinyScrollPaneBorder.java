// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import java.awt.Color;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.plaf.UIResource;
import javax.swing.border.AbstractBorder;

public class TinyScrollPaneBorder extends AbstractBorder implements UIResource
{
    private static final Insets defaultInsets;
    
    public Insets getBorderInsets(final Component component) {
        return TinyScrollPaneBorder.defaultInsets;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Theme.scrollPaneBorderColor[Theme.style].getColor());
        graphics.drawRect(n, n2, n3 - 1, n4 - 1);
    }
    
    static {
        defaultInsets = new Insets(1, 1, 1, 1);
    }
}
