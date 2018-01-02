// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.applet.Applet;
import java.awt.Image;
import java.util.Random;

abstract class CVehicle extends CMovingShape
{
    private CGraphicsConstants gfxElements;
    private int speed;
    public int fuel;
    public CRaceTrack raceTrack;
    public int score;
    private int maxSpeed;
    private int minSpeed;
    private int accelarationFactor;
    private int reverseGearDelay;
    private int slowDownFactor;
    private int tankSize;
    private double fuelBurnRate;
    private boolean computerCar;
    private double frictionFactor;
    private double iceFrictionFactor;
    private double snowAccelarationFactor;
    private double snowDriftFactor;
    private int maxTireStatus;
    private int verticalSpeed;
    private int remainingReverseDelay;
    private boolean waitingForReverse;
    private int boostCounter;
    private int normalMaxSpeed;
    private int[] tireStatus;
    public final int UPPER_LEFT = 0;
    public final int LOWER_LEFT = 1;
    public final int UPPER_RIGHT = 2;
    public final int LOWER_RIGHT = 3;
    private int lapsDone;
    private int maxLapsDone;
    private int remainingTime;
    private Random randomGenerator;
    
    CVehicle() {
        this.score = 0;
        this.maxSpeed = 0;
        this.minSpeed = 0;
        this.accelarationFactor = 0;
        this.reverseGearDelay = 0;
        this.slowDownFactor = 0;
        this.tankSize = 0;
        this.fuelBurnRate = 0.0;
        this.computerCar = false;
        this.frictionFactor = 1.0;
        this.iceFrictionFactor = 1.0;
        this.snowAccelarationFactor = 1.0;
        this.snowDriftFactor = 1.0;
        this.maxTireStatus = 10;
    }
    
    public void CVehicle() {
        this.speed = 0;
        this.fuel = this.tankSize;
        this.lapsDone = 0;
        this.maxLapsDone = 0;
    }
    
    public void bump() {
        if (this.randomGenerator.nextInt(2) == 0) {
            this.moveLeft(2);
        }
        else {
            this.moveRight(2);
        }
        this.bringToBounds();
    }
    
    public int getTotalScore() {
        int ret = (int)((this.lapsDone * this.raceTrack.getLength() + super.trackPosition) * 0.1);
        if (ret < 0) {
            ret = 0;
        }
        return ret + this.score;
    }
    
    public void activateBoost(final int count) {
        this.maxSpeed = (int)(this.normalMaxSpeed * 1.5);
        this.boostCounter = count;
    }
    
