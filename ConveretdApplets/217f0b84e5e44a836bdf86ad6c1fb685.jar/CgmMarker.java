import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class CgmMarker extends CgmPrimitivePoly
{
    CgmMarker(final int points) {
        super.xpoints = new double[points];
        super.ypoints = new double[points];
        super.points = points;
    }
    
    final void draw(final Graphics graphics, final double n, final double n2, final boolean b) {
        if (super.LineColor != null) {
            graphics.setColor(super.LineColor);
            final int n3 = (int)Math.max(2.0, super.LineWidth * n + 0.5);
            final int n4 = n3 >> 1;
            final int n5 = n3 / 3;
            for (int i = 0; i < super.points; ++i) {
                final int n6 = (int)(super.xpoints[i] * n + 0.6);
                final int n7 = (int)(super.ypoints[i] * n2 + 0.6);
                switch (super.LineType) {
                    case 1: {
                        graphics.fillOval(n6 - n4, n7 - n4, n3, n3);
                        break;
                    }
                    case 2: {
                        graphics.drawLine(n6 - n4, n7, n6 + n4, n7);
                        graphics.drawLine(n6, n7 - n4, n6, n7 + n4);
                        break;
                    }
                    case 4: {
                        graphics.drawOval(n6 - n4, n7 - n4, n3, n3);
                        break;
                    }
                    case 5: {
                        graphics.drawLine(n6 - n4, n7 - n4, n6 + n4, n7 + n4);
                        graphics.drawLine(n6 + n4, n7 - n4, n6 - n4, n7 + n4);
                        break;
                    }
                    default: {
                        graphics.drawLine(n6 - n5, n7 - n4, n6 + n5, n7 + n4);
                        graphics.drawLine(n6 + n5, n7 - n4, n6 - n5, n7 + n4);
                        graphics.drawLine(n6 - n4, n7, n6 + n4, n7);
                        break;
                    }
                }
            }
        }
    }
}
