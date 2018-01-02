// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public abstract class Asteroid extends PolygonGameObject
{
    protected long asteroidPoints;
    
    Asteroid(final SuperPolygon[] asteroidOutline, final Rectangle[] asteroidOutlineBounds, final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed) {
        super(asteroidOutline, asteroidOutlineBounds, createLocation, createHeading, createSpeed, createRotationalSpeed);
        super.damageDisplayTimer = 0L;
    }
    
    protected void checkCollision(final PolygonGameObject o) {
        if (o instanceof Projectile || o instanceof Ship || o instanceof Powerup) {
            super.checkCollision(o);
        }
    }
    
    protected void collideWith(final PolygonGameObject o, final int oHealthLevel) {
        super.collideWith(o, oHealthLevel);
        if (super.isAlive) {
            super.damageDisplayTimer = System.currentTimeMillis() + 1000;
        }
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        if (destroyingObject instanceof PlayerShip || (destroyingObject instanceof Projectile && ((Projectile)destroyingObject).shipFiredBy instanceof PlayerShip)) {
            final AsteroidsGame asteroidsGame = GameObject.asteroidsGame;
            asteroidsGame.playerScore += this.asteroidPoints;
        }
    }
}
