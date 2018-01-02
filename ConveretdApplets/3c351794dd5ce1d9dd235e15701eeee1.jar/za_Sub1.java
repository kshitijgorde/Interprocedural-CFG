import jaclib.memory.heap.NativeHeap;

// 
// Decompiled by Procyon v0.5.30
// 

final class za_Sub1 extends za
{
    static int anInt6075;
    static int anInt6076;
    static Class98_Sub5[] aClass98_Sub5Array6077;
    NativeHeap aNativeHeap6078;
    
    final void method1677(final byte b) {
        try {
            if (b != 41) {
                method1679(41, -90, (byte)42, null);
            }
            this.aNativeHeap6078.b();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eb.F(" + b + ')');
        }
    }
    
    public static void method1678(final int n) {
        try {
            if (n == -129) {
                za_Sub1.aClass98_Sub5Array6077 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eb.D(" + n + ')');
        }
    }
    
    static final String method1679(final int n, final int n2, final byte b, final byte[] array) {
        try {
            if (b > -35) {
                za_Sub1.anInt6076 = 112;
            }
            final char[] array2 = new char[n2];
            int n3 = 0;
            int n4 = n;
            final int i = n2 + n;
            while (i > n4) {
                final int n5 = 0xFF & array[n4++];
                int n6;
                if (~n5 > -129) {
                    if (n5 == 0) {
                        n6 = 65533;
                    }
                    else {
                        n6 = n5;
                    }
                }
                else if (n5 < 192) {
                    n6 = 65533;
                }
                else if (n5 < 224) {
                    if (~n4 > ~i && ~(0xC0 & array[n4]) == 0xFFFFFF7F) {
                        n6 = ((array[n4++] & 0x3F) | (n5 << -915049466 & 0x7C0));
                        if (n6 < 128) {
                            n6 = 65533;
                        }
                    }
                    else {
                        n6 = 65533;
                    }
                }
                else if (~n5 > -241) {
                    if (n4 + 1 < i && (array[n4] & 0xC0) == 0x80 && ~(0xC0 & array[n4 + 1]) == 0xFFFFFF7F) {
                        n6 = ((0x3F & array[n4++]) << -1763835738 | (n5 & 0xF) << -2124097300 | (0x3F & array[n4++]));
                        if (n6 < 2048) {
                            n6 = 65533;
                        }
                    }
                    else {
                        n6 = 65533;
                    }
                }
                else if (n5 < 248) {
                    if (2 + n4 >= i || (array[n4] & 0xC0) != 0x80 || (array[n4 + 1] & 0xC0) != 0x80 || (array[2 + n4] & 0xC0) != 0x80) {
                        n6 = 65533;
                    }
                    else {
                        final int n7 = (0x1C0000 & n5 << -1549523662) | (array[n4++] & 0x3F) << 1368200812 | (array[n4++] & 0x3F) << 1875455398 | (array[n4++] & 0x3F);
                        if (~n7 <= -65537 && ~n7 >= -1114112) {
                            n6 = 65533;
                        }
                        else {
                            n6 = 65533;
                        }
                    }
                }
                else {
                    n6 = 65533;
                }
                array2[n3++] = (char)n6;
            }
            return new String(array2, 0, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eb.E(" + n + ',' + n2 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    za_Sub1(final int n) {
        try {
            this.aNativeHeap6078 = new NativeHeap(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eb.<init>(" + n + ')');
        }
    }
    
    static {
        za_Sub1.aClass98_Sub5Array6077 = new Class98_Sub5[8];
    }
}
