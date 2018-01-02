// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class Projectile extends PolygonGameObject
{
    protected Ship shipFiredBy;
    private double timeToLive;
    
    public Projectile(final SuperPolygon[] projectileOutline, final Rectangle[] projectileOutlineBounds, final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed, final Ship firedBy, final double projectileLifetime, final int projectileDamage) {
        super(projectileOutline, projectileOutlineBounds, createLocation, createHeading, createSpeed, 0.0);
        this.timeToLive = projectileLifetime;
        super.healthLevel = projectileDamage;
        super.maxHealthLevel = projectileDamage;
        this.shipFiredBy = firedBy;
        if (this.shipFiredBy instanceof PlayerShip) {
            final AsteroidsGame asteroidsGame = GameObject.asteroidsGame;
            ++asteroidsGame.projectilesFired;
        }
    }
    
    public void gameTick(final double gameTickInterval) {
        this.timeToLive -= gameTickInterval;
        if (this.timeToLive <= 0) {
            this.explode(null);
            if (this.shipFiredBy instanceof PlayerShip) {
                final AsteroidsGame asteroidsGame = GameObject.asteroidsGame;
                ++asteroidsGame.projectilesMissed;
            }
        }
        else {
            super.updatePosition(gameTickInterval);
        }
    }
    
    protected void checkCollision(final PolygonGameObject o) {
        if (o instanceof Asteroid || o instanceof Ship || (o instanceof Powerup && ((Powerup)o).powerupAvailable) || o instanceof Projectile) {
            super.checkCollision(o);
        }
    }
}
