import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class SetContrast extends Canvas
{
    private Image returnImage;
    
    SetContrast(final Image image, final int n) {
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
            final int n2 = array[i] >> 16 & 0xFF;
            int n3;
            if (n2 >= 128) {
                n3 = n2 + n;
            }
            else {
                n3 = n2 - n;
            }
            final int n4 = array[i] >> 8 & 0xFF;
            int n5;
            if (n4 >= 128) {
                n5 = n4 + n;
            }
            else {
                n5 = n4 - n;
            }
            final int n6 = array[i] & 0xFF;
            int n7;
            if (n6 >= 128) {
                n7 = n6 + n;
            }
            else {
                n7 = n6 - n;
            }
            if (n3 > 255) {
                n3 = 255;
            }
            if (n3 < 0) {
                n3 = 0;
            }
            if (n5 > 255) {
                n5 = 255;
            }
            if (n5 < 0) {
                n5 = 0;
            }
            if (n7 > 255) {
                n7 = 255;
            }
            if (n7 < 0) {
                n7 = 0;
            }
            array2[i] = (0xFF000000 | n3 << 16 | n5 << 8 | n7);
        }
        this.returnImage = this.createImage(new MemoryImageSource(width, height, array2, 0, width));
    }
    
    Image returnImg() {
        return this.returnImage;
    }
}
