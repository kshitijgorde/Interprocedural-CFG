// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class Missile extends Projectile
{
    private static SuperPolygon[] missileOutline;
    private static final Rectangle[] missileOutlineBounds;
    private static double MISSILE_SPEED;
    private static double MISSILE_LIFETIME;
    private static int MISSILE_MAX_HEALTH;
    
    public Missile(final HighPrecisionPoint createLocation, final double heading, final Ship firedBy) {
        super(Missile.missileOutline, Missile.missileOutlineBounds, createLocation, heading, Missile.MISSILE_SPEED, 0.0, firedBy, Missile.MISSILE_LIFETIME, Missile.MISSILE_MAX_HEALTH);
        GameObject.asteroidsGame.asteroidsUI.soundManager.playMissileSound(this);
    }
    
    public void gameTick(final double gameTickInterval) {
        super.gameTick(gameTickInterval);
        GameObject.asteroidsGame.addGameObject(new DotObject(new HighPrecisionPoint((int)super.objectLocation.x, (int)super.objectLocation.y), Math.random(), super.objectHeading - 180, 120.0));
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        for (int i = 0; i < 30; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(new HighPrecisionPoint((int)super.objectLocation.x, (int)super.objectLocation.y), Math.random(), Math.random() * 360, Math.random() * 90));
        }
        GameObject.asteroidsGame.asteroidsUI.soundManager.stopPlayingMissileSound(this);
        GameObject.asteroidsGame.asteroidsUI.soundManager.playSmallExplosionSound();
    }
    
    protected void collideWith(final PolygonGameObject o, final int oBeforeHealthLevel) {
        for (int i = 0; i < 30; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(new HighPrecisionPoint((int)super.objectLocation.x, (int)super.objectLocation.y), Math.random(), Math.random() * 360, Math.random() * 90));
        }
        this.explode(o);
    }
    
    static {
        Missile.missileOutline = new SuperPolygon[360];
        missileOutlineBounds = new Rectangle[360];
        Missile.MISSILE_SPEED = 300.0;
        Missile.MISSILE_LIFETIME = 3.0;
        Missile.MISSILE_MAX_HEALTH = 20;
        (Missile.missileOutline[0] = new SuperPolygon()).addPoint(0, -5);
        Missile.missileOutline[0].addPoint(0, 0);
        Missile.missileOutline[0].addPoint(-1, 0);
        Missile.missileOutline[0].addPoint(1, 0);
        PolygonGameObject.setupReferenceOutlineArrays(Missile.missileOutline, Missile.missileOutlineBounds);
    }
}
