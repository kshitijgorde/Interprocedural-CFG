import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class suiTabpanel extends Panel
{
    int top;
    int left;
    int down;
    int right;
    int height;
    int width;
    
    suiTabpanel(final int height, final int width) {
        this.top = 20;
        this.left = 20;
        this.down = 20;
        this.right = 20;
        this.height = height;
        this.width = width;
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(new Color(240, 240, 240));
        graphics.drawLine(0, 0, 0, this.height - 10);
        graphics.setColor(new Color(40, 40, 40));
        graphics.drawLine(0, this.height - 10, this.width - 1, this.height - 10);
        graphics.drawLine(this.width - 1, 0, this.width - 1, this.height - 10);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
}
