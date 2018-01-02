// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public class p implements Serializable
{
    Point a;
    
    public p() {
        final Point point = new Point(0, 0);
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillOval(this.a.x - 3, this.a.y - 3, 6, 6);
    }
    
    public boolean a(final Point point, final Point point2, final Point point3) {
        if (point3.x == point.x && point3.y == point.y) {
            return false;
        }
        if (point3.x == point2.x && point3.y == point2.y) {
            return false;
        }
        if (point.x == point2.x) {
            if (point.y > point2.y) {
                point.x = point2.x;
                point.y = point2.y;
                point2.x = point.x;
                point2.y = point.y;
            }
        }
        else if (point.y == point2.y) {
            if (point.x > point2.x) {
                point.x = point2.x;
                point.y = point2.y;
                point2.x = point.x;
                point2.y = point.y;
            }
        }
        final double n = point2.x - point.x;
        if (n == 0.0 && point3.x > point.x - 2 && point3.x < point.x + 2 && (point3.y > point.y && point3.y < point2.y)) {
            this.a = point3;
            return true;
        }
        if (point.y == point2.y && point3.y > point.y - 2 && point3.y < point.y + 2 && (point3.x > point.x && point3.x < point2.x)) {
            this.a = point3;
            return true;
        }
        final double n2 = (point2.y - point.y) / n;
        final double n3 = n2 * point3.x + (point.y - n2 * point.x);
        if (point.x < point2.x && (point3.y - 2 < n3 && point3.y + 2 > n3 && point3.x > point.x && point3.x < point2.x)) {
            this.a = point3;
            return true;
        }
        if (point.x > point2.x && (point3.y - 2 < n3 && point3.y + 2 > n3 && point3.x < point.x && point3.x > point2.x)) {
            this.a = point3;
            return true;
        }
        return false;
    }
}
