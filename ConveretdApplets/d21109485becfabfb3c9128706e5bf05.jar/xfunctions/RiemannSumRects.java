// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import xfunctions.graphs.CoordinateRect;
import java.awt.Graphics;
import java.awt.Color;
import xfunctions.graphs.Drawable;

class RiemannSumRects extends Drawable
{
    private double[] rectHeights;
    private int method;
    Color color;
    
    void setRectHeights(final double[] rectHeights) {
        this.rectHeights = rectHeights;
    }
    
    void setMethod(final int method) {
        this.method = method;
    }
    
    public void draw(final Graphics graphics, final CoordinateRect coordinateRect) {
        if (this.rectHeights == null) {
            return;
        }
        graphics.setColor(this.color);
        final int n = (this.method == 5 || this.method == 0 || this.method == 1) ? (this.rectHeights.length - 1) : this.rectHeights.length;
        double xmin = coordinateRect.getXmin();
        final double n2 = (coordinateRect.getXmax() - xmin) / n;
        final int yToPixel = coordinateRect.yToPixel(0.0);
        if (this.method == 5) {
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            array[1] = coordinateRect.xToPixel(xmin);
            array2[0] = (array2[1] = yToPixel);
            array2[2] = coordinateRect.yToPixel(this.rectHeights[0]);
            for (int i = 0; i < n; ++i) {
                xmin += n2;
                array[0] = (array[3] = array[1]);
                array[1] = (array[2] = coordinateRect.xToPixel(xmin));
                array2[3] = array2[2];
                array2[2] = coordinateRect.yToPixel(this.rectHeights[i + 1]);
                graphics.fillPolygon(array, array2, 4);
            }
        }
        else {
            int xToPixel = coordinateRect.xToPixel(xmin);
            for (int j = 0; j < n; ++j) {
                final int xToPixel2 = coordinateRect.xToPixel(xmin + n2);
                final int n3 = xToPixel2 - xToPixel + 1;
                final int yToPixel2 = coordinateRect.yToPixel(this.rectHeights[(this.method == 1) ? (j + 1) : j]);
                final int n4 = yToPixel - yToPixel2;
                if (n4 > 0) {
                    graphics.fillRect(xToPixel, yToPixel2, n3, n4);
                }
                else if (n4 == 0) {
                    graphics.drawLine(xToPixel, yToPixel, xToPixel + n3 - 1, yToPixel);
                }
                else {
                    graphics.fillRect(xToPixel, yToPixel, n3, -n4);
                }
                xmin += n2;
                xToPixel = xToPixel2;
            }
        }
        graphics.setColor(Color.black);
    }
    
    public void reset() {
    }
    
    RiemannSumRects() {
        this.color = new Color(255, 255, 150);
    }
}
