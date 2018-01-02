// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.fluendo.utils.MemUtils;
import com.jcraft.jogg.Buffer;

public class FrArray
{
    private int bit_pattern;
    private byte bits_so_far;
    private byte NextBit;
    private int BitsLeft;
    private final short[] empty64;
    
    public FrArray() {
        this.empty64 = new short[64];
    }
    
    public void init() {
        this.bit_pattern = 0;
        this.bits_so_far = 0;
    }
    
    private int deCodeBlockRun(final int n) {
        ++this.bits_so_far;
        this.bit_pattern = (this.bit_pattern << 1) + (n & 0x1);
        switch (this.bits_so_far) {
            case 2: {
                if ((this.bit_pattern & 0x2) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x1) + 1;
                    return 1;
                }
                break;
            }
            case 3: {
                if ((this.bit_pattern & 0x2) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x1) + 3;
                    return 1;
                }
                break;
            }
            case 4: {
                if ((this.bit_pattern & 0x2) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x1) + 5;
                    return 1;
                }
                break;
            }
            case 6: {
                if ((this.bit_pattern & 0x4) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x3) + 7;
                    return 1;
                }
                break;
            }
            case 7: {
                if ((this.bit_pattern & 0x4) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x3) + 11;
                    return 1;
                }
                break;
            }
            case 9: {
                this.BitsLeft = (this.bit_pattern & 0xF) + 15;
                return 1;
            }
        }
        return 0;
    }
    
    private int deCodeSBRun(final int n) {
        ++this.bits_so_far;
        this.bit_pattern = (this.bit_pattern << 1) + (n & 0x1);
        switch (this.bits_so_far) {
            case 1: {
                if (this.bit_pattern == 0) {
                    return this.BitsLeft = 1;
                }
                break;
            }
            case 3: {
                if ((this.bit_pattern & 0x2) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x1) + 2;
                    return 1;
                }
                break;
            }
            case 4: {
                if ((this.bit_pattern & 0x2) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x1) + 4;
                    return 1;
                }
                break;
            }
            case 6: {
                if ((this.bit_pattern & 0x4) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x3) + 6;
                    return 1;
                }
                break;
            }
            case 8: {
                if ((this.bit_pattern & 0x8) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0x7) + 10;
                    return 1;
                }
                break;
            }
            case 10: {
                if ((this.bit_pattern & 0x10) == 0x0) {
                    this.BitsLeft = (this.bit_pattern & 0xF) + 18;
                    return 1;
                }
                break;
            }
            case 18: {
                this.BitsLeft = (this.bit_pattern & 0xFFF) + 34;
                return 1;
            }
        }
        return 0;
    }
    
    private void getNextBInit(final Buffer buffer) {
        this.NextBit = (byte)buffer.readB(1);
        this.init();
        while (this.deCodeBlockRun(buffer.readB(1)) == 0) {}
    }
    
    private byte getNextBBit(final Buffer buffer) {
        if (this.BitsLeft == 0) {
            this.NextBit ^= 0x1;
            this.init();
            while (this.deCodeBlockRun(buffer.readB(1)) == 0) {}
        }
        --this.BitsLeft;
        return this.NextBit;
    }
    
    private void getNextSbInit(final Buffer buffer) {
        this.NextBit = (byte)buffer.readB(1);
        this.init();
        while (this.deCodeSBRun(buffer.readB(1)) == 0) {}
    }
    
    private byte getNextSbBit(final Buffer buffer) {
        if (this.BitsLeft == 0) {
            this.NextBit ^= 0x1;
            this.init();
            while (this.deCodeSBRun(buffer.readB(1)) == 0) {}
        }
        --this.BitsLeft;
        return this.NextBit;
    }
    
    public void quadDecodeDisplayFragments(final Playback playback) {
        int n = 0;
        final Buffer opb = playback.opb;
        playback.CodedBlockIndex = 0;
        MemUtils.set(playback.display_fragments, 0, 0, playback.UnitFragments);
        if (playback.getFrameType() == 0) {
            MemUtils.set(playback.SBFullyFlags, 0, 1, playback.SuperBlocks);
            MemUtils.set(playback.SBCodedFlags, 0, 1, playback.SuperBlocks);
            MemUtils.set(playback.MBCodedFlags, 0, 0, playback.MacroBlocks);
        }
        else {
            MemUtils.set(playback.SBFullyFlags, 0, 0, playback.SuperBlocks);
            MemUtils.set(playback.MBCodedFlags, 0, 0, playback.MacroBlocks);
            this.getNextSbInit(opb);
            for (int i = 0; i < playback.SuperBlocks; ++i) {
                playback.SBCodedFlags[i] = this.getNextSbBit(opb);
            }
            boolean b = false;
            for (int j = 0; j < playback.SuperBlocks; ++j) {
                if (playback.SBCodedFlags[j] == 0) {
                    b = true;
                    break;
                }
            }
            if (b) {
                this.getNextSbInit(opb);
                for (int k = 0; k < playback.SuperBlocks; ++k) {
                    while (k < playback.SuperBlocks && playback.SBCodedFlags[k] != 0) {
                        ++k;
                    }
                    if (k < playback.SuperBlocks) {
                        playback.SBFullyFlags[k] = this.getNextSbBit(opb);
                        if (playback.SBFullyFlags[k] != 0) {
                            playback.SBCodedFlags[k] = 1;
                        }
                    }
                }
            }
            for (int l = 0; l < playback.SuperBlocks; ++l) {
                if (playback.SBCodedFlags[l] != 0 && playback.SBFullyFlags[l] == 0) {
                    this.getNextBInit(opb);
                    break;
                }
            }
        }
        for (int n2 = 0; n2 < playback.SuperBlocks; ++n2) {
            for (int n3 = 0; n3 < 4; ++n3) {
                if (playback.BlockMap.quadMapToMBTopLeft(n2, n3) >= 0) {
                    if (playback.SBCodedFlags[n2] != 0) {
                        for (int n4 = 0; n4 < 4; ++n4) {
                            final int quadMapToIndex1 = playback.BlockMap.quadMapToIndex1(n2, n3, n4);
                            if (quadMapToIndex1 >= 0) {
                                if (playback.SBFullyFlags[n2] != 0) {
                                    playback.display_fragments[quadMapToIndex1] = 1;
                                }
                                else {
                                    playback.display_fragments[quadMapToIndex1] = this.getNextBBit(opb);
                                }
                                if (playback.display_fragments[quadMapToIndex1] != 0) {
                                    playback.MBCodedFlags[n] = 1;
                                    playback.CodedBlockList[playback.CodedBlockIndex] = quadMapToIndex1;
                                    System.arraycopy(this.empty64, 0, playback.QFragData[quadMapToIndex1], 0, 64);
                                    ++playback.CodedBlockIndex;
                                }
                            }
                        }
                    }
                    ++n;
                }
            }
        }
    }
    
    public CodingMode unpackMode(final Buffer buffer) {
        this.bits_so_far = 0;
        this.bit_pattern = buffer.readB(1);
        if (this.bit_pattern == 0) {
            return CodingMode.MODES[0];
        }
        this.bit_pattern = (this.bit_pattern << 1 | buffer.readB(1));
        if (this.bit_pattern == 2) {
            return CodingMode.MODES[1];
        }
        this.bit_pattern = (this.bit_pattern << 1 | buffer.readB(1));
        if (this.bit_pattern == 6) {
            return CodingMode.MODES[2];
        }
        this.bit_pattern = (this.bit_pattern << 1 | buffer.readB(1));
        if (this.bit_pattern == 14) {
            return CodingMode.MODES[3];
        }
        this.bit_pattern = (this.bit_pattern << 1 | buffer.readB(1));
        if (this.bit_pattern == 30) {
            return CodingMode.MODES[4];
        }
        this.bit_pattern = (this.bit_pattern << 1 | buffer.readB(1));
        if (this.bit_pattern == 62) {
            return CodingMode.MODES[5];
        }
        this.bit_pattern = (this.bit_pattern << 1 | buffer.readB(1));
        if (this.bit_pattern == 126) {
            return CodingMode.MODES[6];
        }
        return CodingMode.MODES[7];
    }
}
