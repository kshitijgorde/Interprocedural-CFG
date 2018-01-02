import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class CgmDisjtLine extends CgmPrimitivePoly
{
    CgmDisjtLine(final int points) {
        super.xpoints = new double[points];
        super.ypoints = new double[points];
        super.xpoints1 = new int[points];
        super.ypoints1 = new int[points];
        super.points = points;
    }
    
    final void draw(final Graphics graphics, final double previousW, final double previousH, final boolean b) {
        if (super.LineColor != null) {
            if (super.previousW != previousW || super.previousH != previousH) {
                for (int i = 0; i < super.points; ++i) {
                    super.xpoints1[i] = (int)(super.xpoints[i] * previousW + 0.6);
                    super.ypoints1[i] = (int)(super.ypoints[i] * previousH + 0.6);
                }
                super.lw = (int)(super.LineWidth * previousW + 0.5);
                super.previousW = previousW;
                super.previousH = previousH;
            }
            final int n = super.points - 1;
            graphics.setColor(super.LineColor);
            if (super.lw <= 1) {
                for (int j = 0; j < n; ++j) {
                    graphics.drawLine(super.xpoints1[j], super.ypoints1[j], super.xpoints1[++j], super.ypoints1[j]);
                }
                return;
            }
            for (int k = 0; k < n; ++k) {
                CgmPolygon.drawLine(graphics, super.xpoints1[k], super.ypoints1[k], super.xpoints1[++k], super.ypoints1[k], super.lw);
            }
        }
    }
}
