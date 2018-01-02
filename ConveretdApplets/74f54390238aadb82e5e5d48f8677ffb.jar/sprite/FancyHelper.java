// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Rectangle;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.DirectColorModel;
import java.awt.Point;
import java.awt.image.ColorModel;

public class FancyHelper
{
    public int ALPHAMASK;
    public int BASEMASK;
    public int BITSHIFT;
    public int BLUEMASK;
    public int BLUESHIFT;
    public int BLUESHIFT8;
    public int DEPTH;
    public int GREENMASK;
    public int GREENSHIFT;
    public int GREENSHIFT8;
    public int REDMASK;
    public int REDSHIFT;
    public int REDSHIFT8;
    static final boolean USE32BIT = true;
    protected boolean m_bAlphaSupport;
    protected boolean m_bShadow;
    protected ColorModel m_cm;
    protected int m_nAlphaMult;
    protected int m_nBlueMult;
    protected int m_nGreenMult;
    protected int m_nHpixels;
    protected int m_nRedMult;
    protected int m_nWpixels;
    protected int[] m_pixelsrc;
    protected Point m_ptShadow;
    private static int[] s_nBlend;
    
    static {
        FancyHelper.s_nBlend = null;
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
    
    public boolean addMask(final int[] pixelsrc) {
        if (pixelsrc.length != this.m_nHpixels * this.m_nWpixels) {
            return false;
        }
        for (int y = 0; y < this.m_nHpixels; ++y) {
            for (int index = y * this.m_nWpixels, x = 0; x < this.m_nWpixels; ++x, ++index) {
                final int[] pixelsrc2 = this.m_pixelsrc;
                final int n = index;
                pixelsrc2[n] |= 0xFF000000;
                final int[] pixelsrc3 = this.m_pixelsrc;
                final int n2 = index;
                pixelsrc3[n2] &= ((pixelsrc[index] & 0xFF) << 24 | 0xFFFFFF);
            }
        }
        return true;
    }
    
    public boolean addMask(final Image img) {
        PixelGrabber pg = null;
        final int nWpixels = img.getWidth(null);
        final int nHpixels = img.getHeight(null);
        if (this.m_nWpixels != nWpixels || this.m_nHpixels != nHpixels) {
            return false;
        }
        final int[] pixelsrc = new int[nWpixels * nHpixels];
        pg = new PixelGrabber(img, 0, 0, nWpixels, nHpixels, pixelsrc, 0, nWpixels);
        try {
            pg.grabPixels();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return this.addMask(pixelsrc);
    }
    
    public void copy(final FancyHelper helper) {
        this.m_pixelsrc = helper.m_pixelsrc;
        this.m_nWpixels = helper.m_nWpixels;
        this.m_nHpixels = helper.m_nHpixels;
        this.m_bAlphaSupport = helper.m_bAlphaSupport;
        this.m_nRedMult = helper.m_nRedMult;
        this.m_nGreenMult = helper.m_nGreenMult;
        this.m_nBlueMult = helper.m_nBlueMult;
        this.m_nAlphaMult = helper.m_nAlphaMult;
    }
    
    private static synchronized void createBlendTable() {
        if (FancyHelper.s_nBlend != null) {
            return;
        }
        FancyHelper.s_nBlend = new int[32768];
        for (int a = 0; a < 32; ++a) {
            for (int c1 = 0; c1 < 32; ++c1) {
                for (int c2 = 0; c2 < 32; ++c2) {
                    final int index = a << 10 | c1 << 5 | c2;
                    final int a2 = 31 - a;
                    final int clr = (c2 * a * 8 + c1 * a2 * 8) / 31;
                    FancyHelper.s_nBlend[index] = clr;
                }
            }
        }
    }
    
    public void draw(final int[] pixels, final int nW, final int nH, int x, int y, final Rectangle rClip, final Rectangle rSrc) {
        if (this.m_nAlphaMult == 0) {
            return;
        }
        if (this.m_bShadow) {
            this.drawShadow(pixels, nW, nH, x + this.m_ptShadow.x, y + this.m_ptShadow.y, rClip, rSrc);
        }
        int x2 = rClip.x;
        if (x2 < 0) {
            x2 = 0;
        }
        int y2 = rClip.y;
        if (y2 < 0) {
            y2 = 0;
        }
        int x3 = rClip.x + rClip.width;
        if (x3 > nW) {
            x3 = nW;
        }
        int y3 = rClip.y + rClip.height;
        if (y3 > nH) {
            y3 = nH;
        }
        int w;
        int h;
        int iSrc;
        if (rSrc == null) {
            w = this.m_nWpixels;
            h = this.m_nHpixels;
            iSrc = 0;
        }
        else {
            w = rSrc.width;
            h = rSrc.height;
            iSrc = rSrc.x + rSrc.y * this.m_nWpixels;
            if (rSrc.x + w > this.m_nWpixels) {
                w = this.m_nWpixels - rSrc.x;
            }
            if (rSrc.y + h > this.m_nHpixels) {
                h = this.m_nHpixels - rSrc.y;
            }
        }
        if (y > y3 || x > x3 || x + w < x2 || y + h < y2) {
            return;
        }
        if (x3 > x + w) {
            x3 = x + w;
        }
        if (y3 > y + h) {
            y3 = y + h;
        }
        int iDest = y * nW;
        if (y < y2) {
            iSrc += (y2 - y) * this.m_nWpixels;
            iDest = y2 * nW;
            y = y2;
        }
        y2 = y;
        if (x < x2) {
            iSrc += x2 - x;
            x = x2;
        }
        x2 = x;
        iDest += x;
        x3 -= x2;
        x2 = 0;
        final boolean bColorMult = this.m_nRedMult != 128 || this.m_nGreenMult != 128 || this.m_nBlueMult != 128;
        if (this.m_bAlphaSupport || bColorMult || this.m_nAlphaMult != 128) {
            final int[] src = this.m_pixelsrc;
            if (bColorMult) {
                int iSrcb;
                int iPixel;
                int alpha;
                int r;
                int g;
                int b;
                int pixeld;
                int rs;
                int gs;
                int bs;
                for (y = y2; y < y3; ++y) {
                    x = x3;
                    iSrcb = iSrc;
                    while (x-- > 0) {
                        iPixel = src[iSrcb++];
                        alpha = (iPixel >> 27 & this.BASEMASK);
                        r = (iPixel >> 16 & 0xFF);
                        g = (iPixel >> 8 & 0xFF);
                        b = (iPixel >> 0 & 0xFF);
                        alpha = alpha * this.m_nAlphaMult >> 7;
                        if (alpha >= 31) {
                            r = r * this.m_nRedMult >> 7;
                            if (r > 255) {
                                r = 255;
                            }
                            g = g * this.m_nGreenMult >> 7;
                            if (g > 255) {
                                g = 255;
                            }
                            b = b * this.m_nBlueMult >> 7;
                            if (b > 255) {
                                b = 255;
                            }
                            pixels[iDest] = (0xFF000000 | r << this.REDSHIFT8 | g << this.GREENSHIFT8 | b << this.BLUESHIFT8);
                        }
                        else if (alpha > 0) {
                            pixeld = pixels[iDest];
                            rs = (pixeld & this.REDMASK) >> this.REDSHIFT;
                            gs = (pixeld & this.GREENMASK) >> this.GREENSHIFT;
                            bs = (pixeld & this.BLUEMASK) >> this.BLUESHIFT;
                            alpha <<= 10;
                            r = FancyHelper.s_nBlend[alpha | (rs << 5 & 0x3E0) | r >> 3] * this.m_nRedMult >> 7;
                            if (r > 255) {
                                r = 255;
                            }
                            g = FancyHelper.s_nBlend[alpha | (gs << 5 & 0x3E0) | g >> 3] * this.m_nGreenMult >> 7;
                            if (g > 255) {
                                g = 255;
                            }
                            b = FancyHelper.s_nBlend[alpha | (bs << 5 & 0x3E0) | b >> 3] * this.m_nBlueMult >> 7;
                            if (b > 255) {
                                b = 255;
                            }
                            pixels[iDest] = (0xFF000000 | r << this.REDSHIFT8 | g << this.GREENSHIFT8 | b << this.BLUESHIFT8);
                        }
                        ++iDest;
                    }
                    iDest += nW - x3;
                    iSrc += this.m_nWpixels;
                }
            }
            else {
                int iSrcb;
                int iPixel;
                int alpha;
                int r;
                int g;
                int b;
                int pixeld;
                for (y = y2; y < y3; ++y) {
                    x = x3;
                    iSrcb = iSrc;
                    while (x-- > 0) {
                        iPixel = src[iSrcb++];
                        alpha = (iPixel >> 27 & this.BASEMASK);
                        alpha = alpha * this.m_nAlphaMult >> 7;
                        if (alpha >= this.BASEMASK) {
                            pixels[iDest] = iPixel;
                        }
                        else if (alpha > 0) {
                            r = (iPixel >> 19 & this.BASEMASK);
                            g = (iPixel >> 11 & this.BASEMASK);
                            b = (iPixel >> 3 & this.BASEMASK);
                            pixeld = pixels[iDest];
                            alpha <<= 10;
                            r = FancyHelper.s_nBlend[alpha | (pixeld >> 14 & 0x3E0) | r];
                            g = FancyHelper.s_nBlend[alpha | (pixeld >> 6 & 0x3E0) | g];
                            b = FancyHelper.s_nBlend[alpha | (pixeld << 2 & 0x3E0) | b];
                            pixels[iDest] = (0xFF000000 | r << this.REDSHIFT8 | g << this.GREENSHIFT8 | b << this.BLUESHIFT8);
                        }
                        ++iDest;
                    }
                    iDest += nW - x3;
                    iSrc += this.m_nWpixels;
                }
            }
        }
        else {
            for (y = y2; y < y3; ++y) {
                System.arraycopy(this.m_pixelsrc, iSrc, pixels, iDest, x3);
                iDest += nW;
                iSrc += this.m_nWpixels;
            }
        }
    }
    
    public void drawShadow(final int[] pixels, final int nW, final int nH, int x, int y, final Rectangle rClip, final Rectangle rSrc) {
        if (this.m_nAlphaMult == 0) {
            return;
        }
        int x2 = rClip.x;
        if (x2 < 0) {
            x2 = 0;
        }
        int y2 = rClip.y;
        if (y2 < 0) {
            y2 = 0;
        }
        int x3 = rClip.x + rClip.width;
        if (x3 > nW) {
            x3 = nW;
        }
        int y3 = rClip.y + rClip.height;
        if (y3 > nH) {
            y3 = nH;
        }
        int w;
        int h;
        int iSrc;
        if (rSrc == null) {
            w = this.m_nWpixels;
            h = this.m_nHpixels;
            iSrc = 0;
        }
        else {
            w = rSrc.width;
            h = rSrc.height;
            iSrc = rSrc.x + rSrc.y * this.m_nWpixels;
            if (rSrc.x + w > this.m_nWpixels) {
                w = this.m_nWpixels - rSrc.x;
            }
            if (rSrc.y + h > this.m_nHpixels) {
                h = this.m_nHpixels - rSrc.y;
            }
        }
        if (y > y3 || x > x3 || x + w < x2 || y + h < y2) {
            return;
        }
        if (x3 > x + w) {
            x3 = x + w;
        }
        if (y3 > y + h) {
            y3 = y + h;
        }
        int iDest = y * nW;
        if (y < y2) {
            iSrc += (y2 - y) * this.m_nWpixels;
            iDest = y2 * nW;
            y = y2;
        }
        y2 = y;
        if (x < x2) {
            iSrc += x2 - x;
            x = x2;
        }
        x2 = x;
        iDest += x;
        x3 -= x2;
        x2 = 0;
        final int[] src = this.m_pixelsrc;
        int iSrcb;
        int iPixel;
        int alpha;
        int pixeld;
        int r;
        int g;
        int b;
        for (y = y2; y < y3; ++y) {
            x = x3;
            iSrcb = iSrc;
            while (x-- > 0) {
                iPixel = src[iSrcb++];
                alpha = (iPixel >> 27 & this.BASEMASK);
                alpha = alpha * this.m_nAlphaMult >> 8;
                if (alpha > 0) {
                    pixeld = pixels[iDest];
                    r = (pixeld & this.REDMASK) >> this.REDSHIFT;
                    g = (pixeld & this.GREENMASK) >> this.GREENSHIFT;
                    b = (pixeld & this.BLUEMASK) >> this.BLUESHIFT;
                    alpha <<= 10;
                    r = FancyHelper.s_nBlend[alpha | (r << 5 & 0x3E0)];
                    g = FancyHelper.s_nBlend[alpha | (g << 5 & 0x3E0)];
                    b = FancyHelper.s_nBlend[alpha | (b << 5 & 0x3E0)];
                    pixels[iDest] = (0xFF000000 | r << this.REDSHIFT8 | g << this.GREENSHIFT8 | b << this.BLUESHIFT8);
                }
                ++iDest;
            }
            iDest += nW - x3;
            iSrc += this.m_nWpixels;
        }
    }
    
    public void fillRect(final int[] pixels, final int nW, final int nH, final int nColor, final Rectangle r) {
        int x1 = r.x;
        if (x1 < 0) {
            x1 = 0;
        }
        int y1 = r.y;
        if (y1 < 0) {
            y1 = 0;
        }
        int index = y1 * nW + x1;
        int x2 = r.x + r.width;
        if (x2 > nW) {
            x2 = nW;
        }
        x2 -= x1;
        int y2 = r.y + r.height;
        if (y2 > nH) {
            y2 = nH;
        }
        y2 -= y1;
        for (int y3 = 0; y3 < y2; ++y3) {
            for (int x3 = 0; x3 < x2; ++x3) {
                pixels[index++] = nColor;
            }
            index += nW - x2;
        }
    }
    
    public void fillRectAlpha(final int[] pixels, final int nW, final int nH, final int nColor, final Rectangle rc) {
        int x1 = rc.x;
        if (x1 < 0) {
            x1 = 0;
        }
        int y1 = rc.y;
        if (y1 < 0) {
            y1 = 0;
        }
        int index = y1 * nW + x1;
        int x2 = rc.x + rc.width;
        if (x2 > nW) {
            x2 = nW;
        }
        x2 -= x1;
        int y2 = rc.y + rc.height;
        if (y2 > nH) {
            y2 = nH;
        }
        y2 -= y1;
        final int rs = nColor >> 19 & this.BASEMASK;
        final int gs = nColor >> 11 & this.BASEMASK;
        final int bs = nColor >> 3 & this.BASEMASK;
        int alpha = nColor >> 27 & this.BASEMASK;
        alpha <<= 10;
        if (alpha == 0) {
            return;
        }
        for (int y3 = 0; y3 < y2; ++y3) {
            for (int x3 = 0; x3 < x2; ++x3) {
                final int pixeld = pixels[index];
                final int r = FancyHelper.s_nBlend[alpha | (pixeld >> 14 & 0x3E0) | rs];
                final int g = FancyHelper.s_nBlend[alpha | (pixeld >> 6 & 0x3E0) | gs];
                final int b = FancyHelper.s_nBlend[alpha | (pixeld << 2 & 0x3E0) | bs];
                pixels[index] = (0xFF000000 | r << this.REDSHIFT8 | g << this.GREENSHIFT8 | b << this.BLUESHIFT8);
                ++index;
            }
            index += nW - x2;
        }
    }
    
    public int getAlphaBalance() {
        return this.m_nAlphaMult;
    }
    
    public ColorModel getColorModel() {
        return this.m_cm;
    }
    
    public int getHeight() {
        return this.m_nHpixels;
    }
    
    public int getPixelValue(int r, int g, int b) {
        r = r * this.m_nRedMult >> 7;
        if (r > 255) {
            r = 255;
        }
        else {
            r &= 0xFF;
        }
        g = g * this.m_nGreenMult >> 7;
        if (g > 255) {
            g = 255;
        }
        else {
            g &= 0xFF;
        }
        b = b * this.m_nRedMult >> 7;
        if (b > 255) {
            b = 255;
        }
        else {
            b &= 0xFF;
        }
        return 0xFF000000 | (r << this.REDSHIFT8 & this.REDMASK) | (g << this.GREENSHIFT8 & this.GREENMASK) | (b << this.BLUESHIFT8 & this.BLUEMASK);
    }
    
    public int[] getPixels() {
        return this.m_pixelsrc;
    }
    
    public Point getShadowOffset() {
        return this.m_ptShadow;
    }
    
    public int getWidth() {
        return this.m_nWpixels;
    }
    
    public void setAlphaBalance(final int nAlphaMult) {
        this.m_nAlphaMult = nAlphaMult;
    }
    
    public void setColorBalance(final int nAlpha, final int nRed, final int nGreen, final int nBlue) {
        createBlendTable();
        this.m_nAlphaMult = nAlpha;
        this.m_nRedMult = nRed;
        this.m_nGreenMult = nGreen;
        this.m_nBlueMult = nBlue;
    }
    
    public boolean setImage(final Image img) {
        PixelGrabber pg = null;
        this.m_nWpixels = img.getWidth(null);
        this.m_nHpixels = img.getHeight(null);
        if (this.m_nWpixels == -1 || this.m_nHpixels == -1) {
            return false;
        }
        final int[] pixelsrc = new int[this.m_nWpixels * this.m_nHpixels];
        pg = new PixelGrabber(img, 0, 0, this.m_nWpixels, this.m_nHpixels, pixelsrc, 0, this.m_nWpixels);
        try {
            pg.grabPixels();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        this.m_pixelsrc = pixelsrc;
        createBlendTable();
        return true;
    }
    
    public void setPixels(final int[] pixelsrc, final int w, final int h) {
        this.m_nWpixels = w;
        this.m_nHpixels = h;
        this.m_pixelsrc = pixelsrc;
    }
    
    public boolean setShadowOffset(final int nXOffset, final int nYOffset, final boolean bShow) {
        this.m_bShadow = bShow;
        if (this.m_ptShadow != null) {
            this.m_ptShadow.setLocation(nXOffset, nYOffset);
        }
        else {
            this.m_ptShadow = new Point(nXOffset, nYOffset);
        }
        return true;
    }
}
