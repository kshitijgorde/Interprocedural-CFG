import java.lang.reflect.Field;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class269
{
    static String[] aStringArray2021;
    private Class207 aClass207_2022;
    static float[] aFloatArray2023;
    static int anInt2024;
    static Class207 aClass207_2025;
    static int anInt2026;
    static float[] aFloatArray2027;
    private Class79 aClass79_2028;
    static Class aClass2029;
    static Class aClass2030;
    
    final void method3265(final int n) {
        try {
            synchronized (this.aClass79_2028) {
                if (n != 1) {
                    Class269.aFloatArray2023 = null;
                }
                this.aClass79_2028.method794(85);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.I(" + n + ')');
        }
    }
    
    static final void method3266(final int n, final byte b) {
        try {
            Class69_Sub2.aClass79_5334.method800((byte)62, n);
            Class64_Sub5.aClass79_3650.method800((byte)62, n);
            Class76_Sub11.aClass79_3797.method800((byte)62, n);
            Class151_Sub7.aClass79_5004.method800((byte)62, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.H(" + n + ',' + b + ')');
        }
    }
    
    public static void method3267(final byte b) {
        try {
            Class269.aStringArray2021 = null;
            Class269.aClass207_2025 = null;
            Class269.aFloatArray2027 = null;
            Class269.aFloatArray2023 = null;
            if (b <= 36) {
                method3266(-41, (byte)0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.E(" + b + ')');
        }
    }
    
    final Class379 method3268(final int n, final int n2) {
        try {
            final Class379 class379;
            synchronized (this.aClass79_2028) {
                class379 = (Class379)this.aClass79_2028.method802(-126, n2);
            }
            if (n != -37) {
                method3269(false, 104);
            }
            if (class379 != null) {
                return class379;
            }
            final byte[] method2745;
            synchronized (this.aClass207_2022) {
                method2745 = this.aClass207_2022.method2745(n2, 31, false);
            }
            final Class379 class380 = new Class379();
            if (method2745 != null) {
                class380.method4008((byte)126, new Class98_Sub22(method2745));
            }
            synchronized (this.aClass79_2028) {
                this.aClass79_2028.method805(n2, class380, (byte)(-80));
            }
            return class380;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3269(final boolean b, final int n) {
        try {
            if (b) {
                if (Class15.anInt185 != -1) {
                    Class246.method2964(false, Class15.anInt185);
                }
                for (Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3998(94); class98_Sub18 != null; class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3995(-1)) {
                    if (!class98_Sub18.method941((byte)78)) {
                        class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3998(121);
                        if (class98_Sub18 == null) {
                            break;
                        }
                    }
                    Class196.method2666(16398, false, class98_Sub18, true);
                }
                Class15.anInt185 = -1;
                Class116.aClass377_964 = new Class377(8);
                Class76_Sub9.method768(118);
                Class15.anInt185 = Class297.anInt2470;
                Class40.method359(n + 83, false);
                Class98_Sub43.method1481(2);
                Class247.method3155(Class15.anInt185);
            }
            Class98_Sub5.aString3837 = (Class360.aString3064 = "");
            aa_Sub3.aBoolean3569 = false;
            Class162.method2516(-96);
            Class21_Sub2.anInt5387 = -1;
            Class43.method401(OutputStream_Sub2.anInt39, true);
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 = new Class246_Sub3_Sub4_Sub2_Sub2();
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 = Class165.anInt1276 * 512 / 2;
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[n] = Class165.anInt1276 / 2;
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 = Class98_Sub10_Sub7.anInt5572 * 512 / 2;
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0] = Class98_Sub10_Sub7.anInt5572 / 2;
            Class98_Sub46_Sub10.anInt6020 = (Class134.anInt3461 = 0);
            if (~Class98_Sub46_Sub20_Sub2.anInt6319 != 0xFFFFFFFD) {
                Class183.method2620(0);
            }
            else {
                Class134.anInt3461 = Class98_Sub10_Sub21.anInt5643 << 1006717769;
                Class98_Sub46_Sub10.anInt6020 = Class363.anInt3098 << 1638996777;
            }
            Class374.method3980((byte)121);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.B(" + b + ',' + n + ')');
        }
    }
    
    static final int method3270(final int n) {
        try {
            int n2 = 0;
            final Field[] declaredFields = ((Class269.aClass2029 != null) ? Class269.aClass2029 : (Class269.aClass2029 = method3274("Class98_Sub27"))).getDeclaredFields();
            for (int n3 = 0; ~declaredFields.length < ~n3; ++n3) {
                if (((Class269.aClass2030 != null) ? Class269.aClass2030 : (Class269.aClass2030 = method3274("Class64"))).isAssignableFrom(declaredFields[n3].getType())) {
                    ++n2;
                }
            }
            if (n <= 85) {
                method3273(true);
            }
            return 1 + n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.F(" + n + ')');
        }
    }
    
    final void method3271(final boolean b) {
        try {
            synchronized (this.aClass79_2028) {
                this.aClass79_2028.method806((byte)63);
            }
            if (!b) {
                Class269.aClass207_2025 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.A(" + b + ')');
        }
    }
    
    final void method3272(final int n, final int n2) {
        try {
            synchronized (this.aClass79_2028) {
                this.aClass79_2028.method800((byte)62, n2);
            }
            if (n <= 110) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3273(final boolean b) {
        try {
            if (b) {
                for (Class98_Sub33 class98_Sub33 = (Class98_Sub33)Class191.aClass148_1478.method2418(32); class98_Sub33 != null; class98_Sub33 = (Class98_Sub33)Class191.aClass148_1478.method2417(109)) {
                    Class148.method2428(class98_Sub33, false, 15697);
                }
                for (Class98_Sub33 class98_Sub34 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2418(32); class98_Sub34 != null; class98_Sub34 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2417(93)) {
                    Class148.method2428(class98_Sub34, true, 15697);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.D(" + b + ')');
        }
    }
    
    Class269(final Class279 class279, final int n, final Class207 aClass207_2022) {
        this.aClass79_2028 = new Class79(64);
        try {
            (this.aClass207_2022 = aClass207_2022).method2761(0, 31);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qq.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_2022 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static Class method3274(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class269.aStringArray2021 = new String[8];
        Class269.aFloatArray2023 = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        Class269.anInt2024 = -1;
        Class269.aFloatArray2027 = new float[4];
    }
}
