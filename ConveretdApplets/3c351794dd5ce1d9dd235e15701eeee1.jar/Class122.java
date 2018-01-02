// 
// Decompiled by Procyon v0.5.30
// 

final class Class122
{
    private int[] anIntArray1008;
    static int anInt1009;
    
    static final void method2199(final boolean b) {
        try {
            if (Class221.anIntArray1665 == null) {
                Class221.anIntArray1665 = new int[65536];
                final double n = 0.7 + (0.03 * Math.random() - 0.015);
                int n2 = 0;
                if (b) {
                    Class122.anInt1009 = 50;
                }
                for (int n3 = 0; ~n3 > -513; ++n3) {
                    final float n4 = 360.0f * (0.0078125f + (n3 >> 74274595) / 64.0f);
                    final float n5 = (n3 & 0x7) / 8.0f + 0.0625f;
                    for (int i = 0; i < 128; ++i) {
                        final float n6 = i / 128.0f;
                        float n7 = 0.0f;
                        float n8 = 0.0f;
                        float n9 = 0.0f;
                        final float n10 = n4 / 60.0f;
                        final int n11 = (int)n10;
                        final int n12 = n11 % 6;
                        final float n13 = -n11 + n10;
                        final float n14 = (-n5 + 1.0f) * n6;
                        final float n15 = n6 * (1.0f - n5 * n13);
                        final float n16 = (-((-n13 + 1.0f) * n5) + 1.0f) * n6;
                        if (n12 != 0) {
                            if (n12 != 1) {
                                if (~n12 != 0xFFFFFFFD) {
                                    if (~n12 != 0xFFFFFFFC) {
                                        if (~n12 != 0xFFFFFFFB) {
                                            if (~n12 == 0xFFFFFFFA) {
                                                n9 = n15;
                                                n7 = n6;
                                                n8 = n14;
                                            }
                                        }
                                        else {
                                            n8 = n14;
                                            n9 = n6;
                                            n7 = n16;
                                        }
                                    }
                                    else {
                                        n7 = n14;
                                        n8 = n15;
                                        n9 = n6;
                                    }
                                }
                                else {
                                    n7 = n14;
                                    n9 = n16;
                                    n8 = n6;
                                }
                            }
                            else {
                                n8 = n6;
                                n7 = n15;
                                n9 = n14;
                            }
                        }
                        else {
                            n8 = n16;
                            n7 = n6;
                            n9 = n14;
                        }
                        Class221.anIntArray1665[n2++] = ((int)(256.0f * (float)Math.pow(n7, n)) << 1057698384) + (-16777216 + ((int)((float)Math.pow(n8, n) * 256.0f) << 1325420744) - -(int)((float)Math.pow(n9, n) * 256.0f));
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ica.C(" + b + ')');
        }
    }
    
    final int method2200(final byte b, final int n) {
        try {
            if (b != -26) {
                method2201(83, null, -111, null, 117, (byte)(-54));
            }
            final int n2 = (this.anIntArray1008.length >> 1866406849) - 1;
            int n3 = n2 & n;
            while (true) {
                final int n4 = this.anIntArray1008[1 + (n3 + n3)];
                if (~n4 == 0x0) {
                    return -1;
                }
                if (~n == ~this.anIntArray1008[n3 - -n3]) {
                    return n4;
                }
                n3 = (1 + n3 & n2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ica.A(" + b + ',' + n + ')');
        }
    }
    
    Class122(final int[] array) {
        try {
            int n;
            for (n = 1; ~n >= ~(array.length + (array.length >> 1095190241)); n <<= 1) {}
            this.anIntArray1008 = new int[n + n];
            for (int n2 = 0; n - -n > n2; ++n2) {
                this.anIntArray1008[n2] = -1;
            }
            for (int n3 = 0; ~array.length < ~n3; ++n3) {
                int n4;
                for (n4 = (array[n3] & n - 1); this.anIntArray1008[1 + n4 + n4] != -1; n4 = (n4 + 1 & -1 + n)) {}
                this.anIntArray1008[n4 + n4] = array[n3];
                this.anIntArray1008[1 + (n4 + n4)] = n3;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ica.<init>(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method2201(int n, final Class352 class352, final int n2, final ha ha, final int n3, final byte b) {
        try {
            if (b != 70) {
                method2201(76, null, 62, null, 80, (byte)(-43));
            }
            final Class9 method3766 = Class98_Sub10_Sub23.aClass335_5662.method3766(class352.anInt2990, (byte)97);
            if (~method3766.anInt114 != 0x0) {
                Label_0073: {
                    if (class352.aBoolean3004) {
                        n += class352.anInt2962;
                        n &= 0x3;
                        if (!client.aBoolean3553) {
                            break Label_0073;
                        }
                    }
                    n = 0;
                }
                final Class332 method3767 = method3766.method190(class352.aBoolean2976, 0, n, ha);
                if (method3767 != null) {
                    int n4 = class352.sizeY;
                    int n5 = class352.sizeX;
                    if ((0x1 & n) == 0x1) {
                        n5 = class352.sizeY;
                        n4 = class352.sizeX;
                    }
                    int method3768 = method3767.method3737();
                    int method3769 = method3767.method3749();
                    if (method3766.aBoolean116) {
                        method3769 = 4 * n5;
                        method3768 = 4 * n4;
                    }
                    if (~method3766.anInt115 != -1) {
                        method3767.method3727(n2, n3 + 4 - 4 * n5, method3768, method3769, 0, method3766.anInt115 | 0xFF000000, 1);
                    }
                    else {
                        method3767.method3726(n2, n3 + -(4 * (-1 + n5)), method3768, method3769);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ica.B(" + n + ',' + ((class352 != null) ? "{...}" : "null") + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n3 + ',' + b + ')');
        }
    }
}
