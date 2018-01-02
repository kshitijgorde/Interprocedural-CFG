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

public class TinyTableScrollPaneBorder extends AbstractBorder implements UIResource
{
    private static final Insets insets;
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Theme.tableBorderLightColor[Theme.style].getColor());
        graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.setColor(Theme.tableBorderDarkColor[Theme.style].getColor());
        graphics.drawLine(n, n2, n, n2 + n4 - 1);
        graphics.drawLine(n, n2, n + n3 - 1, n2);
    }
    
    public Insets getBorderInsets(final Component component) {
        return TinyTableScrollPaneBorder.insets;
    }
    
    static {
        insets = new Insets(1, 1, 1, 1);
    }
}
