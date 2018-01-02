// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

public class CheckMark
{
    static final int SIZE = 12;
    private Image checkedMark;
    private Image uncheckedMark;
    
    public CheckMark(final Component c, final boolean hasFrame) {
        this.checkedMark = c.createImage(12, 12);
        Graphics g = this.checkedMark.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 12, 12);
        g.setColor(Color.darkGray);
        if (hasFrame) {
            g.drawRect(0, 0, 11, 11);
        }
        g.drawLine(2, 5, 2, 5);
        g.drawLine(5, 6, 9, 2);
        g.drawLine(5, 8, 9, 4);
        g.setColor(Color.lightGray);
        g.drawLine(1, 5, 1, 6);
        g.drawLine(2, 7, 4, 9);
        g.drawLine(5, 9, 10, 4);
        g.drawLine(10, 2, 10, 3);
        g.drawLine(4, 6, 8, 2);
        g.setColor(Color.black);
        g.drawLine(2, 6, 4, 8);
        g.drawLine(5, 7, 9, 3);
        g.drawLine(3, 6, 4, 7);
        this.uncheckedMark = c.createImage(12, 12);
        g = this.uncheckedMark.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 12, 12);
        if (hasFrame) {
            g.setColor(Color.lightGray.darker());
            g.drawRect(0, 0, 11, 11);
        }
    }
    
    public void paint(final Graphics g, final boolean state, final int x, final int y, final int width, final int height) {
        final int left = x + (width - 12) / 2;
        final int top = y + (height - 12) / 2;
        if (state) {
            g.drawImage(this.checkedMark, left, top, null);
        }
        else {
            g.drawImage(this.uncheckedMark, left, top, null);
        }
    }
}
