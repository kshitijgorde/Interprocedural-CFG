// 
// Decompiled by Procyon v0.5.30
// 

package util;

import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.Color;
import java.awt.Graphics;

public class ImageBlotter
{
    private float[] im;
    private int w;
    private int h;
    private double wxe;
    private double wye;
    private Graphics g;
    private ImageBlot blot;
    private Color fg;
    private Color bg;
    
    public ImageBlotter(final double wxe, final double wye, final int w, final int h, final Color fg, final Color bg, final ImageBlot blot) {
        this.fg = fg;
        this.bg = bg;
        this.w = w;
        this.h = h;
        this.im = null;
        this.wxe = wxe;
        this.wye = wye;
        this.blot = blot;
    }
    
    public void setBlot(final ImageBlot blot) {
        this.blot = blot;
    }
    
    public ImageBlot getBlot() {
        return this.blot;
    }
    
    public Color getBackground() {
        return this.bg;
    }
    
    public synchronized void setColors(final Color fg, final Color bg) {
        if (fg != null) {
            this.fg = fg;
        }
        if (bg != null) {
            this.bg = bg;
        }
    }
    
    protected final void createArrayIfNeeded() {
        if (this.im == null) {
            this.im = new float[this.w * this.h];
            for (int i = 0; i < this.im.length; ++i) {
                this.im[i] = 0.0f;
            }
        }
    }
    
    public synchronized void release() {
        this.im = null;
    }
    
    public synchronized void reinitialize(final Color fg, final Color bg, final ImageBlot blot) {
        this.release();
        if (blot != null) {
            this.blot = blot;
        }
        if (fg != null) {
            this.fg = fg;
        }
        if (bg != null) {
            this.bg = bg;
        }
    }
    
    public MemoryImageSource memoryImageToSource(final int[] array, ColorModel rgBdefault) {
        if (rgBdefault == null) {
            rgBdefault = ColorModel.getRGBdefault();
        }
        return new MemoryImageSource(this.w, this.h, rgBdefault, array, 0, this.w);
    }
    
    public synchronized int[] addToMemoryImage(int[] array) {
        if (this.im == null) {
            return null;
        }
        final int length = this.im.length;
        if (array == null) {
            array = new int[this.w * this.h];
            final int n = (this.bg.getRed() << 8 | this.bg.getGreen()) << 8 | this.bg.getBlue();
            for (int i = 0; i < length; ++i) {
                array[i] = n;
            }
        }
        final int red = this.fg.getRed();
        final int green = this.fg.getGreen();
        final int blue = this.fg.getBlue();
        for (int j = 0; j < length; ++j) {
            final int n2 = array[j];
            final int n3 = (n2 & 0xFF0000) >> 16;
            final int n4 = (n2 & 0xFF00) >> 8;
            final int n5 = n2 & 0xFF;
            array[j] = (((n3 + (int)(this.im[j] * (red - n3)) & 0xFF) << 8 | (n4 + (int)(this.im[j] * (green - n4)) & 0xFF)) << 8 | (n5 + (int)(this.im[j] * (blue - n5)) & 0xFF));
        }
        return array;
    }
    
    public synchronized MemoryImageSource getProducer(ColorModel rgBdefault, boolean b, final int[] array) {
        if (this.im == null) {
            return null;
        }
        if (rgBdefault == null) {
            rgBdefault = ColorModel.getRGBdefault();
        }
        if (rgBdefault instanceof IndexColorModel) {
            b = false;
        }
        final int red = this.bg.getRed();
        final int n = this.fg.getRed() - red;
        final int green = this.bg.getGreen();
        final int n2 = this.fg.getGreen() - green;
        final int blue = this.bg.getBlue();
        final int n3 = this.fg.getBlue() - blue;
        int n4 = -1;
        int n5 = 0;
        int w = this.w;
        int n6 = 0;
        int i = 0;
        int n7 = 0;
        while (i < this.h) {
            for (int j = 0; j < this.w; ++j, ++n7) {
                if (this.im[n7] > 0.0f) {
                    if (n4 < 0) {
                        n4 = i;
                    }
                    n5 = i;
                    if (j < w) {
                        w = j;
                    }
                    if (j > n6) {
                        n6 = j;
                    }
                }
            }
            ++i;
        }
        final int w2 = this.w;
        final int h = this.h;
        final int n8 = n4;
        final int n9 = n5 - n4 + 1;
        if (w == this.w) {
            w = 0;
        }
        final int n10 = w;
        final int n11 = n6 - w + 1;
        final int[] array2 = new int[n11 * n9];
        int n12;
        if (!b) {
            n12 = -16777216;
        }
        else {
            n12 = (this.fg.getRed() << 16 | this.fg.getGreen() << 8 | this.fg.getBlue());
        }
        int n13 = 0;
        for (int k = n4; k <= n5; ++k) {
            for (int l = w; l <= n6; ++l, ++n13) {
                final float n14 = this.im[this.w * k + l];
                if (b) {
                    array2[n13] = (((int)(255.0f * n14) & 0xFF) << 24 | n12);
                }
                else {
                    array2[n13] = (n12 | (red + (int)(n * n14) & 0xFF) << 16 | (green + (int)(n2 * n14) & 0xFF) << 8 | (blue + (int)(n3 * n14) & 0xFF));
                }
            }
        }
        array[0] = n10;
        array[1] = n8;
        return new MemoryImageSource(n11, n9, rgBdefault, array2, 0, n11);
    }
    
    public final double transformX(final double n) {
        return this.w * ((n + this.wxe) / (2.0 * this.wxe));
    }
    
    public final double transformY(final double n) {
        return this.h * ((n + this.wye) / (2.0 * this.wye));
    }
    
    public final double inverseTransformWidth(final double n) {
        return n / this.w * 2.0 * this.wxe;
    }
    
    public void applyBlot(final double n, final double n2) {
        this.createArrayIfNeeded();
        final double transformX = this.transformX(n);
        final double transformY = this.transformY(n2);
        int n3 = (int)(transformX - this.blot.bgrad);
        int n4 = (int)(transformX + this.blot.bgrad + 0.5);
        int n5 = (int)(transformY - this.blot.bgrad);
        int n6 = (int)(transformY + this.blot.bgrad + 0.5);
        if (n3 >= this.w || n5 >= this.h || n4 < 0 || n6 < 0) {
            return;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n4 >= this.w) {
            n4 = this.w - 1;
        }
        if (n6 >= this.h) {
            n6 = this.h - 1;
        }
        for (int i = n5; i <= n6; ++i) {
            for (int j = n3; j <= n4; ++j) {
                this.im[this.w * i + j] = this.blot.applyToPixel(this.im[this.w * i + j], transformX, transformY, j, i);
            }
        }
    }
}
