// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

final class yearup extends Panel implements MouseListener
{
    private yearupdown parent;
    private Color col;
    
    yearup(final yearupdown parent) {
        this.col = Color.gray;
        this.parent = parent;
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.parent.parent.yearval < this.parent.parent.last) {
            this.parent.parent.increaseValue();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.col);
        final Dimension size = this.getSize();
        final int width = size.width;
        final int height = size.height;
        graphics.fillPolygon(new int[] { width / 2, 0, width }, new int[] { 0, height, height }, 3);
    }
    
    void setArrowColor(final Color col) {
        this.col = col;
        this.repaint();
    }
}
