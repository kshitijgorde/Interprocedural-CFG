import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageMorpher
{
    public void init(final Dimension dimension) {
    }
    
    boolean tipMorpher(final Graphics graphics, final Image image, final Dimension dimension) {
        graphics.drawImage(image, 0, 0, null);
        return true;
    }
}
