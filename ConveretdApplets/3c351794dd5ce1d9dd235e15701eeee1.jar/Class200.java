// 
// Decompiled by Procyon v0.5.30
// 

final class Class200
{
    static Class111 aClass111_1543;
    
    static final int method2692(final int n) {
        int n2 = -1;
        for (int i = 0; i < Class18.anInt212 - 1; ++i) {
            if (n < s.anIntArray2205[i] + Class15.anIntArray182[i]) {
                n2 = i;
                break;
            }
        }
        if (n2 == -1) {
            n2 = Class18.anInt212 - 1;
        }
        return n2;
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nd.toString()");
        }
    }
    
    static final Class332 method2693(final int n, final byte b, final ha ha) {
        try {
            if (b > -104) {
                return null;
            }
            final Class98_Sub36 class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3990(n, -1);
            if (class98_Sub36 != null) {
                final Class98_Sub43_Sub3 method2908 = class98_Sub36.aClass237_Sub1_4157.method2908(-32675);
                class98_Sub36.aBoolean4158 = true;
                if (method2908 != null) {
                    return method2908.method1501(ha, 11242);
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nd.A(" + n + ',' + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method2694(final String s, final int n, final int n2, final int n3, final byte[] array, final int n4) {
        try {
            final int n5 = -n + n2;
            for (int n6 = 0; ~n5 < ~n6; ++n6) {
                final char char1 = s.charAt(n + n6);
                if ((char1 > '\0' && char1 < '\u0080') || (char1 >= ' ' && ~char1 >= -256)) {
                    array[n3 + n6] = (byte)char1;
                }
                else if (~char1 == 0xFFFFDF53) {
                    array[n6 + n3] = -128;
                }
                else if (~char1 == 0xFFFFDFE5) {
                    array[n6 + n3] = -126;
                }
                else if (char1 == '\u0192') {
                    array[n3 + n6] = -125;
                }
                else if (~char1 != 0xFFFFDFE1) {
                    if (~char1 == 0xFFFFDFD9) {
                        array[n6 + n3] = -123;
                    }
                    else if (~char1 == 0xFFFFDFDF) {
                        array[n3 - -n6] = -122;
                    }
                    else if (char1 == '\u2021') {
                        array[n6 + n3] = -121;
                    }
                    else if (char1 != '\u02c6') {
                        if (char1 == '\u2030') {
                            array[n6 + n3] = -119;
                        }
                        else if (char1 == '\u0160') {
                            array[n3 + n6] = -118;
                        }
                        else if (~char1 != 0xFFFFDFC6) {
                            if (~char1 != 0xFFFFFEAD) {
                                if (~char1 == 0xFFFFFE82) {
                                    array[n6 + n3] = -114;
                                }
                                else if (~char1 != 0xFFFFDFE7) {
                                    if (char1 != '\u2019') {
                                        if (char1 != '\u201c') {
                                            if (char1 == '\u201d') {
                                                array[n3 + n6] = -108;
                                            }
                                            else if (char1 == '\u2022') {
                                                array[n3 - -n6] = -107;
                                            }
                                            else if (~char1 == 0xFFFFDFEC) {
                                                array[n6 + n3] = -106;
                                            }
                                            else if (~char1 != 0xFFFFDFEB) {
                                                if (char1 == '\u02dc') {
                                                    array[n3 - -n6] = -104;
                                                }
                                                else if (~char1 != 0xFFFFDEDD) {
                                                    if (char1 != '\u0161') {
                                                        if (char1 == '\u203a') {
                                                            array[n6 + n3] = -101;
                                                        }
                                                        else if (~char1 == 0xFFFFFEAC) {
                                                            array[n6 + n3] = -100;
                                                        }
                                                        else if (char1 != '\u017e') {
                                                            if (char1 == '\u0178') {
                                                                array[n6 + n3] = -97;
                                                            }
                                                            else {
                                                                array[n3 - -n6] = 63;
                                                            }
                                                        }
                                                        else {
                                                            array[n6 + n3] = -98;
                                                        }
                                                    }
                                                    else {
                                                        array[n6 + n3] = -102;
                                                    }
                                                }
                                                else {
                                                    array[n6 + n3] = -103;
                                                }
                                            }
                                            else {
                                                array[n6 + n3] = -105;
                                            }
                                        }
                                        else {
                                            array[n3 + n6] = -109;
                                        }
                                    }
                                    else {
                                        array[n6 + n3] = -110;
                                    }
                                }
                                else {
                                    array[n3 - -n6] = -111;
                                }
                            }
                            else {
                                array[n6 + n3] = -116;
                            }
                        }
                        else {
                            array[n6 + n3] = -117;
                        }
                    }
                    else {
                        array[n3 + n6] = -120;
                    }
                }
                else {
                    array[n3 - -n6] = -124;
                }
            }
            if (n4 != -28439) {
                Class200.aClass111_1543 = null;
            }
            return n5;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nd.C(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + n4 + ')');
        }
    }
    
    public static void method2695(final int n) {
        try {
            Class200.aClass111_1543 = null;
            if (n != -382) {
                Class200.aClass111_1543 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nd.B(" + n + ')');
        }
    }
}
