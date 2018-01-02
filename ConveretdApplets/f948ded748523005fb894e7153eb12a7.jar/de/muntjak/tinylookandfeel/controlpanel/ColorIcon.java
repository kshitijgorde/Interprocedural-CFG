// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Icon;

public class ColorIcon implements Icon
{
    private Dimension iconSize;
    private Color color;
    private static int hue;
    
    public ColorIcon(final Dimension iconSize) {
        this.iconSize = iconSize;
        this.color = Color.getHSBColor((float)(ColorIcon.hue / 360.0), 0.5f, 0.9f);
        ColorIcon.hue += 24;
    }
    
    public int getIconHeight() {
        return this.iconSize.height;
    }
    
    public int getIconWidth() {
        return this.iconSize.width;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(this.color);
        graphics.fillRect(n + 1, n2 + 1, this.getIconWidth() - 2, this.getIconHeight() - 2);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(n, n2, this.getIconWidth() - 1, this.getIconHeight() - 1);
    }
    
    static {
        ColorIcon.hue = 0;
    }
}
