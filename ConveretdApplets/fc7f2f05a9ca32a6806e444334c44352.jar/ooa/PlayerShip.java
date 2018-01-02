// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Rectangle;

public class PlayerShip extends Ship
{
    protected static final double PLAYER_SHIP_ROTATIONAL_SPEED = 120.0;
    protected static final double PLAYER_SHIP_THRUST_ACCELERATION = 225.0;
    protected static final double PLAYER_SHIP_DECELERATION = 10.0;
    protected static final int PLAYER_SHIP_MAX_HEALTH = 5;
    protected static final double PLAYER_SHIP_MAX_ENERGY = 100.0;
    protected static final int PLAYER_SHIP_ENERGY_RECHARGE_RATE = 6;
    protected static final int INITIAL_MISSILES = 5;
    protected static SuperPolygon[] playerShipOutline;
    protected static Rectangle[] playerShipOutlineBounds;
    protected static HighPrecisionPoint[] engineLocation;
    protected static HighPrecisionPoint[] missleLauncherLocation;
    protected static HighPrecisionPoint[] gun1Location;
    protected static HighPrecisionPoint[] gun2Location;
    protected static HighPrecisionPoint[] superGunLocation;
    protected int currentGunNumber;
    protected int rotateUIInput;
    protected int thrustUIInput;
    protected int fireMissileUIInput;
    protected int fireGunUIInput;
    
    public PlayerShip(final HighPrecisionPoint createLocation) {
        super(PlayerShip.playerShipOutline, PlayerShip.playerShipOutlineBounds, createLocation, 0.0, 0.0, 0.0, 100.0, 5, 225.0, 10.0, 6.0);
        this.rotateUIInput = 5;
        this.thrustUIInput = 7;
        this.fireMissileUIInput = 9;
        this.fireGunUIInput = 11;
        this.currentGunNumber = 1;
        super.missilesRemaining = 5;
        super.objectHeading = 0.0;
    }
    
    public PlayerShip(final HighPrecisionPoint createLocation, final double shipDeceleration) {
        super(PlayerShip.playerShipOutline, PlayerShip.playerShipOutlineBounds, createLocation, 0.0, 0.0, 0.0, 100.0, 5, 225.0, shipDeceleration, 6.0);
        this.rotateUIInput = 5;
        this.thrustUIInput = 7;
        this.fireMissileUIInput = 9;
        this.fireGunUIInput = 11;
        this.currentGunNumber = 1;
        super.missilesRemaining = 5;
        super.objectHeading = 0.0;
    }
    
