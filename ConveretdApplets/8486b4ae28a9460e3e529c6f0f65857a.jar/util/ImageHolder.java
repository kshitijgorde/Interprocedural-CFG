// 
// Decompiled by Procyon v0.5.30
// 

package util;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

public class ImageHolder
{
    public int xoffset;
    public int yoffset;
    public Image image;
    
    public ImageHolder(final int xoffset, final int yoffset, final Image image) {
        this.xoffset = xoffset;
        this.yoffset = yoffset;
        this.image = image;
    }
    
    public void draw(final Graphics graphics, final ImageObserver imageObserver) {
        graphics.drawImage(this.image, this.xoffset, this.yoffset, imageObserver);
    }
}
