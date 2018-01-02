// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import java.util.Arrays;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class JSTIA implements IfcDevice, Serializable
{
    private static final long serialVersionUID = -1703217043035095708L;
    private static final int[] COSMICBUG_MOVEMENT;
    private boolean M0Disabled;
    private boolean M1Disabled;
    private int[] myCurrentM0Mask;
    private int[] myCurrentM1Mask;
    private int[] myCurrentP0Mask;
    private int[] myCurrentP1Mask;
    private int[] myCurrentBLMask;
    private int[] myTIAPokeRegister;
    private JSConsole myConsole;
    private JSSystem mySystem;
    private boolean myColorLossEnabled;
    private boolean myPartialFrameFlag;
    private int myFramePointer;
    private int myFrameXStart;
    private int myClockWhenFrameStarted;
    private int myClockStartDisplay;
    private int myClockStopDisplay;
    private int myClockAtLastUpdate;
    private int myClocksToEndOfScanLine;
    private int myScanlineCountForLastFrame;
    private int myCurrentScanline;
    private int myMaximumNumberOfScanlines;
    private int myVSYNCFinishClock;
    private int myEnabledObjects;
    private int myPlayfieldPriorityAndScore;
    private int myVBlankOff;
    private int myVBlankOn;
    private int myVSyncOn;
    private int myDetectedYStart;
    private int myDetectedYStop;
    private int myDGRP0;
    private int myDGRP1;
    private boolean myDENABL;
    private int myCollision;
    private int myPOSP0;
    private int myPOSP1;
    private int myPOSM0;
    private int myPOSM1;
    private int myPOSBL;
    private int myCurrentGRP0;
    private int myCurrentGRP1;
    private int myDumpDisabledCycle;
    private boolean myDumpEnabled;
    private int myLastHMOVEClock;
    private boolean myHMOVEBlankEnabled;
    private boolean myAllowHMOVEBlanks;
    private boolean myM0CosmicArkMotionEnabled;
    private int myM0CosmicArkCounter;
    private transient long debugInstructionsExecuted;
    private transient boolean debugHasExecutionOverrun;
    private boolean[] debugRenderTypes;
    private boolean debugStraightPoke;
    private boolean debugLockP0Mask;
    private boolean debugLockP1Mask;
    private boolean[] debugRegLocked;
    
    protected JSTIA(final JSConsole console) {
        this.M0Disabled = false;
        this.M1Disabled = false;
        this.myCurrentM0Mask = new int[4];
        this.myCurrentM1Mask = new int[4];
        this.myCurrentP0Mask = new int[4];
        this.myCurrentP1Mask = new int[4];
        this.myCurrentBLMask = new int[3];
        this.myTIAPokeRegister = new int[45];
        this.myConsole = null;
        this.mySystem = null;
        this.myFramePointer = 0;
        this.myFrameXStart = 0;
        this.myClockWhenFrameStarted = 0;
        this.myClockStartDisplay = 0;
        this.myClockStopDisplay = 0;
        this.myClockAtLastUpdate = 0;
        this.myClocksToEndOfScanLine = 0;
        this.myScanlineCountForLastFrame = 0;
        this.myCurrentScanline = 0;
        this.myMaximumNumberOfScanlines = 0;
        this.myVSYNCFinishClock = 0;
        this.myEnabledObjects = 0;
        this.myPlayfieldPriorityAndScore = 0;
        this.myVBlankOff = 0;
        this.myVBlankOn = 0;
        this.myVSyncOn = 0;
        this.myDetectedYStart = 0;
        this.myDetectedYStop = 0;
        this.myDGRP0 = 0;
        this.myDGRP1 = 0;
        this.myCollision = 0;
        this.myPOSP0 = 0;
        this.myPOSP1 = 0;
        this.myPOSM0 = 0;
        this.myPOSM1 = 0;
        this.myPOSBL = 0;
        this.myCurrentGRP0 = 0;
        this.myCurrentGRP1 = 0;
        this.myDumpDisabledCycle = 0;
        this.myDumpEnabled = false;
        this.myLastHMOVEClock = 0;
        this.myHMOVEBlankEnabled = false;
        this.myAllowHMOVEBlanks = false;
        this.myM0CosmicArkMotionEnabled = true;
        this.myM0CosmicArkCounter = 0;
        this.debugInstructionsExecuted = 0L;
        this.debugHasExecutionOverrun = false;
        this.debugRenderTypes = new boolean[256];
        this.debugStraightPoke = false;
        this.debugLockP0Mask = false;
        this.debugLockP1Mask = false;
        this.debugRegLocked = new boolean[44];
        this.myConsole = console;
        this.myColorLossEnabled = false;
        this.myMaximumNumberOfScanlines = 262;
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
    
    private int getCOLUBK() {
        return this.myTIAPokeRegister[9];
    }
    
    private void setCOLUBK(final int aValue) {
        this.myTIAPokeRegister[9] = aValue;
    }
    
    private int getCOLUPF() {
        return this.myTIAPokeRegister[8];
    }
    
    private void setCOLUPF(final int aValue) {
        this.myTIAPokeRegister[8] = aValue;
    }
    
    private int getCOLUP0() {
        return this.myTIAPokeRegister[6];
    }
    
    private void setCOLUP0(final int aValue) {
        this.myTIAPokeRegister[6] = aValue;
    }
    
    private int getCOLUP1() {
        return this.myTIAPokeRegister[7];
    }
    
    private void setCOLUP1(final int aValue) {
        this.myTIAPokeRegister[7] = aValue;
    }
    
    public int[] getCurrentFrameBuffer() {
        return this.myConsole.getVideo().getCurrentFrameBuffer();
    }
    
    public int getVSyncOn() {
        return this.myVSyncOn;
    }
    
    public int getDetectedYStart() {
        return this.myDetectedYStart;
    }
    
    public int getDetectedYStop() {
        return this.myDetectedYStop;
    }
    
    protected int scanlines() {
        return (this.mySystem.getCycles() * 3 - this.myClockWhenFrameStarted) / 228;
    }
    
    private JSConsole getConsole() {
        return this.myConsole;
    }
    
    private int getCurrentP0Mask(final int aIndex) {
        return JSConstants.PLAYER_MASK_TABLE[this.myCurrentP0Mask[0]][this.myCurrentP0Mask[1]][this.myCurrentP0Mask[2]][this.myCurrentP0Mask[3] + aIndex];
    }
    
    private int getCurrentP1Mask(final int aIndex) {
        return JSConstants.PLAYER_MASK_TABLE[this.myCurrentP1Mask[0]][this.myCurrentP1Mask[1]][this.myCurrentP1Mask[2]][this.myCurrentP1Mask[3] + aIndex];
    }
    
    private boolean getCurrentM0Mask(final int aIndex) {
        if (this.M0Disabled) {
            return bool(JSConstants.DISABLED_MASK_TABLE[aIndex]);
        }
        assert this.myCurrentM0Mask[3] + aIndex < 360;
        return JSConstants.MISSILE_MASK_TABLE[this.myCurrentM0Mask[0]][this.myCurrentM0Mask[1]][this.myCurrentM0Mask[2]][this.myCurrentM0Mask[3] + aIndex];
    }
    
    private boolean getCurrentM1Mask(final int aIndex) {
        if (this.M1Disabled) {
            return bool(JSConstants.DISABLED_MASK_TABLE[aIndex]);
        }
        assert this.myCurrentM1Mask[3] + aIndex < 360;
        return JSConstants.MISSILE_MASK_TABLE[this.myCurrentM1Mask[0]][this.myCurrentM1Mask[1]][this.myCurrentM1Mask[2]][this.myCurrentM1Mask[3] + aIndex];
    }
    
    private void setCurrentM1Mask(final int aA, final int aB, final int aC, final int aD) {
        this.myCurrentM1Mask[0] = aA;
        this.myCurrentM1Mask[1] = aB;
        this.myCurrentM1Mask[2] = aC;
        this.myCurrentM1Mask[3] = aD;
        this.M1Disabled = false;
    }
    
    private void setCurrentP0Mask(final int aA, final int aB, final int aC, final int aD) {
        if (!this.debugLockP0Mask) {
            this.myCurrentP0Mask[0] = aA;
            this.myCurrentP0Mask[1] = aB;
            this.myCurrentP0Mask[2] = aC;
            this.myCurrentP0Mask[3] = aD;
        }
    }
    
    private void setCurrentP1Mask(final int aA, final int aB, final int aC, final int aD) {
        if (!this.debugLockP1Mask) {
            this.myCurrentP1Mask[0] = aA;
            this.myCurrentP1Mask[1] = aB;
            this.myCurrentP1Mask[2] = aC;
            this.myCurrentP1Mask[3] = aD;
        }
    }
    
    private void setCurrentM0Mask(final int aA, final int aB, final int aC, final int aD) {
        this.myCurrentM0Mask[0] = aA;
        this.myCurrentM0Mask[1] = aB;
        this.myCurrentM0Mask[2] = aC;
        this.myCurrentM0Mask[3] = aD;
        this.M0Disabled = false;
    }
    
    private void setCurrentM0MaskDisabled() {
        this.M0Disabled = true;
    }
    
    private boolean getCurrentBLMask(final int aIndex) {
        return JSConstants.BALL_MASK_TABLE[this.myCurrentBLMask[0]][this.myCurrentBLMask[1]][this.myCurrentBLMask[2] + aIndex];
    }
    
    private void setCurrentBLMask(final int aA, final int aB, final int aC) {
        this.myCurrentBLMask[0] = aA;
        this.myCurrentBLMask[1] = aB;
        this.myCurrentBLMask[2] = aC;
    }
    
    private int getYStart() {
        return this.myConsole.getYStart();
    }
    
    private int getDisplayHeight() {
        return this.myConsole.getDisplayHeight();
    }
    
    private static boolean isBitOn(final int aBitNumber, final int aValue) {
        return (aValue & 1 << aBitNumber) != 0x0;
    }
    
    private JSAudio getAudio() {
        return this.myConsole.getAudio();
    }
    
    private int getCurrentClockCount() {
        return this.mySystem.getCycles() * 3;
    }
    
    private int getCurrentXPos() {
        return (this.getCurrentClockCount() - this.myClockWhenFrameStarted) % 228;
    }
    
    private int getCurrentScanline() {
        return (this.getCurrentClockCount() - this.myClockWhenFrameStarted) / 228;
    }
    
    private int getColor(final int aIndex) {
        switch (aIndex) {
            case 0: {
                return this.getCOLUBK();
            }
            case 1: {
                return this.myTIAPokeRegister[8];
            }
            case 2: {
                return this.myTIAPokeRegister[6];
            }
            case 3: {
                return this.myTIAPokeRegister[7];
            }
            default: {
                assert false;
                return 0;
            }
        }
    }
    
    public String name() {
        return "TIA";
    }
    
    public void reset() {
        this.getAudio().reset();
        for (int i = 0; i < this.myTIAPokeRegister.length; ++i) {
            this.myTIAPokeRegister[i] = 0;
        }
        this.myEnabledObjects = 0;
        this.myPlayfieldPriorityAndScore = 0;
        this.myDGRP0 = 0;
        this.myDGRP1 = 0;
        this.myDENABL = false;
        this.myCollision = 0;
        this.myPOSP0 = 0;
        this.myPOSP1 = 0;
        this.myPOSM0 = 0;
        this.myPOSM1 = 0;
        this.myPOSBL = 0;
        this.myCurrentGRP0 = 0;
        this.setCurrentBLMask(this.myCurrentGRP1 = 0, 0, 0);
        this.setCurrentM0Mask(0, 0, 0, 0);
        this.setCurrentM1Mask(0, 0, 0, 0);
        this.setCurrentP0Mask(0, 0, 0, 0);
        this.setCurrentP1Mask(0, 0, 0, 0);
        this.myLastHMOVEClock = 0;
        this.myHMOVEBlankEnabled = false;
        this.myM0CosmicArkMotionEnabled = false;
        this.myM0CosmicArkCounter = 0;
        this.myDumpEnabled = false;
        this.myDumpDisabledCycle = 0;
        this.myAllowHMOVEBlanks = true;
        if (this.myConsole.getDisplayFormat() == JSConstants.DisplayFormat.PAL || this.myConsole.getDisplayFormat() == JSConstants.DisplayFormat.PAL60) {
            this.myColorLossEnabled = true;
            this.myMaximumNumberOfScanlines = 342;
        }
        else {
            this.myColorLossEnabled = false;
            this.myMaximumNumberOfScanlines = 290;
        }
        this.myVBlankOff = 0;
        this.myVBlankOn = 0;
        this.myVSyncOn = -1;
        this.myDetectedYStart = 0;
        this.myDetectedYStop = 0;
        this.debugHasExecutionOverrun = false;
        this.frameReset();
    }
    
    protected void frameReset() {
        this.myConsole.getVideo().clearBuffers();
        this.myFramePointer = 0;
        this.myClockWhenFrameStarted = this.mySystem.getCycles() * 3;
        this.myClockStartDisplay = this.myClockWhenFrameStarted + 228 * this.getYStart();
        this.myClockStopDisplay = this.myClockWhenFrameStarted + 228 * (this.getYStart() + this.getDisplayHeight());
        this.myClockAtLastUpdate = this.myClockWhenFrameStarted;
        this.myClocksToEndOfScanLine = 228;
        this.myVSYNCFinishClock = Integer.MAX_VALUE;
        this.myScanlineCountForLastFrame = 0;
        this.myCurrentScanline = 0;
        this.myFrameXStart = 0;
    }
    
    public void systemCyclesReset() {
        final int cycles = this.mySystem.getCycles();
        if (this.getAudio() != null) {
            this.getAudio().systemCyclesReset(cycles);
        }
        this.myDumpDisabledCycle -= cycles;
        final int clocks = cycles * 3;
        this.myClockWhenFrameStarted -= clocks;
        this.myClockStartDisplay -= clocks;
        this.myClockStopDisplay -= clocks;
        this.myClockAtLastUpdate -= clocks;
        this.myVSYNCFinishClock -= clocks;
        this.myLastHMOVEClock -= clocks;
    }
    
    private void setCurrentFrameBuffer(final int aIndex, final int aValue) {
        this.getCurrentFrameBuffer()[aIndex] = aValue;
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        final int shift = 6;
        this.mySystem.resetCycles();
        final PageAccess access = new PageAccess(this);
        access.setIndirectMode();
        for (int i = 0; i < 8192; i += 1 << shift) {
            if ((i & 0x1080) == 0x0) {
                this.mySystem.setPageAccess((char)(i >> shift), access);
            }
        }
    }
    
    protected void processFrame() throws JSException {
        if (!this.myPartialFrameFlag) {
            this.startFrame();
        }
        this.myPartialFrameFlag = true;
        int zExecutions = 0;
        int zInstructionsExecuted = 0;
        do {
            zInstructionsExecuted += this.mySystem.executeCPU(25000);
            ++zExecutions;
            if (!this.myPartialFrameFlag) {
                break;
            }
        } while (zExecutions < 3);
        if (zExecutions >= 3) {
            if (!this.debugHasExecutionOverrun) {
                System.out.println("debug: ********** Execution overrun in TIA *********");
            }
            this.debugHasExecutionOverrun = true;
        }
        final int totalClocks = this.mySystem.getCycles() * 3 - this.myClockWhenFrameStarted;
        this.myCurrentScanline = totalClocks / 228;
        if (!this.myPartialFrameFlag) {
            this.endFrame();
        }
    }
    
    private static boolean bool(final int aValue) {
        return aValue != 0;
    }
    
    private void startFrame() {
        this.myConsole.getVideo().swapFrameBuffers();
        final int clocks = (this.mySystem.getCycles() * 3 - this.myClockWhenFrameStarted) % 228;
        this.mySystem.resetCycles();
        this.myClockWhenFrameStarted = -1 * clocks;
        this.myClockStartDisplay = this.myClockWhenFrameStarted + 228 * this.getYStart();
        this.myClockStopDisplay = this.myClockWhenFrameStarted + 228 * (this.getYStart() + this.getDisplayHeight());
        this.myClockAtLastUpdate = this.myClockStartDisplay;
        this.myClocksToEndOfScanLine = 228;
        this.myFramePointer = 0;
        if (this.myColorLossEnabled) {
            if ((this.myScanlineCountForLastFrame & 0x1) != 0x0) {
                final int[] myTIAPokeRegister = this.myTIAPokeRegister;
                final int n = 6;
                myTIAPokeRegister[n] |= 0x1010101;
                final int[] myTIAPokeRegister2 = this.myTIAPokeRegister;
                final int n2 = 7;
                myTIAPokeRegister2[n2] |= 0x1010101;
                final int[] myTIAPokeRegister3 = this.myTIAPokeRegister;
                final int n3 = 8;
                myTIAPokeRegister3[n3] |= 0x1010101;
                final int[] myTIAPokeRegister4 = this.myTIAPokeRegister;
                final int n4 = 9;
                myTIAPokeRegister4[n4] |= 0x1010101;
            }
            else {
                final int[] myTIAPokeRegister5 = this.myTIAPokeRegister;
                final int n5 = 6;
                myTIAPokeRegister5[n5] &= 0xFEFEFEFE;
                final int[] myTIAPokeRegister6 = this.myTIAPokeRegister;
                final int n6 = 7;
                myTIAPokeRegister6[n6] &= 0xFEFEFEFE;
                final int[] myTIAPokeRegister7 = this.myTIAPokeRegister;
                final int n7 = 8;
                myTIAPokeRegister7[n7] &= 0xFEFEFEFE;
                final int[] myTIAPokeRegister8 = this.myTIAPokeRegister;
                final int n8 = 9;
                myTIAPokeRegister8[n8] &= 0xFEFEFEFE;
            }
        }
    }
    
    private void endFrame() {
        this.myScanlineCountForLastFrame = this.myCurrentScanline;
    }
    
    private int clocksThisLine() {
        final int totalClocks = this.mySystem.getCycles() * 3 - this.myClockWhenFrameStarted;
        return totalClocks % 228;
    }
    
    private boolean isPlayfieldPixelOn(final int aHPos) {
        int zPFBlock = aHPos / 4;
        if (zPFBlock >= 20) {
            if (isBitOn(0, this.myTIAPokeRegister[10])) {
                zPFBlock = 39 - zPFBlock;
            }
            else {
                zPFBlock -= 20;
            }
        }
        if (zPFBlock < 4) {
            return isBitOn(4 + zPFBlock, this.myTIAPokeRegister[13]);
        }
        if (zPFBlock < 12) {
            return isBitOn(11 - zPFBlock, this.myTIAPokeRegister[14]);
        }
        return isBitOn(zPFBlock - 12, this.myTIAPokeRegister[15]);
    }
    
    private boolean isPlayer0PixelOn(final int aHPos) {
        return (this.myCurrentGRP0 & this.getCurrentP0Mask(aHPos)) != 0x0;
    }
    
    private boolean isPlayer1PixelOn(final int aHPos) {
        return (this.myCurrentGRP1 & this.getCurrentP1Mask(aHPos)) != 0x0;
    }
    
    private boolean isMissile0PixelOn(final int aHPos) {
        return this.getCurrentM0Mask(aHPos);
    }
    
    private boolean isMissile1PixelOn(final int aHPos) {
        return this.getCurrentM1Mask(aHPos);
    }
    
    private boolean isRESMP0() {
        return (this.myTIAPokeRegister[40] & 0x2) != 0x0;
    }
    
    private boolean isRESMP1() {
        return (this.myTIAPokeRegister[41] & 0x2) != 0x0;
    }
    
    private void updatePlayfieldStatus() {
        if ((this.myTIAPokeRegister[13] & 0xF0) == 0x0 && this.myTIAPokeRegister[14] == 0 && this.myTIAPokeRegister[15] == 0) {
            this.myEnabledObjects &= 0xFFFFFFDF;
        }
        else {
            this.myEnabledObjects |= 0x20;
        }
    }
    
    private void memsetFrameBuffer(final int aIndex, final int aByteValue, final int aCount) {
        Arrays.fill(this.getCurrentFrameBuffer(), aIndex, aIndex + aCount, aByteValue & 0xFF);
    }
    
    private void updateFrameScanline(final int clocksToUpdate, int hpos) {
        final int zEnding = this.myFramePointer + clocksToUpdate;
        if (isBitOn(1, this.myTIAPokeRegister[1])) {
            this.memsetFrameBuffer(this.myFramePointer, 0, clocksToUpdate);
        }
        else {
            final int zDebugSwitch = this.myEnabledObjects | this.myPlayfieldPriorityAndScore;
            if (zDebugSwitch >= 0 && zDebugSwitch < 256) {
                this.debugRenderTypes[zDebugSwitch] = true;
            }
            switch (this.myEnabledObjects | this.myPlayfieldPriorityAndScore) {
                case 0:
                case 64:
                case 128:
                case 192: {
                    this.memsetFrameBuffer(this.myFramePointer, this.myTIAPokeRegister[9], clocksToUpdate);
                    break;
                }
                case 32:
                case 160: {
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.isPlayfieldPixelOn(hpos) ? this.myTIAPokeRegister[8] : this.myTIAPokeRegister[9]);
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 96:
                case 224: {
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.isPlayfieldPixelOn(hpos) ? ((hpos < 80) ? this.myTIAPokeRegister[6] : this.myTIAPokeRegister[7]) : this.myTIAPokeRegister[9]);
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 1:
                case 65:
                case 129:
                case 193: {
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayer0Pixel = this.isPlayer0PixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, zPlayer0Pixel ? this.myTIAPokeRegister[6] : this.myTIAPokeRegister[9]);
                        ++hpos;
                        ++this.myFramePointer;
                    }
                    break;
                }
                case 4:
                case 68:
                case 132:
                case 196: {
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.isPlayer1PixelOn(hpos) ? this.myTIAPokeRegister[7] : this.myTIAPokeRegister[9]);
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 5:
                case 69:
                case 133:
                case 197: {
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayer0Pixel = this.isPlayer0PixelOn(hpos);
                        final boolean zPlayer1Pixel = this.isPlayer1PixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, zPlayer0Pixel ? this.myTIAPokeRegister[6] : (zPlayer1Pixel ? this.myTIAPokeRegister[7] : this.myTIAPokeRegister[9]));
                        if (zPlayer0Pixel && zPlayer1Pixel) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[5];
                        }
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 2:
                case 66:
                case 130:
                case 194: {
                    int mM0 = hpos;
                    while (this.myFramePointer < zEnding) {
                        final boolean zMMask = this.getCurrentM0Mask(mM0);
                        this.setCurrentFrameBuffer(this.myFramePointer, zMMask ? this.myTIAPokeRegister[6] : this.myTIAPokeRegister[9]);
                        ++mM0;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 8:
                case 72:
                case 136:
                case 200: {
                    int mM2 = hpos;
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getCurrentM1Mask(mM2) ? this.myTIAPokeRegister[7] : this.myTIAPokeRegister[9]);
                        ++mM2;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 16:
                case 80:
                case 144:
                case 208: {
                    int mBL = hpos;
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getCurrentBLMask(mBL) ? this.myTIAPokeRegister[8] : this.myTIAPokeRegister[9]);
                        ++mBL;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 10:
                case 74:
                case 138:
                case 202: {
                    int mM0 = hpos;
                    int mM3 = hpos;
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getCurrentM0Mask(mM0) ? this.myTIAPokeRegister[6] : (this.getCurrentM1Mask(mM3) ? this.myTIAPokeRegister[7] : this.myTIAPokeRegister[9]));
                        if (this.getCurrentM0Mask(mM0) && this.getCurrentM1Mask(mM3)) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[10];
                        }
                        ++hpos;
                        ++mM0;
                        ++mM3;
                        ++this.myFramePointer;
                    }
                    break;
                }
                case 18:
                case 82: {
                    int mBL = hpos;
                    int mM4 = hpos;
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getCurrentM0Mask(mM4) ? this.myTIAPokeRegister[6] : (this.getCurrentBLMask(mBL) ? this.myTIAPokeRegister[8] : this.myTIAPokeRegister[9]));
                        if (this.getCurrentBLMask(mBL) && this.getCurrentM0Mask(mM4)) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[18];
                        }
                        ++mBL;
                        ++mM4;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 146:
                case 210: {
                    int mBL = hpos;
                    int mM4 = hpos;
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getCurrentBLMask(mBL) ? this.myTIAPokeRegister[8] : (this.getCurrentM0Mask(mM4) ? this.myTIAPokeRegister[6] : this.myTIAPokeRegister[9]));
                        if (this.getCurrentBLMask(mBL) && this.getCurrentM0Mask(mM4)) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[18];
                        }
                        ++mBL;
                        ++mM4;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 24:
                case 88: {
                    int mBL = hpos;
                    int mM3 = hpos;
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getCurrentM1Mask(mM3) ? this.myTIAPokeRegister[7] : (this.getCurrentBLMask(mBL) ? this.myTIAPokeRegister[8] : this.myTIAPokeRegister[9]));
                        if (this.getCurrentBLMask(mBL) && this.getCurrentM1Mask(mM3)) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[24];
                        }
                        ++mBL;
                        ++mM3;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 152:
                case 216: {
                    int mBL = hpos;
                    int mM3 = hpos;
                    while (this.myFramePointer < zEnding) {
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getCurrentBLMask(mBL) ? this.myTIAPokeRegister[8] : (this.getCurrentM1Mask(mM3) ? this.myTIAPokeRegister[7] : this.myTIAPokeRegister[9]));
                        if (this.getCurrentBLMask(mBL) && this.getCurrentM1Mask(mM3)) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[24];
                        }
                        ++mBL;
                        ++mM3;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 20:
                case 84: {
                    int mBL = hpos;
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayer1Pixel = this.isPlayer1PixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, zPlayer1Pixel ? this.myTIAPokeRegister[7] : (this.getCurrentBLMask(mBL) ? this.myTIAPokeRegister[8] : this.myTIAPokeRegister[9]));
                        if (this.getCurrentBLMask(mBL) && zPlayer1Pixel) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[20];
                        }
                        ++mBL;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 148:
                case 212: {
                    int mBL = hpos;
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayer1Pixel = this.isPlayer1PixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getCurrentBLMask(mBL) ? this.myTIAPokeRegister[8] : (zPlayer1Pixel ? this.myTIAPokeRegister[7] : this.myTIAPokeRegister[9]));
                        if (this.getCurrentBLMask(mBL) && zPlayer1Pixel) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[20];
                        }
                        ++mBL;
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
                case 33: {
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayfieldIsOn = this.isPlayfieldPixelOn(hpos);
                        final boolean zPlayer0Pixel2 = this.isPlayer0PixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, zPlayer0Pixel2 ? this.myTIAPokeRegister[6] : (zPlayfieldIsOn ? this.myTIAPokeRegister[8] : this.myTIAPokeRegister[9]));
                        if (zPlayfieldIsOn && zPlayer0Pixel2) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[33];
                        }
                        ++hpos;
                        ++this.myFramePointer;
                    }
                    break;
                }
                case 161: {
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayfieldIsOn = this.isPlayfieldPixelOn(hpos);
                        final boolean zPlayer0Pixel2 = this.isPlayer0PixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, zPlayfieldIsOn ? this.myTIAPokeRegister[8] : (zPlayer0Pixel2 ? this.myTIAPokeRegister[6] : this.myTIAPokeRegister[9]));
                        if (zPlayfieldIsOn && zPlayer0Pixel2) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[33];
                        }
                        ++hpos;
                        ++this.myFramePointer;
                    }
                    break;
                }
                case 36: {
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayfieldIsOn = this.isPlayfieldPixelOn(hpos);
                        final boolean zPlayer1Pixel = this.isPlayer1PixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, zPlayer1Pixel ? this.myTIAPokeRegister[7] : (zPlayfieldIsOn ? this.myTIAPokeRegister[8] : this.myTIAPokeRegister[9]));
                        if (zPlayfieldIsOn && zPlayer1Pixel) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[36];
                        }
                        ++hpos;
                        ++this.myFramePointer;
                    }
                    break;
                }
                case 164: {
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayfieldIsOn = this.isPlayfieldPixelOn(hpos);
                        final boolean zPlayer1Pixel = this.isPlayer1PixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, zPlayfieldIsOn ? this.myTIAPokeRegister[8] : (zPlayer1Pixel ? this.myTIAPokeRegister[7] : this.myTIAPokeRegister[9]));
                        if (zPlayfieldIsOn && zPlayer1Pixel) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[36];
                        }
                        ++hpos;
                        ++this.myFramePointer;
                    }
                    break;
                }
                case 48:
                case 176: {
                    int mBL = hpos;
                    while (this.myFramePointer < zEnding) {
                        final boolean zPlayfieldIsOn2 = this.isPlayfieldPixelOn(hpos);
                        this.setCurrentFrameBuffer(this.myFramePointer, (zPlayfieldIsOn2 || this.getCurrentBLMask(mBL)) ? this.myTIAPokeRegister[8] : this.myTIAPokeRegister[9]);
                        if (zPlayfieldIsOn2 && this.getCurrentBLMask(mBL)) {
                            this.myCollision |= JSConstants.COLLISION_TABLE[48];
                        }
                        ++hpos;
                        ++mBL;
                        ++this.myFramePointer;
                    }
                    break;
                }
                default: {
                    while (this.myFramePointer < zEnding) {
                        int enabled = this.isPlayfieldPixelOn(hpos) ? 32 : 0;
                        if (bool(this.myEnabledObjects & 0x10) && this.getCurrentBLMask(hpos)) {
                            enabled |= 0x10;
                        }
                        if (this.isPlayer1PixelOn(hpos)) {
                            enabled |= 0x4;
                        }
                        if (bool(this.myEnabledObjects & 0x8) && this.getCurrentM1Mask(hpos)) {
                            enabled |= 0x8;
                        }
                        if (this.isPlayer0PixelOn(hpos)) {
                            enabled |= 0x1;
                        }
                        if (bool(this.myEnabledObjects & 0x2) && this.getCurrentM0Mask(hpos)) {
                            enabled |= 0x2;
                        }
                        this.myCollision |= JSConstants.COLLISION_TABLE[enabled];
                        this.setCurrentFrameBuffer(this.myFramePointer, this.getColor(JSConstants.PRIORITY_ENCODER[hpos >= 80][enabled | this.myPlayfieldPriorityAndScore]));
                        ++this.myFramePointer;
                        ++hpos;
                    }
                    break;
                }
            }
        }
        this.myFramePointer = zEnding;
    }
    
    private void updateFrameScanlineSimple(final int aClocksToUpdate, int aHPos) {
        final int zEnding = this.myFramePointer + aClocksToUpdate;
        if (isBitOn(1, this.myTIAPokeRegister[1])) {
            this.memsetFrameBuffer(this.myFramePointer, 0, aClocksToUpdate);
        }
        else {
            final boolean zBallEnabled = (this.myEnabledObjects & 0x10) != 0x0;
            final boolean zPlayfieldEnabled = (this.myEnabledObjects & 0x20) != 0x0;
            final boolean zPlayer0Enabled = (this.myEnabledObjects & 0x1) != 0x0;
            final boolean zPlayer1Enabled = (this.myEnabledObjects & 0x4) != 0x0;
            final boolean zMissile0Enabled = (this.myEnabledObjects & 0x2) != 0x0;
            final boolean zMissile1Enabled = (this.myEnabledObjects & 0x8) != 0x0;
            while (this.myFramePointer < zEnding) {
                int enabled = 0;
                if (zPlayfieldEnabled && this.isPlayfieldPixelOn(aHPos)) {
                    enabled |= 0x20;
                }
                if (zBallEnabled && this.getCurrentBLMask(aHPos)) {
                    enabled |= 0x10;
                }
                if (zPlayer1Enabled && this.isPlayer1PixelOn(aHPos)) {
                    enabled |= 0x4;
                }
                if (zMissile1Enabled && this.getCurrentM1Mask(aHPos)) {
                    enabled |= 0x8;
                }
                if (zPlayer0Enabled && this.isPlayer0PixelOn(aHPos)) {
                    enabled |= 0x1;
                }
                if (zMissile0Enabled && this.getCurrentM0Mask(aHPos)) {
                    enabled |= 0x2;
                }
                this.myCollision |= JSConstants.COLLISION_TABLE[enabled];
                this.setCurrentFrameBuffer(this.myFramePointer, this.getColor(JSConstants.PRIORITY_ENCODER[aHPos >= 80][enabled | this.myPlayfieldPriorityAndScore]));
                ++this.myFramePointer;
                ++aHPos;
            }
        }
    }
    
    private void updateFrame(int clock) {
        if (clock < this.myClockStartDisplay || clock <= this.myClockAtLastUpdate || this.myClockAtLastUpdate >= this.myClockStopDisplay) {
            return;
        }
        if (clock > this.myClockStopDisplay) {
            clock = this.myClockStopDisplay;
        }
        do {
            int clocksToUpdate = 0;
            int clocksFromStartOfScanLine = 228 - this.myClocksToEndOfScanLine;
            if (clock > this.myClockAtLastUpdate + this.myClocksToEndOfScanLine) {
                clocksToUpdate = this.myClocksToEndOfScanLine;
                this.myClocksToEndOfScanLine = 228;
                this.myClockAtLastUpdate += clocksToUpdate;
            }
            else {
                clocksToUpdate = clock - this.myClockAtLastUpdate;
                this.myClocksToEndOfScanLine -= clocksToUpdate;
                this.myClockAtLastUpdate = clock;
            }
            final int startOfScanLine = 68 + this.myFrameXStart;
            if (clocksFromStartOfScanLine < startOfScanLine) {
                int tmp;
                if (startOfScanLine - clocksFromStartOfScanLine < clocksToUpdate) {
                    tmp = startOfScanLine - clocksFromStartOfScanLine;
                }
                else {
                    tmp = clocksToUpdate;
                }
                clocksFromStartOfScanLine += tmp;
                clocksToUpdate -= tmp;
            }
            final int oldFramePointer = this.myFramePointer;
            if (clocksToUpdate != 0) {
                this.updateFrameScanline(clocksToUpdate, clocksFromStartOfScanLine - 68);
            }
            if (this.myHMOVEBlankEnabled && startOfScanLine < 76 && clocksFromStartOfScanLine < 76) {
                final int blanks = 76 - clocksFromStartOfScanLine;
                this.memsetFrameBuffer(oldFramePointer, 0, blanks);
                if (clocksToUpdate + clocksFromStartOfScanLine >= 76) {
                    this.myHMOVEBlankEnabled = false;
                }
            }
            if (this.myClocksToEndOfScanLine == 228) {
                this.myFramePointer -= 160 - this.myConsole.getDisplayWidth() - this.myFrameXStart;
                this.setCurrentP0Mask(this.myPOSP0 & 0x3, 0, this.myTIAPokeRegister[4] & 0x7, 160 - (this.myPOSP0 & 0xFC));
                this.setCurrentP1Mask(this.myPOSP1 & 0x3, 0, this.myTIAPokeRegister[5] & 0x7, 160 - (this.myPOSP1 & 0xFC));
                if (!this.myM0CosmicArkMotionEnabled) {
                    continue;
                }
                this.emulateCosmicBug();
            }
        } while (this.myClockAtLastUpdate < clock);
    }
    
    private void emulateCosmicBug() {
        this.myM0CosmicArkCounter = (this.myM0CosmicArkCounter + 1 & 0x3);
        this.myPOSM0 -= JSTIA.COSMICBUG_MOVEMENT[this.myM0CosmicArkCounter];
        if (this.myPOSM0 >= 160) {
            this.myPOSM0 -= 160;
        }
        else if (this.myPOSM0 < 0) {
            this.myPOSM0 += 160;
        }
        if (this.myM0CosmicArkCounter == 1) {
            this.setCurrentM0Mask(this.myPOSM0 & 0x3, this.myTIAPokeRegister[4] & 0x7, (this.myTIAPokeRegister[4] & 0x30) >> 4 | 0x1, 160 - (this.myPOSM0 & 0xFC));
        }
        else if (this.myM0CosmicArkCounter == 2) {
            this.setCurrentM0MaskDisabled();
        }
        else {
            this.setCurrentM0Mask(this.myPOSM0 & 0x3, this.myTIAPokeRegister[4] & 0x7, (this.myTIAPokeRegister[4] & 0x30) >> 4, 160 - (this.myPOSM0 & 0xFC));
        }
    }
    
    private void waitHorizontalSync() {
        final int cyclesToEndOfLine = 76 - (this.mySystem.getCycles() - this.myClockWhenFrameStarted / 3) % 76;
        if (cyclesToEndOfLine < 76) {
            this.mySystem.incrementCycles(cyclesToEndOfLine);
        }
    }
    
    public int peek(final int addr) {
        int zReturn = 0;
        assert addr >= 0;
        this.updateFrame(this.mySystem.getCycles() * 3);
        final int noise = this.mySystem.getDataBusState() & 0x3F;
        switch (addr & 0xF) {
            case 0: {
                zReturn = ((bool(this.myCollision & 0x1) ? 128 : 0) | (bool(this.myCollision & 0x2) ? 64 : 0) | noise);
                break;
            }
            case 1: {
                zReturn = ((bool(this.myCollision & 0x4) ? 128 : 0) | (bool(this.myCollision & 0x8) ? 64 : 0) | noise);
                break;
            }
            case 2: {
                zReturn = ((bool(this.myCollision & 0x10) ? 128 : 0) | (bool(this.myCollision & 0x20) ? 64 : 0) | noise);
                break;
            }
            case 3: {
                zReturn = ((bool(this.myCollision & 0x40) ? 128 : 0) | (bool(this.myCollision & 0x80) ? 64 : 0) | noise);
                break;
            }
            case 4: {
                zReturn = ((bool(this.myCollision & 0x100) ? 128 : 0) | (bool(this.myCollision & 0x200) ? 64 : 0) | noise);
                break;
            }
            case 5: {
                zReturn = ((bool(this.myCollision & 0x400) ? 128 : 0) | (bool(this.myCollision & 0x800) ? 64 : 0) | noise);
                break;
            }
            case 6: {
                zReturn = ((bool(this.myCollision & 0x1000) ? 128 : 0) | noise);
                break;
            }
            case 7: {
                zReturn = ((bool(this.myCollision & 0x2000) ? 128 : 0) | (bool(this.myCollision & 0x4000) ? 64 : 0) | noise);
                break;
            }
            case 8: {
                final int r = this.myConsole.getController(JSConstants.Jack.LEFT).read(JSConstants.AnalogPin.Nine);
                if (r == 0) {
                    zReturn = (0x80 | noise);
                    break;
                }
                if (r == Integer.MAX_VALUE || this.myDumpEnabled) {
                    zReturn = noise;
                    break;
                }
                final double t = 1.6 * r * 1.0E-8;
                final int needed = (int)(t * 1190000.0);
                if (this.mySystem.getCycles() > this.myDumpDisabledCycle + needed) {
                    zReturn = (0x80 | noise);
                    break;
                }
                zReturn = noise;
                break;
            }
            case 9: {
                final int r = this.myConsole.getController(JSConstants.Jack.LEFT).read(JSConstants.AnalogPin.Five);
                if (r == 0) {
                    zReturn = (0x80 | noise);
                    break;
                }
                if (r == Integer.MAX_VALUE || this.myDumpEnabled) {
                    zReturn = noise;
                    break;
                }
                final double t = 1.6 * r * 1.0E-8;
                final int needed = (int)(t * 1190000.0);
                if (this.mySystem.getCycles() > this.myDumpDisabledCycle + needed) {
                    zReturn = (0x80 | noise);
                    break;
                }
                zReturn = noise;
                break;
            }
            case 10: {
                final int r = this.myConsole.getController(JSConstants.Jack.RIGHT).read(JSConstants.AnalogPin.Nine);
                if (r == 0) {
                    zReturn = (0x80 | noise);
                    break;
                }
                if (r == Integer.MAX_VALUE || this.myDumpEnabled) {
                    zReturn = noise;
                    break;
                }
                final double t = 1.6 * r * 1.0E-8;
                final int needed = (int)(t * 1190000.0);
                if (this.mySystem.getCycles() > this.myDumpDisabledCycle + needed) {
                    zReturn = (0x80 | noise);
                    break;
                }
                zReturn = noise;
                break;
            }
            case 11: {
                final int r = this.myConsole.getController(JSConstants.Jack.RIGHT).read(JSConstants.AnalogPin.Five);
                if (r == 0) {
                    zReturn = (0x80 | noise);
                    break;
                }
                if (r == Integer.MAX_VALUE || this.myDumpEnabled) {
                    zReturn = noise;
                    break;
                }
                final double t = 1.6 * r * 1.0E-8;
                final int needed = (int)(t * 1190000.0);
                if (this.mySystem.getCycles() > this.myDumpDisabledCycle + needed) {
                    zReturn = (0x80 | noise);
                    break;
                }
                zReturn = noise;
                break;
            }
            case 12: {
                zReturn = (this.myConsole.getController(JSConstants.Jack.LEFT).read(JSConstants.DigitalPin.Six) ? (0x80 | noise) : noise);
                break;
            }
            case 13: {
                zReturn = (this.myConsole.getController(JSConstants.Jack.RIGHT).read(JSConstants.DigitalPin.Six) ? (0x80 | noise) : noise);
                break;
            }
            case 14: {
                zReturn = noise;
                break;
            }
            default: {
                zReturn = noise;
                break;
            }
        }
        assert zReturn >= 0 && zReturn < 256;
        return zReturn;
    }
    
    public void poke(final int aAddress, final int aByteValue) {
        assert aByteValue >= 0 && aByteValue < 256;
        final int addr = aAddress & 0x3F;
        final int clock = this.getCurrentClockCount();
        int delay = JSConstants.POKE_DELAY_TABLE[addr];
        if (delay == -1) {
            final int[] d = { 4, 5, 2, 3 };
            final int x = this.getCurrentXPos();
            delay = (char)d[x / 3 & 0x3];
        }
        this.updateFrame(clock + delay);
        if (this.getCurrentScanline() > this.myMaximumNumberOfScanlines) {
            this.mySystem.stopCPU();
            this.myPartialFrameFlag = false;
        }
        if (addr < 45) {
            final int zPreviousValue = this.myTIAPokeRegister[addr];
            this.myTIAPokeRegister[addr] = aByteValue;
            switch (addr) {
                case 0: {
                    if ((aByteValue & 0x2) != 0x0 && (zPreviousValue & 0x2) == 0x0) {
                        this.myVSyncOn = this.scanlines();
                    }
                    if (bool(this.myTIAPokeRegister[0] & 0x2)) {
                        this.myVSYNCFinishClock = clock + 228;
                        break;
                    }
                    if (!bool(this.myTIAPokeRegister[0] & 0x2) && clock >= this.myVSYNCFinishClock) {
                        this.myVSYNCFinishClock = Integer.MAX_VALUE;
                        this.mySystem.stopCPU();
                        this.myPartialFrameFlag = false;
                        break;
                    }
                    break;
                }
                case 1: {
                    if ((aByteValue & 0x2) != 0x0 && (zPreviousValue & 0x2) == 0x0) {
                        this.myVBlankOn = this.scanlines();
                        int zHeight = this.myVBlankOn - this.myVBlankOff;
                        if (zHeight < 0) {
                            zHeight += this.myVSyncOn;
                        }
                        if (zHeight >= 100) {
                            this.myDetectedYStart = this.myVBlankOff;
                            if (this.myDetectedYStart >= this.myVSyncOn) {
                                this.myDetectedYStart -= this.myVSyncOn;
                            }
                            this.myDetectedYStop = this.myDetectedYStart + zHeight;
                        }
                    }
                    else if ((aByteValue & 0x2) == 0x0 && (zPreviousValue & 0x2) != 0x0) {
                        this.myVBlankOff = this.scanlines();
                    }
                    if (!bool(zPreviousValue & 0x80) && bool(this.myTIAPokeRegister[1] & 0x80)) {
                        this.myDumpEnabled = true;
                        break;
                    }
                    if (bool(zPreviousValue & 0x80) && !bool(this.myTIAPokeRegister[1] & 0x80)) {
                        this.myDumpEnabled = false;
                        this.myDumpDisabledCycle = this.mySystem.getCycles();
                        break;
                    }
                    break;
                }
                case 2: {
                    if (this.mySystem.getCPU().lastAccessWasRead()) {
                        this.waitHorizontalSync();
                        break;
                    }
                    break;
                }
                case 4: {
                    this.setCurrentP0Mask(this.myPOSP0 & 0x3, 0, this.myTIAPokeRegister[4] & 0x7, 160 - (this.myPOSP0 & 0xFC));
                    this.setCurrentM0Mask(this.myPOSM0 & 0x3, this.myTIAPokeRegister[4] & 0x7, (this.myTIAPokeRegister[4] & 0x30) >> 4, 160 - (this.myPOSM0 & 0xFC));
                    break;
                }
                case 5: {
                    this.setCurrentP1Mask(this.myPOSP1 & 0x3, 0, this.myTIAPokeRegister[5] & 0x7, 160 - (this.myPOSP1 & 0xFC));
                    this.setCurrentM1Mask(this.myPOSM1 & 0x3, this.myTIAPokeRegister[5] & 0x7, (this.myTIAPokeRegister[5] & 0x30) >> 4, 160 - (this.myPOSM1 & 0xFC));
                    break;
                }
                case 6:
                case 7:
                case 8:
                case 9: {
                    int zColor = aByteValue & 0xFE;
                    if (this.myColorLossEnabled && bool(this.myScanlineCountForLastFrame & 0x1)) {
                        zColor |= 0x1;
                    }
                    this.myTIAPokeRegister[addr] = zColor;
                    break;
                }
                case 10: {
                    this.myPlayfieldPriorityAndScore = (this.myTIAPokeRegister[10] & 0x6) << 5;
                    if ((clock - this.myClockWhenFrameStarted) % 228 < 147) {}
                    this.setCurrentBLMask(this.myPOSBL & 0x3, (this.myTIAPokeRegister[10] & 0x30) >> 4, 160 - (this.myPOSBL & 0xFC));
                    break;
                }
                case 11: {
                    if ((zPreviousValue & 0x8) != (aByteValue & 0x8)) {
                        this.myCurrentGRP0 = JSConstants.PLAYER_REFLECT_TABLE[this.myCurrentGRP0];
                        break;
                    }
                    break;
                }
                case 12: {
                    if ((zPreviousValue & 0x8) != (aByteValue & 0x8)) {
                        this.myCurrentGRP1 = JSConstants.PLAYER_REFLECT_TABLE[this.myCurrentGRP1];
                        break;
                    }
                    break;
                }
                case 13: {
                    this.updatePlayfieldStatus();
                    break;
                }
                case 14: {
                    this.updatePlayfieldStatus();
                    break;
                }
                case 15: {
                    this.updatePlayfieldStatus();
                    break;
                }
                case 16: {
                    final int hpos = (clock - this.myClockWhenFrameStarted) % 228;
                    final int newx = (hpos < 68) ? 3 : ((hpos - 68 + 5) % 160);
                    final int when = JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[this.myTIAPokeRegister[4] & 0x7][this.myPOSP0][newx];
                    if (when == 1) {
                        this.updateFrame(clock + 11);
                        this.myPOSP0 = newx;
                        this.setCurrentP0Mask(this.myPOSP0 & 0x3, 1, this.myTIAPokeRegister[4] & 0x7, 160 - (this.myPOSP0 & 0xFC));
                        break;
                    }
                    if (when == 0) {
                        this.myPOSP0 = newx;
                        this.setCurrentP0Mask(this.myPOSP0 & 0x3, 1, this.myTIAPokeRegister[4] & 0x7, 160 - (this.myPOSP0 & 0xFC));
                        break;
                    }
                    if (when == -1) {
                        this.myPOSP0 = newx;
                        this.setCurrentP0Mask(this.myPOSP0 & 0x3, 0, this.myTIAPokeRegister[4] & 0x7, 160 - (this.myPOSP0 & 0xFC));
                        break;
                    }
                    break;
                }
                case 17: {
                    final int hpos = (clock - this.myClockWhenFrameStarted) % 228;
                    final int newx = (hpos < 68) ? 3 : ((hpos - 68 + 5) % 160);
                    final int when = JSConstants.PLAYER_POSITION_RESET_WHEN_TABLE[this.myTIAPokeRegister[5] & 0x7][this.myPOSP1][newx];
                    if (when == 1) {
                        this.updateFrame(clock + 11);
                        this.myPOSP1 = newx;
                        this.setCurrentP1Mask(this.myPOSP1 & 0x3, 1, this.myTIAPokeRegister[5] & 0x7, 160 - (this.myPOSP1 & 0xFC));
                        break;
                    }
                    if (when == 0) {
                        this.myPOSP1 = newx;
                        this.setCurrentP1Mask(this.myPOSP1 & 0x3, 1, this.myTIAPokeRegister[5] & 0x7, 160 - (this.myPOSP1 & 0xFC));
                        break;
                    }
                    if (when == -1) {
                        this.myPOSP1 = newx;
                        this.setCurrentP1Mask(this.myPOSP1 & 0x3, 0, this.myTIAPokeRegister[5] & 0x7, 160 - (this.myPOSP1 & 0xFC));
                        break;
                    }
                    break;
                }
                case 18: {
                    final int hpos = (clock - this.myClockWhenFrameStarted) % 228;
                    this.myPOSM0 = ((hpos < 68) ? 2 : ((hpos - 68 + 4) % 160));
                    if (clock - this.myLastHMOVEClock == 60 && hpos == 69) {
                        this.myPOSM0 = 8;
                    }
                    this.setCurrentM0Mask(this.myPOSM0 & 0x3, this.myTIAPokeRegister[4] & 0x7, (this.myTIAPokeRegister[4] & 0x30) >> 4, 160 - (this.myPOSM0 & 0xFC));
                    break;
                }
                case 19: {
                    final int hpos = (clock - this.myClockWhenFrameStarted) % 228;
                    this.myPOSM1 = ((hpos < 68) ? 2 : ((hpos - 68 + 4) % 160));
                    if (clock - this.myLastHMOVEClock == 9 && hpos == 18) {
                        this.myPOSM1 = 3;
                    }
                    this.setCurrentM1Mask(this.myPOSM1 & 0x3, this.myTIAPokeRegister[5] & 0x7, (this.myTIAPokeRegister[5] & 0x30) >> 4, 160 - (this.myPOSM1 & 0xFC));
                    break;
                }
                case 20: {
                    final int hpos = (clock - this.myClockWhenFrameStarted) % 228;
                    this.myPOSBL = ((hpos < 68) ? 2 : ((hpos - 68 + 4) % 160));
                    if (clock - this.myLastHMOVEClock == 54 && (hpos == 60 || hpos == 69)) {
                        this.myPOSBL = 10;
                    }
                    else if (clock - this.myLastHMOVEClock == 9 && hpos == 18) {
                        this.myPOSBL = 3;
                    }
                    else if (clock - this.myLastHMOVEClock == 21 && hpos == 30) {
                        this.myPOSBL = 6;
                    }
                    else if (clock - this.myLastHMOVEClock == 18 && hpos == 27) {
                        this.myPOSBL = 5;
                    }
                    this.setCurrentBLMask(this.myPOSBL & 0x3, (this.myTIAPokeRegister[10] & 0x30) >> 4, 160 - (this.myPOSBL & 0xFC));
                    break;
                }
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26: {
                    this.getAudio().pokeAudioRegister(addr, aByteValue, this.mySystem.getCycles());
                    break;
                }
                case 27: {
                    this.myDGRP1 = this.myTIAPokeRegister[28];
                    final int grp0 = bool(this.myTIAPokeRegister[37] & 0x1) ? this.myDGRP0 : this.myTIAPokeRegister[27];
                    this.myCurrentGRP0 = (bool(this.myTIAPokeRegister[11] & 0x8) ? JSConstants.PLAYER_REFLECT_TABLE[grp0] : grp0);
                    final int grp2 = bool(this.myTIAPokeRegister[38] & 0x1) ? this.myDGRP1 : this.myTIAPokeRegister[28];
                    this.myCurrentGRP1 = (bool(this.myTIAPokeRegister[12] & 0x8) ? JSConstants.PLAYER_REFLECT_TABLE[grp2] : grp2);
                    if (this.myCurrentGRP0 != 0) {
                        this.myEnabledObjects |= 0x1;
                    }
                    else {
                        this.myEnabledObjects &= 0xFFFFFFFE;
                    }
                    if (this.myCurrentGRP1 != 0) {
                        this.myEnabledObjects |= 0x4;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFFB;
                    break;
                }
                case 28: {
                    this.myDGRP0 = this.myTIAPokeRegister[27];
                    this.myDENABL = bool(this.myTIAPokeRegister[31] & 0x2);
                    final int grp0 = bool(this.myTIAPokeRegister[37] & 0x1) ? this.myDGRP0 : this.myTIAPokeRegister[27];
                    this.myCurrentGRP0 = (bool(this.myTIAPokeRegister[11] & 0x8) ? JSConstants.PLAYER_REFLECT_TABLE[grp0] : grp0);
                    final int grp2 = bool(this.myTIAPokeRegister[38] & 0x1) ? this.myDGRP1 : this.myTIAPokeRegister[28];
                    this.myCurrentGRP1 = (bool(this.myTIAPokeRegister[12] & 0x8) ? JSConstants.PLAYER_REFLECT_TABLE[grp2] : grp2);
                    if (this.myCurrentGRP0 != 0) {
                        this.myEnabledObjects |= 0x1;
                    }
                    else {
                        this.myEnabledObjects &= 0xFFFFFFFE;
                    }
                    if (this.myCurrentGRP1 != 0) {
                        this.myEnabledObjects |= 0x4;
                    }
                    else {
                        this.myEnabledObjects &= 0xFFFFFFFB;
                    }
                    Label_2309: {
                        if (bool(this.myTIAPokeRegister[39] & 0x1)) {
                            if (!this.myDENABL) {
                                break Label_2309;
                            }
                        }
                        else if (!bool(this.myTIAPokeRegister[31] & 0x2)) {
                            break Label_2309;
                        }
                        this.myEnabledObjects |= 0x10;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFEF;
                    break;
                }
                case 29: {
                    if (bool(this.myTIAPokeRegister[29] & 0x2) && !this.isRESMP0()) {
                        this.myEnabledObjects |= 0x2;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFFD;
                    break;
                }
                case 30: {
                    if (bool(this.myTIAPokeRegister[30] & 0x2) && !this.isRESMP1()) {
                        this.myEnabledObjects |= 0x8;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFF7;
                    break;
                }
                case 31: {
                    Label_2476: {
                        if (bool(this.myTIAPokeRegister[39] & 0x1)) {
                            if (!this.myDENABL) {
                                break Label_2476;
                            }
                        }
                        else if (!bool(this.myTIAPokeRegister[31] & 0x2)) {
                            break Label_2476;
                        }
                        this.myEnabledObjects |= 0x10;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFEF;
                }
                case 32: {}
                case 34: {
                    final int tmp = aByteValue >> 4;
                    if (clock == this.myLastHMOVEClock + 63 && zPreviousValue >> 4 == 7 && tmp == 6) {
                        this.myM0CosmicArkMotionEnabled = true;
                        this.myM0CosmicArkCounter = 0;
                        break;
                    }
                    break;
                }
                case 35: {}
                case 37: {
                    final int grp0 = bool(this.myTIAPokeRegister[37] & 0x1) ? this.myDGRP0 : this.myTIAPokeRegister[27];
                    this.myCurrentGRP0 = (bool(this.myTIAPokeRegister[11] & 0x8) ? JSConstants.PLAYER_REFLECT_TABLE[grp0] : grp0);
                    if (this.myCurrentGRP0 != 0) {
                        this.myEnabledObjects |= 0x1;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFFE;
                    break;
                }
                case 38: {
                    final int grp3 = bool(this.myTIAPokeRegister[38] & 0x1) ? this.myDGRP1 : this.myTIAPokeRegister[28];
                    this.myCurrentGRP1 = (bool(this.myTIAPokeRegister[12] & 0x8) ? JSConstants.PLAYER_REFLECT_TABLE[grp3] : grp3);
                    if (this.myCurrentGRP1 != 0) {
                        this.myEnabledObjects |= 0x4;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFFB;
                    break;
                }
                case 39: {
                    Label_2794: {
                        if (bool(this.myTIAPokeRegister[39] & 0x1)) {
                            if (!this.myDENABL) {
                                break Label_2794;
                            }
                        }
                        else if (!bool(this.myTIAPokeRegister[31] & 0x2)) {
                            break Label_2794;
                        }
                        this.myEnabledObjects |= 0x10;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFEF;
                    break;
                }
                case 40: {
                    if ((zPreviousValue & 0x2) != 0x0 && !bool(aByteValue & 0x2)) {
                        char middle;
                        if ((this.myTIAPokeRegister[4] & 0x7) == 0x5) {
                            middle = '\b';
                        }
                        else if ((this.myTIAPokeRegister[4] & 0x7) == 0x7) {
                            middle = '\u0010';
                        }
                        else {
                            middle = '\u0004';
                        }
                        this.myPOSM0 = (this.myPOSP0 + middle) % 160;
                        this.setCurrentM0Mask(this.myPOSM0 & 0x3, this.myTIAPokeRegister[4] & 0x7, (this.myTIAPokeRegister[4] & 0x30) >> 4, 160 - (this.myPOSM0 & 0xFC));
                    }
                    if (bool(this.myTIAPokeRegister[29] & 0x2) && !this.isRESMP0()) {
                        this.myEnabledObjects |= 0x2;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFFD;
                    break;
                }
                case 41: {
                    if ((zPreviousValue & 0x2) != 0x0 && !bool(aByteValue & 0x2)) {
                        char middle;
                        if ((this.myTIAPokeRegister[5] & 0x7) == 0x5) {
                            middle = '\b';
                        }
                        else if ((this.myTIAPokeRegister[5] & 0x7) == 0x7) {
                            middle = '\u0010';
                        }
                        else {
                            middle = '\u0004';
                        }
                        this.myPOSM1 = (this.myPOSP1 + middle) % 160;
                        this.setCurrentM1Mask(this.myPOSM1 & 0x3, this.myTIAPokeRegister[5] & 0x7, (this.myTIAPokeRegister[5] & 0x30) >> 4, 160 - (this.myPOSM1 & 0xFC));
                    }
                    if (bool(this.myTIAPokeRegister[30] & 0x2) && !this.isRESMP1()) {
                        this.myEnabledObjects |= 0x8;
                        break;
                    }
                    this.myEnabledObjects &= 0xFFFFFFF7;
                    break;
                }
                case 42: {
                    final int x = (clock - this.myClockWhenFrameStarted) % 228 / 3;
                    if (this.myAllowHMOVEBlanks && JSConstants.HMOVE_BLANK_ENABLE_CYCLES[x]) {
                        this.myHMOVEBlankEnabled = true;
                    }
                    this.myPOSP0 += JSConstants.COMPLETE_MOTION_TABLE[x][this.myTIAPokeRegister[32] >> 4];
                    this.myPOSP1 += JSConstants.COMPLETE_MOTION_TABLE[x][this.myTIAPokeRegister[33] >> 4];
                    this.myPOSM0 += JSConstants.COMPLETE_MOTION_TABLE[x][this.myTIAPokeRegister[34] >> 4];
                    this.myPOSM1 += JSConstants.COMPLETE_MOTION_TABLE[x][this.myTIAPokeRegister[35] >> 4];
                    this.myPOSBL += JSConstants.COMPLETE_MOTION_TABLE[x][this.myTIAPokeRegister[36] >> 4];
                    if (this.myPOSP0 >= 160) {
                        this.myPOSP0 -= 160;
                    }
                    else if (this.myPOSP0 < 0) {
                        this.myPOSP0 += 160;
                    }
                    if (this.myPOSP1 >= 160) {
                        this.myPOSP1 -= 160;
                    }
                    else if (this.myPOSP1 < 0) {
                        this.myPOSP1 += 160;
                    }
                    if (this.myPOSM0 >= 160) {
                        this.myPOSM0 -= 160;
                    }
                    else if (this.myPOSM0 < 0) {
                        this.myPOSM0 += 160;
                    }
                    if (this.myPOSM1 >= 160) {
                        this.myPOSM1 -= 160;
                    }
                    else if (this.myPOSM1 < 0) {
                        this.myPOSM1 += 160;
                    }
                    if (this.myPOSBL >= 160) {
                        this.myPOSBL -= 160;
                    }
                    else if (this.myPOSBL < 0) {
                        this.myPOSBL += 160;
                    }
                    this.setCurrentBLMask(this.myPOSBL & 0x3, (this.myTIAPokeRegister[10] & 0x30) >> 4, 160 - (this.myPOSBL & 0xFC));
                    this.setCurrentP0Mask(this.myPOSP0 & 0x3, 0, this.myTIAPokeRegister[4] & 0x7, 160 - (this.myPOSP0 & 0xFC));
                    this.setCurrentP1Mask(this.myPOSP1 & 0x3, 0, this.myTIAPokeRegister[5] & 0x7, 160 - (this.myPOSP1 & 0xFC));
                    this.setCurrentM0Mask(this.myPOSM0 & 0x3, this.myTIAPokeRegister[4] & 0x7, (this.myTIAPokeRegister[4] & 0x30) >> 4, 160 - (this.myPOSM0 & 0xFC));
                    this.setCurrentM1Mask(this.myPOSM1 & 0x3, this.myTIAPokeRegister[5] & 0x7, (this.myTIAPokeRegister[5] & 0x30) >> 4, 160 - (this.myPOSM1 & 0xFC));
                    this.myLastHMOVEClock = clock;
                    this.myM0CosmicArkMotionEnabled = false;
                    break;
                }
                case 43: {
                    this.myTIAPokeRegister[32] = 0;
                    this.myTIAPokeRegister[33] = 0;
                    this.myTIAPokeRegister[34] = 0;
                    this.myTIAPokeRegister[35] = 0;
                    this.myTIAPokeRegister[36] = 0;
                    break;
                }
                case 44: {
                    this.myCollision = 0;
                    break;
                }
            }
        }
    }
    
    public void debugResetRenderTypes() {
        this.debugRenderTypes = new boolean[256];
    }
    
    public boolean[] debugGetRenderTypes() {
        return this.debugRenderTypes;
    }
    
    public void setDebugLockRegister(final int aItem, final boolean aValue) {
        this.debugRegLocked[aItem] = aValue;
    }
    
    public boolean getDebugLockRegister(final int aItem) {
        return this.debugRegLocked[aItem];
    }
    
    public void debugUnlockAllRegisters() {
        this.debugRegLocked = new boolean[44];
        this.debugLockP0Mask = false;
        this.debugLockP1Mask = false;
    }
    
    public int debugGetNUSIZ0() {
        return this.myTIAPokeRegister[4];
    }
    
    public int debugGetNUSIZ1() {
        return this.myTIAPokeRegister[5];
    }
    
    public void debugSetP0Mask(final int aA, final int aB, final int aC, final int aD) {
        this.setCurrentP0Mask(aA, aB, aC, aD);
        this.debugLockP0Mask = true;
    }
    
    public void debugSetP1Mask(final int aA, final int aB, final int aC, final int aD) {
        this.setCurrentP1Mask(aA, aB, aC, aD);
        this.debugLockP1Mask = true;
    }
    
    public String debugDumpRegs() {
        final StringBuffer zSB = new StringBuffer();
        zSB.append("ENAM0=" + bool(this.myTIAPokeRegister[29] & 0x2) + "; NUSIZ0=" + this.dbgHex(this.myTIAPokeRegister[4]) + "; ENAM1=" + bool(this.myTIAPokeRegister[30] & 0x2) + "; NUSIZ1=" + this.dbgHex(this.myTIAPokeRegister[5]) + "\n");
        zSB.append("CurrentM0Mask : alignment=" + this.myCurrentM0Mask[0] + ", num=" + this.myCurrentM0Mask[1] + ", size=" + this.myCurrentM0Mask[2] + ", x=" + this.myCurrentM0Mask[3] + "\n");
        zSB.append("CurrentM1Mask : alignment=" + this.myCurrentM1Mask[0] + ", num=" + this.myCurrentM1Mask[1] + ", size=" + this.myCurrentM1Mask[2] + ", x=" + this.myCurrentM1Mask[3] + "\n");
        zSB.append("CurrentP0Mask : alignment=" + this.myCurrentP0Mask[0] + ", num=" + this.myCurrentP0Mask[1] + ", size=" + this.myCurrentP0Mask[2] + ", x=" + this.myCurrentP0Mask[3] + "\n");
        zSB.append("CurrentP1Mask : alignment=" + this.myCurrentP1Mask[0] + ", num=" + this.myCurrentP1Mask[1] + ", size=" + this.myCurrentP1Mask[2] + ", x=" + this.myCurrentP1Mask[3] + "\n");
        zSB.append("M0Pos=" + this.myPOSM0 + "; M1Pos=" + this.myPOSM1 + "\n");
        zSB.append("P0Pos=" + this.myPOSP0 + "; P1Pos=" + this.myPOSP1 + "\n");
        zSB.append("COLUBK=0x" + Integer.toHexString(this.myTIAPokeRegister[9]) + "\n");
        return zSB.toString();
    }
    
    public void debugPoke(final int aAddr, final int aValue) {
        this.debugStraightPoke = true;
        this.poke((char)aAddr, aValue);
        this.debugStraightPoke = false;
    }
    
    public String dbgHex(final int aNum) {
        return "0x" + Integer.toHexString(aNum);
    }
    
    static {
        COSMICBUG_MOVEMENT = new int[] { 18, 33, 0, 17 };
    }
}
