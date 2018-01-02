// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub37 extends Class98
{
    private Class98_Sub5 aClass98_Sub5_4163;
    private ha_Sub1 aHa_Sub1_4164;
    private float[][] aFloatArrayArray4165;
    private Class98_Sub22_Sub2 aClass98_Sub22_Sub2_4166;
    private int anInt4167;
    static OutgoingOpcode aClass171_4168;
    private Interface8 anInterface8_4169;
    private Class98_Sub22 aClass98_Sub22_4170;
    private float[][] aFloatArrayArray4171;
    private int anInt4172;
    private int anInt4173;
    private int anInt4174;
    private int anInt4175;
    private Interface16 anInterface16_4176;
    private float[][] aFloatArrayArray4177;
    static Class225 aClass225_4178;
    private Class377 aClass377_4179;
    private Class104 aClass104_4180;
    private int anInt4181;
    private s_Sub1 aS_Sub1_4182;
    private Class104 aClass104_4183;
    static int anInt4184;
    static ha aHa4185;
    
    static final void method1460(int n, final int n2, final int n3, int n4, final int n5, int n6, final float[] array, final float n7, final int n8, final int n9, final float n10, final float[] array2) {
        try {
            n6 -= n5;
            n4 -= n2;
            n -= n9;
            final float n11 = array[0] * n + n6 * array[1] + n4 * array[2];
            final float n12 = array[5] * n4 + (n6 * array[4] + array[3] * n);
            float n13 = (float)Math.atan2(n11, n6 * array[7] + array[6] * n + n4 * array[n3]) / 6.2831855f + 0.5f;
            if (n7 != 1.0f) {
                n13 *= n7;
            }
            float n14 = 0.5f + n12 + n10;
            if (n8 == 1) {
                final float n15 = n13;
                n13 = -n14;
                n14 = n15;
            }
            else if (~n8 != 0xFFFFFFFD) {
                if (~n8 == 0xFFFFFFFC) {
                    final float n16 = n13;
                    n13 = n14;
                    n14 = -n16;
                }
            }
            else {
                n13 = -n13;
                n14 = -n14;
            }
            array2[0] = n13;
            array2[1] = n14;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pp.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((array != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1461(final int n, final int n2, final int n3, final boolean[][] array, final byte b) {
        try {
            if (this.anInterface8_4169 != null && ~(n3 + n2) <= ~this.anInt4181 && -n2 + n3 <= this.anInt4172 && b >= 31 && n2 + n >= this.anInt4174 && this.anInt4173 >= n - n2) {
                for (int anInt4174 = this.anInt4174; ~this.anInt4173 <= ~anInt4174; ++anInt4174) {
                    for (int anInt4175 = this.anInt4181; ~this.anInt4172 <= ~anInt4175; ++anInt4175) {
                        final int n4 = -n3 + anInt4175;
                        final int n5 = -n + anInt4174;
                        if (-n2 < n4 && ~n4 > ~n2 && ~(-n2) > ~n5 && ~n5 > ~n2 && array[n4 + n2][n2 + n5]) {
                            this.aHa_Sub1_4164.method1882((int)(255.0f * this.aClass98_Sub5_4163.method956(false)) << 2088417752, -120);
                            this.aHa_Sub1_4164.method1868(this.aClass104_4183, null, null, this.aClass104_4180, 0);
                            this.aHa_Sub1_4164.method1865(this.anInt4167, 4, this.anInterface8_4169, false, 0);
                            return;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pp.B(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method1462(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            long n8 = -1L;
            final int n9 = n5 - -(n6 << this.aS_Sub1_4182.anInt2200);
            final int n10 = (n4 << this.aS_Sub1_4182.anInt2200) + n2;
            final int method3417 = this.aS_Sub1_4182.method3417(n9, n10, true);
            if (~(n5 & 0x7F) == -1 || (n2 & 0x7F) == 0x0) {
                n8 = ((0xFFFFL & n10) << 1522918928 | (0xFFFFL & n9));
                final Class98 method3418 = this.aClass377_4179.method3990(n8, -1);
                if (method3418 != null) {
                    this.method1464(1522918928, ((Class98_Sub40)method3418).aShort4191);
                    return;
                }
            }
            final short n11 = (short)(this.anInt4175++);
            if (n8 != -1L) {
                this.aClass377_4179.method3996(new Class98_Sub40(n11), n8, -1);
            }
            float n26;
            float n27;
            float n28;
            if (n5 != 0 || n2 != 0) {
                if (this.aS_Sub1_4182.anInt2206 != n5 || ~n2 != -1) {
                    if (~n5 != ~this.aS_Sub1_4182.anInt2206 || n2 != this.aS_Sub1_4182.anInt2206) {
                        if (~n5 != -1 || this.aS_Sub1_4182.anInt2206 != n2) {
                            final float n12 = n5 / this.aS_Sub1_4182.anInt2206;
                            final float n13 = n2 / this.aS_Sub1_4182.anInt2206;
                            final float n14 = this.aFloatArrayArray4165[n7][n3];
                            final float n15 = this.aFloatArrayArray4171[n7][n3];
                            final float n16 = this.aFloatArrayArray4177[n7][n3];
                            final float n17 = this.aFloatArrayArray4165[1 + n7][n3];
                            final float n18 = this.aFloatArrayArray4171[n7 + 1][n3];
                            final float n19 = this.aFloatArrayArray4177[1 + n7][n3];
                            final float n20 = n14 + (this.aFloatArrayArray4165[n7][n3 + 1] - n14) * n12;
                            final float n21 = n18 + n12 * (-n18 + this.aFloatArrayArray4171[n7 + 1][n3 + 1]);
                            final float n22 = n16 + (-n16 + this.aFloatArrayArray4177[n7][n3 + 1]) * n12;
                            final float n23 = n17 + n12 * (-n17 + this.aFloatArrayArray4165[1 + n7][n3 + 1]);
                            final float n24 = n15 + n12 * (this.aFloatArrayArray4171[n7][n3 + 1] - n15);
                            final float n25 = n19 + n12 * (this.aFloatArrayArray4177[1 + n7][n3 + 1] - n19);
                            n26 = (-n24 + n21) * n13 + n24;
                            n27 = n20 + (-n20 + n23) * n13;
                            n28 = n22 + n13 * (n25 - n22);
                        }
                        else {
                            n26 = this.aFloatArrayArray4171[n7][1 + n3];
                            n28 = this.aFloatArrayArray4177[n7][n3 + 1];
                            n27 = this.aFloatArrayArray4165[n7][1 + n3];
                        }
                    }
                    else {
                        n26 = this.aFloatArrayArray4171[1 + n7][1 + n3];
                        n27 = this.aFloatArrayArray4165[1 + n7][n3 + 1];
                        n28 = this.aFloatArrayArray4177[1 + n7][1 + n3];
                    }
                }
                else {
                    n26 = this.aFloatArrayArray4171[n7 + 1][n3];
                    n27 = this.aFloatArrayArray4165[1 + n7][n3];
                    n28 = this.aFloatArrayArray4177[1 + n7][n3];
                }
            }
            else {
                n27 = this.aFloatArrayArray4165[n7][n3];
                n26 = this.aFloatArrayArray4171[n7][n3];
                n28 = this.aFloatArrayArray4177[n7][n3];
            }
            final float n29 = this.aClass98_Sub5_4163.method954(7019) + -n9;
            final float n30 = -method3417 + this.aClass98_Sub5_4163.method963((byte)73);
            final float n31 = this.aClass98_Sub5_4163.method962(28699) - n10;
            final float n32 = (float)Math.sqrt(n29 * n29 + n30 * n30 + n31 * n31);
            final float n33 = 1.0f / n32;
            final float n34 = n30 * n33;
            final float n35 = n31 * n33;
            final float n36 = n29 * n33;
            final float n37 = n32 / this.aClass98_Sub5_4163.method958(127);
            float n38 = 1.0f - n37 * n37;
            if (n38 < 0.0f) {
                n38 = 0.0f;
            }
            float n39 = n28 * n35 + (n26 * n34 + n27 * n36);
            if (n39 < 0.0f) {
                n39 = 0.0f;
            }
            float n40 = 2.0f * (n38 * n39);
            if (n40 > 1.0f) {
                n40 = 1.0f;
            }
            final int method3419 = this.aClass98_Sub5_4163.method961((byte)(-78));
            int n41 = (int)(((method3419 & 0xFF1301) >> 185099056) * n40);
            if (n41 > 255) {
                n41 = 255;
            }
            int n42 = (int)(n40 * (0xFF & method3419 >> 1744976008));
            if (~n42 < -256) {
                n42 = 255;
            }
            int n43 = (int)(n40 * (method3419 & 0xFF));
            if (~n43 < -256) {
                n43 = 255;
            }
            if (!this.aHa_Sub1_4164.aBoolean4397) {
                this.aClass98_Sub22_Sub2_4166.method1265((byte)(-52), n9);
                this.aClass98_Sub22_Sub2_4166.method1265((byte)(-52), method3417);
                this.aClass98_Sub22_Sub2_4166.method1265((byte)(-52), n10);
            }
            else {
                this.aClass98_Sub22_Sub2_4166.method1264((byte)(-126), n9);
                this.aClass98_Sub22_Sub2_4166.method1264((byte)7, method3417);
                this.aClass98_Sub22_Sub2_4166.method1264((byte)(-116), n10);
            }
            this.aClass98_Sub22_Sub2_4166.method1194(n41, -92);
            this.aClass98_Sub22_Sub2_4166.method1194(n42, -85);
            this.aClass98_Sub22_Sub2_4166.method1194(n43, 124);
            this.aClass98_Sub22_Sub2_4166.method1194(255, -44);
            this.method1464(1522918928, n11);
            if (n < 85) {
                method1463((byte)(-76));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pp.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    public static void method1463(final byte b) {
        try {
            Class98_Sub37.aClass171_4168 = null;
            if (b != 119) {
                Class98_Sub37.aClass171_4168 = null;
            }
            Class98_Sub37.aClass225_4178 = null;
            Class98_Sub37.aHa4185 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pp.A(" + b + ')');
        }
    }
    
    private final void method1464(final int n, final short n2) {
        try {
            Label_0040: {
                if (!this.aHa_Sub1_4164.aBoolean4397) {
                    this.aClass98_Sub22_4170.method1247(n2, 4);
                    if (!client.aBoolean3553) {
                        break Label_0040;
                    }
                }
                this.aClass98_Sub22_4170.writeShort(n2, n + 48943960);
            }
            if (n != 1522918928) {
                Class98_Sub37.aClass171_4168 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pp.D(" + n + ',' + n2 + ')');
        }
    }
    
    Class98_Sub37(final ha_Sub1 aHa_Sub1_4164, final s_Sub1 as_Sub1_4182, final Class98_Sub5 aClass98_Sub5_4163, final int[] array) {
        try {
            this.aHa_Sub1_4164 = aHa_Sub1_4164;
            this.aS_Sub1_4182 = as_Sub1_4182;
            this.aClass98_Sub5_4163 = aClass98_Sub5_4163;
            final int n = this.aClass98_Sub5_4163.method958(-33) - (as_Sub1_4182.anInt2206 >> -669182751);
            this.anInt4181 = this.aClass98_Sub5_4163.method954(7019) - n >> as_Sub1_4182.anInt2200;
            this.anInt4172 = this.aClass98_Sub5_4163.method954(7019) - -n >> as_Sub1_4182.anInt2200;
            this.anInt4174 = this.aClass98_Sub5_4163.method962(28699) + -n >> as_Sub1_4182.anInt2200;
            this.anInt4173 = n + this.aClass98_Sub5_4163.method962(28699) >> as_Sub1_4182.anInt2200;
            final int n2 = this.anInt4172 + (-this.anInt4181 + 1);
            final int n3 = -this.anInt4174 + this.anInt4173 + 1;
            this.aFloatArrayArray4165 = new float[1 + n2][1 + n3];
            this.aFloatArrayArray4171 = new float[1 + n2][1 + n3];
            this.aFloatArrayArray4177 = new float[n2 + 1][1 + n3];
            for (int n4 = 0; ~n3 <= ~n4; ++n4) {
                final int n5 = this.anInt4174 + n4;
                if (~n5 < -1 && ~n5 > ~(this.aS_Sub1_4182.anInt2204 - 1)) {
                    for (int n6 = 0; ~n6 >= ~n2; ++n6) {
                        final int n7 = n6 + this.anInt4181;
                        if (n7 > 0 && ~(-1 + this.aS_Sub1_4182.anInt2203) < ~n7) {
                            final int n8 = as_Sub1_4182.method3420(n5, -12639, n7 + 1) + -as_Sub1_4182.method3420(n5, -12639, -1 + n7);
                            final int n9 = as_Sub1_4182.method3420(n5 + 1, -12639, n7) + -as_Sub1_4182.method3420(n5 - 1, -12639, n7);
                            final float n10 = (float)(1.0 / Math.sqrt(n9 * n9 + 65536 + n8 * n8));
                            this.aFloatArrayArray4165[n6][n4] = n8 * n10;
                            this.aFloatArrayArray4171[n6][n4] = -256.0f * n10;
                            this.aFloatArrayArray4177[n6][n4] = n9 * n10;
                        }
                    }
                }
            }
            int n11 = 0;
            for (int anInt4174 = this.anInt4174; ~anInt4174 >= ~this.anInt4173; ++anInt4174) {
                if (anInt4174 >= 0 && ~anInt4174 > ~as_Sub1_4182.anInt2204) {
                    for (int anInt4175 = this.anInt4181; ~this.anInt4172 <= ~anInt4175; ++anInt4175) {
                        if (~anInt4175 <= -1 && ~as_Sub1_4182.anInt2203 < ~anInt4175) {
                            final int n12 = array[n11];
                            final int[] array2 = as_Sub1_4182.anIntArrayArrayArray5210[anInt4175][anInt4174];
                            if (array2 != null && ~n12 != -1) {
                                if (~n12 == 0xFFFFFFFE) {
                                    int n13 = 0;
                                    while (array2.length > n13) {
                                        if (~array2[n13++] != 0x0 && array2[n13++] != -1 && ~array2[n13++] != 0x0) {
                                            this.anInt4167 += 3;
                                        }
                                    }
                                }
                                else {
                                    this.anInt4167 += 3;
                                }
                            }
                        }
                        ++n11;
                    }
                }
                else {
                    n11 += this.anInt4172 - this.anInt4181;
                }
            }
            if (this.anInt4167 > 0) {
                this.aClass98_Sub22_4170 = new Class98_Sub22(this.anInt4167 * 2);
                this.aClass98_Sub22_Sub2_4166 = new Class98_Sub22_Sub2(16 * this.anInt4167);
                this.aClass377_4179 = new Class377(Class48.method453(423660257, this.anInt4167));
                int n14 = 0;
                int n15 = 0;
                for (int anInt4176 = this.anInt4174; ~anInt4176 >= ~this.anInt4173; ++anInt4176) {
                    if (~anInt4176 <= -1 && as_Sub1_4182.anInt2204 > anInt4176) {
                        int n16 = 0;
                        for (int i = this.anInt4181; i <= this.anInt4172; ++i) {
                            if (~i <= -1 && ~as_Sub1_4182.anInt2203 < ~i) {
                                final int n17 = array[n14];
                                final int[] array3 = as_Sub1_4182.anIntArrayArrayArray5210[i][anInt4176];
                                if (array3 != null && n17 != 0) {
                                    if (n17 == 1) {
                                        final int[] array4 = as_Sub1_4182.anIntArrayArrayArray5192[i][anInt4176];
                                        final int[] array5 = as_Sub1_4182.anIntArrayArrayArray5194[i][anInt4176];
                                        int n18 = 0;
                                        while (~array3.length < ~n18) {
                                            if (~array3[n18] != 0x0 && array3[1 + n18] != -1 && ~array3[2 + n18] != 0x0) {
                                                this.method1462(101, array5[n18], n15, anInt4176, array4[n18], i, n16);
                                                ++n18;
                                                this.method1462(97, array5[n18], n15, anInt4176, array4[n18], i, n16);
                                                ++n18;
                                                this.method1462(106, array5[n18], n15, anInt4176, array4[n18], i, n16);
                                                ++n18;
                                            }
                                            else {
                                                n18 += 3;
                                            }
                                        }
                                    }
                                    else if (n17 != 3) {
                                        if (~n17 != 0xFFFFFFFD) {
                                            if (n17 == 5) {
                                                this.method1462(122, as_Sub1_4182.anInt2206, n15, anInt4176, as_Sub1_4182.anInt2206, i, n16);
                                                this.method1462(90, as_Sub1_4182.anInt2206, n15, anInt4176, 0, i, n16);
                                                this.method1462(102, 0, n15, anInt4176, as_Sub1_4182.anInt2206, i, n16);
                                            }
                                            else if (~n17 == 0xFFFFFFFB) {
                                                this.method1462(116, as_Sub1_4182.anInt2206, n15, anInt4176, 0, i, n16);
                                                this.method1462(105, 0, n15, anInt4176, 0, i, n16);
                                                this.method1462(92, as_Sub1_4182.anInt2206, n15, anInt4176, as_Sub1_4182.anInt2206, i, n16);
                                            }
                                        }
                                        else {
                                            this.method1462(91, 0, n15, anInt4176, as_Sub1_4182.anInt2206, i, n16);
                                            this.method1462(122, as_Sub1_4182.anInt2206, n15, anInt4176, as_Sub1_4182.anInt2206, i, n16);
                                            this.method1462(120, 0, n15, anInt4176, 0, i, n16);
                                        }
                                    }
                                    else {
                                        this.method1462(117, 0, n15, anInt4176, 0, i, n16);
                                        this.method1462(118, 0, n15, anInt4176, as_Sub1_4182.anInt2206, i, n16);
                                        this.method1462(98, as_Sub1_4182.anInt2206, n15, anInt4176, 0, i, n16);
                                    }
                                }
                            }
                            ++n14;
                            ++n16;
                        }
                    }
                    else {
                        n14 += -this.anInt4181 + this.anInt4172;
                    }
                    ++n15;
                }
                this.anInterface8_4169 = this.aHa_Sub1_4164.method1838(5123, this.aClass98_Sub22_4170.aByteArray3992, 7, false, this.aClass98_Sub22_4170.anInt3991);
                this.anInterface16_4176 = this.aHa_Sub1_4164.method1878(this.aClass98_Sub22_Sub2_4166.anInt3991, false, 16, -55, this.aClass98_Sub22_Sub2_4166.aByteArray3992);
                this.aClass104_4180 = new Class104(this.anInterface16_4176, 5126, 3, 0);
                this.aClass104_4183 = new Class104(this.anInterface16_4176, 5121, 4, 12);
            }
            else {
                this.aClass104_4183 = null;
                this.anInterface8_4169 = null;
                this.aClass104_4180 = null;
                this.anInterface16_4176 = null;
            }
            final float[][] aFloatArrayArray4165 = null;
            this.aFloatArrayArray4177 = aFloatArrayArray4165;
            this.aFloatArrayArray4171 = aFloatArrayArray4165;
            this.aFloatArrayArray4165 = aFloatArrayArray4165;
            this.aClass98_Sub22_Sub2_4166 = null;
            this.aClass377_4179 = null;
            this.aClass98_Sub22_4170 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pp.<init>(" + ((aHa_Sub1_4164 != null) ? "{...}" : "null") + ',' + ((as_Sub1_4182 != null) ? "{...}" : "null") + ',' + ((aClass98_Sub5_4163 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub37.aClass171_4168 = new OutgoingOpcode(80, 3);
    }
}
