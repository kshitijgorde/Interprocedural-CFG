// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import java.util.Random;
import java.io.Serializable;

public class JSRiot implements IfcDevice, Serializable
{
    private static final long serialVersionUID = 7428490123154878165L;
    private static Random mvRandom;
    private JSConsole myConsole;
    private JSSystem mySystem;
    private int[] myRAM;
    private int myTimer;
    private int myIntervalShift;
    private int myCyclesWhenTimerSet;
    private int myCyclesWhenInterruptReset;
    private boolean myTimerReadAfterInterrupt;
    private int myDDRA;
    private int myDDRB;
    
    public JSRiot(final JSConsole console) {
        this.myConsole = null;
        this.mySystem = null;
        this.myRAM = new int[128];
        this.myTimer = 0;
        this.myIntervalShift = 0;
        this.myCyclesWhenTimerSet = 0;
        this.myCyclesWhenInterruptReset = 0;
        this.myTimerReadAfterInterrupt = false;
        this.myDDRA = 0;
        this.myDDRB = 0;
        this.myConsole = console;
        for (int i = 0; i < this.myRAM.length; ++i) {
            this.myRAM[i] = Math.abs(JSRiot.mvRandom.nextInt() % 256);
        }
        this.reset();
    }
    
    public String name() {
        return "6532";
    }
    
    public void reset() {
        this.myTimer = 25 + JSRiot.mvRandom.nextInt() % 75;
        this.myIntervalShift = 6;
        this.myCyclesWhenTimerSet = 0;
        this.myCyclesWhenInterruptReset = 0;
        this.myTimerReadAfterInterrupt = false;
        this.myDDRA = 0;
        this.myDDRB = 0;
    }
    
    public void systemCyclesReset() {
        this.myCyclesWhenTimerSet -= this.mySystem.getCycles();
        this.myCyclesWhenInterruptReset -= this.mySystem.getCycles();
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        final int shift = 6;
        final int mask = 63;
        assert (0x1080 & mask) == 0x0;
        for (int address = 0; address < 8192; address += 1 << shift) {
            if ((address & 0x1080) == 0x80) {
                if ((address & 0x200) == 0x0) {
                    final PageAccess access = new PageAccess(this);
                    access.setDirectPeekMemory(this.myRAM, address & 0x7F);
                    access.setDirectPokeMemory(this.myRAM, address & 0x7F);
                    this.mySystem.setPageAccess(address >> shift, access);
                }
                else {
                    final PageAccess access = new PageAccess(this);
                    access.setIndirectMode();
                    this.mySystem.setPageAccess((char)(address >> shift), access);
                }
            }
        }
    }
    
    public static String toHexStr(final char addr) {
        return "0x" + Integer.toHexString(addr);
    }
    
    public int peek(final int addr) {
        int zReturn = 0;
        switch (addr & 0x7) {
            case 0: {
                int value = 0;
                if (this.myConsole.getController(JSConstants.Jack.LEFT).read(JSConstants.DigitalPin.One)) {
                    value |= 0x10;
                }
                if (this.myConsole.getController(JSConstants.Jack.LEFT).read(JSConstants.DigitalPin.Two)) {
                    value |= 0x20;
                }
                if (this.myConsole.getController(JSConstants.Jack.LEFT).read(JSConstants.DigitalPin.Three)) {
                    value |= 0x40;
                }
                if (this.myConsole.getController(JSConstants.Jack.LEFT).read(JSConstants.DigitalPin.Four)) {
                    value |= 0x80;
                }
                if (this.myConsole.getController(JSConstants.Jack.RIGHT).read(JSConstants.DigitalPin.One)) {
                    value |= 0x1;
                }
                if (this.myConsole.getController(JSConstants.Jack.RIGHT).read(JSConstants.DigitalPin.Two)) {
                    value |= 0x2;
                }
                if (this.myConsole.getController(JSConstants.Jack.RIGHT).read(JSConstants.DigitalPin.Three)) {
                    value |= 0x4;
                }
                if (this.myConsole.getController(JSConstants.Jack.RIGHT).read(JSConstants.DigitalPin.Four)) {
                    value |= 0x8;
                }
                zReturn = value;
                break;
            }
            case 1: {
                zReturn = this.myDDRA;
                break;
            }
            case 2: {
                zReturn = this.myConsole.readSwitches();
                break;
            }
            case 3: {
                zReturn = this.myDDRB;
                break;
            }
            case 4:
            case 6: {
                final int zCurrentCycle = this.mySystem.getCycles() - 1;
                final int zCyclesElapsed = zCurrentCycle - this.myCyclesWhenTimerSet;
                int zCurrentIntervalCount = this.myTimer - (zCyclesElapsed >> this.myIntervalShift) - 1;
                if (zCurrentIntervalCount >= 0) {
                    zReturn = zCurrentIntervalCount;
                    break;
                }
                zCurrentIntervalCount = (this.myTimer << this.myIntervalShift) - zCyclesElapsed - 1;
                if (zCurrentIntervalCount <= -2 && !this.myTimerReadAfterInterrupt) {
                    this.myTimerReadAfterInterrupt = true;
                    this.myCyclesWhenInterruptReset = this.mySystem.getCycles();
                }
                if (this.myTimerReadAfterInterrupt) {
                    final int zOffset = this.myCyclesWhenInterruptReset - (this.myCyclesWhenTimerSet + (this.myTimer << this.myIntervalShift));
                    zCurrentIntervalCount = this.myTimer - (zCyclesElapsed >> this.myIntervalShift) - zOffset;
                }
                zReturn = (zCurrentIntervalCount & 0xFF);
                break;
            }
            case 5:
            case 7: {
                final int cycles = this.mySystem.getCycles() - 1;
                final int delta = cycles - this.myCyclesWhenTimerSet;
                final int timer = this.myTimer - (delta >> this.myIntervalShift) - 1;
                if (timer >= 0 || this.myTimerReadAfterInterrupt) {
                    zReturn = 0;
                    break;
                }
                zReturn = 128;
                break;
            }
            default: {
                zReturn = 0;
                break;
            }
        }
        assert zReturn >= 0 && zReturn < 256;
        return zReturn;
    }
    
