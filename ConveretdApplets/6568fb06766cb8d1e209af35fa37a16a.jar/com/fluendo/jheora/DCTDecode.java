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
    private short[] dequant_matrix;
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
        this.dequant_matrix = new short[64];
        this.ReconDataBuffer = new short[64];
        this.v = new int[4];
        this.fn = new int[4];
        this.Last = new short[3];
        this.idct = new iDCT();
    }
    
    private void ExpandKFBlock(final Playback playback, final int n) {
        final byte b = playback.FragQs[n];
        int n2;
        short[] array;
        if (n < playback.YPlaneFragments) {
            n2 = playback.YStride;
            array = playback.info.dequant_tables[0][0][playback.frameQIS[b]];
            this.dequant_matrix[0] = playback.info.dequant_tables[0][0][playback.frameQIS[0]][0];
        }
        else if (n < playback.YPlaneFragments + playback.UVPlaneFragments) {
            n2 = playback.UVStride;
            array = playback.info.dequant_tables[0][1][playback.frameQIS[b]];
            this.dequant_matrix[0] = playback.info.dequant_tables[0][1][playback.frameQIS[0]][0];
        }
        else {
            n2 = playback.UVStride;
            array = playback.info.dequant_tables[0][2][playback.frameQIS[b]];
            this.dequant_matrix[0] = playback.info.dequant_tables[0][2][playback.frameQIS[0]][0];
        }
        for (int i = 1; i < 64; ++i) {
            this.dequant_matrix[i] = array[i];
        }
        final short[] array2 = playback.QFragData[n];
        switch (playback.FragCoefEOB[n]) {
            case 0:
            case 1: {
                this.idct.IDct1(array2, this.dequant_matrix, this.ReconDataBuffer);
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
                this.idct.IDct10(array2, this.dequant_matrix, this.ReconDataBuffer);
                break;
            }
            default: {
                this.idct.IDctSlow(array2, this.dequant_matrix, this.ReconDataBuffer);
                break;
            }
        }
        Recon.ReconIntra(playback.ThisFrameRecon, playback.recon_pixel_index_table[n], this.ReconDataBuffer, n2);
    }
    
    private void ExpandBlock(final Playback playback, final int n) {
        final byte b = playback.FragQs[n];
        CodingMode code_INTRA;
        if (playback.getFrameType() == 0) {
            code_INTRA = CodingMode.CODE_INTRA;
        }
        else {
            code_INTRA = playback.FragCodingMethod[n];
        }
        int n2;
        int n3;
        int n4;
        short[] array;
        if (n < playback.YPlaneFragments) {
            n2 = playback.YStride;
            n3 = 1;
            n4 = 1;
            if (code_INTRA == CodingMode.CODE_INTRA) {
                array = playback.info.dequant_tables[0][0][playback.frameQIS[b]];
                this.dequant_matrix[0] = playback.info.dequant_tables[0][0][playback.frameQIS[0]][0];
            }
            else {
                array = playback.info.dequant_tables[1][0][playback.frameQIS[b]];
                this.dequant_matrix[0] = playback.info.dequant_tables[1][0][playback.frameQIS[0]][0];
            }
        }
        else {
            n2 = playback.UVStride;
            n3 = 2;
            n4 = 3;
            if (n < playback.YPlaneFragments + playback.UVPlaneFragments) {
                if (code_INTRA == CodingMode.CODE_INTRA) {
                    array = playback.info.dequant_tables[0][1][playback.frameQIS[b]];
                    this.dequant_matrix[0] = playback.info.dequant_tables[0][1][playback.frameQIS[0]][0];
                }
                else {
                    array = playback.info.dequant_tables[1][1][playback.frameQIS[b]];
                    this.dequant_matrix[0] = playback.info.dequant_tables[1][1][playback.frameQIS[0]][0];
                }
            }
            else if (code_INTRA == CodingMode.CODE_INTRA) {
                array = playback.info.dequant_tables[0][2][playback.frameQIS[b]];
                this.dequant_matrix[0] = playback.info.dequant_tables[0][2][playback.frameQIS[0]][0];
            }
            else {
                array = playback.info.dequant_tables[1][2][playback.frameQIS[b]];
                this.dequant_matrix[0] = playback.info.dequant_tables[1][2][playback.frameQIS[0]][0];
            }
        }
        for (int i = 1; i < 64; ++i) {
            this.dequant_matrix[i] = array[i];
        }
        final short[] array2 = playback.QFragData[n];
        switch (playback.FragCoefEOB[n]) {
            case 0:
            case 1: {
                this.idct.IDct1(array2, this.dequant_matrix, this.ReconDataBuffer);
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
                this.idct.IDct10(array2, this.dequant_matrix, this.ReconDataBuffer);
                break;
            }
            default: {
                this.idct.IDctSlow(array2, this.dequant_matrix, this.ReconDataBuffer);
                break;
            }
        }
        final int n5 = playback.recon_pixel_index_table[n];
        if (code_INTRA == CodingMode.CODE_INTER_NO_MV) {
            Recon.ReconInter(playback.ThisFrameRecon, n5, playback.LastFrameRecon, n5, this.ReconDataBuffer, n2);
        }
        else if (DCTDecode.ModeUsesMC[code_INTRA.getValue()] != 0) {
            int n6 = 0;
            int n7 = 0;
            final int x = playback.FragMVect[n].x;
            if (x > 0) {
                n7 = x >> n3;
                if ((x & n4) != 0x0) {
                    n6 = 1;
                }
            }
            else if (x < 0) {
                n7 = -(-x >> n3);
                if ((-x & n4) != 0x0) {
                    n6 = -1;
                }
            }
            final int y = playback.FragMVect[n].y;
            if (y > 0) {
                n7 += (y >> n3) * n2;
                if ((y & n4) != 0x0) {
                    n6 += n2;
                }
            }
            else if (y < 0) {
                n7 -= (-y >> n3) * n2;
                if ((-y & n4) != 0x0) {
                    n6 -= n2;
                }
            }
            final int n8 = n5 + n7;
            short[] array3;
            if (code_INTRA == CodingMode.CODE_GOLDEN_MV) {
                array3 = playback.GoldenFrame;
            }
            else {
                array3 = playback.LastFrameRecon;
            }
            if (n6 == 0) {
                Recon.ReconInter(playback.ThisFrameRecon, n5, array3, n8, this.ReconDataBuffer, n2);
            }
            else {
                Recon.ReconInterHalfPixel2(playback.ThisFrameRecon, n5, array3, n8, array3, n8 + n6, this.ReconDataBuffer, n2);
            }
        }
        else if (code_INTRA == CodingMode.CODE_USING_GOLDEN) {
            Recon.ReconInter(playback.ThisFrameRecon, n5, playback.GoldenFrame, n5, this.ReconDataBuffer, n2);
        }
        else {
            Recon.ReconIntra(playback.ThisFrameRecon, n5, this.ReconDataBuffer, n2);
        }
    }
    
    private void UpdateUMV_HBorders(final Playback playback, final short[] array, final int n) {
        int n2;
        int n3;
        int n4;
        int n5;
        int hFragments;
        if (n == 0) {
            n2 = playback.YStride * 7;
            n3 = playback.YStride;
            n4 = 16;
            n5 = playback.YPlaneFragments;
            hFragments = playback.HFragments;
        }
        else {
            n2 = playback.UVStride * 7;
            n3 = playback.UVStride;
            n4 = 8;
            n5 = playback.UVPlaneFragments;
            hFragments = playback.HFragments / 2;
        }
        final int n6 = playback.recon_pixel_index_table[n] - n4;
        int n7 = n6 - n4 * n3;
        final int n8 = playback.recon_pixel_index_table[n + n5 - hFragments] + n2 - n4;
        int n9 = n8 + n3;
        for (int i = 0; i < n4; ++i) {
            System.arraycopy(array, n6, array, n7, n3);
            System.arraycopy(array, n8, array, n9, n3);
            n7 += n3;
            n9 += n3;
        }
    }
    
    private void UpdateUMV_VBorders(final Playback playback, final short[] array, final int n) {
        int n2;
        int n3;
        int hFragments;
        int height;
        if (n == 0) {
            n2 = playback.YStride;
            n3 = 16;
            hFragments = playback.HFragments;
            height = playback.info.height;
        }
        else {
            n2 = playback.UVStride;
            n3 = 8;
            hFragments = playback.HFragments / 2;
            height = playback.info.height / 2;
        }
        int n5;
        int n4 = (n5 = playback.recon_pixel_index_table[n]) - n3;
        int n7;
        int n6 = (n7 = playback.recon_pixel_index_table[n + hFragments - 1] + 7) + 1;
        for (int i = 0; i < height; ++i) {
            MemUtils.set(array, n4, array[n5], n3);
            MemUtils.set(array, n6, array[n7], n3);
            n4 += n2;
            n6 += n2;
            n5 += n2;
            n7 += n2;
        }
    }
    
    private void UpdateUMVBorder(final Playback playback, final short[] array) {
        final int n = 0;
        this.UpdateUMV_VBorders(playback, array, n);
        this.UpdateUMV_HBorders(playback, array, n);
        final int yPlaneFragments = playback.YPlaneFragments;
        this.UpdateUMV_VBorders(playback, array, yPlaneFragments);
        this.UpdateUMV_HBorders(playback, array, yPlaneFragments);
        final int n2 = playback.YPlaneFragments + playback.UVPlaneFragments;
        this.UpdateUMV_VBorders(playback, array, n2);
        this.UpdateUMV_HBorders(playback, array, n2);
    }
    
    private void CopyRecon(final Playback playback, final short[] array, final short[] array2) {
        final int yStride = playback.YStride;
        for (int i = 0; i < playback.YPlaneFragments; ++i) {
            if (playback.display_fragments[i] != 0) {
                Recon.CopyBlock(array2, array, playback.recon_pixel_index_table[i], yStride);
            }
        }
        final int uvStride = playback.UVStride;
        for (int j = playback.YPlaneFragments; j < playback.UnitFragments; ++j) {
            if (playback.display_fragments[j] != 0) {
                Recon.CopyBlock(array2, array, playback.recon_pixel_index_table[j], uvStride);
            }
        }
    }
    
    private void CopyNotRecon(final Playback playback, final short[] array, final short[] array2) {
        final int yStride = playback.YStride;
        for (int i = 0; i < playback.YPlaneFragments; ++i) {
            if (playback.display_fragments[i] == 0) {
                Recon.CopyBlock(array2, array, playback.recon_pixel_index_table[i], yStride);
            }
        }
        final int uvStride = playback.UVStride;
        for (int j = playback.YPlaneFragments; j < playback.UnitFragments; ++j) {
            if (playback.display_fragments[j] == 0) {
                Recon.CopyBlock(array2, array, playback.recon_pixel_index_table[j], uvStride);
            }
        }
    }
    
    public void ExpandToken(final short[] array, final byte[] array2, final int n, int n2, final int n3) {
        if (n2 >= 23) {
            if (n2 < 30) {
                if (n2 < 28) {
                    array[array2[n] += (byte)(n2 - 23 + 1)] = (short)(-(((n3 & 0x1) << 1) - 1));
                }
                else if (n2 == 28) {
                    array[array2[n] += (byte)(6 + (n3 & 0x3))] = (short)(-(((n3 & 0x4) >> 1) - 1));
                }
                else {
                    array[array2[n] += (byte)(10 + (n3 & 0x7))] = (short)(-(((n3 & 0x8) >> 2) - 1));
                }
            }
            else if (n2 == 30) {
                array[++array2[n]] = (short)((2 + (n3 & 0x1)) * -((n3 & 0x2) - 1));
            }
            else {
                array[array2[n] += (byte)(2 + (n3 & 0x1))] = (short)((2 + ((n3 & 0x2) >> 1)) * -(((n3 & 0x4) >> 1) - 1));
            }
            ++array2[n];
        }
        else if (n2 == 7) {
            array2[n] += (byte)(n3 + 1);
        }
        else if (n2 == 8) {
            array2[n] += (byte)(n3 + 1);
        }
        else if (n2 < 13) {
            switch (n2) {
                case 9: {
                    array[array2[n]] = 1;
                    break;
                }
                case 10: {
                    array[array2[n]] = -1;
                    break;
                }
                case 11: {
                    array[array2[n]] = 2;
                    break;
                }
                case 12: {
                    array[array2[n]] = -2;
                    break;
                }
            }
            ++array2[n];
        }
        else {
            if (n2 < 17) {
                n2 -= 13;
                array[array2[n]] = (short)((n2 + 3) * -((n3 << 1) - 1));
            }
            else if (n2 == 17) {
                array[array2[n]] = (short)((7 + (n3 & 0x1)) * -((n3 & 0x2) - 1));
            }
            else if (n2 == 18) {
                array[array2[n]] = (short)((9 + (n3 & 0x3)) * -(((n3 & 0x4) >> 1) - 1));
            }
            else if (n2 == 19) {
                array[array2[n]] = (short)((13 + (n3 & 0x7)) * -(((n3 & 0x8) >> 2) - 1));
            }
            else if (n2 == 20) {
                array[array2[n]] = (short)((21 + (n3 & 0xF)) * -(((n3 & 0x10) >> 3) - 1));
            }
            else if (n2 == 21) {
                array[array2[n]] = (short)((37 + (n3 & 0x1F)) * -(((n3 & 0x20) >> 4) - 1));
            }
            else if (n2 == 22) {
                array[array2[n]] = (short)((69 + (n3 & 0x1FF)) * -(((n3 & 0x200) >> 8) - 1));
            }
            ++array2[n];
        }
    }
    
    public void ReconRefFrames(final Playback playback) {
        final int hFragments = playback.HFragments;
        final int vFragments = playback.VFragments;
        final boolean b = playback.getFrameType() == 0;
        playback.filter.SetupLoopFilter(playback.FrameQIndex);
        for (int i = 0; i < 3; ++i) {
            int yPlaneFragments = 0;
            int hFragments2 = 0;
            int vFragments2 = 0;
            switch (i) {
                case 0: {
                    yPlaneFragments = 0;
                    hFragments2 = playback.HFragments;
                    vFragments2 = playback.VFragments;
                    break;
                }
                case 1: {
                    yPlaneFragments = playback.YPlaneFragments;
                    hFragments2 = playback.HFragments >> 1;
                    vFragments2 = playback.VFragments >> 1;
                    break;
                }
                default: {
                    yPlaneFragments = playback.YPlaneFragments + playback.UVPlaneFragments;
                    hFragments2 = playback.HFragments >> 1;
                    vFragments2 = playback.VFragments >> 1;
                    break;
                }
            }
            for (int j = 0; j < 3; ++j) {
                this.Last[j] = 0;
            }
            int n = yPlaneFragments;
            for (int k = 0; k < vFragments2; ++k) {
                for (int l = 0; l < hFragments2; ++l, ++n) {
                    if (playback.display_fragments[n] != 0 || playback.getFrameType() == 0) {
                        final short n2 = DCTDecode.Mode2Frame[playback.FragCodingMethod[n].getValue()];
                        final int n3 = ((l == 0) ? 1 : 0) + (((k == 0) ? 1 : 0) << 1) + (((l + 1 == hFragments2) ? 1 : 0) << 2);
                        this.fn[0] = n - 1;
                        this.fn[1] = n - hFragments2 - 1;
                        this.fn[2] = n - hFragments2;
                        this.fn[3] = n - hFragments2 + 1;
                        short n6;
                        int n5;
                        int n4;
                        for (n4 = (n5 = (n6 = 0)); n5 < 4; ++n5) {
                            final int n7 = 1 << n5;
                            if ((DCTDecode.bc_mask[n3] & n7) != 0x0 && playback.display_fragments[this.fn[n5]] != 0 && DCTDecode.Mode2Frame[playback.FragCodingMethod[this.fn[n5]].getValue()] == n2) {
                                this.v[n4] = playback.QFragData[this.fn[n5]][0];
                                n6 |= (short)n7;
                                ++n4;
                            }
                        }
                        if (n6 == 0) {
                            final short[] array = playback.QFragData[n];
                            final int n8 = 0;
                            array[n8] += this.Last[n2];
                        }
                        else {
                            short n9 = (short)(DCTDecode.pc[n6][0] * this.v[0]);
                            for (int n10 = 1; n10 < n4; ++n10) {
                                n9 += (short)(DCTDecode.pc[n6][n10] * this.v[n10]);
                            }
                            if (DCTDecode.pc[n6][4] != 0) {
                                if (n9 < 0) {
                                    n9 += DCTDecode.pc[n6][5];
                                }
                                n9 >>= DCTDecode.pc[n6][4];
                            }
                            if ((n6 & 0x7) == 0x7) {
                                if (Math.abs(n9 - this.v[2]) > 128) {
                                    n9 = (short)this.v[2];
                                }
                                else if (Math.abs(n9 - this.v[0]) > 128) {
                                    n9 = (short)this.v[0];
                                }
                                else if (Math.abs(n9 - this.v[1]) > 128) {
                                    n9 = (short)this.v[1];
                                }
                            }
                            final short[] array2 = playback.QFragData[n];
                            final int n11 = 0;
                            array2[n11] += n9;
                        }
                        this.Last[n2] = playback.QFragData[n][0];
                        if (b) {
                            this.ExpandKFBlock(playback, n);
                        }
                        else {
                            this.ExpandBlock(playback, n);
                        }
                    }
                }
            }
        }
        if (playback.CodedBlockIndex > playback.UnitFragments >> 1) {
            final short[] thisFrameRecon = playback.ThisFrameRecon;
            playback.ThisFrameRecon = playback.LastFrameRecon;
            this.CopyNotRecon(playback, playback.LastFrameRecon = thisFrameRecon, playback.ThisFrameRecon);
        }
        else {
            this.CopyRecon(playback, playback.LastFrameRecon, playback.ThisFrameRecon);
        }
        playback.filter.LoopFilter(playback);
        this.UpdateUMVBorder(playback, playback.LastFrameRecon);
        if (b) {
            this.CopyRecon(playback, playback.GoldenFrame, playback.LastFrameRecon);
            this.UpdateUMVBorder(playback, playback.GoldenFrame);
        }
    }
    
    static {
        ModeUsesMC = new int[] { 0, 0, 1, 1, 1, 0, 1, 1 };
        pc = new short[][] { { 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1, 1 }, { 0, 1, 0, 0, 0, 0 }, { 29, -26, 29, 0, 5, 31 }, { 1, 0, 0, 0, 0, 0 }, { 75, 53, 0, 0, 7, 127 }, { 1, 1, 0, 0, 1, 1 }, { 75, 0, 53, 0, 7, 127 }, { 1, 0, 0, 0, 0, 0 }, { 75, 0, 53, 0, 7, 127 }, { 3, 10, 3, 0, 4, 15 }, { 29, -26, 29, 0, 5, 31 } };
        bc_mask = new int[] { 15, 12, 1, 0, 7, 4, 1, 0 };
        Mode2Frame = new short[] { 1, 0, 1, 1, 1, 2, 2, 1 };
    }
}