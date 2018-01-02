// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.fluendo.utils.MemUtils;

public class DCTDecode
{
    private static final int PUR = 8;
    private static final int PU = 4;
    private static final int PUL = 2;
    private static final int PL = 1;
    private static final int[] ModeUsesMC;
    private static final short[][] pc;
    private static final int[] bc_mask;
    private static final short[] Mode2Frame;
    private short[] ReconDataBuffer;
    private int[] v;
    private int[] fn;
    private short[] Last;
    private iDCT idct;
    
    public DCTDecode() {
        this.ReconDataBuffer = new short[64];
        this.v = new int[4];
        this.fn = new int[4];
        this.Last = new short[3];
        this.idct = new iDCT();
    }
    
    private void ExpandKFBlock(final Playback pbi, final int FragmentNumber) {
        int ReconPixelsPerLine;
        short[] dequant_coeffs;
        if (FragmentNumber < pbi.YPlaneFragments) {
            ReconPixelsPerLine = pbi.YStride;
            dequant_coeffs = pbi.dequant_Y_coeffs;
        }
        else {
            ReconPixelsPerLine = pbi.UVStride;
            dequant_coeffs = pbi.dequant_UV_coeffs;
        }
        final short[] quantized_list = pbi.QFragData[FragmentNumber];
        switch (pbi.FragCoefEOB[FragmentNumber]) {
            case 0:
            case 1: {
                this.idct.IDct1(quantized_list, dequant_coeffs, this.ReconDataBuffer);
                break;
            }
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: {
                this.idct.IDct10(quantized_list, dequant_coeffs, this.ReconDataBuffer);
                break;
            }
            default: {
                this.idct.IDctSlow(quantized_list, dequant_coeffs, this.ReconDataBuffer);
                break;
            }
        }
        final int ReconPixelIndex = pbi.recon_pixel_index_table[FragmentNumber];
        Recon.ReconIntra(pbi.ThisFrameRecon, ReconPixelIndex, this.ReconDataBuffer, ReconPixelsPerLine);
    }
    
    private void ExpandBlock(final Playback pbi, final int FragmentNumber) {
        CodingMode codingMode;
        if (pbi.getFrameType() == 0) {
            codingMode = CodingMode.CODE_INTRA;
        }
        else {
            codingMode = pbi.FragCodingMethod[FragmentNumber];
        }
        int ReconPixelsPerLine;
        int MvShift;
        int MvModMask;
        short[] dequant_coeffs;
        if (FragmentNumber < pbi.YPlaneFragments) {
            ReconPixelsPerLine = pbi.YStride;
            MvShift = 1;
            MvModMask = 1;
            if (codingMode == CodingMode.CODE_INTRA) {
                dequant_coeffs = pbi.dequant_Y_coeffs;
            }
            else {
                dequant_coeffs = pbi.dequant_Inter_coeffs;
            }
        }
        else {
            ReconPixelsPerLine = pbi.UVStride;
            MvShift = 2;
            MvModMask = 3;
            if (codingMode == CodingMode.CODE_INTRA) {
                dequant_coeffs = pbi.dequant_UV_coeffs;
            }
            else {
                dequant_coeffs = pbi.dequant_Inter_coeffs;
            }
        }
        final short[] quantized_list = pbi.QFragData[FragmentNumber];
        switch (pbi.FragCoefEOB[FragmentNumber]) {
            case 0:
            case 1: {
                this.idct.IDct1(quantized_list, dequant_coeffs, this.ReconDataBuffer);
                break;
            }
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: {
                this.idct.IDct10(quantized_list, dequant_coeffs, this.ReconDataBuffer);
                break;
            }
            default: {
                this.idct.IDctSlow(quantized_list, dequant_coeffs, this.ReconDataBuffer);
                break;
            }
        }
        final int ReconPixelIndex = pbi.recon_pixel_index_table[FragmentNumber];
        if (codingMode == CodingMode.CODE_INTER_NO_MV) {
            Recon.ReconInter(pbi.ThisFrameRecon, ReconPixelIndex, pbi.LastFrameRecon, ReconPixelIndex, this.ReconDataBuffer, ReconPixelsPerLine);
        }
        else if (DCTDecode.ModeUsesMC[codingMode.getValue()] != 0) {
            int ReconPtr2Offset = 0;
            int MVOffset = 0;
            int dir = pbi.FragMVect[FragmentNumber].x;
            if (dir > 0) {
                MVOffset = dir >> MvShift;
                if ((dir & MvModMask) != 0x0) {
                    ReconPtr2Offset = 1;
                }
            }
            else if (dir < 0) {
                MVOffset = -(-dir >> MvShift);
                if ((-dir & MvModMask) != 0x0) {
                    ReconPtr2Offset = -1;
                }
            }
            dir = pbi.FragMVect[FragmentNumber].y;
            if (dir > 0) {
                MVOffset += (dir >> MvShift) * ReconPixelsPerLine;
                if ((dir & MvModMask) != 0x0) {
                    ReconPtr2Offset += ReconPixelsPerLine;
                }
            }
            else if (dir < 0) {
                MVOffset -= (-dir >> MvShift) * ReconPixelsPerLine;
                if ((-dir & MvModMask) != 0x0) {
                    ReconPtr2Offset -= ReconPixelsPerLine;
                }
            }
            final int LastFrameRecOffset = ReconPixelIndex + MVOffset;
            short[] LastFrameRecPtr;
            if (codingMode == CodingMode.CODE_GOLDEN_MV) {
                LastFrameRecPtr = pbi.GoldenFrame;
            }
            else {
                LastFrameRecPtr = pbi.LastFrameRecon;
            }
            if (ReconPtr2Offset == 0) {
                Recon.ReconInter(pbi.ThisFrameRecon, ReconPixelIndex, LastFrameRecPtr, LastFrameRecOffset, this.ReconDataBuffer, ReconPixelsPerLine);
            }
            else {
                Recon.ReconInterHalfPixel2(pbi.ThisFrameRecon, ReconPixelIndex, LastFrameRecPtr, LastFrameRecOffset, LastFrameRecPtr, LastFrameRecOffset + ReconPtr2Offset, this.ReconDataBuffer, ReconPixelsPerLine);
            }
        }
        else if (codingMode == CodingMode.CODE_USING_GOLDEN) {
            Recon.ReconInter(pbi.ThisFrameRecon, ReconPixelIndex, pbi.GoldenFrame, ReconPixelIndex, this.ReconDataBuffer, ReconPixelsPerLine);
        }
        else {
            Recon.ReconIntra(pbi.ThisFrameRecon, ReconPixelIndex, this.ReconDataBuffer, ReconPixelsPerLine);
        }
    }
    
