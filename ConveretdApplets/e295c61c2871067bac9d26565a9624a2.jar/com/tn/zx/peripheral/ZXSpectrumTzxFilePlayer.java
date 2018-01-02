// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import java.util.Vector;
import com.tn.util.Array;
import com.tn.z80.Z80A;
import com.tn.zx.ZxExpansionPort;
import com.tn.zx.ZxIoHandler;

public class ZXSpectrumTzxFilePlayer implements ZxIoHandler
{
    private ZxExpansionPort ivConnectedSpectrum;
    private Z80A ivZ80;
    private boolean ivPlaying;
    private byte[] ivTzxFile;
    private int[] ivEventStack;
    private int ivEventStackPointer;
    private long ivCycleNextEvent;
    private int ivBlockPos;
    private int ivBlockLen;
    private int ivBlockType;
    private byte[] ivStreamerBuffer;
    private int ivStreamerBitPos;
    private int ivStreamerBitPosEnd;
    private boolean ivLevel;
    private static final int OP_STACKEND = 0;
    private static final int OP_NOP = 1;
    private static final int OP_PAUSE = 2;
    private static final int OP_LOW = 3;
    private static final int OP_SWITCH = 4;
    private static final int OP_PULSES = 5;
    private static final int OP_BITSTREAM = 6;
    private static final int OP_LOOP = 7;
    private static final int OP_SYNCHRONIZE = 8;
    private static final int OP_NEXTBLOCK = 9;
    
    public ZXSpectrumTzxFilePlayer() {
        this.ivConnectedSpectrum = null;
        this.resetEventStack();
    }
    
    private void advanceToCycle(final long pCycle) {
        while (this.ivCycleNextEvent < pCycle) {
            this.advanceToNextEvent();
        }
    }
    
