// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PImage implements PConstants, Cloneable
{
    static final int PRECISIONB = 15;
    static final int PRECISIONF = 32768;
    static final int PREC_MAXVAL = 32767;
    static final int PREC_ALPHA_SHIFT = 9;
    static final int PREC_RED_SHIFT = 1;
    static byte[] tiff_header;
    public int format;
    public int[] pixels;
    public int width;
    public int height;
    public int imageMode;
    public boolean smooth;
    public Object cache;
    public boolean modified;
    public int mx1;
    public int my1;
    public int mx2;
    public int my2;
    private int fracU;
    private int ifU;
    private int fracV;
    private int ifV;
    private int u1;
    private int u2;
    private int v1;
    private int v2;
    private int sX;
    private int sY;
    private int iw;
    private int iw1;
    private int ih1;
    private int ul;
    private int ll;
    private int ur;
    private int lr;
    private int cUL;
    private int cLL;
    private int cUR;
    private int cLR;
    private int srcXOffset;
    private int srcYOffset;
    private int r;
    private int g;
    private int b;
    private int a;
    private int[] srcBuffer;
    int blurRadius;
    int blurKernelSize;
    int[] blurKernel;
    int[][] blurMult;
    
    public void init(final int width, final int height, final int format) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
        this.format = format;
        this.cache = null;
    }
    
    public void imageMode(final int imageMode) {
        if (imageMode == 0 || imageMode == 1) {
            this.imageMode = imageMode;
            return;
        }
        throw new RuntimeException("imageMode() only works with CORNER or CORNERS");
    }
    
    public void smooth() {
        this.smooth = true;
    }
    
    public void noSmooth() {
        this.smooth = false;
    }
    
    public void loadPixels() {
    }
    
    public void updatePixels() {
        this.updatePixels(0, 0, this.width, this.height);
    }
    
    public void updatePixels(final int mx2, final int my2, int mx3, int my3) {
        if (this.imageMode == 0) {
            mx3 += mx2;
            my3 += my2;
        }
        if (!this.modified) {
            this.mx1 = mx2;
            this.mx2 = mx3;
            this.my1 = my2;
            this.my2 = my3;
            this.modified = true;
        }
        else {
            if (mx2 < this.mx1) {
                this.mx1 = mx2;
            }
            if (mx2 > this.mx2) {
                this.mx2 = mx2;
            }
            if (my2 < this.my1) {
                this.my1 = my2;
            }
            if (my2 > this.my2) {
                this.my2 = my2;
            }
            if (mx3 < this.mx1) {
                this.mx1 = mx3;
            }
            if (mx3 > this.mx2) {
                this.mx2 = mx3;
            }
            if (my3 < this.my1) {
                this.my1 = my3;
            }
            if (my3 > this.my2) {
                this.my2 = my3;
            }
        }
    }
    
    public int get(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.width || n2 >= this.height) {
            return 0;
        }
        switch (this.format) {
            case 1: {
                return this.pixels[n2 * this.width + n] | 0xFF000000;
            }
            case 2: {
                return this.pixels[n2 * this.width + n];
            }
            case 4: {
                return this.pixels[n2 * this.width + n] << 24 | 0xFFFFFF;
            }
            default: {
                return 0;
            }
        }
    }
    
    public PImage get(int n, int n2, int n3, int n4) {
        if (this.imageMode == 1) {
            n3 -= n;
            n4 -= n;
        }
        if (n < 0) {
            n3 += n;
            n = 0;
        }
        if (n2 < 0) {
            n4 += n2;
            n2 = 0;
        }
        if (n + n3 > this.width) {
            n3 = this.width - n;
        }
        if (n2 + n4 > this.height) {
            n4 = this.height - n2;
        }
        final PImage pImage = new PImage(new int[n3 * n4], n3, n4, this.format);
        int n5 = n2 * this.width + n;
        int n6 = 0;
        for (int i = n2; i < n2 + n4; ++i) {
            System.arraycopy(this.pixels, n5, pImage.pixels, n6, n3);
            n5 += this.width;
            n6 += n3;
        }
        return pImage;
    }
    
    public PImage get() {
        try {
            return (PImage)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public void set(final int n, final int n2, final int n3) {
        if (n < 0 || n2 < 0 || n >= this.width || n2 >= this.height) {
            return;
        }
        this.pixels[n2 * this.width + n] = n3;
    }
    
    public void set(int n, int n2, final PImage pImage) {
        int n3 = 0;
        int n4 = 0;
        int width = pImage.width;
        int height = pImage.height;
        if (n < 0) {
            n3 -= n;
            width += n;
            n = 0;
        }
        if (n2 < 0) {
            n4 -= n2;
            height += n2;
            n2 = 0;
        }
        if (n + width > this.width) {
            width = this.width - n;
        }
        if (n2 + height > this.height) {
            height = this.height - n2;
        }
        if (width <= 0 || height <= 0) {
            return;
        }
        this.setImpl(n, n2, n3, n4, width, height, pImage);
    }
    
    protected void setImpl(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final PImage pImage) {
        int n7 = n4 * pImage.width + n3;
        int n8 = n2 * this.width + n;
        for (int i = n4; i < n4 + n6; ++i) {
            System.arraycopy(pImage.pixels, n7, this.pixels, n8, n5);
            n7 += pImage.width;
            n8 += this.width;
        }
    }
    
    public void mask(final int[] array) {
        if (array.length != this.pixels.length) {
            throw new RuntimeException("The PImage used with mask() must be the same size as the applet.");
        }
        for (int i = 0; i < this.pixels.length; ++i) {
            this.pixels[i] = ((array[i] & 0xFF) << 24 | (this.pixels[i] & 0xFFFFFF));
        }
        this.format = 2;
    }
    
    public void mask(final PImage pImage) {
        this.mask(pImage.pixels);
    }
    
    public void filter(final int n) {
        switch (n) {
            case 11: {
                this.filter(11, 1.0f);
                break;
            }
            case 12: {
                for (int i = 0; i < this.pixels.length; ++i) {
                    final int n2 = this.pixels[i];
                    final int n3 = 77 * (n2 >> 16 & 0xFF) + 151 * (n2 >> 8 & 0xFF) + 28 * (n2 & 0xFF) >> 8;
                    this.pixels[i] = ((n2 & 0xFF000000) | n3 << 16 | n3 << 8 | n3);
                }
                break;
            }
            case 13: {
                for (int j = 0; j < this.pixels.length; ++j) {
                    final int[] pixels = this.pixels;
                    final int n4 = j;
                    pixels[n4] ^= 0xFFFFFF;
                }
                break;
            }
            case 15: {
                throw new RuntimeException("Use filter(POSTERIZE, int levels) instead of filter(POSTERIZE)");
            }
            case 1: {
                for (int k = 0; k < this.pixels.length; ++k) {
                    final int[] pixels2 = this.pixels;
                    final int n5 = k;
                    pixels2[n5] |= 0xFF000000;
                }
                this.format = 1;
                break;
            }
            case 16: {
                this.filter(16, 0.5f);
                break;
            }
        }
        this.updatePixels();
    }
    
    public void filter(final int n, final float n2) {
        switch (n) {
            case 11: {
                this.blur(n2);
                break;
            }
            case 12: {
                throw new RuntimeException("Use filter(GRAY) instead of filter(GRAY, param)");
            }
            case 13: {
                throw new RuntimeException("Use filter(INVERT) instead of filter(INVERT, param)");
            }
            case 14: {
                throw new RuntimeException("Use filter(OPAQUE) instead of filter(OPAQUE, param)");
            }
            case 15: {
                final int n3 = (int)n2;
                if (n3 < 2 || n3 > 255) {
                    throw new RuntimeException("Levels must be between 2 and 255 for filter(POSTERIZE, levels)");
                }
                final int n4 = 256 / n3;
                final int n5 = n3 - 1;
                for (int i = 0; i < this.pixels.length; ++i) {
                    this.pixels[i] = ((0xFF000000 & this.pixels[i]) | ((this.pixels[i] >> 16 & 0xFF) / n4 * 255 / n5 & 0xFF) << 16 | ((this.pixels[i] >> 8 & 0xFF) / n4 * 255 / n5 & 0xFF) << 8 | ((this.pixels[i] & 0xFF) / n4 * 255 / n5 & 0xFF));
                }
                break;
            }
            case 16: {
                final int n6 = (int)(n2 * 255.0f);
                for (int j = 0; j < this.pixels.length; ++j) {
                    this.pixels[j] = ((this.pixels[j] & 0xFF000000) | ((Math.max((this.pixels[j] & 0xFF0000) >> 16, Math.max((this.pixels[j] & 0xFF00) >> 8, this.pixels[j] & 0xFF)) < n6) ? 0 : 16777215));
                }
                break;
            }
        }
        this.updatePixels();
    }
    
    protected void blur(final float n) {
        final int n2 = (int)(n * 3.5f);
        int n3 = 1;
        if (n2 >= 1) {
            n3 = ((n2 < 248) ? n2 : 248);
        }
        final int blurRadius = n3;
        if (this.blurRadius != blurRadius) {
            this.blurRadius = blurRadius;
            this.blurKernelSize = 1 + blurRadius * 2;
            this.blurKernel = new int[this.blurKernelSize];
            this.blurMult = new int[this.blurKernelSize][256];
            int n4 = 0;
            for (int i = 1; i < blurRadius; ++i) {
                final int n5 = blurRadius - i;
                this.blurKernel[blurRadius + i] = (this.blurKernel[n5] = n5 * n5);
                n4 += this.blurKernel[n5] + this.blurKernel[n5];
                for (int j = 0; j < 256; ++j) {
                    this.blurMult[blurRadius + i][j] = (this.blurMult[n5][j] = this.blurKernel[n5] * j);
                }
            }
            this.blurKernel[blurRadius] = blurRadius * blurRadius;
            final int n6 = n4 + this.blurKernel[blurRadius];
            for (int k = 0; k < 256; ++k) {
                this.blurMult[blurRadius][k] = this.blurKernel[blurRadius] * k;
            }
        }
        final int n7 = this.width * this.height;
        final int[] array = new int[n7];
        final int[] array2 = new int[n7];
        final int[] array3 = new int[n7];
        for (int l = 0; l < n7; ++l) {
            final int n8 = this.pixels[l];
            array[l] = (n8 & 0xFF0000) >> 16;
            array2[l] = (n8 & 0xFF00) >> 8;
            array3[l] = (n8 & 0xFF);
        }
        final int[] array4 = new int[n7];
        final int[] array5 = new int[n7];
        final int[] array6 = new int[n7];
        final int n9 = 0;
        final int n10 = 0;
        final int width = this.width;
        final int height = this.height;
        int n11 = n10 * this.width;
        for (int n12 = n10; n12 < height; ++n12) {
            for (int n13 = n9; n13 < width; ++n13) {
                int n17;
                int n16;
                int n15;
                int n14 = n15 = (n16 = (n17 = 0));
                final int n18 = n13 - this.blurRadius;
                for (int n19 = 0; n19 < this.blurKernelSize; ++n19) {
                    final int n20 = n18 + n19;
                    if (n20 >= n9 && n20 < width) {
                        final int n21 = n20 + n11;
                        n16 += this.blurMult[n19][array[n21]];
                        n14 += this.blurMult[n19][array2[n21]];
                        n15 += this.blurMult[n19][array3[n21]];
                        n17 += this.blurKernel[n19];
                    }
                }
                final int n22 = n11 + n13;
                array4[n22] = n16 / n17;
                array5[n22] = n14 / n17;
                array6[n22] = n15 / n17;
            }
            n11 += this.width;
        }
        int n23 = n10 * this.width;
        for (int n24 = n10; n24 < height; ++n24) {
            final int n25 = n24 - this.blurRadius;
            final int n26 = n25 * this.width;
            for (int n27 = n9; n27 < width; ++n27) {
                int n31;
                int n30;
                int n29;
                int n28 = n29 = (n30 = (n31 = 0));
                int n32 = n25;
                int n33 = n27 + n26;
                for (int n34 = 0; n34 < this.blurKernelSize; ++n34) {
                    if (n32 < height && n32 >= n10) {
                        n30 += this.blurMult[n34][array4[n33]];
                        n28 += this.blurMult[n34][array5[n33]];
                        n29 += this.blurMult[n34][array6[n33]];
                        n31 += this.blurKernel[n34];
                    }
                    ++n32;
                    n33 += this.width;
                }
                this.pixels[n27 + n23] = (0xFF000000 | n30 / n31 << 16 | n28 / n31 << 8 | n29 / n31);
            }
            n23 += this.width;
        }
    }
    
    public void copy(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.copy(this, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void copy(final PImage pImage, final int n, final int n2, int n3, int n4, final int n5, final int n6, int n7, int n8) {
        if (this.imageMode == 0) {
            n3 += n;
            n4 += n2;
            n7 += n5;
            n8 += n6;
        }
        if (pImage == this && this.intersect(n, n2, n3, n4, n5, n6, n7, n8)) {
            this.blit_resize(this.get(n, n2, n3 - n, n4 - n2), 0, 0, n3 - n - 1, n4 - n2 - 1, this.pixels, this.width, this.height, n5, n6, n7, n8, 0);
        }
        else {
            this.blit_resize(pImage, n, n2, n3, n4, this.pixels, this.width, this.height, n5, n6, n7, n8, 0);
        }
    }
    
    public static int blend(final int n, final int n2, final int n3) {
        switch (n3) {
            case 1: {
                return blend_multiply(n, n2);
            }
            case 2: {
                return blend_add_pin(n, n2);
            }
            case 4: {
                return blend_sub_pin(n, n2);
            }
            case 8: {
                return blend_lightest(n, n2);
            }
            case 16: {
                return blend_darkest(n, n2);
            }
            case 0: {
                return n2;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n3 >= 0 && n3 < this.width && n >= 0 && n < this.width && n4 >= 0 && n4 < this.height && n2 >= 0 && n2 < this.height) {
            this.pixels[n4 * this.width + n3] = blend(this.pixels[n4 * this.width + n3], this.pixels[n2 * this.width + n], n5);
        }
    }
    
    public void blend(final PImage pImage, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n3 >= 0 && n3 < this.width && n >= 0 && n < pImage.width && n4 >= 0 && n4 < this.height && n2 >= 0 && n2 < pImage.height) {
            this.pixels[n4 * this.width + n3] = blend(this.pixels[n4 * this.width + n3], pImage.pixels[n2 * pImage.width + n], n5);
        }
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        this.blend(this, n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public void blend(final PImage pImage, final int n, final int n2, int n3, int n4, final int n5, final int n6, int n7, int n8, final int n9) {
        if (this.imageMode == 0) {
            n3 += n;
            n4 += n2;
            n7 += n5;
            n8 += n6;
        }
        if (pImage == this && this.intersect(n, n2, n3, n4, n5, n6, n7, n8)) {
            this.blit_resize(this.get(n, n2, n3 - n, n4 - n2), 0, 0, n3 - n - 1, n4 - n2 - 1, this.pixels, this.width, this.height, n5, n6, n7, n8, n9);
        }
        else {
            this.blit_resize(pImage, n, n2, n3, n4, this.pixels, this.width, this.height, n5, n6, n7, n8, n9);
        }
    }
    
    protected boolean intersect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n3 - n + 1;
        final int n10 = n4 - n2 + 1;
        int n11 = n7 - n5 + 1;
        int n12 = n8 - n6 + 1;
        if (n5 < n) {
            n11 += n5 - n;
            if (n11 > n9) {
                n11 = n9;
            }
        }
        else {
            final int n13 = n9 + n - n5;
            if (n11 > n13) {
                n11 = n13;
            }
        }
        if (n6 < n2) {
            n12 += n6 - n2;
            if (n12 > n10) {
                n12 = n10;
            }
        }
        else {
            final int n14 = n10 + n2 - n6;
            if (n12 > n14) {
                n12 = n14;
            }
        }
        boolean b = false;
        if (n11 <= 0 || n12 <= 0) {
            b = true;
        }
        return b ^ true;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final PImage pImage = (PImage)super.clone();
        pImage.pixels = new int[this.width * this.height];
        System.arraycopy(this.pixels, 0, pImage.pixels, 0, this.pixels.length);
        return pImage;
    }
    
    private final void blit_resize(final PImage pImage, int n, int n2, int n3, int n4, final int[] array, final int n5, final int n6, int n7, int n8, final int n9, final int n10, final int n11) {
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 >= pImage.width) {
            n3 = pImage.width - 1;
        }
        if (n4 >= pImage.width) {
            n4 = pImage.height - 1;
        }
        int n12 = n3 - n;
        int n13 = n4 - n2;
        int n14 = n9 - n7;
        int n15 = n10 - n8;
        if (!this.smooth) {
            ++n12;
            ++n13;
        }
        if (n14 <= 0 || n15 <= 0 || n12 <= 0 || n13 <= 0 || n7 >= n5 || n8 >= n6 || n >= pImage.width || n2 >= pImage.height) {
            return;
        }
        final int n16 = (int)(n12 / n14 * 32768.0f);
        final int n17 = (int)(n13 / n15 * 32768.0f);
        this.srcXOffset = ((n7 < 0) ? (-n7 * n16) : (n * 32768));
        this.srcYOffset = ((n8 < 0) ? (-n8 * n17) : (n2 * 32768));
        if (n7 < 0) {
            n14 += n7;
            n7 = 0;
        }
        if (n8 < 0) {
            n15 += n8;
            n8 = 0;
        }
        final int low = low(n14, n5 - n7);
        final int low2 = low(n15, n6 - n8);
        int n18 = n8 * n5 + n7;
        this.srcBuffer = pImage.pixels;
        if (this.smooth) {
            this.iw = pImage.width;
            this.iw1 = pImage.width - 1;
            this.ih1 = pImage.height - 1;
            switch (n11) {
                case 1: {
                    for (int i = 0; i < low2; ++i) {
                        this.filter_new_scanline();
                        for (int j = 0; j < low; ++j) {
                            array[n18 + j] = blend_multiply(array[n18 + j], this.filter_bilinear());
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 2: {
                    for (int k = 0; k < low2; ++k) {
                        this.filter_new_scanline();
                        for (int l = 0; l < low; ++l) {
                            array[n18 + l] = blend_add_pin(array[n18 + l], this.filter_bilinear());
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 4: {
                    for (int n19 = 0; n19 < low2; ++n19) {
                        this.filter_new_scanline();
                        for (int n20 = 0; n20 < low; ++n20) {
                            array[n18 + n20] = blend_sub_pin(array[n18 + n20], this.filter_bilinear());
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 8: {
                    for (int n21 = 0; n21 < low2; ++n21) {
                        this.filter_new_scanline();
                        for (int n22 = 0; n22 < low; ++n22) {
                            array[n18 + n22] = blend_lightest(array[n18 + n22], this.filter_bilinear());
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 16: {
                    for (int n23 = 0; n23 < low2; ++n23) {
                        this.filter_new_scanline();
                        for (int n24 = 0; n24 < low; ++n24) {
                            array[n18 + n24] = blend_darkest(array[n18 + n24], this.filter_bilinear());
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 0: {
                    for (int n25 = 0; n25 < low2; ++n25) {
                        this.filter_new_scanline();
                        for (int n26 = 0; n26 < low; ++n26) {
                            array[n18 + n26] = this.filter_bilinear();
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
            }
        }
        else {
            switch (n11) {
                case 1: {
                    for (int n27 = 0; n27 < low2; ++n27) {
                        this.sX = this.srcXOffset;
                        this.sY = (this.srcYOffset >> 15) * pImage.width;
                        for (int n28 = 0; n28 < low; ++n28) {
                            array[n18 + n28] = blend_multiply(array[n18 + n28], this.srcBuffer[this.sY + (this.sX >> 15)]);
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 2: {
                    for (int n29 = 0; n29 < low2; ++n29) {
                        this.sX = this.srcXOffset;
                        this.sY = (this.srcYOffset >> 15) * pImage.width;
                        for (int n30 = 0; n30 < low; ++n30) {
                            array[n18 + n30] = blend_add_pin(array[n18 + n30], this.srcBuffer[this.sY + (this.sX >> 15)]);
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 4: {
                    for (int n31 = 0; n31 < low2; ++n31) {
                        this.sX = this.srcXOffset;
                        this.sY = (this.srcYOffset >> 15) * pImage.width;
                        for (int n32 = 0; n32 < low; ++n32) {
                            array[n18 + n32] = blend_sub_pin(array[n18 + n32], this.srcBuffer[this.sY + (this.sX >> 15)]);
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 8: {
                    for (int n33 = 0; n33 < low2; ++n33) {
                        this.sX = this.srcXOffset;
                        this.sY = (this.srcYOffset >> 15) * pImage.width;
                        for (int n34 = 0; n34 < low; ++n34) {
                            array[n18 + n34] = blend_lightest(array[n18 + n34], this.srcBuffer[this.sY + (this.sX >> 15)]);
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 16: {
                    for (int n35 = 0; n35 < low2; ++n35) {
                        this.sX = this.srcXOffset;
                        this.sY = (this.srcYOffset >> 15) * pImage.width;
                        for (int n36 = 0; n36 < low; ++n36) {
                            array[n18 + n36] = blend_darkest(array[n18 + n36], this.srcBuffer[this.sY + (this.sX >> 15)]);
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
                case 0: {
                    for (int n37 = 0; n37 < low2; ++n37) {
                        this.sX = this.srcXOffset;
                        this.sY = (this.srcYOffset >> 15) * pImage.width;
                        for (int n38 = 0; n38 < low; ++n38) {
                            array[n18 + n38] = this.srcBuffer[this.sY + (this.sX >> 15)];
                            this.sX += n16;
                        }
                        n18 += n5;
                        this.srcYOffset += n17;
                    }
                    break;
                }
            }
        }
    }
    
    private final void filter_new_scanline() {
        this.sX = this.srcXOffset;
        this.fracV = (this.srcYOffset & 0x7FFF);
        this.ifV = 32767 - this.fracV;
        this.v1 = (this.srcYOffset >> 15) * this.iw;
        this.v2 = low((this.srcYOffset >> 15) + 1, this.ih1) * this.iw;
    }
    
    private final int filter_bilinear() {
        this.fracU = (this.sX & 0x7FFF);
        this.ifU = 32767 - this.fracU;
        this.ul = this.ifU * this.ifV >> 15;
        this.ll = this.ifU * this.fracV >> 15;
        this.ur = this.fracU * this.ifV >> 15;
        this.lr = this.fracU * this.fracV >> 15;
        this.u1 = this.sX >> 15;
        this.u2 = low(this.u1 + 1, this.iw1);
        this.cUL = this.srcBuffer[this.v1 + this.u1];
        this.cUR = this.srcBuffer[this.v1 + this.u2];
        this.cLL = this.srcBuffer[this.v2 + this.u1];
        this.cLR = this.srcBuffer[this.v2 + this.u2];
        this.r = (this.ul * ((this.cUL & 0xFF0000) >> 16) + this.ll * ((this.cLL & 0xFF0000) >> 16) + this.ur * ((this.cUR & 0xFF0000) >> 16) + this.lr * ((this.cLR & 0xFF0000) >> 16) << 1 & 0xFF0000);
        this.g = (this.ul * (this.cUL & 0xFF00) + this.ll * (this.cLL & 0xFF00) + this.ur * (this.cUR & 0xFF00) + this.lr * (this.cLR & 0xFF00) >>> 15 & 0xFF00);
        this.b = this.ul * (this.cUL & 0xFF) + this.ll * (this.cLL & 0xFF) + this.ur * (this.cUR & 0xFF) + this.lr * (this.cLR & 0xFF) >>> 15;
        this.a = (this.ul * ((this.cUL & 0xFF000000) >>> 24) + this.ll * ((this.cLL & 0xFF000000) >>> 24) + this.ur * ((this.cUR & 0xFF000000) >>> 24) + this.lr * ((this.cLR & 0xFF000000) >>> 24) << 9 & 0xFF000000);
        return this.a | this.r | this.g | this.b;
    }
    
    private static final int low(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    private static final int high(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    private static final float frac(final float n) {
        return n - (int)n;
    }
    
    private static final int mix(final int n, final int n2, final int n3) {
        return n + ((n2 - n) * n3 >> 8);
    }
    
    private static final int blend_multiply(final int n, final int n2) {
        final int n3 = (n2 & 0xFF000000) >>> 24;
        return low(((n & 0xFF000000) >>> 24) + n3, 255) << 24 | (mix(n & 0xFF0000, n2 & 0xFF0000, n3) & 0xFF0000) | (mix(n & 0xFF00, n2 & 0xFF00, n3) & 0xFF00) | mix(n & 0xFF, n2 & 0xFF, n3);
    }
    
    private static final int blend_add_pin(final int n, final int n2) {
        final int n3 = (n2 & 0xFF000000) >>> 24;
        return low(((n & 0xFF000000) >>> 24) + n3, 255) << 24 | (low((n & 0xFF0000) + ((n2 & 0xFF0000) >> 8) * n3, 16711680) & 0xFF0000) | (low((n & 0xFF00) + ((n2 & 0xFF00) >> 8) * n3, 65280) & 0xFF00) | low((n & 0xFF) + ((n2 & 0xFF) * n3 >> 8), 255);
    }
    
    private static final int blend_sub_pin(final int n, final int n2) {
        final int n3 = (n2 & 0xFF000000) >>> 24;
        return low(((n & 0xFF000000) >>> 24) + n3, 255) << 24 | (high((n & 0xFF0000) - ((n2 & 0xFF0000) >> 8) * n3, 65280) & 0xFF0000) | (high((n & 0xFF00) - ((n2 & 0xFF00) >> 8) * n3, 255) & 0xFF00) | high((n & 0xFF) - ((n2 & 0xFF) * n3 >> 8), 0);
    }
    
    private static final int blend_lightest(final int n, final int n2) {
        final int n3 = (n2 & 0xFF000000) >>> 24;
        return low(((n & 0xFF000000) >>> 24) + n3, 255) << 24 | (high(n & 0xFF0000, ((n2 & 0xFF0000) >> 8) * n3) & 0xFF0000) | (high(n & 0xFF00, ((n2 & 0xFF00) >> 8) * n3) & 0xFF00) | high(n & 0xFF, (n2 & 0xFF) * n3 >> 8);
    }
    
    private static final int blend_darkest(final int n, final int n2) {
        final int n3 = (n2 & 0xFF000000) >>> 24;
        return low(((n & 0xFF000000) >>> 24) + n3, 255) << 24 | (mix(n & 0xFF0000, low(n & 0xFF0000, ((n2 & 0xFF0000) >> 8) * n3), n3) & 0xFF0000) | (mix(n & 0xFF00, low(n & 0xFF00, ((n2 & 0xFF00) >> 8) * n3), n3) & 0xFF00) | mix(n & 0xFF, low(n & 0xFF, (n2 & 0xFF) * n3 >> 8), n3);
    }
    
    public static boolean saveHeaderTIFF(final OutputStream outputStream, final int n, final int n2) {
        try {
            final byte[] array = new byte[768];
            System.arraycopy(PImage.tiff_header, 0, array, 0, PImage.tiff_header.length);
            array[30] = (byte)(n >> 8 & 0xFF);
            array[31] = (byte)(n & 0xFF);
            array[42] = (array[102] = (byte)(n2 >> 8 & 0xFF));
            array[43] = (array[103] = (byte)(n2 & 0xFF));
            final int n3 = n * n2 * 3;
            array[114] = (byte)(n3 >> 24 & 0xFF);
            array[115] = (byte)(n3 >> 16 & 0xFF);
            array[116] = (byte)(n3 >> 8 & 0xFF);
            array[117] = (byte)(n3 & 0xFF);
            outputStream.write(array);
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean saveTIFF(final OutputStream outputStream, final int[] array, final int n, final int n2) {
        try {
            if (!saveHeaderTIFF(outputStream, n, n2)) {
                return false;
            }
            for (int i = 0; i < array.length; ++i) {
                outputStream.write(array[i] >> 16 & 0xFF);
                outputStream.write(array[i] >> 8 & 0xFF);
                outputStream.write(array[i] & 0xFF);
            }
            outputStream.flush();
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean saveHeaderTGA(final OutputStream outputStream, final int n, final int n2) {
        try {
            outputStream.write(new byte[] { 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, (byte)(n & 0xFF), (byte)(n >> 8), (byte)(n2 & 0xFF), (byte)(n2 >> 8), 32, 8 });
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean saveTGA(final OutputStream outputStream, final int[] array, final int n, final int n2) {
        try {
            if (!saveHeaderTGA(outputStream, n, n2)) {
                return false;
            }
            int n3 = (n2 - 1) * n;
            for (int i = n2 - 1; i >= 0; --i) {
                for (int j = 0; j < n; ++j) {
                    final int n4 = array[n3 + j];
                    outputStream.write(n4 & 0xFF);
                    outputStream.write(n4 >> 8 & 0xFF);
                    outputStream.write(n4 >> 16 & 0xFF);
                    outputStream.write(n4 >>> 24 & 0xFF);
                }
                n3 -= n;
            }
            outputStream.flush();
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void save(String string) {
        try {
            BufferedOutputStream bufferedOutputStream;
            if (string.toLowerCase().endsWith(".tga")) {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string), 32768);
                saveTGA(bufferedOutputStream, this.pixels, this.width, this.height);
            }
            else {
                if (!string.toLowerCase().endsWith(".tif") && !string.toLowerCase().endsWith(".tiff")) {
                    string += ".tif";
                }
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string), 32768);
                saveTIFF(bufferedOutputStream, this.pixels, this.width, this.height);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private final /* synthetic */ void this() {
        this.imageMode = 0;
        this.smooth = false;
    }
    
    public PImage() {
        this.this();
        this.format = 1;
        this.cache = null;
    }
    
    public PImage(final int n, final int n2) {
        this.this();
        this.init(n, n2, 1);
    }
    
    public PImage(final int[] pixels, final int width, final int height, final int format) {
        this.this();
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.format = format;
        this.cache = null;
    }
    
    public PImage(final Image image) {
        this.this();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.pixels = new int[this.width * this.height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.width, this.height, this.pixels, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        this.format = 1;
        this.cache = null;
    }
    
    static {
        PImage.tiff_header = new byte[] { 77, 77, 0, 42, 0, 0, 0, 8, 0, 9, 0, -2, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 2, 0, 3, 0, 0, 0, 3, 0, 0, 0, 122, 1, 6, 0, 3, 0, 0, 0, 1, 0, 2, 0, 0, 1, 17, 0, 4, 0, 0, 0, 1, 0, 0, 3, 0, 1, 21, 0, 3, 0, 0, 0, 1, 0, 3, 0, 0, 1, 22, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 23, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 8, 0, 8 };
    }
}
