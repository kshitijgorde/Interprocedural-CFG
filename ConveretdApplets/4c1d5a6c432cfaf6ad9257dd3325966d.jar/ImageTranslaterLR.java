import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageTranslaterLR extends ImageMorpher
{
    int but;
    int x;
    int p;
    
    public ImageTranslaterLR(final int p) {
        this.p = p;
    }
    
    public void init(final Dimension dimension) {
        this.but = dimension.width;
        if (this.p > 0) {
            this.x = -this.but;
            return;
        }
        this.x = this.but;
    }
    
    boolean tipMorpher(final Graphics graphics, final Image image, final Dimension dimension) {
        graphics.drawImage(image, this.x, 0, null);
        this.x += this.p;
        --this.but;
        return this.but < 0;
    }
}
