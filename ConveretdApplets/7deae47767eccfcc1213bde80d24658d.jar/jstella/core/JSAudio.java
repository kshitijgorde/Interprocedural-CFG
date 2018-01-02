// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import java.util.LinkedList;
import java.util.Queue;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat;

public class JSAudio
{
    private static final long serialVersionUID = -2127696001297260042L;
    public static final double CPU_SPEED = 1193191.66666667;
    private static final int JAVA_SOUND_SAMPLE_RATE = 44100;
    private static final int TIA_SAMPLE_RATE = 31400;
    public static final double CYCLES_PER_SAMPLE = 27.056500377929027;
    private static final int BYTES_PER_SAMPLE = 1;
    private static final int BITS_PER_SAMPLE = 8;
    private static final boolean BIG_ENDIAN = true;
    private static final boolean SIGNED_VALUE = true;
    private static final int CHANNELS = 1;
    private static final int DEFAULT_BUFFER_CUSHION = 4000;
    private AudioFormat myAudioFormat;
    private SourceDataLine mySDLine;
    private JSConsole myConsole;
    private byte[] myPreOutputBuffer;
    private boolean myInitSuccess;
    private Queue<AudioRegisterPoke> myPokeQueue;
    private int myExcessCycles;
    private double myCyclePool;
    private int myPreviousCycle;
    private AudioRegisterPoke myPreviousPoke;
    private int[] myAUDC;
    private int[] myAUDF;
    private int[] myAUDV;
    private int[] myFutureAUDC;
    private int[] myFutureAUDF;
    private int[] myFutureAUDV;
    private FrequencyDivider[] myFrequencyDivider;
    private int[] myP4;
    private int[] myP5;
    private int myOutputCounter;
    private int myChannels;
    private int myVolumePercentage;
    private int myVolumeClip;
    private int myNominalDisplayFrameRate;
    private double myRealDisplayFrameRate;
    private double myCycleSampleFactor;
    private double myAdjustedCyclesPerAudioFrame;
    private int myBufferCushion;
    private boolean mySoundEnabled;
    
    protected JSAudio(final JSConsole aConsole) {
        this.myAudioFormat = null;
        this.mySDLine = null;
        this.myConsole = null;
        this.myPreOutputBuffer = new byte[176400];
        this.myInitSuccess = false;
        this.myPokeQueue = new LinkedList<AudioRegisterPoke>();
        this.myExcessCycles = 0;
        this.myCyclePool = 0.0;
        this.myPreviousCycle = 0;
        this.myPreviousPoke = null;
        this.myAUDC = new int[2];
        this.myAUDF = new int[2];
        this.myAUDV = new int[] { 1, 1 };
        this.myFutureAUDC = new int[2];
        this.myFutureAUDF = new int[2];
        this.myFutureAUDV = new int[2];
        this.myFrequencyDivider = new FrequencyDivider[] { new FrequencyDivider(), new FrequencyDivider() };
        this.myP4 = new int[2];
        this.myP5 = new int[2];
        this.myOutputCounter = 0;
        this.myChannels = 1;
        this.myVolumePercentage = 100;
        this.myVolumeClip = 128;
        this.myNominalDisplayFrameRate = 60;
        this.myRealDisplayFrameRate = 60.0;
        this.myCycleSampleFactor = 1.0;
        this.myAdjustedCyclesPerAudioFrame = 27.056500377929027;
        this.myBufferCushion = 4000 * this.myChannels;
        this.mySoundEnabled = true;
        this.myConsole = aConsole;
        this.reset();
    }
    
    public boolean isSoundEnabled() {
        return this.mySoundEnabled;
    }
    
    public void setVolume(final int percent) {
        this.myVolumePercentage = percent;
    }
    
    public void setSoundEnabled(final boolean aSoundEnabled) {
        if (this.mySDLine != null && this.mySDLine.isOpen() && !aSoundEnabled) {
            this.mySDLine.stop();
            this.mySDLine.flush();
        }
        this.mySoundEnabled = aSoundEnabled;
    }
    
    public void pauseAudio() {
        if (this.mySDLine != null) {
            this.mySDLine.stop();
        }
    }
    
    protected void setNominalDisplayFrameRate(final int aFrameRate) {
        this.myNominalDisplayFrameRate = aFrameRate;
        this.myPreviousCycle = 0;
        this.updateCycleSampleFactor();
    }
    
