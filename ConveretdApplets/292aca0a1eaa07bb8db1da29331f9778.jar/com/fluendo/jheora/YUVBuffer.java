// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

public class YUVBuffer implements ImageProducer
{
    public int y_width;
    public int y_height;
    public int y_stride;
    public int uv_width;
    public int uv_height;
    public int uv_stride;
    public short[] data;
    public int y_offset;
    public int u_offset;
    public int v_offset;
    private int[] pixels;
    private int pix_size;
    private boolean newPixels;
    private ColorModel colorModel;
    private static final int VAL_RANGE = 256;
    private static final int SHIFT = 16;
    private static final int CR_FAC = 91881;
    private static final int CB_FAC = 116129;
    private static final int CR_DIFF_FAC = 46801;
    private static final int CB_DIFF_FAC = 22553;
    private static int[] r_tab;
    private static int[] g_tab;
    private static int[] b_tab;
    
    public YUVBuffer() {
        this.newPixels = true;
        this.colorModel = ColorModel.getRGBdefault();
    }
    
    public void addConsumer(final ImageConsumer ic) {
    }
    
    public boolean isConsumer(final ImageConsumer ic) {
        return false;
    }
    
    public void removeConsumer(final ImageConsumer ic) {
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer ic) {
    }
    
    public void startProduction(final ImageConsumer ic) {
        ic.setColorModel(this.colorModel);
        ic.setHints(30);
        ic.setDimensions(this.y_width, this.y_height);
        this.prepareRGBData(0, 0, this.y_width, this.y_height);
        ic.setPixels(0, 0, this.y_width, this.y_height, this.colorModel, this.pixels, 0, this.y_width);
        ic.imageComplete(3);
    }
    
    private synchronized void prepareRGBData(final int x, final int y, final int width, final int height) {
        if (!this.newPixels) {
            return;
        }
        final int size = width * height;
        try {
            if (size != this.pix_size) {
                this.pixels = new int[size];
                this.pix_size = size;
            }
            this.YUVtoRGB(x, y, width, height);
        }
        catch (Throwable t) {}
        this.newPixels = false;
    }
    
    public synchronized void newPixels() {
        this.newPixels = true;
    }
    
    public Object getObject(final int x, final int y, final int width, final int height) {
        return this;
    }
    
    private void YUVtoRGB(final int x, final int y, final int width, final int height) {
        int YPtr = this.y_offset + x + y * this.y_stride;
        int YPtr2 = YPtr + this.y_stride;
        int UPtr = this.u_offset + x / 2 + y / 2 * this.uv_stride;
        int VPtr = this.v_offset + x / 2 + y / 2 * this.uv_stride;
        int RGBPtr = 0;
        int RGBPtr2 = width;
        final int width2 = width / 2;
        final int height2 = height / 2;
        final int YStep = this.y_stride * 2 - width2 * 2;
        final int UVStep = this.uv_stride - width2;
        for (int i = 0; i < height2; ++i) {
            for (int j = 0; j < width2; ++j) {
                int UFactor = this.data[UPtr++] - 128;
                int VFactor = this.data[VPtr++] - 128;
                final int GFactor = UFactor * 46801 + VFactor * 22553 - 16777216;
                UFactor = UFactor * 91881 + 16777216;
                VFactor = VFactor * 116129 + 16777216;
                int YVal = this.data[YPtr] << 16;
                this.pixels[RGBPtr] = (YUVBuffer.r_tab[YVal + VFactor >> 16] | YUVBuffer.b_tab[YVal + UFactor >> 16] | YUVBuffer.g_tab[YVal - GFactor >> 16]);
                YVal = this.data[YPtr + 1] << 16;
                this.pixels[RGBPtr + 1] = (YUVBuffer.r_tab[YVal + VFactor >> 16] | YUVBuffer.b_tab[YVal + UFactor >> 16] | YUVBuffer.g_tab[YVal - GFactor >> 16]);
                YVal = this.data[YPtr2] << 16;
                this.pixels[RGBPtr2] = (YUVBuffer.r_tab[YVal + VFactor >> 16] | YUVBuffer.b_tab[YVal + UFactor >> 16] | YUVBuffer.g_tab[YVal - GFactor >> 16]);
                YVal = this.data[YPtr2 + 1] << 16;
                this.pixels[RGBPtr2 + 1] = (YUVBuffer.r_tab[YVal + VFactor >> 16] | YUVBuffer.b_tab[YVal + UFactor >> 16] | YUVBuffer.g_tab[YVal - GFactor >> 16]);
                YPtr += 2;
                YPtr2 += 2;
                RGBPtr += 2;
                RGBPtr2 += 2;
            }
            YPtr += YStep;
            YPtr2 += YStep;
            UPtr += UVStep;
            VPtr += UVStep;
            RGBPtr += width;
            RGBPtr2 += width;
        }
    }
    
    private static final short clamp255(int val) {
        val -= 255;
        val = -(255 + (val >> 31 & val));
        return (short)(-(val >> 31 & val));
    }
    
    private static void SetupRgbYuvAccelerators() {
        for (int i = 0; i < 768; ++i) {
            YUVBuffer.r_tab[i] = clamp255(i - 256) << 16;
            YUVBuffer.g_tab[i] = clamp255(i - 256) << 8;
            YUVBuffer.b_tab[i] = (clamp255(i - 256) | 0xFF000000);
        }
    }
    
    static {
        YUVBuffer.r_tab = new int[768];
        YUVBuffer.g_tab = new int[768];
        YUVBuffer.b_tab = new int[768];
        SetupRgbYuvAccelerators();
    }
}
