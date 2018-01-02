// 
// Decompiled by Procyon v0.5.30
// 

final class Class43_Sub3 extends Class43
{
    private int[] anIntArray3609;
    private int[] anIntArray3610;
    private int[] anIntArray3611;
    private byte[][] aByteArrayArray3612;
    private int[] anIntArray3613;
    private ha_Sub2 aHa_Sub2_3614;
    
    Class43_Sub3(final ha_Sub2 ha_Sub2, final Class197 class197, final Class324[] array, final int[] anIntArray3610, final int[] anIntArray3611) {
        super(ha_Sub2, class197);
        this.aHa_Sub2_3614 = ha_Sub2;
        this.aHa_Sub2_3614 = ha_Sub2;
        this.anIntArray3610 = anIntArray3610;
        this.anIntArray3609 = anIntArray3611;
        this.aByteArrayArray3612 = new byte[array.length][];
        this.anIntArray3613 = new int[array.length];
        this.anIntArray3611 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            final Class324 class198 = array[i];
            if (class198.aByteArray2723 != null) {
                this.aByteArrayArray3612[i] = class198.aByteArray2723;
            }
            else {
                final byte[] aByteArray2717 = class198.aByteArray2717;
                final byte[][] aByteArrayArray3612 = this.aByteArrayArray3612;
                final int n = i;
                final byte[] array2 = new byte[aByteArray2717.length];
                aByteArrayArray3612[n] = array2;
                final byte[] array3 = array2;
                for (int j = 0; j < aByteArray2717.length; ++j) {
                    array3[j] = (byte)((aByteArray2717[j] == 0) ? 0 : -1);
                }
            }
            this.anIntArray3613[i] = class198.anInt2721;
            this.anIntArray3611[i] = class198.anInt2725;
        }
    }
    
    @Override
    final void fa(final char c, int anInt4509, int anInt4510, final int n, final boolean b) {
        anInt4509 += this.anIntArray3611[c];
        anInt4510 += this.anIntArray3613[c];
        int n2 = this.anIntArray3610[c];
        int n3 = this.anIntArray3609[c];
        final int anInt4511 = this.aHa_Sub2_3614.anInt4505;
        int n4 = anInt4509 + anInt4510 * anInt4511;
        int n5 = anInt4511 - n2;
        int n6 = 0;
        int n7 = 0;
        if (anInt4510 < this.aHa_Sub2_3614.anInt4495) {
            final int n8 = this.aHa_Sub2_3614.anInt4495 - anInt4510;
            n3 -= n8;
            anInt4510 = this.aHa_Sub2_3614.anInt4495;
            n7 += n8 * n2;
            n4 += n8 * anInt4511;
        }
        if (anInt4510 + n3 > this.aHa_Sub2_3614.anInt4492) {
            n3 -= anInt4510 + n3 - this.aHa_Sub2_3614.anInt4492;
        }
        if (anInt4509 < this.aHa_Sub2_3614.anInt4509) {
            final int n9 = this.aHa_Sub2_3614.anInt4509 - anInt4509;
            n2 -= n9;
            anInt4509 = this.aHa_Sub2_3614.anInt4509;
            n7 += n9;
            n4 += n9;
            n6 += n9;
            n5 += n9;
        }
        if (anInt4509 + n2 > this.aHa_Sub2_3614.anInt4507) {
            final int n10 = anInt4509 + n2 - this.aHa_Sub2_3614.anInt4507;
            n2 -= n10;
            n6 += n10;
            n5 += n10;
        }
        if (n2 > 0 && n3 > 0) {
            this.method417(this.aByteArrayArray3612[c], this.aHa_Sub2_3614.anIntArray4504, n, n7, n4, n2, n3, n5, n6);
        }
    }
    
    private final void method417(final byte[] array, final int[] array2, final int n, int n2, int n3, final int n4, final int n5, final int n6, final int n7) {
        for (int i = -n5; i < 0; ++i) {
            for (int j = -n4; j < 0; ++j) {
                final int n8 = array[n2++] & 0xFF;
                if (n8 != 0) {
                    final int n9 = ((n & 0xFF00FF) * n8 & 0xFF00FF00) + ((n & 0xFF00) * n8 & 0xFF0000) >> 8;
                    final int n10 = 256 - n8;
                    final int n11 = array2[n3];
                    array2[n3++] = (((n11 & 0xFF00FF) * n10 & 0xFF00FF00) + ((n11 & 0xFF00) * n10 & 0xFF0000) >> 8) + n9;
                }
                else {
                    ++n3;
                }
            }
            n3 += n6;
            n2 += n7;
        }
    }
    
    private final void method418(final byte[] array, final int[] array2, final int n, int n2, int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final aa aa, final int n11, final int n12) {
        final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
        final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
        final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
        final int n13 = n8 - this.aHa_Sub2_3614.anInt4509;
        int n14 = n9;
        if (n12 > n14) {
            n14 = n12;
            n3 += (n12 - n9) * this.aHa_Sub2_3614.anInt4505;
            n2 += (n12 - n9) * n10;
        }
        for (int n15 = (n12 + anIntArray3555.length < n9 + n5) ? (n12 + anIntArray3555.length) : (n9 + n5), i = n14; i < n15; ++i) {
            final int n16 = anIntArray3555[i - n12] + n11;
            int n17 = anIntArray3556[i - n12];
            int n18 = n4;
            if (n13 > n16) {
                final int n19 = n13 - n16;
                if (n19 >= n17) {
                    n2 += n4 + n7;
                    n3 += n4 + n6;
                    continue;
                }
                n17 -= n19;
            }
            else {
                final int n20 = n16 - n13;
                if (n20 >= n4) {
                    n2 += n4 + n7;
                    n3 += n4 + n6;
                    continue;
                }
                n2 += n20;
                n18 -= n20;
                n3 += n20;
            }
            int n21 = 0;
            if (n18 < n17) {
                n17 = n18;
            }
            else {
                n21 = n18 - n17;
            }
            for (int j = -n17; j < 0; ++j) {
                final int n22 = array[n2++] & 0xFF;
                if (n22 != 0) {
                    final int n23 = ((n & 0xFF00FF) * n22 & 0xFF00FF00) + ((n & 0xFF00) * n22 & 0xFF0000) >> 8;
                    final int n24 = 256 - n22;
                    final int n25 = array2[n3];
                    array2[n3++] = (((n25 & 0xFF00FF) * n24 & 0xFF00FF00) + ((n25 & 0xFF00) * n24 & 0xFF0000) >> 8) + n23;
                }
                else {
                    ++n3;
                }
            }
            n2 += n21 + n7;
            n3 += n21 + n6;
        }
    }
    
    @Override
    final void method409(final char c, int anInt4509, int anInt4510, final int n, final boolean b, final aa aa, final int n2, final int n3) {
        if (aa == null) {
            this.fa(c, anInt4509, anInt4510, n, b);
        }
        else {
            anInt4509 += this.anIntArray3611[c];
            anInt4510 += this.anIntArray3613[c];
            int n4 = this.anIntArray3610[c];
            int n5 = this.anIntArray3609[c];
            final int anInt4511 = this.aHa_Sub2_3614.anInt4505;
            int n6 = anInt4509 + anInt4510 * anInt4511;
            int n7 = anInt4511 - n4;
            int n8 = 0;
            int n9 = 0;
            if (anInt4510 < this.aHa_Sub2_3614.anInt4495) {
                final int n10 = this.aHa_Sub2_3614.anInt4495 - anInt4510;
                n5 -= n10;
                anInt4510 = this.aHa_Sub2_3614.anInt4495;
                n9 += n10 * n4;
                n6 += n10 * anInt4511;
            }
            if (anInt4510 + n5 > this.aHa_Sub2_3614.anInt4492) {
                n5 -= anInt4510 + n5 - this.aHa_Sub2_3614.anInt4492;
            }
            if (anInt4509 < this.aHa_Sub2_3614.anInt4509) {
                final int n11 = this.aHa_Sub2_3614.anInt4509 - anInt4509;
                n4 -= n11;
                anInt4509 = this.aHa_Sub2_3614.anInt4509;
                n9 += n11;
                n6 += n11;
                n8 += n11;
                n7 += n11;
            }
            if (anInt4509 + n4 > this.aHa_Sub2_3614.anInt4507) {
                final int n12 = anInt4509 + n4 - this.aHa_Sub2_3614.anInt4507;
                n4 -= n12;
                n8 += n12;
                n7 += n12;
            }
            if (n4 > 0 && n5 > 0) {
                this.method418(this.aByteArrayArray3612[c], this.aHa_Sub2_3614.anIntArray4504, n, n9, n6, n4, n5, n7, n8, anInt4509, anInt4510, this.anIntArray3610[c], aa, n2, n3);
            }
        }
    }
}
