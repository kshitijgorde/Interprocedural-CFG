// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

public class VietLabel extends Canvas
{
    String text;
    int border;
    
    public VietLabel() {
    }
    
    public VietLabel(final String s) {
        this.text = s;
    }
    
    public void setText(final String s) {
        this.text = s;
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(100, 40);
    }
    
    public void paint(final Graphics g) {
        final int w = this.getSize().width;
        final int h = this.getSize().height;
        final Color old = g.getColor();
        g.setColor(this.getBackground());
        g.fillRect(0, 0, w, h);
        if (this.getBorder() > 0) {
            g.setColor(Color.lightGray);
            g.drawRect(0, 0, w - 1, h - 1);
        }
        if (this.text == null) {
            return;
        }
        g.setColor(old);
        final int x = (w - g.getFontMetrics().stringWidth(this.text)) / 2;
        final int hh = g.getFontMetrics().getAscent();
        final int y = hh + (h - hh) / 2;
        g.drawString(this.text, x, y);
    }
    
    public int getBorder() {
        return this.border;
    }
    
    public void setBorder(final int i) {
        this.border = i;
    }
}
