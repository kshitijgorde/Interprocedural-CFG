// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Image;
import java.awt.Canvas;

public class ImageCanvas extends Canvas
{
    private Image image;
    
    public ImageCanvas(final Image ima) {
        Util.waitForImage(this, this.image = ima);
        this.resize(ima.getWidth(this), ima.getHeight(this));
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.image, 0, 0, this);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void setImage(final Image ima) {
        Util.waitForImage(this, this.image = ima);
        this.resize(ima.getWidth(this), ima.getHeight(this));
        this.repaint();
    }
}
