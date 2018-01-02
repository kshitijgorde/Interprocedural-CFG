// 
// Decompiled by Procyon v0.5.30
// 

final class Class320
{
    static IncomingOpcode aClass58_2708;
    
    static final void method3663(final Class98_Sub46_Sub9 class98_Sub46_Sub9, int n, final int n2, final int n3, final int n4, final int n5, final ha ha, final int n6, final int n7, final int n8, final int n9, final int n10) {
        try {
            if (~n5 > ~n2 && n2 < n5 + n9 && n4 > n6 - 13 && n4 < 3 + n6) {
                n = n7;
            }
            Class98_Sub10_Sub34.aClass43_5730.method413(n6, Class64_Sub5.anIntArray3652, n8, Class21.method262(class98_Sub46_Sub9, (byte)36), n, 3 + n5, (byte)18, Class217.aClass332Array3408);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tn.B(" + ((class98_Sub46_Sub9 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    public static void method3664(final int n) {
        try {
            Class320.aClass58_2708 = null;
            if (n != 5989) {
                Class320.aClass58_2708 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tn.A(" + n + ')');
        }
    }
    
    static final void method3665(final byte b, final String s, final int n, final boolean b2) {
        try {
            Class61.method540((byte)(-51));
            if (n == 0) {
                Class265.aHa1974 = Class76_Sub11.method771(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055.method648((byte)120) * 2, Class42_Sub3.aCanvas5361, b ^ 0x27, Class284_Sub2_Sub2.aD6203, 0, Class212.aClass207_1603);
                if (s != null) {
                    Class265.aHa1974.GA(0);
                    final Class197 method1733 = Class109.method1733((byte)120, 0, Class269.anInt2026, Class36.aClass207_348);
                    final Class43 method1734 = Class265.aHa1974.method1804(method1733, Class324.method3680(Class332_Sub2.aClass207_5423, Class269.anInt2026, 0), true);
                    Class263.method3216(14059);
                    Class246_Sub2.method2972(-127, method1733, method1734, true, Class265.aHa1974, s);
                }
            }
            else {
                ha method1735 = null;
                if (s != null) {
                    method1735 = Class76_Sub11.method771(0, Class42_Sub3.aCanvas5361, b - 21, Class284_Sub2_Sub2.aD6203, 0, Class212.aClass207_1603);
                    method1735.GA(0);
                    final Class197 method1736 = Class109.method1733((byte)115, 0, Class269.anInt2026, Class36.aClass207_348);
                    final Class43 method1737 = method1735.method1804(method1736, Class324.method3680(Class332_Sub2.aClass207_5423, Class269.anInt2026, 0), true);
                    Class263.method3216(14059);
                    Class246_Sub2.method2972(-99, method1736, method1737, true, method1735, s);
                }
                try {
                    Class265.aHa1974 = Class76_Sub11.method771(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055.method648((byte)127) * 2, Class42_Sub3.aCanvas5361, 79, Class284_Sub2_Sub2.aD6203, n, Class212.aClass207_1603);
                    if (s != null) {
                        method1735.GA(0);
                        final Class197 method1738 = Class109.method1733((byte)124, 0, Class269.anInt2026, Class36.aClass207_348);
                        final Class43 method1739 = method1735.method1804(method1738, Class324.method3680(Class332_Sub2.aClass207_5423, Class269.anInt2026, 0), true);
                        Class263.method3216(14059);
                        Class246_Sub2.method2972(-127, method1738, method1739, true, method1735, s);
                    }
                    if (Class265.aHa1974.method1824()) {
                        int n2 = 1;
                        try {
                            n2 = ((Exception_Sub1.aClass98_Sub35_47.anInt4129 > 256) ? 1 : 0);
                        }
                        catch (Throwable t) {}
                        za za;
                        if (n2 == 0) {
                            za = Class265.aHa1974.method1762(104857600);
                        }
                        else {
                            za = Class265.aHa1974.method1762(146800640);
                        }
                        Class265.aHa1974.a(za);
                    }
                }
                catch (Throwable t2) {
                    final int method1740 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)120);
                    if (~method1740 == 0xFFFFFFFD) {
                        Class223.aBoolean1679 = true;
                    }
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042);
                    method3665((byte)69, s, method1740, b2);
                    return;
                }
                finally {
                    if (method1735 != null) {
                        try {
                            method1735.method1743(-1);
                        }
                        catch (Throwable t3) {}
                    }
                }
            }
            Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method582(!b2, false);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042);
            Class39.method353((byte)(-79));
            Class265.aHa1974.method1798(10000);
            Class265.aHa1974.X(32);
            Class266.aClass111_1986 = Class265.aHa1974.method1821();
            Class76_Sub5.aClass111_3745 = Class265.aHa1974.method1821();
            Class98_Sub10_Sub34.method1104(110);
            if (b != 69) {
                method3664(-5);
            }
            Class265.aHa1974.method1749(~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub9_4067.method588((byte)122) == 0xFFFFFFFE);
            if (Class265.aHa1974.method1767()) {
                Class98_Sub5_Sub1.method966(29089, ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065.method570((byte)122) == 0xFFFFFFFE);
            }
            Class159.method2508(Class165.anInt1276 >> 1270556451, Class98_Sub10_Sub7.anInt5572 >> 1930762467, (byte)(-50), Class265.aHa1974);
            Class374.method3980((byte)125);
            Class33.aBoolean316 = true;
            PlayerUpdateMask.aClass259Array527 = null;
            s_Sub1.aBoolean5207 = false;
            Class230.method2871(b - 134);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tn.C(" + b + ',' + ((s != null) ? "{...}" : "null") + ',' + n + ',' + b2 + ')');
        }
    }
    
    static {
        Class320.aClass58_2708 = new IncomingOpcode(23, 0);
    }
}
