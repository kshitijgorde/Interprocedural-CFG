// 
// Decompiled by Procyon v0.5.30
// 

final class Class378
{
    int anInt3187;
    int anInt3188;
    static Class79 aClass79_3189;
    static float aFloat3190;
    int anInt3191;
    
    static final void method4001(final byte b) {
        try {
            Class242.aClass244Array1851 = null;
            if (b != -34) {
                method4001((byte)62);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wu.A(" + b + ')');
        }
    }
    
    public static void method4002(final int n) {
        try {
            Class378.aClass79_3189 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wu.D(" + n + ')');
        }
    }
    
    static final int method4003(final int n, final int n2, final byte[][] array, final boolean b, final int[] array2, final byte[][] array3, final int[] array4, final byte[] array5) {
        try {
            if (b) {
                return 25;
            }
            final int n3 = array4[n2];
            final int n4 = n3 + array2[n2];
            final int n5 = array4[n];
            final int n6 = n5 - -array2[n];
            int n7;
            if ((n7 = n3) < n5) {
                n7 = n5;
            }
            int n8;
            if (n6 < (n8 = n4)) {
                n8 = n6;
            }
            int n9 = 0xFF & array5[n2];
            if ((array5[n] & 0xFF) < n9) {
                n9 = (0xFF & array5[n]);
            }
            final byte[] array6 = array3[n2];
            final byte[] array7 = array[n];
            int n10 = -n3 + n7;
            int n11 = -n5 + n7;
            for (int n12 = n7; ~n8 < ~n12; ++n12) {
                final byte b2 = (byte)(array7[n11++] + array6[n10++]);
                if (b2 < n9) {
                    n9 = b2;
                }
            }
            return -n9;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wu.E(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ',' + ((array4 != null) ? "{...}" : "null") + ',' + ((array5 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method4004(final byte b, final int n, final int n2) {
        try {
            if (n2 >= 1000 && ~n > -1001) {
                return true;
            }
            if (n2 < 1000 && n < 1000) {
                return Class343.method3817((byte)(-98), n) || !Class343.method3817((byte)114, n2);
            }
            if (n2 >= 1000 && n >= 1000) {
                return true;
            }
            if (b < 65) {
                method4002(77);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wu.C(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method4005(final int n, final int n2, final int n3, final boolean b, final int n4) {
        try {
            if (Class85.method837(n2, 86)) {
                Class224_Sub2.method2837(b, Class159.aClass293ArrayArray1252[n2], n, true, n4, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wu.B(" + n + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ')');
        }
    }
    
    static final String method4006(final String s, final int n) {
        try {
            final StringBuffer sb = new StringBuffer();
            for (int i = s.length(), n2 = 0; i > n2; ++n2) {
                final char char1 = s.charAt(n2);
                if (~char1 == 0xFFFFFFDA && ~i < ~(2 + n2)) {
                    final char char2 = s.charAt(n2 + 1);
                    int n3;
                    if (char2 < '0' || ~char2 < -58) {
                        if (~char2 <= -98 && char2 <= 'f') {
                            n3 = -97 + (char2 + '\n');
                        }
                        else {
                            if (char2 < 'A' || char2 > 'F') {
                                sb.append('%');
                                continue;
                            }
                            n3 = '\n' - -char2 - 'A';
                        }
                    }
                    else {
                        n3 = -48 + char2;
                    }
                    final int n4 = n3 * 16;
                    final char char3 = s.charAt(n2 + 2);
                    int n5;
                    if (~char3 <= -49 && ~char3 >= -58) {
                        n5 = n4 + (-48 + char3);
                    }
                    else if (char3 >= 'a' && ~char3 >= -103) {
                        n5 = n4 + (-87 - -char3);
                    }
                    else {
                        if (char3 < 'A' || ~char3 < -71) {
                            sb.append('%');
                            continue;
                        }
                        n5 = n4 + (-65 + ('\n' + char3));
                    }
                    n2 += 2;
                    if (~n5 != -1 && Class302.method3553(-127, (byte)n5)) {
                        sb.append(Class64_Sub7.method576((byte)n5, (byte)125));
                    }
                }
                else if (~char1 != 0xFFFFFFD4) {
                    sb.append(char1);
                }
                else {
                    sb.append(' ');
                }
            }
            if (n != -1) {
                Class378.aFloat3190 = -2.4350014f;
            }
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wu.F(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class378.aClass79_3189 = new Class79(8);
    }
}
