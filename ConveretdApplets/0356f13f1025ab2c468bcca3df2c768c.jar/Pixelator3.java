import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.image.ImageConsumer;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Pixelator3 implements ImageConsumer
{
    boolean imgready;
    int imgxsize;
    int imgysize;
    int[][] imgpixels;
    boolean first;
    
    Pixelator3(final Image image) {
        this.first = true;
        image.getSource().startProduction(this);
        for (int i = 300000; i > 0; i -= 50) {
            try {
                Thread.currentThread();
                Thread.sleep(200L);
            }
            catch (Exception ex) {}
            if (this.imgready) {
                break;
            }
        }
    }
    
    public void setProperties(final Hashtable hashtable) {
    }
    
    public void setColorModel(final ColorModel colorModel) {
    }
    
    public void setHints(final int n) {
    }
    
    public void imageComplete(final int n) {
        this.imgready = true;
    }
    
    public void setDimensions(final int imgxsize, final int imgysize) {
        this.imgxsize = imgxsize;
        this.imgysize = imgysize;
        this.imgpixels = new int[imgysize][imgxsize];
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        for (int i = n; i < n + n3; ++i) {
            for (int j = n2; j < n2 + n4; ++j) {
                final int[] array2 = this.imgpixels[j];
                final int n7 = i;
                int k;
                for (k = array[(j - n2) * n6 + (i - n) + n5]; k < 0; k += 256) {}
                array2[n7] = colorModel.getRGB(k);
            }
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        for (int i = n; i < n + n3; ++i) {
            for (int j = n2; j < n2 + n4; ++j) {
                this.imgpixels[j][i] = colorModel.getRGB(array[(j - n2) * n6 + (i - n) + n5]);
            }
        }
    }
    
    public int makePositive(int i) {
        while (i < 0) {
            i += 256;
        }
        return i;
    }
}
