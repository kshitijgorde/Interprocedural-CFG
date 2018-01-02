// 
// Decompiled by Procyon v0.5.30
// 

class Class224_Sub2 extends Class224
{
    static final Class250 method2836(final int[] array, final boolean b, final Class178 class178, final int n) {
        try {
            if (!b) {
                return null;
            }
            int[] array2 = null;
            int[] array3 = null;
            int[] array4 = null;
            float[][] array5 = null;
            if (class178.aByteArray1420 != null) {
                final int anInt1396 = class178.anInt1396;
                final int[] array6 = new int[anInt1396];
                final int[] array7 = new int[anInt1396];
                final int[] array8 = new int[anInt1396];
                final int[] array9 = new int[anInt1396];
                final int[] array10 = new int[anInt1396];
                final int[] array11 = new int[anInt1396];
                for (int i = 0; i < anInt1396; ++i) {
                    array6[i] = Integer.MAX_VALUE;
                    array7[i] = -2147483647;
                    array8[i] = Integer.MAX_VALUE;
                    array9[i] = -2147483647;
                    array10[i] = Integer.MAX_VALUE;
                    array11[i] = -2147483647;
                }
                for (final int n2 : array) {
                    if (class178.aByteArray1420[n2] != -1) {
                        final int n3 = 0xFF & class178.aByteArray1420[n2];
                        for (int n4 = 0; ~n4 > -4; ++n4) {
                            short n5;
                            if (~n4 != -1) {
                                if (~n4 != 0xFFFFFFFE) {
                                    n5 = class178.aShortArray1392[n2];
                                }
                                else {
                                    n5 = class178.aShortArray1410[n2];
                                }
                            }
                            else {
                                n5 = class178.aShortArray1393[n2];
                            }
                            final int n6 = class178.anIntArray1416[n5];
                            final int n7 = class178.anIntArray1400[n5];
                            final int n8 = class178.anIntArray1418[n5];
                            if (array6[n3] > n6) {
                                array6[n3] = n6;
                            }
                            if (~array7[n3] > ~n6) {
                                array7[n3] = n6;
                            }
                            if (array8[n3] > n7) {
                                array8[n3] = n7;
                            }
                            if (array9[n3] < n7) {
                                array9[n3] = n7;
                            }
                            if (n8 < array10[n3]) {
                                array10[n3] = n8;
                            }
                            if (n8 > array11[n3]) {
                                array11[n3] = n8;
                            }
                        }
                    }
                }
                array5 = new float[anInt1396][];
                array2 = new int[anInt1396];
                array3 = new int[anInt1396];
                array4 = new int[anInt1396];
                for (int k = 0; k < anInt1396; ++k) {
                    final byte b2 = class178.aByteArray1388[k];
                    if (b2 > 0) {
                        array2[k] = (array7[k] + array6[k]) / 2;
                        array3[k] = (array8[k] + array9[k]) / 2;
                        array4[k] = (array10[k] + array11[k]) / 2;
                        float n10;
                        float n11;
                        float n12;
                        if (~b2 == 0xFFFFFFFE) {
                            final int n9 = class178.anIntArray1389[k];
                            n10 = 64.0f / class178.anIntArray1404[k];
                            if (n9 != 0) {
                                if (~n9 < -1) {
                                    n11 = n9 / 1024.0f;
                                    n12 = 1.0f;
                                }
                                else {
                                    n12 = -n9 / 1024.0f;
                                    n11 = 1.0f;
                                }
                            }
                            else {
                                n11 = 1.0f;
                                n12 = 1.0f;
                            }
                        }
                        else if (~b2 != 0xFFFFFFFD) {
                            n10 = class178.anIntArray1404[k] / 1024.0f;
                            n12 = class178.anIntArray1389[k] / 1024.0f;
                            n11 = class178.anIntArray1390[k] / 1024.0f;
                        }
                        else {
                            n10 = 64.0f / class178.anIntArray1404[k];
                            n12 = 64.0f / class178.anIntArray1389[k];
                            n11 = 64.0f / class178.anIntArray1390[k];
                        }
                        array5[k] = Class349.method3839((byte)123, Class202.method2702(class178.aByteArray1423[k], 255), n12, n10, class178.aShortArray1385[k], class178.aShortArray1421[k], n11, class178.aShortArray1403[k]);
                    }
                }
            }
            return new Class250(array2, array3, array4, array5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ih.D(" + ((array != null) ? "{...}" : "null") + ',' + b + ',' + ((class178 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method2837(final boolean b, final Class293[] array, final int n, final boolean b2, final int n2, final int n3) {
        try {
            if (b2) {
                for (int n4 = 0; array.length > n4; ++n4) {
                    final Class293 class293 = array[n4];
                    if (class293 != null && ~class293.anInt2234 == ~n3) {
                        Class253.method3180(n2, n, 1375731712, class293, b);
                        Class98_Sub8.method986(n, n2, class293, 119);
                        if (class293.anInt2246 > -class293.anInt2311 + class293.anInt2290) {
                            class293.anInt2246 = -class293.anInt2311 + class293.anInt2290;
                        }
                        if (-class293.anInt2258 + class293.anInt2228 < class293.anInt2213) {
                            class293.anInt2213 = class293.anInt2228 + -class293.anInt2258;
                        }
                        if (class293.anInt2246 < 0) {
                            class293.anInt2246 = 0;
                        }
                        if (class293.anInt2213 < 0) {
                            class293.anInt2213 = 0;
                        }
                        if (class293.anInt2354 == 0) {
                            Class63.method549(class293, b, (byte)61);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ih.C(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + b2 + ',' + n2 + ',' + n3 + ')');
        }
    }
}
