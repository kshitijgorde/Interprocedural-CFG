import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.image.ImageProducer;
import java.awt.Image;
import java.awt.image.ImageConsumer;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageTo2DIntArrayExtractor implements ImageConsumer
{
    Image myImage;
    int[][] myArray;
    ImageProducer myProducer;
    private boolean myWorking;
    int myXoffset;
    int myYoffset;
    
    private ImageTo2DIntArrayExtractor() {
        this.myWorking = false;
    }
    
    public ImageTo2DIntArrayExtractor(final int[][] array, final Image image) {
        this(array, 0, 0, image);
    }
    
    public ImageTo2DIntArrayExtractor(final int[][] myArray, final int myXoffset, final int myYoffset, final Image myImage) {
        this.myWorking = false;
        this.myArray = myArray;
        this.myImage = myImage;
        this.myXoffset = myXoffset;
        this.myYoffset = myYoffset;
    }
    
    public synchronized void doit() {
        this.myProducer = this.myImage.getSource();
        this.myWorking = true;
        this.myProducer.startProduction(this);
        while (this.myWorking) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized void setDimensions(final int n, final int n2) {
    }
    
    public synchronized void setProperties(final Hashtable hashtable) {
    }
    
    public synchronized void setColorModel(final ColorModel colorModel) {
    }
    
    public synchronized void setHints(final int n) {
    }
    
    public synchronized void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        for (int i = n2; i < n2 + n4; ++i) {
            int n7 = n5 + (i - n2) * n6;
            final int[] array2 = this.myArray[i + this.myYoffset];
            for (int j = n; j < n + n3; ++j) {
                array2[j + this.myXoffset] = (colorModel.getRGB(array[n7] & 0xFF) | 0xFF000000);
                ++n7;
            }
        }
    }
    
    public synchronized void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        boolean b = true;
        for (int i = n2; i < n2 + n4; ++i) {
            if (b) {
                Thread.yield();
            }
            b = !b;
            int n7 = n5 + (i - n2) * n6;
            final int n8 = i + this.myYoffset;
            final int[] array2 = this.myArray[i + this.myYoffset];
            for (int j = n; j < n + n3; ++j) {
                array2[j + this.myXoffset] = (array[n7] | 0xFF000000);
                ++n7;
            }
        }
    }
    
    public synchronized void imageComplete(final int n) {
        this.myProducer.removeConsumer(this);
        this.myWorking = false;
        this.notifyAll();
    }
}
