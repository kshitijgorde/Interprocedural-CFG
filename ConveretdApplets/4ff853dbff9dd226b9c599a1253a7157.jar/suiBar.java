import java.awt.Insets;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiBar extends Canvas
{
    int top;
    int left;
    int down;
    int right;
    int x1;
    int y1;
    int x2;
    int y2;
    int width;
    int height;
    Image background;
    int ww;
    int hh;
    boolean convex;
    
    suiBar(final int x1, final int y1, final int x2, final int y2, final int width, final int height, final boolean convex) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        this.height = height;
        this.convex = convex;
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void setBGImage(final Image background) {
        this.background = background;
        this.ww = background.getWidth(this);
        this.hh = background.getHeight(this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.background != null) {
            final Dimension size = this.getSize();
            for (int i = 0; i < size.height - 1; i += this.hh) {
                for (int j = 0; j < size.width - 1; j += this.ww) {
                    graphics.drawImage(this.background, j, i, this);
                }
            }
        }
        if (!this.convex) {
            graphics.setColor(this.getBackground().brighter());
            graphics.drawLine(this.x1, this.y2, this.x2, this.y2);
            graphics.drawLine(this.x2, this.y1, this.x2, this.y2);
            graphics.setColor(this.getBackground().darker());
            graphics.drawLine(this.x1, this.y1, this.x2, this.y1);
            graphics.drawLine(this.x1, this.y1, this.x1, this.y2);
            return;
        }
        graphics.setColor(this.getBackground().darker());
        graphics.drawLine(this.x1, this.y2, this.x2, this.y2);
        graphics.drawLine(this.x2, this.y1, this.x2, this.y2);
        graphics.setColor(this.getBackground().brighter());
        graphics.drawLine(this.x1, this.y1, this.x2, this.y1);
        graphics.drawLine(this.x1, this.y1, this.x1, this.y2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
}