    public int getScrollSpeed() {
        int rval = 0;
        if (this.speed == 0) {
            rval = 0;
        }
        else if (this.speed > 0) {
            rval = this.speed / 10 + 1;
        }
        else if (this.speed < 0) {
            rval = this.speed / 10 - 1;
        }
        return rval;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public int getRemainingTime() {
        return this.remainingTime;
    }
    
    public boolean outOfTime() {
        return this.remainingTime == 0;
    }
    
    public void addTime(final int rTime) {
        this.remainingTime += rTime;
    }
    
    abstract void autoPilot();
    
    public void setCharacteristics(final int imaxSpeed, final int iminSpeed, final int iaccelarationFactor, final int ireverseGearDelay, final int islowDownFactor, final int itankSize, final double ifuelBurnRate, final boolean icomputerCar, final double ifrictionFactor, final double isnowAccelarationFactor, final double isnowDriftFactor, final double iiceFrictionFactor, final int imaxTireStatus) {
        this.maxSpeed = imaxSpeed;
        this.minSpeed = iminSpeed;
        this.accelarationFactor = iaccelarationFactor;
        this.reverseGearDelay = ireverseGearDelay;
        this.slowDownFactor = islowDownFactor;
        this.tankSize = itankSize;
        this.fuelBurnRate = ifuelBurnRate;
        this.computerCar = icomputerCar;
        this.frictionFactor = ifrictionFactor;
        this.snowAccelarationFactor = isnowAccelarationFactor;
        this.snowDriftFactor = isnowDriftFactor;
        this.iceFrictionFactor = iiceFrictionFactor;
        this.maxTireStatus = imaxTireStatus;
        this.normalMaxSpeed = this.maxSpeed;
    }
    
    public void canisterHit() {
        this.fuel += this.tankSize / 4;
        if (this.fuel > this.tankSize) {
            this.fuel = this.tankSize;
        }
    }
    
    public void tireHit() {
        this.tireStatus[0] = this.maxTireStatus;
        this.tireStatus[1] = this.maxTireStatus;
        this.tireStatus[2] = this.maxTireStatus;
        this.tireStatus[3] = this.maxTireStatus;
    }
    
    public void undoMove() {
        super.undoMove();
    }
    
    public int inLap() {
        return this.maxLapsDone + 1;
    }
    
    public boolean canMove() {
        return this.computerCar || (!this.outOfGas() && !this.outOfTime() && !this.tireBlown());
    }
    
    public boolean outOfGas() {
        return this.fuel <= 0;
    }
    
    public boolean tireBlown() {
        return this.tireStatus[0] == 0 || this.tireStatus[1] == 0 || this.tireStatus[2] == 0 || this.tireStatus[3] == 0;
    }
    
    public void consumeTires(final CCollisionCode ccode, final int factor) {
        if (ccode.upperLeftTireHit && this.tireStatus[0] > 0) {
            this.tireStatus[0] = Math.max(this.tireStatus[0] - factor, 0);
        }
        if (ccode.lowerLeftTireHit && this.tireStatus[1] > 0) {
            this.tireStatus[1] = Math.max(this.tireStatus[1] - factor, 0);
        }
        if (ccode.upperRightTireHit && this.tireStatus[2] > 0) {
            this.tireStatus[2] = Math.max(this.tireStatus[2] - factor, 0);
        }
        if (ccode.lowerRightTireHit && this.tireStatus[3] > 0) {
            this.tireStatus[3] = Math.max(this.tireStatus[3] - factor, 0);
        }
    }
    
    public int giveTireStatus(final int tire) {
        return this.tireStatus[tire];
    }
    
    public int giveMinTireStatus() {
        int min = this.maxTireStatus;
        min = Math.min(this.tireStatus[0], Math.min(this.tireStatus[2], Math.min(this.tireStatus[1], this.tireStatus[3])));
        return min;
    }
    
    public int getBoostCounter() {
        return this.boostCounter;
    }
    
    public void drive() {
        if (this.gfxElements.playSound && !this.computerCar && this.speed != 0) {
            if (this.speed < 40) {
                this.gfxElements.engine.loop();
                this.gfxElements.engine2.stop();
                this.gfxElements.engine3.stop();
                this.gfxElements.engine4.stop();
            }
            else if (this.speed < 98) {
                this.gfxElements.engine.stop();
                this.gfxElements.engine2.loop();
                this.gfxElements.engine3.stop();
                this.gfxElements.engine4.stop();
            }
            else if (this.speed < 140) {
                this.gfxElements.engine.stop();
                this.gfxElements.engine2.stop();
                this.gfxElements.engine3.loop();
                this.gfxElements.engine4.stop();
            }
            else {
                this.gfxElements.engine.stop();
                this.gfxElements.engine2.stop();
                this.gfxElements.engine3.stop();
                this.gfxElements.engine4.loop();
            }
        }
        if (this.remainingTime > 0) {
            --this.remainingTime;
        }
        if (this.boostCounter > 0) {
            --this.boostCounter;
        }
        if (this.boostCounter == 0) {
            this.maxSpeed = this.normalMaxSpeed;
        }
        if (this.raceTrack.getCondition(super.trackPosition) == 1 && this.computerCar && this.getScrollSpeed() > 2) {
            this.drift();
        }
        if (this.remainingReverseDelay > 0) {
            --this.remainingReverseDelay;
        }
        this.fuel -= (int)(Math.abs(this.speed) * this.fuelBurnRate);
        if (this.fuel < 0) {
            this.fuel = 0;
        }
        if (this.raceTrack.getCondition(super.trackPosition) == 0) {
            this.speed *= (int)this.frictionFactor;
        }
        else if (this.raceTrack.getCondition(super.trackPosition) == 1) {
            this.speed *= (int)this.iceFrictionFactor;
        }
        super.trackPosition += this.getScrollSpeed();
        this.rollateTrackPosition();
    }
    
    public void rollateTrackPosition() {
        if (super.trackPosition < 0) {
            super.trackPosition += this.raceTrack.getLength();
            --this.lapsDone;
        }
        else if (super.trackPosition > this.raceTrack.getLength()) {
            super.trackPosition -= this.raceTrack.getLength();
            ++this.lapsDone;
            if (this.maxLapsDone < this.lapsDone) {
                this.maxLapsDone = this.lapsDone;
                if (!this.computerCar) {
                    this.score += this.remainingTime / 30 * 100;
                    if (this.lapsDone == 2) {
                        this.score += 5000;
                    }
                    this.addTime(this.raceTrack.getTimeLimit(this.lapsDone));
                }
            }
        }
    }
    
    public void accelarate() {
        if (this.speed < this.maxSpeed && this.canMove()) {
            if (this.raceTrack.getCondition(super.trackPosition) == 0 || this.getScrollSpeed() <= 2) {
                this.speed += this.accelarationFactor;
            }
            else {
                this.speed += (int)(this.accelarationFactor * this.snowAccelarationFactor);
            }
            if (this.speed > this.maxSpeed) {
                this.speed = this.maxSpeed;
            }
            this.remainingReverseDelay = 0;
            this.waitingForReverse = false;
        }
    }
    
    public void slowDown() {
        if (this.speed > this.minSpeed && (this.canMove() || this.speed > 0)) {
            if (!this.waitingForReverse && this.speed >= 0 && this.speed - this.slowDownFactor < 0) {
                this.speed = 0;
                this.remainingReverseDelay = this.reverseGearDelay;
                this.waitingForReverse = true;
            }
            else if (this.speed != 0 || this.remainingReverseDelay <= 0) {
                if (this.raceTrack.getCondition(super.trackPosition) == 0) {
                    this.speed -= this.slowDownFactor;
                }
                else if (this.raceTrack.getCondition(super.trackPosition) == 1) {
                    this.speed -= (int)(this.slowDownFactor * this.snowAccelarationFactor);
                }
                this.waitingForReverse = false;
            }
            if (this.speed < this.minSpeed) {
                this.speed = this.minSpeed;
            }
        }
    }
    
    public void collisionSlowDown() {
        this.speed *= (int)0.8;
    }
    
    public boolean bringToBounds() {
        int newX = 0;
        boolean rval = false;
        final CCollisionCode ccode = this.raceTrack.bringToBounds(this);
        if (ccode.isAnythingHit() && !this.computerCar) {
            this.consumeTires(ccode, 2);
        }
        newX = ccode.xShift;
        if (newX != 0) {
            rval = true;
            if (this.gfxElements.playSound && !this.computerCar) {
                this.gfxElements.border.play();
            }
            this.setNewXPos(newX);
            this.collisionSlowDown();
        }
        return rval;
    }
    
    public void moveStraight() {
        if (this.raceTrack.getCondition(super.trackPosition) == 0) {
            this.verticalSpeed = 0;
        }
    }
    
    public void moveRight(final int delta) {
        if (super.x2 + delta < this.raceTrack.width()) {
            this.verticalSpeed += (int)(delta / (this.snowDriftFactor * Math.abs(delta)));
            if (Math.abs(this.verticalSpeed) > Math.abs(delta)) {
                this.verticalSpeed = delta;
            }
            if (this.raceTrack.getCondition(super.trackPosition) == 0 || !this.computerCar || this.getScrollSpeed() <= 2) {
                this.setNewXPos(super.x1 + delta);
                this.verticalSpeed = delta;
            }
        }
    }
    
    public void moveLeft(final int delta) {
        if (super.x1 - delta > 0) {
            this.verticalSpeed -= (int)(delta / (this.snowDriftFactor * Math.abs(delta)));
            if (Math.abs(this.verticalSpeed) > Math.abs(delta)) {
                this.verticalSpeed = -delta;
            }
            if (this.raceTrack.getCondition(super.trackPosition) == 0 || !this.computerCar || this.getScrollSpeed() <= 2) {
                this.setNewXPos(super.x1 - delta);
                this.verticalSpeed = -delta;
            }
        }
    }
    
    public void drift() {
        if (this.verticalSpeed > 0) {
            --this.verticalSpeed;
        }
        else if (this.verticalSpeed < 0) {
            ++this.verticalSpeed;
        }
        if (super.x1 + this.verticalSpeed > 0 && super.x2 + this.verticalSpeed < this.raceTrack.width()) {
            this.setNewXPos(super.x1 + this.verticalSpeed);
        }
    }
    
    public void pushLeft(final CVehicle cshape) {
        if (cshape.x1 - super.width - 1 > 0) {
            this.setNewXPos(cshape.x1 - super.width - 1);
        }
    }
    
    public void pushRight(final CVehicle cshape) {
        if (cshape.x2 + super.width + 1 < this.raceTrack.width()) {
            this.setNewXPos(cshape.x2 + 1);
        }
    }
    
    public void pushUp(final CVehicle cshape) {
        super.trackPosition = cshape.trackPosition + super.height + 1;
        this.rollateTrackPosition();
    }
    
    public void pushDown(final CVehicle cshape) {
        super.trackPosition = cshape.trackPosition - cshape.height - 1;
        this.rollateTrackPosition();
    }
    
    public void init(final int ispeed, final int ixPosition, final int iyPosition, final CRaceTrack rt, final Image icarImage, final Applet a, final int itrackPosition, final CGraphicsConstants gfxElements) {
        this.speed = ispeed;
        this.raceTrack = rt;
        this.fuel = this.tankSize;
        this.lapsDone = 0;
        this.maxLapsDone = 0;
        this.waitingForReverse = false;
        this.remainingReverseDelay = 0;
        this.verticalSpeed = 0;
        this.tireStatus = new int[4];
        for (int i = 0; i < 4; ++i) {
            this.tireStatus[i] = this.maxTireStatus;
        }
        this.remainingTime = this.raceTrack.getTimeLimit(this.lapsDone);
        this.boostCounter = 0;
        this.score = 0;
        this.setImage(icarImage, a);
        this.setCoordinates(ixPosition, iyPosition, itrackPosition);
        this.randomGenerator = new Random();
        this.gfxElements = gfxElements;
    }
}
