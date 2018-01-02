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
        final int DctQMask = opb.readB(6);
        opb.readB(1);
        if (this.pbi.FrameType == 0) {
            this.pbi.KeyFrameType = (byte)opb.readB(1);
            opb.readB(2);
        }
        this.pbi.ThisFrameQualityValue = this.pbi.QThreshTable[DctQMask];
        this.pbi.frArray.quadDecodeDisplayFragments(this.pbi);
        return 1;
    }
    
    private void decodeModes(final int SBRows, final int SBCols) {
        int SB = 0;
        int MBListIndex = 0;
        final CodingMode[] FragCodingMethod = this.pbi.FragCodingMethod;
        if (this.pbi.getFrameType() == 0) {
            MemUtils.set(FragCodingMethod, 0, CodingMode.CODE_INTRA, this.pbi.UnitFragments);
        }
        else {
            MemUtils.set(FragCodingMethod, 0, CodingMode.CODE_INTER_NO_MV, this.pbi.UnitFragments);
            long ret = this.pbi.opb.readB(3);
            final int CodingScheme = (int)ret;
            CodingMode[] ModeList;
            if (CodingScheme == 0) {
                final CodingMode[] CustomModeAlphabet = new CodingMode[8];
                for (int i = 0; i < 8; ++i) {
                    ret = this.pbi.opb.readB(3);
                    CustomModeAlphabet[(int)ret] = CodingMode.MODES[i];
                }
                ModeList = CustomModeAlphabet;
            }
            else {
                ModeList = Decode.modeAlphabet[CodingScheme - 1];
            }
            for (int SBrow = 0; SBrow < SBRows; ++SBrow) {
                for (int SBcol = 0; SBcol < SBCols; ++SBcol) {
                    for (int MB = 0; MB < 4; ++MB) {
                        final int FragIndex = this.pbi.BlockMap.quadMapToMBTopLeft(SB, MB);
                        if (FragIndex >= 0 && this.pbi.MBCodedFlags[MBListIndex++] != 0) {
                            CodingMode CodingMethod;
                            if (CodingScheme == 7) {
                                ret = this.pbi.opb.readB(3);
                                CodingMethod = CodingMode.MODES[(int)ret];
                            }
                            else {
                                final CodingMode ModeEntry = this.pbi.frArray.unpackMode(this.pbi.opb);
                                CodingMethod = ModeList[ModeEntry.getValue()];
                            }
                            FragCodingMethod[FragIndex + 1] = (FragCodingMethod[FragIndex] = CodingMethod);
                            FragCodingMethod[FragIndex + this.pbi.HFragments] = CodingMethod;
                            FragCodingMethod[FragIndex + this.pbi.HFragments + 1] = CodingMethod;
                            final int UVRow = FragIndex / (this.pbi.HFragments * 2);
                            final int UVColumn = FragIndex % this.pbi.HFragments / 2;
                            final int UVFragOffset = UVRow * (this.pbi.HFragments / 2) + UVColumn;
                            FragCodingMethod[this.pbi.YPlaneFragments + UVFragOffset] = CodingMethod;
                            FragCodingMethod[this.pbi.YPlaneFragments + this.pbi.UVPlaneFragments + UVFragOffset] = CodingMethod;
                        }
                    }
                    ++SB;
                }
            }
        }
    }
    
    private void decodeMVectors(final int SBRows, final int SBCols) {
        int SB = 0;
        int MBListIndex = 0;
        final Buffer opb = this.pbi.opb;
        if (this.pbi.getFrameType() == 0) {
            return;
        }
        this.LastInterMV.x = 0;
        this.LastInterMV.y = 0;
        this.PriorLastInterMV.x = 0;
        this.PriorLastInterMV.y = 0;
        ExtractMVectorComponent MVC;
        if (opb.readB(1) == 0) {
            MVC = Decode.MVA;
        }
        else {
            MVC = Decode.MVB;
        }
        for (int SBrow = 0; SBrow < SBRows; ++SBrow) {
            for (int SBcol = 0; SBcol < SBCols; ++SBcol) {
                for (int MB = 0; MB < 4; ++MB) {
                    final int FragIndex = this.pbi.BlockMap.quadMapToMBTopLeft(SB, MB);
                    if (FragIndex >= 0 && this.pbi.MBCodedFlags[MBListIndex++] != 0) {
                        final CodingMode CodingMethod = this.pbi.FragCodingMethod[FragIndex];
                        final MotionVector MVect0 = this.pbi.FragMVect[FragIndex];
                        final MotionVector MVect2 = this.pbi.FragMVect[FragIndex + 1];
                        final MotionVector MVect3 = this.pbi.FragMVect[FragIndex + this.pbi.HFragments];
                        final MotionVector MVect4 = this.pbi.FragMVect[FragIndex + this.pbi.HFragments + 1];
                        final int UVRow = FragIndex / (this.pbi.HFragments * 2);
                        final int UVColumn = FragIndex % this.pbi.HFragments / 2;
                        final int UVFragOffset = UVRow * (this.pbi.HFragments / 2) + UVColumn;
                        final MotionVector MVect5 = this.pbi.FragMVect[this.pbi.YPlaneFragments + UVFragOffset];
                        final MotionVector MVect6 = this.pbi.FragMVect[this.pbi.YPlaneFragments + this.pbi.UVPlaneFragments + UVFragOffset];
                        if (CodingMethod == CodingMode.CODE_INTER_PLUS_MV) {
                            this.PriorLastInterMV.x = this.LastInterMV.x;
                            this.PriorLastInterMV.y = this.LastInterMV.y;
                            final MotionVector lastInterMV = this.LastInterMV;
                            final MotionVector motionVector = MVect0;
                            final MotionVector motionVector2 = MVect2;
                            final MotionVector motionVector3 = MVect3;
                            final MotionVector motionVector4 = MVect4;
                            final MotionVector motionVector5 = MVect5;
                            final MotionVector motionVector6 = MVect6;
                            final int extract = MVC.extract(opb);
                            motionVector6.x = extract;
                            motionVector5.x = extract;
                            motionVector4.x = extract;
                            motionVector3.x = extract;
                            motionVector2.x = extract;
                            motionVector.x = extract;
                            lastInterMV.x = extract;
                            final MotionVector lastInterMV2 = this.LastInterMV;
                            final MotionVector motionVector7 = MVect0;
                            final MotionVector motionVector8 = MVect2;
                            final MotionVector motionVector9 = MVect3;
                            final MotionVector motionVector10 = MVect4;
                            final MotionVector motionVector11 = MVect5;
                            final MotionVector motionVector12 = MVect6;
                            final int extract2 = MVC.extract(opb);
                            motionVector12.y = extract2;
                            motionVector11.y = extract2;
                            motionVector10.y = extract2;
                            motionVector9.y = extract2;
                            motionVector8.y = extract2;
                            motionVector7.y = extract2;
                            lastInterMV2.y = extract2;
                        }
                        else if (CodingMethod == CodingMode.CODE_GOLDEN_MV) {
                            final MotionVector motionVector13 = MVect0;
                            final MotionVector motionVector14 = MVect2;
                            final MotionVector motionVector15 = MVect3;
                            final MotionVector motionVector16 = MVect4;
                            final MotionVector motionVector17 = MVect5;
                            final MotionVector motionVector18 = MVect6;
                            final int extract3 = MVC.extract(opb);
                            motionVector18.x = extract3;
                            motionVector17.x = extract3;
                            motionVector16.x = extract3;
                            motionVector15.x = extract3;
                            motionVector14.x = extract3;
                            motionVector13.x = extract3;
                            final MotionVector motionVector19 = MVect0;
                            final MotionVector motionVector20 = MVect2;
                            final MotionVector motionVector21 = MVect3;
                            final MotionVector motionVector22 = MVect4;
                            final MotionVector motionVector23 = MVect5;
                            final MotionVector motionVector24 = MVect6;
                            final int extract4 = MVC.extract(opb);
                            motionVector24.y = extract4;
                            motionVector23.y = extract4;
                            motionVector22.y = extract4;
                            motionVector21.y = extract4;
                            motionVector20.y = extract4;
                            motionVector19.y = extract4;
                        }
                        else if (CodingMethod == CodingMode.CODE_INTER_FOURMV) {
                            final MotionVector motionVector25 = MVect0;
                            final int extract5 = MVC.extract(opb);
                            motionVector25.x = extract5;
                            int x = extract5;
                            final MotionVector motionVector26 = MVect0;
                            final int extract6 = MVC.extract(opb);
                            motionVector26.y = extract6;
                            int y = extract6;
                            final int n = x;
                            final MotionVector motionVector27 = MVect2;
                            final int extract7 = MVC.extract(opb);
                            motionVector27.x = extract7;
                            x = n + extract7;
                            final int n2 = y;
                            final MotionVector motionVector28 = MVect2;
                            final int extract8 = MVC.extract(opb);
                            motionVector28.y = extract8;
                            y = n2 + extract8;
                            final int n3 = x;
                            final MotionVector motionVector29 = MVect3;
                            final int extract9 = MVC.extract(opb);
                            motionVector29.x = extract9;
                            x = n3 + extract9;
                            final int n4 = y;
                            final MotionVector motionVector30 = MVect3;
                            final int extract10 = MVC.extract(opb);
                            motionVector30.y = extract10;
                            y = n4 + extract10;
                            final int n5 = x;
                            final MotionVector motionVector31 = MVect4;
                            final int extract11 = MVC.extract(opb);
                            motionVector31.x = extract11;
                            x = n5 + extract11;
                            final int n6 = y;
                            final MotionVector motionVector32 = MVect4;
                            final int extract12 = MVC.extract(opb);
                            motionVector32.y = extract12;
                            y = n6 + extract12;
                            if (x >= 0) {
                                x = (x + 2) / 4;
                            }
                            else {
                                x = (x - 2) / 4;
                            }
                            MVect5.x = x;
                            MVect6.x = x;
                            if (y >= 0) {
                                y = (y + 2) / 4;
                            }
                            else {
                                y = (y - 2) / 4;
                            }
                            MVect5.y = y;
                            MVect6.y = y;
                            this.PriorLastInterMV.x = this.LastInterMV.x;
                            this.PriorLastInterMV.y = this.LastInterMV.y;
                            this.LastInterMV.x = MVect4.x;
                            this.LastInterMV.y = MVect4.y;
                        }
                        else if (CodingMethod == CodingMode.CODE_INTER_LAST_MV) {
                            final MotionVector motionVector33 = MVect0;
                            final MotionVector motionVector34 = MVect2;
                            final MotionVector motionVector35 = MVect3;
                            final MotionVector motionVector36 = MVect4;
                            final MotionVector motionVector37 = MVect5;
                            final MotionVector motionVector38 = MVect6;
                            final int x2 = this.LastInterMV.x;
                            motionVector38.x = x2;
                            motionVector37.x = x2;
                            motionVector36.x = x2;
                            motionVector35.x = x2;
                            motionVector34.x = x2;
                            motionVector33.x = x2;
                            final MotionVector motionVector39 = MVect0;
                            final MotionVector motionVector40 = MVect2;
                            final MotionVector motionVector41 = MVect3;
                            final MotionVector motionVector42 = MVect4;
                            final MotionVector motionVector43 = MVect5;
                            final MotionVector motionVector44 = MVect6;
                            final int y2 = this.LastInterMV.y;
                            motionVector44.y = y2;
                            motionVector43.y = y2;
                            motionVector42.y = y2;
                            motionVector41.y = y2;
                            motionVector40.y = y2;
                            motionVector39.y = y2;
                        }
                        else if (CodingMethod == CodingMode.CODE_INTER_PRIOR_LAST) {
                            final MotionVector motionVector45 = MVect0;
                            final MotionVector motionVector46 = MVect2;
                            final MotionVector motionVector47 = MVect3;
                            final MotionVector motionVector48 = MVect4;
                            final MotionVector motionVector49 = MVect5;
                            final MotionVector motionVector50 = MVect6;
                            final int x3 = this.PriorLastInterMV.x;
                            motionVector50.x = x3;
                            motionVector49.x = x3;
                            motionVector48.x = x3;
                            motionVector47.x = x3;
                            motionVector46.x = x3;
                            motionVector45.x = x3;
                            final MotionVector motionVector51 = MVect0;
                            final MotionVector motionVector52 = MVect2;
                            final MotionVector motionVector53 = MVect3;
                            final MotionVector motionVector54 = MVect4;
                            final MotionVector motionVector55 = MVect5;
                            final MotionVector motionVector56 = MVect6;
                            final int y3 = this.PriorLastInterMV.y;
                            motionVector56.y = y3;
                            motionVector55.y = y3;
                            motionVector54.y = y3;
                            motionVector53.y = y3;
                            motionVector52.y = y3;
                            motionVector51.y = y3;
                            final MotionVector TmpMVect = this.PriorLastInterMV;
                            this.PriorLastInterMV = this.LastInterMV;
                            this.LastInterMV = TmpMVect;
                        }
                        else {
                            MVect0.x = 0;
                            MVect0.y = 0;
                        }
                    }
                }
                ++SB;
            }
        }
    }
    
    private final int ExtractToken(final Buffer opb, HuffEntry CurrentRoot) {
        while (CurrentRoot.value < 0) {
            CurrentRoot = CurrentRoot.Child[opb.readB(1)];
        }
        return CurrentRoot.value;
    }
    
    private void unpackAndExpandToken(final short[] ExpandedBlock, final byte[] CoeffIndex, final int FragIndex, final int HuffChoice) {
        int ExtraBits = 0;
        final int Token = this.ExtractToken(this.pbi.opb, this.pbi.HuffRoot_VP3x[HuffChoice]);
        if (this.pbi.ExtraBitLengths_VP3x[Token] > 0) {
            ExtraBits = this.pbi.opb.readB(this.pbi.ExtraBitLengths_VP3x[Token]);
        }
        if (Token >= 7) {
            this.dctDecode.ExpandToken(ExpandedBlock, CoeffIndex, FragIndex, Token, ExtraBits);
            if (CoeffIndex[FragIndex] >= 64) {
                --this.BlocksToDecode;
            }
        }
        else {
            switch (Token) {
                case 1: {
                    this.EOB_Run = 1;
                    break;
                }
                case 2: {
                    this.EOB_Run = 2;
                    break;
                }
                case 3: {
                    this.EOB_Run = ExtraBits + 3;
                    break;
                }
                case 4: {
                    this.EOB_Run = ExtraBits + 7;
                    break;
                }
                case 5: {
                    this.EOB_Run = ExtraBits + 15;
                    break;
                }
                case 6: {
                    this.EOB_Run = ExtraBits - 1;
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    return;
                }
            }
            CoeffIndex[FragIndex] = 64;
            --this.BlocksToDecode;
        }
    }
    
    private void unPackVideo() {
        int EncodedCoeffs = 1;
        if (this.pbi.DecoderErrorCode != 0) {
            return;
        }
        MemUtils.set(this.FragCoeffs, 0, 0, this.pbi.UnitFragments);
        MemUtils.set(this.pbi.FragCoefEOB, 0, 0, this.pbi.UnitFragments);
        this.dctDecode.ClearDownQFragData(this.pbi);
        this.BlocksToDecode = this.pbi.CodedBlockIndex;
        final int DcHuffChoice1 = this.pbi.opb.readB(4) + 0;
        final int DcHuffChoice2 = this.pbi.opb.readB(4) + 0;
        for (int cbl = 0, cble = this.pbi.CodedBlockIndex; cbl < cble; ++cbl) {
            final int FragIndex = this.pbi.CodedBlockList[cbl];
            this.pbi.FragCoefEOB[FragIndex] = this.FragCoeffs[FragIndex];
            int DcHuffChoice3;
            if (FragIndex < this.pbi.YPlaneFragments) {
                DcHuffChoice3 = DcHuffChoice1;
            }
            else {
                DcHuffChoice3 = DcHuffChoice2;
            }
            if (this.EOB_Run != 0) {
                this.FragCoeffs[FragIndex] = 64;
                --this.EOB_Run;
                --this.BlocksToDecode;
            }
            else {
                this.unpackAndExpandToken(this.pbi.QFragData[FragIndex], this.FragCoeffs, FragIndex, DcHuffChoice3);
            }
        }
        final int AcHuffIndex1 = this.pbi.opb.readB(4) + 16;
        final int AcHuffIndex2 = this.pbi.opb.readB(4) + 16;
        while (EncodedCoeffs < 64) {
            int cbl = 0;
            final int cble = this.pbi.CodedBlockIndex;
            int AcHuffChoice1;
            int AcHuffChoice2;
            if (EncodedCoeffs <= 5) {
                AcHuffChoice1 = AcHuffIndex1;
                AcHuffChoice2 = AcHuffIndex2;
            }
            else if (EncodedCoeffs <= 14) {
                AcHuffChoice1 = AcHuffIndex1 + 16;
                AcHuffChoice2 = AcHuffIndex2 + 16;
            }
            else if (EncodedCoeffs <= 27) {
                AcHuffChoice1 = AcHuffIndex1 + 32;
                AcHuffChoice2 = AcHuffIndex2 + 32;
            }
            else {
                AcHuffChoice1 = AcHuffIndex1 + 48;
                AcHuffChoice2 = AcHuffIndex2 + 48;
            }
            while (cbl < cble) {
                final int FragIndex = this.pbi.CodedBlockList[cbl];
                if (this.FragCoeffs[FragIndex] <= EncodedCoeffs) {
                    this.pbi.FragCoefEOB[FragIndex] = this.FragCoeffs[FragIndex];
                    if (this.EOB_Run != 0) {
                        this.FragCoeffs[FragIndex] = 64;
                        --this.EOB_Run;
                        --this.BlocksToDecode;
                    }
                    else {
                        int AcHuffChoice3;
                        if (FragIndex < this.pbi.YPlaneFragments) {
                            AcHuffChoice3 = AcHuffChoice1;
                        }
                        else {
                            AcHuffChoice3 = AcHuffChoice2;
                        }
                        this.unpackAndExpandToken(this.pbi.QFragData[FragIndex], this.FragCoeffs, FragIndex, AcHuffChoice3);
                    }
                }
                ++cbl;
            }
            if (this.BlocksToDecode == 0) {
                break;
            }
            ++EncodedCoeffs;
        }
    }
    
    public int loadAndDecode() {
        final int loadFrameOK = this.loadFrame();
        if (loadFrameOK == 0) {
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
