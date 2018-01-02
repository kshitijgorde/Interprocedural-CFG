// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class SmallAsteroid extends Asteroid
{
    static final int SMALL_ASTEROID_MAX_HEALTH = 6;
    static final int SMALL_ASTEROID_POINTS = 50;
    private static SuperPolygon[] smallAsteroidOutline;
    private static Rectangle[] smallAsteroidOutlineBounds;
    
    public SmallAsteroid(final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed) {
        super(SmallAsteroid.smallAsteroidOutline, SmallAsteroid.smallAsteroidOutlineBounds, createLocation, createHeading, createSpeed, createRotationalSpeed);
        super.healthLevel = 6;
        super.maxHealthLevel = 6;
        super.asteroidPoints = 50L;
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        super.isAlive = false;
        for (int i = 0; i < 25; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(super.objectLocation, Math.random() * 2, Math.random() * 360, Math.random() * 90));
        }
        for (int i = 0; i < 4; ++i) {
            GameObject.asteroidsGame.addGameObject(new Debris(super.objectLocation, Math.random() * 360, Math.random() * 100, Math.random() * 720, 2 + Math.random() * 2));
        }
        GameObject.asteroidsGame.asteroidsUI.soundManager.playSmallExplosionSound();
    }
    
    static {
        SmallAsteroid.smallAsteroidOutline = new SuperPolygon[360];
        SmallAsteroid.smallAsteroidOutlineBounds = new Rectangle[360];
        (SmallAsteroid.smallAsteroidOutline[0] = new SuperPolygon()).addPoint(0, -30);
        SmallAsteroid.smallAsteroidOutline[0].addPoint(21, -15);
        SmallAsteroid.smallAsteroidOutline[0].addPoint(30, 10);
        SmallAsteroid.smallAsteroidOutline[0].addPoint(19, 12);
        SmallAsteroid.smallAsteroidOutline[0].addPoint(15, 17);
        SmallAsteroid.smallAsteroidOutline[0].addPoint(-12, 30);
        SmallAsteroid.smallAsteroidOutline[0].addPoint(-30, -5);
        PolygonGameObject.setupReferenceOutlineArrays(SmallAsteroid.smallAsteroidOutline, SmallAsteroid.smallAsteroidOutlineBounds);
    }
}
