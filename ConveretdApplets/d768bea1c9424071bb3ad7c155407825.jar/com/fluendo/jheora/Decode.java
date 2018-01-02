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
    
    private int loadFrame() {
        final Buffer opb = this.pbi.opb;
        this.pbi.FrameType = (byte)opb.readB(1);
        final int b = opb.readB(6);
        final byte b2 = (byte)opb.readB(1);
        if (this.pbi.FrameType == 0) {
            this.pbi.KeyFrameType = (byte)opb.readB(1);
            final byte b3 = (byte)opb.readB(2);
        }
        this.pbi.ThisFrameQualityValue = this.pbi.QThreshTable[b];
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
                            final MotionVector motionVector31 = motionVector;
                            final int extract5 = extractMVectorComponent.extract(opb);
                            motionVector31.x = extract5;
                            final int n6 = extract5;
                            final MotionVector motionVector32 = motionVector;
                            final int extract6 = extractMVectorComponent.extract(opb);
                            motionVector32.y = extract6;
                            final int n7 = extract6;
                            final int n8 = n6;
                            final MotionVector motionVector33 = motionVector2;
                            final int extract7 = extractMVectorComponent.extract(opb);
                            motionVector33.x = extract7;
                            final int n9 = n8 + extract7;
                            final int n10 = n7;
                            final MotionVector motionVector34 = motionVector2;
                            final int extract8 = extractMVectorComponent.extract(opb);
                            motionVector34.y = extract8;
                            final int n11 = n10 + extract8;
                            final int n12 = n9;
                            final MotionVector motionVector35 = motionVector3;
                            final int extract9 = extractMVectorComponent.extract(opb);
                            motionVector35.x = extract9;
                            final int n13 = n12 + extract9;
                            final int n14 = n11;
                            final MotionVector motionVector36 = motionVector3;
                            final int extract10 = extractMVectorComponent.extract(opb);
                            motionVector36.y = extract10;
                            final int n15 = n14 + extract10;
                            final int n16 = n13;
                            final MotionVector motionVector37 = motionVector4;
                            final int extract11 = extractMVectorComponent.extract(opb);
                            motionVector37.x = extract11;
                            final int n17 = n16 + extract11;
                            final int n18 = n15;
                            final MotionVector motionVector38 = motionVector4;
                            final int extract12 = extractMVectorComponent.extract(opb);
                            motionVector38.y = extract12;
                            final int n19 = n18 + extract12;
                            int n20;
                            if (n17 >= 0) {
                                n20 = (n17 + 2) / 4;
                            }
                            else {
                                n20 = (n17 - 2) / 4;
                            }
                            motionVector5.x = n20;
                            motionVector6.x = n20;
                            int n21;
                            if (n19 >= 0) {
                                n21 = (n19 + 2) / 4;
                            }
                            else {
                                n21 = (n19 - 2) / 4;
                            }
                            motionVector5.y = n21;
                            motionVector6.y = n21;
                            this.PriorLastInterMV.x = this.LastInterMV.x;
                            this.PriorLastInterMV.y = this.LastInterMV.y;
                            this.LastInterMV.x = motionVector4.x;
                            this.LastInterMV.y = motionVector4.y;
                        }
                        else if (codingMode == CodingMode.CODE_INTER_LAST_MV) {
                            final MotionVector motionVector39 = motionVector;
                            final MotionVector motionVector40 = motionVector2;
                            final MotionVector motionVector41 = motionVector3;
                            final MotionVector motionVector42 = motionVector4;
                            final MotionVector motionVector43 = motionVector5;
                            final MotionVector motionVector44 = motionVector6;
                            final int x = this.LastInterMV.x;
                            motionVector44.x = x;
                            motionVector43.x = x;
                            motionVector42.x = x;
                            motionVector41.x = x;
                            motionVector40.x = x;
                            motionVector39.x = x;
                            final MotionVector motionVector45 = motionVector;
                            final MotionVector motionVector46 = motionVector2;
                            final MotionVector motionVector47 = motionVector3;
                            final MotionVector motionVector48 = motionVector4;
                            final MotionVector motionVector49 = motionVector5;
                            final MotionVector motionVector50 = motionVector6;
                            final int y = this.LastInterMV.y;
                            motionVector50.y = y;
                            motionVector49.y = y;
                            motionVector48.y = y;
                            motionVector47.y = y;
                            motionVector46.y = y;
                            motionVector45.y = y;
                        }
                        else if (codingMode == CodingMode.CODE_INTER_PRIOR_LAST) {
                            final MotionVector motionVector51 = motionVector;
                            final MotionVector motionVector52 = motionVector2;
                            final MotionVector motionVector53 = motionVector3;
                            final MotionVector motionVector54 = motionVector4;
                            final MotionVector motionVector55 = motionVector5;
                            final MotionVector motionVector56 = motionVector6;
                            final int x2 = this.PriorLastInterMV.x;
                            motionVector56.x = x2;
                            motionVector55.x = x2;
                            motionVector54.x = x2;
                            motionVector53.x = x2;
                            motionVector52.x = x2;
                            motionVector51.x = x2;
                            final MotionVector motionVector57 = motionVector;
                            final MotionVector motionVector58 = motionVector2;
                            final MotionVector motionVector59 = motionVector3;
                            final MotionVector motionVector60 = motionVector4;
                            final MotionVector motionVector61 = motionVector5;
                            final MotionVector motionVector62 = motionVector6;
                            final int y2 = this.PriorLastInterMV.y;
                            motionVector62.y = y2;
                            motionVector61.y = y2;
                            motionVector60.y = y2;
                            motionVector59.y = y2;
                            motionVector58.y = y2;
                            motionVector57.y = y2;
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
        this.dctDecode.ClearDownQFragData(this.pbi);
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
        if (this.pbi.ThisFrameQualityValue != this.pbi.LastFrameQualityValue) {
            Quant.UpdateQ(this.pbi, this.pbi.ThisFrameQualityValue);
            this.pbi.LastFrameQualityValue = this.pbi.ThisFrameQualityValue;
        }
        if (this.pbi.DecoderErrorCode != 0) {
            return 0;
        }
        this.EOB_Run = 0;
        this.pbi.CodedBlocksThisFrame = this.pbi.CodedBlockIndex;
        this.decodeModes(this.pbi.YSBRows, this.pbi.YSBCols);
        this.decodeMVectors(this.pbi.YSBRows, this.pbi.YSBCols);
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
