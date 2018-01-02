// 
// Decompiled by Procyon v0.5.30
// 

abstract class aa
{
    static int anInt48;
    static OutgoingOpcode aClass171_49;
    static Class28 aClass28_50;
    static int anInt51;
    
    public static void method151(final boolean b) {
        try {
            if (!b) {
                method151(false);
            }
            aa.aClass28_50 = null;
            aa.aClass171_49 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aa.E(" + b + ')');
        }
    }
    
    static final byte[] method152(final int n, final String s) {
        try {
            final int length = s.length();
            final byte[] array = new byte[length];
            for (int n2 = n; ~length < ~n2; ++n2) {
                final char char1 = s.charAt(n2);
                if ((char1 > '\0' && ~char1 > -129) || (~char1 <= -161 && ~char1 >= -256)) {
                    array[n2] = (byte)char1;
                }
                else if (char1 != '\u20ac') {
                    if (~char1 != 0xFFFFDFE5) {
                        if (char1 == '\u0192') {
                            array[n2] = -125;
                        }
                        else if (~char1 == 0xFFFFDFE1) {
                            array[n2] = -124;
                        }
                        else if (char1 == '\u2026') {
                            array[n2] = -123;
                        }
                        else if (~char1 != 0xFFFFDFDF) {
                            if (~char1 != 0xFFFFDFDE) {
                                if (char1 == '\u02c6') {
                                    array[n2] = -120;
                                }
                                else if (~char1 != 0xFFFFDFCF) {
                                    if (~char1 != 0xFFFFFE9F) {
                                        if (char1 != '\u2039') {
                                            if (~char1 == 0xFFFFFEAD) {
                                                array[n2] = -116;
                                            }
                                            else if (char1 == '\u017d') {
                                                array[n2] = -114;
                                            }
                                            else if (~char1 == 0xFFFFDFE7) {
                                                array[n2] = -111;
                                            }
                                            else if (char1 != '\u2019') {
                                                if (~char1 == 0xFFFFDFE3) {
                                                    array[n2] = -109;
                                                }
                                                else if (char1 == '\u201d') {
                                                    array[n2] = -108;
                                                }
                                                else if (~char1 != 0xFFFFDFDD) {
                                                    if (char1 != '\u2013') {
                                                        if (char1 != '\u2014') {
                                                            if (char1 == '\u02dc') {
                                                                array[n2] = -104;
                                                            }
                                                            else if (~char1 == 0xFFFFDEDD) {
                                                                array[n2] = -103;
                                                            }
                                                            else if (char1 != '\u0161') {
                                                                if (char1 == '\u203a') {
                                                                    array[n2] = -101;
                                                                }
                                                                else if (~char1 == 0xFFFFFEAC) {
                                                                    array[n2] = -100;
                                                                }
                                                                else if (~char1 == 0xFFFFFE81) {
                                                                    array[n2] = -98;
                                                                }
                                                                else if (char1 == '\u0178') {
                                                                    array[n2] = -97;
                                                                }
                                                                else {
                                                                    array[n2] = 63;
                                                                }
                                                            }
                                                            else {
                                                                array[n2] = -102;
                                                            }
                                                        }
                                                        else {
                                                            array[n2] = -105;
                                                        }
                                                    }
                                                    else {
                                                        array[n2] = -106;
                                                    }
                                                }
                                                else {
                                                    array[n2] = -107;
                                                }
                                            }
                                            else {
                                                array[n2] = -110;
                                            }
                                        }
                                        else {
                                            array[n2] = -117;
                                        }
                                    }
                                    else {
                                        array[n2] = -118;
                                    }
                                }
                                else {
                                    array[n2] = -119;
                                }
                            }
                            else {
                                array[n2] = -121;
                            }
                        }
                        else {
                            array[n2] = -122;
                        }
                    }
                    else {
                        array[n2] = -126;
                    }
                }
                else {
                    array[n2] = -128;
                }
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aa.D(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        aa.aClass171_49 = new OutgoingOpcode(46, 8);
    }
}
