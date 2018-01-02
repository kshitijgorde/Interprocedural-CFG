// 
// Decompiled by Procyon v0.5.30
// 

package gravity.gameObjects;

import gravity.tools.DoubleGraphics;
import gravity.tools.AssertionException;
import gravity.tools.Vector2D;

public class TraceDot extends GameObject
{
    public TraceDot(final Vector2D vector2D) throws AssertionException {
        super(vector2D);
    }
    
    public void draw(final DoubleGraphics doubleGraphics) {
        doubleGraphics.setColor(super._color);
        doubleGraphics.drawVector2D(super._coord.x, super._coord.y);
    }
}
