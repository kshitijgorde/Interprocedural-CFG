// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

public class $V_ extends Polygon implements Cloneable
{
    public Color $K5;
    public Color fillColor;
    
    public void $J1(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Color color = graphics.getColor();
        if (this.fillColor != null) {
            graphics.setColor(this.fillColor);
            this.$Q5(graphics, n, n2, n3, n4);
        }
        if (this.$K5 != null) {
            graphics.setColor(this.$K5);
            this.drawPoly(graphics, n, n2, n3, n4);
        }
        graphics.setColor(color);
    }
    
    static int[] $L5(final int[] array, int n) {
        final int n2 = array.length / 2;
        final int[] array2 = new int[n2];
        for (int i = 0; i < n2; ++i) {
            array2[i] = array[n];
            n += 2;
        }
        return array2;
    }
    
    static int[] $M5(final int[] array) {
        final int length = array.length;
        final int[] array2 = new int[length];
        System.arraycopy(array, 0, array2, 0, length);
        return array2;
    }
    
    public void $O5() {
        final int height = this.getBounds().height;
        int npoints = super.npoints;
        while (npoints-- != 0) {
            super.ypoints[npoints] = height - super.ypoints[npoints];
        }
    }
    
    public void $P5() {
        final int width = this.getBounds().width;
        int npoints = super.npoints;
        while (npoints-- != 0) {
            super.xpoints[npoints] = width - super.xpoints[npoints];
        }
    }
    
    public void $Q5(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Rectangle bounds = this.getBounds();
        if (n3 == bounds.width && n4 == bounds.height) {
            graphics.translate(-bounds.x + n, -bounds.y + n2);
            graphics.fillPolygon(this);
            graphics.translate(bounds.x - n, bounds.y - n2);
        }
        else {
            graphics.translate(n, n2);
            graphics.fillPolygon($R5(this, n3, n4));
            graphics.translate(-n, -n2);
        }
    }
    
    public static $V_ $R5(final Polygon polygon, final int n, final int n2) {
        final Rectangle bounds = polygon.getBounds();
        final float n3 = n / bounds.width;
        final float n4 = n2 / bounds.height;
        final int x = bounds.x;
        final int y = bounds.y;
        int npoints = polygon.npoints;
        final int[] array = new int[npoints];
        final int[] array2 = new int[npoints];
        while (npoints-- != 0) {
            array[npoints] = (int)((polygon.xpoints[npoints] - x) * n3);
            array2[npoints] = (int)((polygon.ypoints[npoints] - y) * n4);
        }
        return new $V_(array, array2, polygon.npoints);
    }
    
    public $V_(final Polygon polygon) {
        super($M5(polygon.xpoints), $M5(polygon.ypoints), polygon.npoints);
        this.$K5 = Color.black;
        this.fillColor = Color.white;
    }
    
    public $V_(final Rectangle rectangle) {
        this.$K5 = Color.black;
        this.fillColor = Color.white;
        final int n = rectangle.x + rectangle.width - 1;
        final int n2 = rectangle.y + rectangle.height - 1;
        this.addPoint(rectangle.x, rectangle.y);
        this.addPoint(n, rectangle.y);
        this.addPoint(n, n2);
        this.addPoint(rectangle.x, n2);
    }
    
    public $V_(final int[] array) {
        super($L5(array, 0), $L5(array, 1), array.length / 2);
        this.$K5 = Color.black;
        this.fillColor = Color.white;
    }
    
    public $V_(final int[] array, final int[] array2, final int n) {
        super(array, array2, n);
        this.$K5 = Color.black;
        this.fillColor = Color.white;
    }
    
    public Object clone() {
        return new $V_(this);
    }
    
    public void drawPoly(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Rectangle bounds = this.getBounds();
        if (n3 - 1 == bounds.width && n4 - 1 == bounds.height) {
            graphics.translate(-bounds.x + n, -bounds.y + n2);
            graphics.drawPolygon(this);
            graphics.translate(bounds.x - n, bounds.y - n2);
        }
        else {
            graphics.translate(n, n2);
            graphics.drawPolygon($R5(this, n3 - 1, n4 - 1));
            graphics.translate(-n, -n2);
        }
    }
}
