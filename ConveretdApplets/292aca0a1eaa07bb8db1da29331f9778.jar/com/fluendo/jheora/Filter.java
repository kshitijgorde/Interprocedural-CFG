// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.fluendo.utils.MemUtils;

public final class Filter
{
    private static final byte[] LoopFilterLimitValuesV1;
    private byte[] LoopFilterLimits;
    private int[] FiltBoundingValue;
    
    public Filter() {
        this.LoopFilterLimits = new byte[64];
        this.FiltBoundingValue = new int[512];
    }
    
    private void SetupBoundingValueArray_Generic(final int FLimit) {
        MemUtils.set(this.FiltBoundingValue, 0, 0, 512);
        for (int i = 0; i < FLimit; ++i) {
            this.FiltBoundingValue[256 - i - FLimit] = -FLimit + i;
            this.FiltBoundingValue[256 - i] = -i;
            this.FiltBoundingValue[256 + i] = i;
            this.FiltBoundingValue[256 + i + FLimit] = FLimit - i;
        }
    }
    
    public void copyFilterTables(final Info ci) {
        System.arraycopy(ci.LoopFilterLimitValues, 0, this.LoopFilterLimits, 0, 64);
    }
    
    public void InitFilterTables() {
        System.arraycopy(Filter.LoopFilterLimitValuesV1, 0, this.LoopFilterLimits, 0, 64);
    }
    
    public void SetupLoopFilter(final int FrameQIndex) {
        final int FLimit = this.LoopFilterLimits[FrameQIndex];
        this.SetupBoundingValueArray_Generic(FLimit);
    }
    
    private static final short clamp255(int val) {
        val -= 255;
        val = -(255 + (val >> 31 & val));
        return (short)(-(val >> 31 & val));
    }
    
    private void FilterHoriz(final short[] PixelPtr, int idx, final int LineLength, final int[] BoundingValuePtr) {
        for (int j = 0; j < 8; ++j) {
            int FiltVal = PixelPtr[0 + idx] - PixelPtr[1 + idx] * 3 + PixelPtr[2 + idx] * 3 - PixelPtr[3 + idx];
            FiltVal = BoundingValuePtr[256 + (FiltVal + 4 >> 3)];
            PixelPtr[1 + idx] = clamp255(PixelPtr[1 + idx] + FiltVal);
            PixelPtr[2 + idx] = clamp255(PixelPtr[2 + idx] - FiltVal);
            idx += LineLength;
        }
    }
    
    private void FilterVert(final short[] PixelPtr, int idx, final int LineLength, final int[] BoundingValuePtr) {
        idx -= 2 * LineLength;
        for (int j = 0; j < 8; ++j) {
            int FiltVal = PixelPtr[idx + 0] - PixelPtr[idx + LineLength] * 3 + PixelPtr[idx + 2 * LineLength] * 3 - PixelPtr[idx + 3 * LineLength];
            FiltVal = BoundingValuePtr[256 + (FiltVal + 4 >> 3)];
            PixelPtr[idx + LineLength] = clamp255(PixelPtr[idx + LineLength] + FiltVal);
            PixelPtr[idx + 2 * LineLength] = clamp255(PixelPtr[idx + 2 * LineLength] - FiltVal);
            ++idx;
        }
    }
    
