// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Button;

final class mybuttonleft extends Button implements ActionListener
{
    monthyearleftrightpanel parent;
    private Color col;
    
    mybuttonleft(final monthyearleftrightpanel parent) {
        super("   ");
        this.col = Color.gray;
        this.parent = parent;
        this.setBackground(this.parent.parent.getBackground());
        this.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.parent.parent.parent.decreaseMonth();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.col);
        final Dimension size = this.getSize();
        final int width = size.width;
        final int height = size.height;
        graphics.fillPolygon(new int[] { width * 20 / 100, width * 80 / 100, width * 80 / 100 }, new int[] { height / 2, height * 80 / 100, height * 20 / 100 }, 3);
    }
    
    void setArrowColor(final Color col) {
        this.col = col;
        this.repaint();
    }
}
