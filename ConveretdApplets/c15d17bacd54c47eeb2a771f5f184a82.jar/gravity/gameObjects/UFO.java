// 
// Decompiled by Procyon v0.5.30
// 

package gravity.gameObjects;

import gravity.tools.DoubleGraphics;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import gravity.tools.Vector2D;

public class UFO extends GameObject
{
    public UFO(final Vector2D vector2D, final double size) throws AssertionException {
        super(vector2D);
        super._size = size;
        Assert.assert(super._size > 0.0);
    }
    
    public void draw(final DoubleGraphics doubleGraphics) {
        doubleGraphics.setColor(super._color);
        doubleGraphics.drawOval(super._coord.x, super._coord.y, 2.0 * super._size, 0.8 * super._size);
        doubleGraphics.drawOval(super._coord.x, super._coord.y, super._size, 0.32 * super._size);
        doubleGraphics.drawOval(super._coord.x, super._coord.y, super._size * 3.0 / 8.0, super._size * 3.0 / 2.0);
    }
    
    public double grav() {
        return super._size * super._size * 3.141592653589793 / 72.0;
    }
    
    public void changeIntegrity(final double n) {
        super._integrity -= n;
    }
    
    public boolean isInside(final GameObject gameObject) {
        final Vector2D coord = gameObject.getCoord();
        final boolean b = coord.x >= super._coord.x - super._size && coord.x <= super._coord.x + super._size && coord.y >= super._coord.y - 0.4 * super._size && coord.y <= super._coord.y + 0.4 * super._size;
        final boolean b2 = coord.x >= super._coord.x - super._size * 3.0 / 16.0 && coord.x <= super._coord.x + super._size * 3.0 / 16.0 && coord.y >= super._coord.y - super._size * 3.0 / 4.0 && coord.y <= super._coord.y + super._size * 3.0 / 4.0;
        return b || b2;
    }
}
