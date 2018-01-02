import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class RotateImageFilter extends ImageFilter
{
    private int imageWidth;
    private int imageHeight;
    
    public void setDimensions(final int imageHeight, final int imageWidth) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        super.consumer.setDimensions(this.imageWidth, this.imageHeight);
    }
    
    public void setHints(final int n) {
        super.consumer.setHints(n & 0xFFFFFFFD);
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        final byte[] array2 = new byte[n4 * n3];
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n4; ++j) {
                array2[i * n4 + n4 - 1 - j] = array[j * n6 + i + n5];
            }
        }
        super.consumer.setPixels(this.imageWidth - n2 - n4, n, n4, n3, colorModel, array2, 0, n4);
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        final int[] array2 = new int[n4 * n3];
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n4; ++j) {
                array2[i * n4 + n4 - 1 - j] = array[j * n6 + i + n5];
            }
        }
        super.consumer.setPixels(this.imageWidth - n2 - n4, n, n4, n3, colorModel, array2, 0, n4);
    }
    
    public RotateImageFilter() {
        this.imageWidth = -1;
        this.imageHeight = -1;
    }
}
