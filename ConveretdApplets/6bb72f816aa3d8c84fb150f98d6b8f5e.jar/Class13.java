// 
// Decompiled by Procyon v0.5.30
// 

final class Class13
{
    private static final Class32 aClass32_305;
    
    public static int method225(final byte[] aByteArray568, int anInt570, final byte[] aByteArray569, final int anInt571, final int anInt572) {
        synchronized (Class13.aClass32_305) {
            Class13.aClass32_305.aByteArray563 = aByteArray569;
            Class13.aClass32_305.anInt564 = anInt572;
            Class13.aClass32_305.aByteArray568 = aByteArray568;
            Class13.aClass32_305.anInt569 = 0;
            Class13.aClass32_305.anInt565 = anInt571;
            Class13.aClass32_305.anInt570 = anInt570;
            Class13.aClass32_305.anInt577 = 0;
            Class13.aClass32_305.anInt576 = 0;
            Class13.aClass32_305.anInt566 = 0;
            Class13.aClass32_305.anInt567 = 0;
            Class13.aClass32_305.anInt571 = 0;
            Class13.aClass32_305.anInt572 = 0;
            Class13.aClass32_305.anInt579 = 0;
            method227(Class13.aClass32_305);
            anInt570 -= Class13.aClass32_305.anInt570;
            return anInt570;
        }
    }
    
    private static void method226(final Class32 class32) {
        byte aByte573 = class32.aByte573;
        int anInt574 = class32.anInt574;
        int anInt575 = class32.anInt584;
        int anInt576 = class32.anInt582;
        final int[] anIntArray587 = Class32.anIntArray587;
        int anInt577 = class32.anInt581;
        final byte[] aByteArray568 = class32.aByteArray568;
        int anInt578 = class32.anInt569;
        final int anInt579;
        int i = anInt579 = class32.anInt570;
        final int n = class32.anInt601 + 1;
    Label_0410:
        while (true) {
            Label_0122: {
                if (anInt574 > 0) {
                    while (i != 0) {
                        if (anInt574 == 1) {
                            if (i == 0) {
                                anInt574 = 1;
                                break;
                            }
                            aByteArray568[anInt578] = aByte573;
                            ++anInt578;
                            --i;
                            break Label_0122;
                        }
                        else {
                            aByteArray568[anInt578] = aByte573;
                            --anInt574;
                            ++anInt578;
                            --i;
                        }
                    }
                    break;
                }
            }
            int j = 1;
            while (j != 0) {
                j = 0;
                if (anInt575 == n) {
                    anInt574 = 0;
                    break Label_0410;
                }
                aByte573 = (byte)anInt576;
                final int n2 = anIntArray587[anInt577];
                final byte b = (byte)(n2 & 0xFF);
                anInt577 = n2 >> 8;
                ++anInt575;
                if (b != anInt576) {
                    anInt576 = b;
                    if (i == 0) {
                        anInt574 = 1;
                        break Label_0410;
                    }
                    aByteArray568[anInt578] = aByte573;
                    ++anInt578;
                    --i;
                    j = 1;
                }
                else {
                    if (anInt575 != n) {
                        continue;
                    }
                    if (i == 0) {
                        anInt574 = 1;
                        break Label_0410;
                    }
                    aByteArray568[anInt578] = aByte573;
                    ++anInt578;
                    --i;
                    j = 1;
                }
            }
            anInt574 = 2;
            final int n3 = anIntArray587[anInt577];
            final byte b2 = (byte)(n3 & 0xFF);
            anInt577 = n3 >> 8;
            if (++anInt575 != n) {
                if (b2 != anInt576) {
                    anInt576 = b2;
                }
                else {
                    anInt574 = 3;
                    final int n4 = anIntArray587[anInt577];
                    final byte b3 = (byte)(n4 & 0xFF);
                    anInt577 = n4 >> 8;
                    if (++anInt575 == n) {
                        continue;
                    }
                    if (b3 != anInt576) {
                        anInt576 = b3;
                    }
                    else {
                        final int n5 = anIntArray587[anInt577];
                        final byte b4 = (byte)(n5 & 0xFF);
                        final int n6 = n5 >> 8;
                        ++anInt575;
                        anInt574 = (b4 & 0xFF) + 4;
                        final int n7 = anIntArray587[n6];
                        anInt576 = (byte)(n7 & 0xFF);
                        anInt577 = n7 >> 8;
                        ++anInt575;
                    }
                }
            }
        }
        final int anInt580 = class32.anInt571;
        class32.anInt571 += anInt579 - i;
        if (class32.anInt571 < anInt580) {
            ++class32.anInt572;
        }
        class32.aByte573 = aByte573;
        class32.anInt574 = anInt574;
        class32.anInt584 = anInt575;
        class32.anInt582 = anInt576;
        Class32.anIntArray587 = anIntArray587;
        class32.anInt581 = anInt577;
        class32.aByteArray568 = aByteArray568;
        class32.anInt569 = anInt578;
        class32.anInt570 = i;
    }
    
