import java.awt.Dimension;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class MultiFisheyeDewarp extends FisheyeDewarp
{
    protected void regular(final Rectangle bounds, final boolean front) {
        if (this.src.getPixmaps().size() == 1) {
            super.regular(bounds, front);
            return;
        }
        final Dimension srcSize = this.src.getSize();
        final Dimension dstSize = this.dst.getSize();
        final int[][] srcMap = new int[this.src.getPixmaps().size()][];
        for (int k = 0; k < this.src.getPixmaps().size(); ++k) {
            srcMap[k] = this.src.getPixmaps().elementAt(k);
        }
        final int[] dstMap = this.dst.getPixmaps().elementAt(0);
        int Hexp = ((Source)this.src).getMapHeight();
        Hexp = (int)(Math.log(Hexp) / Math.log(2.0));
        final int Hmask = (1 << Hexp) - 1;
        int dstBlkRowStart = bounds.y * dstSize.width + bounds.x;
        if (this.layout == 2 && front) {
            for (int v = 0; v < bounds.height; ++v) {
                int dstPix = dstBlkRowStart;
                for (int u = 0; u < bounds.width; ++u) {
                    dstMap[dstPix++] = -16777216;
                }
                dstBlkRowStart += dstSize.width;
            }
            return;
        }
        int srcPixStart = 0;
        if (front && this.layout == 0) {
            srcPixStart = srcSize.height / 2;
        }
        for (int v2 = 0; v2 < bounds.height; ++v2) {
            int X = this.Xo;
            int Y = this.Yo;
            int dstPix2 = dstBlkRowStart;
            for (int u2 = 0; u2 < bounds.width; ++u2) {
                try {
                    dstMap[dstPix2] = this.getWeightedPixel(srcMap, srcSize, X, Y, srcPixStart, Hmask, Hexp);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    dstMap[dstPix2] = -16777216;
                }
                ++dstPix2;
                X += this.dxdu;
                Y += this.dydu;
            }
            this.Xo += this.dxdv;
            this.Yo += this.dydv;
            this.dxdu += this.dxdudv;
            this.dydu += this.dydudv;
            dstBlkRowStart += dstSize.width;
        }
    }
    
    protected void seam(final int To, final int Tmax, final boolean Nfront) {
        if (this.src.getPixmaps().size() == 1) {
            super.seam(To, Tmax, Nfront);
            return;
        }
        final Dimension srcSize = this.src.getSize();
        final Dimension dstSize = this.dst.getSize();
        final int[][] srcMap = new int[this.src.getPixmaps().size()][];
        for (int k = 0; k < this.src.getPixmaps().size(); ++k) {
            srcMap[k] = this.src.getPixmaps().elementAt(k);
        }
        final int[] dstMap = this.dst.getPixmaps().elementAt(0);
        int Hexp = ((Source)this.src).getMapHeight();
        Hexp = (int)(Math.log(Hexp) / Math.log(2.0));
        final int Hmask = (1 << Hexp) - 1;
        int front = 0;
        final int back = 0;
        if (this.layout == 0) {
            front = srcSize.height / 2;
        }
        boolean Nblank = false;
        boolean Pblank = false;
        if (this.layout == 2) {
            Nblank = Nfront;
            Pblank = !Nfront;
        }
        final int Nsrc = Nfront ? front : back;
        final int Psrc = Nfront ? back : front;
        final int dT = this.Orientation ? 1 : dstSize.width;
        final int dS = this.Orientation ? dstSize.width : 1;
        final int Smax = this.Orientation ? dstSize.height : dstSize.width;
        final int j = To >> this.N;
        this.deltas(this.sp[j], this.so[j], this.sp[j + 1], this.so[j + 1], this.N + 1, this.N);
        for (int t = To; t < Tmax; ++t) {
            final int so = Math.max(this.seam[t] - this.GS - 1 & ~(this.GS - 1), 0);
            final int Se = Math.min(this.seam[t], Smax - 1);
            int dstPix = t * dT + so * dS;
            if (Nblank) {
                for (int s = so; s <= Se; ++s) {
                    dstMap[dstPix] = -16777216;
                    dstPix += dS;
                }
            }
            else {
                final int ds = this.seamSpacing - (this.seam[t] - so);
                int X = this.Xo + ds * this.dxdu;
                int Y = this.Yo + ds * this.dydu;
                for (int s2 = so; s2 <= Se; ++s2) {
                    try {
                        dstMap[dstPix] = this.getWeightedPixel(srcMap, srcSize, X, Y, Nsrc, Hmask, Hexp);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        dstMap[dstPix] = -16777216;
                    }
                    dstPix += dS;
                    X += this.dxdu;
                    Y += this.dydu;
                }
            }
            this.Xo += this.dxdv;
            this.Yo += this.dydv;
            this.dxdu += this.dxdudv;
            this.dydu += this.dydudv;
        }
        this.deltas(this.sq[j], this.so[j], this.sq[j + 1], this.so[j + 1], this.N + 1, this.N);
        for (int t = To; t < Tmax; ++t) {
            final int so = Math.min(this.seam[t] + this.seamSpacing & ~(this.GS - 1), Smax) - 1;
            final int Se = Math.max(this.seam[t] + 1, 0);
            int dstPix = t * dT + so * dS;
            if (Pblank) {
                for (int s = so; s >= Se; --s) {
                    dstMap[dstPix] = -16777216;
                    dstPix -= dS;
                }
            }
            else {
                final int ds = this.seamSpacing + (this.seam[t] - so);
                int X = this.Xo + ds * this.dxdu;
                int Y = this.Yo + ds * this.dydu;
                for (int s2 = so; s2 >= Se; --s2) {
                    try {
                        dstMap[dstPix] = this.getWeightedPixel(srcMap, srcSize, X, Y, Psrc, Hmask, Hexp);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        dstMap[dstPix] = -16777216;
                    }
                    dstPix -= dS;
                    X += this.dxdu;
                    Y += this.dydu;
                }
            }
            this.Xo += this.dxdv;
            this.Yo += this.dydv;
            this.dxdu += this.dxdudv;
            this.dydu += this.dydudv;
        }
    }
    
    protected int getWeightedPixel(final int[][] srcMap, final Dimension srcSize, final int X, final int Y, final int srcPixelOffset, final int Hmask, final int Hexp) {
        if (this.mVelocity > 20) {
            final int y = srcPixelOffset + (Y >>> 12);
            final int srcPix = (y & Hmask) * srcSize.width + (X >>> 12);
            return srcMap[y >> Hexp][srcPix];
        }
        int y = srcPixelOffset + (Y >>> 12);
        final int nFloorX = X >>> 12;
        int srcPix2 = (y & Hmask) * srcSize.width + nFloorX;
        int yIndex = y >> Hexp;
        final int Pixel00 = srcMap[yIndex][srcPix2];
        final int Pixel2 = srcMap[yIndex][srcPix2 + 1];
        srcPix2 = (++y & Hmask) * srcSize.width + nFloorX;
        yIndex = y >> Hexp;
        final int Pixel3 = srcMap[yIndex][srcPix2];
        final int Pixel4 = srcMap[yIndex][srcPix2 + 1];
        return this.average(Pixel00, Pixel2, Pixel3, Pixel4, X, Y);
    }
}
