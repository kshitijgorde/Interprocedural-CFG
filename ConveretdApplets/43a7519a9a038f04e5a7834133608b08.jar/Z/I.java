// 
// Decompiled by Procyon v0.5.30
// 

package Z;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.BufferedImage;

public final class I
{
    private final BufferedImage a;
    private final int b;
    private final int c;
    
    public I(final Image image) {
        this.b = image.getWidth(null);
        this.c = image.getHeight(null);
        this.a = new BufferedImage(this.b, this.c, 1);
        this.a.getGraphics().drawImage(image, 0, 0, null);
    }
    
    public final int[] a() {
        final int[] array = new int[this.b * this.c];
        this.a.getRGB(0, 0, this.b, this.c, array, 0, this.b);
        return array;
    }
}
