import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiLine extends Canvas
{
    int top;
    int left;
    int down;
    int right;
    int s;
    int e;
    int w;
    int h;
    boolean convex;
    boolean horizontal;
    Image background;
    int ww;
    int hh;
    
    suiLine(final int s, final int e, final int w, final int h, final boolean convex, final boolean horizontal) {
        this.s = s;
        this.e = e;
        this.w = w;
        this.h = h;
        this.convex = convex;
        this.horizontal = horizontal;
    }
    
    public void setBGImage(final Image background) {
        this.background = background;
        this.ww = background.getWidth(this);
        this.hh = background.getHeight(this);
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.w, this.h);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.w, this.h);
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
        if (this.horizontal) {
            if (this.convex) {
                graphics.setColor(this.getBackground().brighter());
                graphics.drawLine(this.s, this.h / 2 - 1, this.e, this.h / 2 - 1);
                graphics.setColor(this.getBackground().darker());
                graphics.drawLine(this.s, this.h / 2, this.e, this.h / 2);
                return;
            }
            graphics.setColor(this.getBackground().brighter());
            graphics.drawLine(this.s, this.h / 2, this.e, this.h / 2);
            graphics.setColor(this.getBackground().darker());
            graphics.drawLine(this.s, this.h / 2 - 1, this.e, this.h / 2 - 1);
        }
        else {
            if (this.convex) {
                graphics.setColor(this.getBackground().brighter());
                graphics.drawLine(this.w / 2 - 1, this.s, this.w / 2 - 1, this.e);
                graphics.setColor(this.getBackground().darker());
                graphics.drawLine(this.w / 2, this.s, this.w / 2, this.e);
                return;
            }
            graphics.setColor(this.getBackground().brighter());
            graphics.drawLine(this.w / 2, this.s, this.w / 2, this.e);
            graphics.setColor(this.getBackground().darker());
            graphics.drawLine(this.w / 2 - 1, this.s, this.w / 2 - 1, this.e);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
}
