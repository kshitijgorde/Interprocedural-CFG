// 
// Decompiled by Procyon v0.5.30
// 

final class Class43_Sub5 extends Class43
{
    private int[] anIntArray3622;
    private byte[][] aByteArrayArray3623;
    private int[] anIntArray3624;
    private int[] anIntArray3625;
    private ha_Sub2 aHa_Sub2_3626;
    private int[] anIntArray3627;
    
    private final void method423(final byte[] array, final int[] array2, final int n, int n2, int n3, int n4, final int n5, final int n6, final int n7) {
        final int n8 = -(n4 >> 2);
        n4 = -(n4 & 0x3);
        for (int i = -n5; i < 0; ++i) {
            for (int j = n8; j < 0; ++j) {
                if (array[n2++] != 0) {
                    array2[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array[n2++] != 0) {
                    array2[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array[n2++] != 0) {
                    array2[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array[n2++] != 0) {
                    array2[n3++] = n;
                }
                else {
                    ++n3;
                }
            }
            for (int k = n4; k < 0; ++k) {
                if (array[n2++] != 0) {
                    array2[n3++] = n;
                }
                else {
                    ++n3;
                }
            }
            n3 += n6;
            n2 += n7;
        }
    }
    
    private final void method424(final byte[] array, final int[] array2, final int n, int n2, int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8 = n >>> 24;
        final int n9 = 255 - n8;
        for (int i = -n5; i < 0; ++i) {
            for (int j = -n4; j < 0; ++j) {
                if (array[n2++] != 0) {
                    final int n10 = ((n & 0xFF00FF) * n8 & 0xFF00FF00) + ((n & 0xFF00) * n8 & 0xFF0000) >> 8;
                    final int n11 = array2[n3];
                    array2[n3++] = (((n11 & 0xFF00FF) * n9 & 0xFF00FF00) + ((n11 & 0xFF00) * n9 & 0xFF0000) >> 8) + n10;
                }
                else {
                    ++n3;
                }
            }
            n3 += n6;
            n2 += n7;
        }
    }
    
    private final void method425(final byte[] array, final int[] array2, final int n, int n2, int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final aa aa, final int n11, final int n12) {
        final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
        final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
        final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
        int n13 = n9;
        if (n12 > n13) {
            n13 = n12;
            n3 += (n12 - n9) * this.aHa_Sub2_3626.anInt4505;
            n2 += (n12 - n9) * n10;
        }
        for (int n14 = (n12 + anIntArray3555.length < n9 + n5) ? (n12 + anIntArray3555.length) : (n9 + n5), i = n13; i < n14; ++i) {
            final int n15 = n11 + anIntArray3555[i - n12];
            int n16 = anIntArray3556[i - n12];
            int n17 = n4;
            if (n8 > n15) {
                final int n18 = n8 - n15;
                if (n18 >= n16) {
                    n2 += n4 + n7;
                    n3 += n4 + n6;
                    continue;
                }
                n16 -= n18;
            }
            else {
                final int n19 = n15 - n8;
                if (n19 >= n4) {
                    n2 += n4 + n7;
                    n3 += n4 + n6;
                    continue;
                }
                n2 += n19;
                n17 -= n19;
                n3 += n19;
            }
            int n20 = 0;
            if (n17 < n16) {
                n16 = n17;
            }
            else {
                n20 = n17 - n16;
            }
            for (int j = 0; j < n16; ++j) {
                if (array[n2++] != 0) {
                    array2[n3++] = n;
                }
                else {
                    ++n3;
                }
            }
            n2 += n20 + n7;
            n3 += n20 + n6;
        }
    }
    
    private final void method426(final byte[] array, final int[] array2, final int n, int n2, int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final aa aa, final int n11, final int n12) {
        final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
        final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
        final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
        final int n13 = n8 - this.aHa_Sub2_3626.anInt4509;
        int n14 = n9;
        if (n12 > n14) {
            n14 = n12;
            n3 += (n12 - n9) * this.aHa_Sub2_3626.anInt4505;
            n2 += (n12 - n9) * n10;
        }
        final int n15 = (n12 + anIntArray3555.length < n9 + n5) ? (n12 + anIntArray3555.length) : (n9 + n5);
        final int n16 = n >>> 24;
        final int n17 = 255 - n16;
        for (int i = n14; i < n15; ++i) {
            final int n18 = anIntArray3555[i - n12] + n11;
            int n19 = anIntArray3556[i - n12];
            int n20 = n4;
            if (n13 > n18) {
                final int n21 = n13 - n18;
                if (n21 >= n19) {
                    n2 += n4 + n7;
                    n3 += n4 + n6;
                    continue;
                }
                n19 -= n21;
            }
            else {
                final int n22 = n18 - n13;
                if (n22 >= n4) {
                    n2 += n4 + n7;
                    n3 += n4 + n6;
                    continue;
                }
                n2 += n22;
                n20 -= n22;
                n3 += n22;
            }
            int n23 = 0;
            if (n20 < n19) {
                n19 = n20;
            }
            else {
                n23 = n20 - n19;
            }
            for (int j = -n19; j < 0; ++j) {
                if (array[n2++] != 0) {
                    final int n24 = ((n & 0xFF00FF) * n16 & 0xFF00FF00) + ((n & 0xFF00) * n16 & 0xFF0000) >> 8;
                    final int n25 = array2[n3];
                    array2[n3++] = (((n25 & 0xFF00FF) * n17 & 0xFF00FF00) + ((n25 & 0xFF00) * n17 & 0xFF0000) >> 8) + n24;
                }
                else {
                    ++n3;
                }
            }
            n2 += n23 + n7;
            n3 += n23 + n6;
        }
    }
    
    @Override
    final void fa(final char c, int anInt4509, int anInt4510, final int n, final boolean b) {
        anInt4509 += this.anIntArray3624[c];
        anInt4510 += this.anIntArray3622[c];
        int n2 = this.anIntArray3627[c];
        int n3 = this.anIntArray3625[c];
        final int anInt4511 = this.aHa_Sub2_3626.anInt4505;
        int n4 = anInt4509 + anInt4510 * anInt4511;
        int n5 = anInt4511 - n2;
        int n6 = 0;
        int n7 = 0;
        if (anInt4510 < this.aHa_Sub2_3626.anInt4495) {
            final int n8 = this.aHa_Sub2_3626.anInt4495 - anInt4510;
            n3 -= n8;
            anInt4510 = this.aHa_Sub2_3626.anInt4495;
            n7 += n8 * n2;
            n4 += n8 * anInt4511;
        }
        if (anInt4510 + n3 > this.aHa_Sub2_3626.anInt4492) {
            n3 -= anInt4510 + n3 - this.aHa_Sub2_3626.anInt4492;
        }
        if (anInt4509 < this.aHa_Sub2_3626.anInt4509) {
            final int n9 = this.aHa_Sub2_3626.anInt4509 - anInt4509;
            n2 -= n9;
            anInt4509 = this.aHa_Sub2_3626.anInt4509;
            n7 += n9;
            n4 += n9;
            n6 += n9;
            n5 += n9;
        }
        if (anInt4509 + n2 > this.aHa_Sub2_3626.anInt4507) {
            final int n10 = anInt4509 + n2 - this.aHa_Sub2_3626.anInt4507;
            n2 -= n10;
            n6 += n10;
            n5 += n10;
        }
        if (n2 > 0 && n3 > 0) {
            if ((n & 0xFF000000) == 0xFF000000) {
                this.method423(this.aByteArrayArray3623[c], this.aHa_Sub2_3626.anIntArray4504, n, n7, n4, n2, n3, n5, n6);
            }
            else if ((n & 0xFF000000) != 0x0) {
                this.method424(this.aByteArrayArray3623[c], this.aHa_Sub2_3626.anIntArray4504, n, n7, n4, n2, n3, n5, n6);
            }
        }
    }
    
    @Override
    final void method409(final char c, int anInt4509, int anInt4510, final int n, final boolean b, final aa aa, final int n2, final int n3) {
        if (aa == null) {
            this.fa(c, anInt4509, anInt4510, n, b);
        }
        else {
            anInt4509 += this.anIntArray3624[c];
            anInt4510 += this.anIntArray3622[c];
            int n4 = this.anIntArray3627[c];
            int n5 = this.anIntArray3625[c];
            final int anInt4511 = this.aHa_Sub2_3626.anInt4505;
            int n6 = anInt4509 + anInt4510 * anInt4511;
            int n7 = anInt4511 - n4;
            int n8 = 0;
            int n9 = 0;
            if (anInt4510 < this.aHa_Sub2_3626.anInt4495) {
                final int n10 = this.aHa_Sub2_3626.anInt4495 - anInt4510;
                n5 -= n10;
                anInt4510 = this.aHa_Sub2_3626.anInt4495;
                n9 += n10 * n4;
                n6 += n10 * anInt4511;
            }
            if (anInt4510 + n5 > this.aHa_Sub2_3626.anInt4492) {
                n5 -= anInt4510 + n5 - this.aHa_Sub2_3626.anInt4492;
            }
            if (anInt4509 < this.aHa_Sub2_3626.anInt4509) {
                final int n11 = this.aHa_Sub2_3626.anInt4509 - anInt4509;
                n4 -= n11;
                anInt4509 = this.aHa_Sub2_3626.anInt4509;
                n9 += n11;
                n6 += n11;
                n8 += n11;
                n7 += n11;
            }
            if (anInt4509 + n4 > this.aHa_Sub2_3626.anInt4507) {
                final int n12 = anInt4509 + n4 - this.aHa_Sub2_3626.anInt4507;
                n4 -= n12;
                n8 += n12;
                n7 += n12;
            }
            if (n4 > 0 && n5 > 0) {
                if ((n & 0xFF000000) == 0xFF000000) {
                    this.method425(this.aByteArrayArray3623[c], this.aHa_Sub2_3626.anIntArray4504, n, n9, n6, n4, n5, n7, n8, anInt4509, anInt4510, this.anIntArray3627[c], aa, n2, n3);
                }
                else {
                    this.method426(this.aByteArrayArray3623[c], this.aHa_Sub2_3626.anIntArray4504, n, n9, n6, n4, n5, n7, n8, anInt4509, anInt4510, this.anIntArray3627[c], aa, n2, n3);
                }
            }
        }
    }
    
    Class43_Sub5(final ha_Sub2 aHa_Sub2_3626, final Class197 class197, final Class324[] array, final int[] anIntArray3627, final int[] anIntArray3628) {
        super(aHa_Sub2_3626, class197);
        this.aHa_Sub2_3626 = aHa_Sub2_3626;
        this.anIntArray3627 = anIntArray3627;
        this.anIntArray3625 = anIntArray3628;
        this.aByteArrayArray3623 = new byte[array.length][];
        this.anIntArray3622 = new int[array.length];
        this.anIntArray3624 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.aByteArrayArray3623[i] = array[i].aByteArray2717;
            this.anIntArray3622[i] = array[i].anInt2721;
            this.anIntArray3624[i] = array[i].anInt2725;
        }
    }
}
