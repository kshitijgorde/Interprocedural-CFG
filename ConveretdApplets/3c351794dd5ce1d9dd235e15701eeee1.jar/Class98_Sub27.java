import java.lang.reflect.Field;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub27 extends Class98
{
    Class64_Sub6 aClass64_Sub6_4033;
    Class64_Sub15 aClass64_Sub15_4034;
    Class64_Sub26 aClass64_Sub26_4035;
    private Class145 aClass145_4036;
    Class64_Sub21 aClass64_Sub21_4037;
    Class64_Sub11 aClass64_Sub11_4038;
    Class64_Sub25 aClass64_Sub25_4039;
    Class64_Sub16 aClass64_Sub16_4040;
    Class64_Sub3 aClass64_Sub3_4041;
    Class64_Sub8 aClass64_Sub8_4042;
    Class64_Sub1 aClass64_Sub1_4043;
    Class64_Sub23 aClass64_Sub23_4044;
    static OutgoingOpcode aClass171_4045;
    Class64_Sub17 aClass64_Sub17_4046;
    Class64_Sub24 aClass64_Sub24_4047;
    Class64_Sub12 aClass64_Sub12_4048;
    Class64_Sub14 aClass64_Sub14_4049;
    Class64_Sub29 aClass64_Sub29_4050;
    Class64_Sub22 aClass64_Sub22_4051;
    Class64_Sub27 aClass64_Sub27_4052;
    Class64_Sub4 aClass64_Sub4_4053;
    Class64_Sub22 aClass64_Sub22_4054;
    Class64_Sub23 aClass64_Sub23_4055;
    Class64_Sub20 aClass64_Sub20_4056;
    Class64_Sub19 aClass64_Sub19_4057;
    Class64_Sub15 aClass64_Sub15_4058;
    private Class279 aClass279_4059;
    static int anInt4060;
    Class64_Sub2 aClass64_Sub2_4061;
    Class64_Sub8 aClass64_Sub8_4062;
    Class64_Sub13 aClass64_Sub13_4063;
    Class64_Sub28 aClass64_Sub28_4064;
    Class64_Sub5 aClass64_Sub5_4065;
    Class64_Sub22 aClass64_Sub22_4066;
    Class64_Sub9 aClass64_Sub9_4067;
    Class64_Sub27 aClass64_Sub27_4068;
    Class64_Sub22 aClass64_Sub22_4069;
    Class64_Sub10 aClass64_Sub10_4070;
    Class64_Sub18 aClass64_Sub18_4071;
    Class64_Sub22 aClass64_Sub22_4072;
    Class64_Sub7 aClass64_Sub7_4073;
    static Class350 aClass350_4074;
    static byte[] aByteArray4075;
    Class64_Sub3 aClass64_Sub3_4076;
    static Class aClass4077;
    
    public static void method1280(final boolean b) {
        try {
            Class98_Sub27.aClass350_4074 = null;
            Class98_Sub27.aByteArray4075 = null;
            Class98_Sub27.aClass171_4045 = null;
            if (!b) {
                Class98_Sub27.anInt4060 = 95;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.L(" + b + ')');
        }
    }
    
    private final void method1281(final Class98_Sub22 class98_Sub22, final int n, final int n2) {
        try {
            this.aClass64_Sub19_4057 = new Class64_Sub19(class98_Sub22.readUnsignedByte((byte)(-109)), this);
            this.aClass64_Sub3_4041 = new Class64_Sub3(class98_Sub22.readUnsignedByte((byte)107), this);
            this.aClass64_Sub15_4034 = new Class64_Sub15(1 + class98_Sub22.readUnsignedByte((byte)3), this);
            this.aClass64_Sub11_4038 = new Class64_Sub11(class98_Sub22.readUnsignedByte((byte)14), this);
            this.aClass64_Sub9_4067 = new Class64_Sub9(class98_Sub22.readUnsignedByte((byte)29), this);
            this.aClass64_Sub24_4047 = new Class64_Sub24(class98_Sub22.readUnsignedByte((byte)(-119)), this);
            this.aClass64_Sub13_4063 = new Class64_Sub13(class98_Sub22.readUnsignedByte((byte)53), this);
            class98_Sub22.readUnsignedByte((byte)5);
            this.aClass64_Sub26_4035 = new Class64_Sub26(class98_Sub22.readUnsignedByte((byte)(-113)), this);
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-5));
            int unsignedByte2 = 0;
            if (~n <= -18) {
                unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-114));
            }
            this.aClass64_Sub7_4073 = new Class64_Sub7((~unsignedByte2 > ~unsignedByte) ? unsignedByte : unsignedByte2, this);
            boolean b = true;
            boolean b2;
            if (~n <= -3) {
                b2 = (~class98_Sub22.readUnsignedByte((byte)77) == 0xFFFFFFFE);
                if (n >= 17) {
                    b = (class98_Sub22.readUnsignedByte((byte)(-128)) == 1);
                }
            }
            else {
                b2 = (~class98_Sub22.readUnsignedByte((byte)(-112)) == 0xFFFFFFFE);
                class98_Sub22.readUnsignedByte((byte)(-98));
            }
            this.aClass64_Sub10_4070 = new Class64_Sub10((b2 | b) ? 1 : 0, this);
            this.aClass64_Sub28_4064 = new Class64_Sub28(class98_Sub22.readUnsignedByte((byte)(-126)), this);
            this.aClass64_Sub14_4049 = new Class64_Sub14(class98_Sub22.readUnsignedByte((byte)(-101)), this);
            this.aClass64_Sub23_4044 = new Class64_Sub23(class98_Sub22.readUnsignedByte((byte)11), this);
            this.aClass64_Sub1_4043 = new Class64_Sub1(class98_Sub22.readUnsignedByte((byte)80), this);
            this.aClass64_Sub22_4066 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)(-119)), this);
            if (n >= 20) {
                this.aClass64_Sub22_4054 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)(-115)), this);
            }
            else {
                this.aClass64_Sub22_4054 = new Class64_Sub22(this.aClass64_Sub22_4066.method641((byte)124), this);
            }
            this.aClass64_Sub22_4069 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)81), this);
            this.aClass64_Sub22_4051 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)10), this);
            if (~n <= -22) {
                this.aClass64_Sub22_4072 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)42), this);
            }
            else {
                this.aClass64_Sub22_4072 = new Class64_Sub22(this.aClass64_Sub22_4069.method641((byte)125), this);
            }
            if (n >= 1) {
                class98_Sub22.readShort((byte)127);
                class98_Sub22.readShort((byte)127);
            }
            if (~n <= -4 && n < 6) {
                class98_Sub22.readUnsignedByte((byte)(-114));
            }
            if (n2 != 4311) {
                this.method1281(null, -107, -6);
            }
            if (~n <= -5) {
                this.aClass64_Sub6_4033 = new Class64_Sub6(class98_Sub22.readUnsignedByte((byte)35), this);
            }
            class98_Sub22.readInt(-2);
            if (n >= 6) {
                this.aClass64_Sub27_4052 = new Class64_Sub27(class98_Sub22.readUnsignedByte((byte)123), this);
            }
            if (~n <= -8) {
                this.aClass64_Sub2_4061 = new Class64_Sub2(class98_Sub22.readUnsignedByte((byte)35), this);
            }
            if (~n <= -9) {
                class98_Sub22.readUnsignedByte((byte)(-120));
            }
            if (~n <= -10) {
                this.aClass64_Sub17_4046 = new Class64_Sub17(class98_Sub22.readUnsignedByte((byte)(-121)), this);
            }
            if (n >= 10) {
                this.aClass64_Sub5_4065 = new Class64_Sub5(class98_Sub22.readUnsignedByte((byte)79), this);
            }
            if (n >= 11) {
                this.aClass64_Sub29_4050 = new Class64_Sub29(class98_Sub22.readUnsignedByte((byte)47), this);
            }
            if (~n <= -13) {
                this.aClass64_Sub24_4047 = new Class64_Sub24(class98_Sub22.readUnsignedByte((byte)(-115)), this);
            }
            if (~n <= -14) {
                this.aClass64_Sub25_4039 = new Class64_Sub25(class98_Sub22.readUnsignedByte((byte)39), this);
            }
            if (~n <= -15) {
                this.aClass64_Sub8_4062 = new Class64_Sub8(class98_Sub22.readUnsignedByte((byte)(-115)), this);
            }
            if (~n <= -16) {
                this.aClass64_Sub21_4037 = new Class64_Sub21(class98_Sub22.readUnsignedByte((byte)(-115)), this);
            }
            if (n >= 16) {
                this.aClass64_Sub20_4056 = new Class64_Sub20(class98_Sub22.readUnsignedByte((byte)24), this);
            }
            if (~n <= -19) {
                this.aClass64_Sub12_4048 = new Class64_Sub12(class98_Sub22.readUnsignedByte((byte)(-100)), this);
            }
            if (n >= 19) {
                this.aClass64_Sub16_4040 = new Class64_Sub16(class98_Sub22.readUnsignedByte((byte)(-110)), this);
            }
            if (~n <= -23) {
                this.aClass64_Sub4_4053 = new Class64_Sub4(class98_Sub22.readUnsignedByte((byte)120), this);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.I(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method1282(final byte b, final int anInt3994) {
        try {
            if (anInt3994 != Class98_Sub22.anInt3994) {
                Class165.anInt1276 = (Class98_Sub10_Sub7.anInt5572 = Class246_Sub3_Sub4_Sub5.anIntArray6265[anInt3994]);
                Class98_Sub10_Sub34.method1104(112);
                Class170.anIntArrayArrayArray1311 = new int[4][Class165.anInt1276 >> -762838077][Class98_Sub10_Sub7.anInt5572 >> -755805021];
                Class372.anIntArrayArray3149 = new int[Class165.anInt1276][Class98_Sub10_Sub7.anInt5572];
                Class64_Sub28.anIntArrayArray3719 = new int[Class165.anInt1276][Class98_Sub10_Sub7.anInt5572];
                for (int n = 0; ~n > -5; ++n) {
                    Class167.aClass243Array1281[n] = Class299.method3509(2742, Class98_Sub10_Sub7.anInt5572, Class165.anInt1276);
                }
                OutputStream_Sub2.aByteArrayArrayArray41 = new byte[4][Class165.anInt1276][Class98_Sub10_Sub7.anInt5572];
                Class322.method3673(Class98_Sub10_Sub7.anInt5572, Class165.anInt1276, 4, true);
                Class159.method2508(Class165.anInt1276 >> 2012820739, Class98_Sub10_Sub7.anInt5572 >> 1345943043, (byte)(-50), Class265.aHa1974);
                if (b != 8) {
                    method1284(43);
                }
                Class98_Sub22.anInt3994 = anInt3994;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.M(" + b + ',' + anInt3994 + ')');
        }
    }
    
    private final void method1283(final boolean b, final int n) {
        try {
            if (b || this.aClass64_Sub23_4044 == null) {
                this.aClass64_Sub23_4044 = new Class64_Sub23(this);
            }
            if (b || this.aClass64_Sub23_4055 == null) {
                this.aClass64_Sub23_4055 = new Class64_Sub23(this.aClass64_Sub23_4044.method648((byte)125), this);
            }
            if (b || this.aClass64_Sub5_4065 == null) {
                this.aClass64_Sub5_4065 = new Class64_Sub5(this);
            }
            if (b || this.aClass64_Sub19_4057 == null) {
                this.aClass64_Sub19_4057 = new Class64_Sub19(this);
            }
            if (b || this.aClass64_Sub17_4046 == null) {
                this.aClass64_Sub17_4046 = new Class64_Sub17(this);
            }
            if (b || this.aClass64_Sub13_4063 == null) {
                this.aClass64_Sub13_4063 = new Class64_Sub13(this);
            }
            if (b || this.aClass64_Sub14_4049 == null) {
                this.aClass64_Sub14_4049 = new Class64_Sub14(this);
            }
            if (b || this.aClass64_Sub25_4039 == null) {
                this.aClass64_Sub25_4039 = new Class64_Sub25(this);
            }
            if (b || this.aClass64_Sub11_4038 == null) {
                this.aClass64_Sub11_4038 = new Class64_Sub11(this);
            }
            if (b || this.aClass64_Sub24_4047 == null) {
                this.aClass64_Sub24_4047 = new Class64_Sub24(this);
            }
            if (b || this.aClass64_Sub10_4070 == null) {
                this.aClass64_Sub10_4070 = new Class64_Sub10(this);
            }
            if (b || this.aClass64_Sub7_4073 == null) {
                this.aClass64_Sub7_4073 = new Class64_Sub7(this);
            }
            if (b || this.aClass64_Sub18_4071 == null) {
                this.aClass64_Sub18_4071 = new Class64_Sub18(this);
            }
            if (b || this.aClass64_Sub6_4033 == null) {
                this.aClass64_Sub6_4033 = new Class64_Sub6(this);
            }
            if (b || this.aClass64_Sub15_4034 == null) {
                this.aClass64_Sub15_4034 = new Class64_Sub15(this);
            }
            if (b || this.aClass64_Sub15_4058 == null) {
                this.aClass64_Sub15_4058 = new Class64_Sub15(this.aClass64_Sub15_4034.method612((byte)120), this);
            }
            if (b || this.aClass64_Sub16_4040 == null) {
                this.aClass64_Sub16_4040 = new Class64_Sub16(this);
            }
            if (b || this.aClass64_Sub26_4035 == null) {
                this.aClass64_Sub26_4035 = new Class64_Sub26(this);
            }
            if (b || this.aClass64_Sub9_4067 == null) {
                this.aClass64_Sub9_4067 = new Class64_Sub9(this);
            }
            if (b || this.aClass64_Sub20_4056 == null) {
                this.aClass64_Sub20_4056 = new Class64_Sub20(this);
            }
            if (b || this.aClass64_Sub8_4062 == null) {
                this.aClass64_Sub8_4062 = new Class64_Sub8(this);
            }
            if (b || this.aClass64_Sub8_4042 == null) {
                this.aClass64_Sub8_4042 = new Class64_Sub8(this.aClass64_Sub8_4062.method583((byte)125), this);
            }
            if (b || this.aClass64_Sub3_4041 == null) {
                this.aClass64_Sub3_4041 = new Class64_Sub3(this);
            }
            if (b || this.aClass64_Sub3_4076 == null) {
                this.aClass64_Sub3_4076 = new Class64_Sub3(this.aClass64_Sub3_4041.method564((byte)121), this);
            }
            if (b || this.aClass64_Sub28_4064 == null) {
                this.aClass64_Sub28_4064 = new Class64_Sub28(this);
            }
            if (b || this.aClass64_Sub27_4052 == null) {
                this.aClass64_Sub27_4052 = new Class64_Sub27(this);
            }
            if (b || this.aClass64_Sub27_4068 == null) {
                this.aClass64_Sub27_4068 = new Class64_Sub27(this.aClass64_Sub27_4052.method666((byte)127), this);
            }
            if (b || this.aClass64_Sub29_4050 == null) {
                this.aClass64_Sub29_4050 = new Class64_Sub29(this);
            }
            if (b || this.aClass64_Sub12_4048 == null) {
                this.aClass64_Sub12_4048 = new Class64_Sub12(this);
            }
            if (b || this.aClass64_Sub21_4037 == null) {
                this.aClass64_Sub21_4037 = new Class64_Sub21(this);
            }
            if (b || this.aClass64_Sub4_4053 == null) {
                this.aClass64_Sub4_4053 = new Class64_Sub4(this);
            }
            if (n == 0) {
                if (b || this.aClass64_Sub2_4061 == null) {
                    this.aClass64_Sub2_4061 = new Class64_Sub2(this);
                }
                if (b || this.aClass64_Sub22_4066 == null) {
                    this.aClass64_Sub22_4066 = new Class64_Sub22(this);
                }
                if (b || this.aClass64_Sub22_4051 == null) {
                    this.aClass64_Sub22_4051 = new Class64_Sub22(this);
                }
                if (b || this.aClass64_Sub22_4054 == null) {
                    this.aClass64_Sub22_4054 = new Class64_Sub22(this);
                }
                if (b || this.aClass64_Sub22_4069 == null) {
                    this.aClass64_Sub22_4069 = new Class64_Sub22(this);
                }
                if (b || this.aClass64_Sub22_4072 == null) {
                    this.aClass64_Sub22_4072 = new Class64_Sub22(this);
                }
                if (b || this.aClass64_Sub1_4043 == null) {
                    this.aClass64_Sub1_4043 = new Class64_Sub1(this);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.E(" + b + ',' + n + ')');
        }
    }
    
    static final void method1284(final int n) {
        try {
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4041);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4034);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub13_4063);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub6_4033);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065);
            Class151_Sub1.method2450((byte)106);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 4, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub12_4048);
            Class98_Sub46_Sub13_Sub1.method1593((byte)110);
            Class374.method3980((byte)125);
            Class33.aBoolean316 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.A(" + n + ')');
        }
    }
    
    final void method1285(final byte b, final int n, final Class64 class64) {
        try {
            if (b != -13) {
                this.aClass64_Sub17_4046 = null;
            }
            class64.method554(n, b + 9861);
            this.method1287(false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.C(" + b + ',' + n + ',' + ((class64 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class279 method1286(final byte b) {
        try {
            if (b != 104) {
                this.aClass64_Sub26_4035 = null;
            }
            return this.aClass279_4059;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.D(" + b + ')');
        }
    }
    
    private final void method1287(final boolean b) {
        try {
            try {
                final Field[] declaredFields = this.getClass().getDeclaredFields();
                for (int i = 0; i < declaredFields.length; ++i) {
                    final Field field = declaredFields[i];
                    if (((Class98_Sub27.aClass4077 != null) ? Class98_Sub27.aClass4077 : (Class98_Sub27.aClass4077 = method1293("Class64"))).isAssignableFrom(field.getType())) {
                        ((Class64)field.get(this)).method551((byte)119);
                    }
                }
                if (b) {
                    this.aClass279_4059 = null;
                }
            }
            catch (IllegalAccessException ex2) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.B(" + b + ')');
        }
    }
    
    final Class98_Sub22 method1288(final boolean b) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(Class269.method3270(97));
            class98_Sub22.method1194(24, -51);
            class98_Sub22.method1194(this.aClass64_Sub23_4044.method648((byte)125), 126);
            class98_Sub22.method1194(this.aClass64_Sub5_4065.method570((byte)122), 92);
            class98_Sub22.method1194(this.aClass64_Sub19_4057.method630((byte)123), 48);
            class98_Sub22.method1194(this.aClass64_Sub17_4046.method617((byte)126), -116);
            class98_Sub22.method1194(this.aClass64_Sub13_4063.method602((byte)122), 85);
            class98_Sub22.method1194(this.aClass64_Sub14_4049.method609((byte)124), -44);
            class98_Sub22.method1194(this.aClass64_Sub25_4039.method655((byte)121), 124);
            class98_Sub22.method1194(this.aClass64_Sub11_4038.method596((byte)121), -49);
            class98_Sub22.method1194(this.aClass64_Sub24_4047.method651((byte)123), 114);
            class98_Sub22.method1194(this.aClass64_Sub10_4070.method592((byte)122), -68);
            class98_Sub22.method1194(this.aClass64_Sub7_4073.method579((byte)122), -124);
            class98_Sub22.method1194(this.aClass64_Sub18_4071.method627((byte)122), -77);
            class98_Sub22.method1194(this.aClass64_Sub6_4033.method572((byte)122), 40);
            if (!b) {
                return null;
            }
            class98_Sub22.method1194(this.aClass64_Sub15_4034.method612((byte)122), -112);
            class98_Sub22.method1194(this.aClass64_Sub16_4040.method614((byte)120), 77);
            class98_Sub22.method1194(this.aClass64_Sub26_4035.method662((byte)122), -99);
            class98_Sub22.method1194(this.aClass64_Sub9_4067.method588((byte)121), -86);
            class98_Sub22.method1194(this.aClass64_Sub20_4056.method634((byte)127), -119);
            class98_Sub22.method1194(this.aClass64_Sub8_4062.method583((byte)125), 47);
            class98_Sub22.method1194(this.aClass64_Sub3_4041.method564((byte)124), -103);
            class98_Sub22.method1194(this.aClass64_Sub28_4064.method668((byte)122), -104);
            class98_Sub22.method1194(this.aClass64_Sub27_4052.method666((byte)125), 43);
            class98_Sub22.method1194(this.aClass64_Sub29_4050.method677((byte)127), 50);
            class98_Sub22.method1194(this.aClass64_Sub12_4048.method600((byte)122), 66);
            class98_Sub22.method1194(this.aClass64_Sub21_4037.method639((byte)126), -53);
            class98_Sub22.method1194(this.aClass64_Sub4_4053.method568((byte)120), 115);
            class98_Sub22.method1194(this.aClass64_Sub2_4061.method560((byte)126), 111);
            class98_Sub22.method1194(this.aClass64_Sub22_4066.method641((byte)124), -81);
            class98_Sub22.method1194(this.aClass64_Sub22_4051.method641((byte)120), 47);
            class98_Sub22.method1194(this.aClass64_Sub22_4054.method641((byte)125), -54);
            class98_Sub22.method1194(this.aClass64_Sub22_4069.method641((byte)122), -101);
            class98_Sub22.method1194(this.aClass64_Sub22_4072.method641((byte)121), 118);
            class98_Sub22.method1194(this.aClass64_Sub1_4043.method558((byte)121), -97);
            return class98_Sub22;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.K(" + b + ')');
        }
    }
    
    final Class145 method1289(final int n) {
        try {
            if (n >= -95) {
                method1280(true);
            }
            return this.aClass145_4036;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.J(" + n + ')');
        }
    }
    
    private final void method1290(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (class98_Sub22 != null && class98_Sub22.aByteArray3992 != null) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-127));
                if (unsignedByte < 23) {
                    try {
                        this.method1281(class98_Sub22, unsignedByte, 4311);
                    }
                    catch (Exception ex2) {
                        this.method1283(true, n ^ 0x27A4);
                    }
                    this.method1283(false, 0);
                }
                else if (~unsignedByte < -25) {
                    this.method1283(true, n - 10148);
                }
                else {
                    this.aClass64_Sub23_4044 = new Class64_Sub23(class98_Sub22.readUnsignedByte((byte)92), this);
                    this.aClass64_Sub23_4055 = new Class64_Sub23(this.aClass64_Sub23_4044.method648((byte)124), this);
                    this.aClass64_Sub5_4065 = new Class64_Sub5(class98_Sub22.readUnsignedByte((byte)(-108)), this);
                    this.aClass64_Sub19_4057 = new Class64_Sub19(class98_Sub22.readUnsignedByte((byte)74), this);
                    this.aClass64_Sub17_4046 = new Class64_Sub17(class98_Sub22.readUnsignedByte((byte)(-126)), this);
                    this.aClass64_Sub13_4063 = new Class64_Sub13(class98_Sub22.readUnsignedByte((byte)104), this);
                    this.aClass64_Sub14_4049 = new Class64_Sub14(class98_Sub22.readUnsignedByte((byte)(-127)), this);
                    this.aClass64_Sub25_4039 = new Class64_Sub25(class98_Sub22.readUnsignedByte((byte)34), this);
                    this.aClass64_Sub11_4038 = new Class64_Sub11(class98_Sub22.readUnsignedByte((byte)(-6)), this);
                    this.aClass64_Sub24_4047 = new Class64_Sub24(class98_Sub22.readUnsignedByte((byte)(-107)), this);
                    this.aClass64_Sub10_4070 = new Class64_Sub10(class98_Sub22.readUnsignedByte((byte)(-109)), this);
                    this.aClass64_Sub7_4073 = new Class64_Sub7(class98_Sub22.readUnsignedByte((byte)26), this);
                    if (~unsignedByte <= -25) {
                        this.aClass64_Sub18_4071 = new Class64_Sub18(class98_Sub22.readUnsignedByte((byte)(-104)), this);
                    }
                    this.aClass64_Sub6_4033 = new Class64_Sub6(class98_Sub22.readUnsignedByte((byte)(-8)), this);
                    this.aClass64_Sub15_4034 = new Class64_Sub15(class98_Sub22.readUnsignedByte((byte)(-124)), this);
                    this.aClass64_Sub15_4058 = new Class64_Sub15(this.aClass64_Sub15_4034.method612((byte)123), this);
                    this.aClass64_Sub16_4040 = new Class64_Sub16(class98_Sub22.readUnsignedByte((byte)72), this);
                    this.aClass64_Sub26_4035 = new Class64_Sub26(class98_Sub22.readUnsignedByte((byte)4), this);
                    this.aClass64_Sub9_4067 = new Class64_Sub9(class98_Sub22.readUnsignedByte((byte)71), this);
                    this.aClass64_Sub20_4056 = new Class64_Sub20(class98_Sub22.readUnsignedByte((byte)36), this);
                    this.aClass64_Sub8_4062 = new Class64_Sub8(class98_Sub22.readUnsignedByte((byte)76), this);
                    this.aClass64_Sub8_4042 = new Class64_Sub8(this.aClass64_Sub8_4062.method583((byte)122), this);
                    this.aClass64_Sub3_4041 = new Class64_Sub3(class98_Sub22.readUnsignedByte((byte)(-99)), this);
                    this.aClass64_Sub3_4076 = new Class64_Sub3(this.aClass64_Sub3_4041.method564((byte)122), this);
                    this.aClass64_Sub28_4064 = new Class64_Sub28(class98_Sub22.readUnsignedByte((byte)67), this);
                    this.aClass64_Sub27_4052 = new Class64_Sub27(class98_Sub22.readUnsignedByte((byte)(-125)), this);
                    this.aClass64_Sub27_4068 = new Class64_Sub27(this.aClass64_Sub27_4052.method666((byte)127), this);
                    this.aClass64_Sub29_4050 = new Class64_Sub29(class98_Sub22.readUnsignedByte((byte)(-107)), this);
                    this.aClass64_Sub12_4048 = new Class64_Sub12(class98_Sub22.readUnsignedByte((byte)(-106)), this);
                    this.aClass64_Sub21_4037 = new Class64_Sub21(class98_Sub22.readUnsignedByte((byte)(-124)), this);
                    this.aClass64_Sub4_4053 = new Class64_Sub4(class98_Sub22.readUnsignedByte((byte)99), this);
                    this.aClass64_Sub2_4061 = new Class64_Sub2(class98_Sub22.readUnsignedByte((byte)114), this);
                    this.aClass64_Sub22_4066 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)127), this);
                    this.aClass64_Sub22_4051 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)(-118)), this);
                    this.aClass64_Sub22_4054 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)74), this);
                    this.aClass64_Sub22_4069 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)(-102)), this);
                    this.aClass64_Sub22_4072 = new Class64_Sub22(class98_Sub22.readUnsignedByte((byte)111), this);
                    this.aClass64_Sub1_4043 = new Class64_Sub1(class98_Sub22.readUnsignedByte((byte)(-123)), this);
                    this.method1283(false, n ^ 0x27A4);
                }
            }
            else {
                this.method1283(true, 0);
            }
            if (n != 10148) {
                method1280(true);
            }
            this.method1287(false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.F(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final boolean method1291(final byte b) {
        try {
            if (b < 100) {
                Class98_Sub27.aByteArray4075 = null;
            }
            return this.aClass64_Sub8_4042.method586(true) && this.aClass64_Sub8_4042.method583((byte)122) == 0 && this.aClass145_4036.method2318(-1) < 96;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.H(" + b + ')');
        }
    }
    
    static final boolean method1292(final int n, final byte b, final int n2) {
        try {
            if (!Class53_Sub1.method502(n, n2, (byte)116)) {
                return false;
            }
            if (~(n2 & 0x9000) != -1 | Class349.method3842(n, n2, -18021) | Class98_Sub22.method1241(false, n, n2)) {
                return true;
            }
            if (b < 110) {
                Class98_Sub27.aClass171_4045 = null;
            }
            return (~(0x2000 & n2) != -1 | Class373_Sub3.method3978(n, n2, (byte)74) | Class21_Sub2.method271((byte)(-112), n2, n)) & (n & 0x37) == 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.G(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    Class98_Sub27(final Class279 aClass279_4059, final int n) {
        try {
            this.aClass279_4059 = aClass279_4059;
            this.aClass145_4036 = new Class145(Class98_Sub43_Sub2.aClass88_5907.aBoolean682, Class292.anInt3359, Class98_Sub46_Sub19.anInt6065, Class88.aString690.toLowerCase().indexOf("arm") != -1);
            this.aClass64_Sub8_4042 = new Class64_Sub8(n, this);
            this.method1283(true, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.<init>(" + ((aClass279_4059 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    Class98_Sub27(final Class98_Sub22 class98_Sub22, final Class279 aClass279_4059, final int n) {
        try {
            this.aClass279_4059 = aClass279_4059;
            this.aClass145_4036 = new Class145(Class98_Sub43_Sub2.aClass88_5907.aBoolean682, Class292.anInt3359, Class98_Sub46_Sub19.anInt6065, ~Class88.aString690.indexOf("arm") != 0x0);
            this.aClass64_Sub8_4042 = new Class64_Sub8(n, this);
            this.method1290(class98_Sub22, 10148);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kda.<init>(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + ((aClass279_4059 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static Class method1293(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class98_Sub27.aClass171_4045 = new OutgoingOpcode(59, 2);
        Class98_Sub27.aClass350_4074 = new Class350(1);
        Class98_Sub27.aByteArray4075 = new byte[2048];
    }
}
