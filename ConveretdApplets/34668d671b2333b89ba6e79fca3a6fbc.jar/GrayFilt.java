import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class GrayFilt extends RGBImageFilter
{
    int cbx;
    int cby;
    int cbw;
    int cbh;
    
    public GrayFilt() {
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        if (n < this.cbx || n > this.cbx + this.cbw || n2 < this.cby || n2 > this.cby + this.cbh) {
            final int n4 = n3 & 0xFF000000;
            final int n5 = ((n3 >> 16 & 0xFF) + (n3 >> 8 & 0xFF) + (n3 & 0xFF)) / 3 * 2 / 3;
            return n4 | n5 << 16 | n5 << 8 | n5;
        }
        return n3;
    }
    
    public int setRegion(final int cbx, final int cby, final int cbw, final int cbh) {
        this.cbx = cbx;
        this.cby = cby;
        this.cbw = cbw;
        this.cbh = cbh;
        return 1;
    }
}
