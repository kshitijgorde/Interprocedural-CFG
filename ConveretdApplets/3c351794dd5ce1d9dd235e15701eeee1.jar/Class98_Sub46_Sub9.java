// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub9 extends Class98_Sub46
{
    static long aLong5997;
    String aString5998;
    Class215 aClass215_5999;
    static OutgoingOpcode aClass171_6000;
    int anInt6001;
    static boolean aBoolean6002;
    static int anInt6003;
    static Class aClass6004;
    
    static final void method1553(final Class246_Sub3 class246_Sub3, final int n, final int n2, final int n3) {
        if (n2 < Class366.anInt3112) {
            final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2 + 1][n3];
            if (class172 != null && class172.aClass246_Sub3_Sub1_1332 != null && class172.aClass246_Sub3_Sub1_1332.method2982((byte)(-84))) {
                class246_Sub3.method2981(class172.aClass246_Sub3_Sub1_1332, (byte)(-94), true, r_Sub2.anInt6333, Class98_Sub10_Sub30.aHa5709, 0, 0);
            }
        }
        if (n3 < Class366.anInt3112) {
            final Class172 class173 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3 + 1];
            if (class173 != null && class173.aClass246_Sub3_Sub1_1332 != null && class173.aClass246_Sub3_Sub1_1332.method2982((byte)(-97))) {
                class246_Sub3.method2981(class173.aClass246_Sub3_Sub1_1332, (byte)115, true, 0, Class98_Sub10_Sub30.aHa5709, 0, r_Sub2.anInt6333);
            }
        }
        if (n2 < Class366.anInt3112 && n3 < Class64_Sub9.anInt3662) {
            final Class172 class174 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2 + 1][n3 + 1];
            if (class174 != null && class174.aClass246_Sub3_Sub1_1332 != null && class174.aClass246_Sub3_Sub1_1332.method2982((byte)(-88))) {
                class246_Sub3.method2981(class174.aClass246_Sub3_Sub1_1332, (byte)117, true, r_Sub2.anInt6333, Class98_Sub10_Sub30.aHa5709, 0, r_Sub2.anInt6333);
            }
        }
        if (n2 < Class366.anInt3112 && n3 > 0) {
            final Class172 class175 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2 + 1][n3 - 1];
            if (class175 != null && class175.aClass246_Sub3_Sub1_1332 != null && class175.aClass246_Sub3_Sub1_1332.method2982((byte)(-118))) {
                class246_Sub3.method2981(class175.aClass246_Sub3_Sub1_1332, (byte)88, true, r_Sub2.anInt6333, Class98_Sub10_Sub30.aHa5709, 0, -r_Sub2.anInt6333);
            }
        }
    }
    
    static final boolean method1554(final int n, final int n2, final int n3) {
        try {
            if (n2 != 22251) {
                method1554(113, 12, -12);
            }
            return ~(n & 0x180) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final boolean method1555(final int n, final Class98_Sub46_Sub8 class98_Sub46_Sub8) {
        try {
            boolean b = true;
            class98_Sub46_Sub8.method1524((byte)(-90));
            if (n < 68) {
                this.aClass215_5999 = null;
            }
            for (Class98_Sub46_Sub8 class98_Sub46_Sub9 = (Class98_Sub46_Sub8)this.aClass215_5999.method2792(-1); class98_Sub46_Sub9 != null; class98_Sub46_Sub9 = (Class98_Sub46_Sub8)this.aClass215_5999.method2787(0), b = false) {
                if (Class378.method4004((byte)93, class98_Sub46_Sub8.anInt5990, class98_Sub46_Sub9.anInt5990)) {
                    Class51.method487(83, class98_Sub46_Sub9, class98_Sub46_Sub8);
                    ++this.anInt6001;
                    return !b;
                }
            }
            this.aClass215_5999.method2785(class98_Sub46_Sub8, -30);
            ++this.anInt6001;
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.A(" + n + ',' + ((class98_Sub46_Sub8 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class98_Sub11 method1556(final boolean b) {
        try {
            final Class98_Sub11 method3410 = Class289.method3410(-1);
            method3410.aClass171_3864 = null;
            if (b) {
                method1554(39, 118, -15);
            }
            method3410.anInt3867 = 0;
            method3410.aClass98_Sub22_Sub1_3865 = new Class98_Sub22_Sub1(5000);
            return method3410;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.B(" + b + ')');
        }
    }
    
    final boolean method1557(final byte b, final Class98_Sub46_Sub8 class98_Sub46_Sub8) {
        try {
            final int method1559 = this.method1559(75);
            class98_Sub46_Sub8.method1524((byte)(-90));
            --this.anInt6001;
            if (b >= -65) {
                this.aClass215_5999 = null;
            }
            if (~this.anInt6001 != -1) {
                return method1559 != this.method1559(123);
            }
            this.method942(73);
            this.method1524((byte)(-90));
            --Class64_Sub12.anInt3672;
            Class98_Sub46_Sub16.aClass79_6046.method805(class98_Sub46_Sub8.aLong5991, this, (byte)(-80));
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.E(" + b + ',' + ((class98_Sub46_Sub8 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method1558(final byte b, final Class98_Sub33 class98_Sub33) {
        try {
            if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 != null) {
                Interface19 interface19 = null;
                if (class98_Sub33.anInt4118 == 0) {
                    interface19 = (Interface19)Class21_Sub1.method268(class98_Sub33.anInt4116, class98_Sub33.anInt4112, class98_Sub33.anInt4113);
                }
                if (b != 109) {
                    Class98_Sub46_Sub9.aBoolean6002 = false;
                }
                if (~class98_Sub33.anInt4118 == 0xFFFFFFFE) {
                    interface19 = (Interface19)Class101.method1701(class98_Sub33.anInt4116, class98_Sub33.anInt4112, class98_Sub33.anInt4113);
                }
                if (~class98_Sub33.anInt4118 == 0xFFFFFFFD) {
                    interface19 = (Interface19)Class97.method931(class98_Sub33.anInt4116, class98_Sub33.anInt4112, class98_Sub33.anInt4113, (Class98_Sub46_Sub9.aClass6004 != null) ? Class98_Sub46_Sub9.aClass6004 : (Class98_Sub46_Sub9.aClass6004 = method1562("Interface19")));
                }
                if (~class98_Sub33.anInt4118 == 0xFFFFFFFC) {
                    interface19 = (Interface19)Class253.method3177(class98_Sub33.anInt4116, class98_Sub33.anInt4112, class98_Sub33.anInt4113);
                }
                if (interface19 != null) {
                    class98_Sub33.anInt4119 = interface19.method64(30472);
                    class98_Sub33.anInt4115 = interface19.method63((byte)20);
                    class98_Sub33.anInt4121 = interface19.method66(4657);
                }
                else {
                    class98_Sub33.anInt4121 = 0;
                    class98_Sub33.anInt4119 = -1;
                    class98_Sub33.anInt4115 = 0;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.G(" + b + ',' + ((class98_Sub33 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method1559(final int n) {
        try {
            if (n <= 73) {
                Class98_Sub46_Sub9.aLong5997 = 94L;
            }
            if (this.aClass215_5999.aClass98_Sub46_1615.aClass98_Sub46_4262 != this.aClass215_5999.aClass98_Sub46_1615) {
                return ((Class98_Sub46_Sub8)this.aClass215_5999.aClass98_Sub46_1615.aClass98_Sub46_4262).anInt5990;
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.F(" + n + ')');
        }
    }
    
    public static void method1560(final byte b) {
        try {
            if (b >= 43) {
                Class98_Sub46_Sub9.aClass171_6000 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.M(" + b + ')');
        }
    }
    
    static final void method1561(final ha ha, final int n) {
        try {
            if (n != -256) {
                Class98_Sub46_Sub9.anInt6003 = 112;
            }
            int method2642 = 0;
            int method2643 = 0;
            if (za_Sub2.aBoolean6079) {
                method2642 = Class189.method2642((byte)42);
                method2643 = Class335.method3765(false);
            }
            final int n2 = -10660793;
            Class42_Sub1.method381(Class15.anInt172, method2643 + Class104.anInt897, ha, -16777216, n2, Class246_Sub3_Sub4_Sub4.anInt6488, 8516, Class38.anInt355 + method2642);
            Class98_Sub10_Sub34.aClass43_5730.method411((byte)(-73), 14 + Class104.anInt897 + method2643, Class309.aClass309_2610.method3615(Class374.anInt3159, (byte)25), n2, -1, 3 + method2642 + Class38.anInt355);
            final int n3 = Class2.aClass299_73.method3514(68) + method2642;
            final int n4 = Class2.aClass299_73.method3507((byte)90) + method2643;
            if (Class248.aBoolean1896) {
                int n5 = 0;
                for (Class98_Sub46_Sub9 class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2792(-1); class98_Sub46_Sub9 != null; class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2787(0)) {
                    final int n6 = n5 * 16 + (31 + (Class104.anInt897 + method2643));
                    ++n5;
                    if (class98_Sub46_Sub9.anInt6001 == 1) {
                        Class246_Sub3_Sub2_Sub1.method3009(Class15.anInt172, (Class98_Sub46_Sub8)class98_Sub46_Sub9.aClass215_5999.aClass98_Sub46_1615.aClass98_Sub46_4262, -256, Class246_Sub3_Sub4_Sub4.anInt6488, n6, method2643 + Class104.anInt897, n3, ha, n4, 0, -1, Class38.anInt355 - -method2642);
                    }
                    else {
                        Class320.method3663(class98_Sub46_Sub9, -1, n3, Class15.anInt172, n4, method2642 + Class38.anInt355, ha, n6, -256, 0, Class246_Sub3_Sub4_Sub4.anInt6488, method2643 + Class104.anInt897);
                    }
                }
                if (Class308.aClass98_Sub46_Sub9_2583 != null) {
                    Class42_Sub1.method381(Class98_Sub43_Sub4.anInt5938, Class163.anInt3518, ha, -16777216, n2, Class5.anInt3439, 8516, Class282.anInt2128);
                    Class98_Sub10_Sub34.aClass43_5730.method411((byte)(-50), Class163.anInt3518 + 14, Class308.aClass98_Sub46_Sub9_2583.aString5998, n2, -1, Class282.anInt2128 + 3);
                    int n7 = 0;
                    for (Class98_Sub46_Sub8 class98_Sub46_Sub10 = (Class98_Sub46_Sub8)Class308.aClass98_Sub46_Sub9_2583.aClass215_5999.method2792(n + 255); class98_Sub46_Sub10 != null; class98_Sub46_Sub10 = (Class98_Sub46_Sub8)Class308.aClass98_Sub46_Sub9_2583.aClass215_5999.method2787(0)) {
                        Class246_Sub3_Sub2_Sub1.method3009(Class98_Sub43_Sub4.anInt5938, class98_Sub46_Sub10, -256, Class5.anInt3439, 16 * n7 + (31 + Class163.anInt3518), Class163.anInt3518, n3, ha, n4, 0, -1, Class282.anInt2128);
                        ++n7;
                    }
                    Class351.method3849(Class98_Sub43_Sub4.anInt5938, -8, Class282.anInt2128, Class5.anInt3439, Class163.anInt3518);
                }
            }
            else {
                int n8 = 0;
                for (Class98_Sub46_Sub8 class98_Sub46_Sub11 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2418(n ^ 0xFFFFFF20); class98_Sub46_Sub11 != null; class98_Sub46_Sub11 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2417(109)) {
                    Class246_Sub3_Sub2_Sub1.method3009(Class15.anInt172, class98_Sub46_Sub11, -256, Class246_Sub3_Sub4_Sub4.anInt6488, 31 + (method2643 + Class104.anInt897 - -((-n8 - 1 + Class359.anInt3058) * 16)), Class104.anInt897 + method2643, n3, ha, n4, 0, -1, Class38.anInt355 - -method2642);
                    ++n8;
                }
            }
            Class351.method3849(Class15.anInt172, n ^ 0xF8, method2642 + Class38.anInt355, Class246_Sub3_Sub4_Sub4.anInt6488, method2643 + Class104.anInt897);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.D(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    Class98_Sub46_Sub9(final String aString5998) {
        try {
            this.aString5998 = aString5998;
            this.aClass215_5999 = new Class215();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "f.<init>(" + ((aString5998 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static Class method1562(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class98_Sub46_Sub9.aLong5997 = 0L;
        Class98_Sub46_Sub9.aClass171_6000 = new OutgoingOpcode(0, 8);
        Class98_Sub46_Sub9.aBoolean6002 = false;
    }
}
