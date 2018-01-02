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
    
    public WvImagePanel(final Image image, final Point point) {
        this.image = image;
        this.pos = new Point(point.x, point.y);
        if (image != null) {
            this.height = image.getHeight(null);
            this.width = image.getWidth(null);
            return;
        }
        this.height = 1;
        this.width = 1;
    }
    
    public WvImagePanel() {
        this.image = null;
    }
    
    public WvImagePanel(final Image image) {
        this.image = image;
        this.pos = new Point(0, 0);
        if (image != null) {
            this.height = image.getHeight(null);
            this.width = image.getWidth(null);
        }
    }
    
    public void paint(final Graphics graphics) {
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
        graphics.drawImage(this.backBuffer, this.pos.x, this.pos.y, this);
    }
}
