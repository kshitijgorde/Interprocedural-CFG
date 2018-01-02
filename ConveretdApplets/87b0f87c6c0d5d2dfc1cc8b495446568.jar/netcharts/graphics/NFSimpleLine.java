// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFUtil;
import java.awt.Polygon;
import java.awt.Graphics;

public class NFSimpleLine
{
    private static int a(int n, final double n2) {
        if (n > 0 && n2 > 0.0) {
            n *= (int)n2;
            if (n < 1) {
                n = 1;
            }
        }
        return n;
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, int n5, final double n6, final Polygon polygon) {
        if (n5 < 1) {
            n5 = 1;
        }
        final int a = a(n5, n6);
        if (NFUtil.getJDKVersion() >= 1.2) {
            NF12GraphicsUtil.drawLine(graphics, n, n2, n3, n4, a);
            if (polygon != null) {
                polygon.npoints = 0;
            }
            return;
        }
        if (a <= 1) {
            graphics.drawLine(n, n2, n3, n4);
            return;
        }
        final double n7 = a / Math.sqrt((n - n3) * (n - n3) + (n2 - n4) * (n2 - n4));
        final double n8 = n7 * (n2 - n4);
        final double n9 = n7 * (n - n3);
        int n10 = (int)NFUtil.rint((n8 > 0.0) ? (n8 + 0.01) : (n8 - 0.01));
        int n11 = (int)NFUtil.rint((n9 > 0.0) ? (n9 + 0.01) : (n9 - 0.01));
        if (Math.abs(Math.abs(n8) + Math.abs(n9) - (Math.abs(n10) + Math.abs(n11))) >= 0.5) {
            if (Math.abs(n8 - n10) > Math.abs(n9 - n11)) {
                n10 += ((n8 - n10 > 0.0) ? 1 : -1);
            }
            else {
                n11 += ((n9 - n11 > 0.0) ? 1 : -1);
            }
        }
        int n12 = 0;
        int n13 = 0;
        if (n10 < 0 && -n10 % 2 == 1) {
            n12 = -1;
        }
        if (n10 > 0 && n10 % 2 == 1) {
            n12 = 1;
        }
        final int n14 = n10 / 2;
        if (n11 < 0 && -n11 % 2 == 1) {
            n13 = -1;
        }
        if (n11 > 0 && n11 % 2 == 1) {
            n13 = 1;
        }
        final int n15 = n11 / 2;
        final int[] xpoints = new int[4];
        final int[] ypoints = new int[4];
        xpoints[0] = n + n14 + n12;
        ypoints[0] = n2 - n15 - n13;
        xpoints[1] = n - n14;
        ypoints[1] = n2 + n15;
        xpoints[2] = n3 - n14;
        ypoints[2] = n4 + n15;
        xpoints[3] = n3 + n14 + n12;
        ypoints[3] = n4 - n15 - n13;
        graphics.fillPolygon(xpoints, ypoints, 4);
        if (polygon != null) {
            polygon.xpoints = xpoints;
            polygon.ypoints = ypoints;
            polygon.npoints = 4;
        }
    }
}
