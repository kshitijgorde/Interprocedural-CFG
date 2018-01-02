// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

public class SortIcon
{
    public static final int SIZE = 9;
    private Image ascendingIcon;
    private Image descendingIcon;
    
    public SortIcon(final Component c) {
        this.ascendingIcon = c.createImage(9, 9);
        Graphics g = this.ascendingIcon.getGraphics();
        g.setColor(c.getBackground());
        g.fillRect(0, 0, 9, 9);
        if (c.getBackground() == Color.white) {
            g.setColor(Color.black);
        }
        else {
            g.setColor(Color.white);
        }
        for (int i = 0; i < 8; i += 2) {
            g.drawLine(2, i + 1, i + 2, i + 1);
        }
        this.descendingIcon = c.createImage(9, 9);
        g = this.descendingIcon.getGraphics();
        g.setColor(c.getBackground());
        g.fillRect(0, 0, 9, 9);
        if (c.getBackground() == Color.white) {
            g.setColor(Color.black);
        }
        else {
            g.setColor(Color.white);
        }
        for (int i = 0; i < 8; i += 2) {
            g.drawLine(2, i + 1, 8 - i, i + 1);
        }
    }
    
    public void paint(final Graphics g, final boolean ascending, final int x, final int y, final int width, final int height) {
        final int left = x + (width - 9) / 2;
        final int top = y + (height - 9) / 2;
        if (ascending) {
            g.drawImage(this.ascendingIcon, left, top, null);
        }
        else {
            g.drawImage(this.descendingIcon, left, top, null);
        }
    }
}
