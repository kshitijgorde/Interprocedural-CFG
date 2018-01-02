// 
// Decompiled by Procyon v0.5.30
// 

final class Class71
{
    int anInt530;
    private int anInt531;
    private int[] anIntArray532;
    private float[][] aFloatArrayArray533;
    private int[] anIntArray534;
    private int[] anIntArray535;
    
    private final void method712() {
        final int[] array = new int[this.anInt531];
        final int[] array2 = new int[33];
        for (int i = 0; i < this.anInt531; ++i) {
            final int n = this.anIntArray534[i];
            if (n != 0) {
                final int n2 = 1 << 32 - n;
                final int n3 = array2[n];
                array[i] = n3;
                int n4;
                if ((n3 & n2) != 0x0) {
                    n4 = array2[n - 1];
                }
                else {
                    n4 = (n3 | n2);
                    for (int j = n - 1; j >= 1; --j) {
                        final int n5 = array2[j];
                        if (n5 != n3) {
                            break;
                        }
                        final int n6 = 1 << 32 - j;
                        if ((n5 & n6) != 0x0) {
                            array2[j] = array2[j - 1];
                            break;
                        }
                        array2[j] = (n5 | n6);
                    }
                }
                array2[n] = n4;
                for (int k = n + 1; k <= 32; ++k) {
                    if (array2[k] == n3) {
                        array2[k] = n4;
                    }
                }
            }
        }
        this.anIntArray535 = new int[8];
        int n7 = 0;
        for (int l = 0; l < this.anInt531; ++l) {
            final int n8 = this.anIntArray534[l];
            if (n8 != 0) {
                final int n9 = array[l];
                int n10 = 0;
                for (int n11 = 0; n11 < n8; ++n11) {
                    if ((n9 & Integer.MIN_VALUE >>> n11) != 0x0) {
                        if (this.anIntArray535[n10] == 0) {
                            this.anIntArray535[n10] = n7;
                        }
                        n10 = this.anIntArray535[n10];
                    }
                    else {
                        ++n10;
                    }
                    if (n10 >= this.anIntArray535.length) {
                        final int[] anIntArray535 = new int[this.anIntArray535.length * 2];
                        for (int n12 = 0; n12 < this.anIntArray535.length; ++n12) {
                            anIntArray535[n12] = this.anIntArray535[n12];
                        }
                        this.anIntArray535 = anIntArray535;
                    }
                }
                this.anIntArray535[n10] = ~l;
                if (n10 >= n7) {
                    n7 = n10 + 1;
                }
            }
        }
    }
    
    private static final int method713(final int n, final int n2) {
        int n3;
        for (n3 = (int)Math.pow(n, 1.0 / n2) + 1; Class98_Sub49.method1662(n3, -1, n2) > n; --n3) {}
        return n3;
    }
    
    final int method714() {
        int n;
        for (n = 0; this.anIntArray535[n] >= 0; n = ((Class98_Sub13.method1134() != 0) ? this.anIntArray535[n] : (n + 1))) {}
        return ~this.anIntArray535[n];
    }
    
    final float[] method715() {
        return this.aFloatArrayArray533[this.method714()];
    }
    
    Class71() {
        Class98_Sub13.method1138(24);
        this.anInt530 = Class98_Sub13.method1138(16);
        this.anInt531 = Class98_Sub13.method1138(24);
        this.anIntArray534 = new int[this.anInt531];
        if (Class98_Sub13.method1134() != 0) {
            int i = 0;
            int n = Class98_Sub13.method1138(5) + 1;
            while (i < this.anInt531) {
                for (int method1138 = Class98_Sub13.method1138(Class48_Sub2_Sub1.method474(this.anInt531 - i, (byte)31)), j = 0; j < method1138; ++j) {
                    this.anIntArray534[i++] = n;
                }
                ++n;
            }
        }
        else {
            final boolean b = Class98_Sub13.method1134() != 0;
            for (int k = 0; k < this.anInt531; ++k) {
                if (b && Class98_Sub13.method1134() == 0) {
                    this.anIntArray534[k] = 0;
                }
                else {
                    this.anIntArray534[k] = Class98_Sub13.method1138(5) + 1;
                }
            }
        }
        this.method712();
        final int method1139 = Class98_Sub13.method1138(4);
        if (method1139 > 0) {
            final float method1140 = Class98_Sub13.method1139(Class98_Sub13.method1138(32));
            final float method1141 = Class98_Sub13.method1139(Class98_Sub13.method1138(32));
            final int n2 = Class98_Sub13.method1138(4) + 1;
            final boolean b2 = Class98_Sub13.method1134() != 0;
            int method1142;
            if (method1139 == 1) {
                method1142 = method713(this.anInt531, this.anInt530);
            }
            else {
                method1142 = this.anInt531 * this.anInt530;
            }
            this.anIntArray532 = new int[method1142];
            for (int l = 0; l < method1142; ++l) {
                this.anIntArray532[l] = Class98_Sub13.method1138(n2);
            }
            this.aFloatArrayArray533 = new float[this.anInt531][this.anInt530];
            if (method1139 == 1) {
                for (int n3 = 0; n3 < this.anInt531; ++n3) {
                    float n4 = 0.0f;
                    int n5 = 1;
                    for (int n6 = 0; n6 < this.anInt530; ++n6) {
                        final float n7 = this.anIntArray532[n3 / n5 % method1142] * method1141 + method1140 + n4;
                        this.aFloatArrayArray533[n3][n6] = n7;
                        if (b2) {
                            n4 = n7;
                        }
                        n5 *= method1142;
                    }
                }
            }
            else {
                for (int n8 = 0; n8 < this.anInt531; ++n8) {
                    float n9 = 0.0f;
                    int n10 = n8 * this.anInt530;
                    for (int n11 = 0; n11 < this.anInt530; ++n11) {
                        final float n12 = this.anIntArray532[n10] * method1141 + method1140 + n9;
                        this.aFloatArrayArray533[n8][n11] = n12;
                        if (b2) {
                            n9 = n12;
                        }
                        ++n10;
                    }
                }
            }
        }
    }
}
