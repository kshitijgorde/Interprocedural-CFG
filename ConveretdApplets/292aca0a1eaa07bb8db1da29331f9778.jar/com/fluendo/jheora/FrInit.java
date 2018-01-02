// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class FrInit
{
    static void InitializeFragCoordinates(final Playback pbi) {
        int HorizFrags = pbi.HFragments;
        int VertFrags = pbi.VFragments;
        int StartFrag = 0;
        for (int i = 0; i < VertFrags; ++i) {
            for (int j = 0; j < HorizFrags; ++j) {
                final int ThisFrag = i * HorizFrags + j;
                pbi.FragCoordinates[ThisFrag] = new Coordinate(j * 8, i * 8);
            }
        }
        HorizFrags >>= 1;
        VertFrags >>= 1;
        StartFrag = pbi.YPlaneFragments;
        for (int i = 0; i < VertFrags; ++i) {
            for (int j = 0; j < HorizFrags; ++j) {
                final int ThisFrag = StartFrag + i * HorizFrags + j;
                pbi.FragCoordinates[ThisFrag] = new Coordinate(j * 8, i * 8);
            }
        }
        StartFrag = pbi.YPlaneFragments + pbi.UVPlaneFragments;
        for (int i = 0; i < VertFrags; ++i) {
            for (int j = 0; j < HorizFrags; ++j) {
                final int ThisFrag = StartFrag + i * HorizFrags + j;
                pbi.FragCoordinates[ThisFrag] = new Coordinate(j * 8, i * 8);
            }
        }
    }
    
    static void CalcPixelIndexTable(final Playback pbi) {
        int[] PixelIndexTablePtr = pbi.pixel_index_table;
        for (int i = 0; i < pbi.YPlaneFragments; ++i) {
            PixelIndexTablePtr[i] = i / pbi.HFragments * 8 * pbi.info.width;
            final int[] array = PixelIndexTablePtr;
            final int n = i;
            array[n] += i % pbi.HFragments * 8;
        }
        int off = pbi.YPlaneFragments;
        for (int i = 0; i < (pbi.HFragments >> 1) * pbi.VFragments; ++i) {
            PixelIndexTablePtr[i + off] = i / (pbi.HFragments / 2) * (8 * (pbi.info.width / 2));
            final int[] array2 = PixelIndexTablePtr;
            final int n2 = i + off;
            array2[n2] += i % (pbi.HFragments / 2) * 8 + pbi.YPlaneSize;
        }
        PixelIndexTablePtr = pbi.recon_pixel_index_table;
        for (int i = 0; i < pbi.YPlaneFragments; ++i) {
            PixelIndexTablePtr[i] = i / pbi.HFragments * 8 * pbi.YStride;
            final int[] array3 = PixelIndexTablePtr;
            final int n3 = i;
            array3[n3] += i % pbi.HFragments * 8 + pbi.ReconYDataOffset;
        }
        off = pbi.YPlaneFragments;
        for (int i = 0; i < pbi.UVPlaneFragments; ++i) {
            PixelIndexTablePtr[i + off] = i / (pbi.HFragments / 2) * (8 * pbi.UVStride);
            final int[] array4 = PixelIndexTablePtr;
            final int n4 = i + off;
            array4[n4] += i % (pbi.HFragments / 2) * 8 + pbi.ReconUDataOffset;
        }
        off = pbi.YPlaneFragments + pbi.UVPlaneFragments;
        for (int i = 0; i < pbi.UVPlaneFragments; ++i) {
            PixelIndexTablePtr[i + off] = i / (pbi.HFragments / 2) * (8 * pbi.UVStride);
            final int[] array5 = PixelIndexTablePtr;
            final int n5 = i + off;
            array5[n5] += i % (pbi.HFragments / 2) * 8 + pbi.ReconVDataOffset;
        }
    }
    
    static void ClearFragmentInfo(final Playback pbi) {
        pbi.display_fragments = null;
        pbi.pixel_index_table = null;
        pbi.recon_pixel_index_table = null;
        pbi.FragTokenCounts = null;
        pbi.CodedBlockList = null;
        pbi.FragMVect = null;
        pbi.FragCoefEOB = null;
        pbi.QFragData = null;
        pbi.FragCodingMethod = null;
        pbi.FragCoordinates = null;
        pbi.FragQIndex = null;
        pbi.BlockMap = null;
        pbi.SBCodedFlags = null;
        pbi.SBFullyFlags = null;
        pbi.MBFullyFlags = null;
        pbi.MBCodedFlags = null;
    }
    
    static void InitFragmentInfo(final Playback pbi) {
        ClearFragmentInfo(pbi);
        pbi.display_fragments = new byte[pbi.UnitFragments];
        pbi.pixel_index_table = new int[pbi.UnitFragments];
        pbi.recon_pixel_index_table = new int[pbi.UnitFragments];
        pbi.FragTokenCounts = new int[pbi.UnitFragments];
        pbi.CodedBlockList = new int[pbi.UnitFragments];
        pbi.FragMVect = new MotionVector[pbi.UnitFragments];
        for (int i = 0; i < pbi.UnitFragments; ++i) {
            pbi.FragMVect[i] = new MotionVector();
        }
        pbi.FragCoefEOB = new byte[pbi.UnitFragments];
        pbi.QFragData = new short[pbi.UnitFragments][64];
        pbi.FragCodingMethod = new CodingMode[pbi.UnitFragments];
        pbi.FragCoordinates = new Coordinate[pbi.UnitFragments];
        pbi.FragQIndex = new int[pbi.UnitFragments];
        pbi.SBCodedFlags = new byte[pbi.SuperBlocks];
        pbi.SBFullyFlags = new byte[pbi.SuperBlocks];
        pbi.MBCodedFlags = new byte[pbi.MacroBlocks];
        pbi.MBFullyFlags = new byte[pbi.MacroBlocks];
    }
    
    static void ClearFrameInfo(final Playback pbi) {
        pbi.ThisFrameRecon = null;
        pbi.GoldenFrame = null;
        pbi.LastFrameRecon = null;
        pbi.PostProcessBuffer = null;
    }
    
    static void InitFrameInfo(final Playback pbi, final int FrameSize) {
        ClearFrameInfo(pbi);
        pbi.ThisFrameRecon = new short[FrameSize];
        pbi.GoldenFrame = new short[FrameSize];
        pbi.LastFrameRecon = new short[FrameSize];
        pbi.PostProcessBuffer = new short[FrameSize];
    }
    
    static void InitFrameDetails(final Playback pbi) {
        pbi.PostProcessingLevel = 0;
        pbi.YPlaneSize = pbi.info.width * pbi.info.height;
        pbi.UVPlaneSize = pbi.YPlaneSize / 4;
        pbi.HFragments = pbi.info.width / 8;
        pbi.VFragments = pbi.info.height / 8;
        pbi.UnitFragments = pbi.VFragments * pbi.HFragments * 3 / 2;
        pbi.YPlaneFragments = pbi.HFragments * pbi.VFragments;
        pbi.UVPlaneFragments = pbi.YPlaneFragments / 4;
        pbi.YStride = pbi.info.width + 32;
        pbi.UVStride = pbi.YStride / 2;
        pbi.ReconYPlaneSize = pbi.YStride * (pbi.info.height + 32);
        pbi.ReconUVPlaneSize = pbi.ReconYPlaneSize / 4;
        final int FrameSize = pbi.ReconYPlaneSize + 2 * pbi.ReconUVPlaneSize;
        pbi.YDataOffset = 0;
        pbi.UDataOffset = pbi.YPlaneSize;
        pbi.VDataOffset = pbi.YPlaneSize + pbi.UVPlaneSize;
        pbi.ReconYDataOffset = pbi.YStride * 16 + 16;
        pbi.ReconUDataOffset = pbi.ReconYPlaneSize + pbi.UVStride * 8 + 8;
        pbi.ReconVDataOffset = pbi.ReconYPlaneSize + pbi.ReconUVPlaneSize + pbi.UVStride * 8 + 8;
        pbi.YSBRows = pbi.info.height / 32 + ((pbi.info.height % 32 != 0) ? 1 : 0);
        pbi.YSBCols = pbi.info.width / 32 + ((pbi.info.width % 32 != 0) ? 1 : 0);
        pbi.UVSBRows = pbi.info.height / 2 / 32 + ((pbi.info.height / 2 % 32 != 0) ? 1 : 0);
        pbi.UVSBCols = pbi.info.width / 2 / 32 + ((pbi.info.width / 2 % 32 != 0) ? 1 : 0);
        pbi.YSuperBlocks = pbi.YSBRows * pbi.YSBCols;
        pbi.UVSuperBlocks = pbi.UVSBRows * pbi.UVSBCols;
        pbi.SuperBlocks = pbi.YSuperBlocks + 2 * pbi.UVSuperBlocks;
        pbi.YMacroBlocks = (pbi.VFragments + 1) / 2 * ((pbi.HFragments + 1) / 2);
        pbi.UVMacroBlocks = (pbi.VFragments / 2 + 1) / 2 * ((pbi.HFragments / 2 + 1) / 2);
        pbi.MacroBlocks = pbi.YMacroBlocks + 2 * pbi.UVMacroBlocks;
        InitFragmentInfo(pbi);
        InitFrameInfo(pbi, FrameSize);
        InitializeFragCoordinates(pbi);
        pbi.BlockMap = new BlockMapping(pbi.YSuperBlocks, pbi.UVSuperBlocks, pbi.HFragments, pbi.VFragments);
        CalcPixelIndexTable(pbi);
    }
}
