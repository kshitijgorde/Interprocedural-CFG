// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;

public class GBasicImage extends GBasicItem
{
    public Image img;
    
    public GBasicImage(final Image img, final int n, final int n2) {
        super(Color.white);
        this.setPosition(n, n2);
        this.img = img;
        super.width = this.img.getWidth(null);
        super.height = this.img.getHeight(null);
        this.setSize(super.width, super.height);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this.img, n, n2, null);
    }
}
