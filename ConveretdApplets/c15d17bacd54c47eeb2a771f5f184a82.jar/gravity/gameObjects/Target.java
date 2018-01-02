// 
// Decompiled by Procyon v0.5.30
// 

package gravity.gameObjects;

import gravity.tools.DoubleGraphics;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import gravity.tools.Vector2D;

public class Target extends GameObject
{
    public Target(final Vector2D vector2D, final double size) throws AssertionException {
        super(vector2D);
        super._size = size;
        Assert.assert(super._size > 0.0);
    }
    
    public void draw(final DoubleGraphics doubleGraphics) {
        doubleGraphics.setColor(super._color);
        doubleGraphics.drawOval(super._coord.x, super._coord.y, 2.0 * super._size, 2.0 * super._size);
        doubleGraphics.drawOval(super._coord.x, super._coord.y, 1.5 * super._size, 1.5 * super._size);
        doubleGraphics.drawRect(super._coord.x - super._size / 2.0, super._coord.y - super._size / 2.0, super._size, super._size);
    }
    
    public double grav() {
        return super._size * super._size * 3.141592653589793 / 72.0;
    }
    
    public void changeIntegrity(final double n) {
        super._integrity -= n;
    }
    
    public boolean isInside(final GameObject gameObject) {
        try {
            return this.distance(gameObject) <= super._size;
        }
        catch (AssertionException ex) {
            return false;
        }
    }
}
