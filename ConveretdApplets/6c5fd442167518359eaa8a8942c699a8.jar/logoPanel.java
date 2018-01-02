import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class logoPanel extends Canvas
{
    Image image;
    boolean threeD;
    int iWidth;
    int iHeight;
    
    logoPanel(final Image image, final boolean threeD) {
        this.image = image;
        this.threeD = threeD;
        if (image != null) {
            this.iWidth = image.getWidth(this);
            this.iHeight = image.getHeight(this);
        }
        else {
            this.iWidth = 400;
            this.iHeight = 100;
        }
        if (threeD) {
            this.resize(this.iWidth + 4, this.iHeight + 4);
            return;
        }
        this.resize(this.iWidth, this.iHeight);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        int n2;
        int n;
        if (this.threeD) {
            n = (n2 = 2);
            graphics.fill3DRect(0, 0, this.size().width, this.size().height, false);
        }
        else {
            n = (n2 = 0);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
        }
        if (this.image != null) {
            graphics.drawImage(this.image, n2, n, this);
        }
    }
    
    public Dimension minimumSize() {
        if (this.threeD) {
            return new Dimension(this.iWidth + 4, this.iHeight + 4);
        }
        return new Dimension(this.iWidth, this.iHeight);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
}
