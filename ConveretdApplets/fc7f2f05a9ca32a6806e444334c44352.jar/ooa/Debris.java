// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class Debris extends PolygonGameObject
{
    protected double timeToLive;
    private static SuperPolygon[] debrisOutline;
    private static Rectangle[] debrisOutlineBounds;
    
    public Debris(final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed, final double lifetime) {
        super(Debris.debrisOutline, Debris.debrisOutlineBounds, createLocation, createHeading, createSpeed, createRotationalSpeed);
        this.timeToLive = lifetime;
        super.healthLevel = 1;
    }
    
    public void gameTick(final double gameTickInterval) {
        this.timeToLive -= gameTickInterval;
        if (this.timeToLive <= 0) {
            super.isAlive = false;
        }
        super.updatePosition(gameTickInterval);
    }
    
    protected void checkCollision(final PolygonGameObject o) {
        if (o instanceof Ship) {
            super.checkCollision(o);
        }
    }
    
    protected void collideWith(final PolygonGameObject o, final int oBeforeHealthLevel) {
        this.explode(o);
        GameObject.asteroidsGame.asteroidsUI.soundManager.playBulletImpactSound();
    }
    
    static {
        Debris.debrisOutline = new SuperPolygon[360];
        Debris.debrisOutlineBounds = new Rectangle[360];
        (Debris.debrisOutline[0] = new SuperPolygon()).addPoint(0, -1);
        Debris.debrisOutline[0].addPoint(1, 1);
        Debris.debrisOutline[0].addPoint(-1, 1);
        PolygonGameObject.setupReferenceOutlineArrays(Debris.debrisOutline, Debris.debrisOutlineBounds);
    }
}
