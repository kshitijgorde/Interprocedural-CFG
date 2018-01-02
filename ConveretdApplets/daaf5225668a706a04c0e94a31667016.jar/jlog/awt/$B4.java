// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;

public class $B4 extends Component
{
    int width;
    
    public $B4() {
        this.width = 3;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.width);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Color background = this.getBackground();
        final Color darker = background.darker();
        final Color brighter = background.brighter().brighter();
        graphics.setColor(background);
        if (size.width > size.height) {
            graphics.translate(0, (size.height - this.width) / 2);
            graphics.fillRect(0, 0, size.width, this.width);
            graphics.setColor(darker);
            graphics.fillRect(0, 0, size.width, 1);
            graphics.setColor(brighter);
            graphics.fillRect(0, this.width - 1, size.width, 1);
            graphics.translate(0, -(size.height - this.width) / 2);
        }
        else {
            graphics.translate((size.width - this.width) / 2, 0);
            graphics.fillRect(0, 0, this.width, size.height);
            graphics.setColor(darker);
            graphics.fillRect(0, 0, 1, size.height);
            graphics.setColor(brighter);
            graphics.fillRect(this.width - 1, 0, 1, size.height);
            graphics.translate(-(size.width - this.width) / 2, 0);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
