// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub20 extends Class98_Sub10
{
    static IncomingOpcode aClass58_5635;
    private boolean aBoolean5636;
    private int anInt5637;
    static IncomingOpcode aClass58_5638;
    static boolean[] aBooleanArray5639;
    static int anInt5640;
    
    public Class98_Sub10_Sub20() {
        super(1, false);
        this.anInt5637 = 4096;
        this.aBoolean5636 = true;
    }
    
    static final void method1060(final int n, final int n2, final Class98_Sub22_Sub1 class98_Sub22_Sub1) {
        try {
            final boolean b = class98_Sub22_Sub1.readBits((byte)(-110), 1) == 1;
            if (b) {
                Class65.anIntArray501[Class38.anInt354++] = n;
            }
            final int bits = class98_Sub22_Sub1.readBits((byte)(-48), 2);
            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n];
            if (bits == 0) {
                if (b) {
                    class246_Sub3_Sub4_Sub2_Sub2.aBoolean6532 = false;
                }
                else {
                    if (~n == ~za_Sub2.anInt6080) {
                        throw new RuntimeException("s:lr");
                    }
                    final Class376[] aClass376Array2562 = Class306.aClass376Array2562;
                    final Class376 class376 = new Class376();
                    aClass376Array2562[n] = class376;
                    final Class376 class377 = class376;
                    class377.anInt3176 = (aa_Sub2.anInt3562 + class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0] >> 147138566) + (class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0] + Class272.anInt2038 >> -1381338778 << 1313142894) + (class246_Sub3_Sub4_Sub2_Sub2.aByte5088 << 119645116);
                    if (class246_Sub3_Sub4_Sub2_Sub2.anInt6512 != -1) {
                        class377.anInt3172 = class246_Sub3_Sub4_Sub2_Sub2.anInt6512;
                    }
                    else {
                        class377.anInt3172 = class246_Sub3_Sub4_Sub2_Sub2.aClass325_6399.method3698((byte)116);
                    }
                    class377.aBoolean3175 = class246_Sub3_Sub4_Sub2_Sub2.aBoolean6534;
                    class377.anInt3177 = class246_Sub3_Sub4_Sub2_Sub2.anInt6364;
                    if (~class246_Sub3_Sub4_Sub2_Sub2.anInt6525 < -1) {
                        Class213.method2778(true, class246_Sub3_Sub4_Sub2_Sub2);
                    }
                    Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n] = null;
                    if (class98_Sub22_Sub1.readBits((byte)(-105), 1) != 0) {
                        Class351.method3845(n2 - 14, n, class98_Sub22_Sub1);
                    }
                }
            }
            else if (bits == 1) {
                final int bits2 = class98_Sub22_Sub1.readBits((byte)(-46), 3);
                int anInt6521 = class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0];
                int anInt6522 = class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0];
                if (bits2 == 0) {
                    --anInt6521;
                    --anInt6522;
                }
                else if (~bits2 != 0xFFFFFFFE) {
                    if (~bits2 != 0xFFFFFFFD) {
                        if (bits2 == 3) {
                            --anInt6521;
                        }
                        else if (bits2 == 4) {
                            ++anInt6521;
                        }
                        else if (~bits2 == 0xFFFFFFFA) {
                            ++anInt6522;
                            --anInt6521;
                        }
                        else if (~bits2 != 0xFFFFFFF9) {
                            if (~bits2 == 0xFFFFFFF8) {
                                ++anInt6521;
                                ++anInt6522;
                            }
                        }
                        else {
                            ++anInt6522;
                        }
                    }
                    else {
                        --anInt6522;
                        ++anInt6521;
                    }
                }
                else {
                    --anInt6522;
                }
                if (!b) {
                    class246_Sub3_Sub4_Sub2_Sub2.method3057(anInt6521, anInt6522, Class98_Sub10_Sub21.aByteArray5642[n], n2 ^ 0xFFFFFFF3);
                }
                else {
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6541 = anInt6522;
                    class246_Sub3_Sub4_Sub2_Sub2.aBoolean6532 = true;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6521 = anInt6521;
                }
            }
            else if (bits == 2) {
                final int bits3 = class98_Sub22_Sub1.readBits((byte)(-85), 4);
                int anInt6523 = class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0];
                int anInt6524 = class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0];
                if (bits3 != 0) {
                    if (bits3 == 1) {
                        --anInt6523;
                        anInt6524 -= 2;
                    }
                    else if (bits3 != 2) {
                        if (bits3 == 3) {
                            ++anInt6523;
                            anInt6524 -= 2;
                        }
                        else if (bits3 == 4) {
                            anInt6524 -= 2;
                            anInt6523 += 2;
                        }
                        else if (bits3 != 5) {
                            if (bits3 != 6) {
                                if (bits3 != 7) {
                                    if (bits3 == 8) {
                                        anInt6523 += 2;
                                    }
                                    else if (bits3 == 9) {
                                        anInt6523 -= 2;
                                        ++anInt6524;
                                    }
                                    else if (bits3 != 10) {
                                        if (bits3 == 11) {
                                            anInt6523 -= 2;
                                            anInt6524 += 2;
                                        }
                                        else if (bits3 == 12) {
                                            --anInt6523;
                                            anInt6524 += 2;
                                        }
                                        else if (bits3 == 13) {
                                            anInt6524 += 2;
                                        }
                                        else if (bits3 != 14) {
                                            if (bits3 == 15) {
                                                anInt6524 += 2;
                                                anInt6523 += 2;
                                            }
                                        }
                                        else {
                                            anInt6524 += 2;
                                            ++anInt6523;
                                        }
                                    }
                                    else {
                                        ++anInt6524;
                                        anInt6523 += 2;
                                    }
                                }
                                else {
                                    anInt6523 -= 2;
                                }
                            }
                            else {
                                --anInt6524;
                                anInt6523 += 2;
                            }
                        }
                        else {
                            --anInt6524;
                            anInt6523 -= 2;
                        }
                    }
                    else {
                        anInt6524 -= 2;
                    }
                }
                else {
                    anInt6523 -= 2;
                    anInt6524 -= 2;
                }
                if (!b) {
                    class246_Sub3_Sub4_Sub2_Sub2.method3057(anInt6523, anInt6524, Class98_Sub10_Sub21.aByteArray5642[n], n2 - 13);
                }
                else {
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6541 = anInt6524;
                    class246_Sub3_Sub4_Sub2_Sub2.aBoolean6532 = true;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6521 = anInt6523;
                }
            }
            else if (class98_Sub22_Sub1.readBits((byte)(-53), 1) == 0) {
                final int bits4 = class98_Sub22_Sub1.readBits((byte)(-102), 12);
                final int n3 = bits4 >> -1711478582;
                int n4 = (bits4 & 0x3E0) >> 1325772229;
                if (~n4 < -16) {
                    n4 -= 32;
                }
                int n5 = bits4 & 0x1F;
                if (n5 > 15) {
                    n5 -= 32;
                }
                final int anInt6525 = n4 + class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0];
                final int anInt6526 = class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0] - -n5;
                if (b) {
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6521 = anInt6525;
                    class246_Sub3_Sub4_Sub2_Sub2.aBoolean6532 = true;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6541 = anInt6526;
                }
                else {
                    class246_Sub3_Sub4_Sub2_Sub2.method3057(anInt6525, anInt6526, Class98_Sub10_Sub21.aByteArray5642[n], -1);
                }
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub3 = class246_Sub3_Sub4_Sub2_Sub2;
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub4 = class246_Sub3_Sub4_Sub2_Sub2;
                final byte b2 = (byte)(0x3 & n3 + class246_Sub3_Sub4_Sub2_Sub2.aByte5088);
                class246_Sub3_Sub4_Sub2_Sub4.aByte5081 = b2;
                class246_Sub3_Sub4_Sub2_Sub3.aByte5088 = b2;
                if (Class1.method162(anInt6526, (byte)(-102), anInt6525)) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub5 = class246_Sub3_Sub4_Sub2_Sub2;
                    ++class246_Sub3_Sub4_Sub2_Sub5.aByte5081;
                }
                if (za_Sub2.anInt6080 == n) {
                    if (class246_Sub3_Sub4_Sub2_Sub2.aByte5088 != Class43.anInt377) {
                        Class358.aBoolean3033 = true;
                    }
                    Class43.anInt377 = class246_Sub3_Sub4_Sub2_Sub2.aByte5088;
                }
            }
            else {
                final int bits5 = class98_Sub22_Sub1.readBits((byte)(-103), 30);
                final int n6 = bits5 >> 28;
                final int n7 = 0x3FFF & bits5 >> 14;
                final int n8 = bits5 & 0x3FFF;
                final int anInt6527 = n7 - Class272.anInt2038;
                final int anInt6528 = n8 - aa_Sub2.anInt3562;
                if (b) {
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6541 = anInt6528;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6521 = anInt6527;
                    class246_Sub3_Sub4_Sub2_Sub2.aBoolean6532 = true;
                }
                else {
                    class246_Sub3_Sub4_Sub2_Sub2.method3057(anInt6527, anInt6528, Class98_Sub10_Sub21.aByteArray5642[n], -1);
                }
                class246_Sub3_Sub4_Sub2_Sub2.aByte5088 = (byte)n6;
                if (Class1.method162(anInt6528, (byte)(-95), anInt6527)) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub6 = class246_Sub3_Sub4_Sub2_Sub2;
                    ++class246_Sub3_Sub4_Sub2_Sub6.aByte5081;
                }
                if (n == za_Sub2.anInt6080) {
                    Class43.anInt377 = class246_Sub3_Sub4_Sub2_Sub2.aByte5088;
                }
                if (n2 != 12) {
                    Class98_Sub10_Sub20.aClass58_5635 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ma.D(" + n + ',' + n2 + ',' + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n == 1) {
                    this.aBoolean5636 = (~class98_Sub22.readUnsignedByte((byte)81) == 0xFFFFFFFE);
                }
            }
            else {
                this.anInt5637 = class98_Sub22.readShort((byte)127);
            }
            if (b > -92) {
                this.method991(124, null, (byte)77);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ma.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final byte[] method1061(final int n, final int n2, final int n3, final Object o) {
        try {
            if (o == null) {
                return null;
            }
            if (o instanceof byte[]) {
                return Class98_Sub23.method1268(n2, n3, (byte[])o, (byte)50);
            }
            if (n != 2) {
                method1061(-113, -64, 86, null);
            }
            if (o instanceof Class317) {
                return ((Class317)o).method3653(n3, n2, false);
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ma.E(" + n + ',' + n2 + ',' + n3 + ',' + ((o != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                return null;
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[] method2829 = this.method1000(n2 - 1 & za_Sub1.anInt6075, 0, 0);
                final int[] method2830 = this.method1000(n2, 0, 0);
                final int[] method2831 = this.method1000(za_Sub1.anInt6075 & n2 + 1, 0, 0);
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                for (int i = 0; i < Class25.anInt268; ++i) {
                    final int n3 = this.anInt5637 * (method2831[i] - method2829[i]);
                    final int n4 = (-method2830[i - 1 & Class329.anInt2761] + method2830[1 + i & Class329.anInt2761]) * this.anInt5637;
                    final int n5 = n4 >> -1929830068;
                    final int n6 = n3 >> -741780532;
                    final int n7 = (int)(Math.sqrt((4096 + ((n6 * n6 >> 908367244) + (n5 * n5 >> 2073615084))) / 4096.0f) * 4096.0);
                    int n8;
                    int n9;
                    int n10;
                    if (n7 != 0) {
                        n8 = n4 / n7;
                        n9 = 16777216 / n7;
                        n10 = n3 / n7;
                    }
                    else {
                        n9 = 0;
                        n8 = 0;
                        n10 = 0;
                    }
                    if (this.aBoolean5636) {
                        n8 = 2048 - -(n8 >> 2066599329);
                        n10 = (n10 >> 122551201) + 2048;
                        n9 = (n9 >> 323698081) + 2048;
                    }
                    array[i] = n8;
                    array2[i] = n10;
                    array3[i] = n9;
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ma.C(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method1062(final int n) {
        try {
            Class98_Sub10_Sub20.aClass58_5635 = null;
            Class98_Sub10_Sub20.aBooleanArray5639 = null;
            Class98_Sub10_Sub20.aClass58_5638 = null;
            if (n != 0) {
                method1062(97);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ma.B(" + n + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub20.aClass58_5635 = new IncomingOpcode(52, 4);
        Class98_Sub10_Sub20.aClass58_5638 = new IncomingOpcode(2, 2);
        Class98_Sub10_Sub20.aBooleanArray5639 = new boolean[100];
        Class98_Sub10_Sub20.anInt5640 = 0;
    }
}