    private boolean bool(final int aValue) {
        return aValue != 0;
    }
    
    public void poke(final int addr, final int value) {
        if ((addr & 0x7) == 0x0) {
            final int a = value & this.myDDRA;
            this.myConsole.getController(JSConstants.Jack.LEFT).write(JSConstants.DigitalPin.One, this.bool(a & 0x10));
            this.myConsole.getController(JSConstants.Jack.LEFT).write(JSConstants.DigitalPin.Two, this.bool(a & 0x20));
            this.myConsole.getController(JSConstants.Jack.LEFT).write(JSConstants.DigitalPin.Three, this.bool(a & 0x40));
            this.myConsole.getController(JSConstants.Jack.LEFT).write(JSConstants.DigitalPin.Four, this.bool(a & 0x80));
            this.myConsole.getController(JSConstants.Jack.RIGHT).write(JSConstants.DigitalPin.One, this.bool(a & 0x1));
            this.myConsole.getController(JSConstants.Jack.RIGHT).write(JSConstants.DigitalPin.Two, this.bool(a & 0x2));
            this.myConsole.getController(JSConstants.Jack.RIGHT).write(JSConstants.DigitalPin.Three, this.bool(a & 0x4));
            this.myConsole.getController(JSConstants.Jack.RIGHT).write(JSConstants.DigitalPin.Four, this.bool(a & 0x8));
        }
        else if ((addr & 0x7) == 0x1) {
            this.myDDRA = value;
        }
        else {
            if ((addr & 0x7) == 0x2) {
                return;
            }
            if ((addr & 0x7) == 0x3) {
                return;
            }
            if ((addr & 0x17) == 0x14) {
                this.myTimer = value;
                this.myIntervalShift = 0;
                this.myCyclesWhenTimerSet = this.mySystem.getCycles();
                this.myTimerReadAfterInterrupt = false;
            }
            else if ((addr & 0x17) == 0x15) {
                this.myTimer = value;
                this.myIntervalShift = 3;
                this.myCyclesWhenTimerSet = this.mySystem.getCycles();
                this.myTimerReadAfterInterrupt = false;
            }
            else if ((addr & 0x17) == 0x16) {
                this.myTimer = value;
                this.myIntervalShift = 6;
                this.myCyclesWhenTimerSet = this.mySystem.getCycles();
                this.myTimerReadAfterInterrupt = false;
            }
            else if ((addr & 0x17) == 0x17) {
                this.myTimer = value;
                this.myIntervalShift = 10;
                this.myCyclesWhenTimerSet = this.mySystem.getCycles();
                this.myTimerReadAfterInterrupt = false;
            }
            else if ((addr & 0x14) == 0x4) {}
        }
    }
    
    static {
        JSRiot.mvRandom = new Random();
    }
}
