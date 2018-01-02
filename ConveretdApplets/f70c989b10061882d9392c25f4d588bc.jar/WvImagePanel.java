import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvImagePanel extends Panel
{
    private Point pos;
    private Image image;
    private int width;
    private int height;
    private Graphics backGC;
    private Image backBuffer;
    
    public WvImagePanel(final Image image1, final Point point) {
        this.image = image1;
        this.pos = new Point(point.x, point.y);
        if (image1 != null) {
            this.height = image1.getHeight(null);
            this.width = image1.getWidth(null);
            return;
        }
        this.height = 1;
        this.width = 1;
    }
    
    public WvImagePanel() {
        this.image = null;
    }
    
    public WvImagePanel(final Image image1) {
        this.image = image1;
        this.pos = new Point(0, 0);
        if (image1 != null) {
            this.height = image1.getHeight(null);
            this.width = image1.getWidth(null);
        }
    }
    
    public void paint(final Graphics g) {
        if (this.image == null) {
            return;
        }
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(this.width, this.height);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
        }
        this.backGC.setColor(this.getBackground());
        this.backGC.fillRect(0, 0, this.width, this.height);
        this.backGC.drawImage(this.image, 0, 0, this);
        g.drawImage(this.backBuffer, this.pos.x, this.pos.y, this);
    }
}
