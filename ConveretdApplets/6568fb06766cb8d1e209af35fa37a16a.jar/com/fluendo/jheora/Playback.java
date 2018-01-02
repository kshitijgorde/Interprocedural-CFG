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
    int CodedBlockIndex;
    int CodedBlocksThisFrame;
    int FrameSize;
    int[] frameQIS;
    int frameNQIS;
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
    byte[] FragQs;
    CodingMode[] FragCodingMethod;
    BlockMapping BlockMap;
    byte[] SBCodedFlags;
    byte[] SBFullyFlags;
    byte[] MBCodedFlags;
    byte[] MBFullyFlags;
    Coordinate[] FragCoordinates;
    FrArray frArray;
    Filter filter;
    byte[] blockQ;
    int[] quant_index;
    HuffEntry[] HuffRoot_VP3x;
    int[][] HuffCodeArray_VP3x;
    byte[][] HuffCodeLengthArray_VP3x;
    byte[] ExtraBitLengths_VP3x;
    
    public void clear() {
        if (this.opb != null) {
            this.opb = null;
        }
    }
    
    private static int ilog(long n) {
        int n2 = 0;
        while (n != 0L) {
            ++n2;
            n >>= 1;
        }
        return n2;
    }
    
    public Playback(final Info info) {
        this.opb = new Buffer();
        this.frameQIS = new int[3];
        this.frArray = new FrArray();
        this.filter = new Filter();
        this.quant_index = new int[64];
        this.HuffRoot_VP3x = new HuffEntry[80];
        this.info = info;
        this.DecoderErrorCode = 0;
        this.KeyFrameType = 0;
        this.FramesHaveBeenSkipped = 0;
        FrInit.InitFrameDetails(this);
        this.keyframe_granule_shift = ilog(info.keyframe_frequency_force - 1L);
        this.filter.copyFilterTables(info);
        this.initHuffmanTrees(info);
    }
    
    public int getFrameType() {
        return this.FrameType;
    }
    
    void setFrameType(final byte b) {
        switch (b) {
            case 0: {
                this.FrameType = b;
                break;
            }
            default: {
                this.FrameType = b;
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
    
    public int readHuffmanTrees(final Info info, final Buffer buffer) {
        for (int i = 0; i < 80; ++i) {
            info.HuffRoot[i] = new HuffEntry();
            final int read = info.HuffRoot[i].read(0, buffer);
            if (read != 0) {
                return read;
            }
        }
        return 0;
    }
    
    public void initHuffmanTrees(final Info info) {
        this.ExtraBitLengths_VP3x = HuffTables.ExtraBitLengths_VP31;
        for (int i = 0; i < 80; ++i) {
            this.HuffRoot_VP3x[i] = info.HuffRoot[i].copy();
        }
    }
}
