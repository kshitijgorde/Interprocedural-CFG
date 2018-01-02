// 
// Decompiled by Procyon v0.5.30
// 

package ino360both;

import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.Image;
import java.awt.image.ImageConsumer;

public class ImageTo2DIntArrayExtractor implements ImageConsumer
{
    int[][] myArray;
    Image myImage;
    ImageProducer myProducer;
    private boolean myWorking;
    int myXoffset;
    int myYoffset;
    
    public ImageTo2DIntArrayExtractor(final int[][] ai, final int xoffset, final int yoffset, final Image image) {
        this.myWorking = false;
        this.myArray = ai;
        this.myImage = image;
        this.myXoffset = xoffset;
        this.myYoffset = yoffset;
    }
    
    public ImageTo2DIntArrayExtractor(final int[][] ai, final Image image) {
        this(ai, 0, 0, image);
    }
    
    private ImageTo2DIntArrayExtractor() {
        this.myWorking = false;
    }
    
    public synchronized void doit() {
        this.myProducer = this.myImage.getSource();
        this.myWorking = true;
        this.myProducer.startProduction(this);
        while (this.myWorking) {
            try {
                this.wait();
            }
            catch (InterruptedException e) {}
        }
    }
    
    public synchronized void imageComplete(final int status) {
        this.myProducer.removeConsumer(this);
        this.myWorking = false;
        this.notifyAll();
    }
    
    public synchronized void setColorModel(final ColorModel model) {
    }
    
    public synchronized void setDimensions(final int width, final int height) {
    }
    
    public synchronized void setHints(final int hintflags) {
    }
    
    public synchronized void setPixels(final int x, final int y, final int w, final int h, final ColorModel model, final int[] pixels, final int off, final int scansize) {
        boolean b = true;
        for (int iy = y; iy < y + h; ++iy) {
            if (b) {
                Thread.yield();
            }
            b = !b;
            int iptr = off + (iy - y) * scansize;
            final int iyp = iy + this.myYoffset;
            final int[] row = this.myArray[iy + this.myYoffset];
            for (int ix = x; ix < x + w; ++ix) {
                row[ix + this.myXoffset] = (pixels[iptr] | 0xFF000000);
                ++iptr;
            }
        }
    }
    
    public synchronized void setPixels(final int x, final int y, final int w, final int h, final ColorModel model, final byte[] pixels, final int off, final int scansize) {
        for (int iy = y; iy < y + h; ++iy) {
            int iptr = off + (iy - y) * scansize;
            final int[] row = this.myArray[iy + this.myYoffset];
            for (int ix = x; ix < x + w; ++ix) {
                row[ix + this.myXoffset] = (model.getRGB(pixels[iptr] & 0xFF) | 0xFF000000);
                ++iptr;
            }
        }
    }
    
    public synchronized void setProperties(final Hashtable props) {
    }
}
