// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

public class Quant
{
    private static final int MIN_LEGAL_QUANT_ENTRY = 8;
    private static final int MIN_DEQUANT_VAL = 2;
    private static final int IDCT_SCALE_FACTOR = 2;
    private static final int OLD_SCHEME = 1;
    
    private static int ilog(long n) {
        int n2 = 0;
        while (n != 0L) {
            ++n2;
            n >>= 1;
        }
        return n2;
    }
    
    private static int _read_qtable_range(final Info info, final Buffer buffer, final int n) {
        int i = 0;
        buffer.readB(ilog(n - 1));
        while (i < 63) {
            int b = buffer.readB(ilog(62 - i));
            if (++b <= 0) {
                return -20;
            }
            i += b;
            buffer.readB(ilog(n - 1));
        }
        return 0;
    }
    
    public static int readQTables(final Info info, final Buffer buffer) {
        final long n = buffer.readB(4) + 1L;
        for (int i = 0; i < 64; ++i) {
            final long n2 = buffer.readB((int)n);
            if (n < 0L) {
                return -20;
            }
            info.QThreshTable[i] = (int)n2;
        }
        final long n3 = buffer.readB(4) + 1L;
        for (int j = 0; j < 64; ++j) {
            final long n4 = buffer.readB((int)n3);
            if (n3 < 0L) {
                return -20;
            }
            info.DcScaleFactorTable[j] = (short)n4;
        }
        int b = buffer.readB(9);
        if (++b != 3) {
            return -20;
        }
        info.qmats = new short[b * 64];
        info.MaxQMatrixIndex = b;
        for (int k = 0; k < b; ++k) {
            for (int l = 0; l < 64; ++l) {
                final long n5 = buffer.readB(8);
                if (n3 < 0L) {
                    return -20;
                }
                info.qmats[(k << 6) + l] = (short)n5;
            }
        }
        final int read_qtable_range;
        if ((read_qtable_range = _read_qtable_range(info, buffer, b)) < 0) {
            return read_qtable_range;
        }
        final int b2 = buffer.readB(1);
        if (b2 < 0) {
            return -20;
        }
        final int read_qtable_range2;
        if (b2 != 0 && (read_qtable_range2 = _read_qtable_range(info, buffer, b)) < 0) {
            return read_qtable_range2;
        }
        final int b3 = buffer.readB(1);
        if (b3 < 0) {
            return -20;
        }
        final int read_qtable_range3;
        if (b3 != 0 && (read_qtable_range3 = _read_qtable_range(info, buffer, b)) < 0) {
            return read_qtable_range3;
        }
        final int b4 = buffer.readB(1);
        if (b4 < 0) {
            return -20;
        }
        if (b4 != 0) {
            final int read_qtable_range4;
            if ((read_qtable_range4 = _read_qtable_range(info, buffer, b)) < 0) {
                return read_qtable_range4;
            }
        }
        else {
            final int b5 = buffer.readB(1);
            if (b5 < 0) {
                return -20;
            }
            if (b5 != 0) {}
        }
        final int b6 = buffer.readB(1);
        if (b6 < 0) {
            return -20;
        }
        if (b6 != 0) {
            final int read_qtable_range5;
            if ((read_qtable_range5 = _read_qtable_range(info, buffer, b)) < 0) {
                return read_qtable_range5;
            }
        }
        else {
            final int b7 = buffer.readB(1);
            if (b7 < 0) {
                return -20;
            }
            if (b7 != 0) {}
        }
        final int b8 = buffer.readB(1);
        if (b8 < 0) {
            return -20;
        }
        if (b8 != 0) {
            final int read_qtable_range6;
            if ((read_qtable_range6 = _read_qtable_range(info, buffer, b)) < 0) {
                return read_qtable_range6;
            }
        }
        else {
            final int b9 = buffer.readB(1);
            if (b9 < 0) {
                return -20;
            }
            if (b9 != 0) {}
        }
        System.arraycopy(info.qmats, 0, info.Y_coeffs, 0, 64);
        System.arraycopy(info.qmats, 64, info.UV_coeffs, 0, 64);
        System.arraycopy(info.qmats, 128, info.Inter_coeffs, 0, 64);
        return 0;
    }
    
    static void BuildQuantIndex_Generic(final Playback playback) {
        for (int i = 0; i < 64; ++i) {
            playback.quant_index[Constants.dequant_index[i]] = i;
        }
    }
    
