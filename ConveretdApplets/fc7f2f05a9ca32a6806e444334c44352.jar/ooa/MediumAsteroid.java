// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class MediumAsteroid extends Asteroid
{
    static final int MEDIUM_ASTEROID_MAX_HEALTH = 15;
    static final int MEDIUM_ASTEROID_POINTS = 100;
    private static SuperPolygon[] mediumAsteroidOutline;
    private static Rectangle[] mediumAsteroidOutlineBounds;
    
    public MediumAsteroid(final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed) {
        super(MediumAsteroid.mediumAsteroidOutline, MediumAsteroid.mediumAsteroidOutlineBounds, createLocation, createHeading, createSpeed, createRotationalSpeed);
        super.healthLevel = 15;
        super.maxHealthLevel = 15;
        super.asteroidPoints = 100L;
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        super.isAlive = false;
        for (int i = 0; i < 35; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(super.objectLocation, Math.random() * 3, Math.random() * 360, Math.random() * 90));
        }
        for (int i = 0; i < 7; ++i) {
            GameObject.asteroidsGame.addGameObject(new Debris(super.objectLocation, Math.random() * 360, Math.random() * 100, Math.random() * 720, 2 + Math.random() * 2));
        }
        GameObject.asteroidsGame.addGameObject(new SmallAsteroid(super.objectLocation, Math.random() * 360, Math.random() * 150, Math.random() * 200 - 100));
        GameObject.asteroidsGame.addGameObject(new SmallAsteroid(super.objectLocation, Math.random() * 360, Math.random() * 200, Math.random() * 200 - 100));
        GameObject.asteroidsGame.addGameObject(new SmallAsteroid(super.objectLocation, Math.random() * 360, Math.random() * 220, Math.random() * 200 - 100));
        GameObject.asteroidsGame.asteroidsUI.soundManager.playSmallExplosionSound();
    }
    
    protected void collideWith(final PolygonGameObject o, final int oBeforeHealthLevel) {
        super.collideWith(o, oBeforeHealthLevel);
    }
    
    static {
        MediumAsteroid.mediumAsteroidOutline = new SuperPolygon[360];
        MediumAsteroid.mediumAsteroidOutlineBounds = new Rectangle[360];
        (MediumAsteroid.mediumAsteroidOutline[0] = new SuperPolygon()).addPoint(40, -35);
        MediumAsteroid.mediumAsteroidOutline[0].addPoint(50, 0);
        MediumAsteroid.mediumAsteroidOutline[0].addPoint(25, 10);
        MediumAsteroid.mediumAsteroidOutline[0].addPoint(10, 30);
        MediumAsteroid.mediumAsteroidOutline[0].addPoint(-20, 45);
        MediumAsteroid.mediumAsteroidOutline[0].addPoint(-50, 40);
        MediumAsteroid.mediumAsteroidOutline[0].addPoint(-40, -30);
        MediumAsteroid.mediumAsteroidOutline[0].addPoint(-10, -45);
        PolygonGameObject.setupReferenceOutlineArrays(MediumAsteroid.mediumAsteroidOutline, MediumAsteroid.mediumAsteroidOutlineBounds);
    }
}