    private void UpdateUMV_HBorders(final Playback pbi, final short[] DestReconPtr, final int PlaneFragOffset) {
        int BlockVStep;
        int PlaneStride;
        int PlaneBorderWidth;
        int PlaneFragments;
        int LineFragments;
        if (PlaneFragOffset == 0) {
            BlockVStep = pbi.YStride * 7;
            PlaneStride = pbi.YStride;
            PlaneBorderWidth = 16;
            PlaneFragments = pbi.YPlaneFragments;
            LineFragments = pbi.HFragments;
        }
        else {
            BlockVStep = pbi.UVStride * 7;
            PlaneStride = pbi.UVStride;
            PlaneBorderWidth = 8;
            PlaneFragments = pbi.UVPlaneFragments;
            LineFragments = pbi.HFragments / 2;
        }
        int PixelIndex = pbi.recon_pixel_index_table[PlaneFragOffset];
        final int SrcOff1 = PixelIndex - PlaneBorderWidth;
        int DestOff1 = SrcOff1 - PlaneBorderWidth * PlaneStride;
        PixelIndex = pbi.recon_pixel_index_table[PlaneFragOffset + PlaneFragments - LineFragments] + BlockVStep;
        final int SrcOff2 = PixelIndex - PlaneBorderWidth;
        int DestOff2 = SrcOff2 + PlaneStride;
        for (int i = 0; i < PlaneBorderWidth; ++i) {
            System.arraycopy(DestReconPtr, SrcOff1, DestReconPtr, DestOff1, PlaneStride);
            System.arraycopy(DestReconPtr, SrcOff2, DestReconPtr, DestOff2, PlaneStride);
            DestOff1 += PlaneStride;
            DestOff2 += PlaneStride;
        }
    }
    
