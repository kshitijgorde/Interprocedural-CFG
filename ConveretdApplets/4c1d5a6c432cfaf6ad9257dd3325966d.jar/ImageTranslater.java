import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageTranslater extends ImageMorpher
{
    int y;
    int p;
    
    public ImageTranslater(final int p) {
        this.p = p;
    }
    
    public void init(final Dimension dimension) {
        if (this.p > 0) {
            this.y = -dimension.height;
            return;
        }
        this.y = dimension.height;
    }
    
    boolean tipMorpher(final Graphics graphics, final Image image, final Dimension dimension) {
        this.y += this.p;
        if (this.y != dimension.height) {
            graphics.drawImage(image, 0, this.y, null);
        }
        return this.y == 0;
    }
}
