// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.Point;

public class FancyHelper
{
    public int REDMASK;
    public int GREENMASK;
    public int BLUEMASK;
    public int BASEMASK;
    public int ALPHAMASK;
    public int BITSHIFT;
    public int REDSHIFT;
    public int GREENSHIFT;
    public int BLUESHIFT;
    public int REDSHIFT8;
    public int GREENSHIFT8;
    public int BLUESHIFT8;
    public int DEPTH;
    protected int m_nRedMult;
    protected int m_nGreenMult;
    protected int m_nBlueMult;
    protected int m_nAlphaMult;
    protected boolean m_bAlphaSupport;
    protected boolean m_bShadow;
    protected Point m_ptShadow;
    private static int[] \u00e0;
    protected int[] m_pixelsrc;
    protected int m_nWpixels;
    protected int m_nHpixels;
    protected ColorModel m_cm;
    
    public void fillRect(final int[] array, final int n, final int n2, final int n3, final Rectangle rectangle) {
        int x = rectangle.x;
        if (x < 0) {
            x = 0;
        }
        int y = rectangle.y;
        if (y < 0) {
            y = 0;
        }
        int n4 = y * n + x;
        int n5 = rectangle.x + rectangle.width;
        if (n5 > n) {
            n5 = n;
        }
        final int n6 = n5 - x;
        int n7 = rectangle.y + rectangle.height;
        if (n7 > n2) {
            n7 = n2;
        }
        for (int n8 = n7 - y, i = 0; i < n8; ++i) {
            for (int j = 0; j < n6; ++j) {
                array[n4++] = n3;
            }
            n4 += n - n6;
        }
    }
    
    public void setColorBalance(final int nAlphaMult, final int nRedMult, final int nGreenMult, final int nBlueMult) {
        \u00e0();
        this.m_nAlphaMult = nAlphaMult;
        this.m_nRedMult = nRedMult;
        this.m_nGreenMult = nGreenMult;
        this.m_nBlueMult = nBlueMult;
    }
    
    public Point getShadowOffset() {
        return this.m_ptShadow;
    }
    
    public boolean setShadowOffset(final int n, final int n2, final boolean bShadow) {
        this.m_bShadow = bShadow;
        if (this.m_ptShadow != null) {
            this.m_ptShadow.setLocation(n, n2);
        }
        else {
            this.m_ptShadow = new Point(n, n2);
        }
        return true;
    }
    
