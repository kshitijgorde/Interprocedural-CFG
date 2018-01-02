// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import java.awt.Insets;
import java.awt.Color;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.plaf.UIResource;
import javax.swing.border.AbstractBorder;

public class TinyPopupMenuBorder extends AbstractBorder implements UIResource
{
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyBorder(graphics, n, n2, n3, n4);
                break;
            }
            case 1: {
                this.drawWinBorder(graphics, n, n2, n3, n4);
                break;
            }
            case 2: {
                this.drawXpBorder(graphics, n, n2, n3, n4);
                break;
            }
        }
    }
    
    private void drawTinyBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
    
    private void drawWinBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.translate(n, n2);
        graphics.setColor(Theme.menuInnerHilightColor[Theme.style].getColor());
        graphics.drawLine(1, 1, n3 - 3, 1);
        graphics.drawLine(1, 1, 1, n4 - 3);
        graphics.setColor(Theme.menuInnerShadowColor[Theme.style].getColor());
        graphics.drawLine(n3 - 2, 1, n3 - 2, n4 - 2);
        graphics.drawLine(1, n4 - 2, n3 - 2, n4 - 2);
        graphics.setColor(Theme.menuOuterHilightColor[Theme.style].getColor());
        graphics.drawLine(0, 0, n3 - 2, 0);
        graphics.drawLine(0, 0, 0, n4 - 1);
        graphics.setColor(Theme.menuOuterShadowColor[Theme.style].getColor());
        graphics.drawLine(n3 - 1, 0, n3 - 1, n4 - 1);
        graphics.drawLine(0, n4 - 1, n3 - 1, n4 - 1);
        graphics.translate(-n, -n2);
    }
    
    private void drawXpBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.translate(n, n2);
        graphics.setColor(Theme.menuInnerHilightColor[Theme.style].getColor());
        graphics.drawLine(1, 1, n3 - 3, 1);
        graphics.drawLine(1, 1, 1, n4 - 3);
        graphics.setColor(Theme.menuInnerShadowColor[Theme.style].getColor());
        graphics.drawLine(n3 - 2, 1, n3 - 2, n4 - 2);
        graphics.drawLine(1, n4 - 2, n3 - 2, n4 - 2);
        graphics.setColor(Theme.menuOuterHilightColor[Theme.style].getColor());
        graphics.drawLine(0, 0, n3 - 2, 0);
        graphics.drawLine(0, 0, 0, n4 - 1);
        graphics.setColor(Theme.menuOuterShadowColor[Theme.style].getColor());
        graphics.drawLine(n3 - 1, 0, n3 - 1, n4 - 1);
        graphics.drawLine(0, n4 - 1, n3 - 1, n4 - 1);
        graphics.translate(-n, -n2);
    }
    
    public Insets getBorderInsets(final Component component) {
        return Theme.menuBorderInsets[Theme.style];
    }
}
