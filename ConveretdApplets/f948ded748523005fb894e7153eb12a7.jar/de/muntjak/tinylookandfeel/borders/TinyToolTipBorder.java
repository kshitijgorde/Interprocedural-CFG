// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import java.awt.Color;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.border.Border;

public class TinyToolTipBorder implements Border
{
    private static final Insets insets;
    private boolean active;
    
    public TinyToolTipBorder(final boolean active) {
        this.active = active;
    }
    
    public boolean isBorderOpaque() {
        return false;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.active) {
            graphics.setColor(Theme.tipBorderColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.tipBorderDis[Theme.style].getColor());
        }
        graphics.drawRect(n, n2, n3 - 1, n4 - 1);
    }
    
    public Insets getBorderInsets(final Component component) {
        return TinyToolTipBorder.insets;
    }
    
    static {
        insets = new Insets(3, 3, 3, 3);
    }
}
