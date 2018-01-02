// 
// Decompiled by Procyon v0.5.30
// 

package sTools.graph;

import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

class RotateTextFilter extends ImageFilter
{
    private int angle;
    private int xmin;
    private int xmax;
    private int ymin;
    private int ymax;
    private int width;
    private int height;
    private double cos;
    private double sin;
    private int[] ipixels;
    private byte[] bpixels;
    private ColorModel colorModel;
    
    public RotateTextFilter(final int n) {
        this.angle = 0;
        this.xmin = 0;
        this.xmax = 0;
        this.ymin = 0;
        this.ymax = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.angle = n % 360 / 90 * 90;
        this.cos = Math.cos(n * 3.141592653589793 / 180.0);
        this.sin = Math.sin(n * 3.141592653589793 / 180.0);
    }
    
    public void setProperties(Hashtable properties) {
        properties = (Hashtable)properties.clone();
        properties.put("rotAngle", new Integer(this.angle));
        super.setProperties(properties);
    }
    
    public void setDimensions(final int n, final int n2) {
        final int[] array = { 0, n - 1, n - 1, 0 };
        final int[] array2 = { 0, 0, n2 - 1, n2 - 1 };
        for (int i = 0; i < 4; ++i) {
            final int n3 = (int)Math.round(array[i] * this.cos + array2[i] * this.sin);
            final int n4 = (int)Math.round(-array[i] * this.sin + array2[i] * this.cos);
            this.xmin = Math.min(this.xmin, n3);
            this.xmax = Math.max(this.xmax, n3);
            this.ymin = Math.min(this.ymin, n4);
            this.ymax = Math.max(this.ymax, n4);
        }
        this.width = this.xmax - this.xmin + 1;
        this.height = this.ymax - this.ymin + 1;
        super.consumer.setDimensions(this.width, this.height);
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        if (this.bpixels == null) {
            this.colorModel = colorModel;
            this.bpixels = new byte[this.width * this.height];
        }
        for (int n7 = n2, i = 0; i < n4; ++i, ++n7) {
            for (int n8 = n, j = 0; j < n3; ++j, ++n8) {
                this.bpixels[(int)Math.round(n8 * this.cos + n7 * this.sin) - this.xmin + ((int)Math.round(-n8 * this.sin + n7 * this.cos) - this.ymin) * this.width] = array[(n7 - n2) * n6 + (n8 - n) + n5];
            }
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        if (this.ipixels == null) {
            this.colorModel = colorModel;
            this.ipixels = new int[this.width * this.height];
        }
        for (int n7 = n2, i = 0; i < n4; ++i, ++n7) {
            for (int n8 = n, j = 0; j < n3; ++j, ++n8) {
                this.ipixels[(int)Math.round(n8 * this.cos + n7 * this.sin) - this.xmin + ((int)Math.round(-n8 * this.sin + n7 * this.cos) - this.ymin) * this.width] = array[(n7 - n2) * n6 + (n8 - n) + n5];
            }
        }
    }
    
    public void imageComplete(final int n) {
        if (n == 4 || n == 1) {
            super.consumer.imageComplete(n);
            this.ipixels = null;
            this.bpixels = null;
            return;
        }
        if (this.bpixels != null) {
            for (int i = 0; i < this.height; ++i) {
                super.consumer.setPixels(0, i, this.width, 1, this.colorModel, this.bpixels, i * this.width, this.width);
            }
            super.consumer.imageComplete(n);
        }
        else if (this.ipixels != null) {
            for (int j = 0; j < this.height; ++j) {
                super.consumer.setPixels(0, j, this.width, 1, this.colorModel, this.ipixels, j * this.width, this.width);
            }
            super.consumer.imageComplete(n);
        }
        else {
            super.consumer.imageComplete(4);
        }
        this.ipixels = null;
        this.bpixels = null;
    }
}
