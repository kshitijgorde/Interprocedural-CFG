import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class21 implements Interface4
{
    static int[] anIntArray3232;
    ha_Sub3_Sub2 aHa_Sub3_Sub2_3233;
    Class162 aClass162_3234;
    int anInt3235;
    private int anInt3236;
    Class164 aClass164_3237;
    private int anInt3238;
    private Class200 aClass200_3239;
    private boolean aBoolean3240;
    
    final void method256(int n, final float[] array, final boolean b, int n2, final int n3) {
        try {
            if (~n < -1 && !Class81.method815(n, 0)) {
                throw new IllegalArgumentException("");
            }
            if (n2 > 0 && !Class81.method815(n2, 0)) {
                throw new IllegalArgumentException("");
            }
            final int i = this.aClass164_3237.anInt1275;
            if (!b) {
                int n4 = 0;
                int n5 = (n < n2) ? n : n2;
                int n6 = n >> 334180961;
                int n7 = n2 >> 1896195841;
                float[] array2 = array;
                float[] array3 = new float[n6 * n7 * i];
                while (true) {
                    OpenGL.glTexImage2Df(n3, n4, this.method260(0), n, n2, 0, Class196.method2665(false, this.aClass164_3237), 5126, array2, 0);
                    if (n5 <= 1) {
                        break;
                    }
                    final int n8 = n * i;
                    for (int n9 = 0; i > n9; ++n9) {
                        int n10 = n9;
                        int n11 = n9;
                        int n12 = n11 - -n8;
                        for (int n13 = 0; ~n13 > ~n7; ++n13) {
                            for (int n14 = 0; ~n6 < ~n14; ++n14) {
                                final float n15 = array2[n11];
                                final int n16 = n11 + i;
                                final float n17 = n15 + array2[n16];
                                n11 = n16 + i;
                                final float n18 = n17 + array2[n12];
                                final int n19 = n12 + i;
                                array3[n10] = 0.25f * (n18 + array2[n19]);
                                n12 = n19 + i;
                                n10 += i;
                            }
                            n12 += n8;
                            n11 += n8;
                        }
                    }
                    final float[] array4 = array3;
                    array3 = array2;
                    n = n6;
                    n2 = n7;
                    array2 = array4;
                    ++n4;
                    n5 >>= 1;
                    n7 >>= 1;
                    n6 >>= 1;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.AA(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method257(final int n) {
        try {
            Class21.anIntArray3232 = null;
            if (n != 0) {
                method263(-76, -123);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.S(" + n + ')');
        }
    }
    
    @Override
    public final void method5(final int n) {
        try {
            if (n > 14) {
                final int method1967 = this.aHa_Sub3_Sub2_3233.method1967(109);
                final int n2 = this.aHa_Sub3_Sub2_3233.anIntArray6139[method1967];
                if (this.anInt3235 != n2) {
                    if (~n2 != -1) {
                        OpenGL.glBindTexture(n2, 0);
                        OpenGL.glDisable(n2);
                    }
                    OpenGL.glEnable(this.anInt3235);
                    this.aHa_Sub3_Sub2_3233.anIntArray6139[method1967] = this.anInt3235;
                }
                OpenGL.glBindTexture(this.anInt3235, this.anInt3238);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.I(" + n + ')');
        }
    }
    
    private final void method258(final byte b) {
        try {
            if (b != -75) {
                this.aBoolean3240 = false;
            }
            if (this.anInt3238 > 0) {
                this.aHa_Sub3_Sub2_3233.method2083(-80, this.anInt3238, this.method261((byte)(-95)));
                this.anInt3238 = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.Q(" + b + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.method258((byte)(-75));
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.finalize()");
        }
    }
    
    @Override
    public final void method4(final byte b, final Class200 aClass200_3239) {
        try {
            if (b == -81) {
                if (this.aClass200_3239 != aClass200_3239) {
                    this.aClass200_3239 = aClass200_3239;
                    this.method265(14093);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.B(" + b + ',' + ((aClass200_3239 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method259(final byte[] array, final int n, int n2, int n3, final int n4) {
        try {
            if (~n3 < -1 && !Class81.method815(n3, 0)) {
                throw new IllegalArgumentException("");
            }
            if (~n2 < -1 && !Class81.method815(n2, 0)) {
                throw new IllegalArgumentException("");
            }
            final int anInt1275 = this.aClass164_3237.anInt1275;
            int n5 = 0;
            if (n <= 0) {
                this.aClass164_3237 = null;
            }
            int n6 = (~n3 <= ~n2) ? n2 : n3;
            int n7 = n3 >> 1681309249;
            int i = n2 >> -1939080479;
            byte[] array2 = array;
            byte[] array3 = new byte[n7 * (i * anInt1275)];
            while (true) {
                OpenGL.glTexImage2Dub(n4, n5, this.method260(0), n3, n2, 0, Class196.method2665(false, this.aClass164_3237), 5121, array2, 0);
                if (n6 <= 1) {
                    break;
                }
                final int n8 = n3 * anInt1275;
                final byte[] array4 = array3;
                for (int n9 = 0; ~n9 > ~anInt1275; ++n9) {
                    int n10 = n9;
                    int n11 = n9;
                    int n12 = n8 + n11;
                    for (int n13 = 0; i > n13; ++n13) {
                        for (int j = 0; j < n7; ++j) {
                            final byte b = array2[n11];
                            final int n14 = n11 + anInt1275;
                            final byte b2 = (byte)(b + array2[n14] + array2[n12]);
                            n11 = n14 + anInt1275;
                            final int n15 = n12 + anInt1275;
                            final byte b3 = (byte)(b2 + array2[n15]);
                            n12 = n15 + anInt1275;
                            array3[n10] = (byte)(b3 >> 1220013666);
                            n10 += anInt1275;
                        }
                        n11 += n8;
                        n12 += n8;
                    }
                }
                array3 = array2;
                array2 = array4;
                n3 = n7;
                n2 = i;
                i >>= 1;
                ++n5;
                n7 >>= 1;
                n6 >>= 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.U(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final int method260(final int n) {
        try {
            if (n != 0) {
                this.method266(94, (byte)(-47));
            }
            if (Class162.aClass162_1266 == this.aClass162_3234) {
                if (Class98_Sub40.aClass164_4190 == this.aClass164_3237) {
                    return 6407;
                }
                if (this.aClass164_3237 == Class62.aClass164_486) {
                    return 6408;
                }
                if (Class53_Sub1.aClass164_3633 == this.aClass164_3237) {
                    return 6406;
                }
                if (this.aClass164_3237 == Class98_Sub30.aClass164_4088) {
                    return 6409;
                }
                if (this.aClass164_3237 == Class74.aClass164_547) {
                    return 6410;
                }
                if (this.aClass164_3237 == Class280.aClass164_2101) {
                    return 6145;
                }
            }
            else if (Class162.aClass162_1269 == this.aClass162_3234) {
                if (this.aClass164_3237 == Class98_Sub40.aClass164_4190) {
                    return 34843;
                }
                if (Class62.aClass164_486 == this.aClass164_3237) {
                    return 34842;
                }
                if (this.aClass164_3237 == Class53_Sub1.aClass164_3633) {
                    return 34844;
                }
                if (this.aClass164_3237 == Class98_Sub30.aClass164_4088) {
                    return 34846;
                }
                if (this.aClass164_3237 == Class74.aClass164_547) {
                    return 34847;
                }
                if (Class280.aClass164_2101 == this.aClass164_3237) {
                    return 6145;
                }
            }
            else if (Class162.aClass162_1270 == this.aClass162_3234) {
                if (Class98_Sub40.aClass164_4190 == this.aClass164_3237) {
                    return 34837;
                }
                if (Class62.aClass164_486 == this.aClass164_3237) {
                    return 34836;
                }
                if (Class53_Sub1.aClass164_3633 == this.aClass164_3237) {
                    return 34838;
                }
                if (Class98_Sub30.aClass164_4088 == this.aClass164_3237) {
                    return 34840;
                }
                if (Class74.aClass164_547 == this.aClass164_3237) {
                    return 34841;
                }
                if (this.aClass164_3237 == Class280.aClass164_2101) {
                    return 6145;
                }
            }
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.P(" + n + ')');
        }
    }
    
    private final int method261(final byte b) {
        try {
            final int n = this.aClass162_3234.anInt1263 * (this.aClass164_3237.anInt1275 * this.anInt3236);
            if (this.aBoolean3240) {
                return n * 4 / 3;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.CA(" + b + ')');
        }
    }
    
    static final String method262(final Class98_Sub46_Sub9 class98_Sub46_Sub9, final byte b) {
        try {
            if (b <= 11) {
                Class21.anIntArray3232 = null;
            }
            return class98_Sub46_Sub9.aString5998 + " <col=ffffff>>";
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.T(" + ((class98_Sub46_Sub9 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final Class66 method263(final int anInt509, final int n) {
        try {
            if (n != 31866) {
                method263(20, 98);
            }
            final Class66 class66 = (Class66)RuntimeException_Sub1.aClass79_3204.method802(-126, anInt509);
            if (class66 != null) {
                return class66;
            }
            final byte[] method2745 = Class64_Sub3.aClass207_3648.method2745(anInt509, 1, false);
            final Class66 class67 = new Class66();
            class67.anInt509 = anInt509;
            if (method2745 != null) {
                class67.method682(new Class98_Sub22(method2745), n - 14739);
            }
            class67.method685(n - 31865);
            if (class67.anInt508 == 2 && Class294.aClass377_2397.method3990(anInt509, -1) == null) {
                Class294.aClass377_2397.method3996(new Class98_Sub34(Class64_Sub19.anInt3693), anInt509, -1);
                Class336.aClass66Array2828[Class64_Sub19.anInt3693++] = class67;
            }
            RuntimeException_Sub1.aClass79_3204.method805(anInt509, class67, (byte)(-80));
            return class67;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.BA(" + anInt509 + ',' + n + ')');
        }
    }
    
    final void method264(int n, final int[] array, int n2, final int n3, final int n4) {
        try {
            if (~n < -1 && !Class81.method815(n, 0)) {
                throw new IllegalArgumentException("");
            }
            if (n2 > 0 && !Class81.method815(n2, 0)) {
                throw new IllegalArgumentException("");
            }
            if (this.aClass164_3237 != Class62.aClass164_486) {
                throw new IllegalArgumentException("");
            }
            int n5 = 0;
            int n6 = (n < n2) ? n : n2;
            int n7 = n >> 962792001;
            int n8 = n2 >> -1036265535;
            if (n3 != 526364520) {
                this.method264(-17, null, 58, 71, 102);
            }
            int[] array2 = array;
            int[] array3 = new int[n8 * n7];
            while (true) {
                OpenGL.glTexImage2Di(n4, n5, this.method260(0), n, n2, 0, 32993, this.aHa_Sub3_Sub2_3233.anInt6135, array2, 0);
                if (~n6 >= -2) {
                    break;
                }
                int n9 = 0;
                int n10 = 0;
                int n11 = n10 + n;
                for (int n12 = 0; ~n8 < ~n12; ++n12) {
                    for (int n13 = 0; ~n7 < ~n13; ++n13) {
                        final int n14 = array2[n10++];
                        final int n15 = array2[n10++];
                        final int n16 = array2[n11++];
                        final int n17 = 0xFF & n14 >> 526364520;
                        final int n18 = n14 & 0xFF;
                        final int n19 = 0xFF & n14 >> -525338288;
                        final int n20 = 0xFF & n14 >> 820031896;
                        final int n21 = array2[n11++];
                        array3[n9++] = Class41.method366(Class202.method2702(1020, n18 + (0xFF & n15) + (n16 & 0xFF) + (0xFF & n21)) >> -1355627262, Class41.method366(Class41.method366(Class202.method2702(1020, n20 + (n15 >> 2040047352 & 0xFF) + (0xFF & n16 >> -1794750664) + (0xFF & n21 >> 1563658584)) << 196350582, Class202.method2702(1020, n19 + ((0xFF9127 & n15) >> -805414512) + ((n16 & 0xFF9E4B) >> -881369232) + (0xFF & n21 >> 1468342096)) << 2112598990), Class202.method2702(1020, n17 + (n15 >> 1137032648 & 0xFF) + (0xFF & n16 >> 992818344) + ((0xFF51 & n21) >> -450789720)) << -1871880090));
                    }
                    n10 += n;
                    n11 += n;
                }
                final int[] array4 = array3;
                array3 = array2;
                n = n7;
                n2 = n8;
                array2 = array4;
                n8 >>= 1;
                n7 >>= 1;
                n6 >>= 1;
                ++n5;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.W(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    private final void method265(final int n) {
        try {
            this.aHa_Sub3_Sub2_3233.method2005(this, -122);
            if (n == 14093) {
                if (Class284_Sub1_Sub1.aClass200_6187 != this.aClass200_3239) {
                    OpenGL.glTexParameteri(this.anInt3235, 10241, this.aBoolean3240 ? 9984 : 9728);
                    OpenGL.glTexParameteri(this.anInt3235, 10240, 9728);
                }
                else {
                    OpenGL.glTexParameteri(this.anInt3235, 10241, this.aBoolean3240 ? 9987 : 9729);
                    OpenGL.glTexParameteri(this.anInt3235, 10240, 9729);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.R(" + n + ')');
        }
    }
    
    Class21(final ha_Sub3_Sub2 aHa_Sub3_Sub2_3233, final int anInt3235, final Class164 aClass164_3237, final Class162 aClass162_3234, final int anInt3236, final boolean aBoolean3240) {
        this.aClass200_3239 = Class284_Sub1_Sub1.aClass200_6187;
        try {
            this.anInt3235 = anInt3235;
            this.aClass164_3237 = aClass164_3237;
            this.aClass162_3234 = aClass162_3234;
            this.aBoolean3240 = aBoolean3240;
            this.aHa_Sub3_Sub2_3233 = aHa_Sub3_Sub2_3233;
            this.anInt3236 = anInt3236;
            OpenGL.glGenTextures(1, Class342.anIntArray2860, 0);
            this.anInt3238 = Class342.anIntArray2860[0];
            this.method265(14093);
            this.method266(0, (byte)123);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.<init>(" + ((aHa_Sub3_Sub2_3233 != null) ? "{...}" : "null") + ',' + anInt3235 + ',' + ((aClass164_3237 != null) ? "{...}" : "null") + ',' + ((aClass162_3234 != null) ? "{...}" : "null") + ',' + anInt3236 + ',' + aBoolean3240 + ')');
        }
    }
    
    private final void method266(final int n, final byte b) {
        try {
            if (b <= 107) {
                this.aClass162_3234 = null;
            }
            final ha_Sub3_Sub2 aHa_Sub3_Sub2_3233 = this.aHa_Sub3_Sub2_3233;
            aHa_Sub3_Sub2_3233.anInt4541 -= n;
            final ha_Sub3_Sub2 aHa_Sub3_Sub2_3234 = this.aHa_Sub3_Sub2_3233;
            aHa_Sub3_Sub2_3234.anInt4541 += this.method261((byte)(-91));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bj.V(" + n + ',' + b + ')');
        }
    }
    
    static {
        Class21.anIntArray3232 = new int[4];
    }
}