    private static void method227(final Class32 class32) {
        int n = 0;
        int[] array = null;
        int[] array2 = null;
        int[] array3 = null;
        class32.anInt578 = 1;
        if (Class32.anIntArray587 == null) {
            Class32.anIntArray587 = new int[class32.anInt578 * 100000];
        }
        for (int i = 1; i != 0; i = ((class32.anInt584 == class32.anInt601 + 1 && class32.anInt574 == 0) ? 1 : 0)) {
            if (method228(class32) == 23) {
                return;
            }
            method228(class32);
            method228(class32);
            method228(class32);
            method228(class32);
            method228(class32);
            ++class32.anInt579;
            method228(class32);
            method228(class32);
            method228(class32);
            method228(class32);
            class32.aBoolean575 = (method229(class32) != 0);
            if (class32.aBoolean575) {
                System.out.println("PANIC! RANDOMISED BLOCK!");
            }
            class32.anInt580 = 0;
            class32.anInt580 = (class32.anInt580 << 8 | (method228(class32) & 0xFF));
            class32.anInt580 = (class32.anInt580 << 8 | (method228(class32) & 0xFF));
            class32.anInt580 = (class32.anInt580 << 8 | (method228(class32) & 0xFF));
            for (int j = 0; j < 16; ++j) {
                class32.aBooleanArray590[j] = (method229(class32) == 1);
            }
            for (int k = 0; k < 256; ++k) {
                class32.aBooleanArray589[k] = false;
            }
            for (int l = 0; l < 16; ++l) {
                if (class32.aBooleanArray590[l]) {
                    for (int n2 = 0; n2 < 16; ++n2) {
                        if (method229(class32) == 1) {
                            class32.aBooleanArray589[l * 16 + n2] = true;
                        }
                    }
                }
            }
            method231(class32);
            final int n3 = class32.anInt588 + 2;
            final int method230 = method230(3, class32);
            final int method231 = method230(15, class32);
            for (int n4 = 0; n4 < method231; ++n4) {
                int n5 = 0;
                while (method229(class32) != 0) {
                    ++n5;
                }
                class32.aByteArray595[n4] = (byte)n5;
            }
            final byte[] array4 = new byte[6];
            for (byte b = 0; b < method230; ++b) {
                array4[b] = b;
            }
            for (int n6 = 0; n6 < method231; ++n6) {
                byte b2 = class32.aByteArray595[n6];
                final byte b3 = array4[b2];
                while (b2 > 0) {
                    array4[b2] = array4[b2 - 1];
                    --b2;
                }
                array4[0] = b3;
                class32.aByteArray594[n6] = b3;
            }
            for (byte b4 = 0; b4 < method230; ++b4) {
                int method232 = method230(5, class32);
                for (int n7 = 0; n7 < n3; ++n7) {
                    while (method229(class32) != 0) {
                        if (method229(class32) == 0) {
                            ++method232;
                        }
                        else {
                            --method232;
                        }
                    }
                    class32.aByteArrayArray596[b4][n7] = (byte)method232;
                }
            }
            for (byte b5 = 0; b5 < method230; ++b5) {
                byte b6 = 32;
                byte b7 = 0;
                for (int n8 = 0; n8 < n3; ++n8) {
                    if (class32.aByteArrayArray596[b5][n8] > b7) {
                        b7 = class32.aByteArrayArray596[b5][n8];
                    }
                    if (class32.aByteArrayArray596[b5][n8] < b6) {
                        b6 = class32.aByteArrayArray596[b5][n8];
                    }
                }
                method232(class32.anIntArrayArray597[b5], class32.anIntArrayArray598[b5], class32.anIntArrayArray599[b5], class32.aByteArrayArray596[b5], b6, b7, n3);
                class32.anIntArray600[b5] = b6;
            }
            final int n9 = class32.anInt588 + 1;
            final int n10 = 100000 * class32.anInt578;
            int n11 = -1;
            int n12 = 0;
            for (int n13 = 0; n13 <= 255; ++n13) {
                class32.anIntArray583[n13] = 0;
            }
            int n14 = 4095;
            for (int n15 = 15; n15 >= 0; --n15) {
                for (int n16 = 15; n16 >= 0; --n16) {
                    class32.aByteArray592[n14] = (byte)(n15 * 16 + n16);
                    --n14;
                }
                class32.anIntArray593[n15] = n14 + 1;
            }
            int anInt601 = 0;
            if (n12 == 0) {
                ++n11;
                n12 = 50;
                final byte b8 = class32.aByteArray594[n11];
                n = class32.anIntArray600[b8];
                array = class32.anIntArrayArray597[b8];
                array3 = class32.anIntArrayArray599[b8];
                array2 = class32.anIntArrayArray598[b8];
            }
            --n12;
            int n17;
            int method233;
            for (n17 = n, method233 = method230(n17, class32); method233 > array[n17]; ++n17, method233 = (method233 << 1 | method229(class32))) {}
            int n35;
            int method235;
            for (int n18 = array3[method233 - array2[n17]]; n18 != n9; n18 = array3[method235 - array2[n35]]) {
                if (n18 == 0 || n18 == 1) {
                    int n19 = -1;
                    int n20 = 1;
                    do {
                        if (n18 == 0) {
                            n19 += n20;
                        }
                        else if (n18 == 1) {
                            n19 += 2 * n20;
                        }
                        n20 *= 2;
                        if (n12 == 0) {
                            ++n11;
                            n12 = 50;
                            final byte b9 = class32.aByteArray594[n11];
                            n = class32.anIntArray600[b9];
                            array = class32.anIntArrayArray597[b9];
                            array3 = class32.anIntArrayArray599[b9];
                            array2 = class32.anIntArrayArray598[b9];
                        }
                        --n12;
                        int n21;
                        int method234;
                        for (n21 = n, method234 = method230(n21, class32); method234 > array[n21]; ++n21, method234 = (method234 << 1 | method229(class32))) {}
                        n18 = array3[method234 - array2[n21]];
                    } while (n18 == 0 || n18 == 1);
                    ++n19;
                    final byte b10 = class32.aByteArray591[class32.aByteArray592[class32.anIntArray593[0]] & 0xFF];
                    final int[] anIntArray583 = class32.anIntArray583;
                    final int n22 = b10 & 0xFF;
                    anIntArray583[n22] += n19;
                    while (n19 > 0) {
                        Class32.anIntArray587[anInt601] = (b10 & 0xFF);
                        ++anInt601;
                        --n19;
                    }
                }
                else {
                    int n23 = n18 - 1;
                    byte b11;
                    if (n23 < 16) {
                        final int n24 = class32.anIntArray593[0];
                        b11 = class32.aByteArray592[n24 + n23];
                        while (n23 > 3) {
                            final int n25 = n24 + n23;
                            class32.aByteArray592[n25] = class32.aByteArray592[n25 - 1];
                            class32.aByteArray592[n25 - 1] = class32.aByteArray592[n25 - 2];
                            class32.aByteArray592[n25 - 2] = class32.aByteArray592[n25 - 3];
                            class32.aByteArray592[n25 - 3] = class32.aByteArray592[n25 - 4];
                            n23 -= 4;
                        }
                        while (n23 > 0) {
                            class32.aByteArray592[n24 + n23] = class32.aByteArray592[n24 + n23 - 1];
                            --n23;
                        }
                        class32.aByteArray592[n24] = b11;
                    }
                    else {
                        int n26 = n23 / 16;
                        int n27 = class32.anIntArray593[n26] + n23 % 16;
                        b11 = class32.aByteArray592[n27];
                        while (n27 > class32.anIntArray593[n26]) {
                            class32.aByteArray592[n27] = class32.aByteArray592[n27 - 1];
                            --n27;
                        }
                        final int[] anIntArray584 = class32.anIntArray593;
                        final int n28 = n26;
                        ++anIntArray584[n28];
                        while (n26 > 0) {
                            final int[] anIntArray585 = class32.anIntArray593;
                            final int n29 = n26;
                            --anIntArray585[n29];
                            class32.aByteArray592[class32.anIntArray593[n26]] = class32.aByteArray592[class32.anIntArray593[n26 - 1] + 16 - 1];
                            --n26;
                        }
                        final int[] anIntArray586 = class32.anIntArray593;
                        final int n30 = 0;
                        --anIntArray586[n30];
                        class32.aByteArray592[class32.anIntArray593[0]] = b11;
                        if (class32.anIntArray593[0] == 0) {
                            int n31 = 4095;
                            for (int n32 = 15; n32 >= 0; --n32) {
                                for (int n33 = 15; n33 >= 0; --n33) {
                                    class32.aByteArray592[n31] = class32.aByteArray592[class32.anIntArray593[n32] + n33];
                                    --n31;
                                }
                                class32.anIntArray593[n32] = n31 + 1;
                            }
                        }
                    }
                    final int[] anIntArray587 = class32.anIntArray583;
                    final int n34 = class32.aByteArray591[b11 & 0xFF] & 0xFF;
                    ++anIntArray587[n34];
                    Class32.anIntArray587[anInt601] = (class32.aByteArray591[b11 & 0xFF] & 0xFF);
                    ++anInt601;
                    if (n12 == 0) {
                        ++n11;
                        n12 = 50;
                        final byte b12 = class32.aByteArray594[n11];
                        n = class32.anIntArray600[b12];
                        array = class32.anIntArrayArray597[b12];
                        array3 = class32.anIntArrayArray599[b12];
                        array2 = class32.anIntArrayArray598[b12];
                    }
                    --n12;
                    for (n35 = n, method235 = method230(n35, class32); method235 > array[n35]; ++n35, method235 = (method235 << 1 | method229(class32))) {}
                }
            }
            class32.anInt574 = 0;
            class32.aByte573 = 0;
            class32.anIntArray585[0] = 0;
            for (int n36 = 1; n36 <= 256; ++n36) {
                class32.anIntArray585[n36] = class32.anIntArray583[n36 - 1];
            }
            for (int n37 = 1; n37 <= 256; ++n37) {
                final int[] anIntArray588 = class32.anIntArray585;
                final int n38 = n37;
                anIntArray588[n38] += class32.anIntArray585[n37 - 1];
            }
            for (int n39 = 0; n39 < anInt601; ++n39) {
                final byte b13 = (byte)(Class32.anIntArray587[n39] & 0xFF);
                final int[] anIntArray589 = Class32.anIntArray587;
                final int n40 = class32.anIntArray585[b13 & 0xFF];
                anIntArray589[n40] |= n39 << 8;
                final int[] anIntArray590 = class32.anIntArray585;
                final int n41 = b13 & 0xFF;
                ++anIntArray590[n41];
            }
            class32.anInt581 = Class32.anIntArray587[class32.anInt580] >> 8;
            class32.anInt584 = 0;
            class32.anInt581 = Class32.anIntArray587[class32.anInt581];
            class32.anInt582 = (byte)(class32.anInt581 & 0xFF);
            class32.anInt581 >>= 8;
            ++class32.anInt584;
            class32.anInt601 = anInt601;
            method226(class32);
        }
    }
    
