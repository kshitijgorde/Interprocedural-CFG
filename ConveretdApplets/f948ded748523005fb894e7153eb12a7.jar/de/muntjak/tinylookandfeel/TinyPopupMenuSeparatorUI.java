// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalSeparatorUI;

public class TinyPopupMenuSeparatorUI extends MetalSeparatorUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinyPopupMenuSeparatorUI();
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinySeparator(graphics, component.getSize());
                break;
            }
            case 1: {
                this.drawWinSeparator(graphics, component.getSize());
                break;
            }
            case 2: {
                this.drawXpSeparator(graphics, component.getSize());
                break;
            }
        }
    }
    
    private void drawTinySeparator(final Graphics graphics, final Dimension dimension) {
    }
    
    private void drawWinSeparator(final Graphics graphics, final Dimension dimension) {
        graphics.setColor(Theme.menuPopupColor[Theme.style].getColor());
        graphics.fillRect(0, 0, dimension.width, dimension.height);
        graphics.setColor(Theme.menuSepDarkColor[Theme.style].getColor());
        graphics.drawLine(2, 1, dimension.width - 3, 1);
        graphics.setColor(Theme.menuSepLightColor[Theme.style].getColor());
        graphics.drawLine(2, 2, dimension.width - 3, 2);
    }
    
    private void drawXpSeparator(final Graphics graphics, final Dimension dimension) {
        graphics.setColor(Theme.menuPopupColor[Theme.style].getColor());
        graphics.fillRect(0, 0, dimension.width, dimension.height);
        graphics.setColor(Theme.menuSepDarkColor[Theme.style].getColor());
        graphics.drawLine(2, 1, dimension.width - 3, 1);
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        return new Dimension(0, Theme.menuSeparatorHeight[Theme.derivedStyle[Theme.style]]);
    }
}
