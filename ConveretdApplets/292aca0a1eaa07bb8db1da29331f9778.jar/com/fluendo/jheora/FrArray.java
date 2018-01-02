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
    
    public void init() {
        this.bit_pattern = 0;
        this.bits_so_far = 0;
    }
    
    private int deCodeBlockRun(final int bit_value) {
        ++this.bits_so_far;
        this.bit_pattern = (this.bit_pattern << 1) + (bit_value & 0x1);
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
    
    private int deCodeSBRun(final int bit_value) {
        ++this.bits_so_far;
        this.bit_pattern = (this.bit_pattern << 1) + (bit_value & 0x1);
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
    
    private void getNextBInit(final Buffer opb) {
        long ret = opb.readB(1);
        this.NextBit = (byte)ret;
        this.init();
        do {
            ret = opb.readB(1);
        } while (this.deCodeBlockRun((int)ret) == 0);
    }
    
    private byte getNextBBit(final Buffer opb) {
        if (this.BitsLeft == 0) {
            this.NextBit = (byte)((this.NextBit != 1) ? 1 : 0);
            this.init();
            long ret;
            do {
                ret = opb.readB(1);
            } while (this.deCodeBlockRun((int)ret) == 0);
        }
        --this.BitsLeft;
        return this.NextBit;
    }
    
    private void getNextSbInit(final Buffer opb) {
        long ret = opb.readB(1);
        this.NextBit = (byte)ret;
        this.init();
        do {
            ret = opb.readB(1);
        } while (this.deCodeSBRun((int)ret) == 0);
    }
    
    private byte getNextSbBit(final Buffer opb) {
        if (this.BitsLeft == 0) {
            this.NextBit = (byte)((this.NextBit != 1) ? 1 : 0);
            this.init();
            long ret;
            do {
                ret = opb.readB(1);
            } while (this.deCodeSBRun((int)ret) == 0);
        }
        --this.BitsLeft;
        return this.NextBit;
    }
    
    public void quadDecodeDisplayFragments(final Playback pbi) {
        int MBIndex = 0;
        final Buffer opb = pbi.opb;
        pbi.CodedBlockIndex = 0;
        MemUtils.set(pbi.display_fragments, 0, 0, pbi.UnitFragments);
        if (pbi.getFrameType() == 0) {
            MemUtils.set(pbi.SBFullyFlags, 0, 1, pbi.SuperBlocks);
            MemUtils.set(pbi.SBCodedFlags, 0, 1, pbi.SuperBlocks);
            MemUtils.set(pbi.MBCodedFlags, 0, 0, pbi.MacroBlocks);
        }
        else {
            MemUtils.set(pbi.SBFullyFlags, 0, 0, pbi.SuperBlocks);
            MemUtils.set(pbi.MBCodedFlags, 0, 0, pbi.MacroBlocks);
            this.getNextSbInit(opb);
            for (int SB = 0; SB < pbi.SuperBlocks; ++SB) {
                pbi.SBCodedFlags[SB] = this.getNextSbBit(opb);
            }
            int DataToDecode = 0;
            for (int SB = 0; SB < pbi.SuperBlocks; ++SB) {
                if (pbi.SBCodedFlags[SB] == 0) {
                    DataToDecode = 1;
                    break;
                }
            }
            if (DataToDecode != 0) {
                this.getNextSbInit(opb);
                for (int SB = 0; SB < pbi.SuperBlocks; ++SB) {
                    while (SB < pbi.SuperBlocks && pbi.SBCodedFlags[SB] != 0) {
                        ++SB;
                    }
                    if (SB < pbi.SuperBlocks) {
                        pbi.SBFullyFlags[SB] = this.getNextSbBit(opb);
                        if (pbi.SBFullyFlags[SB] != 0) {
                            pbi.SBCodedFlags[SB] = 1;
                        }
                    }
                }
            }
            for (int SB = 0; SB < pbi.SuperBlocks; ++SB) {
                if (pbi.SBCodedFlags[SB] != 0 && pbi.SBFullyFlags[SB] == 0) {
                    this.getNextBInit(opb);
                    break;
                }
            }
        }
        for (int SB = 0; SB < pbi.SuperBlocks; ++SB) {
            for (int MB = 0; MB < 4; ++MB) {
                if (pbi.BlockMap.quadMapToMBTopLeft(SB, MB) >= 0) {
                    if (pbi.SBCodedFlags[SB] != 0) {
                        for (int B = 0; B < 4; ++B) {
                            final int dfIndex = pbi.BlockMap.quadMapToIndex1(SB, MB, B);
                            if (dfIndex >= 0) {
                                if (pbi.SBFullyFlags[SB] != 0) {
                                    pbi.display_fragments[dfIndex] = 1;
                                }
                                else {
                                    pbi.display_fragments[dfIndex] = this.getNextBBit(opb);
                                }
                                if (pbi.display_fragments[dfIndex] != 0) {
                                    pbi.MBCodedFlags[MBIndex] = 1;
                                    pbi.CodedBlockList[pbi.CodedBlockIndex] = dfIndex;
                                    ++pbi.CodedBlockIndex;
                                }
                            }
                        }
                    }
                    ++MBIndex;
                }
            }
        }
    }
    
    public CodingMode unpackMode(final Buffer opb) {
        this.bits_so_far = 0;
        this.bit_pattern = opb.readB(1);
        if (this.bit_pattern == 0) {
            return CodingMode.MODES[0];
        }
        this.bit_pattern = (this.bit_pattern << 1 | opb.readB(1));
        if (this.bit_pattern == 2) {
            return CodingMode.MODES[1];
        }
        this.bit_pattern = (this.bit_pattern << 1 | opb.readB(1));
        if (this.bit_pattern == 6) {
            return CodingMode.MODES[2];
        }
        this.bit_pattern = (this.bit_pattern << 1 | opb.readB(1));
        if (this.bit_pattern == 14) {
            return CodingMode.MODES[3];
        }
        this.bit_pattern = (this.bit_pattern << 1 | opb.readB(1));
        if (this.bit_pattern == 30) {
            return CodingMode.MODES[4];
        }
        this.bit_pattern = (this.bit_pattern << 1 | opb.readB(1));
        if (this.bit_pattern == 62) {
            return CodingMode.MODES[5];
        }
        this.bit_pattern = (this.bit_pattern << 1 | opb.readB(1));
        if (this.bit_pattern == 126) {
            return CodingMode.MODES[6];
        }
        return CodingMode.MODES[7];
    }
}
