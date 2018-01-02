import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub40 extends Class98
{
    static Class164 aClass164_4190;
    short aShort4191;
    static Class88 aClass88_4192;
    static OutgoingOpcode aClass171_4193;
    static int anInt4194;
    static Class98_Sub46_Sub10 aClass98_Sub46_Sub10_4195;
    static int anInt4196;
    static int anInt4197;
    static Class207 aClass207_4198;
    
    static final Class52 method1469(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            return new Class52(class98_Sub22.readUnsignedByte((byte)(-105)), Class98_Sub46_Sub13_Sub1.method1595(n + 123)[class98_Sub22.readUnsignedByte((byte)79)], Class331.method3723(256)[class98_Sub22.readUnsignedByte((byte)(-127))], class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readInt(-2), class98_Sub22.readInt(-2), class98_Sub22.readInt(-2), ~class98_Sub22.readUnsignedByte((byte)96) == n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qd.D(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class98_Sub40() {
    }
    
    static final void method1470(final int n) {
        try {
            Class3.anImage74 = null;
            if (n != 3796) {
                Class98_Sub40.aClass164_4190 = null;
            }
            Class98_Sub10_Sub7.aFont5576 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qd.B(" + n + ')');
        }
    }
    
    static final String method1471(final int n, final long n2) {
        try {
            Class149.aCalendar1200.setTime(new Date(n2));
            final int value = Class149.aCalendar1200.get(7);
            if (n != 5090) {
                method1473(null, -92, null, 85, 126, null);
            }
            final int value2 = Class149.aCalendar1200.get(5);
            final int value3 = Class149.aCalendar1200.get(2);
            final int value4 = Class149.aCalendar1200.get(1);
            final int value5 = Class149.aCalendar1200.get(11);
            final int value6 = Class149.aCalendar1200.get(12);
            final int value7 = Class149.aCalendar1200.get(13);
            return Class32.aStringArray304[-1 + value] + ", " + value2 / 10 + value2 % 10 + "-" + Class98_Sub10_Sub4.aStringArray5550[value3] + "-" + value4 + " " + value5 / 10 + value5 % 10 + ":" + value6 / 10 + value6 % 10 + ":" + value7 / 10 + value7 % 10 + " GMT";
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qd.A(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method1472(final int n) {
        try {
            Class98_Sub40.aClass164_4190 = null;
            Class98_Sub40.aClass98_Sub46_Sub10_4195 = null;
            Class98_Sub40.aClass88_4192 = null;
            if (n != -1) {
                Class98_Sub40.aClass164_4190 = null;
            }
            Class98_Sub40.aClass171_4193 = null;
            Class98_Sub40.aClass207_4198 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qd.E(" + n + ')');
        }
    }
    
    Class98_Sub40(final short aShort4191) {
        try {
            this.aShort4191 = aShort4191;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qd.<init>(" + aShort4191 + ')');
        }
    }
    
    static final boolean method1473(final Class24 class24, final int n, final Class98_Sub47 class98_Sub47, final int n2, final int n3, final ha ha) {
        try {
            int n4 = Integer.MAX_VALUE;
            int n5 = Integer.MIN_VALUE;
            int n6 = Integer.MAX_VALUE;
            int n7 = Integer.MIN_VALUE;
            if (class24.anIntArray265 != null) {
                n4 = (-Class278.anInt2086 + Class278.anInt2093) * (class24.anInt244 + class98_Sub47.anInt4272 - Class278.anInt2091) / (-Class278.anInt2091 + Class278.anInt2074) + Class278.anInt2086;
                n7 = Class278.anInt2094 + -((class24.anInt248 + (class98_Sub47.anInt4267 - Class278.anInt2090)) * (Class278.anInt2094 - Class278.anInt2077) / (Class278.anInt2083 + -Class278.anInt2090));
                n5 = (class98_Sub47.anInt4272 + (class24.anInt247 + -Class278.anInt2091)) * (Class278.anInt2093 + -Class278.anInt2086) / (Class278.anInt2074 + -Class278.anInt2091) + Class278.anInt2086;
                n6 = -((class24.anInt262 + (class98_Sub47.anInt4267 - Class278.anInt2090)) * (Class278.anInt2094 - Class278.anInt2077) / (-Class278.anInt2090 + Class278.anInt2083)) + Class278.anInt2094;
            }
            Class332 class25 = null;
            int anInt3996 = 0;
            int anInt3997 = 0;
            int anInt3998 = 0;
            int anInt3999 = 0;
            if (class24.anInt245 != -1) {
                if (class98_Sub47.aBoolean4275 && class24.anInt225 != -1) {
                    class25 = class24.method287((byte)92, ha, true);
                }
                else {
                    class25 = class24.method287((byte)92, ha, false);
                }
                if (class25 != null) {
                    anInt3996 = class98_Sub47.anInt4266 - (1 + class25.method3737() >> -1309311967);
                    if (anInt3996 < n4) {
                        n4 = anInt3996;
                    }
                    anInt3997 = class98_Sub47.anInt4266 - -(class25.method3737() + 1 >> -1382798303);
                    if (n5 < anInt3997) {
                        n5 = anInt3997;
                    }
                    anInt3998 = class98_Sub47.anInt4271 + -(1 + class25.method3749() >> 682299937);
                    if (~n6 < ~anInt3998) {
                        n6 = anInt3998;
                    }
                    anInt3999 = class98_Sub47.anInt4271 - -(1 + class25.method3749() >> -261887423);
                    if (~anInt3999 < ~n7) {
                        n7 = anInt3999;
                    }
                }
            }
            Class326 method1718 = null;
            int i = 0;
            int n8 = 0;
            int n9 = 0;
            if (n3 != 15924) {
                method1469(-104, null);
            }
            int n10 = 0;
            int anInt4000 = 0;
            int anInt4001 = 0;
            int anInt4002 = 0;
            int anInt4003 = 0;
            if (class24.aString263 != null) {
                method1718 = Class105.method1718(class24.anInt264, 5466);
                if (method1718 != null) {
                    i = Class98_Sub46_Sub2_Sub2.aClass197_6296.method2675(class24.aString263, Class35.aStringArray335, null, null, -1);
                    n8 = (-Class278.anInt2086 + Class278.anInt2093) * class24.anInt235 / (-Class278.anInt2091 + Class278.anInt2074) + class98_Sub47.anInt4266;
                    final int n11 = class98_Sub47.anInt4271 + -(class24.anInt252 * (-Class278.anInt2077 + Class278.anInt2094) / (-Class278.anInt2090 + Class278.anInt2083));
                    if (class25 != null) {
                        n9 = n11 - ((class25.method3749() >> 1059165249) + method1718.method3705() * i);
                    }
                    else {
                        n9 = n11 - method1718.method3704() * i / 2;
                    }
                    for (int n12 = 0; i > n12; ++n12) {
                        String substring = Class35.aStringArray335[n12];
                        if (~n12 > ~(i - 1)) {
                            substring = substring.substring(0, -4 + substring.length());
                        }
                        final int method1719 = method1718.method3701(substring);
                        if (n10 < method1719) {
                            n10 = method1719;
                        }
                    }
                    anInt4000 = n8 + (-(n10 / 2) + n2);
                    if (~anInt4000 > ~n4) {
                        n4 = anInt4000;
                    }
                    anInt4001 = n2 + (n10 / 2 + n8);
                    anInt4002 = n + n9;
                    if (~n5 > ~anInt4001) {
                        n5 = anInt4001;
                    }
                    anInt4003 = n + (i * method1718.method3705() + n9);
                    if (~n6 < ~anInt4002) {
                        n6 = anInt4002;
                    }
                    if (~anInt4003 < ~n7) {
                        n7 = anInt4003;
                    }
                }
            }
            if (Class278.anInt2086 > n5 || n4 > Class278.anInt2093 || Class278.anInt2077 > n7 || n6 > Class278.anInt2094) {
                return true;
            }
            Class278.method3314(ha, class98_Sub47, class24);
            if (class25 != null) {
                if (~Class64_Sub25.anInt3711 < -1 && ((~Class98_Sub5_Sub2.anInt5536 != 0x0 && class98_Sub47.anInt4268 == Class98_Sub5_Sub2.anInt5536) || (~Class256.anInt1945 != 0x0 && ~class24.anInt246 == ~Class256.anInt1945))) {
                    int n13;
                    if (Class287.anInt2186 <= 50) {
                        n13 = Class287.anInt2186 * 2;
                    }
                    else {
                        n13 = 200 + -(Class287.anInt2186 * 2);
                    }
                    final int n14 = 0xFFFF00 | n13 << -702181928;
                    ha.method1757(class25.method3734() / 2 + 7, class98_Sub47.anInt4271, n14, 85, class98_Sub47.anInt4266);
                    ha.method1757(class25.method3734() / 2 + 5, class98_Sub47.anInt4271, n14, 117, class98_Sub47.anInt4266);
                    ha.method1757(class25.method3734() / 2 + 3, class98_Sub47.anInt4271, n14, 92, class98_Sub47.anInt4266);
                    ha.method1757(class25.method3734() / 2 + 1, class98_Sub47.anInt4271, n14, 37, class98_Sub47.anInt4266);
                    ha.method1757(class25.method3734() / 2, class98_Sub47.anInt4271, n14, 95, class98_Sub47.anInt4266);
                }
                class25.method3735(class98_Sub47.anInt4266 - (class25.method3737() >> 160193697), class98_Sub47.anInt4271 - (class25.method3749() >> -277635711));
            }
            if (class24.aString263 != null && method1718 != null) {
                Class126.method2217((byte)12, class98_Sub47, n10, ha, method1718, n9, class24, i, n8);
            }
            if (class24.anInt245 != -1 || class24.aString263 != null) {
                final Class98_Sub23 class98_Sub48 = new Class98_Sub23(class98_Sub47);
                class98_Sub48.anInt4000 = anInt3999;
                class98_Sub48.anInt4005 = anInt4002;
                class98_Sub48.anInt3999 = anInt3998;
                class98_Sub48.anInt4004 = anInt4003;
                class98_Sub48.anInt4003 = anInt4000;
                class98_Sub48.anInt4002 = anInt4001;
                class98_Sub48.anInt3996 = anInt3996;
                class98_Sub48.anInt4006 = anInt3997;
                Class8.aClass148_110.method2419(class98_Sub48, n3 ^ 0xFFFF9065);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qd.C(" + ((class24 != null) ? "{...}" : "null") + ',' + n + ',' + ((class98_Sub47 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub40.aClass164_4190 = new Class164(3);
        Class98_Sub40.aClass171_4193 = new OutgoingOpcode(12, 0);
    }
}
