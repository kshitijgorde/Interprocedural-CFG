// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.ColorModel;
import java.awt.image.Raster;

public class L extends a
{
    Raster \u00e8;
    
    public L(final Raster \u00e8, final ColorModel h) {
        this.\u00e8 = \u00e8;
        final int minX = \u00e8.getMinX();
        this.D = minX;
        this.E = minX;
        final int minY = \u00e8.getMinY();
        this.C = minY;
        this.K = minY;
        final int width = \u00e8.getWidth();
        this.B = width;
        this.J = width;
        final int height = \u00e8.getHeight();
        this.L = height;
        this.F = height;
        this.I = \u00e8.getSampleModel();
        this.H = h;
    }
    
    public Raster getTile(final int n, final int n2) {
        if (n != 0 || n2 != 0) {
            throw new IllegalArgumentException(m.A("SingleTileRenderedImage0"));
        }
        return this.\u00e8;
    }
}
