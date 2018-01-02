import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class vb implements pb
{
    public int a(final Object o, final Object o2) {
        final int u = a.u;
        final Point point = (Point)o;
        final Point point2 = (Point)o2;
        int n = 1;
        if (point.x < point2.x) {
            n = -1;
            if (u == 0) {
                return n;
            }
        }
        if (point.x == point2.x) {
            if (point.y < point2.y) {
                n = -1;
                if (u == 0) {
                    return n;
                }
            }
            if (point.y == point2.y) {
                n = 0;
                if (u == 0) {
                    return n;
                }
            }
            n = 1;
        }
        return n;
    }
}
