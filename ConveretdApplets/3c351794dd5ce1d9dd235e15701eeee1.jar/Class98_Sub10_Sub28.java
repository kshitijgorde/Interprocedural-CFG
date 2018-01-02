import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub28 extends Class98_Sub10
{
    private int anInt5694;
    private int anInt5695;
    private int anInt5696;
    static IncomingOpcode aClass58_5697;
    private int anInt5698;
    private int anInt5699;
    private int anInt5700;
    private int anInt5701;
    private int anInt5702;
    private int anInt5703;
    static Class332 aClass332_5704;
    static Class98_Sub22 aClass98_Sub22_5705;
    private int anInt5706;
    
    public static void method1088(final int n) {
        try {
            Class98_Sub10_Sub28.aClass332_5704 = null;
            Class98_Sub10_Sub28.aClass98_Sub22_5705 = null;
            Class98_Sub10_Sub28.aClass58_5697 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "po.D(" + n + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n != 1) {
                    if (n != 2) {
                        if (n != 3) {
                            if (~n != 0xFFFFFFFB) {
                                if (n != 5) {
                                    if (~n != 0xFFFFFFF9) {
                                        if (n != 7) {
                                            if (~n == 0xFFFFFFF7) {
                                                this.anInt5699 = class98_Sub22.readShort((byte)127);
                                            }
                                        }
                                        else {
                                            this.anInt5694 = class98_Sub22.readShort((byte)127);
                                        }
                                    }
                                    else {
                                        this.anInt5703 = class98_Sub22.readUnsignedByte((byte)(-113));
                                    }
                                }
                                else {
                                    this.anInt5696 = class98_Sub22.readShort((byte)127);
                                }
                            }
                            else {
                                this.anInt5695 = class98_Sub22.readShort((byte)127);
                            }
                        }
                        else {
                            this.anInt5702 = class98_Sub22.readShort((byte)127);
                        }
                    }
                    else {
                        this.anInt5701 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt5698 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt5700 = class98_Sub22.readUnsignedByte((byte)(-122));
            }
            if (b > -92) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "po.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public Class98_Sub10_Sub28() {
        super(0, true);
        this.anInt5694 = 1024;
        this.anInt5696 = 1024;
        this.anInt5695 = 819;
        this.anInt5700 = 0;
        this.anInt5698 = 1024;
        this.anInt5702 = 409;
        this.anInt5699 = 1024;
        this.anInt5703 = 0;
        this.anInt5701 = 2048;
    }
    
    private final void method1089(final byte b, final int n, int n2, final Random random, final int[][] array, final int n3, final int i) {
        try {
            final int n4 = (this.anInt5699 <= 0) ? 4096 : (4096 - Class63.method546(-28737, this.anInt5699, random));
            final int n5 = this.anInt5706 * this.anInt5694 >> 1951392652;
            final int n6 = this.anInt5706 + -((~n5 >= -1) ? 0 : Class63.method546(-28737, n5, random));
            if (Class25.anInt268 <= n2) {
                n2 -= Class25.anInt268;
            }
            if (b >= 39) {
                if (n6 > 0) {
                    if (~i < -1 && n > 0) {
                        final int n7 = n / 2;
                        final int n8 = i / 2;
                        final int j = (~n6 < ~n7) ? n7 : n6;
                        final int n9 = (~n8 > ~n6) ? n8 : n6;
                        final int n10 = n2 - -j;
                        final int n11 = -(2 * j) + n;
                        for (int n12 = 0; ~n12 > ~i; ++n12) {
                            final int[] array2 = array[n12 - -n3];
                            if (~n12 > ~n9) {
                                final int n13 = n4 * n12 / n9;
                                if (this.anInt5703 == 0) {
                                    for (int n14 = 0; ~n14 > ~j; ++n14) {
                                        array2[Class202.method2702(n2 + n14, Class329.anInt2761)] = (array2[Class202.method2702(-1 + -n14 + n + n2, Class329.anInt2761)] = n14 * n4 / j * n13 >> -895330964);
                                    }
                                }
                                else {
                                    for (int n15 = 0; ~n15 > ~j; ++n15) {
                                        final int n16 = n4 * n15 / j;
                                        array2[Class202.method2702(Class329.anInt2761, n15 + n2)] = (array2[Class202.method2702(Class329.anInt2761, -n15 + n2 - (-n + 1))] = ((n16 < n13) ? n16 : n13));
                                    }
                                }
                                if (~Class25.anInt268 <= ~(n10 - -n11)) {
                                    Class236.method2896(array2, n10, n11, n13);
                                }
                                else {
                                    final int n17 = -n10 + Class25.anInt268;
                                    Class236.method2896(array2, n10, n17, n13);
                                    Class236.method2896(array2, 0, n11 + -n17, n13);
                                }
                            }
                            else {
                                final int n18 = -1 + i + -n12;
                                if (~n9 < ~n18) {
                                    final int n19 = n18 * n4 / n9;
                                    if (this.anInt5703 == 0) {
                                        for (int n20 = 0; j > n20; ++n20) {
                                            array2[Class202.method2702(Class329.anInt2761, n2 + n20)] = (array2[Class202.method2702(n2 + n - n20 - 1, Class329.anInt2761)] = n20 * n4 / j * n19 >> 1830367756);
                                        }
                                    }
                                    else {
                                        for (int k = 0; k < j; ++k) {
                                            final int n21 = k * n4 / j;
                                            array2[Class202.method2702(Class329.anInt2761, k + n2)] = (array2[Class202.method2702(Class329.anInt2761, -1 + n + (n2 + -k))] = ((n21 >= n19) ? n19 : n21));
                                        }
                                    }
                                    if (~Class25.anInt268 > ~(n10 + n11)) {
                                        final int n22 = -n10 + Class25.anInt268;
                                        Class236.method2896(array2, n10, n22, n19);
                                        Class236.method2896(array2, 0, -n22 + n11, n19);
                                    }
                                    else {
                                        Class236.method2896(array2, n10, n11, n19);
                                    }
                                }
                                else {
                                    for (int n23 = 0; j > n23; ++n23) {
                                        array2[Class202.method2702(n23 + n2, Class329.anInt2761)] = (array2[Class202.method2702(Class329.anInt2761, -n23 + (n + n2) - 1)] = n4 * n23 / j);
                                    }
                                    if (Class25.anInt268 >= n10 + n11) {
                                        Class236.method2896(array2, n10, n11, n4);
                                    }
                                    else {
                                        final int n24 = Class25.anInt268 + -n10;
                                        Class236.method2896(array2, n10, n24, n4);
                                        Class236.method2896(array2, 0, n11 + -n24, n4);
                                    }
                                }
                            }
                        }
                    }
                }
                else if (n + n2 <= Class25.anInt268) {
                    for (int n25 = 0; i > n25; ++n25) {
                        Class236.method2896(array[n25 + n3], n2, n, n4);
                    }
                }
                else {
                    final int n26 = -n2 + Class25.anInt268;
                    for (int n27 = 0; ~i < ~n27; ++n27) {
                        final int[] array3 = array[n27 + n3];
                        Class236.method2896(array3, n2, n26, n4);
                        Class236.method2896(array3, 0, n + -n26, n4);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "po.B(" + b + ',' + n + ',' + n2 + ',' + ((random != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + i + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int[][] method238 = super.aClass16_3863.method238(98);
                int n3 = 0;
                int method239 = 0;
                int n4 = 0;
                int n5 = 0;
                int n6 = 0;
                int n7 = 1;
                int n8 = 1;
                int n9 = 0;
                int n10 = 0;
                final int n11 = this.anInt5698 * Class25.anInt268 >> -697803540;
                final int n12 = this.anInt5701 * Class25.anInt268 >> 2123484780;
                final int n13 = Class63.anInt492 * this.anInt5702 >> 669504172;
                final int n14 = Class63.anInt492 * this.anInt5695 >> 1744932684;
                if (n14 <= 1) {
                    return method238[n2];
                }
                this.anInt5706 = Class25.anInt268 / 8 * this.anInt5696 >> 2057408108;
                final int n15 = 1 + Class25.anInt268 / n11;
                int[][] array = new int[n15][3];
                int[][] array2 = new int[n15][3];
                final Random random = new Random(this.anInt5700);
                while (true) {
                    int n16 = Class63.method546(n ^ 0xFFFF8F40, n12 - n11, random) + n11;
                    int n17 = Class63.method546(-28737, n14 - n13, random) + n13;
                    int anInt268 = n5 + n16;
                    if (anInt268 > Class25.anInt268) {
                        anInt268 = Class25.anInt268;
                        n16 = Class25.anInt268 + -n5;
                    }
                    int max;
                    if (n8 != 0) {
                        max = 0;
                    }
                    else {
                        int n18 = n6;
                        final int[] array3 = array2[n6];
                        int n19 = 0;
                        int n20 = n3 + anInt268;
                        if (n20 < 0) {
                            n20 += Class25.anInt268;
                        }
                        if (n20 > Class25.anInt268) {
                            n20 -= Class25.anInt268;
                        }
                        max = array3[2];
                        while (true) {
                            final int[] array4 = array2[n18];
                            if (~n20 <= ~array4[0] && array4[1] >= n20) {
                                break;
                            }
                            ++n19;
                            if (n9 > ++n18) {
                                continue;
                            }
                            n18 = 0;
                        }
                        if (n6 != n18) {
                            int n21 = n5 + n3;
                            if (n21 < 0) {
                                n21 += Class25.anInt268;
                            }
                            if (n21 > Class25.anInt268) {
                                n21 -= Class25.anInt268;
                            }
                            for (int n22 = 1; ~n19 <= ~n22; ++n22) {
                                max = Math.max(max, array2[(n22 + n6) % n9][2]);
                            }
                            for (int i = 0; i <= n19; ++i) {
                                final int[] array5 = array2[(i + n6) % n9];
                                final int n23 = array5[2];
                                if (~n23 != ~max) {
                                    final int n24 = array5[0];
                                    final int n25 = array5[1];
                                    int n26;
                                    int n27;
                                    if (~n21 <= ~n20) {
                                        if (n24 != 0) {
                                            n26 = Math.max(n21, n24);
                                            n27 = Class25.anInt268;
                                        }
                                        else {
                                            n27 = Math.min(n20, n25);
                                            n26 = 0;
                                        }
                                    }
                                    else {
                                        n26 = Math.max(n21, n24);
                                        n27 = Math.min(n20, n25);
                                    }
                                    this.method1089((byte)101, n27 - n26, n4 + n26, random, method238, n23, -n23 + max);
                                }
                            }
                        }
                        n6 = n18;
                    }
                    if (~Class63.anInt492 > ~(max - -n17)) {
                        n17 = -max + Class63.anInt492;
                    }
                    else {
                        n7 = 0;
                    }
                    if (anInt268 != Class25.anInt268) {
                        final int[] array6 = array[n10++];
                        array6[1] = anInt268;
                        array6[0] = n5;
                        array6[2] = max - -n17;
                        this.method1089((byte)96, n16, n5 + method239, random, method238, max, n17);
                        n5 = anInt268;
                    }
                    else {
                        this.method1089((byte)126, n16, n5 - -method239, random, method238, max, n17);
                        if (n7 != 0) {
                            break;
                        }
                        n7 = 1;
                        final int[] array7 = array[n10++];
                        array7[1] = anInt268;
                        array7[0] = n5;
                        array7[2] = max - -n17;
                        final int[][] array8 = array2;
                        array2 = array;
                        n9 = n10;
                        array = array8;
                        n4 = method239;
                        n10 = 0;
                        method239 = Class63.method546(-28737, Class25.anInt268, random);
                        n5 = 0;
                        int n28;
                        n3 = (n28 = -n4 + method239);
                        if (n28 < 0) {
                            n28 += Class25.anInt268;
                        }
                        n6 = 0;
                        if (n28 > Class25.anInt268) {
                            n28 -= Class25.anInt268;
                        }
                        n8 = 0;
                        while (true) {
                            final int[] array9 = array2[n6];
                            if (~array9[0] >= ~n28 && n28 <= array9[1]) {
                                break;
                            }
                            if (++n6 < n9) {
                                continue;
                            }
                            n6 = 0;
                        }
                    }
                }
            }
            if (n != 255) {
                this.anInt5696 = -8;
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "po.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            if (b != 66) {
                this.anInt5694 = 64;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "po.I(" + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub28.aClass58_5697 = new IncomingOpcode(97, 2);
    }
}
