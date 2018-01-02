import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class PageOpen extends ImageMorpher
{
    int y;
    int size;
    
    public void init(final Dimension dimension) {
        this.y = dimension.height / 2;
        this.size = 1;
    }
    
    boolean tipMorpher(final Graphics graphics, final Image image, final Dimension dimension) {
        graphics.drawImage(image, 0, this.y, dimension.width, this.size, null);
        --this.y;
        this.size += 2;
        return this.y < 0;
    }
}
