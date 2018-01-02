import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Enlarge extends Canvas
{
    private Image returnImage;
    
    Enlarge(final Image image, int n, final int n2, final int n3, final int n4, final int n5) {
        this.returnImage = null;
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int[] array = new int[width * height];
        final int[] array2 = new int[n3 * n3];
        try {
            new PixelGrabber(image, 0, 0, width, height, array, 0, width).grabPixels();
        }
        catch (InterruptedException ex) {}
        int n6 = 0;
        n = n + (n2 - n4 - n3 / 2) * width - n5 - n3 / 2;
        try {
            for (int i = 0; i < n3 * n3; ++i) {
                array2[i] = array[n];
                ++n;
                if (++n6 == n3) {
                    n6 = 0;
                    n = n + width - n3;
                }
            }
        }
        catch (Exception ex2) {}
        this.returnImage = this.createImage(new MemoryImageSource(n3, n3, array2, 0, n3));
    }
    
    Image returnImg() {
        return this.returnImage;
    }
}