    private void advanceToNextEvent() {
        try {
            final int function = this.pop();
            switch (function) {
                case 0: {
                    this.push(0);
                    this.ivPlaying = false;
                    this.ivCycleNextEvent = Long.MAX_VALUE;
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    this.ivCycleNextEvent += this.pop();
                    break;
                }
                case 3: {
                    this.ivLevel = false;
                    break;
                }
                case 4: {
                    this.ivLevel = !this.ivLevel;
                    break;
                }
                case 5: {
                    int count = this.pop();
                    final int duration = this.pop();
                    if (count > 0) {
                        this.push(duration);
                        this.push(--count);
                        this.push(5);
                        this.ivCycleNextEvent += duration;
                        this.push(4);
                        break;
                    }
                    break;
                }
                case 6: {
                    final int duration2 = this.pop();
                    final int duration3 = this.pop();
                    if (!this.streamerIsEmpty()) {
                        this.push(duration3);
                        this.push(duration2);
                        this.push(6);
                        this.push(this.streamerReadNext() ? duration2 : duration3);
                        this.push(2);
                        this.push(5);
                        break;
                    }
                    break;
                }
                case 7: {
                    int counter = this.pop();
                    final int looppos = this.pop();
                    if (counter > 0) {
                        this.ivBlockPos = looppos;
                        this.ivBlockLen = 0;
                        this.push(looppos);
                        this.push(--counter);
                        this.push(7);
                        this.push(9);
                        break;
                    }
                    break;
                }
                case 8: {
                    this.ivCycleNextEvent = this.ivZ80.getClockFull();
                    break;
                }
                case 9: {
                    this.push(9);
                    if (this.gotoNextBlock()) {
                        this.pop();
                        break;
                    }
                    break;
                }
                default: {
                    throw new RuntimeException("unknown function: " + function);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Event stack problem:");
            e.printStackTrace();
            this.ivCycleNextEvent = 2147483647L;
            this.ivPlaying = false;
        }
    }
    
    public void connectToZXSpectrum(final ZxExpansionPort pZXSpectrum) {
        this.ivConnectedSpectrum = pZXSpectrum;
        this.ivZ80 = pZXSpectrum.getZ80();
        for (int i = 0; i < 256; ++i) {
            if ((i & 0x1) == 0x0) {
                pZXSpectrum.addIOHandler(this, i);
            }
        }
    }
    
    public void disconnectFromZXSpectrum() {
        if (this.ivConnectedSpectrum != null) {
            this.ivConnectedSpectrum.removeIOHandler(this);
            this.ivConnectedSpectrum = null;
        }
    }
    
    public TzxFilePlayerStatus getStatus() {
        throw new RuntimeException("Not implemented");
    }
    
    private boolean gotoNextBlock() {
        this.ivBlockPos += this.ivBlockLen + 1;
        if (this.ivBlockPos <= this.ivTzxFile.length) {
            this.ivBlockType = this.ivTzxFile[this.ivBlockPos - 1];
            System.out.println("TZX block type " + Array.b2x(new byte[] { (byte)this.ivBlockType }) + ", pos=" + (this.ivBlockPos - 1));
            final int length = 0;
            switch (this.ivBlockType) {
                case 16: {
                    this.parse10();
                    break;
                }
                case 17: {
                    this.parse11();
                    break;
                }
                case 18: {
                    this.parse12();
                    break;
                }
                case 19: {
                    this.parse13();
                    break;
                }
                case 20: {
                    this.parse14();
                    break;
                }
                case 21: {
                    this.parse15();
                    break;
                }
                case 22: {
                    this.parse16();
                    break;
                }
                case 23: {
                    this.parse17();
                    break;
                }
                case 32: {
                    this.parse20();
                    break;
                }
                case 33: {
                    this.parse21();
                    break;
                }
                case 34: {
                    this.parse22();
                    break;
                }
                case 35: {
                    this.parse23();
                    break;
                }
                case 36: {
                    this.parse24();
                    break;
                }
                case 37: {
                    this.parse25();
                    break;
                }
                case 38: {
                    this.parse26();
                    break;
                }
                case 39: {
                    this.parse27();
                    break;
                }
                case 40: {
                    this.parse28();
                    break;
                }
                case 42: {
                    this.parse2a();
                    break;
                }
                case 48: {
                    this.parse30();
                    break;
                }
                case 49: {
                    this.parse31();
                    break;
                }
                case 50: {
                    this.parse32();
                    break;
                }
                case 51: {
                    this.parse33();
                    break;
                }
                case 52: {
                    this.parse34();
                    break;
                }
                case 53: {
                    this.parse35();
                    break;
                }
                case 64: {
                    this.parse40();
                    break;
                }
                case 90: {
                    this.parse5a();
                    break;
                }
                default: {
                    System.out.println("Unknown TZX blocktype: " + this.ivBlockType);
                    return this.gotoNextBlock();
                }
            }
            return false;
        }
        return true;
    }
    
    private void ignore() {
        System.out.println("TZX block ignored");
    }
    
    public static boolean isProgram(final byte[] pTzxFile) {
        for (int i = 10; i < pTzxFile.length - 19; ++i) {
            if (pTzxFile[i + 0] == 16 && pTzxFile[i + 3] == 19 && pTzxFile[i + 4] == 0 && pTzxFile[i + 5] == 0) {
                return pTzxFile[i + 6] != 3;
            }
        }
        return true;
    }
    
    public static boolean isTzxFile(final byte[] pBytes) {
        throw new RuntimeException("Not implemented");
    }
    
    public Object[] list() {
        final Vector list = new Vector();
        while (this.ivBlockPos < this.ivTzxFile.length) {
            this.gotoNextBlock();
            list.addElement("ID: " + Array.b2x(new byte[] { (byte)this.ivBlockType }) + ", pos: " + this.ivBlockPos + ", len: " + this.ivBlockLen);
        }
        return list.toArray();
    }
    
    public static void main(final String[] args) {
    }
    
    private void parse10() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos + 2, 2) + 4;
        this.streamerLoad(this.ivTzxFile, this.ivBlockPos + 4, (this.ivBlockLen - 4) * 8);
        this.pushPause(toIntLsbf(this.ivTzxFile, this.ivBlockPos, 2));
        this.pushBitStream(855, 1710);
        this.pushPulses(735, 1);
        this.pushPulses(667, 1);
        this.pushPulses(2168, (this.ivTzxFile[this.ivBlockPos + 4] >= 0) ? 8064 : 3220);
    }
    
