// 
// Decompiled by Procyon v0.5.30
// 

package util;

public class ImageBlot
{
    public double fgrad;
    public double bgrad;
    
    public ImageBlot(final double fgrad, final double bgrad) {
        this.fgrad = fgrad;
        this.bgrad = bgrad;
        if (this.bgrad == 0.0) {
            this.bgrad = this.fgrad;
        }
    }
    
    public ImageBlot(final int n, final int n2) {
        this(n, (double)n2);
    }
    
    public float applyToPixel(final float n, final double n2, final double n3, final double n4, final double n5) {
        final double sqrt = Math.sqrt((n2 - n4) * (n2 - n4) + (n3 - n5) * (n3 - n5));
        if (sqrt <= this.fgrad) {
            return 1.0f;
        }
        if (sqrt >= this.bgrad) {
            return n;
        }
        final float n6 = 1.0f - (float)((sqrt - this.fgrad) / (this.bgrad - this.fgrad));
        return (n > n6) ? n : n6;
    }
}
