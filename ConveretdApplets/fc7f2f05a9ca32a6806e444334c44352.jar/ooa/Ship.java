// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public abstract class Ship extends PolygonGameObject
{
    protected static final double THRUST_SPEED = 120.0;
    protected static final double THRUST_ACCELERATION_ENERGY_COST = 10.0;
    protected static final long INVINCIBILITY_DURATION = 3000L;
    protected static final long AUTOFIRE_INTERVAL = 100L;
    protected double shipThrustAcceleration;
    protected double shipThrustEnergyCost;
    protected double shipMaxEnergy;
    protected double shipEnergy;
    protected double shipDeceleration;
    protected double shipEnergyRechargeRate;
    protected long invincibilityTimer;
    protected int missilesRemaining;
    protected boolean superGunActive;
    protected boolean autoFireActive;
    protected boolean autoFireAvailable;
    protected boolean fastEnergyRechargeActive;
    protected boolean debrisShieldActive;
    protected boolean missileGuidanceActive;
    protected long lastGunShot;
    
    abstract void fireGun();
    
    public Ship(final SuperPolygon[] objectOutline, final Rectangle[] objectOutlineBounds, final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed, final double maxEnergy, final int maxHealth, final double thrustAcceleration, final double deceleration, final double energyRechargeRate) {
        super(objectOutline, objectOutlineBounds, createLocation, createHeading, createSpeed, createRotationalSpeed);
        this.shipEnergy = maxEnergy;
        this.shipMaxEnergy = maxEnergy;
        super.healthLevel = maxHealth;
        super.maxHealthLevel = maxHealth;
        this.shipThrustAcceleration = thrustAcceleration;
        this.shipThrustEnergyCost = thrustAcceleration / 10.0;
        this.shipDeceleration = deceleration;
        this.shipEnergyRechargeRate = energyRechargeRate;
        this.invincibilityTimer = System.currentTimeMillis() + 3000L;
        this.superGunActive = false;
        this.autoFireActive = false;
        this.autoFireAvailable = false;
        this.fastEnergyRechargeActive = false;
        this.debrisShieldActive = false;
        this.missileGuidanceActive = false;
        this.lastGunShot = 0L;
    }
    
    public void fireThruster(final double gameTickInterval, final HighPrecisionPoint thrusterLocation) {
        if (this.shipEnergy < this.shipThrustEnergyCost * gameTickInterval) {
            return;
        }
        final HighPrecisionPoint destinationLocation = new HighPrecisionPoint(super.objectLocation);
        destinationLocation.directionalMove(super.objectMovementDirection, super.objectMovementSpeed * gameTickInterval);
        destinationLocation.directionalMove(super.objectHeading, this.shipThrustAcceleration * Math.pow(gameTickInterval, 1.95));
        super.objectMovementDirection = super.objectLocation.getAngleBetween(destinationLocation);
        super.objectMovementSpeed = super.objectLocation.getDistanceBetween(destinationLocation) / gameTickInterval;
        this.shipEnergy -= this.shipThrustEnergyCost * gameTickInterval;
        for (int i = (int)super.objectHeading - 175; i > super.objectHeading - 185; i -= 2) {
            GameObject.asteroidsGame.addGameObject(new DotObject(thrusterLocation, Math.random(), i, 120.0 + 36.0 * Math.random()));
        }
    }
    
    public void fireGun(final HighPrecisionPoint gunLocation) {
        if (this.shipEnergy < 3) {
            return;
        }
        GameObject.asteroidsGame.addGameObject(new Bullet(gunLocation, super.objectHeading, this));
        this.shipEnergy -= 3;
        this.lastGunShot = System.currentTimeMillis();
    }
    
    public void fireMissile(final HighPrecisionPoint missileLauncherLocation) {
        if (this.missilesRemaining <= 0) {
            return;
        }
        if (this.missileGuidanceActive) {
            GameObject.asteroidsGame.addGameObject(new HomingMissile(missileLauncherLocation, super.objectHeading, this));
        }
        else {
            GameObject.asteroidsGame.addGameObject(new Missile(missileLauncherLocation, super.objectHeading, this));
        }
        --this.missilesRemaining;
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        this.shipEnergy = 0.0;
        for (int i = 0; i < 40; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(new HighPrecisionPoint((int)super.objectLocation.x, (int)super.objectLocation.y), Math.random() * 4, Math.random() * 360, Math.random() * 90));
        }
        for (int i = 0; i < 10; ++i) {
            GameObject.asteroidsGame.addGameObject(new Debris(new HighPrecisionPoint((int)super.objectLocation.x, (int)super.objectLocation.y), Math.random() * 360, Math.random() * 100, Math.random() * 720, 2 + Math.random() * 2));
        }
        GameObject.asteroidsGame.asteroidsUI.soundManager.playBigExplosionSound();
        GameObject.asteroidsGame.asteroidsUI.soundManager.stopPlayingThrustSound();
    }
    
    public void gameTick(final double gameTickInterval) {
        super.updatePosition(gameTickInterval);
        if (super.objectMovementSpeed > 1) {
            super.objectMovementSpeed -= this.shipDeceleration * gameTickInterval;
        }
        else {
            super.objectMovementSpeed = 0.0;
        }
        if (!this.fastEnergyRechargeActive) {
            this.shipEnergy += this.shipEnergyRechargeRate * gameTickInterval;
        }
        else {
            this.shipEnergy += this.shipEnergyRechargeRate * 2 * gameTickInterval;
        }
        if (this.shipEnergy > this.shipMaxEnergy) {
            this.shipEnergy = this.shipMaxEnergy;
        }
        if (this.autoFireActive && this.lastGunShot < System.currentTimeMillis() - 100L) {
            this.fireGun();
        }
        if (super.healthLevel <= 0) {
            this.explode(null);
        }
    }
    
    protected void collideWith(final PolygonGameObject o, final int oBeforeHealthLevel) {
        if (!(o instanceof Debris) || (o instanceof Debris && !this.debrisShieldActive)) {
            super.collideWith(o, oBeforeHealthLevel);
        }
    }
    
    protected boolean isInvincible() {
        return this.invincibilityTimer > System.currentTimeMillis();
    }
}
