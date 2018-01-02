// 
// Decompiled by Procyon v0.5.30
// 

package gravity.gameObjects;

import gravity.tools.DoubleGraphics;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import java.awt.Color;
import gravity.tools.Vector2D;

public class GameObject
{
    protected Vector2D _coord;
    protected Color _color;
    protected double _size;
    protected double _integrity;
    
    public GameObject(final Vector2D coord) throws AssertionException {
        this._color = Color.white;
        this._size = 0.0;
        this._integrity = 1.0;
        this._coord = coord;
        Assert.assert(this._coord != null);
    }
    
    public void draw(final DoubleGraphics doubleGraphics) {
    }
    
    public Vector2D getCoord() {
        return (Vector2D)this._coord.clone();
    }
    
    public double getSize() {
        return this._size;
    }
    
    public double getIntegrity() {
        return this._integrity;
    }
    
    public double grav() {
        return 0.0;
    }
    
    public boolean isOnScreen(final Vector2D vector2D, final Vector2D vector2D2) {
        return this._coord.x >= vector2D.x && this._coord.x <= vector2D.x + vector2D2.x && this._coord.y >= vector2D.y && this._coord.y <= vector2D.y + vector2D2.y;
    }
    
    public double distance(final GameObject gameObject) throws AssertionException {
        Assert.assert(gameObject != null);
        final Vector2D coord = gameObject.getCoord();
        return Math.max(1.0E-10, Math.sqrt(Math.pow(this._coord.x - coord.x, 2.0) + Math.pow(this._coord.y - coord.y, 2.0)));
    }
    
    public boolean isInside(final GameObject gameObject) throws AssertionException {
        return this.distance(gameObject) <= this._size;
    }
    
    public void setColor(final Color color) {
        this._color = color;
    }
    
    public Color getColor() {
        return this._color;
    }
    
    public void changeIntegrity(final double n) {
    }
    
    public boolean equals(final GameObject gameObject) {
        return this._coord.equals(gameObject.getCoord()) && this._size == gameObject.getSize();
    }
}
