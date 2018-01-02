// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.label;

import java.awt.image.DirectColorModel;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

class RotateFilter extends ImageFilter
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
    
    public void transform(final double x, final double y, final double[] retcoord) {
        retcoord[0] = this.cos * x + this.sin * y;
        retcoord[1] = this.cos * y - this.sin * x;
    }
    
    public void itransform(final double x, final double y, final double[] retcoord) {
        retcoord[0] = this.cos * x - this.sin * y;
        retcoord[1] = this.cos * y + this.sin * x;
    }
    
    public void transformBBox(final Rectangle rect) {
        double minx = Double.POSITIVE_INFINITY;
        double miny = Double.POSITIVE_INFINITY;
        double maxx = Double.NEGATIVE_INFINITY;
        double maxy = Double.NEGATIVE_INFINITY;
        for (int y = 0; y <= 1; ++y) {
            for (int x = 0; x <= 1; ++x) {
                this.transform(rect.x + x * rect.width, rect.y + y * rect.height, this.coord);
                minx = Math.min(minx, this.coord[0]);
                miny = Math.min(miny, this.coord[1]);
                maxx = Math.max(maxx, this.coord[0]);
                maxy = Math.max(maxy, this.coord[1]);
            }
        }
        rect.x = (int)Math.floor(minx);
        rect.y = (int)Math.floor(miny);
        rect.width = (int)Math.ceil(maxx) - rect.x + 1;
        rect.height = (int)Math.ceil(maxy) - rect.y + 1;
    }
    
    public void setDimensions(final int width, final int height) {
        final Rectangle rect = new Rectangle(0, 0, width, height);
        this.transformBBox(rect);
        this.xoffset = -rect.x;
        this.yoffset = -rect.y;
        this.srcW = width;
        this.srcH = height;
        this.dstW = rect.width;
        this.dstH = rect.height;
        this.raster = new int[this.srcW * this.srcH];
        super.consumer.setDimensions(this.dstW, this.dstH);
    }
    
    public void setColorModel(final ColorModel model) {
        super.consumer.setColorModel(RotateFilter.defaultRGB);
    }
    
    public void setHints(final int hintflags) {
        super.consumer.setHints(0xE | (hintflags & 0x10));
    }
    
    public void setPixels(final int x, final int y, final int w, final int h, final ColorModel model, final byte[] pixels, final int off, final int scansize) {
        int srcoff = off;
        int dstoff = y * this.srcW + x;
        for (int yc = 0; yc < h; ++yc) {
            for (int xc = 0; xc < w; ++xc) {
                this.raster[dstoff++] = model.getRGB(pixels[srcoff++] & 0xFF);
            }
            srcoff += scansize - w;
            dstoff += this.srcW - w;
        }
    }
    
    public void setPixels(final int x, final int y, final int w, final int h, final ColorModel model, final int[] pixels, final int off, final int scansize) {
        int srcoff = off;
        int dstoff = y * this.srcW + x;
        if (model == RotateFilter.defaultRGB) {
            for (int yc = 0; yc < h; ++yc) {
                System.arraycopy(pixels, srcoff, this.raster, dstoff, w);
                srcoff += scansize;
                dstoff += this.srcW;
            }
        }
        else {
            for (int yc = 0; yc < h; ++yc) {
                for (int xc = 0; xc < w; ++xc) {
                    this.raster[dstoff++] = model.getRGB(pixels[srcoff++]);
                }
                srcoff += scansize - w;
                dstoff += this.srcW - w;
            }
        }
    }
    
    public void imageComplete(final int status) {
        if (status == 1 || status == 4) {
            super.consumer.imageComplete(status);
            return;
        }
        final int[] pixels = new int[this.dstW];
        for (int dy = 0; dy < this.dstH; ++dy) {
            this.itransform(0 - this.xoffset, dy - this.yoffset, this.coord);
            double x1 = this.coord[0];
            double y1 = this.coord[1];
            this.itransform(this.dstW - this.xoffset, dy - this.yoffset, this.coord);
            final double x2 = this.coord[0];
            final double y2 = this.coord[1];
            final double xinc = (x2 - x1) / this.dstW;
            final double yinc = (y2 - y1) / this.dstW;
            for (int dx = 0; dx < this.dstW; ++dx) {
                final int sx = (int)Math.round(x1);
                final int sy = (int)Math.round(y1);
                if (sx < 0 || sy < 0 || sx >= this.srcW || sy >= this.srcH) {
                    pixels[dx] = 0;
                }
                else {
                    pixels[dx] = this.raster[sy * this.srcW + sx];
                }
                x1 += xinc;
                y1 += yinc;
            }
            super.consumer.setPixels(0, dy, this.dstW, 1, RotateFilter.defaultRGB, pixels, 0, this.dstW);
        }
        super.consumer.imageComplete(status);
    }
    
    public ColorModel fixColorModel(final ColorModel colorModel) {
        if (colorModel.getPixelSize() == 24) {
            return new DirectColorModel(32, 65280, 16711680, -16777216);
        }
        return colorModel;
    }
    
    static {
        RotateFilter.defaultRGB = ColorModel.getRGBdefault();
    }
}
