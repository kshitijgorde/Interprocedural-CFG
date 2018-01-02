import java.util.Hashtable;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class RotateFilter extends ImageFilter
{
    private static ColorModel defaultRGB;
    private double angle;
    private double sin;
    private double cos;
    private double[] coord;
    private int[] raster;
    private int xoffset;
    private int yoffset;
    private int srcW;
    private int srcH;
    private int dstW;
    private int dstH;
    
    public RotateFilter(final double angle) {
        this.coord = new double[2];
        this.angle = angle;
        this.sin = Math.sin(angle);
        this.cos = Math.cos(angle);
    }
    
    public void transform(final double n, final double n2, final double[] array) {
        array[0] = this.cos * n + this.sin * n2;
        array[1] = this.cos * n2 - this.sin * n;
    }
    
    public void itransform(final double n, final double n2, final double[] array) {
        array[0] = this.cos * n - this.sin * n2;
        array[1] = this.cos * n2 + this.sin * n;
    }
    
    public void transformBBox(final Rectangle rectangle) {
        double min = Double.POSITIVE_INFINITY;
        double min2 = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double max2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i <= 1; ++i) {
            for (int j = 0; j <= 1; ++j) {
                this.transform(rectangle.x + j * rectangle.width, rectangle.y + i * rectangle.height, this.coord);
                min = Math.min(min, this.coord[0]);
                min2 = Math.min(min2, this.coord[1]);
                max = Math.max(max, this.coord[0]);
                max2 = Math.max(max2, this.coord[1]);
            }
        }
        rectangle.x = (int)Math.floor(min);
        rectangle.y = (int)Math.floor(min2);
        rectangle.width = (int)Math.ceil(max) - rectangle.x + 1;
        rectangle.height = (int)Math.ceil(max2) - rectangle.y + 1;
    }
    
    public void setDimensions(final int srcW, final int srcH) {
        final Rectangle rectangle = new Rectangle(0, 0, srcW, srcH);
        this.transformBBox(rectangle);
        this.xoffset = -rectangle.x;
        this.yoffset = -rectangle.y;
        this.srcW = srcW;
        this.srcH = srcH;
        this.dstW = rectangle.width;
        this.dstH = rectangle.height;
        this.raster = new int[this.srcW * this.srcH];
        super.consumer.setDimensions(this.dstW, this.dstH);
    }
    
    public void setProperties(Hashtable properties) {
        properties = (Hashtable<String, String>)properties.clone();
        final String value = properties.get("filters");
        if (value == null) {
            properties.put("filters", this.toString());
        }
        else if (value instanceof String) {
            properties.put("filters", value + this.toString());
        }
        super.consumer.setProperties(properties);
    }
    
    public void setColorModel(final ColorModel colorModel) {
        super.consumer.setColorModel(RotateFilter.defaultRGB);
    }
    
    public void setHints(final int n) {
        super.consumer.setHints(0xE | (n & 0x10));
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        int n7 = n5;
        int n8 = n2 * this.srcW + n;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                this.raster[n8++] = colorModel.getRGB(array[n7++] & 0xFF);
            }
            n7 += n6 - n3;
            n8 += this.srcW - n3;
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        int n7 = n5;
        int n8 = n2 * this.srcW + n;
        if (colorModel == RotateFilter.defaultRGB) {
            for (int i = 0; i < n4; ++i) {
                System.arraycopy(array, n7, this.raster, n8, n3);
                n7 += n6;
                n8 += this.srcW;
            }
            return;
        }
        for (int j = 0; j < n4; ++j) {
            for (int k = 0; k < n3; ++k) {
                this.raster[n8++] = colorModel.getRGB(array[n7++]);
            }
            n7 += n6 - n3;
            n8 += this.srcW - n3;
        }
    }
    
    public void imageComplete(final int n) {
        if (n == 1 || n == 4) {
            super.consumer.imageComplete(n);
            return;
        }
        final int[] array = new int[this.dstW];
        for (int i = 0; i < this.dstH; ++i) {
            this.itransform(-this.xoffset, i - this.yoffset, this.coord);
            double n2 = this.coord[0];
            double n3 = this.coord[1];
            this.itransform(this.dstW - this.xoffset, i - this.yoffset, this.coord);
            final double n4 = this.coord[0];
            final double n5 = this.coord[1];
            final double n6 = (n4 - n2) / this.dstW;
            final double n7 = (n5 - n3) / this.dstW;
            for (int j = 0; j < this.dstW; ++j) {
                final int n8 = (int)Math.round(n2);
                final int n9 = (int)Math.round(n3);
                if (n8 < 0 || n9 < 0 || n8 >= this.srcW || n9 >= this.srcH) {
                    array[j] = 0;
                }
                else {
                    array[j] = this.raster[n9 * this.srcW + n8];
                }
                n2 += n6;
                n3 += n7;
            }
            super.consumer.setPixels(0, i, this.dstW, 1, RotateFilter.defaultRGB, array, 0, this.dstW);
        }
        super.consumer.imageComplete(n);
    }
    
    static {
        RotateFilter.defaultRGB = ColorModel.getRGBdefault();
    }
}
