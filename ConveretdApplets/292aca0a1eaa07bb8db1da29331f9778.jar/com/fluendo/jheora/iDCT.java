// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public final class iDCT
{
    private static final int IdctAdjustBeforeShift = 8;
    private static final int xC1S7 = 64277;
    private static final int xC2S6 = 60547;
    private static final int xC3S5 = 54491;
    private static final int xC4S4 = 46341;
    private static final int xC5S3 = 36410;
    private static final int xC6S2 = 25080;
    private static final int xC7S1 = 12785;
    private int[] ip;
    
    public iDCT() {
        this.ip = new int[64];
    }
    
    private final void dequant_slow(final short[] dequant_coeffs, final short[] quantized_list, final int[] DCT_block) {
        for (int i = 0; i < 64; ++i) {
            DCT_block[Constants.dequant_index[i]] = quantized_list[i] * dequant_coeffs[i];
        }
    }
    
    public final void IDctSlow(final short[] InputData, final short[] QuantMatrix, final short[] OutputData) {
        this.dequant_slow(QuantMatrix, InputData, this.ip);
        for (int loop = 0, off = 0; loop < 8; ++loop, off += 8) {
            if ((this.ip[0 + off] | this.ip[1 + off] | this.ip[2 + off] | this.ip[3 + off] | this.ip[4 + off] | this.ip[5 + off] | this.ip[6 + off] | this.ip[7 + off]) != 0x0) {
                int t1 = 64277 * this.ip[1 + off] >> 16;
                int t2 = 12785 * this.ip[7 + off] >> 16;
                final int _A = t1 + t2;
                t1 = 12785 * this.ip[1 + off] >> 16;
                t2 = 64277 * this.ip[7 + off] >> 16;
                final int _B = t1 - t2;
                t1 = 54491 * this.ip[3 + off] >> 16;
                t2 = 36410 * this.ip[5 + off] >> 16;
                final int _C = t1 + t2;
                t1 = 54491 * this.ip[5 + off] >> 16;
                t2 = 36410 * this.ip[3 + off] >> 16;
                final int _D = t1 - t2;
                final int _Ad = 46341 * (short)(_A - _C) >> 16;
                final int _Bd = 46341 * (short)(_B - _D) >> 16;
                final int _Cd = _A + _C;
                final int _Dd = _B + _D;
                final int _E = 46341 * (short)(this.ip[0 + off] + this.ip[4 + off]) >> 16;
                final int _F = 46341 * (short)(this.ip[0 + off] - this.ip[4 + off]) >> 16;
                t1 = 60547 * this.ip[2 + off] >> 16;
                t2 = 25080 * this.ip[6 + off] >> 16;
                final int _G = t1 + t2;
                t1 = 25080 * this.ip[2 + off] >> 16;
                t2 = 60547 * this.ip[6 + off] >> 16;
                final int _H = t1 - t2;
                final int _Ed = _E - _G;
                final int _Gd = _E + _G;
                final int _Add = _F + _Ad;
                final int _Bdd = _Bd - _H;
                final int _Fd = _F - _Ad;
                final int _Hd = _Bd + _H;
                this.ip[0 + off] = (short)(_Gd + _Cd >> 0);
                this.ip[7 + off] = (short)(_Gd - _Cd >> 0);
                this.ip[1 + off] = (short)(_Add + _Hd >> 0);
                this.ip[2 + off] = (short)(_Add - _Hd >> 0);
                this.ip[3 + off] = (short)(_Ed + _Dd >> 0);
                this.ip[4 + off] = (short)(_Ed - _Dd >> 0);
                this.ip[5 + off] = (short)(_Fd + _Bdd >> 0);
                this.ip[6 + off] = (short)(_Fd - _Bdd >> 0);
            }
        }
        for (int loop = 0, off = 0; loop < 8; ++loop, ++off) {
            if ((this.ip[0 + off] | this.ip[8 + off] | this.ip[16 + off] | this.ip[24 + off] | this.ip[32 + off] | this.ip[40 + off] | this.ip[48 + off] | this.ip[56 + off]) != 0x0) {
                int t1 = 64277 * this.ip[8 + off] >> 16;
                int t2 = 12785 * this.ip[56 + off] >> 16;
                final int _A = t1 + t2;
                t1 = 12785 * this.ip[8 + off] >> 16;
                t2 = 64277 * this.ip[56 + off] >> 16;
                final int _B = t1 - t2;
                t1 = 54491 * this.ip[24 + off] >> 16;
                t2 = 36410 * this.ip[40 + off] >> 16;
                final int _C = t1 + t2;
                t1 = 54491 * this.ip[40 + off] >> 16;
                t2 = 36410 * this.ip[24 + off] >> 16;
                final int _D = t1 - t2;
                final int _Ad = 46341 * (short)(_A - _C) >> 16;
                final int _Bd = 46341 * (short)(_B - _D) >> 16;
                final int _Cd = _A + _C;
                final int _Dd = _B + _D;
                final int _E = 46341 * (short)(this.ip[0 + off] + this.ip[32 + off]) >> 16;
                final int _F = 46341 * (short)(this.ip[0 + off] - this.ip[32 + off]) >> 16;
                t1 = 60547 * this.ip[16 + off] >> 16;
                t2 = 25080 * this.ip[48 + off] >> 16;
                final int _G = t1 + t2;
                t1 = 25080 * this.ip[16 + off] >> 16;
                t2 = 60547 * this.ip[48 + off] >> 16;
                final int _H = t1 - t2;
                int _Ed = _E - _G;
                int _Gd = _E + _G;
                int _Add = _F + _Ad;
                final int _Bdd = _Bd - _H;
                int _Fd = _F - _Ad;
                final int _Hd = _Bd + _H;
                _Gd += 8;
                _Add += 8;
                _Ed += 8;
                _Fd += 8;
                OutputData[0 + off] = (short)(_Gd + _Cd >> 4);
                OutputData[56 + off] = (short)(_Gd - _Cd >> 4);
                OutputData[8 + off] = (short)(_Add + _Hd >> 4);
                OutputData[16 + off] = (short)(_Add - _Hd >> 4);
                OutputData[24 + off] = (short)(_Ed + _Dd >> 4);
                OutputData[32 + off] = (short)(_Ed - _Dd >> 4);
                OutputData[40 + off] = (short)(_Fd + _Bdd >> 4);
                OutputData[48 + off] = (short)(_Fd - _Bdd >> 4);
            }
            else {
                OutputData[56 + off] = (OutputData[0 + off] = 0);
                OutputData[16 + off] = (OutputData[8 + off] = 0);
                OutputData[32 + off] = (OutputData[24 + off] = 0);
                OutputData[48 + off] = (OutputData[40 + off] = 0);
            }
        }
    }
    
    private final void dequant_slow10(final short[] dequant_coeffs, final short[] quantized_list, final int[] DCT_block) {
        for (int i = 0; i < 32; ++i) {
            DCT_block[i] = 0;
        }
        for (int i = 0; i < 10; ++i) {
            DCT_block[Constants.dequant_index[i]] = quantized_list[i] * dequant_coeffs[i];
        }
    }
    
    public final void IDct10(final short[] InputData, final short[] QuantMatrix, final short[] OutputData) {
        this.dequant_slow10(QuantMatrix, InputData, this.ip);
        for (int loop = 0, off = 0; loop < 4; ++loop, off += 8) {
            if ((this.ip[0 + off] | this.ip[1 + off] | this.ip[2 + off] | this.ip[3 + off]) != 0x0) {
                final int _A = 64277 * this.ip[1 + off] >> 16;
                final int _B = 12785 * this.ip[1 + off] >> 16;
                final int _C = 54491 * this.ip[3 + off] >> 16;
                final int _D = 36410 * this.ip[3 + off] >> 16;
                final int _Ad = 46341 * (short)(_A - _C) >> 16;
                final int _Bd = 46341 * (short)(_B - _D) >> 16;
                final int _Cd = _A + _C;
                final int _Dd = _B + _D;
                final int _F;
                final int _E = _F = 46341 * this.ip[0 + off] >> 16;
                final int _G = 60547 * this.ip[2 + off] >> 16;
                final int _H = 25080 * this.ip[2 + off] >> 16;
                final int _Ed = _E - _G;
                final int _Gd = _E + _G;
                final int _Add = _F + _Ad;
                final int _Bdd = _Bd - _H;
                final int _Fd = _F - _Ad;
                final int _Hd = _Bd + _H;
                this.ip[0 + off] = (short)(_Gd + _Cd >> 0);
                this.ip[7 + off] = (short)(_Gd - _Cd >> 0);
                this.ip[1 + off] = (short)(_Add + _Hd >> 0);
                this.ip[2 + off] = (short)(_Add - _Hd >> 0);
                this.ip[3 + off] = (short)(_Ed + _Dd >> 0);
                this.ip[4 + off] = (short)(_Ed - _Dd >> 0);
                this.ip[5 + off] = (short)(_Fd + _Bdd >> 0);
                this.ip[6 + off] = (short)(_Fd - _Bdd >> 0);
            }
        }
        for (int loop = 0, off = 0; loop < 8; ++loop, ++off) {
            if ((this.ip[0 + off] | this.ip[8 + off] | this.ip[16 + off] | this.ip[24 + off]) != 0x0) {
                final int _A = 64277 * this.ip[8 + off] >> 16;
                final int _B = 12785 * this.ip[8 + off] >> 16;
                final int _C = 54491 * this.ip[24 + off] >> 16;
                final int _D = 36410 * this.ip[24 + off] >> 16;
                final int _Ad = 46341 * (short)(_A - _C) >> 16;
                final int _Bd = 46341 * (short)(_B - _D) >> 16;
                final int _Cd = _A + _C;
                final int _Dd = _B + _D;
                final int _F;
                final int _E = _F = 46341 * this.ip[0 + off] >> 16;
                final int _G = 60547 * this.ip[16 + off] >> 16;
                final int _H = 25080 * this.ip[16 + off] >> 16;
                int _Ed = _E - _G;
                int _Gd = _E + _G;
                int _Add = _F + _Ad;
                final int _Bdd = _Bd - _H;
                int _Fd = _F - _Ad;
                final int _Hd = _Bd + _H;
                _Gd += 8;
                _Add += 8;
                _Ed += 8;
                _Fd += 8;
                OutputData[0 + off] = (short)(_Gd + _Cd >> 4);
                OutputData[56 + off] = (short)(_Gd - _Cd >> 4);
                OutputData[8 + off] = (short)(_Add + _Hd >> 4);
                OutputData[16 + off] = (short)(_Add - _Hd >> 4);
                OutputData[24 + off] = (short)(_Ed + _Dd >> 4);
                OutputData[32 + off] = (short)(_Ed - _Dd >> 4);
                OutputData[40 + off] = (short)(_Fd + _Bdd >> 4);
                OutputData[48 + off] = (short)(_Fd - _Bdd >> 4);
            }
            else {
                OutputData[56 + off] = (OutputData[0 + off] = 0);
                OutputData[16 + off] = (OutputData[8 + off] = 0);
                OutputData[32 + off] = (OutputData[24 + off] = 0);
                OutputData[48 + off] = (OutputData[40 + off] = 0);
            }
        }
    }
    
    public final void IDct1(final short[] InputData, final short[] QuantMatrix, final short[] OutputData) {
        final short OutD = (short)(InputData[0] * QuantMatrix[0] + 15 >> 5);
        for (int loop = 0; loop < 64; ++loop) {
            OutputData[loop] = OutD;
        }
    }
}
