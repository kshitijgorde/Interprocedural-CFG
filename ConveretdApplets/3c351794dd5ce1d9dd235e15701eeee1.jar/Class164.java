// 
// Decompiled by Procyon v0.5.30
// 

final class Class164
{
    static int anInt1273;
    static int anInt1274;
    int anInt1275;
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kt.toString()");
        }
    }
    
    static final void method2522(final int n, final int n2, final int n3, final aa aa, final ha ha, final int n4, final Class293 class293, final int n5, final int n6) {
        try {
            if (n4 != -2040004466) {
                method2522(-86, 94, 35, null, null, 24, null, -1, -62);
            }
            final Class24 method3807 = Class216.aClass341_1622.method3807(-72, n2);
            if (method3807 != null && method3807.aBoolean230 && method3807.method284(34, Class75.aClass140_584)) {
                if (method3807.anIntArray265 != null) {
                    final int[] array = new int[method3807.anIntArray265.length];
                    for (int n7 = 0; ~n7 > ~(array.length / 2); ++n7) {
                        int n8;
                        if (Class98_Sub46_Sub20_Sub2.anInt6319 != 4) {
                            n8 = (0x3FFF & (int)Class98_Sub22_Sub2.aFloat5794 + Class204.anInt1553);
                        }
                        else {
                            n8 = ((int)Class98_Sub22_Sub2.aFloat5794 & 0x3FFF);
                        }
                        int n9 = Class284_Sub2_Sub2.anIntArray6200[n8];
                        int n10 = Class284_Sub2_Sub2.anIntArray6202[n8];
                        if (Class98_Sub46_Sub20_Sub2.anInt6319 != 4) {
                            n10 = 256 * n10 / (256 + Class151.anInt1213);
                            n9 = 256 * n9 / (256 + Class151.anInt1213);
                        }
                        array[2 * n7] = n5 + class293.anInt2311 / 2 + (n9 * (4 * method3807.anIntArray265[2 * n7 + 1] + n6) - -(n10 * (n - -(method3807.anIntArray265[n7 * 2] * 4))) >> 1110343022);
                        array[2 * n7 + 1] = class293.anInt2258 / 2 + n3 - (n10 * (4 * method3807.anIntArray265[n7 * 2 + 1] + n6) - n9 * (4 * method3807.anIntArray265[2 * n7] + n) >> -2040004466);
                    }
                    Class136.method2272(ha, array, method3807.anInt249, class293.anIntArray2217, class293.anIntArray2298);
                    if (~method3807.anInt250 >= -1) {
                        for (int i = 0; i < -1 + array.length / 2; ++i) {
                            ha.a(array[i * 2], array[2 * i + 1], array[2 * i + 2], array[2 + (2 * i + 1)], method3807.anIntArray234[method3807.aByteArray229[i] & 0xFF], 1, aa, n5, n3);
                        }
                        ha.a(array[-2 + array.length], array[array.length - 1], array[0], array[1], method3807.anIntArray234[0xFF & method3807.aByteArray229[method3807.aByteArray229.length - 1]], 1, aa, n5, n3);
                    }
                    else {
                        for (int n11 = 0; array.length / 2 - 1 > n11; ++n11) {
                            int n12 = array[2 * n11];
                            int n13 = array[2 * n11 + 1];
                            int n14 = array[2 * (n11 + 1)];
                            int n15 = array[1 + 2 * (n11 + 1)];
                            if (n12 > n14) {
                                final int n16 = n12;
                                n12 = n14;
                                final int n17 = n13;
                                n13 = n15;
                                n14 = n16;
                                n15 = n17;
                            }
                            else if (n14 == n12 && n13 > n15) {
                                final int n18 = n13;
                                n13 = n15;
                                n15 = n18;
                            }
                            ha.a(n12, n13, n14, n15, method3807.anIntArray234[method3807.aByteArray229[n11] & 0xFF], 1, aa, n5, n3, method3807.anInt250, method3807.anInt253, method3807.anInt224);
                        }
                        int n19 = array[-2 + array.length];
                        int n20 = array[-1 + array.length];
                        int n21 = array[0];
                        int n22 = array[1];
                        if (n21 >= n19) {
                            if (~n21 == ~n19 && ~n20 < ~n22) {
                                final int n23 = n20;
                                n20 = n22;
                                n22 = n23;
                            }
                        }
                        else {
                            final int n24 = n19;
                            final int n25 = n20;
                            n19 = n21;
                            n21 = n24;
                            n20 = n22;
                            n22 = n25;
                        }
                        ha.a(n19, n20, n21, n22, method3807.anIntArray234[method3807.aByteArray229[method3807.aByteArray229.length - 1] & 0xFF], 1, aa, n5, n3, method3807.anInt250, method3807.anInt253, method3807.anInt224);
                    }
                }
                Class332 method3808 = null;
                if (~method3807.anInt245 != 0x0) {
                    method3808 = method3807.method287((byte)92, ha, false);
                    if (method3808 != null) {
                        Class4.method173(n3, n6, n5, class293, aa, n, (byte)(-24), method3808);
                    }
                }
                if (method3807.aString263 != null) {
                    int method3809 = 0;
                    if (method3808 != null) {
                        method3809 = method3808.method3731();
                    }
                    Class43 class294 = Class69_Sub2.aClass43_5336;
                    Class197 class295 = Class98_Sub46_Sub2_Sub2.aClass197_6296;
                    if (method3807.anInt264 == 1) {
                        class295 = Class98_Sub46_Sub10.aClass197_6019;
                        class294 = Class195.aClass43_1499;
                    }
                    if (~method3807.anInt264 == 0xFFFFFFFD) {
                        class295 = Class42_Sub1.aClass197_5354;
                        class294 = Class98_Sub10_Sub34.aClass43_5730;
                    }
                    OutgoingOpcode.method2540(n, n5, class295, n3, class293, method3809, (byte)109, method3807.aString263, aa, method3807.anInt257, n6, class294);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kt.A(" + n + ',' + n2 + ',' + n3 + ',' + ((aa != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n4 + ',' + ((class293 != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    Class164(final int anInt1275) {
        try {
            this.anInt1275 = anInt1275;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kt.<init>(" + anInt1275 + ')');
        }
    }
}