    public void setRealDisplayFrameRate(final double aRealFrameRate) {
        this.myRealDisplayFrameRate = aRealFrameRate;
        this.updateCycleSampleFactor();
    }
    
    private void updateCycleSampleFactor() {
        this.myCycleSampleFactor = this.myRealDisplayFrameRate / this.myNominalDisplayFrameRate;
        this.myAdjustedCyclesPerAudioFrame = 27.056500377929027 * this.myCycleSampleFactor;
    }
    
    protected void systemCyclesReset(final int aCurrentCycle) {
        this.myPreviousCycle -= aCurrentCycle;
    }
    
    protected int[] getAudioRegisterData() {
        final int[] zReturn = { this.myAUDC[0], this.myAUDC[1], this.myAUDF[0], this.myAUDF[1], this.myAUDV[0], this.myAUDV[1] };
        return zReturn;
    }
    
    protected void setAudioRegisterData(final int[] aData) {
        this.setAudioRegister(21, aData[0]);
        this.setAudioRegister(22, aData[1]);
        this.setAudioRegister(23, aData[2]);
        this.setAudioRegister(24, aData[3]);
        this.setAudioRegister(25, aData[4]);
        this.setAudioRegister(26, aData[5]);
    }
    
    protected void setChannelNumber(final int aChannels) {
        assert aChannels == 2;
        if (this.myChannels != aChannels) {
            this.myChannels = aChannels;
            this.initialize();
        }
    }
    
    protected int getChannelNumber() {
        return this.myChannels;
    }
    
