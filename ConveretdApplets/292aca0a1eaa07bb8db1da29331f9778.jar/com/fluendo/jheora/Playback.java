// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

public class Playback
{
    private static final int DCT_KEY_FRAME = 0;
    Buffer opb;
    Info info;
    int keyframe_granule_shift;
    int DecoderErrorCode;
    int FramesHaveBeenSkipped;
    int PostProcessEnabled;
    int PostProcessingLevel;
    byte FrameType;
    byte KeyFrameType;
    int QualitySetting;
    int FrameQIndex;
    int ThisFrameQualityValue;
    int LastFrameQualityValue;
    int CodedBlockIndex;
    int CodedBlocksThisFrame;
    int FrameSize;
    int YPlaneSize;
    int UVPlaneSize;
    int YStride;
    int UVStride;
    int VFragments;
    int HFragments;
    int UnitFragments;
    int YPlaneFragments;
    int UVPlaneFragments;
    int ReconYPlaneSize;
    int ReconUVPlaneSize;
    int YDataOffset;
    int UDataOffset;
    int VDataOffset;
    int ReconYDataOffset;
    int ReconUDataOffset;
    int ReconVDataOffset;
    int YSuperBlocks;
    int UVSuperBlocks;
    int SuperBlocks;
    int YSBRows;
    int YSBCols;
    int UVSBRows;
    int UVSBCols;
    int YMacroBlocks;
    int UVMacroBlocks;
    int MacroBlocks;
    short[] ThisFrameRecon;
    short[] GoldenFrame;
    short[] LastFrameRecon;
    short[] PostProcessBuffer;
    int[] pixel_index_table;
    int[] recon_pixel_index_table;
    byte[] display_fragments;
    int[] CodedBlockList;
    MotionVector[] FragMVect;
    int[] FragTokenCounts;
    int[] FragQIndex;
    byte[] FragCoefEOB;
    short[][] QFragData;
    CodingMode[] FragCodingMethod;
    BlockMapping BlockMap;
    byte[] SBCodedFlags;
    byte[] SBFullyFlags;
    byte[] MBCodedFlags;
    byte[] MBFullyFlags;
    Coordinate[] FragCoordinates;
    FrArray frArray;
    Filter filter;
    int[] QThreshTable;
    short[] DcScaleFactorTable;
    short[] Y_coeffs;
    short[] UV_coeffs;
    short[] Inter_coeffs;
    short[] dequant_InterUV_coeffs;
    int[] quant_index;
    int[] quant_Y_coeffs;
    int[] quant_UV_coeffs;
    HuffEntry[] HuffRoot_VP3x;
    int[][] HuffCodeArray_VP3x;
    byte[][] HuffCodeLengthArray_VP3x;
    byte[] ExtraBitLengths_VP3x;
    short[] dequant_Y_coeffs;
    short[] dequant_UV_coeffs;
    short[] dequant_Inter_coeffs;
    short[] dequant_coeffs;
    
    public void clearTmpBuffers() {
        this.dequant_Y_coeffs = null;
        this.dequant_UV_coeffs = null;
        this.dequant_InterUV_coeffs = null;
        this.dequant_Inter_coeffs = null;
    }
    
    private void initTmpBuffers() {
        this.clearTmpBuffers();
        this.dequant_Y_coeffs = new short[64];
        this.dequant_UV_coeffs = new short[64];
        this.dequant_Inter_coeffs = new short[64];
        this.dequant_InterUV_coeffs = new short[64];
    }
    
    public void clear() {
        this.clearTmpBuffers();
        if (this.opb != null) {
            this.opb = null;
        }
    }
    
    private static int ilog(long v) {
        int ret = 0;
        while (v != 0L) {
            ++ret;
            v >>= 1;
        }
        return ret;
    }
    
    public Playback(final Info ci) {
        this.opb = new Buffer();
        this.frArray = new FrArray();
        this.filter = new Filter();
        this.QThreshTable = new int[64];
        this.DcScaleFactorTable = new short[64];
        this.Y_coeffs = new short[64];
        this.UV_coeffs = new short[64];
        this.Inter_coeffs = new short[64];
        this.quant_index = new int[64];
        this.quant_Y_coeffs = new int[64];
        this.quant_UV_coeffs = new int[64];
        this.HuffRoot_VP3x = new HuffEntry[80];
        this.info = ci;
        this.initTmpBuffers();
        this.DecoderErrorCode = 0;
        this.KeyFrameType = 0;
        this.FramesHaveBeenSkipped = 0;
        FrInit.InitFrameDetails(this);
        this.keyframe_granule_shift = ilog(ci.keyframe_frequency_force - 1L);
        this.LastFrameQualityValue = 0;
        this.copyQTables(ci);
        this.filter.copyFilterTables(ci);
        this.initHuffmanTrees(ci);
    }
    
    public int getFrameType() {
        return this.FrameType;
    }
    
    void setFrameType(final byte FrType) {
        switch (FrType) {
            case 0: {
                this.FrameType = FrType;
                break;
            }
            default: {
                this.FrameType = FrType;
                break;
            }
        }
    }
    
    public void clearHuffmanSet() {
        Huffman.clearHuffmanTrees(this.HuffRoot_VP3x);
        this.HuffCodeArray_VP3x = null;
        this.HuffCodeLengthArray_VP3x = null;
    }
    
    public void initHuffmanSet() {
        this.clearHuffmanSet();
        this.ExtraBitLengths_VP3x = HuffTables.ExtraBitLengths_VP31;
        this.HuffCodeArray_VP3x = new int[80][32];
        this.HuffCodeLengthArray_VP3x = new byte[80][32];
        for (int i = 0; i < 80; ++i) {
            Huffman.buildHuffmanTree(this.HuffRoot_VP3x, this.HuffCodeArray_VP3x[i], this.HuffCodeLengthArray_VP3x[i], i, HuffTables.FrequencyCounts_VP3[i]);
        }
    }
    
    public int readHuffmanTrees(final Info ci, final Buffer opb) {
        for (int i = 0; i < 80; ++i) {
            ci.HuffRoot[i] = new HuffEntry();
            final int ret = ci.HuffRoot[i].read(0, opb);
            if (ret != 0) {
                return ret;
            }
        }
        return 0;
    }
    
    public void initHuffmanTrees(final Info ci) {
        this.ExtraBitLengths_VP3x = HuffTables.ExtraBitLengths_VP31;
        for (int i = 0; i < 80; ++i) {
            this.HuffRoot_VP3x[i] = ci.HuffRoot[i].copy();
        }
    }
    
    public void copyQTables(final Info ci) {
        System.arraycopy(ci.QThreshTable, 0, this.QThreshTable, 0, 64);
        System.arraycopy(ci.DcScaleFactorTable, 0, this.DcScaleFactorTable, 0, 64);
        System.arraycopy(ci.Y_coeffs, 0, this.Y_coeffs, 0, 64);
        System.arraycopy(ci.UV_coeffs, 0, this.UV_coeffs, 0, 64);
        System.arraycopy(ci.Inter_coeffs, 0, this.Inter_coeffs, 0, 64);
    }
}