    public void LoopFilter(final Playback pbi) {
        int FragsAcross = pbi.HFragments;
        int FragsDown = pbi.VFragments;
        int QIndex;
        for (QIndex = 63; QIndex >= 0 && QIndex != 0 && pbi.QThreshTable[QIndex] < pbi.ThisFrameQualityValue; --QIndex) {}
        final int FLimit = this.LoopFilterLimits[QIndex];
        if (FLimit == 0) {
            return;
        }
        this.SetupBoundingValueArray_Generic(FLimit);
        for (int j = 0; j < 3; ++j) {
            int FromFragment = 0;
            int LineLength = 0;
            int LineFragments = 0;
            switch (j) {
                case 0: {
                    FromFragment = 0;
                    FragsAcross = pbi.HFragments;
                    FragsDown = pbi.VFragments;
                    LineLength = pbi.YStride;
                    LineFragments = pbi.HFragments;
                    break;
                }
                case 1: {
                    FromFragment = pbi.YPlaneFragments;
                    FragsAcross = pbi.HFragments >> 1;
                    FragsDown = pbi.VFragments >> 1;
                    LineLength = pbi.UVStride;
                    LineFragments = pbi.HFragments / 2;
                    break;
                }
                default: {
                    FromFragment = pbi.YPlaneFragments + pbi.UVPlaneFragments;
                    FragsAcross = pbi.HFragments >> 1;
                    FragsDown = pbi.VFragments >> 1;
                    LineLength = pbi.UVStride;
                    LineFragments = pbi.HFragments / 2;
                    break;
                }
            }
            int i = FromFragment;
            if (pbi.display_fragments[i] != 0) {
                if (pbi.display_fragments[i + 1] == 0) {
                    this.FilterHoriz(pbi.LastFrameRecon, pbi.recon_pixel_index_table[i] + 6, LineLength, this.FiltBoundingValue);
                }
                if (pbi.display_fragments[i + LineFragments] == 0) {
                    this.FilterVert(pbi.LastFrameRecon, pbi.recon_pixel_index_table[i + LineFragments], LineLength, this.FiltBoundingValue);
                }
            }
            ++i;
            for (int n = 1; n < FragsAcross - 1; ++n) {
                if (pbi.display_fragments[i] != 0) {
                    final int index = pbi.recon_pixel_index_table[i];
                    this.FilterHoriz(pbi.LastFrameRecon, index - 2, LineLength, this.FiltBoundingValue);
                    if (pbi.display_fragments[i + 1] == 0) {
                        this.FilterHoriz(pbi.LastFrameRecon, index + 6, LineLength, this.FiltBoundingValue);
                    }
                    if (pbi.display_fragments[i + LineFragments] == 0) {
                        this.FilterVert(pbi.LastFrameRecon, pbi.recon_pixel_index_table[i + LineFragments], LineLength, this.FiltBoundingValue);
                    }
                }
                ++i;
            }
            if (pbi.display_fragments[i] != 0) {
                this.FilterHoriz(pbi.LastFrameRecon, pbi.recon_pixel_index_table[i] - 2, LineLength, this.FiltBoundingValue);
                if (pbi.display_fragments[i + LineFragments] == 0) {
                    this.FilterVert(pbi.LastFrameRecon, pbi.recon_pixel_index_table[i + LineFragments], LineLength, this.FiltBoundingValue);
                }
            }
            ++i;
            for (int m = 1; m < FragsDown - 1; ++m) {
                if (pbi.display_fragments[i] != 0) {
                    final int index = pbi.recon_pixel_index_table[i];
                    this.FilterVert(pbi.LastFrameRecon, index, LineLength, this.FiltBoundingValue);
                    if (pbi.display_fragments[i + 1] == 0) {
                        this.FilterHoriz(pbi.LastFrameRecon, index + 6, LineLength, this.FiltBoundingValue);
                    }
                    if (pbi.display_fragments[i + LineFragments] == 0) {
                        this.FilterVert(pbi.LastFrameRecon, pbi.recon_pixel_index_table[i + LineFragments], LineLength, this.FiltBoundingValue);
                    }
                }
                ++i;
                for (int n = 1; n < FragsAcross - 1; ++n, ++i) {
                    if (pbi.display_fragments[i] != 0) {
                        final int index = pbi.recon_pixel_index_table[i];
                        this.FilterHoriz(pbi.LastFrameRecon, index - 2, LineLength, this.FiltBoundingValue);
                        this.FilterVert(pbi.LastFrameRecon, index, LineLength, this.FiltBoundingValue);
                        if (pbi.display_fragments[i + 1] == 0) {
                            this.FilterHoriz(pbi.LastFrameRecon, index + 6, LineLength, this.FiltBoundingValue);
                        }
                        if (pbi.display_fragments[i + LineFragments] == 0) {
                            this.FilterVert(pbi.LastFrameRecon, pbi.recon_pixel_index_table[i + LineFragments], LineLength, this.FiltBoundingValue);
                        }
                    }
                }
                if (pbi.display_fragments[i] != 0) {
                    final int index = pbi.recon_pixel_index_table[i];
                    this.FilterHoriz(pbi.LastFrameRecon, index - 2, LineLength, this.FiltBoundingValue);
                    this.FilterVert(pbi.LastFrameRecon, index, LineLength, this.FiltBoundingValue);
                    if (pbi.display_fragments[i + LineFragments] == 0) {
                        this.FilterVert(pbi.LastFrameRecon, pbi.recon_pixel_index_table[i + LineFragments], LineLength, this.FiltBoundingValue);
                    }
                }
                ++i;
            }
            if (pbi.display_fragments[i] != 0) {
                final int index = pbi.recon_pixel_index_table[i];
                this.FilterVert(pbi.LastFrameRecon, index, LineLength, this.FiltBoundingValue);
                if (pbi.display_fragments[i + 1] == 0) {
                    this.FilterHoriz(pbi.LastFrameRecon, index + 6, LineLength, this.FiltBoundingValue);
                }
            }
            ++i;
            for (int n = 1; n < FragsAcross - 1; ++n, ++i) {
                if (pbi.display_fragments[i] != 0) {
                    final int index = pbi.recon_pixel_index_table[i];
                    this.FilterHoriz(pbi.LastFrameRecon, index - 2, LineLength, this.FiltBoundingValue);
                    this.FilterVert(pbi.LastFrameRecon, index, LineLength, this.FiltBoundingValue);
                    if (pbi.display_fragments[i + 1] == 0) {
                        this.FilterHoriz(pbi.LastFrameRecon, index + 6, LineLength, this.FiltBoundingValue);
                    }
                }
            }
            if (pbi.display_fragments[i] != 0) {
                final int index = pbi.recon_pixel_index_table[i];
                this.FilterHoriz(pbi.LastFrameRecon, index - 2, LineLength, this.FiltBoundingValue);
                this.FilterVert(pbi.LastFrameRecon, index, LineLength, this.FiltBoundingValue);
            }
        }
    }
    
    static {
        LoopFilterLimitValuesV1 = new byte[] { 30, 25, 20, 20, 15, 15, 14, 14, 13, 13, 12, 12, 11, 11, 10, 10, 9, 9, 8, 8, 7, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
