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
    
    private final void dequant_slow(final short[] array, final short[] array2, final int[] array3) {
        for (int i = 0; i < 64; ++i) {
            array3[Constants.dequant_index[i]] = array2[i] * array[i];
        }
    }
    
    public final void IDctSlow(final short[] array, final short[] array2, final short[] array3) {
        this.dequant_slow(array2, array, this.ip);
        for (int i = 0, n = 0; i < 8; ++i, n += 8) {
            if ((this.ip[0 + n] | this.ip[1 + n] | this.ip[2 + n] | this.ip[3 + n] | this.ip[4 + n] | this.ip[5 + n] | this.ip[6 + n] | this.ip[7 + n]) != 0x0) {
                final int n2 = (64277 * this.ip[1 + n] >> 16) + (12785 * this.ip[7 + n] >> 16);
                final int n3 = (12785 * this.ip[1 + n] >> 16) - (64277 * this.ip[7 + n] >> 16);
                final int n4 = (54491 * this.ip[3 + n] >> 16) + (36410 * this.ip[5 + n] >> 16);
                final int n5 = (54491 * this.ip[5 + n] >> 16) - (36410 * this.ip[3 + n] >> 16);
                final int n6 = 46341 * (short)(n2 - n4) >> 16;
                final int n7 = 46341 * (short)(n3 - n5) >> 16;
                final int n8 = n2 + n4;
                final int n9 = n3 + n5;
                final int n10 = 46341 * (short)(this.ip[0 + n] + this.ip[4 + n]) >> 16;
                final int n11 = 46341 * (short)(this.ip[0 + n] - this.ip[4 + n]) >> 16;
                final int n12 = (60547 * this.ip[2 + n] >> 16) + (25080 * this.ip[6 + n] >> 16);
                final int n13 = (25080 * this.ip[2 + n] >> 16) - (60547 * this.ip[6 + n] >> 16);
                final int n14 = n10 - n12;
                final int n15 = n10 + n12;
                final int n16 = n11 + n6;
                final int n17 = n7 - n13;
                final int n18 = n11 - n6;
                final int n19 = n7 + n13;
                this.ip[0 + n] = (short)(n15 + n8 >> 0);
                this.ip[7 + n] = (short)(n15 - n8 >> 0);
                this.ip[1 + n] = (short)(n16 + n19 >> 0);
                this.ip[2 + n] = (short)(n16 - n19 >> 0);
                this.ip[3 + n] = (short)(n14 + n9 >> 0);
                this.ip[4 + n] = (short)(n14 - n9 >> 0);
                this.ip[5 + n] = (short)(n18 + n17 >> 0);
                this.ip[6 + n] = (short)(n18 - n17 >> 0);
            }
        }
        for (int j = 0, n20 = 0; j < 8; ++j, ++n20) {
            if ((this.ip[0 + n20] | this.ip[8 + n20] | this.ip[16 + n20] | this.ip[24 + n20] | this.ip[32 + n20] | this.ip[40 + n20] | this.ip[48 + n20] | this.ip[56 + n20]) != 0x0) {
                final int n21 = (64277 * this.ip[8 + n20] >> 16) + (12785 * this.ip[56 + n20] >> 16);
                final int n22 = (12785 * this.ip[8 + n20] >> 16) - (64277 * this.ip[56 + n20] >> 16);
                final int n23 = (54491 * this.ip[24 + n20] >> 16) + (36410 * this.ip[40 + n20] >> 16);
                final int n24 = (54491 * this.ip[40 + n20] >> 16) - (36410 * this.ip[24 + n20] >> 16);
                final int n25 = 46341 * (short)(n21 - n23) >> 16;
                final int n26 = 46341 * (short)(n22 - n24) >> 16;
                final int n27 = n21 + n23;
                final int n28 = n22 + n24;
                final int n29 = 46341 * (short)(this.ip[0 + n20] + this.ip[32 + n20]) >> 16;
                final int n30 = 46341 * (short)(this.ip[0 + n20] - this.ip[32 + n20]) >> 16;
                final int n31 = (60547 * this.ip[16 + n20] >> 16) + (25080 * this.ip[48 + n20] >> 16);
                final int n32 = (25080 * this.ip[16 + n20] >> 16) - (60547 * this.ip[48 + n20] >> 16);
                int n33 = n29 - n31;
                int n34 = n29 + n31;
                int n35 = n30 + n25;
                final int n36 = n26 - n32;
                int n37 = n30 - n25;
                final int n38 = n26 + n32;
                n34 += 8;
                n35 += 8;
                n33 += 8;
                n37 += 8;
                array3[0 + n20] = (short)(n34 + n27 >> 4);
                array3[56 + n20] = (short)(n34 - n27 >> 4);
                array3[8 + n20] = (short)(n35 + n38 >> 4);
                array3[16 + n20] = (short)(n35 - n38 >> 4);
                array3[24 + n20] = (short)(n33 + n28 >> 4);
                array3[32 + n20] = (short)(n33 - n28 >> 4);
                array3[40 + n20] = (short)(n37 + n36 >> 4);
                array3[48 + n20] = (short)(n37 - n36 >> 4);
            }
            else {
                array3[56 + n20] = (array3[0 + n20] = 0);
                array3[16 + n20] = (array3[8 + n20] = 0);
                array3[32 + n20] = (array3[24 + n20] = 0);
                array3[48 + n20] = (array3[40 + n20] = 0);
            }
        }
    }
    
    private final void dequant_slow10(final short[] array, final short[] array2, final int[] array3) {
        for (int i = 0; i < 32; ++i) {
            array3[i] = 0;
        }
        for (int j = 0; j < 10; ++j) {
            array3[Constants.dequant_index[j]] = array2[j] * array[j];
        }
    }
    
    public final void IDct10(final short[] array, final short[] array2, final short[] array3) {
        this.dequant_slow10(array2, array, this.ip);
        for (int i = 0, n = 0; i < 4; ++i, n += 8) {
            if ((this.ip[0 + n] | this.ip[1 + n] | this.ip[2 + n] | this.ip[3 + n]) != 0x0) {
                final int n2 = 64277 * this.ip[1 + n] >> 16;
                final int n3 = 12785 * this.ip[1 + n] >> 16;
                final int n4 = 54491 * this.ip[3 + n] >> 16;
                final int n5 = -(36410 * this.ip[3 + n] >> 16);
                final int n6 = 46341 * (short)(n2 - n4) >> 16;
                final int n7 = 46341 * (short)(n3 - n5) >> 16;
                final int n8 = n2 + n4;
                final int n9 = n3 + n5;
                final int n11;
                final int n10 = n11 = 46341 * this.ip[0 + n] >> 16;
                final int n12 = 60547 * this.ip[2 + n] >> 16;
                final int n13 = 25080 * this.ip[2 + n] >> 16;
                final int n14 = n10 - n12;
                final int n15 = n10 + n12;
                final int n16 = n11 + n6;
                final int n17 = n7 - n13;
                final int n18 = n11 - n6;
                final int n19 = n7 + n13;
                this.ip[0 + n] = (short)(n15 + n8 >> 0);
                this.ip[7 + n] = (short)(n15 - n8 >> 0);
                this.ip[1 + n] = (short)(n16 + n19 >> 0);
                this.ip[2 + n] = (short)(n16 - n19 >> 0);
                this.ip[3 + n] = (short)(n14 + n9 >> 0);
                this.ip[4 + n] = (short)(n14 - n9 >> 0);
                this.ip[5 + n] = (short)(n18 + n17 >> 0);
                this.ip[6 + n] = (short)(n18 - n17 >> 0);
            }
        }
        for (int j = 0, n20 = 0; j < 8; ++j, ++n20) {
            if ((this.ip[0 + n20] | this.ip[8 + n20] | this.ip[16 + n20] | this.ip[24 + n20]) != 0x0) {
                final int n21 = 64277 * this.ip[8 + n20] >> 16;
                final int n22 = 12785 * this.ip[8 + n20] >> 16;
                final int n23 = 54491 * this.ip[24 + n20] >> 16;
                final int n24 = -(36410 * this.ip[24 + n20] >> 16);
                final int n25 = 46341 * (short)(n21 - n23) >> 16;
                final int n26 = 46341 * (short)(n22 - n24) >> 16;
                final int n27 = n21 + n23;
                final int n28 = n22 + n24;
                final int n30;
                final int n29 = n30 = 46341 * this.ip[0 + n20] >> 16;
                final int n31 = 60547 * this.ip[16 + n20] >> 16;
                final int n32 = 25080 * this.ip[16 + n20] >> 16;
                int n33 = n29 - n31;
                int n34 = n29 + n31;
                int n35 = n30 + n25;
                final int n36 = n26 - n32;
                int n37 = n30 - n25;
                final int n38 = n26 + n32;
                n34 += 8;
                n35 += 8;
                n33 += 8;
                n37 += 8;
                array3[0 + n20] = (short)(n34 + n27 >> 4);
                array3[56 + n20] = (short)(n34 - n27 >> 4);
                array3[8 + n20] = (short)(n35 + n38 >> 4);
                array3[16 + n20] = (short)(n35 - n38 >> 4);
                array3[24 + n20] = (short)(n33 + n28 >> 4);
                array3[32 + n20] = (short)(n33 - n28 >> 4);
                array3[40 + n20] = (short)(n37 + n36 >> 4);
                array3[48 + n20] = (short)(n37 - n36 >> 4);
            }
            else {
                array3[56 + n20] = (array3[0 + n20] = 0);
                array3[16 + n20] = (array3[8 + n20] = 0);
                array3[32 + n20] = (array3[24 + n20] = 0);
                array3[48 + n20] = (array3[40 + n20] = 0);
            }
        }
    }
    
    public final void IDct1(final short[] array, final short[] array2, final short[] array3) {
        final short n = (short)(array[0] * array2[0] + 15 >> 5);
        for (int i = 0; i < 64; ++i) {
            array3[i] = n;
        }
    }
}
