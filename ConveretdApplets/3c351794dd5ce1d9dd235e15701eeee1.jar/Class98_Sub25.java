import jaclib.memory.heap.NativeHeapBuffer;
import jaclib.memory.Source;
import jaclib.memory.Buffer;
import jaclib.memory.Stream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub25 extends Class98
{
    private Interface2_Impl2 anInterface2_Impl2_4009;
    private int anInt4010;
    private int anInt4011;
    private Class98_Sub5 aClass98_Sub5_4012;
    private int anInt4013;
    private int anInt4014;
    private ha_Sub3 aHa_Sub3_4015;
    private Stream aStream4016;
    private Interface2_Impl1 anInterface2_Impl1_4017;
    private int anInt4018;
    private float[][] aFloatArrayArray4019;
    private Stream aStream4020;
    private int anInt4021;
    private Class377 aClass377_4022;
    private float[][] aFloatArrayArray4023;
    static int anInt4024;
    private float[][] aFloatArrayArray4025;
    static String[] aStringArray4026;
    private s_Sub2 aS_Sub2_4027;
    
    final void method1270(final int n, final boolean[][] array, final int n2, final int n3, final int n4) {
        try {
            if (this.anInterface2_Impl2_4009 != null && n2 - -n4 >= this.anInt4010 && this.anInt4014 >= n2 - n4 && this.anInt4013 <= n3 - -n4 && ~this.anInt4021 <= ~(-n4 + n3)) {
                if (n != 0) {
                    this.anInt4018 = -100;
                }
                for (int anInt4013 = this.anInt4013; ~this.anInt4021 <= ~anInt4013; ++anInt4013) {
                    for (int i = this.anInt4010; i <= this.anInt4014; ++i) {
                        final int n5 = -n2 + i;
                        final int n6 = anInt4013 - n3;
                        if (~n5 < ~(-n4) && ~n5 > ~n4 && ~(-n4) > ~n6 && ~n4 < ~n6 && array[n4 + n5][n4 + n6]) {
                            this.aHa_Sub3_4015.method1954(4, (byte)(255.0f * this.aClass98_Sub5_4012.method956(false)));
                            this.aHa_Sub3_4015.method1971(0, true, this.anInterface2_Impl1_4017);
                            this.aHa_Sub3_4015.method2042(this.aHa_Sub3_4015.aClass256_4659, (byte)86);
                            this.aHa_Sub3_4015.method1973(Class336.aClass232_2822, this.anInt4011, 0, 26810, this.anInterface2_Impl2_4009, 0, this.anInt4018 / 3);
                            return;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jq.E(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    public static void method1271(final int n) {
        try {
            Class98_Sub25.aStringArray4026 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jq.A(" + n + ')');
        }
    }
    
    private final void method1272(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            long n8 = -1L;
            final int n9 = n7 - -(n2 << this.aS_Sub2_4027.anInt2200);
            final int n10 = (n3 << this.aS_Sub2_4027.anInt2200) + n5;
            final int method3417 = this.aS_Sub2_4027.method3417(n9, n10, true);
            if ((0x7F & n7) == 0x0 || (0x7F & n5) == 0x0) {
                n8 = ((n10 << 519790224 & 0xFFFF0000L) | (n9 & 0xFFFFL));
                final Class98 method3418 = this.aClass377_4022.method3990(n8, -1);
                if (method3418 != null) {
                    this.method1273(((Class98_Sub40)method3418).aShort4191, -29314);
                    return;
                }
            }
            final short n11 = (short)(this.anInt4011++);
            if (~n8 != 0x0L) {
                this.aClass377_4022.method3996(new Class98_Sub40(n11), n8, n4 - 1);
            }
            float n12;
            float n13;
            float n14;
            if (n7 == 0 && ~n5 == -1) {
                n12 = this.aFloatArrayArray4023[n6][n];
                n13 = this.aFloatArrayArray4025[n6][n];
                n14 = this.aFloatArrayArray4019[n6][n];
            }
            else if (~this.aS_Sub2_4027.anInt2206 != ~n7 || ~n5 != -1) {
                if (n7 != this.aS_Sub2_4027.anInt2206 || n5 != this.aS_Sub2_4027.anInt2206) {
                    if (n7 != 0 || this.aS_Sub2_4027.anInt2206 != n5) {
                        final float n15 = n7 / this.aS_Sub2_4027.anInt2206;
                        final float n16 = n5 / this.aS_Sub2_4027.anInt2206;
                        final float n17 = this.aFloatArrayArray4019[n6][n];
                        final float n18 = this.aFloatArrayArray4025[n6][n];
                        final float n19 = this.aFloatArrayArray4023[n6][n];
                        final float n20 = this.aFloatArrayArray4019[1 + n6][n];
                        final float n21 = this.aFloatArrayArray4025[1 + n6][n];
                        final float n22 = n19 + n15 * (-n19 + this.aFloatArrayArray4023[n6][1 + n]);
                        final float n23 = n18 + n15 * (-n18 + this.aFloatArrayArray4025[n6][1 + n]);
                        final float n24 = n17 + n15 * (this.aFloatArrayArray4019[n6][1 + n] - n17);
                        final float n25 = this.aFloatArrayArray4023[n6 + 1][n];
                        final float n26 = n21 + n15 * (-n21 + this.aFloatArrayArray4025[1 + n6][1 + n]);
                        final float n27 = n20 + (this.aFloatArrayArray4019[1 + n6][1 + n] - n20) * n15;
                        n13 = n23 + n16 * (-n23 + n26);
                        final float n28 = n25 + n15 * (this.aFloatArrayArray4023[1 + n6][1 + n] - n25);
                        n14 = n24 + (-n24 + n27) * n16;
                        n12 = n22 + (-n22 + n28) * n16;
                    }
                    else {
                        n12 = this.aFloatArrayArray4023[n6][1 + n];
                        n14 = this.aFloatArrayArray4019[n6][1 + n];
                        n13 = this.aFloatArrayArray4025[n6][1 + n];
                    }
                }
                else {
                    n12 = this.aFloatArrayArray4023[1 + n6][n + 1];
                    n14 = this.aFloatArrayArray4019[1 + n6][1 + n];
                    n13 = this.aFloatArrayArray4025[1 + n6][n + 1];
                }
            }
            else {
                n13 = this.aFloatArrayArray4025[n6 + 1][n];
                n12 = this.aFloatArrayArray4023[1 + n6][n];
                n14 = this.aFloatArrayArray4019[n6 + 1][n];
            }
            final float n29 = this.aClass98_Sub5_4012.method954(n4 + 7019) - n9;
            final float n30 = -method3417 + this.aClass98_Sub5_4012.method963((byte)103);
            final float n31 = this.aClass98_Sub5_4012.method962(n4 ^ 0x701B) + -n10;
            final float n32 = (float)Math.sqrt(n30 * n30 + n29 * n29 + n31 * n31);
            final float n33 = 1.0f / n32;
            final float n34 = n29 * n33;
            final float n35 = n31 * n33;
            final float n36 = n30 * n33;
            final float n37 = n32 / this.aClass98_Sub5_4012.method958(125);
            float n38 = 1.0f - n37 * n37;
            if (n38 < 0.0f) {
                n38 = 0.0f;
            }
            float n39 = n35 * n12 + (n34 * n14 + n36 * n13);
            if (n4 > n39) {
                n39 = 0.0f;
            }
            float n40 = n39 * n38 * 2.0f;
            if (n40 > 1.0f) {
                n40 = 1.0f;
            }
            final int method3419 = this.aClass98_Sub5_4012.method961((byte)(-78));
            int n41 = (int)(n40 * ((method3419 & 0xFFD717) >> -694879952));
            if (~n41 < -256) {
                n41 = 255;
            }
            int n42 = (int)((0xFF & method3419 >> 148226344) * n40);
            if (~n42 < -256) {
                n42 = 255;
            }
            int n43 = (int)(n40 * (method3419 & 0xFF));
            if (n43 > 255) {
                n43 = 255;
            }
            if (Stream.a()) {
                this.aStream4016.b((float)n9);
                this.aStream4016.b((float)method3417);
                this.aStream4016.b((float)n10);
            }
            else {
                this.aStream4016.a((float)n9);
                this.aStream4016.a((float)method3417);
                this.aStream4016.a((float)n10);
            }
            if (this.aHa_Sub3_4015.anInt4580 == 0) {
                this.aStream4016.e(n43);
                this.aStream4016.e(n42);
                this.aStream4016.e(n41);
            }
            else {
                this.aStream4016.e(n41);
                this.aStream4016.e(n42);
                this.aStream4016.e(n43);
            }
            this.aStream4016.e(255);
            this.method1273(n11, -29314);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jq.D(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    private final void method1273(final short n, final int n2) {
        try {
            if (Stream.a()) {
                this.aStream4020.c(n);
                if (!client.aBoolean3553) {
                    return;
                }
            }
            this.aStream4020.d(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jq.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method1274(final int n) {
        try {
            ++Class75.anInt581;
            return Class76_Sub8.aBoolean3771 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jq.B(" + n + ')');
        }
    }
    
    Class98_Sub25(final ha_Sub3 aHa_Sub3_4015, final s_Sub2 as_Sub2_4027, final Class98_Sub5 aClass98_Sub5_4012, final int[] array) {
        try {
            this.aS_Sub2_4027 = as_Sub2_4027;
            this.aHa_Sub3_4015 = aHa_Sub3_4015;
            this.aClass98_Sub5_4012 = aClass98_Sub5_4012;
            final int n = this.aClass98_Sub5_4012.method958(-75) + -(as_Sub2_4027.anInt2206 >> 573132577);
            this.anInt4010 = -n + this.aClass98_Sub5_4012.method954(7019) >> as_Sub2_4027.anInt2200;
            this.anInt4014 = n + this.aClass98_Sub5_4012.method954(7019) >> as_Sub2_4027.anInt2200;
            this.anInt4013 = -n + this.aClass98_Sub5_4012.method962(28699) >> as_Sub2_4027.anInt2200;
            this.anInt4021 = this.aClass98_Sub5_4012.method962(28699) + n >> as_Sub2_4027.anInt2200;
            final int n2 = 1 + (this.anInt4014 + -this.anInt4010);
            final int n3 = 1 + -this.anInt4013 + this.anInt4021;
            this.aFloatArrayArray4023 = new float[1 + n2][1 + n3];
            this.aFloatArrayArray4019 = new float[n2 + 1][1 + n3];
            this.aFloatArrayArray4025 = new float[1 + n2][n3 + 1];
            for (int n4 = 0; ~n4 >= ~n3; ++n4) {
                final int n5 = this.anInt4013 + n4;
                if (~n5 < -1 && this.aS_Sub2_4027.anInt2204 - 1 > n5) {
                    for (int n6 = 0; ~n6 >= ~n2; ++n6) {
                        final int n7 = n6 + this.anInt4010;
                        if (n7 > 0 && this.aS_Sub2_4027.anInt2203 - 1 > n7) {
                            final int n8 = as_Sub2_4027.method3420(n5, -12639, 1 + n7) + -as_Sub2_4027.method3420(n5, -12639, -1 + n7);
                            final int n9 = as_Sub2_4027.method3420(1 + n5, -12639, n7) + -as_Sub2_4027.method3420(n5 - 1, -12639, n7);
                            final float n10 = (float)(1.0 / Math.sqrt(65536 + n8 * n8 + n9 * n9));
                            this.aFloatArrayArray4019[n6][n4] = n8 * n10;
                            this.aFloatArrayArray4025[n6][n4] = n10 * -256.0f;
                            this.aFloatArrayArray4023[n6][n4] = n9 * n10;
                        }
                    }
                }
            }
            int n11 = 0;
            for (int i = this.anInt4013; i <= this.anInt4021; ++i) {
                if (i < 0 || i >= as_Sub2_4027.anInt2204) {
                    n11 += this.anInt4014 - this.anInt4010;
                }
                else {
                    for (int anInt4010 = this.anInt4010; ~anInt4010 >= ~this.anInt4014; ++anInt4010) {
                        if (anInt4010 >= 0 && as_Sub2_4027.anInt2203 > anInt4010) {
                            final int n12 = array[n11];
                            final int[] array2 = as_Sub2_4027.anIntArrayArrayArray5241[anInt4010][i];
                            if (array2 != null && ~n12 != -1) {
                                if (~n12 == 0xFFFFFFFE) {
                                    int n13 = 0;
                                    while (~n13 > ~array2.length) {
                                        if (~array2[n13++] != 0x0 && array2[n13++] != -1 && array2[n13++] != -1) {
                                            this.anInt4018 += 3;
                                        }
                                    }
                                }
                                else {
                                    this.anInt4018 += 3;
                                }
                            }
                        }
                        ++n11;
                    }
                }
            }
            if (this.anInt4018 <= 0) {
                this.anInterface2_Impl1_4017 = null;
                this.anInterface2_Impl2_4009 = null;
            }
            else {
                this.aClass377_4022 = new Class377(Class48.method453(423660257, this.anInt4018));
                (this.anInterface2_Impl2_4009 = this.aHa_Sub3_4015.method1990((byte)84, false)).method76(this.anInt4018, 20779);
                final NativeHeapBuffer method1947 = this.aHa_Sub3_4015.method1947(16 * this.anInt4018, false, 0);
                this.aStream4016 = new Stream(method1947);
                while (true) {
                    final Buffer method1948 = this.anInterface2_Impl2_4009.method78(true, -90);
                    if (method1948 != null) {
                        this.aStream4020 = new Stream(method1948);
                        int n14 = 0;
                        int n15 = 0;
                        for (int anInt4011 = this.anInt4013; ~anInt4011 >= ~this.anInt4021; ++anInt4011) {
                            if (anInt4011 >= 0 && as_Sub2_4027.anInt2204 > anInt4011) {
                                int n16 = 0;
                                for (int j = this.anInt4010; j <= this.anInt4014; ++j) {
                                    if (j >= 0 && ~j > ~as_Sub2_4027.anInt2203) {
                                        final int n17 = array[n14];
                                        final int[] array3 = as_Sub2_4027.anIntArrayArrayArray5241[j][anInt4011];
                                        if (array3 != null && ~n17 != -1) {
                                            if (n17 != 1) {
                                                if (n17 != 3) {
                                                    if (n17 == 2) {
                                                        this.method1272(n15, j, anInt4011, 0, 0, n16, as_Sub2_4027.anInt2206);
                                                        this.method1272(n15, j, anInt4011, 0, as_Sub2_4027.anInt2206, n16, as_Sub2_4027.anInt2206);
                                                        this.method1272(n15, j, anInt4011, 0, 0, n16, 0);
                                                    }
                                                    else if (~n17 == 0xFFFFFFFA) {
                                                        this.method1272(n15, j, anInt4011, 0, as_Sub2_4027.anInt2206, n16, as_Sub2_4027.anInt2206);
                                                        this.method1272(n15, j, anInt4011, 0, as_Sub2_4027.anInt2206, n16, 0);
                                                        this.method1272(n15, j, anInt4011, 0, 0, n16, as_Sub2_4027.anInt2206);
                                                    }
                                                    else if (n17 == 4) {
                                                        this.method1272(n15, j, anInt4011, 0, as_Sub2_4027.anInt2206, n16, 0);
                                                        this.method1272(n15, j, anInt4011, 0, 0, n16, 0);
                                                        this.method1272(n15, j, anInt4011, 0, as_Sub2_4027.anInt2206, n16, as_Sub2_4027.anInt2206);
                                                    }
                                                }
                                                else {
                                                    this.method1272(n15, j, anInt4011, 0, 0, n16, 0);
                                                    this.method1272(n15, j, anInt4011, 0, 0, n16, as_Sub2_4027.anInt2206);
                                                    this.method1272(n15, j, anInt4011, 0, as_Sub2_4027.anInt2206, n16, 0);
                                                }
                                            }
                                            else {
                                                final int[] array4 = as_Sub2_4027.anIntArrayArrayArray5226[j][anInt4011];
                                                final int[] array5 = as_Sub2_4027.anIntArrayArrayArray5240[j][anInt4011];
                                                int n18 = 0;
                                                while (~n18 > ~array3.length) {
                                                    if (array3[n18] != -1 && array3[1 + n18] != -1 && ~array3[2 + n18] != 0x0) {
                                                        this.method1272(n15, j, anInt4011, 0, array5[n18], n16, array4[n18]);
                                                        ++n18;
                                                        this.method1272(n15, j, anInt4011, 0, array5[n18], n16, array4[n18]);
                                                        ++n18;
                                                        this.method1272(n15, j, anInt4011, 0, array5[n18], n16, array4[n18]);
                                                        ++n18;
                                                    }
                                                    else {
                                                        n18 += 3;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    ++n16;
                                    ++n14;
                                }
                            }
                            else {
                                n14 += -this.anInt4010 + this.anInt4014;
                            }
                            ++n15;
                        }
                        this.aStream4020.c();
                        if (this.anInterface2_Impl2_4009.method79((byte)(-96))) {
                            break;
                        }
                        this.aStream4016.b(0);
                        this.aClass377_4022.method3994(-64);
                    }
                }
                this.aStream4016.c();
                (this.anInterface2_Impl1_4017 = this.aHa_Sub3_4015.method2060(false, 65)).method73((byte)(-112), this.anInt4011 * 16, 16, method1947);
            }
            final float[][] aFloatArrayArray4019 = null;
            this.aFloatArrayArray4023 = aFloatArrayArray4019;
            this.aFloatArrayArray4025 = aFloatArrayArray4019;
            this.aFloatArrayArray4019 = aFloatArrayArray4019;
            this.aStream4016 = null;
            this.aClass377_4022 = null;
            this.aStream4020 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jq.<init>(" + ((aHa_Sub3_4015 != null) ? "{...}" : "null") + ',' + ((as_Sub2_4027 != null) ? "{...}" : "null") + ',' + ((aClass98_Sub5_4012 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub25.aStringArray4026 = new String[200];
    }
}
