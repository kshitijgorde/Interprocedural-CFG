// 
// Decompiled by Procyon v0.5.30
// 

package gravity.gameObjects;

import gravity.tools.DoubleGraphics;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import gravity.tools.Vector2D;
import java.awt.Color;

public class Planet extends GameObject
{
    private double _atmosphereSize;
    private Color _atmosphereColor;
    private Color _scanningAtmosphereColor;
    private boolean _isInside;
    
    public Planet(final Vector2D vector2D, final double size) throws AssertionException {
        super(vector2D);
        this._atmosphereSize = 0.0;
        this._isInside = false;
        super._size = size;
        this._atmosphereColor = super._color;
        this._scanningAtmosphereColor = super._color;
        Assert.assert(super._size > 0.0);
    }
    
    public void draw(final DoubleGraphics doubleGraphics) {
        if (this._isInside) {
            doubleGraphics.setColor(this._scanningAtmosphereColor);
        }
        else {
            doubleGraphics.setColor(this._atmosphereColor);
        }
        doubleGraphics.drawOval(super._coord.x, super._coord.y, 2.0 * super._size + 2.0 * this._atmosphereSize, 2.0 * super._size + 2.0 * this._atmosphereSize);
        doubleGraphics.setColor(super._color);
        doubleGraphics.drawOval(super._coord.x, super._coord.y, 2.0 * super._size, 2.0 * super._size);
    }
    
    public double grav() {
        return super._size * super._size * 3.141592653589793;
    }
    
    public double getAtmosphereSize() {
        return this._atmosphereSize;
    }
    
    public boolean isInsideAtmosphere(final GameObject gameObject) throws AssertionException {
        return this.distance(gameObject) <= super._size + this._atmosphereSize;
    }
    
    public void setAtmosphereSize(final double atmosphereSize) throws AssertionException {
        this._atmosphereSize = atmosphereSize;
        Assert.assert(this._atmosphereSize >= 0.0);
    }
    
    public void setInsideAtmosphere(final boolean isInside) {
        this._isInside = isInside;
    }
    
    public void setAtmosphereColors(final Color atmosphereColor, final Color scanningAtmosphereColor) {
        this._atmosphereColor = atmosphereColor;
        this._scanningAtmosphereColor = scanningAtmosphereColor;
    }
}
