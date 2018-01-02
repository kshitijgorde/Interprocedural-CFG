// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class206
{
    static int anInt1567;
    static int anInt1568;
    
    static final void method2723(final int n, final int n2) {
        final Class172 aClass172_1330 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n][n2];
        for (int i = 0; i < 3; ++i) {
            final Class172[] array = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[i][n];
            final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[i + 1][n][n2];
            array[n2] = class172;
            final Class172 class173 = class172;
            if (class173 != null) {
                for (Class154 class174 = class173.aClass154_1325; class174 != null; class174 = class174.aClass154_1233) {
                    final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232 = class174.aClass246_Sub3_Sub4_1232;
                    if (aClass246_Sub3_Sub4_1232.aShort6158 == n && aClass246_Sub3_Sub4_1232.aShort6157 == n2) {
                        final Class246_Sub3_Sub4 class246_Sub3_Sub4 = aClass246_Sub3_Sub4_1232;
                        --class246_Sub3_Sub4.aByte5088;
                    }
                }
                if (class173.aClass246_Sub3_Sub1_1332 != null) {
                    final Class246_Sub3_Sub1 aClass246_Sub3_Sub1_1332 = class173.aClass246_Sub3_Sub1_1332;
                    --aClass246_Sub3_Sub1_1332.aByte5088;
                }
                if (class173.aClass246_Sub3_Sub3_1324 != null) {
                    final Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1324 = class173.aClass246_Sub3_Sub3_1324;
                    --aClass246_Sub3_Sub3_1324.aByte5088;
                }
                if (class173.aClass246_Sub3_Sub3_1333 != null) {
                    final Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1325 = class173.aClass246_Sub3_Sub3_1333;
                    --aClass246_Sub3_Sub3_1325.aByte5088;
                }
                if (class173.aClass246_Sub3_Sub5_1334 != null) {
                    final Class246_Sub3_Sub5 aClass246_Sub3_Sub5_1334 = class173.aClass246_Sub3_Sub5_1334;
                    --aClass246_Sub3_Sub5_1334.aByte5088;
                }
                if (class173.aClass246_Sub3_Sub5_1326 != null) {
                    final Class246_Sub3_Sub5 aClass246_Sub3_Sub5_1335 = class173.aClass246_Sub3_Sub5_1326;
                    --aClass246_Sub3_Sub5_1335.aByte5088;
                }
            }
        }
        if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n][n2] == null) {
            Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n][n2] = new Class172(0);
            Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n][n2].aByte1322 = 1;
        }
        Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n][n2].aClass172_1330 = aClass172_1330;
        Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[3][n][n2] = null;
    }
    
    static final void method2724(final byte b) {
        try {
            if (Class98_Sub31_Sub2.anInt5822 <= 0) {
                Class45.aString382 = "";
            }
            else {
                int n = 0;
                for (int i = 0; i < Class98_Sub46_Sub20.aStringArray6073.length; ++i) {
                    if (Class98_Sub46_Sub20.aStringArray6073[i].indexOf("--> ") != -1 && ~Class98_Sub31_Sub2.anInt5822 == ~(++n)) {
                        Class45.aString382 = Class98_Sub46_Sub20.aStringArray6073[i].substring(Class98_Sub46_Sub20.aStringArray6073[i].indexOf(">") + 2);
                        break;
                    }
                }
            }
            if (b <= 122) {
                method2725(-3, 116, -39);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nha.D(" + b + ')');
        }
    }
    
    static final boolean method2725(final int n, final int n2, final int n3) {
        try {
            if (n != 32768) {
                Class206.anInt1567 = -40;
            }
            return ~(0x8000 & n2) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nha.A(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    abstract Class98_Sub46_Sub2 method2726(final int p0, final Class98_Sub46_Sub2 p1);
    
    static final void method2727(final int n) {
        try {
            if (n < 20) {
                Class206.anInt1567 = -40;
            }
            if (!Class232.aBoolean1744) {
                Class246_Sub4_Sub1.method3105((byte)(-89), Class246_Sub2.aClass172ArrayArrayArray5077);
                if (Class252.aClass172ArrayArrayArray1927 != null) {
                    Class246_Sub4_Sub1.method3105((byte)(-121), Class252.aClass172ArrayArrayArray1927);
                }
                Class232.aBoolean1744 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nha.C(" + n + ')');
        }
    }
    
    static {
        new Class105("", 73);
    }
}
