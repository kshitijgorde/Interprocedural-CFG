// 
// Decompiled by Procyon v0.5.30
// 

package com.privylink.trustfield.iase.client.crypto;

import java.util.Date;

public class TDES
{
    private int[] _$21064;
    private int[] _$21065;
    private int[] _$21066;
    private byte[] _$21067;
    private byte[] _$21068;
    private int _$21069;
    private int _$21070;
    private int _$21071;
    private short _$21072;
    private short _$21073;
    private byte[] _$21074;
    private final byte[] _$21075;
    private final int[] _$21076;
    private final int[] _$21077;
    private final byte[] _$21078;
    private final byte[] _$21079;
    private final int[] _$21080;
    private final int[] _$21081;
    private final int[] _$21082;
    private final int[] _$21083;
    private final int[] _$21084;
    private final int[] _$21085;
    private final int[] _$21086;
    private final int[] _$21087;
    
    public TDES() {
        this._$21075 = new byte[] { -128, 64, 32, 16, 8, 4, 2, 1 };
        this._$21076 = new int[] { 8388608, 4194304, 2097152, 1048576, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
        this._$21077 = new int[] { 56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3 };
        this._$21078 = new byte[] { 1, 2, 4, 6, 8, 10, 12, 14, 15, 17, 19, 21, 23, 25, 27, 28 };
        this._$21079 = new byte[] { 13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19, 12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31 };
        this._$21080 = new int[] { 16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, 1028, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, 65540, 16777220, 16777220, 65540, 0, 1028, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, 65540, 16842752, 16778244, 16777220, 1028, 66564, 16843776, 1028, 16778240, 16778240, 0, 65540, 66560, 0, 16842756 };
        this._$21081 = new int[] { -2146402272, -2147450880, 32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, Integer.MIN_VALUE, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, Integer.MIN_VALUE, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, Integer.MIN_VALUE, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880, 32800, Integer.MIN_VALUE, -2146435040, -2146402272, 1081344 };
        this._$21082 = new int[] { 520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, 134217728, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, 134217728, 134349312, 134217728, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, 134217728, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800, 131592, 8, 134348808, 131584 };
        this._$21083 = new int[] { 8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, 129, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, 129, 8388736, 8388609, 8396800, 8396929, 129, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928 };
        this._$21084 = new int[] { 256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688, 1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080 };
        this._$21085 = new int[] { 536870928, 541065216, 16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, 536870912, 16400, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, 16400, 4210688, 541081600, 536870912, 536887296, 16, 541065232, 4210688, 541081616, 4194304, 16400, 536870928, 4194304, 536887296, 536870912, 16400, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, 536870912, 4194320, 536887312 };
        this._$21086 = new int[] { 2097152, 69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, 67108864, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, 67108864, 2099200, 67108864, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, 67108864, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154 };
        this._$21087 = new int[] { 268439616, 4096, 262144, 268701760, 268435456, 268439616, 64, 268435456, 262208, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, 262208, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, 262208, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, 262208, 268435456, 268701696 };
        this._$21069 = 0;
        this._$21070 = 1;
        this._$21071 = 8;
        this._$21072 = 0;
        this._$21073 = 1;
    }
    
    private void _$21088(final byte[] key, final short edf) {
        final byte[] pc1m = new byte[56];
        final byte[] pcr = new byte[56];
        final int[] kn = new int[32];
        for (int j = 0; j < 56; ++j) {
            final int l = this._$21077[j];
            final int m = l & 0x7;
            if ((key[l >>> 3] & this._$21075[m]) != 0x0) {
                pc1m[j] = 1;
            }
            else {
                pc1m[j] = 0;
            }
        }
        for (int i = 0; i < 16; ++i) {
            int m;
            if (edf == this._$21073) {
                m = 15 - i << 1;
            }
            else {
                m = i << 1;
            }
            final int n = m + 1;
            kn[m] = (kn[n] = 0);
            for (int j = 0; j < 28; ++j) {
                final int l = j + this._$21078[i];
                if (l < 28) {
                    pcr[j] = pc1m[l];
                }
                else {
                    pcr[j] = pc1m[l - 28];
                }
            }
            for (int j = 28; j < 56; ++j) {
                final int l = j + this._$21078[i];
                if (l < 56) {
                    pcr[j] = pc1m[l];
                }
                else {
                    pcr[j] = pc1m[l - 28];
                }
            }
            for (int j = 0; j < 24; ++j) {
                if (pcr[this._$21079[j]] != 0) {
                    final int[] array = kn;
                    final int n2 = m;
                    array[n2] |= this._$21076[j];
                }
                if (pcr[this._$21079[j + 24]] != 0) {
                    final int[] array2 = kn;
                    final int n3 = n;
                    array2[n3] |= this._$21076[j];
                }
            }
        }
        this._$21095(kn);
    }
    
    private void _$21096(final int[] from) {
        int i = 0;
        this._$21064 = new int[32];
        while (i < 32) {
            this._$21064[i] = from[i++];
        }
    }
    
    private final void _$21095(final int[] raw) {
        final int[] dough = new int[32];
        for (int i = 0; i < 32; i += 2) {
            dough[i] = (raw[i] & 0xFC0000) << 6;
            final int[] array = dough;
            final int n = i;
            array[n] |= (raw[i] & 0xFC0) << 10;
            final int[] array2 = dough;
            final int n2 = i;
            array2[n2] |= (raw[i + 1] & 0xFC0000) >>> 10;
            final int[] array3 = dough;
            final int n3 = i;
            array3[n3] |= (raw[i + 1] & 0xFC0) >>> 6;
            dough[i + 1] = (raw[i] & 0x3F000) << 12;
            final int[] array4 = dough;
            final int n4 = i + 1;
            array4[n4] |= (raw[i] & 0x3F) << 16;
            final int[] array5 = dough;
            final int n5 = i + 1;
            array5[n5] |= (raw[i + 1] & 0x3F000) >>> 4;
            final int[] array6 = dough;
            final int n6 = i + 1;
            array6[n6] |= (raw[i + 1] & 0x3F);
        }
        this._$21096(dough);
    }
    
    private void _$21100(final int[] into) {
        for (int i = 0; i < into.length; into[i] = this._$21064[i++]) {}
    }
    
    private void _$21102(final short mode) {
        final short revmod = (mode == this._$21072) ? this._$21073 : this._$21072;
        this._$21088(this._$21068, revmod);
        this._$21100(this._$21065 = new int[32]);
        this._$21088(this._$21067, mode);
        this._$21100(this._$21066 = new int[32]);
    }
    
    private void _$21105(final byte[] from, final byte[] into) {
        final int[] work = new int[2];
        this._$21107(from, work);
        this._$21108(work, this._$21064);
        this._$21108(work, this._$21065);
        this._$21108(work, this._$21066);
        this._$21109(work, into);
    }
    
    private void _$21110(final byte[] from, final byte[] into) {
        final int[] work = new int[2];
        this._$21107(from, work);
        this._$21108(work, this._$21064);
        this._$21109(work, into);
    }
    
    private void _$21107(final byte[] outof, final int[] into) {
        into[0] = outof[0] << 24;
        final int n = 0;
        into[n] |= (outof[1] & 0xFF) << 16;
        final int n2 = 0;
        into[n2] |= (outof[2] & 0xFF) << 8;
        final int n3 = 0;
        into[n3] |= (outof[3] & 0xFF);
        into[1] = outof[4] << 24;
        final int n4 = 1;
        into[n4] |= (outof[5] & 0xFF) << 16;
        final int n5 = 1;
        into[n5] |= (outof[6] & 0xFF) << 8;
        final int n6 = 1;
        into[n6] |= (outof[7] & 0xFF);
    }
    
    private void _$21109(final int[] outof, final byte[] into) {
        into[0] = (byte)(outof[0] >>> 24);
        into[1] = (byte)(outof[0] >>> 16);
        into[2] = (byte)(outof[0] >>> 8);
        into[3] = (byte)outof[0];
        into[4] = (byte)(outof[1] >>> 24);
        into[5] = (byte)(outof[1] >>> 16);
        into[6] = (byte)(outof[1] >>> 8);
        into[7] = (byte)outof[1];
    }
    
    private void _$21108(final int[] block, final int[] keys) {
        int leftt = block[0];
        int right = block[1];
        int work = (leftt >>> 4 ^ right) & 0xF0F0F0F;
        right ^= work;
        leftt ^= work << 4;
        work = ((leftt >>> 16 ^ right) & 0xFFFF);
        right ^= work;
        leftt ^= work << 16;
        work = ((right >>> 2 ^ leftt) & 0x33333333);
        leftt ^= work;
        right ^= work << 2;
        work = ((right >>> 8 ^ leftt) & 0xFF00FF);
        leftt ^= work;
        right ^= work << 8;
        right = ((right << 1 | (right >>> 31 & 0x1)) & -1);
        work = ((leftt ^ right) & 0xAAAAAAAA);
        leftt ^= work;
        right ^= work;
        leftt = ((leftt << 1 | (leftt >>> 31 & 0x1)) & -1);
        int index = 0;
        for (int round = 0; round < 8; ++round) {
            work = (right << 28 | right >>> 4);
            work ^= keys[index++];
            int fval = this._$21086[work & 0x3F];
            fval |= this._$21084[work >>> 8 & 0x3F];
            fval |= this._$21082[work >>> 16 & 0x3F];
            fval |= this._$21080[work >>> 24 & 0x3F];
            work = (right ^ keys[index++]);
            fval |= this._$21087[work & 0x3F];
            fval |= this._$21085[work >>> 8 & 0x3F];
            fval |= this._$21083[work >>> 16 & 0x3F];
            fval |= this._$21081[work >>> 24 & 0x3F];
            leftt ^= fval;
            work = (leftt << 28 | leftt >>> 4);
            work ^= keys[index++];
            fval = this._$21086[work & 0x3F];
            fval |= this._$21084[work >>> 8 & 0x3F];
            fval |= this._$21082[work >>> 16 & 0x3F];
            fval |= this._$21080[work >>> 24 & 0x3F];
            work = (leftt ^ keys[index++]);
            fval |= this._$21087[work & 0x3F];
            fval |= this._$21085[work >>> 8 & 0x3F];
            fval |= this._$21083[work >>> 16 & 0x3F];
            fval |= this._$21081[work >>> 24 & 0x3F];
            right ^= fval;
        }
        right = (right << 31 | right >>> 1);
        work = ((leftt ^ right) & 0xAAAAAAAA);
        leftt ^= work;
        right ^= work;
        leftt = (leftt << 31 | leftt >>> 1);
        work = ((leftt >>> 8 ^ right) & 0xFF00FF);
        right ^= work;
        leftt ^= work << 8;
        work = ((leftt >>> 2 ^ right) & 0x33333333);
        right ^= work;
        leftt ^= work << 2;
        work = ((right >>> 16 ^ leftt) & 0xFFFF);
        leftt ^= work;
        right ^= work << 16;
        work = ((right >>> 4 ^ leftt) & 0xF0F0F0F);
        leftt ^= work;
        right ^= work << 4;
        block[0] = right;
        block[1] = leftt;
    }
    
    public final void DES3CBCInit(final byte[] inkey, final byte[] ivec) {
        this._$21067 = new byte[8];
        this._$21068 = new byte[8];
        for (int i = 0; i < this._$21067.length; ++i) {
            this._$21067[i] = inkey[i];
        }
        for (int i = 0; i < this._$21068.length; ++i) {
            this._$21068[i] = inkey[i + 8];
        }
        this._$21074 = new byte[8];
        for (int i = 0; i < this._$21074.length; ++i) {
            this._$21074[i] = ivec[i];
        }
    }
    
    public final void DESCBCInit(final byte[] inkey, final byte[] ivec) {
        this._$21067 = new byte[8];
        for (int i = 0; i < this._$21067.length; ++i) {
            this._$21067[i] = inkey[i];
        }
        this._$21074 = new byte[8];
        for (int i = 0; i < this._$21074.length; ++i) {
            this._$21074[i] = ivec[i];
        }
    }
    
    public final int DES3CBCEncrypt(final byte[] plaintxt, final byte[] ciphertxt) {
        final byte[] tmpblk = new byte[8];
        final byte[] ivtemp = new byte[8];
        if (plaintxt.length % this._$21071 != 0) {
            return this._$21070;
        }
        int inptr = 0;
        int outptr = 0;
        int inblk = plaintxt.length / this._$21071;
        this._$21102(this._$21072);
        for (int i = 0; i < 8; ++i) {
            ivtemp[i] = this._$21074[i];
        }
        while (inblk != 0) {
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] = (byte)(ivtemp[i] ^ plaintxt[inptr++]);
            }
            this._$21105(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                ciphertxt[outptr++] = (ivtemp[i] = tmpblk[i]);
            }
            --inblk;
        }
        return this._$21069;
    }
    
    public final int DESCBCEncrypt(final byte[] plaintxt, final byte[] ciphertxt) {
        final byte[] tmpblk = new byte[8];
        final byte[] ivtemp = new byte[8];
        if (plaintxt.length % this._$21071 != 0) {
            return this._$21070;
        }
        int inptr = 0;
        int outptr = 0;
        int inblk = plaintxt.length / this._$21071;
        this._$21088(this._$21067, this._$21072);
        for (int i = 0; i < 8; ++i) {
            ivtemp[i] = this._$21074[i];
        }
        while (inblk != 0) {
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] = (byte)(ivtemp[i] ^ plaintxt[inptr++]);
            }
            this._$21110(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                ciphertxt[outptr++] = (ivtemp[i] = tmpblk[i]);
            }
            --inblk;
        }
        return this._$21069;
    }
    
    public final int DES3CBCDecrypt(final byte[] ciphertxt, final byte[] plaintxt) {
        final byte[] tmpblk = new byte[8];
        final byte[] ivtemp = new byte[8];
        final byte[] tempstore = new byte[8];
        if (ciphertxt.length % this._$21071 != 0) {
            return this._$21070;
        }
        int inptr = 0;
        int outptr = 0;
        int inblk = plaintxt.length / this._$21071;
        this._$21102(this._$21073);
        for (int i = 0; i < 8; ++i) {
            ivtemp[i] = this._$21074[i];
        }
        while (inblk != 0) {
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] = (tempstore[i] = ciphertxt[inptr++]);
            }
            this._$21105(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                plaintxt[outptr++] = (byte)(ivtemp[i] ^ tmpblk[i]);
                ivtemp[i] = tempstore[i];
            }
            --inblk;
        }
        return this._$21069;
    }
    
    public final int DESCBCDecrypt(final byte[] ciphertxt, final byte[] plaintxt) {
        final byte[] tmpblk = new byte[8];
        final byte[] ivtemp = new byte[8];
        final byte[] tempstore = new byte[8];
        if (ciphertxt.length % this._$21071 != 0) {
            return this._$21070;
        }
        int inptr = 0;
        int outptr = 0;
        int inblk = plaintxt.length / this._$21071;
        this._$21088(this._$21067, this._$21073);
        for (int i = 0; i < 8; ++i) {
            ivtemp[i] = this._$21074[i];
        }
        while (inblk != 0) {
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] = (tempstore[i] = ciphertxt[inptr++]);
            }
            this._$21110(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                plaintxt[outptr++] = (byte)(ivtemp[i] ^ tmpblk[i]);
                ivtemp[i] = tempstore[i];
            }
            --inblk;
        }
        return this._$21069;
    }
    
    public final void X9_19Init(final byte[] inkey) {
        this._$21067 = new byte[8];
        for (int i = 0; i < this._$21067.length; ++i) {
            this._$21067[i] = inkey[i];
        }
        this._$21074 = new byte[8];
        for (int i = 0; i < this._$21074.length; ++i) {
            this._$21074[i] = 0;
        }
    }
    
    public final int X9_19MAC(final byte[] plaintxt, final byte[] mac) {
        final byte[] tmpblk = new byte[8];
        final byte[] ivtemp = new byte[8];
        int len;
        if (plaintxt.length % this._$21071 != 0) {
            len = plaintxt.length / this._$21071;
            len = (len + 1) * this._$21071;
        }
        else {
            len = plaintxt.length;
        }
        final byte[] padded_msg = new byte[len];
        for (int i = 0; i < plaintxt.length; ++i) {
            padded_msg[i] = plaintxt[i];
        }
        for (int i = plaintxt.length; i < len; ++i) {
            padded_msg[i] = 0;
        }
        int inptr = 0;
        int inblk = len / this._$21071;
        this._$21088(this._$21067, this._$21072);
        for (int i = 0; i < 8; ++i) {
            ivtemp[i] = this._$21074[i];
        }
        while (inblk != 0) {
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] = (byte)(ivtemp[i] ^ padded_msg[inptr++]);
            }
            this._$21110(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                ivtemp[i] = tmpblk[i];
            }
            --inblk;
        }
        for (int i = 0; i < mac.length; ++i) {
            mac[i] = ivtemp[i];
        }
        return this._$21069;
    }
    
    public final void X9_9Init(final byte[] inkey) {
        this._$21067 = new byte[8];
        for (int i = 0; i < this._$21067.length; ++i) {
            this._$21067[i] = inkey[i];
        }
        this._$21074 = new byte[8];
        for (int i = 0; i < this._$21074.length; ++i) {
            this._$21074[i] = 0;
        }
    }
    
    public final int X9_9MAC(final byte[] plaintxt, final byte[] mac) {
        final byte[] tmpblk = new byte[8];
        final byte[] ivtemp = new byte[8];
        int len;
        if (plaintxt.length % this._$21071 != 0) {
            len = plaintxt.length / this._$21071;
            len = (len + 1) * this._$21071;
        }
        else {
            len = plaintxt.length;
        }
        final byte[] padded_msg = new byte[len];
        for (int i = 0; i < plaintxt.length; ++i) {
            padded_msg[i] = plaintxt[i];
        }
        for (int i = plaintxt.length; i < len; ++i) {
            padded_msg[i] = 0;
        }
        int inptr = 0;
        int inblk = len / this._$21071;
        this._$21088(this._$21067, this._$21072);
        for (int i = 0; i < 8; ++i) {
            ivtemp[i] = this._$21074[i];
        }
        while (inblk != 0) {
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] = (byte)(ivtemp[i] ^ padded_msg[inptr++]);
            }
            this._$21110(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                ivtemp[i] = tmpblk[i];
            }
            --inblk;
        }
        for (int i = 0; i < mac.length; ++i) {
            mac[i] = ivtemp[i];
        }
        return this._$21069;
    }
    
    public final void X9_19RandKey(final byte[] K, final byte[] seed, final byte[] rand) {
        final byte[] tmpblk = new byte[8];
        final byte[] V = new byte[8];
        final byte[] I = new byte[8];
        final Date d = new Date();
        this._$21067 = new byte[8];
        for (int i = 0; i < this._$21067.length; ++i) {
            this._$21067[i] = K[i];
        }
        for (int i = 0; i < V.length; ++i) {
            V[i] = seed[i];
        }
        int len;
        if (rand.length % this._$21071 != 0) {
            len = rand.length / this._$21071;
            len = (len + 1) * this._$21071;
        }
        else {
            len = rand.length;
        }
        final byte[] padded_msg = new byte[len];
        int outptr = 0;
        int inblk = len / this._$21071;
        this._$21088(this._$21067, this._$21072);
        while (inblk != 0) {
            long l = d.getTime();
            tmpblk[0] = (byte)(l % 256);
            l /= 256;
            tmpblk[1] = (byte)(l % 256);
            l /= 256;
            tmpblk[2] = (byte)(l % 256);
            l /= 256;
            tmpblk[3] = (byte)(l % 256);
            l /= 256;
            tmpblk[4] = (byte)(l % 256);
            l /= 256;
            tmpblk[5] = (byte)(l % 256);
            l /= 256;
            tmpblk[6] = (byte)(l % 256);
            l /= 256;
            tmpblk[7] = (byte)(l % 256);
            l /= 256;
            this._$21110(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                I[i] = tmpblk[i];
            }
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] = (byte)(I[i] ^ V[i]);
            }
            this._$21110(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                padded_msg[outptr++] = tmpblk[i];
            }
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] ^= I[i];
            }
            this._$21110(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                V[i] = tmpblk[i];
            }
            --inblk;
        }
        for (int i = 0; i < rand.length; ++i) {
            rand[i] = padded_msg[i];
        }
    }
    
    public final void X9_19Init_Ext(final byte[] inkey) {
        this._$21067 = new byte[8];
        this._$21068 = new byte[8];
        for (int i = 0; i < this._$21067.length; ++i) {
            this._$21067[i] = inkey[i];
        }
        for (int i = 0; i < this._$21068.length; ++i) {
            this._$21068[i] = inkey[8 + i];
        }
        this._$21074 = new byte[8];
        for (int i = 0; i < this._$21074.length; ++i) {
            this._$21074[i] = 0;
        }
    }
    
    public final int X9_19MAC_Ext(final byte[] plaintxt, final byte[] mac) {
        final byte[] tmpblk = new byte[8];
        final byte[] ivtemp = new byte[8];
        int len;
        if (plaintxt.length % this._$21071 != 0) {
            len = plaintxt.length / this._$21071;
            len = (len + 1) * this._$21071;
        }
        else {
            len = plaintxt.length;
        }
        final byte[] padded_msg = new byte[len];
        for (int i = 0; i < plaintxt.length; ++i) {
            padded_msg[i] = plaintxt[i];
        }
        for (int i = plaintxt.length; i < len; ++i) {
            padded_msg[i] = 0;
        }
        int inptr = 0;
        int inblk = len / this._$21071;
        this._$21088(this._$21067, this._$21072);
        for (int i = 0; i < 8; ++i) {
            ivtemp[i] = this._$21074[i];
        }
        while (inblk != 0) {
            for (int i = 0; i < 8; ++i) {
                tmpblk[i] = (byte)(ivtemp[i] ^ padded_msg[inptr++]);
            }
            this._$21110(tmpblk, tmpblk);
            for (int i = 0; i < 8; ++i) {
                ivtemp[i] = tmpblk[i];
            }
            --inblk;
        }
        this._$21088(this._$21068, this._$21073);
        this._$21110(ivtemp, ivtemp);
        this._$21088(this._$21067, this._$21072);
        this._$21110(ivtemp, ivtemp);
        for (int i = 0; i < mac.length; ++i) {
            mac[i] = ivtemp[i];
        }
        return this._$21069;
    }
    
    public void getVersion() {
        System.out.println("RSADES3Applet Version 3.02");
        System.out.println("Creation Date: 22-1-2001");
    }
}
