// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.Graphics;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Component;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.plaf.UIResource;
import javax.swing.border.AbstractBorder;

public class TinyTableHeaderBorder extends AbstractBorder implements UIResource
{
    protected static final Insets insets98;
    protected static final Insets insetsXP;
    protected Color color1;
    protected Color color2;
    protected Color color3;
    protected Color color4;
    protected Color color5;
    
    public Insets getBorderInsets(final Component component) {
        if (Theme.derivedStyle[Theme.style] == 2) {
            return TinyTableHeaderBorder.insetsXP;
        }
        return TinyTableHeaderBorder.insets98;
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        if (Theme.derivedStyle[Theme.style] == 2) {
            insets.left = TinyTableHeaderBorder.insetsXP.left;
            insets.top = TinyTableHeaderBorder.insetsXP.top;
            insets.right = TinyTableHeaderBorder.insetsXP.right;
            insets.bottom = TinyTableHeaderBorder.insetsXP.bottom;
        }
        else {
            insets.left = TinyTableHeaderBorder.insets98.left;
            insets.top = TinyTableHeaderBorder.insets98.top;
            insets.right = TinyTableHeaderBorder.insets98.right;
            insets.bottom = TinyTableHeaderBorder.insets98.bottom;
        }
        return insets;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (Theme.derivedStyle[Theme.style] == 2) {
            if (this.color1 == null) {
                this.color1 = ColorRoutines.darken(component.getBackground(), 5);
                this.color2 = ColorRoutines.darken(component.getBackground(), 10);
                this.color3 = ColorRoutines.darken(component.getBackground(), 15);
                this.color4 = Theme.tableHeaderDarkColor[Theme.style].getColor();
                this.color5 = Theme.tableHeaderLightColor[Theme.style].getColor();
            }
            graphics.setColor(this.color1);
            graphics.drawLine(n, n2 + n4 - 3, n + n3 - 1, n2 + n4 - 3);
            graphics.setColor(this.color2);
            graphics.drawLine(n, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
            graphics.setColor(this.color3);
            graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
            graphics.setColor(this.color4);
            graphics.drawLine(n + n3 - 2, n2 + 3, n + n3 - 2, n2 + n4 - 5);
            graphics.setColor(this.color5);
            graphics.drawLine(n + n3 - 1, n2 + 3, n + n3 - 1, n2 + n4 - 5);
        }
        else {
            if (this.color4 == null) {
                this.color4 = Theme.tableHeaderLightColor[Theme.style].getColor();
                this.color5 = Theme.tableHeaderDarkColor[Theme.style].getColor();
            }
            graphics.setColor(this.color4);
            graphics.drawLine(n, n2, n, n2 + n4 - 1);
            graphics.drawLine(n, n2, n + n3 - 1, n2);
            graphics.setColor(this.color5);
            graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 1);
            graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        }
    }
    
    static {
        insets98 = new Insets(1, 1, 1, 1);
        insetsXP = new Insets(3, 0, 3, 2);
    }
}
