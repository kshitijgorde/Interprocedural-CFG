import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.image.BufferedImage;

// 
// Decompiled by Procyon v0.5.30
// 

class StretchyBox implements Drawable
{
    int x;
    int y;
    int w;
    int h;
    BufferedImage cornerTL;
    BufferedImage cornerTR;
    BufferedImage cornerBL;
    BufferedImage cornerBR;
    BufferedImage edgeTop;
    BufferedImage edgeBottom;
    BufferedImage edgeLeft;
    BufferedImage edgeRight;
    Color fillColor;
    
    StretchyBox() {
        this.w = 100;
        this.h = 75;
    }
    
    void setFrameImage(final BufferedImage bufferedImage) {
        final int width = bufferedImage.getWidth(null);
        final int height = bufferedImage.getHeight(null);
        final int n = width / 2;
        final int n2 = height / 2;
        this.cornerTL = bufferedImage.getSubimage(0, 0, n, n2);
        this.cornerTR = bufferedImage.getSubimage(width - n, 0, n, n2);
        this.cornerBL = bufferedImage.getSubimage(0, height - n2, n, n2);
        this.cornerBR = bufferedImage.getSubimage(width - n, height - n2, n, n2);
        this.edgeTop = bufferedImage.getSubimage(n, 0, 1, n2);
        this.edgeBottom = bufferedImage.getSubimage(n, height - n2, 1, n2);
        this.edgeLeft = bufferedImage.getSubimage(0, n2, n, 1);
        this.edgeRight = bufferedImage.getSubimage(width - n, n2, n, 1);
        this.fillColor = new Color(bufferedImage.getRGB(n, n2));
    }
    
    public boolean isShowing() {
        return true;
    }
    
    public Rectangle rect() {
        return new Rectangle(this.x, this.y, this.w, this.h);
    }
    
    public Rectangle fullRect() {
        return this.rect();
    }
    
    public void paint(final Graphics graphics) {
        if (this.cornerTL == null) {
            graphics.setColor(new Color(100, 100, 250));
            graphics.fillRect(this.x, this.y, this.w, this.h);
            return;
        }
        final int width = this.cornerTL.getWidth(null);
        final int height = this.cornerTL.getHeight(null);
        graphics.setColor(this.fillColor);
        graphics.fillRect(this.x + width, this.y + height, this.w - 2 * width, this.h - 2 * height);
        for (int i = this.x + width; i < this.x + this.w - width; ++i) {
            graphics.drawImage(this.edgeTop, i, this.y, null);
            graphics.drawImage(this.edgeBottom, i, this.y + this.h - height, null);
        }
        for (int j = this.y + height; j < this.y + this.h - height; ++j) {
            graphics.drawImage(this.edgeLeft, this.x, j, null);
            graphics.drawImage(this.edgeRight, this.x + this.w - width, j, null);
        }
        graphics.drawImage(this.cornerTL, this.x, this.y, null);
        graphics.drawImage(this.cornerTR, this.x + this.w - width, this.y, null);
        graphics.drawImage(this.cornerBL, this.x, this.y + this.h - height, null);
        graphics.drawImage(this.cornerBR, this.x + this.w - width, this.y + this.h - height, null);
    }
    
    public void paintBubble(final Graphics graphics) {
    }
    
    public void dragTo(final int n, final int n2) {
    }
    
    public void mouseDown(final int n, final int n2) {
    }
}
