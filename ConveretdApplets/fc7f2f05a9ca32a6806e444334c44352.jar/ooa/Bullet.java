// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class Bullet extends Projectile
{
    protected static final int BULLET_ENERGY_COST = 3;
    protected static final int BULLET_MAX_HEALTH = 3;
    private static final double BULLET_SPEED = 500.0;
    private static final double BULLET_LIFETIME = 1.5;
    private static SuperPolygon[] bulletOutline;
    private static Rectangle[] bulletOutlineBounds;
    
    public Bullet(final HighPrecisionPoint createLocation, final double heading, final Ship firedBy) {
        super(Bullet.bulletOutline, Bullet.bulletOutlineBounds, createLocation, heading, 500.0, 0.0, firedBy, 1.5, 3);
        GameObject.asteroidsGame.asteroidsUI.soundManager.playBulletSound();
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        for (int i = 0; i < 5; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(new HighPrecisionPoint((int)super.objectLocation.x, (int)super.objectLocation.y), Math.random(), Math.random() * 360, Math.random() * 40));
        }
    }
    
    protected void collideWith(final PolygonGameObject o, final int oBeforeHealthLevel) {
        for (int i = 0; i < 5; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(new HighPrecisionPoint((int)super.objectLocation.x, (int)super.objectLocation.y), Math.random(), Math.random() * 360, Math.random() * 40));
        }
        this.explode(o);
        GameObject.asteroidsGame.asteroidsUI.soundManager.playBulletImpactSound();
    }
    
    static {
        Bullet.bulletOutline = new SuperPolygon[360];
        Bullet.bulletOutlineBounds = new Rectangle[360];
        (Bullet.bulletOutline[0] = new SuperPolygon()).addPoint(0, -5);
        Bullet.bulletOutline[0].addPoint(0, 0);
        PolygonGameObject.setupReferenceOutlineArrays(Bullet.bulletOutline, Bullet.bulletOutlineBounds);
    }
}
