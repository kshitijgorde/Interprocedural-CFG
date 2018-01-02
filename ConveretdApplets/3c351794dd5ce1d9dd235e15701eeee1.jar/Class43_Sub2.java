import jaclib.memory.Buffer;
import jaclib.memory.Stream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class43_Sub2 extends Class43
{
    private boolean aBoolean3605;
    private Interface2_Impl1 anInterface2_Impl1_3606;
    private Interface4_Impl2 anInterface4_Impl2_3607;
    private ha_Sub3 aHa_Sub3_3608;
    
    @Override
    final void method409(final char c, final int n, final int n2, final int n3, final boolean b, final aa aa, final int n4, final int n5) {
    }
    
    @Override
    final void fa(final char c, final int n, final int n2, final int n3, final boolean b) {
        this.aHa_Sub3_3608.method1949(0);
        this.aHa_Sub3_3608.method2005(this.anInterface4_Impl2_3607, -127);
        if (this.aBoolean3605 || b) {
            this.aHa_Sub3_3608.method2019(Class249.aClass128_1903, Class288.aClass128_3381, 22831);
            this.aHa_Sub3_3608.method2051(0, -117, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_3608.method1984(2, n3);
        }
        else {
            this.aHa_Sub3_3608.method2019(Class288.aClass128_3381, Class288.aClass128_3381, 22831);
        }
        this.aHa_Sub3_3608.method1978((byte)(-26)).method2100(n, n2, 0);
        this.aHa_Sub3_3608.method1935(1);
        this.aHa_Sub3_3608.method1971(0, true, this.anInterface2_Impl1_3606);
        this.aHa_Sub3_3608.method2042(this.aHa_Sub3_3608.aClass256_4664, (byte)120);
        this.aHa_Sub3_3608.method2037(Class98_Sub46_Sub15.aClass232_6043, c * '\u0004', (byte)98, 2);
        if (this.aBoolean3605 || b) {
            this.aHa_Sub3_3608.method2051(0, -104, Class300.aClass65_2499);
        }
    }
    
    Class43_Sub2(final ha_Sub3 aHa_Sub3_3608, final Class197 class197, final Class324[] array, final boolean b) {
        super(aHa_Sub3_3608, class197);
        this.aHa_Sub3_3608 = aHa_Sub3_3608;
        int n = 0;
        for (int i = 0; i < 256; ++i) {
            final Class324 class198 = array[i];
            if (class198.anInt2720 > n) {
                n = class198.anInt2720;
            }
            if (class198.anInt2722 > n) {
                n = class198.anInt2722;
            }
        }
        final int n2 = n * 16;
        if (b) {
            final byte[] array2 = new byte[n2 * n2];
            for (int j = 0; j < 256; ++j) {
                final Class324 class199 = array[j];
                final int anInt2720 = class199.anInt2720;
                final int anInt2721 = class199.anInt2722;
                int n3 = j / 16 * n * n2 + j % 16 * n;
                int n4 = 0;
                if (class199.aByteArray2723 == null) {
                    final byte[] aByteArray2717 = class199.aByteArray2717;
                    for (int k = 0; k < anInt2720; ++k) {
                        for (int l = 0; l < anInt2721; ++l) {
                            array2[n3++] = (byte)((aByteArray2717[n4++] == 0) ? 0 : -1);
                        }
                        n3 += n2 - anInt2721;
                    }
                }
                else {
                    final byte[] aByteArray2718 = class199.aByteArray2723;
                    for (int n5 = 0; n5 < anInt2720; ++n5) {
                        for (int n6 = 0; n6 < anInt2721; ++n6) {
                            array2[n3++] = aByteArray2718[n4++];
                        }
                        n3 += n2 - anInt2721;
                    }
                }
            }
            if (!aHa_Sub3_3608.method1942(0, Class53_Sub1.aClass164_3633, Class162.aClass162_1266)) {
                final int[] array3 = new int[array2.length];
                for (int n7 = 0; n7 < array2.length; ++n7) {
                    array3[n7] = array2[n7] << 24;
                }
                this.anInterface4_Impl2_3607 = aHa_Sub3_3608.method2012(n2, n2, (byte)31, array3, false);
            }
            else {
                this.anInterface4_Impl2_3607 = aHa_Sub3_3608.method2053(n2, Class53_Sub1.aClass164_3633, (byte)87, array2, false, n2);
            }
            this.aBoolean3605 = true;
        }
        else {
            final int[] array4 = new int[n2 * n2];
            for (int n8 = 0; n8 < 256; ++n8) {
                final Class324 class200 = array[n8];
                final int[] anIntArray2718 = class200.anIntArray2718;
                final byte[] aByteArray2719 = class200.aByteArray2723;
                final byte[] aByteArray2720 = class200.aByteArray2717;
                final int anInt2722 = class200.anInt2720;
                final int anInt2723 = class200.anInt2722;
                int n9 = n8 / 16 * n * n2 + n8 % 16 * n;
                int n10 = 0;
                if (aByteArray2719 != null) {
                    for (int n11 = 0; n11 < anInt2722; ++n11) {
                        for (int n12 = 0; n12 < anInt2723; ++n12) {
                            array4[n9++] = (aByteArray2719[n10] << 24 | anIntArray2718[aByteArray2720[n10] & 0xFF]);
                            ++n10;
                        }
                        n9 += n2 - anInt2723;
                    }
                }
                else {
                    for (int n13 = 0; n13 < anInt2722; ++n13) {
                        for (int n14 = 0; n14 < anInt2723; ++n14) {
                            final byte b2;
                            if ((b2 = aByteArray2720[n10++]) != 0) {
                                array4[n9++] = (0xFF000000 | anIntArray2718[b2 & 0xFF]);
                            }
                            else {
                                ++n9;
                            }
                        }
                        n9 += n2 - anInt2723;
                    }
                }
            }
            this.anInterface4_Impl2_3607 = aHa_Sub3_3608.method2012(n2, n2, (byte)31, array4, false);
            this.aBoolean3605 = false;
        }
        this.anInterface4_Impl2_3607.method4((byte)(-81), Class342.aClass200_2861);
        (this.anInterface2_Impl1_3606 = aHa_Sub3_3608.method2060(false, 66)).method74(-20279, 20, 20480);
        for (int n15 = 0; n15 < 4; ++n15) {
            final Buffer method75 = this.anInterface2_Impl1_3606.method75(true, (byte)27);
            if (method75 != null) {
                final Stream method76 = this.aHa_Sub3_3608.method2043(24022, method75);
                final float n16 = this.anInterface4_Impl2_3607.method42((byte)(-63), n2) / n2;
                final float n17 = this.anInterface4_Impl2_3607.method45(-8473, n2) / n2;
                for (int n18 = 0; n18 < 256; ++n18) {
                    final Class324 class201 = array[n18];
                    final int anInt2724 = class201.anInt2720;
                    final int anInt2725 = class201.anInt2722;
                    final int anInt2726 = class201.anInt2721;
                    final int anInt2727 = class201.anInt2725;
                    final float n19 = n18 % 16 * n;
                    final float n20 = n18 / 16 * n;
                    final float n21 = n19 * n16;
                    final float n22 = n20 * n17;
                    final float n23 = (n19 + anInt2725) * n16;
                    final float n24 = (n20 + anInt2724) * n17;
                    if (Stream.a()) {
                        method76.b((float)anInt2727);
                        method76.b((float)anInt2726);
                        method76.b(0.0f);
                        method76.b(n21);
                        method76.b(n22);
                        method76.b((float)anInt2727);
                        method76.b((float)(anInt2726 + anInt2724));
                        method76.b(0.0f);
                        method76.b(n21);
                        method76.b(n24);
                        method76.b((float)(anInt2727 + anInt2725));
                        method76.b((float)(anInt2726 + anInt2724));
                        method76.b(0.0f);
                        method76.b(n23);
                        method76.b(n24);
                        method76.b((float)(anInt2727 + anInt2725));
                        method76.b((float)anInt2726);
                        method76.b(0.0f);
                        method76.b(n23);
                        method76.b(n22);
                    }
                    else {
                        method76.a((float)anInt2727);
                        method76.a((float)anInt2726);
                        method76.a(0.0f);
                        method76.a(n21);
                        method76.a(n22);
                        method76.a((float)anInt2727);
                        method76.a((float)(anInt2726 + anInt2724));
                        method76.a(0.0f);
                        method76.a(n21);
                        method76.a(n24);
                        method76.a((float)(anInt2727 + anInt2725));
                        method76.a((float)(anInt2726 + anInt2724));
                        method76.a(0.0f);
                        method76.a(n23);
                        method76.a(n24);
                        method76.a((float)(anInt2727 + anInt2725));
                        method76.a((float)anInt2726);
                        method76.a(0.0f);
                        method76.a(n23);
                        method76.a(n22);
                    }
                }
                method76.c();
                if (this.anInterface2_Impl1_3606.method71(13623)) {
                    break;
                }
            }
        }
    }
}
