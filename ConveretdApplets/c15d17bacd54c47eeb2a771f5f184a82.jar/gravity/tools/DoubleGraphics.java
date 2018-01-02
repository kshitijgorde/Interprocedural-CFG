// 
// Decompiled by Procyon v0.5.30
// 

package gravity.tools;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;

public class DoubleGraphics
{
    private Graphics _g;
    private Vector2D _factor;
    private Vector2D _origin;
    
    public DoubleGraphics(final Graphics g, final Vector2D factor, final Vector2D origin) throws AssertionException {
        this._factor = new Vector2D(1.0, 1.0);
        this._origin = new Vector2D();
        this._g = g;
        this._factor = factor;
        this._origin = origin;
        Assert.assert(this._g != null && this._factor != null && this._origin != null);
        Assert.assert(!this._factor.equals(new Vector2D()));
    }
    
    public void drawLine(final double n, final double n2, final double n3, final double n4) {
        this._g.drawLine((int)(n * this._factor.x + this._origin.x), (int)(n2 * this._factor.y + this._origin.y), (int)(n3 * this._factor.x + this._origin.x), (int)(n4 * this._factor.y + this._origin.y));
    }
    
    public void drawOval(final double n, final double n2, final double n3, final double n4) {
        this._g.drawOval((int)((n - n3 / 2.0) * this._factor.x + this._origin.x), (int)((n2 - n4 / 2.0) * this._factor.y + this._origin.y), (int)(n3 * this._factor.x), (int)(n4 * this._factor.y));
    }
    
    public void drawRect(final double n, final double n2, final double n3, final double n4) {
        this._g.drawRect((int)(n * this._factor.x + this._origin.x), (int)(n2 * this._factor.y + this._origin.y), (int)(n3 * this._factor.x), (int)(n4 * this._factor.y));
    }
    
    public void drawRoundRect(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        this._g.drawRoundRect((int)(n * this._factor.x + this._origin.x), (int)(n2 * this._factor.y + this._origin.y), (int)(n3 * this._factor.x), (int)(n4 * this._factor.y), (int)(n5 * this._factor.x), (int)(n6 * this._factor.y));
    }
    
    public void drawVector2D(final double n, final double n2) {
        this._g.fillRect((int)(n * this._factor.x + this._origin.x), (int)(n2 * this._factor.y + this._origin.y), 1, 1);
    }
    
    public void drawPolygon(final double[] array, final double[] array2, final int n) {
        final Polygon polygon = new Polygon();
        for (int i = 0; i < n; ++i) {
            polygon.addPoint((int)(array[i] * this._factor.x + this._origin.x), (int)(array2[i] * this._factor.y + this._origin.y));
        }
        this._g.drawPolygon(polygon);
    }
    
    public void drawGravityPoint(final double n, final double n2, final double n3) {
        this._g.fillRect((int)((n - n3 / 2.0) * this._factor.x + this._origin.x), (int)((n2 - n3 / 2.0) * this._factor.y + this._origin.y), (int)(n3 * this._factor.x + 1.0), (int)(n3 * this._factor.y + 1.0));
    }
    
    public void setColor(final Color color) {
        this._g.setColor(color);
    }
}
