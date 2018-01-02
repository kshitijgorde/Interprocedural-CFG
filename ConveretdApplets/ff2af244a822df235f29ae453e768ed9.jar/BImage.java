import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class BImage implements BConstants
{
    static final int PRECISIONB = 15;
    static final int PRECISIONF = 32768;
    static final int PREC_MAXVAL = 32767;
    static final int PREC_ALPHA_SHIFT = 9;
    static final int PREC_RED_SHIFT = 1;
    static byte[] tiff_header;
    int format;
    int[] pixels;
    int width;
    int height;
    boolean smooth;
    int cacheIndex;
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
    
    public void alpha(final int[] array) {
        if (array.length != this.pixels.length) {
            System.err.println("alpha(): the alpha mask image must be the same size");
            return;
        }
        for (int i = 0; i < this.pixels.length; ++i) {
            this.pixels[i] = ((this.pixels[i] & 0xFFFFFF) | (array[i] & 0xFF) << 24);
        }
        this.format = 2;
    }
    
    public void alpha(final BImage bImage) {
        this.alpha(bImage.pixels);
    }
    
    public int blendColor(final int n, final int n2, final int n3) {
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
    
    public void toGrayscale() {
        for (int i = 0; i < this.pixels.length; ++i) {
            final int n = this.pixels[i];
            final int n2 = 77 * (n >> 16 & 0xFF) + 151 * (n >> 8 & 0xFF) + 28 * (n & 0xFF) >> 8;
            this.pixels[i] = ((n & 0xFF000000) | n2 << 16 | n2 << 8 | n2);
        }
    }
    
    public int get(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.width || n2 >= this.height) {
            return 0;
        }
        return this.pixels[n2 * this.width + n];
    }
    
    public BImage get(int n, int n2, int n3, int n4) {
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n + n3 > this.width) {
            n3 = this.width - n;
        }
        if (n2 + n4 > this.height) {
            n4 = this.height - n2;
        }
        final BImage bImage = new BImage(new int[n3 * n4], n3, n4, this.format);
        int n5 = n2 * this.width + n;
        int n6 = 0;
        for (int i = n2; i < n2 + n4; ++i) {
            System.arraycopy(this.pixels, n5, bImage.pixels, n6, n3);
            n5 += this.width;
            n6 += n3;
        }
        return bImage;
    }
    
    public void set(final int n, final int n2, final int n3) {
        if (n < 0 || n2 < 0 || n >= this.width || n2 >= this.height) {
            return;
        }
        this.pixels[n2 * this.width + n] = n3;
    }
    
    public void set(final int n, final int n2, final BImage bImage) {
        int n3 = 0;
        int n4 = 0;
        int width = bImage.width;
        int height = bImage.height;
        int n5 = n;
        int n6 = n2;
        int width2 = bImage.width;
        int height2 = bImage.height;
        if (n5 < 0) {
            n3 -= n5;
            width += n5;
            width2 += n5;
            n5 = 0;
        }
        if (n6 < 0) {
            n4 -= n6;
            height += n6;
            height2 += n6;
            n6 = 0;
        }
        if (n5 + width2 > this.width) {
            width -= n5 + width2 - this.width;
        }
        if (n6 + height2 > this.height) {
            final int n7 = n6 + height2 - this.height;
            height -= n7;
            width -= n7;
        }
        for (int i = n4; i < n4 + height; ++i) {
            System.arraycopy(bImage.pixels, i * bImage.width + n3, this.pixels, (n2 + i) * this.width + n5, width);
        }
    }
    
    public void replicate(final int n, final int n2, final int n3, final int n4) {
        if (n >= 0 && n < this.width && n3 >= 0 && n3 < this.width && n2 >= 0 && n2 < this.height && n4 >= 0 && n4 < this.height) {
            this.pixels[n4 * this.width + n3] = this.pixels[n2 * this.width + n];
        }
    }
    
    public void replicate(final BImage bImage, final int n, final int n2, final int n3, final int n4) {
        if (n3 >= 0 && n3 < this.width && n >= 0 && n < bImage.width && n4 >= 0 && n4 < this.height && n2 >= 0 && n2 < bImage.height) {
            this.pixels[n4 * this.width + n3] = bImage.pixels[n2 * bImage.width + n];
        }
    }
    
    public void replicate(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (this.intersect(n, n2, n3, n4, n5, n6, n7, n8)) {
            this.blit_resize(this.get(n, n2, n3 - n, n4 - n2), 0, 0, n3 - n - 1, n4 - n2 - 1, this.pixels, this.width, this.height, n5, n6, n7, n8, 0);
        }
        else {
            this.blit_resize(this, n, n2, n3, n4, this.pixels, this.width, this.height, n5, n6, n7, n8, 0);
        }
    }
    
    public void replicate(final BImage bImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.blit_resize(bImage, n, n2, n3, n4, this.pixels, this.width, this.height, n5, n6, n7, n8, 0);
    }
    
    public void blend(final BImage bImage, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n3 >= 0 && n3 < this.width && n >= 0 && n < bImage.width && n4 >= 0 && n4 < this.height && n2 >= 0 && n2 < bImage.height) {
            this.pixels[n4 * this.width + n3] = this.blendColor(this.pixels[n4 * this.width + n3], bImage.pixels[n2 * bImage.width + n], n5);
        }
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n3 >= 0 && n3 < this.width && n >= 0 && n < this.width && n4 >= 0 && n4 < this.height && n2 >= 0 && n2 < this.height) {
            this.pixels[n4 * this.width + n3] = this.blendColor(this.pixels[n4 * this.width + n3], this.pixels[n2 * this.width + n], n5);
        }
    }
    
    public void blend(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        if (this.intersect(n, n2, n3, n4, n5, n6, n7, n8)) {
            this.blit_resize(this.get(n, n2, n3 - n, n4 - n2), 0, 0, n3 - n - 1, n4 - n2 - 1, this.pixels, this.width, this.height, n5, n6, n7, n8, n9);
        }
        else {
            this.blit_resize(this, n, n2, n3, n4, this.pixels, this.width, this.height, n5, n6, n7, n8, n9);
        }
    }
    
    public void blend(final BImage bImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        this.blit_resize(bImage, n, n2, n3, n4, this.pixels, this.width, this.height, n5, n6, n7, n8, n9);
    }
    
    public BImage copy() {
        final BImage bImage = new BImage(new int[this.pixels.length], this.width, this.height, this.format);
        System.arraycopy(this.pixels, 0, bImage.pixels, 0, this.pixels.length);
        return bImage;
    }
    
    public BImage copy(final int n, final int n2) {
        final BImage bImage = new BImage(new int[n * n2], n, n2, this.format);
        bImage.replicate(this, 0, 0, this.width - 1, this.height - 1, 0, 0, n - 1, n2 - 1);
        return bImage;
    }
    
    boolean intersect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
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
    
    private final void blit_resize(final BImage bImage, int n, int n2, int n3, int n4, final int[] array, final int n5, final int n6, int n7, int n8, final int n9, final int n10, final int n11) {
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 >= bImage.width) {
            n3 = bImage.width - 1;
        }
        if (n4 >= bImage.width) {
            n4 = bImage.height - 1;
        }
        int n12 = n3 - n;
        int n13 = n4 - n2;
        int n14 = n9 - n7;
        int n15 = n10 - n8;
        if (!this.smooth) {
            ++n12;
            ++n13;
        }
        if (n14 <= 0 || n15 <= 0 || n12 <= 0 || n13 <= 0 || n7 >= n5 || n8 >= n6 || n >= bImage.width || n2 >= bImage.height) {
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
        this.srcBuffer = bImage.pixels;
        if (this.smooth) {
            this.iw = bImage.width;
            this.iw1 = bImage.width - 1;
            this.ih1 = bImage.height - 1;
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
                        this.sY = (this.srcYOffset >> 15) * bImage.width;
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
                        this.sY = (this.srcYOffset >> 15) * bImage.width;
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
                        this.sY = (this.srcYOffset >> 15) * bImage.width;
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
                        this.sY = (this.srcYOffset >> 15) * bImage.width;
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
                        this.sY = (this.srcYOffset >> 15) * bImage.width;
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
                        this.sY = (this.srcYOffset >> 15) * bImage.width;
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
    
    private final float frac(final float n) {
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
    
    static void write_tiff(final OutputStream outputStream, final int[] array, final int n, final int n2) throws IOException {
        final byte[] array2 = new byte[768];
        System.arraycopy(BImage.tiff_header, 0, array2, 0, BImage.tiff_header.length);
        array2[30] = (byte)(n >> 8 & 0xFF);
        array2[31] = (byte)(n & 0xFF);
        array2[42] = (array2[102] = (byte)(n2 >> 8 & 0xFF));
        array2[43] = (array2[103] = (byte)(n2 & 0xFF));
        final int n3 = n * n2 * 3;
        array2[114] = (byte)(n3 >> 24 & 0xFF);
        array2[115] = (byte)(n3 >> 16 & 0xFF);
        array2[116] = (byte)(n3 >> 8 & 0xFF);
        array2[117] = (byte)(n3 & 0xFF);
        outputStream.write(array2);
        for (int i = 0; i < array.length; ++i) {
            outputStream.write(array[i] >> 16 & 0xFF);
            outputStream.write(array[i] >> 8 & 0xFF);
            outputStream.write(array[i] & 0xFF);
        }
        outputStream.flush();
    }
    
    static void write_targa(final OutputStream outputStream, final int[] array, final int n, final int n2) throws IOException {
        outputStream.write(new byte[] { 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, (byte)(n & 0xFF), (byte)(n >> 8), (byte)(n2 & 0xFF), (byte)(n2 >> 8), 32, 8 });
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
    }
    
    public void save(String string) {
        try {
            BufferedOutputStream bufferedOutputStream;
            if (string.toLowerCase().endsWith(".tga")) {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string), 32768);
                write_targa(bufferedOutputStream, this.pixels, this.width, this.height);
            }
            else {
                if (!string.toLowerCase().endsWith(".tif") && !string.toLowerCase().endsWith(".tiff")) {
                    string += ".tif";
                }
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string), 32768);
                write_tiff(bufferedOutputStream, this.pixels, this.width, this.height);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private final /* synthetic */ void this() {
        this.smooth = false;
    }
    
    public BImage() {
        this.this();
    }
    
    public BImage(final int n, final int n2) {
        this(new int[n * n2], n, n2, 2);
    }
    
    public BImage(final int[] pixels, final int width, final int height, final int format) {
        this.this();
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.format = format;
        this.cacheIndex = -1;
    }
    
    public BImage(final Image image) {
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
        this.cacheIndex = -1;
    }
    
    static {
        BImage.tiff_header = new byte[] { 77, 77, 0, 42, 0, 0, 0, 8, 0, 9, 0, -2, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 2, 0, 3, 0, 0, 0, 3, 0, 0, 0, 122, 1, 6, 0, 3, 0, 0, 0, 1, 0, 2, 0, 0, 1, 17, 0, 4, 0, 0, 0, 1, 0, 0, 3, 0, 1, 21, 0, 3, 0, 0, 0, 1, 0, 3, 0, 0, 1, 22, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 23, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 8, 0, 8 };
    }
}
