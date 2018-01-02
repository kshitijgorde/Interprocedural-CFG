// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import java.awt.event.KeyEvent;
import com.tn.zx.ZxExpansionPort;
import com.tn.zx.ZxIoHandler;

public abstract class IOJoystick implements ZxIoHandler
{
    ZxExpansionPort ivConnectedSpectrum;
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int DOWN = 3;
    static final int FIRE = 4;
    public static final String JOYSTICK_ID_KEMPSTON = "KEMPSTON";
    public static final String JOYSTICK_ID_CURSOR = "CURSOR";
    public static final String JOYSTICK_ID_SINCLAIR1 = "SINCLAIR1";
    public static final String JOYSTICK_ID_SINCLAIR2 = "SINCLAIR2";
    
    public IOJoystick() {
        this.ivConnectedSpectrum = null;
    }
    
    abstract void connect();
    
    public final void connectToZXSpectrum(final ZxExpansionPort pZXSpectrum) {
        this.ivConnectedSpectrum = pZXSpectrum;
        this.connect();
    }
    
    public static final IOJoystick createJoystick(final String pJoystickId) {
        if (pJoystickId.toUpperCase().equals("KEMPSTON")) {
            return new JoystickKempston();
        }
        if (pJoystickId.toUpperCase().equals("CURSOR")) {
            return new JoystickCursor();
        }
        if (pJoystickId.toUpperCase().equals("SINCLAIR1")) {
            return new JoystickSinclair1();
        }
        if (pJoystickId.toUpperCase().equals("SINCLAIR2")) {
            return new JoystickSinclair2();
        }
        throw new RuntimeException("Unknown joystick: " + pJoystickId);
    }
    
    public void disconnectFromZXSpectrum() {
        if (this.ivConnectedSpectrum != null) {
            this.ivConnectedSpectrum.removeIOHandler(this);
            this.ivConnectedSpectrum = null;
        }
    }
    
    public abstract String getId();
    
    public boolean keyPressed(final KeyEvent pKeyEvent) {
        return this.registerKeyEvent(pKeyEvent, true);
    }
    
    public boolean keyReleased(final KeyEvent pKeyEvent) {
        return this.registerKeyEvent(pKeyEvent, false);
    }
    
    @Override
    public abstract int readIO(final int p0, final int p1);
    
    @Override
    public void readOpcode1(final int pAddress) {
    }
    
    @Override
    public int readOpcode2(final int pAddress, final int pOpcode) {
        return pOpcode;
    }
    
    private boolean registerKeyEvent(final KeyEvent pKeyEvent, final boolean pStatus) {
        final int keyCode = pKeyEvent.getKeyCode();
        int control = 0;
        switch (keyCode) {
            case 37: {
                control = 0;
                break;
            }
            case 39: {
                control = 1;
                break;
            }
            case 38: {
                control = 2;
                break;
            }
            case 40: {
                control = 3;
                break;
            }
            case 9:
            case 96: {
                control = 4;
                break;
            }
            default: {
                return false;
            }
        }
        this.update(control, pStatus);
        return true;
    }
    
    public abstract void reset();
    
    public void terminate() {
    }
    
    abstract void update(final int p0, final boolean p1);
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
    }
}
