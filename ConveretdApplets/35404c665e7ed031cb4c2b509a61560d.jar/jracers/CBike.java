// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.applet.Applet;
import java.awt.Image;
import java.util.Random;

public class CBike extends CVehicle
{
    private int maxSpeed;
    private int initialMaxSpeed;
    private int currentMaxSpeed;
    private final int minSpeed = -30;
    private final int accelarationFactor = 10;
    private final int reverseGearDelay = 15;
    private final int slowDownFactor = 15;
    private final int tankSize = 2000;
    private final double fuelBurnRate = 0.0;
    private final boolean computerCar = true;
    private final double frictionFactor = 0.98;
    private final double snowAccelarationFactor = 0.8;
    private final double snowDriftFactor = 0.8;
    private final double iceFrictionFactor = 0.99;
    private final int maxTireStatus = 0;
    private int movingDirection;
    private int movingSpeed;
    private Random randomGenerator;
    
    public CBike() {
        this.maxSpeed = 100;
        this.movingDirection = -1;
        this.movingSpeed = 2;
    }
    
    public void init(final long randomSeed, final int ispeed, final int ixPosition, final int iyPosition, final CRaceTrack rt, final Image icarImage, final Applet a, final int itrackPosition, final CGraphicsConstants gfxElements) {
        if (this.randomGenerator == null) {
            this.randomGenerator = new Random(randomSeed);
        }
        this.initialMaxSpeed = itrackPosition / rt.getLength() * 40 + 40;
        this.currentMaxSpeed = this.initialMaxSpeed;
        this.movingSpeed = this.randomGenerator.nextInt(5);
        this.setCharacteristics(this.maxSpeed, -30, 10, 15, 15, 2000, 0.0, true, 0.98, 0.8, 0.8, 0.99, 0);
        super.init(ispeed, ixPosition, iyPosition, rt, icarImage, a, itrackPosition, gfxElements);
    }
    
    public void autoPilot() {
        if (this.getSpeed() < this.currentMaxSpeed) {
            this.accelarate();
        }
        this.currentMaxSpeed = this.initialMaxSpeed + (this.maxSpeed - this.initialMaxSpeed) * (this.inLap() / 10);
        if (super.raceTrack.inNarrow(super.trackPosition)) {
            this.currentMaxSpeed = this.maxSpeed + 20;
        }
        else if (this.currentMaxSpeed > this.maxSpeed) {
            this.currentMaxSpeed = this.initialMaxSpeed;
        }
        if (super.raceTrack.trackWidth(super.trackPosition) > super.raceTrack.width() / 3) {
            int verticalSpeed = this.inLap() / 4 * this.movingSpeed;
            if (verticalSpeed > 8) {
                verticalSpeed = 8;
            }
            if (this.movingDirection < 0) {
                this.moveLeft(verticalSpeed);
            }
            else {
                this.moveRight(verticalSpeed);
            }
        }
        if (!super.raceTrack.inLeftBounds(this, 20)) {
            this.movingDirection = 1;
        }
        else if (!super.raceTrack.inRightBounds(this, 20)) {
            this.movingDirection = -1;
        }
        this.bringToBounds();
    }
}
