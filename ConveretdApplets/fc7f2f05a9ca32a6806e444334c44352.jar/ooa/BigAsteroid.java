// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class BigAsteroid extends Asteroid
{
    static final int BIG_ASTEROID_MAX_HEALTH = 30;
    static final int BIG_ASTEROID_POINTS = 200;
    private static SuperPolygon[] bigAsteroidOutline;
    private static Rectangle[] bigAsteroidOutlineBounds;
    
    public BigAsteroid(final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed) {
        super(BigAsteroid.bigAsteroidOutline, BigAsteroid.bigAsteroidOutlineBounds, createLocation, createHeading, createSpeed, createRotationalSpeed);
        super.healthLevel = 30;
        super.maxHealthLevel = 30;
        super.asteroidPoints = 200L;
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        super.isAlive = false;
        for (int i = 0; i < 40; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(super.objectLocation, Math.random() * 4, Math.random() * 360, Math.random() * 90));
        }
        for (int i = 0; i < 10; ++i) {
            GameObject.asteroidsGame.addGameObject(new Debris(super.objectLocation, Math.random() * 360, Math.random() * 100, Math.random() * 720, 2 + Math.random() * 2));
        }
        GameObject.asteroidsGame.addGameObject(new MediumAsteroid(super.objectLocation, Math.random() * 360, Math.random() * 100, Math.random() * 200 - 100));
        GameObject.asteroidsGame.addGameObject(new MediumAsteroid(super.objectLocation, Math.random() * 360, Math.random() * 150, Math.random() * 200 - 100));
        GameObject.asteroidsGame.addGameObject(new MediumAsteroid(super.objectLocation, Math.random() * 360, Math.random() * 150, Math.random() * 200 - 100));
        GameObject.asteroidsGame.asteroidsUI.soundManager.playBigExplosionSound();
    }
    
    static {
        BigAsteroid.bigAsteroidOutline = new SuperPolygon[360];
        BigAsteroid.bigAsteroidOutlineBounds = new Rectangle[360];
        (BigAsteroid.bigAsteroidOutline[0] = new SuperPolygon()).addPoint(50, -90);
        BigAsteroid.bigAsteroidOutline[0].addPoint(70, -65);
        BigAsteroid.bigAsteroidOutline[0].addPoint(100, -50);
        BigAsteroid.bigAsteroidOutline[0].addPoint(60, 50);
        BigAsteroid.bigAsteroidOutline[0].addPoint(-30, 90);
        BigAsteroid.bigAsteroidOutline[0].addPoint(-60, 25);
        BigAsteroid.bigAsteroidOutline[0].addPoint(-90, 60);
        BigAsteroid.bigAsteroidOutline[0].addPoint(-100, -50);
        BigAsteroid.bigAsteroidOutline[0].addPoint(-60, -70);
        PolygonGameObject.setupReferenceOutlineArrays(BigAsteroid.bigAsteroidOutline, BigAsteroid.bigAsteroidOutlineBounds);
    }
}
