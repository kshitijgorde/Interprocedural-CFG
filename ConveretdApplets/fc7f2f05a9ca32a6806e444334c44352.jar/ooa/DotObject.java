// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Color;

public class DotObject extends GameObject
{
    protected double timeToLive;
    
    public DotObject(final HighPrecisionPoint createLocation, final double lifeTime, final double heading, final double speed) {
        super(createLocation, heading, speed, 0.0);
        this.timeToLive = lifeTime;
    }
    
    public void gameTick(final double gameTickInterval) {
        this.timeToLive -= gameTickInterval;
        if (this.timeToLive <= 0) {
            this.explode(null);
        }
        else {
            super.updatePosition(gameTickInterval);
        }
    }
    
    public void draw() {
        GameObject.asteroidsGame.gameScreenGraphics.setColor(Color.white);
        GameObject.asteroidsGame.gameScreenGraphics.drawLine((int)super.objectLocation.x, (int)super.objectLocation.y, (int)super.objectLocation.x, (int)super.objectLocation.y);
    }
}