    private void parse11() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos + 15, 3) + 18;
        this.streamerLoad(this.ivTzxFile, this.ivBlockPos + 18, (this.ivBlockLen - 18) * 8 - 8 + toIntLsbf(this.ivTzxFile, this.ivBlockPos + 12, 1));
        this.pushPause(toIntLsbf(this.ivTzxFile, this.ivBlockPos + 13, 2));
        this.pushBitStream(toIntLsbf(this.ivTzxFile, this.ivBlockPos + 6, 2), toIntLsbf(this.ivTzxFile, this.ivBlockPos + 8, 2));
        this.pushPulses(toIntLsbf(this.ivTzxFile, this.ivBlockPos + 4, 2), 1);
        this.pushPulses(toIntLsbf(this.ivTzxFile, this.ivBlockPos + 2, 2), 1);
        this.pushPulses(toIntLsbf(this.ivTzxFile, this.ivBlockPos + 0, 2), toIntLsbf(this.ivTzxFile, this.ivBlockPos + 10, 2));
    }
    
    private void parse12() {
        this.ivBlockLen = 4;
        this.pushPulses(toIntLsbf(this.ivTzxFile, this.ivBlockPos + 0, 2), toIntLsbf(this.ivTzxFile, this.ivBlockPos + 2, 2));
    }
    
    private void parse13() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 1) * 2 + 1;
        for (int i = this.ivBlockPos + this.ivBlockLen - 2; i >= this.ivBlockPos + 1; i -= 2) {
            this.pushPulses(toIntLsbf(this.ivTzxFile, i, 2), 1);
        }
    }
    
    private void parse14() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos + 7, 3) + 10;
        this.streamerLoad(this.ivTzxFile, this.ivBlockPos + 10, (this.ivBlockLen - 10) * 8 - 8 + toIntLsbf(this.ivTzxFile, this.ivBlockPos + 4, 1));
        this.pushPause(toIntLsbf(this.ivTzxFile, this.ivBlockPos + 5, 2));
        this.pushBitStream(toIntLsbf(this.ivTzxFile, this.ivBlockPos + 0, 2), toIntLsbf(this.ivTzxFile, this.ivBlockPos + 2, 2));
    }
    
    private void parse15() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos + 5, 3) + 8;
        this.ignore();
    }
    
    private void parse16() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 4);
        this.ignore();
    }
    
    private void parse17() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 4);
        this.ignore();
    }
    
    private void parse20() {
        this.ivBlockLen = 2;
        this.pushPause(toIntLsbf(this.ivTzxFile, this.ivBlockPos, 2));
    }
    
    private void parse21() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 1) + 1;
        this.ignore();
    }
    
    private void parse22() {
        this.ivBlockLen = 0;
        this.ignore();
    }
    
    private void parse23() {
        this.ivBlockLen = 2;
        this.ignore();
    }
    
    private void parse24() {
        this.ivBlockLen = 2;
        this.push(this.ivBlockPos + this.ivBlockLen);
        this.push(toIntLsbf(this.ivTzxFile, this.ivBlockPos, 2));
        this.push(7);
    }
    
    private void parse25() {
        this.ivBlockLen = 0;
        this.pop();
    }
    
    private void parse26() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 2) * 2 + 2;
        this.ignore();
    }
    
    private void parse27() {
        this.ivBlockLen = 0;
        this.ignore();
    }
    
    private void parse28() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 2) * 2 + 2;
        this.ignore();
    }
    
    private void parse2a() {
        this.ivBlockLen = 4;
        this.ignore();
    }
    
    private void parse30() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 1) + 1;
        this.ignore();
    }
    
    private void parse31() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos + 1, 1) + 2;
        this.ignore();
    }
    
    private void parse32() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 2) + 2;
        this.ignore();
    }
    
    private void parse33() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 1) * 3 + 1;
        this.ignore();
    }
    
    private void parse34() {
        this.ivBlockLen = 8;
        this.ignore();
    }
    
    private void parse35() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 4) + 14;
        this.ignore();
    }
    
    private void parse40() {
        this.ivBlockLen = toIntLsbf(this.ivTzxFile, this.ivBlockPos, 3) + 4;
        this.ignore();
    }
    
    private void parse5a() {
        this.ivBlockLen = 9;
        this.ignore();
    }
    
    private int pop() {
        return this.ivEventStack[this.ivEventStackPointer--];
    }
    
    private void push(final int pValue) {
        this.ivEventStack[++this.ivEventStackPointer] = pValue;
    }
    
    private void pushBitStream(final int pCycles0, final int pCycles1) {
        this.push(pCycles0);
        this.push(pCycles1);
        this.push(6);
    }
    
    private void pushPause(final int pMillis) {
        if (pMillis > 0) {
            this.push((pMillis - 1) * 3500);
            this.push(2);
            this.push(3);
            this.push(3500);
            this.push(2);
        }
    }
    
    private void pushPulses(final int pCycles, final int pNumber) {
        this.push(pCycles);
        this.push(pNumber);
        this.push(5);
    }
    
    @Override
    public int readIO(final int pAddress, final int pValue) {
        if ((pAddress & 0x1) != 0x0) {
            return pValue;
        }
        if (this.ivPlaying) {
            try {
                this.advanceToCycle(this.ivZ80.getClockFull());
            }
            catch (Throwable t) {
                System.out.println("Error in TZX file.");
                t.printStackTrace();
                this.ivPlaying = false;
            }
        }
        if (this.ivLevel) {
            return pValue & 0xBF;
        }
        return pValue;
    }
    
    @Override
    public void readOpcode1(final int pAddress) {
    }
    
    @Override
    public int readOpcode2(final int pAddress, final int pOpcode) {
        return pOpcode;
    }
    
    private void resetEventStack() {
        this.ivEventStack = new int[1000];
        this.ivEventStackPointer = 0;
    }
    
    public void setStatus(final TzxFilePlayerStatus pStatus) {
        this.setTzxFileBuffer(pStatus.getTzxFileBytes(), 0);
    }
    
    public void setTzxFileBuffer(final byte[] pTzxFile, final int pPause) {
        this.ivTzxFile = pTzxFile;
        this.ivBlockPos = 0;
        this.ivBlockLen = 10;
        this.ivCycleNextEvent = 0L;
        this.resetEventStack();
        this.push(9);
        this.pushPause(pPause);
        this.push(8);
        this.ivPlaying = true;
    }
    
    private boolean streamerIsEmpty() {
        return this.ivStreamerBitPos >= this.ivStreamerBitPosEnd;
    }
    
    private void streamerLoad(final byte[] pSrc, final int pSrcPos, final int pNumberOfBits) {
        this.ivStreamerBuffer = pSrc;
        this.ivStreamerBitPos = pSrcPos << 3;
        this.ivStreamerBitPosEnd = this.ivStreamerBitPos + pNumberOfBits;
    }
    
    private boolean streamerReadNext() {
        final boolean result = (this.ivStreamerBuffer[this.ivStreamerBitPos >> 3] << (this.ivStreamerBitPos & 0x7) & 0x80) != 0x0;
        ++this.ivStreamerBitPos;
        return result;
    }
    
    public void terminate() {
    }
    
    private static int toIntLsbf(final byte[] pSrc, final int pSrcPos, final int pSrcLen) {
        return Array.toIntLsbf(pSrc, pSrcPos, pSrcLen);
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
    }
}