    static void init_dequantizer(final Playback playback, final int n, final byte b) {
        final short[] inter_coeffs = playback.Inter_coeffs;
        final short[] y_coeffs = playback.Y_coeffs;
        final short[] uv_coeffs = playback.UV_coeffs;
        final short[] dcScaleFactorTable = playback.DcScaleFactorTable;
        final short[] dcScaleFactorTable2 = playback.DcScaleFactorTable;
        BuildQuantIndex_Generic(playback);
        for (int i = 0; i < 64; ++i) {
            final int n2 = playback.quant_index[i];
            playback.dequant_Y_coeffs[n2] = y_coeffs[i];
            playback.dequant_Inter_coeffs[n2] = inter_coeffs[i];
            playback.dequant_UV_coeffs[n2] = uv_coeffs[i];
            playback.dequant_InterUV_coeffs[n2] = inter_coeffs[i];
        }
        playback.dequant_Y_coeffs[0] = (short)(dcScaleFactorTable[b] * playback.dequant_Y_coeffs[0] / 100);
        if (playback.dequant_Y_coeffs[0] < 4) {
            playback.dequant_Y_coeffs[0] = 4;
        }
        playback.dequant_Y_coeffs[0] <<= 2;
        playback.dequant_UV_coeffs[0] = (short)(dcScaleFactorTable2[b] * playback.dequant_UV_coeffs[0] / 100);
        if (playback.dequant_UV_coeffs[0] < 4) {
            playback.dequant_UV_coeffs[0] = 4;
        }
        playback.dequant_UV_coeffs[0] <<= 2;
        playback.dequant_Inter_coeffs[0] = (short)(dcScaleFactorTable[b] * playback.dequant_Inter_coeffs[0] / 100);
        if (playback.dequant_Inter_coeffs[0] < 8) {
            playback.dequant_Inter_coeffs[0] = 8;
        }
        playback.dequant_Inter_coeffs[0] <<= 2;
        playback.dequant_InterUV_coeffs[0] = (short)(dcScaleFactorTable2[b] * playback.dequant_InterUV_coeffs[0] / 100);
        if (playback.dequant_InterUV_coeffs[0] < 8) {
            playback.dequant_InterUV_coeffs[0] = 8;
        }
        playback.dequant_InterUV_coeffs[0] <<= 2;
        for (int j = 1; j < 64; ++j) {
            playback.dequant_Y_coeffs[j] = (short)(n * playback.dequant_Y_coeffs[j] / 100);
            if (playback.dequant_Y_coeffs[j] < 2) {
                playback.dequant_Y_coeffs[j] = 2;
            }
            playback.dequant_Y_coeffs[j] <<= 2;
            playback.dequant_UV_coeffs[j] = (short)(n * playback.dequant_UV_coeffs[j] / 100);
            if (playback.dequant_UV_coeffs[j] < 2) {
                playback.dequant_UV_coeffs[j] = 2;
            }
            playback.dequant_UV_coeffs[j] <<= 2;
            playback.dequant_Inter_coeffs[j] = (short)(n * playback.dequant_Inter_coeffs[j] / 100);
            if (playback.dequant_Inter_coeffs[j] < 4) {
                playback.dequant_Inter_coeffs[j] = 4;
            }
            playback.dequant_Inter_coeffs[j] <<= 2;
            playback.dequant_InterUV_coeffs[j] = (short)(n * playback.dequant_InterUV_coeffs[j] / 100);
            if (playback.dequant_InterUV_coeffs[j] < 4) {
                playback.dequant_InterUV_coeffs[j] = 4;
            }
            playback.dequant_InterUV_coeffs[j] <<= 2;
        }
    }
    
    public static void UpdateQ(final Playback playback, final int n) {
        int n2 = n;
        if (n2 < playback.QThreshTable[63]) {
            n2 = playback.QThreshTable[63];
        }
        else if (n2 > playback.QThreshTable[0]) {
            n2 = playback.QThreshTable[0];
        }
        playback.FrameQIndex = 63;
        while (playback.FrameQIndex >= 0 && playback.FrameQIndex != 0 && playback.QThreshTable[playback.FrameQIndex] < n) {
            --playback.FrameQIndex;
        }
        init_dequantizer(playback, n2, (byte)playback.FrameQIndex);
    }
}
