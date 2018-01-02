// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import java.awt.Color;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Graphics;
import javax.swing.JToolBar;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.plaf.metal.MetalBorders;

public class TinyToolBarBorder extends MetalBorders.ToolBarBorder
{
    public Insets getBorderInsets(final Component component) {
        return this.getBorderInsets(component, new Insets(0, 0, 0, 0));
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        final int n = 2;
        insets.right = n;
        insets.bottom = n;
        insets.left = n;
        insets.top = n;
        if (!(component instanceof JToolBar)) {
            return insets;
        }
        if (((JToolBar)component).isFloatable()) {
            if (((JToolBar)component).getOrientation() == 0) {
                if (component.getComponentOrientation().isLeftToRight()) {
                    insets.left = 10;
                }
                else {
                    insets.right = 10;
                }
            }
            else {
                insets.top = 10;
            }
        }
        final Insets margin = ((JToolBar)component).getMargin();
        if (margin != null) {
            insets.left += margin.left;
            insets.top += margin.top;
            insets.right += margin.right;
            insets.bottom += margin.bottom;
        }
        return insets;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (!(component instanceof JToolBar)) {
            return;
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyBorder(component, graphics, n, n2, n3, n4);
                break;
            }
            case 1: {
                this.drawWinBorder(component, graphics, n, n2, n3, n4);
                break;
            }
            case 2: {
                this.drawWinBorder(component, graphics, n, n2, n3, n4);
                break;
            }
        }
        if (((JToolBar)component).getOrientation() == 0) {
            graphics.setColor(Theme.toolBarLightColor[Theme.style].getColor());
            graphics.drawLine(n, n2, n3 - 1, n2);
            graphics.setColor(Theme.toolBarDarkColor[Theme.style].getColor());
            graphics.drawLine(n, n4 - 1, n3 - 1, n4 - 1);
        }
        else {
            graphics.setColor(Theme.toolBarLightColor[Theme.style].getColor());
            graphics.drawLine(n, n2, n, n4 - 1);
            graphics.setColor(Theme.toolBarDarkColor[Theme.style].getColor());
            graphics.drawLine(n3 - 1, n2, n3 - 1, n4 - 1);
        }
    }
    
    protected void drawTinyBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
    
    protected void drawWinBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.translate(n, n2);
        if (((JToolBar)component).isFloatable()) {
            if (((JToolBar)component).getOrientation() == 0) {
                int n5 = 3;
                if (!component.getComponentOrientation().isLeftToRight()) {
                    n5 = component.getBounds().width - 8 + 3;
                }
                graphics.setColor(Theme.toolGripLightColor[Theme.style].getColor());
                graphics.drawLine(n5, 3, n5 + 1, 3);
                graphics.drawLine(n5, 3, n5, n4 - 5);
                graphics.setColor(Theme.toolGripDarkColor[Theme.style].getColor());
                graphics.drawLine(n5, n4 - 4, n5 + 1, n4 - 4);
                graphics.drawLine(n5 + 2, 3, n5 + 2, n4 - 4);
            }
            else {
                graphics.setColor(Theme.toolGripLightColor[Theme.style].getColor());
                graphics.drawLine(3, 3, 3, 4);
                graphics.drawLine(3, 3, n3 - 4, 3);
                graphics.setColor(Theme.toolGripDarkColor[Theme.style].getColor());
                graphics.drawLine(n3 - 4, 4, n3 - 4, 5);
                graphics.drawLine(3, 5, n3 - 4, 5);
            }
        }
        graphics.translate(-n, -n2);
    }
}
