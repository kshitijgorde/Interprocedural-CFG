// 
// Decompiled by Procyon v0.5.30
// 

package gravity.gameObjects;

import gravity.tools.DoubleGraphics;
import gravity.tools.AssertionException;
import gravity.tools.Vector2D;

public class AngleIndicator extends GameObject
{
    private double _angle;
    private double _absolute;
    private boolean _visible;
    
    public AngleIndicator(final Vector2D vector2D) throws AssertionException {
        super(vector2D);
        this._angle = 0.0;
        this._absolute = 1.0;
        this._visible = true;
    }
    
    public void draw(final DoubleGraphics doubleGraphics) {
        if (this._visible) {
            doubleGraphics.setColor(super._color);
            doubleGraphics.drawLine(super._coord.x, super._coord.y, super._coord.x + this.getVector().x / 2.0, super._coord.y + this.getVector().y / 2.0);
        }
    }
    
    public void setVisible(final boolean visible) {
        this._visible = visible;
    }
    
    public boolean isVisible() {
        return this._visible;
    }
    
    public void setAngle(final double angle) {
        this._angle = angle;
    }
    
    public double getAngle() {
        return this._angle;
    }
    
    public void setAbsolute(final double absolute) {
        this._absolute = absolute;
    }
    
    public double getAbsolute() {
        return this._absolute;
    }
    
    public Vector2D getVector() {
        final Vector2D vector2D = new Vector2D();
        vector2D.x = Math.cos(-this.angleToRadian(this._angle)) * this._absolute;
        vector2D.y = Math.sin(-this.angleToRadian(this._angle)) * this._absolute;
        return vector2D;
    }
    
    private double angleToRadian(final double n) {
        return (n % 360.0 + 360.0) % 360.0 * 3.141592653589793 / 180.0;
    }
}
