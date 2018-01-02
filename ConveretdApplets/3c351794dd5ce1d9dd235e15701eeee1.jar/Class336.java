import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class336
{
    static int anInt2820;
    long aLong2821;
    static Class232 aClass232_2822;
    static int anInt2823;
    private ha_Sub1 aHa_Sub1_2824;
    static IncomingOpcode aClass58_2825;
    static int[] anIntArray2826;
    static Class148 aClass148_2827;
    static Class66[] aClass66Array2828;
    static Class105 aClass105_2829;
    
    static final int method3772(final String s, final String s2, final int n, final int n2) {
        try {
            final int length = s.length();
            final int length2 = s2.length();
            char c = '\0';
            char c2 = '\0';
            char method3217 = '\0';
            char method3218 = '\0';
            while (length > -method3217 + c || ~length2 < ~(-method3218 + c2)) {
                if (length <= c - method3217) {
                    return -1;
                }
                if (c2 + -method3218 >= length2) {
                    return 1;
                }
                char char1;
                if (~method3217 == -1) {
                    char1 = s.charAt(c++);
                }
                else {
                    char1 = method3217;
                }
                char char2;
                if (method3218 == '\0') {
                    char2 = s2.charAt(c2++);
                }
                else {
                    char2 = method3218;
                }
                method3217 = Class263.method3217(14561, char1);
                method3218 = Class263.method3217(14561, char2);
                final char method3219 = Class76_Sub10.method769(char1, n, (byte)(-85));
                final char method3220 = Class76_Sub10.method769(char2, n, (byte)(-123));
                if (~method3219 == ~method3220 || ~Character.toUpperCase(method3219) == ~Character.toUpperCase(method3220)) {
                    continue;
                }
                final char lowerCase = Character.toLowerCase(method3219);
                final char lowerCase2 = Character.toLowerCase(method3220);
                if (~lowerCase2 != ~lowerCase) {
                    return Class347.method3834(n, lowerCase, (byte)(-127)) - Class347.method3834(n, lowerCase2, (byte)(-106));
                }
            }
            final int i = Math.min(length, length2);
            for (char c3 = '\0'; c3 < i; ++c3) {
                int n3;
                char c4;
                if (n == 2) {
                    n3 = -1 + length2 + -c3;
                    c4 = (char)(-c3 + (length - '\u0001'));
                }
                else {
                    n3 = (c4 = c3);
                }
                final char char3 = s.charAt(c4);
                final char char4 = s2.charAt(n3);
                if (~char3 != ~char4 && ~Character.toUpperCase(char3) != ~Character.toUpperCase(char4)) {
                    final char lowerCase3 = Character.toLowerCase(char3);
                    final char lowerCase4 = Character.toLowerCase(char4);
                    if (~lowerCase4 != ~lowerCase3) {
                        return Class347.method3834(n, lowerCase3, (byte)(-106)) + -Class347.method3834(n, lowerCase4, (byte)(-103));
                    }
                }
            }
            if (n2 != 1166845806) {
                method3772(null, null, 8, 52);
            }
            final char c5 = (char)(-length2 + length);
            if (c5 != '\0') {
                return c5;
            }
            for (char c6 = '\0'; i > c6; ++c6) {
                final char char5 = s.charAt(c6);
                final char char6 = s2.charAt(c6);
                if (char6 != char5) {
                    return Class347.method3834(n, char5, (byte)(-113)) + -Class347.method3834(n, char6, (byte)(-94));
                }
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uj.B(" + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method3773(final int n, final int n2, final int n3, final int n4, int n5, final int n6, int n7, final int[] array) {
        try {
            if (n5 > 0 && !Class81.method815(n5, 0)) {
                throw new IllegalArgumentException("");
            }
            if (~n7 < -1 && !Class81.method815(n7, 0)) {
                throw new IllegalArgumentException("");
            }
            if (n6 != 32993) {
                throw new IllegalArgumentException("");
            }
            int n8 = 0;
            int n9 = (n5 >= n7) ? n7 : n5;
            int n10 = n5 >> 197585345;
            int i = n7 >> 1631935457;
            int[] array2 = array;
            int[] array3 = new int[n10 * i];
            while (true) {
                OpenGL.glTexImage2Di(n4, n8, n, n5, n7, 0, n6, n3, array2, 0);
                if (~n9 >= -2) {
                    break;
                }
                int n11 = 0;
                int n12 = 0;
                int n13 = n5 + n12;
                final int[] array4 = array3;
                for (int n14 = 0; i > n14; ++n14) {
                    for (int n15 = 0; ~n10 < ~n15; ++n15) {
                        final int n16 = array2[n12++];
                        final int n17 = array2[n12++];
                        final int n18 = array2[n13++];
                        final int n19 = array2[n13++];
                        array3[n11++] = Class41.method366(Class41.method366(Class202.method2702(((0xFF9F & n16) >> -421974616) + ((n17 & 0xFF1C) >> 812194376) + (0xFF & n18 >> -531512024) + ((0xFF74 & n19) >> -1994009752), 1020) << 2110065190, Class41.method366(Class202.method2702(((0xFF69B8 & n16) >> -776339632) + (0xFF & n17 >> 1557675760) + ((0xFF241E & n18) >> -1634852208) + (0xFF & n19 >> -1828159344), 1020) << 1166845806, Class202.method2702(-16777216, (0xFF & n16 >> -43327976) + (n17 >> 243371288 & 0xFF) + (n18 >> 458737752 & 0xFF) + (n19 >> -1038206792 & 0xFF) << -1269502378))), Class202.method2702(255, (0xFF & n16) + (0xFF & n17) + (n18 & 0xFF) + (n19 & 0xFF) >> 786651458));
                    }
                    n12 += n5;
                    n13 += n5;
                }
                array3 = array2;
                n7 = i;
                array2 = array4;
                n5 = n10;
                n10 >>= 1;
                ++n8;
                i >>= 1;
                n9 >>= 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uj.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.aHa_Sub1_2824.method1855(false, this.aLong2821);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uj.finalize()");
        }
    }
    
    public static void method3774(final int n) {
        try {
            if (n == -43327976) {
                Class336.aClass66Array2828 = null;
                Class336.aClass58_2825 = null;
                Class336.anIntArray2826 = null;
                Class336.aClass232_2822 = null;
                Class336.aClass148_2827 = null;
                Class336.aClass105_2829 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uj.A(" + n + ')');
        }
    }
    
    Class336(final ha_Sub1 aHa_Sub1_2824, final long aLong2821, final Class345[] array) {
        try {
            this.aLong2821 = aLong2821;
            this.aHa_Sub1_2824 = aHa_Sub1_2824;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uj.<init>(" + ((aHa_Sub1_2824 != null) ? "{...}" : "null") + ',' + aLong2821 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class336.aClass232_2822 = new Class232();
        Class336.anIntArray2826 = new int[2];
        Class336.aClass58_2825 = new IncomingOpcode(54, 4);
        Class336.aClass148_2827 = new Class148();
        Class336.aClass66Array2828 = new Class66[16];
        Class336.aClass105_2829 = new Class105("", 11);
    }
}
