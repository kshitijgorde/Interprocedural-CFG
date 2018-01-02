// 
// Decompiled by Procyon v0.5.30
// 

final class Class311
{
    private int anInt2654;
    private int anInt2655;
    private int anInt2656;
    private int[] anIntArray2657;
    private int anInt2658;
    private int anInt2659;
    private int anInt2660;
    
    final void method3619(final float[] array, final int n, final boolean b) {
        for (int i = 0; i < n; ++i) {
            array[i] = 0.0f;
        }
        if (!b) {
            final int anInt530 = Class98_Sub13.aClass71Array3885[this.anInt2655].anInt530;
            final int n2 = (this.anInt2660 - this.anInt2658) / this.anInt2659;
            final int[] array2 = new int[n2];
            for (int j = 0; j < 8; ++j) {
                int k = 0;
                while (k < n2) {
                    if (j == 0) {
                        int method714 = Class98_Sub13.aClass71Array3885[this.anInt2655].method714();
                        for (int l = anInt530 - 1; l >= 0; --l) {
                            if (k + l < n2) {
                                array2[k + l] = method714 % this.anInt2654;
                            }
                            method714 /= this.anInt2654;
                        }
                    }
                    for (int n3 = 0; n3 < anInt530; ++n3) {
                        final int n4 = this.anIntArray2657[array2[k] * 8 + j];
                        if (n4 >= 0) {
                            final int n5 = this.anInt2658 + k * this.anInt2659;
                            final Class71 class71 = Class98_Sub13.aClass71Array3885[n4];
                            if (this.anInt2656 == 0) {
                                for (int n6 = this.anInt2659 / class71.anInt530, n7 = 0; n7 < n6; ++n7) {
                                    final float[] method715 = class71.method715();
                                    for (int n8 = 0; n8 < class71.anInt530; ++n8) {
                                        final int n9 = n5 + n7 + n8 * n6;
                                        array[n9] += method715[n8];
                                    }
                                }
                            }
                            else {
                                int n10 = 0;
                                while (n10 < this.anInt2659) {
                                    final float[] method716 = class71.method715();
                                    for (int n11 = 0; n11 < class71.anInt530; ++n11) {
                                        final int n12 = n5 + n10;
                                        array[n12] += method716[n11];
                                        ++n10;
                                    }
                                }
                            }
                        }
                        if (++k >= n2) {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    Class311() {
        this.anInt2656 = Class98_Sub13.method1138(16);
        this.anInt2658 = Class98_Sub13.method1138(24);
        this.anInt2660 = Class98_Sub13.method1138(24);
        this.anInt2659 = Class98_Sub13.method1138(24) + 1;
        this.anInt2654 = Class98_Sub13.method1138(6) + 1;
        this.anInt2655 = Class98_Sub13.method1138(8);
        final int[] array = new int[this.anInt2654];
        for (int i = 0; i < this.anInt2654; ++i) {
            int method1138 = 0;
            final int method1139 = Class98_Sub13.method1138(3);
            if (Class98_Sub13.method1134() != 0) {
                method1138 = Class98_Sub13.method1138(5);
            }
            array[i] = (method1138 << 3 | method1139);
        }
        this.anIntArray2657 = new int[this.anInt2654 * 8];
        for (int j = 0; j < this.anInt2654 * 8; ++j) {
            this.anIntArray2657[j] = (((array[j >> 3] & 1 << (j & 0x7)) != 0x0) ? Class98_Sub13.method1138(8) : -1);
        }
    }
}
