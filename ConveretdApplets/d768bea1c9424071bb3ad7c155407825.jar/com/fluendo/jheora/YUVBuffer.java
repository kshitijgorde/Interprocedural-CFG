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
    
    public void addConsumer(final ImageConsumer imageConsumer) {
    }
    
    public boolean isConsumer(final ImageConsumer imageConsumer) {
        return false;
    }
    
    public void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        imageConsumer.setColorModel(this.colorModel);
        imageConsumer.setHints(30);
        imageConsumer.setDimensions(this.y_width, this.y_height);
        this.prepareRGBData(0, 0, this.y_width, this.y_height);
        imageConsumer.setPixels(0, 0, this.y_width, this.y_height, this.colorModel, this.pixels, 0, this.y_width);
        imageConsumer.imageComplete(3);
    }
    
    private synchronized void prepareRGBData(final int n, final int n2, final int n3, final int n4) {
        if (!this.newPixels) {
            return;
        }
        final int pix_size = n3 * n4;
        if (pix_size != this.pix_size) {
            this.pixels = new int[pix_size];
            this.pix_size = pix_size;
        }
        this.YUVtoRGB(n, n2, n3, n4);
        this.newPixels = false;
    }
    
    public synchronized void newPixels() {
        this.newPixels = true;
    }
    
    public Object getObject(final int n, final int n2, final int n3, final int n4) {
        return this;
    }
    
    private void YUVtoRGB(final int n, final int n2, final int n3, final int n4) {
        int n5 = this.y_offset + n + n2 * this.y_stride;
        int n6 = n5 + this.y_stride;
        int n7 = this.u_offset + n / 2 + n2 / 2 * this.uv_stride;
        int n8 = this.v_offset + n / 2 + n2 / 2 * this.uv_stride;
        int n9 = 0;
        int n10 = n3;
        final int n11 = n3 / 2;
        final int n12 = n4 / 2;
        final int n13 = this.y_stride * 2 - n11 * 2;
        final int n14 = this.uv_stride - n11;
        for (int i = 0; i < n12; ++i) {
            for (int j = 0; j < n11; ++j) {
                final short n15 = (short)(this.data[n7++] - 128);
                final short n16 = (short)(this.data[n8++] - 128);
                final int n17 = n15 * 46801 + n16 * 22553 - 16777216;
                final int n18 = n15 * 91881 + 16777216;
                final int n19 = n16 * 116129 + 16777216;
                final int n20 = this.data[n5] << 16;
                this.pixels[n9] = (YUVBuffer.r_tab[n20 + n19 >> 16] | YUVBuffer.b_tab[n20 + n18 >> 16] | YUVBuffer.g_tab[n20 - n17 >> 16]);
                final int n21 = this.data[n5 + 1] << 16;
                this.pixels[n9 + 1] = (YUVBuffer.r_tab[n21 + n19 >> 16] | YUVBuffer.b_tab[n21 + n18 >> 16] | YUVBuffer.g_tab[n21 - n17 >> 16]);
                final int n22 = this.data[n6] << 16;
                this.pixels[n10] = (YUVBuffer.r_tab[n22 + n19 >> 16] | YUVBuffer.b_tab[n22 + n18 >> 16] | YUVBuffer.g_tab[n22 - n17 >> 16]);
                final int n23 = this.data[n6 + 1] << 16;
                this.pixels[n10 + 1] = (YUVBuffer.r_tab[n23 + n19 >> 16] | YUVBuffer.b_tab[n23 + n18 >> 16] | YUVBuffer.g_tab[n23 - n17 >> 16]);
                n5 += 2;
                n6 += 2;
                n9 += 2;
                n10 += 2;
            }
            n5 += n13;
            n6 += n13;
            n7 += n14;
            n8 += n14;
            n9 += n3;
            n10 += n3;
        }
    }
    
    private static final short clamp255(int n) {
        n -= 255;
        n = -(255 + (n >> 31 & n));
        return (short)(-(n >> 31 & n));
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
