import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class CgmOval extends CgmPrimitive
{
    CgmOval(final double x, final double y, final double n, final double n2) {
        super.x = x;
        super.y = y;
        super.Width = Math.abs(n);
        super.Height = Math.abs(n2);
    }
    
    final void draw(final Graphics graphics, final double n, final double n2, final boolean b) {
        final int n3 = (int)(super.x * n + 0.6);
        final int n4 = (int)(super.y * n2 + 0.6);
        final int n5 = (int)(super.Width * n + 0.6);
        final int n6 = (int)(super.Height * n2 + 0.6);
        if (b && super.IntStyle >= 1) {
            graphics.setColor(super.FillColor);
            graphics.fillOval(n3 - n5, n4 - n6, n5 << 1, n6 << 1);
        }
        if (super.LineColor != null) {
            graphics.setColor(super.LineColor);
            final int n7 = (int)(super.LineWidth * n + 0.5);
            if (n7 <= 1) {
                graphics.drawOval(n3 - n5, n4 - n6, n5 << 1, n6 << 1);
                return;
            }
            int n8 = (n5 << 1) + n7;
            int n9 = (n6 << 1) + n7;
            int n10 = n3 - (n8 >> 1);
            int n11 = n4 - (n9 >> 1);
            for (int i = 1; i <= n7; ++i) {
                graphics.drawOval(n10, n11, n8, n9);
                if (i < n7) {
                    graphics.drawOval(n10, n11, --n8, --n9);
                    graphics.drawOval(n10 + 1, n11, n8, n9);
                    graphics.drawOval(n10, ++n11, n8, n9);
                    graphics.drawOval(++n10, n11, n8--, n9--);
                }
            }
        }
    }
    
    final boolean find(final double n, final double n2) {
        if (super.visibility == 0 || super.IntStyle < 1) {
            return false;
        }
        final double n3 = super.Width / 2.0;
        final double n4 = super.Height / 2.0;
        final double n5 = (n - super.x - n3) / n3;
        final double n6 = (n2 - super.y - n4) / n4;
        return n5 * n5 + n6 * n6 <= 1.0;
    }
}
