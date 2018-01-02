// 
// Decompiled by Procyon v0.5.30
// 

final class Class43_Sub4 extends Class43
{
    private byte[][] aByteArrayArray3615;
    private int[] anIntArray3616;
    private int[] anIntArray3617;
    private int[] anIntArray3618;
    private int[] anIntArray3619;
    private int[] anIntArray3620;
    private ha_Sub2 aHa_Sub2_3621;
    
    @Override
    final void fa(final char c, int anInt4509, int anInt4510, final int n, final boolean b) {
        anInt4509 += this.anIntArray3620[c];
        anInt4510 += this.anIntArray3618[c];
        int n2 = this.anIntArray3619[c];
        int n3 = this.anIntArray3616[c];
        final int anInt4511 = this.aHa_Sub2_3621.anInt4505;
        int n4 = anInt4509 + anInt4510 * anInt4511;
        int n5 = anInt4511 - n2;
        int n6 = 0;
        int n7 = 0;
        if (anInt4510 < this.aHa_Sub2_3621.anInt4495) {
            final int n8 = this.aHa_Sub2_3621.anInt4495 - anInt4510;
            n3 -= n8;
            anInt4510 = this.aHa_Sub2_3621.anInt4495;
            n7 += n8 * n2;
            n4 += n8 * anInt4511;
        }
        if (anInt4510 + n3 > this.aHa_Sub2_3621.anInt4492) {
            n3 -= anInt4510 + n3 - this.aHa_Sub2_3621.anInt4492;
        }
        if (anInt4509 < this.aHa_Sub2_3621.anInt4509) {
            final int n9 = this.aHa_Sub2_3621.anInt4509 - anInt4509;
            n2 -= n9;
            anInt4509 = this.aHa_Sub2_3621.anInt4509;
            n7 += n9;
            n4 += n9;
            n6 += n9;
            n5 += n9;
        }
        if (anInt4509 + n2 > this.aHa_Sub2_3621.anInt4507) {
            final int n10 = anInt4509 + n2 - this.aHa_Sub2_3621.anInt4507;
            n2 -= n10;
            n6 += n10;
            n5 += n10;
        }
        if (n2 > 0 && n3 > 0) {
            if (b) {
                this.method422(this.aByteArrayArray3615[c], this.aHa_Sub2_3621.anIntArray4504, n, n7, n4, n2, n3, n5, n6);
            }
            else {
                this.method421(this.aByteArrayArray3615[c], this.aHa_Sub2_3621.anIntArray4504, this.anIntArray3617, n7, n4, n2, n3, n5, n6);
            }
        }
    }
    
