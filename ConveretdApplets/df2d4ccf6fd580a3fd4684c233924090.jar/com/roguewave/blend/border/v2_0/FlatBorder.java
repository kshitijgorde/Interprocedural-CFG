// 
// Decompiled by Procyon v0.5.30
// 

package com.roguewave.blend.border.v2_0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

public class FlatBorder extends RoundedBorder
{
    public Insets getInsets(final Rectangle rectangle) {
        final int n = this.getArcLength(rectangle.width - this.getThickness() - rectangle.x) / 2;
        final int n2 = this.getArcLength(rectangle.height - this.getThickness() - rectangle.y) / 2;
        final int n3 = (int)(Math.min(n, n2) / Math.sqrt(2.0));
        final int n4 = n2 - n3 + this.getThickness();
        final int n5 = n - n3 + this.getThickness();
        return new Insets(n4, n5, n4, n5);
    }
    
    public void paint(final Graphics graphics, final Rectangle rectangle) {
        final Color color = graphics.getColor();
        graphics.setColor(this.getBorderColor());
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < this.getThickness(); ++i) {
            n = rectangle.width - 2 * i - 1;
            n2 = rectangle.height - 2 * i - 1;
            n4 = rectangle.x + i;
            n3 = rectangle.y + i;
            graphics.drawRoundRect(n4, n3, n, n2, this.getArcLength(n), this.getArcLength(n2));
        }
        graphics.setColor(this.getBackground());
        graphics.fillRoundRect(n4 + 1, n3 + 1, n - 1, n2 - 1, this.getArcLength(n - 2), this.getArcLength(n2 - 2));
        graphics.setColor(color);
    }
}