    public void gameTick(final double gameTickInterval) {
        super.gameTick(gameTickInterval);
        if (this.rotateUIInput != 5) {
            if (this.rotateUIInput == 1) {
                super.objectRotationalSpeed = -120.0;
            }
            else {
                super.objectRotationalSpeed = 120.0;
            }
        }
        else {
            super.objectRotationalSpeed = 0.0;
        }
        if (this.thrustUIInput == 6) {
            this.fireThruster(gameTickInterval, PlayerShip.engineLocation[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
        }
        else if (super.objectMovementSpeed > 1) {
            super.objectMovementSpeed -= 10.0 * gameTickInterval;
        }
        else {
            super.objectMovementSpeed = 0.0;
        }
        if (this.fireMissileUIInput == 8) {
            this.fireMissile();
        }
        if (this.fireGunUIInput == 10) {
            this.fireGun();
        }
    }
    
    public void fireMissile() {
        this.fireMissileUIInput = 9;
        super.fireMissile(PlayerShip.missleLauncherLocation[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y));
    }
    
    void fireGun() {
        this.fireGunUIInput = 11;
        if (this.currentGunNumber == 1) {
            super.fireGun(PlayerShip.gun1Location[(int)super.objectHeading].cloneTranslate(super.objectLocation.x, super.objectLocation.y));
            this.currentGunNumber = 2;
        }
        else {
            super.fireGun(PlayerShip.gun2Location[(int)super.objectHeading].cloneTranslate(super.objectLocation.x, super.objectLocation.y));
            this.currentGunNumber = 1;
        }
        if (super.superGunActive) {
            super.fireGun(PlayerShip.superGunLocation[(int)super.objectHeading].cloneTranslate(super.objectLocation.x, super.objectLocation.y));
        }
    }
    
    public void setUIInput(final int input) {
        switch (input) {
            case 1: {
                this.rotateUIInput = 1;
                break;
            }
            case 2: {
                this.rotateUIInput = 2;
                break;
            }
            case 3: {
                if (this.rotateUIInput == 1) {
                    this.rotateUIInput = 5;
                    break;
                }
                break;
            }
            case 4: {
                if (this.rotateUIInput == 2) {
                    this.rotateUIInput = 5;
                    break;
                }
                break;
            }
            case 6: {
                this.thrustUIInput = 6;
                GameObject.asteroidsGame.asteroidsUI.soundManager.playThrustSound();
                break;
            }
            case 7: {
                this.thrustUIInput = 7;
                GameObject.asteroidsGame.asteroidsUI.soundManager.stopPlayingThrustSound();
                break;
            }
            case 8: {
                this.fireMissileUIInput = 8;
                break;
            }
            case 10: {
                this.fireGunUIInput = 10;
                super.autoFireActive = false;
                break;
            }
            case 12: {
                if (super.autoFireAvailable) {
                    super.autoFireActive = true;
                    break;
                }
                break;
            }
        }
    }
    
    static {
        PlayerShip.playerShipOutline = new SuperPolygon[360];
        PlayerShip.playerShipOutlineBounds = new Rectangle[360];
        PlayerShip.engineLocation = new HighPrecisionPoint[360];
        PlayerShip.missleLauncherLocation = new HighPrecisionPoint[360];
        PlayerShip.gun1Location = new HighPrecisionPoint[360];
        PlayerShip.gun2Location = new HighPrecisionPoint[360];
        PlayerShip.superGunLocation = new HighPrecisionPoint[360];
        (PlayerShip.playerShipOutline[0] = new SuperPolygon()).addPoint(0, 0);
        PlayerShip.playerShipOutline[0].addPoint(-12, 6);
        PlayerShip.playerShipOutline[0].addPoint(-12, -3);
        PlayerShip.playerShipOutline[0].addPoint(-12, 6);
        PlayerShip.playerShipOutline[0].addPoint(0, -13);
        PlayerShip.playerShipOutline[0].addPoint(12, 6);
        PlayerShip.playerShipOutline[0].addPoint(12, -3);
        PlayerShip.playerShipOutline[0].addPoint(12, 6);
        PolygonGameObject.setupReferenceOutlineArrays(PlayerShip.playerShipOutline, PlayerShip.playerShipOutlineBounds);
        PlayerShip.engineLocation[0] = new HighPrecisionPoint(0.0, 0.0);
        PlayerShip.missleLauncherLocation[0] = new HighPrecisionPoint(0.0, -16.0);
        PlayerShip.gun1Location[0] = new HighPrecisionPoint(-12.0, -4.0);
        PlayerShip.gun2Location[0] = new HighPrecisionPoint(12.0, -4.0);
        PlayerShip.superGunLocation[0] = new HighPrecisionPoint(0.0, -17.0);
        for (int i = 1; i < 360; ++i) {
            PlayerShip.engineLocation[i] = PlayerShip.engineLocation[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            PlayerShip.missleLauncherLocation[i] = PlayerShip.missleLauncherLocation[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            PlayerShip.gun1Location[i] = PlayerShip.gun1Location[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            PlayerShip.gun2Location[i] = PlayerShip.gun2Location[0].cloneRotate(i);
        }
        for (int i = 1; i < 360; ++i) {
            PlayerShip.superGunLocation[i] = PlayerShip.superGunLocation[0].cloneRotate(i);
        }
    }
}