    public boolean addMask(final Image image) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (this.m_nWpixels != width || this.m_nHpixels != height) {
            return false;
        }
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return false;
        }
        return this.addMask(array);
    }
    
    public FancyHelper() {
        this.REDMASK = 63488;
        this.GREENMASK = 2016;
        this.BLUEMASK = 31;
        this.BASEMASK = 31;
        this.ALPHAMASK = 31;
        this.BITSHIFT = 3;
        this.REDSHIFT = 11;
        this.GREENSHIFT = 6;
        this.BLUESHIFT = 0;
        this.REDSHIFT8 = 16;
        this.GREENSHIFT8 = 8;
        this.BLUESHIFT8 = 0;
        this.DEPTH = 16;
        this.m_nRedMult = 128;
        this.m_nGreenMult = 128;
        this.m_nBlueMult = 128;
        this.m_nAlphaMult = 128;
        this.m_bAlphaSupport = true;
        this.m_bShadow = false;
        this.m_ptShadow = null;
        this.m_pixelsrc = null;
        this.m_nWpixels = 0;
        this.m_nHpixels = 0;
        this.m_cm = null;
        this.REDMASK = 16711680;
        this.GREENMASK = 65280;
        this.BLUEMASK = 255;
        this.BASEMASK = 31;
        this.BITSHIFT = 3;
        this.REDSHIFT = 19;
        this.GREENSHIFT = 11;
        this.BLUESHIFT = 3;
        this.DEPTH = 32;
        this.REDSHIFT8 = 16;
        this.GREENSHIFT8 = 8;
        this.BLUESHIFT8 = 0;
        this.m_cm = new DirectColorModel(32, this.REDMASK, this.GREENMASK, this.BLUEMASK);
    }
    
    public boolean addMask(final int[] array) {
        if (array.length != this.m_nHpixels * this.m_nWpixels) {
            return false;
        }
        for (int i = 0; i < this.m_nHpixels; ++i) {
            for (int n = i * this.m_nWpixels, j = 0; j < this.m_nWpixels; ++j, ++n) {
                final int[] pixelsrc = this.m_pixelsrc;
                final int n2 = n;
                pixelsrc[n2] |= 0xFF000000;
                final int[] pixelsrc2 = this.m_pixelsrc;
                final int n3 = n;
                pixelsrc2[n3] &= ((array[n] & 0xFF) << 24 | 0xFFFFFF);
            }
        }
        return true;
    }
    
    public void draw(final int[] array, final int n, final int n2, int n3, int i, final Rectangle rectangle, final Rectangle rectangle2) {
        if (this.m_nAlphaMult == 0) {
            return;
        }
        if (this.m_bShadow) {
            this.drawShadow(array, n, n2, n3 + this.m_ptShadow.x, i + this.m_ptShadow.y, rectangle, rectangle2);
        }
        int x = rectangle.x;
        if (x < 0) {
            x = 0;
        }
        int y = rectangle.y;
        if (y < 0) {
            y = 0;
        }
        int n4 = rectangle.x + rectangle.width;
        if (n4 > n) {
            n4 = n;
        }
        int n5 = rectangle.y + rectangle.height;
        if (n5 > n2) {
            n5 = n2;
        }
        int n6;
        int n7;
        int n8;
        if (rectangle2 == null) {
            n6 = this.m_nWpixels;
            n7 = this.m_nHpixels;
            n8 = 0;
        }
        else {
            n6 = rectangle2.width;
            n7 = rectangle2.height;
            n8 = rectangle2.x + rectangle2.y * this.m_nWpixels;
            if (rectangle2.x + n6 > this.m_nWpixels) {
                n6 = this.m_nWpixels - rectangle2.x;
            }
            if (rectangle2.y + n7 > this.m_nHpixels) {
                n7 = this.m_nHpixels - rectangle2.y;
            }
        }
        if (i > n5 || n3 > n4 || n3 + n6 < x || i + n7 < y) {
            return;
        }
        if (n4 > n3 + n6) {
            n4 = n3 + n6;
        }
        if (n5 > i + n7) {
            n5 = i + n7;
        }
        int n9 = i * n;
        if (i < y) {
            n8 += (y - i) * this.m_nWpixels;
            n9 = y * n;
            i = y;
        }
        final int n10 = i;
        if (n3 < x) {
            n8 += x - n3;
            n3 = x;
        }
        final int n11 = n3;
        int n12 = n9 + n3;
        final int n13 = n4 - n11;
        final boolean b = this.m_nRedMult != 128 || this.m_nGreenMult != 128 || this.m_nBlueMult != 128;
        if (!this.m_bAlphaSupport && !b && this.m_nAlphaMult == 128) {
            for (i = n10; i < n5; ++i) {
                System.arraycopy(this.m_pixelsrc, n8, array, n12, n13);
                n12 += n;
                n8 += this.m_nWpixels;
            }
            return;
        }
        final int[] pixelsrc = this.m_pixelsrc;
        if (b) {
            int n14;
            int n15;
            int n16;
            int n17;
            int n18;
            int n19;
            int n20;
            int n21;
            int n22;
            int n23;
            int n24;
            int n25;
            int n26;
            int n27;
            int n28;
            int n29;
            int n30;
            int n31;
            for (i = n10; i < n5; ++i) {
                n3 = n13;
                n14 = n8;
                while (n3-- > 0) {
                    n15 = pixelsrc[n14++];
                    n16 = (n15 >> 27 & this.BASEMASK);
                    n17 = (n15 >> 16 & 0xFF);
                    n18 = (n15 >> 8 & 0xFF);
                    n19 = (n15 & 0xFF);
                    n20 = n16 * this.m_nAlphaMult >> 7;
                    if (n20 >= 31) {
                        n21 = n17 * this.m_nRedMult >> 7;
                        if (n21 > 255) {
                            n21 = 255;
                        }
                        n22 = n18 * this.m_nGreenMult >> 7;
                        if (n22 > 255) {
                            n22 = 255;
                        }
                        n23 = n19 * this.m_nBlueMult >> 7;
                        if (n23 > 255) {
                            n23 = 255;
                        }
                        array[n12] = (0xFF000000 | n21 << this.REDSHIFT8 | n22 << this.GREENSHIFT8 | n23 << this.BLUESHIFT8);
                    }
                    else if (n20 > 0) {
                        n24 = array[n12];
                        n25 = (n24 & this.REDMASK) >> this.REDSHIFT;
                        n26 = (n24 & this.GREENMASK) >> this.GREENSHIFT;
                        n27 = (n24 & this.BLUEMASK) >> this.BLUESHIFT;
                        n28 = n20 << 10;
                        n29 = FancyHelper.\u00e0[n28 | (n25 << 5 & 0x3E0) | n17 >> 3] * this.m_nRedMult >> 7;
                        if (n29 > 255) {
                            n29 = 255;
                        }
                        n30 = FancyHelper.\u00e0[n28 | (n26 << 5 & 0x3E0) | n18 >> 3] * this.m_nGreenMult >> 7;
                        if (n30 > 255) {
                            n30 = 255;
                        }
                        n31 = FancyHelper.\u00e0[n28 | (n27 << 5 & 0x3E0) | n19 >> 3] * this.m_nBlueMult >> 7;
                        if (n31 > 255) {
                            n31 = 255;
                        }
                        array[n12] = (0xFF000000 | n29 << this.REDSHIFT8 | n30 << this.GREENSHIFT8 | n31 << this.BLUESHIFT8);
                    }
                    ++n12;
                }
                n12 += n - n13;
                n8 += this.m_nWpixels;
            }
            return;
        }
        int n32;
        int n33;
        int n34;
        int n35;
        int n36;
        int n37;
        int n38;
        int n39;
        for (i = n10; i < n5; ++i) {
            n3 = n13;
            n32 = n8;
            while (n3-- > 0) {
                n33 = pixelsrc[n32++];
                n34 = (n33 >> 27 & this.BASEMASK) * this.m_nAlphaMult >> 7;
                if (n34 >= this.BASEMASK) {
                    array[n12] = n33;
                }
                else if (n34 > 0) {
                    n35 = (n33 >> 19 & this.BASEMASK);
                    n36 = (n33 >> 11 & this.BASEMASK);
                    n37 = (n33 >> 3 & this.BASEMASK);
                    n38 = array[n12];
                    n39 = n34 << 10;
                    array[n12] = (0xFF000000 | FancyHelper.\u00e0[n39 | (n38 >> 14 & 0x3E0) | n35] << this.REDSHIFT8 | FancyHelper.\u00e0[n39 | (n38 >> 6 & 0x3E0) | n36] << this.GREENSHIFT8 | FancyHelper.\u00e0[n39 | (n38 << 2 & 0x3E0) | n37] << this.BLUESHIFT8);
                }
                ++n12;
            }
            n12 += n - n13;
            n8 += this.m_nWpixels;
        }
    }
    
    public void fillRectAlpha(final int[] array, final int n, final int n2, final int n3, final Rectangle rectangle) {
        int x = rectangle.x;
        if (x < 0) {
            x = 0;
        }
        int y = rectangle.y;
        if (y < 0) {
            y = 0;
        }
        int n4 = y * n + x;
        int n5 = rectangle.x + rectangle.width;
        if (n5 > n) {
            n5 = n;
        }
        final int n6 = n5 - x;
        int n7 = rectangle.y + rectangle.height;
        if (n7 > n2) {
            n7 = n2;
        }
        final int n8 = n7 - y;
        final int n9 = n3 >> 19 & this.BASEMASK;
        final int n10 = n3 >> 11 & this.BASEMASK;
        final int n11 = n3 >> 3 & this.BASEMASK;
        final int n12 = (n3 >> 27 & this.BASEMASK) << 10;
        if (n12 == 0) {
            return;
        }
        for (int i = 0; i < n8; ++i) {
            for (int j = 0; j < n6; ++j) {
                final int n13 = array[n4];
                array[n4] = (0xFF000000 | FancyHelper.\u00e0[n12 | (n13 >> 14 & 0x3E0) | n9] << this.REDSHIFT8 | FancyHelper.\u00e0[n12 | (n13 >> 6 & 0x3E0) | n10] << this.GREENSHIFT8 | FancyHelper.\u00e0[n12 | (n13 << 2 & 0x3E0) | n11] << this.BLUESHIFT8);
                ++n4;
            }
            n4 += n - n6;
        }
    }
    
    public int getAlphaBalance() {
        return this.m_nAlphaMult;
    }
    
    public void setAlphaBalance(final int nAlphaMult) {
        this.m_nAlphaMult = nAlphaMult;
    }
    
    public int getPixelValue(int n, int n2, int n3) {
        n = n * this.m_nRedMult >> 7;
        if (n > 255) {
            n = 255;
        }
        else {
            n &= 0xFF;
        }
        n2 = n2 * this.m_nGreenMult >> 7;
        if (n2 > 255) {
            n2 = 255;
        }
        else {
            n2 &= 0xFF;
        }
        n3 = n3 * this.m_nRedMult >> 7;
        if (n3 > 255) {
            n3 = 255;
        }
        else {
            n3 &= 0xFF;
        }
        return 0xFF000000 | (n << this.REDSHIFT8 & this.REDMASK) | (n2 << this.GREENSHIFT8 & this.GREENMASK) | (n3 << this.BLUESHIFT8 & this.BLUEMASK);
    }
    
    public int[] getPixels() {
        return this.m_pixelsrc;
    }
    
    public void setPixels(final int[] pixelsrc, final int nWpixels, final int nHpixels) {
        this.m_nWpixels = nWpixels;
        this.m_nHpixels = nHpixels;
        this.m_pixelsrc = pixelsrc;
    }
    
    static {
        FancyHelper.\u00e0 = null;
    }
    
    public void copy(final FancyHelper fancyHelper) {
        this.m_pixelsrc = fancyHelper.m_pixelsrc;
        this.m_nWpixels = fancyHelper.m_nWpixels;
        this.m_nHpixels = fancyHelper.m_nHpixels;
        this.m_bAlphaSupport = fancyHelper.m_bAlphaSupport;
        this.m_nRedMult = fancyHelper.m_nRedMult;
        this.m_nGreenMult = fancyHelper.m_nGreenMult;
        this.m_nBlueMult = fancyHelper.m_nBlueMult;
        this.m_nAlphaMult = fancyHelper.m_nAlphaMult;
    }
    
    private static synchronized void \u00e0() {
        if (FancyHelper.\u00e0 != null) {
            return;
        }
        FancyHelper.\u00e0 = new int[32768];
        int n = 0;
        do {
            int n2 = 0;
            do {
                int n3 = 0;
                do {
                    FancyHelper.\u00e0[n << 10 | n2 << 5 | n3] = (n3 * n * 8 + n2 * (31 - n) * 8) / 31;
                } while (++n3 < 32);
            } while (++n2 < 32);
        } while (++n < 32);
    }
    
    public int getHeight() {
        return this.m_nHpixels;
    }
    
    public boolean setImage(final Image image) {
        this.m_nWpixels = image.getWidth(null);
        this.m_nHpixels = image.getHeight(null);
        if (this.m_nWpixels == -1 || this.m_nHpixels == -1) {
            return false;
        }
        final int[] pixelsrc = new int[this.m_nWpixels * this.m_nHpixels];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.m_nWpixels, this.m_nHpixels, pixelsrc, 0, this.m_nWpixels);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return false;
        }
        this.m_pixelsrc = pixelsrc;
        \u00e0();
        return true;
    }
    
    public void drawShadow(final int[] array, final int n, final int n2, int n3, int i, final Rectangle rectangle, final Rectangle rectangle2) {
        if (this.m_nAlphaMult == 0) {
            return;
        }
        int x = rectangle.x;
        if (x < 0) {
            x = 0;
        }
        int y = rectangle.y;
        if (y < 0) {
            y = 0;
        }
        int n4 = rectangle.x + rectangle.width;
        if (n4 > n) {
            n4 = n;
        }
        int n5 = rectangle.y + rectangle.height;
        if (n5 > n2) {
            n5 = n2;
        }
        int n6;
        int n7;
        int n8;
        if (rectangle2 == null) {
            n6 = this.m_nWpixels;
            n7 = this.m_nHpixels;
            n8 = 0;
        }
        else {
            n6 = rectangle2.width;
            n7 = rectangle2.height;
            n8 = rectangle2.x + rectangle2.y * this.m_nWpixels;
            if (rectangle2.x + n6 > this.m_nWpixels) {
                n6 = this.m_nWpixels - rectangle2.x;
            }
            if (rectangle2.y + n7 > this.m_nHpixels) {
                n7 = this.m_nHpixels - rectangle2.y;
            }
        }
        if (i > n5 || n3 > n4 || n3 + n6 < x || i + n7 < y) {
            return;
        }
        if (n4 > n3 + n6) {
            n4 = n3 + n6;
        }
        if (n5 > i + n7) {
            n5 = i + n7;
        }
        int n9 = i * n;
        if (i < y) {
            n8 += (y - i) * this.m_nWpixels;
            n9 = y * n;
            i = y;
        }
        final int n10 = i;
        if (n3 < x) {
            n8 += x - n3;
            n3 = x;
        }
        final int n11 = n3;
        int n12 = n9 + n3;
        final int n13 = n4 - n11;
        final int[] pixelsrc = this.m_pixelsrc;
        int n14;
        int n15;
        int n16;
        int n17;
        int n18;
        int n19;
        int n20;
        for (i = n10; i < n5; ++i) {
            n3 = n13;
            n14 = n8;
            while (n3-- > 0) {
                n15 = (pixelsrc[n14++] >> 27 & this.BASEMASK) * this.m_nAlphaMult >> 8;
                if (n15 > 0) {
                    n16 = array[n12];
                    n17 = (n16 & this.REDMASK) >> this.REDSHIFT;
                    n18 = (n16 & this.GREENMASK) >> this.GREENSHIFT;
                    n19 = (n16 & this.BLUEMASK) >> this.BLUESHIFT;
                    n20 = n15 << 10;
                    array[n12] = (0xFF000000 | FancyHelper.\u00e0[n20 | (n17 << 5 & 0x3E0)] << this.REDSHIFT8 | FancyHelper.\u00e0[n20 | (n18 << 5 & 0x3E0)] << this.GREENSHIFT8 | FancyHelper.\u00e0[n20 | (n19 << 5 & 0x3E0)] << this.BLUESHIFT8);
                }
                ++n12;
            }
            n12 += n - n13;
            n8 += this.m_nWpixels;
        }
    }
    
    public ColorModel getColorModel() {
        return this.m_cm;
    }
    
    public int getWidth() {
        return this.m_nWpixels;
    }
}
