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
    
    private void SetupBoundingValueArray_Generic(final int n) {
        MemUtils.set(this.FiltBoundingValue, 0, 0, 512);
        for (int i = 0; i < n; ++i) {
            this.FiltBoundingValue[256 - i - n] = -n + i;
            this.FiltBoundingValue[256 - i] = -i;
            this.FiltBoundingValue[256 + i] = i;
            this.FiltBoundingValue[256 + i + n] = n - i;
        }
    }
    
    public void copyFilterTables(final Info info) {
        System.arraycopy(info.LoopFilterLimitValues, 0, this.LoopFilterLimits, 0, 64);
    }
    
    public void InitFilterTables() {
        System.arraycopy(Filter.LoopFilterLimitValuesV1, 0, this.LoopFilterLimits, 0, 64);
    }
    
    public void SetupLoopFilter(final int n) {
        this.SetupBoundingValueArray_Generic(this.LoopFilterLimits[n]);
    }
    
    private static final short clamp255(int n) {
        n -= 255;
        n = -(255 + (n >> 31 & n));
        return (short)(-(n >> 31 & n));
    }
    
    private void FilterHoriz(final short[] array, int n, final int n2, final int[] array2) {
        for (int i = 0; i < 8; ++i) {
            final int n3 = array2[256 + (array[0 + n] - array[1 + n] * 3 + array[2 + n] * 3 - array[3 + n] + 4 >> 3)];
            array[1 + n] = clamp255(array[1 + n] + n3);
            array[2 + n] = clamp255(array[2 + n] - n3);
            n += n2;
        }
    }
    
    private void FilterVert(final short[] array, int n, final int n2, final int[] array2) {
        n -= 2 * n2;
        for (int i = 0; i < 8; ++i) {
            final int n3 = array2[256 + (array[n + 0] - array[n + n2] * 3 + array[n + 2 * n2] * 3 - array[n + 3 * n2] + 4 >> 3)];
            array[n + n2] = clamp255(array[n + n2] + n3);
            array[n + 2 * n2] = clamp255(array[n + 2 * n2] - n3);
            ++n;
        }
    }
    
    public void LoopFilter(final Playback playback) {
        final int hFragments = playback.HFragments;
        final int vFragments = playback.VFragments;
        final byte b = this.LoopFilterLimits[playback.frameQIS[0]];
        if (b == 0) {
            return;
        }
        this.SetupBoundingValueArray_Generic(b);
        for (int i = 0; i < 3; ++i) {
            int yPlaneFragments = 0;
            int hFragments2 = 0;
            int vFragments2 = 0;
            int n = 0;
            int hFragments3 = 0;
            switch (i) {
                case 0: {
                    yPlaneFragments = 0;
                    hFragments2 = playback.HFragments;
                    vFragments2 = playback.VFragments;
                    n = playback.YStride;
                    hFragments3 = playback.HFragments;
                    break;
                }
                case 1: {
                    yPlaneFragments = playback.YPlaneFragments;
                    hFragments2 = playback.HFragments >> 1;
                    vFragments2 = playback.VFragments >> 1;
                    n = playback.UVStride;
                    hFragments3 = playback.HFragments / 2;
                    break;
                }
                default: {
                    yPlaneFragments = playback.YPlaneFragments + playback.UVPlaneFragments;
                    hFragments2 = playback.HFragments >> 1;
                    vFragments2 = playback.VFragments >> 1;
                    n = playback.UVStride;
                    hFragments3 = playback.HFragments / 2;
                    break;
                }
            }
            int n2 = yPlaneFragments;
            if (playback.display_fragments[n2] != 0) {
                if (playback.display_fragments[n2 + 1] == 0) {
                    this.FilterHoriz(playback.LastFrameRecon, playback.recon_pixel_index_table[n2] + 6, n, this.FiltBoundingValue);
                }
                if (playback.display_fragments[n2 + hFragments3] == 0) {
                    this.FilterVert(playback.LastFrameRecon, playback.recon_pixel_index_table[n2 + hFragments3], n, this.FiltBoundingValue);
                }
            }
            ++n2;
            for (int j = 1; j < hFragments2 - 1; ++j) {
                if (playback.display_fragments[n2] != 0) {
                    final int n3 = playback.recon_pixel_index_table[n2];
                    this.FilterHoriz(playback.LastFrameRecon, n3 - 2, n, this.FiltBoundingValue);
                    if (playback.display_fragments[n2 + 1] == 0) {
                        this.FilterHoriz(playback.LastFrameRecon, n3 + 6, n, this.FiltBoundingValue);
                    }
                    if (playback.display_fragments[n2 + hFragments3] == 0) {
                        this.FilterVert(playback.LastFrameRecon, playback.recon_pixel_index_table[n2 + hFragments3], n, this.FiltBoundingValue);
                    }
                }
                ++n2;
            }
            if (playback.display_fragments[n2] != 0) {
                this.FilterHoriz(playback.LastFrameRecon, playback.recon_pixel_index_table[n2] - 2, n, this.FiltBoundingValue);
                if (playback.display_fragments[n2 + hFragments3] == 0) {
                    this.FilterVert(playback.LastFrameRecon, playback.recon_pixel_index_table[n2 + hFragments3], n, this.FiltBoundingValue);
                }
            }
            ++n2;
            for (int k = 1; k < vFragments2 - 1; ++k) {
                if (playback.display_fragments[n2] != 0) {
                    final int n4 = playback.recon_pixel_index_table[n2];
                    this.FilterVert(playback.LastFrameRecon, n4, n, this.FiltBoundingValue);
                    if (playback.display_fragments[n2 + 1] == 0) {
                        this.FilterHoriz(playback.LastFrameRecon, n4 + 6, n, this.FiltBoundingValue);
                    }
                    if (playback.display_fragments[n2 + hFragments3] == 0) {
                        this.FilterVert(playback.LastFrameRecon, playback.recon_pixel_index_table[n2 + hFragments3], n, this.FiltBoundingValue);
                    }
                }
                ++n2;
                for (int l = 1; l < hFragments2 - 1; ++l, ++n2) {
                    if (playback.display_fragments[n2] != 0) {
                        final int n5 = playback.recon_pixel_index_table[n2];
                        this.FilterHoriz(playback.LastFrameRecon, n5 - 2, n, this.FiltBoundingValue);
                        this.FilterVert(playback.LastFrameRecon, n5, n, this.FiltBoundingValue);
                        if (playback.display_fragments[n2 + 1] == 0) {
                            this.FilterHoriz(playback.LastFrameRecon, n5 + 6, n, this.FiltBoundingValue);
                        }
                        if (playback.display_fragments[n2 + hFragments3] == 0) {
                            this.FilterVert(playback.LastFrameRecon, playback.recon_pixel_index_table[n2 + hFragments3], n, this.FiltBoundingValue);
                        }
                    }
                }
                if (playback.display_fragments[n2] != 0) {
                    final int n6 = playback.recon_pixel_index_table[n2];
                    this.FilterHoriz(playback.LastFrameRecon, n6 - 2, n, this.FiltBoundingValue);
                    this.FilterVert(playback.LastFrameRecon, n6, n, this.FiltBoundingValue);
                    if (playback.display_fragments[n2 + hFragments3] == 0) {
                        this.FilterVert(playback.LastFrameRecon, playback.recon_pixel_index_table[n2 + hFragments3], n, this.FiltBoundingValue);
                    }
                }
                ++n2;
            }
            if (playback.display_fragments[n2] != 0) {
                final int n7 = playback.recon_pixel_index_table[n2];
                this.FilterVert(playback.LastFrameRecon, n7, n, this.FiltBoundingValue);
                if (playback.display_fragments[n2 + 1] == 0) {
                    this.FilterHoriz(playback.LastFrameRecon, n7 + 6, n, this.FiltBoundingValue);
                }
            }
            ++n2;
            for (int n8 = 1; n8 < hFragments2 - 1; ++n8, ++n2) {
                if (playback.display_fragments[n2] != 0) {
                    final int n9 = playback.recon_pixel_index_table[n2];
                    this.FilterHoriz(playback.LastFrameRecon, n9 - 2, n, this.FiltBoundingValue);
                    this.FilterVert(playback.LastFrameRecon, n9, n, this.FiltBoundingValue);
                    if (playback.display_fragments[n2 + 1] == 0) {
                        this.FilterHoriz(playback.LastFrameRecon, n9 + 6, n, this.FiltBoundingValue);
                    }
                }
            }
            if (playback.display_fragments[n2] != 0) {
                final int n10 = playback.recon_pixel_index_table[n2];
                this.FilterHoriz(playback.LastFrameRecon, n10 - 2, n, this.FiltBoundingValue);
                this.FilterVert(playback.LastFrameRecon, n10, n, this.FiltBoundingValue);
            }
        }
    }
    
    static {
        LoopFilterLimitValuesV1 = new byte[] { 30, 25, 20, 20, 15, 15, 14, 14, 13, 13, 12, 12, 11, 11, 10, 10, 9, 9, 8, 8, 7, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
