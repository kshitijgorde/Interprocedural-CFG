import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class suiPanel extends Panel
{
    int width;
    int height;
    boolean withBGImage;
    Image image;
    int ww;
    int hh;
    int top;
    int left;
    int down;
    int right;
    int special;
    
    suiPanel(final int width, final int height) {
        this.withBGImage = false;
        this.width = width;
        this.height = height;
    }
    
    public void setBGImage(final Image image) {
        this.withBGImage = true;
        this.image = image;
        this.ww = image.getWidth(this);
        this.hh = image.getHeight(this);
    }
    
    public void setSpecial(final int special) {
        this.special = special;
    }
    
    public void paint(final Graphics graphics) {
        if (this.withBGImage) {
            for (int i = 0; i < this.height - 1; i += this.hh) {
                for (int j = 0; j < this.width - 1; j += this.ww) {
                    graphics.drawImage(this.image, j, i, this);
                }
            }
        }
        if (this.special == 1) {
            graphics.setColor(new Color(220, 220, 220));
            graphics.drawLine(0, 0, this.width - 1, 0);
            graphics.drawLine(0, 0, 0, this.height - 1);
            graphics.setColor(new Color(90, 90, 90));
            graphics.drawLine(0, this.height - 1, this.width - 1, this.height - 1);
            graphics.drawLine(this.width - 1, 0, this.width - 1, this.height - 1);
            return;
        }
        if (this.special == 2) {
            graphics.setColor(new Color(90, 90, 90));
            graphics.drawLine(0, 0, this.width - 1, 0);
            graphics.drawLine(0, 0, 0, this.height - 1);
            graphics.setColor(new Color(240, 240, 240));
            graphics.drawLine(0, this.height - 1, this.width - 1, this.height - 1);
            graphics.drawLine(this.width - 1, 0, this.width - 1, this.height - 1);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setRoom(final int top, final int left, final int down, final int right) {
        this.top = top;
        this.left = left;
        this.down = down;
        this.right = right;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
}
