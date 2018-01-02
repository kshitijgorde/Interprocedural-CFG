// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Color;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

public class TransparencyFilter extends ImageFilter
{
    public static final ColorModel rgbModel;
    private int width;
    private int height;
    private int colorRGB;
    private float transparency;
    private float[] rgbLUT;
    private ImageProducer producer;
    private boolean pixelOrderChanges;
    
    public ImageProducer getSource() {
        return this.producer;
    }
    
    public void setSource(final ImageProducer producer) {
        this.producer = producer;
    }
    
    public void setHints(int hints) {
        if (this.pixelOrderChanges) {
            hints &= 0xFFFFFFFD;
        }
        super.consumer.setHints(hints);
    }
    
    public void imageComplete(final int n) {
        if (n != 2) {
            this.producer.removeConsumer(this);
        }
        super.imageComplete(n);
    }
    
    public void setDimensions(final int width, final int height) {
        this.width = width;
        this.height = height;
        super.consumer.setDimensions(width, height);
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        for (int i = 0; i < array.length; ++i) {
            final int n7 = array[i];
            array[i] = ((int)(((n7 & 0xFF0000) >> 16) * this.transparency + this.rgbLUT[0]) << 16 | (int)(((n7 & 0xFF00) >> 8) * this.transparency + this.rgbLUT[1]) << 8 | (int)((n7 & 0xFF) * this.transparency + this.rgbLUT[2]));
        }
        super.consumer.setPixels(n, n2, n3, n4, colorModel, array, n5, n6);
    }
    
    public TransparencyFilter(final ImageProducer imageProducer, final Color color) {
        this(imageProducer, color, 1.0f);
    }
    
    public TransparencyFilter(final ImageProducer imageProducer, final float n) {
        this(imageProducer, Color.gray, n);
    }
    
    public TransparencyFilter(final ImageProducer source, final Color color, final float transparency) {
        this.pixelOrderChanges = false;
        this.setSource(source);
        this.transparency = transparency;
        this.colorRGB = color.getRGB();
        this.rgbLUT = new float[] { (1.0f - transparency) * ((this.colorRGB & 0xFF0000) >> 16), (1.0f - transparency) * ((this.colorRGB & 0xFF00) >> 8), (1.0f - transparency) * (this.colorRGB & 0xFF) };
    }
    
    static {
        rgbModel = ColorModel.getRGBdefault();
    }
}