    private void UpdateUMV_VBorders(final Playback pbi, final short[] DestReconPtr, final int PlaneFragOffset) {
        int PlaneStride;
        int PlaneBorderWidth;
        int LineFragments;
        int PlaneHeight;
        if (PlaneFragOffset == 0) {
            PlaneStride = pbi.YStride;
            PlaneBorderWidth = 16;
            LineFragments = pbi.HFragments;
            PlaneHeight = pbi.info.height;
        }
        else {
            PlaneStride = pbi.UVStride;
            PlaneBorderWidth = 8;
            LineFragments = pbi.HFragments / 2;
            PlaneHeight = pbi.info.height / 2;
        }
        int SrcOff1;
        int PixelIndex = SrcOff1 = pbi.recon_pixel_index_table[PlaneFragOffset];
        int DestOff1 = PixelIndex - PlaneBorderWidth;
        int SrcOff2;
        PixelIndex = (SrcOff2 = pbi.recon_pixel_index_table[PlaneFragOffset + LineFragments - 1] + 7);
        int DestOff2 = PixelIndex + 1;
        for (int i = 0; i < PlaneHeight; ++i) {
            MemUtils.set(DestReconPtr, DestOff1, DestReconPtr[SrcOff1], PlaneBorderWidth);
            MemUtils.set(DestReconPtr, DestOff2, DestReconPtr[SrcOff2], PlaneBorderWidth);
            DestOff1 += PlaneStride;
            DestOff2 += PlaneStride;
            SrcOff1 += PlaneStride;
            SrcOff2 += PlaneStride;
        }
    }
    
    private void UpdateUMVBorder(final Playback pbi, final short[] DestReconPtr) {
        int PlaneFragOffset = 0;
        this.UpdateUMV_VBorders(pbi, DestReconPtr, PlaneFragOffset);
        this.UpdateUMV_HBorders(pbi, DestReconPtr, PlaneFragOffset);
        PlaneFragOffset = pbi.YPlaneFragments;
        this.UpdateUMV_VBorders(pbi, DestReconPtr, PlaneFragOffset);
        this.UpdateUMV_HBorders(pbi, DestReconPtr, PlaneFragOffset);
        PlaneFragOffset = pbi.YPlaneFragments + pbi.UVPlaneFragments;
        this.UpdateUMV_VBorders(pbi, DestReconPtr, PlaneFragOffset);
        this.UpdateUMV_HBorders(pbi, DestReconPtr, PlaneFragOffset);
    }
    
    private void CopyRecon(final Playback pbi, final short[] DestReconPtr, final short[] SrcReconPtr) {
        int PlaneLineStep = pbi.YStride;
        for (int i = 0; i < pbi.YPlaneFragments; ++i) {
            if (pbi.display_fragments[i] != 0) {
                final int PixelIndex = pbi.recon_pixel_index_table[i];
                Recon.CopyBlock(SrcReconPtr, DestReconPtr, PixelIndex, PlaneLineStep);
            }
        }
        PlaneLineStep = pbi.UVStride;
        for (int i = pbi.YPlaneFragments; i < pbi.UnitFragments; ++i) {
            if (pbi.display_fragments[i] != 0) {
                final int PixelIndex = pbi.recon_pixel_index_table[i];
                Recon.CopyBlock(SrcReconPtr, DestReconPtr, PixelIndex, PlaneLineStep);
            }
        }
    }
    
    private void CopyNotRecon(final Playback pbi, final short[] DestReconPtr, final short[] SrcReconPtr) {
        int PlaneLineStep = pbi.YStride;
        for (int i = 0; i < pbi.YPlaneFragments; ++i) {
            if (pbi.display_fragments[i] == 0) {
                final int PixelIndex = pbi.recon_pixel_index_table[i];
                Recon.CopyBlock(SrcReconPtr, DestReconPtr, PixelIndex, PlaneLineStep);
            }
        }
        PlaneLineStep = pbi.UVStride;
        for (int i = pbi.YPlaneFragments; i < pbi.UnitFragments; ++i) {
            if (pbi.display_fragments[i] == 0) {
                final int PixelIndex = pbi.recon_pixel_index_table[i];
                Recon.CopyBlock(SrcReconPtr, DestReconPtr, PixelIndex, PlaneLineStep);
            }
        }
    }
    