    protected void close() {
        try {
            if (this.mySDLine != null) {
                this.mySDLine.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected synchronized void initialize() {
        try {
            final boolean zPreviouslyEnabled = this.mySoundEnabled;
            this.mySoundEnabled = false;
            if (this.mySDLine != null) {
                this.mySDLine.stop();
                this.mySDLine.close();
                this.mySDLine = null;
            }
            this.clearPokeQueue();
            this.myAudioFormat = new AudioFormat(44100.0f, 8, this.myChannels, true, true);
            final DataLine.Info zDLI = new DataLine.Info(SourceDataLine.class, this.myAudioFormat);
            (this.mySDLine = (SourceDataLine)AudioSystem.getLine(zDLI)).open(this.myAudioFormat);
            this.myInitSuccess = true;
            this.mySoundEnabled = zPreviouslyEnabled;
            this.myBufferCushion = 4000 * this.myChannels;
            this.updateCycleSampleFactor();
        }
        catch (LineUnavailableException e) {
            this.myInitSuccess = false;
        }
    }
    
    protected boolean isSuccessfullyInitialized() {
        return this.myInitSuccess;
    }
    
    protected void reset() {
        this.myPreviousCycle = 0;
        this.clearPokeQueue();
        final int[] myAUDC = this.myAUDC;
        final int n = 0;
        final int[] myAUDC2 = this.myAUDC;
        final int n2 = 1;
        final int[] myAUDF = this.myAUDF;
        final int n3 = 0;
        final int[] myAUDF2 = this.myAUDF;
        final int n4 = 1;
        final int[] myAUDV = this.myAUDV;
        final int n5 = 0;
        final int[] myAUDV2 = this.myAUDV;
        final int n6 = 1;
        final boolean b = false;
        myAUDV[n5] = (myAUDV2[n6] = (b ? 1 : 0));
        myAUDF[n3] = (myAUDF2[n4] = (b ? 1 : 0));
        myAUDC[n] = (myAUDC2[n2] = (b ? 1 : 0));
        final int[] myFutureAUDC = this.myFutureAUDC;
        final int n7 = 0;
        final int[] myFutureAUDC2 = this.myFutureAUDC;
        final int n8 = 1;
        final int[] myFutureAUDF = this.myFutureAUDF;
        final int n9 = 0;
        final int[] myFutureAUDF2 = this.myFutureAUDF;
        final int n10 = 1;
        final int[] myFutureAUDV = this.myFutureAUDV;
        final int n11 = 0;
        final int[] myFutureAUDV2 = this.myFutureAUDV;
        final int n12 = 1;
        final boolean b2 = false;
        myFutureAUDV[n11] = (myFutureAUDV2[n12] = (b2 ? 1 : 0));
        myFutureAUDF[n9] = (myFutureAUDF2[n10] = (b2 ? 1 : 0));
        myFutureAUDC[n7] = (myFutureAUDC2[n8] = (b2 ? 1 : 0));
        final int[] myP4 = this.myP4;
        final int n13 = 0;
        final int[] myP5 = this.myP5;
        final int n14 = 0;
        final int[] myP6 = this.myP4;
        final int n15 = 1;
        final int[] myP7 = this.myP5;
        final int n16 = 1;
        final boolean b3 = true;
        myP6[n15] = (myP7[n16] = (b3 ? 1 : 0));
        myP4[n13] = (myP5[n14] = (b3 ? 1 : 0));
        this.myFrequencyDivider[0].set(0);
        this.myFrequencyDivider[1].set(0);
        this.myOutputCounter = 0;
    }
    
    private void setAudioRegister(final int address, final int value) {
        switch (address) {
            case 21: {
                this.myAUDC[0] = (value & 0xF);
                break;
            }
            case 22: {
                this.myAUDC[1] = (value & 0xF);
                break;
            }
            case 23: {
                this.myAUDF[0] = (value & 0x1F);
                this.myFrequencyDivider[0].set(this.myAUDF[0]);
                break;
            }
            case 24: {
                this.myAUDF[1] = (value & 0x1F);
                this.myFrequencyDivider[1].set(this.myAUDF[1]);
                break;
            }
            case 25: {
                this.myAUDV[0] = (value & 0xF);
                break;
            }
            case 26: {
                this.myAUDV[1] = (value & 0xF);
                break;
            }
        }
    }
    
    private int getAudioRegister(final char address) {
        switch (address) {
            case '\u0015': {
                return this.myAUDC[0];
            }
            case '\u0016': {
                return this.myAUDC[1];
            }
            case '\u0017': {
                return this.myAUDF[0];
            }
            case '\u0018': {
                return this.myAUDF[1];
            }
            case '\u0019': {
                return this.myAUDV[0];
            }
            case '\u001a': {
                return this.myAUDV[1];
            }
            default: {
                return 0;
            }
        }
    }
    
    private void setFutureAudioRegister(final char address, final int value) {
        switch (address) {
            case '\u0015': {
                this.myFutureAUDC[0] = (value & 0xF);
                break;
            }
            case '\u0016': {
                this.myFutureAUDC[1] = (value & 0xF);
                break;
            }
            case '\u0017': {
                this.myFutureAUDF[0] = (value & 0x1F);
                break;
            }
            case '\u0018': {
                this.myFutureAUDF[1] = (value & 0x1F);
                break;
            }
            case '\u0019': {
                this.myFutureAUDV[0] = (value & 0xF);
                break;
            }
            case '\u001a': {
                this.myFutureAUDV[1] = (value & 0xF);
                break;
            }
        }
    }
    
    private int getFutureAudioRegister(final char address) {
        switch (address) {
            case '\u0015': {
                return this.myFutureAUDC[0];
            }
            case '\u0016': {
                return this.myFutureAUDC[1];
            }
            case '\u0017': {
                return this.myFutureAUDF[0];
            }
            case '\u0018': {
                return this.myFutureAUDF[1];
            }
            case '\u0019': {
                return this.myFutureAUDV[0];
            }
            case '\u001a': {
                return this.myFutureAUDV[1];
            }
            default: {
                return 0;
            }
        }
    }
    
    private boolean bool(final int aValue) {
        return aValue != 0;
    }
    
    private void synthesizeAudioData(final byte[] aPreOutputBuffer, final int aStartIndex, final int aAudioFrames) {
        int zSamples = aAudioFrames * this.myChannels;
        final int zVolChannelZero = (this.myAUDV[0] << 2) * this.myVolumePercentage / 100;
        final int zVolChannelOne = (this.myAUDV[1] << 2) * this.myVolumePercentage / 100;
        int zIndex = aStartIndex;
        while (zSamples > 0) {
            for (int c = 0; c < 2; ++c) {
                if (this.myFrequencyDivider[c].clock()) {
                    switch (this.myAUDC[c]) {
                        case 0: {
                            this.myP4[c] = (this.myP4[c] << 1 | 0x1);
                            break;
                        }
                        case 1: {
                            this.myP4[c] = ((!this.bool(this.myP4[c] & 0xF) || (this.myP4[c] << 1 | ((this.bool(this.myP4[c] & 0x8) ^ this.bool(this.myP4[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            break;
                        }
                        case 2: {
                            this.myP5[c] = ((!this.bool(this.myP5[c] & 0x1F) || (this.myP5[c] << 1 | ((this.bool(this.myP5[c] & 0x10) ^ this.bool(this.myP5[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            if ((this.myP5[c] & 0xF) == 0x8) {
                                this.myP4[c] = ((!this.bool(this.myP4[c] & 0xF) || (this.myP4[c] << 1 | ((this.bool(this.myP4[c] & 0x8) ^ this.bool(this.myP4[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                                break;
                            }
                            break;
                        }
                        case 3: {
                            this.myP5[c] = ((!this.bool(this.myP5[c] & 0x1F) || (this.myP5[c] << 1 | ((this.bool(this.myP5[c] & 0x10) ^ this.bool(this.myP5[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            if (this.bool(this.myP5[c] & 0x10)) {
                                this.myP4[c] = ((!this.bool(this.myP4[c] & 0xF) || (this.myP4[c] << 1 | ((this.bool(this.myP4[c] & 0x8) ^ this.bool(this.myP4[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                                break;
                            }
                            break;
                        }
                        case 4: {
                            this.myP4[c] = (this.myP4[c] << 1 | (this.bool(this.myP4[c] & 0x1) ? 0 : 1));
                            break;
                        }
                        case 5: {
                            this.myP4[c] = (this.myP4[c] << 1 | (this.bool(this.myP4[c] & 0x1) ? 0 : 1));
                            break;
                        }
                        case 6: {
                            this.myP5[c] = ((!this.bool(this.myP5[c] & 0x1F) || (this.myP5[c] << 1 | ((this.bool(this.myP5[c] & 0x10) ^ this.bool(this.myP5[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            if ((this.myP5[c] & 0xF) == 0x8) {
                                this.myP4[c] = (this.myP4[c] << 1 | (this.bool(this.myP4[c] & 0x1) ? 0 : 1));
                                break;
                            }
                            break;
                        }
                        case 7: {
                            this.myP5[c] = ((!this.bool(this.myP5[c] & 0x1F) || (this.myP5[c] << 1 | ((this.bool(this.myP5[c] & 0x10) ^ this.bool(this.myP5[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            if (this.bool(this.myP5[c] & 0x10)) {
                                this.myP4[c] = (this.myP4[c] << 1 | (this.bool(this.myP4[c] & 0x1) ? 0 : 1));
                                break;
                            }
                            break;
                        }
                        case 8: {
                            this.myP5[c] = (((!this.bool(this.myP5[c] & 0x1F) && !this.bool(this.myP4[c] & 0xF)) || (this.myP5[c] << 1 | ((this.bool(this.myP4[c] & 0x8) ^ this.bool(this.myP5[c] & 0x10)) ? 1 : 0))) ? 1 : 0);
                            this.myP4[c] = (this.myP4[c] << 1 | (this.bool(this.myP5[c] & 0x20) ? 1 : 0));
                            break;
                        }
                        case 9: {
                            this.myP5[c] = ((!this.bool(this.myP5[c] & 0x1F) || (this.myP5[c] << 1 | ((this.bool(this.myP5[c] & 0x10) ^ this.bool(this.myP5[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            this.myP4[c] = (this.myP4[c] << 1 | (this.bool(this.myP5[c] & 0x20) ? 1 : 0));
                            break;
                        }
                        case 10: {
                            this.myP5[c] = ((!this.bool(this.myP5[c] & 0x1F) || (this.myP5[c] << 1 | ((this.bool(this.myP5[c] & 0x10) ^ this.bool(this.myP5[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            if ((this.myP5[c] & 0xF) == 0x8) {
                                this.myP4[c] = (this.myP4[c] << 1 | (this.bool(this.myP5[c] & 0x10) ? 1 : 0));
                                break;
                            }
                            break;
                        }
                        case 11: {
                            this.myP4[c] = (this.myP4[c] << 1 | 0x1);
                            break;
                        }
                        case 12: {
                            this.myP4[c] = (~this.myP4[c] << 1 | ((!this.bool(this.myP4[c] & 0x4) && this.bool(this.myP4[c] & 0x7)) ? 1 : 0));
                            break;
                        }
                        case 13: {
                            this.myP4[c] = (~this.myP4[c] << 1 | ((!this.bool(this.myP4[c] & 0x4) && this.bool(this.myP4[c] & 0x7)) ? 1 : 0));
                            break;
                        }
                        case 14: {
                            this.myP5[c] = ((!this.bool(this.myP5[c] & 0x1F) || (this.myP5[c] << 1 | ((this.bool(this.myP5[c] & 0x10) ^ this.bool(this.myP5[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            if ((this.myP5[c] & 0xF) == 0x8) {
                                this.myP4[c] = (~this.myP4[c] << 1 | ((!this.bool(this.myP4[c] & 0x4) && this.bool(this.myP4[c] & 0x7)) ? 1 : 0));
                                break;
                            }
                            break;
                        }
                        case 15: {
                            this.myP5[c] = ((!this.bool(this.myP5[c] & 0x1F) || (this.myP5[c] << 1 | ((this.bool(this.myP5[c] & 0x10) ^ this.bool(this.myP5[c] & 0x4)) ? 1 : 0))) ? 1 : 0);
                            if (this.bool(this.myP5[c] & 0x10)) {
                                this.myP4[c] = (~this.myP4[c] << 1 | ((!this.bool(this.myP4[c] & 0x4) && this.bool(this.myP4[c] & 0x7)) ? 1 : 0));
                                break;
                            }
                            break;
                        }
                    }
                }
            }
            this.myOutputCounter += 44100;
            if (this.myChannels == 1) {
                while (zSamples > 0 && this.myOutputCounter >= 31400) {
                    final int zChannelZero = this.bool(this.myP4[0] & 0x8) ? zVolChannelZero : 0;
                    final int zChannelOne = this.bool(this.myP4[1] & 0x8) ? zVolChannelOne : 0;
                    final int zBothChannels = zChannelZero + zChannelOne + this.myVolumeClip;
                    aPreOutputBuffer[zIndex] = (byte)(zBothChannels - 128);
                    this.myOutputCounter -= 31400;
                    ++zIndex;
                    --zSamples;
                }
            }
            else {
                while (zSamples > 0 && this.myOutputCounter >= 31400) {
                    final int zChannelZero = (this.bool(this.myP4[0] & 0x8) ? zVolChannelZero : 0) + this.myVolumeClip;
                    final int zChannelOne = (this.bool(this.myP4[1] & 0x8) ? zVolChannelOne : 0) + this.myVolumeClip;
                    aPreOutputBuffer[zIndex] = (byte)(zChannelZero - 128);
                    ++zIndex;
                    --zSamples;
                    aPreOutputBuffer[zIndex] = (byte)(zChannelOne - 128);
                    ++zIndex;
                    --zSamples;
                    this.myOutputCounter -= 31400;
                }
            }
        }
    }
    
    private int processPokeQueue() {
        assert this.myPokeQueue.size() > 0;
        int zCurrentBufferIndex = 0;
        for (boolean zEndOfFrame = false; !zEndOfFrame; zEndOfFrame = true) {
            final AudioRegisterPoke zRW = this.myPokeQueue.poll();
            if (zRW == null) {
                zEndOfFrame = true;
            }
            if (zRW != null) {
                assert zRW.myDeltaCycles >= 0;
                if (zRW.myAddr != 0) {
                    this.setAudioRegister((char)zRW.myAddr, zRW.myByteValue);
                }
                this.myCyclePool += zRW.myDeltaCycles;
                final double zAudioFramesInPool = this.myCyclePool / this.myAdjustedCyclesPerAudioFrame;
                final int zWholeAudioFramesInPool = (int)zAudioFramesInPool;
                this.myCyclePool -= zWholeAudioFramesInPool * this.myAdjustedCyclesPerAudioFrame;
                this.synthesizeAudioData(this.myPreOutputBuffer, zCurrentBufferIndex, zWholeAudioFramesInPool);
                zCurrentBufferIndex += zWholeAudioFramesInPool * this.myChannels;
            }
            final AudioRegisterPoke zNextARP = this.myPokeQueue.peek();
            if (zNextARP == null || zNextARP.myFrameEnd) {}
        }
        return zCurrentBufferIndex;
    }
    
    protected synchronized void doFrameAudio(final int aCycles, final int aFPS) {
        if (this.isSoundEnabled()) {
            this.addPokeToQueue(true, aCycles, 0, 0);
            final int zSamples = this.processPokeQueue();
            final int zBufferSize = this.mySDLine.getBufferSize();
            final int zAvailable = this.mySDLine.available();
            final int zInBuffer = zBufferSize - zAvailable;
            final double zPercentFull = 100.0 * (zInBuffer / zBufferSize);
            int zToPlay = Math.min(this.myBufferCushion - zInBuffer, zSamples);
            zToPlay = Math.max(0, zToPlay);
            if (this.myChannels == 2) {
                zToPlay = roundToEven(zToPlay);
            }
            if (!this.mySDLine.isRunning()) {
                this.mySDLine.start();
            }
            this.mySDLine.write(this.myPreOutputBuffer, 0, zToPlay);
        }
    }
    
    private static int roundToEven(final int aNumber) {
        return (aNumber % 2 != 0) ? (aNumber - 1) : aNumber;
    }
    
    private void clearPokeQueue() {
        this.myPokeQueue.clear();
        this.addPokeToQueue(true, 0, 0, 0);
    }
    
    private void addPokeToQueue(final boolean aFrameEnd, final int aCycleNumber, final int aAddress, final int aByteValue) {
        final int zDeltaCycles = aCycleNumber - this.myPreviousCycle;
        if (this.myPreviousPoke != null) {
            this.myPreviousPoke.myDeltaCycles = zDeltaCycles;
        }
        final int zValueToOverwrite = this.getFutureAudioRegister((char)aAddress);
        final AudioRegisterPoke zRW = new AudioRegisterPoke(aFrameEnd, aAddress, aByteValue);
        this.myPokeQueue.offer(zRW);
        this.setFutureAudioRegister((char)aAddress, aByteValue);
        this.myPreviousPoke = zRW;
        this.myPreviousCycle = aCycleNumber;
    }
    
    protected void pokeAudioRegister(final int addr, final int aByteValue, final int cycle) {
        if (this.isSoundEnabled()) {
            this.addPokeToQueue(false, cycle, addr, aByteValue);
        }
    }
    
    public void debugSetReg(final char address, final int value) {
        this.setAudioRegister(address, value);
    }
    
    private void dbgout(final String aOut) {
        System.out.println("DEBUG: " + aOut);
    }
    
    public void debugPlayChunk(final int aTimerDelay) {
        int zSamples = (int)(44.1f * aTimerDelay);
        if (zSamples > this.myPreOutputBuffer.length) {
            zSamples = this.myPreOutputBuffer.length;
        }
        this.synthesizeAudioData(this.myPreOutputBuffer, 0, zSamples);
        final int zAvail = this.mySDLine.available();
        this.mySDLine.write(this.myPreOutputBuffer, 0, Math.min(zSamples, zAvail));
    }
    
    public SourceDataLine debugGetSourceDataLine() {
        return this.mySDLine;
    }
    
    private class FrequencyDivider
    {
        private int myDivideByValue;
        private int myCounter;
        
        public FrequencyDivider() {
            this.myDivideByValue = 0;
            this.myCounter = 0;
            final boolean b = false;
            this.myCounter = (b ? 1 : 0);
            this.myDivideByValue = (b ? 1 : 0);
        }
        
        public void set(final int divideBy) {
            this.myDivideByValue = divideBy;
        }
        
        public boolean clock() {
            ++this.myCounter;
            if (this.myCounter > this.myDivideByValue) {
                this.myCounter = 0;
                return true;
            }
            return false;
        }
    }
    
    private class AudioRegisterPoke
    {
        private int myAddr;
        private int myByteValue;
        private int myDeltaCycles;
        private boolean myFrameEnd;
        
        public AudioRegisterPoke(final boolean aFrameEnd, final int aAddr, final int aByteValue) {
            this.myDeltaCycles = 0;
            this.myFrameEnd = false;
            this.myAddr = aAddr;
            this.myByteValue = aByteValue;
            this.myFrameEnd = aFrameEnd;
        }
    }
}
