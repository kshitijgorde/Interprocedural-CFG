import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiLabel extends Canvas
{
    int top;
    int left;
    int down;
    int right;
    int w;
    int h;
    int ww;
    int hh;
    int align;
    String label;
    Image background;
    
    suiLabel(final int w, final int h, final String label, final int align) {
        this.w = w;
        this.h = h;
        this.label = label;
        this.align = align;
    }
    
    public void setBGImage(final Image background) {
        this.background = background;
        this.ww = background.getWidth(this);
        this.hh = background.getHeight(this);
    }
    
    public void setText(final String label) {
        this.label = label;
    }
    
    public int getAlignment() {
        return this.align;
    }
    
    public String getText() {
        return this.label;
    }
    
    public void setAlignment(final int align) {
        this.align = align;
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
        final int height = graphics.getFontMetrics().getHeight();
        final int descent = graphics.getFontMetrics().getDescent();
        final int stringWidth = graphics.getFontMetrics().stringWidth(this.label);
        graphics.setColor(this.getForeground());
        if (this.align == 0) {
            graphics.drawString(this.label, 1, this.h / 2 + height / 2 - descent);
            return;
        }
        if (this.align == 1) {
            graphics.drawString(this.label, Math.max(this.w / 2 - stringWidth / 2, 1), this.h / 2 + height / 2 - descent);
            return;
        }
        graphics.drawString(this.label, Math.max(this.w - stringWidth - 1, 1), this.h / 2 + height / 2 - descent);
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.w, this.h);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.w, this.h);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
}
