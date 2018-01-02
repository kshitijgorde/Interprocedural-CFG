import java.awt.Insets;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiBarN extends Canvas
{
    int top;
    int left;
    int down;
    int right;
    int x1;
    int y1;
    int x2;
    int y2;
    int w;
    int h;
    Color color;
    Image pic;
    int width;
    int height;
    Image background;
    int ww;
    int hh;
    
    suiBarN(final int x1, final int y1, final int x2, final int y2, final int w, final int h, final Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.w = w;
        this.h = h;
        this.color = color;
    }
    
    suiBarN(final Image pic, final int x1, final int y1, final int x2, final int y2, final int w, final int h, final Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.w = w;
        this.h = h;
        this.color = color;
        this.pic = pic;
        this.width = pic.getWidth(this);
        this.height = pic.getHeight(this);
    }
    
    suiBarN(final int x1, final int y1, final int x2, final int y2, final int w, final int h, final Color color, final Image background) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.w = w;
        this.h = h;
        this.color = color;
        this.background = background;
        this.ww = background.getWidth(this);
        this.hh = background.getHeight(this);
    }
    
    suiBarN(final Image pic, final int x1, final int y1, final int x2, final int y2, final int w, final int h, final Color color, final Image background) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.w = w;
        this.h = h;
        this.color = color;
        this.pic = pic;
        this.width = pic.getWidth(this);
        this.height = pic.getHeight(this);
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
        if (this.pic != null) {
            graphics.setFont(new Font("Helvetica", 0, 9));
            graphics.drawImage(this.pic, this.x1, this.y1, this);
            graphics.setColor(this.color.brighter());
            graphics.drawLine(this.x1 + this.width - 1, this.y1, this.x1 + this.width - 1, this.y1 + this.height - 1);
            graphics.drawLine(this.x1, this.y1 + this.height - 1, this.x1 + this.width - 1, this.y1 + this.height - 1);
            graphics.drawLine(this.x1 + this.width + 6, this.y2, this.x2, this.y2);
            graphics.drawLine(this.x2, this.y1, this.x2, this.y2);
            graphics.setColor(this.color.darker());
            graphics.drawLine(this.x1, this.y1, this.x1 + this.width - 1, this.y1);
            graphics.drawLine(this.x1, this.y1, this.x1, this.y1 + this.height - 1);
            graphics.drawLine(this.x1 + this.width + 6, this.y1, this.x2, this.y1);
            graphics.drawLine(this.x1 + this.width + 6, this.y1, this.x1 + this.width + 6, this.y2);
            graphics.setColor(Color.red);
            graphics.drawString("Chunyen Liu & Lin Zhou, Copyright (c) 1999", this.x1 + this.w - 10 - graphics.getFontMetrics().stringWidth("Chunyen Liu & Lin Zhou, Copyright (c) 1999"), 14 + graphics.getFontMetrics().getHeight() / 2 - graphics.getFontMetrics().getDescent());
            return;
        }
        graphics.setColor(this.color.brighter());
        graphics.drawLine(this.x1, this.y2, this.x2, this.y2);
        graphics.drawLine(this.x2, this.y1, this.x2, this.y2);
        graphics.setColor(this.color.darker());
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
