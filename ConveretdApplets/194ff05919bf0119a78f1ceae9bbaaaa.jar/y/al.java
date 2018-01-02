// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Polygon;

public final class al
{
    private int a;
    
    public al(final int a) {
        this.a = a;
    }
    
    final void a(final byte[] array, int i, final int n) {
        while (i < n) {
            this.a *= 83;
            final int n2 = i;
            array[n2] ^= (byte)this.a;
            ++i;
        }
    }
    
    public al() {
    }
    
    public static Polygon a(final int n, final int n2, final int n3, final int n4, final int n5) {
        final Polygon polygon;
        (polygon = new Polygon()).addPoint(23, 0);
        polygon.addPoint(45, 17);
        polygon.addPoint(38, 17);
        polygon.addPoint(38, 35);
        polygon.addPoint(7, 35);
        polygon.addPoint(7, 17);
        polygon.addPoint(0, 17);
        polygon.addPoint(23, 0);
        final Polygon polygon2 = new Polygon();
        for (int i = 0; i < polygon.npoints; ++i) {
            switch (n5) {
                case 0: {
                    polygon2.addPoint(n + polygon.xpoints[i], n2 + polygon.ypoints[i]);
                    break;
                }
                case 2: {
                    polygon2.addPoint(n + polygon.xpoints[i], n2 + 36 - polygon.ypoints[i] - 1);
                    break;
                }
                case 3: {
                    polygon2.addPoint(n + polygon.ypoints[i], n2 + 46 - polygon.xpoints[i] - 1);
                    break;
                }
                default: {
                    polygon2.addPoint(n + 36 - polygon.ypoints[i] - 1, n2 + polygon.xpoints[i]);
                    break;
                }
            }
        }
        return polygon2;
    }
}