    public void ExpandToken(final short[] ExpandedBlock, final byte[] CoeffIndex, final int FragIndex, int Token, final int ExtraBits) {
        if (Token >= 23) {
            if (Token < 30) {
                if (Token < 28) {
                    ExpandedBlock[CoeffIndex[FragIndex] += (byte)(Token - 23 + 1)] = (short)(-(((ExtraBits & 0x1) << 1) - 1));
                }
                else if (Token == 28) {
                    ExpandedBlock[CoeffIndex[FragIndex] += (byte)(6 + (ExtraBits & 0x3))] = (short)(-(((ExtraBits & 0x4) >> 1) - 1));
                }
                else {
                    ExpandedBlock[CoeffIndex[FragIndex] += (byte)(10 + (ExtraBits & 0x7))] = (short)(-(((ExtraBits & 0x8) >> 2) - 1));
                }
            }
            else if (Token == 30) {
                ExpandedBlock[++CoeffIndex[FragIndex]] = (short)((2 + (ExtraBits & 0x1)) * -((ExtraBits & 0x2) - 1));
            }
            else {
                ExpandedBlock[CoeffIndex[FragIndex] += (byte)(2 + (ExtraBits & 0x1))] = (short)((2 + ((ExtraBits & 0x2) >> 1)) * -(((ExtraBits & 0x4) >> 1) - 1));
            }
            ++CoeffIndex[FragIndex];
        }
        else if (Token == 7) {
            CoeffIndex[FragIndex] += (byte)(ExtraBits + 1);
        }
        else if (Token == 8) {
            CoeffIndex[FragIndex] += (byte)(ExtraBits + 1);
        }
        else if (Token < 13) {
            switch (Token) {
                case 9: {
                    ExpandedBlock[CoeffIndex[FragIndex]] = 1;
                    break;
                }
                case 10: {
                    ExpandedBlock[CoeffIndex[FragIndex]] = -1;
                    break;
                }
                case 11: {
                    ExpandedBlock[CoeffIndex[FragIndex]] = 2;
                    break;
                }
                case 12: {
                    ExpandedBlock[CoeffIndex[FragIndex]] = -2;
                    break;
                }
            }
            ++CoeffIndex[FragIndex];
        }
        else {
            if (Token < 17) {
                Token -= 13;
                ExpandedBlock[CoeffIndex[FragIndex]] = (short)((Token + 3) * -((ExtraBits << 1) - 1));
            }
            else if (Token == 17) {
                ExpandedBlock[CoeffIndex[FragIndex]] = (short)((7 + (ExtraBits & 0x1)) * -((ExtraBits & 0x2) - 1));
            }
            else if (Token == 18) {
                ExpandedBlock[CoeffIndex[FragIndex]] = (short)((9 + (ExtraBits & 0x3)) * -(((ExtraBits & 0x4) >> 1) - 1));
            }
            else if (Token == 19) {
                ExpandedBlock[CoeffIndex[FragIndex]] = (short)((13 + (ExtraBits & 0x7)) * -(((ExtraBits & 0x8) >> 2) - 1));
            }
            else if (Token == 20) {
                ExpandedBlock[CoeffIndex[FragIndex]] = (short)((21 + (ExtraBits & 0xF)) * -(((ExtraBits & 0x10) >> 3) - 1));
            }
            else if (Token == 21) {
                ExpandedBlock[CoeffIndex[FragIndex]] = (short)((37 + (ExtraBits & 0x1F)) * -(((ExtraBits & 0x20) >> 4) - 1));
            }
            else if (Token == 22) {
                ExpandedBlock[CoeffIndex[FragIndex]] = (short)((69 + (ExtraBits & 0x1FF)) * -(((ExtraBits & 0x200) >> 8) - 1));
            }
            ++CoeffIndex[FragIndex];
        }
    }
    
    public void ClearDownQFragData(final Playback pbi) {
        for (int i = 0; i < pbi.CodedBlockIndex; ++i) {
            final short[] QFragPtr = pbi.QFragData[pbi.CodedBlockList[i]];
            for (int j = 0; j < 64; ++j) {
                QFragPtr[j] = 0;
            }
        }
    }
    
