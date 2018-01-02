// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class146
{
    static IncomingOpcode aClass58_1179;
    static Class377 aClass377_1180;
    boolean aBoolean1181;
    static Class196 aClass196_1182;
    static int anInt1183;
    static Class aClass1184;
    
    abstract void k(final int p0);
    
    abstract int G();
    
    abstract Class87[] method2320();
    
    abstract void FA(final int p0);
    
    abstract int RA();
    
    abstract void a(final int p0);
    
    final void method2321(final int n, final int n2, final Class98_Sub46_Sub16 class98_Sub46_Sub16, final Class98_Sub46_Sub16 class98_Sub46_Sub17, final boolean[] array, final int n3, final int n4, final int n5, final Class98_Sub46_Sub16 class98_Sub46_Sub18, final int n6, final int n7, final boolean b, final Class98_Sub46_Sub16 class98_Sub46_Sub19, final int n8, final int n9) {
        try {
            if (~n2 != 0x0) {
                if (array == null || ~n5 == 0x0) {
                    this.method2338(n7, class98_Sub46_Sub16, n2, class98_Sub46_Sub17, b, 0, 112, n, n9);
                }
                else {
                    this.method2342();
                    if (!this.NA()) {
                        this.method2327();
                    }
                    else {
                        final Class7 class7 = class98_Sub46_Sub16.aClass7Array6045[n2];
                        final Class98_Sub1 aClass98_Sub1_93 = class7.aClass98_Sub1_93;
                        Class7 class8 = null;
                        if (class98_Sub46_Sub17 != null) {
                            class8 = class98_Sub46_Sub17.aClass7Array6045[n9];
                            if (aClass98_Sub1_93 != class8.aClass98_Sub1_93) {
                                class8 = null;
                            }
                        }
                        this.method2330(class7, aClass98_Sub1_93, false, class8, null, array, b, (byte)77, n7, 65535, 0, n);
                        if (n4 != 28777) {
                            this.method2326();
                        }
                        final Class7 class9 = class98_Sub46_Sub18.aClass7Array6045[n5];
                        Class7 class10 = null;
                        if (class98_Sub46_Sub19 != null) {
                            class10 = class98_Sub46_Sub19.aClass7Array6045[n6];
                            if (class10.aClass98_Sub1_93 != aClass98_Sub1_93) {
                                class10 = null;
                            }
                        }
                        this.method2344(0, new int[0], 0, 0, 0, 0, b);
                        this.method2330(class9, class9.aClass98_Sub1_93, true, class10, null, array, b, (byte)69, n8, 65535, 0, n3);
                        this.wa();
                        this.method2327();
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ka.TB(" + n + ',' + n2 + ',' + ((class98_Sub46_Sub16 != null) ? "{...}" : "null") + ',' + ((class98_Sub46_Sub17 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((class98_Sub46_Sub18 != null) ? "{...}" : "null") + ',' + n6 + ',' + n7 + ',' + b + ',' + ((class98_Sub46_Sub19 != null) ? "{...}" : "null") + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    abstract void LA(final int p0);
    
    abstract Class35[] method2322();
    
    abstract boolean r();
    
    abstract r ba(final r p0);
    
    abstract int WA();
    
    final void method2323(final int n, final int n2, final Class98_Sub46_Sub16 class98_Sub46_Sub16, final int n3, final int n4, final Class98_Sub46_Sub16 class98_Sub46_Sub17, final boolean b, final int n5, final int[] array, final int n6, final int n7) {
        try {
            if (n != -1) {
                this.method2342();
                if (!this.NA()) {
                    this.method2327();
                }
                else {
                    if (n3 != -27033) {
                        Class146.aClass196_1182 = null;
                    }
                    final Class7 class7 = class98_Sub46_Sub17.aClass7Array6045[n];
                    final Class98_Sub1 aClass98_Sub1_93 = class7.aClass98_Sub1_93;
                    Class7 class8 = null;
                    if (class98_Sub46_Sub16 != null) {
                        class8 = class98_Sub46_Sub16.aClass7Array6045[n5];
                        if (aClass98_Sub1_93 != class8.aClass98_Sub1_93) {
                            class8 = null;
                        }
                    }
                    this.method2330(class7, aClass98_Sub1_93, false, class8, array, null, b, (byte)126, n2, n4, n6, n7);
                    this.wa();
                    this.method2327();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ka.WB(" + n + ',' + n2 + ',' + ((class98_Sub46_Sub16 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + ((class98_Sub46_Sub17 != null) ? "{...}" : "null") + ',' + b + ',' + n5 + ',' + ((array != null) ? "{...}" : "null") + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    abstract int ua();
    
    abstract boolean method2324();
    
    abstract void method2325(final Class111 p0, final Class246_Sub6 p1, final int p2);
    
    abstract void H(final int p0, final int p1, final int p2);
    
    abstract void method2326();
    
    abstract void VA(final int p0);
    
    abstract void method2327();
    
    public static void method2328(final int n) {
        try {
            Class146.aClass58_1179 = null;
            Class146.aClass377_1180 = null;
            if (n != 0) {
                Class146.aClass196_1182 = null;
            }
            Class146.aClass196_1182 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ka.QB(" + n + ')');
        }
    }
    
    abstract int V();
    
    abstract void method2329(final Class111 p0, final Class246_Sub6 p1, final int p2, final int p3);
    
    abstract void aa(final short p0, final short p1);
    
    private final void method2330(final Class7 class7, final Class98_Sub1 class98_Sub1, final boolean b, final Class7 class8, final int[] array, final boolean[] array2, final boolean b2, final byte b3, final int n, final int n2, final int n3, final int n4) {
        try {
            if (class8 == null || n == 0) {
                for (int n5 = 0; class7.anInt100 > n5; ++n5) {
                    final short n6 = class7.aShortArray108[n5];
                    if (array2 == null || b == array2[n6] || class98_Sub1.anIntArray3812[n6] == 0) {
                        final short n7 = class7.aShortArray107[n5];
                        if (~n7 != 0x0) {
                            this.method2334(0, b2, 0, 0, n2 & class98_Sub1.anIntArray3815[n7], (byte)74, n3, 0, class98_Sub1.anIntArrayArray3816[n7], array);
                        }
                        this.method2334(class98_Sub1.anIntArray3812[n6], b2, class7.aShortArray94[n5], class7.aShortArray106[n5], n2 & class98_Sub1.anIntArray3815[n6], (byte)64, n3, class7.aShortArray105[n5], class98_Sub1.anIntArrayArray3816[n6], array);
                    }
                }
            }
            else {
                int n8 = 0;
                if (b3 < 52) {
                    this.method2331(null, -74, false);
                }
                int n9 = 0;
                for (short n10 = 0; ~n10 > ~class98_Sub1.anInt3809; ++n10) {
                    boolean b4 = false;
                    if (n8 < class7.anInt100 && ~class7.aShortArray108[n8] == ~n10) {
                        b4 = true;
                    }
                    boolean b5 = false;
                    if (n9 < class8.anInt100 && class8.aShortArray108[n9] == n10) {
                        b5 = true;
                    }
                    if (b4 || b5) {
                        if (array2 != null && array2[n10] == !b && ~class98_Sub1.anIntArray3812[n10] != -1) {
                            if (b5) {
                                ++n9;
                            }
                            if (b4) {
                                ++n8;
                            }
                        }
                        else {
                            int n11 = 0;
                            final int n12 = class98_Sub1.anIntArray3812[n10];
                            if (~n12 == 0xFFFFFFFC || n12 == 10) {
                                n11 = 128;
                            }
                            int n13;
                            byte b6;
                            int n14;
                            int n15;
                            int n16;
                            if (b4) {
                                n13 = class7.aShortArray107[n8];
                                b6 = class7.aByteArray99[n8];
                                n14 = class7.aShortArray106[n8];
                                n15 = class7.aShortArray105[n8];
                                n16 = class7.aShortArray94[n8];
                                ++n8;
                            }
                            else {
                                n14 = n11;
                                n13 = -1;
                                n15 = n11;
                                b6 = 0;
                                n16 = n11;
                            }
                            int n17;
                            int n18;
                            int n19;
                            int n20;
                            byte b7;
                            if (!b5) {
                                n17 = n11;
                                n18 = n11;
                                n19 = n11;
                                n20 = -1;
                                b7 = 0;
                            }
                            else {
                                n18 = class8.aShortArray105[n9];
                                n17 = class8.aShortArray106[n9];
                                b7 = class8.aByteArray99[n9];
                                n20 = class8.aShortArray107[n9];
                                n19 = class8.aShortArray94[n9];
                                ++n9;
                            }
                            int n24;
                            int n25;
                            int n26;
                            if (~(0x2 & b6) == -1 && ~(b7 & 0x1) == -1) {
                                if (~n12 == 0xFFFFFFFD) {
                                    int n21 = n19 - n16 & 0x3FFF;
                                    int n22 = n18 + -n15 & 0x3FFF;
                                    if (n21 >= 8192) {
                                        n21 -= 16384;
                                    }
                                    int n23 = n17 - n14 & 0x3FFF;
                                    if (~n22 <= -8193) {
                                        n22 -= 16384;
                                    }
                                    n24 = (0x3FFF & n * n21 / n4 + n16);
                                    if (~n23 <= -8193) {
                                        n23 -= 16384;
                                    }
                                    n25 = (n22 * n / n4 + n15 & 0x3FFF);
                                    n26 = (n23 * n / n4 + n14 & 0x3FFF);
                                }
                                else if (n12 != 9) {
                                    if (~n12 == 0xFFFFFFF8) {
                                        int n27 = n19 - n16 & 0x3F;
                                        if (n27 >= 32) {
                                            n27 -= 64;
                                        }
                                        n24 = (0x3F & n16 + n * n27 / n4);
                                        n26 = n14 + (n17 - n14) * n / n4;
                                        n25 = (n18 - n15) * n / n4 + n15;
                                    }
                                    else {
                                        n25 = n * (-n15 + n18) / n4 + n15;
                                        n24 = n16 - -((n19 + -n16) * n / n4);
                                        n26 = (n17 + -n14) * n / n4 + n14;
                                    }
                                }
                                else {
                                    int n28 = -n16 + n19 & 0x3FFF;
                                    if (n28 >= 8192) {
                                        n28 -= 16384;
                                    }
                                    n24 = (n28 * n / n4 + n16 & 0x3FFF);
                                    n26 = (n25 = 0);
                                }
                            }
                            else {
                                n25 = n15;
                                n26 = n14;
                                n24 = n16;
                            }
                            if (~n13 == 0x0) {
                                if (~n20 != 0x0) {
                                    this.method2334(0, b2, 0, 0, n2 & class98_Sub1.anIntArray3815[n20], (byte)82, n3, 0, class98_Sub1.anIntArrayArray3816[n20], array);
                                }
                            }
                            else {
                                this.method2334(0, b2, 0, 0, class98_Sub1.anIntArray3815[n13] & n2, (byte)73, n3, 0, class98_Sub1.anIntArrayArray3816[n13], array);
                            }
                            this.method2334(n12, b2, n24, n26, class98_Sub1.anIntArray3815[n10] & n2, (byte)70, n3, n25, class98_Sub1.anIntArrayArray3816[n10], array);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ka.BC(" + ((class7 != null) ? "{...}" : "null") + ',' + ((class98_Sub1 != null) ? "{...}" : "null") + ',' + b + ',' + ((class8 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + b2 + ',' + b3 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    abstract void method2331(final Class111 p0, final int p1, final boolean p2);
    
    abstract void wa();
    
    abstract int HA();
    
    abstract void method2332(final Class146 p0, final int p1, final int p2, final int p3, final boolean p4);
    
    abstract void v();
    
    abstract boolean method2333(final int p0, final int p1, final Class111 p2, final boolean p3, final int p4, final int p5);
    
    private final void method2334(final int n, final boolean b, int n2, int n3, final int n4, final byte b2, final int n5, final int n6, final int[] array, final int[] array2) {
        try {
            if (~n5 == 0xFFFFFFFE) {
                if (~n == -1 || n == 1) {
                    final int n7 = -n2;
                    n2 = n3;
                    n3 = n7;
                }
                else if (n == 3) {
                    final int n8 = n2;
                    n2 = n3;
                    n3 = n8;
                }
                else if (~n == 0xFFFFFFFD) {
                    final int n9 = n2;
                    n2 = (-n3 & 0x3FFF);
                    n3 = (n9 & 0x3FFF);
                }
            }
            else if (~n5 != 0xFFFFFFFD) {
                if (~n5 == 0xFFFFFFFC) {
                    if (n == 0 || n == 1) {
                        final int n10 = n2;
                        n2 = -n3;
                        n3 = n10;
                    }
                    else if (n != 3) {
                        if (n == 2) {
                            final int n11 = n2;
                            n2 = (0x3FFF & n3);
                            n3 = (0x3FFF & -n11);
                        }
                    }
                    else {
                        final int n12 = n2;
                        n2 = n3;
                        n3 = n12;
                    }
                }
            }
            else if (n != 0 && n != 1) {
                if (~n == 0xFFFFFFFD) {
                    n2 = (-n2 & 0x3FFF);
                    n3 = (0x3FFF & -n3);
                }
            }
            else {
                n3 = -n3;
                n2 = -n2;
            }
            if (b2 < 51) {
                Class146.aClass377_1180 = null;
            }
            if (~n4 != 0xFFFF0000) {
                this.I(n, array, n2, n6, n3, b, n4, array2);
            }
            else {
                this.method2344(n, array, n2, n6, n3, n5, b);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ka.RB(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b2 + ',' + n5 + ',' + n6 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void O(final int p0, final int p1, final int p2);
    
    abstract int da();
    
    static final void method2335() {
        for (int i = 0; i < Class226.anInt1705; ++i) {
            if (!Class21_Sub4.aBooleanArray5399[i]) {
                final Class1 class1 = Class98_Sub10_Sub31.aClass1Array5717[i];
                final Class98_Sub5 aClass98_Sub5_55 = class1.aClass98_Sub5_55;
                final int anInt57 = class1.anInt57;
                final int n = aClass98_Sub5_55.method958(5) - Class207.anInt1577;
                final int n2 = 1 + (2 * n >> Class151_Sub8.anInt5015);
                int n3 = 0;
                final int[] array = new int[n2 * n2];
                final int n4 = aClass98_Sub5_55.method954(7019) - n >> Class151_Sub8.anInt5015;
                int n5 = aClass98_Sub5_55.method962(28699) - n >> Class151_Sub8.anInt5015;
                int n6 = aClass98_Sub5_55.method962(28699) + n >> Class151_Sub8.anInt5015;
                if (n5 < 0) {
                    n3 -= n5;
                    n5 = 0;
                }
                if (n6 >= Class64_Sub9.anInt3662) {
                    n6 = Class64_Sub9.anInt3662 - 1;
                }
                for (int j = n5; j <= n6; ++j) {
                    final short n7 = class1.aShortArray59[n3];
                    int n8 = n3 * n2 + (n7 >>> 8);
                    int n9 = n4 + (n7 >>> 8);
                    int n10 = n9 + (n7 & 0xFF) - 1;
                    if (n9 < 0) {
                        n8 -= n9;
                        n9 = 0;
                    }
                    if (n10 >= Class366.anInt3112) {
                        n10 = Class366.anInt3112 - 1;
                    }
                    for (short n11 = (short)n9; n11 <= n10; ++n11) {
                        int n12 = 1;
                        final Class246_Sub3_Sub4 method931 = Class97.method931(anInt57, n11, j, (Class146.aClass1184 != null) ? Class146.aClass1184 : (Class146.aClass1184 = method2345("Class246_Sub3_Sub4")));
                        if (method931 != null && method931.aByte6161 != 0) {
                            if (method931.aByte6161 == 1) {
                                boolean b = n11 - 1 >= n9;
                                boolean b2 = n11 + 1 <= n10;
                                if (!b && j + 1 <= n6) {
                                    final short n13 = class1.aShortArray59[n3 + 1];
                                    final int n14 = n4 + (n13 >>> 8);
                                    final short n15 = (short)(n14 + (n13 & 0xFF));
                                    b = (n11 > n14 && n11 < n15);
                                }
                                if (!b2 && j - 1 >= n5) {
                                    final short n16 = class1.aShortArray59[n3 - 1];
                                    final int n17 = n4 + (n16 >>> 8);
                                    final short n18 = (short)(n17 + (n16 & 0xFF));
                                    b2 = (n11 > n17 && n11 < n18);
                                }
                                if (b && !b2) {
                                    n12 = 4;
                                }
                                else if (b2 && !b) {
                                    n12 = 2;
                                }
                            }
                            else {
                                boolean b3 = n11 - 1 >= n9;
                                boolean b4 = n11 + 1 <= n10;
                                if (!b3 && j - 1 >= n5) {
                                    final short n19 = class1.aShortArray59[n3 - 1];
                                    final int n20 = n4 + (n19 >>> 8);
                                    final short n21 = (short)(n20 + (n19 & 0xFF));
                                    b3 = (n11 > n20 && n11 < n21);
                                }
                                if (!b4 && j + 1 <= n6) {
                                    final short n22 = class1.aShortArray59[n3 + 1];
                                    final int n23 = n4 + (n22 >>> 8);
                                    final short n24 = (short)(n23 + (n22 & 0xFF));
                                    b4 = (n11 > n23 && n11 < n24);
                                }
                                if (b3 && !b4) {
                                    n12 = 3;
                                }
                                else if (b4 && !b3) {
                                    n12 = 5;
                                }
                            }
                        }
                        array[n8++] = n12;
                    }
                    ++n3;
                }
                Class21_Sub4.aBooleanArray5399[i] = true;
                Class78.aSArray594[anInt57].method3421(aClass98_Sub5_55, array);
            }
        }
    }
    
    final void method2336(final int n, final int n2, final s s, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            final int method3417 = s.method3417(n4 - -(-n2 / 2), n5 + -n / 2, true);
            final int method3418 = s.method3417(n2 / 2 + n4, -n / 2 + n5, true);
            final int method3419 = s.method3417(-n2 / 2 + n4, n / 2 + n5, true);
            final int method3420 = s.method3417(n4 + n2 / 2, n / n7 + n5, true);
            final int n9 = (~method3418 >= ~method3417) ? method3418 : method3417;
            final int n10 = (method3420 > method3419) ? method3419 : method3420;
            final int n11 = (method3420 <= method3418) ? method3420 : method3418;
            if (~n != -1) {
                int n12 = (int)(Math.atan2(-n10 + n9, n) * 2607.5945876176133) & 0x3FFF;
                if (~n12 != -1) {
                    if (~n6 != -1) {
                        if (n12 > 8192) {
                            final int n13 = 16384 - n6;
                            if (n13 > n12) {
                                n12 = n13;
                            }
                        }
                        else if (~n12 < ~n6) {
                            n12 = n6;
                        }
                    }
                    this.FA(n12);
                }
            }
            final int n14 = (~method3417 <= ~method3419) ? method3419 : method3417;
            if (n2 != 0) {
                int n15 = (int)(2607.5945876176133 * Math.atan2(-n11 + n14, n2)) & 0x3FFF;
                if (~n15 != -1) {
                    if (n8 != 0) {
                        if (~n15 >= -8193) {
                            if (~n8 > ~n15) {
                                n15 = n8;
                            }
                        }
                        else {
                            final int n16 = -n8 + 16384;
                            if (~n15 > ~n16) {
                                n15 = n16;
                            }
                        }
                    }
                    this.VA(n15);
                }
            }
            int n17 = method3417 - -method3420;
            if (~(method3418 + method3419) > ~n17) {
                n17 = method3418 + method3419;
            }
            final int n18 = -n3 + (n17 >> -1210480799);
            if (n18 != 0) {
                this.H(0, n18, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ka.AC(" + n + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    abstract void s(final int p0);
    
    abstract void method2337(final int p0, final int p1, final int p2, final int p3);
    
    abstract int fa();
    
    final void method2338(final int n, final Class98_Sub46_Sub16 class98_Sub46_Sub16, final int n2, final Class98_Sub46_Sub16 class98_Sub46_Sub17, final boolean b, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (n2 != -1) {
                this.method2342();
                if (!this.NA()) {
                    this.method2327();
                }
                else {
                    final Class7 class7 = class98_Sub46_Sub16.aClass7Array6045[n2];
                    final Class98_Sub1 aClass98_Sub1_93 = class7.aClass98_Sub1_93;
                    Class7 class8 = null;
                    if (class98_Sub46_Sub17 != null) {
                        class8 = class98_Sub46_Sub17.aClass7Array6045[n6];
                        if (aClass98_Sub1_93 != class8.aClass98_Sub1_93) {
                            class8 = null;
                        }
                    }
                    this.method2330(class7, aClass98_Sub1_93, false, class8, null, null, b, (byte)124, n, 65535, n3, n5);
                    this.wa();
                    this.method2327();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ka.SB(" + n + ',' + ((class98_Sub46_Sub16 != null) ? "{...}" : "null") + ',' + n2 + ',' + ((class98_Sub46_Sub17 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    abstract boolean method2339(final int p0, final int p1, final Class111 p2, final boolean p3, final int p4);
    
    abstract int na();
    
    abstract void P(final int p0, final int p1, final int p2, final int p3);
    
    final void method2340(final Class98_Sub46_Sub16 class98_Sub46_Sub16, final int n, final int n2) {
        try {
            if (n2 != -1) {
                this.method2342();
                if (!this.NA()) {
                    this.method2327();
                }
                else {
                    final Class7 class7 = class98_Sub46_Sub16.aClass7Array6045[n2];
                    final Class98_Sub1 aClass98_Sub1_93 = class7.aClass98_Sub1_93;
                    for (int n3 = n; ~class7.anInt100 < ~n3; ++n3) {
                        final short n4 = class7.aShortArray108[n3];
                        if (aClass98_Sub1_93.aBooleanArray3810[n4]) {
                            if (~class7.aShortArray107[n3] != 0x0) {
                                this.P(0, 0, 0, 0);
                            }
                            this.P(aClass98_Sub1_93.anIntArray3812[n4], class7.aShortArray94[n3], class7.aShortArray105[n3], class7.aShortArray106[n3]);
                        }
                    }
                    this.wa();
                    this.method2327();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ka.VB(" + ((class98_Sub46_Sub16 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    abstract void I(final int p0, final int[] p1, final int p2, final int p3, final int p4, final boolean p5, final int p6, final int[] p7);
    
    abstract void ia(final short p0, final short p1);
    
    abstract void C(final int p0);
    
    abstract void p(final int p0, final int p1, final s p2, final s p3, final int p4, final int p5, final int p6);
    
    abstract Class146 method2341(final byte p0, final int p1, final boolean p2);
    
    abstract void method2342();
    
    abstract boolean NA();
    
    abstract int EA();
    
    public Class146() {
        this.aBoolean1181 = false;
    }
    
    abstract boolean F();
    
    abstract void method2343(final Class111 p0);
    
    abstract void method2344(final int p0, final int[] p1, final int p2, final int p3, final int p4, final int p5, final boolean p6);
    
    abstract int ma();
    
    static Class method2345(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class146.aClass58_1179 = new IncomingOpcode(84, 4);
        Class146.aClass377_1180 = new Class377(64);
        Class146.aClass196_1182 = new Class196("WTRC", "office", "_rc", 1);
    }
}
