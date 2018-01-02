// 
// Decompiled by Procyon v0.5.30
// 

final class Class249
{
    static long aLong1898;
    int anInt1899;
    int anInt1900;
    short aShort1901;
    short aShort1902;
    static Class128 aClass128_1903;
    boolean aBoolean1904;
    int anInt1905;
    byte aByte1906;
    int anInt1907;
    short aShort1908;
    int anInt1909;
    
    static final String method3160(final String s, final int n) {
        try {
            final int i = s.length();
            int n2 = 0;
            for (int n3 = 0; i > n3; ++n3) {
                final char char1 = s.charAt(n3);
                if (char1 == '<' || ~char1 == 0xFFFFFFC1) {
                    n2 += 3;
                }
            }
            final StringBuffer sb = new StringBuffer(n2 + i);
            for (int n4 = 0; i > n4; ++n4) {
                final char char2 = s.charAt(n4);
                if (~char2 != 0xFFFFFFC3) {
                    if (char2 != '>') {
                        sb.append(char2);
                    }
                    else {
                        sb.append("<gt>");
                    }
                }
                else {
                    sb.append("<lt>");
                }
            }
            if (n != 62) {
                method3162();
            }
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pk.C(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final int method3161(final int n, final int n2, final boolean b, final int n3) {
        try {
            final Class98_Sub3 method669 = Class64_Sub28.method669(n, b, 6);
            if (method669 == null) {
                return 0;
            }
            if (~n3 == 0x0) {
                return 0;
            }
            int n4 = 0;
            for (int n5 = 0; ~n5 > ~method669.anIntArray3823.length; ++n5) {
                if (~n3 == ~method669.anIntArray3824[n5]) {
                    n4 += method669.anIntArray3823[n5];
                }
            }
            return n4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pk.D(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    static final void method3162() {
        for (int i = 0; i < Class98_Sub46_Sub5.aClass174Array5970.length; ++i) {
            Class98_Sub46_Sub5.aClass174Array5970[i].method2565();
        }
        Class98_Sub46_Sub5.aClass174Array5970 = null;
    }
    
    public static void method3163(final byte b) {
        try {
            if (b <= 34) {
                method3161(-115, -43, false, -122);
            }
            Class249.aClass128_1903 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pk.E(" + b + ')');
        }
    }
    
    static final void method3164(final int n, final int n2, final ha ha, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (n5 != -22275) {
                Class249.aClass128_1903 = null;
            }
            if ((Class98_Sub47.aClass332_4273 == null || Class98_Sub10_Sub28.aClass332_5704 == null || Class45.aClass332_383 == null) && Class332_Sub2.aClass207_5423.method2742(-69, Class25.anInt267) && Class332_Sub2.aClass207_5423.method2742(n5 + 22240, Class95.anInt799) && Class332_Sub2.aClass207_5423.method2742(-39, Class146.anInt1183)) {
                final Class324 method3685 = Class324.method3685(Class332_Sub2.aClass207_5423, Class95.anInt799, 0);
                Class98_Sub10_Sub28.aClass332_5704 = ha.method1758(method3685, true);
                method3685.method3691();
                Class221.aClass332_1666 = ha.method1758(method3685, true);
                Class98_Sub47.aClass332_4273 = ha.method1758(Class324.method3685(Class332_Sub2.aClass207_5423, Class25.anInt267, 0), true);
                final Class324 method3686 = Class324.method3685(Class332_Sub2.aClass207_5423, Class146.anInt1183, 0);
                Class45.aClass332_383 = ha.method1758(method3686, true);
                method3686.method3691();
                Class98_Sub50.aClass332_4287 = ha.method1758(method3686, true);
            }
            if (Class98_Sub47.aClass332_4273 != null && Class98_Sub10_Sub28.aClass332_5704 != null && Class45.aClass332_383 != null) {
                for (int n7 = (n + -(2 * Class45.aClass332_383.method3734())) / Class98_Sub47.aClass332_4273.method3734(), i = 0; i < n7; ++i) {
                    Class98_Sub47.aClass332_4273.method3735(n4 + Class45.aClass332_383.method3734() + Class98_Sub47.aClass332_4273.method3734() * i, -Class98_Sub47.aClass332_4273.method3731() + (n3 + n6));
                }
                for (int n8 = (-n2 + n6 - Class45.aClass332_383.method3731()) / Class98_Sub10_Sub28.aClass332_5704.method3731(), j = 0; j < n8; ++j) {
                    Class98_Sub10_Sub28.aClass332_5704.method3735(n4, n2 + n3 + Class98_Sub10_Sub28.aClass332_5704.method3731() * j);
                    Class221.aClass332_1666.method3735(-Class221.aClass332_1666.method3734() + (n + n4), j * Class98_Sub10_Sub28.aClass332_5704.method3731() + n2 + n3);
                }
                Class45.aClass332_383.method3735(n4, -Class45.aClass332_383.method3731() + (n3 - -n6));
                Class98_Sub50.aClass332_4287.method3735(n + n4 - Class45.aClass332_383.method3734(), n3 - (-n6 + Class45.aClass332_383.method3731()));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pk.B(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    Class249(final int anInt1905, final int anInt1906, final int anInt1907, final int anInt1908, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean aBoolean1904, final int anInt1909) {
        try {
            this.anInt1899 = anInt1909;
            this.anInt1909 = anInt1906;
            this.aBoolean1904 = aBoolean1904;
            this.anInt1905 = anInt1905;
            this.anInt1907 = anInt1908;
            this.anInt1900 = anInt1907;
            this.aShort1908 = (short)n;
            this.aShort1901 = (short)n2;
            this.aShort1902 = (short)n3;
            this.aByte1906 = (byte)n5;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pk.<init>(" + anInt1905 + ',' + anInt1906 + ',' + anInt1907 + ',' + anInt1908 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ',' + aBoolean1904 + ',' + anInt1909 + ')');
        }
    }
    
    static {
        Class249.aClass128_1903 = new Class128();
    }
}