    public void ReconRefFrames(final Playback pbi) {
        int FragsAcross = pbi.HFragments;
        int FragsDown = pbi.VFragments;
        final boolean isBaseFrame = pbi.getFrameType() == 0;
        pbi.filter.SetupLoopFilter(pbi.FrameQIndex);
        for (int j = 0; j < 3; ++j) {
            int FromFragment = 0;
            switch (j) {
                case 0: {
                    FromFragment = 0;
                    FragsAcross = pbi.HFragments;
                    FragsDown = pbi.VFragments;
                    break;
                }
                case 1: {
                    FromFragment = pbi.YPlaneFragments;
                    FragsAcross = pbi.HFragments >> 1;
                    FragsDown = pbi.VFragments >> 1;
                    break;
                }
                default: {
                    FromFragment = pbi.YPlaneFragments + pbi.UVPlaneFragments;
                    FragsAcross = pbi.HFragments >> 1;
                    FragsDown = pbi.VFragments >> 1;
                    break;
                }
            }
            for (int k = 0; k < 3; ++k) {
                this.Last[k] = 0;
            }
            int i = FromFragment;
            for (int m = 0; m < FragsDown; ++m) {
                for (int n = 0; n < FragsAcross; ++n, ++i) {
                    if (pbi.display_fragments[i] != 0 || pbi.getFrameType() == 0) {
                        final int WhichFrame = DCTDecode.Mode2Frame[pbi.FragCodingMethod[i].getValue()];
                        final int WhichCase = ((n == 0) ? 1 : 0) + (((m == 0) ? 1 : 0) << 1) + (((n + 1 == FragsAcross) ? 1 : 0) << 2);
                        this.fn[0] = i - 1;
                        this.fn[1] = i - FragsAcross - 1;
                        this.fn[2] = i - FragsAcross;
                        this.fn[3] = i - FragsAcross + 1;
                        int k;
                        short wpc;
                        int pcount;
                        for (pcount = (k = (wpc = 0)); k < 4; ++k) {
                            final int pflag = 1 << k;
                            if ((DCTDecode.bc_mask[WhichCase] & pflag) != 0x0 && pbi.display_fragments[this.fn[k]] != 0 && DCTDecode.Mode2Frame[pbi.FragCodingMethod[this.fn[k]].getValue()] == WhichFrame) {
                                this.v[pcount] = pbi.QFragData[this.fn[k]][0];
                                wpc |= (short)pflag;
                                ++pcount;
                            }
                        }
                        if (wpc == 0) {
                            final short[] array = pbi.QFragData[i];
                            final int n2 = 0;
                            array[n2] += this.Last[WhichFrame];
                        }
                        else {
                            short PredictedDC = (short)(DCTDecode.pc[wpc][0] * this.v[0]);
                            for (k = 1; k < pcount; ++k) {
                                PredictedDC += (short)(DCTDecode.pc[wpc][k] * this.v[k]);
                            }
                            if (DCTDecode.pc[wpc][4] != 0) {
                                if (PredictedDC < 0) {
                                    PredictedDC += DCTDecode.pc[wpc][5];
                                }
                                PredictedDC >>= DCTDecode.pc[wpc][4];
                            }
                            if ((wpc & 0x7) == 0x7) {
                                if (Math.abs(PredictedDC - this.v[2]) > 128) {
                                    PredictedDC = (short)this.v[2];
                                }
                                else if (Math.abs(PredictedDC - this.v[0]) > 128) {
                                    PredictedDC = (short)this.v[0];
                                }
                                else if (Math.abs(PredictedDC - this.v[1]) > 128) {
                                    PredictedDC = (short)this.v[1];
                                }
                            }
                            final short[] array2 = pbi.QFragData[i];
                            final int n3 = 0;
                            array2[n3] += PredictedDC;
                        }
                        this.Last[WhichFrame] = pbi.QFragData[i][0];
                        if (isBaseFrame) {
                            this.ExpandKFBlock(pbi, i);
                        }
                        else {
                            this.ExpandBlock(pbi, i);
                        }
                    }
                }
            }
        }
        if (pbi.CodedBlockIndex > pbi.UnitFragments >> 1) {
            final short[] SwapReconBuffersTemp = pbi.ThisFrameRecon;
            pbi.ThisFrameRecon = pbi.LastFrameRecon;
            this.CopyNotRecon(pbi, pbi.LastFrameRecon = SwapReconBuffersTemp, pbi.ThisFrameRecon);
        }
        else {
            this.CopyRecon(pbi, pbi.LastFrameRecon, pbi.ThisFrameRecon);
        }
        pbi.filter.LoopFilter(pbi);
        this.UpdateUMVBorder(pbi, pbi.LastFrameRecon);
        if (isBaseFrame) {
            this.CopyRecon(pbi, pbi.GoldenFrame, pbi.LastFrameRecon);
            this.UpdateUMVBorder(pbi, pbi.GoldenFrame);
        }
    }
    
    static {
        ModeUsesMC = new int[] { 0, 0, 1, 1, 1, 0, 1, 1 };
        pc = new short[][] { { 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1, 1 }, { 0, 1, 0, 0, 0, 0 }, { 29, -26, 29, 0, 5, 31 }, { 1, 0, 0, 0, 0, 0 }, { 75, 53, 0, 0, 7, 127 }, { 1, 1, 0, 0, 1, 1 }, { 75, 0, 53, 0, 7, 127 }, { 1, 0, 0, 0, 0, 0 }, { 75, 0, 53, 0, 7, 127 }, { 3, 10, 3, 0, 4, 15 }, { 29, -26, 29, 0, 5, 31 } };
        bc_mask = new int[] { 15, 12, 1, 0, 7, 4, 1, 0 };
        Mode2Frame = new short[] { 1, 0, 1, 1, 1, 2, 2, 1 };
    }
}
