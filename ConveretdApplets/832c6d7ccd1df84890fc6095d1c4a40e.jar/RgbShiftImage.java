import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class RgbShiftImage extends Canvas
{
    private Image returnImage;
    
    RgbShiftImage(final Image image, final int n, final int n2, final int n3) {
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
            int n4 = (array[i] >> 16 & 0xFF) + n;
            if (n4 > 255) {
                n4 = 255;
            }
            if (n4 < 0) {
                n4 = 0;
            }
            int n5 = (array[i] >> 8 & 0xFF) + n2;
            if (n5 > 255) {
                n5 = 255;
            }
            if (n5 < 0) {
                n5 = 0;
            }
            int n6 = (array[i] & 0xFF) + n3;
            if (n6 > 255) {
                n6 = 255;
            }
            if (n6 < 0) {
                n6 = 0;
            }
            array2[i] = (0xFF000000 | n4 << 16 | n5 << 8 | n6);
        }
        this.returnImage = this.createImage(new MemoryImageSource(width, height, array2, 0, width));
    }
    
    Image returnImg() {
        return this.returnImage;
    }
}
