// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import java.io.Serializable;

public class JSController implements Serializable
{
    private static final long serialVersionUID = -2036930480809642251L;
    public static final int JOYSTICK_UP = 0;
    public static final int JOYSTICK_DOWN = 1;
    public static final int JOYSTICK_LEFT = 2;
    public static final int JOYSTICK_RIGHT = 3;
    public static final int JOYSTICK_BUTTON = 5;
    public static final int PADDLE_ALPHA_BUTTON = 3;
    public static final int PADDLE_BETA_BUTTON = 2;
    public static final int PADDLE_ALPHA_RESISTANCE = 8;
    public static final int PADDLE_BETA_RESISTANCE = 4;
    public static final int BOOSTERGRIP_BOOSTER = 4;
    public static final int BOOSTERGRIP_TRIGGER = 8;
    private JSConstants.Jack myJack;
    private int[] myPinValue;
    
    public JSController(final JSConstants.Jack aJack) {
        this.myJack = JSConstants.Jack.LEFT;
        this.myPinValue = new int[9];
        this.resetController();
    }
    
    public void resetController() {
        for (int i = 0; i < this.myPinValue.length; ++i) {
            this.myPinValue[i] = 1;
        }
        this.setPaddlePosition(PaddleID.PADDLE_ALPHA, 0);
        this.setPaddlePosition(PaddleID.PADDLE_BETA, 0);
    }
    
    public boolean read(final JSConstants.DigitalPin pin) {
        return this.myPinValue[this.getPinIndex(pin)] != 0;
    }
    
    public void write(final JSConstants.DigitalPin pin, final boolean value) {
    }
    
    public int read(final JSConstants.AnalogPin pin) {
        return this.myPinValue[this.getPinIndex(pin)];
    }
    
    public void setJoystickState(final int aJoystickDir, final boolean aPressed) {
        if (aPressed) {
            this.myPinValue[aJoystickDir] = 0;
        }
        else {
            this.myPinValue[aJoystickDir] = 1;
        }
    }
    
    public void setPaddleTrigger(final PaddleID aID, final boolean aPressed) {
        final int zValue = aPressed ? 0 : 1;
        if (aID == PaddleID.PADDLE_ALPHA) {
            this.myPinValue[3] = zValue;
        }
        else if (aID == PaddleID.PADDLE_BETA) {
            this.myPinValue[2] = zValue;
        }
    }
    
    public void changeControllerState(final int aControlEventType, final boolean aOn) {
        final int zValue = aOn ? 0 : 1;
        this.myPinValue[aControlEventType] = zValue;
    }
    
    private static int toPercentX(final int aResistance) {
        return (int)(100.0 - aResistance / 10000.0);
    }
    
    private static int toResistance(final int aPercentX) {
        return (int)(10000.0 * (100 - aPercentX));
    }
    
    public void setPaddlePosition(final PaddleID aID, final int aPercentage) {
        int zNewPercent = Math.min(aPercentage, 100);
        zNewPercent = Math.max(0, zNewPercent);
        final int zRes = toResistance(zNewPercent);
        if (aID == PaddleID.PADDLE_ALPHA) {
            this.myPinValue[8] = zRes;
        }
        else if (aID == PaddleID.PADDLE_BETA) {
            this.myPinValue[4] = zRes;
        }
    }
    
    public int getPaddlePosition(final PaddleID aID) {
        final int zIndex = (aID == PaddleID.PADDLE_BETA) ? 4 : 8;
        return toPercentX(this.myPinValue[zIndex]);
    }
    
    public void changePaddlePosition(final PaddleID aID, final int aDeltaPercent) {
        final int zCurrent = this.getPaddlePosition(aID);
        this.setPaddlePosition(aID, zCurrent + aDeltaPercent);
    }
    
    public void setBoosterGripBooster(final boolean aPressed) {
        this.setPaddlePosition(PaddleID.PADDLE_BETA, aPressed ? 100 : 0);
    }
    
    public void setBoosterGripTrigger(final boolean aPressed) {
        this.setPaddlePosition(PaddleID.PADDLE_ALPHA, aPressed ? 100 : 0);
    }
    
    private int getPinIndex(final JSConstants.DigitalPin aPin) {
        switch (aPin) {
            case One: {
                return 0;
            }
            case Two: {
                return 1;
            }
            case Three: {
                return 2;
            }
            case Four: {
                return 3;
            }
            case Six: {
                return 5;
            }
            default: {
                assert false;
                return 0;
            }
        }
    }
    
    private int getPinIndex(final JSConstants.AnalogPin aPin) {
        switch (aPin) {
            case Five: {
                return 4;
            }
            case Nine: {
                return 8;
            }
            default: {
                assert false;
                return 0;
            }
        }
    }
    
    public enum PaddleID
    {
        PADDLE_ALPHA, 
        PADDLE_BETA;
    }
}