    private final void method419(final byte[] array, final int[] array2, final int n, int n2, int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final aa aa, final int n11, final int n12) {
        final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
        final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
        final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
        final int n13 = n8 - this.aHa_Sub2_3621.anInt4509;
        int n14 = n9;
        if (n12 > n14) {
            n14 = n12;
            n3 += (n12 - n9) * this.aHa_Sub2_3621.anInt4505;
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
                if (array[n2++] != 0) {
                    array2[n3++] = n;
                }
                else {
                    ++n3;
                }
            }
            n2 += n21 + n2;
            n3 += n21 + n6;
        }
    }
    
    private final void method420(final byte[] array, final int[] array2, final int[] array3, final int n, int n2, int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final aa aa, final int n11, final int n12) {
        final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
        final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
        final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
        final int n13 = n8 - this.aHa_Sub2_3621.anInt4509;
        int n14 = n9;
        if (n12 > n14) {
            n14 = n12;
            n3 += (n12 - n9) * this.aHa_Sub2_3621.anInt4505;
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
                final byte b;
                if ((b = array[n2++]) != 0) {
                    array2[n3++] = array3[b & 0xFF];
                }
                else {
                    ++n3;
                }
            }
            n2 += n21 + n7;
            n3 += n21 + n6;
        }
    }
    
    Class43_Sub4(final ha_Sub2 ha_Sub2, final Class197 class197, final Class324[] array, final int[] anIntArray3619, final int[] anIntArray3620) {
        super(ha_Sub2, class197);
        this.aHa_Sub2_3621 = ha_Sub2;
        this.aHa_Sub2_3621 = ha_Sub2;
        this.anIntArray3619 = anIntArray3619;
        this.anIntArray3616 = anIntArray3620;
        this.aByteArrayArray3615 = new byte[array.length][];
        this.anIntArray3618 = new int[array.length];
        this.anIntArray3620 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.aByteArrayArray3615[i] = array[i].aByteArray2717;
            this.anIntArray3618[i] = array[i].anInt2721;
            this.anIntArray3620[i] = array[i].anInt2725;
        }
        this.anIntArray3617 = array[0].anIntArray2718;
    }
    
    @Override
    final void method409(final char c, int anInt4509, int anInt4510, final int n, final boolean b, final aa aa, final int n2, final int n3) {
        if (aa == null) {
            this.fa(c, anInt4509, anInt4510, n, b);
        }
        else {
            anInt4509 += this.anIntArray3620[c];
            anInt4510 += this.anIntArray3618[c];
            int n4 = this.anIntArray3619[c];
            int n5 = this.anIntArray3616[c];
            final int anInt4511 = this.aHa_Sub2_3621.anInt4505;
            int n6 = anInt4509 + anInt4510 * anInt4511;
            int n7 = anInt4511 - n4;
            int n8 = 0;
            int n9 = 0;
            if (anInt4510 < this.aHa_Sub2_3621.anInt4495) {
                final int n10 = this.aHa_Sub2_3621.anInt4495 - anInt4510;
                n5 -= n10;
                anInt4510 = this.aHa_Sub2_3621.anInt4495;
                n9 += n10 * n4;
                n6 += n10 * anInt4511;
            }
            if (anInt4510 + n5 > this.aHa_Sub2_3621.anInt4492) {
                n5 -= anInt4510 + n5 - this.aHa_Sub2_3621.anInt4492;
            }
            if (anInt4509 < this.aHa_Sub2_3621.anInt4509) {
                final int n11 = this.aHa_Sub2_3621.anInt4509 - anInt4509;
                n4 -= n11;
                anInt4509 = this.aHa_Sub2_3621.anInt4509;
                n9 += n11;
                n6 += n11;
                n8 += n11;
                n7 += n11;
            }
            if (anInt4509 + n4 > this.aHa_Sub2_3621.anInt4507) {
                final int n12 = anInt4509 + n4 - this.aHa_Sub2_3621.anInt4507;
                n4 -= n12;
                n8 += n12;
                n7 += n12;
            }
            if (n4 > 0 && n5 > 0) {
                if (b) {
                    this.method419(this.aByteArrayArray3615[c], this.aHa_Sub2_3621.anIntArray4504, n, n9, n6, n4, n5, n7, n8, anInt4509, anInt4510, this.anIntArray3619[c], aa, n2, n3);
                }
                else {
                    this.method420(this.aByteArrayArray3615[c], this.aHa_Sub2_3621.anIntArray4504, this.anIntArray3617, n, n9, n6, n4, n5, n7, n8, anInt4509, anInt4510, this.anIntArray3619[c], aa, n2, n3);
                }
            }
        }
    }
    
    private final void method421(final byte[] array, final int[] array2, final int[] array3, int n, int n2, int n3, final int n4, final int n5, final int n6) {
        final int n7 = -(n3 >> 2);
        n3 = -(n3 & 0x3);
        for (int i = -n4; i < 0; ++i) {
            for (int j = n7; j < 0; ++j) {
                final byte b;
                if ((b = array[n++]) != 0) {
                    array2[n2++] = array3[b & 0xFF];
                }
                else {
                    ++n2;
                }
                final byte b2;
                if ((b2 = array[n++]) != 0) {
                    array2[n2++] = array3[b2 & 0xFF];
                }
                else {
                    ++n2;
                }
                final byte b3;
                if ((b3 = array[n++]) != 0) {
                    array2[n2++] = array3[b3 & 0xFF];
                }
                else {
                    ++n2;
                }
                final byte b4;
                if ((b4 = array[n++]) != 0) {
                    array2[n2++] = array3[b4 & 0xFF];
                }
                else {
                    ++n2;
                }
            }
            for (int k = n3; k < 0; ++k) {
                final byte b5;
                if ((b5 = array[n++]) != 0) {
                    array2[n2++] = array3[b5 & 0xFF];
                }
                else {
                    ++n2;
                }
            }
            n2 += n5;
            n += n6;
        }
    }
    
    private final void method422(final byte[] array, final int[] array2, final int n, int n2, int n3, int n4, final int n5, final int n6, final int n7) {
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
}
