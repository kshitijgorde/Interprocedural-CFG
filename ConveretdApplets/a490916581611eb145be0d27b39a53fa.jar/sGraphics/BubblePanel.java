// 
// Decompiled by Procyon v0.5.30
// 

package sGraphics;

import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

class BubblePanel extends Panel
{
    String text;
    
    public BubblePanel(final String text) {
        this.text = text;
        this.setForeground(Color.black);
        this.setBackground(Color.yellow);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.drawString(this.text, 2, fontMetrics.getAscent() + 2);
    }
    
    public Dimension getPreferredSize() {
        final String property = System.getProperty("java.vendor");
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return new Dimension(10, 10);
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n = fontMetrics.stringWidth(this.text) + 4;
        int n2 = fontMetrics.getHeight() + 4;
        if (n < 50) {
            n = 50;
        }
        if (!property.startsWith("Microsoft")) {
            n2 += 15;
        }
        return new Dimension(n, n2);
    }
    
    void setText(final String text) {
        this.text = text;
    }
}
