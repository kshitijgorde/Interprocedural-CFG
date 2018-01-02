// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.control;

import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Panel;

public class GroupBox extends Panel
{
    private String label;
    
    public GroupBox() {
        this.label = "";
    }
    
    public GroupBox(final String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.repaint();
    }
    
    public Insets insets() {
        final Font font = this.getFont();
        int height = 0;
        if (font != null) {
            height = this.getFontMetrics(font).getHeight();
        }
        return new Insets(height, height, height, height);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
        final int ascent = fontMetrics.getAscent();
        final int n = ascent / 2 + 1;
        final Rectangle bounds = this.getBounds();
        if (bounds == null) {
            return;
        }
        final int n2 = n;
        final int n3 = n;
        final int n4 = bounds.width - n - 1;
        final int n5 = bounds.height - n - 1;
        graphics.setColor(this.getForeground());
        graphics.drawString(this.label, n2 + n2, ascent);
        final int stringWidth = fontMetrics.stringWidth(this.label);
        for (int i = 1; i >= 0; --i) {
            graphics.setColor((i == 0) ? SystemColor.controlShadow : SystemColor.controlLtHighlight);
            graphics.drawLine(n2 + n2 - 1, n3 + i, n2 + i, n3 + i);
            graphics.drawLine(n2 + i, n3 + i, n2 + i, n5 + i);
            graphics.drawLine(n2 + i, n5 + i, n4 + i, n5 + i);
            graphics.drawLine(n4 + i, n5 + i, n4 + i, n3 + i);
            graphics.drawLine(n4 + i, n3 + i, n2 + n2 + stringWidth, n3 + i);
        }
    }
}
