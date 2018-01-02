// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class FrInit
{
    static void InitializeFragCoordinates(final Playback playback) {
        final int hFragments = playback.HFragments;
        final int vFragments = playback.VFragments;
        for (int i = 0; i < vFragments; ++i) {
            for (int j = 0; j < hFragments; ++j) {
                playback.FragCoordinates[i * hFragments + j] = new Coordinate(j * 8, i * 8);
            }
        }
        final int n = hFragments >> 1;
        final int n2 = vFragments >> 1;
        final int yPlaneFragments = playback.YPlaneFragments;
        for (int k = 0; k < n2; ++k) {
            for (int l = 0; l < n; ++l) {
                playback.FragCoordinates[yPlaneFragments + k * n + l] = new Coordinate(l * 8, k * 8);
            }
        }
        final int n3 = playback.YPlaneFragments + playback.UVPlaneFragments;
        for (int n4 = 0; n4 < n2; ++n4) {
            for (int n5 = 0; n5 < n; ++n5) {
                playback.FragCoordinates[n3 + n4 * n + n5] = new Coordinate(n5 * 8, n4 * 8);
            }
        }
    }
    
    static void CalcPixelIndexTable(final Playback playback) {
        final int[] pixel_index_table = playback.pixel_index_table;
        for (int i = 0; i < playback.YPlaneFragments; ++i) {
            pixel_index_table[i] = i / playback.HFragments * 8 * playback.info.width;
            final int[] array = pixel_index_table;
            final int n = i;
            array[n] += i % playback.HFragments * 8;
        }
        final int yPlaneFragments = playback.YPlaneFragments;
        for (int j = 0; j < (playback.HFragments >> 1) * playback.VFragments; ++j) {
            pixel_index_table[j + yPlaneFragments] = j / (playback.HFragments / 2) * (8 * (playback.info.width / 2));
            final int[] array2 = pixel_index_table;
            final int n2 = j + yPlaneFragments;
            array2[n2] += j % (playback.HFragments / 2) * 8 + playback.YPlaneSize;
        }
        final int[] recon_pixel_index_table = playback.recon_pixel_index_table;
        for (int k = 0; k < playback.YPlaneFragments; ++k) {
            recon_pixel_index_table[k] = k / playback.HFragments * 8 * playback.YStride;
            final int[] array3 = recon_pixel_index_table;
            final int n3 = k;
            array3[n3] += k % playback.HFragments * 8 + playback.ReconYDataOffset;
        }
        final int yPlaneFragments2 = playback.YPlaneFragments;
        for (int l = 0; l < playback.UVPlaneFragments; ++l) {
            recon_pixel_index_table[l + yPlaneFragments2] = l / (playback.HFragments / 2) * (8 * playback.UVStride);
            final int[] array4 = recon_pixel_index_table;
            final int n4 = l + yPlaneFragments2;
            array4[n4] += l % (playback.HFragments / 2) * 8 + playback.ReconUDataOffset;
        }
        final int n5 = playback.YPlaneFragments + playback.UVPlaneFragments;
        for (int n6 = 0; n6 < playback.UVPlaneFragments; ++n6) {
            recon_pixel_index_table[n6 + n5] = n6 / (playback.HFragments / 2) * (8 * playback.UVStride);
            final int[] array5 = recon_pixel_index_table;
            final int n7 = n6 + n5;
            array5[n7] += n6 % (playback.HFragments / 2) * 8 + playback.ReconVDataOffset;
        }
    }
    
    static void ClearFragmentInfo(final Playback playback) {
        playback.display_fragments = null;
        playback.pixel_index_table = null;
        playback.recon_pixel_index_table = null;
        playback.FragTokenCounts = null;
        playback.CodedBlockList = null;
        playback.FragMVect = null;
        playback.FragCoefEOB = null;
        playback.QFragData = null;
        playback.FragCodingMethod = null;
        playback.FragCoordinates = null;
        playback.FragQIndex = null;
        playback.BlockMap = null;
        playback.SBCodedFlags = null;
        playback.SBFullyFlags = null;
        playback.MBFullyFlags = null;
        playback.MBCodedFlags = null;
    }
    
    static void InitFragmentInfo(final Playback playback) {
        ClearFragmentInfo(playback);
        playback.display_fragments = new byte[playback.UnitFragments];
        playback.pixel_index_table = new int[playback.UnitFragments];
        playback.recon_pixel_index_table = new int[playback.UnitFragments];
        playback.FragTokenCounts = new int[playback.UnitFragments];
        playback.CodedBlockList = new int[playback.UnitFragments];
        playback.FragMVect = new MotionVector[playback.UnitFragments];
        for (int i = 0; i < playback.UnitFragments; ++i) {
            playback.FragMVect[i] = new MotionVector();
        }
        playback.FragCoefEOB = new byte[playback.UnitFragments];
        playback.QFragData = new short[playback.UnitFragments][64];
        playback.FragCodingMethod = new CodingMode[playback.UnitFragments];
        playback.FragCoordinates = new Coordinate[playback.UnitFragments];
        playback.FragQIndex = new int[playback.UnitFragments];
        playback.SBCodedFlags = new byte[playback.SuperBlocks];
        playback.SBFullyFlags = new byte[playback.SuperBlocks];
        playback.MBCodedFlags = new byte[playback.MacroBlocks];
        playback.MBFullyFlags = new byte[playback.MacroBlocks];
    }
    
    static void ClearFrameInfo(final Playback playback) {
        playback.ThisFrameRecon = null;
        playback.GoldenFrame = null;
        playback.LastFrameRecon = null;
        playback.PostProcessBuffer = null;
    }
    
    static void InitFrameInfo(final Playback playback, final int n) {
        ClearFrameInfo(playback);
        playback.ThisFrameRecon = new short[n];
        playback.GoldenFrame = new short[n];
        playback.LastFrameRecon = new short[n];
        playback.PostProcessBuffer = new short[n];
    }
    
    static void InitFrameDetails(final Playback playback) {
        playback.PostProcessingLevel = 0;
        playback.YPlaneSize = playback.info.width * playback.info.height;
        playback.UVPlaneSize = playback.YPlaneSize / 4;
        playback.HFragments = playback.info.width / 8;
        playback.VFragments = playback.info.height / 8;
        playback.UnitFragments = playback.VFragments * playback.HFragments * 3 / 2;
        playback.YPlaneFragments = playback.HFragments * playback.VFragments;
        playback.UVPlaneFragments = playback.YPlaneFragments / 4;
        playback.YStride = playback.info.width + 32;
        playback.UVStride = playback.YStride / 2;
        playback.ReconYPlaneSize = playback.YStride * (playback.info.height + 32);
        playback.ReconUVPlaneSize = playback.ReconYPlaneSize / 4;
        final int n = playback.ReconYPlaneSize + 2 * playback.ReconUVPlaneSize;
        playback.YDataOffset = 0;
        playback.UDataOffset = playback.YPlaneSize;
        playback.VDataOffset = playback.YPlaneSize + playback.UVPlaneSize;
        playback.ReconYDataOffset = playback.YStride * 16 + 16;
        playback.ReconUDataOffset = playback.ReconYPlaneSize + playback.UVStride * 8 + 8;
        playback.ReconVDataOffset = playback.ReconYPlaneSize + playback.ReconUVPlaneSize + playback.UVStride * 8 + 8;
        playback.YSBRows = playback.info.height / 32 + ((playback.info.height % 32 != 0) ? 1 : 0);
        playback.YSBCols = playback.info.width / 32 + ((playback.info.width % 32 != 0) ? 1 : 0);
        playback.UVSBRows = playback.info.height / 2 / 32 + ((playback.info.height / 2 % 32 != 0) ? 1 : 0);
        playback.UVSBCols = playback.info.width / 2 / 32 + ((playback.info.width / 2 % 32 != 0) ? 1 : 0);
        playback.YSuperBlocks = playback.YSBRows * playback.YSBCols;
        playback.UVSuperBlocks = playback.UVSBRows * playback.UVSBCols;
        playback.SuperBlocks = playback.YSuperBlocks + 2 * playback.UVSuperBlocks;
        playback.YMacroBlocks = (playback.VFragments + 1) / 2 * ((playback.HFragments + 1) / 2);
        playback.UVMacroBlocks = (playback.VFragments / 2 + 1) / 2 * ((playback.HFragments / 2 + 1) / 2);
        playback.MacroBlocks = playback.YMacroBlocks + 2 * playback.UVMacroBlocks;
        InitFragmentInfo(playback);
        InitFrameInfo(playback, n);
        InitializeFragCoordinates(playback);
        playback.BlockMap = new BlockMapping(playback.YSuperBlocks, playback.UVSuperBlocks, playback.HFragments, playback.VFragments);
        CalcPixelIndexTable(playback);
    }
}
