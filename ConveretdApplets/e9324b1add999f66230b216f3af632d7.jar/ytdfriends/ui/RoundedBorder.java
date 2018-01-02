// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.ui;

import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder
{
    private static final int MARGIN = 10;
    
    public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
        g.setColor(new Color(201, 215, 241));
        g.drawRoundRect(x, y, width - 1, height - 1, 10, 10);
    }
    
    public Insets getBorderInsets(final Component c) {
        return new Insets(10, 10, 10, 10);
    }
    
    public Insets getBorderInsets(final Component c, final Insets insets) {
        final int n = 10;
        insets.bottom = n;
        insets.right = n;
        insets.top = n;
        insets.left = n;
        return insets;
    }
}
