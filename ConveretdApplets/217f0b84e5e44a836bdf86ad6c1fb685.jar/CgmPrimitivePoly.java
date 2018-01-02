import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class CgmPrimitivePoly extends CgmPrimitive
{
    double[] xpoints;
    double[] ypoints;
    int[] xpoints1;
    int[] ypoints1;
    int points;
    int lw;
    double previousW;
    double previousH;
    
    CgmPrimitivePoly() {
        this.lw = 1;
        this.previousW = 0.0;
        this.previousH = 0.0;
    }
    
    void draw(final Graphics graphics, final double n, final double n2) throws AbstractMethodError {
    }
    
    void move(final double n, final double n2) {
        super.x += n;
        super.y += n2;
        for (int i = 0; i < this.points; ++i) {
            final double[] xpoints = this.xpoints;
            final int n3 = i;
            xpoints[n3] += n;
            final double[] ypoints = this.ypoints;
            final int n4 = i;
            ypoints[n4] += n2;
        }
        this.previousW = 0.0;
    }
}
