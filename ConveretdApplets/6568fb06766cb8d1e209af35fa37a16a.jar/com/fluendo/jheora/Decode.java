// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.fluendo.utils.MemUtils;
import com.jcraft.jogg.Buffer;

public final class Decode
{
    private static final ExtractMVectorComponent MVA;
    private static final ExtractMVectorComponent MVB;
    private static final CodingMode[][] modeAlphabet;
    private int BlocksToDecode;
    private int EOB_Run;
    private DCTDecode dctDecode;
    private byte[] FragCoeffs;
    private MotionVector LastInterMV;
    private MotionVector PriorLastInterMV;
    private Playback pbi;
    
    public Decode(final Playback pbi) {
        this.dctDecode = new DCTDecode();
        this.LastInterMV = new MotionVector();
        this.PriorLastInterMV = new MotionVector();
        this.FragCoeffs = new byte[pbi.UnitFragments];
        this.pbi = pbi;
    }
    
    private int longRunBitStringDecode() {
        final Buffer opb = this.pbi.opb;
        if (opb.readB(1) == 0) {
            return 1;
        }
        final int b = opb.readB(2);
        if ((b & 0x2) == 0x0) {
            return 2 + b;
        }
        if ((b & 0x1) == 0x0) {
            return 4 + opb.readB(1);
        }
        final int b2 = opb.readB(3);
        if ((b2 & 0x4) == 0x0) {
            return 6 + b2;
        }
        if ((b2 & 0x2) == 0x0) {
            return 10 + ((b2 & 0x1) << 2) + opb.readB(2);
        }
        if ((b2 & 0x1) == 0x0) {
            return 18 + opb.readB(4);
        }
        return 34 + opb.readB(12);
    }
    
    private void decodeBlockLevelQi() {
        final int codedBlockIndex = this.pbi.CodedBlockIndex;
        if (codedBlockIndex <= 0) {
            return;
        }
        if (this.pbi.frameNQIS == 1) {
            for (int i = 0; i < codedBlockIndex; ++i) {
                this.pbi.FragQs[this.pbi.CodedBlockList[i]] = 0;
            }
        }
        else {
            final Buffer opb = this.pbi.opb;
            int n = opb.readB(1);
            int n2 = 0;
            int j = 0;
            while (j < codedBlockIndex) {
                int longRunBitStringDecode = this.longRunBitStringDecode();
                final boolean b = longRunBitStringDecode >= 4129;
                do {
                    this.pbi.FragQs[this.pbi.CodedBlockList[j++]] = (byte)n;
                    if (n < 1) {
                        ++n2;
                    }
                } while (--longRunBitStringDecode > 0 && j < codedBlockIndex);
                if (b && j < codedBlockIndex) {
                    n = opb.readB(1);
                }
                else {
                    n = ((n == 0) ? 1 : 0);
                }
            }
            if (this.pbi.frameNQIS == 3 && n2 < codedBlockIndex) {
                int k;
                for (k = 0; k < codedBlockIndex && this.pbi.FragQs[this.pbi.CodedBlockList[k]] == 0; ++k) {}
                int n3 = opb.readB(1);
                while (k < codedBlockIndex) {
                    int longRunBitStringDecode2 = this.longRunBitStringDecode();
                    final boolean b2 = longRunBitStringDecode2 >= 4129;
                    while (k < codedBlockIndex) {
                        if (this.pbi.FragQs[this.pbi.CodedBlockList[k]] != 0) {
                            if (longRunBitStringDecode2-- <= 0) {
                                break;
                            }
                            final byte[] fragQs = this.pbi.FragQs;
                            final int n4 = this.pbi.CodedBlockList[k];
                            fragQs[n4] += (byte)n3;
                        }
                        ++k;
                    }
                    if (b2 && k < codedBlockIndex) {
                        n3 = opb.readB(1);
                    }
                    else {
                        n3 = ((n3 == 0) ? 1 : 0);
                    }
                }
            }
        }
    }
    