    private static byte method228(final Class32 class32) {
        return (byte)method230(8, class32);
    }
    
    private static byte method229(final Class32 class32) {
        return (byte)method230(1, class32);
    }
    
    private static int method230(final int n, final Class32 class32) {
        while (class32.anInt577 < n) {
            class32.anInt576 = (class32.anInt576 << 8 | (class32.aByteArray563[class32.anInt564] & 0xFF));
            class32.anInt577 += 8;
            ++class32.anInt564;
            --class32.anInt565;
            ++class32.anInt566;
            if (class32.anInt566 == 0) {
                ++class32.anInt567;
            }
        }
        final int n2 = class32.anInt576 >> class32.anInt577 - n & (1 << n) - 1;
        class32.anInt577 -= n;
        return n2;
    }
    
    private static void method231(final Class32 class32) {
        class32.anInt588 = 0;
        for (int i = 0; i < 256; ++i) {
            if (class32.aBooleanArray589[i]) {
                class32.aByteArray591[class32.anInt588] = (byte)i;
                ++class32.anInt588;
            }
        }
    }
    
    private static void method232(final int[] array, final int[] array2, final int[] array3, final byte[] array4, final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = n; i <= n2; ++i) {
            for (int j = 0; j < n3; ++j) {
                if (array4[j] == i) {
                    array3[n4] = j;
                    ++n4;
                }
            }
        }
        for (int k = 0; k < 23; ++k) {
            array2[k] = 0;
        }
        for (int l = 0; l < n3; ++l) {
            final byte b = (byte)(array4[l] + 1);
            ++array2[b];
        }
        for (int n5 = 1; n5 < 23; ++n5) {
            final int n6 = n5;
            array2[n6] += array2[n5 - 1];
        }
        for (int n7 = 0; n7 < 23; ++n7) {
            array[n7] = 0;
        }
        int n8 = 0;
        for (int n9 = n; n9 <= n2; ++n9) {
            final int n10 = n8 + (array2[n9 + 1] - array2[n9]);
            array[n9] = n10 - 1;
            n8 = n10 << 1;
        }
        for (int n11 = n + 1; n11 <= n2; ++n11) {
            array2[n11] = (array[n11 - 1] + 1 << 1) - array2[n11];
        }
    }
    
    static {
        aClass32_305 = new Class32();
    }
}
