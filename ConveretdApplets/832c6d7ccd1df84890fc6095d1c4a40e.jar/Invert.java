import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Invert extends Canvas
{
    private Image returnImage;
    
    Invert(final Image image) {
        this.returnImage = null;
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int[] array = new int[width * height];
        final int[] array2 = new int[array.length];
        try {
            new PixelGrabber(image, 0, 0, width, height, array, 0, width).grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (0xFF000000 | 255 - (array[i] >> 16 & 0xFF) << 16 | 255 - (array[i] >> 8 & 0xFF) << 8 | 255 - (array[i] & 0xFF));
        }
        this.returnImage = this.createImage(new MemoryImageSource(width, height, array2, 0, width));
    }
    
    Image returnImg() {
        return this.returnImage;
    }
}
