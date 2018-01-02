import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class PageOpenLR extends ImageMorpher
{
    int x;
    int size;
    
    public void init(final Dimension dimension) {
        this.x = dimension.width / 2;
        this.size = 1;
    }
    
    boolean tipMorpher(final Graphics graphics, final Image image, final Dimension dimension) {
        graphics.drawImage(image, this.x, 0, this.size, dimension.height, null);
        --this.x;
        this.size += 2;
        return this.x < 0;
    }
}