    private int loadFrame() {
        final Buffer opb = this.pbi.opb;
        this.pbi.FrameType = (byte)opb.readB(1);
        this.pbi.frameQIS[0] = opb.readB(6);
        this.pbi.frameNQIS = 1;
        if (opb.readB(1) > 0) {
            this.pbi.frameQIS[1] = opb.readB(6);
            this.pbi.frameNQIS = 2;
            if (opb.readB(1) > 0) {
                this.pbi.frameQIS[2] = opb.readB(6);
                this.pbi.frameNQIS = 3;
            }
        }
        if (this.pbi.FrameType == 0) {
            this.pbi.KeyFrameType = (byte)opb.readB(1);
            opb.readB(2);
        }
        this.pbi.frArray.quadDecodeDisplayFragments(this.pbi);
        return 1;
    }
    
    private void decodeModes(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        final CodingMode[] fragCodingMethod = this.pbi.FragCodingMethod;
        if (this.pbi.getFrameType() == 0) {
            MemUtils.set(fragCodingMethod, 0, CodingMode.CODE_INTRA, this.pbi.UnitFragments);
        }
        else {
            MemUtils.set(fragCodingMethod, 0, CodingMode.CODE_INTER_NO_MV, this.pbi.UnitFragments);
            final int n5 = this.pbi.opb.readB(3);
            CodingMode[] array2;
            if (n5 == 0) {
                final CodingMode[] array = new CodingMode[8];
                for (int i = 0; i < 8; ++i) {
                    array[this.pbi.opb.readB(3)] = CodingMode.MODES[i];
                }
                array2 = array;
            }
            else {
                array2 = Decode.modeAlphabet[n5 - 1];
            }
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n2; ++k) {
                    for (int l = 0; l < 4; ++l) {
                        final int quadMapToMBTopLeft = this.pbi.BlockMap.quadMapToMBTopLeft(n3, l);
                        if (quadMapToMBTopLeft >= 0 && this.pbi.MBCodedFlags[n4++] != 0) {
                            CodingMode codingMode;
                            if (n5 == 7) {
                                codingMode = CodingMode.MODES[this.pbi.opb.readB(3)];
                            }
                            else {
                                codingMode = array2[this.pbi.frArray.unpackMode(this.pbi.opb).getValue()];
                            }
                            fragCodingMethod[quadMapToMBTopLeft + 1] = (fragCodingMethod[quadMapToMBTopLeft] = codingMode);
                            fragCodingMethod[quadMapToMBTopLeft + this.pbi.HFragments] = codingMode;
                            fragCodingMethod[quadMapToMBTopLeft + this.pbi.HFragments + 1] = codingMode;
                            final int n6 = quadMapToMBTopLeft / (this.pbi.HFragments * 2) * (this.pbi.HFragments / 2) + quadMapToMBTopLeft % this.pbi.HFragments / 2;
                            fragCodingMethod[this.pbi.YPlaneFragments + n6] = codingMode;
                            fragCodingMethod[this.pbi.YPlaneFragments + this.pbi.UVPlaneFragments + n6] = codingMode;
                        }
                    }
                    ++n3;
                }
            }
        }
    }
    
    private void decodeMVectors(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        final Buffer opb = this.pbi.opb;
        if (this.pbi.getFrameType() == 0) {
            return;
        }
        this.LastInterMV.x = 0;
        this.LastInterMV.y = 0;
        this.PriorLastInterMV.x = 0;
        this.PriorLastInterMV.y = 0;
        ExtractMVectorComponent extractMVectorComponent;
        if (opb.readB(1) == 0) {
            extractMVectorComponent = Decode.MVA;
        }
        else {
            extractMVectorComponent = Decode.MVB;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                for (int k = 0; k < 4; ++k) {
                    final int quadMapToMBTopLeft = this.pbi.BlockMap.quadMapToMBTopLeft(n3, k);
                    if (quadMapToMBTopLeft >= 0 && this.pbi.MBCodedFlags[n4++] != 0) {
                        final CodingMode codingMode = this.pbi.FragCodingMethod[quadMapToMBTopLeft];
                        final MotionVector motionVector = this.pbi.FragMVect[quadMapToMBTopLeft];
                        final MotionVector motionVector2 = this.pbi.FragMVect[quadMapToMBTopLeft + 1];
                        final MotionVector motionVector3 = this.pbi.FragMVect[quadMapToMBTopLeft + this.pbi.HFragments];
                        final MotionVector motionVector4 = this.pbi.FragMVect[quadMapToMBTopLeft + this.pbi.HFragments + 1];
                        final int n5 = quadMapToMBTopLeft / (this.pbi.HFragments * 2) * (this.pbi.HFragments / 2) + quadMapToMBTopLeft % this.pbi.HFragments / 2;
                        final MotionVector motionVector5 = this.pbi.FragMVect[this.pbi.YPlaneFragments + n5];
                        final MotionVector motionVector6 = this.pbi.FragMVect[this.pbi.YPlaneFragments + this.pbi.UVPlaneFragments + n5];
                        if (codingMode == CodingMode.CODE_INTER_PLUS_MV) {
                            this.PriorLastInterMV.x = this.LastInterMV.x;
                            this.PriorLastInterMV.y = this.LastInterMV.y;
                            final MotionVector lastInterMV = this.LastInterMV;
                            final MotionVector motionVector7 = motionVector;
                            final MotionVector motionVector8 = motionVector2;
                            final MotionVector motionVector9 = motionVector3;
                            final MotionVector motionVector10 = motionVector4;
                            final MotionVector motionVector11 = motionVector5;
                            final MotionVector motionVector12 = motionVector6;
                            final int extract = extractMVectorComponent.extract(opb);
                            motionVector12.x = extract;
                            motionVector11.x = extract;
                            motionVector10.x = extract;
                            motionVector9.x = extract;
                            motionVector8.x = extract;
                            motionVector7.x = extract;
                            lastInterMV.x = extract;
                            final MotionVector lastInterMV2 = this.LastInterMV;
                            final MotionVector motionVector13 = motionVector;
                            final MotionVector motionVector14 = motionVector2;
                            final MotionVector motionVector15 = motionVector3;
                            final MotionVector motionVector16 = motionVector4;
                            final MotionVector motionVector17 = motionVector5;
                            final MotionVector motionVector18 = motionVector6;
                            final int extract2 = extractMVectorComponent.extract(opb);
                            motionVector18.y = extract2;
                            motionVector17.y = extract2;
                            motionVector16.y = extract2;
                            motionVector15.y = extract2;
                            motionVector14.y = extract2;
                            motionVector13.y = extract2;
                            lastInterMV2.y = extract2;
                        }
                        else if (codingMode == CodingMode.CODE_GOLDEN_MV) {
                            final MotionVector motionVector19 = motionVector;
                            final MotionVector motionVector20 = motionVector2;
                            final MotionVector motionVector21 = motionVector3;
                            final MotionVector motionVector22 = motionVector4;
                            final MotionVector motionVector23 = motionVector5;
                            final MotionVector motionVector24 = motionVector6;
                            final int extract3 = extractMVectorComponent.extract(opb);
                            motionVector24.x = extract3;
                            motionVector23.x = extract3;
                            motionVector22.x = extract3;
                            motionVector21.x = extract3;
                            motionVector20.x = extract3;
                            motionVector19.x = extract3;
                            final MotionVector motionVector25 = motionVector;
                            final MotionVector motionVector26 = motionVector2;
                            final MotionVector motionVector27 = motionVector3;
                            final MotionVector motionVector28 = motionVector4;
                            final MotionVector motionVector29 = motionVector5;
                            final MotionVector motionVector30 = motionVector6;
                            final int extract4 = extractMVectorComponent.extract(opb);
                            motionVector30.y = extract4;
                            motionVector29.y = extract4;
                            motionVector28.y = extract4;
                            motionVector27.y = extract4;
                            motionVector26.y = extract4;
                            motionVector25.y = extract4;
                        }
                        else if (codingMode == CodingMode.CODE_INTER_FOURMV) {
                            this.PriorLastInterMV.x = this.LastInterMV.x;
                            this.PriorLastInterMV.y = this.LastInterMV.y;
                            int n6;
                            int n7;
                            if (this.pbi.display_fragments[quadMapToMBTopLeft] != 0) {
                                final MotionVector motionVector31 = motionVector;
                                final int extract5 = extractMVectorComponent.extract(opb);
                                motionVector31.x = extract5;
                                n6 = extract5;
                                final MotionVector motionVector32 = motionVector;
                                final int extract6 = extractMVectorComponent.extract(opb);
                                motionVector32.y = extract6;
                                n7 = extract6;
                                this.LastInterMV.x = motionVector.x;
                                this.LastInterMV.y = motionVector.y;
                            }
                            else {
                                final MotionVector motionVector33 = motionVector;
                                final boolean x = false;
                                motionVector33.x = (x ? 1 : 0);
                                n6 = (x ? 1 : 0);
                                final MotionVector motionVector34 = motionVector;
                                final boolean y = false;
                                motionVector34.y = (y ? 1 : 0);
                                n7 = (y ? 1 : 0);
                            }
                            int n9;
                            int n11;
                            if (this.pbi.display_fragments[quadMapToMBTopLeft + 1] != 0) {
                                final int n8 = n6;
                                final MotionVector motionVector35 = motionVector2;
                                final int extract7 = extractMVectorComponent.extract(opb);
                                motionVector35.x = extract7;
                                n9 = n8 + extract7;
                                final int n10 = n7;
                                final MotionVector motionVector36 = motionVector2;
                                final int extract8 = extractMVectorComponent.extract(opb);
                                motionVector36.y = extract8;
                                n11 = n10 + extract8;
                                this.LastInterMV.x = motionVector2.x;
                                this.LastInterMV.y = motionVector2.y;
                            }
                            else {
                                final int n12 = n6;
                                final MotionVector motionVector37 = motionVector2;
                                final int x2 = 0;
                                motionVector37.x = x2;
                                n9 = n12 + x2;
                                final int n13 = n7;
                                final MotionVector motionVector38 = motionVector2;
                                final int y2 = 0;
                                motionVector38.y = y2;
                                n11 = n13 + y2;
                            }
                            int n15;
                            int n17;
                            if (this.pbi.display_fragments[quadMapToMBTopLeft + this.pbi.HFragments] != 0) {
                                final int n14 = n9;
                                final MotionVector motionVector39 = motionVector3;
                                final int extract9 = extractMVectorComponent.extract(opb);
                                motionVector39.x = extract9;
                                n15 = n14 + extract9;
                                final int n16 = n11;
                                final MotionVector motionVector40 = motionVector3;
                                final int extract10 = extractMVectorComponent.extract(opb);
                                motionVector40.y = extract10;
                                n17 = n16 + extract10;
                                this.LastInterMV.x = motionVector3.x;
                                this.LastInterMV.y = motionVector3.y;
                            }
                            else {
                                final int n18 = n9;
                                final MotionVector motionVector41 = motionVector3;
                                final int x3 = 0;
                                motionVector41.x = x3;
                                n15 = n18 + x3;
                                final int n19 = n11;
                                final MotionVector motionVector42 = motionVector3;
                                final int y3 = 0;
                                motionVector42.y = y3;
                                n17 = n19 + y3;
                            }
                            int n21;
                            int n23;
                            if (this.pbi.display_fragments[quadMapToMBTopLeft + this.pbi.HFragments + 1] != 0) {
                                final int n20 = n15;
                                final MotionVector motionVector43 = motionVector4;
                                final int extract11 = extractMVectorComponent.extract(opb);
                                motionVector43.x = extract11;
                                n21 = n20 + extract11;
                                final int n22 = n17;
                                final MotionVector motionVector44 = motionVector4;
                                final int extract12 = extractMVectorComponent.extract(opb);
                                motionVector44.y = extract12;
                                n23 = n22 + extract12;
                                this.LastInterMV.x = motionVector4.x;
                                this.LastInterMV.y = motionVector4.y;
                            }
                            else {
                                final int n24 = n15;
                                final MotionVector motionVector45 = motionVector4;
                                final int x4 = 0;
                                motionVector45.x = x4;
                                n21 = n24 + x4;
                                final int n25 = n17;
                                final MotionVector motionVector46 = motionVector4;
                                final int y4 = 0;
                                motionVector46.y = y4;
                                n23 = n25 + y4;
                            }
                            int n26;
                            if (n21 >= 0) {
                                n26 = (n21 + 2) / 4;
                            }
                            else {
                                n26 = (n21 - 2) / 4;
                            }
                            motionVector5.x = n26;
                            motionVector6.x = n26;
                            int n27;
                            if (n23 >= 0) {
                                n27 = (n23 + 2) / 4;
                            }
                            else {
                                n27 = (n23 - 2) / 4;
                            }
                            motionVector5.y = n27;
                            motionVector6.y = n27;
                        }
                        else if (codingMode == CodingMode.CODE_INTER_LAST_MV) {
                            final MotionVector motionVector47 = motionVector;
                            final MotionVector motionVector48 = motionVector2;
                            final MotionVector motionVector49 = motionVector3;
                            final MotionVector motionVector50 = motionVector4;
                            final MotionVector motionVector51 = motionVector5;
                            final MotionVector motionVector52 = motionVector6;
                            final int x5 = this.LastInterMV.x;
                            motionVector52.x = x5;
                            motionVector51.x = x5;
                            motionVector50.x = x5;
                            motionVector49.x = x5;
                            motionVector48.x = x5;
                            motionVector47.x = x5;
                            final MotionVector motionVector53 = motionVector;
                            final MotionVector motionVector54 = motionVector2;
                            final MotionVector motionVector55 = motionVector3;
                            final MotionVector motionVector56 = motionVector4;
                            final MotionVector motionVector57 = motionVector5;
                            final MotionVector motionVector58 = motionVector6;
                            final int y5 = this.LastInterMV.y;
                            motionVector58.y = y5;
                            motionVector57.y = y5;
                            motionVector56.y = y5;
                            motionVector55.y = y5;
                            motionVector54.y = y5;
                            motionVector53.y = y5;
                        }
                        else if (codingMode == CodingMode.CODE_INTER_PRIOR_LAST) {
                            final MotionVector motionVector59 = motionVector;
                            final MotionVector motionVector60 = motionVector2;
                            final MotionVector motionVector61 = motionVector3;
                            final MotionVector motionVector62 = motionVector4;
                            final MotionVector motionVector63 = motionVector5;
                            final MotionVector motionVector64 = motionVector6;
                            final int x6 = this.PriorLastInterMV.x;
                            motionVector64.x = x6;
                            motionVector63.x = x6;
                            motionVector62.x = x6;
                            motionVector61.x = x6;
                            motionVector60.x = x6;
                            motionVector59.x = x6;
                            final MotionVector motionVector65 = motionVector;
                            final MotionVector motionVector66 = motionVector2;
                            final MotionVector motionVector67 = motionVector3;
                            final MotionVector motionVector68 = motionVector4;
                            final MotionVector motionVector69 = motionVector5;
                            final MotionVector motionVector70 = motionVector6;
                            final int y6 = this.PriorLastInterMV.y;
                            motionVector70.y = y6;
                            motionVector69.y = y6;
                            motionVector68.y = y6;
                            motionVector67.y = y6;
                            motionVector66.y = y6;
                            motionVector65.y = y6;
                            final MotionVector priorLastInterMV = this.PriorLastInterMV;
                            this.PriorLastInterMV = this.LastInterMV;
                            this.LastInterMV = priorLastInterMV;
                        }
                        else {
                            motionVector.x = 0;
                            motionVector.y = 0;
                        }
                    }
                }
                ++n3;
            }
        }
    }
    
    private final int ExtractToken(final Buffer buffer, HuffEntry huffEntry) {
        while (huffEntry.value < 0) {
            huffEntry = huffEntry.Child[buffer.readB(1)];
        }
        return huffEntry.value;
    }
    
    private void unpackAndExpandToken(final short[] array, final byte[] array2, final int n, final int n2) {
        int b = 0;
        final int extractToken = this.ExtractToken(this.pbi.opb, this.pbi.HuffRoot_VP3x[n2]);
        if (this.pbi.ExtraBitLengths_VP3x[extractToken] > 0) {
            b = this.pbi.opb.readB(this.pbi.ExtraBitLengths_VP3x[extractToken]);
        }
        if (extractToken >= 7) {
            this.dctDecode.ExpandToken(array, array2, n, extractToken, b);
            if (array2[n] >= 64) {
                --this.BlocksToDecode;
            }
        }
        else {
            switch (extractToken) {
                case 1: {
                    this.EOB_Run = 1;
                    break;
                }
                case 2: {
                    this.EOB_Run = 2;
                    break;
                }
                case 3: {
                    this.EOB_Run = b + 3;
                    break;
                }
                case 4: {
                    this.EOB_Run = b + 7;
                    break;
                }
                case 5: {
                    this.EOB_Run = b + 15;
                    break;
                }
                case 6: {
                    this.EOB_Run = b - 1;
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    return;
                }
            }
            array2[n] = 64;
            --this.BlocksToDecode;
        }
    }
    
    private void unPackVideo() {
        byte b = 1;
        if (this.pbi.DecoderErrorCode != 0) {
            return;
        }
        MemUtils.set(this.FragCoeffs, 0, 0, this.pbi.UnitFragments);
        MemUtils.set(this.pbi.FragCoefEOB, 0, 0, this.pbi.UnitFragments);
        this.BlocksToDecode = this.pbi.CodedBlockIndex;
        final int n = this.pbi.opb.readB(4) + 0;
        final int n2 = this.pbi.opb.readB(4) + 0;
        for (int i = 0; i < this.pbi.CodedBlockIndex; ++i) {
            final int n3 = this.pbi.CodedBlockList[i];
            this.pbi.FragCoefEOB[n3] = this.FragCoeffs[n3];
            int n4;
            if (n3 < this.pbi.YPlaneFragments) {
                n4 = n;
            }
            else {
                n4 = n2;
            }
            if (this.EOB_Run != 0) {
                this.FragCoeffs[n3] = 64;
                --this.EOB_Run;
                --this.BlocksToDecode;
            }
            else {
                this.unpackAndExpandToken(this.pbi.QFragData[n3], this.FragCoeffs, n3, n4);
            }
        }
        final int n5 = this.pbi.opb.readB(4) + 16;
        final int n6 = this.pbi.opb.readB(4) + 16;
        while (b < 64) {
            int j = 0;
            final int codedBlockIndex = this.pbi.CodedBlockIndex;
            int n7;
            int n8;
            if (b <= 5) {
                n7 = n5;
                n8 = n6;
            }
            else if (b <= 14) {
                n7 = n5 + 16;
                n8 = n6 + 16;
            }
            else if (b <= 27) {
                n7 = n5 + 32;
                n8 = n6 + 32;
            }
            else {
                n7 = n5 + 48;
                n8 = n6 + 48;
            }
            while (j < codedBlockIndex) {
                final int n9 = this.pbi.CodedBlockList[j];
                if (this.FragCoeffs[n9] <= b) {
                    this.pbi.FragCoefEOB[n9] = this.FragCoeffs[n9];
                    if (this.EOB_Run != 0) {
                        this.FragCoeffs[n9] = 64;
                        --this.EOB_Run;
                        --this.BlocksToDecode;
                    }
                    else {
                        int n10;
                        if (n9 < this.pbi.YPlaneFragments) {
                            n10 = n7;
                        }
                        else {
                            n10 = n8;
                        }
                        this.unpackAndExpandToken(this.pbi.QFragData[n9], this.FragCoeffs, n9, n10);
                    }
                }
                ++j;
            }
            if (this.BlocksToDecode == 0) {
                break;
            }
            ++b;
        }
    }
    
    public int loadAndDecode() {
        if (this.loadFrame() == 0) {
            return -24;
        }
        if (this.pbi.DecoderErrorCode != 0) {
            return 0;
        }
        this.EOB_Run = 0;
        this.pbi.CodedBlocksThisFrame = this.pbi.CodedBlockIndex;
        this.decodeModes(this.pbi.YSBRows, this.pbi.YSBCols);
        this.decodeMVectors(this.pbi.YSBRows, this.pbi.YSBCols);
        this.decodeBlockLevelQi();
        this.unPackVideo();
        this.dctDecode.ReconRefFrames(this.pbi);
        return 0;
    }
    
    static {
        MVA = new ExtractMVectorComponentA();
        MVB = new ExtractMVectorComponentB();
        modeAlphabet = new CodingMode[][] { { CodingMode.CODE_INTER_LAST_MV, CodingMode.CODE_INTER_PRIOR_LAST, CodingMode.CODE_INTER_PLUS_MV, CodingMode.CODE_INTER_NO_MV, CodingMode.CODE_INTRA, CodingMode.CODE_USING_GOLDEN, CodingMode.CODE_GOLDEN_MV, CodingMode.CODE_INTER_FOURMV }, { CodingMode.CODE_INTER_LAST_MV, CodingMode.CODE_INTER_PRIOR_LAST, CodingMode.CODE_INTER_NO_MV, CodingMode.CODE_INTER_PLUS_MV, CodingMode.CODE_INTRA, CodingMode.CODE_USING_GOLDEN, CodingMode.CODE_GOLDEN_MV, CodingMode.CODE_INTER_FOURMV }, { CodingMode.CODE_INTER_LAST_MV, CodingMode.CODE_INTER_PLUS_MV, CodingMode.CODE_INTER_PRIOR_LAST, CodingMode.CODE_INTER_NO_MV, CodingMode.CODE_INTRA, CodingMode.CODE_USING_GOLDEN, CodingMode.CODE_GOLDEN_MV, CodingMode.CODE_INTER_FOURMV }, { CodingMode.CODE_INTER_LAST_MV, CodingMode.CODE_INTER_PLUS_MV, CodingMode.CODE_INTER_NO_MV, CodingMode.CODE_INTER_PRIOR_LAST, CodingMode.CODE_INTRA, CodingMode.CODE_USING_GOLDEN, CodingMode.CODE_GOLDEN_MV, CodingMode.CODE_INTER_FOURMV }, { CodingMode.CODE_INTER_NO_MV, CodingMode.CODE_INTER_LAST_MV, CodingMode.CODE_INTER_PRIOR_LAST, CodingMode.CODE_INTER_PLUS_MV, CodingMode.CODE_INTRA, CodingMode.CODE_USING_GOLDEN, CodingMode.CODE_GOLDEN_MV, CodingMode.CODE_INTER_FOURMV }, { CodingMode.CODE_INTER_NO_MV, CodingMode.CODE_USING_GOLDEN, CodingMode.CODE_INTER_LAST_MV, CodingMode.CODE_INTER_PRIOR_LAST, CodingMode.CODE_INTER_PLUS_MV, CodingMode.CODE_INTRA, CodingMode.CODE_GOLDEN_MV, CodingMode.CODE_INTER_FOURMV }, { CodingMode.CODE_INTER_NO_MV, CodingMode.CODE_USING_GOLDEN, CodingMode.CODE_INTER_LAST_MV, CodingMode.CODE_INTER_PRIOR_LAST, CodingMode.CODE_INTER_PLUS_MV, CodingMode.CODE_INTRA, CodingMode.CODE_GOLDEN_MV, CodingMode.CODE_INTER_FOURMV } };
    }
}
