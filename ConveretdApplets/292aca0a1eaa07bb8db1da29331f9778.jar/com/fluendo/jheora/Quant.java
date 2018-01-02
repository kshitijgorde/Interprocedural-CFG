// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

public class Quant
{
    private static final int MIN_DEQUANT_VAL = 2;
    private static final int IDCT_SCALE_FACTOR = 2;
    
    private static int ilog(long v) {
        int ret = 0;
        while (v != 0L) {
            ++ret;
            v >>= 1;
        }
        return ret;
    }
    
    private static int _read_qtable_range(final Info ci, final Buffer opb, final int N) {
        int qi = 0;
        opb.readB(ilog(N - 1));
        while (qi < 63) {
            int range = opb.readB(ilog(62 - qi));
            if (++range <= 0) {
                return -20;
            }
            qi += range;
            opb.readB(ilog(N - 1));
        }
        return 0;
    }
    
    public static int readQTables(final Info ci, final Buffer opb) {
        long bits = opb.readB(4);
        ++bits;
        for (int x = 0; x < 64; ++x) {
            final long value = opb.readB((int)bits);
            if (bits < 0L) {
                return -20;
            }
            ci.QThreshTable[x] = (int)value;
        }
        bits = opb.readB(4);
        ++bits;
        for (int x = 0; x < 64; ++x) {
            final long value = opb.readB((int)bits);
            if (bits < 0L) {
                return -20;
            }
            ci.DcScaleFactorTable[x] = (short)value;
        }
        int N = opb.readB(9);
        if (++N != 3) {
            return -20;
        }
        ci.qmats = new short[N * 64];
        ci.MaxQMatrixIndex = N;
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < 64; ++x) {
                final long value = opb.readB(8);
                if (bits < 0L) {
                    return -20;
                }
                ci.qmats[(y << 6) + x] = (short)value;
            }
        }
        int ret;
        if ((ret = _read_qtable_range(ci, opb, N)) < 0) {
            return ret;
        }
        int flag = opb.readB(1);
        if (flag < 0) {
            return -20;
        }
        if (flag != 0 && (ret = _read_qtable_range(ci, opb, N)) < 0) {
            return ret;
        }
        flag = opb.readB(1);
        if (flag < 0) {
            return -20;
        }
        if (flag != 0 && (ret = _read_qtable_range(ci, opb, N)) < 0) {
            return ret;
        }
        flag = opb.readB(1);
        if (flag < 0) {
            return -20;
        }
        if (flag != 0) {
            if ((ret = _read_qtable_range(ci, opb, N)) < 0) {
                return ret;
            }
        }
        else {
            flag = opb.readB(1);
            if (flag < 0) {
                return -20;
            }
            if (flag != 0) {}
        }
        flag = opb.readB(1);
        if (flag < 0) {
            return -20;
        }
        if (flag != 0) {
            if ((ret = _read_qtable_range(ci, opb, N)) < 0) {
                return ret;
            }
        }
        else {
            flag = opb.readB(1);
            if (flag < 0) {
                return -20;
            }
            if (flag != 0) {}
        }
        flag = opb.readB(1);
        if (flag < 0) {
            return -20;
        }
        if (flag != 0) {
            if ((ret = _read_qtable_range(ci, opb, N)) < 0) {
                return ret;
            }
        }
        else {
            flag = opb.readB(1);
            if (flag < 0) {
                return -20;
            }
            if (flag != 0) {}
        }
        System.arraycopy(ci.qmats, 0, ci.Y_coeffs, 0, 64);
        System.arraycopy(ci.qmats, 64, ci.UV_coeffs, 0, 64);
        System.arraycopy(ci.qmats, 128, ci.Inter_coeffs, 0, 64);
        return 0;
    }
    
    static void BuildQuantIndex_Generic(final Playback pbi) {
        for (int i = 0; i < 64; ++i) {
            final int j = Constants.dequant_index[i];
            pbi.quant_index[j] = i;
        }
    }
    
    static void init_dequantizer(final Playback pbi, final int scale_factor, final byte QIndex) {
        final short[] Inter_coeffs = pbi.Inter_coeffs;
        final short[] Y_coeffs = pbi.Y_coeffs;
        final short[] UV_coeffs = pbi.UV_coeffs;
        final short[] DcScaleFactorTable = pbi.DcScaleFactorTable;
        final short[] UVDcScaleFactorTable = pbi.DcScaleFactorTable;
        BuildQuantIndex_Generic(pbi);
        for (int i = 0; i < 64; ++i) {
            final int j = pbi.quant_index[i];
            pbi.dequant_Y_coeffs[j] = Y_coeffs[i];
            pbi.dequant_Inter_coeffs[j] = Inter_coeffs[i];
            pbi.dequant_UV_coeffs[j] = UV_coeffs[i];
            pbi.dequant_InterUV_coeffs[j] = Inter_coeffs[i];
        }
        pbi.dequant_Y_coeffs[0] = (short)(DcScaleFactorTable[QIndex] * pbi.dequant_Y_coeffs[0] / 100);
        if (pbi.dequant_Y_coeffs[0] < 4) {
            pbi.dequant_Y_coeffs[0] = 4;
        }
        pbi.dequant_Y_coeffs[0] <<= 2;
        pbi.dequant_UV_coeffs[0] = (short)(UVDcScaleFactorTable[QIndex] * pbi.dequant_UV_coeffs[0] / 100);
        if (pbi.dequant_UV_coeffs[0] < 4) {
            pbi.dequant_UV_coeffs[0] = 4;
        }
        pbi.dequant_UV_coeffs[0] <<= 2;
        pbi.dequant_Inter_coeffs[0] = (short)(DcScaleFactorTable[QIndex] * pbi.dequant_Inter_coeffs[0] / 100);
        if (pbi.dequant_Inter_coeffs[0] < 8) {
            pbi.dequant_Inter_coeffs[0] = 8;
        }
        pbi.dequant_Inter_coeffs[0] <<= 2;
        pbi.dequant_InterUV_coeffs[0] = (short)(UVDcScaleFactorTable[QIndex] * pbi.dequant_InterUV_coeffs[0] / 100);
        if (pbi.dequant_InterUV_coeffs[0] < 8) {
            pbi.dequant_InterUV_coeffs[0] = 8;
        }
        pbi.dequant_InterUV_coeffs[0] <<= 2;
        for (int i = 1; i < 64; ++i) {
            pbi.dequant_Y_coeffs[i] = (short)(scale_factor * pbi.dequant_Y_coeffs[i] / 100);
            if (pbi.dequant_Y_coeffs[i] < 2) {
                pbi.dequant_Y_coeffs[i] = 2;
            }
            pbi.dequant_Y_coeffs[i] <<= 2;
            pbi.dequant_UV_coeffs[i] = (short)(scale_factor * pbi.dequant_UV_coeffs[i] / 100);
            if (pbi.dequant_UV_coeffs[i] < 2) {
                pbi.dequant_UV_coeffs[i] = 2;
            }
            pbi.dequant_UV_coeffs[i] <<= 2;
            pbi.dequant_Inter_coeffs[i] = (short)(scale_factor * pbi.dequant_Inter_coeffs[i] / 100);
            if (pbi.dequant_Inter_coeffs[i] < 4) {
                pbi.dequant_Inter_coeffs[i] = 4;
            }
            pbi.dequant_Inter_coeffs[i] <<= 2;
            pbi.dequant_InterUV_coeffs[i] = (short)(scale_factor * pbi.dequant_InterUV_coeffs[i] / 100);
            if (pbi.dequant_InterUV_coeffs[i] < 4) {
                pbi.dequant_InterUV_coeffs[i] = 4;
            }
            pbi.dequant_InterUV_coeffs[i] <<= 2;
        }
    }
    
    public static void UpdateQ(final Playback pbi, final int NewQ) {
        int qscale = NewQ;
        if (qscale < pbi.QThreshTable[63]) {
            qscale = pbi.QThreshTable[63];
        }
        else if (qscale > pbi.QThreshTable[0]) {
            qscale = pbi.QThreshTable[0];
        }
        pbi.FrameQIndex = 63;
        while (pbi.FrameQIndex >= 0 && pbi.FrameQIndex != 0 && pbi.QThreshTable[pbi.FrameQIndex] < NewQ) {
            --pbi.FrameQIndex;
        }
        init_dequantizer(pbi, qscale, (byte)pbi.FrameQIndex);
    }
}
